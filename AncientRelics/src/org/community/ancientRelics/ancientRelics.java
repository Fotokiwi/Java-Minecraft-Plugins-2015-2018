package org.community.ancientRelics;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.milkbowl.vault.permission.Permission;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.community.ancientRelics.API.ancientRelicsPvpStatus;
import org.community.ancientRelics.Cache.*;
import org.community.ancientRelics.Configs.*;
import org.community.ancientRelics.Core.*;
import org.community.ancientRelics.Commands.*;
import org.community.ancientRelics.Graves.Graves;
import org.community.ancientRelics.Groups.*;
import org.community.ancientRelics.Groups.Classes.Groups;
import org.community.ancientRelics.Help.ancientRelicsHelp;
import org.community.ancientRelics.Listener.*;
import org.community.ancientRelics.PvP.*;
import org.community.ancientRelics.Tasks.*;
import org.community.ancientRelics.Utility.ancientRelicsCommandHandler;
import org.community.newSettlers.newSettlers;

public class ancientRelics extends JavaPlugin {
	
	public Logger log = Logger.getLogger("Minecraft");
	public String logprefix = "[AncientRelics 3.0.0]";
    
    public newSettlers newSettlersAPI = null;
	
	public Permission permission = null;
	
	public FileConfiguration config = null;
    public File configFile = null;
    
    public Map<Player, Player> lastDamagerPlayer = new HashMap<Player, Player>();
    public Map<Player, Long> lastDamagerTimestamp = new HashMap<Player, Long>();
    public Map<Player, Groups> playerGroupMembership = new HashMap<Player, Groups>();
    public Map<Player, String> newRequest = new HashMap<Player, String>();
    public Map<Player, Player> newApproval = new HashMap<Player, Player>();
    public Map<Player, Player> duellModus = new HashMap<Player, Player>();
    
    //API Klasse
    public ancientRelicsPvpStatus aRApiPvpStatus = null;
    //Config Klassen
    public ancientRelicsConfig aRConfig = null;
    //Command Klassen
    public ancientRelicsCommandMain aRCommandMain = null;
    //PvP Klassen
    public ancientRelicsPvP aRPvP = null;
    //Groups Klassen
    public ancientRelicsGroups aRGroups = null;
    
    public ancientRelicsCore aRCore = null;
    //Cache Management
    public ancientRelicsCacheManagement aRCache = null;
    
    public ancientRelicsHeartbeat aRHeartBeat = null;
    
    public ancientRelicsHelp aRHelp = null;
    
    public ancientRelicsCommandHandler aRCommandHandler = null;
    
    public Graves graves = null;
    
    // Scoreboard and Team Manager
    public ScoreboardManager scoreboardManager = null;
    public Scoreboard defaultBoard = null;
    Objective defaultObjective = null;
	
	public void LogInfo(String Message) {
		
		log.info(logprefix + " " + Message);
		
	}
	
	public void LogDebug(String Message) {
		
		if(config.getBoolean("Config.Debug"))
			log.info(logprefix + " [DEBUG]: " + Message);
		
	}
	
	public void LogError(String Message) {
		
		log.log(Level.SEVERE, logprefix + " " + Message);
		
	}
	
	public void LogWarning(String Message) {
		
		log.log(Level.WARNING, logprefix + " " + Message);
		
	}
	
	private Boolean setupPermissions()
    {
        RegisteredServiceProvider<Permission> permissionProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.permission.Permission.class);
        if (permissionProvider != null) {
            permission = permissionProvider.getProvider();
            LogInfo("initialized: Vault permissions");
        }
        return (permission != null);
    }
	
	public void onEnable() {
		
		LogInfo("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
		if (load()) {			
			LogInfo("successfully initialized.");
		} else {
			LogWarning("error: initialization failure!");
		}
		LogInfo("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
		
	}
	
	public void onDisable() {
	
		LogInfo("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
		if(save()) {			
			LogInfo("successfully disabled.");
		}
		LogInfo("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
				
	}
	
	public boolean load() {
		
		aRApiPvpStatus = new ancientRelicsPvpStatus(this);
		
		aRConfig = new ancientRelicsConfig(this);

		aRHeartBeat = new ancientRelicsHeartbeat(this);

		aRCore = new ancientRelicsCore(this);
		
		aRCache = new ancientRelicsCacheManagement(this);
		
		aRCommandMain = new ancientRelicsCommandMain(this);
		aRPvP = new ancientRelicsPvP(this);
		aRGroups = new ancientRelicsGroups(this);
		
		aRHelp = new ancientRelicsHelp(this);
		
		aRCommandHandler = new ancientRelicsCommandHandler(this);
		
		graves = new Graves(this);
		
		newSettlersAPI = (newSettlers) Bukkit.getServer().getPluginManager().getPlugin("NewSettlers");
		
		if(aRConfig.initiateConfig()){
			LogInfo("initialized: config.yml");
		} else {
			LogWarning("error: config.yml couldn't be initiated.");
			return false;
		}

		//make sure the timers are stopped for a reset
		aRCore.toggleAncientRelicsHeartbeat(false);

		//Start timers
		aRCore.toggleAncientRelicsHeartbeat(true);

		if(aRPvP.initiatePvP()){
			LogInfo("initialized: PvP Systems");
		} else {
			LogWarning("error: PvP Systems couldn't be initiated.");
			return false;
		}
	    
	    // Scoreboard and Team Manager
	    scoreboardManager = Bukkit.getScoreboardManager();
	    
		
		if(aRGroups.initiateGroups()){
			LogInfo("initialized: Groups Systems");
		} else {
			LogWarning("error: Groups Systems couldn't be initiated.");
			return false;
		}
		
		if (!setupPermissions()) {
			LogWarning("error: No permissions plugin found.");
	        //use these if you require econ
	        getServer().getPluginManager().disablePlugin(this);
	        return false;
	    }
		
		aRCache.loadGroupsList();
		
		getServer().getPluginManager().registerEvents(new ancientRelicsEntityDamageByEntityEvent(this), this);
		LogInfo("initialized: EntityDamageByEntityEvent");
		getServer().getPluginManager().registerEvents(new ancientRelicsPlayerDeathEvent(this), this);
		LogInfo("initialized: PlayerDeathEvent");
		getServer().getPluginManager().registerEvents(new ancientRelicsPlayerJoinEvent(this), this);
		LogInfo("initialized: PlayerJoinEvent");
		getServer().getPluginManager().registerEvents(new ancientRelicsPlayerInteractEvent(this), this);
		
		return true;
	}
	
	public boolean save() {
		
		graves.removeAllDeaths();
		
		aRConfig.saveConfig();
		LogInfo("saved: config.yml");
		
		//deactivate heartbeat
		aRCore.toggleAncientRelicsHeartbeat(false);
		
		if(aRPvP.deactivatePvP()){
			LogInfo("deactivated: PvP Systems");
		} else {
			LogWarning("error: PvP Systems couldn't be deactivated.");
			return false;
		}
		
		if(aRGroups.deactivateGroups()){
			LogInfo("deactivated: Groups Systems");
		} else {
			LogWarning("error: Groups Systems couldn't be deactivated.");
			return false;
		}
		
		return true;
		
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		
		if(cmd.getName().equalsIgnoreCase("ancientrelics") || cmd.getName().equalsIgnoreCase("akzeptieren") || cmd.getName().equalsIgnoreCase("ablehnen")){
			aRCommandMain.getCommand(sender, cmd, commandLabel, args);
			return true;
		}

		//if(cmd.getName().equalsIgnoreCase("pvp") || cmd.getName().equalsIgnoreCase("bounty") || cmd.getName().equalsIgnoreCase("duell")){
		if(cmd.getName().equalsIgnoreCase("pvp") || cmd.getName().equalsIgnoreCase("duell")){
			aRPvP.aRCommandPvP.getCommand(sender, cmd, commandLabel, args);
			return true;
		}
		
		if(cmd.getName().equalsIgnoreCase("group")){
			aRGroups.aRCommandGroups.getCommand(sender, cmd, commandLabel, args);
			return true;
		}
		
		return false;
	}
	
	public Player getPlayerByName(String name) {
		Player foundPlayer = null;
		 
		for(Player p : Bukkit.getServer().getOnlinePlayers()) {
			if(p.getName().equals(name)) {
				foundPlayer = p;
				break;
			}
		}
		if(foundPlayer != null) {
			return foundPlayer;
		}
		
		return null;
	}
	
	public OfflinePlayer getOfflinePlayerByName(String name) {
		OfflinePlayer foundPlayer = null;
		 
		for(OfflinePlayer p : Bukkit.getServer().getOfflinePlayers()) {
			if(p.getName().equals(name)) {
				foundPlayer = p;
				break;
			}
		}
		if(foundPlayer != null) {
			return foundPlayer;
		}
		
		return null;
	}
	
}