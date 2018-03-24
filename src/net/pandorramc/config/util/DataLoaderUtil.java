package net.pandorramc.config.util;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.configuration.file.FileConfiguration;

public class DataLoaderUtil {

	public static Map<String, String> convertConfigToStringMap(FileConfiguration config){
		Map<String, String> configInfo = new HashMap<>();
		for(String key : config.getKeys(true)){
			configInfo.put(key, config.getString(key));
		}
		return configInfo;
	}
	
	public static Map<String, Object> convertConfigToObjectMap(FileConfiguration config){
		Map<String, Object> configInfo = new HashMap<>();
		for(String key : config.getKeys(true)){
			configInfo.put(key, config.get(key));
		}
		return configInfo;
	}
	
}
