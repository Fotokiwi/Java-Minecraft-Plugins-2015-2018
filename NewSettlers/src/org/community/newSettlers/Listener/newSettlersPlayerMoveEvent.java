package org.community.newSettlers.Listener;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.community.newSettlers.newSettlers;
import org.community.newSettlers.Town.Town;


public class newSettlersPlayerMoveEvent implements Listener {	
	
	private newSettlers plugin;

	public newSettlersPlayerMoveEvent(newSettlers plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void onPlayerMove(PlayerMoveEvent event) {
	
		Player player = event.getPlayer();
		/*if(plugin.nSCore.playerDisplayChunks.get(player) == null && plugin.nSCore.playerDisplayMap.get(player) == null)
			return;
		if(plugin.nSCore.playerDisplayChunks.get(player) == false && plugin.nSCore.playerDisplayMap.get(player) == false)
			return;*/
		String oldChunk = event.getFrom().getWorld().getName() + "," + event.getFrom().getChunk().getX() + "," + event.getFrom().getChunk().getZ();
		String newChunk = event.getTo().getWorld().getName() + "," + event.getTo().getChunk().getX() + "," + event.getTo().getChunk().getZ();
		
		if(oldChunk.equals(newChunk)){
			return;
		} else {
			
			if(plugin.nSCore.playerDisplayChunks.get(player) != null){
				if(plugin.nSCore.playerDisplayChunks.get(player)) {
					//player.sendMessage("Chunkwechsel");	
					Town town = plugin.nSCore.getChunkInfo(newChunk);
					if(town == null) {
						player.sendMessage(ChatColor.YELLOW + "Wildnis");
					} else {
						player.sendMessage(ChatColor.YELLOW + "" + town.getName());
					}
				}
			}
			if(plugin.nSCore.playerDisplayMap.get(player) != null){
				if(plugin.nSCore.playerDisplayMap.get(player)) {
					String output = "";	
					Town town = null;
						
					player.sendMessage("+~+~+~+~+~+~+~+~+ Regionskarte +~+~+~+~+~+~+~+~+");
					for(int i = -4; i < 5; i++) {
						
						for(int j = -8; j < 9; j++) {
							town = plugin.nSCore.getChunkInfo(event.getTo().getWorld().getName() + "," + (event.getTo().getChunk().getX() + i) + "," + (event.getTo().getChunk().getZ() + j));
							if(i == 0 && j == 0) {
								if(town == null) {
									output += ChatColor.RED + "O";
								} else {
									output += ChatColor.RED + "X";
								}
							} else {
								if(town == null) {
									output += ChatColor.DARK_GRAY + "O";
								} else {
									output += ChatColor.GREEN + "X";
								}
							}
								
						}
						
						player.sendMessage(output);
						output = "";
						
					}
				}
			}
				
		}			
		
	}
	
}