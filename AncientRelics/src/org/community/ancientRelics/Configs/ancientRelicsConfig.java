package org.community.ancientRelics.Configs;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.community.ancientRelics.ancientRelics;

public class ancientRelicsConfig {
		
	private final ancientRelics plugin;
	 	 
	public ancientRelicsConfig(ancientRelics plugin)
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
		plugin.logprefix = "Ancient Relics " + plugin.config.getString("Credit.Version");
		
		if(plugin.config.getString("Config.Plugin.HeartBeatInMinutes") == null)
        	plugin.config.set("Config.Plugin.HeartBeatInMinutes", 2);
		
		if(plugin.config.getString("Config.PvP.CooldownInMinutes") == null)
        	plugin.config.set("Config.PvP.CooldownInMinutes", 360);
		if(plugin.config.getString("Config.PvP.UserSaveInMinutes") == null)
        	plugin.config.set("Config.PvP.UserSaveInMinutes", 2);
		if(plugin.config.getString("Config.PvP.KopfgeldInGulden") == null)
        	plugin.config.set("Config.PvP.KopfgeldInGulden", 50);
		if(plugin.config.getString("Config.PvP.KopfgeldPvPDeaktivierungsLimit") == null)
        	plugin.config.set("Config.PvP.KopfgeldPvPDeaktivierungsLimit", 500);
		if(plugin.config.getString("Config.PvP.PvPTodToleranzInSekunden") == null)
        	plugin.config.set("Config.PvP.PvPTodToleranzInSekunden", 10);
		if(plugin.config.getString("Config.PvP.NonPvPSterbegeld") == null)
        	plugin.config.set("Config.PvP.NonPvPSterbegeld", 50);
		
		if(plugin.config.getString("Config.Groups.GroupPlayerLimit") == null)
        	plugin.config.set("Config.Groups.GroupPlayerLimit", 8);
		
		if(plugin.config.getString("Config.Debug") == null)
        	plugin.config.set("Config.Debug", false);
		
		saveConfig();
		
		return true;
		
	}
	
}