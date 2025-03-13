package org.community.LootChest;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.community.LootChest.Cache.Cache;
import org.community.LootChest.Commands.LootChestCommand;
import org.community.LootChest.Config.LootChestConfig;
import org.community.LootChest.Listener.ChestOpenListener;
import org.community.LootChest.Listener.OnPlayerJoin;
import org.community.fourWays.fourWays;
import org.community.fourWays.Core.fourWaysCore;




public class LootChest extends JavaPlugin {
	
	public final static Logger log = Logger.getLogger("Minecraft");
	public static final String logprefix = "[LootChest 3.0.0 1.7.9-R0.1]";



    
    public LootChestCommand lcc = null;
    public Cache cache = null;
	public LootChestConfig lcConfig = null;
	public fourWaysCore fwc = null;
	
	public void LogInfo(String Message) {
		
		log.info(logprefix + " " + Message);
		
	}
	
	public void LogError(String Message) {
		
		log.log(Level.SEVERE, logprefix + " " + Message);
		
	}
	
	public void LogWarning(String Message) {
		
		log.log(Level.WARNING, logprefix + " " + Message);
		
	}

	
	
	public void onEnable() {	

		//loadConfig();
		lcc = new LootChestCommand(this);
		getServer().getPluginManager().registerEvents(new ChestOpenListener(this), this);
		getServer().getPluginManager().registerEvents(new OnPlayerJoin(this), this);
		cache = new Cache(this);
		cache.loadLootChestList();
		//cache.loadUserDataChestsMap();
		lcConfig = new LootChestConfig(this);
		lcConfig.loadConfig();
		
		fwc = ((fourWays) Bukkit.getServer().getPluginManager().getPlugin("FourWays")).fWCore;
		

		
	}	
	
	public void onDisable() {
	    
	    //cache.saveLootChestList();
	    cache.saveUserDataChestsMap();
		
		//saveConfig();
		
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if(cmd.getName().equalsIgnoreCase("lootChest") || cmd.getName().equalsIgnoreCase("loot")){
			lcc.getCommand(sender, cmd, commandLabel, args);
			return true;
		}
		else
			return false;
	}
}