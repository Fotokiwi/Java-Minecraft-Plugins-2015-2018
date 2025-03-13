package org.community.Statistics.Listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.community.Statistics.Statistics;
import org.community.Statistics.Player.statisticsPlayer;

public class statisticsBlockPlaceEvent implements Listener {
	
	private Statistics plugin;

	public statisticsBlockPlaceEvent(Statistics plugin)
	{
		this.plugin = plugin;
	}
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void onBlockPlace(BlockPlaceEvent event) {
		
		if(event.isCancelled())
			return;
		
		Player player = event.getPlayer();
		statisticsPlayer statisticsPlayer = this.plugin.sCore.playerHash.get(player);
		
		statisticsPlayer.setBlockPlaceCount(event.getBlock().getType().name());
		
	}

}
