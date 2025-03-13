package org.community.fourWays.Listener;

import java.util.Set;

import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBucketFillEvent;
import org.community.fourWays.fourWays;
import org.community.fourWays.User.User;
import org.community.newSettlers.Town.Town;


public class fourWaysPlayerBucketFillEvent implements Listener{

	private fourWays plugin;

	public fourWaysPlayerBucketFillEvent(fourWays plugin){
		this.plugin = plugin;
	}

	@SuppressWarnings("deprecation")
	@EventHandler(priority = EventPriority.NORMAL)
	public void onPlayerBucketFill(PlayerBucketFillEvent event) {
		
		if(plugin.adminMode.get(event.getPlayer()) == null){
			
		} else if(plugin.adminMode.get(event.getPlayer()) == false){
			
		} else {
			return;
		}	
		
		Player player = (Player) event.getPlayer();
		
		User user = plugin.fWUsers.getPlayerInfo(player);
		
		if (event.getItemStack().getType() != Material.MILK_BUCKET) {
			return;
		}
		
		ConfigurationSection entitySection = plugin.entity.getConfigurationSection("Entity.COW.Melken.Beruf");
		
		if(entitySection == null)
			return;
		
		Set<String> entityKeys = entitySection.getKeys(false);
  	  	String[] entityArray = entityKeys.toArray(new String[0]);  	  	
  	  	
		int blockLevel;
		
		String[] blockInfo = new String[2];
		blockInfo = user.getJobHash().split(",");
		String playerClass = blockInfo[1];
		int playerLevel = new Integer(blockInfo[0]);
		
		for(int i = 0; i < entityArray.length; i++){
			blockLevel = plugin.block.getInt("Entity.COW.Melken.Beruf." + entityArray[i], 0);
			if(playerClass.contains(entityArray[i]) && playerLevel >= blockLevel) {
				if(plugin.block.getString("Entity.COW.Melken.Gebaeude") != null) {
					Town town = plugin.newSettlersAPI.nSAPI.getChunkTown(player.getLocation().getChunk());
					if(town == null) {
						//player.sendMessage("Du hast diesen Block nun initialisiert");
						user.addExp(plugin.block.getInt("Entity.COW.Melken.Exp", 0));
						event.setCancelled(true);
						return;
					} else {
						if(town.getBuildingStatus(plugin.block.getString("Entity.COW.Melken.Gebaeude"))) {
							player.sendMessage("" + plugin.block.getString("Entity.COW.Melken.Gebaeude"));
							//player.sendMessage("Du hast diesen Block nun initialisiert");
							user.addExp(plugin.block.getInt("Entity.COW.Melken.Exp", 0));
							return;
						} else {
							//player.sendMessage("Die Stadt verfügt nicht über das passende Gebäude.");
							event.setCancelled(true);
							player.updateInventory();
							return;
						}
					}
				} else {
					//player.sendMessage("Du hast diesen Block nun initialisiert");
					user.addExp(plugin.block.getInt("Entity.COW.Melken.Exp", 0));
					return;
				}
					
			}
		}
		
		event.setCancelled(true);
		player.updateInventory();
		return;
		
    }
}
