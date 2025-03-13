package org.community.newSettlers.Town;

import org.bukkit.entity.Player;
import org.community.newSettlers.newSettlers;
import org.community.newSettlers.Town.Town;

public class newSettlersTownInvite {
		
	private final newSettlers plugin;
	 	 
	public newSettlersTownInvite(newSettlers plugin)
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
		
		requestingPlayer.sendMessage("Du hast " + askedPlayer.getName() + " in deine Stadt " + town.getName() + " eingeladen.");
		
		askedPlayer.sendMessage(requestingPlayer.getName() + " hat dich in seine Stadt " + town.getName() + " eingeladen.");
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
		if(System.currentTimeMillis() >= plugin.user.getLong("Spieler." + invited.getUniqueId().toString() + "." + town.getName() + ".TownJoinCooldown") + 259200000) {
			town.addMember(invited);
			requester.sendMessage(invited.getName() + " hat deine Einladung angenommen."); 
			invited.sendMessage("Du bist nun Bürger der Stadt " + town.getName());
			plugin.nSTownInvite.deleteRequest(requester, invited);
			return;
		} else {
			requester.sendMessage(invited.getName() + " darf deiner Stadt noch nicht beitreten."); 
			invited.sendMessage("Es ist zu kurz her, seit du diese Stadt verlassen hast!");
			plugin.nSTownInvite.deleteRequest(requester, invited);
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

		plugin.nSTownInvite.deleteRequest(requester, invited);
		requester.sendMessage(invited.getName() + " hat deine Einladung abgelehnt."); 
		return;
	}
	
}