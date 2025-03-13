package org.community.monsterspawner.Cache;

import java.io.File;

import org.community.monsterspawner.MonsterSpawner;
import org.community.monsterspawner.Zones.Zones;
import org.community.monsterspawner.spawner.Spawner;

public class Cache {
	
	private MonsterSpawner plugin;
	
	public Cache(MonsterSpawner MonsterSpawner) {
		this.plugin = MonsterSpawner;
	}
	
	public void loadZonesList() {
		File dir = new File(plugin.getDataFolder() + "/zones/");
		String fileName;
		String tempName;
		File[] files = dir.listFiles();
		if (files != null) {
			for (int i = 0; i < files.length; i++) {
				if (files[i].isDirectory()) {
				}
				else {
					fileName = files[i].getName();
					tempName = fileName.substring(0, fileName.indexOf('.'));
					Zones zone = new Zones(plugin, tempName);
					plugin.LogInfo("initialized: Zone (" + zone.getZoneName() + ")");
				}
			}
		}
	}
	
	public void saveZonesList() {
		Zones tempZone;
		for(int i = 0; i < plugin.mSCore.getCompleteZonesList().size(); i++) {
			tempZone = plugin.mSCore.getZonesList(i);
			//tempGroup.saveToFile();
			plugin.LogDebug("saving zone " + tempZone.getZoneName());
		}
	}
	public void loadSpawnerList() {
		File dir = new File(plugin.getDataFolder() + "/spawner/");
		String fileName;
		String tempName;
		File[] files = dir.listFiles();
		if (files != null) {
			for (int i = 0; i < files.length; i++) {
				if (files[i].isDirectory()) {
				}
				else {
					fileName = files[i].getName();
					tempName = fileName.substring(0, fileName.indexOf('.'));
					Spawner spawner = new Spawner(plugin, tempName);
					plugin.LogInfo("initialized: Spawner (" + spawner.getSpawnerName() + ")");
				}
			}
		}
	}
	
	public void saveSpawnerList() {
		Spawner tempSpawner;
		for(int i = 0; i < plugin.mSCore.getCompleteSpawnerList().size(); i++) {
			tempSpawner = plugin.mSCore.getSpawnerList(i);
			//tempGroup.saveToFile();
			plugin.LogDebug("saving spawner " + tempSpawner.getSpawnerName());
		}
	}
			
}