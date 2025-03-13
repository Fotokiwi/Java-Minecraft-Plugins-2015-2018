package org.community.ambience.Diseases.Config;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.community.ambience.Ambience;

public class ambienceConfig {
		
	private final Ambience plugin;
	 	 
	public ambienceConfig(Ambience plugin)
	{
		this.plugin = plugin;
		initiateConfig();
	}
	
	public void reloadConfig() {
	    if (plugin.diseasesFile == null) {
	    	plugin.diseasesFile = new File(plugin.getDataFolder(), "configs/diseases.yml");
	    }
	    plugin.diseases = YamlConfiguration.loadConfiguration(plugin.diseasesFile);

	}

	public FileConfiguration getConfig() {
	    if (plugin.diseases == null) {
	        reloadConfig();
	    }
	    return plugin.diseases;
	}
	
	public void saveConfig() {
	    if (plugin.diseases == null || plugin.diseasesFile == null) {
	    return;
	    }
	    try {
	    	plugin.diseases.save(plugin.diseasesFile);
	    } catch (IOException ex) {
	        Logger.getLogger(JavaPlugin.class.getName()).log(Level.SEVERE, "Could not save config to " + plugin.diseasesFile, ex);
	    }
	}
	
	public boolean initiateConfig() {
		
		reloadConfig();
		getConfig();
		
		if(plugin.diseases.getString("Config.HeartBeatInSeconds") == null)
        	plugin.diseases.set("Config.HeartBeatInSeconds", 30);
		

        if (plugin.diseases.getString("Config.Biome.plains.Basistemperatur") == null)
        	plugin.diseases.set("Config.Biome.plains.Basistemperatur", 11.0);
        if (plugin.diseases.getString("Config.Biome.plains.Wassertemperatur") == null)
        	plugin.diseases.set("Config.Biome.plains.Wassertemperatur", 12.0);
        if (plugin.diseases.getString("Config.Biome.plains.Schwankung") == null)
        	plugin.diseases.set("Config.Biome.plains.Schwankung", 12.0);
        if (plugin.diseases.getString("Config.Biome.plains.Niederschlag") == null)
        	plugin.diseases.set("Config.Biome.plains.Niederschlag", -6.0);
        if (plugin.diseases.getString("Config.Biome.plains.Sonnenlicht") == null)
        	plugin.diseases.set("Config.Biome.plains.Sonnenlicht", 0.6);
		
		saveConfig();
		
		return true;
		
	}
	
}