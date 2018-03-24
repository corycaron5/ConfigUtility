package net.pandorramc.config.task;

import java.net.URI;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import net.pandorramc.config.util.ConfigUtil;

public class ConfigLoaderTask implements Runnable{

	private FileConfiguration config;
	private final String name, subdirectory;
	private final URI directory;
	private final JavaPlugin plugin;
	private final byte configType;
	
	public static ConfigLoaderTask createConfigLoaderTask(final String name, final JavaPlugin plugin, final String subdirectory, final URI directory){
		return new ConfigLoaderTask(name, directory, subdirectory, plugin);
	}
	
	protected ConfigLoaderTask(String name){
		this.name = name;
		this.subdirectory = null;
		this.directory = null;
		this.plugin = null;
		this.configType = 0;
	}
	
	protected ConfigLoaderTask(String name, String subdirectory){
		this.name = name;
		this.subdirectory = subdirectory;
		this.directory = null;
		this.plugin = null;
		this.configType = 1;
	}
	
	protected ConfigLoaderTask(String name, JavaPlugin plugin){
		this.name = name;
		this.subdirectory = null;
		this.directory = null;
		this.plugin = plugin;
		this.configType = 2;
	}
	
	protected ConfigLoaderTask(String name, JavaPlugin plugin, String subdirectory){
		this.name = name;
		this.subdirectory = subdirectory;
		this.directory = null;
		this.plugin = plugin;
		this.configType = 3;
	}
	
	protected ConfigLoaderTask(String name, URI directory){
		this.name = name;
		this.subdirectory = null;
		this.directory = directory;
		this.plugin = null;
		this.configType = 4;
	}
	
	protected ConfigLoaderTask(String name, URI directory, String subdirectory, JavaPlugin plugin){
		this.name = name;
		this.subdirectory = subdirectory;
		this.directory = directory;
		this.plugin = plugin;
		if(directory!=null){
			configType = 4;
		}else{
			if(plugin!=null){
				if(subdirectory!=null){
					configType = 3;
				}else{
					configType = 2;
				}
			}
			else if(subdirectory!=null){
				configType = 1;
			}
			else{
				configType = 0;
			}
		}
	}
	
	private void loadConfig(){
		switch(configType){
		case 0:
			config = ConfigUtil.getConfig(this.name);
			return;
		case 1:
			config = ConfigUtil.getConfig(this.name, this.subdirectory);
			return;
		case 2:
			config = ConfigUtil.getConfig(this.plugin, this.name);
			return;
		case 3:
			config = ConfigUtil.getConfig(this.plugin, this.name, this.subdirectory);
			return;
		case 4:
			config = ConfigUtil.getDistantConfig(this.name, this.directory);
			return;
		default:
			return;
		}
	}
	
	@Override
	public void run() {
		loadConfig();
	}
	
	public boolean isConfigLoaded(){
		return config!=null;
	}
	
	public FileConfiguration getConfig(){
		return config;
	}
	
}
