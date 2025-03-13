package org.community.pointsOfInterest.Honorlist;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.community.DatabaseProvider.MySQL.MySQL;
import org.community.pointsOfInterest.pointsOfInterest;

public class pointsOfInterestHonorlist {
	
	private pointsOfInterest plugin;
	@SuppressWarnings("unused")
	private int userOnHonorList;
	private List<HonorlistEntry> honorList; 
	
	/**
	 * Weist der Klasse ihr Oberplugin zu und initialisiert eine HonorListe
	 * 
	 * @param plugin
	 *            PointsOfInterest
	 */
	public pointsOfInterestHonorlist(pointsOfInterest plugin) {
		this.plugin = plugin;
		honorList = new LinkedList<HonorlistEntry>();
	}



	public boolean loadFromDB() {
		MySQL mysql = new MySQL();
		mysql.connect();
		ResultSet rs = mysql.selectRS("SELECT * FROM POI_Honorlist");
		if ( rs != null){
		try {
			while(rs.next()){
				HonorlistEntry he = new HonorlistEntry(UUID.fromString(rs.getString("UUID")), rs.getLong("TimeStamp"));
				honorList.add(he);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		mysql.disconnect();
		
		return true;

	}
	
	public void saveOneUserToDB(Player player) {
		HonorlistEntry h = null;
		for(HonorlistEntry he : honorList)
		{
			if(he.getId().equals(player.getUniqueId()))
			{
				h = he;
				break;
			}
		}
		saveOneUserToDB(h);
	}
	
	public void saveOneUserToDB(HonorlistEntry h){
		if(h == null)
			return;
		MySQL mysql = new MySQL();
		mysql.connect();
		mysql.insert("INSERT INTO POI_Honorlist (ID, UUID, TimeStamp) "
				+ "VALUES (NULL,\""+ h.getId() + "\", \"" + h.getTimeStamp() + "\")");
		mysql.disconnect();
		
	
	}
	

	public void saveToDB() {
		MySQL mysql = new MySQL();
		mysql.connect();
		mysql.update("DELETE FROM POI_Honorlist");
		for(HonorlistEntry h : honorList)
			saveOneUserToDB(h);
		mysql.disconnect();
	}
	
	public boolean honorListContainsPlayer(UUID id){
		for(HonorlistEntry e : honorList){
			if(e.getId().equals(id))
				return true;
		}
		return false;
	}

	
	/**
	 * �berpr�ft die gesamte Poi-user Liste nach falschen eintr�gen und
	 * korrigiert diese.
	 */
	public void refreshHonorList() {

		int overall = plugin.poiPOIs.getNumberOfPOIs();
		
		List<HonorlistEntry> markedForRemoval = new LinkedList<HonorlistEntry>();
		for(HonorlistEntry e : honorList){
			if(plugin.poiUser.getOnePlayer(e.getId()).getAnzahlBesuchterPOIs() < overall)
				markedForRemoval.add(e);
		}
		honorList.removeAll(markedForRemoval);
		saveToDB();
	}

	/**
	 * F�gt einen user mit �bergebenen Namen auf die Ehrentafel. Dabei wird er
	 * unter dem Letzen angeordnet und erh�lt eine h�here Nummer. Au�erdem wird
	 * sein Zeitstempel gespeichert.
	 * 
	 * @param name
	 *            Name des users
	 * @param date
	 *            Zeitstempel des users
	 */
	public void addUserToList(UUID id, long date) {		
		honorList.add(new HonorlistEntry(id, date));
		saveToDB();
	}

	/**
	 * L�scht den �bergebenen user von der Ehrentafel und l�sst alle unter ihm
	 * nachr�cken. Gibt jedem user, der von der Liste genommen wird eine
	 * Nachricht aus, so fern dieser user online ist.
	 * 
	 * @param name
	 *            zu l�schender user
	 */
	public void removeFromList(UUID id) {
		honorList.remove(id);

		saveToDB();

	}

	/**
	 * Zeigt dem �bergebenen user die Ehrentafel an.
	 * 
	 * @param player
	 *            aktueller user
	 */
	public void showList(Player player) {
		if(!honorList.isEmpty()){
			player.sendMessage(ChatColor.AQUA + "  Points of Interests Ehrentafel " + ChatColor.GREEN + " [2.0]");
			player.sendMessage(ChatColor.GOLD + "____________________________________________________________");

			player.sendMessage(ChatColor.GOLD + "  Platz " + ChatColor.AQUA + " Rang " + ChatColor.WHITE + " Name " + ChatColor.GREEN + " (Stufe) ");

			for (HonorlistEntry e : honorList) {
				int stufe = plugin.poiUser.getOnePlayer(e.getId()).getStufe();
				String rang = plugin.poiUser.getOnePlayer(e.getId()).getRang();
				String name = plugin.poiUser.getOnePlayer(e.getId()).getSpielerName();
				int platz = honorList.indexOf(e)+1;
				long timestamp = e.getTimeStamp();

				player.sendMessage(ChatColor.GOLD + "  " + platz + " " + ChatColor.AQUA + " " + rang + " " + ChatColor.WHITE + " " + name + " "
						+ ChatColor.GREEN + " (" + stufe + ") ");
				player.sendMessage("Vervollständigt am: " + ChatColor.LIGHT_PURPLE + " [" + timestampToDate(timestamp) + "]");

			}

			player.sendMessage(ChatColor.GOLD + "____________________________________________________________");
		} else {
			player.sendMessage(ChatColor.RED + "Derzeit hat kein user alle Pois entdeckt!");
		}

	}

	/**
	 * Diese Funktion liefert einen Datum als String zur�ck, wenn es einen
	 * Timestamp wie z.B. currentTimeMillies() bekommt.
	 * 
	 * @param timestemp
	 *            in Millisekunden
	 * @return ein Datum des Formates "MMM dd,yyyy HH:mm"
	 */
	private String timestampToDate(long timestemp) {
		String Datum = "";
		SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy HH:mm");

		Date resultdate = new Date(timestemp);
		Datum = (sdf.format(resultdate));
		return Datum;
	}

}