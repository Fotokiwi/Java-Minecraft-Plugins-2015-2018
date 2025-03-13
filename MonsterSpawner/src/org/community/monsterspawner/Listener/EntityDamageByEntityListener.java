package org.community.monsterspawner.Listener;

import java.util.UUID;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.community.monsterspawner.MonsterSpawner;
import org.community.monsterspawner.spawner.Spawner;


public class EntityDamageByEntityListener implements Listener {

	private MonsterSpawner plugin;

	public EntityDamageByEntityListener(MonsterSpawner plugin){
		this.plugin = plugin;
	}

	@EventHandler(priority = EventPriority.LOW)
	public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {

		if(!(event.getDamager() instanceof Player)){
			if(event.getEntity() instanceof Player){
				Entity entity = event.getDamager();
				Player player = (Player) event.getEntity();
				UUID MonsterUUID = entity.getUniqueId();
				if(!plugin.mSCore.monsterKey.containsKey(MonsterUUID)){
					return;
				}
				String spawnerName = plugin.mSCore.monsterKey.get(MonsterUUID);
				for(int x=0; x<plugin.mSCore.spawnerList.size(); x++){
					Spawner spawner = plugin.mSCore.spawnerList.get(x);
					if(spawner.getSpawnerName().equalsIgnoreCase(spawnerName)){
						if(!spawner.getfireDamage()&&!spawner.getpoisonDamage()&&!spawner.getslowDamage()&&!spawner.getwitherDamage()){
							return;
						}
						if(spawner.getfireDamage()){
							player.setFireTicks(150);
						}
						if(spawner.getpoisonDamage()){
							player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 80, 2));
						}
						if(spawner.getslowDamage()){
							player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 40, 5));
						}
						if(spawner.getwitherDamage()){
							player.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 60, 2));
						}
							
					}
				}

			}
		}


	}


}
