package org.community.EpicFighters.Class.Assassine;

import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.community.EpicFighters.EpicFighters;
import org.community.fourWays.User.User;


public class epicFightersAssassineEntityDamageByEntityEvent implements Listener {

	private EpicFighters plugin;
	
	private double damageMultiplier;
    private int tempCritChance;
    private int tempMassiveChance;
    double initialDamage;

	public epicFightersAssassineEntityDamageByEntityEvent(EpicFighters plugin) {
		this.plugin = plugin;
	}

	@EventHandler(priority = EventPriority.NORMAL)
	public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {

		if (event.getCause() != EntityDamageEvent.DamageCause.PROJECTILE) {
			return;
		}
		
		if(!(event.getDamager() instanceof Arrow))
			return;
		
		Arrow attackingEntity = (Arrow) event.getDamager();
		
		if(!(attackingEntity.getShooter() instanceof Player))
			return;
		
		if(plugin.shotArrows.get(attackingEntity) == null)
			return;
		
		Entity entity = event.getEntity();
		
		if(entity instanceof ItemFrame)
			return;
		
		String skill = plugin.shotArrows.get(attackingEntity);
		Player player = plugin.shotArrowsOwner.get(attackingEntity);
    	
    	plugin.shotArrows.remove(attackingEntity);
    	plugin.shotArrowsOwner.remove(attackingEntity);
    	
		double damageReduction = plugin.eFDmg.getPenalty(player, "Assassine");
		double armorReduction = plugin.eFDmg.getArmorReduction(entity);
		double enchantmentReduction = plugin.eFDmg.getEnchantmentReduction(entity, "Environmental", armorReduction);
		double equipmentReduction = armorReduction + enchantmentReduction;
		double enchantmentDamageModifier = plugin.eFDmg.getBowEnchantmentDamage(player);
		
		//plugin.getServer().broadcastMessage("Dmg-Reduction: " + damageReduction);
		//plugin.getServer().broadcastMessage("Armor-Reduction: " + armorReduction);
		//plugin.getServer().broadcastMessage("Ench-Reduction: " + enchantmentReduction);
		//plugin.getServer().broadcastMessage("Equip-Reduction: " + equipmentReduction);

		double critNum = (plugin.randomGenerator.nextInt(40) + 80.0) / 100.0;

		//plugin.getServer().broadcastMessage("Raw-Damage: " + critNum);
		
		double bowDamage = plugin.config.getDouble("Assassine.Damage.Bow", 3);
		
		int damage = setDamage(player, entity, skill, (bowDamage * critNum) + enchantmentDamageModifier, damageReduction, equipmentReduction);
		
		if(damage == -1) {
			event.setCancelled(true);
			return;
		}
		
		event.setDamage(damage);
		
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
		int critLevel = plugin.skill.getInt("Skill.Kritischer_Pfeil.Stufe", 40);
		String critClass = plugin.skill.getString("Skill.Kritischer_Pfeil.Klasse", "FK");

		if (arrow.equalsIgnoreCase("Scharfer_Pfeil")) {
			tempCritChance = (int) (plugin.config.getDouble("Assassine.Damage.BaseCritChance", 1.0) * plugin.skill.getDouble("Skill.Scharfer_Pfeil.Option.Multiplier", 1.0));
			tempMassiveChance = (int) (plugin.config.getDouble("Assassine.Damage.BaseMassiveChance", 1.0) * plugin.skill.getDouble("Skill.Scharfer_Pfeil.Option.Multiplier", 1.0));
		} else {
			tempCritChance = (int) plugin.config.getDouble("Assassine.Damage.BaseCritChance", 1.0);
			tempMassiveChance = (int) plugin.config.getDouble("Assassine.Damage.BaseMassiveChance", 1.0);
		}

		if (player.isSneaking()) {
			if (plugin.eFDmg.hasCritChance(playerClass, playerLevel, critLevel, critClass) && (critNum >= 0 && critNum < tempCritChance)) {
				damageMultiplier = ((initialDamage * plugin.config.getDouble("Assassine.Damage.BaseCrit", 1.0) * plugin.config.getDouble("Assassine.Damage.Crouch", 1.0)) / damageReduction) * plugin.config.getDouble("Assassine.Damage.Base", 1.0);
				if (entity instanceof LivingEntity)
					player.sendMessage(ChatColor.YELLOW + "Critical hit!");
			} else if (plugin.eFDmg.hasCritChance(playerClass, playerLevel, critLevel, critClass) && (critNum >= tempCritChance && critNum < (plugin.config.getDouble("Assassine.Damage.BaseCritChance", 1.0) + tempMassiveChance))) {
				damageMultiplier = ((initialDamage * plugin.config.getDouble("Assassine.Damage.BaseMassive", 1.0) * plugin.config.getDouble("Assassine.Damage.Crouch", 1.0)) / damageReduction) * plugin.config.getDouble("Assassine.Damage.Base", 1.0);
				if (entity instanceof LivingEntity)
					player.sendMessage(ChatColor.RED + "MASSIVE CRIT!");
			} else {
				damageMultiplier = ((initialDamage * plugin.config.getDouble("Assassine.Damage.Crouch", 1.0)) / damageReduction) * plugin.config.getDouble("Assassine.Damage.Base", 1.0);
			}
		} else {
			if (plugin.eFDmg.hasCritChance(playerClass, playerLevel, critLevel, critClass) && (critNum >= 0 && critNum < tempCritChance)) {
				damageMultiplier = ((initialDamage * plugin.config.getDouble("Assassine.Damage.BaseCrit", 1.0)) / damageReduction) * plugin.config.getDouble("Assassine.Damage.Base", 1.0);
				if (entity instanceof LivingEntity)
					player.sendMessage(ChatColor.YELLOW + "Critical hit!");
			} else if (plugin.eFDmg.hasCritChance(playerClass, playerLevel, critLevel, critClass) && (critNum >= tempCritChance && critNum < (tempCritChance + tempMassiveChance))) {
				damageMultiplier = ((initialDamage * plugin.config.getDouble("Assassine.Damage.BaseMassive", 1.0)) / damageReduction) * plugin.config.getDouble("Assassine.Damage.Base", 1.0);
				if (entity instanceof LivingEntity)
					player.sendMessage(ChatColor.RED + "MASSIVE CRIT!");
			} else {
				damageMultiplier = ((initialDamage) / damageReduction) * plugin.config.getDouble("Assassine.Damage.Base", 1.0);
			}
		}
		if (arrow.equalsIgnoreCase("Spitzer_Pfeil")) {
			if (entity instanceof LivingEntity) {
				LivingEntity foobar = (LivingEntity) entity;
				if (foobar.getHealth() - (int) (Math.floor(damageMultiplier) * plugin.skill.getDouble("Skill.Spitzer_Pfeil.Option.Multiplier", 1.0)) > 0) {
					foobar.setHealth(foobar.getHealth()	- (int) (Math.floor(damageMultiplier) * plugin.skill.getDouble("Skill.Spitzer_Pfeil.Option.Multiplier", 1.0)));
				} else {
					foobar.setHealth(0);
				}
			}
			return 0;
		} else {
			if(plugin.eFDmg.isAttackBlocked(entity)) {
				player.sendMessage("Der Angriff wurde geblockt.");
				plugin.getServer().getWorld(entity.getWorld().getName()).playEffect(entity.getLocation(), Effect.DOOR_TOGGLE, 5);
				return -1;
			}
			return ((int) Math.floor(damageMultiplier - (damageMultiplier * armorReduction)));
		}
	}
	
}