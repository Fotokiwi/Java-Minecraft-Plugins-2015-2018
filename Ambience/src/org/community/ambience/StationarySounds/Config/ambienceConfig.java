package org.community.ambience.StationarySounds.Config;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.community.ambience.Ambience;

public class ambienceConfig {

	private final Ambience plugin;

	public ambienceConfig(Ambience plugin)
	{
		this.plugin = plugin;
		initiateConfig();
	}

	public void reloadConfig() {
		if (plugin.soundsFile == null) {
			plugin.soundsFile = new File(plugin.getDataFolder(), "configs/sounds.yml");
		}
		plugin.sounds = YamlConfiguration.loadConfiguration(plugin.soundsFile);

	}

	public FileConfiguration getConfig() {
		if (plugin.sounds == null) {
			reloadConfig();
		}
		return plugin.sounds;
	}

	public void saveConfig() {
		if (plugin.sounds == null || plugin.soundsFile == null) {
			return;
		}
		try {
			plugin.sounds.save(plugin.soundsFile);
		} catch (IOException ex) {
			Logger.getLogger(JavaPlugin.class.getName()).log(Level.SEVERE, "Could not save config to " + plugin.soundsFile, ex);
		}
	}

	public boolean initiateConfig() {

		reloadConfig();
		getConfig();

		if(plugin.sounds.getString("Config.HeartBeatInSeconds") == null)
			plugin.sounds.set("Config.HeartBeatInSeconds", 10);

		saveConfig();

		return true;

	}

}
