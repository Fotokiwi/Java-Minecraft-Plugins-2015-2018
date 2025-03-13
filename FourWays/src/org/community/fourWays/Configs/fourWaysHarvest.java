package org.community.fourWays.Configs;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.community.fourWays.fourWays;

public class fourWaysHarvest {
		
	private final fourWays plugin;
	 	 
	public fourWaysHarvest(fourWays plugin)
	{
		this.plugin = plugin;
	}
	
	public void reloadConfig() {
	    if (plugin.harvestFile == null) {
	    	plugin.harvestFile = new File(plugin.getDataFolder(), "configs/harvest.yml");
	    }
	    plugin.harvest = YamlConfiguration.loadConfiguration(plugin.harvestFile);

	}

	public FileConfiguration getConfig() {
	    if (plugin.harvest == null) {
	        reloadConfig();
	    }
	    return plugin.harvest;
	}
	
	public void saveConfig() {
	    if (plugin.harvest == null || plugin.harvestFile == null) {
	    return;
	    }
	    try {
	    	plugin.harvest.save(plugin.harvestFile);
	    } catch (IOException ex) {
	        Logger.getLogger(JavaPlugin.class.getName()).log(Level.SEVERE, "Could not save config to " + plugin.harvestFile, ex);
	    }
	}
	
	public boolean initiateConfig() {
		
		reloadConfig();
		getConfig();
		
		if(plugin.harvest.getString("Credit.Version") == null)
        	plugin.harvest.set("Credit.Version", "v0.0.1");
		
		if (plugin.harvest.getString("Options.Waterharvest") == null)
        	plugin.harvest.set("Options.Waterharvest", false);
        if (plugin.harvest.getString("Options.Physicsharvest") == null)
        	plugin.harvest.set("Options.Physicsharvest", false);
        if (plugin.harvest.getString("Options.SkyViewLevel") == null)
        	plugin.harvest.set("Options.SkyViewLevel", 8);
        if (plugin.harvest.getString("Options.GrowAtDarkness") == null)
        	plugin.harvest.set("Options.GrowAtDarkness", false);
        if (plugin.harvest.getString("Options.Control.Wheat") == null)
        	plugin.harvest.set("Options.Control.Wheat", true);
        if (plugin.harvest.getString("Options.Control.Mushroom") == null)
        	plugin.harvest.set("Options.Control.Mushroom", true);
        if (plugin.harvest.getString("Options.Control.Pumpkin") == null)
        	plugin.harvest.set("Options.Control.Pumpkin", true);
        if (plugin.harvest.getString("Options.Control.Melon") == null)
        	plugin.harvest.set("Options.Control.Melon", true);
        if (plugin.harvest.getString("Options.Control.Cactus") == null)
        	plugin.harvest.set("Options.Control.Cactus", true);
        if (plugin.harvest.getString("Options.Control.Sugarcane") == null)
        	plugin.harvest.set("Options.Control.Sugarcane", true);
        if (plugin.harvest.getString("Options.Control.Netherwart") == null)
        	plugin.harvest.set("Options.Control.Netherwart", true);
        if (plugin.harvest.getString("Options.Control.Cocoa") == null)
        	plugin.harvest.set("Options.Control.Cocoa", true);
        if (plugin.harvest.getString("Options.Control.Beet") == null)
        	plugin.harvest.set("Options.Control.Beet", true);
        if (plugin.harvest.getString("Options.Control.Grapes") == null)
        	plugin.harvest.set("Options.Control.Grapes", true);
        if (plugin.harvest.getString("Options.Control.Carrot") == null)
        	plugin.harvest.set("Options.Control.Grapes", true);
        if (plugin.harvest.getString("Options.Control.Potatoes") == null)
        	plugin.harvest.set("Options.Control.Grapes", true);
        
        if (plugin.harvest.getString("Options.Durability.active") == null)
        	plugin.harvest.set("Options.Durability.active", true);
        if (plugin.harvest.getString("Options.Durability.Damage") == null)
        	plugin.harvest.set("Options.Durability.Damage", 1);
        if (plugin.harvest.getString("Options.Durability.Amount.WOOD_HOE") == null)
        	plugin.harvest.set("Options.Durability.Amount.WOOD_HOE", 66);
        if (plugin.harvest.getString("Options.Durability.Amount.STONE_HOE") == null)
        	plugin.harvest.set("Options.Durability.Amount.STONE_HOE", 132);
        if (plugin.harvest.getString("Options.Durability.Amount.IRON_HOE") == null)
        	plugin.harvest.set("Options.Durability.Amount.IRON_HOE", 251);
        if (plugin.harvest.getString("Options.Durability.Amount.DIAMOND_HOE") == null)
        	plugin.harvest.set("Options.Durability.Amount.DIAMOND_HOE", 1562);
        if (plugin.harvest.getString("Options.Durability.Amount.GOLD_HOE") == null)
        	plugin.harvest.set("Options.Durability.Amount.GOLD_HOE", 33);

        if (plugin.harvest.getString("Harvest.Wheat.Amount.min") == null)
        	plugin.harvest.set("Harvest.Wheat.Amount.min", 1);
        if (plugin.harvest.getString("Harvest.Wheat.Amount.max") == null)
        	plugin.harvest.set("Harvest.Wheat.Amount.max", 1);
        if (plugin.harvest.getString("Harvest.Wheat.Seed.min") == null)
        	plugin.harvest.set("Harvest.Wheat.Seed.min", 1);
        if (plugin.harvest.getString("Harvest.Wheat.Seed.max") == null)
        	plugin.harvest.set("Harvest.Wheat.Seed.max", 3);
        if (plugin.harvest.getString("Harvest.Wheat.Chance.OTHER") == null)
        	plugin.harvest.set("Harvest.Wheat.Chance.OTHER", 10);
        if (plugin.harvest.getString("Harvest.Wheat.Chance.WOOD_HOE") == null)
        	plugin.harvest.set("Harvest.Wheat.Chance.WOOD_HOE", 30); 
        if (plugin.harvest.getString("Harvest.Wheat.Chance.STONE_HOE") == null)
        	plugin.harvest.set("Harvest.Wheat.Chance.STONE_HOE", 50); 
        if (plugin.harvest.getString("Harvest.Wheat.Chance.IRON_HOE") == null)
        	plugin.harvest.set("Harvest.Wheat.Chance.IRON_HOE", 70); 
        if (plugin.harvest.getString("Harvest.Wheat.Chance.DIAMOND_HOE") == null)
        	plugin.harvest.set("Harvest.Wheat.Chance.DIAMOND_HOE", 90); 
        if (plugin.harvest.getString("Harvest.Wheat.Chance.GOLD_HOE") == null)
        	plugin.harvest.set("Harvest.Wheat.Chance.GOLD_HOE", 100);
		
		saveConfig();
		
		plugin.harvestCache.put("BROWN_MUSHROOM", "Mushroom");
		plugin.harvestCache.put("RED_MUSHROOM", "Mushroom");
		plugin.harvestCache.put("CROPS", "Wheat");
		plugin.harvestCache.put("SOIL", "Beet");
		plugin.harvestCache.put("CACTUS", "Cactus");
		plugin.harvestCache.put("SUGAR_CANE_BLOCK", "Sugarcane");
		plugin.harvestCache.put("PUMPKIN", "Pumpkin");
		plugin.harvestCache.put("HUGE_MUSHROOM_1", "GiantMushroom");
		plugin.harvestCache.put("HUGE_MUSHROOM_2", "GiantMushroom");
		plugin.harvestCache.put("MELON", "Melon");
		plugin.harvestCache.put("PUMPKIN_STEM", "Pumpkin");
		plugin.harvestCache.put("MELON_STEM", "Melon");
		plugin.harvestCache.put("VINE", "Grapes");
		plugin.harvestCache.put("NETHER_WARTS", "Netherwart");
		plugin.harvestCache.put("COCOA", "Cocoa");
		plugin.harvestCache.put("CARROT", "Carrot");
		plugin.harvestCache.put("POTATO", "Potatoes");
		
		return true;
		
	}
	
}