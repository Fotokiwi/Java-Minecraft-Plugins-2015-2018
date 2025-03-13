package org.community.monsterspawner.Tasks;

import org.bukkit.Bukkit;
import org.community.monsterspawner.MonsterSpawner;
import org.community.monsterspawner.Zones.Zones;
import org.community.monsterspawner.spawner.Spawner;
import org.bukkit.Chunk;

public class Heartbeat implements Runnable {

	private MonsterSpawner plugin;	

	private int monsterSpawnerHeartbeatTask = -1;


	public Heartbeat(MonsterSpawner monsterSpawner) {
		this.plugin = monsterSpawner;
	}


	public boolean isMonsterTaskRunning() {
		return monsterSpawnerHeartbeatTask != -1;
	}

	@Override
	public void run() {
        Chunk chunk;
		for(int i=0; i < plugin.mSCore.zoneList.size(); i++){
			Zones zone = plugin.mSCore.zoneList.get(i);
			chunk=Bukkit.getWorld(zone.getWorldName()).getChunkAt(zone.getChunkX(), zone.getChunkZ());
			if(chunk.isLoaded()){
				if(plugin.mSCore.zoneTime.containsKey(zone.getZoneName()) == false){
					plugin.mSCore.zoneTime.put(zone.getZoneName(), System.currentTimeMillis());
				}
				if(plugin.mSCore.zoneChunkLoaded.get(zone.getZoneName()) == false){
					zone.resetZoneTempData();
					plugin.mSCore.zoneChunkLoaded.put(zone.getZoneName(), true);
				}
				int playercount = zone.checkForPlayers();
				if(playercount > 0){
					for(int x=0; x<plugin.mSCore.spawnerList.size(); x++){
						Spawner spawner = plugin.mSCore.spawnerList.get(x);
						if(zone.getZoneName().equalsIgnoreCase(spawner.getZoneName())){
							if(playercount > 1){
								int spawnmulti = zone.scaleSpawn(playercount);
								int healthmulti = zone.scaleHealth(playercount);
								int damagemulti = zone.scaleDamage(playercount);
								if(zone.getDynamicScaleTrue()){
									spawner.putDynamicScale(spawnmulti);
								}
								if(zone.getDynamicHealthTrue()){
									spawner.putDynamicHealth(healthmulti);
								}
								if(zone.getDynamicDamageTrue()){
									spawner.putDynamicDamage(damagemulti);
								}
							}
							spawner.runSpawner();
						}
					}
				} else {
					zone.resetZoneTempData();
				}
			}
			else if(!Bukkit.getWorld(zone.getWorldName()).getChunkAt(zone.getChunkX(), zone.getChunkZ()).isLoaded()){
				if(plugin.mSCore.zoneChunkLoaded.get(zone.getZoneName())){
					zone.resetZoneTempData();
					plugin.mSCore.zoneChunkLoaded.put(zone.getZoneName(), false);
					if(plugin.mSCore.zoneTime.containsKey(zone.getZoneName())){
						plugin.mSCore.zoneTime.remove(zone.getZoneName());
					}
				}
			}
		}

	}

	public void toggleMonsterHeartBeat(boolean on) {		
		if (on && !isMonsterTaskRunning()) {
			monsterSpawnerHeartbeatTask = plugin.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Heartbeat(plugin), 100, 100);
			if (monsterSpawnerHeartbeatTask == -1) {
				plugin.LogError("error: Monster Spawner Task initialization failed!");
			} else {
				plugin.LogInfo("initialized: Monster Spawner Task");
			}

		} else if (!on && isMonsterTaskRunning()) {
			plugin.getServer().getScheduler().cancelTask(monsterSpawnerHeartbeatTask);
			monsterSpawnerHeartbeatTask = -1;
		}
	}

}