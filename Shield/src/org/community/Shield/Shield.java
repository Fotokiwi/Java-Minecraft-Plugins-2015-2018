package org.community.Shield;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;









import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.community.Shield.Commands.ShieldAdminCommand;
import org.community.Shield.Commands.ShieldCreateCommand;
import org.community.Shield.Commands.ShieldInfoCommand;
import org.community.Shield.Commands.ShieldMainCommand;
import org.community.Shield.Commands.ShieldModifyCommand;
import org.community.Shield.Commands.ShieldPersistCommand;
import org.community.Shield.Commands.ShieldRemoveCommand;
import org.community.Shield.Configs.ShieldBrewing;
import org.community.Shield.Configs.ShieldButtons;
import org.community.Shield.Configs.ShieldChest;
import org.community.Shield.Configs.ShieldConfig;
import org.community.Shield.Configs.ShieldDispenser;
import org.community.Shield.Configs.ShieldFencegate;
import org.community.Shield.Configs.ShieldFurnance;
import org.community.Shield.Configs.ShieldIrondoor;
import org.community.Shield.Configs.ShieldPlates;
import org.community.Shield.Configs.ShieldTrapdoor;
import org.community.Shield.Configs.ShieldWooddoor;
import org.community.Shield.Listener.ShieldBlockDestroyListener;
import org.community.Shield.Listener.ShieldBlockPlaceListener;
import org.community.Shield.Listener.ShieldInteractListener;
import org.community.newSettlers.newSettlers;
import org.community.newSettlers.Town.Town;
import org.fusesource.jansi.Ansi;

public class Shield extends JavaPlugin{

	//MC System Variablen
	final static public String CMD_COLOR_BLUE = Ansi.ansi().fg(Ansi.Color.BLUE).bold().toString();
	final static public String CMD_COLOR_YELLOW = Ansi.ansi().fg(Ansi.Color.YELLOW).bold().toString();
	final static public String CMD_COLOR_RED = Ansi.ansi().fg(Ansi.Color.RED).bold().toString();
	final static public String CMD_COLOR_DEFAULT = Ansi.ansi().reset().toString();
	
	public Logger log = Logger.getLogger("Minecraft");
	public String logprefix = CMD_COLOR_BLUE+"[Shield 3.0.0]";
	
	public FileConfiguration config = null;
	public File configFile = null;
	public FileConfiguration dispenser = null;
	public File dispenserFile = null;
	public FileConfiguration chest = null;
	public File chestFile = null;
	public FileConfiguration furnance = null;
	public File furnanceFile = null;
	public FileConfiguration wooddoor = null;
	public File wooddoorFile = null;
	public FileConfiguration brewing = null;
	public File brewingFile = null;
	public FileConfiguration irondoor = null;
	public File irondoorFile = null;
	public FileConfiguration fencegate = null;
	public File fencegateFile = null;
	public FileConfiguration trapdoor = null;
	public File trapdoorFile = null;
	public File buttonsFile = null;
	public FileConfiguration buttons = null;
	public File platesFile = null;
    public FileConfiguration plates = null;

	//Shield Subclasses
	public ShieldMainCommand maincommand = null;
	public ShieldAdminCommand admincommand = null;
	public ShieldCreateCommand createcommand = null;
	public ShieldModifyCommand modifycommand = null;
	public ShieldRemoveCommand removecommand = null;
	public ShieldInfoCommand infocommand = null;
	public ShieldPersistCommand persistcommand = null;
	public ShieldBlockPlaceListener blocklistener = null;
	public ShieldBlockDestroyListener destroylistener = null;
	public ShieldInteractListener interactlistener = null;
	public ShieldConfig shieldconfig = null;
	public ShieldBrewing shieldbrewing = null;
	public ShieldChest shieldchest = null;
	public ShieldDispenser shielddispenser = null;
	public ShieldFencegate shieldfencegate = null;
	public ShieldFurnance shieldfurnance = null;
	public ShieldIrondoor shieldirondoor = null;
	public ShieldTrapdoor shieldtrapdoor = null;
	public ShieldWooddoor shieldwooddoor = null;
	public ShieldButtons shieldbuttons = null;
	public ShieldPlates shieldplates = null;
    
    public newSettlers newSettlersAPI = null;

	//Hashmaps und Maps
	public final String[] Blocklist = {"LEVER","WOOD_PLATE","STONE_PLATE","WOOD_BUTTON","STONE_BUTTON","DISPENSER","CHEST","FURNACE","WOODEN_DOOR","IRON_DOOR_BLOCK","FENCE_GATE","BREWING_STAND","TRAP_DOOR"};
	public final List<String> Blocks = Arrays.asList(Blocklist);
	public Map<Player, String> persist = new HashMap<Player, String>();
    
	//Public Variablen
	public int taskbrewing;
	public int taskchest;
	public int taskdispenser;
	public int taskfencegate;
	public int taskfurnace;
	public int taskirondoor;
	public int tasktrapdoor;
	public int taskwooddoor;
	public int taskbuttons;
	public int taskplates;
	
	//Logger Funktionen
	public void LogInfo(String Message) {

		log.info(logprefix + " " + Message);

	}

	public void LogError(String Message) {

		log.log(Level.SEVERE, logprefix+ CMD_COLOR_RED  + " " + Message);

	}

	public void LogWarning(String Message) {

		log.log(Level.WARNING, logprefix+ CMD_COLOR_RED + " " + Message);

	}




	// Methoden
	/**
	 * Pr�fen ob der Spieler Admin oder Mod ist
	 * @param player Spieler muss �bergeben werden
	 * @return boolean True wenn in Gruppe Admin oder Moderator
	 */
	public boolean isAdmin(Player player){

		if(config.getList("Settings.Admins").contains(player.getUniqueId().toString())){
			return true;
		}
		else
			return false;	
	}

	/**
	 * Pr�fen ob der Spieler Zugriff auf das gesperrte Objekt hat
	 * @param player Spieler muss �bergeben werden
	 * @param material Block des Events
	 * @return boolean True wenn Zugriff gew�hrt.
	 */

	public boolean hasAccess(Player player, Block block){
		Material material = block.getType();
		String world = block.getWorld().getName();
		String xyz = block.getX()+"_"+block.getY()+"_"+block.getZ();
		if(material==Material.DISPENSER){
			if(dispenser.getString("Register."+world+"."+xyz+".Owner")==null){
				return true;
			}
			if(dispenser.getString("Register."+world+"."+xyz+".Access", "").contains(player.getUniqueId().toString())){
				return true;
			}
			else{
				Town town = newSettlersAPI.nSCore.getPlayerTown(player);
				String stadt = "Jd7jhd/§DH";
				if(town != null)
					stadt = town.getName();

				if(dispenser.getString("Register."+world+"."+xyz+".Access", "").contains(stadt)){
				//if(dispenser.getString("Register."+world+"."+xyz+".Access", "").toLowerCase().contains("t:"+stadt)){
					return true;
				}
				else
					return false;
			}
		}
		if(material==Material.CHEST){
			if(chest.getString("Register."+world+"."+xyz+".Owner")==null){
				String xyz2 = "";
				Block bpos1 = block.getLocation().add(-1, 0, 0).getBlock();
				Block bpos2 = block.getLocation().add(+1, 0, 0).getBlock();
				Block bpos3 = block.getLocation().add(0, 0, -1).getBlock();
				Block bpos4 = block.getLocation().add(0, 0, +1).getBlock();
				if(bpos1.getType()==Material.CHEST)
					xyz2 = bpos1.getX()+"_"+bpos1.getY()+"_"+bpos1.getZ();
				if(bpos2.getType()==Material.CHEST)
					xyz2 = bpos2.getX()+"_"+bpos2.getY()+"_"+bpos2.getZ();
				if(bpos3.getType()==Material.CHEST)
					xyz2 = bpos3.getX()+"_"+bpos3.getY()+"_"+bpos3.getZ();
				if(bpos4.getType()==Material.CHEST)
					xyz2 = bpos4.getX()+"_"+bpos4.getY()+"_"+bpos4.getZ();
				if(xyz2!=""){
					if(chest.getString("Register."+world+"."+xyz2+".Owner")!=null){
						String ownername = chest.getString("Register."+world+"."+xyz2+".Owner");
						chest.set("Register."+world+"."+xyz+".Owner", ownername);
						chest.set("Register."+world+"."+xyz+".Access", ownername);
						return false;}
				}
				return true;
			}
			if(chest.getString("Register."+world+"."+xyz+".Access", "").contains(player.getUniqueId().toString())){
				return true;
			}
			else{
				Town town = newSettlersAPI.nSCore.getPlayerTown(player);
				String stadt = "Jd7jhd/§DH";
				if(town != null)
					stadt = town.getName();

			  if(chest.getString("Register."+world+"."+xyz+".Access", "").contains(stadt)){
			//	if(chest.getString("Register."+world+"."+xyz+".Access", "").toLowerCase().contains("t:"+stadt)){
					return true;
				}
				else
					return false;
			}
		}
		if(material==Material.FURNACE){
			if(furnance.getString("Register."+world+"."+xyz+".Owner")==null){
				return true;
			}
			if(furnance.getString("Register."+world+"."+xyz+".Access", "").contains(player.getUniqueId().toString())){
				return true;
			}
			else{
				Town town = newSettlersAPI.nSCore.getPlayerTown(player);
				String stadt = "Jd7jhd/§DH";
				if(town != null)
					stadt = town.getName();

				if(furnance.getString("Register."+world+"."+xyz+".Access", "").contains(stadt)){
				//if(furnance.getString("Register."+world+"."+xyz+".Access", "").toLowerCase().contains("t:"+stadt)){
					return true;
				}
				else
					return false;
			}
		}
		if(material==Material.WOODEN_DOOR){
			if(wooddoor.getString("Register."+world+"."+xyz+".Owner")==null){
				return true;
			}
			if(wooddoor.getString("Register."+world+"."+xyz+".Access", "").contains(player.getUniqueId().toString())){
				return true;
			}
			else{
				Town town = newSettlersAPI.nSCore.getPlayerTown(player);
				String stadt = "Jd7jhd/§DH";
				if(town != null)
					stadt = town.getName();

			   if(wooddoor.getString("Register."+world+"."+xyz+".Access", "").contains(stadt)){
				//if(wooddoor.getString("Register."+world+"."+xyz+".Access", "").toLowerCase().contains("t:"+stadt)){
					return true;
				}
				else
					return false;
			}
		}
		if(material==Material.IRON_DOOR_BLOCK){
			if(irondoor.getString("Register."+world+"."+xyz+".Owner")==null){
				return true;
			}
			if(irondoor.getString("Register."+world+"."+xyz+".Access", "").contains(player.getUniqueId().toString())){
				return true;
			}
			else{
				Town town = newSettlersAPI.nSCore.getPlayerTown(player);
				String stadt = "Jd7jhd/§DH";
				if(town != null)
					stadt = town.getName();
				
				 if(irondoor.getString("Register."+world+"."+xyz+".Access", "").contains(stadt)){
				//if(irondoor.getString("Register."+world+"."+xyz+".Access", "").toLowerCase().contains("t:"+stadt)){
					return true;
				}
				else
					return false;
			}
		}
		if(material==Material.FENCE_GATE){
			if(fencegate.getString("Register."+world+"."+xyz+".Owner")==null){
				return true;
			}
			if(fencegate.getString("Register."+world+"."+xyz+".Access", "").contains(player.getUniqueId().toString())){
				return true;
			}
			else{
				Town town = newSettlersAPI.nSCore.getPlayerTown(player);
				String stadt = "Jd7jhd/§DH";
				if(town != null)
					stadt = town.getName();
 
				 if(fencegate.getString("Register."+world+"."+xyz+".Access", "").contains(stadt)){
				//if(fencegate.getString("Register."+world+"."+xyz+".Access", "").toLowerCase().contains("t:"+stadt)){
					return true;
				}
				else
					return false;
			}
		}
		if(material==Material.BREWING_STAND){
			if(brewing.getString("Register."+world+"."+xyz+".Owner")==null){
				return true;
			}
			if(brewing.getString("Register."+world+"."+xyz+".Access", "").contains(player.getUniqueId().toString())){
				return true;
			}
			else{
				Town town = newSettlersAPI.nSCore.getPlayerTown(player);
				String stadt = "Jd7jhd/§DH";
				if(town != null)
					stadt = town.getName();
				if(brewing.getString("Register."+world+"."+xyz+".Access", "").contains(stadt)){
				//if(brewing.getString("Register."+world+"."+xyz+".Access", "").toLowerCase().contains("t:"+stadt)){
					return true;
				}
				else
					return false;
			}
		}
		if(material==Material.TRAP_DOOR){
			if(trapdoor.getString("Register."+world+"."+xyz+".Owner")==null){
				return true;
			}
			if(trapdoor.getString("Register."+world+"."+xyz+".Access", "").contains(player.getUniqueId().toString())){
				return true;
			}
			else{
				Town town = newSettlersAPI.nSCore.getPlayerTown(player);
				String stadt = "Jd7jhd/§DH";
				if(town != null)
					stadt = town.getName();

				 if(trapdoor.getString("Register."+world+"."+xyz+".Access", "").contains(stadt)){
				//if(trapdoor.getString("Register."+world+"."+xyz+".Access", "").toLowerCase().contains("t:"+stadt)){
					return true;
				}
				else
					return false;
			}
		}
		if(material==Material.WOOD_BUTTON || material==Material.STONE_BUTTON || material==Material.LEVER){
			if(buttons.getString("Register."+world+"."+xyz+".Owner")==null){
				return true;
			}
			if(buttons.getString("Register."+world+"."+xyz+".Access", "").contains(player.getUniqueId().toString())){
				return true;
			}
			else{
				Town town = newSettlersAPI.nSCore.getPlayerTown(player);
				String stadt = "Jd7jhd/§DH";
				if(town != null)
					stadt = town.getName();

				if(buttons.getString("Register."+world+"."+xyz+".Access", "").contains(stadt)){
				//if(buttons.getString("Register."+world+"."+xyz+".Access", "").toLowerCase().contains("t:"+stadt)){
					return true;
				}
				else
					return false;
			}
		}
		if(material==Material.WOOD_PLATE || material==Material.STONE_PLATE){
			if(plates.getString("Register."+world+"."+xyz+".Owner")==null){
				return true;
			}
			if(plates.getString("Register."+world+"."+xyz+".Access", "").contains(player.getUniqueId().toString())){
				return true;
			}
			else{
				Town town = newSettlersAPI.nSCore.getPlayerTown(player);
				String stadt = "Jd7jhd/§DH";
				if(town != null)
					stadt = town.getName();

				if(plates.getString("Register."+world+"."+xyz+".Access", "").contains(stadt)){
				//if(plates.getString("Register."+world+"."+xyz+".Access", "").toLowerCase().contains("t:"+stadt)){
					return true;
				}
				else
					return false;
			}
		}
		return false;
	}

	//Save Timer
	public void shieldsave(){
		shieldconfig.saveConfig();
		shieldbrewing.savebrewing();
		shieldchest.saveChest();
		shielddispenser.savedispenser();
		shieldfencegate.savefencegate();
		shieldfurnance.savefurnance();
		shieldirondoor.saveirondoor();
		shieldtrapdoor.savetrapdoor();
		shieldwooddoor.savewooddoor();
		shieldbuttons.savebuttons();
		shieldplates.saveplates();
	}

	public void savescheduler(){
		new BukkitRunnable(){
			@Override
			public void run() {
				shieldconfig.saveConfig();
				shieldbrewing.savebrewing();
				taskbrewing = this.getTaskId();
			}
		}.runTaskTimerAsynchronously(this, config.getLong("Settings.Saveintervall")*20,config.getLong("Settings.Saveintervall")*20);
       new BukkitRunnable(){
    	   @Override
    	   public void run() {
    		   savechest();
    	   }
       }.runTaskLater(this, 600L);
       new BukkitRunnable(){
    	   @Override
    	   public void run() {
    		   savedispenser();
    	   }
       }.runTaskLater(this, 1200L);
       new BukkitRunnable(){
    	   @Override
    	   public void run() {
    		   savefencegate();
    	   }
       }.runTaskLater(this, 1800L);
       new BukkitRunnable(){
    	   @Override
    	   public void run() {
    		   savefurnace();
    	   }
       }.runTaskLater(this, 2400L);
       new BukkitRunnable(){
    	   @Override
    	   public void run() {
    		   saveirondoor();
    	   }
       }.runTaskLater(this, 3000L);
       new BukkitRunnable(){
    	   @Override
    	   public void run() {
    		   savetrapdoor();
    	   }
       }.runTaskLater(this, 3600L);
       new BukkitRunnable(){
    	   @Override
    	   public void run() {
    		   savewooddoor();
    	   }
       }.runTaskLater(this, 4200L);
       new BukkitRunnable(){
    	   @Override
    	   public void run() {
    		   savebuttons();
    	   }
       }.runTaskLater(this, 4800L);
       new BukkitRunnable(){
    	   @Override
    	   public void run() {
    		   saveplates();
    	   }
       }.runTaskLater(this, 5400L);
	}
	public void savechest(){
		new BukkitRunnable(){
			@Override
			public void run() {
				shieldchest.saveChest();
				taskchest = this.getTaskId();
			}
		}.runTaskTimerAsynchronously(this, config.getLong("Settings.Saveintervall")*20,config.getLong("Settings.Saveintervall")*20);
	}
	public void savedispenser(){
		new BukkitRunnable(){
			@Override
			public void run() {
				shielddispenser.savedispenser();
				taskdispenser = this.getTaskId();
			}
		}.runTaskTimerAsynchronously(this, config.getLong("Settings.Saveintervall")*20,config.getLong("Settings.Saveintervall")*20);
	}
	public void savefencegate(){
		new BukkitRunnable(){
			@Override
			public void run() {
				shieldfencegate.savefencegate();
				taskfencegate = this.getTaskId();
			}
		}.runTaskTimerAsynchronously(this, config.getLong("Settings.Saveintervall")*20,config.getLong("Settings.Saveintervall")*20);

	}
	public void savefurnace(){
		new BukkitRunnable(){
			@Override
			public void run() {
				shieldfurnance.savefurnance();
				taskfurnace = this.getTaskId();
			}
		}.runTaskTimerAsynchronously(this, config.getLong("Settings.Saveintervall")*20,config.getLong("Settings.Saveintervall")*20);

	}
	public void saveirondoor(){
		new BukkitRunnable(){
			@Override
			public void run() {
				shieldirondoor.saveirondoor();
				taskirondoor = this.getTaskId();
			}
		}.runTaskTimerAsynchronously(this, config.getLong("Settings.Saveintervall")*20,config.getLong("Settings.Saveintervall")*20);

	}
	public void savetrapdoor(){
		new BukkitRunnable(){
			@Override
			public void run() {
				shieldtrapdoor.savetrapdoor();
				tasktrapdoor = this.getTaskId();
			}
		}.runTaskTimerAsynchronously(this, config.getLong("Settings.Saveintervall")*20,config.getLong("Settings.Saveintervall")*20);

	}
	public void savewooddoor(){
		new BukkitRunnable(){
			@Override
			public void run() {
				shieldwooddoor.savewooddoor();
				taskwooddoor = this.getTaskId();
			}
		}.runTaskTimerAsynchronously(this, config.getLong("Settings.Saveintervall")*20,config.getLong("Settings.Saveintervall")*20);

	}
	public void savebuttons(){
		new BukkitRunnable(){
			@Override
			public void run() {
				shieldbuttons.savebuttons();
				taskbuttons = this.getTaskId();
			}
		}.runTaskTimerAsynchronously(this, config.getLong("Settings.Saveintervall")*20,config.getLong("Settings.Saveintervall")*20);

	}
	public void saveplates(){
		new BukkitRunnable(){
			@Override
			public void run() {
				shieldplates.saveplates();
				taskplates = this.getTaskId();
			}
		}.runTaskTimerAsynchronously(this, config.getLong("Settings.Saveintervall")*20,config.getLong("Settings.Saveintervall")*20);

	}



	//Startup Teil

	public boolean starter(){


		shieldconfig.initiateConfig();
		shieldbrewing.initiatebrewing();
		shieldchest.initiateChest();
		shielddispenser.initiatedispenser();
		shieldfencegate.initiatefencegate();
		shieldfurnance.initiatefurnance();
		shieldirondoor.initiateirondoor();
		shieldtrapdoor.initiatetrapdoor();
		shieldwooddoor.initiatewooddoor();
		shieldplates.initiateplates();
		shieldbuttons.initiatebuttons();
		LogInfo("Config Handler geladen");
		getServer().getPluginManager().registerEvents(new ShieldBlockPlaceListener(this), this);
		getServer().getPluginManager().registerEvents(new ShieldBlockDestroyListener(this), this);
		LogInfo("Blocklistener eingehaengt");
		getServer().getPluginManager().registerEvents(new ShieldInteractListener(this), this);
		LogInfo("Interactlistener eingehaengt");
		savescheduler();
		LogInfo("Save Task gestartet mit "+config.getInt("Settings.Saveintervall")+" Sekunden Timer");
		persist.clear();
		return true;
	}


	@Override
	public void onEnable() {
		LogInfo(CMD_COLOR_BLUE);
		LogInfo("#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*");
		LogInfo("Starte Shield...");
		LogInfo("|`-._��_.-`|");
		LogInfo("|    ||    |");
		LogInfo("|___o()o___|");
		LogInfo("|__((<>))__|");
		LogInfo("`   o!!o   �");
		LogInfo(" `   ||   �");
		LogInfo("  `  ||  �");
		LogInfo("   '.||.'");
		LogInfo("     ``");
		maincommand = new ShieldMainCommand(this);
		admincommand = new ShieldAdminCommand(this);
		createcommand = new ShieldCreateCommand(this);
		modifycommand = new ShieldModifyCommand(this);
		removecommand = new ShieldRemoveCommand(this);
		infocommand = new ShieldInfoCommand(this);
		persistcommand = new ShieldPersistCommand(this);
		blocklistener = new ShieldBlockPlaceListener(this);
		destroylistener = new ShieldBlockDestroyListener(this);
		interactlistener = new ShieldInteractListener(this);
		shieldconfig = new ShieldConfig(this);
		shieldbrewing = new ShieldBrewing(this);
		shieldchest = new ShieldChest(this);
		shielddispenser = new ShieldDispenser(this);
		shieldfencegate = new ShieldFencegate(this);
		shieldfurnance = new ShieldFurnance(this);
		shieldirondoor = new ShieldIrondoor(this);
		shieldtrapdoor = new ShieldTrapdoor(this);
		shieldwooddoor = new ShieldWooddoor(this);
		shieldbuttons = new ShieldButtons(this);
		shieldplates = new ShieldPlates(this);
		
		newSettlersAPI = (newSettlers) Bukkit.getServer().getPluginManager().getPlugin("NewSettlers");
		
		if(starter())
			LogInfo("Parameter erfolgreich initalisiert");
		else{
			LogError("Initialisierung fehlgeschlagen!!!!");
			LogError("Shield ist"+CMD_COLOR_YELLOW+ "----> NICHT <----"+CMD_COLOR_RED+ "aktiv !");
		}
		LogInfo("#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*"+CMD_COLOR_DEFAULT);
	}

	public void onDisable(){
		Bukkit.getScheduler().cancelAllTasks();
		LogInfo(CMD_COLOR_BLUE);
		LogInfo("#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*");
		LogInfo("Beende Shield...");
		shieldsave();
		LogInfo("Datenbank Dateien gespeichert...");
		LogInfo("#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*"+CMD_COLOR_DEFAULT);
	}

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {

		if(cmd.getName().equalsIgnoreCase("shield")){
			maincommand.getCommand(sender, cmd, commandLabel, args);
			return true;
		}
		if(cmd.getName().equalsIgnoreCase("sadmin")){
			admincommand.getCommand(sender, cmd, commandLabel, args);
			return true;
		}
		if(cmd.getName().equalsIgnoreCase("screate")){
			createcommand.getCommand(sender, cmd, commandLabel, args);
			return true;
		}
		if(cmd.getName().equalsIgnoreCase("smodify")){
			modifycommand.getCommand(sender, cmd, commandLabel, args);
			return true;
		}
		if(cmd.getName().equalsIgnoreCase("sremove")){
			removecommand.getCommand(sender, cmd, commandLabel, args);
			return true;
		}
		if(cmd.getName().equalsIgnoreCase("sinfo")){
			infocommand.getCommand(sender, cmd, commandLabel, args);
			return true;
		}
		if(cmd.getName().equalsIgnoreCase("spersist")){
			persistcommand.getCommand(sender, cmd, commandLabel, args);
			return true;
		}
		return false;
	}
	
	@SuppressWarnings("deprecation")
	public UUID getPlayerUUID(String name) {
		OfflinePlayer o = Bukkit.getServer().getOfflinePlayer(name);
		return o.isWhitelisted() ? o.getUniqueId() : null;
	}
	
	/*public UUID getPlayerUUID(String name) {
		Player foundPlayer = null;
		OfflinePlayer foundOffPlayer = null;
		LogInfo("online size: " + Bukkit.getServer().getOnlinePlayers().length);
		for(Player p : Bukkit.getServer().getOnlinePlayers()) {
			LogInfo("online: " + p.getName() + " VS " + name);
			if(p.getName().equals(name)) {
				foundPlayer = p;
				break;
			}
		}
		if(foundPlayer != null) {
			return foundPlayer.getUniqueId();
		} else {
			LogInfo("offline size: " + Bukkit.getServer().getOfflinePlayers().length);
			for(OfflinePlayer p : Bukkit.getServer().getOfflinePlayers()) {
				LogInfo("offline: " + p.getName() + " VS " + name);
				if(p.getName().equals(name)) {
					foundOffPlayer = p;
					break;
				}
			}
			if(foundOffPlayer != null) {
				return foundOffPlayer.getUniqueId();
			}
		}
		
		return null;
	}*/
	
	//not checking for has player before 
	public String getPlayerNameByUUID(UUID id) {
		return Bukkit.getServer().getOfflinePlayer(id).getName();
	}
	
	public String getPlayerNameByUUIDString(String id) {
		return getPlayerNameByUUID(UUID.fromString(id));
	}
	
	public Boolean isProtectAllowed(Player player, Town town) {
		
		Town playerTown = newSettlersAPI.nSCore.getPlayerTown(player);
		
		if(town == null) {
			return false;
		}
		
		if(playerTown == null) {
			if(town.isProtectAllowedOutsider()) {
				return true;
			}
		} else {
			if(town.getName().equals(playerTown.getName())) {
				if(town.isProtectAllowed()) {
					return true;
				}
			} else {
				if(town.getAllies().contains(playerTown.getName())) {
					if(town.isProtectAllowedAlly()) {
						return true;
					}
				} else {
					if(town.isProtectAllowedOutsider()) {
						return true;
					}
				}
			}
		}		
		
		return false;
	}
	
}