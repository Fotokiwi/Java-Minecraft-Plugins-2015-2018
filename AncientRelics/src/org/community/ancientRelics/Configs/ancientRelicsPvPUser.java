package org.community.ancientRelics.Configs;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.community.ancientRelics.ancientRelics;

public class ancientRelicsPvPUser {
		
	private final ancientRelics plugin;
	
	public FileConfiguration user = null;
    public File userFile = null;
	 	 
	public ancientRelicsPvPUser(ancientRelics plugin)
	{
		this.plugin = plugin;
	}
	
	public void reloadUserConfig() {
	    if (userFile == null) {
	    	userFile = new File(plugin.getDataFolder(), "pvp/user.yml");
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
		
		if(user.getString("Example.User.PvP") == null)
			user.set("Example.User.PvP", true);
		if(user.getString("Example.User.Cooldown") == null)
			user.set("Example.User.Cooldown", "1358626815723");
		if(user.getString("Example.User.Bounty") == null)
			user.set("Example.User.Bounty", 0);
		if(user.getString("Example.User.Kills") == null)
			user.set("Example.User.Kills", 7);
		if(user.getString("Example.User.Deaths") == null)
			user.set("Example.User.Deaths", 3);
		if(user.getString("Example.User.Killer") == null)
			user.set("Example.User.Killer", "Orphon");
		if(user.getString("Example.User.Victim") == null)
			user.set("Example.User.Victim", "Lucil");
		
		saveUserConfig();
		
		return true;
		
	}
	
}