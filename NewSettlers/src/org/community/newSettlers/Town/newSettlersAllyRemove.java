package org.community.newSettlers.Town;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.community.newSettlers.newSettlers;
import org.community.newSettlers.Town.Town;

public class newSettlersAllyRemove {
		
	private final newSettlers plugin;
	 	 
	public newSettlersAllyRemove(newSettlers plugin)
	{
		this.plugin = plugin;
	}

	/**
	 * Sendet eine Stadt an einen Spieler
	 * @return boolean - "true", wenn erfolgreich
	 */
	public boolean sendRequest(Player requestingPlayer, String requestTag, Player askedPlayer) {

		String townTag = plugin.newRequest.get(requestingPlayer);
		String[] townString = townTag.split(":");
		Town askedTown = plugin.nSCore.getTown(townString[1].replace("#", ""));
		
		plugin.newRequest.put(requestingPlayer, requestTag);
		
		requestingPlayer.sendMessage("Möchtest du wirklich die Allianz mit " + askedTown.getName() + " auflösen?");
		
		askedPlayer.sendMessage("Bestätige die Anfrage mit /ja oder /nein.");
		
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
			
		Town requestTown = plugin.nSCore.getPlayerTown(requester);
		String allyTownTag = plugin.newRequest.get(requester);
		String[] allyTownString = allyTownTag.split(":");
		Town allyTown = plugin.nSCore.getTown(allyTownString[1].replace("#", ""));
		requestTown.removeAlly(allyTown.getName());
		allyTown.removeAlly(requestTown.getName());
		requestTown.sendTownMessage(ChatColor.YELLOW + "Ihr habt das Bündnis mit " + allyTown.getName() + " aufgelöst.");
		allyTown.sendTownMessage(ChatColor.YELLOW + requestTown.getName() + " hat das Bündnis mit deiner Stadt aufgelöst.");
		return;
	}
	
	/**
	 * Lehnt die Gruppenanfrage ab
	 * @param requester Die Spielerklasse des Einladenden
	 * @param invited Die Spielerklasse des Eingeladenen
	 * @return void
	 */
	public void denyRequest(Player requester, Player invited) {

		plugin.nSAllyRemove.deleteRequest(requester, invited);
		return;
	}
	
}