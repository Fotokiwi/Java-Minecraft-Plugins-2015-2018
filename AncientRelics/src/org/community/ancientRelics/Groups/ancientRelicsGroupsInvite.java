package org.community.ancientRelics.Groups;

import org.bukkit.entity.Player;
import org.community.ancientRelics.ancientRelics;
import org.community.ancientRelics.Groups.Classes.Groups;

public class ancientRelicsGroupsInvite {
		
	private final ancientRelics plugin;
	 	 
	public ancientRelicsGroupsInvite(ancientRelics plugin)
	{
		this.plugin = plugin;
	}

	/**
	 * Sendet eine Gruppenanfrage an einen Spieler
	 * @return boolean - "true", wenn erfolgreich
	 */
	public boolean sendRequest(Player requestingPlayer, String groupName, Player askedPlayer) {

		plugin.newRequest.put(requestingPlayer, groupName);
		
		requestingPlayer.sendMessage("Du hast " + askedPlayer.getName() + " in deine Gruppe " + groupName + " eingeladen.");
		
		askedPlayer.sendMessage(requestingPlayer.getName() + " hat dich in seine Gruppe " + groupName + " eingeladen.");
		askedPlayer.sendMessage("Bestätige die Anfrage mit /akzeptieren oder /ablehnen.");
		
		plugin.newApproval.put(askedPlayer, requestingPlayer);
		
		return true;
		
	}

	/**
	 * Löscht verarbeitete Gruppenanfragen
	 * @return boolean - "true", wenn erfolgreich
	 */
	public boolean deleteRequest(Player requestingPlayer, Player askedPlayer) {
		
		plugin.newApproval.remove(askedPlayer);
		plugin.newRequest.remove(requestingPlayer);
		
		return true;
		
	}
	
	/**
	 * Akzeptiert die Gruppenanfrage
	 * @param requester Die Spielerklasse des Einladenden
	 * @param invited Die Spielerklasse des Eingeladenen
	 * @return void
	 */
	public void acceptRequest(Player requester, Player invited) {
		if(plugin.aRGroups.getPlayerGroupInfo(requester) == null) {									
			Groups group = new Groups(plugin, plugin.newRequest.get(requester), requester.getName());
			group.addMember(invited);
			plugin.aRGroups.aRGroupsInvite.deleteRequest(requester, invited);
			requester.sendMessage(invited.getName() + " hat deine Einladung angenommen.");
			return;
		} else {
			plugin.aRGroups.updatePlayerGroupInfo(invited, plugin.aRGroups.getPlayerGroupInfo(requester));
			plugin.aRGroups.aRGroupsInvite.deleteRequest(requester, invited);
			plugin.aRGroups.getPlayerGroupInfo(requester).addMember(invited);
			requester.sendMessage(invited.getName() + " hat deine Einladung angenommen."); 
			return;
		}
	}
	
	/**
	 * Lehnt die Gruppenanfrage ab
	 * @param requester Die Spielerklasse des Einladenden
	 * @param invited Die Spielerklasse des Eingeladenen
	 * @return void
	 */
	public void denyRequest(Player requester, Player invited) {

		plugin.aRGroups.aRGroupsInvite.deleteRequest(requester, invited);
		requester.sendMessage(invited.getName() + " hat deine Einladung abgelehnt."); 
		return;
	}
	
}