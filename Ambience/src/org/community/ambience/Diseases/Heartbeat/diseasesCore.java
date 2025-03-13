package org.community.ambience.Diseases.Heartbeat;

import org.community.ambience.Ambience;

public class diseasesCore {

	private Ambience plugin;

	private int ambienceHeartbeatTask = -1;

	public diseasesCore(Ambience plugin) {
		this.plugin = plugin;
	}

	public void init() {
		ambienceHeartbeatTask = plugin.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, plugin.ambienceDiseasesHeartbeat, 600L, plugin.diseases.getLong("Config.HeartBeatInSeconds", 30) * 20L);

		if (ambienceHeartbeatTask == -1) {
			plugin.LogError("error: Diseases Heartbeat initialization failed!");
		} else {
			plugin.LogInfo("initialized: Diseases Heartbeat");
		}
	}

}
