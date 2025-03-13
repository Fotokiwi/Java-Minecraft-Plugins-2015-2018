package org.community.Shield.Configs;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.community.Shield.Shield;


public class ShieldPlates{

	private final Shield plugin;

	public ShieldPlates(Shield plugin)
	{
		this.plugin = plugin;
	}

	public void reloadplates() {
		if (plugin.platesFile == null) {
			plugin.platesFile = new File(plugin.getDataFolder(), "/database/plates.yml");
		}
		plugin.plates = YamlConfiguration.loadConfiguration(plugin.platesFile);
		saveplates();

	}

	public FileConfiguration getplates() {
		if (plugin.plates == null) {
			reloadplates();
		}
		return plugin.plates;
	}

	public void saveplates() {
		if (plugin.plates == null || plugin.platesFile == null) {
			return;
		}
		try {
			plugin.plates.save(plugin.platesFile);
		} catch (IOException ex) {
			Logger.getLogger(JavaPlugin.class.getName()).log(Level.SEVERE, "Could not save plates to " + plugin.platesFile, ex);
		}
	}
	/** 
	 * Initiator fuer den Start.
	 * @return boolean true
	 */	
	public boolean initiateplates() {
		reloadplates();
		getplates();
		return true;
	}



}