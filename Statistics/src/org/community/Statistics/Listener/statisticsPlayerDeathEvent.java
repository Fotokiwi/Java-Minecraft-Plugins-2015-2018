package org.community.Statistics.Listener;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.community.Statistics.Statistics;
import org.community.Statistics.Player.statisticsPlayer;

public class statisticsPlayerDeathEvent implements Listener{
	
	private Statistics plugin;
	
	public statisticsPlayerDeathEvent(Statistics plugin) {
		
		this.plugin = plugin;
		
	}
	
	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void onPlayerDeath(PlayerDeathEvent event) {
		
		Player player = event.getEntity();
		
		statisticsPlayer sPlayer = plugin.sCore.playerHash.get(player);
		
		EntityDamageEvent damageEvent = player.getLastDamageCause();
		
		if(damageEvent instanceof EntityDamageByEntityEvent) {
			Entity damager = ((EntityDamageByEntityEvent)damageEvent).getDamager();
			
			if(damager.getType() == EntityType.PLAYER) {
				sPlayer.setPlayerDeathCount("PLAYER");
				sPlayer.setPlayerDeathByPlayerCount(((Player) damager).getName());
			}
			
			if(damager.getType() == EntityType.ARROW && ((Arrow) damager).getShooter() instanceof Skeleton) {
				sPlayer.setPlayerDeathCount("SKELETON");			
				return;
			}

			sPlayer.setPlayerDeathCount(damager.getType().name());
			return;
			
		} else {
			sPlayer.setPlayerDeathCount(damageEvent.getCause().toString());
			return;
		}
		
	}

}
