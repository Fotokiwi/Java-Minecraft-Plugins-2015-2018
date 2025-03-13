package org.community.ambience.AmbientObjects.dataObjects;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;
import org.community.ambience.Ambience;
import org.community.ambience.AmbientObjects.Utility.DetectBuildPatternMatch;
import org.community.ambience.AmbientObjects.Utility.ItemTypeParser;

public class Blueprint {

	public Ambience plugin = null;
	private FileConfiguration BlueprintData = null;
	private File BlueprintDataFile = null;
	private String displayName = "";
	private String identifierName = "";
	private Map<String, String> buildPattern = null;
	private List<ItemStack> requiredMaterials = null;

	public Blueprint(Ambience plugin) {
		this.plugin = plugin;
		buildPattern = new HashMap<String, String>();
		requiredMaterials = new ArrayList<ItemStack>();
	}

	public Blueprint(Ambience plugin, String identifierName) {
		this(plugin);
		this.identifierName = identifierName;
		this.displayName = identifierName;
	}

	public Blueprint(Ambience plugin, String identifierName, Map<String, String> m) {
		this(plugin);
		this.identifierName = identifierName;
		this.displayName = identifierName;
		buildPattern = m;
		// calculateRequiredMaterials();
	}

	public Blueprint(Ambience plugin, String identifierName, Map<String, String> m, String displayName) {
		this(plugin, identifierName, m);
		this.displayName = displayName;
	}

	public String getDisplayName() {
		return displayName;
	}

	public String getIdentifierName() {
		return identifierName;
	}

	public List<ItemStack> getRequiredMaterials() {
		return requiredMaterials;
	}

	public Map<String, String> getBuildPattern() {
		return buildPattern;
	}

	public void SaveToFile() {
		getBlueprintData();
		BlueprintData.set("Displayname", displayName);

		BlueprintData.createSection("buildPattern", buildPattern);

		saveBlueprintData();
	}

	public void LoadFromFile() {
		getBlueprintData();
		displayName = BlueprintData.getString("Displayname");

		ConfigurationSection buildPatternCS = BlueprintData.getConfigurationSection("buildPattern");
		Map<String, Object> dataFileMap = buildPatternCS.getValues(false);
		for (Entry<String, Object> e : dataFileMap.entrySet()) {
			try {
				buildPattern.put(e.getKey(), (String) e.getValue());
			} catch (ClassCastException cce) {
				plugin.LogError("couldn't cast value of buildpattern from object to string: " + cce);
			}
		}

	}

	private void reloadBlueprintData() {
		if (BlueprintDataFile == null) {
			BlueprintDataFile = new File(plugin.getDataFolder(), "/Blueprints/" + identifierName + ".yml");
		}
		BlueprintData = YamlConfiguration.loadConfiguration(BlueprintDataFile);
	}

	private FileConfiguration getBlueprintData() {
		if (BlueprintData == null) {
			reloadBlueprintData();
		}
		return BlueprintData;
	}

	private void saveBlueprintData() {
		if (BlueprintData == null || BlueprintDataFile == null) {
			return;
		}
		try {
			BlueprintData.save(BlueprintDataFile);
		} catch (IOException ex) {
			Logger.getLogger(JavaPlugin.class.getName()).log(Level.SEVERE,
					"Could not save config to " + BlueprintDataFile, ex);
		}
	}

	private boolean checkPlace(Map<String, String> absolutePos) {
		for (Entry<String, String> entry : absolutePos.entrySet()) {
			if (entry.getValue() != null) {
				String[] clearedLoc = entry.getKey().split(",");
				Location loc = new Location(plugin.getServer().getWorld(clearedLoc[0]),
						Integer.parseInt(clearedLoc[1]), Integer.parseInt(clearedLoc[2]),
						Integer.parseInt(clearedLoc[3]));
				// Ambience.LogInfo(loc.toVector().toString() + "  " +
				// loc.getBlock().getType());
				if (loc.getBlock().getType() == Material.AIR || loc.getBlock().getType() == Material.DEAD_BUSH
						|| loc.getBlock().getType() == Material.DIRT || loc.getBlock().getType() == Material.LONG_GRASS
						|| loc.getBlock().getType() == Material.GRASS || loc.getBlock().getType() == Material.SAND
						|| loc.getBlock().getType() == Material.SOIL || loc.getBlock().getType() == Material.SNOW) {

				} else {

					return false;
				}

			}
		}
		return true;
	}

	public void initiateAmbientObjecte(Player player, Location location) {
		Map<String, String> absolutePos = transferRelativeToAbsolute(location);
		// plugin.LogInfo(absolutePos.size() + " size map");
		if (collidesWithOtherAmbienceObject(absolutePos)) {
			player.sendMessage("Das Objekt kollidiert mit einem bereits gebauten Ambienteobjekt.");
			return;
		}

		if (checkPlace(absolutePos)) {
			plugin.cache.getPlacedBlueprints().put(location, DetectBuildPatternMatch.getBoundingBox(absolutePos));
			player.sendMessage("Gehe ein Stück zur Seite, das Objekt " + displayName + " wird gleich gebaut.");
			plugin.getServer()
					.getScheduler()
					.scheduleSyncDelayedTask(plugin,
							new buildAmbientObject(notLadderAndTorchList(absolutePos), 300 * 20L), 5 * 20L);
			plugin.getServer()
					.getScheduler()
					.scheduleSyncDelayedTask(plugin,
							new buildAmbientObject(ladderAndTorchList(absolutePos), 295 * 20L), 7 * 20L);
			plugin.getServer()
					.getScheduler()
					.scheduleSyncDelayedTask(plugin, new buildAmbientObject(carpetList(absolutePos), 290 * 20L),
							9 * 20L);
			plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new resetMapEntry(location), 305 * 20L);
			player.setItemInHand(null);
			return;
		} else {
			player.sendMessage("Du kannst hier das Objekt " + displayName + " nicht platzieren.");
			return;
		}
	}

	public Map<String, String> transferRelativeToAbsolute(Location center) {
		Map<String, String> absolutePos = new HashMap<String, String>();
		for (Entry<String, String> e : buildPattern.entrySet()) {
			// Ambience.LogInfo(e.getKey());
			Location loc = center;
			String[] clearedLoc = e.getKey().split(",");
			loc.add(Integer.parseInt(clearedLoc[1]), Integer.parseInt(clearedLoc[2]), Integer.parseInt(clearedLoc[3]));
			// Ambience.LogInfo(clearedLoc[0]+","+loc.getBlockX()+","+loc.getBlockY()+","+loc.getBlockZ());
			absolutePos
					.put(center.getWorld().getName() + "," + loc.getBlockX() + "," + loc.getBlockY() + ","
							+ loc.getBlockZ(), e.getValue());
			loc.add((-1) * Integer.parseInt(clearedLoc[1]), (-1) * Integer.parseInt(clearedLoc[2]),
					(-1) * Integer.parseInt(clearedLoc[3]));
		}

		return absolutePos;
	}

	public class buildAmbientObject implements Runnable {

		private Map<String, String> blockCache = new HashMap<String, String>();
		private Map<String, String> blockList = new HashMap<String, String>();
		private long delay = 0;

		public buildAmbientObject(Map<String, String> blockList, long delay) {
			this.blockList = blockList;
			this.delay = delay;
		}

		@SuppressWarnings("deprecation")
		public void run() {

			for (Entry<String, String> entry : blockList.entrySet()) {
				if (entry.getValue() != null) {
					String[] clearedLoc = entry.getKey().split(",");
					Location loc = new Location(plugin.getServer().getWorld(clearedLoc[0]),
							Integer.parseInt(clearedLoc[1]), Integer.parseInt(clearedLoc[2]),
							Integer.parseInt(clearedLoc[3]));
					// Bukkit.broadcastMessage("changed block at " +
					// loc.toVector() + " " + loc.getWorld().getName() + "    "
					// + entry.getValue());
					blockCache
							.put(entry.getKey(), loc.getBlock().getType().toString() + "," + loc.getBlock().getData());
					plugin.resetOnShutdown.put(entry.getKey(), loc.getBlock().getType().toString() + ","
							+ loc.getBlock().getData());
					String[] blockType = entry.getValue().split(",");
					// Bukkit.broadcastMessage(blockType[0] + "   " +
					// blockType[1]);
					loc.getBlock().setType(Material.getMaterial(blockType[0]));
					loc.getBlock().setData((byte) Integer.parseInt(blockType[1]));
					plugin.forbidBreak.add(entry.getKey());

				}
			}

			plugin.getServer().getScheduler()
					.scheduleSyncDelayedTask(plugin, new resetAmbientObject(blockCache), delay);
		}
	}

	private class resetAmbientObject implements Runnable {

		private Map<String, String> blockCache;

		public resetAmbientObject(Map<String, String> blockCache) {
			this.blockCache = blockCache;

		}

		@SuppressWarnings("deprecation")
		public void run() {

			Location location = null;
			for (Entry<String, String> entry : blockCache.entrySet()) {
				String[] locs = entry.getKey().split(",");
				location = new Location(plugin.getServer().getWorld(locs[0]), Integer.parseInt(locs[1]),
						Integer.parseInt(locs[2]), Integer.parseInt(locs[3]));
				String[] blockType = entry.getValue().split(",");
				location.getBlock().setType(Material.getMaterial(blockType[0]));
				location.getBlock().setData((byte) Integer.parseInt(blockType[1]));
				plugin.forbidBreak.remove(entry.getKey());
				plugin.resetOnShutdown.remove(entry.getKey());
			}

		}
	}

	private class resetMapEntry implements Runnable {
		Location loc;

		public resetMapEntry(Location loc) {
			this.loc = loc;
		}

		public void run() {
			try {
				plugin.cache.getPlacedBlueprints().remove(loc);
			} catch (Exception e) {
				plugin.LogError("Tried to remove a nonexisting element in the placed blueprints Map. Something went wrong.");
			}
		}
	}

	public Map<String, String> carpetList(Map<String, String> m) {
		Map<String, String> carpet = new HashMap<String, String>();
		for (Entry<String, String> e : m.entrySet()) {
			if (e.getValue().contains("CARPET")) {
				carpet.put(e.getKey(), e.getValue());
			}
		}
		// plugin.LogInfo(torchAndLadder.size() + " size lat map");
		return carpet;
	}

	public Map<String, String> ladderAndTorchList(Map<String, String> m) {
		Map<String, String> torchAndLadder = new HashMap<String, String>();
		for (Entry<String, String> e : m.entrySet()) {
			if (e.getValue().contains("TORCH") || e.getValue().contains("LADDER") || e.getValue().contains("WOOD_DOOR")
					|| e.getValue().contains("IRON_DOOR") || e.getValue().contains("TRAP_DOOR")
					|| e.getValue().contains("PAINTING") || e.getValue().contains("RAIL")
					|| e.getValue().contains("BED")) {
				torchAndLadder.put(e.getKey(), e.getValue());
			}
		}
		// plugin.LogInfo(torchAndLadder.size() + " size lat map");
		return torchAndLadder;
	}

	public Map<String, String> notLadderAndTorchList(Map<String, String> m) {
		Map<String, String> notTorchAndLadder = new HashMap<String, String>();
		for (Entry<String, String> e : m.entrySet()) {
			if (!(e.getValue().contains("TORCH") || e.getValue().contains("LADDER")
					|| e.getValue().contains("WOOD_DOOR") || e.getValue().contains("IRON_DOOR")
					|| e.getValue().contains("TRAP_DOOR") || e.getValue().contains("CARPET")
					|| e.getValue().contains("PAINTING") || e.getValue().contains("RAIL") || e.getValue().contains(
					"BED"))) {
				notTorchAndLadder.put(e.getKey(), e.getValue());
			}

		}
		// plugin.LogInfo(notTorchAndLadder.size() + " size nlat map");
		return notTorchAndLadder;
	}

	public void calculateRequiredMaterials() {
		for (Entry<String, String> e : buildPattern.entrySet()) {
			String[] blockType = e.getValue().split(",");
			ItemStack is = new ItemStack(Material.getMaterial(blockType[0]), 1, Byte.parseByte(blockType[1]));
			is = changeItemStackAccordingly(is);
			addItemStackToRequiredMaterials(is);
		}
		plugin.LogInfo("requiredMaterials.size: " + requiredMaterials.size());
	}

	public void addItemStackToRequiredMaterials(ItemStack is) {
		if (is.getType().equals(Material.AIR))
			return;
		boolean isAlreadyInList = false;
		for (ItemStack item : requiredMaterials) {
			if (item.isSimilar(is)) {
				if ((item.getAmount() + 1) > item.getMaxStackSize()) {
					requiredMaterials.add(is);
					isAlreadyInList = true;
					break;
				} else {
					item.setAmount(item.getAmount() + 1);
					isAlreadyInList = true;
					break;
				}
			}
		}
		if (!isAlreadyInList) {
			requiredMaterials.add(is);
		}
	}

	public ItemStack changeItemStackAccordingly(ItemStack is) {
		if (is.getType().equals(Material.TORCH))
			return new ItemStack(Material.TORCH, 1);
		if (is.getType().equals(Material.LADDER))
			return new ItemStack(Material.LADDER, 1);
		if (!ItemTypeParser.getBlockType(null, is).equals(""))
			is = new ItemStack(Material.getMaterial(ItemTypeParser.getBlockType(null, is)), 1);
		return is;
	}

	public boolean collidesWithOtherAmbienceObject(Map<String, String> buildPattern) {
		for (Entry<String, String> e : buildPattern.entrySet()) {
			String[] clearedLoc = e.getKey().split(",");
			Vector v = new Vector(Integer.parseInt(clearedLoc[1]), Integer.parseInt(clearedLoc[2]),
					Integer.parseInt(clearedLoc[3]));
			for (Entry<Location, Vector[]> e2 : plugin.cache.getPlacedBlueprints().entrySet()) {
				if (v.isInAABB(e2.getValue()[0], e2.getValue()[1]))
					return true;
			}
		}
		return false;
	}
}
