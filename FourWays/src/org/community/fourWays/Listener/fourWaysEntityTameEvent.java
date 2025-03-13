package org.community.fourWays.Listener;

import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTameEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.community.fourWays.fourWays;
import org.community.fourWays.User.User;
import org.community.newSettlers.Town.Town;


public class fourWaysEntityTameEvent implements Listener {

	private fourWays plugin;


	public fourWaysEntityTameEvent(fourWays plugin) {
		this.plugin = plugin;
	}

	@EventHandler(priority = EventPriority.NORMAL)
	public void onEntityTame(EntityTameEvent event) {

		String entity = event.getEntity().getType().name();
		Player player = (Player) event.getOwner();
		User user = plugin.fWUsers.getPlayerInfo(player);
		

		ConfigurationSection entitySection = plugin.entity.getConfigurationSection("Entity." + entity + ".Zaehmen.Beruf");

		//player.sendMessage(entity + "");

		if(entitySection == null) {
			return;
		}					

		Set<String> entityKeys = entitySection.getKeys(false);
		String[] entityArray = entityKeys.toArray(new String[0]);  	  	

		int blockLevel;

		String[] blockInfo = new String[2];
		blockInfo = user.getJobHash().split(",");
		String playerClass = blockInfo[1];
		int playerLevel = new Integer(blockInfo[0]);

		for(int j = 0; j < entityArray.length; j++){
			blockLevel = plugin.entity.getInt("Entity." + entity + ".Zaehmen.Beruf." + entityArray[j], 0);
			if(playerClass.contains(entityArray[j]) && playerLevel >= blockLevel) {
				if(plugin.entity.getString("Entity." + entity + ".Zaehmen.Gebaeude") != null) {
					Town town = plugin.newSettlersAPI.nSAPI.getChunkTown(event.getEntity().getLocation().getChunk());
					if(town == null) {
						//player.sendMessage("Du hast diesen Block nun initialisiert");
						/*player = (Player)event.getOwner();
						player.sendMessage("Herzlichen Glückwunsch zu deinem neuen Tier.");
						return;*/
						player = (Player)event.getOwner();
						player.sendMessage("Du schaffst es nicht das Tier zu zähmen.");
						event.setCancelled(true);
						return;
					} else {
						if(town.getBuildingStatus(plugin.entity.getString("Entity." + entity + ".Zaehmen.Gebaeude"))) {
							player = (Player)event.getOwner();
							player.sendMessage("Herzlichen Glückwunsch zu deinem neuen Tier.");
							user.addExp(plugin.entity.getInt("Entity." + entity + ".Zaehmen.Exp", 0));
							
							ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
							BookMeta meta = (BookMeta) book.getItemMeta();
							meta.setAuthor("Zuchtregister");
							ArrayList<String> lore = new ArrayList<String>();
							lore.add("" + event.getEntity().getUniqueId());
							meta.addPage("Diese Besitzurkunde dient dazu, ein Pferd auf einen neuen Besitzer zu registrieren. Der neue Besitzer muss mit dem Buch in der Hand mit dem Pferd interagieren.");
							meta.setLore(lore);
							meta.setTitle("Besitzurkunde");
							book.setItemMeta(meta);
							
							//Horse horse = (Horse) event.getEntity();
							//horse.setCustomName("Gezähmtes Pferd");
							
							UUID horseID = event.getEntity().getUniqueId();
							plugin.horses.set("Pferd." + horseID.toString() + ".BesitzerID", player.getUniqueId().toString());
							plugin.horses.set("Pferd." + horseID.toString() + ".Besitzer", player.getName());
							plugin.fWHorses.saveConfig();
			
							book = player.getWorld().dropItemNaturally(player.getLocation(), book).getItemStack();
							return;
						} else {
							player = (Player)event.getOwner();
							player.sendMessage("Du schaffst es nicht das Tier zu zähmen.");
							event.setCancelled(true);
							return;
						}
					}
				} else {
					//player.sendMessage("Du hast diesen Block nun initialisiert");
					player = (Player)event.getOwner();
					player.sendMessage("Herzlichen Glückwunsch zu deinem neuen Tier.");
					user.addExp(plugin.entity.getInt("Entity." + entity + ".Zaehmen.Exp", 0));
					
					ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
					BookMeta meta = (BookMeta) book.getItemMeta();
					meta.setAuthor("Zuchtregister");
					ArrayList<String> lore = new ArrayList<String>();
					lore.add("" + event.getEntity().getUniqueId());
					meta.addPage("Diese Besitzurkunde dient dazu, ein Pferd auf einen neuen Besitzer zu registrieren. Der neue Besitzer muss mit dem Buch in der Hand mit dem Pferd interagieren.");
					meta.setLore(lore);
					meta.setTitle("Besitzurkunde");
					book.setItemMeta(meta);
					
					//Horse horse = (Horse) event.getEntity();
					//horse.setCustomName("Gezähmtes Wildpferd");
					
					UUID horseID = event.getEntity().getUniqueId();
					plugin.horses.set("Pferd." + horseID.toString() + ".BesitzerID", player.getUniqueId().toString());
					plugin.horses.set("Pferd." + horseID.toString() + ".Besitzer", player.getName());
					plugin.fWHorses.saveConfig();
					
					book = player.getWorld().dropItemNaturally(player.getLocation(), book).getItemStack();
					return;
				}

			}
		}
		player = (Player)event.getOwner();
		player.sendMessage("Du schaffst es nicht das Tier zu zähmen.");
		event.setCancelled(true);
		return;
	}
}