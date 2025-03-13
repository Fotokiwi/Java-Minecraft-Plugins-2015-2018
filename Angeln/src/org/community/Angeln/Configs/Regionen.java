package org.community.Angeln.Configs;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.community.Angeln.Angeln;

public class Regionen {
		
	private final Angeln plugin;
	 	 
	public Regionen(Angeln plugin)
	{
		this.plugin = plugin;
	}
	
	public void reloadConfig() {
	    if (plugin.biomeFile == null) {
	    	plugin.biomeFile = new File(plugin.getDataFolder(), "configs/biomes.yml");
	    }
	    plugin.biome = YamlConfiguration.loadConfiguration(plugin.biomeFile);

	}

	public FileConfiguration getConfig() {
	    if (plugin.biome == null) {
	        reloadConfig();
	    }
	    return plugin.biome;
	}
	
	public void saveConfig() {
	    if (plugin.biome == null || plugin.biomeFile == null) {
	    return;
	    }
	    try {
	    	plugin.biome.save(plugin.biomeFile);
	    } catch (IOException ex) {
	        Logger.getLogger(JavaPlugin.class.getName()).log(Level.SEVERE, "Could not save config to " + plugin.biomeFile, ex);
	    }
	}
	
	public boolean initiateConfig() {
		
		reloadConfig();
		getConfig();
		
		if(plugin.biome.getString("Credit.Version") == null)
        	plugin.biome.set("Credit.Version", "v3.0.0");
		plugin.logprefix = "Angeln " + plugin.biome.getString("Credit.Version");
		
		saveConfig();
		
		return true;
		
	}
	
}