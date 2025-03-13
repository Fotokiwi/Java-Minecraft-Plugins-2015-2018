package org.community.ancientRelics.Core;

import java.util.ArrayList;
import java.util.List;

import org.community.ancientRelics.ancientRelics;
import org.community.ancientRelics.Groups.Classes.Groups;

public class ancientRelicsCore {
	
	private ancientRelics plugin;
	
	private int ancientRelicsHeartbeatTask = -1;
	
	public List<Groups> groupsList = new ArrayList<Groups>();
		
	public ancientRelicsCore(ancientRelics ancientRelics) {
		this.plugin = ancientRelics;
	}

	public boolean isAncientRelicsHeartbeatTaskRunning() {
		return ancientRelicsHeartbeatTask != -1;
	}
	
	public void toggleAncientRelicsHeartbeat(boolean on) {		
		if (on && !isAncientRelicsHeartbeatTaskRunning()) {
			ancientRelicsHeartbeatTask = plugin.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, plugin.aRHeartBeat, 1200L, plugin.config.getLong("Config.Plugin.HeartBeatInMinutes", 2) * 1200L);
				
			plugin.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
				@Override
				public void run() {
					plugin.aRCache.saveGroupsList();
				}
			}, 1200L, 1200L);
			
			if (ancientRelicsHeartbeatTask == -1) {
				plugin.LogError("error: Heartbeat initialization failed!");
			} else {
				plugin.aRHeartBeat.toggleAncientRelicsConfig(true);
				plugin.LogInfo("initialized: Heartbeat");
			}
			
		} else if (!on && isAncientRelicsHeartbeatTaskRunning()) {
			plugin.getServer().getScheduler().cancelTask(ancientRelicsHeartbeatTask);
			plugin.aRHeartBeat.toggleAncientRelicsConfig(false);
			ancientRelicsHeartbeatTask = -1;
		}
	}
	
	public void addGroupsList(Groups group) {
		groupsList.add(group);
	}
	
	public void removeGroupsList(Groups group) {
		groupsList.remove(group);
	}
	
	public Groups getGroupsList(int index) {
		return groupsList.get(index);
	}
	
	public int sizeGroupsList() {
		return groupsList.size();
	}
	
	public List<Groups> getCompleteGroupsList() {
		return groupsList;
	}

}