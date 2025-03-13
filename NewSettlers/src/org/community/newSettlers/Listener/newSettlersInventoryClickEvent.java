package org.community.newSettlers.Listener;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import org.community.newSettlers.newSettlers;
import org.community.newSettlers.Trade.newSettlersTrade;


public class newSettlersInventoryClickEvent implements Listener{

	private newSettlers plugin;

	public newSettlersInventoryClickEvent(newSettlers plugin){
		this.plugin = plugin;
	}

	@EventHandler(priority = EventPriority.NORMAL)
	public void onInventoryClick(InventoryClickEvent event) {
		
		//plugin.getServer().broadcastMessage(event.getRawSlot() + "");
		
		if(!(event.getWhoClicked() instanceof Player))
			return;
		
		Player player = (Player) event.getWhoClicked();
		
		if(event.getInventory().getType() != InventoryType.CHEST || plugin.nSCore.isTradeWindow(player) == false)
			return;
		
		if(event.isShiftClick()) {
			player.sendMessage(ChatColor.RED + "Im Handelsfenster ist Shiftklick nicht möglich.");
			event.setCancelled(true);
			return;
		}
		
		if((event.getRawSlot() == 4 || event.getRawSlot() == 13 || event.getRawSlot() == 22) && event.getCurrentItem().getType() == Material.ITEM_FRAME){
			event.setCancelled(true);
			return;
		}
		
		newSettlersTrade trade = plugin.nSCore.getTradeWindow(player);
		
		if(trade.isPlayerA(player)) {
			int slot = event.getRawSlot();
			if(slot == 5 || slot == 6 || slot == 7 || slot == 8 || slot == 15 || slot == 16 || slot == 17 || slot == 23 || slot == 24 || slot == 25 || slot == 26) {
				event.setCancelled(true);
				return;
			}
		} else {
			int slot = event.getRawSlot();
			if(slot == 0 || slot == 1 || slot == 2 || slot == 3 || slot == 9 || slot == 10 || slot == 11 || slot == 18 || slot == 19 || slot == 20 || slot == 21) {
				event.setCancelled(true);
				return;
			}
		}
		
		if(event.getRawSlot() == 12 || event.getRawSlot() == 14) {
			if(event.getCurrentItem().getType() != Material.WOOL) {
				
			} else {
				ItemStack wool = event.getCurrentItem();
				if(trade.isPlayerA(player) == true) {
					if(event.getRawSlot() == 14) {						
						event.setCancelled(true);
					} else {				
						event.setCancelled(true);
						event.setCurrentItem(trade.changeStatus(player, wool));	
					}
				} else { 
					if(event.getRawSlot() == 12) {
						event.setCancelled(true);
					} else {
						event.setCancelled(true);
						event.setCurrentItem(trade.changeStatus(player, wool));
					}
				}
				
				trade.slot0 = event.getInventory().getItem(0);
				trade.slot1 = event.getInventory().getItem(1);
				trade.slot2 = event.getInventory().getItem(2);
				trade.slot3 = event.getInventory().getItem(3);
				trade.slot9 = event.getInventory().getItem(9);
				trade.slot10 = event.getInventory().getItem(10);
				trade.slot11 = event.getInventory().getItem(11);
				trade.slot18 = event.getInventory().getItem(18);
				trade.slot19 = event.getInventory().getItem(19);
				trade.slot20 = event.getInventory().getItem(20);
				trade.slot21 = event.getInventory().getItem(21);
				
				trade.slot5 = event.getInventory().getItem(5);
				trade.slot6 = event.getInventory().getItem(6);
				trade.slot7 = event.getInventory().getItem(7);
				trade.slot8 = event.getInventory().getItem(8);
				trade.slot15 = event.getInventory().getItem(15);
				trade.slot16 = event.getInventory().getItem(16);
				trade.slot17 = event.getInventory().getItem(17);
				trade.slot23 = event.getInventory().getItem(23);
				trade.slot24 = event.getInventory().getItem(24);
				trade.slot25 = event.getInventory().getItem(25);
				trade.slot26 = event.getInventory().getItem(26);
			
				if(trade.isTradeReady()) {
					trade.cancelTrade();
				}
			}
			
		}
		
    }
}
