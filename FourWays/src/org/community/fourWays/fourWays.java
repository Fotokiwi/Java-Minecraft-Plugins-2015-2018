package org.community.fourWays;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.community.fourWays.Cache.fourWaysCache;
import org.community.fourWays.Commands.*;
import org.community.fourWays.Configs.*;
import org.community.fourWays.Core.fourWaysCore;
import org.community.fourWays.Help.fourWaysHelp;
import org.community.fourWays.Listener.*;
import org.community.fourWays.Tasks.fourWaysHeartbeat;
import org.community.fourWays.Trader.Trader;
import org.community.fourWays.User.fourWaysUsers;
import org.community.fourWays.Utility.*;
import org.community.newSettlers.newSettlers;

public class fourWays extends JavaPlugin {
	
	public Logger log = Logger.getLogger("Minecraft");
	public String logprefix = "[FourWays]";
	
	public FileConfiguration config = null;
    public File configFile = null;
	public FileConfiguration block = null;
    public File blockFile = null;
	public FileConfiguration entity = null;
    public File entityFile = null;
	public FileConfiguration jobs = null;
    public File jobsFile = null;
	public FileConfiguration harvest = null;
    public File harvestFile = null;
	public FileConfiguration craftingCooldown = null;
    public File craftingCooldownFile = null;
	public FileConfiguration questmode = null;
    public File questmodeFile = null;
	public FileConfiguration abilities = null;
    public File abilitiesFile = null;
	public FileConfiguration horses = null;
    public File horsesFile = null;
    
    public newSettlers newSettlersAPI = null;
    
    //Config Klassen
    public fourWaysConfig fWConfig = null;    
    public fourWaysBlocks fWBlocks = null;     
    public fourWaysEntities fWEntity = null;   
    public fourWaysJobs fWJobs = null;     
    public fourWaysHarvest fWHarvest = null;      
    public fourWaysCraftingCooldown fWCraftingCooldown = null;
    public fourWaysQuestmode fWQuestmode = null;
    public fourWaysAbilities fWAbilities = null;
    public fourWaysHorses fWHorses = null;
    //Hilfe Klassen
    public fourWaysHelp fWHelp = null;    
    //Core Klassen
    public fourWaysCore fWCore = null;    
    //Command Klassen
    public fourWaysCommandMain fWCommandMain = null;
    public fourWaysCommandUser fWCommandUser = null;
    public fourWaysCommandTrader fWCommandTrader = null;   
    //Heartbeat Klassen
    public fourWaysHeartbeat fWHeartbeat = null;    
    //Cache Klassen
    public fourWaysCache fWCache = null;    
    //Users Klassen
    public fourWaysUsers fWUsers = null; 
    //Trader Klassen
    public Trader fWTrader = null;
    // Utility Klassen
    public ItemTypeParser fWItems = null;    
    public Rewards fWRewards = null;
	public CreatureUtils fWCreatureUtils = null;
	public ExperienceCalculation fWExpCalc = null;
	
	// preCache HashMaps	

	public Map<Player, Boolean> adminMode = new HashMap<Player, Boolean>();
	
    public Map<Player, String> preCache_BlockBreak = new HashMap<Player, String>();
    public Map<Player, String> preCache_BlockBreak_disallow = new HashMap<Player, String>();
    public Map<Player, String> preCache_BlockPlace = new HashMap<Player, String>();
    public Map<Player, String> preCache_BlockPlace_disallow = new HashMap<Player, String>();
    // positionCache HashMaps
    public Map<Player, Location> positionCache = new HashMap<Player, Location>();
    // harvest HashMaps
    public Map<String, String> harvestCache = new HashMap<String, String>();
    // Variable zum Speichern des letzten Angreifers (hilft bei Elementar-Sch�den)
    public Map<Integer, Entity> damagerTemp = new HashMap<Integer, Entity>();
    
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
		
		LogInfo("===========================================================");
		
		fWConfig = new fourWaysConfig(this);
		fWBlocks = new fourWaysBlocks(this);
		fWEntity = new fourWaysEntities(this);
		fWJobs = new fourWaysJobs(this);	
		fWHarvest = new fourWaysHarvest(this);	
		fWCraftingCooldown = new fourWaysCraftingCooldown(this);
		fWQuestmode = new fourWaysQuestmode(this);
		fWAbilities = new fourWaysAbilities(this);
		fWHorses = new fourWaysHorses(this);
		fWHelp = new fourWaysHelp(this);
		fWCommandMain = new fourWaysCommandMain(this);
		fWCommandUser = new fourWaysCommandUser(this);
		fWCommandTrader = new fourWaysCommandTrader(this);
		fWCache = new fourWaysCache(this);		
		fWUsers = new fourWaysUsers(this);	
		fWTrader = new Trader(this);
		fWCore = new fourWaysCore(this);		
		fWHeartbeat = new fourWaysHeartbeat(this);
		fWItems = new ItemTypeParser(this);
		fWRewards = new Rewards(this);
		fWCreatureUtils = new CreatureUtils(this);
		fWExpCalc = new ExperienceCalculation(this);
		
		newSettlersAPI = (newSettlers) Bukkit.getServer().getPluginManager().getPlugin("NewSettlers");
		
		if(fWConfig.initiateConfig()){
			LogInfo("initialized: config.yml");
		} else {
			LogWarning("error: config.yml couldn't be initiated.");
			return false;
		}
		
		if(fWBlocks.initiateConfig()){
			LogInfo("initialized: blocks.yml");
		} else {
			LogWarning("error: blocks.yml couldn't be initiated.");
			return false;
		}
		
		if(fWEntity.initiateConfig()){
			LogInfo("initialized: entities.yml");
		} else {
			LogWarning("error: entities.yml couldn't be initiated.");
			return false;
		}
		
		if(fWJobs.initiateConfig()){
			LogInfo("initialized: jobs.yml");
		} else {
			LogWarning("error: jobs.yml couldn't be initiated.");
			return false;
		}
		
		if(fWHarvest.initiateConfig()){
			LogInfo("initialized: harvest.yml");
		} else {
			LogWarning("error: harvest.yml couldn't be initiated.");
			return false;
		}
		
		if(fWCraftingCooldown.initiateConfig()){
			LogInfo("initialized: craftingCooldown.yml");
		} else {
			LogWarning("error: craftingCooldown.yml couldn't be initiated.");
			return false;
		}
		
		if(fWQuestmode.initiateConfig()){
			LogInfo("initialized: questmode.yml");
		} else {
			LogWarning("error: questmode.yml couldn't be initiated.");
			return false;
		}
		
		if(fWAbilities.initiateConfig()){
			LogInfo("initialized: abilities.yml");
		} else {
			LogWarning("error: abilities.yml couldn't be initiated.");
			return false;
		}
		
		if(fWHorses.initiateConfig()){
			LogInfo("initialized: horses.yml");
		} else {
			LogWarning("error: horses.yml couldn't be initiated.");
			return false;
		}
		
		fWCache.loadQuestmode();
		LogInfo("initialized: questmode-hashmaps");

		//make sure the timers are stopped for a reset
		fWCore.toggleFourWaysHeartbeat(false);

		//Start timers
		fWCore.toggleFourWaysHeartbeat(true);
		
		if(fWUsers.initiateUsers()){
			LogInfo("initialized: Users Systems");
		} else {
			LogWarning("error: Users Systems couldn't be initiated.");
			return false;
		}
		
		//getServer().getPluginManager().registerEvents(new ancientRelicsEntityDamageByEntityEvent(this), this);
		//LogInfo("initialized: EntityDamageByEntityEvent");
		getServer().getPluginManager().registerEvents(new fourWaysBlockBreakEvent(this), this);
		LogInfo("initialized: BlockBreakEvent");
		getServer().getPluginManager().registerEvents(new fourWaysBlockBreakHarvestEvent(this), this);
		LogInfo("initialized: BlockBreakHarvestEvent");
		getServer().getPluginManager().registerEvents(new fourWaysBlockGrowEvent(this), this);
		LogInfo("initialized: BlockGrowEvent");
		getServer().getPluginManager().registerEvents(new fourWaysBlockPlaceEvent(this), this);
		LogInfo("initialized: BlockPlaceEvent");
		getServer().getPluginManager().registerEvents(new fourWaysCreatureSpawnEvent(this), this);
		LogInfo("initialized: CreatureSpawnEvent");
		getServer().getPluginManager().registerEvents(new fourWaysEntityDamageByEntityEvent(this), this);
		LogInfo("initialized: EntityDamageByEntityEvent");
		getServer().getPluginManager().registerEvents(new fourWaysEntityDamageEvent(this), this);
		LogInfo("initialized: EntityDamageEvent");
		getServer().getPluginManager().registerEvents(new fourWaysEntityDeathEvent(this), this);
		LogInfo("initialized: EntityDeathEvent");
		getServer().getPluginManager().registerEvents(new fourWaysEntityInteractEntityEvent(this), this);
		LogInfo("initialized: EntityInteractEntityEvent");
		getServer().getPluginManager().registerEvents(new fourWaysEntityTameEvent(this), this);
		LogInfo("initialized: EntityTameEvent");
		getServer().getPluginManager().registerEvents(new fourWaysHangingBreakEvent(this), this);
		LogInfo("initialized: HangingBreakEvent");
		getServer().getPluginManager().registerEvents(new fourWaysInventoryClickEvent(this), this);
		LogInfo("initialized: InventoryClickEvent");
		getServer().getPluginManager().registerEvents(new fourWaysInventoryCloseEvent(this), this);
		LogInfo("initialized: InventoryCloseEvent");
		getServer().getPluginManager().registerEvents(new fourWaysInventoryCraftEvent(this), this);
		LogInfo("initialized: InventoryCraftEvent");
		getServer().getPluginManager().registerEvents(new fourWaysInventoryOpenEvent(this), this);
		LogInfo("initialized: InventoryOpenEvent");
		getServer().getPluginManager().registerEvents(new fourWaysItemHeldEvent(this), this);
		LogInfo("initialized: ItemHeldEvent");
		getServer().getPluginManager().registerEvents(new fourWaysItemSpawnEvent(this), this);
		LogInfo("initialized: ItemSpawnEvent");
		getServer().getPluginManager().registerEvents(new fourWaysPlayerBucketFillEvent(this), this);
		LogInfo("initialized: PlayerBucketFillEvent");
		getServer().getPluginManager().registerEvents(new fourWaysPlayerInteractEvent(this), this);
		LogInfo("initialized: PlayerInteractEvent");
		getServer().getPluginManager().registerEvents(new fourWaysPlayerJoinEvent(this), this);
		LogInfo("initialized: PlayerJoinEvent");
		getServer().getPluginManager().registerEvents(new fourWaysPlayerUnleashEvent(this), this);
		LogInfo("initialized: PlayerUnleashEvent");
		getServer().getPluginManager().registerEvents(new fourWaysPlayerQuitEvent(this), this);
		LogInfo("initialized: PlayerQuitEvent");
		getServer().getPluginManager().registerEvents(new fourWaysPlayerShearEntityEvent(this), this);
		LogInfo("initialized: PlayerShearEntityEvent");
		//().getPluginManager().registerEvents(new fourWaysPrepareCraftEvent(this), this);
		//LogInfo("initialized: PrepareCraftEvent");
		getServer().getPluginManager().registerEvents(new fourWaysSignChangeEvent(this), this);
		LogInfo("initialized: SignChangeEvent");
		getServer().getPluginManager().registerEvents(new fourWaysStructureGrowEvent(this), this);
		LogInfo("initialized: StructureGrowEvent");
		getServer().getPluginManager().registerEvents(new fourWaysVehicleDestroyEvent(this), this);
		LogInfo("initialized: VehicleDestroyEvent");

		fWCreatureUtils.initiateTools();
		
		return true;
	}
	
	public boolean save() {
		
		//fWConfig.saveConfig();
		//LogInfo("saved: config.yml");
		
		//fWBlocks.saveConfig();
		//LogInfo("saved: blocks.yml");
		
		//fWJobs.saveConfig();
		//LogInfo("saved: jobs.yml");
		
		//fWHarvest.saveConfig();
		//LogInfo("saved: harvest.yml");
		
		fWCraftingCooldown.saveConfig();
		LogInfo("saved: craftingCooldown.yml");
		
		fWHorses.saveConfig();
		LogInfo("saved: horses.yml");
		
		fWCache.saveQuestmode();
		fWQuestmode.saveConfig();
		LogInfo("saved: questmode.yml");
		
		//fWAbilities.saveConfig();
		//LogInfo("saved: abilities.yml");
		
		//deactivate heartbeat
		fWCore.toggleFourWaysHeartbeat(false);
		
		if(fWUsers.deactivateUsers()){
			LogInfo("deactivated: Users Systems");
		} else {
			LogWarning("error: Users Systems couldn't be deactivated.");
			return false;
		}
		
		fWTrader.saveToFile();
		
		return true;
		
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		
		if(cmd.getName().equalsIgnoreCase("fourways") || cmd.getName().equalsIgnoreCase("akzeptieren") || cmd.getName().equalsIgnoreCase("ablehnen") || cmd.getName().equalsIgnoreCase("questmodus")){
			fWCommandMain.getCommand(sender, cmd, commandLabel, args);
			return true;
		}
		
		if(cmd.getName().equalsIgnoreCase("beruf")){
			fWCommandUser.getCommand(sender, cmd, commandLabel, args);
			return true;
		}
		
		if(cmd.getName().equalsIgnoreCase("lieferung")){
			fWCommandTrader.getCommand(sender, cmd, commandLabel, args);
			return true;
		}
		
		return false;
	}
	
	public Player getPlayerByName(String name) {
		Player foundPlayer = null;
		 
		for(Player p : Bukkit.getServer().getOnlinePlayers()) {
			if(p.getName().equalsIgnoreCase(name)) {
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