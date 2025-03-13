package org.community.ambience.AmbientObjects.Listener;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.community.ambience.*;

public class blockBreakEvent implements Listener {

	private final Ambience plugin;

	public blockBreakEvent(Ambience plugin) {
		this.plugin = plugin;
	}

	@EventHandler(priority = EventPriority.NORMAL)
	public void onBlockBreak(BlockBreakEvent event) {

		if (!(event.getPlayer() instanceof Player))
			return;

		String blockHash = event.getBlock().getWorld().getName() + "," + event.getBlock().getLocation().getBlockX() + ","
				+ event.getBlock().getLocation().getBlockY() + "," + event.getBlock().getLocation().getBlockZ();
		if (plugin.forbidBreak.contains(blockHash)) {
			event.setCancelled(true);
			event.getPlayer().sendMessage(ChatColor.DARK_RED + "Ambiente-Objekte sind nicht abbaubar!");
		}

		return;

	}

}