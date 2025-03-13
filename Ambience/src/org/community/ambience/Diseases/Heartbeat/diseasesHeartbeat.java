package org.community.ambience.Diseases.Heartbeat;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;
import org.community.ambience.Ambience;

public class diseasesHeartbeat implements Runnable {
	
private Ambience plugin;
	
	public Map<String, Long> soundCache = new HashMap<String, Long>();
	
	public diseasesHeartbeat(Ambience plugin) {
		this.plugin = plugin;
	}

	@Override
	public void run() {
		
		//plugin.getServer().broadcastMessage("[DEBUG] Temperature Check");
		@SuppressWarnings("deprecation")
		Player[] player = plugin.getServer().getOnlinePlayers();
		for(int i = 0; i < player.length; i++) {
			plugin.ambienceDiseases.diseasesTemperature.calculateTemperature(player[i]);
		}
		
	}

}
