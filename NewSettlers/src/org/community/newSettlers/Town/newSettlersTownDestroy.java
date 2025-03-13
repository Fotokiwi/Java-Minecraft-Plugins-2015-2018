package org.community.newSettlers.Town;

import org.bukkit.entity.Player;
import org.community.newSettlers.newSettlers;
import org.community.newSettlers.Town.Town;

public class newSettlersTownDestroy {
		
	private final newSettlers plugin;
	 	 
	public newSettlersTownDestroy(newSettlers plugin)
	{
		this.plugin = plugin;
	}

	/**
	 * Sendet eine Stadt an einen Spieler
	 * @return boolean - "true", wenn erfolgreich
	 */
	public boolean sendRequest(Player requestingPlayer, String requestTag, Player askedPlayer) {

		Town town = plugin.nSCore.getPlayerTown(requestingPlayer);
		
		plugin.newRequest.put(requestingPlayer, requestTag);
		
		requestingPlayer.sendMessage("Möchtest du wirklich deine Stadt " + town.getName() + " auflösen?");
		
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
			
		Town town = plugin.nSCore.getPlayerTown(requester);
		town.eraseTown();
		//requester.sendMessage(invited.getName() + " hat deine Einladung angenommen."); 
		return;
	}
	
	/**
	 * Lehnt die Gruppenanfrage ab
	 * @param requester Die Spielerklasse des Einladenden
	 * @param invited Die Spielerklasse des Eingeladenen
	 * @return void
	 */
	public void denyRequest(Player requester, Player invited) {

		plugin.nSTownDestroy.deleteRequest(requester, invited);
		requester.sendMessage("Du hast deine Stadt nicht aufgelöst."); 
		return;
	}
	
}