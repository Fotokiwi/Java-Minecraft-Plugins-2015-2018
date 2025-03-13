package org.community.EpicFighters.Class.Assassine;

import org.bukkit.Effect;
import org.bukkit.block.Block;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.community.EpicFighters.EpicFighters;


public class epicFightersAssassineProjectileHitEvent implements Listener {

	private EpicFighters plugin;
	private Arrow arrow;

	public epicFightersAssassineProjectileHitEvent(EpicFighters plugin) {
		this.plugin = plugin;
	}

	@EventHandler(priority = EventPriority.NORMAL)
	public void onProjectileHit(ProjectileHitEvent event) {
		
		if(!(event.getEntity() instanceof Arrow))
			return;
		
		this.arrow = (Arrow) event.getEntity();
		
		if(!(arrow.getShooter() instanceof Player))
			return;
		
		plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
            @Override
            public void run() {
                            handleProjectileHit();
                    }
            }, 1);		
	}
	
	private void handleProjectileHit() {
		
		Arrow arrow = this.arrow;
		
		if(plugin.shotArrows.get(arrow) == null)
			return;
		
		Block block = arrow.getLocation().getBlock();
		String skill = plugin.shotArrows.get(arrow);
		Player player = plugin.shotArrowsOwner.get(arrow);
		
		plugin.eFAssassineSkillTranslation.translateSkill(skill, block, player);
    	plugin.getServer().getWorld(block.getWorld().getName()).playEffect(block.getLocation(), Effect.SMOKE, 5);
    	
    	plugin.shotArrows.remove(arrow);
    	plugin.shotArrowsOwner.remove(arrow);
	}
}