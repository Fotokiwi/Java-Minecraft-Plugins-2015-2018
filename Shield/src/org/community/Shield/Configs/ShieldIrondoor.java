package org.community.Shield.Configs;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;



import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.community.Shield.Shield;


public class ShieldIrondoor{

	private final Shield plugin;

	public ShieldIrondoor(Shield plugin)
	{
		this.plugin = plugin;
	}

	public void reloadirondoor() {
		if (plugin.irondoorFile == null) {
			plugin.irondoorFile = new File(plugin.getDataFolder(), "/database/irondoor.yml");
		}
		plugin.irondoor = YamlConfiguration.loadConfiguration(plugin.irondoorFile);
		saveirondoor();

	}

	public FileConfiguration getirondoor() {
		if (plugin.irondoor == null) {
			reloadirondoor();
		}
		return plugin.irondoor;
	}

	public void saveirondoor() {
		if (plugin.irondoor == null || plugin.irondoorFile == null) {
			return;
		}
		try {
			plugin.irondoor.save(plugin.irondoorFile);
		} catch (IOException ex) {
			Logger.getLogger(JavaPlugin.class.getName()).log(Level.SEVERE, "Could not save irondoor to " + plugin.irondoorFile, ex);
		}
	}
	/** 
	 * Initiator fuer den Start.
	 * @return boolean true
	 */	
	public boolean initiateirondoor() {
		reloadirondoor();
		getirondoor();
		return true;
	}



}