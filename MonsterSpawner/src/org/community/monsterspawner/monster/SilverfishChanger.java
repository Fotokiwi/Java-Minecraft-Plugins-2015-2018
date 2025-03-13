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
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Silverfish;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.community.monsterspawner.MonsterSpawner;
import org.community.monsterspawner.spawner.Spawner;


public class SilverfishChanger {

	@SuppressWarnings("unused")
	private MonsterSpawner plugin;


	public SilverfishChanger(MonsterSpawner plugin) {
		this.plugin = plugin;
	}
	
	private static final UUID movementSpeedUID = UUID.fromString("206a89dc-ae78-4c4d-b42c-3b31db3f5a7c");
	private static final UUID maxHealthUID = UUID.fromString("206aA6d23c-aD78-41cd-b42c-3b31533f5a7c");
	private static final UUID followrangeUID = UUID.fromString("224fa89dc-ae78-4c4d-b92c-3b31db3fd23c");
	private static final UUID knockbackResiUID = UUID.fromString("999a89dc-ae78-5c6d-b42c-3b31db3f5a7c");
	private static final UUID attackDamageUID = UUID.fromString("206a8666-a475-4c4d-b42c-3b33db3f5a7c");
	
	public void changeSilverfish(Silverfish silverfish, Spawner spawner){

		EntityEquipment equipment = silverfish.getEquipment();

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
			Entity entity = (Entity) silverfish;
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
				silverfish.addPotionEffect(new PotionEffect(PotionEffectType.getByName(spawner.getPotion()), 8640000, 1));
			}
			

			double skelx = silverfish.getLocation().getX();
			double skely = silverfish.getLocation().getY();
			double skelz = silverfish.getLocation().getZ();
			Boolean found = false;

			for (int i = 0; i < 100; i++){
				List<Entity> entities = silverfish.getNearbyEntities(skelx+i, skely+i, skelz+i);
				for (Entity e : entities) {
					if (e.getType().equals(EntityType.PLAYER)) {
						silverfish.setTarget((Player) e);
						found = true;
						break;
					}
				}
				if (found) break;
			}
		}
		catch (Throwable e) {
			e.printStackTrace();
			Bukkit.broadcastMessage("Fehler im Change Silverfish");
		}
	}
}