package org.community.newSettlers.Listener;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.community.newSettlers.newSettlers;
import org.community.newSettlers.Town.Town;

public class newSettlersBlockPlaceEvent implements Listener {
	
	private final newSettlers plugin;

	public newSettlersBlockPlaceEvent(newSettlers plugin)
	{
		this.plugin = plugin;
	}
	@SuppressWarnings("deprecation")
	@EventHandler(priority = EventPriority.LOW)
	public void onBlockPlace(BlockPlaceEvent event) {
		
		if(plugin.adminMode.get(event.getPlayer()) == null){
			
		} else if(plugin.adminMode.get(event.getPlayer()) == false){
			
		} else {
			return;
		}	
		
		if(event.getBlock().getWorld().getName().equalsIgnoreCase("Berufeinstanz")) {
			if(plugin.config.getList("Wilderness.Whitelist.Build") == null) {
				event.setCancelled(true);
				return;
			} else {
				if(plugin.config.getList("Berufeinstanz.Whitelist.Build").contains(event.getBlock().getType().name())) {
					return;
				}
			}
		}
				
		Town town = plugin.nSCore.getChunkInfo(event.getBlock().getLocation().getWorld().getName() + "," + event.getBlock().getLocation().getChunk().getX() + "," + event.getBlock().getLocation().getChunk().getZ()); 
		
		if(event.getBlock().getWorld().getName().equalsIgnoreCase("Startinsel") && town != null) {
			Material block = event.getBlock().getType();
			if(block == Material.SIGN || block == Material.SIGN_POST || block == Material.CHEST) {
				
			} else {
				event.setCancelled(true);
				event.getPlayer().sendMessage(ChatColor.RED + "Auf dem Marktplatz darfst du nur Schilder und Truhen bauen!");
			}
		}
		
		if(town == null) {
			if(plugin.config.getList("Wilderness.Whitelist.Build") == null) {
				return;
			} else if(plugin.config.getList("Wilderness.Whitelist.Build").contains(event.getBlock().getType().name())) {
				return;
			} else {
				event.setCancelled(true);
				event.getPlayer().updateInventory();
				event.getPlayer().sendMessage(ChatColor.DARK_GRAY + "Du darfst in der Wildnis nichts bauen.");
				
			}				
		} else {
			Player player = event.getPlayer();
			if(town.isMember(player.getName())) {
				if(town.isBuildAllowed()) {
					return;
				} else {
					event.setCancelled(true);
					event.getPlayer().updateInventory();
					player.sendMessage("Deine Stadt genehmigt dir das Platzieren nicht.");
					return;
				}
			}
			
			if(town.isAlly(player)) {
				if(town.isBuildAllowedAlly()) {
					return;
				} else {
					event.setCancelled(true);
					event.getPlayer().updateInventory();
					player.sendMessage("Die verbündete Stadt genehmigt dir das Platzieren nicht.");
					return;
				}
			}		

			if(town.isBuildAllowedOutsider()) {
				return;
			} else {
				event.setCancelled(true);
				event.getPlayer().updateInventory();
				player.sendMessage("Diese Stadt genehmigt dir das Platzieren nicht.");
				return;
			}
		}
		
	}
	
}