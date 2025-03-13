package org.community.newSettlers.Listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.community.newSettlers.newSettlers;
import org.community.newSettlers.Trade.newSettlersTrade;


public class newSettlersInventoryCloseEvent implements Listener{

	private newSettlers plugin;

	public newSettlersInventoryCloseEvent(newSettlers plugin){
		this.plugin = plugin;
	}

	@EventHandler(priority = EventPriority.NORMAL)
	public void onInventoryClose(InventoryCloseEvent event) {
		
		if(event.getInventory().getType() != InventoryType.CHEST)
			return;
		
		if(plugin.nSCore.isTradeWindow((Player) event.getPlayer())) {
			//plugin.getServer().broadcastMessage(event.getInventory() + "");
			newSettlersTrade trade = plugin.nSCore.getTradeWindow((Player) event.getPlayer());
			

			
			trade.slot0 = event.getInventory().getItem(5);
			trade.slot1 = event.getInventory().getItem(6);
			trade.slot2 = event.getInventory().getItem(7);
			trade.slot3 = event.getInventory().getItem(8);
			trade.slot9 = event.getInventory().getItem(15);
			trade.slot10 = event.getInventory().getItem(16);
			trade.slot11 = event.getInventory().getItem(17);
			trade.slot18 = event.getInventory().getItem(23);
			trade.slot19 = event.getInventory().getItem(24);
			trade.slot20 = event.getInventory().getItem(25);
			trade.slot21 = event.getInventory().getItem(26);
			
			trade.slot5 = event.getInventory().getItem(0);
			trade.slot6 = event.getInventory().getItem(1);
			trade.slot7 = event.getInventory().getItem(2);
			trade.slot8 = event.getInventory().getItem(3);
			trade.slot15 = event.getInventory().getItem(9);
			trade.slot16 = event.getInventory().getItem(10);
			trade.slot17 = event.getInventory().getItem(11);
			trade.slot23 = event.getInventory().getItem(18);
			trade.slot24 = event.getInventory().getItem(19);
			trade.slot25 = event.getInventory().getItem(20);
			trade.slot26 = event.getInventory().getItem(21);
			
			trade.cancelTrade();
		}
		
    }
}
