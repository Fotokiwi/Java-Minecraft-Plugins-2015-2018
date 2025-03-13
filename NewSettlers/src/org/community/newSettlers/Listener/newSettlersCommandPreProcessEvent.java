package org.community.newSettlers.Listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.community.newSettlers.newSettlers;

public class newSettlersCommandPreProcessEvent implements Listener {

	newSettlers plugin;
	
	public newSettlersCommandPreProcessEvent(newSettlers plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void onCommandPreProcess(PlayerCommandPreprocessEvent event) {
		String message = event.getMessage();
		Player player = event.getPlayer();
		if(message.startsWith("/w ") || message.startsWith("/plugin") || message.startsWith("/bukkit") || message.startsWith("/version") || message.startsWith("/help") || message.startsWith("/?")) {
			event.setMessage(" ");
		}
		if(message.startsWith("/we") && !plugin.nSCore.isAdmin(player)) {
			//player.sendMessage("WorldEdit? Wirklich?");
			event.setMessage(" ");
			//event.setCancelled(true);
		}
	}
	
}
