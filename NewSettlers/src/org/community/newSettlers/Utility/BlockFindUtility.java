package org.community.newSettlers.Utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;

public class BlockFindUtility {

	/*public Map<Location, Block> getBlocksInRange(Block b, int x, int y, int z) {
		Map<Location, Block> result = new HashMap<Location, Block>();
		Location l = b.getLocation();
		for (int i = l.getBlockX() - x; i <= l.getBlockX() + x; i++) {
			for (int j = l.getBlockY() - y; j <= l.getBlockY() + y; j++) {
				for (int k = l.getBlockZ() - z; k <= l.getBlockZ() + z; k++) {
					Block bn = b.getRelative(i, j, k);
					result.put(bn.getLocation(), bn);
				}
			}
		}
		return result;
	}*/
	
	public Map<Location, Block> getBlocksInRange(Block b, int x, int y, int z) {
		Map<Location, Block> result = new HashMap<Location, Block>();
		Location l = b.getLocation();
		World world = b.getWorld();
		for (int i = (-1 * x); i <= x; i++) {
			for (int j = (-1 * y); j <= y; j++) {
				for (int k = (-1 * z); k <= z; k++) {
					Block bn = world.getBlockAt(l.getBlockX() + i, l.getBlockY() + j, l.getBlockZ() + k);
					result.put(bn.getLocation(), bn);
				}
			}
		}
		return result;
	}

	public Map<Location, Block> getBlocksInRange(Location lStart, int x, int y, int z) {
		return getBlocksInRange(lStart.getBlock(), x, y, z);
	}

	public List<Block> getBlocksInRangeByMaterial(Block b, int x, int y, int z, Material m) {
		List<Block> result = new ArrayList<Block>();
		Location l = b.getLocation();
		for (int i = l.getBlockX() - x; i <= l.getBlockX() + x; i++) {
			for (int j = l.getBlockY() - y; i <= l.getBlockY() + y; j++) {
				for (int k = l.getBlockZ() - z; k <= l.getBlockZ() + z; k++) {
					Block bn = b.getRelative(i, j, k);
					if (bn.getType().equals(m))
						result.add(bn);
				}
			}
		}
		return result;
	}

	public List<Block> getBlocksInRangeByMaterial(Block b, int x, int y, int z) {
		List<Block> result = new ArrayList<Block>();
		Location l = b.getLocation();
		for (int i = l.getBlockX() - x; i <= l.getBlockX() + x; i++) {
			for (int j = l.getBlockY() - y; i <= l.getBlockY() + y; j++) {
				for (int k = l.getBlockZ() - z; k <= l.getBlockZ() + z; k++) {
					Block bn = b.getRelative(i, j, k);
					result.add(bn);
				}
			}
		}
		return result;
	}
}
