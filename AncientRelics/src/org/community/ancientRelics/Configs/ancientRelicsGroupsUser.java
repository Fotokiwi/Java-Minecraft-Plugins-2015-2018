package org.community.ancientRelics.Configs;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.community.ancientRelics.ancientRelics;

public class ancientRelicsGroupsUser {
		
	private final ancientRelics plugin;
	
	public FileConfiguration user = null;
    public File userFile = null;
	 	 
	public ancientRelicsGroupsUser(ancientRelics plugin)
	{
		this.plugin = plugin;
	}
	
	public void reloadUserConfig() {
	    if (userFile == null) {
	    	userFile = new File(plugin.getDataFolder(), "groups/user.yml");
	    }
	    user = YamlConfiguration.loadConfiguration(userFile);

	}

	public FileConfiguration getUserConfig() {
	    if (user == null) {
	        reloadUserConfig();
	    }
	    return user;
	}
	
	public void saveUserConfig() {
	    if (user == null || userFile == null) {
	    return;
	    }
	    try {
	    	user.save(userFile);
	    } catch (IOException ex) {
	        Logger.getLogger(JavaPlugin.class.getName()).log(Level.SEVERE, "Could not save config to " + userFile, ex);
	    }
	}
	
	public boolean initiateConfig() {
		
		reloadUserConfig();
		getUserConfig();
		
		if(user.getString("Credit.Plugin-Version") == null)
			user.set("Credit.Plugin-Version", plugin.logprefix);
		
		if(user.getString("Example.User.Group") == null)
			user.set("Example.User.Group", "Headhunters");
		
		saveUserConfig();
		
		return true;
		
	}
	
}