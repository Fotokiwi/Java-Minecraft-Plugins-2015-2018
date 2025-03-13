package org.community.EpicFighters.Class.Assassine;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.community.EpicFighters.EpicFighters;

public class epicFightersAssassineDamageSkills {
	
	private EpicFighters plugin;

	public epicFightersAssassineDamageSkills(EpicFighters plugin)
	{
		this.plugin = plugin;
	}
	
	public void piercingArrow(Entity entity) {
		
    	plugin.getServer().getWorld(entity.getWorld().getName()).playEffect(entity.getLocation(), Effect.SMOKE, 5);
		
	}
	
	public void razorArrow(Entity entity) {
		
    	plugin.getServer().getWorld(entity.getWorld().getName()).playEffect(entity.getLocation(), Effect.SMOKE, 5);
		
	}
	
	public void fireArrow(Entity entity, int ticks) {
		entity.setFireTicks(ticks * 20);
		plugin.getServer().getWorld(entity.getWorld().getName()).playEffect(entity.getLocation(), Effect.SMOKE, 5);
	}
	
	public void poisonArrow(Entity entity, int duration, int strength) {
		LivingEntity livingEntity = (LivingEntity) entity;
		livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.POISON, duration * 20, strength));
		plugin.getServer().getWorld(entity.getWorld().getName()).playEffect(entity.getLocation(), Effect.SMOKE, 5);
	}
	
	public void slowArrow(Entity entity, int duration, int strength) {
		LivingEntity livingEntity = (LivingEntity) entity;
		livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, duration * 20, strength));
		plugin.getServer().getWorld(entity.getWorld().getName()).playEffect(entity.getLocation(), Effect.SMOKE, 5);
	}
	
	public void explosiveArrow(Entity entity) {
		Location loc = entity.getLocation();
		loc.setY(loc.getY() + 0.5);
		entity.getWorld().createExplosion(loc, 0.5F);
		plugin.getServer().getWorld(entity.getWorld().getName()).playEffect(entity.getLocation(), Effect.SMOKE, 5);
	}
	
	public void explosiveArrow(Block block) {
		Location loc = block.getLocation();
		loc.setY(loc.getY() + 0.5);
		block.getWorld().createExplosion(loc, 0.5F);
	}
	
	public void stunArrow(Entity entity, int duration) {
		plugin.charmedEntities.put(entity, true);		
		plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new resetCharmedEntities(entity), duration * 20L);
		plugin.getServer().getWorld(entity.getWorld().getName()).playEffect(entity.getLocation(), Effect.SMOKE, 5);
	}
	
	public void netArrow(Entity entity) {

		plugin.getServer().getWorld(entity.getWorld().getName()).playEffect(entity.getLocation(), Effect.SMOKE, 5);
	}

	private class resetCharmedEntities implements Runnable{

		private Entity entity = null;
		
		public resetCharmedEntities(Entity entity) {
			this.entity = entity;
		}

		public void run() {
			plugin.charmedEntities.remove(entity);
		}

	}
	
}
