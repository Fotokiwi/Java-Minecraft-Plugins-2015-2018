package org.community.fourWays.Listener;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.community.fourWays.fourWays;


public class fourWaysEntityDamageEvent implements Listener {

	private fourWays plugin;

	public fourWaysEntityDamageEvent(fourWays plugin) {
		this.plugin = plugin;
	}

	@EventHandler(priority = EventPriority.LOWEST)
	public void onEntityDamage(EntityDamageEvent event) {		
		if (event.getCause() == DamageCause.PROJECTILE) {
			EntityDamageByEntityEvent e = (EntityDamageByEntityEvent) event;
			Projectile arrow = (Projectile) e.getDamager();
			Player player = null;

			if (arrow.getShooter() instanceof Player) {
				player = (Player) arrow.getShooter();				
			} else {
				return;
			}

			Entity entity = event.getEntity();
			if(plugin.damagerTemp.containsKey(entity.getEntityId())) {
				return;
			} else {
				plugin.damagerTemp.put(entity.getEntityId(), player);
				return;
			}
		} else {
			return;
		}
	}
}