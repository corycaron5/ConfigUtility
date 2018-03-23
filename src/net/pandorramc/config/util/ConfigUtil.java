package net.pandorramc.config.util;

import java.io.File;
import java.io.IOException;
import java.net.URI;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import net.pandorramc.config.Main;

public class ConfigUtil {
	
	public static FileConfiguration createDistantConfig(String name, URI directory)
			throws IOException {
		File dir = new File(directory);
		// this is optional. Only needed if you want to put it into a
		// subdirectory. If not, and you just wanna put your file in the Main.plugin
		// data folder, no need to add "+File.separator+subdirectory" to the
		// constructor parameter. You can remove the second parameter even. If
		// you wanna get fancy, check for null, or overload the method.
		if (!dir.isDirectory()) {
			dir.mkdirs(); // creates the directory folders if they dont exist.
		}
		File file = new File(dir, name + ".yml"); // initializes the file object
		if (!file.exists()) {
			file.createNewFile(); // creates a new file if it doesnt exist
		}
		return YamlConfiguration.loadConfiguration(file);// returns the newly
															// created
															// configuration
															// object.
	}

	public static void saveDistantConfig(FileConfiguration config, String name, URI directory) throws IOException {
		// gets directory
		File dir = new File(directory);
		if (!dir.isDirectory()) {
			dir.mkdirs();
		}
		// gets file
		File file = new File(dir, name + ".yml");
		// saves to file
		config.save(file);
	}

	public static FileConfiguration getDistantConfig(String name, URI directory) {
		File dir = new File(directory);
		if (!dir.isDirectory()) {
			System.out.println("Dir is invalid");
			return null; // File directory doesnt exist, file cant exist. Return
							// null.
		}
		File file = new File(dir, name + ".yml");
		if (!file.exists()) {
			System.out.println("File does not exist");
			return null; // File doesnt exist, Return null.
		}
		return YamlConfiguration.loadConfiguration(file); // file found, load
															// into config and
															// return it.
	}
	
	public static FileConfiguration createConfig(String name, String subdirectory)
			throws IOException {
		File dir = new File(Main.plugin.getDataFolder() + File.separator
				+ subdirectory);
		// this is optional. Only needed if you want to put it into a
		// subdirectory. If not, and you just wanna put your file in the Main.plugin
		// data folder, no need to add "+File.separator+subdirectory" to the
		// constructor parameter. You can remove the second parameter even. If
		// you wanna get fancy, check for null, or overload the method.
		if (!dir.isDirectory()) {
			dir.mkdirs(); // creates the directory folders if they dont exist.
		}
		File file = new File(dir, name + ".yml"); // initializes the file object
		if (!file.exists()) {
			file.createNewFile(); // creates a new file if it doesnt exist
		}
		return YamlConfiguration.loadConfiguration(file);// returns the newly
															// created
															// configuration
															// object.
	}

	public static void saveConfig(FileConfiguration config, String name, String subdirectory) throws IOException {
		// gets directory
		File dir = new File(Main.plugin.getDataFolder() + File.separator + subdirectory);
		if (!dir.isDirectory()) {
			dir.mkdirs();
		}
		// gets file
		File file = new File(dir, name + ".yml");
		// saves to file
		config.save(file);
	}

	public static FileConfiguration getConfig(String name, String subdirectory) {
		File dir = new File(Main.plugin.getDataFolder() + File.separator + subdirectory);
		if (!dir.isDirectory()) {
			System.out.println("Dir is invalid");
			return null; // File directory doesnt exist, file cant exist. Return
							// null.
		}
		File file = new File(dir, name + ".yml");
		if (!file.exists()) {
			System.out.println("File does not exist");
			return null; // File doesnt exist, Return null.
		}
		return YamlConfiguration.loadConfiguration(file); // file found, load
															// into config and
															// return it.
	}

	public static FileConfiguration createConfig(String name) throws IOException {
		File dir = Main.plugin.getDataFolder();
		if (!dir.isDirectory()) {
			dir.mkdirs();
		}
		File file = new File(Main.plugin.getDataFolder(), name + ".yml");
		// initializes the file object
		if (!file.exists()) {
			file.createNewFile(); // creates a new file if it doesnt exist
		}
		return YamlConfiguration.loadConfiguration(file);// returns the newly
															// created
															// configuration
															// object.
	}

	public static void saveConfig(FileConfiguration config, String name) throws IOException {
		// gets file
		File file = new File(Main.plugin.getDataFolder(), name + ".yml");
		// saves to file
		config.save(file);
	}

	public static FileConfiguration getConfig(String name) {
		File file = new File(Main.plugin.getDataFolder(), name + ".yml");
		if (!file.exists()) {
			return null; // File doesnt exist, Return null.
		}
		return YamlConfiguration.loadConfiguration(file); // file found, load
															// into config and
															// return it.
	}
	
	public static FileConfiguration createConfig(JavaPlugin plugin, String name, String subdirectory)
			throws IOException {
		File dir = new File(plugin.getDataFolder() + File.separator
				+ subdirectory);
		// this is optional. Only needed if you want to put it into a
		// subdirectory. If not, and you just wanna put your file in the Main.plugin
		// data folder, no need to add "+File.separator+subdirectory" to the
		// constructor parameter. You can remove the second parameter even. If
		// you wanna get fancy, check for null, or overload the method.
		if (!dir.isDirectory()) {
			dir.mkdirs(); // creates the directory folders if they dont exist.
		}
		File file = new File(dir, name + ".yml"); // initializes the file object
		if (!file.exists()) {
			file.createNewFile(); // creates a new file if it doesnt exist
		}
		return YamlConfiguration.loadConfiguration(file);// returns the newly
															// created
															// configuration
															// object.
	}

	public static void saveConfig(FileConfiguration config, JavaPlugin plugin, String name, String subdirectory) throws IOException {
		// gets directory
		File dir = new File(plugin.getDataFolder() + File.separator + subdirectory);
		if (!dir.isDirectory()) {
			dir.mkdirs();
		}
		// gets file
		File file = new File(dir, name + ".yml");
		// saves to file
		config.save(file);
	}

	public static FileConfiguration getConfig(JavaPlugin plugin, String name, String subdirectory) {
		File dir = new File(plugin.getDataFolder() + File.separator + subdirectory);
		if (!dir.isDirectory()) {
			System.out.println("Dir is invalid");
			return null; // File directory doesnt exist, file cant exist. Return
							// null.
		}
		File file = new File(dir, name + ".yml");
		if (!file.exists()) {
			System.out.println("File does not exist");
			return null; // File doesnt exist, Return null.
		}
		return YamlConfiguration.loadConfiguration(file); // file found, load
															// into config and
															// return it.
	}

	public static FileConfiguration createConfig(JavaPlugin plugin, String name) throws IOException {
		File dir = plugin.getDataFolder();
		if (!dir.isDirectory()) {
			dir.mkdirs();
		}
		File file = new File(Main.plugin.getDataFolder(), name + ".yml");
		// initializes the file object
		if (!file.exists()) {
			file.createNewFile(); // creates a new file if it doesnt exist
		}
		return YamlConfiguration.loadConfiguration(file);// returns the newly
															// created
															// configuration
															// object.
	}

	public static void saveConfig(FileConfiguration config, JavaPlugin plugin, String name) throws IOException {
		// gets file
		File file = new File(plugin.getDataFolder(), name + ".yml");
		// saves to file
		config.save(file);
	}

	public static FileConfiguration getConfig(JavaPlugin plugin, String name) {
		File file = new File(plugin.getDataFolder(), name + ".yml");
		if (!file.exists()) {
			return null; // File doesnt exist, Return null.
		}
		return YamlConfiguration.loadConfiguration(file); // file found, load
															// into config and
															// return it.
	}
	
}
