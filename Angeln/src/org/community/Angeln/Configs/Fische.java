package org.community.Angeln.Configs;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.community.Angeln.Angeln;

public class Fische {
		
	private final Angeln plugin;
	 	 
	public Fische(Angeln plugin)
	{
		this.plugin = plugin;
	}
	
	public void reloadConfig() {
	    if (plugin.fishesFile == null) {
	    	plugin.fishesFile = new File(plugin.getDataFolder(), "configs/fishes.yml");
	    }
	    plugin.fishes = YamlConfiguration.loadConfiguration(plugin.fishesFile);

	}

	public FileConfiguration getConfig() {
	    if (plugin.fishes == null) {
	        reloadConfig();
	    }
	    return plugin.fishes;
	}
	
	public void saveConfig() {
	    if (plugin.fishes == null || plugin.fishesFile == null) {
	    return;
	    }
	    try {
	    	plugin.fishes.save(plugin.fishesFile);
	    } catch (IOException ex) {
	        Logger.getLogger(JavaPlugin.class.getName()).log(Level.SEVERE, "Could not save config to " + plugin.fishesFile, ex);
	    }
	}
	
	public boolean initiateConfig() {
		
		reloadConfig();
		getConfig();
		
		if(plugin.fishes.getString("Credit.Version") == null)
        	plugin.fishes.set("Credit.Version", "v3.0.0");
		plugin.logprefix = "Angeln " + plugin.fishes.getString("Credit.Version");
		
		saveConfig();
		
		return true;
		
	}
	
}