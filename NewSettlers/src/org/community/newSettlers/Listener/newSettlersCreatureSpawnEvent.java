package org.community.newSettlers.Listener;


import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.community.newSettlers.newSettlers;
import org.community.newSettlers.Town.Town;


public class newSettlersCreatureSpawnEvent implements Listener {	
	
	private newSettlers plugin;

	public newSettlersCreatureSpawnEvent(newSettlers plugin) {
		this.plugin = plugin;
	}
	
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void onCreatureSpawn(CreatureSpawnEvent event){
		
		Entity entity = event.getEntity();	
		
		//if(event.getEntity().getType() != EntityType.ZOMBIE && event.getEntity().getType() != EntityType.WOLF && event.getEntity().getType() != EntityType.WITCH && event.getEntity().getType() != EntityType.SPIDER && event.getEntity().getType() != EntityType.SLIME && event.getEntity().getType() != EntityType.SKELETON && event.getEntity().getType() != EntityType.SILVERFISH && event.getEntity().getType() != EntityType.PIG_ZOMBIE && event.getEntity().getType() != EntityType.ENDERMAN && event.getEntity().getType() != EntityType.CREEPER) {
		//	return;
		//}
		
		if(event.getSpawnReason() == SpawnReason.CUSTOM || event.getSpawnReason() == SpawnReason.BREEDING) {
			return;
		}	
		
		Town town = plugin.nSCore.getChunkInfo(entity.getLocation().getWorld().getName() + "," + entity.getLocation().getChunk().getX() + "," + entity.getLocation().getChunk().getZ());
		
		if(town == null)
			return;
		
		Boolean isProtected = town.hasTownGuard();
		
		if(isProtected == false)
			return;
		
		event.setCancelled(true);

	}
}