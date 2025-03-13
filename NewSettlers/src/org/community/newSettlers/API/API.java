package org.community.newSettlers.API;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.community.newSettlers.newSettlers;
import org.community.newSettlers.Town.Town;

public class API {
	
	private final newSettlers plugin;
 	 
	public API(newSettlers plugin)
	{
		this.plugin = plugin;
	}
	
	public Town getPlayerTown(Player player){		
		return plugin.nSCore.getPlayerTown(player);
	}
	
	public Town getChunkTown(Chunk chunk){	
		return plugin.nSCore.getChunkInfo(chunk.getWorld().getName() + "," + chunk.getX() + "," + chunk.getZ());
	}
	
	public boolean isPlayerInTown(Player player) {
		if(plugin.nSCore.getPlayerTown(player) != null) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean getBuildingStatus(Town town, String building){		
		return town.getBuildingStatus(building);
	}
	
	public boolean isBuildingInDistance(Town town, String building, Location location) {
		return town.isBuildingInDistance(building, location);		
	}
}