package org.community.newSettlers.Listener;

import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.hanging.HangingPlaceEvent;
import org.community.newSettlers.newSettlers;
import org.community.newSettlers.Town.Town;

public class newSettlersHangingPlaceEvent implements Listener {
	
	private final newSettlers plugin;

	public newSettlersHangingPlaceEvent(newSettlers plugin)
	{
		this.plugin = plugin;
	}
	@SuppressWarnings("deprecation")
	@EventHandler(priority = EventPriority.LOW)
	public void onHangingPlace(HangingPlaceEvent event) {
		
		if(plugin.adminMode.get(event.getPlayer()) == null){
			
		} else if(plugin.adminMode.get(event.getPlayer()) == false){
			
		} else {
			return;
		}	
		
		if(event.getBlock().getWorld().toString().equalsIgnoreCase("Berufeinstanz")) {
			if(plugin.config.getList("Wilderness.Whitelist.Build") == null) {
				event.setCancelled(true);
				return;
			} else {
				if(plugin.config.getList("Berufeinstanz.Whitelist.Build").contains(event.getBlock().toString())) {
					return;
				} else {
					event.setCancelled(true);
					return;
				}
			}
		}
				
		Chunk chunk = event.getBlock().getLocation().getChunk();
		Town town = plugin.nSCore.getChunkInfo(chunk.getWorld().getName() + "," + chunk.getX() + "," + chunk.getZ()); 
		if(town == null) {	
			if(plugin.config.getList("Wilderness.Whitelist.Build") == null) {
				return;
			}else if(plugin.config.getList("Wilderness.Whitelist.Build").contains(event.getBlock().getType().name())) {
				return;
			} else {				
				event.setCancelled(true);
				event.getPlayer().updateInventory();
				event.getPlayer().sendMessage(ChatColor.DARK_GRAY + "Du darfst in der Wildnis nichts bauen.");
				return;
			}			
		} else {
			Player player = event.getPlayer();
			if(town.isMember(player.getName())) {
				if(town.isBreakAllowed()) {					
					return;
				} else {
					event.setCancelled(true);
					event.getPlayer().updateInventory();
					player.sendMessage("Deine Stadt genehmigt dir den Bau nicht.");
					return;
				}
			}
			
			if(town.isAlly(player)) {
				if(town.isBreakAllowedAlly()) {
					return;
				} else {
					event.setCancelled(true);
					event.getPlayer().updateInventory();
					player.sendMessage("Die verbündete Stadt genehmigt dir den Bau nicht.");
					return;
				}
			}
			
			if(town.isBreakAllowedOutsider()) {
				return;
			} else {
				event.setCancelled(true);
				event.getPlayer().updateInventory();
				player.sendMessage("Diese Stadt genehmigt dir den Bau nicht.");
				return;
			}
		}		
	}
	
}