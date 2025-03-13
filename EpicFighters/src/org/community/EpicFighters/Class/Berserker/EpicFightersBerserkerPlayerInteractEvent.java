package org.community.EpicFighters.Class.Berserker;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;
import org.community.EpicFighters.EpicFighters;

public class EpicFightersBerserkerPlayerInteractEvent implements Listener{
	private EpicFighters plugin;

	public EpicFightersBerserkerPlayerInteractEvent(EpicFighters plugin)
	{
		this.plugin = plugin;
	}
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if(player.getItemInHand().getType() != Material.WOOD_SWORD)
        	return;
        
        if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)
        	return;

        
        Player targetPlayer = null;
        Entity targetEntity = null;
        
        String skill = plugin.user.getString("User." + player.getName() + ".ActiveSkill." + (plugin.user.getString("User." + player.getName() + ".ActiveSkill.Active")));
        
        if(skill == null)
        	return;
        
        int skillRange = plugin.skill.getInt("Skill." + skill + ".Option.Range", 0);
        String skillType = plugin.skill.getString("Skill." + skill + ".Type");
        
        targetPlayer = getTargetPlayer(player);
        targetEntity = getTargetEntity(player);
        
        if(targetPlayer != null && targetPlayer.getLocation().distance(player.getLocation()) <= skillRange && skillType.equalsIgnoreCase("Direktzauber")) {
        	
        	if(targetPlayer.isOnline() == false)
        		return;
        	plugin.eFBerserkerCastTranslation.translateSpell(skill, player, targetPlayer);

        	
        } else if(targetEntity != null && targetEntity.getLocation().distance(player.getLocation()) <= skillRange && skillType.equalsIgnoreCase("Direktzauber")) {
        	
        	plugin.eFBerserkerCastTranslation.translateSpell(skill, player, targetEntity);
        	
        } else if(skillType.equalsIgnoreCase("Umgebungszauber")) {
        	
        	plugin.eFBerserkerCastTranslation.translateSpell(skill, player);
        	
        } else {
        	return;
        }
	}
	
	private Player getTargetPlayer(final Player player) {
		return getTarget(player, player.getWorld().getPlayers());
	}
	
	private org.bukkit.entity.Entity getTargetEntity(final org.bukkit.entity.Entity entity) {
		return getTarget(entity, entity.getWorld().getEntities());
	}
			 
	private <T extends org.bukkit.entity.Entity> T getTarget(final org.bukkit.entity.Entity entity, final Iterable<T> entities) {
		if (entity == null)
			return null;
		T target = null;
		final double threshold = 0.7;
		for (final T other : entities) {
			final Vector n = other.getLocation().toVector().subtract(entity.getLocation().toVector());
			if (entity.getLocation().getDirection().normalize().crossProduct(n).lengthSquared() < threshold && n.normalize().dot(entity.getLocation().getDirection().normalize()) >= 0) {
				if (target == null || target.getLocation().distanceSquared(entity.getLocation()) > other.getLocation().distanceSquared(entity.getLocation()))
					target = other;
			}
		}
		return target;
	}

}
