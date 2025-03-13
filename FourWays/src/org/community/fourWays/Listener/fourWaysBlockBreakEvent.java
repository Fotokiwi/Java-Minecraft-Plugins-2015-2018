package org.community.fourWays.Listener;

import java.util.Set;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Furnace;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.community.fourWays.fourWays;
import org.community.fourWays.User.User;
import org.community.newSettlers.Town.Town;

public class fourWaysBlockBreakEvent implements Listener {
	
	private final fourWays plugin;

	public fourWaysBlockBreakEvent(fourWays plugin)
	{
		this.plugin = plugin;
	}
	@EventHandler(priority = EventPriority.NORMAL)
	public void onBlockBreak(BlockBreakEvent event) {
		
		if (event.isCancelled())
			return;
		if (!(event.getPlayer() instanceof Player))
			return;
		
		if(plugin.adminMode.get(event.getPlayer()) == null){
			
		} else if(plugin.adminMode.get(event.getPlayer()) == false){
			
		} else {
			return;
		}
		
		if(event.getBlock().getType() == Material.FURNACE || event.getBlock().getType() == Material.BURNING_FURNACE) {
			Furnace furnace = (Furnace) event.getBlock().getState();
			furnace.getInventory().setResult(null);
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
		
		/*if(plugin.fWCore.questModePlayer.get(player.getName()) != null) {
			if(plugin.fWCore.questModePlayer.get(player.getName())) {				
				if(plugin.fWCore.questModeBlock.get(player.getName()).equalsIgnoreCase(blockIdentity)) {
					user.addExp(plugin.block.getInt("Block." + blockIdentity + ".Abbau.Exp", 0));
					return;
				}
			}
		}*/
		
		if(plugin.preCache_BlockBreak.get(player) != null){
			if(plugin.preCache_BlockBreak.get(player).equalsIgnoreCase(blockIdentity)) {
				//player.sendMessage("Allow preCache contains ID");
				if(plugin.block.getString("Block." + blockIdentity + ".Abbau.Gebaeude") != null) {
					//player.sendMessage("Diesen Block gibt es in der Config");
					Town town = plugin.newSettlersAPI.nSAPI.getChunkTown(event.getBlock().getChunk());
					if(town == null) {
						//player.sendMessage("Du hast diesen Block nun initialisiert");
						user.addExp(plugin.block.getInt("Block." + blockIdentity + ".Abbau.Exp", 0));
						return;
					} else {
						if(town.getBuildingStatus(plugin.block.getString("Block." + blockIdentity + ".Abbau.Gebaeude"))) {
							//player.sendMessage("Du hast diesen Block nun initialisiert");
							user.addExp(plugin.block.getInt("Block." + blockIdentity + ".Abbau.Exp", 0));
							return;
						} else {
							player.sendMessage("Die Stadt verfügt nicht über das passende Gebäude.");
							event.setCancelled(true);
							event.getBlock().setType(Material.AIR);
							return;
						}
					}
				} else {
					//player.sendMessage("Diesen Block gibt es nicht in der Config");
					user.addExp(plugin.block.getInt("Block." + blockIdentity + ".Abbau.Exp", 0));
					return;
				}
			}
		}
		
		if(plugin.preCache_BlockBreak_disallow.get(player) != null){
			if(plugin.preCache_BlockBreak_disallow.get(player).equalsIgnoreCase(blockIdentity)) {
				//player.sendMessage("Disallow preCache contains ID");
				event.setCancelled(true);
				checkDoublePlant(event.getBlock());
				event.getBlock().setType(Material.AIR);
				return;
			}
		}
		
		ConfigurationSection blockSection = plugin.block.getConfigurationSection("Block." + blockIdentity + ".Abbau.Beruf");
		
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
			blockLevel = plugin.block.getInt("Block." + blockIdentity + ".Abbau.Beruf." + blockArray[i], 0);
			if(playerClass.contains(blockArray[i]) && playerLevel >= blockLevel) {
				if(plugin.block.getString("Block." + blockIdentity + ".Abbau.Gebaeude") != null) {
					Town town = plugin.newSettlersAPI.nSAPI.getChunkTown(event.getBlock().getChunk());
					if(town == null) {
						//player.sendMessage("Du hast diesen Block nun initialisiert");
						user.addExp(plugin.block.getInt("Block." + blockIdentity + ".Abbau.Exp", 0));
						plugin.preCache_BlockBreak.put(player, blockIdentity);
						return;
					} else {
						if(town.getBuildingStatus(plugin.block.getString("Block." + blockIdentity + ".Abbau.Gebaeude"))) {
							//player.sendMessage("" + plugin.block.getString("Block." + blockIdentity + ".Abbau.Gebaeude"));
							//player.sendMessage("Du hast diesen Block nun initialisiert");
							user.addExp(plugin.block.getInt("Block." + blockIdentity + ".Abbau.Exp", 0));
							plugin.preCache_BlockBreak.put(player, blockIdentity);
							return;
						} else {
							//player.sendMessage("Die Stadt verfügt nicht über das passende Gebäude.");
							event.setCancelled(true);
							event.getBlock().setType(Material.AIR);
							plugin.preCache_BlockBreak.put(player, blockIdentity);
							return;
						}
					}
				} else {
					//player.sendMessage("Du hast diesen Block nun initialisiert");
					user.addExp(plugin.block.getInt("Block." + blockIdentity + ".Abbau.Exp", 0));
					plugin.preCache_BlockBreak.put(player, blockIdentity);
					return;
				}
					
			}
		}
		
		event.setCancelled(true);
		checkDoublePlant(event.getBlock());
		event.getBlock().setType(Material.AIR);
		plugin.preCache_BlockBreak_disallow.put(player, blockIdentity);
		//player.sendMessage("Du hast nicht den nötigen Beruf oder die entsprechende Stufe.");		
		
	}
	
	@SuppressWarnings("deprecation")
	private void checkDoublePlant(Block block) {
		if(block.getType() == Material.DOUBLE_PLANT) {
			if(block.getData() > 5) {
				Block blockBelow = block.getLocation().add(0, -1, -0).getBlock();
				if(blockBelow.getType() == Material.DOUBLE_PLANT) {
					blockBelow.setType(Material.AIR);
					return;
				}
			}
		}
	}
	
}