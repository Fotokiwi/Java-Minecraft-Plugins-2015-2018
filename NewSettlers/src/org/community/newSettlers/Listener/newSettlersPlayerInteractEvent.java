package org.community.newSettlers.Listener;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.community.newSettlers.newSettlers;
import org.community.newSettlers.Town.Town;

public class newSettlersPlayerInteractEvent implements Listener {
	
	private final newSettlers plugin;

	public newSettlersPlayerInteractEvent(newSettlers plugin)
	{
		this.plugin = plugin;
	}
	@EventHandler(priority = EventPriority.LOW)
	public void onPlayerInteract(PlayerInteractEvent event) {
				
		if(plugin.config.getList("System.InteractBlocks") == null) {
			return;
		}	
		if(event.getClickedBlock() == null) {
			return;
		}
		
		if(event.getItem() != null){
			if(event.getItem().getType() == Material.FLINT_AND_STEEL) {
				Town town = plugin.nSCore.getChunkInfo(event.getClickedBlock().getLocation().getWorld().getName() + "," + event.getClickedBlock().getLocation().getChunk().getX() + "," + event.getClickedBlock().getLocation().getChunk().getZ()); 			
				
				if(plugin.adminMode.get(event.getPlayer()) == null){
					
				} else if(plugin.adminMode.get(event.getPlayer()) == false){
					
				} else {
					return;
				}
				
				if(town == null) {	
					Player player = event.getPlayer();

					event.setCancelled(true);
					player.sendMessage("In der Wildnis darf nicht gezündelt werden!");
					return;			
				}
			}
			if(event.getItem().getType() == Material.WATER_BUCKET || event.getItem().getType() == Material.LAVA_BUCKET) {
				Town town = plugin.nSCore.getChunkInfo(event.getClickedBlock().getLocation().getWorld().getName() + "," + event.getClickedBlock().getLocation().getChunk().getX() + "," + event.getClickedBlock().getLocation().getChunk().getZ()); 			
				
				if(plugin.adminMode.get(event.getPlayer()) == null){
					
				} else if(plugin.adminMode.get(event.getPlayer()) == false){
					
				} else {
					return;
				}
				
				if(town == null) {	
					Player player = event.getPlayer();

					event.setCancelled(true);
					player.sendMessage("In der Wildnis dürfen Eimer nicht ausgeschüttet werden!");
					return;			
				}
			}
		}
		
		if(event.getClickedBlock().getType() == Material.FURNACE || event.getClickedBlock().getType() == Material.BURNING_FURNACE || event.getClickedBlock().getType() == Material.BREWING_STAND) {
			Player player = event.getPlayer();
			Town town = plugin.nSCore.getChunkInfo(event.getClickedBlock().getLocation().getWorld().getName() + "," + event.getClickedBlock().getLocation().getChunk().getX() + "," + event.getClickedBlock().getLocation().getChunk().getZ()); 			
			if(town == null) {	
				event.setCancelled(true);
				player.sendMessage("Dieses Objekt darfst du hier nicht benutzen.");				
				return;			
			}

		}
		
		if(!plugin.config.getList("System.InteractActiveWorlds").contains(event.getClickedBlock().getWorld().toString())) {
			return;
		}
				
		if(plugin.config.getList("System.InteractBlocks").contains(event.getClickedBlock().getType().name())) {
			Player player = event.getPlayer();
			Town town = plugin.nSCore.getChunkInfo(event.getClickedBlock().getLocation().getWorld().getName() + "," + event.getClickedBlock().getLocation().getChunk().getX() + "," + event.getClickedBlock().getLocation().getChunk().getZ()); 			
			if(town == null) {					
				return;			
			} else {
				if(town.isMember(player.getName())) {
					if(town.isInteractAllowed()) {
						return;
					} else {
						event.setCancelled(true);
						player.sendMessage("Deine Stadt genehmigt dir die Nutzung von Schaltern nicht.");
						return;
					}
				}
				
				if(town.isAlly(player)) {
					if(town.isInteractAllowedAlly()) {
						return;
					} else {
						event.setCancelled(true);
						player.sendMessage("Die verbündete Stadt genehmigt dir die Nutzung von Schaltern nicht.");
						return;
					}
				}
				
				if(town.isInteractAllowedOutsider()) {
					return;
				} else {
					event.setCancelled(true);
					player.sendMessage("Diese Stadt genehmigt dir die Nutzung von Schaltern nicht.");
					return;
				}
			}
		}			
		
	}
	
}