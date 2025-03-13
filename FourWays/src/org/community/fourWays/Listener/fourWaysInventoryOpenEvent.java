package org.community.fourWays.Listener;

import java.util.Set;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;
import org.community.fourWays.*;
import org.community.fourWays.User.*;
import org.community.newSettlers.Town.Town;

public class fourWaysInventoryOpenEvent implements Listener {
		
	private fourWays plugin;
	 	 
	public fourWaysInventoryOpenEvent(fourWays plugin)
	{
	this.plugin = plugin;
	}
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void onInventoryOpen(InventoryOpenEvent event) {
		
		if (!(event.getPlayer() instanceof Player))
			return;
		
		Player player = (Player)event.getPlayer();
		User user = plugin.fWUsers.getPlayerInfo(player);
		
		String[] playerInfo = user.getJobHash().split(",");
		String playerClass = playerInfo[1];
		int playerLevel = new Integer(playerInfo[0]);
		
		if(event.getInventory().getType() == InventoryType.ANVIL || event.getInventory().getType() == InventoryType.ENCHANTING || event.getInventory().getType() == InventoryType.BEACON) {
			event.setCancelled(true);
			return;
		}
		
		if(player.isInsideVehicle()) {
			if(player.getVehicle().getType() == EntityType.HORSE) {
				ConfigurationSection blockSection = plugin.entity.getConfigurationSection("Entity.HORSE.Ausstatten");
				
				if(blockSection == null)
					return;
				
				Set<String> blockKeys = blockSection.getKeys(false);
		  	  	String[] blockArray = blockKeys.toArray(new String[0]);  	  	
		  	  	
				int blockLevel;
				
				for(int i = 0; i < blockArray.length; i++){
					blockLevel = plugin.entity.getInt("Entity.HORSE.Ausstatten.Beruf." + blockArray[i], 0);
					if(playerClass.contains(blockArray[i]) && playerLevel >= blockLevel) {
						if(plugin.entity.getString("Entity.HORSE.Ausstatten.Gebaeude") != null) {
							Town town = plugin.newSettlersAPI.nSAPI.getChunkTown(player.getLocation().getChunk());
							if(town == null) {
								//player.sendMessage("Du hast diesen Block nun initialisiert");
								//user.addExp(plugin.entity.getInt("Entity.HORSE.Ausstatten.Exp", 0));
								player.sendMessage("Du kannst dein Pferd nur bei einem Züchter ausstatten lassen.");
								event.setCancelled(true);
								return;
							} else {
								if(town.getBuildingStatus(plugin.entity.getString("Entity.HORSE.Ausstatten.Gebaeude"))) {
									player.sendMessage("" + plugin.entity.getString("Entity.HORSE.Ausstatten.Gebaeude"));
									//player.sendMessage("Du hast diesen Block nun initialisiert");
									user.addExp(plugin.entity.getInt("Entity.HORSE.Ausstatten.Exp", 0));
									return;
								} else {
									//player.sendMessage("Die Stadt verfügt nicht über das passende Gebäude.");
									player.sendMessage("Du kannst dein Pferd nur bei einem Züchter ausstatten lassen.");
									event.setCancelled(true);
									return;
								}
							}
						} else {
							//player.sendMessage("Du hast diesen Block nun initialisiert");
							user.addExp(plugin.entity.getInt("Entity.HORSE.Ausstatten.Exp", 0));
							return;
						}
							
					}
				}
				
				event.setCancelled(true);
				player.sendMessage("Du kannst dein Pferd nur bei einem Züchter ausstatten lassen.");
				return;
			}
		}
		
		if(event.getInventory().getHolder() instanceof Horse) {
			ConfigurationSection blockSection = plugin.entity.getConfigurationSection("Entity.HORSE.Ausstatten.Beruf");
			
			if(blockSection == null)
				return;
			
			Set<String> blockKeys = blockSection.getKeys(false);
	  	  	String[] blockArray = blockKeys.toArray(new String[0]);  	  	
	  	  	
			int blockLevel;
			
			for(int i = 0; i < blockArray.length; i++){
				blockLevel = plugin.entity.getInt("Entity.HORSE.Ausstatten.Beruf." + blockArray[i], 0);
				if(playerClass.contains(blockArray[i]) && playerLevel >= blockLevel) {
					if(plugin.entity.getString("Entity.HORSE.Ausstatten.Gebaeude") != null) {
						Town town = plugin.newSettlersAPI.nSAPI.getChunkTown(player.getLocation().getChunk());
						if(town == null) {
							//player.sendMessage("Du hast diesen Block nun initialisiert");
							//user.addExp(plugin.entity.getInt("Entity.HORSE.Ausstatten.Exp", 0));
							player.sendMessage("Du kannst dein Pferd nur bei einem Züchter ausstatten lassen.");
							event.setCancelled(true);
							return;
						} else {
							if(town.getBuildingStatus(plugin.entity.getString("Entity.HORSE.Ausstatten.Gebaeude"))) {
								player.sendMessage("" + plugin.entity.getString("Entity.HORSE.Ausstatten.Gebaeude"));
								//player.sendMessage("Du hast diesen Block nun initialisiert");
								user.addExp(plugin.entity.getInt("Entity.HORSE.Ausstatten.Exp", 0));
								return;
							} else {
								//player.sendMessage("Die Stadt verfügt nicht über das passende Gebäude.");
								player.sendMessage("Du kannst dein Pferd nur bei einem Züchter ausstatten lassen.");
								event.setCancelled(true);
								return;
							}
						}
					} else {
						//player.sendMessage("Du hast diesen Block nun initialisiert");
						user.addExp(plugin.entity.getInt("Entity.HORSE.Ausstatten.Exp", 0));
						return;
					}
						
				}
			}
			
			event.setCancelled(true);
			player.sendMessage("Du kannst dein Pferd nur bei einem Züchter ausstatten lassen.");
			return;
		}
		
	}
}