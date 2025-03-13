package org.community.newSettlers.Core;

import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.community.newSettlers.newSettlers;

public class newSettlersNotfall implements Runnable{
	
	Player player = null;
	Location location = null;
	newSettlers plugin = null;
	
	public newSettlersNotfall(newSettlers plugin) {
		this.plugin = plugin;
	}
	
	public newSettlersNotfall(newSettlers plugin, Player player, Location location) {
		this.player = player;
		this.location = location;
		this.plugin = plugin;
	}

	public void run() {
		Location currentLocation = player.getLocation();
		if(!(location.getWorld().getName().equalsIgnoreCase(currentLocation.getWorld().getName()))){
			player.sendMessage("Du musst still stehen um dich wegporten zu können");
			return;
		}
		if(location.distance(currentLocation)>0){
			player.sendMessage("Du musst still stehen um dich wegporten zu können");
			return;
		}else{
			plugin.user.set("Spieler." + player.getUniqueId().toString() + ".Notfall-Cooldown", System.currentTimeMillis());

			Map<Location, Block> blockList = plugin.nSBFU.getBlocksInRange(currentLocation, 15, 0, 15);
			//plugin.getServer().broadcastMessage(blockList.toString());
			World world = player.getWorld();

			for(Map.Entry<Location, Block> e : blockList.entrySet()){			
				Block tempBlock = world.getHighestBlockAt(e.getKey());
				if(tempBlock.getType() != Material.LAVA && tempBlock.getType() != Material.STATIONARY_LAVA) {
					Location tele = tempBlock.getLocation().add(0, 1, 0);
					if(currentLocation.distance(tele) > 7) {
						player.teleport(tele);
						Bukkit.broadcastMessage("Der Spieler "+ChatColor.RED+player.getName()+ChatColor.WHITE+" hat den Notfall Teleport eingesetzt");
						break;
					}

				}
			}

		}

	}

}
