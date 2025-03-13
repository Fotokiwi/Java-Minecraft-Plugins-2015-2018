package org.community.ancientRelics.Listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.community.ancientRelics.ancientRelics;
import org.community.ancientRelics.Groups.Classes.Groups;

public class ancientRelicsPlayerJoinEvent implements Listener {
	
	private final ancientRelics plugin;

	public ancientRelicsPlayerJoinEvent(ancientRelics plugin)
	{
		this.plugin = plugin;
	}
	
	@EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
	public void onPlayerJoin(PlayerJoinEvent event) {

		Player player = event.getPlayer();
		
		Groups playerGroup = plugin.aRGroups.getPlayerGroupInfo(player);
		
		if(playerGroup != null) {
			playerGroup.setPlayerTeam(player);
			player.setHealth(player.getHealth());
		}
		
		plugin.playerGroupMembership.put(player, playerGroup);
		
		return;
		
	}
	
	@EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
	public void onPlayerQuit(PlayerQuitEvent event) {

		Player player = event.getPlayer();
		
		plugin.playerGroupMembership.put(player, null);
		
		return;
		
	}
	
}