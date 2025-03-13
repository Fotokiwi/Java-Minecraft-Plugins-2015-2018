package org.community.Shield.Configs;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.community.Shield.Shield;


public class ShieldConfig{

	private final Shield plugin;

	public ShieldConfig(Shield plugin)
	{
		this.plugin = plugin;
	}

	public void reloadConfig() {
		if (plugin.configFile == null) {
			plugin.configFile = new File(plugin.getDataFolder(), "config.yml");
			plugin.saveDefaultConfig();
		}
		plugin.config = YamlConfiguration.loadConfiguration(plugin.configFile);
		saveConfig();

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
	/** 
	 * Initiator fuer den Start.
	 * @return boolean true
	 */	
	public boolean initiateConfig() {
		reloadConfig();
		getConfig();
		return true;
	}



}