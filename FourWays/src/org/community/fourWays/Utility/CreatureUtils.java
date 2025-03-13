package org.community.fourWays.Utility;

import java.util.List;
import java.util.Random;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.community.fourWays.fourWays;


public class CreatureUtils {

	private fourWays plugin;


	public CreatureUtils(fourWays plugin) {
		this.plugin = plugin;
	}

	public void initiateTools(){
		//plugin.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new updateDB(), plugin.config.getInt("Config.AnimalClean", 5) * 1200L, plugin.config.getInt("Config.AnimalClean", 5) * 1200L);
	}

	public class updateDB implements Runnable{

		public void run() {
			//plugin.getServer().broadcastMessage("" + System.currentTimeMillis());
			//int townCount = 0;
			//int wildCount = 0;
			List<LivingEntity> creatureList = plugin.getServer().getWorld(plugin.config.getString("Config.World")).getLivingEntities();
			for (int i = 0; i < creatureList.size(); i++){
				if(creatureList.get(i).getType() == EntityType.CHICKEN || creatureList.get(i).getType() == EntityType.COW || creatureList.get(i).getType() == EntityType.MUSHROOM_COW || creatureList.get(i).getType() == EntityType.PIG || creatureList.get(i).getType() == EntityType.SHEEP) {
					Entity entity = (Entity) creatureList.get(i);
					if(plugin.newSettlersAPI.nSCore.getChunkInfo(entity.getLocation().getWorld() + "," + entity.getLocation().getChunk().getX() + "," + entity.getLocation().getChunk().getZ()) != null) {
						//townCount++;
					} else {
						Random dice = new Random();
						int rndRoll = dice.nextInt(100 + 1);
						if(rndRoll <= plugin.config.getInt("Config.AnimalChance", 50)) {
							entity.remove();
						} else {
							//wildCount++;
						}						
					}
				}
			}
			//plugin.getServer().broadcastMessage("In Town: " + townCount + " Out Town: " + wildCount);
			//plugin.getServer().broadcastMessage("" + System.currentTimeMillis());
		}

	}
}