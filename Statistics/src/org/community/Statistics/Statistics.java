package org.community.Statistics;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.community.DatabaseProvider.DatabaseProvider;
import org.community.Statistics.Core.statisticsCore;
import org.community.Statistics.Listener.*;
import org.community.Statistics.Player.statisticsPlayer;

public class Statistics extends JavaPlugin {
	
	public final static Logger log = Logger.getLogger("Minecraft");
	public static final String logprefix = "[Statistics]";
	
	public statisticsCore sCore = null;

	public FileConfiguration config = null;
    public File configFile = null;
	
	public void reloadConfig() {
	    if (configFile == null) {
	    configFile = new File(getDataFolder(), "config.yml");
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
	        DatabaseProvider.log.LogError("Could not save config to " + configFile, ex);
	    }
	}
	
	private Boolean loadConfig()
	{
		reloadConfig();
		getConfig();
		
		config.set("Version", "0.0.1");
		saveConfig();
		
		return true;
	}
	
	public void LogInfo(String Message) {
		
		log.info(logprefix + " " + Message);
		
	}
	
	public static void LogError(String Message) {
		
		log.log(Level.SEVERE, logprefix + " " + Message);
		
	}
	
	public static void LogWarning(String Message) {
		
		log.log(Level.WARNING, logprefix + " " + Message);
		
	}
	
	public void onEnable() {	
		
		loadConfig();

		sCore = new statisticsCore();

		getServer().getPluginManager().registerEvents(new statisticsPlayerJoinEvent(this), this);
		getServer().getPluginManager().registerEvents(new statisticsBlockBreakEvent(this), this);
		getServer().getPluginManager().registerEvents(new statisticsBlockPlaceEvent(this), this);
		getServer().getPluginManager().registerEvents(new statisticsPlayerDeathEvent(this), this);
		
		saveConfig();
		
	}	
	
	public void onDisable() {
		
		saveConfig();
		
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		
		if(cmd.getName().equalsIgnoreCase("stats")){	
			statisticsPlayer sPlayer = sCore.playerHash.get((Player) sender);
			sPlayer.saveStats();
		}
		
		return false;
		
	}

}
