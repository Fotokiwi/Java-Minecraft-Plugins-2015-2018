package org.community.ancientRelics.PvP;

import org.bukkit.entity.Player;
import org.community.ancientRelics.ancientRelics;

public class ancientRelicsDuellInvite {
		
	private final ancientRelics plugin;
	 	 
	public ancientRelicsDuellInvite(ancientRelics plugin)
	{
		this.plugin = plugin;
	}

	/**
	 * Sendet eine Gruppenanfrage an einen Spieler
	 * @return boolean - "true", wenn erfolgreich
	 */
	public boolean sendRequest(Player requestingPlayer, String duellTag, Player askedPlayer) {

		plugin.newRequest.put(requestingPlayer, duellTag);
		
		requestingPlayer.sendMessage("Du hast " + askedPlayer.getName() + " zu einem Duell herausgefordert.");
		
		askedPlayer.sendMessage(requestingPlayer.getName() + " hat dich zu einem Duell herausgefordert.");
		askedPlayer.sendMessage("Bestätige die Anfrage mit /akzeptieren oder /ablehnen.");
		
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
	 * Akzeptiert die Duellanfrage
	 * @param requester Die Spielerklasse des Einladenden
	 * @param invited Die Spielerklasse des Eingeladenen
	 * @return void
	 */
	public void acceptRequest(Player requester, Player invited) {
		
		plugin.duellModus.put(requester, invited);
		plugin.duellModus.put(invited, requester);
		requester.sendMessage(invited.getName() + " hat deine Duellanfrage angenommen.");
		requester.sendMessage("Möge der Kampf beginnen!");
		invited.sendMessage("Möge der Kampf beginnen!");
		return;
		
	}
	
	/**
	 * Lehnt die Duellanfrage ab
	 * @param requester Die Spielerklasse des Einladenden
	 * @param invited Die Spielerklasse des Eingeladenen
	 * @return void
	 */
	public void denyRequest(Player requester, Player invited) {

		plugin.aRPvP.aRDuellInvite.deleteRequest(requester, invited);
		requester.sendMessage(invited.getName() + " hat deine Duelleinladung abgelehnt."); 
		return;
		
	}
	
	/**
	 * Beendet das Duell durch Fremdeinfluss
	 * @param player Die Spielerklasse eines der beiden Spieler
	 * @return void
	 */
	public void cancelDuell(Player player) {

		Player secondPlayer = plugin.duellModus.get(player);
		player.sendMessage("Das Duell wurde durch Einmischung beendet.");
		secondPlayer.sendMessage("Das Duell wurde durch Einmischung beendet");
		plugin.duellModus.put(player, null);
		plugin.duellModus.put(secondPlayer, null);
		return;
		
	}
	
}