package org.community.fourWays.Listener;

import java.util.Set;

import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.community.fourWays.fourWays;
import org.community.fourWays.User.User;
import org.community.newSettlers.Town.Town;

public class fourWaysBlockPlaceEvent implements Listener {
	
	private final fourWays plugin;

	public fourWaysBlockPlaceEvent(fourWays plugin)
	{
		this.plugin = plugin;
	}
	@SuppressWarnings("deprecation")
	@EventHandler(priority = EventPriority.NORMAL)
	public void onBlockPlace(BlockPlaceEvent event) {
		
		if (event.isCancelled())
			return;
		if (!(event.getPlayer() instanceof Player))
			return;
		
		if(plugin.adminMode.get(event.getPlayer()) == null){
			
		} else if(plugin.adminMode.get(event.getPlayer()) == false){
			
		} else {
			return;
		}	
		
		String blockID = event.getBlock().getType().toString();
		String blockSubID = plugin.fWItems.getBlockType(event.getBlock(), null);
		String blockIdentity = "";
		Player player = event.getPlayer();
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
		
		if(plugin.preCache_BlockPlace.get(player) != null){
			if(plugin.preCache_BlockPlace.get(player).equalsIgnoreCase(blockIdentity)) {
				//player.sendMessage("Allow preCache contains ID");
				if(plugin.block.getString("Block." + blockIdentity + ".Platzieren.Gebaeude") != null) {
					//player.sendMessage("Diesen Block gibt es in der Config");
					Town town = plugin.newSettlersAPI.nSAPI.getChunkTown(event.getBlock().getChunk());
					if(town == null) {
						//player.sendMessage("Du hast diesen Block nun initialisiert");
						user.addExp(plugin.block.getInt("Block." + blockIdentity + ".Platzieren.Exp", 0));
						return;
					} else {
						if(town.getBuildingStatus(plugin.block.getString("Block." + blockIdentity + ".Platzieren.Gebaeude"))) {
							//player.sendMessage("Du hast diesen Block nun initialisiert");
							user.addExp(plugin.block.getInt("Block." + blockIdentity + ".Platzieren.Exp", 0));
							return;
						} else {
							player.sendMessage("Die Stadt verfügt nicht über das passende Gebäude.");
							event.setCancelled(true);
							player.updateInventory();
							event.getBlock().setType(Material.AIR);
							return;
						}
					}
				} else {
					//player.sendMessage("Diesen Block gibt es nicht in der Config");
					user.addExp(plugin.block.getInt("Block." + blockIdentity + ".Platzieren.Exp", 0));
					return;
				}
			}
		}
		
		if(plugin.preCache_BlockPlace_disallow.get(player) != null){
			if(plugin.preCache_BlockPlace_disallow.get(player).equalsIgnoreCase(blockIdentity)) {
				//player.sendMessage("Disallow preCache contains ID");
				event.setCancelled(true);
				player.updateInventory();
				event.getBlock().setType(Material.AIR);
				return;
			}
		}
		
		ConfigurationSection blockSection = plugin.block.getConfigurationSection("Block." + blockIdentity + ".Platzieren.Beruf");
		
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
			blockLevel = plugin.block.getInt("Block." + blockIdentity + ".Platzieren.Beruf." + blockArray[i], 0);
			if(playerClass.contains(blockArray[i]) && playerLevel >= blockLevel) {
				if(plugin.block.getString("Block." + blockIdentity + ".Platzieren.Gebaeude") != null) {
					Town town = plugin.newSettlersAPI.nSAPI.getChunkTown(event.getBlock().getChunk());
					if(town == null) {
						//player.sendMessage("Du hast diesen Block nun initialisiert");
						user.addExp(plugin.block.getInt("Block." + blockIdentity + ".Platzieren.Exp", 0));
						plugin.preCache_BlockPlace.put(player, blockIdentity);
						return;
					} else {
						if(town.getBuildingStatus(plugin.block.getString("Block." + blockIdentity + ".Platzieren.Gebaeude"))) {
							player.sendMessage("" + plugin.block.getString("Block." + blockIdentity + ".Platzieren.Gebaeude"));
							//player.sendMessage("Du hast diesen Block nun initialisiert");
							user.addExp(plugin.block.getInt("Block." + blockIdentity + ".Platzieren.Exp", 0));
							plugin.preCache_BlockPlace.put(player, blockIdentity);
							return;
						} else {
							//player.sendMessage("Die Stadt verfügt nicht über das passende Gebäude.");
							event.setCancelled(true);
							player.updateInventory();
							event.getBlock().setType(Material.AIR);
							plugin.preCache_BlockPlace.put(player, blockIdentity);
							return;
						}
					}
				} else {
					//player.sendMessage("Du hast diesen Block nun initialisiert");
					user.addExp(plugin.block.getInt("Block." + blockIdentity + ".Platzieren.Exp", 0));
					plugin.preCache_BlockPlace.put(player, blockIdentity);
					return;
				}
					
			}
		}
		
		event.setCancelled(true);
		player.updateInventory();
		event.getBlock().setType(Material.AIR);
		plugin.preCache_BlockPlace_disallow.put(player, blockIdentity);
		player.sendMessage("Du hast nicht den nötigen Beruf oder die entsprechende Stufe.");		
		
	}
	
}