package net.pandorramc.config.info;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.SerializationUtils;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.util.Vector;

import net.pandorramc.config.util.ItemSerializer;
import net.pandorramc.config.util.SerializerUtil;

public class ConfigInfo {
	
	private final String key;
	private final Object value;
	private final InfoType type;

	public ConfigInfo(String key, String value){
		this.key = key;
		this.value = new String(value);
		this.type = InfoType.STRING;
	}

	public ConfigInfo(String key, byte value){
		this.key = key;
		byte copy = new Byte(value);
		this.value = copy;
		this.type = InfoType.BYTE;
	}

	public ConfigInfo(String key, short value){
		this.key = key;
		short copy = new Short(value);
		this.value = copy;
		this.type = InfoType.SHORT;
	}

	public ConfigInfo(String key, int value){
		this.key = key;
		int copy = new Integer(value);
		this.value = copy;
		this.type = InfoType.INT;
	}

	public ConfigInfo(String key, float value){
		this.key = key;
		float copy = new Float(value);
		this.value = copy;
		this.type = InfoType.FLOAT;
	}

	public ConfigInfo(String key, long value){
		this.key = key;
		long copy = new Long(value);
		this.value = copy;
		this.type = InfoType.LONG;
	}

	public ConfigInfo(String key, double value){
		this.key = key;
		double copy = new Double(value);
		this.value = copy;
		this.type = InfoType.DOUBLE;
	}

	public ConfigInfo(String key, char value){
		this.key = key;
		char copy = new Character(value);
		this.value = copy;
		this.type = InfoType.CHAR;
	}

	public ConfigInfo(String key, boolean value){
		this.key = key;
		boolean copy = new Boolean(value);
		this.value = copy;
		this.type = InfoType.BOOLEAN;
	}

	public <T> ConfigInfo(String key, List<T> value){
		this.key = key;
		List<T> copy = new ArrayList<>(value);
		this.value = copy;
		this.type = InfoType.LIST;
	}

	public ConfigInfo(String key, ItemStack value){
		this.key = key;
		this.value = value.clone();
		this.type = InfoType.ITEMSTACK;
	}

	public ConfigInfo(String key, ItemStack[] value){
		this.key = key;
		this.value = value.clone();
		this.type = InfoType.ITEMARRAY;
	}

	public ConfigInfo(String key, Serializable value){
		this.key = key;
		Serializable copy = SerializationUtils.clone(value);
		this.value = copy;
		this.type = InfoType.SERIALIZABLE;
	}

	public ConfigInfo(String key, Location value){
		this.key = key;
		this.value = value.clone();
		this.type = InfoType.LOCATION;
	}

	public ConfigInfo(String key, Collection<PotionEffect> value){
		this.key = key;
		Collection<PotionEffect> copy = new HashSet<>(value);
		this.value = copy;
		this.type = InfoType.POTIONLIST;
	}

	public ConfigInfo(String key, Map<String, String> value){
		this.key = key;
		Map<String, String> copy = new HashMap<>(value);
		this.value = copy;
		this.type = InfoType.MAP;
	}

	public ConfigInfo(String key, byte[] value){
		this.key = key;
		byte[] copy = value.clone();
		this.value = copy;
		this.type = InfoType.BYTEARRAY;
	}
	
	public ConfigInfo(String key, Vector value){
		this.key = key;
		this.value = value.clone();
		this.type = InfoType.VECTOR;
	}
	
	public ConfigInfo(String key, Color value){
		this.key = key;
		this.value = Color.fromRGB(value.asRGB());
		this.type = InfoType.COLOR;
	}
	
	@SuppressWarnings("unchecked")
	public void writeToConfig(FileConfiguration config){
		switch(type){
		case STRING:
			config.set(key, (String) value);
			break;
		case BYTE:
			config.set(key, (byte) value);
			break;
		case SHORT:
			config.set(key, (short) value);
			break;
		case INT:
			config.set(key, (int) value);
			break;
		case FLOAT:
			config.set(key, (float) value);
			break;
		case LONG:
			config.set(key, (long) value);
			break;
		case DOUBLE:
			config.set(key, (double) value);
			break;
		case CHAR:
			config.set(key, (char) value);
			break;
		case BOOLEAN:
			config.set(key, (boolean) value);
			break;
		case LIST:
			config.set(key, (List<?>) value);
			break;
		case ITEMSTACK:
			config.set(key, (ItemStack) value);
			break;
		case ITEMARRAY:
			String itemArray = ItemSerializer.itemStackArrayToBase64((ItemStack[])value);
			config.set(key, itemArray);
			break;
		case SERIALIZABLE:
			config.set(key, SerializerUtil.serializeByteArray(SerializerUtil.serializeObject(((Serializable)value))));
			break;
		case LOCATION:
			config.set(key, SerializerUtil.serializeLoc((Location)value));
			break;
		case POTIONLIST:
			config.set(key, SerializerUtil.serializePotionEffects((Collection<PotionEffect>)value));
			break;
		case MAP:
			config.set(key, SerializerUtil.serializeMap((Map<String, String>)value));
			break;
		case BYTEARRAY:
			config.set(key, SerializerUtil.serializeByteArray((byte[])value));
			break;
		case VECTOR:
			config.set(key, (Vector)value);
			break;
		case COLOR:
			config.set(key, (Color)value);
			break;
		}
	}
	
}
