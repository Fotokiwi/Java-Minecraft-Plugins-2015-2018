package org.community.monsterspawner.Listener;

import java.util.UUID;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.community.monsterspawner.MonsterSpawner;
import org.community.monsterspawner.spawner.Spawner;

public class CreatureDeathListener implements Listener {

	private MonsterSpawner plugin;

	public CreatureDeathListener(MonsterSpawner plugin){
		this.plugin = plugin;
	}

	@EventHandler(priority = EventPriority.NORMAL)
	public void onCreatureDeath(EntityDeathEvent event) {

		Entity ent = event.getEntity();
		EntityType entity = ent.getType();
		UUID entityID = event.getEntity().getUniqueId();
		if(plugin.mSCore.monsterKey.containsKey(entityID)){
			String spawnerName = plugin.mSCore.monsterKey.get(entityID);
			for(int x=0; x<plugin.mSCore.spawnerList.size(); x++){
				Spawner spawner = plugin.mSCore.spawnerList.get(x);
				if(spawner.getSpawnerName().equalsIgnoreCase(spawnerName)){
					if(spawner.getexplodeOnDeath()){
						ent.getWorld().createExplosion(ent.getLocation().getX(), ent.getLocation().getY(), ent.getLocation().getZ(), 3F, true, false);
					}
					if(spawner.getslimeSize()>0 && spawner.getMonsterName().equalsIgnoreCase("slime")){
						for(int g=0;g<=spawner.getslimeSize();g++){
							ent.getWorld().spawnEntity(ent.getLocation(), EntityType.SLIME);
						}
					}
					if(spawner.getslimeSize()>0 && spawner.getMonsterName().equalsIgnoreCase("magma_cube")){
						for(int h=0;h<=spawner.getslimeSize();h++){
							ent.getWorld().spawnEntity(ent.getLocation(), EntityType.MAGMA_CUBE);
						}
					}

				}
			}
			plugin.mSCore.monsterKey.remove(entityID);
			return;
		}
		else if(entity==EntityType.IRON_GOLEM){
			event.getDrops().clear();
		}
		else if(entity==EntityType.SNOWMAN){
			event.getDrops().clear();
		}
		/**TODO
		 * restliche Drops klären
		 */


	}



}