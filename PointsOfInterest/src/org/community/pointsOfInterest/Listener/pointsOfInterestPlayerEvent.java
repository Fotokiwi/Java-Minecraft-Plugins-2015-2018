package org.community.pointsOfInterest.Listener;

import java.io.File;
import java.util.HashMap;
import java.util.Random;
import java.util.Set;
import java.util.Map.Entry;

import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.community.pointsOfInterest.pointsOfInterest;
import org.community.pointsOfInterest.POIList.pointsOfInterestPOIObject;


@SuppressWarnings("unused")
public class pointsOfInterestPlayerEvent implements Listener {

	private pointsOfInterest plugin;
	private String foundPOI = null;

	public pointsOfInterestPlayerEvent(pointsOfInterest plugin) {
		this.plugin = plugin;
	}

	@EventHandler(priority = EventPriority.NORMAL)
	public void onPlayerMove(PlayerMoveEvent event) {
		Player player = event.getPlayer();
		// Welt unterscheidet sich vom letzten Standort oder der Spieler ist
		// noch nicht registriert
		if (plugin.ismoved.containsKey(player.getName())) {
			if (player
					.getLocation()
					.getWorld()
					.getName()
					.equals(plugin.ismoved.get(player.getName()).getWorld()
							.getName())) {
				Double movedBlocks = (player.getLocation()
						.distanceSquared(plugin.ismoved.get((player.getName()))));
				if (movedBlocks > (plugin.poiConfig.getRadius() * plugin.poiConfig
						.getRadius())) {
					plugin.ismoved.put(player.getName(), player.getLocation());
					String foundPOI = isInArea(player.getLocation());

					if (foundPOI != null) {
						if (plugin.poiUser.playerFoundPOI(player.getUniqueId(),
								foundPOI)) {
							return;
						}

						player.sendMessage("Du hast die Sehenswürdigkeit "
								+ ChatColor.GOLD + foundPOI + ChatColor.WHITE
								+ " gefunden");
						plugin.poiUser.addOnePOIToOneUser(player, foundPOI);
						if (plugin.poiUser.getOnePlayer(player.getUniqueId())
								.getAnzahlBesuchterPOIs() == plugin.poiPOIs
								.getNumberOfPOIs()) {
							plugin.poiHonor.addUserToList(player.getUniqueId(),
									System.currentTimeMillis());
							player.sendMessage(ChatColor.RED + "H"
									+ ChatColor.BLUE + "E" + ChatColor.GREEN
									+ "R" + ChatColor.DARK_AQUA + "Z"
									+ ChatColor.YELLOW + "L"
									+ ChatColor.DARK_PURPLE + "I"
									+ ChatColor.RED + "C" + ChatColor.GRAY
									+ "H" + ChatColor.AQUA + "E"
									+ ChatColor.GOLD + "N " + ChatColor.RED
									+ "" + ChatColor.DARK_GREEN + "G"
									+ ChatColor.LIGHT_PURPLE + "L"
									+ ChatColor.AQUA + "Ü" + ChatColor.YELLOW
									+ "C" + ChatColor.BLUE + "K"
									+ ChatColor.DARK_RED + "W" + ChatColor.GOLD
									+ "U" + ChatColor.GREEN + "N"
									+ ChatColor.DARK_GRAY + "S"
									+ ChatColor.LIGHT_PURPLE + "C"
									+ ChatColor.YELLOW + "H");
							player.sendMessage("Endlich hast du alle Pois gefunden! Du wirst fortan auf der "
									+ ChatColor.GOLD
									+ " Ehrentafel "
									+ ChatColor.WHITE
									+ "der Poi-Finder plaziert sein.");
						}
						return;
					}

				} else {
					return;
				}
			} else {
				plugin.ismoved.put(player.getName(), player.getLocation());
				return;

			}
		} else {
			plugin.ismoved.put(player.getName(), player.getLocation());
			return;
		}
	}

	/**
	 * Gibt dem player aus, wenn er eine Stufe aufsteigt, welche Stufe er jetzt
	 * ist und ggf seinen neuen Rang.
	 * 
	 * @param player
	 *            Nachrichtenempf�nger
	 * @param menge
	 *            Seine aktuelle Stufe
	 * @param name
	 *            Name des Spielers
	 */
	public void aufstiegsAusgabe(Player player, String foundPOI) {
		int alteStufe = plugin.poiUser.getOnePlayer(player.getUniqueId())
				.getStufe();
		String alterRang = plugin.poiUser.getOnePlayer(player.getUniqueId())
				.getRang();
		plugin.poiUser.addOnePOIToOneUser(player.getUniqueId(), foundPOI);
		int neueStufe = plugin.poiUser.getOnePlayer(player.getUniqueId())
				.getStufe();
		String neuerRang = plugin.poiUser.getOnePlayer(player.getUniqueId())
				.getRang();
		if (alteStufe != neueStufe) {
			player.sendMessage("Herzlichen Glückwunsch, du bist eine Stufe aufgestiegen!");
			player.sendMessage("Du erreichst Stufe " + ChatColor.GOLD
					+ neueStufe + ChatColor.WHITE + " !");
			if (!alterRang.equals(neuerRang)) {
				player.sendMessage("Du bist fortan als " + ChatColor.GOLD
						+ neuerRang + " " + ChatColor.RESET + neuerRang
						+ " bekannt.");
			}

		}
	}

	private String isInArea(Location location) {
		for (Entry<String, pointsOfInterestPOIObject> entry : plugin.poiPOIs.getPoiLocations().entrySet()) {
			if (location.getWorld().equals(entry.getValue().getPoiLocation().getWorld())
					&& location.distance(entry.getValue().getPoiLocation()) <= plugin.poiConfig
							.getRadius()) {
				return entry.getKey();
			}
		}
		return null;
	}

}