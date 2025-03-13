package org.community.fourWays.Listener;

import java.util.List;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.community.fourWays.fourWays;
import org.community.fourWays.User.User;
import org.community.newSettlers.Town.Town;


public class fourWaysEntityDeathEvent implements Listener {

	private fourWays plugin;


	public fourWaysEntityDeathEvent(fourWays plugin) {
		this.plugin = plugin;
	}

	@EventHandler(priority = EventPriority.LOW)
	public void onEntityDeath(EntityDeathEvent event) {
		
		if (event.getEntity() instanceof Player) {
			return;
		}

		if (!(event.getEntity().getKiller() instanceof Player)) {
			event.setDroppedExp(0);
			event.getDrops().clear();
			return;
		}
		
		String entity = event.getEntityType().name();
		Player player = event.getEntity().getKiller();
		User user = plugin.fWUsers.getPlayerInfo(player);
		
		if(entity.equalsIgnoreCase("SHEEP") || entity.equalsIgnoreCase("COW") || entity.equalsIgnoreCase("PIG") || entity.equalsIgnoreCase("CHICKEN") || entity.equalsIgnoreCase("HORSE")) {
			
			List<ItemStack> drops = event.getDrops();
			
			for(int i = 0; i < drops.size(); i++) {
				ItemStack item = drops.get(i);
				
				ConfigurationSection entitySection = plugin.entity.getConfigurationSection("Entity." + entity + ".Schlachten.Beruf");
				
				//player.sendMessage(entity + "" + item.getType().toString());
				
				if(entitySection == null) {
					
				} else {
					Set<String> entityKeys = entitySection.getKeys(false);
					String[] entityArray = entityKeys.toArray(new String[0]);  	  	

					int blockLevel;

					String[] blockInfo = new String[2];
					blockInfo = user.getJobHash().split(",");
					String playerClass = blockInfo[1];
					int playerLevel = new Integer(blockInfo[0]);

					for(int j = 0; j < entityArray.length; j++){
						blockLevel = plugin.entity.getInt("Entity." + entity + ".Schlachten.Beruf." + entityArray[j], 0);
						if(playerClass.contains(entityArray[j]) && playerLevel >= blockLevel) {
							if(plugin.entity.getString("Entity." + entity + ".Schlachten.Gebaeude") != null) {
								Town town = plugin.newSettlersAPI.nSAPI.getChunkTown(event.getEntity().getLocation().getChunk());
								if(town == null) {
									//player.sendMessage("Du hast diesen Block nun initialisiert");
									user.addExp(plugin.entity.getInt("Entity." + entity + ".Schlachten.Exp", 0));
									//event.getDrops().remove(i);
									//return;
								} else {
									if(town.getBuildingStatus(plugin.entity.getString("Entity." + entity + ".Schlachten.Gebaeude"))) {
										player.sendMessage("" + plugin.entity.getString("Entity." + entity + ".Schlachten.Gebaeude"));
										user.addExp(plugin.entity.getInt("Entity." + entity + ".Schlachten.Exp", 0));
										
										if(item.getType() == Material.GRILLED_PORK || item.getType() == Material.COOKED_BEEF || item.getType() == Material.COOKED_CHICKEN){
											
										} else {
											event.getEntity().getWorld().dropItem(event.getEntity().getLocation(), item);
										}											
										
										//event.getDrops().remove(i);
										//return;
									} else {
										//player.sendMessage("Die Stadt verfügt nicht über das passende Gebäude.");
										//event.getDrops().remove(i);
										//return;
									}
								}
							} else {
								//player.sendMessage("Du hast diesen Block nun initialisiert");
								user.addExp(plugin.entity.getInt("Entity." + entity + ".Schlachten.Exp", 0));
								//event.getDrops().remove(i);
								//return;
							}

						} else {
							//event.getDrops().remove(i);
						}
					}
				}
			}
			event.getDrops().clear();
			event.setDroppedExp(0);
		} else {			
			List<ItemStack> drops = event.getDrops();
			
			for(int i = 0; i < drops.size(); i++) {
				ItemStack item = drops.get(i);
				
				ConfigurationSection entitySection = plugin.entity.getConfigurationSection("Entity." + entity + ".Pluendern." + item.getType().toString() + ".Beruf");
				
				//player.sendMessage(entity + "" + item.getType().toString());
				
				if(entitySection == null) {
					
				} else {
					Set<String> entityKeys = entitySection.getKeys(false);
					String[] entityArray = entityKeys.toArray(new String[0]);  	  	

					int blockLevel;

					String[] blockInfo = new String[2];
					blockInfo = user.getJobHash().split(",");
					String playerClass = blockInfo[1];
					int playerLevel = new Integer(blockInfo[0]);

					for(int j = 0; j < entityArray.length; j++){
						blockLevel = plugin.entity.getInt("Entity." + entity + ".Pluendern." + item.getType().toString() + ".Beruf." + entityArray[j], 0);
						//player.sendMessage(entityArray[j] + "" + blockLevel);
						if(playerClass.contains(entityArray[j]) && playerLevel >= blockLevel) {
							event.getEntity().getWorld().dropItem(event.getEntity().getLocation(), item);
						}
					}
				}
				
			}
			user.addExp(plugin.entity.getInt("Entity." + entity + ".Pluendern.Exp", 0));
		}	
		
		event.getDrops().clear();
		event.setDroppedExp(0);
	}
}