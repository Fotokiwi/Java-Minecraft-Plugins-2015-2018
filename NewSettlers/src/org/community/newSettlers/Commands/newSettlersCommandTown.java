package org.community.newSettlers.Commands;

import java.util.List;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.community.newSettlers.newSettlers;
import org.community.newSettlers.Town.Town;

public class newSettlersCommandTown {
		
	private final newSettlers plugin;
	 	 
	public newSettlersCommandTown(newSettlers plugin)
	{
		this.plugin = plugin;
	}
	
	public boolean getCommand(Player sender, Command cmd, String commandLabel, String[] args) {
		
		if(cmd.getName().equalsIgnoreCase("stadt")){
			if(args.length == 0) {
				Town town = plugin.nSCore.getPlayerTown(sender);
				if(town == null) {
					sender.sendMessage(ChatColor.DARK_GREEN + "Du bist kein Bürger einer Stadt.");
					return true;
				}
				sender.sendMessage(ChatColor.DARK_GREEN + "+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~");
				sender.sendMessage(ChatColor.DARK_GREEN + "Stadt: " + town.getName() + " (" + ChatColor.BLUE + town.getLevel() + ChatColor.DARK_GREEN + ")");
				sender.sendMessage(ChatColor.DARK_GREEN + "Beschreibung: " + town.getDescription());
				sender.sendMessage(ChatColor.DARK_GREEN + "Maximale Grundstücke: " + ChatColor.BLUE + town.getPlotLimit());
				sender.sendMessage(ChatColor.DARK_GREEN + "Aktuelle Grundstücke: " + ChatColor.BLUE + town.getPlots());
				sender.sendMessage(ChatColor.DARK_GREEN + "Stadtwache: " + town.hasTownGuard());
				sender.sendMessage(ChatColor.DARK_GREEN + "Bürgermeister: " + town.getMayor());
				sender.sendMessage(ChatColor.DARK_GREEN + "Verbündete: " + ChatColor.GREEN + town.getAllies());
				sender.sendMessage(ChatColor.DARK_GREEN + "Kriege: " + ChatColor.RED + town.getEnemies());
				sender.sendMessage(ChatColor.DARK_GREEN + "Einwohner: " + town.getMembers());
				sender.sendMessage(ChatColor.DARK_GREEN + "Reichspunkte: " + town.getRealmpoints());
				sender.sendMessage(ChatColor.DARK_GREEN + "+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~");
				return true;
			}
			if(args.length == 1) {	
				if(args[0].equalsIgnoreCase("help") || args[0].equalsIgnoreCase("hilfe") || args[0].equalsIgnoreCase("?")){					
					plugin.nSHelp.getCommand(sender, cmd, commandLabel, args);
					return true;
				}	
				Town town = plugin.nSCore.getPlayerTown(sender);
				/*if(town == null) {
					sender.sendMessage(ChatColor.DARK_RED + "Du bist kein B�rger einer Stadt.");
					return true;
				}*/
				if(args[0].equalsIgnoreCase("verlassen")){					
					if(town.isMayor(sender)) {
						sender.sendMessage(ChatColor.DARK_RED + "Du kannst die Stadt nicht verlassen ohne einen Nachfolger einzusetzen.");
						return true;
					}
					town.removeMember(sender);
					town.sendTownMessage(sender.getName() + " hat deine Stadt verlassen.");
					sender.sendMessage(ChatColor.DARK_GREEN + "Du hast deine Stadt verlassen. Ab sofort bist du ein Stadtloser.");
					return true;
				}		
				if(args[0].equalsIgnoreCase("offen")) {
					if(town.isMayor(sender) || town.isAssistant(sender.getName())) {

					} else {
						sender.sendMessage(ChatColor.DARK_RED + "Für diese Einstellung benötigst du Anführerrechte.");
						return true;
					}
					if(town.togglePublicStatus()) {
						sender.sendMessage(ChatColor.DARK_GREEN + "Die Stadt ist nun offen für neue Bürger.");
						return true;
					} else {
						sender.sendMessage(ChatColor.DARK_GREEN + "Die Stadt nimmt nun keine neuen Bürger auf.");
						return true;
					}
				}
				if(args[0].equalsIgnoreCase("auflösen")) {
					if(town.isMayor(sender)) {

					} else {
						sender.sendMessage(ChatColor.DARK_RED + "Für diese Einstellung benötigst du Anführerrechte.");
						return true;
					}
					plugin.nSTownDestroy.sendRequest(sender, "#TownDestroy#", sender);
					return true;
				}
				if(args[0].equalsIgnoreCase("freikaufen")) {
					if(town.isFormerMayor(sender)) {

					} else {
						sender.sendMessage(ChatColor.DARK_RED + "Nur der ehemalige Bürgermeister kann eine Stadt freikaufen.");
						return true;
					}
					town.runUpkeepCalculation(null, true);
					return true;
				}
				if(args[0].equalsIgnoreCase("expandieren")) {
					if(town.isMayor(sender) || town.isAssistant(sender.getName())) {

					} else {
						sender.sendMessage(ChatColor.DARK_RED + "Für diese Einstellung benötigst du Anführerrechte.");
						return true;
					}
					plugin.nSTownExpand.sendRequest(sender, "#TownExpand#", sender);
					return true;
				}
				/*if(args[0].equalsIgnoreCase("anwesend")){					
					Town town = plugin.nSCore.getPlayerTown(sender);
					if(town == null) {
						sender.sendMessage(ChatColor.DARK_GREEN + "Du bist kein Bürger einer Stadt.");
					} else {
						List<String> townMembers = town.getMemberList();
						sender.sendMessage(ChatColor.DARK_GREEN + "Aus deiner Stadt sind gerade aktiv:");
						for(int i = 0; i < townMembers.size(); i++) {
							if(plugin.getServer().getPlayer(townMembers.get(i)) != null) {
								sender.sendMessage(ChatColor.DARK_GREEN + townMembers.get(i));
							}
						}
					}
					return true;
				}
				if(args[0].equalsIgnoreCase("urbanisieren")){					
					Town town = plugin.nSCore.getPlayerTown(sender);
					town.claimTownPlot(sender);
					return true;
				}
				if(args[0].equalsIgnoreCase("verwildern")){					
					Town town = plugin.nSCore.getPlayerTown(sender);
					town.removeTownPlot(sender);
					return true;
				}*/
				if(plugin.nSCore.getTown(args[0]) == null) {
					sender.sendMessage(ChatColor.DARK_GREEN + "Wir können keine Stadt mit diesem Namen in den Registern finden.");
					return true;
				} else {
					Town otherTown = plugin.nSCore.getTown(args[0]);
					sender.sendMessage(ChatColor.DARK_GREEN + "+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~");
					sender.sendMessage(ChatColor.DARK_GREEN + "Stadt: " + otherTown.getName());
					sender.sendMessage(ChatColor.DARK_GREEN + "Beschreibung: " + otherTown.getDescription());
					sender.sendMessage(ChatColor.DARK_GREEN + "Bürgermeister: " + otherTown.getMayor());
					sender.sendMessage(ChatColor.DARK_GREEN + "Verbündete: " + ChatColor.GREEN + otherTown.getAllies());
					sender.sendMessage(ChatColor.DARK_GREEN + "Kriege: " + ChatColor.RED + otherTown.getEnemies());
					sender.sendMessage(ChatColor.DARK_GREEN + "Einwohner: " + otherTown.getMembers());
					sender.sendMessage(ChatColor.DARK_GREEN + "+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~");
					return true;
				}
			}
			if(args.length >= 2) {		
				if(args[0].equalsIgnoreCase("help") || args[0].equalsIgnoreCase("hilfe") || args[0].equalsIgnoreCase("?")){					
					plugin.nSHelp.getCommand(sender, cmd, commandLabel, args);
					return true;
				}
				if(args[0].equalsIgnoreCase("beitreten")){	
					Town town = plugin.nSCore.getPlayerTown(sender);
					if(town != null) {
						sender.sendMessage(ChatColor.DARK_RED + "Du bist bereits Bürger einer Stadt.");
					} else {
						if(plugin.nSCore.getTown(args[1]) == null) {
							sender.sendMessage(ChatColor.DARK_RED + "Wir können keine Stadt mit diesem Namen in den Registern finden.");
							return true;
						} else {
							town = plugin.nSCore.getTown(args[1]);
							if(plugin.nSCore.getChunkInfo(sender.getLocation().getWorld().getName() + "," + sender.getLocation().getChunk().getX() + "," + sender.getLocation().getChunk().getZ()) != town) {
								sender.sendMessage(ChatColor.DARK_RED + "Du musst dich innerhalb der Stadt aufhalten, der du beitreten möchtest.");
								return true;
							}
							if(System.currentTimeMillis() <= plugin.user.getLong("Spieler." + sender.getUniqueId().toString() + "." + town.getName() + ".TownJoinCooldown") + 604800000) {
								sender.sendMessage("Es ist zu kurz her, seit du diese Stadt verlassen hast!");
								return true;
							}
							if(town.isOpen()) {
								town.addMember(sender);
								sender.sendMessage(ChatColor.DARK_GREEN + "Du bist nun Bürger der Stadt " + town.getName());
								return true;
							} else {
								sender.sendMessage(ChatColor.DARK_RED + "Du kannst der Stadt nur nach einer Einladung beitreten.");
								sender.sendMessage(ChatColor.DARK_RED + "Bitte wende dich direkt an den Bürgermeister oder einen Minister.");
								return true;
							}
						}
					}
					return true;
				}	
				Town town = plugin.nSCore.getPlayerTown(sender);
				if(args[0].equalsIgnoreCase("einladen")){	
					if(town.isMayor(sender) || town.isAssistant(sender.getName())) {
						
					} else {
						sender.sendMessage(ChatColor.DARK_RED + "Für diese Einstellung benötigst du Anführerrechte.");
						return true;
					}
					if(plugin.getPlayerByName(args[1]) == null && plugin.getOfflinePlayerByName(args[1]) == null) {
						sender.sendMessage(ChatColor.DARK_RED + "Dieser Spieler steht nicht in unseren Registern.");
						return true;
					}
					if(plugin.getPlayerByName(args[1]) == null) {
						sender.sendMessage(ChatColor.DARK_RED + "Der Spieler ist zur Zeit nicht anwesend.");
						return true;
					}
					if(plugin.nSCore.getPlayerTown(plugin.getPlayerByName(args[1])) != null) {
						sender.sendMessage(ChatColor.DARK_RED + "Dieser Spieler ist bereits Einwohner einer Stadt.");
						return true;
					}
					if(args[1].equalsIgnoreCase(sender.getName())) {
						sender.sendMessage(ChatColor.DARK_RED + "Du kannst dich nicht selber einladen.");
						return true;
					}
					if(sender.getLocation().getWorld().getName().equalsIgnoreCase(plugin.getPlayerByName(args[1]).getLocation().getWorld().getName())) {
						if(sender.getLocation().distance(plugin.getPlayerByName(args[1]).getLocation()) > plugin.config.getDouble("System.Distance.Interact", 16)) {
							sender.sendMessage(ChatColor.DARK_RED + "Du bist zu weit von deinem Gegenüber entfernt, ihr müsst näher beisammen stehen.");
							return true;
						}
					} else {
						sender.sendMessage(ChatColor.DARK_RED + "Du bist zu weit von deinem Gegenüber entfernt, ihr müsst näher beisammen stehen.");
						return true;
					}
					
					plugin.nSTownInvite.sendRequest(sender, "#TownInvite#", plugin.getPlayerByName(args[1]));
					return true;
				}
				
				if(args[0].equalsIgnoreCase("rauswerfen")){	
					if(town.isMayor(sender)) {
						
					} else {
						sender.sendMessage(ChatColor.DARK_RED + "Für diese Einstellung benötigst du Anführerrechte.");
						return true;
					}
					if(args[1].equalsIgnoreCase(sender.getName())) {
						sender.sendMessage(ChatColor.DARK_RED + "Du kannst dich nicht selber rauswerfen.");
						return true;
					}
					OfflinePlayer op = plugin.getOfflinePlayerByName(args[1]);
					if(op == null) {
						sender.sendMessage(ChatColor.DARK_RED + "Dieser Spieler steht nicht in unseren Registern.");
						return true;
					}
					if(plugin.nSCore.getPlayerTown(op) == null){
						sender.sendMessage("Der Spieler gehört zu keiner Stadt");
						return true;
					}
					if(!plugin.nSCore.getPlayerTown(op).getName().equalsIgnoreCase(plugin.nSCore.getPlayerTown(sender).getName())) {
						sender.sendMessage(ChatColor.DARK_RED + "Dieser Spieler ist kein Einwohner deiner Stadt.");
						return true;
					}
					town.removeMember(op);			
					town.sendTownMessage(op.getName() + " ist aus der Stadt geworfen worden.");
					sender.sendMessage(ChatColor.DARK_GREEN + "Du hast " + args[1] + " aus deiner Stadt geworfen.");
					return true;
				}
				
				
				
				if(args[0].equalsIgnoreCase("spawn")){	
					if(town.isMayor(sender) || town.isAssistant(sender.getName())) {
						
					} else {
						sender.sendMessage(ChatColor.DARK_RED + "Für diese Einstellung benötigst du Anführerrechte.");
						return true;
					}
					if(!town.hasBuilding("Friedhof")) {
						sender.sendMessage(ChatColor.DARK_RED + "Die Funktion kann erst aktiviert werden, wenn die Stadt einen Friedhof besitzt.");
						return true;
					}
					if(args[1].equalsIgnoreCase("setzen")) {
						plugin.nSCore.setSpawn(sender.getLocation(), sender);
						return true;
					}
					return true;
				}
				if(args[0].equalsIgnoreCase("stadtwache")){	
					if(town.isMayor(sender) || town.isAssistant(sender.getName())) {
						
					} else {
						sender.sendMessage(ChatColor.DARK_RED + "Für diese Einstellung benötigst du Anführerrechte.");
						return true;
					}
					if(!town.hasBuilding("Stadtwache")) {
						sender.sendMessage(ChatColor.DARK_RED + "Die Funktion kann erst aktiviert werden, wenn die Stadt eine Stadtwache besitzt.");
						return true;
					}

					if(!town.isCooldownAllowed("TownGuard")) {
						sender.sendMessage(ChatColor.DARK_RED + "Der letzte Wechsel der Änderung dieser Berechtigung liegt noch nicht lange genug zurück.");
						return true;
					}
					if(args[1].equalsIgnoreCase("einrichten")) {
						if(town.toggleTownGuardStatus()) {
							//sender.sendMessage(ChatColor.DARK_GREEN + "Bürgern ist es nun gestattet Blöcke abzubauen.");
							town.sendTownMessage("Deine Stadtwache verhindert jetzt den Monsterspawn.");
							return true;
						} else {
							//sender.sendMessage(ChatColor.DARK_GREEN + "Bürgern ist es nun verwehrt Blöcke abzubauen.");
							town.sendTownMessage("Deine Stadtwache verhindert nicht länger den Monsterspawn.");
							return true;
						}
					}
					return true;
				}
				
				if(town == null) {
					sender.sendMessage(ChatColor.DARK_RED + "Du bist kein Bürger einer Stadt.");
					return true;
				}	
				if(args[0].equalsIgnoreCase("stadtlager")){	
					/*if(town.isMayor(sender) || town.isAssistant(sender.getName())) {
						
					} else {
						sender.sendMessage(ChatColor.DARK_GREEN + "Für diese Einstellung benötigst du Anführerrechte.");
						return true;
					}*/
					if(args[1].equalsIgnoreCase("einlagern")) {
						if(plugin.nSCore.getChunkInfo(sender.getLocation().getWorld().getName() + "," + sender.getLocation().getChunk().getX() + "," + sender.getLocation().getChunk().getZ()) != town) {
							sender.sendMessage(ChatColor.DARK_RED + "Das Stadtlager kann nur innerhalb der Stadt befüllt werden.");
							return true;
						}
						town.saveInventoryToTown((Player) sender);
						sender.sendMessage(ChatColor.DARK_GREEN + "Du hast alle nötigen Ressourcen eingelagert.");
						return true;
					}
				}		
				if(args[0].equalsIgnoreCase("mitteilung")) {
					String message = "";
					for(int i = 1; i < args.length; i++) {
						message += args[i] + " ";
					}
					message = message.substring(0, message.length()-1);
					town.setTownMessage(message);
					sender.sendMessage(ChatColor.DARK_GREEN + "Die Stadtmitteilung wurde aktualisiert:");
					sender.sendMessage(ChatColor.DARK_GREEN + message);
					return true;
				}
				if(args[0].equalsIgnoreCase("rechte")) {					
					if(args[1].equalsIgnoreCase("zurücksetzen")) {
						if(town.isMayor(sender) || town.isAssistant(sender.getName())) {

						} else {
							sender.sendMessage(ChatColor.DARK_RED + "Für diese Einstellung benötigst du Anführerrechte.");
							return true;
						}
						town.setDefaultPermissions();
						sender.sendMessage(ChatColor.DARK_GREEN + "Alle Berechtigungen wurden auf den Standardwert gesetzt.");
						return true;
					}
				}
				if(args[0].equalsIgnoreCase("grundstück")) {
					if(args[1].equalsIgnoreCase("urbanisieren")) {
						if(town.isMayor(sender) || town.isAssistant(sender.getName())) {

						} else {
							sender.sendMessage(ChatColor.DARK_RED + "Für diese Einstellung benötigst du Anführerrechte.");
							return true;
						}
						town.claimTownPlot(sender);
						return true;
					}
					if(args[1].equalsIgnoreCase("verwildern")) {
						if(town.isMayor(sender) || town.isAssistant(sender.getName())) {

						} else {
							sender.sendMessage(ChatColor.DARK_RED + "Für diese Einstellung benötigst du Anführerrechte.");
							return true;
						}
						//town.removeTownPlot(sender);
						sender.sendMessage(ChatColor.DARK_RED + "Dieser Befehl steht nur Administratoren zur Verfügung.");
						return true;
					}
				}
				if(args[0].equalsIgnoreCase("anzeigen")) {
					if(args[1].equalsIgnoreCase("stadtverwaltung")) {
						sender.sendMessage(ChatColor.DARK_GREEN + "");
						sender.sendMessage(ChatColor.DARK_GREEN + "+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~");
						sender.sendMessage(ChatColor.DARK_GREEN + "                                  Stadtverwaltung");
						sender.sendMessage(ChatColor.DARK_GREEN + "+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~");
						sender.sendMessage(ChatColor.DARK_GREEN + "Bürgermeister: " + ChatColor.GREEN + town.getMayor());
						sender.sendMessage(ChatColor.DARK_GREEN + "Assistenten: " + ChatColor.GREEN + town.getAssistants());
						sender.sendMessage(ChatColor.DARK_GREEN + "Diplomat: " + ChatColor.GREEN + town.getDiplomat());
						sender.sendMessage(ChatColor.DARK_GREEN + "+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~");
						sender.sendMessage(ChatColor.DARK_GREEN + "");
						return true;
					}
					if(args[1].equalsIgnoreCase("stadtrechte")) {
						String einwohner = "";
						String alliierte = "";
						String fremde = "";
						if(town.isBreakAllowed()) { einwohner += ChatColor.GREEN + "Bauen" + ChatColor.DARK_GREEN + " / "; } else { einwohner += ChatColor.RED + "Bauen" + ChatColor.DARK_GREEN + " / "; }
						if(town.isBuildAllowed()) { einwohner += ChatColor.GREEN + "Abbauen" + ChatColor.DARK_GREEN + " / "; } else { einwohner += ChatColor.RED + "Abbauen" + ChatColor.DARK_GREEN + " / "; }
						if(town.isCraftAllowed()) { einwohner += ChatColor.GREEN + "Herstellen" + ChatColor.DARK_GREEN + " / "; } else { einwohner += ChatColor.RED + "Herstellen" + ChatColor.DARK_GREEN + " / "; }
						if(town.isInteractAllowed()) { einwohner += ChatColor.GREEN + "Schalten" + ChatColor.DARK_GREEN + " / "; } else { einwohner += ChatColor.RED + "Schalten"; }
						if(town.isBreakAllowedAlly()) { alliierte += ChatColor.GREEN + "Bauen" + ChatColor.DARK_GREEN + " / "; } else { alliierte += ChatColor.RED + "Bauen" + ChatColor.DARK_GREEN + " / "; }
						if(town.isBuildAllowedAlly()) { alliierte += ChatColor.GREEN + "Abbauen" + ChatColor.DARK_GREEN + " / "; } else { alliierte += ChatColor.RED + "Abbauen" + ChatColor.DARK_GREEN + " / "; }
						if(town.isCraftAllowedAlly()) { alliierte += ChatColor.GREEN + "Herstellen" + ChatColor.DARK_GREEN + " / "; } else { alliierte += ChatColor.RED + "Herstellen" + ChatColor.DARK_GREEN + " / "; }
						if(town.isInteractAllowedAlly()) { alliierte += ChatColor.GREEN + "Schalten" + ChatColor.DARK_GREEN + " / "; } else { alliierte += ChatColor.RED + "Schalten"; }
						if(town.isBreakAllowedOutsider()) { fremde += ChatColor.GREEN + "Bauen" + ChatColor.DARK_GREEN + " / "; } else { fremde += ChatColor.RED + "Bauen" + ChatColor.DARK_GREEN + " / "; }
						if(town.isBuildAllowedOutsider()) { fremde += ChatColor.GREEN + "Abbauen" + ChatColor.DARK_GREEN + " / "; } else { fremde += ChatColor.RED + "Abbauen" + ChatColor.DARK_GREEN + " / "; }
						if(town.isCraftAllowedOutsider()) { fremde += ChatColor.GREEN + "Herstellen" + ChatColor.DARK_GREEN + " / "; } else { fremde += ChatColor.RED + "Herstellen" + ChatColor.DARK_GREEN + " / "; }
						if(town.isInteractAllowedOutsider()) { fremde += ChatColor.GREEN + "Schalten" + ChatColor.DARK_GREEN + " / "; } else { fremde += ChatColor.RED + "Schalten"; }
						sender.sendMessage(ChatColor.DARK_GREEN + "");
						sender.sendMessage(ChatColor.DARK_GREEN + "+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~");
						sender.sendMessage(ChatColor.DARK_GREEN + "                                  Stadtrechte");
						sender.sendMessage(ChatColor.DARK_GREEN + "+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~");
						sender.sendMessage(ChatColor.DARK_GREEN + "Einwohner: " + einwohner);
						sender.sendMessage(ChatColor.DARK_GREEN + "Alliierte: " + alliierte);
						sender.sendMessage(ChatColor.DARK_GREEN + "Fremde: " + fremde);
						sender.sendMessage(ChatColor.DARK_GREEN + "+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~");
						sender.sendMessage(ChatColor.DARK_GREEN + "");
						return true;
					}
					if(args[1].equalsIgnoreCase("baustoffe")) {
						Map<String, String> storageType = plugin.nSCore.getRessourceType();
						
						sender.sendMessage(ChatColor.DARK_GREEN + "");
						sender.sendMessage(ChatColor.DARK_GREEN + "+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~");
						sender.sendMessage(ChatColor.DARK_GREEN + "                                  Baustoffe");
						sender.sendMessage(ChatColor.DARK_GREEN + "+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~");
						
						for(Map.Entry<String, Integer> entry : town.getTownStorage().entrySet()) {
							if(storageType.get(entry.getKey()) != null && storageType.get(entry.getKey()).equalsIgnoreCase("Plot")) {
								sender.sendMessage(ChatColor.DARK_GREEN + entry.getKey() + ": " + ChatColor.BLUE +  entry.getValue());
							}				
						}
						
						sender.sendMessage(ChatColor.DARK_GREEN + "+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~");
						sender.sendMessage(ChatColor.DARK_GREEN + "");
						
						return true;
					}
					if(args[1].equalsIgnoreCase("produkte")) {
						
						Map<String, String> storageType = plugin.nSCore.getRessourceType();
						
						sender.sendMessage(ChatColor.DARK_GREEN + "");
						sender.sendMessage(ChatColor.DARK_GREEN + "+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~");
						sender.sendMessage(ChatColor.DARK_GREEN + "                                  Produkte");
						sender.sendMessage(ChatColor.DARK_GREEN + "+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~");
						
						for(Map.Entry<String, Integer> entry : town.getTownStorage().entrySet()) {
							if(storageType.get(entry.getKey()) != null && storageType.get(entry.getKey()).equalsIgnoreCase("Production")) {
								sender.sendMessage(ChatColor.DARK_GREEN + entry.getKey() + ": " + ChatColor.BLUE +  entry.getValue());
							}				
						}
						
						sender.sendMessage(ChatColor.DARK_GREEN + "+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~");
						sender.sendMessage(ChatColor.DARK_GREEN + "");
						
						return true;
					}
					if(args[1].equalsIgnoreCase("lebensmittel")) {
						Map<String, String> storageType = plugin.nSCore.getRessourceType();
						
						sender.sendMessage(ChatColor.DARK_GREEN + "");
						sender.sendMessage(ChatColor.DARK_GREEN + "+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~");
						sender.sendMessage(ChatColor.DARK_GREEN + "                                  Lebensmittel");
						sender.sendMessage(ChatColor.DARK_GREEN + "+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~");
						
						for(Map.Entry<String, Integer> entry : town.getTownStorage().entrySet()) {
							//sender.sendMessage("DEBUG: " + storageType.get(entry.getKey()));
							if(storageType.get(entry.getKey()) != null && storageType.get(entry.getKey()).equalsIgnoreCase("Citizen")) {
								sender.sendMessage(ChatColor.DARK_GREEN + entry.getKey() + ": " + ChatColor.BLUE +  entry.getValue());
							}				
						}
						
						sender.sendMessage(ChatColor.DARK_GREEN + "+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~");
						sender.sendMessage(ChatColor.DARK_GREEN + "");
						
						return true;
					}
					if(args[1].equalsIgnoreCase("verteidigung")) {
						Map<String, String> storageType = plugin.nSCore.getRessourceType();
						
						sender.sendMessage(ChatColor.DARK_GREEN + "");
						sender.sendMessage(ChatColor.DARK_GREEN + "+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~");
						sender.sendMessage(ChatColor.DARK_GREEN + "                                  Verteidigung");
						sender.sendMessage(ChatColor.DARK_GREEN + "+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~");
						
						for(Map.Entry<String, Integer> entry : town.getTownStorage().entrySet()) {
							if(storageType.get(entry.getKey()) != null && storageType.get(entry.getKey()).equalsIgnoreCase("Ally")) {
								sender.sendMessage(ChatColor.DARK_GREEN + entry.getKey() + ": " + ChatColor.BLUE +  entry.getValue());
							}				
						}
						
						sender.sendMessage(ChatColor.DARK_GREEN + "+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~");
						sender.sendMessage(ChatColor.DARK_GREEN + "");
												
						return true;
					}
					if(args[1].equalsIgnoreCase("gebäude")) {
						sender.sendMessage(ChatColor.DARK_GREEN + "");
						sender.sendMessage(ChatColor.DARK_GREEN + "+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~");
						sender.sendMessage(ChatColor.DARK_GREEN + "                                  Stadtgebäude");
						sender.sendMessage(ChatColor.DARK_GREEN + "+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~");
						
						List<?> list = plugin.config.getList("TownUpgrade.Level2");
						String stufe = ChatColor.DARK_GREEN + "Stufe 2: ";
						for(int i = 0; i < list.size(); i++) {
							if(town.hasBuilding("" + list.get(i))) { stufe += ChatColor.GREEN + "" + list.get(i) + ", "; } else { stufe += ChatColor.GRAY + "" + list.get(i) + ", ";}
						}
						sender.sendMessage(stufe);
						
						list = plugin.config.getList("TownUpgrade.Level3");
						stufe = ChatColor.DARK_GREEN + "Stufe 3: ";
						for(int j = 0; j < list.size(); j++) {
							if(town.hasBuilding("" + list.get(j))) { stufe += ChatColor.GREEN + "" + list.get(j) + ", "; } else { stufe += ChatColor.GRAY + "" + list.get(j) + ", ";}
						}
						sender.sendMessage(stufe);
						
						list = plugin.config.getList("TownUpgrade.Level4");
						stufe = ChatColor.DARK_GREEN + "Stufe 4: ";
						for(int k = 0; k < list.size(); k++) {
							if(town.hasBuilding("" + list.get(k))) { stufe += ChatColor.GREEN + "" + list.get(k) + ", "; } else { stufe += ChatColor.GRAY + "" + list.get(k) + ", ";}
						}
						sender.sendMessage(stufe);
						
						list = plugin.config.getList("TownUpgrade.Level5");
						stufe = ChatColor.DARK_GREEN + "Stufe 5: ";
						for(int l = 0; l < list.size(); l++) {
							if(town.hasBuilding("" + list.get(l))) { stufe += ChatColor.GREEN + "" + list.get(l) + ", "; } else { stufe += ChatColor.GRAY + "" + list.get(l) + ", ";}
						}
						sender.sendMessage(stufe);
						
						list = plugin.config.getList("TownUpgrade.Level6");
						stufe = ChatColor.DARK_GREEN + "Stufe 6: ";
						for(int m = 0; m < list.size(); m++) {
							if(town.hasBuilding("" + list.get(m))) { stufe += ChatColor.GREEN + "" + list.get(m) + ", "; } else { stufe += ChatColor.GRAY + "" + list.get(m) + ", ";}
						}
						sender.sendMessage(stufe);
						
						list = plugin.config.getList("TownUpgrade.Level7");
						stufe = ChatColor.DARK_GREEN + "Stufe 7: ";
						for(int n = 0; n < list.size(); n++) {
							if(town.hasBuilding("" + list.get(n))) { stufe += ChatColor.GREEN + "" + list.get(n) + ", "; } else { stufe += ChatColor.GRAY + "" + list.get(n) + ", ";}
						}
						sender.sendMessage(stufe);
						
						list = plugin.config.getList("TownUpgrade.Level8");
						stufe = ChatColor.DARK_GREEN + "Stufe 8: ";
						for(int o = 0; o < list.size(); o++) {
							if(town.hasBuilding("" + list.get(o))) { stufe += ChatColor.GREEN + "" + list.get(o) + ", "; } else { stufe += ChatColor.GRAY + "" + list.get(o) + ", ";}
						}
						sender.sendMessage(stufe);
						
						sender.sendMessage(ChatColor.DARK_GREEN + "+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~");
						sender.sendMessage(ChatColor.DARK_GREEN + "");
						
						return true;
					}
					if(args[1].equalsIgnoreCase("produktion")) {
						Map<String, Boolean> buildings = town.getProductionBuildings();
						
						sender.sendMessage(ChatColor.DARK_GREEN + "");
						sender.sendMessage(ChatColor.DARK_GREEN + "+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~");
						sender.sendMessage(ChatColor.DARK_GREEN + "                                  Produktionsgebäude");
						sender.sendMessage(ChatColor.DARK_GREEN + "+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~");
						String stufe = ChatColor.DARK_GREEN + "Gebäude: ";
						for(Map.Entry<String, Boolean> entry : buildings.entrySet()) {
							if(entry.getValue() == true) { stufe += ChatColor.GREEN + "" + entry.getKey() + ", "; } else { stufe += ChatColor.GRAY + "" + entry.getKey() + ", ";}				
						}
						sender.sendMessage(stufe);
						sender.sendMessage(ChatColor.DARK_GREEN + "+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~");
						sender.sendMessage(ChatColor.DARK_GREEN + "");
						return true;
					}
					if(args[1].equalsIgnoreCase("stadtlager")) {
						Map<String, Integer> storageLevel = plugin.nSCore.getRessourceLevel();
						sender.sendMessage(ChatColor.DARK_GREEN + "+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~");
						sender.sendMessage(ChatColor.DARK_GREEN + "                                  Stadtlager");
						sender.sendMessage(ChatColor.DARK_GREEN + "+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~");
						for(Map.Entry<String, Integer> entry : town.getTownStorage().entrySet()) {
							if(storageLevel.get(entry.getKey()) != null /*&& storageLevel.get(entry.getKey()) <= town.getLevel()*/) {
								sender.sendMessage(ChatColor.DARK_GREEN + entry.getKey() + ": " + ChatColor.BLUE + entry.getValue());
							}				
						}
						sender.sendMessage(ChatColor.DARK_GREEN + "+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~");
						return true;
					}
					if(args[1].equalsIgnoreCase("kosten")) {
						town.runUpkeepCalculation(sender, false);
						return true;
					}
				}
			}
			if(args.length >= 3) {
				if(args[0].equalsIgnoreCase("freischalten")) {
					if(!plugin.nSCore.isAdmin(sender) && !plugin.nSCore.isMod(sender)) {
						sender.sendMessage(ChatColor.DARK_RED + "Du benötigst administrative Rechte für diesen Befehl.");
						return true;
					}
					Town requestTown = plugin.nSCore.getTown(args[2]);
					if(requestTown == null) {
						sender.sendMessage(ChatColor.DARK_RED + "Diese Stadt steht nicht in unseren Registern.");
						return true;
					}
					if(args[1].equalsIgnoreCase("gebäude")) {								
						if(!plugin.nSCore.buildingListContains(args[3])) {
							sender.sendMessage(ChatColor.DARK_RED + "Das angegebene Gebäude ist nicht gültig.");
							return true;
						}
						String townBuilding = Character.toUpperCase(args[3].charAt(0)) + args[3].substring(1);
						if(requestTown.getBuildingList().contains(townBuilding)) {
							sender.sendMessage(ChatColor.DARK_RED + "Das angegebene Gebäude ist bereits auf die Stadt registriert.");
							return true;
						}
						plugin.nSBuildingInvite.sendRequest(sender, "#BuildingInvite:" + args[2] + ":" + townBuilding + "#", sender);
						return true;
					}
				}
				Town town = plugin.nSCore.getPlayerTown(sender);
				if(town == null) {
					sender.sendMessage(ChatColor.DARK_RED + "Du bist kein Bürger einer Stadt.");
					return true;
				}
				if(args[0].equalsIgnoreCase("assistent")) {
					if(town.isMayor(sender) || town.isAssistant(sender.getName())) {

					} else {
						sender.sendMessage(ChatColor.DARK_RED + "Für diese Einstellung benötigst du Anführerrechte.");
						return true;
					}
					if(args[1].equalsIgnoreCase("einsetzen")) {
						if(plugin.getPlayerByName(args[2]) == null && plugin.getOfflinePlayerByName(args[2]) == null) {
							sender.sendMessage(ChatColor.DARK_RED + "Dieser Spieler steht nicht in unseren Registern.");
							return true;
						}
						if(town.isMember(args[2]) == false) {
							sender.sendMessage(ChatColor.DARK_RED + "Du kannst nur einen Bürger deiner Stadt zum Assistenten befördern.");
							return true;
						}
						if(args[2].equalsIgnoreCase(sender.getName())) {
							sender.sendMessage(ChatColor.DARK_RED + "Du kannst dich nicht selber zum Assistenten befördern.");
							return true;
						}
						if(sender.getLocation().distance(plugin.getPlayerByName(args[2]).getLocation()) > plugin.config.getDouble("System.Distance.Interact", 16)) {
							sender.sendMessage(ChatColor.DARK_RED + "Du bist zu weit von deinem Gegenüber entfernt, ihr müsst näher beisammen stehen.");
						}
						town.addAssistant(args[2]);
						//sender.sendMessage(ChatColor.DARK_GREEN + "Du hast " + args[2] + " zum Assistenten befördert.");
						town.sendTownMessage(args[2] + " wurde zum Assistenten des Bürgermeisters befördert.");
						return true;
					}
					if(args[1].equalsIgnoreCase("entlassen")) {
						if(args[2].equalsIgnoreCase(sender.getName())) {
							sender.sendMessage(ChatColor.DARK_RED + "Du kannst dich nicht selber aus dem Assistentenamt entlassen.");
							return true;
						}
						if(town.isAssistant(args[2]) == false) {
							sender.sendMessage(ChatColor.DARK_RED + args[2] + " ist kein Assistent in deiner Stadt.");
							return true;
						}
						town.removeAssistant(args[2]);
						//sender.sendMessage(ChatColor.DARK_GREEN + "Du hast " + args[2] + " aus dem Assistentenamt entlassen.");
						town.sendTownMessage(args[2] + " wurde aus dem Assistentenamt entlassen.");
						return true;
					}
				}
				if(args[0].equalsIgnoreCase("diplomat")) {
					if(town.isMayor(sender) || town.isAssistant(sender.getName())) {

					} else {
						sender.sendMessage(ChatColor.DARK_RED + "Für diese Einstellung benötigst du Anführerrechte.");
						return true;
					}
					if(town.getLevel() < 5) {
						sender.sendMessage(ChatColor.DARK_RED + "Diplomatische Optionen stehen erst ab dem Stadtstatus (lvl 5) zur Verfügung.");
						return true;
					}
					if(args[1].equalsIgnoreCase("einsetzen")) {
						if(plugin.getPlayerByName(args[2]) == null && plugin.getOfflinePlayerByName(args[2]) == null) {
							sender.sendMessage(ChatColor.DARK_RED + "Dieser Spieler steht nicht in unseren Registern.");
							return true;
						}
						if(town.isMember(args[2]) == false) {
							sender.sendMessage(ChatColor.DARK_RED + "Du kannst nur einen Bürger deiner Stadt zum Diplomaten befördern.");
							return true;
						}
						if(sender.getLocation().distance(plugin.getPlayerByName(args[2]).getLocation()) > plugin.config.getDouble("System.Distance.Interact", 16)) {
							sender.sendMessage(ChatColor.DARK_RED + "Du bist zu weit von deinem Gegenüber entfernt, ihr müsst näher beisammen stehen.");
						}
						town.setDiplomat(args[2]);
						//sender.sendMessage(ChatColor.DARK_GREEN + "Du hast " + args[2] + " zum Assistenten bef�rdert.");
						town.sendTownMessage(args[2] + " wurde zum Diplomaten der Stadt befördert.");
						return true;
					}
					if(args[1].equalsIgnoreCase("entlassen")) {
						if(town.isDiplomat(plugin.getPlayerByName(args[2])) == false) {
							sender.sendMessage(ChatColor.DARK_RED + args[2] + " ist kein Diplomat in deiner Stadt.");
							return true;
						}
						town.removeDiplomat(args[2]);
						//sender.sendMessage(ChatColor.DARK_GREEN + "Du hast " + args[2] + " aus dem Assistentenamt entlassen.");
						town.sendTownMessage(args[2] + " wurde aus dem Diplomatenamt entlassen.");
						return true;
					}
				}
				if(args[0].equalsIgnoreCase("bürgermeister")) {
					if(!town.isMayor(sender)) {
						sender.sendMessage(ChatColor.DARK_RED + "Nur der Bürgermeister kann seinen Nachfolger einsetzen.");
						return true;
					}
					if(args[1].equalsIgnoreCase("einsetzen")) {
						if(plugin.getPlayerByName(args[2]) == null && plugin.getOfflinePlayerByName(args[2]) == null) {
							sender.sendMessage(ChatColor.DARK_RED + "Dieser Spieler steht nicht in unseren Registern.");
							return true;
						}
						if(town.isMember(args[2]) == false) {
							sender.sendMessage(ChatColor.DARK_RED + "Du kannst nur einen Bürger deiner Stadt zum Bürgermeister befördern.");
							return true;
						}
						if(args[2].equalsIgnoreCase(sender.getName())) {
							sender.sendMessage(ChatColor.DARK_RED + "Du bist bereits Bürgermeister deiner Stadt.");
							return true;
						}
						if(sender.getWorld().getName().equalsIgnoreCase((plugin.getPlayerByName(args[2]).getWorld().getName()))) {
							if(sender.getLocation().distance(plugin.getPlayerByName(args[2]).getLocation()) > plugin.config.getDouble("System.Distance.Interact", 16)) {
								sender.sendMessage(ChatColor.DARK_RED + "Du bist zu weit von deinem Gegenüber entfernt, ihr müsst näher beisammen stehen.");
								return true;
							}
						} else {
							sender.sendMessage(ChatColor.DARK_RED + "Du bist zu weit von deinem Gegenüber entfernt, ihr müsst näher beisammen stehen.");
							return true;
						}
						town.setMayor(args[2]);
						//sender.sendMessage(ChatColor.DARK_GREEN + "Du hast " + args[2] + " zum B�rgermeister bef�rdert.");
						town.sendTownMessage(args[2] + " ist der neue Bürgermeister deiner Stadt.");
						return true;
					}
				}
				if(args[0].equalsIgnoreCase("rechte")) {
					if(town.isMayor(sender) || town.isAssistant(sender.getName())) {

					} else {
						sender.sendMessage(ChatColor.DARK_RED + "Für diese Einstellung benötigst du Anführerrechte.");
						return true;
					}
					if(plugin.nSCore.getChunkInfo(sender.getLocation().getWorld().getName() + "," + sender.getLocation().getChunk().getX() + "," + sender.getLocation().getChunk().getZ()) != town) {
						sender.sendMessage(ChatColor.DARK_RED + "Die Stadtrechte können nur innerhalb des Stadtgebietes verändert werden.");
						return true;
					}
					if(args[1].equalsIgnoreCase("bürger")) {
						if(args[2].equalsIgnoreCase("abbauen")) {
							if(town.toggleBreakAllowed()) {
								//sender.sendMessage(ChatColor.DARK_GREEN + "B�rgern ist es nun gestattet Bl�cke abzubauen.");
								town.sendTownMessage("Deine Stadt gewährt nun allen Bürgern das Abbaurecht.");
								return true;
							} else {
								//sender.sendMessage(ChatColor.DARK_GREEN + "B�rgern ist es nun verwehrt Bl�cke abzubauen.");
								town.sendTownMessage("Deine Stadt verwehrt nun allen Bürgern das Abbaurecht.");
								return true;
							}
						}
						if(args[2].equalsIgnoreCase("bauen")) {
							if(town.toggleBuildAllowed()) {
								//sender.sendMessage(ChatColor.DARK_GREEN + "B�rgern ist es nun gestattet Bl�cke zu setzen.");
								town.sendTownMessage("Deine Stadt gewährt nun allen Bürgern das Baurecht.");
								return true;
							} else {
								//sender.sendMessage(ChatColor.DARK_GREEN + "B�rgern ist es nun verwehrt Bl�cke zu setzen.");
								town.sendTownMessage("Deine Stadt verwehrt nun allen Bürgern das Baurecht.");
								return true;
							}
						}
						if(args[2].equalsIgnoreCase("herstellen")) {
							if(town.toggleCraftAllowed()) {
								//sender.sendMessage(ChatColor.DARK_GREEN + "B�rgern ist es nun gestattet Waren herzustellen.");
								town.sendTownMessage("Deine Stadt gewährt nun allen Bürgern das Handwerksrecht.");
								return true;
							} else {
								//sender.sendMessage(ChatColor.DARK_GREEN + "B�rgern ist es nun verwehrt Waren herzustellen.");
								town.sendTownMessage("Deine Stadt verwehrt nun allen Bürgern das Handwerksrecht.");
								return true;
							}
						}
						if(args[2].equalsIgnoreCase("schalten")) {
							if(town.toggleInteractAllowed()) {
								//sender.sendMessage(ChatColor.DARK_GREEN + "B�rgern ist es nun gestattet Waren herzustellen.");
								town.sendTownMessage("Deine Stadt gewährt nun allen Bürgern das Schaltrecht.");
								return true;
							} else {
								//sender.sendMessage(ChatColor.DARK_GREEN + "B�rgern ist es nun verwehrt Waren herzustellen.");
								town.sendTownMessage("Deine Stadt verwehrt nun allen Bürgern das Schaltrecht.");
								return true;
							}
						}
						if(args[2].equalsIgnoreCase("schützen")) {
							if(town.toggleProtectAllowed()) {
								//sender.sendMessage(ChatColor.DARK_GREEN + "B�rgern ist es nun gestattet Waren herzustellen.");
								town.sendTownMessage("Deine Stadt gewährt nun allen Bürgern das Schutzrecht.");
								return true;
							} else {
								//sender.sendMessage(ChatColor.DARK_GREEN + "B�rgern ist es nun verwehrt Waren herzustellen.");
								town.sendTownMessage("Deine Stadt verwehrt nun allen Bürgern das Schutzrecht.");
								return true;
							}
						}
					}
					if(args[1].equalsIgnoreCase("fremde")) {
						if(args[2].equalsIgnoreCase("abbauen")) {
							if(!town.isCooldownAllowed("OutsiderBreak")) {
								sender.sendMessage(ChatColor.DARK_RED + "Der letzte Wechsel der Änderung dieser Berechtigung liegt noch nicht lange genug zurück.");
								return true;
							}
							if(town.toggleBreakAllowedOutsider()) {
								//sender.sendMessage(ChatColor.DARK_GREEN + "Fremden ist es nun gestattet Bl�cke abzubauen.");
								town.sendTownMessage("Deine Stadt gewährt nun allen Fremden das Abbaurecht.");
								return true;
							} else {
								//sender.sendMessage(ChatColor.DARK_GREEN + "Fremden ist es nun verwehrt Bl�cke abzubauen.");
								town.sendTownMessage("Deine Stadt verwehrt nun allen Fremden das Abbaurecht.");
								return true;
							}
						}
						if(args[2].equalsIgnoreCase("bauen")) {
							if(!town.isCooldownAllowed("OutsiderBuild")) {
								sender.sendMessage(ChatColor.DARK_RED + "Der letzte Wechsel der Änderung dieser Berechtigung liegt noch nicht lange genug zurück.");
								return true;
							}
							if(town.toggleBuildAllowedOutsider()) {
								//sender.sendMessage(ChatColor.DARK_GREEN + "Fremden ist es nun gestattet Bl�cke zu setzen.");
								town.sendTownMessage("Deine Stadt gewährt nun allen Fremden das Baurecht.");
								return true;
							} else {
								//sender.sendMessage(ChatColor.DARK_GREEN + "Fremden ist es nun verwehrt Bl�cke zu setzen.");
								town.sendTownMessage("Deine Stadt verwehrt nun allen Fremden das Baurecht.");
								return true;
							}
						}
						if(args[2].equalsIgnoreCase("herstellen")) {
							if(!town.isCooldownAllowed("OutsiderCraft")) {
								sender.sendMessage(ChatColor.DARK_RED + "Der letzte Wechsel der Änderung dieser Berechtigung liegt noch nicht lange genug zurück.");
								return true;
							}
							if(town.toggleCraftAllowedOutsider()) {
								//sender.sendMessage(ChatColor.DARK_GREEN + "Fremden ist es nun gestattet Waren herzustellen.");
								town.sendTownMessage("Deine Stadt gewährt nun allen Fremden das Handwerksrecht.");
								return true;
							} else {
								//sender.sendMessage(ChatColor.DARK_GREEN + "Fremden ist es nun verwehrt Waren herzustellen.");
								town.sendTownMessage("Deine Stadt verwehrt nun allen Fremden das Handwerksrecht.");
								return true;
							}
						}
						if(args[2].equalsIgnoreCase("schalten")) {
							if(!town.isCooldownAllowed("OutsiderInteract")) {
								sender.sendMessage(ChatColor.DARK_RED + "Der letzte Wechsel der Änderung dieser Berechtigung liegt noch nicht lange genug zurück.");
								return true;
							}
							if(town.toggleInteractAllowedOutsider()) {
								//sender.sendMessage(ChatColor.DARK_GREEN + "Fremden ist es nun gestattet Waren herzustellen.");
								town.sendTownMessage("Deine Stadt gewährt nun allen Fremden das Schaltrecht.");
								return true;
							} else {
								//sender.sendMessage(ChatColor.DARK_GREEN + "Fremden ist es nun verwehrt Waren herzustellen.");
								town.sendTownMessage("Deine Stadt verwehrt nun allen Fremden das Schaltrecht.");
								return true;
							}
						}
						if(args[2].equalsIgnoreCase("schützen")) {
							if(town.toggleProtectAllowedOutsider()) {
								//sender.sendMessage(ChatColor.DARK_GREEN + "Fremden ist es nun gestattet Waren herzustellen.");
								town.sendTownMessage("Deine Stadt gewährt nun allen Fremden das Schutzrecht.");
								return true;
							} else {
								//sender.sendMessage(ChatColor.DARK_GREEN + "Fremden ist es nun verwehrt Waren herzustellen.");
								town.sendTownMessage("Deine Stadt verwehrt nun allen Fremden das Schutzrecht.");
								return true;
							}
						}
					}
					if(args[1].equalsIgnoreCase("alliierte")) {
						if(!town.isCooldownAllowed("AllyBreak")) {
							sender.sendMessage(ChatColor.DARK_RED + "Der letzte Wechsel der Änderung dieser Berechtigung liegt noch nicht lange genug zurück.");
							return true;
						}
						if(args[2].equalsIgnoreCase("abbauen")) {
							if(town.toggleBreakAllowedAlly()) {
								//sender.sendMessage(ChatColor.DARK_GREEN + "Alliierten ist es nun gestattet Bl�cke abzubauen.");
								town.sendTownMessage("Deine Stadt gewährt nun allen Alliierten das Abbaurecht.");
								return true;
							} else {
								//sender.sendMessage(ChatColor.DARK_GREEN + "Alliierten ist es nun verwehrt Bl�cke abzubauen.");
								town.sendTownMessage("Deine Stadt verwehrt nun allen Alliierten das Abbaurecht.");
								return true;
							}
						}
						if(args[2].equalsIgnoreCase("bauen")) {
							if(!town.isCooldownAllowed("AllyBuild")) {
								sender.sendMessage(ChatColor.DARK_RED + "Der letzte Wechsel der Änderung dieser Berechtigung liegt noch nicht lange genug zurück.");
								return true;
							}
							if(town.toggleBuildAllowedAlly()) {
								//sender.sendMessage(ChatColor.DARK_GREEN + "Alliierten ist es nun gestattet Bl�cke zu setzen.");
								town.sendTownMessage("Deine Stadt gewährt nun allen Alliierten das Baurecht.");
								return true;
							} else {
								//sender.sendMessage(ChatColor.DARK_GREEN + "Alliierten ist es nun verwehrt Bl�cke zu setzen.");
								town.sendTownMessage("Deine Stadt verwehrt nun allen Alliierten das Baurecht.");
								return true;
							}
						}
						if(args[2].equalsIgnoreCase("herstellen")) {
							if(!town.isCooldownAllowed("AllyCraft")) {
								sender.sendMessage(ChatColor.DARK_RED + "Der letzte Wechsel der Änderung dieser Berechtigung liegt noch nicht lange genug zurück.");
								return true;
							}
							if(town.toggleCraftAllowedAlly()) {
								//sender.sendMessage(ChatColor.DARK_GREEN + "Alliierten ist es nun gestattet Waren herzustellen.");
								town.sendTownMessage("Deine Stadt gewährt nun allen Alliierten das Handwerksrecht.");
								return true;
							} else {
								//sender.sendMessage(ChatColor.DARK_GREEN + "Alliierten ist es nun verwehrt Waren herzustellen.");
								town.sendTownMessage("Deine Stadt verwehrt nun allen Alliierten das Handwerksrecht.");
								return true;
							}
						}
						if(args[2].equalsIgnoreCase("schalten")) {
							if(!town.isCooldownAllowed("AllyInteract")) {
								sender.sendMessage(ChatColor.DARK_RED + "Der letzte Wechsel der Änderung dieser Berechtigung liegt noch nicht lange genug zurück.");
								return true;
							}
							if(town.toggleInteractAllowedAlly()) {
								//sender.sendMessage(ChatColor.DARK_GREEN + "Alliierten ist es nun gestattet Waren herzustellen.");
								town.sendTownMessage("Deine Stadt gewährt nun allen Alliierten das Schaltrecht.");
								return true;
							} else {
								//sender.sendMessage(ChatColor.DARK_GREEN + "Alliierten ist es nun verwehrt Waren herzustellen.");
								town.sendTownMessage("Deine Stadt verwehrt nun allen Alliierten das Schaltrecht.");
								return true;
							}
						}
						if(args[2].equalsIgnoreCase("schützen")) {
							if(town.toggleProtectAllowedAlly()) {
								//sender.sendMessage(ChatColor.DARK_GREEN + "Alliierten ist es nun gestattet Waren herzustellen.");
								town.sendTownMessage("Deine Stadt gewährt nun allen Alliierten das Schutzrecht.");
								return true;
							} else {
								//sender.sendMessage(ChatColor.DARK_GREEN + "Alliierten ist es nun verwehrt Waren herzustellen.");
								town.sendTownMessage("Deine Stadt verwehrt nun allen Alliierten das Schutzrecht.");
								return true;
							}
						}
					}
				}
				if(args[0].equalsIgnoreCase("diplomatie")) {
					if(!town.isDiplomat(sender)) {
						sender.sendMessage(ChatColor.DARK_RED + "Nur der Diplomat einer Stadt hat Diplomatierechte.");
						return true;
					}
					if(town.getLevel() < 5) {
						sender.sendMessage(ChatColor.DARK_RED + "Diplomatische Optionen stehen erst ab dem Stadtstatus (lvl 5) zur Verfügung.");
						return true;
					}
					Town requestTown = plugin.nSCore.getTown(args[3]);
					Player requestDiplomat = null;
					if(requestTown == null) {
						sender.sendMessage(ChatColor.DARK_RED + "Diese Stadt steht nicht in unseren Registern.");
						return true;
					} else {
						if(requestTown.getDiplomat() == null) {
							sender.sendMessage(ChatColor.DARK_RED + requestTown.getName() + " hat noch keinen Diplomaten ernannt.");
							return true;
						}
						requestDiplomat = plugin.getPlayerByName(requestTown.getDiplomat());
						if(requestDiplomat == null) {
							sender.sendMessage(ChatColor.DARK_RED + "Der Diplomat von " + requestTown.getName() + " ist nicht anwesend.");
							return true;
						}
					}
					if(args[1].equalsIgnoreCase("allianz")) {
						if(args[2].equalsIgnoreCase("anbieten")) {
							if(sender.getLocation().distance(requestDiplomat.getLocation()) > plugin.config.getDouble("System.Distance.Interact", 16)) {
								sender.sendMessage(ChatColor.DARK_RED + "Du bist zu weit von deinem Gegenüber entfernt, ihr müsst näher beisammen stehen.");
							}
							plugin.nSAllyInvite.sendRequest(sender, "#AllyInvite#", requestDiplomat);
							return true;
						}
						if(args[2].equalsIgnoreCase("auflösen") || args[2].equalsIgnoreCase("beenden")) {
							if(town.getAllies().contains(requestTown.getName())) {
								plugin.nSAllyRemove.sendRequest(sender, "#AllyRemove:" + requestTown.getName() + "#", sender);
								return true;
							} else {
								sender.sendMessage(ChatColor.DARK_RED + "Ihr seid nicht mit " + requestTown.getName() + " verbündet.");
								return true;
							}
						}
						return true;
					}
					if(args[1].equalsIgnoreCase("krieg")) {
						if(args[2].equalsIgnoreCase("erklären")) {
							plugin.nSEnemyInvite.sendRequest(sender, "#EnemyInvite:" + requestTown.getName() + "#", sender);
							return true;
						}
						if(args[2].equalsIgnoreCase("beenden")) {
							if(town.getEnemies().contains(requestTown.getName())) {
								if(sender.getLocation().distance(requestDiplomat.getLocation()) > plugin.config.getDouble("System.Distance.Interact", 16)) {
									sender.sendMessage(ChatColor.DARK_RED + "Du bist zu weit von deinem Gegenüber entfernt, ihr müsst näher beisammen stehen.");
								}
								plugin.nSEnemyRemove.sendRequest(sender, "#EnemyRemove#", requestDiplomat);
								return true;
							} else {
								sender.sendMessage(ChatColor.DARK_RED + "Ihr seid nicht mit " + requestTown.getName() + " im Krieg.");
								return true;
							}
						}								
						return true;
					}
				}
				if(args[0].equalsIgnoreCase("produktion")) {
					if(!town.isMayor(sender)) {
						sender.sendMessage(ChatColor.DARK_RED + "Du benötigst administrative Rechte für diesen Befehl.");
						return true;
					}
					if(args[1].equalsIgnoreCase("einrichten")) {
						String townBuilding = Character.toUpperCase(args[2].charAt(0)) + args[2].substring(1);								
						if(!town.getProductionBuildings().containsKey(townBuilding)) {
							sender.sendMessage(ChatColor.DARK_RED + "Das angegebene Gebäude ist nicht gültig.");
							return true;
						}
						if(plugin.nSCore.getChunkInfo(sender.getLocation().getWorld().getName() + "," + sender.getLocation().getChunk().getX() + "," + sender.getLocation().getChunk().getZ()) != town) {
							sender.sendMessage(ChatColor.DARK_RED + "Dieses Grundstück ist nicht im Besitz deiner Stadt.");
							return true;
						}
						if(town.getProductionBuildings().containsKey(townBuilding) && town.getProductionBuildings().get(townBuilding)) {
							if(town.isProductionInDistance(sender, townBuilding, sender.getLocation())) {
								town.setBuildingLocation(townBuilding, sender.getLocation());
								sender.sendMessage(ChatColor.DARK_GREEN + "Du hast den Standort des Gebäudes (" + townBuilding + ") aktualisiert.");
							}							
							return true;
						} else {
							if(town.getProduction() < town.getProductionLimit()) {
								if(town.isProductionInDistance(sender, townBuilding, sender.getLocation())) {
									town.setBuildingStatus(townBuilding, sender.getLocation(), true);
									sender.sendMessage(ChatColor.DARK_GREEN + "Du hast das Gebäude erfolgreich registriert.");
								}
							} else {
								sender.sendMessage(ChatColor.DARK_RED + "Deine Stadt hat bereits ihr Maximum an Produktionsstätten erreicht.");
							}
							return true;
						}
					}
					if(args[1].equalsIgnoreCase("entfernen")) {
						String townBuilding = Character.toUpperCase(args[2].charAt(0)) + args[2].substring(1);								
						if(!town.getProductionBuildings().containsKey(townBuilding)) {
							sender.sendMessage(ChatColor.DARK_RED + "Das angegebene Gebäude ist nicht gültig.");
							return true;
						}
						if(plugin.nSCore.getChunkInfo(sender.getLocation().getWorld().getName() + "," + sender.getLocation().getChunk().getX() + "," + sender.getLocation().getChunk().getZ()) != town) {
							sender.sendMessage(ChatColor.DARK_RED + "Du musst dich innerhalb deiner Stadt befinden.");
							return true;
						}
						if(town.getProductionSlotResets() == 0) {
							sender.sendMessage(ChatColor.DARK_RED + "Du darfst keine Produktionsstätten mehr entfernen.");
							return true;
						}
						town.removeBuilding(townBuilding);
						sender.sendMessage(ChatColor.DARK_GREEN + "Du hast das Gebäude erfolgreich aus der Stadt entfernt.");
						return true;
					}
				}
			}
		}
		
		return false;
	}
	
}
