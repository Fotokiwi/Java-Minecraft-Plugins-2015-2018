package org.community.fourWays.User;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.community.fourWays.fourWays;

public class User {
		
	private final fourWays plugin;
	
	private FileConfiguration user = null;
	private File userFile = null;
    
	private Player player = null;
	
	// Allgemeine User-Variablen
	private String userName;
	private int userLevel = 1;
	private double userExp = 0;
	private String userJobHash;
	// Systemvariablen
	private int userLevelUpExp;
	private int userDailyPlaytimeExp;
	private int userDailyBlockbreakExp;
	private long userDailyReset;
	
	private boolean noticeOnMaxExp = false;
	
	/**
	 * Server-Startup Initialisierung.
	 * Der Spieler wird aus der Datei in den Cache geladen und registriert.
	 * @param plugin Die Hauptvariable des Plugins
	 * @param name Der Name des Spielers, ausgelesen aus dem Dateinamen
	 */
	public User(fourWays plugin, Player player, boolean dummy) {
		this.plugin = plugin;
		this.userName = player.getName();
		this.player = player;
		this.userJobHash = "1,XX";
		this.userLevelUpExp = plugin.fWUsers.fWLevels.calculateLevelExp(2);
		
		registerUser();
		saveToFile();
		
		checkDailyReset();
	}
	
	/**
	 * Server-Startup Initialisierung.
	 * Der Spieler wird aus der Datei in den Cache geladen und registriert.
	 * @param plugin Die Hauptvariable des Plugins
	 * @param name Der Name des Spielers, ausgelesen aus dem Dateinamen
	 */
	public User(fourWays plugin, UUID id) {
		this.plugin = plugin;
		this.player = (Player) plugin.getServer().getPlayer(id);
		this.userName = player.getName();
		
		loadFromFile();
		registerUser();
		
		checkDailyReset();
	}
	
	/**
	 * Liefert den Berufs-Hash zurück
	 * @return String
	 */
	public void checkDailyReset() {
		if(userDailyReset == 0) {
			userDailyReset = System.currentTimeMillis();
			saveToFile();
			return;
		}
		if(System.currentTimeMillis() >= (userDailyReset + 75600000)) {
			userDailyPlaytimeExp = 0;
			userDailyBlockbreakExp = 0;
			userDailyReset = System.currentTimeMillis();
			saveToFile();
			return;
		}
		return;
	}
	
	/**
	 * Liefert den Spielernamen zurück
	 * @return String
	 */
	public String getName() {
		return this.player.getName();
	}
	
	/**
	 * Liefert das Berufslevel zurück
	 * @return int
	 */
	public int getLevel() {
		return userLevel;
	}
	
	public int whenIsDailyResetInMinutes() {
		long difference = (this.userDailyReset + 75600000) - System.currentTimeMillis();
		if(difference <= 0) {
			return 0;
		} else {
			return (int) (difference/1000/60);
		}
	}
	
	/**
	 * Setzt das Berufslevel
	 * @return void
	 */
	private void addLevel() {
		userLevel++;
		return;
	}
	
	/**
	 * Die Hauptroutine für das Levelup des Spielers
	 * @return void
	 */
	public void levelUp() {
		if(userLevel < plugin.config.getInt("Config.Levels.MaxLevel", 40)) {
			if(userExp >= userLevelUpExp) {
				if(userLevel == getMaxLevel()){
					this.player.sendMessage("Bitte suche einen weiterführenden Trainer auf.");
					return;
				} else {
					addLevel();
					userLevelUpExp = plugin.fWUsers.fWLevels.calculateLevelExp(userLevel + 1);
					userExp = 0;
					String[] hash = userJobHash.split(",");
					hash[0] = "" + userLevel;
					userJobHash = hash[0] + "," + hash[1];
					this.player.sendMessage("Du bist erfolgreich auf Stufe " + userLevel + " aufgestiegen.");
					plugin.preCache_BlockBreak_disallow.put(this.player, null);
					plugin.preCache_BlockPlace_disallow.put(this.player, null);
					this.noticeOnMaxExp = false;
					saveToFile();

					plugin.fWCore.handleCommand(plugin, this.player, "/U1XX152c653jrfKD " + this.player.getName() + " " + userLevel);
					//plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new removePermission(this.player, "AllowedToLevelUp"), 200L);

					return;
				}
			} else {
				this.player.sendMessage("Du hast nicht ausreichend Erfahrung um die nächste Stufe zu erreichen.");
				return;
			}
		} else {
			this.player.sendMessage("Du hast das derzeitige Höchstlevel erreicht, herzlichen Glückwunsch!");
			return;
		}
	}
	
	/*private class removePermission implements Runnable{
		
		private String permission = null;
		private Player player = null;
		
		public removePermission(Player player, String permission) {
			this.permission = permission;
			this.player = player;
		}

		public void run() {
			plugin.fWCore.handleCommand(plugin, player, "/pex user " + player.getName() + " remove " + permission);
		}
	}*/
	
	/**
	 * Liefert die Berufserfahrung zurück
	 * @return double
	 */
	public double getExp() {
		return userExp;
	}
	
	/**
	 * Liefert die Berufserfahrung zurück
	 * @return double
	 */
	public double getBlockbreakExp() {
		return userDailyBlockbreakExp;
	}
	
	/**
	 * Liefert die Berufserfahrung zurück
	 * @return double
	 */
	public double getPlaytimeExp() {
		return userDailyPlaytimeExp;
	}
	
	/**
	 * Addiert Berufserfahrung
	 * @return void
	 */
	public void addPlaytimeExp(int exp) {
		if((userExp + exp) <= userLevelUpExp){			
			if(userDailyPlaytimeExp + exp <= plugin.config.getInt("Config.Levels.PlaytimeExp")){
				userDailyPlaytimeExp = userDailyPlaytimeExp + exp;
				userExp = userExp + exp;
			} else {
				userDailyPlaytimeExp = plugin.config.getInt("Config.Levels.PlaytimeExp");
			}
		} else {
			userExp = userLevelUpExp;
		}
		saveToFile();
		return;
	}
	
	/**
	 * Addiert Berufserfahrung
	 * @return void
	 */
	public void addExp(int exp) {
		if(userLevel >= 40)
			return;
		if((userExp + exp) <= userLevelUpExp){			
			if(userDailyBlockbreakExp + exp <= plugin.config.getInt("Config.Levels.ActionExp")){
				userDailyBlockbreakExp = userDailyBlockbreakExp + exp;
				userExp = userExp + exp;
			} else {
				userDailyBlockbreakExp = plugin.config.getInt("Config.Levels.ActionExp");
			}
		} else {
			userExp = userLevelUpExp;
			if(this.noticeOnMaxExp) {

			} else {
				this.player.sendMessage(ChatColor.AQUA + "Bitte suche einen Trainer auf um aufzusteigen.");
				this.player.sendMessage(ChatColor.AQUA + "Du erhälst momentan keine Abbau-Erfahrung mehr.");
				this.noticeOnMaxExp = true;
			}
		}
		// Erzeugt Lag!
		//saveToFile();
		return;
	}
	
	/**
	 * Addiert Berufserfahrung ab Stufe 30
	 * @return void
	 */
	public void grantExp(int exp) {
		userExp = userExp + exp;
		saveToFile();
		return;
	}
	
	/**
	 * Liefert den Berufs-Hash zurück
	 * @return String
	 */
	public String getJobHash() {
		return userJobHash;
	}
	
	/**
	 * Fügt dem Spieler einen neuen Beruf hinzu
	 * @return int
	 */
	public int addJob(String jobCode) {
		if(plugin.jobs.get("Beruf." + jobCode + ".Name") == null)
			return 0;
		
		int jobTier = plugin.jobs.getInt("Beruf." + jobCode + ".Tier");
		String[] rawHash = userJobHash.split(",");
		String[] jobHash = rawHash[1].split("-");
		for(int i = 0; i < jobHash.length; i++) {
			if(plugin.jobs.getInt("Beruf." + jobHash[i] + ".Tier") == jobTier)
				return 1;
		}
		
		ConfigurationSection jobSection = plugin.jobs.getConfigurationSection("Beruf." + jobCode + ".Voraussetzung");
		
		if(jobSection == null)
			return -1;
		
		Set<String> jobKeys = jobSection.getKeys(false);
  	  	String[] jobArray = jobKeys.toArray(new String[0]); 
  	  	
  	  	int jobLevel;
  	  	
  	  	String[] jobInfo = getJobHash().split(",");
		String playerClass = jobInfo[1];
		int playerLevel = new Integer(jobInfo[0]);
  	  	
  	  	for(int i = 0; i < jobArray.length; i++){
	  	  	jobLevel = plugin.jobs.getInt("Beruf." + jobCode + ".Voraussetzung." + jobArray[i], 0);
			if(playerClass.contains(jobArray[i]) && playerLevel >= jobLevel) {
				userJobHash += "-" + jobCode;
				plugin.preCache_BlockBreak_disallow.put(this.player, null);
				saveToFile();
				return 2;
			}
  	  	}
		return 3;
	}
	
	/**
	 * Fügt dem Spieler einen neuen Beruf hinzu
	 * @return int
	 */
	public int removeJob(String jobCode) {  
		if(jobCode.equalsIgnoreCase("YY")) {
			userJobHash = userJobHash.replace("-" + jobCode, "");
			String[] tempHash = userJobHash.split(",");
			userJobHash = userLevel + "," + tempHash[1];
			plugin.preCache_BlockBreak_disallow.put(this.player, null);
			saveToFile();
			return 2;
		}
		
		userLevel = 1;
		userJobHash = userJobHash.replace("-" + jobCode, "");
		String[] tempHash = userJobHash.split(",");
		userJobHash = userLevel + "," + tempHash[1];
		plugin.preCache_BlockBreak_disallow.put(this.player, null);
		this.userLevelUpExp = plugin.fWUsers.fWLevels.calculateLevelExp(2);
		saveToFile();
		return 2;
	}
	
	/**
	 * Liefert die maximale Berufsstufe zurück
	 * @return int
	 */
	public int getMaxLevel() {
		String[] rawHash = userJobHash.split(",");
		String[] jobHash = rawHash[1].split("-");
		int maxLevel = 0;
		for(int i = 0; i < jobHash.length; i++) {
			if(plugin.jobs.getInt("Beruf." + jobHash[i] + ".MaxStufe") >= maxLevel)
				maxLevel = plugin.jobs.getInt("Beruf." + jobHash[i] + ".MaxStufe");
		}
		return maxLevel;
	}
	
	/**
	 * Registriert den Spieler in der Spielerliste, sie ist sonst nicht gültig.
	 * @return void
	 */
	private void registerUser() {
		plugin.fWCore.addUserList(userName, this);
		return;
	}

	/**
	 * Lädt einen Spieler aus der Datei in den Cache
	 * @return void
	 */
	private void loadFromFile() {
		getUser();
		
		userName = user.getString("Allgemein.Name");
		userLevel = user.getInt("Allgemein.Stufe", 1);
		userExp = user.getDouble("Allgemein.Erfahrungspunkte", 0);
		userJobHash = user.getString("Allgemein.Berufs-Hash", "1,XX");
		
		userLevelUpExp = user.getInt("System.Aufstieg-Erfahrungspunkte", plugin.fWUsers.fWLevels.calculateLevelExp(2));
		userDailyPlaytimeExp = user.getInt("System.Taegliche-Spielzeit-Exp");
		userDailyBlockbreakExp = user.getInt("System.Taegliche-Abbau-Exp");
		userDailyReset = user.getLong("System.Taeglicher-Reset");
		
		saveToFile();
	}
	
	/**
	 * Speichert einen Spieler aus dem Cache in die Datei
	 * @return void
	 */
	public void saveToFile() {
		getUser();
		
		user.set("Allgemein.Name", userName);
		user.set("Allgemein.Stufe", userLevel);
		user.set("Allgemein.Erfahrungspunkte", userExp);
		user.set("Allgemein.Berufs-Hash", userJobHash);
		
		user.set("System.Aufstieg-Erfahrungspunkte", userLevelUpExp);
		user.set("System.Taegliche-Spielzeit-Exp", userDailyPlaytimeExp);
		user.set("System.Taegliche-Abbau-Exp", userDailyBlockbreakExp);
		user.set("System.Taeglicher-Reset", userDailyReset);
		
		saveUser();
	}
	
	/**
	 * Lädt die FileConfiguration des Spielers in eine Variable
	 * @return void
	 */
	private void reloadUser() {
		if (userFile == null) {
			userFile = new File(plugin.getDataFolder(), "/user/" + this.player.getUniqueId() + ".yml");
		}
		user = YamlConfiguration.loadConfiguration(userFile);
	}

	/**
	 * Liefert die FileConfiguration des Spielers zurück
	 * @return FileConfiguration
	 */
	private FileConfiguration getUser() {
		if (user == null) {
			reloadUser();
		}
		return user;
	}
	
	/**
	 * Speichert die FileConfiguration in die Datei
	 * @return void
	 */
	private void saveUser() {
		if (user == null || userFile == null) {
			return;
		}
		try {
			user.save(userFile);
		} catch (IOException ex) {
			Logger.getLogger(JavaPlugin.class.getName()).log(Level.SEVERE, "Could not save config to " + userFile, ex);
		}
	}
	
}