package org.community.ambience.StationarySounds.Listener;

import java.util.Map.Entry;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.community.ambience.Ambience;

public class ambienceStationarySoundsPlayerDisconnectEvent implements Listener {
	
	private Ambience plugin;

	public ambienceStationarySoundsPlayerDisconnectEvent(Ambience plugin)
	{
		this.plugin = plugin;
	}
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void onPlayerMove(PlayerQuitEvent event) {
		
		String playerName = event.getPlayer().getName();
		for(Entry<String, Long> key : plugin.ambienceHeartbeat.soundCache.entrySet()) {
			if(key.getKey().contains(playerName))
				plugin.ambienceHeartbeat.soundCache.remove(key.getKey());
		}
		
	}

}
