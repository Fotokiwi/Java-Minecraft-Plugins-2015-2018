package org.community.newSettlers.Listener;

import java.util.Set;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.community.newSettlers.newSettlers;
import org.community.newSettlers.Town.Town;

public class newSettlersPlayerRespawnEvent implements Listener {
	
	private final newSettlers plugin;

	public newSettlersPlayerRespawnEvent(newSettlers plugin)
	{
		this.plugin = plugin;
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerRespawn(PlayerRespawnEvent event) {
		
		Player player = event.getPlayer();
		Town town = plugin.nSCore.getPlayerTown(player);
		
		//player.setFoodLevel(5);
		//player.setSaturation(0);
		
		Location tempSpawn = checkTempSpawn(player);
		if(tempSpawn != null) {
			event.setRespawnLocation(tempSpawn);
			player.sendMessage(ChatColor.GOLD+"Du wurdest an deinem temporären Spawn wiederbelebt!");
			return;	
		}
		
		
		if(player.getWorld().getName().equalsIgnoreCase("GwinBar")) {
			Location newLocation = new Location(plugin.getServer().getWorld("GwinBar"), -300, 64, -50);
			event.setRespawnLocation(newLocation);
			player.sendMessage(ChatColor.GOLD+"Du wurdest an deinem temporären Spawn wiederbelebt!");
			return;	
		}
		
		Location newLocation = new Location(plugin.getServer().getWorld(plugin.config.getString("Locations.Default.World")), plugin.config.getDouble("Locations.Default.X"), plugin.config.getDouble("Locations.Default.Y"), plugin.config.getDouble("Locations.Default.Z"));
		
		if(plugin.config.getString("System.SpawnMode").equalsIgnoreCase("Town")) {
			
			if(town == null){
				event.setRespawnLocation(newLocation);
				return;
			}			

			if(town.getTownSpawn() == null){
				event.setRespawnLocation(newLocation);
				return;
			}
			event.setRespawnLocation(town.getTownSpawn());
			return;
			
		} else {
			
			Location targetLocation = getNearestSpawn(player, town.getName());
			
			if(targetLocation == null) {
				if(plugin.config.getString("Locations.Town." + town.getName() + ".Location.X") == null){
					event.setRespawnLocation(newLocation);
					return;
				}
				newLocation = new Location(plugin.getServer().getWorld(plugin.config.getString("Locations.Default.World")), plugin.config.getDouble("Locations.Town." + town.getName() + ".Location.X"), plugin.config.getDouble("Locations.Town." + town.getName() + ".Location.Y"), plugin.config.getDouble("Locations.Town." + town.getName() + ".Location.Z"));
				event.setRespawnLocation(newLocation);
				return;
			}
			
			event.setRespawnLocation(targetLocation);
			return;
			
		}		
		
    }
	
	private Location getNearestSpawn(Player player, String playerTown) {

		Location targetLocation = null;
		Location townLocation = null;
		Location playerLocation = plugin.playerDeathLocation.get(player);
		double xPos = 0;
		double yPos = 0;
		double zPos = 0;
		double range = -1;

		if(plugin.config.getString("System.SpawnMode").equalsIgnoreCase("Town")) {

			ConfigurationSection townSection = plugin.config.getConfigurationSection("Locations.Town");
			Set<String> townKeys = townSection.getKeys(false);
			String[] townArray = townKeys.toArray(new String[0]);

			for(int i = 0; i < townKeys.size(); i++) {
				xPos = plugin.config.getDouble("Locations.Town." + townArray[i] + ".Location.X");
				yPos = plugin.config.getDouble("Locations.Town." + townArray[i] + ".Location.Y");
				zPos = plugin.config.getDouble("Locations.Town." + townArray[i] + ".Location.Z");
				townLocation = new Location(plugin.getServer().getWorld(plugin.config.getString("Locations.Town." + townArray[i] + ".Location.World")), xPos, yPos, zPos);

				if(range == -1) {
					targetLocation = townLocation;
					range = playerLocation.distance(townLocation);
				} else {
					if(playerLocation.distance(townLocation) <= range) {
						targetLocation = townLocation;
						range = playerLocation.distance(townLocation);
					}
				}		    		
			}

		}	

		return targetLocation;
		
	}
	
	private Location checkTempSpawn(Player player) {
		Location tempLocation = null;
		if(plugin.user.getString("Spieler." + player.getUniqueId().toString() + ".Tempspawn.Location") != null) {
			if(System.currentTimeMillis() <= (plugin.user.getLong("Spieler." + player.getUniqueId().toString() + ".Tempspawn.Timer") + (plugin.user.getLong("Spieler." + player.getUniqueId().toString() + ".Tempspawn.Duration")*1000))) {
				tempLocation = plugin.nSCore.parseStringToLocation(plugin.user.getString("Spieler." + player.getUniqueId().toString() + ".Tempspawn.Location"));
				return tempLocation;
			} else {
				plugin.user.set("Spieler." + player.getUniqueId().toString() + ".Tempspawn.Location", null);
				plugin.user.set("Spieler." + player.getUniqueId().toString() + ".Tempspawn.Timer", null);
				plugin.user.set("Spieler." + player.getUniqueId().toString() + ".Tempspawn.Duration", null);
				return tempLocation;
			}			
		} else {
			return tempLocation;
		}
	}
	
}