package org.community.fourWays.Listener;

import java.util.Set;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.community.fourWays.fourWays;
import org.community.fourWays.User.User;
import org.community.newSettlers.Town.Town;

@SuppressWarnings("deprecation")
public class fourWaysInventoryCloseEvent implements Listener {
		
	private final fourWays plugin;
	Location locB = null;
	 	 
	public fourWaysInventoryCloseEvent(fourWays plugin)
	{
	this.plugin = plugin;
	}
	
	@SuppressWarnings("unused")
	@EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
	public void onInventoryClose(InventoryCloseEvent event) {
		
		Player player = (Player)event.getPlayer();
		
		if(plugin.adminMode.get(event.getPlayer()) == null){
			
		} else if(plugin.adminMode.get(event.getPlayer()) == false){
			
		} else {
			return;
		}	
		
		if(event.getInventory().getHolder() instanceof Horse) {
			manageBarding(event);
			manageSaddle(event);
		}

		ItemStack itemType = player.getInventory().getHelmet();
		if (itemType == null){
			
		} else if (itemType.getType() == Material.AIR){
			
		}
		else{
			manageHelmet(player);
		}
		
		itemType = player.getInventory().getChestplate();
		if (itemType == null){
			
		} else if (itemType.getType() == Material.AIR){
			
		}
		else{
			manageChestplate(player);
		}
		
		itemType = player.getInventory().getLeggings();
		if (itemType == null){
			
		} else if (itemType.getType() == Material.AIR){
			
		}
		else{
			manageLeggings(player);
		}
		
		itemType = player.getInventory().getBoots();
		if (itemType == null){
			
		} else if (itemType.getType() == Material.AIR){
			
		}
		else{
			manageBoots(player);
		}
		ItemStack item = event.getPlayer().getItemInHand();
		
		if(item != null)
		{
			User user = plugin.fWUsers.getPlayerInfo(player);
			
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
					inventory.clear(player.getInventory().getHeldItemSlot());
					player.getWorld().dropItem(player.getLocation().add(0, 1, 0), item);
					player.sendMessage(ChatColor.GOLD + "Du darfst dieses Item nicht nutzen.");
					player.sendMessage(ChatColor.GOLD + "Dein Inventar ist voll, das Item wurde auf den Boden gelegt.");
					player.updateInventory();
					return;
				} else{
					int newItemSlot = inventory.firstEmpty();
					inventory.setItem(newItemSlot, item);
					inventory.clear(player.getInventory().getHeldItemSlot());
					player.sendMessage(ChatColor.GOLD + "Du darfst dieses Item nicht nutzen.");
					player.updateInventory();
					return;
				}
			} else {
				return;
			}
		}
		return;
		
	}
	
	public void manageHelmet(Player player){
		User user = plugin.fWUsers.getPlayerInfo(player);
		ItemStack item = player.getInventory().getHelmet();
		
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
			
			ConfigurationSection blockSection = plugin.block.getConfigurationSection("Block." + itemString + ".Nutzung.Beruf");
			
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
				blockLevel = plugin.block.getInt("Block." + itemString + ".Nutzung.Beruf." + blockArray[i], 0);
				if(playerClass.contains(blockArray[i]) && playerLevel >= blockLevel) {
					return;
				}
			}
			if (inventory.firstEmpty() == -1){
				inventory.setHelmet(new ItemStack(Material.AIR));
				player.getWorld().dropItem(player.getLocation().add(0, 1, 0), item);
				player.sendMessage(ChatColor.GOLD + "Du darfst dieses Item nicht nutzen.");
				player.sendMessage(ChatColor.GOLD + "Dein Inventar ist voll, das Item wurde auf den Boden gelegt.");
				player.updateInventory();
				return;
			} else{
				int newItemSlot = inventory.firstEmpty();
				inventory.setItem(newItemSlot, item);
				inventory.setHelmet(new ItemStack(Material.AIR));
				player.sendMessage(ChatColor.GOLD + "Du darfst dieses Item nicht nutzen.");
				player.updateInventory();
				return;
			}
		} else {
			return;
		}
	}
	
	public void manageChestplate(Player player){
		User user = plugin.fWUsers.getPlayerInfo(player);
		ItemStack item = player.getInventory().getChestplate();
		
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
			
			ConfigurationSection blockSection = plugin.block.getConfigurationSection("Block." + itemString + ".Nutzung.Beruf");
			
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
				blockLevel = plugin.block.getInt("Block." + itemString + ".Nutzung.Beruf." + blockArray[i], 0);
				if(playerClass.contains(blockArray[i]) && playerLevel >= blockLevel) {
					return;
				}
			}
			if (inventory.firstEmpty() == -1){
				inventory.setChestplate(new ItemStack(Material.AIR));
				player.getWorld().dropItem(player.getLocation().add(0, 1, 0), item);
				player.sendMessage(ChatColor.GOLD + "Du darfst dieses Item nicht nutzen.");
				player.sendMessage(ChatColor.GOLD + "Dein Inventar ist voll, das Item wurde auf den Boden gelegt.");
				player.updateInventory();
				return;
			} else{
				int newItemSlot = inventory.firstEmpty();
				inventory.setItem(newItemSlot, item);
				inventory.setChestplate(new ItemStack(Material.AIR));
				player.sendMessage(ChatColor.GOLD + "Du darfst dieses Item nicht nutzen.");
				player.updateInventory();
				return;
			}
		} else {
			return;
		}
	}
	
	public void manageLeggings(Player player){
		User user = plugin.fWUsers.getPlayerInfo(player);
		ItemStack item = player.getInventory().getLeggings();
		
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
			
			ConfigurationSection blockSection = plugin.block.getConfigurationSection("Block." + itemString + ".Nutzung.Beruf");
			
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
				blockLevel = plugin.block.getInt("Block." + itemString + ".Nutzung.Beruf." + blockArray[i], 0);
				if(playerClass.contains(blockArray[i]) && playerLevel >= blockLevel) {
					return;
				}
			}
			if (inventory.firstEmpty() == -1){
				inventory.setLeggings(new ItemStack(Material.AIR));
				player.getWorld().dropItem(player.getLocation().add(0, 1, 0), item);
				player.sendMessage(ChatColor.GOLD + "Du darfst dieses Item nicht nutzen.");
				player.sendMessage(ChatColor.GOLD + "Dein Inventar ist voll, das Item wurde auf den Boden gelegt.");
				player.updateInventory();
				return;
			} else{
				int newItemSlot = inventory.firstEmpty();
				inventory.setItem(newItemSlot, item);
				inventory.setLeggings(new ItemStack(Material.AIR));
				player.sendMessage(ChatColor.GOLD + "Du darfst dieses Item nicht nutzen.");
				player.updateInventory();
				return;
			}
		} else {
			return;
		}
	}
	
	public void manageBoots(Player player){
		User user = plugin.fWUsers.getPlayerInfo(player);
		ItemStack item = player.getInventory().getBoots();
		
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
			
			ConfigurationSection blockSection = plugin.block.getConfigurationSection("Block." + itemString + ".Nutzung.Beruf");
			
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
				blockLevel = plugin.block.getInt("Block." + itemString + ".Nutzung.Beruf." + blockArray[i], 0);
				if(playerClass.contains(blockArray[i]) && playerLevel >= blockLevel) {
					return;
				}
			}
			if (inventory.firstEmpty() == -1){
				inventory.setBoots(new ItemStack(Material.AIR));
				player.getWorld().dropItem(player.getLocation().add(0, 1, 0), item);
				player.sendMessage(ChatColor.GOLD + "Du darfst dieses Item nicht nutzen.");
				player.sendMessage(ChatColor.GOLD + "Dein Inventar ist voll, das Item wurde auf den Boden gelegt.");
				player.updateInventory();
				return;
			} else{
				int newItemSlot = inventory.firstEmpty();
				inventory.setItem(newItemSlot, item);
				inventory.setBoots(new ItemStack(Material.AIR));
				player.sendMessage(ChatColor.GOLD + "Du darfst dieses Item nicht nutzen.");
				player.updateInventory();
				return;
			}
		} else {
			return;
		}
	}
	
	public void manageSaddle(InventoryCloseEvent event) {
		
		if(event.getInventory().getItem(0) == null)
			return;
		
		ItemStack rawSlot = event.getInventory().getItem(0);
				
		String blockID = rawSlot.getType().toString();
		String blockSubID = "";
		
		blockSubID = plugin.fWItems.getBlockType(null, rawSlot);

		String blockIdentity = "";
		Player player = (Player) event.getPlayer();
		User user = plugin.fWUsers.getPlayerInfo(player);
		
		if(blockSubID == "") {
			blockIdentity = blockID + "";
		} else {
			blockIdentity = blockID + "-" + blockSubID;
		}
		
		if(plugin.block.getString("Block." + blockIdentity) == null) {
			String[] split = blockIdentity.split("-");
			blockIdentity = split[0];
		}
		
		player.sendMessage("Item: " + blockIdentity);
		
		ConfigurationSection blockSection = plugin.block.getConfigurationSection("Block." + blockIdentity + ".Nutzung.Beruf");
		
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
			blockLevel = plugin.block.getInt("Block." + blockIdentity + ".Nutzung.Beruf." + blockArray[i], 0);
			if(playerClass.contains(blockArray[i]) && playerLevel >= blockLevel) {
				if(plugin.block.getString("Block." + blockIdentity + ".Nutzung.Gebaeude") != null) {
					Town town = plugin.newSettlersAPI.nSAPI.getChunkTown(player.getLocation().getChunk());
					if(town == null) {
						//player.sendMessage("Du hast diesen Block nun initialisiert");
						user.addExp(plugin.block.getInt("Block." + blockIdentity + ".Nutzung.Exp", 0));
						return;
					} else {
						if(town.getBuildingStatus(plugin.block.getString("Block." + blockIdentity + ".Nutzung.Gebaeude"))) {
							player.sendMessage("" + plugin.block.getString("Block." + blockIdentity + ".Nutzung.Gebaeude"));
							//player.sendMessage("Du hast diesen Block nun initialisiert");
							user.addExp(plugin.block.getInt("Block." + blockIdentity + ".Nutzung.Exp", 0));
							return;
						} else {
							//player.sendMessage("Die Stadt verfügt nicht über das passende Gebäude.");
							player.getWorld().dropItemNaturally(player.getLocation(), rawSlot);
							event.getInventory().remove(rawSlot);
							return;
						}
					}
				} else {
					//player.sendMessage("Du hast diesen Block nun initialisiert");
					user.addExp(plugin.block.getInt("Block." + blockIdentity + ".Nutzung.Exp", 0));
					return;
				}
					
			}
		}		

		player.getWorld().dropItemNaturally(player.getLocation(), rawSlot);
		event.getInventory().remove(rawSlot);
		//player.sendMessage("Du hast nicht den nötigen Beruf oder die entsprechende Stufe.");
	}
	
	public void manageBarding(InventoryCloseEvent event) {
		
		if(event.getInventory().getItem(1) == null)
			return;
		
		ItemStack rawSlot = event.getInventory().getItem(1);
				
		String blockID = rawSlot.getType().toString();
		String blockSubID = "";
		
		blockSubID = plugin.fWItems.getBlockType(null, rawSlot);

		String blockIdentity = "";
		Player player = (Player) event.getPlayer();
		User user = plugin.fWUsers.getPlayerInfo(player);
		
		if(blockSubID == "") {
			blockIdentity = blockID + "";
		} else {
			blockIdentity = blockID + "-" + blockSubID;
		}
		
		if(plugin.block.getString("Block." + blockIdentity) == null) {
			String[] split = blockIdentity.split("-");
			blockIdentity = split[0];
		}
		
		player.sendMessage("Item: " + blockIdentity);
		
		ConfigurationSection blockSection = plugin.block.getConfigurationSection("Block." + blockIdentity + ".Nutzung.Beruf");
		
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
			blockLevel = plugin.block.getInt("Block." + blockIdentity + ".Nutzung.Beruf." + blockArray[i], 0);
			if(playerClass.contains(blockArray[i]) && playerLevel >= blockLevel) {
				if(plugin.block.getString("Block." + blockIdentity + ".Nutzung.Gebaeude") != null) {
					Town town = plugin.newSettlersAPI.nSAPI.getChunkTown(player.getLocation().getChunk());
					if(town == null) {
						//player.sendMessage("Du hast diesen Block nun initialisiert");
						user.addExp(plugin.block.getInt("Block." + blockIdentity + ".Nutzung.Exp", 0));
						return;
					} else {
						if(town.getBuildingStatus(plugin.block.getString("Block." + blockIdentity + ".Nutzung.Gebaeude"))) {
							player.sendMessage("" + plugin.block.getString("Block." + blockIdentity + ".Nutzung.Gebaeude"));
							//player.sendMessage("Du hast diesen Block nun initialisiert");
							user.addExp(plugin.block.getInt("Block." + blockIdentity + ".Nutzung.Exp", 0));
							return;
						} else {
							//player.sendMessage("Die Stadt verfügt nicht über das passende Gebäude.");
							player.getWorld().dropItemNaturally(player.getLocation(), rawSlot);
							event.getInventory().remove(rawSlot);
							return;
						}
					}
				} else {
					//player.sendMessage("Du hast diesen Block nun initialisiert");
					user.addExp(plugin.block.getInt("Block." + blockIdentity + ".Nutzung.Exp", 0));
					return;
				}
					
			}
		}		

		player.getWorld().dropItemNaturally(player.getLocation(), rawSlot);
		event.getInventory().remove(rawSlot);
		//player.sendMessage("Du hast nicht den nötigen Beruf oder die entsprechende Stufe.");
	}
}