package org.community.monsterspawner.monster;

import org.bukkit.Bukkit;
import org.bukkit.entity.Chicken;
import org.community.monsterspawner.MonsterSpawner;
import org.community.monsterspawner.spawner.Spawner;

public class ChickenChanger {

	@SuppressWarnings("unused")
	private MonsterSpawner plugin;

	public ChickenChanger(MonsterSpawner plugin) {
		this.plugin = plugin;
	}

	public void changeChicken(Chicken chicken, Spawner spawner){

		try {
			
		}
		catch (Throwable e) {
			e.printStackTrace();
			Bukkit.broadcastMessage("Fehler im Change Chicken");
		}
	}
}