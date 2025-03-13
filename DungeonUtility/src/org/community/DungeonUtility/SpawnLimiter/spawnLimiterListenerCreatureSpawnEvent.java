package org.community.DungeonUtility.SpawnLimiter;


import java.util.ArrayList;
import java.util.List;

import org.bukkit.Chunk;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Sheep;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.community.DungeonUtility.*;


public class spawnLimiterListenerCreatureSpawnEvent implements Listener {	
	
	private DungeonUtility plugin;

	public spawnLimiterListenerCreatureSpawnEvent(DungeonUtility plugin) {
		this.plugin = plugin;
	}
	
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onCreatureSpawn(CreatureSpawnEvent event){
		
		Entity entity = event.getEntity();	
		
		if(entity.getType() == EntityType.SHEEP) {
			Sheep sheep = (Sheep) entity;
			sheep.setColor(DyeColor.WHITE);
		}
		
		//plugin.getServer().broadcastMessage(event.getSpawnReason().name());
		
		if(event.getSpawnReason() == SpawnReason.CUSTOM || event.getSpawnReason() == SpawnReason.BREEDING) {
			return;
		}
		
		if(event.getEntity().getWorld().getName().equalsIgnoreCase("Dungeons")) {
			event.setCancelled(true);
		}

		if(entity.getType() == EntityType.ENDER_DRAGON || entity.getType() == EntityType.WITHER || entity.getType() == EntityType.GHAST || entity.getType() == EntityType.ENDERMAN || entity.getType() == EntityType.PIG_ZOMBIE){
			event.setCancelled(true);
			return;
		}
		
		Material tempBlock = entity.getLocation().add(0, -1, 0).getBlock().getType();
		if(tempBlock == Material.LEAVES || tempBlock == Material.LEAVES_2) {
			event.setCancelled(true);
			return;
		}
		
		if(entity.getType() == EntityType.SKELETON || entity.getType() == EntityType.ZOMBIE) {
			if(entity.getLocation().getBlock().getLightFromSky() < 4) {
				event.setCancelled(true);
				return;
			}
		}
		
		if(entity.getType() == EntityType.CREEPER) {
			if(entity.getLocation().getBlock().getLightFromSky() > 12) {
				event.setCancelled(true);
				return;
			}
		}
		
		if(entity.getType() == EntityType.SPIDER) {
			if(entity.getLocation().getBlock().getLightFromSky() > 8) {
				event.setCancelled(true);
				return;
			}
		}
		
		if(entity.getType() == EntityType.WITCH) {
			if(entity.getLocation().getBlock().getBiome() != Biome.SWAMPLAND && entity.getLocation().getBlock().getBiome() != Biome.SWAMPLAND_MOUNTAINS) {
				event.setCancelled(true);
				return;
			}
		}
		
		if(entity.getType() == EntityType.SLIME) {
			if(entity.getLocation().getBlock().getLightLevel() > 0 || entity.getLocation().getY() > 35) {
				event.setCancelled(true);
				return;
			}
		}

		
		int radius = plugin.config.getInt("Config.Fix", 20);
		plugin.radius = radius;      		

		if(entity.getType() == EntityType.CREEPER || entity.getType() == EntityType.SKELETON || entity.getType() == EntityType.SPIDER || entity.getType() == EntityType.ZOMBIE || entity.getType() == EntityType.ENDERMAN || entity.getType() == EntityType.SLIME) {
			List<Entity> entityCountList = entity.getNearbyEntities(radius, 24, radius);
			int entityCounter = 0;
			for(int i = 0; i < entityCountList.size(); i++) {
				if(entityCountList.get(i).getType() != EntityType.PLAYER) {
					entityCounter++;
				}
			}
			if(entityCounter >= 1)
				event.setCancelled(true);
		} else if(entity.getType() == EntityType.WOLF || entity.getType() == EntityType.OCELOT) {
			List<Entity> entityList = entity.getNearbyEntities(plugin.config.getInt("Config.PlayerBased.Settings.Tamable", 80), 40, plugin.config.getInt("Config.PlayerBased.Settings.Tamable", 80));
			int tamableCounter = 0;
			for(int i = 0; i < entityList.size(); i++) {
				if(entityList.get(i).getType() == EntityType.WOLF || entityList.get(i).getType() == EntityType.OCELOT) {
					tamableCounter++;
				}
			}
			if(tamableCounter > 2)
				event.setCancelled(true);
		} else if(entity.getType() == EntityType.SQUID) {
			List<Entity> entityList = entity.getNearbyEntities(plugin.config.getInt("Config.PlayerBased.Settings.Water", 80), 40, plugin.config.getInt("Config.PlayerBased.Settings.Water", 80));
			int aquaCounter = 0;
			for(int i = 0; i < entityList.size(); i++) {
				if(entityList.get(i).getType() == EntityType.SQUID) {
					aquaCounter++;
				}
			}
			if(aquaCounter > 2)
				event.setCancelled(true);
		} else if(entity.getType() == EntityType.SHEEP || entity.getType() == EntityType.COW || entity.getType() == EntityType.PIG || entity.getType() == EntityType.CHICKEN || entity.getType() == EntityType.HORSE) {
			List<Entity> entityList = entity.getNearbyEntities(plugin.config.getInt("Config.PlayerBased.Settings.Tamable", 80), 40, plugin.config.getInt("Config.PlayerBased.Settings.Tamable", 80));
			int passiveCounter = 0;
			for(int i = 0; i < entityList.size(); i++) {
				if(entity.getType() == EntityType.SHEEP || entity.getType() == EntityType.COW || entity.getType() == EntityType.PIG || entity.getType() == EntityType.CHICKEN || entity.getType() == EntityType.HORSE) {
					passiveCounter++;
				}
			}
			if(passiveCounter >= 2)
				event.setCancelled(true);
		} else {
			return;
		}
		
		/*if(plugin.config.getString("Config.Mode", "player").equalsIgnoreCase("player")) {

			int radius = plugin.config.getInt("Config.PlayerBased.Minimum", 5);
			int activePlayers = 0;

			if(System.currentTimeMillis() >= plugin.timestamp + plugin.config.getInt("Config.Cache", 15000)) {
				plugin.activePlayers = entity.getServer().getOnlinePlayers().length;
				plugin.timestamp = System.currentTimeMillis();
				//plugin.getServer().broadcastMessage("Active Players: " + plugin.activePlayers);
			}

			activePlayers = plugin.activePlayers;

			if(activePlayers > radius)
				radius = activePlayers;
			radius = radius + plugin.config.getInt("Config.PlayerBased.Offset", 5);
			if(radius > plugin.config.getInt("Config.PlayerBased.Maximum", 30))
				radius = plugin.config.getInt("Config.PlayerBased.Maximum", 5);
			plugin.radius = radius;

			if(entity.getType() == EntityType.CREEPER || entity.getType() == EntityType.SKELETON || entity.getType() == EntityType.SPIDER || entity.getType() == EntityType.ZOMBIE || entity.getType() == EntityType.ENDERMAN || entity.getType() == EntityType.SLIME) {
				List<Entity> entityCountList = entity.getNearbyEntities(radius, 24, radius);    			
				int entityCounter = 0;
				for(int i = 0; i < entityCountList.size(); i++) {
					if(entityCountList.get(i).getType() != EntityType.PLAYER) {
						entityCounter++;
					}
				}
				if(entityCounter >= 1)
					event.setCancelled(true);
			} else if(entity.getType() == EntityType.WOLF || entity.getType() == EntityType.OCELOT) {
				List<Entity> entityList = entity.getNearbyEntities(plugin.config.getInt("Config.PlayerBased.Settings.Tamable", 80), 40, plugin.config.getInt("Config.PlayerBased.Settings.Tamable", 80));
				int tamableCounter = 0;
				for(int i = 0; i < entityList.size(); i++) {
					if(entityList.get(i).getType() == EntityType.WOLF || entityList.get(i).getType() == EntityType.OCELOT) {
						tamableCounter++;
					}
				}
				if(tamableCounter > 2)
					event.setCancelled(true);
			} else if(entity.getType() == EntityType.SQUID) {
				List<Entity> entityList = entity.getNearbyEntities(plugin.config.getInt("Config.PlayerBased.Settings.Water", 80), 40, plugin.config.getInt("Config.PlayerBased.Settings.Water", 80));
				int aquaCounter = 0;
				for(int i = 0; i < entityList.size(); i++) {
					if(entityList.get(i).getType() == EntityType.SQUID) {
						aquaCounter++;
					}
				}
				if(aquaCounter > 2)
					event.setCancelled(true);
			} else {
				return;
			}
			return;

		} else if(plugin.config.getString("Config.Mode", "player").equalsIgnoreCase("chunk")) {

			int radius = plugin.config.getInt("Config.PlayerBased.Minimum", 5);
			int activeChunks = 0;

			if(System.currentTimeMillis() >= plugin.timestamp + plugin.config.getInt("Config.Cache", 15000)) {
				plugin.activeChunks = getActiveChunks(entity.getWorld());
				plugin.timestamp = System.currentTimeMillis();
				//plugin.getServer().broadcastMessage("Loaded Chunks: " + entity.getWorld().getLoadedChunks().length);
				//plugin.getServer().broadcastMessage("Active Chunks: " + plugin.activeChunks);
			}

			activeChunks = plugin.activeChunks;

			String[] chunkKeysArray = null;

			ConfigurationSection chunkSection = plugin.config.getConfigurationSection("Config.ChunkBased");
			Set<String> chunkKeys = chunkSection.getKeys(false);
			chunkKeysArray = chunkKeys.toArray(new String[0]);  

			for(int i = 0; i < chunkKeys.size(); i++) {
				if(activeChunks >= new Integer(chunkKeysArray[i])) {
					radius = plugin.config.getInt("Config.ChunkBased." + chunkKeysArray[i], 5);
				}
			}
			plugin.radius = radius;      		

			if(entity.getType() == EntityType.CREEPER || entity.getType() == EntityType.SKELETON || entity.getType() == EntityType.SPIDER || entity.getType() == EntityType.ZOMBIE || entity.getType() == EntityType.ENDERMAN || entity.getType() == EntityType.SLIME) {
				List<Entity> entityCountList = entity.getNearbyEntities(radius, 24, radius);
				int entityCounter = 0;
				for(int i = 0; i < entityCountList.size(); i++) {
					if(entityCountList.get(i).getType() != EntityType.PLAYER) {
						entityCounter++;
					}
				}
				if(entityCounter >= 1)
					event.setCancelled(true);
			} else if(entity.getType() == EntityType.COW || entity.getType() == EntityType.PIG || entity.getType() == EntityType.CHICKEN || entity.getType() == EntityType.SHEEP || entity.getType() == EntityType.HORSE) {
				List<Entity> entityCountList = entity.getNearbyEntities(64, 24, 64);
				int entityCounter = 0;
				for(int i = 0; i < entityCountList.size(); i++) {
					if(entityCountList.get(i).getType() != EntityType.PLAYER) {
						entityCounter++;
					}
				}
				if(entityCounter >= 1)
					event.setCancelled(true);
			} else if(entity.getType() == EntityType.WOLF || entity.getType() == EntityType.OCELOT) {
				List<Entity> entityList = entity.getNearbyEntities(plugin.config.getInt("Config.PlayerBased.Settings.Tamable", 80), 40, plugin.config.getInt("Config.PlayerBased.Settings.Tamable", 80));
				int tamableCounter = 0;
				for(int i = 0; i < entityList.size(); i++) {
					if(entityList.get(i).getType() == EntityType.WOLF || entityList.get(i).getType() == EntityType.OCELOT) {
						tamableCounter++;
					}
				}
				if(tamableCounter > 2)
					event.setCancelled(true);
			} else if(entity.getType() == EntityType.SQUID) {
				List<Entity> entityList = entity.getNearbyEntities(plugin.config.getInt("Config.PlayerBased.Settings.Water", 80), 40, plugin.config.getInt("Config.PlayerBased.Settings.Water", 80));
				int aquaCounter = 0;
				for(int i = 0; i < entityList.size(); i++) {
					if(entityList.get(i).getType() == EntityType.SQUID) {
						aquaCounter++;
					}
				}
				if(aquaCounter > 2)
					event.setCancelled(true);
			} else {
				return;
			}
			return;

		} else if(plugin.config.getString("Config.Mode", "player").equalsIgnoreCase("fix")) {

			int radius = plugin.config.getInt("Config.Fix", 20);
			plugin.radius = radius;      		

			if(entity.getType() == EntityType.CREEPER || entity.getType() == EntityType.SKELETON || entity.getType() == EntityType.SPIDER || entity.getType() == EntityType.ZOMBIE || entity.getType() == EntityType.ENDERMAN || entity.getType() == EntityType.SLIME) {
				List<Entity> entityCountList = entity.getNearbyEntities(radius, 24, radius);
				int entityCounter = 0;
				for(int i = 0; i < entityCountList.size(); i++) {
					if(entityCountList.get(i).getType() != EntityType.PLAYER) {
						entityCounter++;
					}
				}
				if(entityCounter >= 1)
					event.setCancelled(true);
			} else if(entity.getType() == EntityType.WOLF || entity.getType() == EntityType.OCELOT) {
				List<Entity> entityList = entity.getNearbyEntities(plugin.config.getInt("Config.PlayerBased.Settings.Tamable", 80), 40, plugin.config.getInt("Config.PlayerBased.Settings.Tamable", 80));
				int tamableCounter = 0;
				for(int i = 0; i < entityList.size(); i++) {
					if(entityList.get(i).getType() == EntityType.WOLF || entityList.get(i).getType() == EntityType.OCELOT) {
						tamableCounter++;
					}
				}
				if(tamableCounter > 2)
					event.setCancelled(true);
			} else if(entity.getType() == EntityType.SQUID) {
				List<Entity> entityList = entity.getNearbyEntities(plugin.config.getInt("Config.PlayerBased.Settings.Water", 80), 40, plugin.config.getInt("Config.PlayerBased.Settings.Water", 80));
				int aquaCounter = 0;
				for(int i = 0; i < entityList.size(); i++) {
					if(entityList.get(i).getType() == EntityType.SQUID) {
						aquaCounter++;
					}
				}
				if(aquaCounter > 2)
					event.setCancelled(true);
			} else {
				return;
			}
			return;

		} else {
			return;
		}	*/	

	}
	
	public int getActiveChunks(World world) {
	 
	  ArrayList<Chunk> chunks = new ArrayList<Chunk>();
	 
	  for (Chunk c : world.getLoadedChunks())
	  {
	    if (hasLivingEntities(c))
	        chunks.add(c);
	  }
	  
	  return chunks.size();
	 
	}
	 
	public boolean hasLivingEntities(Chunk chunk) {
	   
	    for (Entity e : chunk.getEntities())
	    {
	      if (e instanceof LivingEntity)
	          return true;
	    }
	 
	    return false;
	 
	}
}