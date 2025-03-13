package org.community.MessageController;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;


public class MessageController extends JavaPlugin {
	
	public final static Logger log = Logger.getLogger("Minecraft");
	public static final String logprefix = "[MessageController 3.0.0]";
	
	public MessageDeathListener msglist = null;
	
	public static void LogInfo(String Message) {
		
		log.info(logprefix + " " + Message);
		
	}
	
	public static void LogError(String Message) {
		
		log.log(Level.SEVERE, logprefix + " " + Message);
		
	}
	
	public static void LogWarning(String Message) {
		
		log.log(Level.WARNING, logprefix + " " + Message);
		
	}
	
	
	public void onEnable() {	
    msglist = new MessageDeathListener(this);
    getServer().getPluginManager().registerEvents(msglist, this);
		
	}	
	
	public void onDisable() {
		
	}
	
}