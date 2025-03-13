package org.community.pointsOfInterest.Sign;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.community.DatabaseProvider.MySQL.MySQL;
import org.community.pointsOfInterest.pointsOfInterest;
import org.community.pointsOfInterest.pointsOfInterestUtility;
import org.community.pointsOfInterest.User.pointsOfInterestUserData;

public class pointsOfInterestSign {

	private final pointsOfInterest plugin;

	private List<pointsOfInterestSignObject> signLocations;

	public pointsOfInterestSign(pointsOfInterest plugin) {
		this.plugin = plugin;
		signLocations = new LinkedList<pointsOfInterestSignObject>();
	}

	public void saveToDB() {
		MySQL mysql = new MySQL();
		mysql.connect();
		mysql.insert("DELETE FROM POI_Signs");
		for(pointsOfInterestSignObject so : signLocations)
			mysql.insert("INSERT INTO POI_Signs (ID, UUID, Location) "
					+ "VALUES (NULL, \"" + so.toString() + "\", \"" + pointsOfInterestUtility.convertLocToString(so.getSignLocation())+ "\")");	
		mysql.disconnect();
	}

	public boolean loadFromDB() {
		MySQL mysql = new MySQL();
		mysql.connect();
		ResultSet rs = mysql.selectRS("SELECT UUID, Location FROM POI_Signs");
		if(rs != null)
		{
		try {
			while(rs.next()){
				signLocations.add(new pointsOfInterestSignObject(UUID.fromString(rs.getString("UUID")), pointsOfInterestUtility.convertStringToLoc(rs.getString("Location"))));
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		mysql.disconnect();
		return true;
	}
	
	public void addOneSign(UUID id, Location l){
		signLocations.add(new pointsOfInterestSignObject(id, l));
	}

	public void updateSigns() {
		List<pointsOfInterestSignObject> markedForRemoval = new LinkedList<pointsOfInterestSignObject>();
		for (pointsOfInterestSignObject poiSO : signLocations) {
			Block targetBlock = poiSO.getSignLocation().getBlock();

			if (targetBlock.getType() == Material.WALL_SIGN
					|| targetBlock.getType() == Material.SIGN_POST
					|| targetBlock.getType() == Material.SIGN) {
				pointsOfInterestUserData ud = plugin.poiUser.getOnePlayer(poiSO
						.getID());
				if (ud.getStufe() >= 5) {
					Sign schild = (Sign) targetBlock.getState();
					int anzahl = ud.getStufe();
					// TODO gesamtanzahl aus POIS auslesen einfügen
					int overall = plugin.poiPOIs.getNumberOfPOIs();
					schild.setLine(0, ChatColor.GREEN + ud.getSpielerName());
					schild.setLine(2, "Orte gefunden");
					schild.setLine(1, ChatColor.RED + "***" + ChatColor.GOLD
							+ anzahl + ChatColor.RED + "***");
					schild.setLine(3, "von: " + ChatColor.DARK_PURPLE + overall);
					schild.update(true);
				}
			} else
				markedForRemoval.add(poiSO);
		}
		signLocations.removeAll(markedForRemoval);
		saveToDB();
	}
}