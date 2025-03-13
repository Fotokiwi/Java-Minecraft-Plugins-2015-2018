package org.community.Shield.Configs;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;



import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.community.Shield.Shield;


public class ShieldTrapdoor{

	private final Shield plugin;

	public ShieldTrapdoor(Shield plugin)
	{
		this.plugin = plugin;
	}

	public void reloadtrapdoor() {
		if (plugin.trapdoorFile == null) {
			plugin.trapdoorFile = new File(plugin.getDataFolder(), "/database/trapdoor.yml");
		}
		plugin.trapdoor = YamlConfiguration.loadConfiguration(plugin.trapdoorFile);
		savetrapdoor();

	}

	public FileConfiguration gettrapdoor() {
		if (plugin.trapdoor == null) {
			reloadtrapdoor();
		}
		return plugin.trapdoor;
	}

	public void savetrapdoor() {
		if (plugin.trapdoor == null || plugin.trapdoorFile == null) {
			return;
		}
		try {
			plugin.trapdoor.save(plugin.trapdoorFile);
		} catch (IOException ex) {
			Logger.getLogger(JavaPlugin.class.getName()).log(Level.SEVERE, "Could not save trapdoor to " + plugin.trapdoorFile, ex);
		}
	}
	/** 
	 * Initiator fuer den Start.
	 * @return boolean true
	 */	
	public boolean initiatetrapdoor() {
		reloadtrapdoor();
		gettrapdoor();
		return true;
	}



}