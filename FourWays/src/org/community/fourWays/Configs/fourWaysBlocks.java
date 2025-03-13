package org.community.fourWays.Configs;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.community.fourWays.fourWays;

public class fourWaysBlocks {
		
	private final fourWays plugin;
	 	 
	public fourWaysBlocks(fourWays plugin)
	{
		this.plugin = plugin;
	}
	
	public void reloadConfig() {
	    if (plugin.blockFile == null) {
	    	plugin.blockFile = new File(plugin.getDataFolder(), "configs/blocks.yml");
	    }
	    plugin.block = YamlConfiguration.loadConfiguration(plugin.blockFile);

	}

	public FileConfiguration getConfig() {
	    if (plugin.block == null) {
	        reloadConfig();
	    }
	    return plugin.block;
	}
	
	public void saveConfig() {
	    if (plugin.block == null || plugin.blockFile == null) {
	    return;
	    }
	    try {
	    	plugin.block.save(plugin.blockFile);
	    } catch (IOException ex) {
	        Logger.getLogger(JavaPlugin.class.getName()).log(Level.SEVERE, "Could not save block to " + plugin.blockFile, ex);
	    }
	}
	
	public boolean initiateConfig() {
		
		reloadConfig();
		getConfig();
				
		saveConfig();
		
		return true;
		
	}
	
}