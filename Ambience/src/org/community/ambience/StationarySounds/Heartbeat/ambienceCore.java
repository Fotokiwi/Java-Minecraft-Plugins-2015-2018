package org.community.ambience.StationarySounds.Heartbeat;

import org.community.ambience.Ambience;


public class ambienceCore {
	
	private Ambience plugin;
	
	private int ambienceHeartbeatTask = -1;
	
	public ambienceCore(Ambience plugin) {
		this.plugin = plugin;
	}
	
	public void init() {
		ambienceHeartbeatTask = plugin.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, plugin.ambienceHeartbeat, 600L, plugin.sounds.getLong("Config.HeartBeatInSeconds", 2) * 20L);

		if (ambienceHeartbeatTask == -1) {
			plugin.LogError("error: Heartbeat initialization failed!");
		} else {
			plugin.LogInfo("initialized: Heartbeat");
		}
	}
	
	

}
