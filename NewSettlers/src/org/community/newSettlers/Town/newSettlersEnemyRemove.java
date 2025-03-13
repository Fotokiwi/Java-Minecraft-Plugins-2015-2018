package org.community.newSettlers.Town;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.community.newSettlers.newSettlers;
import org.community.newSettlers.Town.Town;

public class newSettlersEnemyRemove {
		
	private final newSettlers plugin;
	 	 
	public newSettlersEnemyRemove(newSettlers plugin)
	{
		this.plugin = plugin;
	}

	/**
	 * Sendet eine Stadt an einen Spieler
	 * @return boolean - "true", wenn erfolgreich
	 */
	public boolean sendRequest(Player requestingPlayer, String requestTag, Player askedPlayer) {

		Town requestTown = plugin.nSCore.getPlayerTown(requestingPlayer);
		String townTag = plugin.newRequest.get(requestingPlayer);
		String[] townString = townTag.split(":");
		Town askedTown = plugin.nSCore.getTown(townString[1].replace("#", ""));
		
		plugin.newRequest.put(requestingPlayer, requestTag);
		
		requestingPlayer.sendMessage("Du hast " + askedTown.getName() + " im Namen deiner Stadt einen Waffenstillstand angeboten.");
		
		askedPlayer.sendMessage(requestTown.getName() + " hat deiner Stadt einen Waffenstillstand angeboten.");
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
		Town askedTown = plugin.nSCore.getPlayerTown(invited);
		requestTown.removeEnemy(askedTown.getName());
		askedTown.removeEnemy(requestTown.getName());
		requestTown.sendTownMessage(ChatColor.YELLOW + "Ihr liegt mit " + askedTown.getName() + " nicht mehr im Krieg.");
		askedTown.sendTownMessage(ChatColor.YELLOW + "" + requestTown.getName() + " nicht mehr im Krieg.");
		return;
	}
	
	/**
	 * Lehnt die Gruppenanfrage ab
	 * @param requester Die Spielerklasse des Einladenden
	 * @param invited Die Spielerklasse des Eingeladenen
	 * @return void
	 */
	public void denyRequest(Player requester, Player invited) {

		Town askedTown = plugin.nSCore.getPlayerTown(invited);
		
		plugin.nSEnemyRemove.deleteRequest(requester, invited);
		requester.sendMessage(askedTown.getName() + " hat dein Waffenstillstandsangebot abgelehnt."); 
		return;
	}
	
}