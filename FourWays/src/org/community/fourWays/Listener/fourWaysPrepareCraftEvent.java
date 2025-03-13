package org.community.fourWays.Listener;

import java.util.ArrayList;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.community.fourWays.fourWays;



public class fourWaysPrepareCraftEvent implements Listener{

	private fourWays plugin;

	public fourWaysPrepareCraftEvent(fourWays plugin){
		this.plugin = plugin;
	}




	@SuppressWarnings("unused")
	@EventHandler(priority=EventPriority.HIGHEST)
	public void onPrepareCraft(PrepareItemCraftEvent event) {		
		Inventory inv = event.getInventory();
		ItemStack itemsTook = event.getRecipe().getResult();
		
		ItemStack enchPotion = new ItemStack(Material.POTION,1,(short)30);
		ItemMeta meta = enchPotion.getItemMeta();
		meta.setDisplayName("magische Saeure");
		ArrayList<String>desc=new ArrayList<String>();
		desc.add("Entfernt verzauberungen");
		meta.setLore(desc);
		enchPotion.setItemMeta(meta);
		enchPotion.setDurability((short)30);
		PotionMeta pmeta = (PotionMeta) enchPotion.getItemMeta();
		pmeta.clearCustomEffects();
		pmeta.addCustomEffect(new PotionEffect(PotionEffectType.CONFUSION,400,0),true);	
		enchPotion.setItemMeta(pmeta);
				
		Player player = (Player) event.getView().getPlayer();
		
		if(plugin.adminMode.get(player) == null){
			
		} else if(plugin.adminMode.get(player) == false){
			
		} else {
			return;
		}	
		/*		
		if(inv.getType()==InventoryType.CRAFTING || inv.getType()==InventoryType.WORKBENCH){			
			boolean check = false;
			ItemStack[] checkstack = inv.getContents();
			for(int x=0;x<checkstack.length;x++){
				if(checkstack[x].toString()!=null && checkstack[x].toString().equals(enchPotion.toString()))
					check = true;
			}
			
			
			if(check==true){
				return;
			}
			if(itemsTook!=null){
				String blockID = itemsTook.getType().toString();
				String blockSubID = plugin.fWItems.getBlockType(null, itemsTook);
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
				
				plugin.getServer().broadcastMessage(blockIdentity);
				
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
												event.getInventory().setResult(new ItemStack(Material.AIR));
												return;
											}
										} else {
											player.sendMessage("Die Stadt verfügt nicht über das passende Gebäude.");
											event.getInventory().setResult(new ItemStack(Material.AIR));
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
												event.getInventory().setResult(new ItemStack(Material.AIR));
												return;
											}
										} else {
											player.sendMessage("Die Stadt verfügt nicht über das passende Gebäude.");
											event.getInventory().setResult(new ItemStack(Material.AIR));
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
												event.getInventory().setResult(new ItemStack(Material.AIR));
												return;
											} else {
												player.sendMessage("Der Handwerksbetrieb ist zu weit entfernt.");
												event.getInventory().setResult(new ItemStack(Material.AIR));
												return;
											}											
										} else {
											player.sendMessage("Die Stadt verfügt nicht über das passende Gebäude.");
											event.getInventory().setResult(new ItemStack(Material.AIR));
											return;
										}
									}
								} else {
									long restzeit = (plugin.craftingCooldown.getLong("Spieler." + player.getName()+ "."+ blockIdentity) + (cooldown*1000))-System.currentTimeMillis();
									int restzeith = (int)((restzeit /1000) / 60)/60;
									int restzeitm = (int) (((restzeit-(((restzeith*60)*60)*1000))/1000)/60);
	                                player.sendMessage("Du kannst dieses Buch erst wieder in " + ChatColor.DARK_RED + restzeitm + ChatColor.WHITE + " Minuten herstellen");
									event.getInventory().setResult(new ItemStack(Material.AIR));
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
													event.getInventory().setResult(new ItemStack(Material.AIR));
													return;
												}
											} else {
												event.getInventory().setResult(new ItemStack(Material.AIR));
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
													event.getInventory().setResult(new ItemStack(Material.AIR));
													return;
												}
											} else {
												player.sendMessage("Die Stadt verfügt nicht über das passende Gebäude.");
												event.getInventory().setResult(new ItemStack(Material.AIR));
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
				event.getInventory().setResult(new ItemStack(Material.AIR));
			}
		}*/
	}
}