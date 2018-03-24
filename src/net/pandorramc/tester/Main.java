package net.pandorramc.tester;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import net.pandorramc.config.ConfigExecutor;
import net.pandorramc.config.info.ConfigInfo;
import net.pandorramc.config.task.ConfigLoaderTask;
import net.pandorramc.config.task.ConfigWriterTask;
import net.pandorramc.config.util.ConfigUtil;
import net.pandorramc.config.util.DataLoaderUtil;

public class Main extends JavaPlugin{

	public static Main plugin;
	@SuppressWarnings("unused")
	private FileConfiguration test1,test2,test4,test5;
	private Map<String, String> test3,test6;
	
	private Map<String, Integer> test1Map,test3Map,test4Map,test6Map;
	
	public void onEnable(){	  
		plugin = this;
		
		loadTestConfigs();
		
		/*try {
			generateTestConfigs();
		} catch (IOException e) {
			e.printStackTrace();
		}*/
	}
	
	public void onDisable(){
		
	}
	
	public void loadTestConfigs(){
		{
			long test1Start = System.currentTimeMillis();
			test1 = ConfigUtil.getConfig("test1");
			long test1End = System.currentTimeMillis();
			double time = test1End - test1Start;
			Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_AQUA+""+(time/1000)+" seconds that main thread was locked for test1");
		}
		{
			ConfigLoaderTask task = ConfigLoaderTask.createConfigLoaderTask("test2", null, null, null);
			ConfigExecutor.executeConfigTask(task);
			Bukkit.getScheduler().runTaskLater(this, new Runnable(){
				
				@Override
				public void run(){
					long test2Start = System.currentTimeMillis();
					if(task.isConfigLoaded()){
						test2 = task.getConfig();
					}
					long test2End = System.currentTimeMillis();
					double time = test2End - test2Start;
					Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_AQUA+""+(time/1000)+" seconds that main thread was locked for test2");
				}
				
			}, 100);
		}
		{
			long test4Start = System.currentTimeMillis();
			test4 = ConfigUtil.getConfig("test4");
			long test4End = System.currentTimeMillis();
			double time = test4End - test4Start;
			Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_AQUA+""+(time/1000)+" seconds that main thread was locked for test4");
		}
		{
			ConfigLoaderTask task = ConfigLoaderTask.createConfigLoaderTask("test5", null, null, null);
			ConfigExecutor.executeConfigTask(task);
			Bukkit.getScheduler().runTaskLater(this, new Runnable(){
				
				@Override
				public void run(){
					long test5Start = System.currentTimeMillis();
					if(task.isConfigLoaded()){
						test5 = task.getConfig();
					}
					long test5End = System.currentTimeMillis();
					double time = test5End - test5Start;
					Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_AQUA+""+(time/1000)+" seconds that main thread was locked for test5");
				}
				
			}, 100);
		}
		{
			long test3Start = System.currentTimeMillis();
			test3 = DataLoaderUtil.convertConfigToStringMap(ConfigUtil.getConfig("test1"));
			long test3End = System.currentTimeMillis();
			double time = test3End - test3Start;
			Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_AQUA+""+(time/1000)+" seconds that main thread was locked for test3");
		}
		{
			long test6Start = System.currentTimeMillis();
			test6 = DataLoaderUtil.convertConfigToStringMap(ConfigUtil.getConfig("test6"));
			long test6End = System.currentTimeMillis();
			double time = test6End - test6Start;
			Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_AQUA+""+(time/1000)+" seconds that main thread was locked for test6");
		}
		{
			Bukkit.getScheduler().runTaskLater(this, new Runnable(){
				
				@Override
				public void run(){
					{
						long test1Start = System.currentTimeMillis();
						test1Map = new HashMap<>();
						for(String string : test1.getKeys(true)){
							test1Map.put(string, test1.getInt(string));
						}
						long test1End = System.currentTimeMillis();
						double time = test1End - test1Start;
						Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_AQUA+""+(time/1000)+" seconds that main thread was locked to read all entries from test1");
					}
					{
						long test3Start = System.currentTimeMillis();
						test3Map = new HashMap<>();
						for(String string : test3.keySet()){
							test3Map.put(string, Integer.getInteger(string));
						}
						long test3End = System.currentTimeMillis();
						double time = test3End - test3Start;
						Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_AQUA+""+(time/1000)+" seconds that main thread was locked to read all entries from test3");
					}
					{
						long test4Start = System.currentTimeMillis();
						test4Map = new HashMap<>();
						for(String string : test4.getKeys(true)){
							test4Map.put(string, test4.getInt(string));
						}
						long test4End = System.currentTimeMillis();
						double time = test4End - test4Start;
						Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_AQUA+""+(time/1000)+" seconds that main thread was locked to read all entries from test4");
					}
					{
						long test6Start = System.currentTimeMillis();
						test6Map = new HashMap<>();
						for(String string : test6.keySet()){
							test6Map.put(string, Integer.getInteger(string));
						}
						long test6End = System.currentTimeMillis();
						double time = test6End - test6Start;
						Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_AQUA+""+(time/1000)+" seconds that main thread was locked to read all entries from test6");
					}
				}
				
			}, 120);
		}
	}
	
	public void generateTestConfigs() throws IOException{
		{
			long test1Start = System.currentTimeMillis();
			FileConfiguration test1 = ConfigUtil.createConfig("test1");
			for(int i = 0; i < 100000; i++){
				test1.set(i+"", i);
			}
			ConfigUtil.saveConfig(test1, "test1");
			long test1End = System.currentTimeMillis();
			double time = test1End - test1Start;
			Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA +""+ time);
			Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_AQUA+""+(time/1000)+" seconds that main thread was locked for test1");
		}
		{
			long test2Start = System.currentTimeMillis();
			FileConfiguration test2 = ConfigUtil.createConfig("test2");
			for(int i = 0; i < 100000; i++){
				test2.set(i+"", i);
			}
			ConfigExecutor.executeConfigTask(ConfigWriterTask.createConfigWriterTask("test2", null, null, null, test2));
			long test2End = System.currentTimeMillis();
			double time = test2End - test2Start;
			Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA +""+ time);
			Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_AQUA+""+(time/1000)+" seconds that main thread was locked for test2");
		}
		{
			long test3Start = System.currentTimeMillis();
			ConfigInfo[] information = new ConfigInfo[100000];
			for(int i = 0; i < 100000; i++){
				information[i] = new ConfigInfo(i+"", i);
			}
			ConfigExecutor.executeConfigTask(ConfigWriterTask.createConfigWriterTask("test3", null, null, null, information));
			long test3End = System.currentTimeMillis();
			double time = test3End - test3Start;
			Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA +""+ time);
			Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_AQUA+""+(time/1000)+" seconds that main thread was locked for test3");
		}
		{
			long test4Start = System.currentTimeMillis();
			FileConfiguration test4 = ConfigUtil.createConfig("test4");
			for(int i = 0; i < 10000; i++){
				test4.set(i+"", i);
			}
			ConfigUtil.saveConfig(test4, "test4");
			long test4End = System.currentTimeMillis();
			double time = test4End - test4Start;
			Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA +""+ time);
			Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_AQUA+""+(time/1000)+" seconds that main thread was locked for test4");
		}
		{
			long test5Start = System.currentTimeMillis();
			FileConfiguration test5 = ConfigUtil.createConfig("test5");
			for(int i = 0; i < 10000; i++){
				test5.set(i+"", i);
			}
			ConfigExecutor.executeConfigTask(ConfigWriterTask.createConfigWriterTask("test5", null, null, null, test5));
			long test5End = System.currentTimeMillis();
			double time = test5End - test5Start;
			Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA +""+ time);
			Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_AQUA+""+(time/1000)+" seconds that main thread was locked for test5");
		}
		{
			long test6Start = System.currentTimeMillis();
			ConfigInfo[] information = new ConfigInfo[10000];
			for(int i = 0; i < 10000; i++){
				information[i] = new ConfigInfo(i+"", i);
			}
			ConfigExecutor.executeConfigTask(ConfigWriterTask.createConfigWriterTask("test6", null, null, null, information));
			long test6End = System.currentTimeMillis();
			double time = test6End - test6Start;
			Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA +""+ time);
			Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_AQUA+""+(time/1000)+" seconds that main thread was locked for test6");
		}
	}
	
}
