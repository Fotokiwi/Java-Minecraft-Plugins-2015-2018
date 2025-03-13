package org.community.newSettlers.Listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.community.newSettlers.newSettlers;

public class newSettlersPlayerDeathEvent implements Listener {
	
	private final newSettlers plugin;

	public newSettlersPlayerDeathEvent(newSettlers plugin)
	{
		this.plugin = plugin;
	}
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void onPlayerDeath(PlayerDeathEvent event) {
		
		Player player = event.getEntity();
		
		plugin.playerDeathLocation.put(player, player.getLocation());	
		
    }
	
}