package org.community.fourWays.Listener;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.community.fourWays.fourWays;


public class fourWaysItemSpawnEvent implements Listener{

	private fourWays plugin;

	public fourWaysItemSpawnEvent(fourWays plugin){
		this.plugin = plugin;
	}

	@EventHandler(priority = EventPriority.HIGH)
	public void onItemSpawn(ItemSpawnEvent event) {

		if(event.getEntityType() != EntityType.DROPPED_ITEM)
			return;

		Item rawItem= event.getEntity();		

		String item = rawItem.getItemStack().getType().name();
		
		if(event.getEntity().getItemStack().getType() == Material.GRAVEL || event.getEntity().getItemStack().getType() == Material.SAND) {
			if(event.getLocation().getBlock().getType() == Material.TORCH) {
				event.setCancelled(true);
				return;
			}
		}

		/*if(item.equalsIgnoreCase("WHEAT")){
			Block block = event.getEntity().getLocation().getBlock();
			int lightlevel = block.getLightLevel();

			if(lightlevel < 4){
				event.getEntity().remove();
				event.setCancelled(true);
				return;
			}
		}
		if(item.equalsIgnoreCase("SEEDS")){
			Block block = event.getEntity().getLocation().getBlock();
			int lightlevel = block.getLightLevel();
			if(lightlevel < 4){
				event.getEntity().remove();
				event.setCancelled(true);
				return;
			}
		}*/
		if(item.equalsIgnoreCase("BROWN_MUSHROOM")){
			if(event.getLocation().getBlock().getType() == Material.SNOW) {
				event.setCancelled(true);
				return;
			}
		}
		if(item.equalsIgnoreCase("RED_MUSHROOM")){
			if(event.getLocation().getBlock().getType() == Material.SNOW) {
				event.setCancelled(true);
				return;
			}
		}
		if(item.equalsIgnoreCase("SUGAR_CANE")){
			Block block = event.getEntity().getLocation().getBlock();
			int blockx = block.getX();
			int blockz = block.getZ();
			int blocky = block.getY();
			Location blockloc = block.getLocation();
			blockloc.setY(blocky-1);
			org.bukkit.Material caineblock = org.bukkit.Material.SUGAR_CANE_BLOCK;
			if(blockloc.getBlock().getType().equals(caineblock)){
				return;
			}			
			while(blockloc.getBlock().getType().equals(caineblock)){
				blockloc.setY(blocky-1);
			}
				
			blockloc.setX(blockx-1);
			
			if(blockloc.getBlock().getType().equals(Material.WATER))
				return;
			blockloc.setX(blockx+2);
			if(blockloc.getBlock().getType().equals(Material.WATER))
				return;
			blockloc.setX(blockx-1);
			blockloc.setZ(blockz-1);
			if(blockloc.getBlock().getType().equals(Material.WATER))
				return;
			blockloc.setZ(blockz+2);
			if(blockloc.getBlock().getType().equals(Material.WATER))
				return;
			else{
				event.getEntity().remove();
				event.setCancelled(true);
				return;
			}
		}
		if(item.equalsIgnoreCase("EGG")) {
			int radius = plugin.config.getInt("Config.ItemMax.Radius", 32);
			int maximum = plugin.config.getInt("Config.ItemMax." + item, 16);   		
	
			List<Entity> entityList = event.getEntity().getNearbyEntities(radius, radius, radius);
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
}