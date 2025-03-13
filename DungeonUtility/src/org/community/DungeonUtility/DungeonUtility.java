package org.community.DungeonUtility;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.community.DungeonUtility.BlockMode.*;
import org.community.DungeonUtility.EndlessDispenser.*;
import org.community.DungeonUtility.SpawnLimiter.*;
import org.community.Shield.Shield;


public class DungeonUtility extends JavaPlugin {
	
	public final Logger log = Logger.getLogger("Minecraft");
	public final String logprefix = "[DungeonUtility 3.0.0]";

	public FileConfiguration config = null;
    public File configFile = null;

	public FileConfiguration set = null;
    public File setFile = null;

	public FileConfiguration dispenser = null;
    public File dispenserFile = null;
    
    blockModeCommand dUBlockModeCommand = null;
    endlessDispenserCommand dUEndlessDispenserCommand = null;
    spawnLimiterCommand dUSpawnLimiterCommand = null;
    
    public Shield shieldAPI = null;
    
    public long timestamp = 0;
    public int activePlayers = 0;
    public int radius = 0;
    public int activeChunks = 0;

    public Map<Player, String> blockmodeSet = new HashMap<Player, String>();
    public Map<Block, Boolean> dispenserMode = new HashMap<Block, Boolean>();
	
	
	public void LogInfo(String Message) {
		
		log.info(logprefix + " " + Message);
		
	}
	
	public void LogError(String Message) {
		
		log.log(Level.SEVERE, logprefix + " " + Message);
		
	}
	
	public void LogWarning(String Message) {
		
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
	
	public void reloadSet() {
	    if (setFile == null) {
	    setFile = new File(getDataFolder(), "set.yml");
	    }
	    set = YamlConfiguration.loadConfiguration(setFile);

	}

	public FileConfiguration getSet() {
	    if (set == null) {
	        reloadSet();
	    }
	    return set;
	}
	
	public void saveSet() {
	    if (set == null || setFile == null) {
	    return;
	    }
	    try {
	        set.save(setFile);
	    } catch (IOException ex) {
	        Logger.getLogger(JavaPlugin.class.getName()).log(Level.SEVERE, "Could not save set to " + setFile, ex);
	    }
	}
	
	public void reloadDispenser() {
	    if (dispenserFile == null) {
	    dispenserFile = new File(getDataFolder(), "dispenser.yml");
	    }
	    dispenser = YamlConfiguration.loadConfiguration(dispenserFile);

	}

	public FileConfiguration getDispenser() {
	    if (dispenser == null) {
	        reloadDispenser();
	    }
	    return dispenser;
	}
	
	public void saveDispenser() {
	    if (dispenser == null || dispenserFile == null) {
	    return;
	    }
	    try {
	        dispenser.save(dispenserFile);
	    } catch (IOException ex) {
	        Logger.getLogger(JavaPlugin.class.getName()).log(Level.SEVERE, "Could not save dispenser to " + dispenserFile, ex);
	    }
	}
	
	private Boolean loadConfig()
	{
		reloadConfig();
		getConfig();
		
		if(config.getList("Admins") == null){
        	ArrayList<String> admins = new ArrayList<String>();
        	admins.add("a7acba2c-a937-47ce-8b82-96435bfbd6af");
        	admins.add("6de3d461-7ffb-4edd-a509-131f0dc42e89");
			config.set("Admins", admins);
		}
		
		if(config.getString("Config.Mode") == null)
			config.set("Config.Mode", "player");

		if(config.getString("Config.ItemMax.Radius") == null)
			config.set("Config.ItemMax.Radius", 32);
		if(config.getString("Config.ItemMax.Egg") == null)
			config.set("Config.ItemMax.Egg", 16);

		if(config.getString("Config.PlayerBased.Minimum") == null)
			config.set("Config.PlayerBased.Minimum", 5);
		if(config.getString("Config.PlayerBased.Maximum") == null)
			config.set("Config.PlayerBased.Maximum", 30);
		if(config.getString("Config.PlayerBased.Offset") == null)
			config.set("Config.PlayerBased.Offset", 5);
		if(config.getString("Config.PlayerBased.Settings.Water") == null)
			config.set("Config.PlayerBased.Settings.Water", 80);
		if(config.getString("Config.PlayerBased.Settings.Tamable") == null)
			config.set("Config.PlayerBased.Settings.Tamable", 80);

		if(config.getString("Config.ChunkBased.2500") == null)
			config.set("Config.ChunkBased.2500", 10);
		if(config.getString("Config.ChunkBased.3000") == null)
			config.set("Config.ChunkBased.3000", 15);
		if(config.getString("Config.ChunkBased.3500") == null)
			config.set("Config.ChunkBased.3500", 20);
		if(config.getString("Config.ChunkBased.4000") == null)
			config.set("Config.ChunkBased.4000", 25);

		if(config.getString("Config.Fix") == null)
			config.set("Config.Fix", 20);
		
		saveConfig();
		
		reloadSet();
		getSet();
		
		//
		
		saveSet();
		
		reloadDispenser();
		getDispenser();
		
		ConfigurationSection dispenserWorlds = dispenser.getConfigurationSection("Dispenser");
    	if(dispenserWorlds == null){
    		
    	} else {
    		Set<String> dispenserKeys = dispenserWorlds.getKeys(false);
	    	String[] dispenserWorldsArray = dispenserKeys.toArray(new String[0]); 
	    	for(int i = 0; i < dispenserKeys.size(); i++) {
	    		ConfigurationSection dispenserBlocks = dispenser.getConfigurationSection("Dispenser." + dispenserWorldsArray[i]);
	        	if(dispenserBlocks == null){
	        		
	        	} else {
	        		Set<String> dispenserBlockKeys = dispenserBlocks.getKeys(false);
	    	    	String[] dispenserBlocksArray = dispenserBlockKeys.toArray(new String[0]); 
	    	    	for(int m = 0; m < dispenserBlockKeys.size(); m++) {
	    	    		Block block = getServer().getWorld(dispenserWorldsArray[i]).getBlockAt(dispenser.getInt("Dispenser." + dispenserWorldsArray[i] + "." + dispenserBlocksArray[m] + ".X"), dispenser.getInt("Dispenser." + dispenserWorldsArray[i] + "." + dispenserBlocksArray[m] + ".Y"), dispenser.getInt("Dispenser." + dispenserWorldsArray[i] + "." + dispenserBlocksArray[m] + ".Z"));
	    	    		if (block.getType() != Material.DISPENSER) {
	    	    			dispenser.set("Dispenser." + dispenserWorldsArray[i] + "." + dispenserBlocksArray[m] + ".X", null);
	    	    			dispenser.set("Dispenser." + dispenserWorldsArray[i] + "." + dispenserBlocksArray[m] + ".Y", null);
	    	    			dispenser.set("Dispenser." + dispenserWorldsArray[i] + "." + dispenserBlocksArray[m] + ".Z", null);
	    	    			dispenser.set("Dispenser." + dispenserWorldsArray[i] + "." + dispenserBlocksArray[m], null);
	    	    		} else {
	    	    			dispenserMode.put(block, true);
	    	    		}
	    			}
	        	}
			}
    	}	    	
		
		saveDispenser();
		
		return true;
	}
	
	public void onEnable() {	

		loadConfig();
		
		dUBlockModeCommand = new blockModeCommand(this);
		dUEndlessDispenserCommand = new endlessDispenserCommand(this);
		dUSpawnLimiterCommand = new spawnLimiterCommand(this);
		
		shieldAPI = (Shield) Bukkit.getServer().getPluginManager().getPlugin("Shield");
		
		getServer().getPluginManager().registerEvents(new blockModeListenerBlockBreakEvent(this), this);
		LogInfo("initialized: BlockBreakEvent");
		getServer().getPluginManager().registerEvents(new blockModeListenerPlayerInteractEvent(this), this);
		LogInfo("initialized: PlayerInteractEvent");
		getServer().getPluginManager().registerEvents(new endlessDispenserListenerOnDispenseEvent(this), this);
		LogInfo("initialized: OnDispenseEvent");
		getServer().getPluginManager().registerEvents(new spawnLimiterListenerCreatureSpawnEvent(this), this);
		LogInfo("initialized: CreatureSpawnEvent");
		getServer().getPluginManager().registerEvents(new spawnLimiterListenerItemSpawnEvent(this), this);
		LogInfo("initialized: ItemSpawnEvent");
		
	}	
	
	public void onDisable() {
		
		saveConfig();
		saveSet();
		saveDispenser();
		
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		
		if(cmd.getName().equalsIgnoreCase("blockmode")){
			dUBlockModeCommand.getCommand(sender, cmd, commandLabel, args);
			return true;
		}
		if(cmd.getName().equalsIgnoreCase("edispenser")){
			dUEndlessDispenserCommand.getCommand(sender, cmd, commandLabel, args);
			return true;
		}
		if(cmd.getName().equalsIgnoreCase("spawnlimiter")){
			dUSpawnLimiterCommand.getCommand(sender, cmd, commandLabel, args);
			return true;
		}

		
		return true;
	}
}