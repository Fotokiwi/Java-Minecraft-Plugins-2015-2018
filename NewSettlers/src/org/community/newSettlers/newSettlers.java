package org.community.newSettlers;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.community.newSettlers.ShipRoute.ZoneVector;
import org.community.newSettlers.ShipRoute.newSettlersShipRoute;
import org.community.newSettlers.Tasks.*;
import org.community.newSettlers.Town.*;
import org.community.newSettlers.Trade.newSettlersTrade;
import org.community.newSettlers.Utility.BlockFindUtility;
import org.community.newSettlers.API.API;
import org.community.newSettlers.Cache.*;
import org.community.newSettlers.Commands.*;
import org.community.newSettlers.Help.*;
import org.community.newSettlers.Listener.*;
import org.community.newSettlers.Configs.*;
import org.community.newSettlers.Core.*;
import org.community.fourWays.fourWays;
import org.community.DatabaseProvider.MySQL.MySQL;
import org.community.ancientRelics.ancientRelics;


public class newSettlers extends JavaPlugin {
	
	public Logger log = Logger.getLogger("Minecraft");
	public String logprefix = "[NewSettlers]";

	public FileConfiguration config = null;
    public File configFile = null;
    public FileConfiguration user = null;
    public File userFile = null;
    public FileConfiguration routes = null;
    public File routesFile = null;

    public Map<Player, String> newRequest = new HashMap<Player, String>();
    public Map<Player, Player> newApproval = new HashMap<Player, Player>();
	
	public Map<Player, Location> playerDeathLocation = new HashMap<Player, Location>();

	public Map<Player, Boolean> adminMode = new HashMap<Player, Boolean>();
	
	public Map<Player, String> tempChat = new HashMap<Player, String>();
	public Map<Player, Long> chatCooldown = new HashMap<Player, Long>();
	public Map<Player, Long> screamCooldown = new HashMap<Player, Long>();
	public Map<Player, Player> lastMsgPartner = new HashMap<Player, Player>();
    
    //Config Klassen
    public newSettlersConfig nSConfig = null;
    public newSettlersUserConfig nSUserConfig = null;
    public newSettlersRoutesConfig nSRoutesConfig = null;
    //Hilfe Klassen
    public newSettlersHelp nSHelp = null;
    //Command Klassen
    public newSettlersCommandMain nSCommandMain = null;
    public newSettlersCommandTown nSCommandTown = null;
    public newSettlersCommandTrade nSCommandTrade = null;
    public newSettlersCommandChat nSCommandChat = null;
    //Cache Klassen
    public newSettlersCache nSCache = null;
    //Heartbeat Klassen
    public newSettlersHeartbeat nSHeartbeat = null;
    public newSettlersCore nSCore = null;
    //Town Klasse
    public Town nSTown = null;
    public newSettlersTownInvite nSTownInvite = null;
    public newSettlersTownDestroy nSTownDestroy = null;
    public newSettlersAllyInvite nSAllyInvite = null;
    public newSettlersEnemyInvite nSEnemyInvite = null;
    public newSettlersAllyRemove nSAllyRemove = null;
    public newSettlersEnemyRemove nSEnemyRemove = null;
    public newSettlersBuildingInvite nSBuildingInvite = null;
    public newSettlersTownExpand nSTownExpand = null;
    //Schiffsrouten
    public newSettlersShipRoute nSShipRoute = null;
    //API Klasse
    public API nSAPI = null;
    public BlockFindUtility nSBFU = null;
    //Trade Klasse
    public newSettlersTrade nSTrade = null;
    public newSettlersNotfall nSNotfall = null;
    public ZoneVector nSZoneVector = null;
    public MySQL nSMySQL = null;
    
    public fourWays fourWays = null;
    
    public ancientRelics ancientRelics = null;
    
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
		
		nSConfig = new newSettlersConfig(this);
		nSUserConfig = new newSettlersUserConfig(this);
		nSRoutesConfig = new newSettlersRoutesConfig(this);
		nSHelp = new newSettlersHelp(this);
		nSCommandMain = new newSettlersCommandMain(this);
		nSCommandTown = new newSettlersCommandTown(this);
		nSCommandTrade = new newSettlersCommandTrade(this);
		nSCommandChat = new newSettlersCommandChat(this);
		nSCache = new newSettlersCache(this);	
		nSHeartbeat = new newSettlersHeartbeat(this);
		nSCore = new newSettlersCore(this);
		nSAPI = new API(this);
		nSBFU = new BlockFindUtility();
		nSTownInvite = new newSettlersTownInvite(this);
		nSTownDestroy = new newSettlersTownDestroy(this);
		nSAllyInvite = new newSettlersAllyInvite(this);
		nSEnemyInvite = new newSettlersEnemyInvite(this);
		nSAllyRemove = new newSettlersAllyRemove(this);
		nSEnemyRemove = new newSettlersEnemyRemove(this);
		nSBuildingInvite = new newSettlersBuildingInvite(this);
		nSTownExpand = new newSettlersTownExpand(this);
	    nSShipRoute = new newSettlersShipRoute(this);
	    
	    // Dummy Class Load to (hopefully) prevent NoClassDefFoundError
	    nSNotfall = new newSettlersNotfall(this);
	    nSTrade = new newSettlersTrade(this);
	    nSZoneVector = new ZoneVector();
	    nSMySQL = new MySQL();
		
		fourWays = (fourWays) Bukkit.getServer().getPluginManager().getPlugin("FourWays");
		ancientRelics = (ancientRelics) Bukkit.getServer().getPluginManager().getPlugin("AncientRelics");
		
		if(nSConfig.initiateConfig()){
			LogInfo("initialized: config.yml");
		} else {
			LogWarning("error: config.yml couldn't be initiated.");
			return false;
		}
		if(nSUserConfig.initiateConfig()){
			LogInfo("initialized: user.yml");
		} else {
			LogWarning("error: user.yml couldn't be initiated.");
			return false;
		}
		if(nSRoutesConfig.initiateConfig()){
			LogInfo("initialized: routes.yml");
		} else {
			LogWarning("error: routes.yml couldn't be initiated.");
			return false;
		}

		//make sure the timers are stopped for a reset
		nSCore.toggleNewSettlersHeartbeat(false);

		//Start timers
		nSCore.toggleNewSettlersHeartbeat(true);
		
		nSCore.initializeMySQL();
		
		nSCache.loadTownList();

		getServer().getPluginManager().registerEvents(new newSettlersAsyncPlayerChatEvent(this), this);
		LogInfo("initialized: AsyncPlayerChatEvent");
		getServer().getPluginManager().registerEvents(new newSettlersBlockBreakEvent(this), this);
		LogInfo("initialized: BlockBreakEvent");
		getServer().getPluginManager().registerEvents(new newSettlersBlockPlaceEvent(this), this);
		LogInfo("initialized: BlockPlaceEvent");
		getServer().getPluginManager().registerEvents(new newSettlersEntityDamageByEntityEvent(this), this);
		LogInfo("initialized: EntityDamageByEntityEvent");
		getServer().getPluginManager().registerEvents(new newSettlersEntityExplodeEvent(this), this);
		LogInfo("initialized: EntityExplodeEvent");
		getServer().getPluginManager().registerEvents(new newSettlersCreatureSpawnEvent(this), this);
		LogInfo("initialized: CreatureSpawnEvent");
		getServer().getPluginManager().registerEvents(new newSettlersHangingBreakEvent(this), this);
		LogInfo("initialized: HangingBreakEvent");
		getServer().getPluginManager().registerEvents(new newSettlersHangingPlaceEvent(this), this);
		LogInfo("initialized: HangingPlaceEvent");
		getServer().getPluginManager().registerEvents(new newSettlersInventoryClickEvent(this), this);
		LogInfo("initialized: InventoryClickEvent");
		getServer().getPluginManager().registerEvents(new newSettlersInventoryCloseEvent(this), this);
		LogInfo("initialized: InventoryCloseEvent");
		getServer().getPluginManager().registerEvents(new newSettlersInventoryCraftEvent(this), this);
		LogInfo("initialized: InventoryCraftEvent");
		getServer().getPluginManager().registerEvents(new newSettlersCommandPreProcessEvent(this), this);
		LogInfo("initialized: PlayerCommandPreprocessEvent");
		getServer().getPluginManager().registerEvents(new newSettlersPlayerDeathEvent(this), this);
		LogInfo("initialized: PlayerDeathEvent");
		getServer().getPluginManager().registerEvents(new newSettlersPlayerInteractEvent(this), this);
		LogInfo("initialized: PlayerInteractEvent");
		getServer().getPluginManager().registerEvents(new newSettlersPlayerJoinEvent(this), this);
		LogInfo("initialized: PlayerJoinEvent");
		getServer().getPluginManager().registerEvents(new newSettlersPlayerMoveEvent(this), this);
		LogInfo("initialized: PlayerMoveEvent");
		getServer().getPluginManager().registerEvents(new newSettlersPlayerRespawnEvent(this), this);
		LogInfo("initialized: PlayerRespawnEvent");
		//getServer().getPluginManager().registerEvents(new newSettlersPrepareCraftEvent(this), this);
		//LogInfo("initialized: PrepareCraftEvent");
		
		return true;
	}
	
	public boolean save() {
		
		nSUserConfig.saveConfig();
		LogInfo("saved: user.yml");
		
		//deactivate heartbeat
		nSCore.toggleNewSettlersHeartbeat(false);
		
		return true;
		
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		
		if(sender instanceof Player) {
			
			Player player = (Player)sender;
			
			if(cmd.getName().equalsIgnoreCase("siedler") || cmd.getName().equalsIgnoreCase("ja") || cmd.getName().equalsIgnoreCase("nein") || cmd.getName().equalsIgnoreCase("webui")){
				nSCommandMain.getCommand(player, cmd, commandLabel, args);
				return true;
			}
			if(cmd.getName().equalsIgnoreCase("stadt")){
				nSCommandTown.getCommand(player, cmd, commandLabel, args);
				return true;
			}
			if(cmd.getName().equalsIgnoreCase("handel")){
				nSCommandTrade.getCommand(player, cmd, commandLabel, args);
				return true;
			}
			//if(cmd.getName().equalsIgnoreCase("lc") || cmd.getName().equalsIgnoreCase("chatname") || cmd.getName().equalsIgnoreCase("sc") || cmd.getName().equalsIgnoreCase("gc") || cmd.getName().equalsIgnoreCase("ac") || cmd.getName().equalsIgnoreCase("mc") || cmd.getName().equalsIgnoreCase("global")){
			if(cmd.getName().equalsIgnoreCase("me") || cmd.getName().equalsIgnoreCase("flüstern") || cmd.getName().equalsIgnoreCase("lc") || cmd.getName().equalsIgnoreCase("sc") || cmd.getName().equalsIgnoreCase("gc") || cmd.getName().equalsIgnoreCase("ac") || cmd.getName().equalsIgnoreCase("mc") || cmd.getName().equalsIgnoreCase("schreien") || cmd.getName().equalsIgnoreCase("hilfe") || cmd.getName().equalsIgnoreCase("msg") || cmd.getName().equalsIgnoreCase("global") || cmd.getName().equalsIgnoreCase("toggleglobal")){
				nSCommandChat.getCommand(player, cmd, commandLabel, args);
				return true;
			}
			if(cmd.getName().equalsIgnoreCase("notfall")){
				Player p = (Player) sender;
				nSCore.notfall(p);
				return true;
			}
			
		} else {

			if(cmd.getName().equalsIgnoreCase("shiproute")){
				nSShipRoute.prepareTeleport(args[0]);
				return true;
			}
			if(cmd.getName().equalsIgnoreCase("testshiproute")){
				nSShipRoute.testTeleport();
				return true;
			}
			if(cmd.getName().equalsIgnoreCase("tempspawn")){				
				Player p = this.getPlayerByName(args[0]);
				long duration = Integer.parseInt(args[1]);
				nSCore.setTempSpawn(p, duration);
				return true;
			}
			if(cmd.getName().equalsIgnoreCase("stadt")) {
				if(args[0].equalsIgnoreCase("stadturkunde")) {
					Player player = this.getPlayerByName(args[1]);
					if(player == null)
						return true;
					
					ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
					BookMeta meta = (BookMeta) book.getItemMeta();
					meta.setAuthor("Stadtregistrar");
					ArrayList<String> lore = new ArrayList<String>();
					lore.add("Diese Urkunde berechtigt");
					lore.add("Euch zur Gründung einer");
					lore.add("unabhängigen Stadt.");
					meta.setLore(lore);
					meta.setTitle("Stadturkunde");
					book.setItemMeta(meta);
	
					book = player.getWorld().dropItemNaturally(player.getLocation(), book).getItemStack();
					return true;
				}
			}

			
			if(cmd.getName().equalsIgnoreCase("seen")){
				if(args.length != 1)
				{
					sender.sendMessage("Bitte gebe genau einen Spielernamen nach dem /seen Befehl an.");
					return true;
				}
				OfflinePlayer op = getOfflinePlayerByName(args[0]);
				if(op != null){
					Date d = new Date(op.getLastPlayed());
					SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss - dd.MM.yyyy ");
					sender.sendMessage("Der Spieler " + args[0] + " war zuletzt online: " + sdf.format(d));
					return true;
				}
				else
				{
					sender.sendMessage("Der Spieler hat noch nie auf diesem Server gespielt.");
					return true;
				}
			}
			sender.sendMessage("Die aktuelle Version unterstützt keine Konsolen-Befehle.");
			return true;
		}			
		
		return false;
	}
	
	@SuppressWarnings("deprecation")
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