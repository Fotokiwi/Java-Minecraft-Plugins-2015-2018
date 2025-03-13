package org.community.fourWays.Tasks;

import org.community.fourWays.fourWays;

public class fourWaysHeartbeat implements Runnable {
	
	private fourWays plugin;	
	
	private int fourWaysConfigTask = -1;
	private int fourWaysExpGranterTask = -1;
	
	
	public fourWaysHeartbeat(fourWays plugin) {
		this.plugin = plugin;
	}	

	public boolean isFourWaysConfigTaskRunning() {
		return fourWaysConfigTask != -1;
	}

	public boolean isFourWaysExpGranterTaskRunning() {
		return fourWaysExpGranterTask != -1;
	}

	@Override
	public void run() {
		
		//plugin.LogInfo("[DEBUG]: ... bumm bumm ...");
		
		/*
		if(plugin.ecoSystemCore.sizeBuildingList() > 0) {
			fillBuildingQueue();
			plugin.ecoSystemCore.removeBuildingList();
		}
		*/
		
	}
	
	public void toggleFourWaysConfig(boolean on) {		
		if (on && !isFourWaysConfigTaskRunning()) {
			fourWaysConfigTask = plugin.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new fourWaysConfigSaver(plugin), 1200L, plugin.config.getLong("Config.Plugin.HeartBeatInMinutes") * 1200L);
			if (fourWaysConfigTask == -1) {
				plugin.LogError("error: Config Saver Task initialization failed!");
			} else {
				plugin.LogInfo("initialized: Config Saver Task");
			}
			
		} else if (!on && isFourWaysConfigTaskRunning()) {
			plugin.getServer().getScheduler().cancelTask(fourWaysConfigTask);
			fourWaysConfigTask = -1;
		}
	}
	
	public void toggleFourWaysExpGranter(boolean on) {		
		if (on && !isFourWaysExpGranterTaskRunning()) {
			fourWaysExpGranterTask = plugin.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new fourWaysExpGranter(plugin), 1200L, plugin.config.getLong("Config.Plugin.HeartBeatInMinutes") * 1200L);
			if (fourWaysExpGranterTask == -1) {
				plugin.LogError("error: Exp Granter Task initialization failed!");
			} else {
				plugin.LogInfo("initialized: Exp Granter Saver Task");
			}
			
		} else if (!on && isFourWaysExpGranterTaskRunning()) {
			plugin.getServer().getScheduler().cancelTask(fourWaysExpGranterTask);
			fourWaysExpGranterTask = -1;
		}
	}
	
}