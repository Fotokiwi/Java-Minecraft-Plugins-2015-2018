package org.community.fourWays.Configs;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.community.fourWays.fourWays;

public class fourWaysEntities {
		
	private final fourWays plugin;
	 	 
	public fourWaysEntities(fourWays plugin)
	{
		this.plugin = plugin;
	}
	
	public void reloadConfig() {
	    if (plugin.entityFile == null) {
	    	plugin.entityFile = new File(plugin.getDataFolder(), "configs/entitys.yml");
	    }
	    plugin.entity = YamlConfiguration.loadConfiguration(plugin.entityFile);

	}

	public FileConfiguration getConfig() {
	    if (plugin.entity == null) {
	        reloadConfig();
	    }
	    return plugin.entity;
	}
	
	public void saveConfig() {
	    if (plugin.entity == null || plugin.entityFile == null) {
	    return;
	    }
	    try {
	    	plugin.entity.save(plugin.entityFile);
	    } catch (IOException ex) {
	        Logger.getLogger(JavaPlugin.class.getName()).log(Level.SEVERE, "Could not save entity to " + plugin.entityFile, ex);
	    }
	}
	
	public boolean initiateConfig() {
		
		reloadConfig();
		getConfig();
				
		saveConfig();
		
		return true;
		
	}
	
}