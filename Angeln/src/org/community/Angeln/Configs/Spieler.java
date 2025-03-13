package org.community.Angeln.Configs;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.community.Angeln.Angeln;

public class Spieler {
		
	private final Angeln plugin;
	 	 
	public Spieler(Angeln plugin)
	{
		this.plugin = plugin;
	}
	
	public void reloadConfig() {
	    if (plugin.playersFile == null) {
	    	plugin.playersFile = new File(plugin.getDataFolder(), "configs/players.yml");
	    }
	    plugin.players = YamlConfiguration.loadConfiguration(plugin.playersFile);

	}

	public FileConfiguration getConfig() {
	    if (plugin.players == null) {
	        reloadConfig();
	    }
	    return plugin.players;
	}
	
	public void saveConfig() {
	    if (plugin.players == null || plugin.playersFile == null) {
	    return;
	    }
	    try {
	    	plugin.players.save(plugin.playersFile);
	    } catch (IOException ex) {
	        Logger.getLogger(JavaPlugin.class.getName()).log(Level.SEVERE, "Could not save config to " + plugin.playersFile, ex);
	    }
	}
	
	public boolean initiateConfig() {
		
		reloadConfig();
		getConfig();
		
		if(plugin.players.getString("Credit.Version") == null)
        	plugin.players.set("Credit.Version", "v3.0.0");
		plugin.logprefix = "Angeln " + plugin.players.getString("Credit.Version");
		
		saveConfig();
		
		return true;
		
	}
	
}