package org.community.pointsOfInterest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class pointsOfInterestUtility {

	/**
	 * Berechnet den Abstand vom Spieler zu einer gegebenen Koordinate.
	 * 
	 * @param sender
	 *            Spieler
	 * @param x
	 *            X-Koordinate des gesuchten Ortes als String
	 * @param y
	 *            Y-Koordinate des gesuchten Ortes als String
	 * @param z
	 *            Z-Koordinate des gesuchten Ortes als String
	 * @return
	 */
	public static int calcDistance(Player sender, String x, String y, String z) {
		int xKoordinate = Integer.parseInt(x); // vorne
		int yKoordinate = Integer.parseInt(y); // oben
		int zKoordinate = Integer.parseInt(z); // seite

		int xKoordinateSpieler = sender.getLocation().getBlockX();
		int yKoordinateSpieler = sender.getLocation().getBlockY();
		int zKoordinateSpieler = sender.getLocation().getBlockZ();

		// Achtung Vektorsubtraktion!
		int[] spielervektor = { xKoordinateSpieler, yKoordinateSpieler,
				zKoordinateSpieler };
		int[] poivektor = { xKoordinate, yKoordinate, zKoordinate };

		double[] ergebnisvektor = { spielervektor[0] - poivektor[0],
				spielervektor[1] - poivektor[1],
				spielervektor[2] - poivektor[2] };
		//
		long abstand = Math.round(Math.sqrt((Math.pow(ergebnisvektor[0], 2)
				+ Math.pow(ergebnisvektor[1], 2) + Math.pow(ergebnisvektor[2],
				2))));

		return (int) abstand;
	}

	/**
	 * Sortiert eine �bergebenes Stringarray alphabetisch beginnend mit A und
	 * gibt diese zur�ck
	 * 
	 * @param stringliste
	 *            das zu sortierende Array
	 * @return sortierte Liste
	 */
	public static String[] alphsort(String[] stringliste) {
		List<String> tempListe = new ArrayList<String>();
		String[] sortierteListe = new String[stringliste.length];

		for (String part : stringliste) {
			tempListe.add(part);
		}

		Collections.sort(tempListe);

		for (int i = 0; i < stringliste.length; i++) {
			sortierteListe[i] = tempListe.get(i);
		}

		tempListe = null;

		return sortierteListe;
	}

	/**
	 * Gibt f�r den gesendeten Spieler die Himmelsrichtung zur�ck, in dem sich
	 * der gesucht Punkt befindet
	 * 
	 * @param sender
	 *            Von dessen Position aus geschaut wird
	 * @param x
	 *            X-Koordinate des gesuchten Punktes
	 * @param z
	 *            Z-Koordinate des gesuchten Punktes
	 * @return himmelsrichtung : z.B. "n�rdlich", "nord-westlich" ...
	 */
	public static String giveDirection(Player sender, int x, int z) {
		int xKoordinate = x; // vorne
		int zKoordinate = z; // seite

		int xKoordinateSpieler = sender.getLocation().getBlockX();
		int zKoordinateSpieler = sender.getLocation().getBlockZ();

		String himmelsrichtung = "";
		int quadrant = 0;

		// x & z koordinaten
		int[] playerkoords = { xKoordinateSpieler, zKoordinateSpieler };
		int[] punktkoords = { xKoordinate, zKoordinate };
		int[] nordvektor = { -1, 0 };
		int[] zielvektor = { punktkoords[0] - playerkoords[0],
				punktkoords[1] - playerkoords[1] };

		double winkel = (180 / Math.PI)
				* Math.acos(((nordvektor[1] * zielvektor[1]) + (nordvektor[0] * zielvektor[0]))
						/ ((Math.sqrt(Math.pow(zielvektor[0], 2)
								+ Math.pow(zielvektor[1], 2))) * Math.sqrt(Math
								.pow(nordvektor[0], 2)
								+ Math.pow(nordvektor[1], 2))));

		if (playerkoords[0] < punktkoords[0]) {
			if (playerkoords[1] < punktkoords[1]) {
				quadrant = 3;
			} else
				quadrant = 4;
		} else {
			if (playerkoords[1] < punktkoords[1]) {
				quadrant = 1;
			} else
				quadrant = 2;
		}

		if (quadrant == 1 || quadrant == 3) {
			if (winkel <= 22.5) {
				himmelsrichtung = "nördlich";
			} else if (winkel <= 67.5) {
				himmelsrichtung = "nord-östlich";
			} else if (winkel <= 112.5) {
				himmelsrichtung = "östlich";
			} else if (winkel <= 157.5) {
				himmelsrichtung = "süd-östlich";
			} else {
				himmelsrichtung = "südlich";
			}
		} else {
			if (winkel <= 22.5) {
				himmelsrichtung = "nördlich";
			} else if (winkel <= 67.5) {
				himmelsrichtung = "nord-westlich";
			} else if (winkel <= 112.5) {
				himmelsrichtung = "westlich";
			} else if (winkel <= 157.5) {
				himmelsrichtung = "süd-westlich";
			} else {
				himmelsrichtung = "südlich";
			}
		}
		return himmelsrichtung;
	}

	public static String giveDirection(Player sender, String x, String z){
		return giveDirection(sender, Integer.parseInt(x), Integer.parseInt(z));
	}
	
	@SuppressWarnings("deprecation")
	public static Player getPlayerByName(String name) {
		Player foundPlayer = null;

		for (Player p : Bukkit.getServer().getOnlinePlayers()) {
			if (p.getName().equals(name)) {
				foundPlayer = p;
				break;
			}
		}
		if (foundPlayer != null) {
			return foundPlayer;
		}

		return null;
	}

	public static OfflinePlayer getOfflinePlayerByName(String name) {
		OfflinePlayer foundPlayer = null;

		for (OfflinePlayer p : Bukkit.getServer().getOfflinePlayers()) {
			if (p.getName().equals(name)) {
				foundPlayer = p;
				break;
			}
		}
		if (foundPlayer != null) {
			return foundPlayer;
		}

		return null;
	}
	
	public static UUID getUUIDByName(String name)
	{
		UUID id = null;
		Player p = getPlayerByName(name);
		if(p != null)
			id = p.getUniqueId();
		else
		{
			OfflinePlayer op = getOfflinePlayerByName(name);
			id = op.getUniqueId();
		}
		return id;
	}
	
	public static Location convertStringToLoc(String s){
		
		String[] as = s.split("#");
		return (new Location(Bukkit.getWorld(as[0]), Double.parseDouble(as[1]), Double.parseDouble(as[2]), Double.parseDouble(as[3])));
	}
	
	public static String convertLocToString(Location l){
		return l.getWorld().getName() + "#" + l.getBlockX() + "#" + l.getBlockY() + "#" + l.getBlockZ();
	}
}
