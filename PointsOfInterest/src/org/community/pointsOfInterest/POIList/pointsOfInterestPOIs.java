package org.community.pointsOfInterest.POIList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.Location;
import org.community.DatabaseProvider.MySQL.MySQL;
import org.community.pointsOfInterest.pointsOfInterest;
import org.community.pointsOfInterest.pointsOfInterestUtility;

public class pointsOfInterestPOIs {

	private final pointsOfInterest plugin;
	private Map<String, pointsOfInterestPOIObject> poiLocations;
	private int numberOfPOIs;

	public pointsOfInterestPOIs(pointsOfInterest plugin) {
		this.plugin = plugin;
		poiLocations = new HashMap<String, pointsOfInterestPOIObject>();
		numberOfPOIs = 0;
	}


	public boolean loadFromDB() {

		MySQL mysql = new MySQL();
		mysql.connect();
		ResultSet rs = mysql.selectRS("SELECT * FROM POI_Pois");
		if(rs != null)
		{
		int rowCount = 0;
		try {
			while(rs.next()){
				rowCount++;
				poiLocations.put(rs.getString("POIName"), new pointsOfInterestPOIObject(true, pointsOfInterestUtility.convertStringToLoc(rs.getString("Location")), rs.getString("Lore")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		numberOfPOIs = rowCount;
		}
		mysql.disconnect();
		
		return true;

	}
	

	
	public void saveToDB() {
		MySQL mysql = new MySQL();
		mysql.connect();
		mysql.insert("DELETE FROM POI_Pois");
		for(Entry<String, pointsOfInterestPOIObject> e : poiLocations.entrySet()){
				mysql.insert("INSERT INTO POI_Pois (ID, POIName, Location, Lore) "
					+ "VALUES (NULL, \""+ e.getKey() + "\", \"" + pointsOfInterestUtility.convertLocToString(e.getValue().getPoiLocation()) + "\", \"" + e.getValue().getLore() + "\")");
		}
		mysql.disconnect();
	}

	public void addPOI(String name, Location l) {

		getPoiLocations().put(name, new pointsOfInterestPOIObject(true, l));
		numberOfPOIs += 1;
	}
	
	public void addPOI(String name, Location l, String lore) {

		getPoiLocations().put(name, new pointsOfInterestPOIObject(true, l, lore));
		numberOfPOIs += 1;
	}
	
	/**
	 * Entfernt den POI auch aus allen Spielern
	 * @param Name des POIs
	 */
	public void removePOI(String name) {

		getPoiLocations().remove(name);
		numberOfPOIs -= 1;
		plugin.poiUser.removeOnePOI(name);
	}
	
	public boolean containsPOI(String poiName){
		return poiLocations.containsKey(poiName);
	}


	public int getNumberOfPOIs() {
		return numberOfPOIs;
	}


	public void setNumberOfPOIs(int numberOfPOIs) {
		this.numberOfPOIs = numberOfPOIs;
	}


	public Map<String, pointsOfInterestPOIObject> getPoiLocations() {
		return poiLocations;
	}

	public pointsOfInterestPOIObject getOnePOI(String poiName){
		return poiLocations.get(poiName);
	}

}