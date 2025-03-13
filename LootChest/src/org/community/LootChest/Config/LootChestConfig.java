package org.community.LootChest.Config;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.community.LootChest.LootChest;

public class LootChestConfig {
		
	private final LootChest plugin;
	 	 
	private FileConfiguration config = null;
	private File configFile = null;
	
	private List<String> admins; 
	
	public LootChestConfig(LootChest plugin)
	{
		this.plugin = plugin;
		admins = new ArrayList<String>();
	}
	
	public void reloadConfig() {
	    if (configFile == null) {
	    	configFile = new File(plugin.getDataFolder(), "configs/config.yml");
	    }
	    config = YamlConfiguration.loadConfiguration(configFile);

	}

	public FileConfiguration getConfig() {
	    if (config == null) {
	        reloadConfig();
	    }
	    return config;
	}
	
	public void saveConfig() {
	    if (config == null || configFile == null) {
	    return;
	    }
	    try {
	    	config.save(configFile);
	    } catch (IOException ex) {
	        Logger.getLogger(JavaPlugin.class.getName()).log(Level.SEVERE, "Could not save config to " + configFile, ex);
	    }
	}
	
	public List<String> getAdmins(){
		return admins;
	}
	
	public void loadConfig() {
		reloadConfig();
		if(config.getStringList("admins").size() == 0)
		{
			initiateConfig();
		}
		admins = config.getStringList("admins");
	}
	
	public boolean initiateConfig() {
		List<String> admins = new ArrayList<String>();
		admins.add("2a820534-b95e-4dd7-99ad-8eec9fff88e1");
		admins.add("36354e42-3d8e-4203-b2c6-c26f871f79d9");
		admins.add("6de3d461-7ffb-4edd-a509-131f0dc42e89");
		admins.add("8f9cc838-9b78-4b91-a1c5-375c7253b79f");
		admins.add("1f6c32d4-8fc3-4ffb-bdc2-7f723fa772ff");
		admins.add("8aaf5fad-2a4b-411c-86c8-9715fb3e018e");
		admins.add("991af201-888b-40a4-af5d-7d89f86a7e73");
	
		config.set("admins", admins);
		
		
		saveConfig();
		
		return true;
		
	}
	

	
}