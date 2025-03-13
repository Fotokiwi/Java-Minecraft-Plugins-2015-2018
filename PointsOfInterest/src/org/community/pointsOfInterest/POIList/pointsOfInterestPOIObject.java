package org.community.pointsOfInterest.POIList;

import org.bukkit.Location;

public class pointsOfInterestPOIObject {
	private boolean enabled; 
	private Location poiLocation;
	private String lore;
	
	public pointsOfInterestPOIObject(boolean enabled, Location poiLocation){
		this.setEnabled(enabled);
		this.setPoiLocation(poiLocation);
		lore = "";
	}
	
	public pointsOfInterestPOIObject(boolean enabled, Location poiLocation, String lore){
		this(enabled, poiLocation);
		this.setLore(lore);
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Location getPoiLocation() {
		return poiLocation;
	}

	public void setPoiLocation(Location poiLocation) {
		this.poiLocation = poiLocation;
	}

	public String getLore() {
		return lore;
	}

	public void setLore(String lore) {
		this.lore = lore;
	}

}
