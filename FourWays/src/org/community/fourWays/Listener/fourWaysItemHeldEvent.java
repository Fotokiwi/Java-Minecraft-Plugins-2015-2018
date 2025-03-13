package org.community.fourWays.Listener;

import java.util.Set;

import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.community.fourWays.fourWays;
import org.community.fourWays.User.User;

public class fourWaysItemHeldEvent implements Listener {
	
	private final fourWays plugin;

	public fourWaysItemHeldEvent(fourWays plugin)
	{
		this.plugin = plugin;
	}
	
	@SuppressWarnings({ "deprecation" })
	@EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
	public void onItemHeldChange(PlayerItemHeldEvent event) {
		
		if(plugin.adminMode.get(event.getPlayer()) == null){
			
		} else if(plugin.adminMode.get(event.getPlayer()) == false){
			
		} else {
			return;
		}	
		
		Player player = event.getPlayer();
		User user = plugin.fWUsers.getPlayerInfo(player);
		ItemStack item = player.getInventory().getItem(event.getNewSlot());
		
		if(item != null)
		{
			String itemID = item.getType().toString();
			String itemString = "";
			if (plugin.block.getBoolean("Block." + itemID + ".Nutzung.Ignoriere-Haltbarkeit") == true){
				itemString = itemID.replace("'", "");
			} else{
				int itemdamage = item.getDurability();
				if(itemdamage == 0){
					itemString = itemID.replace("'", "");
				} else{
					itemString = itemID.replace("'", "") + "-" + (new Integer(itemdamage)).toString().replace("'", "");
				}				
			}
			
			PlayerInventory inventory = player.getInventory();
			
			ConfigurationSection blockSection = null;
			
			if(itemString.equalsIgnoreCase("BOW") && item.getEnchantments().size() >= 1) {
				blockSection = plugin.block.getConfigurationSection("Block." + itemString + ".Nutzung_Verzauberung.Beruf");
			} else {
				blockSection = plugin.block.getConfigurationSection("Block." + itemString + ".Nutzung.Beruf");
			}
			
			if(blockSection == null)
				return;
			
			Set<String> blockKeys = blockSection.getKeys(false);
	  	  	String[] blockArray = blockKeys.toArray(new String[0]); 
	    	
	  	  	int blockLevel;
			
			String[] blockInfo = new String[2];
			blockInfo = user.getJobHash().split(",");
			String playerClass = blockInfo[1];
			int playerLevel = new Integer(blockInfo[0]);
			
			for(int i = 0; i < blockArray.length; i++){
				
				if(itemString.equalsIgnoreCase("BOW") && item.getEnchantments().size() >= 1) {
					blockLevel = plugin.block.getInt("Block." + itemString + ".Nutzung_Verzauberung.Beruf." + blockArray[i], 0);
				} else {
					blockLevel = plugin.block.getInt("Block." + itemString + ".Nutzung.Beruf." + blockArray[i], 0);
				}
				
				if(playerClass.contains(blockArray[i]) && playerLevel >= blockLevel) {
					return;
				}
			}
			if (inventory.firstEmpty() == -1){
				inventory.clear(event.getNewSlot());
				player.getWorld().dropItem(player.getLocation().add(0, 1, 0), item);
				player.sendMessage(ChatColor.GOLD + "Du darfst dieses Item nicht nutzen.");
				player.sendMessage(ChatColor.GOLD + "Dein Inventar ist voll, das Item wurde auf den Boden gelegt.");
				player.updateInventory();
				return;
			} else{
				int newItemSlot = inventory.firstEmpty();
				inventory.setItem(newItemSlot, item);
				inventory.clear(event.getNewSlot());
				player.sendMessage(ChatColor.GOLD + "Du darfst dieses Item nicht nutzen.");
				player.updateInventory();
				return;
			}
		} else {
			return;
		}
		
	}
	
}