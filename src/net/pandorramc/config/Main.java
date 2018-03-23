package net.pandorramc.config;

import org.bukkit.plugin.java.JavaPlugin;


public class Main extends JavaPlugin{

	public static Main plugin;
	
	public void onEnable(){	  
		plugin = this;
		
		ConfigExecutor.init();
	}
	
	public void onDisable(){
		ConfigExecutor.shutdown();
	}
	
}
