package org.community.newSettlers.Listener;

//import java.util.HashMap;
//import java.util.Map;
//import java.util.Map.Entry;

import org.bukkit.ChatColor;
import org.bukkit.Material;
//import org.bukkit.Location;
//import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.community.newSettlers.newSettlers;
import org.community.newSettlers.Town.Town;

public class newSettlersBlockBreakEvent implements Listener {
	
	private final newSettlers plugin;

	public newSettlersBlockBreakEvent(newSettlers plugin)
	{
		this.plugin = plugin;
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler(priority = EventPriority.LOW)
	public void onBlockBreak(BlockBreakEvent event) {
		
		if(plugin.adminMode.get(event.getPlayer()) == null){
			
		} else if(plugin.adminMode.get(event.getPlayer()) == false){
			
		} else {
			return;
		}	
		
		if(event.getBlock().getWorld().getName().equalsIgnoreCase("Berufeinstanz")) {
			if(plugin.config.getList("Berufeinstanz.Whitelist.Break") == null) {
				event.setCancelled(true);
				return;
			} else {
				if(plugin.config.getList("Berufeinstanz.Whitelist.Break").contains(event.getBlock().getType().name())) {
					return;
				}
			}
		}
				
		Town town = plugin.nSCore.getChunkInfo(event.getBlock().getLocation().getWorld().getName() + "," + event.getBlock().getLocation().getChunk().getX() + "," + event.getBlock().getLocation().getChunk().getZ()); 
		
		if(event.getBlock().getWorld().getName().equalsIgnoreCase("Startinsel") && town != null) {
			Material block = event.getBlock().getType();
			if(block == Material.SIGN || block == Material.SIGN_POST || block == Material.CHEST) {
				
			} else {
				event.setCancelled(true);
				event.getPlayer().sendMessage(ChatColor.RED + "Auf dem Marktplatz darfst du nur Schilder und Truhen abbauen!");
			}
		}
		
		if(town == null) {	
			if(plugin.config.getList("Wilderness.Whitelist.Break") == null) {
				return;
			}else if(plugin.config.getList("Wilderness.Whitelist.Break").contains(event.getBlock().getType().name())) {
				return;
			} else {
				/*Player player = event.getPlayer();

				if(plugin.fourWays.fWCore.questModePlayer.get(player.getName()) != null) {
					if(plugin.fourWays.fWCore.questModePlayer.get(player.getName())) {
						String block = event.getBlock().getType().name();
						String blockType = plugin.fourWays.fWItems.getBlockType(event.getBlock(), null);
						String blockFull = "";
						if(blockType == "") {
							blockFull = block;
						} else {
							blockFull = block + "-" + blockType;
						}

						if(plugin.fourWays.block.getString("Block." + blockFull) == null) {
							String[] split = blockFull.split("-");
							blockFull = split[0];
						}
						if(plugin.fourWays.fWCore.questModeBlock.get(player.getName()).equalsIgnoreCase(blockFull)) {
							if(plugin.fourWays.fWCore.questModeAmountTemp.get(player.getName()) < plugin.fourWays.fWCore.questModeAmount.get(player.getName())) {
								plugin.fourWays.fWCore.questModeAmountTemp.put(player.getName(), plugin.fourWays.fWCore.questModeAmountTemp.get(player.getName()) + 1);
								player.sendMessage(ChatColor.DARK_GRAY + "" + plugin.fourWays.fWCore.questModeAmountTemp.get(player.getName()) + " / " + plugin.fourWays.fWCore.questModeAmount.get(player.getName()) + " abgebaut.");
								plugin.fourWays.fWCore.questModeBlockRefreshList.put(event.getBlock().getWorld().getName() + "," + event.getBlock().getX() + "," + event.getBlock().getY() + "," + event.getBlock().getZ() + "," + event.getBlock().getType().name() + "," + plugin.fourWays.fWItems.getBlockType(event.getBlock(), null), player.getName());
							}
							if(plugin.fourWays.fWCore.questModeAmountTemp.get(player.getName()) >= plugin.fourWays.fWCore.questModeAmount.get(player.getName()))  {
								int taskID = plugin.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new resetBlocks(plugin.fourWays.fWCore.questModeBlockRefreshList, player.getName(), plugin.fourWays.fWCore.questModeAmount.get(player.getName())), 600L, 20L);
								plugin.fourWays.fWCore.questModeTaskID.put(player.getName(), taskID);
								plugin.fourWays.fWCore.questModePlayer.remove(player.getName());
								plugin.fourWays.fWCore.questModeBlock.remove(player.getName());
								plugin.fourWays.fWCore.questModeAmount.remove(player.getName());
								plugin.fourWays.fWCore.questModeAmountTemp.remove(player.getName());
								plugin.fourWays.fWCore.questModeNPC.remove(player.getName());
								plugin.fourWays.fWCache.saveQuestmode();
								plugin.fourWays.questmode.set("Player." + player.getName() + ".Block", null);
								plugin.fourWays.questmode.set("Player." + player.getName() + ".Amount", null);
								plugin.fourWays.questmode.set("Player." + player.getName() + ".TempAmount", null);
								plugin.fourWays.questmode.set("Player." + player.getName() + ".NPC", null);
								plugin.fourWays.questmode.set("Player." + player.getName(), null);
								plugin.fourWays.fWQuestmode.saveConfig();
								plugin.permission.playerRemoveGroup(player, "Questmodus");
							}
						}
						return;
					}
				}*/
				event.setCancelled(true);
				event.getPlayer().updateInventory();
				event.getPlayer().sendMessage(ChatColor.DARK_GRAY + "Du darfst in der Wildnis nichts abbauen.");
				return;
			}			
		} else {
			Player player = event.getPlayer();
			if(town.isMember(player.getName())) {
				if(town.isBreakAllowed()) {
					/*if(plugin.fourWays.fWCore.questModePlayer.get(player.getName()) != null) {
						if(plugin.fourWays.fWCore.questModePlayer.get(player.getName())) {
							String block = event.getBlock().getType().name();
							String blockType = plugin.fourWays.fWItems.getBlockType(event.getBlock(), null);
							String blockFull = "";
							if(blockType == "") {
								blockFull = block;
							} else {
								blockFull = block + "-" + blockType;
							}
							
							if(plugin.fourWays.block.getString("Block." + blockFull) == null) {
								String[] split = blockFull.split("-");
								blockFull = split[0];
							}
							if(plugin.fourWays.fWCore.questModeBlock.get(player.getName()).equalsIgnoreCase(blockFull)) {
								if(plugin.fourWays.fWCore.questModeAmountTemp.get(player.getName()) < plugin.fourWays.fWCore.questModeAmount.get(player.getName())) {
									plugin.fourWays.fWCore.questModeAmountTemp.put(player.getName(), plugin.fourWays.fWCore.questModeAmountTemp.get(player.getName()) + 1);
									player.sendMessage(ChatColor.DARK_GRAY + "" + plugin.fourWays.fWCore.questModeAmountTemp.get(player.getName()) + " / " + plugin.fourWays.fWCore.questModeAmount.get(player.getName()) + " abgebaut.");
									plugin.fourWays.fWCore.questModeBlockRefreshList.put(event.getBlock().getWorld().getName() + "," + event.getBlock().getX() + "," + event.getBlock().getY() + "," + event.getBlock().getZ() + "," + event.getBlock().getType().name() + "," + plugin.fourWays.fWItems.getBlockType(event.getBlock(), null), player.getName());
								}
								if(plugin.fourWays.fWCore.questModeAmountTemp.get(player.getName()) >= plugin.fourWays.fWCore.questModeAmount.get(player.getName()))  {
									int taskID = plugin.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new resetBlocks(plugin.fourWays.fWCore.questModeBlockRefreshList, player.getName(), plugin.fourWays.fWCore.questModeAmount.get(player.getName())), 600L, 20L);
									plugin.fourWays.fWCore.questModeTaskID.put(player.getName(), taskID);
									plugin.fourWays.fWCore.questModePlayer.remove(player.getName());
									plugin.fourWays.fWCore.questModeBlock.remove(player.getName());
									plugin.fourWays.fWCore.questModeAmount.remove(player.getName());
									plugin.fourWays.fWCore.questModeAmountTemp.remove(player.getName());
									plugin.fourWays.fWCore.questModeNPC.remove(player.getName());
									plugin.fourWays.fWCache.saveQuestmode();
									plugin.fourWays.questmode.set("Player." + player.getName() + ".Block", null);
									plugin.fourWays.questmode.set("Player." + player.getName() + ".Amount", null);
									plugin.fourWays.questmode.set("Player." + player.getName() + ".TempAmount", null);
									plugin.fourWays.questmode.set("Player." + player.getName() + ".NPC", null);
									plugin.fourWays.questmode.set("Player." + player.getName(), null);
									plugin.fourWays.fWQuestmode.saveConfig();
									plugin.permission.playerRemoveGroup(player, "Questmodus");
								}
							}
							return;
						}
					}*/
					return;
				} else {
					event.setCancelled(true);
					event.getPlayer().updateInventory();
					player.sendMessage("Deine Stadt genehmigt dir den Abbau nicht.");
					return;
				}
			}
			
			if(town.isAlly(player)) {
				if(town.isBreakAllowedAlly()) {
					return;
				} else {
					event.setCancelled(true);
					event.getPlayer().updateInventory();
					player.sendMessage("Die verbündete Stadt genehmigt dir den Abbau nicht.");
					return;
				}
			}
			
			if(town.isBreakAllowedOutsider()) {
				return;
			} else {
				event.setCancelled(true);
				event.getPlayer().updateInventory();
				player.sendMessage("Diese Stadt genehmigt dir den Abbau nicht.");
				return;
			}
		}
		
	}

	/*private class resetBlocks implements Runnable{

		private Map<String, String> blockCache = new HashMap<String, String>();
		private String player = "";
		private int count = 1;
		private int amount = 0;
		
		public resetBlocks(Map<String, String> blockCache, String player, int amount) {
			this.blockCache = blockCache;
			this.player = player;
			this.amount = amount;
		}

		public void run() {
			for(Entry<String, String> entry : blockCache.entrySet()){				
				if(count >= amount) {
					plugin.getServer().getScheduler().cancelTask(plugin.fourWays.fWCore.questModeTaskID.get(player));
					plugin.LogInfo("All blocks have been restored.");
				}
				if(entry.getValue().equalsIgnoreCase(player)) {
					String[] params = entry.getKey().split(",");
					Location locations = new Location(plugin.getServer().getWorld(params[0]), Integer.parseInt(params[1]), Integer.parseInt(params[2]), Integer.parseInt(params[3]));
					Block block = plugin.getServer().getWorld(params[0]).getBlockAt(locations);
					plugin.getPlayerByName(player).sendMessage(params);
					if(params.length == 5) {
						plugin.fourWays.fWItems.setBlockData(block, params[4], "");
					} else {
						plugin.fourWays.fWItems.setBlockData(block, params[4], params[5]);
					}					
					plugin.fourWays.questmode.set("Blocklist." + entry.getKey(), null);
					plugin.fourWays.fWQuestmode.saveConfig();
					blockCache.remove(entry.getKey());
					count++;
					break;
				}
			}
		}

	}*/
	
}