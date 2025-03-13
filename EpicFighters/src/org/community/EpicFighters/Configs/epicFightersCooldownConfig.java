package org.community.EpicFighters.Configs;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.community.EpicFighters.EpicFighters;

public class epicFightersCooldownConfig {
		
	private final EpicFighters plugin;
	 	 
	public epicFightersCooldownConfig(EpicFighters plugin)
	{
		this.plugin = plugin;
	}
	
	public void reloadConfig() {
	    if (plugin.cooldownFile == null) {
	    	plugin.cooldownFile = new File(plugin.getDataFolder(), "configs/cooldown.yml");
	    }
	    plugin.cooldown = YamlConfiguration.loadConfiguration(plugin.cooldownFile);

	}

	public FileConfiguration getConfig() {
	    if (plugin.cooldown == null) {
	        reloadConfig();
	    }
	    return plugin.cooldown;
	}
	
	public void saveConfig() {
	    if (plugin.cooldown == null || plugin.cooldownFile == null) {
	    return;
	    }
	    try {
	    	plugin.cooldown.save(plugin.cooldownFile);
	    } catch (IOException ex) {
	        Logger.getLogger(JavaPlugin.class.getName()).log(Level.SEVERE, "Could not save config to " + plugin.cooldownFile, ex);
	    }
	}
	
	public boolean initiateConfig() {
		
		reloadConfig();
		getConfig();
		
		if(plugin.config.getString("Credit.Version") == null)
        	plugin.config.set("Credit.Version", "v0.0.1");
		plugin.logprefix = "EpicFighters " + plugin.config.getString("Credit.Version");
		
		saveConfig();
		
		return true;
		
	}
	
}