package org.community.CustomRecipes.Listener;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.community.CustomRecipes.CustomRecipes;

public class customRecipesPrepareCraftEvent implements Listener {
	
	private CustomRecipes plugin;
	
	public customRecipesPrepareCraftEvent(CustomRecipes plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler(priority=EventPriority.NORMAL)
	public void onPrepareCraft(PrepareItemCraftEvent event) {
		
		String resultMaterial = event.getRecipe().getResult().getType().toString();
		
		@SuppressWarnings("unchecked")
		List<String> alternativesList = (List<String>) plugin.alternatives.getList("Alternatives." + resultMaterial);
		
		if(alternativesList == null)
			return;
		if(alternativesList.size() >= 1) {
			//plugin.getServer().broadcastMessage("Alternatives contains recipes for: " + resultMaterial);
		}
		
		ItemStack[] craftingMatrix = event.getInventory().getMatrix();
		
		if(craftingMatrix == null)
			return;
		
		for(int i = 0; i < alternativesList.size(); i++) {
			//plugin.getServer().broadcastMessage(alternativesList.get(i));
			if(checkRecipe(craftingMatrix, alternativesList.get(i))) {
				ItemStack item = event.getInventory().getResult();
				ItemMeta itemMeta = item.getItemMeta();
				itemMeta.setDisplayName(plugin.alternatives.getString("Rezepte." + alternativesList.get(i) + ".Displayname"));
				ArrayList<String>desc=new ArrayList<String>();
				desc.add(plugin.alternatives.getString("Rezepte." + alternativesList.get(i) + ".Lore"));
				itemMeta.setLore(desc);
				item.setAmount(plugin.alternatives.getInt("Rezepte." + alternativesList.get(i) + ".OutputMenge", 1));
				item.setItemMeta(itemMeta);
				event.getInventory().setResult(item);
				return;
			}
		}
		event.getInventory().setResult(new ItemStack(Material.AIR));		
	}

	private boolean checkRecipe(ItemStack[] craftingMatrix, String string) {
		
		if(craftingMatrix[0].getType() != Material.AIR) {
			if(craftingMatrix[0].getItemMeta().getDisplayName() == null) {
				return false;
			}
			if(!craftingMatrix[0].getItemMeta().getDisplayName().equalsIgnoreCase(plugin.alternatives.getString("Rezepte." + string + ".OL.Name"))) {
				return false;
			}
		}
		if(craftingMatrix[1].getType() != Material.AIR) {
			if(craftingMatrix[1].getItemMeta().getDisplayName() == null) {
				return false;
			}
			if(!craftingMatrix[1].getItemMeta().getDisplayName().equalsIgnoreCase(plugin.alternatives.getString("Rezepte." + string + ".OM.Name"))) {
				return false;
			}
		}
		if(craftingMatrix[2].getType() != Material.AIR) {
			if(craftingMatrix[2].getItemMeta().getDisplayName() == null) {
				return false;
			}
			if(!craftingMatrix[2].getItemMeta().getDisplayName().equalsIgnoreCase(plugin.alternatives.getString("Rezepte." + string + ".OR.Name"))) {
				return false;
			}
		}	
		if(craftingMatrix[3].getType() != Material.AIR) {
			if(craftingMatrix[3].getItemMeta().getDisplayName() == null) {
				return false;
			}
			if(!craftingMatrix[3].getItemMeta().getDisplayName().equalsIgnoreCase(plugin.alternatives.getString("Rezepte." + string + ".ML.Name"))) {
				return false;
			}
		}
		if(craftingMatrix[4].getType() != Material.AIR) {
			if(craftingMatrix[4].getItemMeta().getDisplayName() == null) {
				return false;
			}
			if(!craftingMatrix[4].getItemMeta().getDisplayName().equalsIgnoreCase(plugin.alternatives.getString("Rezepte." + string + ".MM.Name"))) {
				return false;
			}
		}
		if(craftingMatrix[5].getType() != Material.AIR) {
			if(craftingMatrix[5].getItemMeta().getDisplayName() == null) {
				return false;
			}
			if(!craftingMatrix[5].getItemMeta().getDisplayName().equalsIgnoreCase(plugin.alternatives.getString("Rezepte." + string + ".MR.Name"))) {
				return false;
			}
		}
		if(craftingMatrix[6].getType() != Material.AIR) {
			if(craftingMatrix[6].getItemMeta().getDisplayName() == null) {
				return false;
			}
			if(!craftingMatrix[6].getItemMeta().getDisplayName().equalsIgnoreCase(plugin.alternatives.getString("Rezepte." + string + ".UL.Name"))) {
				return false;
			}
		}
		if(craftingMatrix[7].getType() != Material.AIR) {
			if(craftingMatrix[7].getItemMeta().getDisplayName() == null) {
				return false;
			}
			if(!craftingMatrix[7].getItemMeta().getDisplayName().equalsIgnoreCase(plugin.alternatives.getString("Rezepte." + string + ".UM.Name"))) {
				return false;
			}
		}
		if(craftingMatrix[8].getType() != Material.AIR) {
			if(craftingMatrix[8].getItemMeta().getDisplayName() == null) {
				return false;
			}
			if(!craftingMatrix[8].getItemMeta().getDisplayName().equalsIgnoreCase(plugin.alternatives.getString("Rezepte." + string + ".UR.Name"))) {
				return false;
			}
		}
		
		return true;
	}

}
