package org.community.newSettlers.Listener;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.hanging.HangingBreakByEntityEvent;
import org.community.newSettlers.newSettlers;
import org.community.newSettlers.Town.Town;

public class newSettlersHangingBreakEvent implements Listener {
	
	private final newSettlers plugin;

	public newSettlersHangingBreakEvent(newSettlers plugin)
	{
		this.plugin = plugin;
	}
	@SuppressWarnings("deprecation")
	@EventHandler(priority = EventPriority.LOW)
	public void onHangingPlace(HangingBreakByEntityEvent event) {
		
		if(!(event.getRemover() instanceof Player)){
			event.setCancelled(true);
			return;			
		}
		
		Player player = (Player) event.getRemover();
		
		if(plugin.adminMode.get(player) == null){
			
		} else if(plugin.adminMode.get(player) == false){
			
		} else {
			return;
		}	
		
		if(event.getEntity().getWorld().toString().equalsIgnoreCase("Berufeinstanz")) {
			if(plugin.config.getList("Wilderness.Whitelist.Break") == null) {
				event.setCancelled(true);
				return;
			} else {
				if(plugin.config.getList("Berufeinstanz.Whitelist.Break").contains("ITEMFRAME")) {
					return;
				} else {
					event.setCancelled(true);
					return;
				}
			}
		}
				
		Town town = plugin.nSCore.getChunkInfo(player.getLocation().getWorld().getName() + "," + player.getLocation().getChunk().getX() + "," + player.getLocation().getChunk().getZ()); 
		if(town == null) {	
			if(plugin.config.getList("Wilderness.Whitelist.Break") == null) {
				return;
			}else if(plugin.config.getList("Wilderness.Whitelist.Break").contains("ITEMFRAME")) {
				return;
			} else {				
				event.setCancelled(true);
				player.updateInventory();
				player.sendMessage(ChatColor.DARK_GRAY + "Du darfst in der Wildnis nichts abbauen.");
				return;
			}			
		} else {
			if(town.isMember(player.getName())) {
				if(town.isBreakAllowed()) {					
					return;
				} else {
					event.setCancelled(true);
					player.updateInventory();
					player.sendMessage("Deine Stadt genehmigt dir den Abbau nicht.");
					return;
				}
			}
			
			if(town.isAlly(player)) {
				if(town.isBreakAllowedAlly()) {
					return;
				} else {
					event.setCancelled(true);
					player.updateInventory();
					player.sendMessage("Die verbündete Stadt genehmigt dir den Abbau nicht.");
					return;
				}
			}
			
			if(town.isBreakAllowedOutsider()) {
				return;
			} else {
				event.setCancelled(true);
				player.updateInventory();
				player.sendMessage("Diese Stadt genehmigt dir den Abbau nicht.");
				return;
			}
		}		
	}
	
}