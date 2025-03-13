package org.community.EpicFighters.Class.Berserker;

import java.util.LinkedList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.World;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.community.EpicFighters.EpicFighters;

public class EpicFightersBerserkerDamageSpells {
	private EpicFighters plugin;

	public EpicFightersBerserkerDamageSpells(EpicFighters plugin) {
		this.plugin = plugin;
	}
	
	public void getAggro(Player player, Entity entity) {
		
		Creature c = (Creature) entity;
		c.setTarget(player);
		
	}

	public void testSwordSkill(Player player, Entity entity) {
		if (entity instanceof Creature) {
			Creature monster = (Creature) entity;
			Bukkit.getServer()
					.getWorld(player.getWorld().getName())
					.playEffect(player.getLocation(), Effect.MOBSPAWNER_FLAMES,
							5);
			List<Entity> entityList = entity
					.getNearbyEntities(10.0, 10.0, 10.0);

			if (!entityList.isEmpty()) {
				for (Entity ent : entityList) {
					if (ent != player && ent instanceof LivingEntity)
					{
						monster.remove();
						World world = player.getWorld();
						LivingEntity le = (LivingEntity) world.spawnEntity(player.getLocation(), EntityType.ZOMBIE);
						Creature monster2 = (Creature) le;
						monster2.setTarget((LivingEntity) ent);
						plugin.LogInfo("Changed Aggro!");
					}
				}
			} else
				plugin.LogInfo("Keine Gegner in der Nähe");
		} else
			plugin.LogInfo("Gegner kann nicht angreifen");

	}

	public void swordWhirl(Player player, Entity entity, int boxrangex,
			int boxrangey, int boxrangez) {
		Bukkit.getServer().getWorld(player.getWorld().getName())
				.playEffect(player.getLocation(), Effect.MOBSPAWNER_FLAMES, 5);
		List<Entity> entityList = new LinkedList<Entity>();
		entityList = player.getNearbyEntities(boxrangex, boxrangey, boxrangez);
		entityList.remove(entity);
		for (Entity e : entityList) {
			// TODO: Damage berechnen und Entity's Leben entsprechend reduzieren
			// Nur ein Dummy gegen "unused" Fehlermeldung
			e.setFireTicks(0);
		}
	}

	public void slowStrike(Player player, Entity entity, int duration,
			int strength) {
		Bukkit.getServer().getWorld(player.getWorld().getName())
				.playEffect(player.getLocation(), Effect.MOBSPAWNER_FLAMES, 5);
		if (entity instanceof LivingEntity) {
			LivingEntity ent = (LivingEntity) entity;
			ent.addPotionEffect(new PotionEffect(PotionEffectType.SLOW,
					duration * 20, strength), true);
		}
	}
}
