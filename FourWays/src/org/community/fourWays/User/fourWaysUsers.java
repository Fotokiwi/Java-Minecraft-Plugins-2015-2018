package org.community.fourWays.User;

import org.bukkit.entity.Player;
import org.community.fourWays.fourWays;
import org.community.fourWays.Commands.fourWaysCommandUser;

public class fourWaysUsers {
		
	private final fourWays plugin;
	
    public fourWaysCommandUser fWCommandUser = null;
    public fourWaysLevels fWLevels = null;
	 	 
	public fourWaysUsers(fourWays plugin)
	{
		this.plugin = plugin;
	}

	/**
	 * Initialisiert die Configuration des Gruppen-Moduls
	 * @return boolean - "true", wenn erfolgreich
	 */
	public boolean initiateUsers() {

		fWCommandUser = new fourWaysCommandUser(plugin);
		fWLevels = new fourWaysLevels(plugin);
		
		plugin.fWCache.loadUserList();
		
		return true;
		
	}

	/**
	 * Speichert und beendet die Configuration des Gruppen-Moduls
	 * @return boolean - "true", wenn erfolgreich
	 */
	public boolean deactivateUsers() {
		
		plugin.fWCache.saveUserList();
		
		return true;
		
	}

	/**
	 * Liefert die Spielerklasse zur�ck
	 * @param player Die Spielerklasse
	 * @return User - "null" wenn der Spieler keine Klasse hat
	 */
	public User getPlayerInfo(Player player) {
		
		return plugin.fWCore.getUserClass(player);
		
	}

	/**
	 * Schreibt allen aktiven Spielern Spielzeit-Exp gut
	 * @return void
	 */
	public void grantPlaytimeExp() {
		
		Player[] players = plugin.getServer().getOnlinePlayers();
		User user = null;
		int exp = plugin.config.getInt("Config.Levels.PlaytimeExpPerTick");
		
		for(int i = 0; i < players.length; i++) {
			if(plugin.positionCache.get(players[i]) == null){
				plugin.positionCache.put(players[i], players[i].getLocation());
			}
			if(players[i].getLocation().getWorld() != plugin.positionCache.get(players[i]).getWorld()) {
				plugin.positionCache.put(players[i], players[i].getLocation());
			}
			if(players[i].getLocation().distance(plugin.positionCache.get(players[i])) >= 0.5) {
				user = plugin.fWUsers.getPlayerInfo(players[i]);
				user.addPlaytimeExp(exp);
				plugin.positionCache.put(players[i], players[i].getLocation());
				//players[i].sendMessage("Exp erhalten: " + exp);
			} else {
				//players[i].sendMessage("AFK gibt es keine Exp!");
			}
		}
		return;
		
	}
	
}