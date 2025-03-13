package org.community.Shield.Configs;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;



import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.community.Shield.Shield;


public class ShieldDispenser{

	private final Shield plugin;

	public ShieldDispenser(Shield plugin)
	{
		this.plugin = plugin;
	}

	public void reloaddispenser() {
		if (plugin.dispenserFile == null) {
			plugin.dispenserFile = new File(plugin.getDataFolder(), "/database/dispenser.yml");
		}
		plugin.dispenser = YamlConfiguration.loadConfiguration(plugin.dispenserFile);
		savedispenser();

	}

	public FileConfiguration getdispenser() {
		if (plugin.dispenser == null) {
			reloaddispenser();
		}
		return plugin.dispenser;
	}

	public void savedispenser() {
		if (plugin.dispenser == null || plugin.dispenserFile == null) {
			return;
		}
		try {
			plugin.dispenser.save(plugin.dispenserFile);
		} catch (IOException ex) {
			Logger.getLogger(JavaPlugin.class.getName()).log(Level.SEVERE, "Could not save dispenser to " + plugin.dispenserFile, ex);
		}
	}
	/** 
	 * Initiator fuer den Start.
	 * @return boolean true
	 */	
	public boolean initiatedispenser() {
		reloaddispenser();
		getdispenser();
		return true;
	}



}