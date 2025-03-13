package org.community.EpicFighters.Listener;

import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.community.EpicFighters.EpicFighters;


public class epicFightersEntityExplodeEvent implements Listener {

	@SuppressWarnings("unused")
	private EpicFighters plugin;


	public epicFightersEntityExplodeEvent(EpicFighters plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void onProjectileImpact(ProjectileHitEvent event) {
		Projectile projectile = event.getEntity();
		projectile.remove();
	}

	@EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
	public void onEntityExplode(EntityExplodeEvent event) {
		Entity ent = event.getEntity();
		if (ent instanceof EnderDragon) {
			return;
		} else if (ent instanceof TNTPrimed) {
			return;
		} else {
			event.blockList().clear();
		}
	}
}