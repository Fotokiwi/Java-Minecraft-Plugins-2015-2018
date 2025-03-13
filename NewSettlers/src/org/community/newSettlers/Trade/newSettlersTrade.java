package org.community.newSettlers.Trade;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.community.newSettlers.newSettlers;

public class newSettlersTrade {
		
	private final newSettlers plugin;
	
	Player playerA = null;
	Player playerB = null;
	
	boolean readyPlayerA = false;
	boolean readyPlayerB = false;
	
	public ItemStack slot0 = null;
	public ItemStack slot1 = null;
	public ItemStack slot2 = null;
	public ItemStack slot3 = null;
	public ItemStack slot9 = null;
	public ItemStack slot10 = null;
	public ItemStack slot11 = null;
	public ItemStack slot18 = null;
	public ItemStack slot19 = null;
	public ItemStack slot20 = null;
	public ItemStack slot21 = null;

	public ItemStack slot5 = null;
	public ItemStack slot6 = null;
	public ItemStack slot7 = null;
	public ItemStack slot8 = null;
	public ItemStack slot15 = null;
	public ItemStack slot16 = null;
	public ItemStack slot17 = null;
	public ItemStack slot23 = null;
	public ItemStack slot24 = null;
	public ItemStack slot25 = null;
	public ItemStack slot26 = null;
	
	Inventory inventory = null;

	public newSettlersTrade(newSettlers plugin)
	{
		this.plugin = plugin;
	}
	 	 
	public newSettlersTrade(newSettlers plugin, Player inviter, Player invited)
	{
		this.plugin = plugin;
		
		this.playerA = inviter;
		this.playerB = invited;
		
		initiateTrade();
	}
	
	public void initiateTrade() {
		inventory = Bukkit.getServer().createInventory(null, InventoryType.CHEST);
		inventory.setItem(4, new ItemStack(Material.ITEM_FRAME, 1));
		inventory.setItem(13, new ItemStack(Material.ITEM_FRAME, 1));
		inventory.setItem(22, new ItemStack(Material.ITEM_FRAME, 1));
		
		ItemStack wool = new ItemStack(Material.WOOL);
		wool.setDurability((short) 14);
		wool.setAmount(1);
		
		inventory.setItem(12, wool);
		inventory.setItem(14, wool);
		
		playerA.openInventory(inventory);
		playerB.openInventory(inventory);
		
		plugin.nSCore.addTradeWindow(playerA, this);
		plugin.nSCore.addTradeWindow(playerB, this);
	}
	
	public boolean isTradeReady() {
		if(readyPlayerA == true && readyPlayerB == true)
			return true;
		
		return false;
	}
	
	public boolean isPlayerA(Player player) {
		if(player == playerA)
			return true;
		
		return false;
	}
	
	public ItemStack changeStatus(Player player, ItemStack item) {
		
		if(item.getDurability() == 14) {
			item.setDurability((short) 5);
			if(player == playerA) {
				readyPlayerA = true;
			} else {
				readyPlayerB = true;
			}
		} else if(item.getDurability() == 5) {
			item.setDurability((short) 14);
			if(player == playerA) {
				readyPlayerA = false;
			} else {
				readyPlayerB = false;
			}
		}
		
		return item;
	}
	
	public void cancelTrade() {
		plugin.nSCore.removeTradeWindow(playerA, this);
		plugin.nSCore.removeTradeWindow(playerB, this);
		
		Inventory inventoryA = playerA.getInventory();
		Inventory inventoryB = playerB.getInventory();
		
		clearInventories(inventoryA, inventoryB);
		
		playerA.closeInventory();
		playerB.closeInventory();
	}
	
	@SuppressWarnings("deprecation")
	private void clearInventories(Inventory inventoryA, Inventory inventoryB) {
		List<ItemStack> listA = new ArrayList<ItemStack>();
		List<ItemStack> listB = new ArrayList<ItemStack>();
		
		listA.add(slot0);
		listA.add(slot1);
		listA.add(slot2);
		listA.add(slot3);
		listA.add(slot9);
		listA.add(slot10);
		listA.add(slot11);
		listA.add(slot18);
		listA.add(slot19);
		listA.add(slot20);
		listA.add(slot21);
		
		//for(int a = 0; a < listA.size(); a++) {
		//	plugin.getServer().broadcastMessage(listA.get(a) + "");
		//}
		
		listB.add(slot5);
		listB.add(slot6);
		listB.add(slot7);
		listB.add(slot8);
		listB.add(slot15);
		listB.add(slot16);
		listB.add(slot17);
		listB.add(slot23);
		listB.add(slot24);
		listB.add(slot25);
		listB.add(slot26);
		
		//for(int a = 0; a < listA.size(); a++) {
		//	plugin.getServer().broadcastMessage(listB.get(a) + "");
		//}
		
		for(int i = 0; i < listA.size(); i++) {
			int slot = playerB.getInventory().firstEmpty();
			if (slot == -1){
				playerB.getWorld().dropItem(playerB.getLocation().add(0, 1, 0), listA.get(i));
				playerB.sendMessage(ChatColor.RED + "Dein Inventar war voll, ein Stapel wurde auf den Boden gelegt.");
				playerB.updateInventory();
			} else{
				playerB.getInventory().setItem(slot, listA.get(i));
				playerB.updateInventory();
			}
		}
		
		for(int i = 0; i < listB.size(); i++) {
			int slot = playerA.getInventory().firstEmpty();
			if (slot == -1){
				playerA.getWorld().dropItem(playerA.getLocation().add(0, 1, 0), listB.get(i));
				playerA.sendMessage(ChatColor.RED + "Dein Inventar war voll, ein Stapel wurde auf den Boden gelegt.");
				playerA.updateInventory();
			} else{
				playerA.getInventory().setItem(slot, listB.get(i));
				playerA.updateInventory();
			}
		}
	}
	
}