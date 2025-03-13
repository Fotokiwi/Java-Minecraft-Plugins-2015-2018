package org.community.fourWays.Listener;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockGrowEvent;
import org.bukkit.event.block.LeavesDecayEvent;
import org.community.fourWays.fourWays;


public class fourWaysBlockGrowEvent implements Listener{

	private fourWays plugin;

	public fourWaysBlockGrowEvent(fourWays plugin){
		this.plugin = plugin;
	}

	@EventHandler(priority=EventPriority.NORMAL)
	public void onLeavesDecay(LeavesDecayEvent event) {
		event.setCancelled(true);
		event.getBlock().setType(Material.AIR);
	}

	@EventHandler(priority=EventPriority.HIGHEST)
	public void onBlockGrow(BlockGrowEvent event) {
		
		Block block = event.getBlock();
		String blockID = block.getType().toString();
		//plugin.getServer().broadcastMessage("Block: " + blockID);
		

		//if(blockID.equalsIgnoreCase("AIR"))
		//	return;
		
		long time = plugin.getServer().getWorld(block.getWorld().getName()).getTime();
		
		String biomeName = block.getBiome().name().toLowerCase().replace(" ", "_");
		//String[] shortName = biomeName.split("_");
		//if(shortName[0].equalsIgnoreCase("small"))
		//	shortName[0] = "extreme";
		
		String plant = "Wheat";
		if(blockID == "" || blockID.equalsIgnoreCase("AIR")){
			Location location = block.getLocation().add(0, -1, 0);
			blockID = location.getBlock().getType().toString();
		}
		plant = plugin.harvestCache.get(blockID);
		//plugin.getServer().broadcastMessage("Plant: " + plant);
		
		if(block.getLightFromSky() < plugin.harvest.getInt("Options.SkyViewLevel", 8)) {
			event.setCancelled(true);
			//plugin.getServer().broadcastMessage("ID: " + blockID + " - Zu wenig Sicht auf den Himmel");
			return;
		} else if(plugin.getServer().getWorld(block.getWorld().getName()).hasStorm() || (time >= 14334 && time <= 21655)) {
			event.setCancelled(true);
			//plugin.getServer().broadcastMessage("ID: " + blockID + " - Es ist Nacht");
			return;
		} else {
			Material newBlock = event.getNewState().getType();
			if(newBlock == Material.MELON_BLOCK || newBlock == Material.PUMPKIN)
			{
				return;	
			}
			if(checkBiomePercentage(plant, biomeName) == true) {
				//plugin.getServer().broadcastMessage("ID: " + blockID + " - Alles super!");
				return;
			} else {
				event.setCancelled(true);
				//plugin.getServer().broadcastMessage("ID: " + blockID + " - Die Pflanze wächst hier nur selten! ( " + plugin.harvest.getInt("Harvest." + plant + ".Biome." + biomeName, 0) + ")");
				return;
			}
			
		}
		
	}
	
	private boolean checkBiomePercentage(String plant, String biome) {
		Random rnd = new Random();
		int roll = rnd.nextInt(100) + 1;

		//plugin.getServer().broadcastMessage("" + biome);
		//plugin.getServer().broadcastMessage("" + plant);
		//plugin.getServer().broadcastMessage(plugin.harvest.getInt("Harvest." + plant + ".Biome." + biome, -5) + "");
		if(roll <= plugin.harvest.getInt("Harvest." + plant + ".Biome." + biome, 0)) {
			return true;
		} else {
			return false;
		}
	}
}
