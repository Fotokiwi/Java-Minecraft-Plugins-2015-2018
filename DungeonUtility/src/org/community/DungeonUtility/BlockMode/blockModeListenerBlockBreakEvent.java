package org.community.DungeonUtility.BlockMode;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.community.DungeonUtility.*;

public class blockModeListenerBlockBreakEvent implements Listener {
	
	private final DungeonUtility plugin;

	public blockModeListenerBlockBreakEvent(DungeonUtility plugin)
	{
		this.plugin = plugin;
	}
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void onBlockBreak(BlockBreakEvent event) {
		
		if(!(event.getPlayer() instanceof Player))
			return;
		if(event.getPlayer().getGameMode() != GameMode.CREATIVE)
			return;
		if(event.getPlayer().getItemInHand().getType() != Material.BOOK)
			return;
		
		if(plugin.blockmodeSet.get(event.getPlayer()) != null)
			event.setCancelled(true);
		
		return;
		
	}
	
}