package org.community.newSettlers.ShipRoute;

import org.bukkit.Location;
import org.bukkit.World;

public class ZoneVector {
	
	private Location location;
	private World world;
	private int X;
	private int Y;
	private int Z;
	
	public ZoneVector() {
	}
	
	public ZoneVector(Location location) {
		this.location = location;
		this.world = location.getWorld();
		this.X = location.getBlockX();
		this.Y = location.getBlockY();
		this.Z = location.getBlockZ();
	}
	
	public Location getLocation() {
		return this.location;
	}
	
	public World getWorld() {
		return this.world;
	}
	
	public int getX() {
		return this.X;
	}
	
	public int getY() {
		return this.Y;
	}
	
	public int getZ() {
		return this.Z;
	}

}
