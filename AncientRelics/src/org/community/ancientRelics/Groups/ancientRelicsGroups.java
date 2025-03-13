package org.community.ancientRelics.Groups;

import org.bukkit.entity.Player;
import org.community.ancientRelics.ancientRelics;
import org.community.ancientRelics.Commands.*;
import org.community.ancientRelics.Configs.*;
import org.community.ancientRelics.Groups.Classes.Groups;

public class ancientRelicsGroups {
		
	private final ancientRelics plugin;
	
    public ancientRelicsGroupsUser aRGroupsUser = null;
    public ancientRelicsCommandGroups aRCommandGroups = null;
    public ancientRelicsGroupsInvite aRGroupsInvite = null;
	 	 
	public ancientRelicsGroups(ancientRelics plugin)
	{
		this.plugin = plugin;
	}

	/**
	 * Initialisiert die Configuration des Gruppen-Moduls
	 * @return boolean - "true", wenn erfolgreich
	 */
	public boolean initiateGroups() {

		aRGroupsUser = new ancientRelicsGroupsUser(plugin);
		aRCommandGroups = new ancientRelicsCommandGroups(plugin);
		aRGroupsInvite = new ancientRelicsGroupsInvite(plugin);
		
		if(aRGroupsUser.initiateConfig()){
			plugin.LogInfo("initialized: (Groups) user.yml");
		} else {
			plugin.LogWarning("error: (Groups) user.yml couldn't be initiated.");
			return false;
		}
		
		plugin.aRCache.loadGroupsList();
		
		return true;
		
	}

	/**
	 * Speichert und beendet die Configuration des Gruppen-Moduls
	 * @return boolean - "true", wenn erfolgreich
	 */
	public boolean deactivateGroups() {
		
		aRGroupsUser.saveUserConfig();
		
		plugin.aRCache.saveGroupsList();
		
		plugin.LogInfo("deactivated: (Groups) user.yml");
		
		return true;
		
	}
	
	/**
	 * Aktualisiert die Spielergruppe im Spieler-Gruppen-Register
	 * @param player Die Spielerklasse
	 * @param group Die Gruppenklasse
	 * @return void
	 */
	public void updatePlayerGroupInfo(Player player, Groups group) {
		
		if(group == null){
			aRGroupsUser.user.set("Spieler." + player.getName() + ".Gruppe", null);
		} else {
			aRGroupsUser.user.set("Spieler." + player.getName() + ".Gruppe", group.getGroupHash());
		}		
		return;
		
	}
	
	/**
	 * Aktualisiert die Spielergruppe im Spieler-Gruppen-Register
	 * @param string Der Spielername
	 * @param group Die Gruppenklasse
	 * @return void
	 */
	public void updatePlayerGroupInfo(String player, Groups group) {
		
		if(group == null){
			aRGroupsUser.user.set("Spieler." + player + ".Gruppe", null);
		} else {
			aRGroupsUser.user.set("Spieler." + player + ".Gruppe", group.getGroupHash());
		}		
		return;
		
	}

	/**
	 * Liefert zurück, ob der Spieler in einer Gruppe ist.
	 * @param player Die Spielerklasse
	 * @return Boolean - "false" wenn der Spieler keine Gruppe hat
	 */
	public boolean isPlayerInGroup(Player player) {
		
		if(getPlayerGroupInfo(player) != null)
			return true;
		
		return false;
		
	}

	/**
	 * Liefert die Spielergruppe zurück
	 * @param player Die Spielerklasse
	 * @return Groups - "null" wenn der Spieler keine Gruppe hat
	 */
	public Groups getPlayerGroupInfo(Player player) {
		
		String groupHash = aRGroupsUser.user.getString("Spieler." + player.getName() + ".Gruppe");
		if(groupHash == null)
			return null;
		
		//plugin.getServer().broadcastMessage("[DEBUG]: GroupHash: " + groupHash);
		
		return parsePlayerGroupInfo(player, groupHash);
		
	}

	/**
	 * Durchsucht alle Gruppen nach dem passenden Hash des Spielers und liefert die korrekte Gruppe zurück
	 * @param player Die Spielerklasse
	 * @param hash Der Hashwert der Gruppe aus dem Spielerregister
	 * @return Groups - "null" wenn der Hash mit keiner Gruppe übereinstimmt
	 */
	private Groups parsePlayerGroupInfo(Player player, String hash) {
		
		for(int i = 0; i < plugin.aRCore.sizeGroupsList(); i++){
			//plugin.getServer().broadcastMessage("Gruppe " + plugin.aRCore.getGroupsList(i).getGroupName() + " gefunden.");
			if(plugin.aRCore.getGroupsList(i).getGroupHash().contains(hash)) {
				//plugin.getServer().broadcastMessage("Gruppe " + plugin.aRCore.getGroupsList(i).getGroupName() + " gefunden.");
				return plugin.aRCore.getGroupsList(i);
			}
		}
		return null;
		
	}
	
}