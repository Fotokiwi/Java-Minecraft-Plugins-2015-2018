package org.community.EpicFighters.Configs;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.community.EpicFighters.EpicFighters;

public class epicFightersUserConfig {
		
	private final EpicFighters plugin;
	 	 
	public epicFightersUserConfig(EpicFighters plugin)
	{
		this.plugin = plugin;
	}
	
	public void reloadConfig() {
	    if (plugin.userFile == null) {
	    	plugin.userFile = new File(plugin.getDataFolder(), "configs/user.yml");
	    }
	    plugin.user = YamlConfiguration.loadConfiguration(plugin.userFile);

	}

	public FileConfiguration getConfig() {
	    if (plugin.user == null) {
	        reloadConfig();
	    }
	    return plugin.user;
	}
	
	public void saveConfig() {
	    if (plugin.user == null || plugin.userFile == null) {
	    return;
	    }
	    try {
	    	plugin.user.save(plugin.userFile);
	    } catch (IOException ex) {
	        Logger.getLogger(JavaPlugin.class.getName()).log(Level.SEVERE, "Could not save config to " + plugin.userFile, ex);
	    }
	}
	
	public boolean initiateConfig() {
		
		reloadConfig();
		getConfig();
		
		if(plugin.config.getString("Credit.Version") == null)
        	plugin.config.set("Credit.Version", "v0.0.1");
		plugin.logprefix = "NewSettlers " + plugin.config.getString("Credit.Version");
		
		saveConfig();
		
		return true;
		
	}
	
}