package org.community.ancientRelics.Graves;


import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class PlayerDeath {
	
	private List<ItemStack> inventory;
	private long timeOfDeath;
	private Location l;
	private String playerName;
	
	public PlayerDeath(List<ItemStack> inventory, long timeOfDeath, Location l, String playerName){
		this.setInventory(inventory);
		this.setTimeOfDeath(timeOfDeath);
		this.setL(l);
		this.playerName = playerName;
	}

	public List<ItemStack> getInventory() {
		return inventory;
	}

	public void setInventory(List<ItemStack> inventory) {
		this.inventory = inventory;
	}

	public long getTimeOfDeath() {
		return timeOfDeath;
	}

	public void setTimeOfDeath(long timeOfDeath) {
		this.timeOfDeath = timeOfDeath;
	}

	public Location getL() {
		return l;
	}

	public void setL(Location l) {
		this.l = l;
	}
	
	public void dropInventoryAtPosition(Location l){

		for(ItemStack is : inventory){
			l.getWorld().dropItemNaturally(l, is);
		}
	}
	
	public String getPlayerName(){
		return playerName;
	}

}
