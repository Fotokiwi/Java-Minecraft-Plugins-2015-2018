package org.community.fourWays.Listener;

import java.util.Set;

import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.community.fourWays.fourWays;
import org.community.fourWays.User.User;
import org.community.newSettlers.Town.Town;


public class fourWaysInventoryClickEvent implements Listener{

	private fourWays plugin;

	public fourWaysInventoryClickEvent(fourWays plugin){
		this.plugin = plugin;
	}

	@EventHandler(priority = EventPriority.NORMAL)
	public void onInventoryClick(InventoryClickEvent event) {
		
		if(!(event.getWhoClicked() instanceof Player))
			return;
		
		Player player = (Player) event.getWhoClicked();
		
		if(plugin.adminMode.get(player) == null){
			
		} else if(plugin.adminMode.get(player) == false){
			
		} else {
			return;
		}

		if(!(event.getInventory().getHolder() instanceof Horse))
			return;
		if(event.isShiftClick() && (event.getInventory().getHolder() instanceof Horse)) {
			player.sendMessage(ChatColor.RED + "Im Handwerksfenster ist Shiftklick nicht möglich.");
			event.setCancelled(true);
			return;
		}	
		
		int rawSlot = event.getRawSlot();
		
		if(rawSlot > 1) {
			return;		
		}
		
		String blockID = event.getCursor().getType().toString();
		String blockSubID = "";
		
		if(blockID.equalsIgnoreCase("AIR")) {
			blockID = event.getCursor().getType().toString();
			blockSubID = plugin.fWItems.getBlockType(null, event.getCurrentItem());
		} else {
			blockSubID = plugin.fWItems.getBlockType(null, event.getCursor());
		}

		String blockIdentity = "";
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
		
		//plugin.getServer().broadcastMessage("Item: " + blockIdentity);
		
		ConfigurationSection blockSection = plugin.block.getConfigurationSection("Block." + blockIdentity + ".Nutzung.Beruf");
		
		if(blockSection == null){
			return;
		}
		
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
							//player.sendMessage("" + plugin.block.getString("Block." + blockIdentity + ".Nutzung.Gebaeude"));
							//player.sendMessage("Du hast diesen Block nun initialisiert");
							user.addExp(plugin.block.getInt("Block." + blockIdentity + ".Nutzung.Exp", 0));
							return;
						} else {
							//player.sendMessage("Die Stadt verfügt nicht über das passende Gebäude.");
							event.setCancelled(true);
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
		event.setCancelled(true);
		//player.sendMessage("Du hast nicht den nütigen Beruf oder die entsprechende Stufe.");
		
    }
}
