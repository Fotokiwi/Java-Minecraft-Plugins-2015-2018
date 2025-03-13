package org.community.newSettlers.Help;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.community.newSettlers.newSettlers;

public class newSettlersHelp {
		
	private final newSettlers plugin;
	 	 
	public newSettlersHelp(newSettlers plugin)
	{
		this.plugin = plugin;
	}
	
	public boolean getCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		
		if(cmd.getName().equalsIgnoreCase("siedler")){
			if(args.length == 1){
				displayHelpMainpage(sender);
				return true;
			}

			if(args.length == 2){
				if(args[1].equalsIgnoreCase("2")){
					displaySettlersMainpage(sender);
					return true;
				}
			}
		}
		
		if(cmd.getName().equalsIgnoreCase("stadt")){
			if(args.length == 1){
				displayTownMainpage(sender);
				return true;
			}

			if(args.length == 2){
				if(args[1].equalsIgnoreCase("info") || args[1].equalsIgnoreCase("information") || args[1].equalsIgnoreCase("informationen")){
					displayInfoMainpage(sender);
					return true;
				}
				if(args[1].equalsIgnoreCase("allgemein")){
					displayMayorMainpage(sender);
					return true;
				}
				if(args[1].equalsIgnoreCase("rechte")){
					displayMayorRights(sender);
					return true;
				}
				if(args[1].equalsIgnoreCase("diplomatie")){
					displayMayorDiplomacy(sender);
					return true;
				}
				if(args[1].equalsIgnoreCase("assistent")){
					displayMayorAssistant(sender);
					return true;
				}
				if(args[1].equalsIgnoreCase("moderator")){
					if(plugin.nSCore.isAdmin((Player) sender)) {
						displayMayorModerator(sender);
					} else {
						sender.sendMessage(ChatColor.DARK_RED + "Dieser Befehl steht nur 'New Settlers' Moderatoren zur Verfügung");
					}					
					return true;
				}
			}
		}
		
		
		
		return false;
	}
	
	private void displayHelpMainpage(CommandSender sender){
		sender.sendMessage(ChatColor.DARK_GREEN + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		sender.sendMessage(ChatColor.DARK_GREEN + "Willkommen im Hilfe-Menü von 'New Settlers'");
		sender.sendMessage(ChatColor.DARK_GREEN + " ");
		sender.sendMessage(ChatColor.DARK_GREEN + "Hier findest du Hilfe zu:");
		sender.sendMessage(ChatColor.DARK_GREEN + "- Basisbefehle: " + ChatColor.GOLD + "/siedler hilfe 2");
		sender.sendMessage(ChatColor.DARK_GREEN + "- Stadtbefehle: " + ChatColor.GOLD + "/stadt hilfe");
		sender.sendMessage(ChatColor.DARK_GREEN + "- Stadtinformationen: " + ChatColor.GOLD + "/stadt hilfe info");
		sender.sendMessage(ChatColor.UNDERLINE + "" + ChatColor.DARK_GREEN + "- Bürgermeisterbefehle:");
		sender.sendMessage(ChatColor.DARK_GREEN + "- Allgemeines: " + ChatColor.GOLD + "/stadt hilfe allgemein");
		sender.sendMessage(ChatColor.DARK_GREEN + "- Rechte: " + ChatColor.GOLD + "/stadt hilfe rechte");
		sender.sendMessage(ChatColor.DARK_GREEN + "- Diplomatie: " + ChatColor.GOLD + "/stadt hilfe diplomatie");
		sender.sendMessage(ChatColor.DARK_GREEN + "- Assistenten: " + ChatColor.GOLD + "/stadt hilfe assistent");
		sender.sendMessage(ChatColor.UNDERLINE + "" + ChatColor.DARK_GREEN + "- Moderatorenbefehle:");
		sender.sendMessage(ChatColor.DARK_GREEN + "- Moderator: " + ChatColor.GOLD + "/stadt hilfe moderator");
		sender.sendMessage(ChatColor.UNDERLINE + "" + ChatColor.DARK_GREEN + "- Handel:");
		sender.sendMessage(ChatColor.GOLD + "/handel {Spielername}");
		sender.sendMessage(ChatColor.DARK_GREEN + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		return;
	}
	
	private void displaySettlersMainpage(CommandSender sender){
		sender.sendMessage(ChatColor.DARK_GREEN + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		sender.sendMessage(ChatColor.DARK_GREEN + "'New Settlers' Hilfemenü: Allgemeines");
		sender.sendMessage(ChatColor.DARK_GREEN + " ");
		sender.sendMessage(ChatColor.DARK_GREEN + "" + ChatColor.GOLD + "/siedler städte " + ChatColor.DARK_GREEN + "- Zeigt eine Liste aller Städte an");
		sender.sendMessage(ChatColor.DARK_GREEN + "" + ChatColor.GOLD + "/siedler plotanzeige " + ChatColor.DARK_GREEN + "- Aktiviert / Deaktiviert den Plot-Anzeigemodus");
		sender.sendMessage(ChatColor.DARK_GREEN + "" + ChatColor.GOLD + "/siedler kartenanzeige " + ChatColor.DARK_GREEN + "- Aktiviert / Deaktiviert den Karten-Anzeigemodus");
		sender.sendMessage(ChatColor.DARK_GREEN + "" + ChatColor.GOLD + "/siedler gründung {Stadtname} " + ChatColor.DARK_GREEN + "- Gründet die Stadt mit dem angegebenen Namen");
		sender.sendMessage(ChatColor.DARK_GREEN + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		return;
	}
	
	private void displayTownMainpage(CommandSender sender){
		sender.sendMessage(ChatColor.DARK_GREEN + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		sender.sendMessage(ChatColor.DARK_GREEN + "'New Settlers' Hilfemenü: Stadtbefehle");
		sender.sendMessage(ChatColor.DARK_GREEN + " ");
		sender.sendMessage(ChatColor.DARK_GREEN + "" + ChatColor.GOLD + "/stadt " + ChatColor.DARK_GREEN + "- Zeigt einige Statistiken der eigenen Stadt an");
		sender.sendMessage(ChatColor.DARK_GREEN + "" + ChatColor.GOLD + "/stadt {Stadtname} " + ChatColor.DARK_GREEN + "- Zeigt einige Statistiken der angegebenen Stadt an");
		sender.sendMessage(ChatColor.DARK_GREEN + "" + ChatColor.GOLD + "");
		sender.sendMessage(ChatColor.DARK_GREEN + "" + ChatColor.GOLD + "/stadt beitreten {Stadtname} " + ChatColor.DARK_GREEN + "- Der angegebenen (offenen) Stadt beitreten");
		sender.sendMessage(ChatColor.DARK_GREEN + "" + ChatColor.GOLD + "/stadt verlassen " + ChatColor.DARK_GREEN + "- Die derzeitige Stadt verlassen");
		sender.sendMessage(ChatColor.DARK_GREEN + "" + ChatColor.GOLD + "");
		sender.sendMessage(ChatColor.DARK_GREEN + "" + ChatColor.GOLD + "/stadt anzeigen stadtlager " + ChatColor.DARK_GREEN + "- Zeigt die aktuellen Lagerwerte der Stadt an");
		sender.sendMessage(ChatColor.DARK_GREEN + "" + ChatColor.GOLD + "/stadt stadtlager einlagern " + ChatColor.DARK_GREEN + "- Lagert den Inhalt einer Truhe in das Stadtlager ein");
		sender.sendMessage(ChatColor.DARK_GREEN + "" + ChatColor.GOLD + "");
		sender.sendMessage(ChatColor.DARK_GREEN + "" + ChatColor.GOLD + "/stadt produktion einrichten {Produktionsgebäude} " + ChatColor.DARK_GREEN + "- Richtet das benannte Produktionsgebäude ein");
		sender.sendMessage(ChatColor.DARK_GREEN + "" + ChatColor.GOLD + "/stadt produktion entfernen {Produktionsgebäude} " + ChatColor.DARK_GREEN + "- Entfernt das angegebene Produktionsgebäude");
		sender.sendMessage(ChatColor.DARK_GREEN + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		return;
	}
	
	private void displayMayorMainpage(CommandSender sender){
		sender.sendMessage(ChatColor.DARK_GREEN + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		sender.sendMessage(ChatColor.DARK_GREEN + "'New Settlers' Hilfemenü: Stadtbefehle");
		sender.sendMessage(ChatColor.DARK_GREEN + " ");
		sender.sendMessage(ChatColor.DARK_GREEN + "" + ChatColor.GOLD + "/stadt offen " + ChatColor.DARK_GREEN + "- Öffnet / Schließt die Stadt für den Beitritt Fremder");
		sender.sendMessage(ChatColor.DARK_GREEN + "" + ChatColor.GOLD + "/stadt mitteilung {Text} " + ChatColor.DARK_GREEN + "- Aktualisiert die Stadtmitteilung");
		sender.sendMessage(ChatColor.DARK_GREEN + "" + ChatColor.GOLD + "/stadt spawn setzen " + ChatColor.DARK_GREEN + "- Setzt den Stadtspawn");
		sender.sendMessage(ChatColor.DARK_GREEN + "" + ChatColor.GOLD + "");
		sender.sendMessage(ChatColor.DARK_GREEN + "" + ChatColor.GOLD + "/stadt grundstück urbanisieren " + ChatColor.DARK_GREEN + "- Erweitert die Stadt um das Grundstück");
		//sender.sendMessage(ChatColor.DARK_GREEN + "" + ChatColor.GOLD + "/stadt grundstück verwildern " + ChatColor.DARK_GREEN + "- Entfernt das Grundstück aus der Stadt (Admin only)");
		sender.sendMessage(ChatColor.DARK_GREEN + "" + ChatColor.GOLD + "");
		sender.sendMessage(ChatColor.DARK_GREEN + "" + ChatColor.GOLD + "/stadt bürgermeister einsetzen {Spielername} " + ChatColor.DARK_GREEN + "- Befördert den Bürger zum Bürgermeister");
		sender.sendMessage(ChatColor.DARK_GREEN + "" + ChatColor.GOLD + "");
		sender.sendMessage(ChatColor.DARK_GREEN + "" + ChatColor.GOLD + "/stadt expandieren " + ChatColor.DARK_GREEN + "- Erweitert die Stadt auf die nächste Stufe");
		sender.sendMessage(ChatColor.DARK_GREEN + "" + ChatColor.GOLD + "/stadt auflösen " + ChatColor.DARK_GREEN + "- Löscht die Stadt aus allen Registern(!)");
		sender.sendMessage(ChatColor.DARK_GREEN + "" + ChatColor.GOLD + "/stadt freikaufen " + ChatColor.DARK_GREEN + "- Kauft die Stadt aus der NPC-Verwaltung zurück");
		sender.sendMessage(ChatColor.DARK_GREEN + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		return;
	}
	
	private void displayMayorRights(CommandSender sender){
		sender.sendMessage(ChatColor.DARK_GREEN + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		sender.sendMessage(ChatColor.DARK_GREEN + "'New Settlers' Hilfemenü: Rechte");
		sender.sendMessage(ChatColor.DARK_GREEN + " ");
		sender.sendMessage(ChatColor.DARK_GREEN + "" + ChatColor.GOLD + "/stadt rechte zurücksetzen " + ChatColor.DARK_GREEN + "- Setzt alle Berechtigungen auf ihren Normalwert");
		sender.sendMessage(ChatColor.DARK_GREEN + "" + ChatColor.GOLD + "");
		sender.sendMessage(ChatColor.DARK_GREEN + "" + ChatColor.GOLD + "/stadt rechte bürger bauen " + ChatColor.DARK_GREEN + "- Aktiviert / Deaktiviert das Recht für Bürger zum Bauen");
		sender.sendMessage(ChatColor.DARK_GREEN + "" + ChatColor.GOLD + "/stadt rechte bürger abbauen " + ChatColor.DARK_GREEN + "- Aktiviert / Deaktiviert das Recht für Bürger zum Abbauen");
		sender.sendMessage(ChatColor.DARK_GREEN + "" + ChatColor.GOLD + "/stadt rechte bürger herstellen " + ChatColor.DARK_GREEN + "- Aktiviert / Deaktiviert das Recht für Bürger zum Herstellen");
		sender.sendMessage(ChatColor.DARK_GREEN + "" + ChatColor.GOLD + "/stadt rechte bürger schalten " + ChatColor.DARK_GREEN + "- Aktiviert / Deaktiviert das Recht für Bürger zum Schalten");
		sender.sendMessage(ChatColor.DARK_GREEN + "" + ChatColor.GOLD + "");
		sender.sendMessage(ChatColor.DARK_GREEN + "" + ChatColor.GOLD + "/stadt rechte fremde bauen " + ChatColor.DARK_GREEN + "- Aktiviert / Deaktiviert das Recht für Fremde zum Bauen");
		sender.sendMessage(ChatColor.DARK_GREEN + "" + ChatColor.GOLD + "/stadt rechte fremde abbauen " + ChatColor.DARK_GREEN + "- Aktiviert / Deaktiviert das Recht für Fremde zum Abbauen");
		sender.sendMessage(ChatColor.DARK_GREEN + "" + ChatColor.GOLD + "/stadt rechte fremde herstellen " + ChatColor.DARK_GREEN + "- Aktiviert / Deaktiviert das Recht für Fremde zum Herstellen");
		sender.sendMessage(ChatColor.DARK_GREEN + "" + ChatColor.GOLD + "/stadt rechte fremde schalten " + ChatColor.DARK_GREEN + "- Aktiviert / Deaktiviert das Recht für Fremde zum Schalten");
		sender.sendMessage(ChatColor.DARK_GREEN + "" + ChatColor.GOLD + "");
		sender.sendMessage(ChatColor.DARK_GREEN + "" + ChatColor.GOLD + "/stadt rechte alliierte bauen " + ChatColor.DARK_GREEN + "- Aktiviert / Deaktiviert das Recht für Alliierte zum Bauen");
		sender.sendMessage(ChatColor.DARK_GREEN + "" + ChatColor.GOLD + "/stadt rechte alliierte abbauen " + ChatColor.DARK_GREEN + "- Aktiviert / Deaktiviert das Recht für Alliierte zum Abbauen");
		sender.sendMessage(ChatColor.DARK_GREEN + "" + ChatColor.GOLD + "/stadt rechte alliierte herstellen " + ChatColor.DARK_GREEN + "- Aktiviert / Deaktiviert das Recht für Alliierte zum Herstellen");
		sender.sendMessage(ChatColor.DARK_GREEN + "" + ChatColor.GOLD + "/stadt rechte alliierte schalten " + ChatColor.DARK_GREEN + "- Aktiviert / Deaktiviert das Recht für Alliierte zum Schalten");
		sender.sendMessage(ChatColor.DARK_GREEN + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		return;
	}
	
	private void displayMayorDiplomacy(CommandSender sender){
		sender.sendMessage(ChatColor.DARK_GREEN + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		sender.sendMessage(ChatColor.DARK_GREEN + "'New Settlers' Hilfemenü: Diplomatie");
		sender.sendMessage(ChatColor.DARK_GREEN + " ");
		sender.sendMessage(ChatColor.DARK_GREEN + "" + ChatColor.GOLD + "/stadt diplomatie allianz anbieten {Stadt} " + ChatColor.DARK_GREEN + "- Bietet der angegebenen Stadt eine Allianz an");
		sender.sendMessage(ChatColor.DARK_GREEN + "" + ChatColor.GOLD + "/stadt diplomatie allianz auflösen {Stadt} " + ChatColor.DARK_GREEN + "- Löst die Allianz mit der angegebenen Stadt auf");
		sender.sendMessage(ChatColor.DARK_GREEN + "" + ChatColor.GOLD + "");
		sender.sendMessage(ChatColor.DARK_GREEN + "" + ChatColor.GOLD + "/stadt diplomatie krieg erklären {Stadt} " + ChatColor.DARK_GREEN + "- Erklärt der angegebenen Stadt den Krieg");
		sender.sendMessage(ChatColor.DARK_GREEN + "" + ChatColor.GOLD + "/stadt diplomatie krieg beenden {Stadt} " + ChatColor.DARK_GREEN + "- Beendet mit der angegebenen Stadt den Krieg");
		sender.sendMessage(ChatColor.DARK_GREEN + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		return;
	}
	
	private void displayMayorAssistant(CommandSender sender){
		sender.sendMessage(ChatColor.DARK_GREEN + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		sender.sendMessage(ChatColor.DARK_GREEN + "'New Settlers' Hilfemenü: Assistenten");
		sender.sendMessage(ChatColor.DARK_GREEN + " ");
		sender.sendMessage(ChatColor.DARK_GREEN + "" + ChatColor.GOLD + "/stadt assistent einsetzen {Spielername} " + ChatColor.DARK_GREEN + "- Befördert den Bürger zum Assistenten");
		sender.sendMessage(ChatColor.DARK_GREEN + "" + ChatColor.GOLD + "/stadt assistent entlassen {Spielername} " + ChatColor.DARK_GREEN + "- Degradiert den Assistenten zum Bürger");
		sender.sendMessage(ChatColor.DARK_GREEN + " ");
		sender.sendMessage(ChatColor.DARK_GREEN + "" + ChatColor.GOLD + "/stadt diplomat einsetzen {Spielername} " + ChatColor.DARK_GREEN + "- Befördert den Bürger zum Diplomaten");
		sender.sendMessage(ChatColor.DARK_GREEN + "" + ChatColor.GOLD + "/stadt diplomat entlassen {Spielername} " + ChatColor.DARK_GREEN + "- Degradiert den Diplomaten zum Bürger");
		sender.sendMessage(ChatColor.DARK_GREEN + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		return;
	}
	
	private void displayMayorModerator(CommandSender sender){
		sender.sendMessage(ChatColor.DARK_GREEN + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		sender.sendMessage(ChatColor.DARK_GREEN + "'New Settlers' Hilfemenü: Moderator");
		sender.sendMessage(ChatColor.DARK_GREEN + " ");
		sender.sendMessage(ChatColor.DARK_GREEN + "" + ChatColor.GOLD + "/stadt freischalten gebäude {Stadtname} {Gebäudename} " + ChatColor.DARK_GREEN + "- Schaltet der angegebenen Stadt das genannte Gebäude frei");
		sender.sendMessage(ChatColor.DARK_GREEN + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		return;
	}
	
	private void displayInfoMainpage(CommandSender sender){
		sender.sendMessage(ChatColor.DARK_GREEN + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		sender.sendMessage(ChatColor.DARK_GREEN + "'New Settlers' Hilfemenü: Stadtinformationen");
		sender.sendMessage(ChatColor.DARK_GREEN + " ");
		sender.sendMessage(ChatColor.DARK_GREEN + "" + ChatColor.GOLD + "/stadt anzeigen stadtverwaltung " + ChatColor.DARK_GREEN + "- Zeigt alle Stadtbediensteten an");
		sender.sendMessage(ChatColor.DARK_GREEN + "" + ChatColor.GOLD + "/stadt anzeigen stadtrechte " + ChatColor.DARK_GREEN + "- Zeigt alle Stadtrechte an");
		sender.sendMessage(ChatColor.DARK_GREEN + " ");
		sender.sendMessage(ChatColor.DARK_GREEN + "" + ChatColor.GOLD + "/stadt anzeigen stadtlager " + ChatColor.DARK_GREEN + "- Zeigt das städtische Lager an");
		sender.sendMessage(ChatColor.DARK_GREEN + "" + ChatColor.GOLD + "/stadt anzeigen kosten " + ChatColor.DARK_GREEN + "- Zeigt die Stadterhaltungskosten an");
		sender.sendMessage(ChatColor.DARK_GREEN + " ");
		sender.sendMessage(ChatColor.DARK_GREEN + "" + ChatColor.GOLD + "/stadt anzeigen baustoffe " + ChatColor.DARK_GREEN + "- Zeigt den Lagerbestand aller Baustoffe");
		sender.sendMessage(ChatColor.DARK_GREEN + "" + ChatColor.GOLD + "/stadt anzeigen produkte " + ChatColor.DARK_GREEN + "- Zeigt den Lagerbestand aller Produkte");
		sender.sendMessage(ChatColor.DARK_GREEN + "" + ChatColor.GOLD + "/stadt anzeigen lebensmittel " + ChatColor.DARK_GREEN + "- Zeigt den Lagerbestand aller Lebensmittel");
		sender.sendMessage(ChatColor.DARK_GREEN + "" + ChatColor.GOLD + "/stadt anzeigen verteidigung " + ChatColor.DARK_GREEN + "- Zeigt den Lagerbestand aller Kriegsgeräte");
		sender.sendMessage(ChatColor.DARK_GREEN + " ");
		sender.sendMessage(ChatColor.DARK_GREEN + "" + ChatColor.GOLD + "/stadt anzeigen gebäude " + ChatColor.DARK_GREEN + "- Zeigt alle (freigeschalteten) Stadtgebäude");
		sender.sendMessage(ChatColor.DARK_GREEN + "" + ChatColor.GOLD + "/stadt anzeigen produktion " + ChatColor.DARK_GREEN + "- Zeigt alle (freigeschalteten) Produktionsgebäude");
		sender.sendMessage(ChatColor.DARK_GREEN + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		return;
	}
	
}