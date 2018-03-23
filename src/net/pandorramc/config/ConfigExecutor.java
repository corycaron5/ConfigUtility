package net.pandorramc.config;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import net.pandorramc.config.task.ConfigLoaderTask;
import net.pandorramc.config.task.ConfigWriterTask;

public class ConfigExecutor {

	private static ExecutorService executor;
	
	protected static void init(){
		executor = Executors.newCachedThreadPool();
	}
	
	protected static void shutdown(){
		executor.shutdown();
	}
	
	public static void executeConfigTask(ConfigWriterTask task){
		executor.execute(task);
	}
	
	public static void executeConfigTask(ConfigLoaderTask task){
		executor.execute(task);
	}
}
