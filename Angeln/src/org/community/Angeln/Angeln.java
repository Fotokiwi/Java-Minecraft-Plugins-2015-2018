package org.community.Angeln;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.community.Angeln.Achievement.Achievements;
import org.community.Angeln.Configs.*;
import org.community.Angeln.Listener.PlayerFishingEvent;
import org.community.Angeln.Listener.SignPlaceEvent;


public class Angeln extends JavaPlugin {

	public final static Logger log = Logger.getLogger("Minecraft");
	public String logprefix = "[Fischer]";

	public FileConfiguration biome;
	public File biomeFile;
	public FileConfiguration fishes;
	public File fishesFile;
	public FileConfiguration players;
	public File playersFile;
	
	public Regionen angelnBiome;
	public Fische angelnFische;
	public Spieler angelnSpieler;
	public Achievements angelnAchievements;

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
		
		angelnBiome = new Regionen(this);
		angelnFische = new Fische(this);
		angelnSpieler = new Spieler(this);
		angelnAchievements = new Achievements(this);
		
		if(angelnBiome.initiateConfig()){
			LogInfo("initialized: biomes.yml");
		} else {
			LogWarning("error: biomes.yml couldn't be initiated.");
			return false;
		}
		
		if(angelnFische.initiateConfig()){
			LogInfo("initialized: fishes.yml");
		} else {
			LogWarning("error: fishes.yml couldn't be initiated.");
			return false;
		}
		
		if(angelnSpieler.initiateConfig()){
			LogInfo("initialized: players.yml");
		} else {
			LogWarning("error: players.yml couldn't be initiated.");
			return false;
		}

		getServer().getPluginManager().registerEvents(new PlayerFishingEvent(this), this);
		LogInfo("initialized: PlayerFishingEvent");
		getServer().getPluginManager().registerEvents(new SignPlaceEvent(this), this);
		LogInfo("initialized: SignPlaceEvent");
		
		return true;
	}
	
	public boolean save() {
		return true;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if(sender instanceof Player) {
			if(cmd.getName().equalsIgnoreCase("angeln")){	
				
				if(args.length == 1) {
					if(args[0].equalsIgnoreCase("top5")) {
						angelnAchievements.displayTop5(sender);
					}
					if(args[0].equalsIgnoreCase("erfolge")) {
						angelnAchievements.displayAchieved(sender);
					}
				}
				
				return true;
			}
		}
		return true;
	}
}