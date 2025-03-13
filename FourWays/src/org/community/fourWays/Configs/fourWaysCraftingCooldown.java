package org.community.fourWays.Configs;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.community.fourWays.fourWays;

public class fourWaysCraftingCooldown {
		
	private final fourWays plugin;
	 	 
	public fourWaysCraftingCooldown(fourWays plugin)
	{
		this.plugin = plugin;
	}
	
	public void reloadConfig() {
	    if (plugin.craftingCooldownFile == null) {
	    	plugin.craftingCooldownFile = new File(plugin.getDataFolder(), "cooldowns/crafting.yml");
	    }
	    plugin.craftingCooldown = YamlConfiguration.loadConfiguration(plugin.craftingCooldownFile);

	}

	public FileConfiguration getConfig() {
	    if (plugin.craftingCooldown == null) {
	        reloadConfig();
	    }
	    return plugin.craftingCooldown;
	}
	
	public void saveConfig() {
	    if (plugin.craftingCooldown == null || plugin.craftingCooldownFile == null) {
	    return;
	    }
	    try {
	    	plugin.craftingCooldown.save(plugin.craftingCooldownFile);
	    } catch (IOException ex) {
	        Logger.getLogger(JavaPlugin.class.getName()).log(Level.SEVERE, "Could not save config to " + plugin.craftingCooldownFile, ex);
	    }
	}
	
	public boolean initiateConfig() {
		
		reloadConfig();
		getConfig();
		
		if(plugin.craftingCooldown.getString("Credit.Version") == null)
        	plugin.craftingCooldown.set("Credit.Version", "v0.0.1");
		plugin.logprefix = "Four Ways " + plugin.craftingCooldown.getString("Credit.Version");
				
		saveConfig();
		
		return true;
		
	}
	
}