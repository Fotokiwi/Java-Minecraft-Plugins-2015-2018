package org.community.Shield.Configs;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;



import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.community.Shield.Shield;


public class ShieldFurnance{

	private final Shield plugin;

	public ShieldFurnance(Shield plugin)
	{
		this.plugin = plugin;
	}

	public void reloadfurnance() {
		if (plugin.furnanceFile == null) {
			plugin.furnanceFile = new File(plugin.getDataFolder(), "/database/furnance.yml");
		}
		plugin.furnance = YamlConfiguration.loadConfiguration(plugin.furnanceFile);
		savefurnance();

	}

	public FileConfiguration getfurnance() {
		if (plugin.furnance == null) {
			reloadfurnance();
		}
		return plugin.furnance;
	}

	public void savefurnance() {
		if (plugin.furnance == null || plugin.furnanceFile == null) {
			return;
		}
		try {
			plugin.furnance.save(plugin.furnanceFile);
		} catch (IOException ex) {
			Logger.getLogger(JavaPlugin.class.getName()).log(Level.SEVERE, "Could not save furnance to " + plugin.furnanceFile, ex);
		}
	}
	/** 
	 * Initiator fuer den Start.
	 * @return boolean true
	 */	
	public boolean initiatefurnance() {
		reloadfurnance();
		getfurnance();
		return true;
	}



}