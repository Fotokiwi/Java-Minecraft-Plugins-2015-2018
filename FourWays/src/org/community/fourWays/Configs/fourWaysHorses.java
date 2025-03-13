package org.community.fourWays.Configs;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.community.fourWays.fourWays;

public class fourWaysHorses {
		
	private final fourWays plugin;
	 	 
	public fourWaysHorses(fourWays plugin)
	{
		this.plugin = plugin;
	}
	
	public void reloadConfig() {
	    if (plugin.horsesFile == null) {
	    	plugin.horsesFile = new File(plugin.getDataFolder(), "horses/horses.yml");
	    }
	    plugin.horses = YamlConfiguration.loadConfiguration(plugin.horsesFile);

	}

	public FileConfiguration getConfig() {
	    if (plugin.horses == null) {
	        reloadConfig();
	    }
	    return plugin.horses;
	}
	
	public void saveConfig() {
	    if (plugin.horses == null || plugin.horsesFile == null) {
	    return;
	    }
	    try {
	    	plugin.horses.save(plugin.horsesFile);
	    } catch (IOException ex) {
	        Logger.getLogger(JavaPlugin.class.getName()).log(Level.SEVERE, "Could not save entity to " + plugin.horsesFile, ex);
	    }
	}
	
	public boolean initiateConfig() {
		
		reloadConfig();
		getConfig();
				
		saveConfig();
		
		return true;
		
	}
	
}