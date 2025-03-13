package org.community.EpicFighters.Class.Berserker;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Material;
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


public class epicFightersBerserkerEntityDamageByEntityEvent implements Listener {

	private EpicFighters plugin;
	
	private double damageMultiplier;
    private int tempCritChance;
    private int tempMassiveChance;
    double initialDamage;

	public epicFightersBerserkerEntityDamageByEntityEvent(EpicFighters plugin) {
		this.plugin = plugin;
	}

	@EventHandler(priority = EventPriority.NORMAL)
	public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {


		if (event.isCancelled())
			return;
		
		if (event.getCause() == EntityDamageEvent.DamageCause.PROJECTILE) {
			return;
		}
		
		if(event.getDamager() instanceof Arrow)
			return;
		

		
		String skill = "";
		String enchantmentType = "";
		Player player = null;

		if (event.getCause() != EntityDamageEvent.DamageCause.ENTITY_ATTACK) {
			return;
		}
		
		if(!(event.getDamager() instanceof Player))
			return;
		
		player = (Player) event.getDamager();

		if(plugin.playerAttack.get(player) == null) {
			skill = "Schwertschlag";
		} else {
			skill = plugin.playerAttack.get(player);
			plugin.playerAttack.remove(player);
		}
		

		enchantmentType = "Environmental";		
		
		Entity entity = event.getEntity();	
		
		if(entity instanceof ItemFrame)
			return;	
		
    	plugin.getServer().getWorld(entity.getWorld().getName()).playEffect(entity.getLocation(), Effect.SMOKE, 5);
    	
    	
		double damageReduction = plugin.eFDmg.getPenalty(player, "Berserker");
		double armorReduction = plugin.eFDmg.getArmorReduction(entity);
		double enchantmentReduction = plugin.eFDmg.getEnchantmentReduction(entity, enchantmentType, armorReduction);
		double equipmentReduction = armorReduction + enchantmentReduction;
		double enchantmentDamageModifier = plugin.eFDmg.getSwordEnchantmentDamage(player, entity);
		
		//plugin.getServer().broadcastMessage("Dmg-Reduction: " + damageReduction);
		//plugin.getServer().broadcastMessage("Armor-Reduction: " + armorReduction);
		//plugin.getServer().broadcastMessage("Ench-Reduction: " + enchantmentReduction);
		//plugin.getServer().broadcastMessage("Equip-Reduction: " + equipmentReduction);
				
		double critNum = (plugin.randomGenerator.nextInt(40) + 80.0) / 100.0;
		//plugin.getServer().broadcastMessage("Raw-Damage: " + critNum);
		
		double swordDamage = 0;
		if(player.getItemInHand().getType() == Material.WOOD_SWORD){
			swordDamage = plugin.config.getDouble("Berserker.Damage.Wood", 1);
		} else if(player.getItemInHand().getType() == Material.STONE_SWORD){
			swordDamage = plugin.config.getDouble("Berserker.Damage.Stone", 1);
		} else if(player.getItemInHand().getType() == Material.IRON_SWORD){
			swordDamage = plugin.config.getDouble("Berserker.Damage.Iron", 1);
		} else if(player.getItemInHand().getType() == Material.GOLD_SWORD){
			swordDamage = plugin.config.getDouble("Berserker.Damage.Gold", 1);
		} else if(player.getItemInHand().getType() == Material.DIAMOND_SWORD){
			swordDamage = plugin.config.getDouble("Berserker.Damage.Diamond", 1);
		} else {
			swordDamage = 1;
		}
		
		int damage = setDamage(player, entity, skill, ((swordDamage + plugin.skill.getDouble("Skill." + skill + ".Option.Damage", 1)) * critNum) + enchantmentDamageModifier, damageReduction, equipmentReduction);
		
		
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
		int critLevel = plugin.skill.getInt("Skill.Kritischer_Schlag.Stufe", 40);
		String critClass = plugin.skill.getString("Skill.Kritischer_Schlag.Klasse", "BS");

		tempCritChance = (int) plugin.config.getDouble("Berserker.Damage.BaseCritChance", 1.0);
		tempMassiveChance = (int) plugin.config.getDouble("Berserker.Damage.BaseMassiveChance", 1.0);

		if (player.isSneaking()) {
			if (plugin.eFDmg.hasCritChance(playerClass, playerLevel, critLevel, critClass) && (critNum >= 0 && critNum < tempCritChance)) {
				damageMultiplier = ((initialDamage * plugin.config.getDouble("Berserker.Damage.BaseCrit", 1.0) * plugin.config.getDouble("Berserker.Damage.Crouch", 1.0)) / damageReduction) * plugin.config.getDouble("Berserker.Damage.Base", 1.0);
				if (entity instanceof LivingEntity)
					player.sendMessage(ChatColor.YELLOW + "Critical hit!");
			} else if (plugin.eFDmg.hasCritChance(playerClass, playerLevel, critLevel, critClass) && (critNum >= tempCritChance && critNum < (plugin.config.getDouble("Berserker.Damage.BaseCritChance", 1.0) + tempMassiveChance))) {
				damageMultiplier = ((initialDamage * plugin.config.getDouble("Berserker.Damage.BaseMassive", 1.0) * plugin.config.getDouble("Berserker.Damage.Crouch", 1.0)) / damageReduction) * plugin.config.getDouble("Berserker.Damage.Base", 1.0);
				if (entity instanceof LivingEntity)
					player.sendMessage(ChatColor.RED + "MASSIVE CRIT!");
			} else {
				damageMultiplier = ((initialDamage * plugin.config.getDouble("Berserker.Damage.Crouch", 1.0)) / damageReduction) * plugin.config.getDouble("Berserker.Damage.Base", 1.0);
			}
		} else {
			if (plugin.eFDmg.hasCritChance(playerClass, playerLevel, critLevel, critClass) && (critNum >= 0 && critNum < tempCritChance)) {
				damageMultiplier = ((initialDamage * plugin.config.getDouble("Berserker.Damage.BaseCrit", 1.0)) / damageReduction) * plugin.config.getDouble("Berserker.Damage.Base", 1.0);
				if (entity instanceof LivingEntity)
					player.sendMessage(ChatColor.YELLOW + "Critical hit!");
			} else if (plugin.eFDmg.hasCritChance(playerClass, playerLevel, critLevel, critClass) && (critNum >= tempCritChance && critNum < (tempCritChance + tempMassiveChance))) {
				damageMultiplier = ((initialDamage * plugin.config.getDouble("Berserker.Damage.BaseMassive", 1.0)) / damageReduction) * plugin.config.getDouble("Berserker.Damage.Base", 1.0);
				if (entity instanceof LivingEntity)
					player.sendMessage(ChatColor.RED + "MASSIVE CRIT!");
			} else {
				damageMultiplier = ((initialDamage) / damageReduction) * plugin.config.getDouble("Berserker.Damage.Base", 1.0);
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