package org.community.pointsOfInterest.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.community.pointsOfInterest.pointsOfInterest;
import org.community.DatabaseProvider.MySQL.MySQL;

public class pointsOfInterestUser {

	@SuppressWarnings("unused")
	private final pointsOfInterest plugin;

	private Map<UUID, pointsOfInterestUserData> userdata;

	public pointsOfInterestUser(pointsOfInterest plugin) {
		this.plugin = plugin;
		setUserdata(new HashMap<UUID, pointsOfInterestUserData>());
	}

	public void saveToDB() {
		MySQL mysql = new MySQL();
		mysql.connect();
		mysql.insert("DELETE FROM POI_VisitedPOI");
		mysql.insert("DELETE FROM POI_User");
		for (Entry<UUID, pointsOfInterestUserData> e : userdata.entrySet()) {

			mysql.insert("INSERT INTO POI_User (ID, UUID, AnzahlPOIs, "
					+ "Spielername, Cooldown) " + "VALUES (NULL,\""
					+ e.getKey().toString() + "\", "
					+ e.getValue().getAnzahlBesuchterPOIs() + ",\""
					+ e.getValue().getSpielerName() + "\", "
					+ e.getValue().getCooldown() + ")");
			for (String poiName : e.getValue().getBesuchtePOIs())
				mysql.insert("INSERT INTO POI_VisitedPOI (ID, UUID, POIName) VALUES (NULL, \""
						+ e.getKey().toString() + "\",\"" + poiName + "\")");
		}

		mysql.disconnect();
	}

	public boolean loadFromDB() {
		MySQL mysql = new MySQL();
		mysql.connect();
		ResultSet rs = mysql.selectRS("SELECT * FROM POI_User");
		if (rs != null) {
			try {
				while (rs.next()) {
					pointsOfInterestUserData ud = new pointsOfInterestUserData(
							rs.getString("Spielername"),
							rs.getInt("AnzahlPOIs"), rs.getLong("Cooldown"));

					ResultSet rs2 = mysql
							.selectRS("SELECT POIName FROM POI_VisitedPOI WHERE (POI_VisitedPOI.UUID = \""
									+ rs.getString("UUID") + "\")");
					List<String> l = new LinkedList<String>();


					if (rs2 != null) {
						while (rs2.next()) {

							l.add(rs2.getString("POIName"));
						}
					}

					ud.setBesuchtePOIs(l);
					userdata.put(UUID.fromString(rs.getString("UUID")), ud);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		mysql.disconnect();
		return true;
	}

	/**
	 * 
	 * Keine Überprüfung auf null !
	 * 
	 */
	public pointsOfInterestUserData getOnePlayer(UUID id) {
		return getUserdata().get(id);
	}

	public pointsOfInterestUserData getOnePlayer(Player player) {
		return getUserdata().get(player.getUniqueId());
	}

	/**
	 * Berechnet Stufe und Rang aller Spieler neu.
	 */
	public void updateAllUser() {

		for (Entry<UUID, pointsOfInterestUserData> e : getUserdata().entrySet()) {
			e.getValue().updateRang();
		}

		saveToDB();
	}

	/**
	 * Berechnet Stufe und Rang des Spielers neu.
	 * 
	 * @param UUID
	 *            des Spielers, der geupdatet werden soll
	 * 
	 */
	public void updateOneUser(UUID id) {
		pointsOfInterestUserData ud = getUserdata().get(id);
		if (ud == null)
			return;
		ud.updateRang();
		saveToDB();
	}

	public void addOnePOIToOneUser(Player player, String name) {
		addOnePOIToOneUser(player.getUniqueId(), name);
	}

	public void addOnePOIToOneUser(UUID id, String name) {
		pointsOfInterestUserData ud = getUserdata().get(id);
		if (ud == null)
			return;
		ud.besuchtenPOIhinzufuegen(name);
		saveToDB();
	}

	/**
	 * Entfernt einen POI bei allen Spielern und berechnet anschließend alle
	 * Ränge und Stufen neu
	 * 
	 * @param Name
	 *            des POIs, der bei allen Spielern entfernt werden soll
	 */
	public void removeOnePOI(String poiName) {

		for (Entry<UUID, pointsOfInterestUserData> e : getUserdata().entrySet()) {
			e.getValue().besuchtenPOIentfernen(poiName);
		}

		saveToDB();
	}

	public boolean playerFoundPOI(UUID id, String poiName) {
		pointsOfInterestUserData ud = getUserdata().get(id);
		if (ud == null)
			return false;
		return ud.getBesuchtePOIs().contains(poiName);
	}

	public boolean playerFoundPOI(Player player, String poiName) {
		return playerFoundPOI(player.getUniqueId(), poiName);
	}

	public void changePlayerCooldown(UUID id, long l) {
		pointsOfInterestUserData ud = getUserdata().get(id);
		if (ud == null)
			return;
		ud.setCooldown(l);
		saveToDB();
	}

	public void changePlayerCooldown(Player player, long l) {
		changePlayerCooldown(player.getUniqueId(), l);
	}

	public void addUserIfNotAvailable(UUID id) {
		if (userdata.containsKey(id))
			return;
		userdata.put(id, new pointsOfInterestUserData(Bukkit.getServer()
				.getPlayer(id).getPlayerListName()));
	}

	public Map<UUID, pointsOfInterestUserData> getUserdata() {
		return userdata;
	}

	public void setUserdata(Map<UUID, pointsOfInterestUserData> userdata) {
		this.userdata = userdata;
	}
}