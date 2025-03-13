package org.community.CrazyLabs.Builder;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.Material;
import org.bukkit.Location;
import org.community.CrazyLabs.CrazyLabs;

public class Builder {

	private CrazyLabs plugin;
	private ModuleSet ms;
	private Layer[] layers;
	private Location startLocation;
	private int moduleSize;
	private String theme;

	public Builder(CrazyLabs plugin, Location startLocation, int moduleSize, String theme) {
		this.plugin = plugin;
		ms = new ModuleSet(plugin);
		this.startLocation = startLocation;
		this.moduleSize = moduleSize;
		this.theme = theme;
	}

	public Layer[] getLayers() {
		return layers;
	}

	public void setLayers(Layer[] layers) {
		this.layers = layers;
	}

	public void startBuildingMaze() {
		if (layers.length < 1) {
			CrazyLabs.LogError("Keine Layer an Builder übergegeben");
			return;
		}
		if (startLocation == null) {
			CrazyLabs.LogError("Keine Anfangsposition übergegeben");
			return;
		}
		if (moduleSize < 1) {
			CrazyLabs.LogError("Falsche Modulgröße übergegeben");
			return;
		}
		ms.LoadAllBuildPatternAndAddModules(theme, moduleSize);
		for (int y = 0; y < layers.length; y++) {
			for (int z = 0; z < layers[y].getDimensionz(); z++) {
				for (int x = 0; x < layers[y].getDimensionx(); x++) {
					if(layers[y].getCell(x, z) != null)
						buildOneCell(x, y, z);
				}
			}
		}

	}

	private void buildOneCell(int x, int y, int z) {
		long uniqueDelay = ((x + (z * layers[y].getDimensionx()) + (y * layers[y].getDimensionx() * layers[y].getDimensionz())) * 2L);

		Module m = ms.getModuleByForm(cellToForm(layers[y].getCell(x, z)));

		if (m == null) {
			CrazyLabs.LogError("Kein Module in der Form " + cellToForm(layers[y].getCell(x, z)) + " gefunden.");
			return;
		}
		startLocation.add(x * moduleSize, y * moduleSize, z * moduleSize);

		if (m.getBuildPattern() == null) {
			CrazyLabs.LogError("Für ein Module in der Form " + cellToForm(layers[y].getCell(x, z)) + " wurde kein Buildpattern gefunden.");
			startLocation.subtract(x * moduleSize, y * moduleSize, z * moduleSize);
			return;
		}
		Map<String, String> absolutePosition = transferRelativeToAbsolute(startLocation, m.getBuildPattern());

		plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new buildOneModule(absolutePosition), uniqueDelay);

		startLocation.subtract(x * moduleSize, y * moduleSize, z * moduleSize);

	}

	public String cellToForm(Cell c) {
		return "" + (c.isUp() ? "U" : "") + (c.isDown() ? "D" : "") + (c.isLeft() ? "L" : "") + (c.isRight() ? "R" : "") + (c.isFront() ? "F" : "")
				+ (c.isBack() ? "B" : "");
	}

	public Map<String, String> transferRelativeToAbsolute(Location center, Map<String, String> buildPattern) {
		Map<String, String> absolutePos = new HashMap<String, String>();
		for (Entry<String, String> e : buildPattern.entrySet()) {
			// Ambience.LogInfo(e.getKey());
			Location loc = center;
			String[] clearedLoc = e.getKey().split(",");
			loc.add(Integer.parseInt(clearedLoc[1]), Integer.parseInt(clearedLoc[2]), Integer.parseInt(clearedLoc[3]));
			// Ambience.LogInfo(clearedLoc[0]+","+loc.getBlockX()+","+loc.getBlockY()+","+loc.getBlockZ());
			absolutePos.put(center.getWorld().getName() + "," + loc.getBlockX() + "," + loc.getBlockY() + "," + loc.getBlockZ(), e.getValue());
			loc.add((-1) * Integer.parseInt(clearedLoc[1]), (-1) * Integer.parseInt(clearedLoc[2]), (-1) * Integer.parseInt(clearedLoc[3]));
		}

		return absolutePos;
	}

	public class buildOneModule implements Runnable {

		private Map<String, String> blockList = new HashMap<String, String>();

		public buildOneModule(Map<String, String> blockList) {
			this.blockList = blockList;
		}

		@SuppressWarnings("deprecation")
		public void run() {

			for (Entry<String, String> entry : blockList.entrySet()) {
				if (entry.getValue() != null) {
					String[] clearedLoc = entry.getKey().split(",");
					Location loc = new Location(plugin.getServer().getWorld(clearedLoc[0]), Integer.parseInt(clearedLoc[1]), Integer.parseInt(clearedLoc[2]),
							Integer.parseInt(clearedLoc[3]));
					//plugin.LogInfo("changed block at location " + loc.getWorld() + ", " + loc.getBlockX() + ", " + loc.getBlockY() + ", " + loc.getBlockZ());
					String[] blockType = entry.getValue().split(",");
					loc.getBlock().setTypeIdAndData(Material.getMaterial(blockType[0]).getId(), (byte) Integer.parseInt(blockType[1]), false);

				}
			}

		}
	}
}
