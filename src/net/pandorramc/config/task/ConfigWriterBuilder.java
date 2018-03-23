package net.pandorramc.config.task;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.plugin.java.JavaPlugin;

import net.pandorramc.config.info.ConfigInfo;

public class ConfigWriterBuilder {
	private List<ConfigInfo> information;
	private final String name, subdirectory;
	private final URI directory;
	private final JavaPlugin plugin;
	
	public ConfigWriterBuilder(String name){
		this.information = new ArrayList<>();
		this.name = name;
		this.subdirectory = null;
		this.directory = null;
		this.plugin = null;
	}
	
	public ConfigWriterBuilder(String name, String subdirectory){
		this.information = new ArrayList<>();
		this.name = name;
		this.subdirectory = subdirectory;
		this.directory = null;
		this.plugin = null;
	}
	
	public ConfigWriterBuilder(String name, JavaPlugin plugin){
		this.information = new ArrayList<>();
		this.name = name;
		this.subdirectory = null;
		this.directory = null;
		this.plugin = plugin;
	}
	
	public ConfigWriterBuilder(String name, JavaPlugin plugin, String subdirectory){
		this.information = new ArrayList<>();
		this.name = name;
		this.subdirectory = subdirectory;
		this.directory = null;
		this.plugin = plugin;
	}
	
	public ConfigWriterBuilder(String name, URI directory){
		this.information = new ArrayList<>();
		this.name = name;
		this.subdirectory = null;
		this.directory = directory;
		this.plugin = null;
	}
	
	public ConfigWriterBuilder(String name, JavaPlugin plugin, String subdirectory, URI directory, ConfigInfo... info){
		this.information = Arrays.asList(info);
		this.name = name;
		this.subdirectory = subdirectory;
		this.directory = directory;
		this.plugin = plugin;
	}
	
	public ConfigWriterBuilder addInfo(ConfigInfo... info){
		information.addAll(Arrays.asList(info));
		return this;
	}
	
	public List<ConfigInfo> getInfo(){
		return this.information;
	}
	
	public ConfigWriterBuilder removeInfo(ConfigInfo... info){
		information.removeAll(Arrays.asList(info));
		return this;
	}
	
	public ConfigWriterTask buildTask(){
		ConfigWriterTask task;
		task = new ConfigWriterTask(name, directory, subdirectory, plugin);
		for(ConfigInfo info : information)task.addInfo(info);
		return task;
	}
}
