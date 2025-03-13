package org.community.newSettlers.Town;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.community.newSettlers.newSettlers;
import org.community.newSettlers.Town.Town;

public class newSettlersBuildingInvite {
		
	private final newSettlers plugin;
	 	 
	public newSettlersBuildingInvite(newSettlers plugin)
	{
		this.plugin = plugin;
	}

	/**
	 * Sendet eine Stadt an einen Spieler
	 * @return boolean - "true", wenn erfolgreich
	 */
	public boolean sendRequest(Player requestingPlayer, String requestTag, Player askedPlayer) {

		//String townTag = plugin.newRequest.get(requestTag);
		String[] townString = requestTag.split(":");
		Town town = plugin.nSCore.getTown(townString[1].replace("#", ""));
		
		plugin.newRequest.put(requestingPlayer, requestTag);
		
		requestingPlayer.sendMessage(ChatColor.DARK_GREEN + "Möchtest du " + town.getName() + " wirklich das Gebäude freischalten?");
		
		askedPlayer.sendMessage(ChatColor.DARK_GREEN + "Bestätige die Anfrage mit " + ChatColor.GOLD + "/ja" + ChatColor.DARK_GREEN + " oder" + ChatColor.GOLD + " /nein.");
		
		plugin.newApproval.put(askedPlayer, requestingPlayer);
		
		return true;
		
	}

	/**
	 * L�scht verarbeitete Gruppenanfragen
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
			
		String townTag = plugin.newRequest.get(requester);
		String[] townString = townTag.split(":");
		Town town = plugin.nSCore.getTown(townString[1].replace("#", ""));
		String building =townString[2].replace("#", "");
		requester.sendMessage(ChatColor.DARK_GREEN + town.getName() + " verfügt nun über das Gebäude " + building);
		town.addBuilding(building);
		plugin.nSBuildingInvite.deleteRequest(requester, invited);
		return;
	}
	
	/**
	 * Lehnt die Gruppenanfrage ab
	 * @param requester Die Spielerklasse des Einladenden
	 * @param invited Die Spielerklasse des Eingeladenen
	 * @return void
	 */
	public void denyRequest(Player requester, Player invited) {

		plugin.nSBuildingInvite.deleteRequest(requester, invited);
		requester.sendMessage(ChatColor.DARK_RED + "Du hast das Gebäude nicht freigeschaltet."); 
		return;
	}
	
}