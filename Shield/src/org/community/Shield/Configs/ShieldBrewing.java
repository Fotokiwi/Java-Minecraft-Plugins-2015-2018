package org.community.Shield.Configs;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;



import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.community.Shield.Shield;


public class ShieldBrewing{

	private final Shield plugin;

	public ShieldBrewing(Shield plugin)
	{
		this.plugin = plugin;
	}

	public void reloadbrewing() {
		if (plugin.brewingFile == null) {
			plugin.brewingFile = new File(plugin.getDataFolder(), "/database/brewing.yml");
		}
		plugin.brewing = YamlConfiguration.loadConfiguration(plugin.brewingFile);
		savebrewing();

	}

	public FileConfiguration getbrewing() {
		if (plugin.brewing == null) {
			reloadbrewing();
		}
		return plugin.brewing;
	}

	public void savebrewing() {
		if (plugin.brewing == null || plugin.brewingFile == null) {
			return;
		}
		try {
			plugin.brewing.save(plugin.brewingFile);
		} catch (IOException ex) {
			Logger.getLogger(JavaPlugin.class.getName()).log(Level.SEVERE, "Could not save brewing to " + plugin.brewingFile, ex);
		}
	}
	/** 
	 * Initiator fuer den Start.
	 * @return boolean true
	 */	
	public boolean initiatebrewing() {
		reloadbrewing();
		getbrewing();
		return true;
	}



}