package org.community.ambience.AmbientObjects.Zelte;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.community.ambience.Ambience;

public class kleinesZelt {	
	
	private Ambience plugin;

	public kleinesZelt(Ambience plugin) {
		this.plugin = plugin;
	}
	
	private Map<String, String> buildPattern(Location location, String[] itemArray) {
		Location blockLocation = null;
		Map<String, String> pattern = new HashMap<String, String>();
		
		// Erste Reihe Boden
		
		blockLocation = location.add(-2, 0, -2);
		pattern.put(blockLocation.getWorld().getName() + "," + blockLocation.getBlockX() + "," + blockLocation.getBlockY() + "," + blockLocation.getBlockZ(), itemArray[0]);
		blockLocation = location.add(2, 0, 2);
		
		blockLocation = location.add(-2, 0, -1);
		pattern.put(blockLocation.getWorld().getName() + "," + blockLocation.getBlockX() + "," + blockLocation.getBlockY() + "," + blockLocation.getBlockZ(), itemArray[0]);
		blockLocation = location.add(2, 0, 1);
		
		blockLocation = location.add(-2, 0, 0);
		pattern.put(blockLocation.getWorld().getName() + "," + blockLocation.getBlockX() + "," + blockLocation.getBlockY() + "," + blockLocation.getBlockZ(), itemArray[0]);
		blockLocation = location.add(2, 0, 0);
		
		blockLocation = location.add(-2, 0, 1);
		pattern.put(blockLocation.getWorld().getName() + "," + blockLocation.getBlockX() + "," + blockLocation.getBlockY() + "," + blockLocation.getBlockZ(), itemArray[0]);
		blockLocation = location.add(2, 0, -1);
		
		blockLocation = location.add(-2, 0, 2);
		pattern.put(blockLocation.getWorld().getName() + "," + blockLocation.getBlockX() + "," + blockLocation.getBlockY() + "," + blockLocation.getBlockZ(), itemArray[0]);
		blockLocation = location.add(2, 0, -2);
		
		// Zweite Reihe Boden
		
		blockLocation = location.add(2, 0, -2);
		pattern.put(blockLocation.getWorld().getName() + "," + blockLocation.getBlockX() + "," + blockLocation.getBlockY() + "," + blockLocation.getBlockZ(), itemArray[1]);
		blockLocation = location.add(-2, 0, 2);
		
		blockLocation = location.add(2, 0, -1);
		pattern.put(blockLocation.getWorld().getName() + "," + blockLocation.getBlockX() + "," + blockLocation.getBlockY() + "," + blockLocation.getBlockZ(), itemArray[1]);
		blockLocation = location.add(-2, 0, 1);
		
		blockLocation = location.add(2, 0, 0);
		pattern.put(blockLocation.getWorld().getName() + "," + blockLocation.getBlockX() + "," + blockLocation.getBlockY() + "," + blockLocation.getBlockZ(), itemArray[1]);
		blockLocation = location.add(-2, 0, 0);
		
		blockLocation = location.add(2, 0, 1);
		pattern.put(blockLocation.getWorld().getName() + "," + blockLocation.getBlockX() + "," + blockLocation.getBlockY() + "," + blockLocation.getBlockZ(), itemArray[1]);
		blockLocation = location.add(-2, 0, -1);
		
		blockLocation = location.add(2, 0, 2);
		pattern.put(blockLocation.getWorld().getName() + "," + blockLocation.getBlockX() + "," + blockLocation.getBlockY() + "," + blockLocation.getBlockZ(), itemArray[1]);
		blockLocation = location.add(-2, 0, -2);
		
		// Erste Reihe Mitte
		
		blockLocation = location.add(1, 1, -2);
		pattern.put(blockLocation.getWorld().getName() + "," + blockLocation.getBlockX() + "," + blockLocation.getBlockY() + "," + blockLocation.getBlockZ(), itemArray[2]);
		blockLocation = location.add(-1, -1, 2);
		
		blockLocation = location.add(1, 1, -1);
		pattern.put(blockLocation.getWorld().getName() + "," + blockLocation.getBlockX() + "," + blockLocation.getBlockY() + "," + blockLocation.getBlockZ(), itemArray[2]);
		blockLocation = location.add(-1, -1, 1);
		
		blockLocation = location.add(1, 1, 0);
		pattern.put(blockLocation.getWorld().getName() + "," + blockLocation.getBlockX() + "," + blockLocation.getBlockY() + "," + blockLocation.getBlockZ(), itemArray[2]);
		blockLocation = location.add(-1, -1, 0);
		
		blockLocation = location.add(1, 1, 1);
		pattern.put(blockLocation.getWorld().getName() + "," + blockLocation.getBlockX() + "," + blockLocation.getBlockY() + "," + blockLocation.getBlockZ(), itemArray[2]);
		blockLocation = location.add(-1, -1, -1);
		
		blockLocation = location.add(1, 1, 2);
		pattern.put(blockLocation.getWorld().getName() + "," + blockLocation.getBlockX() + "," + blockLocation.getBlockY() + "," + blockLocation.getBlockZ(), itemArray[2]);
		blockLocation = location.add(-1, -1, -2);
		
		// Zweite Reihe Mitte
		
		blockLocation = location.add(-1, 1, -2);
		pattern.put(blockLocation.getWorld().getName() + "," + blockLocation.getBlockX() + "," + blockLocation.getBlockY() + "," + blockLocation.getBlockZ(), itemArray[3]);
		blockLocation = location.add(1, -1, 2);
		
		blockLocation = location.add(-1, 1, -1);
		pattern.put(blockLocation.getWorld().getName() + "," + blockLocation.getBlockX() + "," + blockLocation.getBlockY() + "," + blockLocation.getBlockZ(), itemArray[3]);
		blockLocation = location.add(1, -1, 1);
		
		blockLocation = location.add(-1, 1, 0);
		pattern.put(blockLocation.getWorld().getName() + "," + blockLocation.getBlockX() + "," + blockLocation.getBlockY() + "," + blockLocation.getBlockZ(), itemArray[3]);
		blockLocation = location.add(1, -1, 0);
		
		blockLocation = location.add(-1, 1, 1);
		pattern.put(blockLocation.getWorld().getName() + "," + blockLocation.getBlockX() + "," + blockLocation.getBlockY() + "," + blockLocation.getBlockZ(), itemArray[3]);
		blockLocation = location.add(1, -1, -1);
		
		blockLocation = location.add(-1, 1, 2);
		pattern.put(blockLocation.getWorld().getName() + "," + blockLocation.getBlockX() + "," + blockLocation.getBlockY() + "," + blockLocation.getBlockZ(), itemArray[3]);
		blockLocation = location.add(1, -1, -2);
		
		// Reihe Oben
		
		blockLocation = location.add(0, 2, -2);
		pattern.put(blockLocation.getWorld().getName() + "," + blockLocation.getBlockX() + "," + blockLocation.getBlockY() + "," + blockLocation.getBlockZ(), itemArray[4]);
		blockLocation = location.add(0, -2, 2);
		
		blockLocation = location.add(0, 2, -1);
		pattern.put(blockLocation.getWorld().getName() + "," + blockLocation.getBlockX() + "," + blockLocation.getBlockY() + "," + blockLocation.getBlockZ(), itemArray[4]);
		blockLocation = location.add(0, -2, 1);
		
		blockLocation = location.add(0, 2, 0);
		pattern.put(blockLocation.getWorld().getName() + "," + blockLocation.getBlockX() + "," + blockLocation.getBlockY() + "," + blockLocation.getBlockZ(), itemArray[4]);
		blockLocation = location.add(0, -2, 0);
		
		blockLocation = location.add(0, 2, 1);
		pattern.put(blockLocation.getWorld().getName() + "," + blockLocation.getBlockX() + "," + blockLocation.getBlockY() + "," + blockLocation.getBlockZ(), itemArray[4]);
		blockLocation = location.add(0, -2, -1);
		
		blockLocation = location.add(0, 2, 2);
		pattern.put(blockLocation.getWorld().getName() + "," + blockLocation.getBlockX() + "," + blockLocation.getBlockY() + "," + blockLocation.getBlockZ(), itemArray[4]);
		blockLocation = location.add(0, -2, -2);
		
		// Zaunelemente
		
		blockLocation = location.add(0, 0, -2);
		pattern.put(blockLocation.getWorld().getName() + "," + blockLocation.getBlockX() + "," + blockLocation.getBlockY() + "," + blockLocation.getBlockZ(), itemArray[5]);
		blockLocation = location.add(0, 0, 2);
		
		blockLocation = location.add(0, 1, -2);
		pattern.put(blockLocation.getWorld().getName() + "," + blockLocation.getBlockX() + "," + blockLocation.getBlockY() + "," + blockLocation.getBlockZ(), itemArray[5]);
		blockLocation = location.add(0, -1, 2);
		
		blockLocation = location.add(1, 0, 2);
		pattern.put(blockLocation.getWorld().getName() + "," + blockLocation.getBlockX() + "," + blockLocation.getBlockY() + "," + blockLocation.getBlockZ(), itemArray[5]);
		blockLocation = location.add(-1, 0, -2);
		
		blockLocation = location.add(-1, 0, 2);
		pattern.put(blockLocation.getWorld().getName() + "," + blockLocation.getBlockX() + "," + blockLocation.getBlockY() + "," + blockLocation.getBlockZ(), itemArray[5]);
		blockLocation = location.add(1, 0, -2);
		
		// Torelement
		
		blockLocation = location.add(0, 0, 2);
		pattern.put(blockLocation.getWorld().getName() + "," + blockLocation.getBlockX() + "," + blockLocation.getBlockY() + "," + blockLocation.getBlockZ(), itemArray[6]);
		blockLocation = location.add(0, 0, -2);
		
		return pattern;
	}
 
	private boolean checkPlace(Map<String, String> checkList) {
		for(Entry<String, String> entry : checkList.entrySet()){
			if(entry.getValue() != null) {
				String[] clearedLoc = entry.getKey().split(",");
				Location loc = new Location(plugin.getServer().getWorld(clearedLoc[0]), Integer.parseInt(clearedLoc[1]), Integer.parseInt(clearedLoc[2]), Integer.parseInt(clearedLoc[3]));
				
				//plugin.getServer().broadcastMessage("" + loc.getBlock().getType().toString());
				
				if(loc.getBlock().getType() == Material.AIR || loc.getBlock().getType() == Material.DEAD_BUSH || loc.getBlock().getType() == Material.LONG_GRASS || loc.getBlock().getType() == Material.GRASS || loc.getBlock().getType() == Material.SAND || loc.getBlock().getType() == Material.SOIL || loc.getBlock().getType() == Material.SNOW) {
					
				} else {
					return false;
				}
				
			}			
		}

		return true;
	}
	
	public void initiateSmallTent(Player player, Location location) {
		Map<String, String> blockList = new HashMap<String, String>();
		
		ItemStack book = player.getItemInHand();
		BookMeta meta = (BookMeta) book.getItemMeta();
		
		player.sendMessage(meta.getPage(3));
		
		String[] itemArray = meta.getPage(3).split("-");
		
		blockList = buildPattern(location, itemArray);
		if(checkPlace(blockList) == true) {
			player.sendMessage("Gehe ein Stück zur Seite, das Zelt wird gleich errichtet.");
			plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new buildSmallTent(blockList), 5 * 20L);
			player.setItemInHand(null);
			return;
		} else {
			player.sendMessage("Du kannst hier kein Zelt platzieren.");
			return;
		}
	}
	
	public class buildSmallTent implements Runnable {
		
		private Map<String, String> blockCache = new HashMap<String, String>();
		private Map<String, String> buildList;
		
		public buildSmallTent(Map<String, String> blockList) {
			this.buildList = blockList;
		}

		@SuppressWarnings("deprecation")
		public void run() {
			for(Entry<String, String> entry : buildList.entrySet()){
				if(entry.getValue() != null) {
					String[] clearedLoc = entry.getKey().split(",");
					Location loc = new Location(plugin.getServer().getWorld(clearedLoc[0]), Integer.parseInt(clearedLoc[1]), Integer.parseInt(clearedLoc[2]), Integer.parseInt(clearedLoc[3]));
					
					blockCache.put(entry.getKey(), loc.getBlock().getType().toString() + "," + loc.getBlock().getData());
					plugin.resetOnShutdown.put(entry.getKey(), loc.getBlock().getType().toString() + "," + loc.getBlock().getData());
					String[] blockType = entry.getValue().split(",");
					loc.getBlock().setType(Material.getMaterial(blockType[0]));
					loc.getBlock().setData((byte) Integer.parseInt(blockType[1]));
					plugin.forbidBreak.add(entry.getKey());
					
				}			
			}		
			
			plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new resetSmallTent(blockCache), 300 * 20L);
		}		
		
	}

	private class resetSmallTent implements Runnable{

		private Map<String, String> blockCache = new HashMap<String, String>();
		
		public resetSmallTent(Map<String, String> blockCache) {
			this.blockCache = blockCache;
		}

		@SuppressWarnings("deprecation")
		public void run() {
			Location location = null;
			for(Entry<String, String> entry : blockCache.entrySet()){
				String[] locs = entry.getKey().split(",");
				location = new Location(plugin.getServer().getWorld(locs[0]), Integer.parseInt(locs[1]), Integer.parseInt(locs[2]), Integer.parseInt(locs[3]));
				String[] blockType = entry.getValue().split(",");
				location.getBlock().setType(Material.getMaterial(blockType[0]));
				location.getBlock().setData((byte) Integer.parseInt(blockType[1]));
				plugin.forbidBreak.remove(entry.getKey());
				plugin.resetOnShutdown.remove(entry.getKey());
			}
		}

	}
	
}