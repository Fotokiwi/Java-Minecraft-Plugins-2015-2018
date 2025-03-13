package org.community.ambience.AmbientObjects.Zelte;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.community.ambience.Ambience;

public class mittleresZeltlager {	
	
	private Ambience plugin;

	public mittleresZeltlager(Ambience plugin) {
		this.plugin = plugin;
	}
	
	private String getLocationString(Location location) {
		String loc = location.getWorld().getName() + "," + location.getBlockX() + "," + location.getBlockY() + "," + location.getBlockZ();
		return loc;		
	}
	
	private Map<String, String> buildPattern(Location location) {
		Location blockLocation = null;
		Map<String, String> pattern = new HashMap<String, String>();
		
		blockLocation = location.add(5, -1, 0);
		pattern.put(getLocationString(blockLocation), "LOG,0");
		blockLocation = location.add(-5, 1, 0);
		
		blockLocation = location.add(-1, 0, -9);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(1, 0, 9);
		
		blockLocation = location.add(0, 0, -9);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(0, 0, 9);
		
		blockLocation = location.add(1, 0, -9);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-1, 0, 9);
		
		blockLocation = location.add(-3, 0, -8);
		pattern.put(getLocationString(blockLocation), "LOG,0");
		blockLocation = location.add(3, 0, 8);
		
		blockLocation = location.add(-2, 0, -8);
		pattern.put(getLocationString(blockLocation), "LOG,0");
		blockLocation = location.add(2, 0, 8);
		
		blockLocation = location.add(-1, 0, -8);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(1, 0, 8);
		
		blockLocation = location.add(0, 0, -8);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(0, 0, 8);
		
		blockLocation = location.add(1, 0, -8);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-1, 0, 8);
		
		blockLocation = location.add(2, 0, -8);
		pattern.put(getLocationString(blockLocation), "LOG,0");
		blockLocation = location.add(-2, 0, 8);
		
		blockLocation = location.add(3, 0, -8);
		pattern.put(getLocationString(blockLocation), "LOG,0");
		blockLocation = location.add(-3, 0, 8);
		
		blockLocation = location.add(-5, 0, -7);
		pattern.put(getLocationString(blockLocation), "LOG,0");
		blockLocation = location.add(5, 0, 7);
		
		blockLocation = location.add(-4, 0, -7);
		pattern.put(getLocationString(blockLocation), "LOG,0");
		blockLocation = location.add(4, 0, 7);
		
		blockLocation = location.add(-3, 0, -7);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(3, 0, 7);
		
		blockLocation = location.add(-2, 0, -7);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(2, 0, 7);
		
		blockLocation = location.add(-1, 0, -7);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(1, 0, 7);
		
		blockLocation = location.add(0, 0, -7);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(0, 0, 7);
		
		blockLocation = location.add(1, 0, -7);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-1, 0, 7);
		
		blockLocation = location.add(2, 0, -7);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-2, 0, 7);
		
		blockLocation = location.add(3, 0, -7);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-3, 0, 7);
		
		blockLocation = location.add(4, 0, -7);
		pattern.put(getLocationString(blockLocation), "LOG,0");
		blockLocation = location.add(-4, 0, 7);
		
		blockLocation = location.add(5, 0, -7);
		pattern.put(getLocationString(blockLocation), "LOG,0");
		blockLocation = location.add(-5, 0, 7);
		
		blockLocation = location.add(-6, 0, -6);
		pattern.put(getLocationString(blockLocation), "LOG,0");
		blockLocation = location.add(6, 0, 6);
		
		blockLocation = location.add(-5, 0, -6);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(5, 0, 6);
		
		blockLocation = location.add(-4, 0, -6);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(4, 0, 6);
		
		blockLocation = location.add(-3, 0, -6);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(3, 0, 6);
		
		blockLocation = location.add(-1, 0, -6);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(1, 0, 6);
		
		blockLocation = location.add(0, 0, -6);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(0, 0, 6);
		
		blockLocation = location.add(1, 0, -6);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-1, 0, 6);
		
		blockLocation = location.add(3, 0, -6);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-3, 0, 6);
		
		blockLocation = location.add(4, 0, -6);
		pattern.put(getLocationString(blockLocation), "CHEST,3");
		blockLocation = location.add(-4, 0, 6);
		
		blockLocation = location.add(5, 0, -6);
		pattern.put(getLocationString(blockLocation), "CHEST,3");
		blockLocation = location.add(-5, 0, 6);
		
		blockLocation = location.add(6, 0, -6);
		pattern.put(getLocationString(blockLocation), "LOG,0");
		blockLocation = location.add(-6, 0, 6);
		
		blockLocation = location.add(-7, 0, -5);
		pattern.put(getLocationString(blockLocation), "LOG,0");
		blockLocation = location.add(7, 0, 5);
		
		blockLocation = location.add(-6, 0, -5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(6, 0, 5);
		
		blockLocation = location.add(-5, 0, -5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(5, 0, 5);
		
		blockLocation = location.add(-4, 0, -5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(4, 0, 5);
		
		blockLocation = location.add(-3, 0, -5);
		pattern.put(getLocationString(blockLocation), "LOG,0");
		blockLocation = location.add(3, 0, 5);
		
		blockLocation = location.add(2, 0, -6);
		pattern.put(getLocationString(blockLocation), "FENCE,0");
		blockLocation = location.add(-2, 0, 6);
		
		blockLocation = location.add(-2, 0, -6);
		pattern.put(getLocationString(blockLocation), "FENCE,0");
		blockLocation = location.add(2, 0, 6);
		
		blockLocation = location.add(-1, 0, -5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(1, 0, 5);
		
		blockLocation = location.add(0, 0, -5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(0, 0, 5);
		
		blockLocation = location.add(1, 0, -5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-1, 0, 5);
		
		blockLocation = location.add(3, 0, -5);
		pattern.put(getLocationString(blockLocation), "LOG,0");
		blockLocation = location.add(-3, 0, 5);
		
		blockLocation = location.add(4, 0, -5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-4, 0, 5);
		
		blockLocation = location.add(5, 0, -5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-5, 0, 5);
		
		blockLocation = location.add(6, 0, -5);
		pattern.put(getLocationString(blockLocation), "CHEST,4");
		blockLocation = location.add(-6, 0, 5);
		
		blockLocation = location.add(7, 0, -5);
		pattern.put(getLocationString(blockLocation), "LOG,0");
		blockLocation = location.add(-7, 0, 5);
		
		blockLocation = location.add(-7, 0, -4);
		pattern.put(getLocationString(blockLocation), "LOG,0");
		blockLocation = location.add(7, 0, 4);
		
		blockLocation = location.add(-6, 0, -4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(6, 0, 4);
		
		blockLocation = location.add(-5, 0, -4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(5, 0, 4);
		
		blockLocation = location.add(-4, 0, -4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(4, 0, 4);
		
		blockLocation = location.add(-3, 0, -4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(3, 0, 4);
		
		blockLocation = location.add(-2, 0, -4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(2, 0, 4);
		
		blockLocation = location.add(-1, 0, -4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(1, 0, 4);
		
		blockLocation = location.add(0, 0, -4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(0, 0, 4);
		
		blockLocation = location.add(1, 0, -4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-1, 0, 4);
		
		blockLocation = location.add(2, 0, -4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-2, 0, 4);
		
		blockLocation = location.add(3, 0, -4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-3, 0, 4);
		
		blockLocation = location.add(4, 0, -4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-4, 0, 4);
		
		blockLocation = location.add(5, 0, -4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-5, 0, 4);
		
		blockLocation = location.add(6, 0, -4);
		pattern.put(getLocationString(blockLocation), "CHEST,4");
		blockLocation = location.add(-6, 0, 4);
		
		blockLocation = location.add(7, 0, -4);
		pattern.put(getLocationString(blockLocation), "LOG,0");
		blockLocation = location.add(-7, 0, 4);
		
		blockLocation = location.add(-8, 0, -3);
		pattern.put(getLocationString(blockLocation), "LOG,0");
		blockLocation = location.add(8, 0, 3);
		
		blockLocation = location.add(-7, 0, -3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(7, 0, 3);
		
		blockLocation = location.add(-6, 0, -3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(6, 0, 3);
		
		blockLocation = location.add(-5, 0, -3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(5, 0, 3);
		
		blockLocation = location.add(-4, 0, -3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(4, 0, 3);
		
		blockLocation = location.add(-3, 0, -3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(3, 0, 3);
		
		blockLocation = location.add(-2, 0, -3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(2, 0, 3);
		
		blockLocation = location.add(-1, 0, -3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(1, 0, 3);
		
		blockLocation = location.add(0, 0, -3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(0, 0, 3);
		
		blockLocation = location.add(1, 0, -3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-1, 0, 3);
		
		blockLocation = location.add(2, 0, -3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-2, 0, 3);
		
		blockLocation = location.add(3, 0, -3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-3, 0, 3);
		
		blockLocation = location.add(4, 0, -3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-4, 0, 3);
		
		blockLocation = location.add(5, 0, -3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-5, 0, 3);
		
		blockLocation = location.add(6, 0, -3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-6, 0, 3);
		
		blockLocation = location.add(7, 0, -3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-7, 0, 3);
		
		blockLocation = location.add(8, 0, -3);
		pattern.put(getLocationString(blockLocation), "LOG,0");
		blockLocation = location.add(-8, 0, 3);
		
		blockLocation = location.add(-8, 0, -2);
		pattern.put(getLocationString(blockLocation), "LOG,0");
		blockLocation = location.add(8, 0, 2);
		
		blockLocation = location.add(-7, 0, -2);
		pattern.put(getLocationString(blockLocation), "WOOL,12");
		blockLocation = location.add(7, 0, 2);
		
		blockLocation = location.add(-6, 0, -2);
		pattern.put(getLocationString(blockLocation), "WOOL,12");
		blockLocation = location.add(6, 0, 2);
		
		blockLocation = location.add(-5, 0, -2);
		pattern.put(getLocationString(blockLocation), "WOOL,12");
		blockLocation = location.add(5, 0, 2);
		
		blockLocation = location.add(-4, 0, -2);
		pattern.put(getLocationString(blockLocation), "WOOL,12");
		blockLocation = location.add(4, 0, 2);
		
		blockLocation = location.add(-3, 0, -2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(3, 0, 2);
		
		blockLocation = location.add(-2, 0, -2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(2, 0, 2);
		
		blockLocation = location.add(-1, 0, -2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(1, 0, 2);
		
		blockLocation = location.add(0, 0, -2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(0, 0, 2);
		
		blockLocation = location.add(1, 0, -2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-1, 0, 2);
		
		blockLocation = location.add(2, 0, -2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-2, 0, 2);
		
		blockLocation = location.add(3, 0, -2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-3, 0, 2);
		
		blockLocation = location.add(4, 0, -2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-4, 0, 2);
		
		blockLocation = location.add(5, 0, -2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-5, 0, 2);
		
		blockLocation = location.add(6, 0, -2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-6, 0, 2);
		
		blockLocation = location.add(8, 0, -2);
		pattern.put(getLocationString(blockLocation), "LOG,0");
		blockLocation = location.add(-8, 0, 2);
		
		blockLocation = location.add(-9, 0, -1);
		pattern.put(getLocationString(blockLocation), "LOG,0");
		blockLocation = location.add(9, 0, 1);
		
		blockLocation = location.add(-8, 0, -1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(8, 0, 1);
		
		blockLocation = location.add(-7, 0, -1);
		pattern.put(getLocationString(blockLocation), "FENCE,0");
		blockLocation = location.add(7, 0, 1);
		
		blockLocation = location.add(-6, 0, -1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(6, 0, 1);
		
		blockLocation = location.add(-5, 0, -1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(5, 0, 1);
		
		blockLocation = location.add(-4, 0, -1);
		pattern.put(getLocationString(blockLocation), "FENCE,0");
		blockLocation = location.add(4, 0, 1);
		
		blockLocation = location.add(-3, 0, -1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(3, 0, 1);
		
		blockLocation = location.add(-2, 0, -1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(2, 0, 1);
		
		blockLocation = location.add(-1, 0, -1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(1, 0, 1);
		
		blockLocation = location.add(0, 0, -1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(0, 0, 1);
		
		blockLocation = location.add(1, 0, -1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-1, 0, 1);
		
		blockLocation = location.add(2, 0, -1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-2, 0, 1);
		
		blockLocation = location.add(3, 0, -1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-3, 0, 1);
		
		blockLocation = location.add(4, 0, -1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-4, 0, 1);
		
		blockLocation = location.add(5, 0, -1);
		pattern.put(getLocationString(blockLocation), "COBBLESTONE_STAIRS,3");
		blockLocation = location.add(-5, 0, 1);
		
		blockLocation = location.add(6, 0, -1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-6, 0, 1);
		
		blockLocation = location.add(7, 0, -1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-7, 0, 1);
		
		blockLocation = location.add(8, 0, -1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-8, 0, 1);
		
		blockLocation = location.add(9, 0, -1);
		pattern.put(getLocationString(blockLocation), "LOG,0");
		blockLocation = location.add(-9, 0, 1);
		
		blockLocation = location.add(-9, 0, 0);
		pattern.put(getLocationString(blockLocation), "LOG,0");
		blockLocation = location.add(9, 0, 0);
		
		blockLocation = location.add(-8, 0, 0);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(8, 0, 0);
		
		blockLocation = location.add(-7, 0, 0);
		pattern.put(getLocationString(blockLocation), "FENCE,0");
		blockLocation = location.add(7, 0, 0);
		
		blockLocation = location.add(-6, 0, 0);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(6, 0, 0);
		
		blockLocation = location.add(-5, 0, 0);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(5, 0, 0);
		
		blockLocation = location.add(-3, 0, 0);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(3, 0, 0);
		
		blockLocation = location.add(-2, 0, 0);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(2, 0, 0);
		
		blockLocation = location.add(-1, 0, 0);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(1, 0, 0);
		
		blockLocation = location.add(0, 0, 0);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(0, 0, 0);
		
		blockLocation = location.add(1, 0, 0);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-1, 0, 0);
		
		blockLocation = location.add(2, 0, 0);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-2, 0, 0);
		
		blockLocation = location.add(3, 0, 0);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-3, 0, 0);
		
		blockLocation = location.add(4, 0, 0);
		pattern.put(getLocationString(blockLocation), "STEP,3");
		blockLocation = location.add(-4, 0, 0);
		
		blockLocation = location.add(6, 0, 0);
		pattern.put(getLocationString(blockLocation), "STEP,3");
		blockLocation = location.add(-6, 0, 0);
		
		blockLocation = location.add(7, 0, 0);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-7, 0, 0);
		
		blockLocation = location.add(8, 0, 0);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-8, 0, 0);
		
		blockLocation = location.add(9, 0, 0);
		pattern.put(getLocationString(blockLocation), "LOG,0");
		blockLocation = location.add(-9, 0, 0);
		
		blockLocation = location.add(-9, 0, 1);
		pattern.put(getLocationString(blockLocation), "LOG,0");
		blockLocation = location.add(9, 0, -1);
		
		blockLocation = location.add(-8, 0, 1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(8, 0, -1);
		
		blockLocation = location.add(-7, 0, 1);
		pattern.put(getLocationString(blockLocation), "FENCE,0");
		blockLocation = location.add(7, 0, -1);
		
		blockLocation = location.add(-6, 0, 1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(6, 0, -1);
		
		blockLocation = location.add(-5, 0, 1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(5, 0, -1);
		
		blockLocation = location.add(-4, 0, 1);
		pattern.put(getLocationString(blockLocation), "FENCE,0");
		blockLocation = location.add(4, 0, -1);
		
		blockLocation = location.add(-3, 0, 1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(3, 0, -1);
		
		blockLocation = location.add(-2, 0, 1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(2, 0, -1);
		
		blockLocation = location.add(-1, 0, 1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(1, 0, -1);
		
		blockLocation = location.add(0, 0, 1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(0, 0, -1);
		
		blockLocation = location.add(1, 0, 1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-1, 0, -1);
		
		blockLocation = location.add(2, 0, 1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-2, 0, -1);
		
		blockLocation = location.add(3, 0, 1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-3, 0, -1);
		
		blockLocation = location.add(4, 0, 1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-4, 0, -1);
		
		blockLocation = location.add(5, 0, 1);
		pattern.put(getLocationString(blockLocation), "COBBLESTONE_STAIRS,2");
		blockLocation = location.add(-5, 0, -1);
		
		blockLocation = location.add(6, 0, 1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-6, 0, -1);
		
		blockLocation = location.add(7, 0, 1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-7, 0, -1);
		
		blockLocation = location.add(8, 0, 1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-8, 0, -1);
		
		blockLocation = location.add(9, 0, 1);
		pattern.put(getLocationString(blockLocation), "LOG,0");
		blockLocation = location.add(-9, 0, -1);
		
		blockLocation = location.add(-8, 0, 2);
		pattern.put(getLocationString(blockLocation), "LOG,0");
		blockLocation = location.add(8, 0, -2);
		
		blockLocation = location.add(-7, 0, 2);
		pattern.put(getLocationString(blockLocation), "WOOL,12");
		blockLocation = location.add(7, 0, -2);
		
		blockLocation = location.add(-6, 0, 2);
		pattern.put(getLocationString(blockLocation), "WOOL,12");
		blockLocation = location.add(6, 0, -2);
		
		blockLocation = location.add(-5, 0, 2);
		pattern.put(getLocationString(blockLocation), "WOOL,12");
		blockLocation = location.add(5, 0, -2);
		
		blockLocation = location.add(-4, 0, 2);
		pattern.put(getLocationString(blockLocation), "WOOL,12");
		blockLocation = location.add(4, 0, -2);
		
		blockLocation = location.add(-3, 0, 2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(3, 0, -2);
		
		blockLocation = location.add(-2, 0, 2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(2, 0, -2);
		
		blockLocation = location.add(-1, 0, 2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(1, 0, -2);
		
		blockLocation = location.add(0, 0, 2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(0, 0, -2);
		
		blockLocation = location.add(1, 0, 2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-1, 0, -2);
		
		blockLocation = location.add(2, 0, 2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-2, 0, -2);
		
		blockLocation = location.add(3, 0, 2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-3, 0, -2);
		
		blockLocation = location.add(4, 0, 2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-4, 0, -2);
		
		blockLocation = location.add(5, 0, 2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-5, 0, -2);
		
		blockLocation = location.add(6, 0, 2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-6, 0, -2);
		
		blockLocation = location.add(7, 0, 2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-7, 0, -2);
		
		blockLocation = location.add(8, 0, 2);
		pattern.put(getLocationString(blockLocation), "LOG,0");
		blockLocation = location.add(-8, 0, -2);
		
		blockLocation = location.add(-8, 0, 3);
		pattern.put(getLocationString(blockLocation), "LOG,0");
		blockLocation = location.add(8, 0, -3);
		
		blockLocation = location.add(-7, 0, 3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(7, 0, -3);
		
		blockLocation = location.add(-6, 0, 3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(6, 0, -3);
		
		blockLocation = location.add(-5, 0, 3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(5, 0, -3);
		
		blockLocation = location.add(-4, 0, 3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(4, 0, -3);
		
		blockLocation = location.add(-3, 0, 3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(3, 0, -3);
		
		blockLocation = location.add(-2, 0, 3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(2, 0, -3);
		
		blockLocation = location.add(-1, 0, 3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(1, 0, -3);
		
		blockLocation = location.add(0, 0, 3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(0, 0, -3);
		
		blockLocation = location.add(1, 0, 3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-1, 0, -3);
		
		blockLocation = location.add(2, 0, 3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-2, 0, -3);
		
		blockLocation = location.add(3, 0, 3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-3, 0, -3);
		
		blockLocation = location.add(4, 0, 3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-4, 0, -3);
		
		blockLocation = location.add(5, 0, 3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-5, 0, -3);
		
		blockLocation = location.add(6, 0, 3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-6, 0, -3);
		
		blockLocation = location.add(7, 0, 3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-7, 0, -3);
		
		blockLocation = location.add(8, 0, 3);
		pattern.put(getLocationString(blockLocation), "LOG,0");
		blockLocation = location.add(-8, 0, -3);
		
		blockLocation = location.add(-7, 0, 4);
		pattern.put(getLocationString(blockLocation), "LOG,0");
		blockLocation = location.add(7, 0, -4);
		
		blockLocation = location.add(-6, 0, 4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(6, 0, -4);
		
		blockLocation = location.add(-5, 0, 4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(5, 0, -4);
		
		blockLocation = location.add(-4, 0, 4);
		pattern.put(getLocationString(blockLocation), "CAULDRON,3");
		blockLocation = location.add(4, 0, -4);
		
		blockLocation = location.add(-3, 0, 4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(3, 0, -4);
		
		blockLocation = location.add(-2, 0, 4);
		pattern.put(getLocationString(blockLocation), "WOOL,12");
		blockLocation = location.add(2, 0, -4);
		
		blockLocation = location.add(-1, 0, 4);
		pattern.put(getLocationString(blockLocation), "FENCE,0");
		blockLocation = location.add(1, 0, -4);
		
		blockLocation = location.add(1, 0, 4);
		pattern.put(getLocationString(blockLocation), "FENCE,0");
		blockLocation = location.add(-1, 0, -4);
		
		blockLocation = location.add(2, 0, 4);
		pattern.put(getLocationString(blockLocation), "WOOL,12");
		blockLocation = location.add(-2, 0, -4);
		
		blockLocation = location.add(3, 0, 4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-3, 0, -4);
		
		blockLocation = location.add(4, 0, 4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-4, 0, -4);
		
		blockLocation = location.add(5, 0, 4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-5, 0, -4);
		
		blockLocation = location.add(6, 0, 4);
		pattern.put(getLocationString(blockLocation), "CHEST,4");
		blockLocation = location.add(-6, 0, -4);
		
		blockLocation = location.add(7, 0, 4);
		pattern.put(getLocationString(blockLocation), "LOG,0");
		blockLocation = location.add(-7, 0, -4);
		
		blockLocation = location.add(-7, 0, 5);
		pattern.put(getLocationString(blockLocation), "LOG,0");
		blockLocation = location.add(7, 0, -5);
		
		blockLocation = location.add(-6, 0, 5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(6, 0, -5);
		
		blockLocation = location.add(-5, 0, 5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(5, 0, -5);
		
		blockLocation = location.add(-4, 0, 5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(4, 0, -5);
		
		blockLocation = location.add(-3, 0, 5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(3, 0, -5);
		
		blockLocation = location.add(-2, 0, 5);
		pattern.put(getLocationString(blockLocation), "WOOL,12");
		blockLocation = location.add(2, 0, -5);
		
		blockLocation = location.add(-1, 0, 5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(1, 0, -5);
		
		blockLocation = location.add(0, 0, 5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(0, 0, -5);
		
		blockLocation = location.add(1, 0, 5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-1, 0, -5);
		
		blockLocation = location.add(2, 0, 5);
		pattern.put(getLocationString(blockLocation), "WOOL,12");
		blockLocation = location.add(-2, 0, -5);
		
		blockLocation = location.add(3, 0, 5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-3, 0, -5);
		
		blockLocation = location.add(4, 0, 5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-4, 0, -5);
		
		blockLocation = location.add(5, 0, 5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-5, 0, -5);
		
		blockLocation = location.add(6, 0, 5);
		pattern.put(getLocationString(blockLocation), "CHEST,4");
		blockLocation = location.add(-6, 0, -5);
		
		blockLocation = location.add(7, 0, 5);
		pattern.put(getLocationString(blockLocation), "LOG,0");
		blockLocation = location.add(-7, 0, -5);
		
		blockLocation = location.add(-6, 0, 6);
		pattern.put(getLocationString(blockLocation), "LOG,0");
		blockLocation = location.add(6, 0, -6);
		
		blockLocation = location.add(-5, 0, 6);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(5, 0, -6);
		
		blockLocation = location.add(-4, 0, 6);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(4, 0, -6);
		
		blockLocation = location.add(-3, 0, 6);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(3, 0, -6);
		
		blockLocation = location.add(-2, 0, 6);
		pattern.put(getLocationString(blockLocation), "WOOL,12");
		blockLocation = location.add(2, 0, -6);
		
		blockLocation = location.add(-1, 0, 6);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(1, 0, -6);
		
		blockLocation = location.add(0, 0, 6);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(0, 0, -6);
		
		blockLocation = location.add(1, 0, 6);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-1, 0, -6);
		
		blockLocation = location.add(2, 0, 6);
		pattern.put(getLocationString(blockLocation), "WOOL,12");
		blockLocation = location.add(-2, 0, -6);
		
		blockLocation = location.add(3, 0, 6);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-3, 0, -6);
		
		blockLocation = location.add(4, 0, 6);
		pattern.put(getLocationString(blockLocation), "CHEST,2");
		blockLocation = location.add(-4, 0, -6);
		
		blockLocation = location.add(5, 0, 6);
		pattern.put(getLocationString(blockLocation), "CHEST,2");
		blockLocation = location.add(-5, 0, -6);
		
		blockLocation = location.add(6, 0, 6);
		pattern.put(getLocationString(blockLocation), "LOG,0");
		blockLocation = location.add(-6, 0, -6);
		
		blockLocation = location.add(-5, 0, 7);
		pattern.put(getLocationString(blockLocation), "LOG,0");
		blockLocation = location.add(5, 0, -7);
		
		blockLocation = location.add(-4, 0, 7);
		pattern.put(getLocationString(blockLocation), "LOG,0");
		blockLocation = location.add(4, 0, -7);
		
		blockLocation = location.add(-3, 0, 7);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(3, 0, -7);
		
		blockLocation = location.add(-2, 0, 7);
		pattern.put(getLocationString(blockLocation), "WOOL,12");
		blockLocation = location.add(2, 0, -7);
		
		blockLocation = location.add(-1, 0, 7);
		pattern.put(getLocationString(blockLocation), "FENCE,0");
		blockLocation = location.add(1, 0, -7);
		
		blockLocation = location.add(0, 0, 7);
		pattern.put(getLocationString(blockLocation), "FENCE,0");
		blockLocation = location.add(0, 0, -7);
		
		blockLocation = location.add(1, 0, 7);
		pattern.put(getLocationString(blockLocation), "FENCE,0");
		blockLocation = location.add(-1, 0, -7);
		
		blockLocation = location.add(2, 0, 7);
		pattern.put(getLocationString(blockLocation), "WOOL,12");
		blockLocation = location.add(-2, 0, -7);
		
		blockLocation = location.add(3, 0, 7);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-3, 0, -7);
		
		blockLocation = location.add(4, 0, 7);
		pattern.put(getLocationString(blockLocation), "LOG,0");
		blockLocation = location.add(-4, 0, -7);
		
		blockLocation = location.add(5, 0, 7);
		pattern.put(getLocationString(blockLocation), "LOG,0");
		blockLocation = location.add(-5, 0, -7);
		
		blockLocation = location.add(-3, 0, 8);
		pattern.put(getLocationString(blockLocation), "LOG,0");
		blockLocation = location.add(3, 0, -8);
		
		blockLocation = location.add(-2, 0, 8);
		pattern.put(getLocationString(blockLocation), "LOG,0");
		blockLocation = location.add(2, 0, -8);
		
		blockLocation = location.add(-1, 0, 8);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(1, 0, -8);
		
		blockLocation = location.add(0, 0, 8);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(0, 0, -8);
		
		blockLocation = location.add(1, 0, 8);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-1, 0, -8);
		
		blockLocation = location.add(2, 0, 8);
		pattern.put(getLocationString(blockLocation), "LOG,0");
		blockLocation = location.add(-2, 0, -8);
		
		blockLocation = location.add(3, 0, 8);
		pattern.put(getLocationString(blockLocation), "LOG,0");
		blockLocation = location.add(-3, 0, -8);
		
		blockLocation = location.add(-1, 0, 9);
		pattern.put(getLocationString(blockLocation), "LOG,0");
		blockLocation = location.add(1, 0, -9);
		
		blockLocation = location.add(0, 0, 9);
		pattern.put(getLocationString(blockLocation), "LOG,0");
		blockLocation = location.add(0, 0, -9);
		
		blockLocation = location.add(1, 0, 9);
		pattern.put(getLocationString(blockLocation), "LOG,0");
		blockLocation = location.add(-1, 0, -9);
		
		blockLocation = location.add(-1, 1, -9);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(1, -1, 9);
		
		blockLocation = location.add(0, 1, -9);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(0, -1, 9);
		
		blockLocation = location.add(1, 1, -9);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-1, -1, 9);
		
		blockLocation = location.add(-3, 1, -8);
		pattern.put(getLocationString(blockLocation), "LOG,0");
		blockLocation = location.add(3, -1, 8);
		
		blockLocation = location.add(-2, 1, -8);
		pattern.put(getLocationString(blockLocation), "LOG,0");
		blockLocation = location.add(2, -1, 8);
		
		blockLocation = location.add(-1, 1, -8);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(1, -1, 8);
		
		blockLocation = location.add(0, 1, -8);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(0, -1, 8);
		
		blockLocation = location.add(1, 1, -8);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-1, -1, 8);
		
		blockLocation = location.add(2, 1, -8);
		pattern.put(getLocationString(blockLocation), "LOG,0");
		blockLocation = location.add(-2, -1, 8);
		
		blockLocation = location.add(3, 1, -8);
		pattern.put(getLocationString(blockLocation), "LOG,0");
		blockLocation = location.add(-3, -1, 8);
		
		blockLocation = location.add(-5, 1, -7);
		pattern.put(getLocationString(blockLocation), "LOG,0");
		blockLocation = location.add(5, -1, 7);
		
		blockLocation = location.add(-4, 1, -7);
		pattern.put(getLocationString(blockLocation), "LOG,0");
		blockLocation = location.add(4, -1, 7);
		
		blockLocation = location.add(-3, 1, -7);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(3, -1, 7);
		
		blockLocation = location.add(-2, 1, -7);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(2, -1, 7);
		
		blockLocation = location.add(-1, 1, -7);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(1, -1, 7);
		
		blockLocation = location.add(0, 1, -7);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(0, -1, 7);
		
		blockLocation = location.add(1, 1, -7);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-1, -1, 7);
		
		blockLocation = location.add(2, 1, -7);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-2, -1, 7);
		
		blockLocation = location.add(3, 1, -7);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-3, -1, 7);
		
		blockLocation = location.add(4, 1, -7);
		pattern.put(getLocationString(blockLocation), "LOG,0");
		blockLocation = location.add(-4, -1, 7);
		
		blockLocation = location.add(5, 1, -7);
		pattern.put(getLocationString(blockLocation), "LOG,0");
		blockLocation = location.add(-5, -1, 7);
		
		blockLocation = location.add(-6, 1, -6);
		pattern.put(getLocationString(blockLocation), "LOG,0");
		blockLocation = location.add(6, -1, 6);
		
		blockLocation = location.add(-5, 1, -6);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(5, -1, 6);
		
		blockLocation = location.add(-4, 1, -6);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(4, -1, 6);
		
		blockLocation = location.add(-3, 1, -6);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(3, -1, 6);
		
		blockLocation = location.add(-2, 1, -6);
		pattern.put(getLocationString(blockLocation), "FENCE,0");
		blockLocation = location.add(2, -1, 6);
		
		blockLocation = location.add(-1, 1, -6);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(1, -1, 6);
		
		blockLocation = location.add(0, 1, -6);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(0, -1, 6);
		
		blockLocation = location.add(1, 1, -6);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-1, -1, 6);
		
		blockLocation = location.add(2, 1, -6);
		pattern.put(getLocationString(blockLocation), "FENCE,0");
		blockLocation = location.add(-2, -1, 6);
		
		blockLocation = location.add(3, 1, -6);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-3, -1, 6);
		
		blockLocation = location.add(4, 1, -6);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-4, -1, 6);
		
		blockLocation = location.add(5, 1, -6);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-5, -1, 6);
		
		blockLocation = location.add(6, 1, -6);
		pattern.put(getLocationString(blockLocation), "LOG,0");
		blockLocation = location.add(-6, -1, 6);
		
		blockLocation = location.add(-7, 1, -5);
		pattern.put(getLocationString(blockLocation), "LOG,0");
		blockLocation = location.add(7, -1, 5);
		
		blockLocation = location.add(-6, 1, -5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(6, -1, 5);
		
		blockLocation = location.add(-5, 1, -5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(5, -1, 5);
		
		blockLocation = location.add(-4, 1, -5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(4, -1, 5);
		
		blockLocation = location.add(-3, 1, -5);
		pattern.put(getLocationString(blockLocation), "LOG,0");
		blockLocation = location.add(3, -1, 5);
		
		blockLocation = location.add(-1, 1, -5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(1, -1, 5);
		
		blockLocation = location.add(0, 1, -5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(0, -1, 5);
		
		blockLocation = location.add(1, 1, -5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-1, -1, 5);
		
		blockLocation = location.add(3, 1, -5);
		pattern.put(getLocationString(blockLocation), "LOG,0");
		blockLocation = location.add(-3, -1, 5);
		
		blockLocation = location.add(4, 1, -5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-4, -1, 5);
		
		blockLocation = location.add(5, 1, -5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-5, -1, 5);
		
		blockLocation = location.add(6, 1, -5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-6, -1, 5);
		
		blockLocation = location.add(7, 1, -5);
		pattern.put(getLocationString(blockLocation), "LOG,0");
		blockLocation = location.add(-7, -1, 5);
		
		blockLocation = location.add(-7, 1, -4);
		pattern.put(getLocationString(blockLocation), "LOG,0");
		blockLocation = location.add(7, -1, 4);
		
		blockLocation = location.add(-6, 1, -4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(6, -1, 4);
		
		blockLocation = location.add(-5, 1, -4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(5, -1, 4);
		
		blockLocation = location.add(-4, 1, -4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(4, -1, 4);
		
		blockLocation = location.add(-2, 1, -4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(2, -1, 4);
		
		blockLocation = location.add(-1, 1, -4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(1, -1, 4);
		
		blockLocation = location.add(0, 1, -4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(0, -1, 4);
		
		blockLocation = location.add(1, 1, -4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-1, -1, 4);
		
		blockLocation = location.add(2, 1, -4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-2, -1, 4);
		
		blockLocation = location.add(4, 1, -4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-4, -1, 4);
		
		blockLocation = location.add(5, 1, -4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-5, -1, 4);
		
		blockLocation = location.add(6, 1, -4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-6, -1, 4);
		
		blockLocation = location.add(7, 1, -4);
		pattern.put(getLocationString(blockLocation), "LOG,0");
		blockLocation = location.add(-7, -1, 4);
		
		blockLocation = location.add(-8, 1, -3);
		pattern.put(getLocationString(blockLocation), "LOG,0");
		blockLocation = location.add(8, -1, 3);
		
		blockLocation = location.add(-7, 1, -3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(7, -1, 3);
		
		blockLocation = location.add(-6, 1, -3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(6, -1, 3);
		
		blockLocation = location.add(-5, 1, -3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(5, -1, 3);
		
		blockLocation = location.add(-4, 1, -3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(4, -1, 3);
		
		blockLocation = location.add(-3, 1, -3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(3, -1, 3);
		
		blockLocation = location.add(-2, 1, -3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(2, -1, 3);
		
		blockLocation = location.add(-1, 1, -3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(1, -1, 3);
		
		blockLocation = location.add(0, 1, -3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(0, -1, 3);
		
		blockLocation = location.add(1, 1, -3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-1, -1, 3);
		
		blockLocation = location.add(2, 1, -3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-2, -1, 3);
		
		blockLocation = location.add(3, 1, -3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-3, -1, 3);
		
		blockLocation = location.add(4, 1, -3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-4, -1, 3);
		
		blockLocation = location.add(5, 1, -3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-5, -1, 3);
		
		blockLocation = location.add(6, 1, -3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-6, -1, 3);
		
		blockLocation = location.add(7, 1, -3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-7, -1, 3);
		
		blockLocation = location.add(8, 1, -3);
		pattern.put(getLocationString(blockLocation), "LOG,0");
		blockLocation = location.add(-8, -1, 3);
		
		blockLocation = location.add(-8, 1, -2);
		pattern.put(getLocationString(blockLocation), "LOG,0");
		blockLocation = location.add(8, -1, 2);
		
		blockLocation = location.add(-7, 1, -2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(7, -1, 2);
		
		blockLocation = location.add(-6, 1, -2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(6, -1, 2);
		
		blockLocation = location.add(-5, 1, -2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(5, -1, 2);
		
		blockLocation = location.add(-4, 1, -2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(4, -1, 2);
		
		blockLocation = location.add(-3, 1, -2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(3, -1, 2);
		
		blockLocation = location.add(-2, 1, -2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(2, -1, 2);
		
		blockLocation = location.add(-1, 1, -2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(1, -1, 2);
		
		blockLocation = location.add(0, 1, -2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(0, -1, 2);
		
		blockLocation = location.add(1, 1, -2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-1, -1, 2);
		
		blockLocation = location.add(2, 1, -2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-2, -1, 2);
		
		blockLocation = location.add(3, 1, -2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-3, -1, 2);
		
		blockLocation = location.add(4, 1, -2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-4, -1, 2);
		
		blockLocation = location.add(5, 1, -2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-5, -1, 2);
		
		blockLocation = location.add(6, 1, -2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-6, -1, 2);
		
		blockLocation = location.add(8, 1, -2);
		pattern.put(getLocationString(blockLocation), "LOG,0");
		blockLocation = location.add(-8, -1, 2);
		
		blockLocation = location.add(-9, 1, -1);
		pattern.put(getLocationString(blockLocation), "LOG,0");
		blockLocation = location.add(9, -1, 1);
		
		blockLocation = location.add(-8, 1, -1);
		pattern.put(getLocationString(blockLocation), "WOOD_STEP,1");
		blockLocation = location.add(8, -1, 1);
		
		blockLocation = location.add(-7, 1, -1);
		pattern.put(getLocationString(blockLocation), "WOOL,13");
		blockLocation = location.add(7, -1, 1);
		
		blockLocation = location.add(-6, 1, -1);
		pattern.put(getLocationString(blockLocation), "WOOL,13");
		blockLocation = location.add(6, -1, 1);
		
		blockLocation = location.add(-5, 1, -1);
		pattern.put(getLocationString(blockLocation), "WOOL,13");
		blockLocation = location.add(5, -1, 1);
		
		blockLocation = location.add(-4, 1, -1);
		pattern.put(getLocationString(blockLocation), "WOOL,13");
		blockLocation = location.add(4, -1, 1);
		
		blockLocation = location.add(-2, 1, -1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(2, -1, 1);
		
		blockLocation = location.add(-1, 1, -1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(1, -1, 1);
		
		blockLocation = location.add(0, 1, -1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(0, -1, 1);
		
		blockLocation = location.add(1, 1, -1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-1, -1, 1);
		
		blockLocation = location.add(2, 1, -1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-2, -1, 1);
		
		blockLocation = location.add(3, 1, -1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-3, -1, 1);
		
		blockLocation = location.add(4, 1, -1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-4, -1, 1);
		
		blockLocation = location.add(5, 1, -1);
		pattern.put(getLocationString(blockLocation), "FENCE,0");
		blockLocation = location.add(-5, -1, 1);
		
		blockLocation = location.add(6, 1, -1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-6, -1, 1);
		
		blockLocation = location.add(7, 1, -1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-7, -1, 1);
		
		blockLocation = location.add(8, 1, -1);
		pattern.put(getLocationString(blockLocation), "WOOD_STEP,1");
		blockLocation = location.add(-8, -1, 1);
		
		blockLocation = location.add(9, 1, -1);
		pattern.put(getLocationString(blockLocation), "LOG,0");
		blockLocation = location.add(-9, -1, 1);
		
		blockLocation = location.add(-9, 1, 0);
		pattern.put(getLocationString(blockLocation), "LOG,0");
		blockLocation = location.add(9, -1, 0);
		
		blockLocation = location.add(-8, 1, 0);
		pattern.put(getLocationString(blockLocation), "WOOD_STEP,1");
		blockLocation = location.add(8, -1, 0);
		
		blockLocation = location.add(-7, 1, 0);
		pattern.put(getLocationString(blockLocation), "FENCE,0");
		blockLocation = location.add(7, -1, 0);
		
		blockLocation = location.add(-6, 1, 0);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(6, -1, 0);
		
		blockLocation = location.add(-5, 1, 0);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(5, -1, 0);
		
		blockLocation = location.add(-4, 1, 0);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(4, -1, 0);
		
		blockLocation = location.add(-3, 1, 0);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(3, -1, 0);
		
		blockLocation = location.add(-2, 1, 0);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(2, -1, 0);
		
		blockLocation = location.add(-1, 1, 0);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(1, -1, 0);
		
		blockLocation = location.add(0, 1, 0);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(0, -1, 0);
		
		blockLocation = location.add(1, 1, 0);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-1, -1, 0);
		
		blockLocation = location.add(2, 1, 0);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-2, -1, 0);
		
		blockLocation = location.add(3, 1, 0);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-3, -1, 0);
		
		blockLocation = location.add(4, 1, 0);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-4, -1, 0);
		
		blockLocation = location.add(5, 1, 0);
		pattern.put(getLocationString(blockLocation), "CAULDRON,0");
		blockLocation = location.add(-5, -1, 0);
		
		blockLocation = location.add(6, 1, 0);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-6, -1, 0);
		
		blockLocation = location.add(7, 1, 0);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-7, -1, 0);
		
		blockLocation = location.add(8, 1, 0);
		pattern.put(getLocationString(blockLocation), "WOOD_STEP,1");
		blockLocation = location.add(-8, -1, 0);
		
		blockLocation = location.add(9, 1, 0);
		pattern.put(getLocationString(blockLocation), "LOG,0");
		blockLocation = location.add(-9, -1, 0);
		
		blockLocation = location.add(-9, 1, 1);
		pattern.put(getLocationString(blockLocation), "LOG,0");
		blockLocation = location.add(9, -1, -1);
		
		blockLocation = location.add(-8, 1, 1);
		pattern.put(getLocationString(blockLocation), "WOOD_STEP,1");
		blockLocation = location.add(8, -1, -1);
		
		blockLocation = location.add(-7, 1, 1);
		pattern.put(getLocationString(blockLocation), "WOOL,13");
		blockLocation = location.add(7, -1, -1);
		
		blockLocation = location.add(-6, 1, 1);
		pattern.put(getLocationString(blockLocation), "WOOL,13");
		blockLocation = location.add(6, -1, -1);
		
		blockLocation = location.add(-5, 1, 1);
		pattern.put(getLocationString(blockLocation), "WOOL,13");
		blockLocation = location.add(5, -1, -1);
		
		blockLocation = location.add(-4, 1, 1);
		pattern.put(getLocationString(blockLocation), "WOOL,13");
		blockLocation = location.add(4, -1, -1);
		
		blockLocation = location.add(-2, 1, 1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(2, -1, -1);
		
		blockLocation = location.add(-1, 1, 1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(1, -1, -1);
		
		blockLocation = location.add(0, 1, 1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(0, -1, -1);
		
		blockLocation = location.add(1, 1, 1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-1, -1, -1);
		
		blockLocation = location.add(2, 1, 1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-2, -1, -1);
		
		blockLocation = location.add(3, 1, 1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-3, -1, -1);
		
		blockLocation = location.add(4, 1, 1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-4, -1, -1);
		
		blockLocation = location.add(5, 1, 1);
		pattern.put(getLocationString(blockLocation), "FENCE,0");
		blockLocation = location.add(-5, -1, -1);
		
		blockLocation = location.add(6, 1, 1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-6, -1, -1);
		
		blockLocation = location.add(7, 1, 1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-7, -1, -1);
		
		blockLocation = location.add(8, 1, 1);
		pattern.put(getLocationString(blockLocation), "WOOD_STEP,1");
		blockLocation = location.add(-8, -1, -1);
		
		blockLocation = location.add(9, 1, 1);
		pattern.put(getLocationString(blockLocation), "LOG,0");
		blockLocation = location.add(-9, -1, -1);
		
		blockLocation = location.add(-8, 1, 2);
		pattern.put(getLocationString(blockLocation), "LOG,0");
		blockLocation = location.add(8, -1, -2);
		
		blockLocation = location.add(-7, 1, 2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(7, -1, -2);
		
		blockLocation = location.add(-6, 1, 2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(6, -1, -2);
		
		blockLocation = location.add(-5, 1, 2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(5, -1, -2);
		
		blockLocation = location.add(-4, 1, 2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(4, -1, -2);
		
		blockLocation = location.add(-3, 1, 2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(3, -1, -2);
		
		blockLocation = location.add(-2, 1, 2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(2, -1, -2);
		
		blockLocation = location.add(-1, 1, 2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(1, -1, -2);
		
		blockLocation = location.add(0, 1, 2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(0, -1, -2);
		
		blockLocation = location.add(1, 1, 2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-1, -1, -2);
		
		blockLocation = location.add(2, 1, 2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-2, -1, -2);
		
		blockLocation = location.add(3, 1, 2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-3, -1, -2);
		
		blockLocation = location.add(4, 1, 2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-4, -1, -2);
		
		blockLocation = location.add(5, 1, 2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-5, -1, -2);
		
		blockLocation = location.add(6, 1, 2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-6, -1, -2);
		
		blockLocation = location.add(7, 1, 2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-7, -1, -2);
		
		blockLocation = location.add(8, 1, 2);
		pattern.put(getLocationString(blockLocation), "LOG,0");
		blockLocation = location.add(-8, -1, -2);
		
		blockLocation = location.add(-8, 1, 3);
		pattern.put(getLocationString(blockLocation), "LOG,0");
		blockLocation = location.add(8, -1, -3);
		
		blockLocation = location.add(-7, 1, 3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(7, -1, -3);
		
		blockLocation = location.add(-6, 1, 3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(6, -1, -3);
		
		blockLocation = location.add(-5, 1, 3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(5, -1, -3);
		
		blockLocation = location.add(-4, 1, 3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(4, -1, -3);
		
		blockLocation = location.add(-3, 1, 3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(3, -1, -3);
		
		blockLocation = location.add(-2, 1, 3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(2, -1, -3);
		
		blockLocation = location.add(0, 1, 3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(0, -1, -3);
		
		blockLocation = location.add(2, 1, 3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-2, -1, -3);
		
		blockLocation = location.add(3, 1, 3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-3, -1, -3);
		
		blockLocation = location.add(4, 1, 3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-4, -1, -3);
		
		blockLocation = location.add(5, 1, 3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-5, -1, -3);
		
		blockLocation = location.add(6, 1, 3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-6, -1, -3);
		
		blockLocation = location.add(7, 1, 3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-7, -1, -3);
		
		blockLocation = location.add(8, 1, 3);
		pattern.put(getLocationString(blockLocation), "LOG,0");
		blockLocation = location.add(-8, -1, -3);
		
		blockLocation = location.add(-7, 1, 4);
		pattern.put(getLocationString(blockLocation), "LOG,0");
		blockLocation = location.add(7, -1, -4);
		
		blockLocation = location.add(-6, 1, 4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(6, -1, -4);
		
		blockLocation = location.add(-5, 1, 4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(5, -1, -4);
		
		blockLocation = location.add(-4, 1, 4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(4, -1, -4);
		
		blockLocation = location.add(-3, 1, 4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(3, -1, -4);
		
		blockLocation = location.add(-2, 1, 4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(2, -1, -4);
		
		blockLocation = location.add(-1, 1, 4);
		pattern.put(getLocationString(blockLocation), "WOOL,13");
		blockLocation = location.add(1, -1, -4);
		
		blockLocation = location.add(0, 1, 4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(0, -1, -4);
		
		blockLocation = location.add(1, 1, 4);
		pattern.put(getLocationString(blockLocation), "WOOL,13");
		blockLocation = location.add(-1, -1, -4);
		
		blockLocation = location.add(2, 1, 4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-2, -1, -4);
		
		blockLocation = location.add(3, 1, 4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-3, -1, -4);
		
		blockLocation = location.add(4, 1, 4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-4, -1, -4);
		
		blockLocation = location.add(5, 1, 4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-5, -1, -4);
		
		blockLocation = location.add(6, 1, 4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-6, -1, -4);
		
		blockLocation = location.add(7, 1, 4);
		pattern.put(getLocationString(blockLocation), "LOG,0");
		blockLocation = location.add(-7, -1, -4);
		
		blockLocation = location.add(-7, 1, 5);
		pattern.put(getLocationString(blockLocation), "LOG,0");
		blockLocation = location.add(7, -1, -5);
		
		blockLocation = location.add(-6, 1, 5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(6, -1, -5);
		
		blockLocation = location.add(-5, 1, 5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(5, -1, -5);
		
		blockLocation = location.add(-4, 1, 5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(4, -1, -5);
		
		blockLocation = location.add(-3, 1, 5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(3, -1, -5);
		
		blockLocation = location.add(-2, 1, 5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(2, -1, -5);
		
		blockLocation = location.add(-1, 1, 5);
		pattern.put(getLocationString(blockLocation), "WOOL,13");
		blockLocation = location.add(1, -1, -5);
		
		blockLocation = location.add(0, 1, 5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(0, -1, -5);
		
		blockLocation = location.add(1, 1, 5);
		pattern.put(getLocationString(blockLocation), "WOOL,13");
		blockLocation = location.add(-1, -1, -5);
		
		blockLocation = location.add(2, 1, 5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-2, -1, -5);
		
		blockLocation = location.add(3, 1, 5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-3, -1, -5);
		
		blockLocation = location.add(4, 1, 5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-4, -1, -5);
		
		blockLocation = location.add(5, 1, 5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-5, -1, -5);
		
		blockLocation = location.add(6, 1, 5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-6, -1, -5);
		
		blockLocation = location.add(7, 1, 5);
		pattern.put(getLocationString(blockLocation), "LOG,0");
		blockLocation = location.add(-7, -1, -5);
		
		blockLocation = location.add(-6, 1, 6);
		pattern.put(getLocationString(blockLocation), "LOG,0");
		blockLocation = location.add(6, -1, -6);
		
		blockLocation = location.add(-5, 1, 6);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(5, -1, -6);
		
		blockLocation = location.add(-4, 1, 6);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(4, -1, -6);
		
		blockLocation = location.add(-3, 1, 6);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(3, -1, -6);
		
		blockLocation = location.add(-2, 1, 6);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(2, -1, -6);
		
		blockLocation = location.add(-1, 1, 6);
		pattern.put(getLocationString(blockLocation), "WOOL,13");
		blockLocation = location.add(1, -1, -6);
		
		blockLocation = location.add(0, 1, 6);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(0, -1, -6);
		
		blockLocation = location.add(1, 1, 6);
		pattern.put(getLocationString(blockLocation), "WOOL,13");
		blockLocation = location.add(-1, -1, -6);
		
		blockLocation = location.add(2, 1, 6);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-2, -1, -6);
		
		blockLocation = location.add(3, 1, 6);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-3, -1, -6);
		
		blockLocation = location.add(4, 1, 6);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-4, -1, -6);
		
		blockLocation = location.add(5, 1, 6);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-5, -1, -6);
		
		blockLocation = location.add(6, 1, 6);
		pattern.put(getLocationString(blockLocation), "LOG,0");
		blockLocation = location.add(-6, -1, -6);
		
		blockLocation = location.add(-5, 1, 7);
		pattern.put(getLocationString(blockLocation), "LOG,0");
		blockLocation = location.add(5, -1, -7);
		
		blockLocation = location.add(-4, 1, 7);
		pattern.put(getLocationString(blockLocation), "LOG,0");
		blockLocation = location.add(4, -1, -7);
		
		blockLocation = location.add(-3, 1, 7);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(3, -1, -7);
		
		blockLocation = location.add(-2, 1, 7);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(2, -1, -7);
		
		blockLocation = location.add(-1, 1, 7);
		pattern.put(getLocationString(blockLocation), "WOOL,13");
		blockLocation = location.add(1, -1, -7);
		
		blockLocation = location.add(0, 1, 7);
		pattern.put(getLocationString(blockLocation), "FENCE,0");
		blockLocation = location.add(0, -1, -7);
		
		blockLocation = location.add(1, 1, 7);
		pattern.put(getLocationString(blockLocation), "WOOL,13");
		blockLocation = location.add(-1, -1, -7);
		
		blockLocation = location.add(2, 1, 7);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-2, -1, -7);
		
		blockLocation = location.add(3, 1, 7);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-3, -1, -7);
		
		blockLocation = location.add(4, 1, 7);
		pattern.put(getLocationString(blockLocation), "LOG,0");
		blockLocation = location.add(-4, -1, -7);
		
		blockLocation = location.add(5, 1, 7);
		pattern.put(getLocationString(blockLocation), "LOG,0");
		blockLocation = location.add(-5, -1, -7);
		
		blockLocation = location.add(-3, 1, 8);
		pattern.put(getLocationString(blockLocation), "LOG,0");
		blockLocation = location.add(3, -1, -8);
		
		blockLocation = location.add(-2, 1, 8);
		pattern.put(getLocationString(blockLocation), "LOG,0");
		blockLocation = location.add(2, -1, -8);
		
		blockLocation = location.add(-1, 1, 8);
		pattern.put(getLocationString(blockLocation), "WOOD_STEP,1");
		blockLocation = location.add(1, -1, -8);
		
		blockLocation = location.add(0, 1, 8);
		pattern.put(getLocationString(blockLocation), "WOOD_STEP,1");
		blockLocation = location.add(0, -1, -8);
		
		blockLocation = location.add(1, 1, 8);
		pattern.put(getLocationString(blockLocation), "WOOD_STEP,1");
		blockLocation = location.add(-1, -1, -8);
		
		blockLocation = location.add(2, 1, 8);
		pattern.put(getLocationString(blockLocation), "LOG,0");
		blockLocation = location.add(-2, -1, -8);
		
		blockLocation = location.add(3, 1, 8);
		pattern.put(getLocationString(blockLocation), "LOG,0");
		blockLocation = location.add(-3, -1, -8);
		
		blockLocation = location.add(-1, 1, 9);
		pattern.put(getLocationString(blockLocation), "LOG,0");
		blockLocation = location.add(1, -1, -9);
		
		blockLocation = location.add(0, 1, 9);
		pattern.put(getLocationString(blockLocation), "LOG,0");
		blockLocation = location.add(0, -1, -9);
		
		blockLocation = location.add(1, 1, 9);
		pattern.put(getLocationString(blockLocation), "LOG,0");
		blockLocation = location.add(-1, -1, -9);
		
		blockLocation = location.add(0, 2, -9);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(0, -2, 9);
		
		blockLocation = location.add(-3, 2, -8);
		pattern.put(getLocationString(blockLocation), "LOG,0");
		blockLocation = location.add(3, -2, 8);
		
		blockLocation = location.add(-2, 2, -8);
		pattern.put(getLocationString(blockLocation), "LOG,8");
		blockLocation = location.add(2, -2, 8);
		
		blockLocation = location.add(-1, 2, -8);
		pattern.put(getLocationString(blockLocation), "WOOD_STAIRS,5");
		blockLocation = location.add(1, -2, 8);
		
		blockLocation = location.add(0, 2, -8);
		pattern.put(getLocationString(blockLocation), "AIR,4");
		blockLocation = location.add(0, -2, 8);
		
		blockLocation = location.add(1, 2, -8);
		pattern.put(getLocationString(blockLocation), "WOOD_STAIRS,4");
		blockLocation = location.add(-1, -2, 8);
		
		blockLocation = location.add(2, 2, -8);
		pattern.put(getLocationString(blockLocation), "LOG,8");
		blockLocation = location.add(-2, -2, 8);
		
		blockLocation = location.add(3, 2, -8);
		pattern.put(getLocationString(blockLocation), "LOG,0");
		blockLocation = location.add(-3, -2, 8);
		
		blockLocation = location.add(-5, 2, -7);
		pattern.put(getLocationString(blockLocation), "LOG,0");
		blockLocation = location.add(5, -2, 7);
		
		blockLocation = location.add(-4, 2, -7);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(4, -2, 7);
		
		blockLocation = location.add(-3, 2, -7);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(3, -2, 7);
		
		blockLocation = location.add(-2, 2, -7);
		pattern.put(getLocationString(blockLocation), "FENCE,0");
		blockLocation = location.add(2, -2, 7);
		
		blockLocation = location.add(-1, 2, -7);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(1, -2, 7);
		
		blockLocation = location.add(0, 2, -7);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(0, -2, 7);
		
		blockLocation = location.add(1, 2, -7);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-1, -2, 7);
		
		blockLocation = location.add(2, 2, -7);
		pattern.put(getLocationString(blockLocation), "FENCE,0");
		blockLocation = location.add(-2, -2, 7);
		
		blockLocation = location.add(3, 2, -7);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-3, -2, 7);
		
		blockLocation = location.add(4, 2, -7);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-4, -2, 7);
		
		blockLocation = location.add(5, 2, -7);
		pattern.put(getLocationString(blockLocation), "LOG,0");
		blockLocation = location.add(-5, -2, 7);
		
		blockLocation = location.add(-6, 2, -6);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(6, -2, 6);
		
		blockLocation = location.add(-5, 2, -6);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(5, -2, 6);
		
		blockLocation = location.add(-4, 2, -6);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(4, -2, 6);
		
		blockLocation = location.add(-3, 2, -6);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(3, -2, 6);
		
		blockLocation = location.add(-2, 2, -6);
		pattern.put(getLocationString(blockLocation), "FENCE,0");
		blockLocation = location.add(2, -2, 6);
		
		blockLocation = location.add(-1, 2, -6);
		pattern.put(getLocationString(blockLocation), "FENCE,0");
		blockLocation = location.add(1, -2, 6);
		
		blockLocation = location.add(0, 2, -6);
		pattern.put(getLocationString(blockLocation), "FENCE,0");
		blockLocation = location.add(0, -2, 6);
		
		blockLocation = location.add(1, 2, -6);
		pattern.put(getLocationString(blockLocation), "FENCE,0");
		blockLocation = location.add(-1, -2, 6);
		
		blockLocation = location.add(2, 2, -6);
		pattern.put(getLocationString(blockLocation), "FENCE,0");
		blockLocation = location.add(-2, -2, 6);
		
		blockLocation = location.add(3, 2, -6);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-3, -2, 6);
		
		blockLocation = location.add(4, 2, -6);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-4, -2, 6);
		
		blockLocation = location.add(5, 2, -6);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-5, -2, 6);
		
		blockLocation = location.add(6, 2, -6);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-6, -2, 6);
		
		blockLocation = location.add(-7, 2, -5);
		pattern.put(getLocationString(blockLocation), "LOG,0");
		blockLocation = location.add(7, -2, 5);
		
		blockLocation = location.add(-6, 2, -5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(6, -2, 5);
		
		blockLocation = location.add(-5, 2, -5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(5, -2, 5);
		
		blockLocation = location.add(-4, 2, -5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(4, -2, 5);
		
		blockLocation = location.add(-3, 2, -5);
		pattern.put(getLocationString(blockLocation), "LOG,0");
		blockLocation = location.add(3, -2, 5);
		
		blockLocation = location.add(-1, 2, -5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(1, -2, 5);
		
		blockLocation = location.add(0, 2, -5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(0, -2, 5);
		
		blockLocation = location.add(1, 2, -5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-1, -2, 5);
		
		blockLocation = location.add(3, 2, -5);
		pattern.put(getLocationString(blockLocation), "LOG,0");
		blockLocation = location.add(-3, -2, 5);
		
		blockLocation = location.add(4, 2, -5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-4, -2, 5);
		
		blockLocation = location.add(5, 2, -5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-5, -2, 5);
		
		blockLocation = location.add(6, 2, -5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-6, -2, 5);
		
		blockLocation = location.add(7, 2, -5);
		pattern.put(getLocationString(blockLocation), "LOG,0");
		blockLocation = location.add(-7, -2, 5);
		
		blockLocation = location.add(-7, 2, -4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(7, -2, 4);
		
		blockLocation = location.add(-6, 2, -4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(6, -2, 4);
		
		blockLocation = location.add(-5, 2, -4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(5, -2, 4);
		
		blockLocation = location.add(-4, 2, -4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(4, -2, 4);
		
		blockLocation = location.add(-3, 2, -4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(3, -2, 4);
		
		blockLocation = location.add(-2, 2, -4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(2, -2, 4);
		
		blockLocation = location.add(-1, 2, -4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(1, -2, 4);
		
		blockLocation = location.add(0, 2, -4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(0, -2, 4);
		
		blockLocation = location.add(1, 2, -4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-1, -2, 4);
		
		blockLocation = location.add(2, 2, -4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-2, -2, 4);
		
		blockLocation = location.add(3, 2, -4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-3, -2, 4);
		
		blockLocation = location.add(4, 2, -4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-4, -2, 4);
		
		blockLocation = location.add(5, 2, -4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-5, -2, 4);
		
		blockLocation = location.add(6, 2, -4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-6, -2, 4);
		
		blockLocation = location.add(7, 2, -4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-7, -2, 4);
		
		blockLocation = location.add(-8, 2, -3);
		pattern.put(getLocationString(blockLocation), "LOG,0");
		blockLocation = location.add(8, -2, 3);
		
		blockLocation = location.add(-7, 2, -3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(7, -2, 3);
		
		blockLocation = location.add(-6, 2, -3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(6, -2, 3);
		
		blockLocation = location.add(-5, 2, -3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(5, -2, 3);
		
		blockLocation = location.add(-4, 2, -3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(4, -2, 3);
		
		blockLocation = location.add(-3, 2, -3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(3, -2, 3);
		
		blockLocation = location.add(-2, 2, -3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(2, -2, 3);
		
		blockLocation = location.add(-1, 2, -3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(1, -2, 3);
		
		blockLocation = location.add(0, 2, -3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(0, -2, 3);
		
		blockLocation = location.add(1, 2, -3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-1, -2, 3);
		
		blockLocation = location.add(2, 2, -3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-2, -2, 3);
		
		blockLocation = location.add(3, 2, -3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-3, -2, 3);
		
		blockLocation = location.add(4, 2, -3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-4, -2, 3);
		
		blockLocation = location.add(5, 2, -3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-5, -2, 3);
		
		blockLocation = location.add(6, 2, -3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-6, -2, 3);
		
		blockLocation = location.add(7, 2, -3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-7, -2, 3);
		
		blockLocation = location.add(8, 2, -3);
		pattern.put(getLocationString(blockLocation), "LOG,0");
		blockLocation = location.add(-8, -2, 3);
		
		blockLocation = location.add(-8, 2, -2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(8, -2, 2);
		
		blockLocation = location.add(-7, 2, -2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(7, -2, 2);
		
		blockLocation = location.add(-6, 2, -2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(6, -2, 2);
		
		blockLocation = location.add(-5, 2, -2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(5, -2, 2);
		
		blockLocation = location.add(-4, 2, -2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(4, -2, 2);
		
		blockLocation = location.add(-3, 2, -2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(3, -2, 2);
		
		blockLocation = location.add(-2, 2, -2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(2, -2, 2);
		
		blockLocation = location.add(-1, 2, -2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(1, -2, 2);
		
		blockLocation = location.add(0, 2, -2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(0, -2, 2);
		
		blockLocation = location.add(1, 2, -2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-1, -2, 2);
		
		blockLocation = location.add(2, 2, -2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-2, -2, 2);
		
		blockLocation = location.add(3, 2, -2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-3, -2, 2);
		
		blockLocation = location.add(4, 2, -2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-4, -2, 2);
		
		blockLocation = location.add(5, 2, -2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-5, -2, 2);
		
		blockLocation = location.add(6, 2, -2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-6, -2, 2);
		
		blockLocation = location.add(7, 2, -2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-7, -2, 2);
		
		blockLocation = location.add(8, 2, -2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-8, -2, 2);
		
		blockLocation = location.add(-9, 2, -1);
		pattern.put(getLocationString(blockLocation), "LOG,0");
		blockLocation = location.add(9, -2, 1);
		
		blockLocation = location.add(-8, 2, -1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(8, -2, 1);
		
		blockLocation = location.add(-7, 2, -1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(7, -2, 1);
		
		blockLocation = location.add(-6, 2, -1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(6, -2, 1);
		
		blockLocation = location.add(-5, 2, -1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(5, -2, 1);
		
		blockLocation = location.add(-4, 2, -1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(4, -2, 1);
		
		blockLocation = location.add(-3, 2, -1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(3, -2, 1);
		
		blockLocation = location.add(-2, 2, -1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(2, -2, 1);
		
		blockLocation = location.add(-1, 2, -1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(1, -2, 1);
		
		blockLocation = location.add(0, 2, -1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(0, -2, 1);
		
		blockLocation = location.add(1, 2, -1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-1, -2, 1);
		
		blockLocation = location.add(2, 2, -1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-2, -2, 1);
		
		blockLocation = location.add(3, 2, -1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-3, -2, 1);
		
		blockLocation = location.add(4, 2, -1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-4, -2, 1);
		
		blockLocation = location.add(5, 2, -1);
		pattern.put(getLocationString(blockLocation), "FENCE,0");
		blockLocation = location.add(-5, -2, 1);
		
		blockLocation = location.add(6, 2, -1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-6, -2, 1);
		
		blockLocation = location.add(7, 2, -1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-7, -2, 1);
		
		blockLocation = location.add(8, 2, -1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-8, -2, 1);
		
		blockLocation = location.add(9, 2, -1);
		pattern.put(getLocationString(blockLocation), "LOG,0");
		blockLocation = location.add(-9, -2, 1);
		
		blockLocation = location.add(-9, 2, 0);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(9, -2, 0);
		
		blockLocation = location.add(-8, 2, 0);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(8, -2, 0);
		
		blockLocation = location.add(-7, 2, 0);
		pattern.put(getLocationString(blockLocation), "WOOL,12");
		blockLocation = location.add(7, -2, 0);
		
		blockLocation = location.add(-6, 2, 0);
		pattern.put(getLocationString(blockLocation), "WOOL,12");
		blockLocation = location.add(6, -2, 0);
		
		blockLocation = location.add(-5, 2, 0);
		pattern.put(getLocationString(blockLocation), "WOOL,12");
		blockLocation = location.add(5, -2, 0);
		
		blockLocation = location.add(-4, 2, 0);
		pattern.put(getLocationString(blockLocation), "WOOL,12");
		blockLocation = location.add(4, -2, 0);
		
		blockLocation = location.add(-3, 2, 0);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(3, -2, 0);
		
		blockLocation = location.add(-2, 2, 0);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(2, -2, 0);
		
		blockLocation = location.add(-1, 2, 0);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(1, -2, 0);
		
		blockLocation = location.add(0, 2, 0);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(0, -2, 0);
		
		blockLocation = location.add(1, 2, 0);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-1, -2, 0);
		
		blockLocation = location.add(2, 2, 0);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-2, -2, 0);
		
		blockLocation = location.add(3, 2, 0);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-3, -2, 0);
		
		blockLocation = location.add(4, 2, 0);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-4, -2, 0);
		
		blockLocation = location.add(5, 2, 0);
		pattern.put(getLocationString(blockLocation), "FENCE,0");
		blockLocation = location.add(-5, -2, 0);
		
		blockLocation = location.add(6, 2, 0);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-6, -2, 0);
		
		blockLocation = location.add(7, 2, 0);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-7, -2, 0);
		
		blockLocation = location.add(8, 2, 0);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-8, -2, 0);
		
		blockLocation = location.add(9, 2, 0);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-9, -2, 0);
		
		blockLocation = location.add(-9, 2, 1);
		pattern.put(getLocationString(blockLocation), "LOG,0");
		blockLocation = location.add(9, -2, -1);
		
		blockLocation = location.add(-8, 2, 1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(8, -2, -1);
		
		blockLocation = location.add(-7, 2, 1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(7, -2, -1);
		
		blockLocation = location.add(-6, 2, 1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(6, -2, -1);
		
		blockLocation = location.add(-5, 2, 1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(5, -2, -1);
		
		blockLocation = location.add(-4, 2, 1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(4, -2, -1);
		
		blockLocation = location.add(-3, 2, 1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(3, -2, -1);
		
		blockLocation = location.add(-2, 2, 1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(2, -2, -1);
		
		blockLocation = location.add(-1, 2, 1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(1, -2, -1);
		
		blockLocation = location.add(0, 2, 1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(0, -2, -1);
		
		blockLocation = location.add(1, 2, 1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-1, -2, -1);
		
		blockLocation = location.add(2, 2, 1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-2, -2, -1);
		
		blockLocation = location.add(3, 2, 1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-3, -2, -1);
		
		blockLocation = location.add(4, 2, 1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-4, -2, -1);
		
		blockLocation = location.add(5, 2, 1);
		pattern.put(getLocationString(blockLocation), "FENCE,0");
		blockLocation = location.add(-5, -2, -1);
		
		blockLocation = location.add(6, 2, 1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-6, -2, -1);
		
		blockLocation = location.add(7, 2, 1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-7, -2, -1);
		
		blockLocation = location.add(8, 2, 1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-8, -2, -1);
		
		blockLocation = location.add(9, 2, 1);
		pattern.put(getLocationString(blockLocation), "LOG,0");
		blockLocation = location.add(-9, -2, -1);
		
		blockLocation = location.add(-8, 2, 2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(8, -2, -2);
		
		blockLocation = location.add(-7, 2, 2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(7, -2, -2);
		
		blockLocation = location.add(-6, 2, 2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(6, -2, -2);
		
		blockLocation = location.add(-5, 2, 2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(5, -2, -2);
		
		blockLocation = location.add(-4, 2, 2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(4, -2, -2);
		
		blockLocation = location.add(-3, 2, 2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(3, -2, -2);
		
		blockLocation = location.add(-2, 2, 2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(2, -2, -2);
		
		blockLocation = location.add(-1, 2, 2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(1, -2, -2);
		
		blockLocation = location.add(0, 2, 2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(0, -2, -2);
		
		blockLocation = location.add(1, 2, 2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-1, -2, -2);
		
		blockLocation = location.add(2, 2, 2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-2, -2, -2);
		
		blockLocation = location.add(3, 2, 2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-3, -2, -2);
		
		blockLocation = location.add(4, 2, 2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-4, -2, -2);
		
		blockLocation = location.add(5, 2, 2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-5, -2, -2);
		
		blockLocation = location.add(6, 2, 2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-6, -2, -2);
		
		blockLocation = location.add(7, 2, 2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-7, -2, -2);
		
		blockLocation = location.add(8, 2, 2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-8, -2, -2);
		
		blockLocation = location.add(-8, 2, 3);
		pattern.put(getLocationString(blockLocation), "LOG,0");
		blockLocation = location.add(8, -2, -3);
		
		blockLocation = location.add(-7, 2, 3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(7, -2, -3);
		
		blockLocation = location.add(-6, 2, 3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(6, -2, -3);
		
		blockLocation = location.add(-5, 2, 3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(5, -2, -3);
		
		blockLocation = location.add(-4, 2, 3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(4, -2, -3);
		
		blockLocation = location.add(-3, 2, 3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(3, -2, -3);
		
		blockLocation = location.add(-2, 2, 3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(2, -2, -3);
		
		blockLocation = location.add(-1, 2, 3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(1, -2, -3);
		
		blockLocation = location.add(0, 2, 3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(0, -2, -3);
		
		blockLocation = location.add(1, 2, 3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-1, -2, -3);
		
		blockLocation = location.add(2, 2, 3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-2, -2, -3);
		
		blockLocation = location.add(3, 2, 3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-3, -2, -3);
		
		blockLocation = location.add(4, 2, 3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-4, -2, -3);
		
		blockLocation = location.add(5, 2, 3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-5, -2, -3);
		
		blockLocation = location.add(6, 2, 3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-6, -2, -3);
		
		blockLocation = location.add(7, 2, 3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-7, -2, -3);
		
		blockLocation = location.add(8, 2, 3);
		pattern.put(getLocationString(blockLocation), "LOG,0");
		blockLocation = location.add(-8, -2, -3);
		
		blockLocation = location.add(-7, 2, 4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(7, -2, -4);
		
		blockLocation = location.add(-6, 2, 4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(6, -2, -4);
		
		blockLocation = location.add(-5, 2, 4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(5, -2, -4);
		
		blockLocation = location.add(-4, 2, 4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(4, -2, -4);
		
		blockLocation = location.add(-3, 2, 4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(3, -2, -4);
		
		blockLocation = location.add(-2, 2, 4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(2, -2, -4);
		
		blockLocation = location.add(-1, 2, 4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(1, -2, -4);
		
		blockLocation = location.add(0, 2, 4);
		pattern.put(getLocationString(blockLocation), "WOOL,12");
		blockLocation = location.add(0, -2, -4);
		
		blockLocation = location.add(1, 2, 4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-1, -2, -4);
		
		blockLocation = location.add(2, 2, 4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-2, -2, -4);
		
		blockLocation = location.add(3, 2, 4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-3, -2, -4);
		
		blockLocation = location.add(4, 2, 4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-4, -2, -4);
		
		blockLocation = location.add(5, 2, 4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-5, -2, -4);
		
		blockLocation = location.add(6, 2, 4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-6, -2, -4);
		
		blockLocation = location.add(7, 2, 4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-7, -2, -4);
		
		blockLocation = location.add(-7, 2, 5);
		pattern.put(getLocationString(blockLocation), "LOG,0");
		blockLocation = location.add(7, -2, -5);
		
		blockLocation = location.add(-6, 2, 5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(6, -2, -5);
		
		blockLocation = location.add(-5, 2, 5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(5, -2, -5);
		
		blockLocation = location.add(-4, 2, 5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(4, -2, -5);
		
		blockLocation = location.add(-3, 2, 5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(3, -2, -5);
		
		blockLocation = location.add(-2, 2, 5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(2, -2, -5);
		
		blockLocation = location.add(-1, 2, 5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(1, -2, -5);
		
		blockLocation = location.add(0, 2, 5);
		pattern.put(getLocationString(blockLocation), "WOOL,12");
		blockLocation = location.add(0, -2, -5);
		
		blockLocation = location.add(1, 2, 5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-1, -2, -5);
		
		blockLocation = location.add(2, 2, 5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-2, -2, -5);
		
		blockLocation = location.add(3, 2, 5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-3, -2, -5);
		
		blockLocation = location.add(4, 2, 5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-4, -2, -5);
		
		blockLocation = location.add(5, 2, 5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-5, -2, -5);
		
		blockLocation = location.add(6, 2, 5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-6, -2, -5);
		
		blockLocation = location.add(7, 2, 5);
		pattern.put(getLocationString(blockLocation), "LOG,0");
		blockLocation = location.add(-7, -2, -5);
		
		blockLocation = location.add(-6, 2, 6);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(6, -2, -6);
		
		blockLocation = location.add(-5, 2, 6);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(5, -2, -6);
		
		blockLocation = location.add(-4, 2, 6);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(4, -2, -6);
		
		blockLocation = location.add(-3, 2, 6);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(3, -2, -6);
		
		blockLocation = location.add(-2, 2, 6);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(2, -2, -6);
		
		blockLocation = location.add(-1, 2, 6);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(1, -2, -6);
		
		blockLocation = location.add(0, 2, 6);
		pattern.put(getLocationString(blockLocation), "WOOL,12");
		blockLocation = location.add(0, -2, -6);
		
		blockLocation = location.add(1, 2, 6);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-1, -2, -6);
		
		blockLocation = location.add(2, 2, 6);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-2, -2, -6);
		
		blockLocation = location.add(3, 2, 6);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-3, -2, -6);
		
		blockLocation = location.add(4, 2, 6);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-4, -2, -6);
		
		blockLocation = location.add(5, 2, 6);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-5, -2, -6);
		
		blockLocation = location.add(6, 2, 6);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-6, -2, -6);
		
		blockLocation = location.add(-5, 2, 7);
		pattern.put(getLocationString(blockLocation), "LOG,0");
		blockLocation = location.add(5, -2, -7);
		
		blockLocation = location.add(-4, 2, 7);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(4, -2, -7);
		
		blockLocation = location.add(-3, 2, 7);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(3, -2, -7);
		
		blockLocation = location.add(-2, 2, 7);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(2, -2, -7);
		
		blockLocation = location.add(-1, 2, 7);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(1, -2, -7);
		
		blockLocation = location.add(0, 2, 7);
		pattern.put(getLocationString(blockLocation), "WOOL,12");
		blockLocation = location.add(0, -2, -7);
		
		blockLocation = location.add(1, 2, 7);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-1, -2, -7);
		
		blockLocation = location.add(2, 2, 7);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-2, -2, -7);
		
		blockLocation = location.add(3, 2, 7);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-3, -2, -7);
		
		blockLocation = location.add(4, 2, 7);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-4, -2, -7);
		
		blockLocation = location.add(5, 2, 7);
		pattern.put(getLocationString(blockLocation), "LOG,0");
		blockLocation = location.add(-5, -2, -7);
		
		blockLocation = location.add(-3, 2, 8);
		pattern.put(getLocationString(blockLocation), "LOG,0");
		blockLocation = location.add(3, -2, -8);
		
		blockLocation = location.add(-2, 2, 8);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(2, -2, -8);
		
		blockLocation = location.add(-1, 2, 8);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(1, -2, -8);
		
		blockLocation = location.add(0, 2, 8);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(0, -2, -8);
		
		blockLocation = location.add(1, 2, 8);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-1, -2, -8);
		
		blockLocation = location.add(2, 2, 8);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-2, -2, -8);
		
		blockLocation = location.add(3, 2, 8);
		pattern.put(getLocationString(blockLocation), "LOG,0");
		blockLocation = location.add(-3, -2, -8);
		
		blockLocation = location.add(-1, 2, 9);
		pattern.put(getLocationString(blockLocation), "LOG,0");
		blockLocation = location.add(1, -2, -9);
		
		blockLocation = location.add(0, 2, 9);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(0, -2, -9);
		
		blockLocation = location.add(1, 2, 9);
		pattern.put(getLocationString(blockLocation), "LOG,0");
		blockLocation = location.add(-1, -2, -9);
		
		blockLocation = location.add(-3, 3, -8);
		pattern.put(getLocationString(blockLocation), "LOG,8");
		blockLocation = location.add(3, -3, 8);
		
		blockLocation = location.add(-2, 3, -8);
		pattern.put(getLocationString(blockLocation), "WOOD_STAIRS,1");
		blockLocation = location.add(2, -3, 8);
		
		blockLocation = location.add(-1, 3, -8);
		pattern.put(getLocationString(blockLocation), "WOOD_STAIRS,0");
		blockLocation = location.add(1, -3, 8);
		
		blockLocation = location.add(0, 3, -8);
		pattern.put(getLocationString(blockLocation), "WOOD_STEP,0");
		blockLocation = location.add(0, -3, 8);
		
		blockLocation = location.add(1, 3, -8);
		pattern.put(getLocationString(blockLocation), "WOOD_STAIRS,1");
		blockLocation = location.add(-1, -3, 8);
		
		blockLocation = location.add(2, 3, -8);
		pattern.put(getLocationString(blockLocation), "WOOD_STAIRS,0");
		blockLocation = location.add(-2, -3, 8);
		
		blockLocation = location.add(3, 3, -8);
		pattern.put(getLocationString(blockLocation), "LOG,8");
		blockLocation = location.add(-3, -3, 8);
		
		blockLocation = location.add(-4, 3, -7);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(4, -3, 7);
		
		blockLocation = location.add(-3, 3, -7);
		pattern.put(getLocationString(blockLocation), "LOG,8");
		blockLocation = location.add(3, -3, 7);
		
		blockLocation = location.add(-2, 3, -7);
		pattern.put(getLocationString(blockLocation), "WOOD_STEP,1");
		blockLocation = location.add(2, -3, 7);
		
		blockLocation = location.add(-1, 3, -7);
		pattern.put(getLocationString(blockLocation), "WOOD_STEP,1");
		blockLocation = location.add(1, -3, 7);
		
		blockLocation = location.add(0, 3, -7);
		pattern.put(getLocationString(blockLocation), "WOOD_STEP,1");
		blockLocation = location.add(0, -3, 7);
		
		blockLocation = location.add(1, 3, -7);
		pattern.put(getLocationString(blockLocation), "WOOD_STEP,1");
		blockLocation = location.add(-1, -3, 7);
		
		blockLocation = location.add(2, 3, -7);
		pattern.put(getLocationString(blockLocation), "WOOD_STEP,1");
		blockLocation = location.add(-2, -3, 7);
		
		blockLocation = location.add(3, 3, -7);
		pattern.put(getLocationString(blockLocation), "LOG,8");
		blockLocation = location.add(-3, -3, 7);
		
		blockLocation = location.add(4, 3, -7);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-4, -3, 7);
		
		blockLocation = location.add(-6, 3, -6);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(6, -3, 6);
		
		blockLocation = location.add(-5, 3, -6);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(5, -3, 6);
		
		blockLocation = location.add(-4, 3, -6);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(4, -3, 6);
		
		blockLocation = location.add(-3, 3, -6);
		pattern.put(getLocationString(blockLocation), "LOG,8");
		blockLocation = location.add(3, -3, 6);
		
		blockLocation = location.add(-2, 3, -6);
		pattern.put(getLocationString(blockLocation), "WOOD_STEP,1");
		blockLocation = location.add(2, -3, 6);
		
		blockLocation = location.add(-1, 3, -6);
		pattern.put(getLocationString(blockLocation), "WOOD_STEP,1");
		blockLocation = location.add(1, -3, 6);
		
		blockLocation = location.add(0, 3, -6);
		pattern.put(getLocationString(blockLocation), "WOOD_STEP,1");
		blockLocation = location.add(0, -3, 6);
		
		blockLocation = location.add(1, 3, -6);
		pattern.put(getLocationString(blockLocation), "WOOD_STEP,1");
		blockLocation = location.add(-1, -3, 6);
		
		blockLocation = location.add(2, 3, -6);
		pattern.put(getLocationString(blockLocation), "WOOD_STEP,1");
		blockLocation = location.add(-2, -3, 6);
		
		blockLocation = location.add(3, 3, -6);
		pattern.put(getLocationString(blockLocation), "LOG,8");
		blockLocation = location.add(-3, -3, 6);
		
		blockLocation = location.add(4, 3, -6);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-4, -3, 6);
		
		blockLocation = location.add(5, 3, -6);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-5, -3, 6);
		
		blockLocation = location.add(6, 3, -6);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-6, -3, 6);
		
		blockLocation = location.add(-6, 3, -5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(6, -3, 5);
		
		blockLocation = location.add(-5, 3, -5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(5, -3, 5);
		
		blockLocation = location.add(-4, 3, -5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(4, -3, 5);
		
		blockLocation = location.add(-3, 3, -5);
		pattern.put(getLocationString(blockLocation), "LOG,8");
		blockLocation = location.add(3, -3, 5);
		
		blockLocation = location.add(-1, 3, -5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(1, -3, 5);
		
		blockLocation = location.add(0, 3, -5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(0, -3, 5);
		
		blockLocation = location.add(1, 3, -5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-1, -3, 5);
		
		blockLocation = location.add(3, 3, -5);
		pattern.put(getLocationString(blockLocation), "LOG,8");
		blockLocation = location.add(-3, -3, 5);
		
		blockLocation = location.add(4, 3, -5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-4, -3, 5);
		
		blockLocation = location.add(5, 3, -5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-5, -3, 5);
		
		blockLocation = location.add(6, 3, -5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-6, -3, 5);
		
		blockLocation = location.add(-7, 3, -4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(7, -3, 4);
		
		blockLocation = location.add(-6, 3, -4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(6, -3, 4);
		
		blockLocation = location.add(-5, 3, -4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(5, -3, 4);
		
		blockLocation = location.add(-4, 3, -4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(4, -3, 4);
		
		blockLocation = location.add(-3, 3, -4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(3, -3, 4);
		
		blockLocation = location.add(-2, 3, -4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(2, -3, 4);
		
		blockLocation = location.add(-1, 3, -4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(1, -3, 4);
		
		blockLocation = location.add(0, 3, -4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(0, -3, 4);
		
		blockLocation = location.add(1, 3, -4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-1, -3, 4);
		
		blockLocation = location.add(2, 3, -4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-2, -3, 4);
		
		blockLocation = location.add(3, 3, -4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-3, -3, 4);
		
		blockLocation = location.add(4, 3, -4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-4, -3, 4);
		
		blockLocation = location.add(5, 3, -4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-5, -3, 4);
		
		blockLocation = location.add(6, 3, -4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-6, -3, 4);
		
		blockLocation = location.add(7, 3, -4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-7, -3, 4);
		
		blockLocation = location.add(-8, 3, -3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(8, -3, 3);
		
		blockLocation = location.add(-7, 3, -3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(7, -3, 3);
		
		blockLocation = location.add(-6, 3, -3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(6, -3, 3);
		
		blockLocation = location.add(-5, 3, -3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(5, -3, 3);
		
		blockLocation = location.add(-4, 3, -3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(4, -3, 3);
		
		blockLocation = location.add(-3, 3, -3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(3, -3, 3);
		
		blockLocation = location.add(-2, 3, -3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(2, -3, 3);
		
		blockLocation = location.add(-1, 3, -3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(1, -3, 3);
		
		blockLocation = location.add(0, 3, -3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(0, -3, 3);
		
		blockLocation = location.add(1, 3, -3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-1, -3, 3);
		
		blockLocation = location.add(2, 3, -3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-2, -3, 3);
		
		blockLocation = location.add(3, 3, -3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-3, -3, 3);
		
		blockLocation = location.add(4, 3, -3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-4, -3, 3);
		
		blockLocation = location.add(5, 3, -3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-5, -3, 3);
		
		blockLocation = location.add(6, 3, -3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-6, -3, 3);
		
		blockLocation = location.add(7, 3, -3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-7, -3, 3);
		
		blockLocation = location.add(8, 3, -3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-8, -3, 3);
		
		blockLocation = location.add(-8, 3, -2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(8, -3, 2);
		
		blockLocation = location.add(-7, 3, -2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(7, -3, 2);
		
		blockLocation = location.add(-6, 3, -2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(6, -3, 2);
		
		blockLocation = location.add(-5, 3, -2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(5, -3, 2);
		
		blockLocation = location.add(-4, 3, -2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(4, -3, 2);
		
		blockLocation = location.add(-3, 3, -2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(3, -3, 2);
		
		blockLocation = location.add(-2, 3, -2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(2, -3, 2);
		
		blockLocation = location.add(-1, 3, -2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(1, -3, 2);
		
		blockLocation = location.add(0, 3, -2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(0, -3, 2);
		
		blockLocation = location.add(1, 3, -2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-1, -3, 2);
		
		blockLocation = location.add(2, 3, -2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-2, -3, 2);
		
		blockLocation = location.add(3, 3, -2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-3, -3, 2);
		
		blockLocation = location.add(4, 3, -2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-4, -3, 2);
		
		blockLocation = location.add(5, 3, -2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-5, -3, 2);
		
		blockLocation = location.add(6, 3, -2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-6, -3, 2);
		
		blockLocation = location.add(7, 3, -2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-7, -3, 2);
		
		blockLocation = location.add(8, 3, -2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-8, -3, 2);
		
		blockLocation = location.add(-8, 3, -1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(8, -3, 1);
		
		blockLocation = location.add(-7, 3, -1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(7, -3, 1);
		
		blockLocation = location.add(-6, 3, -1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(6, -3, 1);
		
		blockLocation = location.add(-5, 3, -1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(5, -3, 1);
		
		blockLocation = location.add(-4, 3, -1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(4, -3, 1);
		
		blockLocation = location.add(-3, 3, -1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(3, -3, 1);
		
		blockLocation = location.add(-2, 3, -1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(2, -3, 1);
		
		blockLocation = location.add(-1, 3, -1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(1, -3, 1);
		
		blockLocation = location.add(0, 3, -1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(0, -3, 1);
		
		blockLocation = location.add(1, 3, -1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-1, -3, 1);
		
		blockLocation = location.add(2, 3, -1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-2, -3, 1);
		
		blockLocation = location.add(3, 3, -1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-3, -3, 1);
		
		blockLocation = location.add(4, 3, -1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-4, -3, 1);
		
		blockLocation = location.add(5, 3, -1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-5, -3, 1);
		
		blockLocation = location.add(6, 3, -1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-6, -3, 1);
		
		blockLocation = location.add(7, 3, -1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-7, -3, 1);
		
		blockLocation = location.add(8, 3, -1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-8, -3, 1);
		
		blockLocation = location.add(-9, 3, 0);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(9, -3, 0);
		
		blockLocation = location.add(-8, 3, 0);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(8, -3, 0);
		
		blockLocation = location.add(-7, 3, 0);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(7, -3, 0);
		
		blockLocation = location.add(-6, 3, 0);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(6, -3, 0);
		
		blockLocation = location.add(-5, 3, 0);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(5, -3, 0);
		
		blockLocation = location.add(-4, 3, 0);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(4, -3, 0);
		
		blockLocation = location.add(-3, 3, 0);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(3, -3, 0);
		
		blockLocation = location.add(-2, 3, 0);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(2, -3, 0);
		
		blockLocation = location.add(-1, 3, 0);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(1, -3, 0);
		
		blockLocation = location.add(0, 3, 0);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(0, -3, 0);
		
		blockLocation = location.add(1, 3, 0);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-1, -3, 0);
		
		blockLocation = location.add(2, 3, 0);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-2, -3, 0);
		
		blockLocation = location.add(3, 3, 0);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-3, -3, 0);
		
		blockLocation = location.add(4, 3, 0);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-4, -3, 0);
		
		blockLocation = location.add(5, 3, 0);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-5, -3, 0);
		
		blockLocation = location.add(6, 3, 0);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-6, -3, 0);
		
		blockLocation = location.add(7, 3, 0);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-7, -3, 0);
		
		blockLocation = location.add(8, 3, 0);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-8, -3, 0);
		
		blockLocation = location.add(9, 3, 0);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-9, -3, 0);
		
		blockLocation = location.add(-8, 3, 1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(8, -3, -1);
		
		blockLocation = location.add(-7, 3, 1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(7, -3, -1);
		
		blockLocation = location.add(-6, 3, 1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(6, -3, -1);
		
		blockLocation = location.add(-5, 3, 1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(5, -3, -1);
		
		blockLocation = location.add(-4, 3, 1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(4, -3, -1);
		
		blockLocation = location.add(-3, 3, 1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(3, -3, -1);
		
		blockLocation = location.add(-2, 3, 1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(2, -3, -1);
		
		blockLocation = location.add(-1, 3, 1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(1, -3, -1);
		
		blockLocation = location.add(0, 3, 1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(0, -3, -1);
		
		blockLocation = location.add(1, 3, 1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-1, -3, -1);
		
		blockLocation = location.add(2, 3, 1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-2, -3, -1);
		
		blockLocation = location.add(3, 3, 1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-3, -3, -1);
		
		blockLocation = location.add(4, 3, 1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-4, -3, -1);
		
		blockLocation = location.add(5, 3, 1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-5, -3, -1);
		
		blockLocation = location.add(6, 3, 1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-6, -3, -1);
		
		blockLocation = location.add(7, 3, 1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-7, -3, -1);
		
		blockLocation = location.add(8, 3, 1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-8, -3, -1);
		
		blockLocation = location.add(-8, 3, 2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(8, -3, -2);
		
		blockLocation = location.add(-7, 3, 2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(7, -3, -2);
		
		blockLocation = location.add(-6, 3, 2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(6, -3, -2);
		
		blockLocation = location.add(-5, 3, 2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(5, -3, -2);
		
		blockLocation = location.add(-4, 3, 2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(4, -3, -2);
		
		blockLocation = location.add(-3, 3, 2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(3, -3, -2);
		
		blockLocation = location.add(-2, 3, 2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(2, -3, -2);
		
		blockLocation = location.add(-1, 3, 2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(1, -3, -2);
		
		blockLocation = location.add(0, 3, 2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(0, -3, -2);
		
		blockLocation = location.add(1, 3, 2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-1, -3, -2);
		
		blockLocation = location.add(2, 3, 2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-2, -3, -2);
		
		blockLocation = location.add(3, 3, 2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-3, -3, -2);
		
		blockLocation = location.add(4, 3, 2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-4, -3, -2);
		
		blockLocation = location.add(5, 3, 2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-5, -3, -2);
		
		blockLocation = location.add(6, 3, 2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-6, -3, -2);
		
		blockLocation = location.add(7, 3, 2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-7, -3, -2);
		
		blockLocation = location.add(8, 3, 2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-8, -3, -2);
		
		blockLocation = location.add(-8, 3, 3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(8, -3, -3);
		
		blockLocation = location.add(-7, 3, 3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(7, -3, -3);
		
		blockLocation = location.add(-6, 3, 3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(6, -3, -3);
		
		blockLocation = location.add(-5, 3, 3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(5, -3, -3);
		
		blockLocation = location.add(-4, 3, 3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(4, -3, -3);
		
		blockLocation = location.add(-3, 3, 3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(3, -3, -3);
		
		blockLocation = location.add(-2, 3, 3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(2, -3, -3);
		
		blockLocation = location.add(-1, 3, 3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(1, -3, -3);
		
		blockLocation = location.add(0, 3, 3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(0, -3, -3);
		
		blockLocation = location.add(1, 3, 3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-1, -3, -3);
		
		blockLocation = location.add(2, 3, 3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-2, -3, -3);
		
		blockLocation = location.add(3, 3, 3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-3, -3, -3);
		
		blockLocation = location.add(4, 3, 3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-4, -3, -3);
		
		blockLocation = location.add(5, 3, 3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-5, -3, -3);
		
		blockLocation = location.add(6, 3, 3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-6, -3, -3);
		
		blockLocation = location.add(7, 3, 3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-7, -3, -3);
		
		blockLocation = location.add(8, 3, 3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-8, -3, -3);
		
		blockLocation = location.add(-7, 3, 4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(7, -3, -4);
		
		blockLocation = location.add(-6, 3, 4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(6, -3, -4);
		
		blockLocation = location.add(-5, 3, 4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(5, -3, -4);
		
		blockLocation = location.add(-4, 3, 4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(4, -3, -4);
		
		blockLocation = location.add(-3, 3, 4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(3, -3, -4);
		
		blockLocation = location.add(-2, 3, 4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(2, -3, -4);
		
		blockLocation = location.add(-1, 3, 4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(1, -3, -4);
		
		blockLocation = location.add(0, 3, 4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(0, -3, -4);
		
		blockLocation = location.add(1, 3, 4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-1, -3, -4);
		
		blockLocation = location.add(2, 3, 4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-2, -3, -4);
		
		blockLocation = location.add(3, 3, 4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-3, -3, -4);
		
		blockLocation = location.add(4, 3, 4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-4, -3, -4);
		
		blockLocation = location.add(5, 3, 4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-5, -3, -4);
		
		blockLocation = location.add(6, 3, 4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-6, -3, -4);
		
		blockLocation = location.add(7, 3, 4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-7, -3, -4);
		
		blockLocation = location.add(-6, 3, 5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(6, -3, -5);
		
		blockLocation = location.add(-5, 3, 5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(5, -3, -5);
		
		blockLocation = location.add(-4, 3, 5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(4, -3, -5);
		
		blockLocation = location.add(-3, 3, 5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(3, -3, -5);
		
		blockLocation = location.add(-2, 3, 5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(2, -3, -5);
		
		blockLocation = location.add(-1, 3, 5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(1, -3, -5);
		
		blockLocation = location.add(0, 3, 5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(0, -3, -5);
		
		blockLocation = location.add(1, 3, 5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-1, -3, -5);
		
		blockLocation = location.add(2, 3, 5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-2, -3, -5);
		
		blockLocation = location.add(3, 3, 5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-3, -3, -5);
		
		blockLocation = location.add(4, 3, 5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-4, -3, -5);
		
		blockLocation = location.add(5, 3, 5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-5, -3, -5);
		
		blockLocation = location.add(6, 3, 5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-6, -3, -5);
		
		blockLocation = location.add(-6, 3, 6);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(6, -3, -6);
		
		blockLocation = location.add(-5, 3, 6);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(5, -3, -6);
		
		blockLocation = location.add(-4, 3, 6);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(4, -3, -6);
		
		blockLocation = location.add(-3, 3, 6);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(3, -3, -6);
		
		blockLocation = location.add(-2, 3, 6);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(2, -3, -6);
		
		blockLocation = location.add(-1, 3, 6);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(1, -3, -6);
		
		blockLocation = location.add(0, 3, 6);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(0, -3, -6);
		
		blockLocation = location.add(1, 3, 6);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-1, -3, -6);
		
		blockLocation = location.add(2, 3, 6);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-2, -3, -6);
		
		blockLocation = location.add(3, 3, 6);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-3, -3, -6);
		
		blockLocation = location.add(4, 3, 6);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-4, -3, -6);
		
		blockLocation = location.add(5, 3, 6);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-5, -3, -6);
		
		blockLocation = location.add(6, 3, 6);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-6, -3, -6);
		
		blockLocation = location.add(-4, 3, 7);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(4, -3, -7);
		
		blockLocation = location.add(-3, 3, 7);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(3, -3, -7);
		
		blockLocation = location.add(-2, 3, 7);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(2, -3, -7);
		
		blockLocation = location.add(-1, 3, 7);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(1, -3, -7);
		
		blockLocation = location.add(0, 3, 7);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(0, -3, -7);
		
		blockLocation = location.add(1, 3, 7);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-1, -3, -7);
		
		blockLocation = location.add(2, 3, 7);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-2, -3, -7);
		
		blockLocation = location.add(3, 3, 7);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-3, -3, -7);
		
		blockLocation = location.add(4, 3, 7);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-4, -3, -7);
		
		blockLocation = location.add(-3, 3, 8);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(3, -3, -8);
		
		blockLocation = location.add(-2, 3, 8);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(2, -3, -8);
		
		blockLocation = location.add(-1, 3, 8);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(1, -3, -8);
		
		blockLocation = location.add(0, 3, 8);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(0, -3, -8);
		
		blockLocation = location.add(1, 3, 8);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-1, -3, -8);
		
		blockLocation = location.add(2, 3, 8);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-2, -3, -8);
		
		blockLocation = location.add(3, 3, 8);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-3, -3, -8);
		
		blockLocation = location.add(0, 3, 9);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(0, -3, -9);
		
		blockLocation = location.add(-3, 4, -8);
		pattern.put(getLocationString(blockLocation), "LOG,0");
		blockLocation = location.add(3, -4, 8);
		
		blockLocation = location.add(-2, 4, -8);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(2, -4, 8);
		
		blockLocation = location.add(-1, 4, -8);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(1, -4, 8);
		
		blockLocation = location.add(0, 4, -8);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(0, -4, 8);
		
		blockLocation = location.add(1, 4, -8);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-1, -4, 8);
		
		blockLocation = location.add(2, 4, -8);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-2, -4, 8);
		
		blockLocation = location.add(3, 4, -8);
		pattern.put(getLocationString(blockLocation), "LOG,0");
		blockLocation = location.add(-3, -4, 8);
		
		blockLocation = location.add(-5, 4, -7);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(5, -4, 7);
		
		blockLocation = location.add(-4, 4, -7);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(4, -4, 7);
		
		blockLocation = location.add(-3, 4, -7);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(3, -4, 7);
		
		blockLocation = location.add(-2, 4, -7);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(2, -4, 7);
		
		blockLocation = location.add(-1, 4, -7);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(1, -4, 7);
		
		blockLocation = location.add(0, 4, -7);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(0, -4, 7);
		
		blockLocation = location.add(1, 4, -7);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-1, -4, 7);
		
		blockLocation = location.add(2, 4, -7);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-2, -4, 7);
		
		blockLocation = location.add(3, 4, -7);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-3, -4, 7);
		
		blockLocation = location.add(4, 4, -7);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-4, -4, 7);
		
		blockLocation = location.add(5, 4, -7);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-5, -4, 7);
		
		blockLocation = location.add(-6, 4, -6);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(6, -4, 6);
		
		blockLocation = location.add(-5, 4, -6);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(5, -4, 6);
		
		blockLocation = location.add(-4, 4, -6);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(4, -4, 6);
		
		blockLocation = location.add(-3, 4, -6);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(3, -4, 6);
		
		blockLocation = location.add(-2, 4, -6);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(2, -4, 6);
		
		blockLocation = location.add(-1, 4, -6);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(1, -4, 6);
		
		blockLocation = location.add(0, 4, -6);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(0, -4, 6);
		
		blockLocation = location.add(1, 4, -6);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-1, -4, 6);
		
		blockLocation = location.add(2, 4, -6);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-2, -4, 6);
		
		blockLocation = location.add(3, 4, -6);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-3, -4, 6);
		
		blockLocation = location.add(4, 4, -6);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-4, -4, 6);
		
		blockLocation = location.add(5, 4, -6);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-5, -4, 6);
		
		blockLocation = location.add(6, 4, -6);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-6, -4, 6);
		
		blockLocation = location.add(-7, 4, -5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(7, -4, 5);
		
		blockLocation = location.add(-6, 4, -5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(6, -4, 5);
		
		blockLocation = location.add(-5, 4, -5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(5, -4, 5);
		
		blockLocation = location.add(-4, 4, -5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(4, -4, 5);
		
		blockLocation = location.add(-3, 4, -5);
		pattern.put(getLocationString(blockLocation), "LOG,0");
		blockLocation = location.add(3, -4, 5);
		
		blockLocation = location.add(-2, 4, -5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(2, -4, 5);
		
		blockLocation = location.add(-1, 4, -5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(1, -4, 5);
		
		blockLocation = location.add(0, 4, -5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(0, -4, 5);
		
		blockLocation = location.add(1, 4, -5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-1, -4, 5);
		
		blockLocation = location.add(2, 4, -5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-2, -4, 5);
		
		blockLocation = location.add(3, 4, -5);
		pattern.put(getLocationString(blockLocation), "LOG,0");
		blockLocation = location.add(-3, -4, 5);
		
		blockLocation = location.add(4, 4, -5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-4, -4, 5);
		
		blockLocation = location.add(5, 4, -5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-5, -4, 5);
		
		blockLocation = location.add(6, 4, -5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-6, -4, 5);
		
		blockLocation = location.add(7, 4, -5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-7, -4, 5);
		
		blockLocation = location.add(-7, 4, -4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(7, -4, 4);
		
		blockLocation = location.add(-6, 4, -4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(6, -4, 4);
		
		blockLocation = location.add(-5, 4, -4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(5, -4, 4);
		
		blockLocation = location.add(-4, 4, -4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(4, -4, 4);
		
		blockLocation = location.add(-3, 4, -4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(3, -4, 4);
		
		blockLocation = location.add(-2, 4, -4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(2, -4, 4);
		
		blockLocation = location.add(-1, 4, -4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(1, -4, 4);
		
		blockLocation = location.add(0, 4, -4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(0, -4, 4);
		
		blockLocation = location.add(1, 4, -4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-1, -4, 4);
		
		blockLocation = location.add(2, 4, -4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-2, -4, 4);
		
		blockLocation = location.add(3, 4, -4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-3, -4, 4);
		
		blockLocation = location.add(4, 4, -4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-4, -4, 4);
		
		blockLocation = location.add(5, 4, -4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-5, -4, 4);
		
		blockLocation = location.add(6, 4, -4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-6, -4, 4);
		
		blockLocation = location.add(7, 4, -4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-7, -4, 4);
		
		blockLocation = location.add(-8, 4, -3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(8, -4, 3);
		
		blockLocation = location.add(-7, 4, -3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(7, -4, 3);
		
		blockLocation = location.add(-6, 4, -3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(6, -4, 3);
		
		blockLocation = location.add(-5, 4, -3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(5, -4, 3);
		
		blockLocation = location.add(-4, 4, -3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(4, -4, 3);
		
		blockLocation = location.add(-3, 4, -3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(3, -4, 3);
		
		blockLocation = location.add(-2, 4, -3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(2, -4, 3);
		
		blockLocation = location.add(-1, 4, -3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(1, -4, 3);
		
		blockLocation = location.add(0, 4, -3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(0, -4, 3);
		
		blockLocation = location.add(1, 4, -3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-1, -4, 3);
		
		blockLocation = location.add(2, 4, -3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-2, -4, 3);
		
		blockLocation = location.add(3, 4, -3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-3, -4, 3);
		
		blockLocation = location.add(4, 4, -3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-4, -4, 3);
		
		blockLocation = location.add(5, 4, -3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-5, -4, 3);
		
		blockLocation = location.add(6, 4, -3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-6, -4, 3);
		
		blockLocation = location.add(7, 4, -3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-7, -4, 3);
		
		blockLocation = location.add(8, 4, -3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-8, -4, 3);
		
		blockLocation = location.add(-8, 4, -2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(8, -4, 2);
		
		blockLocation = location.add(-7, 4, -2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(7, -4, 2);
		
		blockLocation = location.add(-6, 4, -2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(6, -4, 2);
		
		blockLocation = location.add(-5, 4, -2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(5, -4, 2);
		
		blockLocation = location.add(-4, 4, -2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(4, -4, 2);
		
		blockLocation = location.add(-3, 4, -2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(3, -4, 2);
		
		blockLocation = location.add(-2, 4, -2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(2, -4, 2);
		
		blockLocation = location.add(-1, 4, -2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(1, -4, 2);
		
		blockLocation = location.add(0, 4, -2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(0, -4, 2);
		
		blockLocation = location.add(1, 4, -2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-1, -4, 2);
		
		blockLocation = location.add(2, 4, -2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-2, -4, 2);
		
		blockLocation = location.add(3, 4, -2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-3, -4, 2);
		
		blockLocation = location.add(4, 4, -2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-4, -4, 2);
		
		blockLocation = location.add(5, 4, -2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-5, -4, 2);
		
		blockLocation = location.add(6, 4, -2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-6, -4, 2);
		
		blockLocation = location.add(7, 4, -2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-7, -4, 2);
		
		blockLocation = location.add(8, 4, -2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-8, -4, 2);
		
		blockLocation = location.add(-9, 4, -1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(9, -4, 1);
		
		blockLocation = location.add(-8, 4, -1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(8, -4, 1);
		
		blockLocation = location.add(-7, 4, -1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(7, -4, 1);
		
		blockLocation = location.add(-6, 4, -1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(6, -4, 1);
		
		blockLocation = location.add(-5, 4, -1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(5, -4, 1);
		
		blockLocation = location.add(-4, 4, -1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(4, -4, 1);
		
		blockLocation = location.add(-3, 4, -1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(3, -4, 1);
		
		blockLocation = location.add(-2, 4, -1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(2, -4, 1);
		
		blockLocation = location.add(-1, 4, -1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(1, -4, 1);
		
		blockLocation = location.add(0, 4, -1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(0, -4, 1);
		
		blockLocation = location.add(1, 4, -1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-1, -4, 1);
		
		blockLocation = location.add(2, 4, -1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-2, -4, 1);
		
		blockLocation = location.add(3, 4, -1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-3, -4, 1);
		
		blockLocation = location.add(4, 4, -1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-4, -4, 1);
		
		blockLocation = location.add(5, 4, -1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-5, -4, 1);
		
		blockLocation = location.add(6, 4, -1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-6, -4, 1);
		
		blockLocation = location.add(7, 4, -1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-7, -4, 1);
		
		blockLocation = location.add(8, 4, -1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-8, -4, 1);
		
		blockLocation = location.add(9, 4, -1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-9, -4, 1);
		
		blockLocation = location.add(-9, 4, 0);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(9, -4, 0);
		
		blockLocation = location.add(-8, 4, 0);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(8, -4, 0);
		
		blockLocation = location.add(-7, 4, 0);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(7, -4, 0);
		
		blockLocation = location.add(-6, 4, 0);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(6, -4, 0);
		
		blockLocation = location.add(-5, 4, 0);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(5, -4, 0);
		
		blockLocation = location.add(-4, 4, 0);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(4, -4, 0);
		
		blockLocation = location.add(-3, 4, 0);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(3, -4, 0);
		
		blockLocation = location.add(-2, 4, 0);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(2, -4, 0);
		
		blockLocation = location.add(-1, 4, 0);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(1, -4, 0);
		
		blockLocation = location.add(0, 4, 0);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(0, -4, 0);
		
		blockLocation = location.add(1, 4, 0);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-1, -4, 0);
		
		blockLocation = location.add(2, 4, 0);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-2, -4, 0);
		
		blockLocation = location.add(3, 4, 0);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-3, -4, 0);
		
		blockLocation = location.add(4, 4, 0);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-4, -4, 0);
		
		blockLocation = location.add(5, 4, 0);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-5, -4, 0);
		
		blockLocation = location.add(6, 4, 0);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-6, -4, 0);
		
		blockLocation = location.add(7, 4, 0);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-7, -4, 0);
		
		blockLocation = location.add(8, 4, 0);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-8, -4, 0);
		
		blockLocation = location.add(9, 4, 0);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-9, -4, 0);
		
		blockLocation = location.add(-9, 4, 1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(9, -4, -1);
		
		blockLocation = location.add(-8, 4, 1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(8, -4, -1);
		
		blockLocation = location.add(-7, 4, 1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(7, -4, -1);
		
		blockLocation = location.add(-6, 4, 1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(6, -4, -1);
		
		blockLocation = location.add(-5, 4, 1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(5, -4, -1);
		
		blockLocation = location.add(-4, 4, 1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(4, -4, -1);
		
		blockLocation = location.add(-3, 4, 1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(3, -4, -1);
		
		blockLocation = location.add(-2, 4, 1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(2, -4, -1);
		
		blockLocation = location.add(-1, 4, 1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(1, -4, -1);
		
		blockLocation = location.add(0, 4, 1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(0, -4, -1);
		
		blockLocation = location.add(1, 4, 1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-1, -4, -1);
		
		blockLocation = location.add(2, 4, 1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-2, -4, -1);
		
		blockLocation = location.add(3, 4, 1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-3, -4, -1);
		
		blockLocation = location.add(4, 4, 1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-4, -4, -1);
		
		blockLocation = location.add(5, 4, 1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-5, -4, -1);
		
		blockLocation = location.add(6, 4, 1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-6, -4, -1);
		
		blockLocation = location.add(7, 4, 1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-7, -4, -1);
		
		blockLocation = location.add(8, 4, 1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-8, -4, -1);
		
		blockLocation = location.add(9, 4, 1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-9, -4, -1);
		
		blockLocation = location.add(-8, 4, 2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(8, -4, -2);
		
		blockLocation = location.add(-7, 4, 2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(7, -4, -2);
		
		blockLocation = location.add(-6, 4, 2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(6, -4, -2);
		
		blockLocation = location.add(-5, 4, 2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(5, -4, -2);
		
		blockLocation = location.add(-4, 4, 2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(4, -4, -2);
		
		blockLocation = location.add(-3, 4, 2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(3, -4, -2);
		
		blockLocation = location.add(-2, 4, 2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(2, -4, -2);
		
		blockLocation = location.add(-1, 4, 2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(1, -4, -2);
		
		blockLocation = location.add(0, 4, 2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(0, -4, -2);
		
		blockLocation = location.add(1, 4, 2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-1, -4, -2);
		
		blockLocation = location.add(2, 4, 2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-2, -4, -2);
		
		blockLocation = location.add(3, 4, 2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-3, -4, -2);
		
		blockLocation = location.add(4, 4, 2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-4, -4, -2);
		
		blockLocation = location.add(5, 4, 2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-5, -4, -2);
		
		blockLocation = location.add(6, 4, 2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-6, -4, -2);
		
		blockLocation = location.add(7, 4, 2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-7, -4, -2);
		
		blockLocation = location.add(8, 4, 2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-8, -4, -2);
		
		blockLocation = location.add(-8, 4, 3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(8, -4, -3);
		
		blockLocation = location.add(-7, 4, 3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(7, -4, -3);
		
		blockLocation = location.add(-6, 4, 3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(6, -4, -3);
		
		blockLocation = location.add(-5, 4, 3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(5, -4, -3);
		
		blockLocation = location.add(-4, 4, 3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(4, -4, -3);
		
		blockLocation = location.add(-3, 4, 3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(3, -4, -3);
		
		blockLocation = location.add(-2, 4, 3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(2, -4, -3);
		
		blockLocation = location.add(-1, 4, 3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(1, -4, -3);
		
		blockLocation = location.add(0, 4, 3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(0, -4, -3);
		
		blockLocation = location.add(1, 4, 3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-1, -4, -3);
		
		blockLocation = location.add(2, 4, 3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-2, -4, -3);
		
		blockLocation = location.add(3, 4, 3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-3, -4, -3);
		
		blockLocation = location.add(4, 4, 3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-4, -4, -3);
		
		blockLocation = location.add(5, 4, 3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-5, -4, -3);
		
		blockLocation = location.add(6, 4, 3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-6, -4, -3);
		
		blockLocation = location.add(7, 4, 3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-7, -4, -3);
		
		blockLocation = location.add(8, 4, 3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-8, -4, -3);
		
		blockLocation = location.add(-7, 4, 4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(7, -4, -4);
		
		blockLocation = location.add(-6, 4, 4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(6, -4, -4);
		
		blockLocation = location.add(-5, 4, 4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(5, -4, -4);
		
		blockLocation = location.add(-4, 4, 4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(4, -4, -4);
		
		blockLocation = location.add(-3, 4, 4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(3, -4, -4);
		
		blockLocation = location.add(-2, 4, 4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(2, -4, -4);
		
		blockLocation = location.add(-1, 4, 4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(1, -4, -4);
		
		blockLocation = location.add(0, 4, 4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(0, -4, -4);
		
		blockLocation = location.add(1, 4, 4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-1, -4, -4);
		
		blockLocation = location.add(2, 4, 4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-2, -4, -4);
		
		blockLocation = location.add(3, 4, 4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-3, -4, -4);
		
		blockLocation = location.add(4, 4, 4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-4, -4, -4);
		
		blockLocation = location.add(5, 4, 4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-5, -4, -4);
		
		blockLocation = location.add(6, 4, 4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-6, -4, -4);
		
		blockLocation = location.add(7, 4, 4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-7, -4, -4);
		
		blockLocation = location.add(-7, 4, 5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(7, -4, -5);
		
		blockLocation = location.add(-6, 4, 5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(6, -4, -5);
		
		blockLocation = location.add(-5, 4, 5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(5, -4, -5);
		
		blockLocation = location.add(-4, 4, 5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(4, -4, -5);
		
		blockLocation = location.add(-3, 4, 5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(3, -4, -5);
		
		blockLocation = location.add(-2, 4, 5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(2, -4, -5);
		
		blockLocation = location.add(-1, 4, 5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(1, -4, -5);
		
		blockLocation = location.add(0, 4, 5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(0, -4, -5);
		
		blockLocation = location.add(1, 4, 5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-1, -4, -5);
		
		blockLocation = location.add(2, 4, 5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-2, -4, -5);
		
		blockLocation = location.add(3, 4, 5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-3, -4, -5);
		
		blockLocation = location.add(4, 4, 5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-4, -4, -5);
		
		blockLocation = location.add(5, 4, 5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-5, -4, -5);
		
		blockLocation = location.add(6, 4, 5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-6, -4, -5);
		
		blockLocation = location.add(7, 4, 5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-7, -4, -5);
		
		blockLocation = location.add(-6, 4, 6);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(6, -4, -6);
		
		blockLocation = location.add(-5, 4, 6);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(5, -4, -6);
		
		blockLocation = location.add(-4, 4, 6);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(4, -4, -6);
		
		blockLocation = location.add(-3, 4, 6);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(3, -4, -6);
		
		blockLocation = location.add(-2, 4, 6);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(2, -4, -6);
		
		blockLocation = location.add(-1, 4, 6);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(1, -4, -6);
		
		blockLocation = location.add(0, 4, 6);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(0, -4, -6);
		
		blockLocation = location.add(1, 4, 6);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-1, -4, -6);
		
		blockLocation = location.add(2, 4, 6);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-2, -4, -6);
		
		blockLocation = location.add(3, 4, 6);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-3, -4, -6);
		
		blockLocation = location.add(4, 4, 6);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-4, -4, -6);
		
		blockLocation = location.add(5, 4, 6);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-5, -4, -6);
		
		blockLocation = location.add(6, 4, 6);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-6, -4, -6);
		
		blockLocation = location.add(-5, 4, 7);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(5, -4, -7);
		
		blockLocation = location.add(-4, 4, 7);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(4, -4, -7);
		
		blockLocation = location.add(-3, 4, 7);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(3, -4, -7);
		
		blockLocation = location.add(-2, 4, 7);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(2, -4, -7);
		
		blockLocation = location.add(-1, 4, 7);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(1, -4, -7);
		
		blockLocation = location.add(0, 4, 7);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(0, -4, -7);
		
		blockLocation = location.add(1, 4, 7);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-1, -4, -7);
		
		blockLocation = location.add(2, 4, 7);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-2, -4, -7);
		
		blockLocation = location.add(3, 4, 7);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-3, -4, -7);
		
		blockLocation = location.add(4, 4, 7);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-4, -4, -7);
		
		blockLocation = location.add(5, 4, 7);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-5, -4, -7);
		
		blockLocation = location.add(-3, 4, 8);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(3, -4, -8);
		
		blockLocation = location.add(-2, 4, 8);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(2, -4, -8);
		
		blockLocation = location.add(-1, 4, 8);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(1, -4, -8);
		
		blockLocation = location.add(0, 4, 8);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(0, -4, -8);
		
		blockLocation = location.add(1, 4, 8);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-1, -4, -8);
		
		blockLocation = location.add(2, 4, 8);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-2, -4, -8);
		
		blockLocation = location.add(3, 4, 8);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-3, -4, -8);
		
		blockLocation = location.add(-1, 4, 9);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(1, -4, -9);
		
		blockLocation = location.add(0, 4, 9);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(0, -4, -9);
		
		blockLocation = location.add(1, 4, 9);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-1, -4, -9);
		
		blockLocation = location.add(-3, 5, -8);
		pattern.put(getLocationString(blockLocation), "FENCE,0");
		blockLocation = location.add(3, -5, 8);
		
		blockLocation = location.add(-2, 5, -8);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(2, -5, 8);
		
		blockLocation = location.add(-1, 5, -8);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(1, -5, 8);
		
		blockLocation = location.add(0, 5, -8);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(0, -5, 8);
		
		blockLocation = location.add(1, 5, -8);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-1, -5, 8);
		
		blockLocation = location.add(2, 5, -8);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-2, -5, 8);
		
		blockLocation = location.add(3, 5, -8);
		pattern.put(getLocationString(blockLocation), "FENCE,0");
		blockLocation = location.add(-3, -5, 8);
		
		blockLocation = location.add(-5, 5, -7);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(5, -5, 7);
		
		blockLocation = location.add(-4, 5, -7);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(4, -5, 7);
		
		blockLocation = location.add(-3, 5, -7);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(3, -5, 7);
		
		blockLocation = location.add(-2, 5, -7);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(2, -5, 7);
		
		blockLocation = location.add(-1, 5, -7);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(1, -5, 7);
		
		blockLocation = location.add(0, 5, -7);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(0, -5, 7);
		
		blockLocation = location.add(1, 5, -7);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-1, -5, 7);
		
		blockLocation = location.add(2, 5, -7);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-2, -5, 7);
		
		blockLocation = location.add(3, 5, -7);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-3, -5, 7);
		
		blockLocation = location.add(4, 5, -7);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-4, -5, 7);
		
		blockLocation = location.add(5, 5, -7);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-5, -5, 7);
		
		blockLocation = location.add(-6, 5, -6);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(6, -5, 6);
		
		blockLocation = location.add(-5, 5, -6);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(5, -5, 6);
		
		blockLocation = location.add(-4, 5, -6);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(4, -5, 6);
		
		blockLocation = location.add(-3, 5, -6);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(3, -5, 6);
		
		blockLocation = location.add(-2, 5, -6);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(2, -5, 6);
		
		blockLocation = location.add(-1, 5, -6);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(1, -5, 6);
		
		blockLocation = location.add(0, 5, -6);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(0, -5, 6);
		
		blockLocation = location.add(1, 5, -6);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-1, -5, 6);
		
		blockLocation = location.add(2, 5, -6);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-2, -5, 6);
		
		blockLocation = location.add(3, 5, -6);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-3, -5, 6);
		
		blockLocation = location.add(4, 5, -6);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-4, -5, 6);
		
		blockLocation = location.add(5, 5, -6);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-5, -5, 6);
		
		blockLocation = location.add(6, 5, -6);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-6, -5, 6);
		
		blockLocation = location.add(-7, 5, -5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(7, -5, 5);
		
		blockLocation = location.add(-6, 5, -5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(6, -5, 5);
		
		blockLocation = location.add(-5, 5, -5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(5, -5, 5);
		
		blockLocation = location.add(-4, 5, -5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(4, -5, 5);
		
		blockLocation = location.add(-3, 5, -5);
		pattern.put(getLocationString(blockLocation), "FENCE,0");
		blockLocation = location.add(3, -5, 5);
		
		blockLocation = location.add(-2, 5, -5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(2, -5, 5);
		
		blockLocation = location.add(-1, 5, -5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(1, -5, 5);
		
		blockLocation = location.add(0, 5, -5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(0, -5, 5);
		
		blockLocation = location.add(1, 5, -5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-1, -5, 5);
		
		blockLocation = location.add(2, 5, -5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-2, -5, 5);
		
		blockLocation = location.add(3, 5, -5);
		pattern.put(getLocationString(blockLocation), "FENCE,0");
		blockLocation = location.add(-3, -5, 5);
		
		blockLocation = location.add(4, 5, -5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-4, -5, 5);
		
		blockLocation = location.add(5, 5, -5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-5, -5, 5);
		
		blockLocation = location.add(6, 5, -5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-6, -5, 5);
		
		blockLocation = location.add(7, 5, -5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-7, -5, 5);
		
		blockLocation = location.add(-7, 5, -4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(7, -5, 4);
		
		blockLocation = location.add(-6, 5, -4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(6, -5, 4);
		
		blockLocation = location.add(-5, 5, -4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(5, -5, 4);
		
		blockLocation = location.add(-4, 5, -4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(4, -5, 4);
		
		blockLocation = location.add(-3, 5, -4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(3, -5, 4);
		
		blockLocation = location.add(-2, 5, -4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(2, -5, 4);
		
		blockLocation = location.add(-1, 5, -4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(1, -5, 4);
		
		blockLocation = location.add(0, 5, -4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(0, -5, 4);
		
		blockLocation = location.add(1, 5, -4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-1, -5, 4);
		
		blockLocation = location.add(2, 5, -4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-2, -5, 4);
		
		blockLocation = location.add(3, 5, -4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-3, -5, 4);
		
		blockLocation = location.add(4, 5, -4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-4, -5, 4);
		
		blockLocation = location.add(5, 5, -4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-5, -5, 4);
		
		blockLocation = location.add(6, 5, -4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-6, -5, 4);
		
		blockLocation = location.add(7, 5, -4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-7, -5, 4);
		
		blockLocation = location.add(-8, 5, -3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(8, -5, 3);
		
		blockLocation = location.add(-7, 5, -3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(7, -5, 3);
		
		blockLocation = location.add(-6, 5, -3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(6, -5, 3);
		
		blockLocation = location.add(-5, 5, -3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(5, -5, 3);
		
		blockLocation = location.add(-4, 5, -3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(4, -5, 3);
		
		blockLocation = location.add(-3, 5, -3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(3, -5, 3);
		
		blockLocation = location.add(-2, 5, -3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(2, -5, 3);
		
		blockLocation = location.add(-1, 5, -3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(1, -5, 3);
		
		blockLocation = location.add(0, 5, -3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(0, -5, 3);
		
		blockLocation = location.add(1, 5, -3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-1, -5, 3);
		
		blockLocation = location.add(2, 5, -3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-2, -5, 3);
		
		blockLocation = location.add(3, 5, -3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-3, -5, 3);
		
		blockLocation = location.add(4, 5, -3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-4, -5, 3);
		
		blockLocation = location.add(5, 5, -3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-5, -5, 3);
		
		blockLocation = location.add(6, 5, -3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-6, -5, 3);
		
		blockLocation = location.add(7, 5, -3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-7, -5, 3);
		
		blockLocation = location.add(8, 5, -3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-8, -5, 3);
		
		blockLocation = location.add(-8, 5, -2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(8, -5, 2);
		
		blockLocation = location.add(-7, 5, -2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(7, -5, 2);
		
		blockLocation = location.add(-6, 5, -2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(6, -5, 2);
		
		blockLocation = location.add(-5, 5, -2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(5, -5, 2);
		
		blockLocation = location.add(-4, 5, -2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(4, -5, 2);
		
		blockLocation = location.add(-3, 5, -2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(3, -5, 2);
		
		blockLocation = location.add(-2, 5, -2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(2, -5, 2);
		
		blockLocation = location.add(-1, 5, -2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(1, -5, 2);
		
		blockLocation = location.add(0, 5, -2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(0, -5, 2);
		
		blockLocation = location.add(1, 5, -2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-1, -5, 2);
		
		blockLocation = location.add(2, 5, -2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-2, -5, 2);
		
		blockLocation = location.add(3, 5, -2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-3, -5, 2);
		
		blockLocation = location.add(4, 5, -2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-4, -5, 2);
		
		blockLocation = location.add(5, 5, -2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-5, -5, 2);
		
		blockLocation = location.add(6, 5, -2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-6, -5, 2);
		
		blockLocation = location.add(7, 5, -2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-7, -5, 2);
		
		blockLocation = location.add(8, 5, -2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-8, -5, 2);
		
		blockLocation = location.add(-9, 5, -1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(9, -5, 1);
		
		blockLocation = location.add(-8, 5, -1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(8, -5, 1);
		
		blockLocation = location.add(-7, 5, -1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(7, -5, 1);
		
		blockLocation = location.add(-6, 5, -1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(6, -5, 1);
		
		blockLocation = location.add(-5, 5, -1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(5, -5, 1);
		
		blockLocation = location.add(-4, 5, -1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(4, -5, 1);
		
		blockLocation = location.add(-3, 5, -1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(3, -5, 1);
		
		blockLocation = location.add(-2, 5, -1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(2, -5, 1);
		
		blockLocation = location.add(-1, 5, -1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(1, -5, 1);
		
		blockLocation = location.add(0, 5, -1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(0, -5, 1);
		
		blockLocation = location.add(1, 5, -1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-1, -5, 1);
		
		blockLocation = location.add(2, 5, -1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-2, -5, 1);
		
		blockLocation = location.add(3, 5, -1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-3, -5, 1);
		
		blockLocation = location.add(4, 5, -1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-4, -5, 1);
		
		blockLocation = location.add(5, 5, -1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-5, -5, 1);
		
		blockLocation = location.add(6, 5, -1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-6, -5, 1);
		
		blockLocation = location.add(7, 5, -1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-7, -5, 1);
		
		blockLocation = location.add(8, 5, -1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-8, -5, 1);
		
		blockLocation = location.add(9, 5, -1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-9, -5, 1);
		
		blockLocation = location.add(-9, 5, 0);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(9, -5, 0);
		
		blockLocation = location.add(-8, 5, 0);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(8, -5, 0);
		
		blockLocation = location.add(-7, 5, 0);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(7, -5, 0);
		
		blockLocation = location.add(-6, 5, 0);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(6, -5, 0);
		
		blockLocation = location.add(-5, 5, 0);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(5, -5, 0);
		
		blockLocation = location.add(-4, 5, 0);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(4, -5, 0);
		
		blockLocation = location.add(-3, 5, 0);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(3, -5, 0);
		
		blockLocation = location.add(-2, 5, 0);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(2, -5, 0);
		
		blockLocation = location.add(-1, 5, 0);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(1, -5, 0);
		
		blockLocation = location.add(0, 5, 0);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(0, -5, 0);
		
		blockLocation = location.add(1, 5, 0);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-1, -5, 0);
		
		blockLocation = location.add(2, 5, 0);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-2, -5, 0);
		
		blockLocation = location.add(3, 5, 0);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-3, -5, 0);
		
		blockLocation = location.add(4, 5, 0);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-4, -5, 0);
		
		blockLocation = location.add(5, 5, 0);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-5, -5, 0);
		
		blockLocation = location.add(6, 5, 0);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-6, -5, 0);
		
		blockLocation = location.add(7, 5, 0);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-7, -5, 0);
		
		blockLocation = location.add(8, 5, 0);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-8, -5, 0);
		
		blockLocation = location.add(9, 5, 0);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-9, -5, 0);
		
		blockLocation = location.add(-9, 5, 1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(9, -5, -1);
		
		blockLocation = location.add(-8, 5, 1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(8, -5, -1);
		
		blockLocation = location.add(-7, 5, 1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(7, -5, -1);
		
		blockLocation = location.add(-6, 5, 1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(6, -5, -1);
		
		blockLocation = location.add(-5, 5, 1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(5, -5, -1);
		
		blockLocation = location.add(-4, 5, 1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(4, -5, -1);
		
		blockLocation = location.add(-3, 5, 1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(3, -5, -1);
		
		blockLocation = location.add(-2, 5, 1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(2, -5, -1);
		
		blockLocation = location.add(-1, 5, 1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(1, -5, -1);
		
		blockLocation = location.add(0, 5, 1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(0, -5, -1);
		
		blockLocation = location.add(1, 5, 1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-1, -5, -1);
		
		blockLocation = location.add(2, 5, 1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-2, -5, -1);
		
		blockLocation = location.add(3, 5, 1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-3, -5, -1);
		
		blockLocation = location.add(4, 5, 1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-4, -5, -1);
		
		blockLocation = location.add(5, 5, 1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-5, -5, -1);
		
		blockLocation = location.add(6, 5, 1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-6, -5, -1);
		
		blockLocation = location.add(7, 5, 1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-7, -5, -1);
		
		blockLocation = location.add(8, 5, 1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-8, -5, -1);
		
		blockLocation = location.add(9, 5, 1);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-9, -5, -1);
		
		blockLocation = location.add(-8, 5, 2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(8, -5, -2);
		
		blockLocation = location.add(-7, 5, 2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(7, -5, -2);
		
		blockLocation = location.add(-6, 5, 2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(6, -5, -2);
		
		blockLocation = location.add(-5, 5, 2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(5, -5, -2);
		
		blockLocation = location.add(-4, 5, 2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(4, -5, -2);
		
		blockLocation = location.add(-3, 5, 2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(3, -5, -2);
		
		blockLocation = location.add(-2, 5, 2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(2, -5, -2);
		
		blockLocation = location.add(-1, 5, 2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(1, -5, -2);
		
		blockLocation = location.add(0, 5, 2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(0, -5, -2);
		
		blockLocation = location.add(1, 5, 2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-1, -5, -2);
		
		blockLocation = location.add(2, 5, 2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-2, -5, -2);
		
		blockLocation = location.add(3, 5, 2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-3, -5, -2);
		
		blockLocation = location.add(4, 5, 2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-4, -5, -2);
		
		blockLocation = location.add(5, 5, 2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-5, -5, -2);
		
		blockLocation = location.add(6, 5, 2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-6, -5, -2);
		
		blockLocation = location.add(7, 5, 2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-7, -5, -2);
		
		blockLocation = location.add(8, 5, 2);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-8, -5, -2);
		
		blockLocation = location.add(-8, 5, 3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(8, -5, -3);
		
		blockLocation = location.add(-7, 5, 3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(7, -5, -3);
		
		blockLocation = location.add(-6, 5, 3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(6, -5, -3);
		
		blockLocation = location.add(-5, 5, 3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(5, -5, -3);
		
		blockLocation = location.add(-4, 5, 3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(4, -5, -3);
		
		blockLocation = location.add(-3, 5, 3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(3, -5, -3);
		
		blockLocation = location.add(-2, 5, 3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(2, -5, -3);
		
		blockLocation = location.add(-1, 5, 3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(1, -5, -3);
		
		blockLocation = location.add(0, 5, 3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(0, -5, -3);
		
		blockLocation = location.add(1, 5, 3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-1, -5, -3);
		
		blockLocation = location.add(2, 5, 3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-2, -5, -3);
		
		blockLocation = location.add(3, 5, 3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-3, -5, -3);
		
		blockLocation = location.add(4, 5, 3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-4, -5, -3);
		
		blockLocation = location.add(5, 5, 3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-5, -5, -3);
		
		blockLocation = location.add(6, 5, 3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-6, -5, -3);
		
		blockLocation = location.add(7, 5, 3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-7, -5, -3);
		
		blockLocation = location.add(8, 5, 3);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-8, -5, -3);
		
		blockLocation = location.add(-7, 5, 4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(7, -5, -4);
		
		blockLocation = location.add(-6, 5, 4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(6, -5, -4);
		
		blockLocation = location.add(-5, 5, 4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(5, -5, -4);
		
		blockLocation = location.add(-4, 5, 4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(4, -5, -4);
		
		blockLocation = location.add(-3, 5, 4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(3, -5, -4);
		
		blockLocation = location.add(-2, 5, 4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(2, -5, -4);
		
		blockLocation = location.add(-1, 5, 4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(1, -5, -4);
		
		blockLocation = location.add(0, 5, 4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(0, -5, -4);
		
		blockLocation = location.add(1, 5, 4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-1, -5, -4);
		
		blockLocation = location.add(2, 5, 4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-2, -5, -4);
		
		blockLocation = location.add(3, 5, 4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-3, -5, -4);
		
		blockLocation = location.add(4, 5, 4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-4, -5, -4);
		
		blockLocation = location.add(5, 5, 4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-5, -5, -4);
		
		blockLocation = location.add(6, 5, 4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-6, -5, -4);
		
		blockLocation = location.add(7, 5, 4);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-7, -5, -4);
		
		blockLocation = location.add(-7, 5, 5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(7, -5, -5);
		
		blockLocation = location.add(-6, 5, 5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(6, -5, -5);
		
		blockLocation = location.add(-5, 5, 5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(5, -5, -5);
		
		blockLocation = location.add(-4, 5, 5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(4, -5, -5);
		
		blockLocation = location.add(-3, 5, 5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(3, -5, -5);
		
		blockLocation = location.add(-2, 5, 5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(2, -5, -5);
		
		blockLocation = location.add(-1, 5, 5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(1, -5, -5);
		
		blockLocation = location.add(0, 5, 5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(0, -5, -5);
		
		blockLocation = location.add(1, 5, 5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-1, -5, -5);
		
		blockLocation = location.add(2, 5, 5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-2, -5, -5);
		
		blockLocation = location.add(3, 5, 5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-3, -5, -5);
		
		blockLocation = location.add(4, 5, 5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-4, -5, -5);
		
		blockLocation = location.add(5, 5, 5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-5, -5, -5);
		
		blockLocation = location.add(6, 5, 5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-6, -5, -5);
		
		blockLocation = location.add(7, 5, 5);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-7, -5, -5);
		
		blockLocation = location.add(-6, 5, 6);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(6, -5, -6);
		
		blockLocation = location.add(-5, 5, 6);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(5, -5, -6);
		
		blockLocation = location.add(-4, 5, 6);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(4, -5, -6);
		
		blockLocation = location.add(-3, 5, 6);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(3, -5, -6);
		
		blockLocation = location.add(-2, 5, 6);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(2, -5, -6);
		
		blockLocation = location.add(-1, 5, 6);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(1, -5, -6);
		
		blockLocation = location.add(0, 5, 6);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(0, -5, -6);
		
		blockLocation = location.add(1, 5, 6);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-1, -5, -6);
		
		blockLocation = location.add(2, 5, 6);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-2, -5, -6);
		
		blockLocation = location.add(3, 5, 6);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-3, -5, -6);
		
		blockLocation = location.add(4, 5, 6);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-4, -5, -6);
		
		blockLocation = location.add(5, 5, 6);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-5, -5, -6);
		
		blockLocation = location.add(6, 5, 6);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-6, -5, -6);
		
		blockLocation = location.add(-5, 5, 7);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(5, -5, -7);
		
		blockLocation = location.add(-4, 5, 7);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(4, -5, -7);
		
		blockLocation = location.add(-3, 5, 7);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(3, -5, -7);
		
		blockLocation = location.add(-2, 5, 7);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(2, -5, -7);
		
		blockLocation = location.add(-1, 5, 7);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(1, -5, -7);
		
		blockLocation = location.add(0, 5, 7);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(0, -5, -7);
		
		blockLocation = location.add(1, 5, 7);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-1, -5, -7);
		
		blockLocation = location.add(2, 5, 7);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-2, -5, -7);
		
		blockLocation = location.add(3, 5, 7);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-3, -5, -7);
		
		blockLocation = location.add(4, 5, 7);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-4, -5, -7);
		
		blockLocation = location.add(5, 5, 7);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-5, -5, -7);
		
		blockLocation = location.add(-3, 5, 8);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(3, -5, -8);
		
		blockLocation = location.add(-2, 5, 8);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(2, -5, -8);
		
		blockLocation = location.add(-1, 5, 8);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(1, -5, -8);
		
		blockLocation = location.add(0, 5, 8);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(0, -5, -8);
		
		blockLocation = location.add(1, 5, 8);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-1, -5, -8);
		
		blockLocation = location.add(2, 5, 8);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-2, -5, -8);
		
		blockLocation = location.add(3, 5, 8);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-3, -5, -8);
		
		blockLocation = location.add(-1, 5, 9);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(1, -5, -9);
		
		blockLocation = location.add(0, 5, 9);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(0, -5, -9);
		
		blockLocation = location.add(1, 5, 9);
		pattern.put(getLocationString(blockLocation), "AIR,0");
		blockLocation = location.add(-1, -5, -9);

		
		return pattern;
	}
	
	private Map<String, String> buildSecondPattern(Location location) {
		Location blockLocation = null;
		Map<String, String> pattern = new HashMap<String, String>();
		
		blockLocation = location.add(5, 0, 0);
		pattern.put(getLocationString(blockLocation), "FIRE,15");
		blockLocation = location.add(-5, 0, 0);
		
		blockLocation = location.add(-4, 0, 0);
		pattern.put(getLocationString(blockLocation), "FENCE_GATE,1");
		blockLocation = location.add(4, 0, 0);
		
		blockLocation = location.add(0, 0, 4);
		pattern.put(getLocationString(blockLocation), "FENCE_GATE,0");
		blockLocation = location.add(0, 0, -4);
		
		blockLocation = location.add(2, 3, -5);
		pattern.put(getLocationString(blockLocation), "LADDER,4");
		blockLocation = location.add(-2, -3, 5);
		
		blockLocation = location.add(-2, 3, -5);
		pattern.put(getLocationString(blockLocation), "LADDER,5");
		blockLocation = location.add(2, -3, 5);
		
		blockLocation = location.add(-2, 2, -5);
		pattern.put(getLocationString(blockLocation), "LADDER,5");
		blockLocation = location.add(2, -2, 5);
		
		blockLocation = location.add(2, 1, -5);
		pattern.put(getLocationString(blockLocation), "LADDER,4");
		blockLocation = location.add(-2, -1, 5);
		
		blockLocation = location.add(-2, 1, -5);
		pattern.put(getLocationString(blockLocation), "LADDER,5");
		blockLocation = location.add(2, -1, 5);
		
		blockLocation = location.add(7, 0, -2);
		pattern.put(getLocationString(blockLocation), "LADDER,4");
		blockLocation = location.add(-7, 0, 2);
		
		blockLocation = location.add(2, 0, -5);
		pattern.put(getLocationString(blockLocation), "LADDER,4");
		blockLocation = location.add(-2, 0, 5);
		
		blockLocation = location.add(-2, 0, -5);
		pattern.put(getLocationString(blockLocation), "LADDER,5");
		blockLocation = location.add(2, 0, 5);
		
		blockLocation = location.add(2, 2, -5);
		pattern.put(getLocationString(blockLocation), "LADDER,4");
		blockLocation = location.add(-2, -2, 5);
		
		blockLocation = location.add(7, 1, -2);
		pattern.put(getLocationString(blockLocation), "LADDER,4");
		blockLocation = location.add(-7, -1, 2);
		
		blockLocation = location.add(5, 3, 7);
		pattern.put(getLocationString(blockLocation), "TORCH,5");
		blockLocation = location.add(-5, -3, -7);
		
		blockLocation = location.add(-5, 3, 7);
		pattern.put(getLocationString(blockLocation), "TORCH,5");
		blockLocation = location.add(5, -3, -7);
		
		blockLocation = location.add(-2, 1, -9);
		pattern.put(getLocationString(blockLocation), "TORCH,4");
		blockLocation = location.add(2, -1, 9);
		
		blockLocation = location.add(2, 1, -9);
		pattern.put(getLocationString(blockLocation), "TORCH,4");
		blockLocation = location.add(-2, -1, 9);
		
		blockLocation = location.add(-3, 1, -4);
		pattern.put(getLocationString(blockLocation), "TORCH,3");
		blockLocation = location.add(3, -1, 4);
		
		blockLocation = location.add(3, 1, -4);
		pattern.put(getLocationString(blockLocation), "TORCH,3");
		blockLocation = location.add(-3, -1, 4);
		
		blockLocation = location.add(-3, 1, -1);
		pattern.put(getLocationString(blockLocation), "TORCH,1");
		blockLocation = location.add(3, -1, 1);
		
		blockLocation = location.add(-3, 1, 1);
		pattern.put(getLocationString(blockLocation), "TORCH,1");
		blockLocation = location.add(3, -1, -1);
		
		blockLocation = location.add(-1, 1, 3);
		pattern.put(getLocationString(blockLocation), "TORCH,4");
		blockLocation = location.add(1, -1, -3);
		
		blockLocation = location.add(1, 1, 3);
		pattern.put(getLocationString(blockLocation), "TORCH,4");
		blockLocation = location.add(-1, -1, -3);
		
		blockLocation = location.add(-5, 3, -7);
		pattern.put(getLocationString(blockLocation), "TORCH,5");
		blockLocation = location.add(5, -3, 7);
		
		blockLocation = location.add(5, 3, -7);
		pattern.put(getLocationString(blockLocation), "TORCH,5");
		blockLocation = location.add(-5, -3, 7);
		
		blockLocation = location.add(-7, 3, -5);
		pattern.put(getLocationString(blockLocation), "TORCH,5");
		blockLocation = location.add(7, -3, 5);
		
		blockLocation = location.add(7, 3, -5);
		pattern.put(getLocationString(blockLocation), "TORCH,5");
		blockLocation = location.add(-7, -3, 5);
		
		blockLocation = location.add(-9, 3, -1);
		pattern.put(getLocationString(blockLocation), "TORCH,5");
		blockLocation = location.add(9, -3, 1);
		
		blockLocation = location.add(9, 3, -1);
		pattern.put(getLocationString(blockLocation), "TORCH,5");
		blockLocation = location.add(-9, -3, 1);
		
		blockLocation = location.add(-9, 3, 1);
		pattern.put(getLocationString(blockLocation), "TORCH,5");
		blockLocation = location.add(9, -3, -1);
		
		blockLocation = location.add(9, 3, 1);
		pattern.put(getLocationString(blockLocation), "TORCH,5");
		blockLocation = location.add(-9, -3, -1);
		
		blockLocation = location.add(-7, 3, 5);
		pattern.put(getLocationString(blockLocation), "TORCH,5");
		blockLocation = location.add(7, -3, -5);
		
		blockLocation = location.add(7, 3, 5);
		pattern.put(getLocationString(blockLocation), "TORCH,5");
		blockLocation = location.add(-7, -3, -5);
		
		blockLocation = location.add(-1, 3, 9);
		pattern.put(getLocationString(blockLocation), "TORCH,5");
		blockLocation = location.add(1, -3, -9);
		
		blockLocation = location.add(1, 3, 9);
		pattern.put(getLocationString(blockLocation), "TORCH,5");
		blockLocation = location.add(-1, -3, -9);
		
		blockLocation = location.add(-3, 6, -8);
		pattern.put(getLocationString(blockLocation), "TORCH,5");
		blockLocation = location.add(3, -6, 8);
		
		blockLocation = location.add(3, 6, -8);
		pattern.put(getLocationString(blockLocation), "TORCH,5");
		blockLocation = location.add(-3, -6, 8);
		
		blockLocation = location.add(-3, 6, -5);
		pattern.put(getLocationString(blockLocation), "TORCH,5");
		blockLocation = location.add(3, -6, 5);
		
		blockLocation = location.add(3, 6, -5);
		pattern.put(getLocationString(blockLocation), "TORCH,5");
		blockLocation = location.add(-3, -6, 5);

		
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
		Map<String, String> itemBlockList = new HashMap<String, String>();
				
		blockList = buildPattern(location);
		itemBlockList = buildSecondPattern(location);
		if(checkPlace(blockList) == true) {
			player.sendMessage("Gehe ein Stück zur Seite, das Zelt wird gleich errichtet.");
			plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new buildSmallTent(blockList), 5 * 20L);
			plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new buildSmallTentItems(itemBlockList), 10 * 20L);
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
	
	public class buildSmallTentItems implements Runnable {
		
		private Map<String, String> blockCache = new HashMap<String, String>();
		private Map<String, String> buildList;
		
		public buildSmallTentItems(Map<String, String> blockList) {
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
			
			plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new resetSmallTent(blockCache), 275 * 20L);
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