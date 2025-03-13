package org.community.newSettlers.Tasks;

import java.util.List;

import org.community.newSettlers.newSettlers;
import org.community.newSettlers.Town.Town;

public class newSettlersHeartbeat implements Runnable {
	
	private newSettlers plugin;	
	
	private int newSettlersConfigTask = -1;
	
	
	public newSettlersHeartbeat(newSettlers plugin) {
		this.plugin = plugin;
	}	

	public boolean isNewSettlersConfigTaskRunning() {
		return newSettlersConfigTask != -1;
	}

	@Override
	public void run() {
		
		List<Town> townList = plugin.nSCore.getCompleteTownList();
		Town town = null;
		long currentTimestamp = System.currentTimeMillis();
		long townTimestamp = 0;
		
		for(int i = 0; i < townList.size(); i++) {
			town = townList.get(i);
			townTimestamp = town.getUpkeepTime();
			if(currentTimestamp >= (townTimestamp + (86400000 * plugin.config.getInt("System.UpkeepIntervalInDays", 1)))){
				plugin.LogInfo(town.getName() + " wurde soeben von der Upkeep-Routine positiv geprüft.");
				town.runUpkeepCalculation(null, false);
				town.setUpkeepTime();
			} else {
				//plugin.LogInfo(town.getName() + "-Upkeep Negativprüfung");
			}
		}
		
	}
	
	public void toggleNewSettlersConfig(boolean on) {		

		if (on && !isNewSettlersConfigTaskRunning()) {
			newSettlersConfigTask = plugin.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new newSettlersDaytimeToggle(plugin), 1200L, plugin.config.getLong("Config.Plugin.DayTimeToggleInSeconds", 30) * 20L);
			if (newSettlersConfigTask == -1) {
				plugin.LogError("error: Config Saver Task initialization failed!");
			} else {
				plugin.LogInfo("initialized: Daytime Toggle Task");
			}
			
		} else if (!on && isNewSettlersConfigTaskRunning()) {
			plugin.getServer().getScheduler().cancelTask(newSettlersConfigTask);
			newSettlersConfigTask = -1;
		}
	}
	
}