package org.community.DungeonUtility.BlockMode;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.community.DungeonUtility.*;

public class blockModeListenerPlayerInteractEvent implements Listener {
	
	private final DungeonUtility plugin;

	public blockModeListenerPlayerInteractEvent(DungeonUtility plugin)
	{
		this.plugin = plugin;
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler(priority = EventPriority.NORMAL)
	public void onPlayerInteract(PlayerInteractEvent event) {
		
		if(!(event.getPlayer() instanceof Player))
			return;
		if(event.getPlayer().getGameMode() != GameMode.CREATIVE)
			return;
		if(plugin.blockmodeSet.get(event.getPlayer()) == null)
			return;
		if(event.getPlayer().getItemInHand().getType() != Material.BOOK)
			return;
		
		Block block = event.getClickedBlock();
		
		String blockWorld = block.getWorld().getName();
		Location blockLoc = block.getLocation();
		String blockType = block.getType().toString();
		byte blockData = block.getData();
		
		String blockCode = blockWorld + "," + blockLoc.getBlockX() + "," + blockLoc.getBlockY() + "," + blockLoc.getBlockZ();
		
		if(event.getAction() == Action.LEFT_CLICK_BLOCK && event.getPlayer().isSneaking()) {
			plugin.set.set("Sets." + plugin.blockmodeSet.get(event.getPlayer()) + ".A-Set." + blockCode, "AIR,0");
			event.getPlayer().sendMessage("Du hast den Block als Luft-Block im A-Set registriert.");
			return;
		}
		
		if(event.getAction() == Action.LEFT_CLICK_BLOCK) {
			plugin.set.set("Sets." + plugin.blockmodeSet.get(event.getPlayer()) + ".A-Set." + blockCode, blockType + "," + blockData);
			event.getPlayer().sendMessage("Du hast den Block als " + blockType + "-Block im A-Set registriert.");
			return;
		}
		
		if(event.getAction() == Action.RIGHT_CLICK_BLOCK && event.getPlayer().isSneaking()) {
			plugin.set.set("Sets." + plugin.blockmodeSet.get(event.getPlayer()) + ".B-Set." + blockCode, "AIR,0");
			event.getPlayer().sendMessage("Du hast den Block als Luft-Block im B-Set registriert.");
			return;
		}
		
		if(event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			plugin.set.set("Sets." + plugin.blockmodeSet.get(event.getPlayer()) + ".B-Set." + blockCode, blockType + "," + blockData);
			event.getPlayer().sendMessage("Du hast den Block als " + blockType + "-Block im B-Set registriert.");
			return;
		}
		
		return;
		
	}
	
}