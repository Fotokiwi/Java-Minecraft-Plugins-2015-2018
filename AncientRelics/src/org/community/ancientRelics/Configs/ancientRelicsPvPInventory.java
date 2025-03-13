package org.community.ancientRelics.Configs;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.community.ancientRelics.ancientRelics;

public class ancientRelicsPvPInventory {
		
	private final ancientRelics plugin;
	
	public FileConfiguration inventory = null;
    public File inventoryFile = null;
	 	 
	public ancientRelicsPvPInventory(ancientRelics plugin)
	{
		this.plugin = plugin;
	}
	
	public void reloadInventoryConfig() {
	    if (inventoryFile == null) {
	    	inventoryFile = new File(plugin.getDataFolder(), "pvp/inventory.yml");
	    }
	    inventory = YamlConfiguration.loadConfiguration(inventoryFile);

	}

	public FileConfiguration getInventoryConfig() {
	    if (inventory == null) {
	        reloadInventoryConfig();
	    }
	    return inventory;
	}
	
	public void saveInventoryConfig() {
	    if (inventory == null || inventoryFile == null) {
	    return;
	    }
	    try {
	    	inventory.save(inventoryFile);
	    } catch (IOException ex) {
	        Logger.getLogger(JavaPlugin.class.getName()).log(Level.SEVERE, "Could not save config to " + inventoryFile, ex);
	    }
	}
	
	public boolean initiateConfig() {
		
		reloadInventoryConfig();
		getInventoryConfig();
		
		if(inventory.getString("Credit.Plugin-Version") == null)
			inventory.set("Credit.Plugin-Version", plugin.logprefix);
		
		if(inventory.getString("Example.User") == null)
			inventory.set("Example.User", "9dz87dh9h2h9udzf8hf82zfh8hf9hfa9z93hr98hf89a73hrf");
		
		saveInventoryConfig();
		
		return true;
		
	}
	
}