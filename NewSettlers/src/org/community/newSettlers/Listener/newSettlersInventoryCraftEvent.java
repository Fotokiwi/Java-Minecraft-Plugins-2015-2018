package org.community.newSettlers.Listener;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.community.newSettlers.newSettlers;
import org.community.newSettlers.Town.Town;


public class newSettlersInventoryCraftEvent implements Listener{

	private newSettlers plugin;

	public newSettlersInventoryCraftEvent(newSettlers plugin){
		this.plugin = plugin;
	}

	@EventHandler(priority=EventPriority.LOW)
	public void onInventoryCraft(InventoryClickEvent event) {
		Inventory inv = event.getInventory();

		if((inv.getType()==InventoryType.CHEST) || inv.getType()==InventoryType.ENDER_CHEST || inv.getType()==InventoryType.PLAYER || inv.getType()==InventoryType.BEACON || inv.getType()==InventoryType.CREATIVE || inv.getType()==InventoryType.ENCHANTING || inv.getType()==InventoryType.ANVIL || inv.getType()==InventoryType.DISPENSER || inv.getType()==InventoryType.MERCHANT){
			return;
		}

		if(inv.getType() == InventoryType.CRAFTING || inv.getType() == InventoryType.WORKBENCH) {
			if(event.getRawSlot() > 0)
				return;

			Player player = (Player) event.getWhoClicked();

			ItemStack tempItem = inv.getItem(event.getSlot());
			if(tempItem == null)
				return;
			if(tempItem.getType() != null && tempItem.getType() != Material.AIR){
				Chunk chunk = player.getLocation().getChunk();
				Town town = plugin.nSCore.getChunkInfo(chunk.getWorld().getName() + "," + chunk.getX() + "," + chunk.getZ()); 
				//plugin.getServer().broadcastMessage(chunk.getWorld().getName() + "," + chunk.getX() + "," + chunk.getZ());
				if(town == null) {
					player.sendMessage("In der Wildnis kann nicht gecraftet werden.");
					event.setCancelled(true);
					return;
				} else {
					if(town.isMember(player.getName())) {
						if(town.isCraftAllowed()) {
							return;
						} else {
							event.setCancelled(true);
							player.sendMessage("Deine Stadt genehmigt dir die Herstellung nicht.");
						}
					}

					if(town.isAlly(player)) {
						if(town.isCraftAllowedAlly()) {
							return;
						} else {
							event.setCancelled(true);
							player.sendMessage("Die verbündete Stadt genehmigt dir die Herstellung nicht.");
							return;
						}
					}

					if(town.isCraftAllowedOutsider()) {
						return;
					} else {
						event.setCancelled(true);
						player.sendMessage("Diese Stadt genehmigt dir die Herstellung nicht.");
						return;
					}
				}
			}
		}
	}
}
