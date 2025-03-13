package org.community.newSettlers.Listener;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;
import org.community.newSettlers.newSettlers;
import org.community.newSettlers.Town.Town;


public class newSettlersPrepareCraftEvent implements Listener{

	private newSettlers plugin;

	public newSettlersPrepareCraftEvent(newSettlers plugin){
		this.plugin = plugin;
	}


	@EventHandler(priority=EventPriority.LOW)
	public void onPrepareCraft(PrepareItemCraftEvent event) {
		Player player = (Player) event.getView().getPlayer();
		Town town = plugin.nSCore.getChunkInfo(player.getWorld().getName() + "," + player.getLocation().getChunk().getX() + "," + player.getLocation().getChunk().getZ()); 
		if(town == null) {
			//event.getInventory().setResult(new ItemStack(Material.AIR));
			return;
		} else {
			if(town.isMember(player.getName())) {
				if(town.isCraftAllowed()) {
					return;
				} else {
					event.getInventory().setResult(new ItemStack(Material.AIR));
					player.sendMessage("Deine Stadt genehmigt dir die Herstellung nicht.");
					return;
				}
			}
			
			if(town.isAlly(player)) {
				if(town.isCraftAllowedAlly()) {
					return;
				} else {
					event.getInventory().setResult(new ItemStack(Material.AIR));
					player.sendMessage("Die verbündete Stadt genehmigt dir die Herstellung nicht.");
					return;
				}
			}
			
			if(town.isCraftAllowedOutsider()) {
				return;
			} else {
				event.getInventory().setResult(new ItemStack(Material.AIR));
				player.sendMessage("Diese Stadt genehmigt dir die Herstellung nicht.");
				return;
			}
		}
	}
}