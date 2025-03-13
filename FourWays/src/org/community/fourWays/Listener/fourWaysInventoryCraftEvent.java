package org.community.fourWays.Listener;

import java.util.Set;

import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.community.fourWays.fourWays;
import org.community.fourWays.User.User;
import org.community.newSettlers.Town.Town;


public class fourWaysInventoryCraftEvent implements Listener{

	private fourWays plugin;

	public fourWaysInventoryCraftEvent(fourWays plugin){
		this.plugin = plugin;
	}

	@EventHandler(priority=EventPriority.HIGHEST)
	public void onInventoryCraft(InventoryClickEvent event) {

		if (event.isCancelled())
			return;

		Inventory inv = event.getInventory();

		if((inv.getType()==InventoryType.CHEST) || inv.getType()==InventoryType.ENDER_CHEST || inv.getType()==InventoryType.PLAYER || inv.getType()==InventoryType.BEACON || inv.getType()==InventoryType.CREATIVE || inv.getType()==InventoryType.ENCHANTING || inv.getType()==InventoryType.ANVIL || inv.getType()==InventoryType.DISPENSER || inv.getType()==InventoryType.MERCHANT){
			return;
		}
		// restricted area multiplier
		/*if((inv.getType()==InventoryType.CRAFTING)){
			if (!(event.getSlotType() == SlotType.RESULT))
				return;
		}*/

		Player player = (Player) event.getWhoClicked();

		if(plugin.adminMode.get(player) == null){

		} else if(plugin.adminMode.get(player) == false){

		} else {
			return;
		}

		if(inv.getType() == InventoryType.CRAFTING || inv.getType() == InventoryType.WORKBENCH) {
			if(event.getRawSlot() > 0)
				return;

			ItemStack tempItem = inv.getItem(event.getSlot());
			if(tempItem == null)
				return;
			
			String blockID = tempItem.getType().toString();
			String blockSubID = plugin.fWItems.getBlockType(null, tempItem);
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

			ConfigurationSection blockSection = plugin.block.getConfigurationSection("Block." + blockIdentity + ".Herstellung.Beruf");

			//plugin.getServer().broadcastMessage(blockIdentity);

			if(blockSection == null) {
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
				blockLevel = plugin.block.getInt("Block." + blockIdentity + ".Herstellung.Beruf." + blockArray[i], 0);
				if(playerClass.contains(blockArray[i]) && playerLevel >= blockLevel) {
					if(plugin.craftingCooldown.getString("Spieler." + player.getName() + "." + blockIdentity) == null){
						if(blockIdentity.contains("WRITTEN_BOOK")){
							if(plugin.block.getString("Block." + blockIdentity + ".Herstellung.Gebaeude") != null) {
								Town town = plugin.newSettlersAPI.nSAPI.getChunkTown(player.getLocation().getChunk());
								if(town == null) {									
									return;
								} else {
									if(town.getBuildingStatus(plugin.block.getString("Block." + blockIdentity + ".Herstellung.Gebaeude"))) {
										if(town.isBuildingInDistance(plugin.block.getString("Block." + blockIdentity + ".Herstellung.Gebaeude"), player.getLocation())) {
											plugin.craftingCooldown.set("Spieler." + player.getName()+ "." + blockIdentity, System.currentTimeMillis());
											plugin.fWCraftingCooldown.saveConfig();	
											return;
										} else {
											player.sendMessage("Der Handwerksbetrieb ist zu weit entfernt.");
											//event.getInventory().setResult(new ItemStack(Material.AIR));
											event.setCancelled(true);
											return;
										}
									} else {
										player.sendMessage("Die Stadt verfügt nicht über das passende Gebäude.");
										//event.getInventory().setResult(new ItemStack(Material.AIR));
										event.setCancelled(true);
										return;
									}
								}
							} else {
								plugin.craftingCooldown.set("Spieler." + player.getName()+ "." + blockIdentity, System.currentTimeMillis());
								plugin.fWCraftingCooldown.saveConfig();
								return;
							}								
						} else {
							if(plugin.block.getString("Block." + blockIdentity + ".Herstellung.Gebaeude") != null) {
								Town town = plugin.newSettlersAPI.nSAPI.getChunkTown(player.getLocation().getChunk());
								if(town == null) {
									return;
								} else {
									if(town.getBuildingStatus(plugin.block.getString("Block." + blockIdentity + ".Herstellung.Gebaeude"))) {
										if(town.isBuildingInDistance(plugin.block.getString("Block." + blockIdentity + ".Herstellung.Gebaeude"), player.getLocation())) {
											return;
										} else {
											player.sendMessage("Der Handwerksbetrieb ist zu weit entfernt.");
											//event.getInventory().setResult(new ItemStack(Material.AIR));
											event.setCancelled(true);
											return;
										}
									} else {
										player.sendMessage("Die Stadt verfügt nicht über das passende Gebäude.");
										//event.getInventory().setResult(new ItemStack(Material.AIR));
										event.setCancelled(true);
										return;
									}
								}
							} else {
								return;
							}
						}							
					} else {
						int cooldown = plugin.craftingCooldown.getInt("Config.CD." + blockIdentity, 0);
						if(System.currentTimeMillis() <= (plugin.craftingCooldown.getLong(("Spieler." + player.getName())+ "." + blockIdentity) + (cooldown*1000))){
							if(plugin.block.getString("Block." + blockIdentity + ".Herstellung.Gebaeude") != null) {
								Town town = plugin.newSettlersAPI.nSAPI.getChunkTown(player.getLocation().getChunk());
								if(town == null) {
									return;
								} else {
									if(town.getBuildingStatus(plugin.block.getString("Block." + blockIdentity + ".Herstellung.Gebaeude"))) {
										if(town.isBuildingInDistance(plugin.block.getString("Block." + blockIdentity + ".Herstellung.Gebaeude"), player.getLocation())) {
											long restzeit = (plugin.craftingCooldown.getLong("Spieler." + player.getName()+ "."+ blockIdentity) + (cooldown*1000))-System.currentTimeMillis();
											int restzeith = (int)((restzeit /1000) / 60)/60;
											int restzeitm = (int) (((restzeit-(((restzeith*60)*60)*1000))/1000)/60);
											player.sendMessage("Du kannst dieses Buch erst wieder in " + ChatColor.DARK_RED + restzeitm + ChatColor.WHITE + " Minuten herstellen");
											//event.getInventory().setResult(new ItemStack(Material.AIR));
											event.setCancelled(true);
											return;
										} else {
											player.sendMessage("Der Handwerksbetrieb ist zu weit entfernt.");
											//event.getInventory().setResult(new ItemStack(Material.AIR));
											event.setCancelled(true);
											return;
										}											
									} else {
										player.sendMessage("Die Stadt verfügt nicht über das passende Gebäude.");
										//event.getInventory().setResult(new ItemStack(Material.AIR));
										event.setCancelled(true);
										return;
									}
								}
							} else {
								long restzeit = (plugin.craftingCooldown.getLong("Spieler." + player.getName()+ "."+ blockIdentity) + (cooldown*1000))-System.currentTimeMillis();
								int restzeith = (int)((restzeit /1000) / 60)/60;
								int restzeitm = (int) (((restzeit-(((restzeith*60)*60)*1000))/1000)/60);
								player.sendMessage("Du kannst dieses Buch erst wieder in " + ChatColor.DARK_RED + restzeitm + ChatColor.WHITE + " Minuten herstellen");
								//event.getInventory().setResult(new ItemStack(Material.AIR));
								event.setCancelled(true);
								return;
							}
						} else{
							if(blockIdentity.contains("WRITTEN_BOOK")){
								if(plugin.block.getString("Block." + blockIdentity + ".Herstellung.Gebaeude") != null) {
									Town town = plugin.newSettlersAPI.nSAPI.getChunkTown(player.getLocation().getChunk());
									if(town == null) {
										return;
									} else {
										if(town.getBuildingStatus(plugin.block.getString("Block." + blockIdentity + ".Herstellung.Gebaeude"))) {
											if(town.isBuildingInDistance(plugin.block.getString("Block." + blockIdentity + ".Herstellung.Gebaeude"), player.getLocation())) {
												plugin.craftingCooldown.set("Spieler." + player.getName() + "." + blockIdentity, System.currentTimeMillis());
												plugin.fWCraftingCooldown.saveConfig();
												return;
											} else {
												player.sendMessage("Der Handwerksbetrieb ist zu weit entfernt.");
												//event.getInventory().setResult(new ItemStack(Material.AIR));
												event.setCancelled(true);
												return;
											}
										} else {
											//event.getInventory().setResult(new ItemStack(Material.AIR));
											event.setCancelled(true);
											return;
										}
									}
								} else {
									plugin.craftingCooldown.set("Spieler." + player.getName() + "." + blockIdentity, System.currentTimeMillis());
									plugin.fWCraftingCooldown.saveConfig();
									return;
								}
							} else {
								if(plugin.block.getString("Block." + blockIdentity + ".Herstellung.Gebaeude") != null) {
									Town town = plugin.newSettlersAPI.nSAPI.getChunkTown(player.getLocation().getChunk());
									if(town == null) {
										return;
									} else {
										if(town.getBuildingStatus(plugin.block.getString("Block." + blockIdentity + ".Herstellung.Gebaeude"))) {
											if(town.isBuildingInDistance(plugin.block.getString("Block." + blockIdentity + ".Herstellung.Gebaeude"), player.getLocation())) {
												return;
											} else {
												player.sendMessage("Der Handwerksbetrieb ist zu weit entfernt.");
												//event.getInventory().setResult(new ItemStack(Material.AIR));
												event.setCancelled(true);
												return;
											}
										} else {
											player.sendMessage("Die Stadt verfügt nicht über das passende Gebäude.");
											//event.getInventory().setResult(new ItemStack(Material.AIR));
											event.setCancelled(true);
											return;
										}
									}
								} else {
									return;
								}
							}

						}
					}
				}
			}	
			event.setCancelled(true);
			player.sendMessage("Für die Herstellung dieses Gegenstandes fehlt dir der nötige Beruf.");
			return;			
		}	

		if(inv.getType() == InventoryType.FURNACE) {
			if(event.getRawSlot() == 0) {
				String blockID = event.getCursor().getType().name();
				String blockSubID = plugin.fWItems.getBlockType(null, event.getCursor());
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

				ConfigurationSection blockSection = plugin.block.getConfigurationSection("Block." + blockIdentity + ".Brennen.Beruf");

				//plugin.getServer().broadcastMessage(blockIdentity);

				if(blockSection == null) {
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
					blockLevel = plugin.block.getInt("Block." + blockIdentity + ".Brennen.Beruf." + blockArray[i], 0);
					if(playerClass.contains(blockArray[i]) && playerLevel >= blockLevel) {
						if(plugin.craftingCooldown.getString("Spieler." + player.getName() + "." + blockIdentity) == null){
							if(blockIdentity.contains("WRITTEN_BOOK")){
								if(plugin.block.getString("Block." + blockIdentity + ".Brennen.Gebaeude") != null) {
									Town town = plugin.newSettlersAPI.nSAPI.getChunkTown(player.getLocation().getChunk());
									if(town == null) {									
										return;
									} else {
										if(town.getBuildingStatus(plugin.block.getString("Block." + blockIdentity + ".Brennen.Gebaeude"))) {
											if(town.isBuildingInDistance(plugin.block.getString("Block." + blockIdentity + ".Brennen.Gebaeude"), player.getLocation())) {
												plugin.craftingCooldown.set("Spieler." + player.getName()+ "." + blockIdentity, System.currentTimeMillis());
												plugin.fWCraftingCooldown.saveConfig();	
												return;
											} else {
												player.sendMessage("Der Handwerksbetrieb ist zu weit entfernt.");
												//event.getInventory().setResult(new ItemStack(Material.AIR));
												event.setCancelled(true);
												return;
											}
										} else {
											player.sendMessage("Die Stadt verfügt nicht über das passende Gebäude.");
											//event.getInventory().setResult(new ItemStack(Material.AIR));
											event.setCancelled(true);
											return;
										}
									}
								} else {
									plugin.craftingCooldown.set("Spieler." + player.getName()+ "." + blockIdentity, System.currentTimeMillis());
									plugin.fWCraftingCooldown.saveConfig();
									return;
								}								
							} else {
								if(plugin.block.getString("Block." + blockIdentity + ".Brennen.Gebaeude") != null) {
									Town town = plugin.newSettlersAPI.nSAPI.getChunkTown(player.getLocation().getChunk());
									if(town == null) {
										return;
									} else {
										if(town.getBuildingStatus(plugin.block.getString("Block." + blockIdentity + ".Brennen.Gebaeude"))) {
											if(town.isBuildingInDistance(plugin.block.getString("Block." + blockIdentity + ".Brennen.Gebaeude"), player.getLocation())) {
												return;
											} else {
												player.sendMessage("Der Handwerksbetrieb ist zu weit entfernt.");
												//event.getInventory().setResult(new ItemStack(Material.AIR));
												event.setCancelled(true);
												return;
											}
										} else {
											player.sendMessage("Die Stadt verfügt nicht über das passende Gebäude.");
											//event.getInventory().setResult(new ItemStack(Material.AIR));
											event.setCancelled(true);
											return;
										}
									}
								} else {
									return;
								}
							}							
						} else {
							int cooldown = plugin.craftingCooldown.getInt("Config.CD." + blockIdentity, 0);
							if(System.currentTimeMillis() <= (plugin.craftingCooldown.getLong(("Spieler." + player.getName())+ "." + blockIdentity) + (cooldown*1000))){
								if(plugin.block.getString("Block." + blockIdentity + ".Brennen.Gebaeude") != null) {
									Town town = plugin.newSettlersAPI.nSAPI.getChunkTown(player.getLocation().getChunk());
									if(town == null) {
										return;
									} else {
										if(town.getBuildingStatus(plugin.block.getString("Block." + blockIdentity + ".Brennen.Gebaeude"))) {
											if(town.isBuildingInDistance(plugin.block.getString("Block." + blockIdentity + ".Brennen.Gebaeude"), player.getLocation())) {
												long restzeit = (plugin.craftingCooldown.getLong("Spieler." + player.getName()+ "."+ blockIdentity) + (cooldown*1000))-System.currentTimeMillis();
												int restzeith = (int)((restzeit /1000) / 60)/60;
												int restzeitm = (int) (((restzeit-(((restzeith*60)*60)*1000))/1000)/60);
												player.sendMessage("Du kannst dieses Buch erst wieder in " + ChatColor.DARK_RED + restzeitm + ChatColor.WHITE + " Minuten herstellen");
												//event.getInventory().setResult(new ItemStack(Material.AIR));
												event.setCancelled(true);
												return;
											} else {
												player.sendMessage("Der Handwerksbetrieb ist zu weit entfernt.");
												//event.getInventory().setResult(new ItemStack(Material.AIR));
												event.setCancelled(true);
												return;
											}											
										} else {
											player.sendMessage("Die Stadt verfügt nicht über das passende Gebäude.");
											//event.getInventory().setResult(new ItemStack(Material.AIR));
											event.setCancelled(true);
											return;
										}
									}
								} else {
									long restzeit = (plugin.craftingCooldown.getLong("Spieler." + player.getName()+ "."+ blockIdentity) + (cooldown*1000))-System.currentTimeMillis();
									int restzeith = (int)((restzeit /1000) / 60)/60;
									int restzeitm = (int) (((restzeit-(((restzeith*60)*60)*1000))/1000)/60);
									player.sendMessage("Du kannst dieses Buch erst wieder in " + ChatColor.DARK_RED + restzeitm + ChatColor.WHITE + " Minuten herstellen");
									//event.getInventory().setResult(new ItemStack(Material.AIR));
									event.setCancelled(true);
									return;
								}
							} else{
								if(blockIdentity.contains("WRITTEN_BOOK")){
									if(plugin.block.getString("Block." + blockIdentity + ".Brennen.Gebaeude") != null) {
										Town town = plugin.newSettlersAPI.nSAPI.getChunkTown(player.getLocation().getChunk());
										if(town == null) {
											return;
										} else {
											if(town.getBuildingStatus(plugin.block.getString("Block." + blockIdentity + ".Brennen.Gebaeude"))) {
												if(town.isBuildingInDistance(plugin.block.getString("Block." + blockIdentity + ".Brennen.Gebaeude"), player.getLocation())) {
													plugin.craftingCooldown.set("Spieler." + player.getName() + "." + blockIdentity, System.currentTimeMillis());
													plugin.fWCraftingCooldown.saveConfig();
													return;
												} else {
													player.sendMessage("Der Handwerksbetrieb ist zu weit entfernt.");
													//event.getInventory().setResult(new ItemStack(Material.AIR));
													event.setCancelled(true);
													return;
												}
											} else {
												//event.getInventory().setResult(new ItemStack(Material.AIR));
												event.setCancelled(true);
												return;
											}
										}
									} else {
										plugin.craftingCooldown.set("Spieler." + player.getName() + "." + blockIdentity, System.currentTimeMillis());
										plugin.fWCraftingCooldown.saveConfig();
										return;
									}
								} else {
									if(plugin.block.getString("Block." + blockIdentity + ".Brennen.Gebaeude") != null) {
										Town town = plugin.newSettlersAPI.nSAPI.getChunkTown(player.getLocation().getChunk());
										if(town == null) {
											return;
										} else {
											if(town.getBuildingStatus(plugin.block.getString("Block." + blockIdentity + ".Brennen.Gebaeude"))) {
												if(town.isBuildingInDistance(plugin.block.getString("Block." + blockIdentity + ".Brennen.Gebaeude"), player.getLocation())) {
													return;
												} else {
													player.sendMessage("Der Handwerksbetrieb ist zu weit entfernt.");
													//event.getInventory().setResult(new ItemStack(Material.AIR));
													event.setCancelled(true);
													return;
												}
											} else {
												player.sendMessage("Die Stadt verfügt nicht über das passende Gebäude.");
												//event.getInventory().setResult(new ItemStack(Material.AIR));
												event.setCancelled(true);
												return;
											}
										}
									} else {
										return;
									}
								}

							}
						}
					}
				}	
				event.setCancelled(true);
				player.sendMessage("Für die Herstellung dieses Gegenstandes fehlt dir der nötige Beruf.");
				return;	
			}

			if(event.getRawSlot() == 2) {
				String blockID = inv.getItem(event.getSlot()).getType().toString();
				String blockSubID = plugin.fWItems.getBlockType(null, inv.getItem(event.getSlot()));
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

				ConfigurationSection blockSection = plugin.block.getConfigurationSection("Block." + blockIdentity + ".Brennen.Beruf");

				//plugin.getServer().broadcastMessage(blockIdentity);

				if(blockSection == null) {
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
					blockLevel = plugin.block.getInt("Block." + blockIdentity + ".Brennen.Beruf." + blockArray[i], 0);
					if(playerClass.contains(blockArray[i]) && playerLevel >= blockLevel) {
						if(plugin.craftingCooldown.getString("Spieler." + player.getName() + "." + blockIdentity) == null){
							if(blockIdentity.contains("WRITTEN_BOOK")){
								if(plugin.block.getString("Block." + blockIdentity + ".Brennen.Gebaeude") != null) {
									Town town = plugin.newSettlersAPI.nSAPI.getChunkTown(player.getLocation().getChunk());
									if(town == null) {									
										return;
									} else {
										if(town.getBuildingStatus(plugin.block.getString("Block." + blockIdentity + ".Brennen.Gebaeude"))) {
											if(town.isBuildingInDistance(plugin.block.getString("Block." + blockIdentity + ".Brennen.Gebaeude"), player.getLocation())) {
												plugin.craftingCooldown.set("Spieler." + player.getName()+ "." + blockIdentity, System.currentTimeMillis());
												plugin.fWCraftingCooldown.saveConfig();	
												return;
											} else {
												player.sendMessage("Der Handwerksbetrieb ist zu weit entfernt.");
												//event.getInventory().setResult(new ItemStack(Material.AIR));
												event.setCancelled(true);
												return;
											}
										} else {
											player.sendMessage("Die Stadt verfügt nicht über das passende Gebäude.");
											//event.getInventory().setResult(new ItemStack(Material.AIR));
											event.setCancelled(true);
											return;
										}
									}
								} else {
									plugin.craftingCooldown.set("Spieler." + player.getName()+ "." + blockIdentity, System.currentTimeMillis());
									plugin.fWCraftingCooldown.saveConfig();
									return;
								}								
							} else {
								if(plugin.block.getString("Block." + blockIdentity + ".Brennen.Gebaeude") != null) {
									Town town = plugin.newSettlersAPI.nSAPI.getChunkTown(player.getLocation().getChunk());
									if(town == null) {
										return;
									} else {
										if(town.getBuildingStatus(plugin.block.getString("Block." + blockIdentity + ".Brennen.Gebaeude"))) {
											if(town.isBuildingInDistance(plugin.block.getString("Block." + blockIdentity + ".Brennen.Gebaeude"), player.getLocation())) {
												return;
											} else {
												player.sendMessage("Der Handwerksbetrieb ist zu weit entfernt.");
												//event.getInventory().setResult(new ItemStack(Material.AIR));
												event.setCancelled(true);
												return;
											}
										} else {
											player.sendMessage("Die Stadt verfügt nicht über das passende Gebäude.");
											//event.getInventory().setResult(new ItemStack(Material.AIR));
											event.setCancelled(true);
											return;
										}
									}
								} else {
									return;
								}
							}							
						} else {
							int cooldown = plugin.craftingCooldown.getInt("Config.CD." + blockIdentity, 0);
							if(System.currentTimeMillis() <= (plugin.craftingCooldown.getLong(("Spieler." + player.getName())+ "." + blockIdentity) + (cooldown*1000))){
								if(plugin.block.getString("Block." + blockIdentity + ".Brennen.Gebaeude") != null) {
									Town town = plugin.newSettlersAPI.nSAPI.getChunkTown(player.getLocation().getChunk());
									if(town == null) {
										return;
									} else {
										if(town.getBuildingStatus(plugin.block.getString("Block." + blockIdentity + ".Brennen.Gebaeude"))) {
											if(town.isBuildingInDistance(plugin.block.getString("Block." + blockIdentity + ".Brennen.Gebaeude"), player.getLocation())) {
												long restzeit = (plugin.craftingCooldown.getLong("Spieler." + player.getName()+ "."+ blockIdentity) + (cooldown*1000))-System.currentTimeMillis();
												int restzeith = (int)((restzeit /1000) / 60)/60;
												int restzeitm = (int) (((restzeit-(((restzeith*60)*60)*1000))/1000)/60);
												player.sendMessage("Du kannst dieses Buch erst wieder in " + ChatColor.DARK_RED + restzeitm + ChatColor.WHITE + " Minuten herstellen");
												//event.getInventory().setResult(new ItemStack(Material.AIR));
												event.setCancelled(true);
												return;
											} else {
												player.sendMessage("Der Handwerksbetrieb ist zu weit entfernt.");
												//event.getInventory().setResult(new ItemStack(Material.AIR));
												event.setCancelled(true);
												return;
											}											
										} else {
											player.sendMessage("Die Stadt verfügt nicht über das passende Gebäude.");
											//event.getInventory().setResult(new ItemStack(Material.AIR));
											event.setCancelled(true);
											return;
										}
									}
								} else {
									long restzeit = (plugin.craftingCooldown.getLong("Spieler." + player.getName()+ "."+ blockIdentity) + (cooldown*1000))-System.currentTimeMillis();
									int restzeith = (int)((restzeit /1000) / 60)/60;
									int restzeitm = (int) (((restzeit-(((restzeith*60)*60)*1000))/1000)/60);
									player.sendMessage("Du kannst dieses Buch erst wieder in " + ChatColor.DARK_RED + restzeitm + ChatColor.WHITE + " Minuten herstellen");
									//event.getInventory().setResult(new ItemStack(Material.AIR));
									event.setCancelled(true);
									return;
								}
							} else{
								if(blockIdentity.contains("WRITTEN_BOOK")){
									if(plugin.block.getString("Block." + blockIdentity + ".Brennen.Gebaeude") != null) {
										Town town = plugin.newSettlersAPI.nSAPI.getChunkTown(player.getLocation().getChunk());
										if(town == null) {
											return;
										} else {
											if(town.getBuildingStatus(plugin.block.getString("Block." + blockIdentity + ".Brennen.Gebaeude"))) {
												if(town.isBuildingInDistance(plugin.block.getString("Block." + blockIdentity + ".Brennen.Gebaeude"), player.getLocation())) {
													plugin.craftingCooldown.set("Spieler." + player.getName() + "." + blockIdentity, System.currentTimeMillis());
													plugin.fWCraftingCooldown.saveConfig();
													return;
												} else {
													player.sendMessage("Der Handwerksbetrieb ist zu weit entfernt.");
													//event.getInventory().setResult(new ItemStack(Material.AIR));
													event.setCancelled(true);
													return;
												}
											} else {
												//event.getInventory().setResult(new ItemStack(Material.AIR));
												event.setCancelled(true);
												return;
											}
										}
									} else {
										plugin.craftingCooldown.set("Spieler." + player.getName() + "." + blockIdentity, System.currentTimeMillis());
										plugin.fWCraftingCooldown.saveConfig();
										return;
									}
								} else {
									if(plugin.block.getString("Block." + blockIdentity + ".Brennen.Gebaeude") != null) {
										Town town = plugin.newSettlersAPI.nSAPI.getChunkTown(player.getLocation().getChunk());
										if(town == null) {
											return;
										} else {
											if(town.getBuildingStatus(plugin.block.getString("Block." + blockIdentity + ".Brennen.Gebaeude"))) {
												if(town.isBuildingInDistance(plugin.block.getString("Block." + blockIdentity + ".Brennen.Gebaeude"), player.getLocation())) {
													return;
												} else {
													player.sendMessage("Der Handwerksbetrieb ist zu weit entfernt.");
													//event.getInventory().setResult(new ItemStack(Material.AIR));
													event.setCancelled(true);
													return;
												}
											} else {
												player.sendMessage("Die Stadt verfügt nicht über das passende Gebäude.");
												//event.getInventory().setResult(new ItemStack(Material.AIR));
												event.setCancelled(true);
												return;
											}
										}
									} else {
										return;
									}
								}

							}
						}
					}
				}	
				event.setCancelled(true);
				player.sendMessage("Für die Entnahme dieses Gegenstandes fehlt dir der nötige Beruf.");
				return;	
			}

		}

		/*
		List<HumanEntity> viewers = event.getViewers();
		if (viewers.size() == 0)
			return;
		Player player = null;
		for (HumanEntity viewer : event.getViewers()) {
			if (viewer instanceof Player) {
				player = (Player) viewer;
				break;
			}
		}

		if (player == null)
			return;	

		InventoryClickResult result = new InventoryClickResult(event);
		ItemStack itemsPut = result.getItemsPut();
		ItemStack itemsTook = result.getItemsTook();


		if(itemsTook==null && itemsPut==null)
			return;

		if(itemsPut!=null){
			//plugin.getServer().broadcastMessage(event.getRawSlot() + "");
			if(inv.getType()==InventoryType.BREWING || inv.getType()==InventoryType.FURNACE){
				String blockID = itemsPut.getType().toString();
				String blockSubID = plugin.fWItems.getBlockType(null, itemsPut);
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

				if(inv.getType()==InventoryType.BREWING){

					BrewerInventory inventory = (BrewerInventory) event.getInventory();

					ConfigurationSection blockSection = plugin.block.getConfigurationSection("Block." + blockIdentity + ".Brauen.Beruf");

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
						blockLevel = plugin.block.getInt("Block." + blockIdentity + ".Brauen.Beruf." + blockArray[i], 0);
						if(playerClass.contains(blockArray[i]) && playerLevel >= blockLevel) {
							if(plugin.block.getString("Block." + blockIdentity + ".Brauen.Gebaeude") != null) {
								Town town = plugin.newSettlersAPI.nSAPI.getChunkTown(player.getLocation().getChunk());
								if(town == null) {
									event.setCancelled(true);
									return;
								} else {
									if(town.getBuildingStatus(plugin.block.getString("Block." + blockIdentity + ".Brauen.Gebaeude"))) {
										if(town.isBuildingInDistance(plugin.block.getString("Block." + blockIdentity + ".Brauen.Gebaeude"), player.getLocation())) {
											return;
										} else {
											player.sendMessage("Der Handwerksbetrieb ist zu weit entfernt.");
											event.setCancelled(true);
											return;
										}
									} else {
										player.sendMessage("Die Stadt verfügt nicht über das passende Gebäude.");
										event.setCancelled(true);
										return;
									}
								}
							} else {
								return;
							}
						}
					}
					event.setCancelled(true);
					return;
				}
				if(inv.getType()==InventoryType.FURNACE){

					FurnaceInventory inventory = (FurnaceInventory) event.getInventory();

					ConfigurationSection blockSection = plugin.block.getConfigurationSection("Block." + blockIdentity + ".Brennen.Beruf");

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
						blockLevel = plugin.block.getInt("Block." + blockIdentity + ".Brennen.Beruf." + blockArray[i], 0);
						if(playerClass.contains(blockArray[i]) && playerLevel >= blockLevel) {
							if(plugin.block.getString("Block." + blockIdentity + ".Brennen.Gebaeude") != null) {
								Town town = plugin.newSettlersAPI.nSAPI.getChunkTown(player.getLocation().getChunk());
								if(town == null) {
									plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new cleanFurnaceInventory(inventory), 20L);
									event.setCancelled(true);
									return;
								} else {
									if(town.getBuildingStatus(plugin.block.getString("Block." + blockIdentity + ".Brennen.Gebaeude"))) {
										if(town.isBuildingInDistance(plugin.block.getString("Block." + blockIdentity + ".Brennen.Gebaeude"), player.getLocation())) {
											return;
										} else {
											player.sendMessage("Der Handwerksbetrieb ist zu weit entfernt.");
											plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new cleanFurnaceInventory(inventory), 20L);
											event.setCancelled(true);
											return;
										}										
									} else {
										player.sendMessage("Die Stadt verfügt nicht über das passende Gebäude.");
										plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new cleanFurnaceInventory(inventory), 20L);
										event.setCancelled(true);
										return;
									}
								}
							} else {
								return;
							}
						}
					}
					plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new cleanFurnaceInventory(inventory), 10L);
					event.setCancelled(true);
					return;
				}
			}
		}*/
	}
	/*private class cleanFurnaceInventory implements Runnable{

		private FurnaceInventory inventory = null;

		public cleanFurnaceInventory(FurnaceInventory inventory) {
			this.inventory = inventory;
		}

		public void run() {
			this.inventory.setSmelting(null);
		}
	}*/
}
