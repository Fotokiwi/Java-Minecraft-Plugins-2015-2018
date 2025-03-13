package org.community.pointsOfInterestCommands;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.community.pointsOfInterest.pointsOfInterest;
import org.community.pointsOfInterest.pointsOfInterestUtility;
import org.community.pointsOfInterest.POIList.pointsOfInterestPOIObject;
import org.community.pointsOfInterest.User.pointsOfInterestUserData;

public class pointsOfInterestCommands {
	private pointsOfInterest plugin;
	//private CACommandHandler caCH;

	public pointsOfInterestCommands(pointsOfInterest plugin) {
		this.plugin = plugin;
		//init();
	}

	public boolean getCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){


		/*
		
		boolean isAdmin = !(sender instanceof Player) || isAdmin(((Player)sender).getUniqueId());
		
		
		return caCH.getCommand(sender, cmd, args, isAdmin);
		*/
		
		if (sender instanceof Player) {
			final Player player = (Player) sender;
			if (cmd.getName().equalsIgnoreCase("PointsOfInterest")) {
				switch (args.length) {
				case 0:
					noArgs(player);
					return true;
				case 1:
					if (args[0].equalsIgnoreCase("reload")) {
						reload(player);
						return true;
					} else if (args[0].equalsIgnoreCase("top5")) {
						top5(player);
						return true;
					}

					else if (args[0].equalsIgnoreCase("info")) {
						info(player);
						return true;
					}

					else if (args[0].equalsIgnoreCase("level")) {
						level(player);
						return true;
					} // end level

					else if (args[0].equalsIgnoreCase("wall")) {
						wall(player);
						return true;
					}

					else if (args[0].equalsIgnoreCase("list")) {
						list(player);
						return true;
					}

					else if (args[0].equalsIgnoreCase("tip") || args[0].equalsIgnoreCase("tipp")) {
						tip(player);
						return true;
					}

					else {
						player.sendMessage("Ungültiger Befehl. Für eine Liste /poi info");
						return true;
					}
				case 2:
					if (args[0].equalsIgnoreCase("remember")) {
						remember(player, args[1]);
						return true;
					} else if (args[0].equalsIgnoreCase("distance")) {
						distance(player, args[1]);
						return true;
					} else if (args[0].equalsIgnoreCase("stats")) {
						stats(player, args[1]);
						return true;
					} else if (args[0].equalsIgnoreCase("nearby")) {
						nearby(player, args[1]);
						return true;
					} else if (args[0].equalsIgnoreCase("list")) {
						list(player, args[1]);
						return true;
					} else if (args[0].equalsIgnoreCase("set")) {
						set(player, args);
						return true;
					} else if (args[0].equalsIgnoreCase("remove")) {
						removePOI(player, args);
						return true;
					} else {
						player.sendMessage("Ungültiger Befehl. Für eine Liste /poi info");
						return true;
					}
				default:
					if (args[0].equalsIgnoreCase("set")) {
						set(player, args);
						return true;
					} else if (args[0].equalsIgnoreCase("remove")) {
						removePOI(player, args);
						return true;
					} else {

						sender.sendMessage("Ungültiger Befehl. Für eine Liste /poi info");
						return true;
					}
				}
			}
		} else {
			sender.sendMessage("Ungültiger Befehl. Für eine Liste /poi info");
			return true;
		}
		return false;
	}// end getCommand

	public boolean isAdmin(UUID uuid) {
		for (String s : plugin.poiConfig.getAdmins()) {
			if (uuid.compareTo(UUID.fromString(s)) == 0)
				return true;
		}
		return false;
	}

	public void noArgs(Player player) {
		plugin.showPluginInfo(player);

		String rang = plugin.poiUser.getOnePlayer(player).getRang();
		int stufe = plugin.poiUser.getOnePlayer(player).getStufe();
		int anzahl = plugin.poiUser.getOnePlayer(player).getAnzahlBesuchterPOIs();
		int overall = plugin.poiPOIs.getNumberOfPOIs();
		player.sendMessage("Unter den Poi-Suchern wirst du : " + ChatColor.AQUA + rang + ChatColor.WHITE + " genannt.");
		player.sendMessage("Du hast bereits " + ChatColor.GOLD + anzahl + ChatColor.WHITE + " von insgesamt "
				+ ChatColor.GOLD + overall + ChatColor.WHITE + " interessanten Orten gefunden, ");
		player.sendMessage("damit hast du die Stufe " + ChatColor.GOLD + stufe + ChatColor.WHITE + " erreicht.");
	}

	public void reload(Player player) {
		if(isAdmin(player.getUniqueId())){
			plugin.poiConfig.reloadConfig();
			player.sendMessage("Die Config wurde neu geladen.");
		}
		else
			player.sendMessage("Dir fehlen die nötigen Rechte, um diesen Befehl auszuführen.");

	}

	public void top5(CommandSender cs) {
		Player player = (Player) cs;
		if (plugin.poiUser.getOnePlayer(player).getStufe() < 2 && !isAdmin(player.getUniqueId())) {
			player.sendMessage(ChatColor.RED + "Deine Stufe ist noch nicht hoch genug!");
			return;
		}

		String platz[] = { "", "", "", "", "" };
		int anzahl[] = { 0, 0, 0, 0, 0 };

		for (Entry<UUID, pointsOfInterestUserData> e: plugin.poiUser.getUserdata().entrySet()) {

			boolean shouldNotAppearOnList = false;

			if (isAdmin(e.getKey())) {
				shouldNotAppearOnList = true;
			}

			if (plugin.poiHonor.honorListContainsPlayer(e.getKey())) {
				shouldNotAppearOnList = true;
			}

			if (shouldNotAppearOnList == false) {
				int aktuelleanzahl = e.getValue().getAnzahlBesuchterPOIs();
				String user = e.getValue().getSpielerName();
				if (aktuelleanzahl > 0) {
					if (aktuelleanzahl > anzahl[0]) {
						for (int i = 4; i > 0; i--) {
							anzahl[i] = anzahl[i - 1];
							platz[i] = platz[i - 1];
						}
						anzahl[0] = aktuelleanzahl;
						platz[0] = user;
					} else if (aktuelleanzahl > anzahl[1]) {
						for (int i = 4; i > 1; i--) {
							anzahl[i] = anzahl[i - 1];
							platz[i] = platz[i - 1];
						}
						anzahl[1] = aktuelleanzahl;
						platz[1] = user;
					} else if (aktuelleanzahl > anzahl[2]) {
						for (int i = 4; i > 2; i--) {
							anzahl[i] = anzahl[i - 1];
							platz[i] = platz[i - 1];
						}
						anzahl[2] = aktuelleanzahl;
						platz[2] = user;
					} else if (aktuelleanzahl > anzahl[3]) {
						anzahl[4] = anzahl[3];
						platz[4] = platz[3];

						anzahl[3] = aktuelleanzahl;
						platz[3] = user;
					} else if (aktuelleanzahl > anzahl[4]) {
						anzahl[4] = aktuelleanzahl;
						platz[4] = user;
					}
				}
			}
		}

		player.sendMessage(ChatColor.GREEN + "Die " + ChatColor.UNDERLINE + ChatColor.AQUA + "Top 5" + ChatColor.RESET
				+ ChatColor.GREEN + " der besten Poi-Finder:");
		player.sendMessage("Platz 1: " + ChatColor.GOLD + platz[0] + ChatColor.WHITE + " (" + anzahl[0] + ") ");
		player.sendMessage("Platz 2: " + ChatColor.GOLD + platz[1] + ChatColor.WHITE + " (" + anzahl[1] + ") ");
		player.sendMessage("Platz 3: " + ChatColor.GOLD + platz[2] + ChatColor.WHITE + " (" + anzahl[2] + ") ");
		player.sendMessage("Platz 4: " + ChatColor.GOLD + platz[3] + ChatColor.WHITE + " (" + anzahl[3] + ") ");
		player.sendMessage("Platz 5: " + ChatColor.GOLD + platz[4] + ChatColor.WHITE + " (" + anzahl[4] + ") ");

		if (player.getName().equals(platz[0])) {
			player.sendMessage("Herzlichen Glückwunsch zum " + ChatColor.GOLD + "ersten " + ChatColor.WHITE + "Platz "
					+ player.getName() + " !");
		} else if (player.getName().equals(platz[1])) {
			player.sendMessage("Herzlichen Glückwunsch du hast den " + ChatColor.GOLD + "zweiten " + ChatColor.WHITE
					+ "Platz, " + player.getName() + " !");
		} else if (player.getName().equals(platz[2])) {
			player.sendMessage("Herzlichen Glückwunsch zum " + ChatColor.GOLD + "dritten " + ChatColor.WHITE + "Platz "
					+ player.getName() + " !");
		}

	}

	public void info(Player player) {
		player.sendMessage("Folgende Paramter sind verfügbar:");

		if (isAdmin(player.getUniqueId())) {
			player.sendMessage(ChatColor.GOLD + "poi " + ChatColor.WHITE
					+ " zeigt deine Stufe, deinen Rang und deine Poianzahl an");
			player.sendMessage(ChatColor.GOLD + "poi level" + ChatColor.WHITE
					+ " zeigt die PointsOfInterest Levelübersicht an.");
			player.sendMessage(ChatColor.GOLD + "poi wall" + ChatColor.WHITE + " zeigt die Ehrentafel an");
			player.sendMessage(ChatColor.GOLD + "poi list " + ChatColor.WHITE + "Zeigt eine List aller POIs an");
			player.sendMessage(ChatColor.GOLD + "poi reload " + ChatColor.WHITE
					+ "Ladet die Config und POIliste neu ein ");
			player.sendMessage(ChatColor.GOLD + "poi set <NAME>" + ChatColor.WHITE
					+ "Erstellt an deiner Position einen POI");
			player.sendMessage(ChatColor.GOLD + "poi remove <NAME>" + ChatColor.WHITE
					+ "Entfernt den POI mit diesem Namen");
			player.sendMessage(ChatColor.GOLD + "poi list <NAME> " + ChatColor.WHITE
					+ "zeigt die Details eines POIs an");
			player.sendMessage(ChatColor.GOLD + "poi list <PLAYERNAME> " + ChatColor.WHITE
					+ "zeigt alle gefunden Pois des Spielers an");
			player.sendMessage(ChatColor.GOLD + "poi nearby <PLAYERNAME> " + ChatColor.WHITE
					+ "zeigt welcher Poi bei einem Spieler in der Nähe ist.");
			player.sendMessage(ChatColor.GOLD + "poi distance <POINAME> " + ChatColor.WHITE
					+ "zeigt deine ungefähre Distanz zu diesem Poi an");
			player.sendMessage(ChatColor.GOLD + "poi tip(p) " + ChatColor.WHITE
					+ "zeigt deinen Abstand zum nächst gelegenem ungefundenem Poi an.");
			player.sendMessage(ChatColor.GOLD + "poi top5 " + ChatColor.WHITE + "Zeigt die 5 besten Poifinder an");
			player.sendMessage(ChatColor.GOLD + "poi stats <PLAYERNAME> " + ChatColor.WHITE
					+ "zeigt die Stats eines Spielers");
			player.sendMessage(ChatColor.GOLD + "poi remember <POINAME> " + ChatColor.WHITE
					+ "zeigt dir Distanz und Himmelsrichtung eines bereits gefundenen Pois an!" + ChatColor.RED
					+ " [Benötigt Stufe 8]");
			player.sendMessage("Um ein Schild mit deinen Poi-Daten zu versehen nutze [poi] als Schildtext.");
		}

		else {
			player.sendMessage(ChatColor.GOLD + "poi " + ChatColor.WHITE
					+ " zeigt deine Stufe, deinen Rang und deine Poianzahl an");
			player.sendMessage(ChatColor.GOLD + "poi level" + ChatColor.WHITE
					+ " zeigt die PointsOfInterest Levelübersicht an");
			player.sendMessage(ChatColor.GOLD + "poi wall" + ChatColor.WHITE + " zeigt die Ehrentafel an");
			player.sendMessage(ChatColor.GOLD + "poi top5 " + ChatColor.WHITE + "Zeigt die 5 besten Poifinder an"
					+ ChatColor.RED + "[Benötigt Stufe 2]");
			player.sendMessage(ChatColor.GOLD + "poi tip(p) " + ChatColor.WHITE
					+ "Zeigt deinen Abstand zum nächst gelegenem ungefundenem Poi an." + ChatColor.RED
					+ "[Benötigt Stufe 3]");
			player.sendMessage(ChatColor.GOLD + "poi list " + ChatColor.WHITE
					+ "Zeigt eine List deiner gefundenen POIs an" + ChatColor.RED + "[Benötigt Stufe 4]");
			player.sendMessage(ChatColor.GOLD + "poi stats <PLAYERNAME> " + ChatColor.WHITE
					+ "Zeigt dir die Stats eines Spielers an." + ChatColor.RED + " [Benötigt Stufe 7]");
			player.sendMessage(ChatColor.GOLD + "poi remember <POINAME> " + ChatColor.WHITE
					+ "Zeigt dir Distanz und Himmelsrichtung eines bereits gefundenen Pois an!" + ChatColor.RED
					+ " [Benötigt Stufe 8]");
			player.sendMessage("Um ein Schild mit deinen Poi-Daten zu versehen nutze [poi] als Schildtext."
					+ ChatColor.RED + "[Benötigt Stufe 1]");

		}
	}

	public void level(CommandSender cs) {
		Player player = (Player) cs;
		ChatColor color = ChatColor.GREEN;
		int stufe = plugin.poiUser.getOnePlayer(player).getStufe();

		player.sendMessage(ChatColor.AQUA + "  Levelübersicht für Points of Interest " + ChatColor.GREEN + " [2.0]");
		player.sendMessage(ChatColor.GOLD + "____________________________________________________________");
		player.sendMessage(ChatColor.GOLD + "Stufe" + ChatColor.WHITE + " Belohnung");
		if (stufe < 1)
			color = ChatColor.RED;
		player.sendMessage(color + "  1 :" + ChatColor.WHITE + " Poi-Schild setzbar");
		if (stufe < 2)
			color = ChatColor.RED;
		player.sendMessage(color + "  2 :" + ChatColor.WHITE + " besten 5 Poi-Finder anzeigbar");
		if (stufe < 3)
			color = ChatColor.RED;
		player.sendMessage(color + "  3 :" + ChatColor.WHITE + " ungenaur Tip verfügbar");
		if (stufe < 4)
			color = ChatColor.RED;
		player.sendMessage(color + "  4 :" + ChatColor.WHITE + " gefundene Pois anzeigbar");
		if (stufe < 5)
			color = ChatColor.RED;
		player.sendMessage(color + "  5 :" + ChatColor.WHITE + " Poi-Schilder aktualisieren sich");
		if (stufe < 6)
			color = ChatColor.RED;
		player.sendMessage(color + "  6 :" + ChatColor.WHITE + " Tip Cooldown gesenkt");
		if (stufe < 7)
			color = ChatColor.RED;
		player.sendMessage(color + "  7 :" + ChatColor.WHITE + " Stats anderer anzeigbar");
		if (stufe < 8)
			color = ChatColor.RED;
		player.sendMessage(color + "  8 :" + ChatColor.WHITE + " an Pois erinnern verfügbar");
		if (stufe < 9)
			color = ChatColor.RED;
		player.sendMessage(color + "  9 :" + ChatColor.WHITE + " genauer Tip verfügbar");
		if (stufe < 10)
			color = ChatColor.RED;
		player.sendMessage(color + " 10 :" + ChatColor.WHITE + " Tip Cooldown gesenkt");
		player.sendMessage(ChatColor.GOLD + "____________________________________________________________");

	}

	public void wall(CommandSender cs) {
		Player player = (Player) cs;
		plugin.poiHonor.showList(player);
	}

	public void list(CommandSender cs) {
		Player player = (Player) cs;
		if (isAdmin(player.getUniqueId())) {
			String[] poiArray = (String[]) plugin.poiPOIs.getPoiLocations().keySet()
					.toArray(new String[plugin.poiPOIs.getPoiLocations().size()]);
			if (!plugin.poiPOIs.getPoiLocations().keySet().isEmpty()) {

				poiArray = pointsOfInterestUtility.alphsort(poiArray);

				int overall = plugin.poiPOIs.getNumberOfPOIs();
				int anzahl = plugin.poiUser.getOnePlayer(player).getAnzahlBesuchterPOIs();

				for (int i = 0; i < poiArray.length; i++) {
					if (plugin.poiUser.playerFoundPOI(player.getUniqueId(), poiArray[i])) {
						player.sendMessage(ChatColor.GREEN + "" + poiArray[i]);
					}

					else {
						player.sendMessage(ChatColor.GRAY + "" + poiArray[i]);
					}
				}

				player.sendMessage("Du hast bereits " + ChatColor.GREEN + anzahl + ChatColor.WHITE + " von insgesamt "
						+ ChatColor.GOLD + overall + ChatColor.WHITE + " interessanten Orten gefunden!");

			} else {
				player.sendMessage("Es gibt noch keine interessanten Orte.");
			}
		}

		else {

			if (plugin.poiUser.getOnePlayer(player).getStufe() >= 4) {

				int overall = plugin.poiPOIs.getNumberOfPOIs();
				int anzahl = plugin.poiUser.getOnePlayer(player).getAnzahlBesuchterPOIs();

				String[] poiArray = (String[]) plugin.poiUser.getOnePlayer(player.getUniqueId()).getBesuchtePOIs()
						.toArray(new String[plugin.poiPOIs.getPoiLocations().size()]);
				poiArray = pointsOfInterestUtility.alphsort(poiArray);

				for (String s : poiArray) {
					player.sendMessage(ChatColor.GREEN + s);
				}

				player.sendMessage("Du hast bereits " + ChatColor.GREEN + anzahl + ChatColor.WHITE + " von insgesamt "
						+ ChatColor.GOLD + overall + ChatColor.WHITE + " interessanten Orten gefunden!");

			} else {
				player.sendMessage(ChatColor.RED + "Deine Stufe ist noch nicht hoch genug!");

			}
		}
	}

	public void tip(Player player) {

		if (plugin.poiUser.getOnePlayer(player).getStufe() < 3 && !isAdmin(player.getUniqueId())) {
			player.sendMessage(ChatColor.RED + "Deine Stufe ist noch nicht hoch genug!");
			return;
		}

		int spielerstufe = plugin.poiUser.getOnePlayer(player).getStufe();

		if (spielerstufe < 6) {
			int cooldown = 420;
			if (((plugin.poiUser.getOnePlayer(player).getCooldown() / 1000) + cooldown) > System.currentTimeMillis() / 1000) {
				int wait = (int) ((((plugin.poiUser.getOnePlayer(player).getCooldown() / 1000) + cooldown) - (System
						.currentTimeMillis() / 1000)) / 60);
				player.sendMessage("Leider musst du noch " + ChatColor.RED + wait + ChatColor.WHITE
						+ " Minuten warten.");

			}
		}

		else if (spielerstufe <= 9 && spielerstufe >= 6) {
			int cooldown = 340;
			if (((plugin.poiUser.getOnePlayer(player).getCooldown() / 1000) + cooldown) > System.currentTimeMillis() / 1000) {
				int wait = (int) ((((plugin.poiUser.getOnePlayer(player).getCooldown() / 1000) + cooldown) - (System
						.currentTimeMillis() / 1000)) / 60);
				player.sendMessage("Leider musst du noch " + ChatColor.RED + wait + ChatColor.WHITE
						+ " Minuten warten.");

			}
		}

		else if (spielerstufe > 9) {
			int cooldown = 200;
			if (((plugin.poiUser.getOnePlayer(player).getCooldown() / 1000) + cooldown) > System.currentTimeMillis() / 1000) {
				int wait = (int) ((((plugin.poiUser.getOnePlayer(player).getCooldown() / 1000) + cooldown) - (System
						.currentTimeMillis() / 1000)) / 60);
				player.sendMessage("Leider musst du noch " + ChatColor.RED + wait + ChatColor.WHITE
						+ " Minuten warten.");

			}

		}

		double shortestdistance = Double.MAX_VALUE;
		String name = "";

		double distance = 0;

		String[] poiArray = (String[]) plugin.poiPOIs.getPoiLocations().keySet()
				.toArray(new String[plugin.poiPOIs.getPoiLocations().size()]);

		if (plugin.poiUser.getOnePlayer(player).getAnzahlBesuchterPOIs() == plugin.poiPOIs.getNumberOfPOIs()) {
			player.sendMessage("Du hast alle verfügbaren interessanten Orte gefunden gefunden!");
			return;
		}

		for (int i = 0; i < poiArray.length; i++) {

			if (!(plugin.poiUser.playerFoundPOI(player, poiArray[i]))) {

				if(player.getLocation().getWorld().getName().equalsIgnoreCase(plugin.poiPOIs.getOnePOI(poiArray[i]).getPoiLocation().getWorld().getName())) {
					distance = player.getLocation().distanceSquared(plugin.poiPOIs.getOnePOI(poiArray[i]).getPoiLocation());

					if (distance < shortestdistance) {
						shortestdistance = distance;
						name = poiArray[i];
					}
				}
				
			}
		}

		String ausgabe = "Der nächstgelegende interessante Ort ";
		String ausgabeAdmin = "";
		if (isAdmin(player.getUniqueId())) {
			ausgabeAdmin += "( " + name + " ) ";
		}
		String ausgabe2 = " ist ";
		String ungefaehr = "";
		if (plugin.poiUser.getOnePlayer(player).getStufe() > 4 && plugin.poiUser.getOnePlayer(player).getStufe() < 9) {
			ungefaehr += "ungefähr ";
			int ungenauigkeit = (int) (Math.random() * 88);
			double vorzeichen = Math.random() * 2;
			if (vorzeichen < 1)
				ungenauigkeit = ungenauigkeit * (-1);
			shortestdistance = shortestdistance + ungenauigkeit;
		}
		if (plugin.poiUser.getOnePlayer(player).getStufe() >= 9) {
			ungefaehr += "exakt ";
		}
		String ausgabeDistance = "" + shortestdistance;
		String ausgabe3 = " Bloecke von dir entfernt.";

		player.sendMessage(ausgabe + ChatColor.GOLD + ausgabeAdmin + ChatColor.WHITE + ausgabe2 + ungefaehr
				+ ChatColor.GOLD + ausgabeDistance + ChatColor.WHITE + ausgabe3);

		plugin.poiUser.changePlayerCooldown(player, System.currentTimeMillis());

	}

	public void removePOI(Player player, String[] args1) {
		if (isAdmin(player.getUniqueId())) {
			String[] args = Arrays.copyOfRange(args1, 1, args1.length);
			String poiName = "";
			for (String s : args)
				poiName += s + " ";
			poiName = poiName.trim();
			if (plugin.poiPOIs.containsPOI(poiName)) {
				plugin.poiPOIs.removePOI(poiName);
				player.sendMessage("Der POI " + ChatColor.RED + args1 + ChatColor.WHITE
						+ " wurde gerade aus der Liste aller POIs gelöscht.");
				plugin.poiUser.removeOnePOI(poiName);
				player.sendMessage("Der POI " + ChatColor.RED + args1 + ChatColor.WHITE
						+ " wurde gerade aus allen Spielerlisten entfernt.");
			} else
				player.sendMessage("Der POI existiert leider nicht");
		} else
			player.sendMessage("Du hast keine Berechtigungen für diesen Befehl.");
	}

	public void remember(Player player, String args1) {

		if (plugin.poiUser.getOnePlayer(player).getStufe() >= 8) {

			String[] poiArray = (String[]) plugin.poiPOIs.getPoiLocations().keySet()
					.toArray(new String[plugin.poiPOIs.getPoiLocations().size()]);

			boolean isOnList = false;

			for (String aktuellerPoi : poiArray) {
				if (args1.equalsIgnoreCase(aktuellerPoi))
					isOnList = true;
			}

			if (isOnList) {
				if (plugin.poiUser.playerFoundPOI(player, args1)) {

					double abstand = player.getLocation().distance(plugin.poiPOIs.getOnePOI(args1).getPoiLocation());
					String himmelsrichtung = pointsOfInterestUtility.giveDirection(player,
							plugin.poiPOIs.getOnePOI(args1).getPoiLocation().getBlockX(),
							plugin.poiPOIs.getOnePOI(args1).getPoiLocation().getBlockZ());

					player.sendMessage("Du erinnerst dich, dass " + ChatColor.GOLD + args1 + ChatColor.WHITE + " gute "
							+ ChatColor.GOLD + abstand + ChatColor.WHITE + " Blöcke " + ChatColor.GOLD
							+ himmelsrichtung + ChatColor.WHITE + " von hier entfernt ist!");

				} else {
					player.sendMessage(ChatColor.RED + "Diesen Ort hast du noch nicht entdeckt!");

				}

			} else {
				player.sendMessage(ChatColor.RED + "Diesen Poi gibt es nicht!");

			}

		} else {
			player.sendMessage(ChatColor.RED + "Deine Stufe ist noch nicht hoch genug!");

		}
	}

	public void distance(Player player, String args1) {
		if (isAdmin(player.getUniqueId())) {

			pointsOfInterestPOIObject poiO = plugin.poiPOIs.getOnePOI(args1);

			if (!(poiO == null)) {

				double distance = player.getLocation().distance(poiO.getPoiLocation());

				player.sendMessage("Der Poi " + ChatColor.GOLD + args1 + ChatColor.WHITE + " ist ca. " + ChatColor.GOLD
						+ distance + ChatColor.WHITE + " Bloecke von dir entfernt!");

			} else {
				player.sendMessage("Diesen Poi gibt es nicht.");

			}
		} else {
			player.sendMessage("Du hast nicht die Berechtigungen für diesen Befehl!");

		}
	}

	public void stats(Player player, String args1) {
		if (plugin.poiUser.getOnePlayer(player).getStufe() >= 7 || isAdmin(player.getUniqueId())) {
			String targetName = args1;
			if (plugin.poiUser.getUserdata().containsKey(pointsOfInterestUtility.getPlayerByName(targetName))) {
				Player p = pointsOfInterestUtility.getPlayerByName(targetName);
				String targetRang = plugin.poiUser.getOnePlayer(p).getRang();
				int targetStufe = plugin.poiUser.getOnePlayer(p).getStufe();
				int targetAnzahl = plugin.poiUser.getOnePlayer(p).getAnzahlBesuchterPOIs();
				int overall = plugin.poiPOIs.getNumberOfPOIs();
				player.sendMessage("Der " + ChatColor.AQUA + targetRang + " " + ChatColor.GOLD + targetName
						+ ChatColor.WHITE + " hat bereits " + ChatColor.GREEN + targetAnzahl + ChatColor.WHITE
						+ " von insgesamt " + ChatColor.GOLD + overall + ChatColor.WHITE
						+ " interessanten Orten gefunden!");
				player.sendMessage("Damit hat er die Stufe " + ChatColor.GOLD + targetStufe + ChatColor.WHITE
						+ " erreicht.");
			} else {
				player.sendMessage(ChatColor.RED + "Diesen Spieler gibt es nicht! ");
			}

		} else {
			player.sendMessage(ChatColor.RED + "Deine Stufe ist noch nicht hoch genug!");
		}
	}

	public void nearby(Player player, String args1) {
		if (isAdmin(player.getUniqueId())) {

			if (pointsOfInterestUtility.getPlayerByName(args1) != null) {
				double shortestdistance = Double.MAX_VALUE;
				double distance = 0;
				String name = "";
				int poiX = Integer.MAX_VALUE;
				int poiZ = Integer.MAX_VALUE;
				Player user = pointsOfInterestUtility.getPlayerByName(args1);

				String[] poiArray = (String[]) plugin.poiPOIs.getPoiLocations().keySet()
						.toArray(new String[plugin.poiPOIs.getPoiLocations().size()]);

				for (int i = 0; i < poiArray.length; i++) {

					distance = player.getLocation().distance(plugin.poiPOIs.getOnePOI(poiArray[i]).getPoiLocation());
					if (distance < shortestdistance) {
						shortestdistance = distance;
						name = poiArray[i];
						poiX = plugin.poiPOIs.getOnePOI(poiArray[i]).getPoiLocation().getBlockX();
						poiX = plugin.poiPOIs.getOnePOI(poiArray[i]).getPoiLocation().getBlockZ();
					}

				}

				ChatColor poiFound = ChatColor.GRAY;
				if (plugin.poiUser.playerFoundPOI(player, name)) {
					poiFound = ChatColor.GREEN;
				}

				String direktion = pointsOfInterestUtility.giveDirection(user, poiX, poiZ);

				player.sendMessage(ChatColor.GOLD + args1 + ChatColor.WHITE + " befindet sich in der Nähe von "
						+ poiFound + name + " !");
				player.sendMessage("" + name + " ist " + ChatColor.AQUA + shortestdistance + ChatColor.WHITE
						+ " Blöcke " + ChatColor.AQUA + direktion + ChatColor.WHITE + " vom User.");

			} else {
				player.sendMessage(ChatColor.RED + "Dieser Spieler ist gerade nicht online!");

			}

		} else {
			player.sendMessage(ChatColor.RED + "Dir fehlen die nötigen Berechtigungen für diesen Befehl.");

		}

	}

	public void list(Player player, String args1) {
		if (isAdmin(player.getUniqueId())) {
			if (plugin.poiPOIs.containsPOI(args1)) {
				Location l = plugin.poiPOIs.getOnePOI(args1).getPoiLocation();
				player.sendMessage("POI " + ChatColor.GOLD + args1 + ChatColor.WHITE + " befindet sich auf: "
						+ l.getWorld().getName());
				player.sendMessage("POI " + ChatColor.GOLD + args1 + ChatColor.WHITE + " x Koordinate: "
						+ String.valueOf(l.getBlockX()));
				player.sendMessage("POI " + ChatColor.GOLD + args1 + ChatColor.WHITE + " y Koordinate: "
						+ String.valueOf(l.getBlockY()));
				player.sendMessage("POI " + ChatColor.GOLD + args1 + ChatColor.WHITE + " z Koordinate: "
						+ String.valueOf(l.getBlockZ()));

			}

			else {
				// pr�ft ob ein eingegebener Name ein
				// registrierter Spielername ist
				Player p = pointsOfInterestUtility.getPlayerByName(args1);
				if (p != null) {

					String[] poiArray = (String[]) plugin.poiPOIs.getPoiLocations().keySet()
							.toArray(new String[plugin.poiPOIs.getPoiLocations().size()]);
					poiArray = pointsOfInterestUtility.alphsort(poiArray);
					int overall = plugin.poiPOIs.getNumberOfPOIs();
					int anzahl = plugin.poiUser.getOnePlayer(p).getAnzahlBesuchterPOIs();

					player.sendMessage("Der Spieler " + ChatColor.GOLD + args1 + ChatColor.WHITE
							+ " hat folgendede Pois bereits entdeckt:\n");

					for (int i = 0; i < poiArray.length; i++) {
						if (plugin.poiUser.playerFoundPOI(p, poiArray[i])) {
							player.sendMessage(ChatColor.GREEN + "" + poiArray[i]);
						} else {
							player.sendMessage(ChatColor.GRAY + "" + poiArray[i]);
						}
					}
					player.sendMessage("Der Spieler " + ChatColor.GOLD + args1 + ChatColor.WHITE + " hat bereits "
							+ ChatColor.GREEN + anzahl + ChatColor.WHITE + " von " + overall
							+ " interessanten Orten gefunden!");

				} else {
					player.sendMessage("Diesen Poi oder Spieler gibt es nicht.");

				}
			}
		} else {
			player.sendMessage("Dir fehlen die nötigen Berechtigungen für diesen Befehl.");

		}

	}

	public void set(Player player, String[] args1) {
		if (isAdmin(player.getUniqueId())) {
			if (args1[1].length() <= 2) {
				player.sendMessage("Der Poiname muss mindestens 3 Zeichen lang sein.");
			}
			String[] args = Arrays.copyOfRange(args1, 1, args1.length);
			String poiName = "";
			for (String s : args)
				poiName += s + " ";
			poiName = poiName.trim();
			plugin.poiPOIs.addPOI(poiName, player.getLocation());
			plugin.poiHonor.refreshHonorList();
			player.sendMessage("Der Point of Interest " + ChatColor.GOLD + poiName + ChatColor.WHITE
					+ " wurde erfolgreich an deiner Position gesetzt.");
		}
	}
	/*
	public void init(){




		
		List<CACommandArgument> l1 = new ArrayList<CACommandArgument>();
		l1.add(new CACommandArgumentString("reload", true, ""));
		CACommand c1 = new CACommand("pointsOfInterest", l1, false, false, true, true, "Dieser Befehl die Config-Datei neu", getExistingMethodByName("reload"));
		
		List<CACommandArgument> l2 = new ArrayList<CACommandArgument>();
		l2.add(new CACommandArgumentString("top5", true, ""));
		CACommand c2 = new CACommand("pointsOfInterest", l2, false, false, false, false, "Zeigt die fünf besten POI-Sucher an"+ ChatColor.RED + "[Benötigt Stufe 2]",  getExistingMethodByName("top5"));
		
		List<CACommandArgument> l3 = new ArrayList<CACommandArgument>();
		l3.add(new CACommandArgumentString("level", true, ""));
		CACommand c3 = new CACommand("pointsOfInterest", l3, false, false, false, false, "Zeigt die Levelübersicht an",  getExistingMethodByName("level"));
		
		List<CACommandArgument> l4 = new ArrayList<CACommandArgument>();
		l4.add(new CACommandArgumentString("wall", true, ""));
		CACommand c4 = new CACommand("pointsOfInterest", l4, false, false, false, false, "Zeigt die Ehrentafel ",  getExistingMethodByName("wall"));
				
		List<CACommandArgument> l5 = new ArrayList<CACommandArgument>();
		l5.add(new CACommandArgumentString("list", true, ""));
		CACommand c5 = new CACommand("pointsOfInterest", l5, false, false, false, false, "Zeigt alle bekannten Points of Interest an "+ ChatColor.RED + "[Benötigt Stufe 4]",  getExistingMethodByName("list"));
				
		
		List<CACommand> ca = new ArrayList<CACommand>();
		ca.add(c1);
		ca.add(c2);
		ca.add(c3);
		ca.add(c4);
		ca.add(c5);
		
		caCH = new CACommandHandler(ca);
		
	}
	
	private Method getExistingMethodByName(String name){
		Method[] ms = (this.getClass().getMethods());
		for(Method m : ms)		
		{
			if(name.equalsIgnoreCase(m.getName()))
					return m;
		}
		return null;
	}*/

}
