package org.community.pointsOfInterest.Sign;

import java.util.UUID;

import org.bukkit.Location;

public class pointsOfInterestSignObject {
	private UUID id;
	private Location signLocation;
	
	public pointsOfInterestSignObject(UUID id, Location l){
		this.id = id;
		signLocation = l;
	}
	
	public UUID getID(){
		return id;
	}
	
	public void setID(UUID id){
		this.id = id;
	}
	
	public Location getSignLocation(){
		return signLocation;
	}
	
	public void setSignLocation(Location l){
		this.signLocation = l;
	}

}

