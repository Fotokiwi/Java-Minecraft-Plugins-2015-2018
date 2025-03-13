package org.community.Statistics.Listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.community.Statistics.Statistics;
import org.community.Statistics.Player.statisticsPlayer;

public class statisticsPlayerJoinEvent implements Listener{
	
	private Statistics plugin;
	
	public statisticsPlayerJoinEvent(Statistics plugin) {
		
		this.plugin = plugin;
		
	}
	
	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void onPlayerJoin(PlayerJoinEvent event) {

		Player player = event.getPlayer();
		
		plugin.sCore.playerHash.put(player, new statisticsPlayer(this.plugin, player));
		
		statisticsPlayer sPlayer = plugin.sCore.playerHash.get(player);
		sPlayer.setPlayerJoinCount();
		
		return;
		
	}
}
