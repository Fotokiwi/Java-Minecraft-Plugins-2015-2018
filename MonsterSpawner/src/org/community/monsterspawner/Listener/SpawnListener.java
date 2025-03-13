package org.community.monsterspawner.Listener;

import java.util.UUID;

import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.community.monsterspawner.MonsterSpawner;

public class SpawnListener implements Listener {
	
	private MonsterSpawner plugin;
	
	public SpawnListener(MonsterSpawner plugin){
		this.plugin = plugin;
	}
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void onCreatureSpawn(CreatureSpawnEvent event){
		
		Entity entity = event.getEntity();
		int spawnX = (int) entity.getLocation().getBlockX();
		int spawnY = (int) entity.getLocation().getBlockY();
		int spawnZ = (int) entity.getLocation().getBlockZ();
		String spawnID = spawnX + "," + spawnY + "," + spawnZ;
		if(plugin.mSCore.spawnerLocs.containsKey(spawnID)){
			String spawnerName = plugin.mSCore.spawnerLocs.get(spawnID);
			UUID monsterID = entity.getUniqueId();
			int anzahl = 0;
			if(plugin.mSCore.monsterLimit.containsKey(spawnerName)){
				anzahl = plugin.mSCore.monsterLimit.get(spawnerName);
			}
			anzahl = anzahl + 1;
			plugin.mSCore.monsterLimit.put(spawnerName, anzahl);
			plugin.mSCore.spawnerTime.put(spawnerName, System.currentTimeMillis());
			plugin.mSCore.monsterKey.put(monsterID, spawnerName);
			plugin.mSMonsterChange.ChangeCreature(entity, spawnerName);
		}
	}
	
}