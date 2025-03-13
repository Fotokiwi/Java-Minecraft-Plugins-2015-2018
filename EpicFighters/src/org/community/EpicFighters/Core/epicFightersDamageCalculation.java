package org.community.EpicFighters.Core;

import java.util.Set;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.community.EpicFighters.EpicFighters;
import org.community.fourWays.User.User;

public class epicFightersDamageCalculation {
	
	private EpicFighters plugin;

	public epicFightersDamageCalculation(EpicFighters plugin) {
		this.plugin = plugin;
	}
	
	public double getPenalty (Player player, String klasse) {
		double damageReduction = 0;

		String helm = String.valueOf(player.getInventory().getHelmet());
		String chest = String.valueOf(player.getInventory().getChestplate());
		String pants = String.valueOf(player.getInventory().getLeggings());
		String boots = String.valueOf(player.getInventory().getBoots());

		if (helm.contains("DIAMOND_HELMET")) {
			damageReduction += (plugin.config.getDouble(klasse + ".Armor.DiamondPenalty", 1.0) * 0.20);
		} else if (helm.contains("IRON_HELMET")) {
			damageReduction += (plugin.config.getDouble(klasse + ".Armor.IronPenalty", 1.0) * 0.20);
		} else if (helm.contains("GOLD_HELMET")) {
			damageReduction += (plugin.config.getDouble(klasse + ".Armor.GoldPenalty", 1.0) * 0.20);
		} else if (helm.contains("LEATHER_HELMET")) {
			damageReduction += (plugin.config.getDouble(klasse + ".Armor.LeatherPenalty", 1.0) * 0.20);
		} else if (helm.contains("CHAINMAIL_HELMET")) {
			damageReduction += (plugin.config.getDouble(klasse + ".Armor.ChainPenalty", 1.0) * 0.20);
		} else {
			damageReduction += (plugin.config.getDouble(klasse + ".Armor.NakedPenalty", 1.0) * 0.20);
		}
		if (chest.contains("DIAMOND_CHESTPLATE")) {
			damageReduction += (plugin.config.getDouble(klasse + ".Armor.DiamondPenalty", 1.0) * 0.40);
		} else if (chest.contains("IRON_CHESTPLATE")) {
			damageReduction += (plugin.config.getDouble(klasse + ".Armor.IronPenalty", 1.0) * 0.40);
		} else if (chest.contains("GOLD_CHESTPLATE")) {
			damageReduction += (plugin.config.getDouble(klasse + ".Armor.GoldPenalty", 1.0) * 0.40);
		} else if (chest.contains("LEATHER_CHESTPLATE")) {
			damageReduction += (plugin.config.getDouble(klasse + ".Armor.LeatherPenalty", 1.0) * 0.40);
		} else if (chest.contains("CHAINMAIL_CHESTPLATE")) {
			damageReduction += (plugin.config.getDouble(klasse + ".Armor.ChainPenalty", 1.0) * 0.40);
		} else {
			damageReduction += (plugin.config.getDouble(klasse + ".Armor.NakedPenalty", 1.0) * 0.40);
		}
		if (pants.contains("DIAMOND_LEGGINGS")) {
			damageReduction += (plugin.config.getDouble(klasse + ".Armor.DiamondPenalty", 1.0) * 0.30);
		} else if (pants.contains("IRON_LEGGINGS")) {
			damageReduction += (plugin.config.getDouble(klasse + ".Armor.IronPenalty", 1.0) * 0.30);
		} else if (pants.contains("GOLD_LEGGINGS")) {
			damageReduction += (plugin.config.getDouble(klasse + ".Armor.GoldPenalty", 1.0) * 0.30);
		} else if (pants.contains("LEATHER_LEGGINGS")) {
			damageReduction += (plugin.config.getDouble(klasse + ".Armor.LeatherPenalty", 1.0) * 0.30);
		} else if (pants.contains("CHAINMAIL_LEGGINGS")) {
			damageReduction += (plugin.config.getDouble(klasse + ".Armor.ChainPenalty", 1.0) * 0.30);
		} else {
			damageReduction += (plugin.config.getDouble(klasse + ".Armor.NakedPenalty", 1.0) * 0.30);
		}
		if (boots.contains("DIAMOND_BOOTS")) {
			damageReduction += (plugin.config.getDouble(klasse + ".Armor.DiamondPenalty", 1.0) * 0.10);
		} else if (boots.contains("IRON_BOOTS")) {
			damageReduction += (plugin.config.getDouble(klasse + ".Armor.IronPenalty", 1.0) * 0.10);
		} else if (boots.contains("GOLD_BOOTS")) {
			damageReduction += (plugin.config.getDouble(klasse + ".Armor.GoldPenalty", 1.0) * 0.10);
		} else if (boots.contains("LEATHER_BOOTS")) {
			damageReduction += (plugin.config.getDouble(klasse + ".Armor.LeatherPenalty", 1.0) * 0.10);
		} else if (boots.contains("CHAINMAIL_BOOTS")) {
			damageReduction += (plugin.config.getDouble(klasse + ".Armor.ChainPenalty", 1.0) * 0.10);
		} else {
			damageReduction += (plugin.config.getDouble(klasse + ".Armor.NakedPenalty", 1.0) * 0.10);
		}
		return damageReduction;
	}
	
	public double getArmorReduction(Entity entity) {
		double damageReduction = 0;
		
		LivingEntity livingEntity = (LivingEntity) entity;
		EntityEquipment equipment = livingEntity.getEquipment();

		String helm = String.valueOf(equipment.getHelmet());
		String chest = String.valueOf(equipment.getChestplate());
		String pants = String.valueOf(equipment.getLeggings());
		String boots = String.valueOf(equipment.getBoots());
		
		if (helm.contains("DIAMOND_HELMET")) {
			damageReduction += (0.12);
		} else if (helm.contains("IRON_HELMET")) {
			damageReduction += (0.08);
		} else if (helm.contains("GOLD_HELMET")) {
			damageReduction += (0.08);
		} else if (helm.contains("LEATHER_HELMET")) {
			damageReduction += (0.04);
		} else if (helm.contains("CHAINMAIL_HELMET")) {
			damageReduction += (0.08);
		} else {
			damageReduction += (0);
		}
		if (chest.contains("DIAMOND_CHESTPLATE")) {
			damageReduction += (0.32);
		} else if (chest.contains("IRON_CHESTPLATE")) {
			damageReduction += (0.24);
		} else if (chest.contains("GOLD_CHESTPLATE")) {
			damageReduction += (0.20);
		} else if (chest.contains("LEATHER_CHESTPLATE")) {
			damageReduction += (0.12);
		} else if (chest.contains("CHAINMAIL_CHESTPLATE")) {
			damageReduction += (0.20);
		} else {
			damageReduction += (0);
		}
		if (pants.contains("DIAMOND_LEGGINGS")) {
			damageReduction += (0.24);
		} else if (pants.contains("IRON_LEGGINGS")) {
			damageReduction += (0.20);
		} else if (pants.contains("GOLD_LEGGINGS")) {
			damageReduction += (0.16);
		} else if (pants.contains("LEATHER_LEGGINGS")) {
			damageReduction += (0.08);
		} else if (pants.contains("CHAINMAIL_LEGGINGS")) {
			damageReduction += (0.12);
		} else {
			damageReduction += (0);
		}
		if (boots.contains("DIAMOND_BOOTS")) {
			damageReduction += (0.12);
		} else if (boots.contains("IRON_BOOTS")) {
			damageReduction += (0.08);
		} else if (boots.contains("GOLD_BOOTS")) {
			damageReduction += (0.04);
		} else if (boots.contains("LEATHER_BOOTS")) {
			damageReduction += (0.04);
		} else if (boots.contains("CHAINMAIL_BOOTS")) {
			damageReduction += (0.04);
		} else {
			damageReduction += (0);
		}
		
		return damageReduction;
	}
	
	public double getEnchantmentReduction(Entity entity, String enchantment, double armorReduction) {
		double damageReduction = 0;
		
		LivingEntity livingEntity = (LivingEntity) entity;
		EntityEquipment equipment = livingEntity.getEquipment();
		
		if(equipment == null)
			return 0;
		
		Enchantment ench = null;
		double baseEnchFactor1 = 0.0;
		double baseEnchFactor2 = 0.0;
		double baseEnchFactor3 = 0.0;
		double baseEnchFactor4 = 0.0;
		
		if(enchantment.equalsIgnoreCase("Environmental")) {
			ench = Enchantment.PROTECTION_ENVIRONMENTAL;
			baseEnchFactor1 = 1;
			baseEnchFactor2 = 2;
			baseEnchFactor3 = 3;
			baseEnchFactor4 = 5;
		}
		if(enchantment.equalsIgnoreCase("Fire")) {
			ench = Enchantment.PROTECTION_FIRE;
			baseEnchFactor1 = 2;
			baseEnchFactor2 = 4;
			baseEnchFactor3 = 6;
			baseEnchFactor4 = 9;
		}
		if(enchantment.equalsIgnoreCase("Blast")) {
			ench = Enchantment.PROTECTION_EXPLOSIONS;
			baseEnchFactor1 = 3;
			baseEnchFactor2 = 5;
			baseEnchFactor3 = 7;
			baseEnchFactor4 = 11;
		}
		if(enchantment.equalsIgnoreCase("Projectile")) {
			ench = Enchantment.PROTECTION_PROJECTILE;
			baseEnchFactor1 = 3;
			baseEnchFactor2 = 5;
			baseEnchFactor3 = 7;
			baseEnchFactor4 = 11;
		}
		
		if(equipment.getHelmet() != null) {
			if (equipment.getHelmet().containsEnchantment(ench) == false) {
				if(equipment.getHelmet().getEnchantmentLevel(ench) == 4) {
					damageReduction += (0.04 * baseEnchFactor4 * armorReduction);
				} else if(equipment.getHelmet().getEnchantmentLevel(ench) == 3) {
					damageReduction += (0.04 * baseEnchFactor3 * armorReduction);
				} else if(equipment.getHelmet().getEnchantmentLevel(ench) == 2) {
					damageReduction += (0.04 * baseEnchFactor2 * armorReduction);
				} else if(equipment.getHelmet().getEnchantmentLevel(ench) == 1){
					damageReduction += (0.04 * baseEnchFactor1 * armorReduction);
				} else {
					damageReduction += (0);
				}
			}
		}
		if(equipment.getChestplate() != null) {
			if (equipment.getChestplate().containsEnchantment(ench) == false) {
				if(equipment.getChestplate().getEnchantmentLevel(ench) == 4) {
					damageReduction += (0.04 * baseEnchFactor4 * armorReduction);
				} else if(equipment.getChestplate().getEnchantmentLevel(ench) == 3) {
					damageReduction += (0.04 * baseEnchFactor3 * armorReduction);
				} else if(equipment.getChestplate().getEnchantmentLevel(ench) == 2) {
					damageReduction += (0.04 * baseEnchFactor2 * armorReduction);
				} else if(equipment.getChestplate().getEnchantmentLevel(ench) == 1){
					damageReduction += (0.04 * baseEnchFactor1 * armorReduction);
				} else {
					damageReduction += (0);
				}
			}
		}
		if(equipment.getLeggings() != null) {
			if (equipment.getLeggings().containsEnchantment(ench) == false) {
				if(equipment.getLeggings().getEnchantmentLevel(ench) == 4) {
					damageReduction += (0.04 * baseEnchFactor4 * armorReduction);
				} else if(equipment.getLeggings().getEnchantmentLevel(ench) == 3) {
					damageReduction += (0.04 * baseEnchFactor3 * armorReduction);
				} else if(equipment.getLeggings().getEnchantmentLevel(ench) == 2) {
					damageReduction += (0.04 * baseEnchFactor2 * armorReduction);
				} else if(equipment.getLeggings().getEnchantmentLevel(ench) == 1){
					damageReduction += (0.04 * baseEnchFactor1 * armorReduction);
				} else {
					damageReduction += (0);
				}
			}
		}
		if(equipment.getBoots() != null) {
			if (equipment.getBoots().containsEnchantment(ench) == false) {
				if(equipment.getBoots().getEnchantmentLevel(ench) == 4) {
					damageReduction += (0.04 * baseEnchFactor4 * armorReduction);
				} else if(equipment.getBoots().getEnchantmentLevel(ench) == 3) {
					damageReduction += (0.04 * baseEnchFactor3 * armorReduction);
				} else if(equipment.getBoots().getEnchantmentLevel(ench) == 2) {
					damageReduction += (0.04 * baseEnchFactor2 * armorReduction);
				} else if(equipment.getBoots().getEnchantmentLevel(ench) == 1){
					damageReduction += (0.04 * baseEnchFactor1 * armorReduction);
				} else {
					damageReduction += (0);
				}
			}
		}
		
		if(damageReduction > ((1 - armorReduction) * 0.8))
			damageReduction = ((1 - armorReduction) * 0.8);
		
		return damageReduction;
	}
	
	public boolean isAttackBlocked(Entity entity) {
		
		int blockNum = plugin.randomGenerator.nextInt(100);
		
		if(entity instanceof LivingEntity && !(entity instanceof Player)) {
			if(blockNum <= 3) {
				return true;
			} else {
				return false;
			}
		}
		
		if(!(entity instanceof Player)) {
			return false;
		}
		
		if(plugin.getServer().getPlayer(entity.getUniqueId()) == null) {
			return false;
		}
		
		Player player = (Player) entity;
		User user = plugin.fourWaysAPI.fWCore.getUserClass(player);
		
		String[] userInfo = user.getJobHash().split(",");
		String playerClass = userInfo[1];
		int playerLevel = new Integer(userInfo[0]);
		
		ConfigurationSection blockSection = plugin.skill.getConfigurationSection("Skill.Grosser_Schild.Klassen");
		
		if(blockSection == null)
			return false;
		
		Set<String> blockKeys = blockSection.getKeys(false);
  	  	String[] blockArray = blockKeys.toArray(new String[0]);  	  	
  	  	
		int blockLevel;
		
		for(int i = 0; i < blockArray.length; i++){
			blockLevel = plugin.skill.getInt("Skill.Grosser_Schild.Klassen." + blockArray[i], 0);
			if(playerClass.contains(blockArray[i]) && playerLevel >= blockLevel) {
				if(blockNum <= plugin.skill.getInt("Skill.Grosser_Schild.Option.Chance", 0))
					return true;
			}
		}
		
		blockSection = plugin.skill.getConfigurationSection("Skill.Kleiner_Schild.Klassen");
		
		if(blockSection == null)
			return false;
		
		blockKeys = blockSection.getKeys(false);
  	  	blockArray = blockKeys.toArray(new String[0]);
		
		for(int i = 0; i < blockArray.length; i++){
			blockLevel = plugin.skill.getInt("Skill.Kleiner_Schild.Klassen." + blockArray[i], 0);
			if(playerClass.contains(blockArray[i]) && playerLevel >= blockLevel) {
				if(blockNum <= plugin.skill.getInt("Skill.Kleiner_Schild.Option.Chance", 0))
					return true;
			}
		}
		
		return false;
	}
	
	public boolean hasCritChance(String playerClass, int playerLevel, int critLevel, String critClass) {
		if(playerClass.contains(critClass) && playerLevel >= critLevel)
			return true;
		
		return false;
	}
	
	public void setEffects(Entity entity, String skill) {
		
		if(plugin.skill.getString("Skill." + skill + ".Effects.Type") == null)
			return;
		
		String effectType = plugin.skill.getString("Skill." + skill + ".Effects.Type");
		int effectDuration = plugin.skill.getInt("Skill." + skill + ".Effects.Duration", 1) * 20;
		double effectStrength = plugin.skill.getInt("Skill." + skill + ".Effects.Strength", 1);
		
		if(effectType.equalsIgnoreCase("FireTick")) {
			LivingEntity lEntity = (LivingEntity) entity;
			lEntity.setFireTicks(effectDuration);
			//plugin.getServer().broadcastMessage("Effekt ausgelöst:" + effectType + "(" + effectDuration + "s)");
			return;
		}
		
		if(effectType.equalsIgnoreCase("Freeze")) {
			LivingEntity lEntity = (LivingEntity) entity;
			lEntity.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, effectDuration, (int)effectStrength));
			//plugin.getServer().broadcastMessage("Effekt ausgelöst:" + effectType + "(" + effectDuration + "s)");
			return;
		}
		
		if(effectType.equalsIgnoreCase("Explosion")) {
			Location loc = entity.getLocation();
			loc.setY(loc.getY() + 0.5);
			entity.getWorld().createExplosion(loc, (float) effectStrength);
			//plugin.getServer().broadcastMessage("Effekt ausgelöst:" + effectType + "(" + effectDuration + "s)");
			return;
		}
	}

	public double getBowEnchantmentDamage(Player player) {

		if(player.getItemInHand().getType() != Material.BOW)
			return 0.0;
		
		if(!player.getItemInHand().getEnchantments().containsKey(Enchantment.ARROW_DAMAGE))
			return 0.0;
		
		int enchantmentLevel = player.getItemInHand().getEnchantmentLevel(Enchantment.ARROW_DAMAGE);
		
		double baseEnchFactor = 1.0;
		
		baseEnchFactor = 0.5 * enchantmentLevel;
		
		return baseEnchFactor;
	}

	public double getSwordEnchantmentDamage(Player player, Entity entity) {

		if(player.getItemInHand().getType() != Material.WOOD_SWORD && player.getItemInHand().getType() != Material.STONE_SWORD && player.getItemInHand().getType() != Material.IRON_SWORD && player.getItemInHand().getType() != Material.GOLD_SWORD && player.getItemInHand().getType() != Material.DIAMOND_SWORD)
			return 0.0;
		
		if((!player.getItemInHand().getEnchantments().containsKey(Enchantment.DAMAGE_ALL)) && (!player.getItemInHand().getEnchantments().containsKey(Enchantment.DAMAGE_ARTHROPODS)) && (!player.getItemInHand().getEnchantments().containsKey(Enchantment.DAMAGE_UNDEAD)))
			return 0.0;
		
		int enchantmentLevelAll = 0;
		int enchantmentLevelArthropods = 0;
		int enchantmentLevelUndead = 0;
		
		double baseEnchFactor = 1.0;
		
		if(entity.getType() == EntityType.SPIDER || entity.getType() == EntityType.CAVE_SPIDER || entity.getType() == EntityType.SILVERFISH) {
			if(player.getItemInHand().getEnchantments().containsKey(Enchantment.DAMAGE_ALL))
				enchantmentLevelAll =  player.getItemInHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL);
			if(player.getItemInHand().getEnchantments().containsKey(Enchantment.DAMAGE_ARTHROPODS))
				enchantmentLevelArthropods =  player.getItemInHand().getEnchantmentLevel(Enchantment.DAMAGE_ARTHROPODS);
			if(enchantmentLevelArthropods >= enchantmentLevelAll) {
				baseEnchFactor = 0.5 * enchantmentLevelArthropods;
			} else {
				baseEnchFactor = 0.5 * enchantmentLevelAll;
			}
		} else if(entity.getType() == EntityType.ZOMBIE || entity.getType() == EntityType.PIG_ZOMBIE || entity.getType() == EntityType.SKELETON) {
			if(player.getItemInHand().getEnchantments().containsKey(Enchantment.DAMAGE_ALL))
				enchantmentLevelAll =  player.getItemInHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL);
			if(player.getItemInHand().getEnchantments().containsKey(Enchantment.DAMAGE_UNDEAD))
				enchantmentLevelUndead =  player.getItemInHand().getEnchantmentLevel(Enchantment.DAMAGE_UNDEAD);
			if(enchantmentLevelUndead >= enchantmentLevelAll) {
				baseEnchFactor = 0.5 * enchantmentLevelUndead;
			} else {
				baseEnchFactor = 0.5 * enchantmentLevelAll;
			}
		} else {
			if(player.getItemInHand().getEnchantments().containsKey(Enchantment.DAMAGE_ALL))
				enchantmentLevelAll =  player.getItemInHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL);
			baseEnchFactor = 0.5 * enchantmentLevelAll;
		}
		
		return baseEnchFactor;
	}
	
}