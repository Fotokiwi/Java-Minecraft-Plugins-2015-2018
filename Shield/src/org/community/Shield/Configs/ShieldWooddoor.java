package org.community.Shield.Configs;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;



import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.community.Shield.Shield;


public class ShieldWooddoor{

	private final Shield plugin;

	public ShieldWooddoor(Shield plugin)
	{
		this.plugin = plugin;
	}

	public void reloadwooddoor() {
		if (plugin.wooddoorFile == null) {
			plugin.wooddoorFile = new File(plugin.getDataFolder(), "/database/wooddoor.yml");
		}
		plugin.wooddoor = YamlConfiguration.loadConfiguration(plugin.wooddoorFile);
		savewooddoor();

	}

	public FileConfiguration getwooddoor() {
		if (plugin.wooddoor == null) {
			reloadwooddoor();
		}
		return plugin.wooddoor;
	}

	public void savewooddoor() {
		if (plugin.wooddoor == null || plugin.wooddoorFile == null) {
			return;
		}
		try {
			plugin.wooddoor.save(plugin.wooddoorFile);
		} catch (IOException ex) {
			Logger.getLogger(JavaPlugin.class.getName()).log(Level.SEVERE, "Could not save wooddoor to " + plugin.wooddoorFile, ex);
		}
	}
	/** 
	 * Initiator fuer den Start.
	 * @return boolean true
	 */	
	public boolean initiatewooddoor() {
		reloadwooddoor();
		getwooddoor();
		return true;
	}



}