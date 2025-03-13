package org.community.fourWays.Configs;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.community.fourWays.fourWays;

public class fourWaysAbilities {
		
	private final fourWays plugin;
	 	 
	public fourWaysAbilities(fourWays plugin)
	{
		this.plugin = plugin;
	}
	
	public void reloadConfig() {
	    if (plugin.abilitiesFile == null) {
	    	plugin.abilitiesFile = new File(plugin.getDataFolder(), "configs/abilities.yml");
	    }
	    plugin.abilities = YamlConfiguration.loadConfiguration(plugin.abilitiesFile);

	}

	public FileConfiguration getConfig() {
	    if (plugin.abilities == null) {
	        reloadConfig();
	    }
	    return plugin.abilities;
	}
	
	public void saveConfig() {
	    if (plugin.abilities == null || plugin.abilitiesFile == null) {
	    return;
	    }
	    try {
	    	plugin.abilities.save(plugin.abilitiesFile);
	    } catch (IOException ex) {
	        Logger.getLogger(JavaPlugin.class.getName()).log(Level.SEVERE, "Could not save config to " + plugin.abilitiesFile, ex);
	    }
	}
	
	public boolean initiateConfig() {
		
		reloadConfig();
		getConfig();
		
		if(plugin.abilities.getString("Credit.Version") == null)
        	plugin.abilities.set("Credit.Version", "v0.0.1");
		plugin.logprefix = "Four Ways " + plugin.abilities.getString("Credit.Version");
		
		saveConfig();
		
		return true;
		
	}
	
}