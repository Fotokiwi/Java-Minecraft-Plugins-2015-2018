package org.community.fourWays.Listener;

import java.util.List;
import java.util.Random;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.community.fourWays.fourWays;

public class fourWaysCreatureSpawnEvent implements Listener {
	
	@SuppressWarnings("unused")
	private fourWays plugin;

	public fourWaysCreatureSpawnEvent(fourWays plugin) {
		this.plugin = plugin;
	}
    
    @EventHandler(priority = EventPriority.NORMAL)
	public void onCreatureSpawn(CreatureSpawnEvent event) {
		if(event.getSpawnReason() == SpawnReason.SPAWNER){
			event.setCancelled(true);
			return;
		}
    	
    	if(event.getEntityType() != EntityType.CHICKEN)
			return;
		
		List<Entity> entityList = event.getEntity().getNearbyEntities(48, 40, 48);
		double n = 0;
		
		for(int i = 0; i < entityList.size(); i++) {
			if(entityList.get(i).getType() == EntityType.CHICKEN) {
				n++;
			}
		}
		if(n == 0)
			return;
		
		double chance = (double) ((6 / n) - 0.05) * 100;
		
		Random dice = new Random();
		int rndRoll = dice.nextInt(100 + 1);
		
		if(rndRoll >= chance) {
			event.setCancelled(true);
			//event.getEntity().getServer().broadcastMessage("Fail! " + chance + " - " + rndRoll + "(n:" + n + ")");
		} else {
			//event.getEntity().getServer().broadcastMessage("Grats! " + chance + " - " + rndRoll + "(n:" + n + ")");
		}		
	}
	
}