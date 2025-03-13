package org.community.LootChest.UserData;

import org.bukkit.inventory.Inventory;

public class ChestUserData
{
	private long lastUse;
	private Inventory inventory = null;
	
	public ChestUserData(long lastUse)
	{
		this.setLastUse(lastUse);
	}
	
	public ChestUserData(long lastUse, Inventory inventory)
	{
	    this.setLastUse(lastUse);
	    this.setInventory(inventory);
	}

	public long getLastUse() {
		return lastUse;
	}

	public void setLastUse(long lastUse) {
		this.lastUse = lastUse;
	}

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}
	

	
}
