package org.community.EpicFighters.Class.Mage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Snowball;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;
import org.community.EpicFighters.EpicFighters;

public class epicFightersMageDamageSpells {
	
	private EpicFighters plugin;

	public epicFightersMageDamageSpells(EpicFighters plugin) {
		this.plugin = plugin;
	}
	
	// Richtungszauber
	
	public void magicFireball(Player player, float speed, String shortcut) {
		Location loc = player.getEyeLocation();
        loc.setX(loc.getX());
        loc.setY(loc.getY());
        loc.setZ(loc.getZ());
        Fireball fball = player.getWorld().spawn(loc, Fireball.class);
        fball.setShooter(player);
        fball.setVelocity(player.getLocation().getDirection().normalize().multiply(speed));
        plugin.shotFireballs.put(fball, shortcut);
        plugin.shotFireballsOwner.put(fball, player);
		Bukkit.getServer().getWorld(player.getWorld().getName()).playEffect(player.getLocation(), Effect.MOBSPAWNER_FLAMES, 5);
	}
	
	public void magicIceball(Player player, float speed, String shortcut) {
		Location loc = player.getEyeLocation();
        loc.setX(loc.getX());
        loc.setY(loc.getY());
        loc.setZ(loc.getZ());
        Snowball sball = player.getWorld().spawn(loc, Snowball.class);
        sball.setShooter(player);
        sball.setVelocity(player.getLocation().getDirection().normalize().multiply(speed * 3));
        plugin.shotSnowballs.put(sball, shortcut);
        plugin.shotSnowballsOwner.put(sball, player);
		Bukkit.getServer().getWorld(player.getWorld().getName()).playEffect(player.getLocation(), Effect.MOBSPAWNER_FLAMES, 5);
	}
	
	public void magicArrow(Player player, float speed, String shortcut) {
		Location loc = player.getEyeLocation();
        loc.setX(loc.getX());
        loc.setY(loc.getY());
        loc.setZ(loc.getZ());
        Arrow ePearl = player.getWorld().spawn(loc, Arrow.class);
        ePearl.setShooter(player);
        ePearl.setVelocity(player.getLocation().getDirection().normalize().multiply(speed * 3));
        plugin.shotMagicArrows.put(ePearl, shortcut);
        plugin.shotMagicArrowsOwner.put(ePearl, player);
		Bukkit.getServer().getWorld(player.getWorld().getName()).playEffect(player.getLocation(), Effect.MOBSPAWNER_FLAMES, 5);
	}


	public void magicExplosion(Player player, int radius) {
		List<Entity> entities = player.getNearbyEntities(radius, radius, radius);

		for(Entity ent : entities)
		{
			if(ent instanceof LivingEntity)
			{				
				if(ent instanceof Player && plugin.ancientRelicsAPI.aRApiPvpStatus.getPvpStatus(player, ent, 0)) {

				} else {
					Location loc = ent.getLocation();
					loc.setY(loc.getY() + 0.5);
					ent.getWorld().createExplosion(loc, 0.5F);
				}

			}
		}
	}
	
	// Direktzauber
	
	public void magicFire(Player player, Entity entity, int ticks) {
		if(entity instanceof Player && plugin.ancientRelicsAPI.aRApiPvpStatus.getPvpStatus(player, entity, 0)) {

		} else {
			entity.setFireTicks(ticks * 20);
		}
	}
	
	public void magicPoison(Player player, Entity entity, int duration, int strength) {
		if(entity instanceof Player && plugin.ancientRelicsAPI.aRApiPvpStatus.getPvpStatus(player, entity, 0)) {

		} else {
			LivingEntity livingEntity = (LivingEntity) entity;
			livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.POISON, duration * 20, strength));
		}
		
	}
	
	public void magicSlowness(Player player, Entity entity, int duration, int strength) {
		if(entity instanceof Player && plugin.ancientRelicsAPI.aRApiPvpStatus.getPvpStatus(player, entity, 0)) {

		} else {
			LivingEntity livingEntity = (LivingEntity) entity;
			livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, duration * 20, strength));
		}
		
	}
	
	public void magicWeakness(Player player, Entity entity, int duration, int strength) {
		if(entity instanceof Player && plugin.ancientRelicsAPI.aRApiPvpStatus.getPvpStatus(player, entity, 0)) {

		} else {
			LivingEntity livingEntity = (LivingEntity) entity;
			livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, duration * 20, strength));
		}		
	}
	
	public void magicCharm(Entity entity, int duration) {
		if(entity instanceof Player)
			return;
		
		plugin.charmedEntities.put(entity, true);
		
		plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new resetCharmedEntities(entity), duration * 20L);
	}
	
	public void magicKnockback(Player player, Entity entity, int strength, int force, int yForce, int maxYForce) {
		LivingEntity livingEntity = (LivingEntity) entity;
		Vector p = player.getLocation().toVector();
		Vector e,v;
		//int force = 15;
		//int yForce = 15;
		//int maxYForce = 20;
		e = entity.getLocation().toVector();
        v = e.subtract(p).normalize().multiply(force/10.0*strength);
        if (force != 0) {
                v.setY(v.getY() + (yForce/10.0*strength));
        } else {
                v.setY(yForce/10.0*strength);
        }
        if (v.getY() > (maxYForce/10.0)) {
                v.setY(maxYForce/10.0);
        }
        
        if(entity instanceof Player && plugin.ancientRelicsAPI.aRApiPvpStatus.getPvpStatus(player, entity, 0)) {

		} else {
			livingEntity.setVelocity(v);
		}        

		//livingEntity.setVelocity(player.getLocation().getDirection().normalize().multiply(strength));
	}
	
	public void magicKnockback(Player player, Location location, int radius, int strength, int force, int yForce, int maxYForce) {

		Arrow arrow = (Arrow) player.getWorld().spawnEntity(location.add(0, 1, 0), EntityType.ARROW);
		List<Entity> entities = arrow.getNearbyEntities(radius, 3, radius);
		Vector p = location.toVector();
		Vector e, v;
		for (Entity entity : entities) {
			if (entity instanceof LivingEntity) {
				e = entity.getLocation().toVector();
				v = e.subtract(p).normalize().multiply(force/10.0*strength);
				if (force != 0) {
					v.setY(v.getY() + (yForce/10.0*strength));
				} else {
					v.setY(yForce/10.0*strength);
				}
				if (v.getY() > (maxYForce/10.0)) {
					v.setY(maxYForce/10.0);
				}
				if(entity instanceof Player && plugin.ancientRelicsAPI.aRApiPvpStatus.getPvpStatus(player, entity, 0)) {

				} else if(entity instanceof Player && ((Player) entity == player)) {
					
				} else {
					entity.setVelocity(v);
				}
			}
		}

	}
	
	
	//Bodenzauber

	public void magicLightning(Player player, Location loc, int amount) {
		loc.setY(loc.getY() + 0.5);
		for(int i = 0; i < amount; i++) {
			player.getWorld().strikeLightning(loc);
		}		
	}
	
	public void magicLightningChain(Player player, Location loc, int area) {
		loc.setY(loc.getY() + 0.5);
		player.getWorld().strikeLightning(loc);
		Arrow arrow = (Arrow) plugin.getServer().getWorld(player.getWorld().getName()).spawnEntity(loc, EntityType.ARROW);
		List<Entity> areaCreatures = arrow.getNearbyEntities(area, 3, area);
		for(Entity ent : areaCreatures)
		{
			if(ent instanceof LivingEntity)
		    {
				if(ent instanceof Player && plugin.ancientRelicsAPI.aRApiPvpStatus.getPvpStatus(player, ent, 0)) {

				} else {
					LivingEntity creatureEnt = (LivingEntity)ent;
					creatureEnt.getWorld().strikeLightning(creatureEnt.getLocation());
				}
				
		    }
		}
		arrow.remove();
	}	
	
	
	
	
	public void magicPoison(Block block, int duration, int strength, int area) {
		Location loc = block.getLocation().add(0, 1, 0);
		loc.setY(loc.getY() + 0.5);
		Arrow arrow = (Arrow) plugin.getServer().getWorld(block.getWorld().getName()).spawnEntity(loc, EntityType.ARROW);
		List<Entity> areaCreatures = arrow.getNearbyEntities(area, 3, area);
		for(Entity ent : areaCreatures)
		{
			if(ent instanceof LivingEntity)
		    {
				if(ent instanceof Player) {
					if(plugin.ancientRelicsAPI.aRApiPvpStatus.getPvpStatus((Player) ent, ent, 0)) {
						
					} else {
						LivingEntity livingEntity = (LivingEntity) ent;
						livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.POISON, duration * 20, strength));
					}
				} else {
					LivingEntity livingEntity = (LivingEntity) ent;
					livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.POISON, duration * 20, strength));
				}
				
		    }
		}
		arrow.remove();
	}
	
	public void magicSlowness(Block block, int duration, int strength, int area) {
		Location loc = block.getLocation().add(0, 1, 0);
		loc.setY(loc.getY() + 0.5);
		Arrow arrow = (Arrow) plugin.getServer().getWorld(block.getWorld().getName()).spawnEntity(loc, EntityType.ARROW);
		List<Entity> areaCreatures = arrow.getNearbyEntities(area, 3, area);
		for(Entity ent : areaCreatures)
		{
			if(ent instanceof LivingEntity)
		    {
				if(ent instanceof Player) {
					if(plugin.ancientRelicsAPI.aRApiPvpStatus.getPvpStatus((Player) ent, ent, 0)) {
						
					} else {
						LivingEntity livingEntity = (LivingEntity) ent;
						livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, duration * 20, strength));
					}
				} else {
					LivingEntity livingEntity = (LivingEntity) ent;
					livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, duration * 20, strength));
				}
				
		    }
		}
		arrow.remove();
	}
	
	public void magicWeakness(Block block, int duration, int strength, int area) {
		Location loc = block.getLocation().add(0, 1, 0);
		loc.setY(loc.getY() + 0.5);
		Arrow arrow = (Arrow) plugin.getServer().getWorld(block.getWorld().getName()).spawnEntity(loc, EntityType.ARROW);
		List<Entity> areaCreatures = arrow.getNearbyEntities(area, 3, area);
		for(Entity ent : areaCreatures)
		{
			if(ent instanceof LivingEntity)
		    {
				if(ent instanceof Player) {
					if(plugin.ancientRelicsAPI.aRApiPvpStatus.getPvpStatus((Player) ent, ent, 0)) {
						
					} else {
						LivingEntity livingEntity = (LivingEntity) ent;
						livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, duration * 20, strength));
					}
				} else {
					LivingEntity livingEntity = (LivingEntity) ent;
					livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, duration * 20, strength));
				}
				
		    }
		}
		arrow.remove();
	}
	
	public void magicCharm(Block block, int duration, int strength, int area) {
		Location loc = block.getLocation().add(0, 1, 0);
		loc.setY(loc.getY() + 0.5);
		Arrow arrow = (Arrow) plugin.getServer().getWorld(block.getWorld().getName()).spawnEntity(loc, EntityType.ARROW);
		List<Entity> areaCreatures = arrow.getNearbyEntities(area, 3, area);
		for(Entity ent : areaCreatures)
		{
			if(ent instanceof LivingEntity)
		    {
				if(ent instanceof Player)
					return;
				
				plugin.charmedEntities.put(ent, true);
				
				plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new resetCharmedEntities(ent), duration * 20L);
		    }
		}
		arrow.remove();
	}

    public void sendFireballsFromPlayer(Player player, int amt, float speed) {
        Location loc = player.getEyeLocation();
        final double tau = 2 * Math.PI;
        double arc = tau / amt;
        for (double a = 0; a < tau; a += arc) {
            Vector dir = new Vector(Math.cos(a), 0, Math.sin(a));
            Location spawn = loc.toVector().add(dir.multiply(2)).toLocation(loc.getWorld(), 0.0F, 0.0F);
            Fireball fball = player.getWorld().spawn(spawn, Fireball.class);
            fball.setShooter(player);
            fball.setDirection(dir.multiply(speed));
        }
    }

    public void sendCannonToEntity(Entity entity, Player player) {
    	Location loc = entity.getLocation();
	    loc.setX(loc.getX());
	    loc.setY(loc.getY()+1);
	    loc.setZ(loc.getZ());
	    Fireball fireball = entity.getWorld().spawn(loc, Fireball.class);
        fireball.setShooter(player);
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
	
	public void icePrison(Player player, Entity entity, int duration) {
		Map<String, Material> blockCache = new HashMap<String, Material>();
		Location entityLocation = entity.getLocation();
		Location blockLocation = null;
		
		blockLocation = entityLocation.add(2, 0, 0);
		blockCache.put(blockLocation.getWorld().getName() + "," + blockLocation.getBlockX() + "," + blockLocation.getBlockY() + "," + blockLocation.getBlockZ(), blockLocation.getBlock().getType());
		blockLocation.getBlock().setType(Material.ICE);
		
		blockLocation = entityLocation.add(0, 0, 1);
		blockCache.put(blockLocation.getWorld().getName() + "," + blockLocation.getBlockX() + "," + blockLocation.getBlockY() + "," + blockLocation.getBlockZ(), blockLocation.getBlock().getType());
		blockLocation.getBlock().setType(Material.ICE);
		
		blockLocation = entityLocation.add(0, 0, -2);
		blockCache.put(blockLocation.getWorld().getName() + "," + blockLocation.getBlockX() + "," + blockLocation.getBlockY() + "," + blockLocation.getBlockZ(), blockLocation.getBlock().getType());
		blockLocation.getBlock().setType(Material.ICE);
		
		blockLocation = entityLocation.add(0, 0, 1);
		
		blockLocation = blockLocation.add(0, 1, 0);
		blockCache.put(blockLocation.getWorld().getName() + "," + blockLocation.getBlockX() + "," + blockLocation.getBlockY() + "," + blockLocation.getBlockZ(), blockLocation.getBlock().getType());
		blockLocation.getBlock().setType(Material.ICE);
		
		blockLocation = entityLocation.add(0, 0, 1);
		blockCache.put(blockLocation.getWorld().getName() + "," + blockLocation.getBlockX() + "," + blockLocation.getBlockY() + "," + blockLocation.getBlockZ(), blockLocation.getBlock().getType());
		blockLocation.getBlock().setType(Material.ICE);
		
		blockLocation = entityLocation.add(0, 0, -2);
		blockCache.put(blockLocation.getWorld().getName() + "," + blockLocation.getBlockX() + "," + blockLocation.getBlockY() + "," + blockLocation.getBlockZ(), blockLocation.getBlock().getType());
		blockLocation.getBlock().setType(Material.ICE);
		
		blockLocation = entityLocation.add(0, 0, 1);
		
		blockLocation = blockLocation.add(-4, 0, 0);
		blockCache.put(blockLocation.getWorld().getName() + "," + blockLocation.getBlockX() + "," + blockLocation.getBlockY() + "," + blockLocation.getBlockZ(), blockLocation.getBlock().getType());
		blockLocation.getBlock().setType(Material.ICE);
		
		blockLocation = entityLocation.add(0, 0, 1);
		blockCache.put(blockLocation.getWorld().getName() + "," + blockLocation.getBlockX() + "," + blockLocation.getBlockY() + "," + blockLocation.getBlockZ(), blockLocation.getBlock().getType());
		blockLocation.getBlock().setType(Material.ICE);
		
		blockLocation = entityLocation.add(0, 0, -2);
		blockCache.put(blockLocation.getWorld().getName() + "," + blockLocation.getBlockX() + "," + blockLocation.getBlockY() + "," + blockLocation.getBlockZ(), blockLocation.getBlock().getType());
		blockLocation.getBlock().setType(Material.ICE);
		
		blockLocation = entityLocation.add(0, 0, 1);
		
		blockLocation = blockLocation.add(0, -1, 0);
		blockCache.put(blockLocation.getWorld().getName() + "," + blockLocation.getBlockX() + "," + blockLocation.getBlockY() + "," + blockLocation.getBlockZ(), blockLocation.getBlock().getType());
		blockLocation.getBlock().setType(Material.ICE);
		
		blockLocation = entityLocation.add(0, 0, 1);
		blockCache.put(blockLocation.getWorld().getName() + "," + blockLocation.getBlockX() + "," + blockLocation.getBlockY() + "," + blockLocation.getBlockZ(), blockLocation.getBlock().getType());
		blockLocation.getBlock().setType(Material.ICE);
		
		blockLocation = entityLocation.add(0, 0, -2);
		blockCache.put(blockLocation.getWorld().getName() + "," + blockLocation.getBlockX() + "," + blockLocation.getBlockY() + "," + blockLocation.getBlockZ(), blockLocation.getBlock().getType());
		blockLocation.getBlock().setType(Material.ICE);
		
		blockLocation = entityLocation.add(0, 0, 1);
		
		blockLocation = blockLocation.add(2, 0, 2);
		blockCache.put(blockLocation.getWorld().getName() + "," + blockLocation.getBlockX() + "," + blockLocation.getBlockY() + "," + blockLocation.getBlockZ(), blockLocation.getBlock().getType());
		blockLocation.getBlock().setType(Material.ICE);
		
		blockLocation = entityLocation.add(1, 0, 0);
		blockCache.put(blockLocation.getWorld().getName() + "," + blockLocation.getBlockX() + "," + blockLocation.getBlockY() + "," + blockLocation.getBlockZ(), blockLocation.getBlock().getType());
		blockLocation.getBlock().setType(Material.ICE);
		
		blockLocation = entityLocation.add(-2, 0, 0);
		blockCache.put(blockLocation.getWorld().getName() + "," + blockLocation.getBlockX() + "," + blockLocation.getBlockY() + "," + blockLocation.getBlockZ(), blockLocation.getBlock().getType());
		blockLocation.getBlock().setType(Material.ICE);
		
		blockLocation = entityLocation.add(1, 0, 0);
		
		blockLocation = blockLocation.add(0, 1, 0);
		blockCache.put(blockLocation.getWorld().getName() + "," + blockLocation.getBlockX() + "," + blockLocation.getBlockY() + "," + blockLocation.getBlockZ(), blockLocation.getBlock().getType());
		blockLocation.getBlock().setType(Material.ICE);
		
		blockLocation = entityLocation.add(1, 0, 0);
		blockCache.put(blockLocation.getWorld().getName() + "," + blockLocation.getBlockX() + "," + blockLocation.getBlockY() + "," + blockLocation.getBlockZ(), blockLocation.getBlock().getType());
		blockLocation.getBlock().setType(Material.ICE);
		
		blockLocation = entityLocation.add(-2, 0, 0);
		blockCache.put(blockLocation.getWorld().getName() + "," + blockLocation.getBlockX() + "," + blockLocation.getBlockY() + "," + blockLocation.getBlockZ(), blockLocation.getBlock().getType());
		blockLocation.getBlock().setType(Material.ICE);
		
		blockLocation = entityLocation.add(1, 0, 0);
		
		blockLocation = blockLocation.add(0, 0, -4);
		blockCache.put(blockLocation.getWorld().getName() + "," + blockLocation.getBlockX() + "," + blockLocation.getBlockY() + "," + blockLocation.getBlockZ(), blockLocation.getBlock().getType());
		blockLocation.getBlock().setType(Material.ICE);
		
		blockLocation = entityLocation.add(1, 0, 0);
		blockCache.put(blockLocation.getWorld().getName() + "," + blockLocation.getBlockX() + "," + blockLocation.getBlockY() + "," + blockLocation.getBlockZ(), blockLocation.getBlock().getType());
		blockLocation.getBlock().setType(Material.ICE);
		
		blockLocation = entityLocation.add(-2, 0, 0);
		blockCache.put(blockLocation.getWorld().getName() + "," + blockLocation.getBlockX() + "," + blockLocation.getBlockY() + "," + blockLocation.getBlockZ(), blockLocation.getBlock().getType());
		blockLocation.getBlock().setType(Material.ICE);
		
		blockLocation = entityLocation.add(1, 0, 0);
		
		blockLocation = blockLocation.add(0, -1, 0);
		blockCache.put(blockLocation.getWorld().getName() + "," + blockLocation.getBlockX() + "," + blockLocation.getBlockY() + "," + blockLocation.getBlockZ(), blockLocation.getBlock().getType());
		blockLocation.getBlock().setType(Material.ICE);
		
		blockLocation = entityLocation.add(1, 0, 0);
		blockCache.put(blockLocation.getWorld().getName() + "," + blockLocation.getBlockX() + "," + blockLocation.getBlockY() + "," + blockLocation.getBlockZ(), blockLocation.getBlock().getType());
		blockLocation.getBlock().setType(Material.ICE);
		
		blockLocation = entityLocation.add(-2, 0, 0);
		blockCache.put(blockLocation.getWorld().getName() + "," + blockLocation.getBlockX() + "," + blockLocation.getBlockY() + "," + blockLocation.getBlockZ(), blockLocation.getBlock().getType());
		blockLocation.getBlock().setType(Material.ICE);
		
		blockLocation = entityLocation.add(1, 0, 0);
		
		plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new resetBlocks(blockCache), duration * 20L);
	}

	private class resetBlocks implements Runnable{

		private Map<String, Material> blockCache = new HashMap<String, Material>();
		
		public resetBlocks(Map<String, Material> blockCache) {
			this.blockCache = blockCache;
		}

		public void run() {
			Location location = null;
			for(Entry<String, Material> entry : blockCache.entrySet()){
				String[] locs = entry.getKey().split(",");
				location = new Location(plugin.getServer().getWorld(locs[0]), Integer.parseInt(locs[1]), Integer.parseInt(locs[2]), Integer.parseInt(locs[3]));
				location.getBlock().setType(entry.getValue());
			}
		}

	}

	
	public void magicArrow(Player player, float speed) {
		Location loc = player.getEyeLocation();
        loc.setX(loc.getX());
        loc.setY(loc.getY() - 0.5);
        loc.setZ(loc.getZ());
		Arrow arrow = player.getWorld().spawn(loc, Arrow.class);
		arrow.setShooter(player);
		//arrow.setFireTicks(100);
		arrow.setVelocity(player.getLocation().getDirection().normalize().multiply(speed));
	}
	
	public void sendArrowFromPlayer(Player player, int amt, float speed) {
		Location loc = player.getEyeLocation();
        final double tau = 2 * Math.PI;
        double arc = tau / amt;
        for (double a = 0; a < tau; a += arc) {
            Vector dir = new Vector(Math.cos(a), 0, Math.sin(a));
            Location spawn = loc.toVector().add(dir.multiply(2)).toLocation(loc.getWorld(), 0.0F, 0.0F);
            spawn.setY(spawn.getY() - 0.5);
            Arrow arrow = player.getWorld().spawn(loc, Arrow.class);
            arrow.setShooter(player);
            arrow.setVelocity(dir.multiply(speed));
        }
    }
	
}