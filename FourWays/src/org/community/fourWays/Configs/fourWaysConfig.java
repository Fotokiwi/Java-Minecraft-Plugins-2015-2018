package org.community.fourWays.Configs;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.community.fourWays.fourWays;

public class fourWaysConfig {
		
	private final fourWays plugin;
	 	 
	public fourWaysConfig(fourWays plugin)
	{
		this.plugin = plugin;
	}
	
	public void reloadConfig() {
	    if (plugin.configFile == null) {
	    	plugin.configFile = new File(plugin.getDataFolder(), "configs/config.yml");
	    }
	    plugin.config = YamlConfiguration.loadConfiguration(plugin.configFile);

	}

	public FileConfiguration getConfig() {
	    if (plugin.config == null) {
	        reloadConfig();
	    }
	    return plugin.config;
	}
	
	public void saveConfig() {
	    if (plugin.config == null || plugin.configFile == null) {
	    return;
	    }
	    try {
	    	plugin.config.save(plugin.configFile);
	    } catch (IOException ex) {
	        Logger.getLogger(JavaPlugin.class.getName()).log(Level.SEVERE, "Could not save config to " + plugin.configFile, ex);
	    }
	}
	
	public boolean initiateConfig() {
		
		reloadConfig();
		getConfig();
		
		if(plugin.config.getString("Credit.Version") == null)
        	plugin.config.set("Credit.Version", "v0.0.1");
		plugin.logprefix = "Four Ways " + plugin.config.getString("Credit.Version");
		
		if(plugin.config.getString("Config.Plugin.HeartBeatInMinutes") == null)
        	plugin.config.set("Config.Plugin.HeartBeatInMinutes", 2);
		if(plugin.config.getString("Config.Debug") == null)
        	plugin.config.set("Config.Debug", false);
		if(plugin.config.getString("Config.Levels.LevelBase") == null)
        	plugin.config.set("Config.Levels.LevelBase", 30);
		if(plugin.config.getString("Config.Levels.PlaytimeExpPerTick") == null)
        	plugin.config.set("Config.Levels.PlaytimeExpPerTick", 8);
		if(plugin.config.getString("Config.Levels.ActionExp") == null)
        	plugin.config.set("Config.Levels.ActionExp", 500);
		if(plugin.config.getString("Config.Levels.PlaytimeExp") == null)
        	plugin.config.set("Config.Levels.PlaytimeExp", 250);
		if(plugin.config.getString("Config.Levels.MaxLevel") == null)
        	plugin.config.set("Config.Levels.MaxLevel", 30);
		
		saveConfig();
		
		return true;
		
	}
	
}