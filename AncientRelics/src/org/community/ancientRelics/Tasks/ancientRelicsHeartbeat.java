package org.community.ancientRelics.Tasks;

import org.community.ancientRelics.ancientRelics;

public class ancientRelicsHeartbeat implements Runnable {
	
	private ancientRelics plugin;	
	
	private int ancientRelicsConfigTask = -1;
	
	
	public ancientRelicsHeartbeat(ancientRelics ancientRelics) {
		this.plugin = ancientRelics;
	}
	

	public boolean isAncientRelicsConfigTaskRunning() {
		return ancientRelicsConfigTask != -1;
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
	
	public void toggleAncientRelicsConfig(boolean on) {		
		if (on && !isAncientRelicsConfigTaskRunning()) {
			ancientRelicsConfigTask = plugin.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new ancientRelicsConfigSaver(plugin), 1200L, plugin.config.getLong("Config.PvP.UserSaveInMinutes") * 1200L);
			if (ancientRelicsConfigTask == -1) {
				plugin.LogError("error: Config Saver Task initialization failed!");
			} else {
				plugin.LogInfo("initialized: Config Saver Task");
			}
			
		} else if (!on && isAncientRelicsConfigTaskRunning()) {
			plugin.getServer().getScheduler().cancelTask(ancientRelicsConfigTask);
			ancientRelicsConfigTask = -1;
		}
	}
	
}