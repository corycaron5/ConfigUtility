package net.pandorramc.config.task;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import net.pandorramc.config.info.ConfigInfo;
import net.pandorramc.config.util.ConfigUtil;

public class ConfigWriterTask implements Runnable {

	private List<ConfigInfo> information;
	private FileConfiguration config;
	private final String name, subdirectory;
	private final URI directory;
	private final JavaPlugin plugin;
	private final byte configType;
	
	public static ConfigWriterTask createConfigWriterTask(String name, JavaPlugin plugin, String subdirectory, URI directory, 
			ConfigInfo... info){
		return new ConfigWriterBuilder(name, plugin, subdirectory, directory, info).buildTask();
	}
	
	public static ConfigWriterTask createConfigWriterTask(String name, JavaPlugin plugin, String subdirectory, URI directory, 
			FileConfiguration config){
		return new ConfigWriterTask(name, directory, subdirectory, plugin, config);
	}
	
	protected ConfigWriterTask(String name){
		this.information = new ArrayList<>();
		this.name = name;
		this.subdirectory = null;
		this.directory = null;
		this.plugin = null;
		this.configType = 0;
	}
	
	protected ConfigWriterTask(String name, String subdirectory){
		this.information = new ArrayList<>();
		this.name = name;
		this.subdirectory = subdirectory;
		this.directory = null;
		this.plugin = null;
		this.configType = 1;
	}
	
	protected ConfigWriterTask(String name, JavaPlugin plugin){
		this.information = new ArrayList<>();
		this.name = name;
		this.subdirectory = null;
		this.directory = null;
		this.plugin = plugin;
		this.configType = 2;
	}
	
	protected ConfigWriterTask(String name, JavaPlugin plugin, String subdirectory){
		this.information = new ArrayList<>();
		this.name = name;
		this.subdirectory = subdirectory;
		this.directory = null;
		this.plugin = plugin;
		this.configType = 3;
	}
	
	protected ConfigWriterTask(String name, URI directory){
		this.information = new ArrayList<>();
		this.name = name;
		this.subdirectory = null;
		this.directory = directory;
		this.plugin = null;
		this.configType = 4;
	}
	
	protected ConfigWriterTask(String name, URI directory, String subdirectory, JavaPlugin plugin){
		this.information = new ArrayList<>();
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
	
	protected ConfigWriterTask(String name, URI directory, String subdirectory, JavaPlugin plugin, FileConfiguration config){
		this.config = config;
		this.information = new ArrayList<>();
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
	
	@Override
	public void run() {
		if(config==null){
			try {
				setConfig();
			} catch (IOException e) {
				e.printStackTrace();
			}
			writeInfoToConfig();
		}else{
			try {
				saveConfig();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	protected void addInfo(ConfigInfo... info){
		for(ConfigInfo i : info)information.add(i);
	}
	
	private void setConfig() throws IOException{
		switch(configType){
		case 0:
			if(ConfigUtil.getConfig(name)!=null){
				this.config = ConfigUtil.getConfig(name);
			}else{
				this.config = ConfigUtil.createConfig(name);
			}
			return;
		case 1:
			if(ConfigUtil.getConfig(name, subdirectory)!=null){
				this.config = ConfigUtil.getConfig(name, subdirectory);
			}else{
				this.config = ConfigUtil.createConfig(name, subdirectory);
			}
			return;
		case 2:
			if(ConfigUtil.getConfig(plugin, name)!=null){
				this.config = ConfigUtil.getConfig(plugin, name);
			}else{
				this.config = ConfigUtil.createConfig(plugin, name);
			}
			return;
		case 3:
			if(ConfigUtil.getConfig(plugin, name, subdirectory)!=null){
				this.config = ConfigUtil.getConfig(plugin, name, subdirectory);
			}else{
				this.config = ConfigUtil.createConfig(plugin, name, subdirectory);
			}
			return;
		case 4:
			if(ConfigUtil.getDistantConfig(name, directory)!=null){
				this.config = ConfigUtil.getDistantConfig(name, directory);
			}else{
				this.config = ConfigUtil.createDistantConfig(name, directory);
			}
			return;
		default:
			return;
		}
	}
	
	private void writeInfoToConfig(){
		for(ConfigInfo info : information)info.writeToConfig(config);
		try {
			saveConfig();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void saveConfig() throws IOException{
		switch(configType){
		case 0:
			ConfigUtil.saveConfig(this.config, this.name);
			return;
		case 1:
			ConfigUtil.saveConfig(this.config, this.name, this.subdirectory);
			return;
		case 2:
			ConfigUtil.saveConfig(this.config, this.plugin, this.name);
			return;
		case 3:
			ConfigUtil.saveConfig(this.config, this.plugin, this.name, this.subdirectory);
			return;
		case 4:
			ConfigUtil.saveDistantConfig(this.config, this.name, this.directory);
			return;
		default:
			return;
		}
	}

}
