package org.community.fourWays.Configs;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.community.fourWays.fourWays;

public class fourWaysQuestmode {
		
	private final fourWays plugin;
	 	 
	public fourWaysQuestmode(fourWays plugin)
	{
		this.plugin = plugin;
	}
	
	public void reloadConfig() {
	    if (plugin.questmodeFile == null) {
	    	plugin.questmodeFile = new File(plugin.getDataFolder(), "configs/questmode.yml");
	    }
	    plugin.questmode = YamlConfiguration.loadConfiguration(plugin.questmodeFile);

	}

	public FileConfiguration getConfig() {
	    if (plugin.questmode == null) {
	        reloadConfig();
	    }
	    return plugin.questmode;
	}
	
	public void saveConfig() {
	    if (plugin.questmode == null || plugin.questmodeFile == null) {
	    return;
	    }
	    try {
	    	plugin.questmode.save(plugin.questmodeFile);
	    } catch (IOException ex) {
	        Logger.getLogger(JavaPlugin.class.getName()).log(Level.SEVERE, "Could not save questmode to " + plugin.questmodeFile, ex);
	    }
	}
	
	public boolean initiateConfig() {
		
		reloadConfig();
		getConfig();
				
		saveConfig();
		
		return true;
		
	}
	
}