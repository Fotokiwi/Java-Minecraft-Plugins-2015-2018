package org.community.ancientRelics.Listener;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.community.ancientRelics.ancientRelics;
import org.community.ancientRelics.Graves.PlayerDeath;

public class ancientRelicsPlayerDeathEvent implements Listener {
	
	private final ancientRelics plugin;

	public ancientRelicsPlayerDeathEvent(ancientRelics plugin)
	{
		this.plugin = plugin;
	}

	@EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
	public void onPlayerDeath(PlayerDeathEvent event) {

		Entity attacker = (Entity) event.getEntity().getKiller();
		Entity defender = (Entity) event.getEntity();
		
		if((attacker instanceof Player) && (defender instanceof Player)) {
			
			Player winner = (Player) attacker;
			Player looser = (Player) defender;
			
			List<ItemStack> dropList = event.getDrops();
			
			plugin.aRPvP.onDeathInventorySave(winner, looser, dropList);
			
			// Kein Exp-Drop im PvP
			
			event.setDroppedExp(0);
			event.setKeepLevel(true);
			
			event.setDeathMessage(looser.getName() + " wurde von " + winner.getName() + " getötet.");
			
			return;
			
		} else if(defender instanceof Player) {
			if(plugin.duellModus.get(defender) != null) {
				
				Player looser = (Player) defender;
				
				List<ItemStack> dropList = event.getDrops();
				
				plugin.aRPvP.onDeathInventorySave(looser, dropList);
				
				// Kein Exp-Drop im PvP
				
				event.setDroppedExp(0);
				event.setKeepLevel(true);				

				plugin.duellModus.put(plugin.duellModus.get(looser), null);
				plugin.duellModus.put(looser, null);
				
				return;
				
			}
			if(!plugin.lastDamagerPlayer.containsKey((Player) defender))
				return;
			if(System.currentTimeMillis() <= (plugin.lastDamagerTimestamp.get((Player) defender) + (plugin.config.getLong("Config.PvP.PvPTodToleranzInSekunden", 10) * 1000))) {
				
				Player winner = plugin.lastDamagerPlayer.get(defender);
				Player looser = (Player) defender;
				
				List<ItemStack> dropList = event.getDrops();
				
				plugin.aRPvP.onDeathInventorySave(winner, looser, dropList);
				
				// Kein Exp-Drop im PvP
				
				event.setDroppedExp(0);
				event.setKeepLevel(true);
				
				event.setDeathMessage(looser.getName() + " wurde von " + winner.getName() + " getötet.");
				
				return;
				
			}
			
		}
		
		plugin.graves.addDeath(defender.getUniqueId(), new PlayerDeath(event.getDrops(), System.currentTimeMillis(), findEmptyBlock(defender.getLocation()), ((Player)defender).getName()));
		event.getDrops().clear();
		//Player looser = (Player) defender;
		
		//plugin.economy.withdrawPlayer(looser.getName(), plugin.config.getDouble("Config.PvP.NonPvPSterbegeld", 50));
		//looser.sendMessage(ChatColor.AQUA + "Als du wieder zu dir kommst, fehlen deinem Geldbeutel " + ChatColor.BLUE + plugin.config.getDouble("Config.PvP.NonPvPSterbegeld", 50) + " Gulden");
		
		return;
		
	}	

	@EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
	public void onPlayerRespawn(PlayerRespawnEvent event) {

		Player player = event.getPlayer();
		
		plugin.aRPvP.getPlayerInventory(player);
		
		return;
		
	}
	
	public Location findEmptyBlock(Location l){
		if(l.getBlock().getType().equals(Material.AIR))
			return l;
		for(int i = -2; i < 3; i++){
			for(int j = -2; j < 3; j++){
				for(int k = -2; k < 3; k++){
					if(l.getWorld().getBlockAt(l.getBlockX() + i, l.getBlockY() + j, l.getBlockZ() + k).getType().equals(Material.AIR))
						return l.getWorld().getBlockAt(l.getBlockX() + i, l.getBlockY() + j, l.getBlockZ() + k).getLocation();
						
				}
			}
		}
		return l;
	}
	
}