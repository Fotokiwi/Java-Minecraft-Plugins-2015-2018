package org.community.newSettlers.Listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.community.newSettlers.newSettlers;
import org.community.newSettlers.Town.Town;

public class newSettlersPlayerJoinEvent implements Listener {
	
	private final newSettlers plugin;

	public newSettlersPlayerJoinEvent(newSettlers plugin)
	{
		this.plugin = plugin;
	}
	
	@EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
	public void onPlayerJoin(PlayerJoinEvent event) {

		Player player = event.getPlayer();
		
		Town town = plugin.nSCore.getPlayerTown(player);
	
		
		player.setDisplayName(player.getName().replace("_", " "));
		player.setPlayerListName(player.getName().replace("_", " "));
		//if(plugin.user.getString("Spieler." + player.getName() + ".Chat.Displayname") != null) {
			//String name = plugin.user.getString("Spieler." + player.getName() + ".Chat.Displayname");
			//player.setDisplayName(name);
			//player.setPlayerListName(name);
		//}
		
		if(plugin.user.getBoolean("Spieler." + player.getName() + ".Chat.GlobalEnabled", true)) {
			player.sendMessage("Du sieht momentan die Nachrichten im globalen Chatkanal.");
		}
		else{
			player.sendMessage("Du siehst momentan nicht die Nachrichten im globalen Chatkanal.");
		}
		
		
		if(town != null) {
			player.sendMessage(town.getTownMessage());
			return;
		}
		
		return;
		
	}
	
}