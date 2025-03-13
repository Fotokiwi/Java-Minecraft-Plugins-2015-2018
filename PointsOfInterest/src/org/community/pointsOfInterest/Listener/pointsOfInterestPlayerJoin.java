package org.community.pointsOfInterest.Listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.community.pointsOfInterest.pointsOfInterest;

public class pointsOfInterestPlayerJoin implements Listener{
	private pointsOfInterest plugin;
	public pointsOfInterestPlayerJoin(pointsOfInterest plugin){
		this.plugin = plugin;
	}
	
	@EventHandler(priority = EventPriority.LOW)
	public void onPlayerJoin(PlayerJoinEvent e){
		plugin.poiUser.addUserIfNotAvailable(e.getPlayer().getUniqueId());
	}
}
