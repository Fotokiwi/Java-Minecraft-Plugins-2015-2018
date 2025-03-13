package org.community.DungeonUtility.SpawnLimiter;

import java.util.List;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.community.DungeonUtility.*;


public class spawnLimiterListenerItemSpawnEvent implements Listener {

	private DungeonUtility plugin;


	public spawnLimiterListenerItemSpawnEvent(DungeonUtility plugin) {
		this.plugin = plugin;
	}

	@EventHandler(priority = EventPriority.NORMAL)
	public void onItemSpawn(ItemSpawnEvent event) {

		Entity entity = event.getEntity();

		if(event.getEntityType() != EntityType.DROPPED_ITEM)
			return;

		Item rawItem= event.getEntity();
		

		String item = rawItem.getItemStack().getType().name();

		if(!item.equalsIgnoreCase("EGG"))
			return;

		int radius = plugin.config.getInt("Config.ItemMax.Radius", 32);
		int maximum = plugin.config.getInt("Config.ItemMax." + item, 16);   		

		List<Entity> entityList = entity.getNearbyEntities(radius, radius, radius);
		int itemCounter = 0;
		for(int i = 0; i < entityList.size(); i++) {
			if(entityList.get(i).getType().name().equalsIgnoreCase("DROPPED_ITEM")) {
				Item tempItem = (Item) entityList.get(i);
				if(tempItem.getItemStack().getType().name().equalsIgnoreCase("EGG")) {
					itemCounter++;
				}
			}
		}
		if(itemCounter > maximum) {
			event.setCancelled(true);
		}

	}
}
