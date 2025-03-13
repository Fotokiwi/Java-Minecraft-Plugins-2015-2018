package org.community.fourWays.Listener;

import java.util.Random;
import java.util.Set;

import org.bukkit.DyeColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.SheepDyeWoolEvent;
import org.bukkit.event.entity.SheepRegrowWoolEvent;
import org.bukkit.event.player.PlayerShearEntityEvent;
import org.community.fourWays.fourWays;
import org.community.fourWays.User.User;
import org.community.newSettlers.Town.Town;


public class fourWaysPlayerShearEntityEvent implements Listener {

	private fourWays plugin;


	public fourWaysPlayerShearEntityEvent(fourWays plugin) {
		this.plugin = plugin;
	}

	@EventHandler(priority = EventPriority.NORMAL)
	public void onPlayerEntityShear(PlayerShearEntityEvent event) {
		
		if(event.getEntity().getType() != EntityType.SHEEP)
			return;
		
		if(plugin.adminMode.get(event.getPlayer()) == null){
			
		} else if(plugin.adminMode.get(event.getPlayer()) == false){
			
		} else {
			return;
		}	
		
		String entity = event.getEntity().getType().name();
		Player player = event.getPlayer();
		User user = plugin.fWUsers.getPlayerInfo(player);
		
		ConfigurationSection entitySection = plugin.entity.getConfigurationSection("Entity." + entity + ".Scheren.Beruf");
		
		if(entitySection == null)
			return;
		
		Set<String> entityKeys = entitySection.getKeys(false);
  	  	String[] entityArray = entityKeys.toArray(new String[0]);  	  	
  	  	
		int blockLevel;
		
		String[] blockInfo = new String[2];
		blockInfo = user.getJobHash().split(",");
		String playerClass = blockInfo[1];
		int playerLevel = new Integer(blockInfo[0]);
		
		for(int i = 0; i < entityArray.length; i++){
			blockLevel = plugin.entity.getInt("Entity." + entity + ".Scheren.Beruf." + entityArray[i], 0);
			if(playerClass.contains(entityArray[i]) && playerLevel >= blockLevel) {
				if(plugin.entity.getString("Entity." + entity + ".Scheren.Gebaeude") != null) {
					Town town = plugin.newSettlersAPI.nSAPI.getChunkTown(event.getEntity().getLocation().getChunk());
					if(town == null) {
						//player.sendMessage("Du hast diesen Block nun initialisiert");
						user.addExp(plugin.entity.getInt("Entity." + entity + ".Scheren.Exp", 0));
						event.setCancelled(true);
						return;
					} else {
						if(town.getBuildingStatus(plugin.entity.getString("Entity." + entity + ".Scheren.Gebaeude"))) {
							player.sendMessage("" + plugin.entity.getString("Entity." + entity + ".Scheren.Gebaeude"));
							//player.sendMessage("Du hast diesen Block nun initialisiert");
							user.addExp(plugin.entity.getInt("Entity." + entity + ".Scheren.Exp", 0));
							return;
						} else {
							//player.sendMessage("Die Stadt verfügt nicht über das passende Gebäude.");
							event.setCancelled(true);
							return;
						}
					}
				} else {
					//player.sendMessage("Du hast diesen Block nun initialisiert");
					user.addExp(plugin.entity.getInt("Entity." + entity + ".Scheren.Exp", 0));
					return;
				}
					
			}
		}
		
		event.setCancelled(true);
		return;
		
		//player.sendMessage("Du hast nicht den nötigen Beruf oder die entsprechende Stufe.");

	}
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void onSheepRegrowWool(SheepRegrowWoolEvent event) {		
		Random dice = new Random();
		int rndRoll = dice.nextInt(100 + 1);
		if(rndRoll <= 10) {
			Entity entity = event.getEntity();	
			
			if(entity.getType() == EntityType.SHEEP) {
				Sheep sheep = (Sheep) entity;
				sheep.setColor(DyeColor.WHITE);
			}
			return;	
		} else {
			event.setCancelled(true);
			return;
		}
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onSheepDyeWool(SheepDyeWoolEvent event) {
		DyeColor oldColor = event.getEntity().getColor();
		event.setCancelled(true);
		event.setColor(oldColor);
	}
}