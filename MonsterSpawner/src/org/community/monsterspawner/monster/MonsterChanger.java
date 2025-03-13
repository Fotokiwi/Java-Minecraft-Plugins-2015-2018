package org.community.monsterspawner.monster;

import org.bukkit.entity.Blaze;
import org.bukkit.entity.CaveSpider;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Ghast;
import org.bukkit.entity.Giant;
import org.bukkit.entity.MagmaCube;
import org.bukkit.entity.PigZombie;
import org.bukkit.entity.Silverfish;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Slime;
import org.bukkit.entity.Spider;
import org.bukkit.entity.Witch;
import org.bukkit.entity.Wither;
import org.bukkit.entity.Wolf;
import org.bukkit.entity.Zombie;
import org.community.monsterspawner.MonsterSpawner;
import org.community.monsterspawner.spawner.Spawner;

public class MonsterChanger {

	private MonsterSpawner plugin;

	public MonsterChanger(MonsterSpawner plugin){
		this.plugin = plugin;
	}

    // https://github.com/Bukkit/mc-dev/blob/master/net/minecraft/server/GenericAttributes.java
	// a=maxHealth, b=followRange, c=knockbackResistance, d=movementSpeed, e=attackDamage
	
	public void ChangeCreature (Entity entity, String spawnerName){

		for(int x=0; x<plugin.mSCore.spawnerList.size(); x++){
			Spawner spawner = plugin.mSCore.spawnerList.get(x);
			if(spawner.getSpawnerName().equalsIgnoreCase(spawnerName)){
				EntityType entType = entity.getType();
				if(entType==EntityType.SKELETON){
					Skeleton monster = (Skeleton) entity;
					plugin.mSChangeSkeleton.changeSkeleton(monster, spawner);
				}
				else if(entType==EntityType.ZOMBIE){
					Zombie monster = (Zombie) entity;
					plugin.mSChangeZombie.changeZombie(monster, spawner);
				}
				else if(entType==EntityType.PIG_ZOMBIE){
					PigZombie monster = (PigZombie) entity;
					plugin.mSChangePigZombie.changePigZombie(monster, spawner);
				}
				else if(entType==EntityType.WITCH){
					Witch monster = (Witch) entity;
					plugin.mSChangeWitch.changeWitch(monster, spawner);
				}
				else if(entType==EntityType.GIANT){
					Giant monster = (Giant) entity;
					plugin.mSChangeGiant.changeGiant(monster, spawner);
				}
				else if(entType==EntityType.CREEPER){
					Creeper monster = (Creeper) entity;
					plugin.mSChangeCreeper.changeCreeper(monster, spawner);
				}
				else if(entType==EntityType.WOLF){
					Wolf monster = (Wolf) entity;
					plugin.mSChangeWolf.changeWolf(monster, spawner);
				}
				else if(entType==EntityType.SILVERFISH){
					Silverfish monster = (Silverfish) entity;
					plugin.mSChangeSilverFish.changeSilverfish(monster, spawner);
				}
				else if(entType==EntityType.SPIDER){
					Spider monster = (Spider) entity;
					plugin.mSChangeSpider.changeSpider(monster, spawner);
				}
				else if(entType==EntityType.SLIME){
					Slime monster = (Slime) entity;
					plugin.mSChangeSlime.changeSlime(monster, spawner);
				}
				else if(entType==EntityType.MAGMA_CUBE){
					MagmaCube monster = (MagmaCube) entity;
					plugin.mSChangeMagmaCube.changeMagmacube(monster, spawner);
				}
				else if(entType==EntityType.BLAZE){
					Blaze monster = (Blaze) entity;
					plugin.mSChangeBlaze.changeBlaze(monster, spawner);
				}
				else if(entType==EntityType.CAVE_SPIDER){
					CaveSpider monster = (CaveSpider) entity;
					plugin.mSChangeCaveSpider.changeCaveSpider(monster, spawner);
				}
				else if(entType==EntityType.ENDER_DRAGON){
					EnderDragon monster = (EnderDragon) entity;
					plugin.mSChangeEnderDragon.changeEnderDragon(monster, spawner);
				}
				else if(entType==EntityType.ENDERMAN){
					Enderman monster = (Enderman) entity;
					plugin.mSChangeEnderman.changeEnderman(monster, spawner);
				}
				else if(entType==EntityType.GHAST){
					Ghast monster = (Ghast) entity;
					plugin.mSChangeGhast.changeGhast(monster, spawner);
				}
				else if(entType==EntityType.WITHER){
					Wither monster = (Wither) entity;
					plugin.mSChangeWither.changeWither(monster, spawner);
				}
				else if(entType==EntityType.CHICKEN){
					Chicken monster = (Chicken) entity;
					plugin.mSChangeChicken.changeChicken(monster, spawner);
				}

			}

		}
	}

}