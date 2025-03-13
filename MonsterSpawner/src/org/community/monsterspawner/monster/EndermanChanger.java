package org.community.monsterspawner.monster;



import java.util.List;
import java.util.UUID;

import net.minecraft.server.v1_7_R3.AttributeInstance;
import net.minecraft.server.v1_7_R3.AttributeModifier;
import net.minecraft.server.v1_7_R3.EntityInsentient;
import net.minecraft.server.v1_7_R3.GenericAttributes;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_7_R3.entity.CraftLivingEntity;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.community.monsterspawner.MonsterSpawner;
import org.community.monsterspawner.spawner.Spawner;

public class EndermanChanger {

	@SuppressWarnings("unused")
	private MonsterSpawner plugin;

	public EndermanChanger(MonsterSpawner plugin) {
		this.plugin = plugin;
	}

	private static final UUID movementSpeedUID = UUID.fromString("206a89dc-ae78-4c4d-b42c-3b31db3f5a7c");
	private static final UUID maxHealthUID = UUID.fromString("206eee23c-aD78-41cd-b42c-3b31e33f5a7c");
	private static final UUID followrangeUID = UUID.fromString("224fa89dc-ae78-4c4d-b92c-3b31db3fd23c");
	private static final UUID knockbackResiUID = UUID.fromString("999a89dc-ae78-5c6d-b42c-3b31db3f5a7c");
	private static final UUID attackDamageUID = UUID.fromString("206a8666-aeee-4c4d-b42c-3b31db3f5a7c");

	public void changeEnderman(Enderman endermen, Spawner spawner){
		EntityEquipment equipment = endermen.getEquipment();
		try {
			equipment.setItemInHandDropChance(0);
			equipment.setChestplateDropChance(0);
			equipment.setLeggingsDropChance(0);
			equipment.setHelmetDropChance(0);
			equipment.setBootsDropChance(0);

			if(!spawner.getHelmet().equalsIgnoreCase("none")){
				equipment.setHelmet(new ItemStack(Material.getMaterial(spawner.getHelmet().toUpperCase())));
			}
			if(!spawner.getBoots().equalsIgnoreCase("none")){
				equipment.setBoots(new ItemStack(Material.getMaterial(spawner.getBoots().toUpperCase())));
			}
			if(!spawner.getLeggings().equalsIgnoreCase("none")){
				equipment.setLeggings(new ItemStack(Material.getMaterial(spawner.getLeggings().toUpperCase())));
			}
			if(!spawner.getChest().equalsIgnoreCase("none")){
				equipment.setChestplate(new ItemStack(Material.getMaterial(spawner.getChest().toUpperCase())));
			}
			if(!spawner.getWeapon().equalsIgnoreCase("none")){
				equipment.setItemInHand(new ItemStack(Material.getMaterial(spawner.getWeapon().toUpperCase())));
			}

			//Attribut Änderungen
			Entity entity = (Entity) endermen;
			EntityInsentient nmsEntity = (EntityInsentient) ((CraftLivingEntity) entity).getHandle();

			if(spawner.getmoveSpeed()>0){

				AttributeInstance attributes = nmsEntity.getAttributeInstance(GenericAttributes.d);
				AttributeModifier modifier = new AttributeModifier(movementSpeedUID, "MonsterSpawner movement speed multiplier", spawner.getmoveSpeed(), 1);

				attributes.b(modifier);
				attributes.a(modifier);
			}
			if(spawner.getmaxHealth()>0){

				AttributeInstance attributes = nmsEntity.getAttributeInstance(GenericAttributes.a);
				AttributeModifier modifier = new AttributeModifier(maxHealthUID, "MonsterSpawner max Health multiplier", spawner.getmaxHealth(), 1);

				attributes.b(modifier);
				attributes.a(modifier);		

			}
			if(spawner.getDynamicHealth()>1){
				AttributeInstance attributes = nmsEntity.getAttributeInstance(GenericAttributes.a);
				AttributeModifier modifier = new AttributeModifier(maxHealthUID, "MonsterSpawner max Health multiplier", spawner.getDynamicHealth(), 1);

				attributes.b(modifier);
				attributes.a(modifier);	
			}
			if(spawner.getfollowRage()>0){

				AttributeInstance attributes = nmsEntity.getAttributeInstance(GenericAttributes.b);
				AttributeModifier modifier = new AttributeModifier(followrangeUID, "MonsterSpawner follow Range multiplier", spawner.getfollowRage(), 1);

				attributes.b(modifier);
				attributes.a(modifier);

			}
			if(spawner.getknockBackResi()>0){

				AttributeInstance attributes = nmsEntity.getAttributeInstance(GenericAttributes.c);
				AttributeModifier modifier = new AttributeModifier(knockbackResiUID, "MonsterSpawner knockback Resi multiplier", spawner.getknockBackResi(), 1);

				attributes.b(modifier);
				attributes.a(modifier);

			}
			if(spawner.getattackDamage()>0){

				AttributeInstance attributes = nmsEntity.getAttributeInstance(GenericAttributes.e);
				AttributeModifier modifier = new AttributeModifier(attackDamageUID, "MonsterSpawner attack Damage multiplier", spawner.getattackDamage(), 1);

				attributes.b(modifier);
				attributes.a(modifier);

			}
			if(spawner.getDynamicDamage()>1){
				AttributeInstance attributes = nmsEntity.getAttributeInstance(GenericAttributes.e);
				AttributeModifier modifier = new AttributeModifier(attackDamageUID, "MonsterSpawner attack Damage multiplier", spawner.getDynamicDamage(), 1);

				attributes.b(modifier);
				attributes.a(modifier);
			}

			if(!spawner.getPotion().equalsIgnoreCase("none")){
				endermen.addPotionEffect(new PotionEffect(PotionEffectType.getByName(spawner.getPotion()), 8640000, 1));
			}

			double skelx = endermen.getLocation().getX();
			double skely = endermen.getLocation().getY();
			double skelz = endermen.getLocation().getZ();
			Boolean found = false;
			for (int i = 0; i < 100; i++){
				List<Entity> entities = endermen.getNearbyEntities(skelx+i, skely+i, skelz+i);
				for (Entity e : entities) {
					if (e.getType().equals(EntityType.PLAYER)) {
						endermen.setTarget((Player) e);
						found = true;
						break;
					}
				}
				if (found) break;
			}
		}
		catch (Throwable e) {
			e.printStackTrace();
			Bukkit.broadcastMessage("Fehler im Change Enderman");
		}
	}
}