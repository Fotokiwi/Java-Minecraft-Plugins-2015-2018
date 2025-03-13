package org.community.fourWays.Listener;

import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.meta.BookMeta;
import org.community.fourWays.fourWays;
import org.community.fourWays.User.User;
import org.community.newSettlers.Town.Town;

public class fourWaysEntityInteractEntityEvent implements Listener {
	
	private fourWays plugin;

	public fourWaysEntityInteractEntityEvent(fourWays plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void onEntityInteract(PlayerInteractEntityEvent event) {
		Player player = event.getPlayer();
		Entity entity = event.getRightClicked();
		
		if(entity.getType() == EntityType.VILLAGER) {
			event.setCancelled(true);
			return;
		}
		
		if(entity.getType() == EntityType.COW && player.getItemInHand().getType() == Material.BUCKET) {
			User user = plugin.fWUsers.getPlayerInfo(player);
			
			ConfigurationSection entitySection = plugin.entity.getConfigurationSection("Entity." + entity + ".Melken.Beruf");
			
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
				blockLevel = plugin.entity.getInt("Entity." + entity + ".Melken.Beruf." + entityArray[i], 0);
				if(playerClass.contains(entityArray[i]) && playerLevel >= blockLevel) {
					if(plugin.entity.getString("Entity." + entity + ".Melken.Gebaeude") != null) {
						Town town = plugin.newSettlersAPI.nSAPI.getChunkTown(entity.getLocation().getChunk());
						if(town == null) {
							//player.sendMessage("Du hast diesen Block nun initialisiert");
							user.addExp(plugin.entity.getInt("Entity." + entity + ".Melken.Exp", 0));
							event.setCancelled(true);
							return;
						} else {
							if(town.getBuildingStatus(plugin.entity.getString("Entity." + entity + ".Melken.Gebaeude"))) {
								player.sendMessage("" + plugin.entity.getString("Entity." + entity + ".Melken.Gebaeude"));
								//player.sendMessage("Du hast diesen Block nun initialisiert");
								user.addExp(plugin.entity.getInt("Entity." + entity + ".Melken.Exp", 0));
								return;
							} else {
								//player.sendMessage("Die Stadt verfügt nicht über das passende Gebäude.");
								event.setCancelled(true);
								return;
							}
						}
					} else {
						//player.sendMessage("Du hast diesen Block nun initialisiert");
						user.addExp(plugin.entity.getInt("Entity." + entity + ".Melken.Exp", 0));
						return;
					}
						
				}
			}
			
			event.setCancelled(true);
			return;
		}
		
		if(entity.getType() == EntityType.SHEEP || entity.getType() == EntityType.COW || entity.getType() == EntityType.PIG || entity.getType() == EntityType.CHICKEN || entity.getType() == EntityType.HORSE) {
			if(player.getItemInHand().getType() == Material.WHEAT || player.getItemInHand().getType() == Material.CARROT_ITEM || player.getItemInHand().getType() == Material.POTATO_ITEM || player.getItemInHand().getType() == Material.SEEDS || player.getItemInHand().getType() == Material.PUMPKIN_SEEDS || player.getItemInHand().getType() == Material.MELON_SEEDS || player.getItemInHand().getType() == Material.GOLDEN_APPLE || player.getItemInHand().getType() == Material.GOLDEN_CARROT) {
				
				User user = plugin.fWUsers.getPlayerInfo(player);
				
				int blockLevel;
				String blockIdentity = "";

				String[] blockInfo = new String[2];
				blockInfo = user.getJobHash().split(",");
				String playerClass = blockInfo[1];
				int playerLevel = new Integer(blockInfo[0]);
				
				blockIdentity = entity.getType().toString();
				
				ConfigurationSection blockSection = plugin.entity.getConfigurationSection("Entity." + blockIdentity
						+ ".Zuechten.Beruf");

				if (blockSection == null)
					return;

				Set<String> blockKeys = blockSection.getKeys(false);
				String[] blockArray = blockKeys.toArray(new String[0]);

				for (int i = 0; i < blockArray.length; i++) {
					blockLevel = plugin.entity.getInt("Entity." + blockIdentity + ".Zuechten.Beruf." + blockArray[i], 0);
					if (playerClass.contains(blockArray[i]) && playerLevel >= blockLevel) {
						if (plugin.entity.getString("Entity." + blockIdentity + ".Zuechten.Gebaeude") != null) {
							Town town = plugin.newSettlersAPI.nSAPI.getChunkTown(player.getLocation().getChunk());
							if (town == null) {
								// player.sendMessage("Du hast diesen Block nun initialisiert");
								user.addExp(plugin.entity.getInt("Entity." + blockIdentity + ".Zuechten.Exp", 0));
								// plugin.preCache_BlockBreak.put(player,
								// blockIdentity);
								if(!breedCheck(player, entity)) {
									event.setCancelled(true);
								}
								return;
							} else {
								if (town.getBuildingStatus(plugin.entity.getString("Entity." + blockIdentity
										+ ".Zuechten.Gebaeude"))) {
									player.sendMessage(""
											+ plugin.entity.getString("Entity." + blockIdentity + ".Zuechten.Gebaeude"));
									// player.sendMessage("Du hast diesen Block nun initialisiert");
									user.addExp(plugin.entity.getInt("Entity." + blockIdentity + ".Zuechten.Exp", 0));
									// plugin.preCache_BlockBreak.put(player,
									// blockIdentity);
									if(!breedCheck(player, entity)) {
										event.setCancelled(true);
									}
									return;
								} else {
									// player.sendMessage("Die Stadt verfügt nicht über das passende Gebäude.");
									// plugin.preCache_BlockBreak.put(player,
									// blockIdentity);
									event.setCancelled(true);
									return;
								}
							}
						} else {
							// player.sendMessage("Du hast diesen Block nun initialisiert");
							user.addExp(plugin.entity.getInt("Entity." + blockIdentity + ".Zuechten.Exp", 0));
							// plugin.preCache_BlockBreak.put(player, blockIdentity);
							
							if(!breedCheck(player, entity)) {
								event.setCancelled(true);
							}
							
							return;
						}
					}
				}				
				
			}
		}
		
		if(entity.getType() == EntityType.HORSE && player.getItemInHand().getType() == Material.WRITTEN_BOOK) {
			BookMeta meta = (BookMeta) player.getItemInHand().getItemMeta();
			if(meta.getAuthor().equalsIgnoreCase("Zuchtregister") && meta.getTitle().equalsIgnoreCase("Besitzurkunde")) {
				UUID uuid = UUID.fromString(meta.getLore().get(0));
				if(entity.getUniqueId().equals(uuid)) {
					Horse horse = (Horse) entity;
					horse.setOwner(player);
					
					UUID horseID = horse.getUniqueId();
					plugin.horses.set("Pferd." + horseID.toString() + ".BesitzerID", player.getUniqueId().toString());
					plugin.horses.set("Pferd." + horseID.toString() + ".Besitzer", player.getName());
					plugin.fWHorses.saveConfig();
					
					player.sendMessage(ChatColor.GREEN + "Herzlichen Glückwunsch, das Pferd ist jetzt dein Eigentum.");
					return;
				} else {
					player.sendMessage(ChatColor.RED + "Diese Besitzurkunde gehört zu einem anderen Pferd.");
				}
			}
		}
		
		if(entity.getType() == EntityType.HORSE) {
			Horse horse = (Horse) entity;
			UUID horseID = horse.getUniqueId();
			if(plugin.horses.getString("Pferd." + horseID.toString() + ".BesitzerID") == null) {
				return;
			}
			if(horseID.toString().equalsIgnoreCase(plugin.horses.getString("Pferd." + horseID.toString() + ".BesitzerID"))) {
				event.setCancelled(true);
				event.getPlayer().sendMessage(ChatColor.RED + "Du darfst dieses Pferd nicht reiten, es gehört " + ChatColor.GREEN + plugin.horses.getString("Pferd." + horseID.toString() + ".Besitzer") + "!");
				return;
			}
		}
	}
	
	private boolean breedCheck(Player player, Entity entity) {
		List<Entity> entityList = entity.getNearbyEntities(48, 40, 48);
		double n = 0;
		
		for(int i = 0; i < entityList.size(); i++) {
			if(entityList.get(i).getType() == entity.getType()) {
				n++;
			}
		}
		if(n == 0)
			return true;
		
		double chance = (double) ((6 / n) - 0.05) * 100;
		
		Random dice = new Random();
		int rndRoll = dice.nextInt(100 + 1);
		
		if(rndRoll >= chance) {
			if(player.getItemInHand().getAmount() == 1) {
				player.setItemInHand(null);
			} else {
				player.getItemInHand().setAmount(player.getItemInHand().getAmount() - 1);
				//player.sendMessage("Fail! " + chance + " - " + rndRoll + "(n:" + n + ")");
			}
			return false;
		} else {
			return true;
			//player.sendMessage("Grats! " + chance + " - " + rndRoll + "(n:" + n + ")");
		}
	}
	
}