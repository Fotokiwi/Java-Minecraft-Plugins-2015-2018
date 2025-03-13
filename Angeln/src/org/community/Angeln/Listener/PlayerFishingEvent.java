package org.community.Angeln.Listener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.community.Angeln.Angeln;


public class PlayerFishingEvent implements Listener {	

	private Angeln plugin;

	public PlayerFishingEvent(Angeln plugin) {
		this.plugin = plugin;
	}

	@EventHandler(priority = EventPriority.NORMAL)
	public void onPlayerFish(PlayerFishEvent event) {

		String state = event.getState().name();

		//Angel auswerfen braucht keine Abfrage
		if(state.equalsIgnoreCase("FISHING")){
			return;
		}


		//Fehlversuch braucht keine Abfrage
		if (state.equalsIgnoreCase("FAILED_ATTEMPT")) {
			return;
		}

		Player player = event.getPlayer();

		if(state.equalsIgnoreCase("CAUGHT_FISH")){
			
			//event.setCancelled(true);
			event.getCaught().remove();
			
			//player.sendMessage("Das ist eine Fishing-Meldung");
			List<String> fishList = plugin.biome.getStringList("Biome." + player.getLocation().getBlock().getBiome().name().toLowerCase());
			
			String fishName = "";
			List<String> fishLore = new ArrayList<String>();
			
			boolean achieved = false;
			Random rnd = new Random();
			
			for(int i = fishList.size() - 1; i >= 0; i--) {
				int roll = rnd.nextInt(10000) + 1;
				if(roll <= plugin.fishes.getInt("Fische." + fishList.get(i) + ".Wahrscheinlichkeit")) {
					player.sendMessage(ChatColor.GOLD + "An deiner Angel zappelt: " + ChatColor.AQUA + fishList.get(i));
					player.sendMessage(ChatColor.GRAY + plugin.fishes.getString("Fische." + fishList.get(i) + ".Beschreibung"));
					plugin.angelnAchievements.setCaught(player, fishList.get(i));
					fishName = fishList.get(i);
					achieved = true;
					break;
				}				
			}
			
			if(achieved == false) {
				fishList = plugin.biome.getStringList("Biome.default");

				for(int i = fishList.size() - 1; i >= 0; i--) {
					int roll = rnd.nextInt(10000) + 1;
					if(roll <= plugin.fishes.getInt("Fische." + fishList.get(i) + ".Wahrscheinlichkeit")) {
						player.sendMessage(ChatColor.GOLD + "An deiner Angel zappelt: " + ChatColor.AQUA + fishList.get(i));
						player.sendMessage(ChatColor.GRAY + plugin.fishes.getString("Fische." + fishList.get(i) + ".Beschreibung"));
						plugin.angelnAchievements.setCaught(player, fishList.get(i));
						fishName = fishList.get(i);
						achieved = true;
						break;
					}				
				}
			}
			
			plugin.angelnSpieler.saveConfig();
			
			int playerLevel = getPlayerFishingLevel(player);
			
			if(achieved) {
				if(playerLevel < 20) {
					ItemStack fish = new ItemStack(Material.RAW_FISH);
					fish.setAmount(1);
					fish.setDurability((short) 0);
					ItemMeta meta = fish.getItemMeta();
					meta.setDisplayName(ChatColor.GOLD + fishName);
					fishLore.add("Dies ist ein besonderer Fisch");
					meta.setLore(fishLore);
					fish.setItemMeta(meta);
					player.getWorld().dropItemNaturally(player.getLocation(), fish);
				} else if(playerLevel > 20 && playerLevel <= 40) {
					ItemStack fish = new ItemStack(Material.RAW_FISH);
					fish.setAmount(1);
					int roll = rnd.nextInt(2);
					fish.setDurability((short) roll);
					ItemMeta meta = fish.getItemMeta();
					meta.setDisplayName(ChatColor.GOLD + fishName);
					fishLore.add("Dies ist ein besonderer Fisch");
					meta.setLore(fishLore);
					fish.setItemMeta(meta);
					player.getWorld().dropItemNaturally(player.getLocation(), fish);
				} else if(playerLevel > 40 && playerLevel <= 60) {
					ItemStack fish = new ItemStack(Material.RAW_FISH);
					fish.setAmount(1);
					int roll = rnd.nextInt(3);
					fish.setDurability((short) roll);
					ItemMeta meta = fish.getItemMeta();
					meta.setDisplayName(ChatColor.GOLD + fishName);
					fishLore.add("Dies ist ein besonderer Fisch");
					meta.setLore(fishLore);
					fish.setItemMeta(meta);
					player.getWorld().dropItemNaturally(player.getLocation(), fish);
				} else {
					ItemStack fish = new ItemStack(Material.RAW_FISH);
					fish.setAmount(1);
					int roll = rnd.nextInt(4);
					fish.setDurability((short) roll);
					ItemMeta meta = fish.getItemMeta();
					meta.setDisplayName(ChatColor.GOLD + fishName);
					fishLore.add("Dies ist ein besonderer Fisch");
					meta.setLore(fishLore);
					fish.setItemMeta(meta);
					player.getWorld().dropItemNaturally(player.getLocation(), fish);
				}
			} else {
				if(playerLevel < 20) {
					ItemStack fish = new ItemStack(Material.RAW_FISH);
					fish.setAmount(1);
					fish.setDurability((short) 0);
					player.getWorld().dropItemNaturally(player.getLocation(), fish);
				} else if (playerLevel > 20 && playerLevel <= 40) {
					ItemStack fish = new ItemStack(Material.RAW_FISH);
					fish.setAmount(1);
					int roll = rnd.nextInt(2);
					fish.setDurability((short) roll);
					player.getWorld().dropItemNaturally(player.getLocation(), fish);
				} else if (playerLevel > 40 && playerLevel <= 60) {
					ItemStack fish = new ItemStack(Material.RAW_FISH);
					fish.setAmount(1);
					int roll = rnd.nextInt(3);
					fish.setDurability((short) roll);
					player.getWorld().dropItemNaturally(player.getLocation(), fish);
				} else {
					ItemStack fish = new ItemStack(Material.RAW_FISH);
					fish.setAmount(1);
					int roll = rnd.nextInt(4);
					fish.setDurability((short) roll);
					player.getWorld().dropItemNaturally(player.getLocation(), fish);
				}
			}
			
			
		}
		
	}
	
	private int getPlayerFishingLevel(Player player) {
		
		ConfigurationSection caught = plugin.players.getConfigurationSection("Spieler." + player.getUniqueId().toString() + ".Fische");
		
		if(caught == null)
			return 0;
		
		Set<String> caughtKeys = caught.getKeys(false);
  	  	String[] caughtCount = caughtKeys.toArray(new String[0]);
  	  	
  	  	return caughtCount.length;
		
	}

}