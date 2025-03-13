package org.community.CrazyLabs;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.entity.Player;
import org.community.CrazyLabs.Commands.*;
import org.community.CrazyLabs.MazeGeneration.*;


public class CrazyLabs extends JavaPlugin {
	
	public static Logger log = Logger.getLogger("Minecraft");
	public static String logprefix = "[CrazyLabs]";

	public FileConfiguration config = null;
    public File configFile = null;
    
    public Map<CommandSender, Maze> mazeEditor = new HashMap<CommandSender, Maze>();
    
    public MazeCommand mazeCommand = null;
    
	public void LogInfo(String Message) {
		
		log.info(logprefix + " " + Message);
		
	}
	
	public static void LogError(String Message) {
		
		log.log(Level.SEVERE, logprefix + " " + Message);
		
	}
	
	public static void LogWarning(String Message) {
		
		log.log(Level.WARNING, logprefix + " " + Message);
		
	}
	
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
	        Logger.getLogger(JavaPlugin.class.getName()).log(Level.SEVERE, "Could not save config to " + configFile, ex);
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
	
	public void onEnable() {	

		mazeCommand = new MazeCommand(this);
		loadConfig();
		
	}	
	
	public void onDisable() {
		
		saveConfig();
		
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		
		if(cmd.getName().equalsIgnoreCase("maze")){
			mazeCommand.getCommand(sender, cmd, commandLabel, args);
			return true;
		}
		
		if(cmd.getName().equalsIgnoreCase("crazylabs")){	
			if(sender instanceof Player) {
				//Player player = (Player) sender;
				if(args[0].equalsIgnoreCase("generate")) {
					//new GenerateMaze(this, player.getLocation(), Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3]), Integer.parseInt(args[4]), Integer.parseInt(args[5]));
					return true;
				}
			} else {
				sender.sendMessage("Es sind keinen Konsolenbefehle verfügbar");
				return true;
			}
			
		}
		
		return true;
	}
}