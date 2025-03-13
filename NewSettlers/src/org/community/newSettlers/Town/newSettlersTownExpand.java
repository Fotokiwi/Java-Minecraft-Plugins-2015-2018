package org.community.newSettlers.Town;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.community.newSettlers.newSettlers;
import org.community.newSettlers.Town.Town;

public class newSettlersTownExpand {
		
	private final newSettlers plugin;
	 	 
	public newSettlersTownExpand(newSettlers plugin)
	{
		this.plugin = plugin;
	}

	/**
	 * Sendet eine Stadt an einen Spieler
	 * @return boolean - "true", wenn erfolgreich
	 */
	public boolean sendRequest(Player requestingPlayer, String requestTag, Player askedPlayer) {
		
		plugin.newRequest.put(requestingPlayer, requestTag);
		
		requestingPlayer.sendMessage(ChatColor.DARK_GREEN + "Bist du sicher, dass du mit deiner Stadt expandieren möchtest?");
		requestingPlayer.sendMessage(ChatColor.DARK_GREEN + "Deine Unterhaltskosten werden sich spürbar erhöhen!");
		requestingPlayer.sendMessage(ChatColor.DARK_GREEN + "Bitte informiere dich vorher in der Enzyklopädie Calad Amar.");
		
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
			
		Town requestTown = plugin.nSCore.getPlayerTown(requester);
		requestTown.expandTown(requester);
		return;
	}
	
	/**
	 * Lehnt die Gruppenanfrage ab
	 * @param requester Die Spielerklasse des Einladenden
	 * @param invited Die Spielerklasse des Eingeladenen
	 * @return void
	 */
	public void denyRequest(Player requester, Player invited) {
		
		plugin.nSTownInvite.deleteRequest(requester, invited);
		requester.sendMessage(ChatColor.DARK_RED + "Du lässt deine Stadt nicht expandieren."); 
		return;
	}
	
}