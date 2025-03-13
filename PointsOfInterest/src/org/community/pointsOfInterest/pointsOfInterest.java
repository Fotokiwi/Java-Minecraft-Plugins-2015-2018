package org.community.pointsOfInterest;

import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import terranetworkorg.XPConomy.*;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.community.DatabaseProvider.MySQL.MySQL;
import org.community.pointsOfInterest.Configs.*;
import org.community.pointsOfInterest.Honorlist.pointsOfInterestHonorlist;
import org.community.pointsOfInterest.Listener.*;
import org.community.pointsOfInterest.POIList.pointsOfInterestPOIs;
import org.community.pointsOfInterest.Sign.pointsOfInterestSign;
import org.community.pointsOfInterest.User.pointsOfInterestUser;
import org.community.pointsOfInterestCommands.pointsOfInterestCommands;

public class pointsOfInterest extends JavaPlugin {
	
	public final static Logger log = Logger.getLogger("Minecraft");
	public static final String logprefix = "[PointsOfInterest]";
 	
	public XPConomy xpconomy = null;
	
	public HashMap<String, Location> ismoved = new HashMap<String, Location>();
	
    //Klassen
    public pointsOfInterestConfig poiConfig = null;
    public pointsOfInterestPOIs poiPOIs = null;
    public pointsOfInterestSign poiSign = null;
    public pointsOfInterestUser poiUser = null;
    public pointsOfInterestHonorlist poiHonor = null;
    public pointsOfInterestCommands poiCommands = null;
	
	public static void LogInfo(String Message) {
		
		log.info(logprefix + " " + Message);
		
	}
	
	public static void LogError(String Message) {
		
		log.log(Level.SEVERE, logprefix + " " + Message);
		
	}
	
	public static void LogWarning(String Message) {
		
		log.log(Level.WARNING, logprefix + " " + Message);
		
	}
	
	private boolean setupXPConomy()
    {		
        if (getServer().getPluginManager().getPlugin("XPConomy") != null) {
        	
        	LogInfo("succesfully connected with XPConomy");
        	return true;
            
        }
        else {
        	return false;
        }
        
    }		
	
	public void onEnable() {
		LogInfo("Starte PointsOfInterest...");

		if (!setupXPConomy()) {
			System.out.println("No XPconomy plugin found.");
	        //use these if you require econ
	        getServer().getPluginManager().disablePlugin(this);
	        return;
		}
		
		poiConfig = new pointsOfInterestConfig(this);		
		poiConfig.loadConfig();
		poiPOIs = new pointsOfInterestPOIs(this);		
		poiSign = new pointsOfInterestSign(this);		
		poiUser = new pointsOfInterestUser(this);		
		poiHonor = new pointsOfInterestHonorlist(this);	
		poiCommands = new pointsOfInterestCommands(this);
		
		getServer().getPluginManager().registerEvents(new pointsOfInterestPlayerEvent(this), this);
		LogInfo("initialized listener: PlayerEvent");
		
		getServer().getPluginManager().registerEvents(new pointsOfInterestSignEvent(this), this);
		LogInfo("initialized listener: SignEvent");
		
		getServer().getPluginManager().registerEvents(new pointsOfInterestPlayerJoin (this), this);
		LogInfo("initialized listener: PlayerJoin");
		
		if(poiPOIs.loadFromDB()){
			LogInfo("initialized: poi");
		} else {
			LogWarning("error: poi couldn't be initiated.");
			return;
		}
		if(poiSign.loadFromDB()){
			LogInfo("initialized: sign.yml");
		} else {
			LogWarning("error: sign.yml couldn't be initiated.");
			return;
		}
		if(poiUser.loadFromDB()){
			LogInfo("initialized: user");
		} else {
			LogWarning("error: user couldn't be initiated.");
			return;
		}
		if(poiHonor.loadFromDB()){
			LogInfo("initialized: honor.yml");
		} else {
			LogWarning("error: honor.yml couldn't be initiated.");
			return;
		}
		
		initializeMySQL();
		
	}
	
	public void onDisable() {
		poiPOIs.saveToDB();
		poiSign.saveToDB();
		poiUser.saveToDB();
		poiHonor.saveToDB();
	}	
	
	public void showPluginInfo(CommandSender sender)
	{
		sender.sendMessage(ChatColor.GREEN + logprefix);
	}	
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) 
	{
		if(cmd.getName().equalsIgnoreCase("pointsOfInterest")){
			poiCommands.getCommand(sender, cmd, commandLabel, args);
			return true;
		}
		else
			return false;
	}
	
	public void initializeMySQL() {
		
		MySQL mysql = new MySQL();
		mysql.connect();
		mysql.create("CREATE TABLE IF NOT EXISTS POI_User (ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY, UUID TEXT NOT NULL,  AnzahlPOIs INT, Spielername TEXT NOT NULL , Cooldown BIGINT NOT NULL) ENGINE=MYISAM;");
		mysql.create("CREATE TABLE IF NOT EXISTS POI_Pois (ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY, POIName TEXT NOT NULL, Location TEXT NOT NULL, Lore TEXT) ENGINE=MYISAM;");
		mysql.create("CREATE TABLE IF NOT EXISTS POI_Honorlist (ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY, UUID TEXT NOT NULL, TimeStamp BIGINT NOT NULL) ENGINE=MYISAM;");
		mysql.create("CREATE TABLE IF NOT EXISTS POI_VisitedPOI(ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY, UUID TEXT NOT NULL, POIName TEXT NOT NULL) ENGINE=MYISAM;");
		mysql.create("CREATE TABLE IF NOT EXISTS POI_Signs(ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY, UUID TEXT NOT NULL, Location TEXT NOT NULL) ENGINE=MYISAM;");
		mysql.disconnect();
		
		
	}

}