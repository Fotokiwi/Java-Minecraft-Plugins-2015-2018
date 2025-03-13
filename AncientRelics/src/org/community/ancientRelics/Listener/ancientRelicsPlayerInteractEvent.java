package org.community.ancientRelics.Listener;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.community.ancientRelics.ancientRelics;

public class ancientRelicsPlayerInteractEvent implements Listener{
	
	private final ancientRelics plugin;

	public ancientRelicsPlayerInteractEvent(ancientRelics plugin)
	{
		this.plugin = plugin;
	}
	
	@EventHandler(priority = EventPriority.LOW)
	public void onPlayerInteract(PlayerInteractEvent event) {
		Block clickedBlock = event.getClickedBlock();
		if(clickedBlock.getType().equals(Material.SIGN)){
			if(((Sign) clickedBlock.getState()).getLine(0).equals("Hier verstarb")){
				plugin.graves.playerRemoveDeath(event.getPlayer(), clickedBlock.getLocation());
			}
		}
	}
}
