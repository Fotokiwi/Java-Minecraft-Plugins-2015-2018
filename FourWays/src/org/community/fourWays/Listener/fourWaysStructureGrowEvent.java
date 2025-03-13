package org.community.fourWays.Listener;

import java.util.Random;

import org.bukkit.TreeType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.world.StructureGrowEvent;
import org.community.fourWays.fourWays;


public class fourWaysStructureGrowEvent implements Listener{

	private fourWays plugin;

	public fourWaysStructureGrowEvent(fourWays plugin){
		this.plugin = plugin;
	}

	@EventHandler(priority=EventPriority.HIGHEST)
	public void onStructureGrow(StructureGrowEvent event) {
		
		TreeType block = event.getSpecies();
		
		String biomeName = event.getLocation().getBlock().getBiome().name().toLowerCase().replace(" ", "_");
		//String[] shortName = biomeName.split("_");
		//if(shortName[0].equalsIgnoreCase("small"))
		//	shortName[0] = "extreme";
		
		Random rnd = new Random();
		int roll = rnd.nextInt(100) + 1;
		
		if(roll <= 75) {
			event.setCancelled(true);
			return;
		}
		
		long time = plugin.getServer().getWorld(event.getLocation().getBlock().getWorld().getName()).getTime();
		
		if(event.getLocation().getBlock().getLightFromSky() < plugin.harvest.getInt("Options.SkyViewLevel", 8)) {
			event.setCancelled(true);
			//plugin.getServer().broadcastMessage("ID: " + blockID + " - Zu wenig Sicht auf den Himmel");
			return;
		} else if(plugin.getServer().getWorld(event.getLocation().getBlock().getWorld().getName()).hasStorm() || (time >= 14334 && time <= 21655)) {
			event.setCancelled(true);
			//plugin.getServer().broadcastMessage("ID: " + blockID + " - Es ist Nacht");
			return;
		}
		
		if(block == TreeType.TREE || block == TreeType.BIG_TREE) {
			if(checkBiomePercentage("Oak", biomeName) == true) {
				//plugin.getServer().broadcastMessage("ID: Oak - Alles super!");
				return;
			} else {
				event.setCancelled(true);
				//plugin.getServer().broadcastMessage("ID: Oak - Die Pflanze wächst hier nur selten!");
				return;
			}
		}
		
		if(block == TreeType.REDWOOD || block == TreeType.TALL_REDWOOD) {
			if(checkBiomePercentage("Redwood", biomeName) == true) {
				//plugin.getServer().broadcastMessage("ID: Redwood - Alles super!");
				return;
			} else {
				event.setCancelled(true);
				//plugin.getServer().broadcastMessage("ID: Redwood - Die Pflanze wächst hier nur selten!");
				return;
			}
		}
		
		if(block == TreeType.BIRCH) {
			if(checkBiomePercentage("Birch", biomeName) == true) {
				//plugin.getServer().broadcastMessage("ID: Birch - Alles super!");
				return;
			} else {
				event.setCancelled(true);
				//plugin.getServer().broadcastMessage("ID: Birch - Die Pflanze wächst hier nur selten!");
				return;
			}
		}
		
		if(block == TreeType.JUNGLE || block == TreeType.SMALL_JUNGLE) {
			if(checkBiomePercentage("Jungle", biomeName) == true) {
				//plugin.getServer().broadcastMessage("ID: JUNGLE - Alles super!");
				return;
			} else {
				event.setCancelled(true);
				//plugin.getServer().broadcastMessage("ID: JUNGLE - Die Pflanze wächst hier nur selten!");
				return;
			}
		}
		
		if(block == TreeType.ACACIA) {
			if(checkBiomePercentage("Akazie", biomeName) == true) {
				//plugin.getServer().broadcastMessage("ID: JUNGLE - Alles super!");
				return;
			} else {
				event.setCancelled(true);
				//plugin.getServer().broadcastMessage("ID: JUNGLE - Die Pflanze wächst hier nur selten!");
				return;
			}
		}
		
		if(block == TreeType.DARK_OAK) {
			if(checkBiomePercentage("DarkOak", biomeName) == true) {
				//plugin.getServer().broadcastMessage("ID: JUNGLE - Alles super!");
				return;
			} else {
				event.setCancelled(true);
				//plugin.getServer().broadcastMessage("ID: JUNGLE - Die Pflanze wächst hier nur selten!");
				return;
			}
		}
		
	}
	
	private boolean checkBiomePercentage(String plant, String biome) {
		Random rnd = new Random();
		int roll = rnd.nextInt(100) + 1;
		
		//plugin.getServer().broadcastMessage("" + biome);
		//plugin.getServer().broadcastMessage("" + plant);
		//plugin.getServer().broadcastMessage(plugin.harvest.getInt("Config." + plant + ".Biome." + biome, -5) + "");
		if(roll <= plugin.harvest.getInt("Harvest." + plant + ".Biome." + biome, 0)) {
			return true;
		} else {
			return false;
		}
	}
}
