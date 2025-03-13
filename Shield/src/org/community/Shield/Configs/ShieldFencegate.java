package org.community.Shield.Configs;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;



import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.community.Shield.Shield;


public class ShieldFencegate{

	private final Shield plugin;

	public ShieldFencegate(Shield plugin)
	{
		this.plugin = plugin;
	}

	public void reloadfencegate() {
		if (plugin.fencegateFile == null) {
			plugin.fencegateFile = new File(plugin.getDataFolder(), "/database/fencegate.yml");
		}
		plugin.fencegate = YamlConfiguration.loadConfiguration(plugin.fencegateFile);
		savefencegate();

	}

	public FileConfiguration getfencegate() {
		if (plugin.fencegate == null) {
			reloadfencegate();
		}
		return plugin.fencegate;
	}

	public void savefencegate() {
		if (plugin.fencegate == null || plugin.fencegateFile == null) {
			return;
		}
		try {
			plugin.fencegate.save(plugin.fencegateFile);
		} catch (IOException ex) {
			Logger.getLogger(JavaPlugin.class.getName()).log(Level.SEVERE, "Could not save fencegate to " + plugin.fencegateFile, ex);
		}
	}
	/** 
	 * Initiator fuer den Start.
	 * @return boolean true
	 */	
	public boolean initiatefencegate() {
		reloadfencegate();
		getfencegate();
		return true;
	}



}