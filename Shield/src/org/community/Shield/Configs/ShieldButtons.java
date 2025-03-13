package org.community.Shield.Configs;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.community.Shield.Shield;


public class ShieldButtons{

	private final Shield plugin;

	public ShieldButtons(Shield plugin)
	{
		this.plugin = plugin;
	}

	public void reloadbuttons() {
		if (plugin.buttonsFile == null) {
			plugin.buttonsFile = new File(plugin.getDataFolder(), "/database/buttons.yml");
		}
		plugin.buttons = YamlConfiguration.loadConfiguration(plugin.buttonsFile);
		savebuttons();

	}

	public FileConfiguration getbuttons() {
		if (plugin.buttons == null) {
			reloadbuttons();
		}
		return plugin.buttons;
	}

	public void savebuttons() {
		if (plugin.buttons == null || plugin.buttonsFile == null) {
			return;
		}
		try {
			plugin.buttons.save(plugin.buttonsFile);
		} catch (IOException ex) {
			Logger.getLogger(JavaPlugin.class.getName()).log(Level.SEVERE, "Could not save buttons to " + plugin.buttonsFile, ex);
		}
	}
	/** 
	 * Initiator fuer den Start.
	 * @return boolean true
	 */	
	public boolean initiatebuttons() {
		reloadbuttons();
		getbuttons();
		return true;
	}



}