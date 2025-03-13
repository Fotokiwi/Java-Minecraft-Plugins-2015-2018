package org.community.Statistics.Listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.community.Statistics.Statistics;
import org.community.Statistics.Player.statisticsPlayer;

public class statisticsBlockBreakEvent implements Listener {
	
	private Statistics plugin;

	public statisticsBlockBreakEvent(Statistics plugin)
	{
		this.plugin = plugin;
	}
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void onBlockBreak(BlockBreakEvent event) {
		
		if(event.isCancelled())
			return;
		
		Player player = event.getPlayer();
		statisticsPlayer statisticsPlayer = this.plugin.sCore.playerHash.get(player);
		
		statisticsPlayer.setBlockBreakCount(event.getBlock().getType().name());
		
	}

}
