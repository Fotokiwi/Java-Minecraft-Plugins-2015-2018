package org.community.LootChest.ChestData;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.bukkit.inventory.ItemStack;

public class Wurf extends Roll {
	private List<ItemStack> itemstack = new LinkedList<ItemStack>();
	private int probability;
	
	public Wurf(List<ItemStack> itemstack, int probability)
	{
		this.setItemstack(itemstack);
		this.setProbability(probability);
		super.setJobs(new ArrayList<String>());
		super.getJobs().add("XX");
	}
	
	public Wurf(ItemStack itemstack, int wahrscheinlichkeit)
	{
	    List<ItemStack> isList = new LinkedList<ItemStack>();
	    isList.add(itemstack);
	    this.setItemstack(isList);
	    this.setProbability(wahrscheinlichkeit);
		super.setJobs(new ArrayList<String>());
		super.getJobs().add("XX");
	}
	
	public List<ItemStack> getItemstack() {
		return itemstack;
	}

	public void setItemstack(List<ItemStack> itemstack) {
		this.itemstack = itemstack;
	}

	public int getProbability() {
		return probability;
	}

	public void setProbability(int probability) {
		this.probability = probability;
	}
	
	

}
