package org.community.fourWays.Listener;

import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.community.fourWays.fourWays;

public class fourWaysPlayerQuitEvent implements Listener {
	
	private final fourWays plugin;

	public fourWaysPlayerQuitEvent(fourWays plugin)
	{
		this.plugin = plugin;
	}
	@EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
	public void onPlayerQuit(PlayerQuitEvent event) {

		Player player = event.getPlayer();
		UUID id = player.getUniqueId();
		
		if(plugin.fWCore.lastQuit.get(id) == null) {
			plugin.fWCore.lastQuit.put(id, System.currentTimeMillis());
			event.setQuitMessage(ChatColor.LIGHT_PURPLE + "[Server] " + player.getName() + " hat das Spiel verlassen.");
		} else if(System.currentTimeMillis() >= (plugin.fWCore.lastQuit.get(id) + 300000)) {
			plugin.fWCore.lastQuit.put(id, System.currentTimeMillis());
			event.setQuitMessage(ChatColor.LIGHT_PURPLE + "[Server] " + player.getName() + " hat das Spiel verlassen.");
		} else {
			event.setQuitMessage("");
		}
		
		plugin.fWUsers.getPlayerInfo(player).saveToFile();
		plugin.fWCore.userList.remove(player.getName());
		
		return;
		
	}
	
}