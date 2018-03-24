package net.pandorramc.config.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class SerializerUtil {

	public static String serializeLoc(Location loc){
		String data = loc.getWorld().getName()+","+loc.getX()+","+loc.getY()+","+loc.getZ()+","+loc.getYaw()+","+loc.getPitch();
		return data;
	}
	
	public static Location deserializeLoc(String data){
		String string[] = data.split(",");
		double x, y, z;
		float yaw, pitch;
		x = Double.parseDouble(string[1]);
		y = Double.parseDouble(string[2]);
		z = Double.parseDouble(string[3]);
		yaw = Float.parseFloat(string[4]);
		pitch = Float.parseFloat(string[5]);
		Location loc = new Location(Bukkit.getWorld(string[0]), x, y, z, yaw, pitch);
		return loc;
	}
	
	
	public static String serializeMap(Map<String, String> map){
		StringBuilder builder = new StringBuilder();
		for(String string : map.keySet()){
			builder.append(string + "===" + map.get(string) + "|||");
		}
		return builder.toString();
	}
	
	public static Map<String, String> deserializeMap(String serialized){
		HashMap<String, String> extraData = new HashMap<>();
		String[] data = serialized.split("|||");
		for(String string : data){
			String[] toMap = string.split("===");
			extraData.put(toMap[0], toMap[1]);
		}
		return extraData;
	}
	
	public static String serializePotionEffects(Collection<PotionEffect> effects){
		StringBuilder builder = new StringBuilder();
		for(PotionEffect pot : effects){
			builder.append(pot.getType()+","+pot.getAmplifier()+","+pot.getDuration()+"|");
	    }
		return builder.toString();
	}
	
	public static Collection<PotionEffect> deserializePotionEffects(String serialized){
		Collection<PotionEffect> pots = new HashSet<>();
		String[] effects = serialized.split("|");
		for(String string : effects){
			String[] pot = string.split(",");
			pots.add(new PotionEffect(PotionEffectType.getByName(pot[0]), Integer.parseInt(pot[1]), Integer.parseInt(pot[2])));
		}
		return pots;
	}
	
	public static String serializeByteArray(byte[] value){
		return Base64.encodeBase64String(value);
	}
	
	public static byte[] deserializeByteArray(String value){
		return Base64.decodeBase64(value);
	}
	
	private static void serialize(final Serializable obj, final OutputStream outputStream) {
		try (ObjectOutputStream out = new ObjectOutputStream(outputStream)){
			out.writeObject(obj);
		} catch (final IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public static byte[] serializeObject(final Serializable obj) {
		final ByteArrayOutputStream baos = new ByteArrayOutputStream(512);
		serialize(obj, baos);
		return baos.toByteArray();
	}
	
	private static <T> T deserialize(final InputStream inputStream) {
		try (ObjectInputStream in = new ObjectInputStream(inputStream)) {
			@SuppressWarnings("unchecked")
			final T obj = (T) in.readObject();
			return obj;
		} catch (final ClassNotFoundException | IOException ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public static <T> T deserialize(final byte[] objectData) {
		return deserialize(new ByteArrayInputStream(objectData));
	}
	
	public static <T> T deserializeObject(final String data){
		return deserialize(deserializeByteArray(data));
	}
}
