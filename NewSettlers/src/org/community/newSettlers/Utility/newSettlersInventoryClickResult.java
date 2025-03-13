package org.community.newSettlers.Utility;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType.SlotType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;


public class newSettlersInventoryClickResult {

	private ItemStack itemsPut;
	private ItemStack itemsTook;

	public newSettlersInventoryClickResult(InventoryClickEvent event)
	{
		this.calcChangeInChest(event);
	}

	public ItemStack getItemsPut()
	{
		return itemsPut;
	}
	public ItemStack getItemsTook()
	{
		return itemsTook;
	}

	public String toString()
	{
		return "InventoryClickResult, ItemsTook:" + itemsTook + ", ItemsPut: "+itemsPut;
	}

	private void calcChangeInChest(InventoryClickEvent event)
	{
		if((event.getRawSlot()>event.getInventory().getSize() && !event.isShiftClick()) || event.getRawSlot() == -999)
		{
			this.itemsTook=null;
			this.itemsPut=null;
			return;
		}
		if(event.isShiftClick()) //Shiftklick
		{
			if(event.getRawSlot()>event.getInventory().getSize()) //im Spieler Inventar
			{
				int free;
				if(hasEmptySlot(event.getInventory()))
				{//Alles uebertragen
					this.itemsPut=event.getCurrentItem().clone();
					this.itemsTook=null;
					return;
				}

				free = freeRoomForItem(event.getInventory(),event.getCurrentItem()); //Berechnen wie viel uebertragen wird
				if (free == 0)
				{
					this.itemsPut=null;
					this.itemsTook=null;
					return;
				}
				if (free >= event.getCurrentItem().getAmount())
				{
					this.itemsPut = event.getCurrentItem().clone();
					this.itemsTook = null;
					return;
				}
				this.itemsPut=event.getCurrentItem().clone();
				this.itemsPut.setAmount(free);
				this.itemsTook=null;
				return;
			}
			//ShiftKlick in der Kiste
			{
				int free;
				if(hasEmptySlot(event.getWhoClicked().getInventory()))
				{
					this.itemsPut=null;
					this.itemsTook=event.getCurrentItem().clone();
					return;
				}
				free = freeRoomForItem(event.getWhoClicked().getInventory(),event.getCurrentItem());
				if(free == 0)
				{
					this.itemsPut=null;
					this.itemsTook=null;
					return;
				}
				if(free >= event.getCurrentItem().getAmount())
				{
					this.itemsPut=null;
					this.itemsTook=event.getCurrentItem().clone();
					return;
				}
				this.itemsPut=null;
				this.itemsTook=event.getCurrentItem().clone();
				this.itemsTook.setAmount(free);
				return;
			}
		}
		if(event.getSlotType() == SlotType.OUTSIDE) {
			return;
		}
		if(event.isRightClick())
		{//Rechtsklick in der Kiste
			if(event.getCursor().getAmount() == 0)
			{//Haelfte des Vorhandenen Stacks
				this.itemsPut=null;
				this.itemsTook=event.getCurrentItem().clone();
				this.itemsTook.setAmount(this.itemsTook.getAmount()-this.itemsTook.getAmount()/2);
				return;
			}
			if(!(event.getCursor().isSimilar(event.getCurrentItem())))
			{//Items werden getauscht
				this.itemsTook=event.getCurrentItem().clone();
				this.itemsPut=event.getCursor().clone();
				return;
			}
			if(event.getCurrentItem().getMaxStackSize() == event.getCurrentItem().getAmount())
			{
				this.itemsPut=null;
				this.itemsTook=null;
				return;
			}
			{ //Ein Item wird abgelegt
				this.itemsPut=event.getCursor().clone();
				this.itemsPut.setAmount(1);
				this.itemsTook=null;
				return;
			}
		}
		//Linksklick
		{
			if(event.getCursor().getAmount() == 0)
			{//Stack wird raus genommen
				this.itemsPut=null;
				this.itemsTook=event.getCurrentItem().clone();
				return;
			}
			if(event.getCurrentItem().getAmount() == 0)
			{//Stack wird reingelegt
				this.itemsPut=event.getCursor().clone();
				this.itemsTook=null;
				return;
			}
			if(!event.getCurrentItem().isSimilar(event.getCursor()))
			{//Items werden getauscht
				this.itemsTook=event.getCurrentItem().clone();
				this.itemsPut=event.getCursor().clone();
				return;
			}
			{//Stack wird aufgefuellt
				int maxToPut = event.getCurrentItem().getMaxStackSize()-event.getCurrentItem().getAmount();
				if(maxToPut > event.getCursor().getAmount())
				{
					this.itemsPut=event.getCursor().clone();
					this.itemsTook=null;
					return;
				}
				this.itemsPut=event.getCursor().clone();
				this.itemsPut.setAmount(maxToPut);
				this.itemsTook=null;
			}
		}
	}
	private boolean hasEmptySlot(Inventory inv)
	{
		return inv.firstEmpty()!=-1;
	}
	private int freeRoomForItem(Inventory inv, ItemStack stack)
	{
		int freeRoom=0;
		for (ItemStack is:inv.getContents())
		{
			if(is.isSimilar(stack))
				freeRoom += is.getMaxStackSize()-is.getAmount();
		}
		return freeRoom;
	}
}

