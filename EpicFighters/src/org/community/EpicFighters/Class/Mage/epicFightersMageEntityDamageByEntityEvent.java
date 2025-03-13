package org.community.EpicFighters.Class.Mage;

import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Snowball;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.community.EpicFighters.EpicFighters;
import org.community.fourWays.User.User;


public class epicFightersMageEntityDamageByEntityEvent implements Listener {

	private EpicFighters plugin;
	
	private double damageMultiplier;
    private int tempCritChance;
    private int tempMassiveChance;
    double initialDamage;

	public epicFightersMageEntityDamageByEntityEvent(EpicFighters plugin) {
		this.plugin = plugin;
	}

	@EventHandler(priority = EventPriority.NORMAL)
	public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
		
		String skill = "";
		String enchantmentType = "";
		Player player = null;
		
		if(event.getEntity() instanceof ItemFrame)
			return;

		if (event.getCause() != EntityDamageEvent.DamageCause.PROJECTILE) {
			return;
		}
		
		if(!(event.getDamager() instanceof Fireball) && !(event.getDamager() instanceof Snowball) && !(event.getDamager() instanceof Arrow))
			return;
		
		if(event.getDamager() instanceof Fireball) {
			Fireball attackingEntity = (Fireball) event.getDamager();

			if(!(attackingEntity.getShooter() instanceof Player))
				return;

			if(plugin.shotFireballs.get(attackingEntity) == null)
				return;
			
			skill = plugin.shotFireballs.get(attackingEntity);
			player = plugin.shotFireballsOwner.get(attackingEntity);
			enchantmentType = "Fire";

			plugin.shotFireballs.remove(attackingEntity);
			plugin.shotFireballsOwner.remove(attackingEntity);
		}
		
		if(event.getDamager() instanceof Snowball) {
			Snowball attackingEntity = (Snowball) event.getDamager();

			if(!(attackingEntity.getShooter() instanceof Player))
				return;

			if(plugin.shotSnowballs.get(attackingEntity) == null)
				return;
			
			skill = plugin.shotSnowballs.get(attackingEntity);
			player = plugin.shotSnowballsOwner.get(attackingEntity);
			enchantmentType = "Environmental";

			plugin.shotSnowballs.remove(attackingEntity);
			plugin.shotSnowballsOwner.remove(attackingEntity);
		}
		
		if(event.getDamager() instanceof Arrow) {
			Arrow attackingEntity = (Arrow) event.getDamager();

			if(!(attackingEntity.getShooter() instanceof Player))
				return;

			if(plugin.shotMagicArrows.get(attackingEntity) == null)
				return;
			
			skill = plugin.shotMagicArrows.get(attackingEntity);
			player = plugin.shotMagicArrowsOwner.get(attackingEntity);
			enchantmentType = "Blast";

			plugin.shotMagicArrows.remove(attackingEntity);
			plugin.shotMagicArrowsOwner.remove(attackingEntity);
		}

		Entity entity = event.getEntity();
		
		if(plugin.ancientRelicsAPI.aRApiPvpStatus.getPvpStatus(player, entity, 0))
			return;
		
    	plugin.getServer().getWorld(entity.getWorld().getName()).playEffect(entity.getLocation(), Effect.SMOKE, 5);
    	
		double damageReduction = plugin.eFDmg.getPenalty(player, "Magier");
		double armorReduction = plugin.eFDmg.getArmorReduction(entity);
		double enchantmentReduction = plugin.eFDmg.getEnchantmentReduction(entity, enchantmentType, armorReduction);
		double equipmentReduction = armorReduction + enchantmentReduction;
		
		//plugin.getServer().broadcastMessage("Dmg-Reduction: " + damageReduction);
		//plugin.getServer().broadcastMessage("Armor-Reduction: " + armorReduction);
		//plugin.getServer().broadcastMessage("Ench-Reduction: " + enchantmentReduction);
		//plugin.getServer().broadcastMessage("Equip-Reduction: " + equipmentReduction);
				
		double critNum = (plugin.randomGenerator.nextInt(40) + 80.0) / 100.0;
		
		//plugin.getServer().broadcastMessage("Raw-Damage: " + critNum);
		
		int damage = setDamage(player, entity, skill, plugin.skill.getDouble("Skill." + skill + ".Option.Damage", 1) * critNum, damageReduction, equipmentReduction);
		
		if(damage == -1) {
			event.setCancelled(true);
			return;
		}
		
		event.setDamage(damage);
		plugin.eFDmg.setEffects(entity, skill);
		
		//plugin.getServer().broadcastMessage("Damage: " + damage);
		
	}
	
	public int setDamage (Player player, Entity entity, String arrow, double damage, double damageReduction, double armorReduction) {
		tempCritChance = 0;
		tempMassiveChance = 0;
		initialDamage = damage;
		int critNum = plugin.randomGenerator.nextInt(100);
		
		User user = plugin.fourWaysAPI.fWCore.getUserClass(player);
		
		String[] userInfo = user.getJobHash().split(",");
		String playerClass = userInfo[1];
		int playerLevel = new Integer(userInfo[0]);
		int critLevel = plugin.skill.getInt("Skill.Kritische_Magie.Stufe", 40);
		String critClass = plugin.skill.getString("Skill.Kritische_Magie.Klasse", "FK");

		tempCritChance = (int) plugin.config.getDouble("Mage.Damage.BaseCritChance", 1.0);
		tempMassiveChance = (int) plugin.config.getDouble("Mage.Damage.BaseMassiveChance", 1.0);

		if (player.isSneaking()) {
			if (plugin.eFDmg.hasCritChance(playerClass, playerLevel, critLevel, critClass) && (critNum >= 0 && critNum < tempCritChance)) {
				damageMultiplier = ((initialDamage * plugin.config.getDouble("Mage.Damage.BaseCrit", 1.0) * plugin.config.getDouble("Mage.Damage.Crouch", 1.0)) / damageReduction) * plugin.config.getDouble("Mage.Damage.Base", 1.0);
				if (entity instanceof LivingEntity)
					player.sendMessage(ChatColor.YELLOW + "Critical hit!");
			} else if (plugin.eFDmg.hasCritChance(playerClass, playerLevel, critLevel, critClass) && (critNum >= tempCritChance && critNum < (plugin.config.getDouble("Mage.Damage.BaseCritChance", 1.0) + tempMassiveChance))) {
				damageMultiplier = ((initialDamage * plugin.config.getDouble("Mage.Damage.BaseMassive", 1.0) * plugin.config.getDouble("Mage.Damage.Crouch", 1.0)) / damageReduction) * plugin.config.getDouble("Mage.Damage.Base", 1.0);
				if (entity instanceof LivingEntity)
					player.sendMessage(ChatColor.RED + "MASSIVE CRIT!");
			} else {
				damageMultiplier = ((initialDamage * plugin.config.getDouble("Mage.Damage.Crouch", 1.0)) / damageReduction) * plugin.config.getDouble("Mage.Damage.Base", 1.0);
			}
		} else {
			if (plugin.eFDmg.hasCritChance(playerClass, playerLevel, critLevel, critClass) && (critNum >= 0 && critNum < tempCritChance)) {
				damageMultiplier = ((initialDamage * plugin.config.getDouble("Mage.Damage.BaseCrit", 1.0)) / damageReduction) * plugin.config.getDouble("Mage.Damage.Base", 1.0);
				if (entity instanceof LivingEntity)
					player.sendMessage(ChatColor.YELLOW + "Critical hit!");
			} else if (plugin.eFDmg.hasCritChance(playerClass, playerLevel, critLevel, critClass) && (critNum >= tempCritChance && critNum < (tempCritChance + tempMassiveChance))) {
				damageMultiplier = ((initialDamage * plugin.config.getDouble("Mage.Damage.BaseMassive", 1.0)) / damageReduction) * plugin.config.getDouble("Mage.Damage.Base", 1.0);
				if (entity instanceof LivingEntity)
					player.sendMessage(ChatColor.RED + "MASSIVE CRIT!");
			} else {
				damageMultiplier = ((initialDamage) / damageReduction) * plugin.config.getDouble("Mage.Damage.Base", 1.0);
			}
		}
		if(plugin.eFDmg.isAttackBlocked(entity)) {
			player.sendMessage("Der Angriff wurde geblockt.");
			plugin.getServer().getWorld(entity.getWorld().getName()).playEffect(entity.getLocation(), Effect.DOOR_TOGGLE, 5);
			return -1;
		}
		return ((int) Math.floor(damageMultiplier - (damageMultiplier * armorReduction)));
	}
	
}