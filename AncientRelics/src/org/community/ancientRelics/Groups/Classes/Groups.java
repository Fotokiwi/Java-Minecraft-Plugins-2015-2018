package org.community.ancientRelics.Groups.Classes;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;
import org.community.ancientRelics.ancientRelics;

public class Groups {
		
	private final ancientRelics plugin;
	
	private FileConfiguration group = null;
	private File groupFile = null;
    
    private String groupHash;
	private String groupName;
	
	private Boolean groupPvPStatus = false;
	private Boolean groupPotionStatus = false;
	
	private String groupLeader;
	private List<String> groupMembers = new ArrayList<String>();	

    private Scoreboard board = null;
	private Team team = null;
	private Objective objective = null;
	
	/**
	 * Server-Startup Initialisierung.
	 * Die Gruppe wird aus der Datei in den Cache geladen und registriert.
	 * @param plugin Die Hauptvariable des Plugins
	 * @param hash Der Hash der Gruppe, ausgelesen aus dem Dateinamen
	 * @param dummy Eine Dummy-Variable, default: True
	 */
	public Groups(ancientRelics plugin, String hash, boolean dummy) {
		this.plugin = plugin;
		this.groupHash = hash;
		
		loadFromFile();
		registerGroup();
		
		registerTeam();
		setScoreboard();
	}
	 	 
	public Groups(ancientRelics plugin, String name, String leader)	{
		this.plugin = plugin;
		this.groupName = "" + name;
		this.groupHash = "" + System.currentTimeMillis();
		this.groupLeader = leader;
		
		registerGroup();
		addLeader(plugin.getPlayerByName(leader));
		saveToFile();
		
		registerTeam();
		setScoreboard();
		setPlayerTeam(plugin.getPlayerByName(groupLeader));
		setPlayerScoreboard(plugin.getPlayerByName(groupLeader));
	}
	
	/**
	 * Registriert die Gruppe in der Gruppenliste, sie ist sonst nicht g�ltig.
	 * @return void
	 */
	private void registerGroup() {
		plugin.aRCore.addGroupsList(this);
		return;
	}
	
	/**
	 * Registriert die Gruppe als offizielles Minecraft-Team
	 * @return void
	 */
	private void registerTeam() {	
	    board = plugin.scoreboardManager.getNewScoreboard();
		team = board.registerNewTeam(groupHash);
		team.setDisplayName(groupName);
		team.setPrefix(ChatColor.GREEN + "");
		team.setSuffix(" [" + groupName +"]" + ChatColor.WHITE + "");
		team.setCanSeeFriendlyInvisibles(true);
		return;
	}
	
	/**
	 * Registriert das Scoreboard f�r die Gruppe
	 * @return void
	 */
	private void setScoreboard() {	
		objective = board.registerNewObjective(groupHash, "health");
		objective.setDisplaySlot(DisplaySlot.BELOW_NAME);
		objective.setDisplayName("HP");
		return;
	}
	
	/**
	 * Registriert das Scoreboard f�r den Spieler
	 * @return void
	 */
	private void setPlayerScoreboard(Player player) {	
		player.setScoreboard(board);
		return;
	}
	
	/**
	 * Registriert das Scoreboard f�r den Spieler
	 * @return void
	 */
	public void setPlayerTeam(Player player) {	
		team.addPlayer(plugin.getOfflinePlayerByName(player.getName()));
		setPlayerScoreboard(player);
		//player.sendMessage(team.getName());
		return;
	}
	
	/**
	 * Stellt alle Gruppeninformatione im Chat dar.
	 * @return void
	 */
	public void displayGroupInfo(Player player) {
		player.sendMessage("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		player.sendMessage("Gruppe:   " + groupName);
		player.sendMessage("");
		player.sendMessage("PvP:   " + groupPvPStatus);
		player.sendMessage("Tränke:   " + groupPotionStatus);
		player.sendMessage("");
		player.sendMessage("Anführer:   " + groupLeader);
		player.sendMessage("Mitglieder: " + groupMembers.toString().replace("[", "").replace("]", ""));
		player.sendMessage("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		return;
	}
	
	/**
	 * Liefert den Namen der Gruppe
	 * @return String Gibt den Namen der Gruppe zur�ck
	 */
	public String getGroupName() {
		return groupName;
	}
	
	/**
	 * Setzt den Namen der Gruppe
	 * @param newName Der neue Name der Gruppe
	 * @return void
	 */
	public void setGroupName(String newName) {
		groupName = newName;
		this.team.setSuffix(" [" + groupName +"]" + ChatColor.WHITE + "");
		saveGroup();
		return;
	}
	
	/**
	 * Liefert den Hashwert der Gruppe
	 * @return String
	 */
	public String getGroupHash() {
		return groupHash;
	}
	
	/**
	 * Setzt den Anf�hrer der Gruppe
	 * @param player Die Spielerklasse des Anf�hrers
	 * @return void
	 */
	public void addLeader(Player player) {
		plugin.playerGroupMembership.put(player, this);
		plugin.aRGroups.updatePlayerGroupInfo(player, this);
		saveGroup();
		return;
	}
	
	/**
	 * F�gt einen Spieler der Gruppe hinzu
	 * @param player Die Spielerklasse des neuen Mitglieds
	 * @return void
	 */
	public void addMember(Player player) {
		plugin.playerGroupMembership.put(player, this);
		groupMembers.add(player.getName().toString());
		plugin.aRGroups.updatePlayerGroupInfo(player, this);
		saveGroup();
		team.addPlayer(plugin.getOfflinePlayerByName(player.getName()));
		setPlayerScoreboard(player);
		return;
	}
	
	/**
	 * Entfernt einen Spieler aus der Mitglieder-Liste (bei Bef�rderung)
	 * @param player Die Spielerklasse des Mitglieds
	 * @return void
	 */
	public void demoteMember(Player player) {
		for(int i = 0; i < groupMembers.size(); i++){
			if(groupMembers.get(i).equalsIgnoreCase(player.getName().toString())){
				player.sendMessage("Du bist jetzt Anführer der Gruppe.");
				groupMembers.remove(i);
			}
		}
		saveGroup();
		return;
	}
	
	/**
	 * Entfernt einen Spieler aus der Gruppe
	 * @param player Die Spielerklasse des zu entfernenden Mitglieds
	 * @return void
	 */
	@SuppressWarnings("deprecation")
	public void removeMember(Player player) {
		plugin.playerGroupMembership.remove(player);
		plugin.aRGroups.updatePlayerGroupInfo(player, null);
		if(plugin.getServer().getOfflinePlayer(player.getName()) == null) {
			team.removePlayer(plugin.getServer().getPlayer(player.getName()));
		} else {
			team.removePlayer(plugin.getServer().getOfflinePlayer(player.getName()));
		}		
		for(int i = 0; i < groupMembers.size(); i++){
			if(groupMembers.get(i).equalsIgnoreCase(player.getName())){
				player = plugin.getPlayerByName(groupMembers.get(i));
				if(player != null) {
					plugin.playerGroupMembership.put(plugin.getPlayerByName(groupMembers.get(i)), null);
					player = null;
				}
				groupMembers.remove(i);
			}
		}		
		saveGroup();
		if(!isExistenceAllowed()){
			eraseGroup();
		}
		return;
	}
	
	/**
	 * Entfernt einen Spieler aus der Gruppe
	 * @param player Die Spielerklasse des zu entfernenden Mitglieds
	 * @return void
	 */
	@SuppressWarnings("deprecation")
	public void removeAllMembers() {
		Player player = null;
		OfflinePlayer offplayer = null;
		for(int i = 0; i < groupMembers.size(); i++){
			plugin.aRGroups.updatePlayerGroupInfo(groupMembers.get(i), null);
			if(plugin.getPlayerByName(groupMembers.get(i)) != null) {
				player = plugin.getPlayerByName(groupMembers.get(i));
				team.removePlayer(plugin.getServer().getPlayer(player.getName()));
			} else {
				offplayer = plugin.getOfflinePlayerByName(groupMembers.get(i));
				if(offplayer != null) {
					team.removePlayer(plugin.getServer().getOfflinePlayer(offplayer.getName()));
				}
			}
			if(player != null) {
				plugin.playerGroupMembership.put(plugin.getPlayerByName(groupMembers.get(i)), null);
				player.sendMessage("Deine Gruppe hat sich aufgelöst.");
				player = null;
			}
		}
		plugin.playerGroupMembership.put(plugin.getPlayerByName(groupLeader), null);
		plugin.aRGroups.updatePlayerGroupInfo(groupLeader, null);
		groupMembers.clear();
		saveGroup();
		return;
	}
	
	/**
	 * Liefert den Namen des Gruppenleiters zur�ck
	 * @return String
	 */
	public String getLeader() {
		return groupLeader;
	}
	
	/**
	 * Setzt den Namen des neuen Gruppenleiters
	 * @param playerName Der Name des neuen Gruppenleiters
	 * @return void
	 */
	public void setLeader(String playerName) {
		groupLeader = playerName;
		saveGroup();
		return;
	}
	
	public List<String> getMembers() {
		return groupMembers;
	}
	
	/**
	 * Z�hlt die Mitglieder einer Gruppe (ohne Anf�hrer)
	 * @return int
	 */
	public int getMemberCount() {		
		return groupMembers.size();
	}
	
	/**
	 * Pr�ft die Mitgliederanzahl und entfernt die Gruppe bei weniger als 2 Mitgliedern
	 * @return boolean
	 */
	public boolean isExistenceAllowed() {
		if(getMemberCount() < 1)
			return false;
		return true;
	}
	
	/**
	 * �bergibt den aktuellen PvP-Status der Gruppe
	 * @return boolean
	 */
	public boolean getPvPStatus() {		
		return groupPvPStatus;
	}
	
	/**
	 * Setzt den PvP-Status innerhalb der Gruppe von True auf False auf True
	 * @return void
	 */
	public void togglePvP() {
		if(groupPvPStatus){
			groupPvPStatus = false;
		} else {
			groupPvPStatus = true;
		}		
		saveGroup();
		return;
	}
	
	/**
	 * �bergibt den aktuellen Potion-Status der Gruppe
	 * @return boolean
	 */
	public boolean getPotionStatus() {		
		return groupPotionStatus;
	}
	
	/**
	 * Setzt den Potion-Status innerhalb der Gruppe von True auf False auf True
	 * @return void
	 */
	public void togglePotion() {
		if(groupPotionStatus){
			groupPotionStatus = false;
		} else {
			groupPotionStatus = true;
		}		
		saveGroup();
		return;
	}

	/**
	 * L�dt eine Gruppe aus der Datei in den Cache
	 * @return void
	 */
	@SuppressWarnings("unchecked")
	private void loadFromFile() {
		getGroup();
		
		board = null;
		team = null;
		objective = null;
		
		groupName = group.getString("General.Name");
		groupHash = group.getString("General.Hash");
		
		groupPvPStatus = group.getBoolean("General.Flag.PvP", false);
		groupPotionStatus = group.getBoolean("General.Flag.Potion", false);
		
		groupLeader 	= group.getString("Fellower.Leader");
		groupMembers	= (List<String>) group.getList("Fellower.Members");
		
		saveGroup();
	}
	
	/**
	 * Speichert eine Gruppe aus dem Cache in die Datei
	 * @return void
	 */
	public void saveToFile() {
		getGroup();
		
		group.set("General.Name", groupName);
		group.set("General.Hash", groupHash);
		
		group.set("General.Flag.PvP", groupPvPStatus);
		group.set("General.Flag.Potion", groupPotionStatus);

		group.set("Fellower.Leader", groupLeader);
		group.set("Fellower.Members", groupMembers);
		
		saveGroup();
	}
	
	/**
	 * L�dt die FileConfiguration der Gruppe in eine Variable
	 * @return void
	 */
	private void reloadGroup() {
		if (groupFile == null) {
			groupFile = new File(plugin.getDataFolder(), "/groups/groupConfig/" + groupHash + ".yml");
		}
		group = YamlConfiguration.loadConfiguration(groupFile);
	}

	/**
	 * Liefert die FileConfiguration der Gruppe zur�ck
	 * @return FileConfiguration
	 */
	private FileConfiguration getGroup() {
		if (group == null) {
			reloadGroup();
		}
		return group;
	}
	
	/**
	 * Speichert die FileConfiguration in die Datei
	 * @return void
	 */
	private void saveGroup() {
		if (group == null || groupFile == null) {
			return;
		}
		try {
			group.save(groupFile);
		} catch (IOException ex) {
			Logger.getLogger(JavaPlugin.class.getName()).log(Level.SEVERE, "Could not save config to " + groupFile, ex);
		}
	}
	
	/**
	 * Entfernt die Gruppe aus dem System
	 * @return void
	 */
	public void eraseGroup() {
		if (groupFile == null) {
			return;
		} else {
			removeAllMembers();
			plugin.aRCore.removeGroupsList(this);
			group = null;
			groupFile.delete();		
			team.unregister();
			return;
		}
	}
	
}