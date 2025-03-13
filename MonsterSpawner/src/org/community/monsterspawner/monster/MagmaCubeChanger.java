package org.community.monsterspawner.monster;

import java.util.UUID;

import net.minecraft.server.v1_7_R3.AttributeInstance;
import net.minecraft.server.v1_7_R3.AttributeModifier;
import net.minecraft.server.v1_7_R3.EntityInsentient;
import net.minecraft.server.v1_7_R3.GenericAttributes;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_7_R3.entity.CraftLivingEntity;
import org.bukkit.entity.Entity;
import org.bukkit.entity.MagmaCube;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.community.monsterspawner.MonsterSpawner;
import org.community.monsterspawner.spawner.Spawner;


public class MagmaCubeChanger {

	@SuppressWarnings("unused")
	private MonsterSpawner plugin;


	public MagmaCubeChanger(MonsterSpawner plugin) {
		this.plugin = plugin;
	}

	private static final UUID movementSpeedUID = UUID.fromString("206a89dc-ae78-4c4d-b42c-3b31db3f5a7c");
	private static final UUID maxHealthUID = UUID.fromString("206ab1d23c-aD78-41cd-b42c-3b31c43f5a7c");
	private static final UUID followrangeUID = UUID.fromString("224fa89dc-ae78-4c4d-b92c-3b31db3fd23c");
	private static final UUID knockbackResiUID = UUID.fromString("999a89dc-ae78-5c6d-b42c-3b31db3f5a7c");
	private static final UUID attackDamageUID = UUID.fromString("206a8666-aa75-4c4d-b42c-3b11db3f5a7c");
	
	@SuppressWarnings("unused")
	public void changeMagmacube(MagmaCube magmacube, Spawner spawner){


		try {			
			EntityEquipment equipment = magmacube.getEquipment();
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
			
			if(spawner.getslimeSize()>0){
				 magmacube.setSize(spawner.getslimeSize());  	
				}

			//Attribut Änderungen
			Entity entity = (Entity) magmacube;
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
				magmacube.addPotionEffect(new PotionEffect(PotionEffectType.getByName(spawner.getPotion()), 8640000, 1));
			}
			

			double skelx = magmacube.getLocation().getX();
			double skely = magmacube.getLocation().getY();
			double skelz = magmacube.getLocation().getZ();



		}
		catch (Throwable e) {
			e.printStackTrace();
			Bukkit.broadcastMessage("Fehler im Change MagmaCube");
		}
	}
}