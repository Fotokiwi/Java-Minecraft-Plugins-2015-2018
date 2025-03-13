package org.community.Shield.Configs;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.community.Shield.Shield;


public class ShieldChest{

	private final Shield plugin;

	public ShieldChest(Shield plugin)
	{
		this.plugin = plugin;
	}

	public void reloadChest() {
		if (plugin.chestFile == null) {
			plugin.chestFile = new File(plugin.getDataFolder(), "/database/chest.yml");
		}
		plugin.chest = YamlConfiguration.loadConfiguration(plugin.chestFile);
		saveChest();

	}

	public FileConfiguration getChest() {
		if (plugin.chest == null) {
			reloadChest();
		}
		return plugin.chest;
	}

	public void saveChest() {
		if (plugin.chest == null || plugin.chestFile == null) {
			return;
		}
		try {
			plugin.chest.save(plugin.chestFile);
		} catch (IOException ex) {
			Logger.getLogger(JavaPlugin.class.getName()).log(Level.SEVERE, "Could not save chest to " + plugin.chestFile, ex);
		}
	}
	/** 
	 * Initiator fuer den Start.
	 * @return boolean true
	 */	
	public boolean initiateChest() {
		reloadChest();
		getChest();
		return true;
	}



}