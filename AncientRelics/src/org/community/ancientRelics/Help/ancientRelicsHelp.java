package org.community.ancientRelics.Help;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.community.ancientRelics.ancientRelics;

public class ancientRelicsHelp {
		
	@SuppressWarnings("unused")
	private final ancientRelics plugin;
	 	 
	public ancientRelicsHelp(ancientRelics plugin)
	{
		this.plugin = plugin;
	}
	
	public boolean getCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		
		if(args.length == 1){
			displayHelpMainpage(sender);
			return true;
		}
		
		if(args.length == 2){
			if(args[1].equalsIgnoreCase("gruppe")){
				displayGroupMainpage(sender);
				return true;
			}
			if(args[1].equalsIgnoreCase("pvp")){
				displayPvPMainpage(sender);
				return true;
			}
		}
		
		return false;
	}
	
	private void displayHelpMainpage(CommandSender sender){
		sender.sendMessage("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		sender.sendMessage("Willkommen im Hilfe-Menü von 'Ancient Relics'");
		sender.sendMessage(" ");
		sender.sendMessage("Hier findest du Hilfe zu:");
		sender.sendMessage("- unserem Gruppensystem: /ar hilfe gruppe");
		sender.sendMessage("- unserem PvP-System: /ar hilfe pvp");
		sender.sendMessage(" ");
		sender.sendMessage("/duell {Spieler} - Fordere einen Spieler heraus");
		sender.sendMessage(" ");
		sender.sendMessage("/akzeptieren - Einladungen annehmen");
		sender.sendMessage("/ablehnen - Einladungen ablehnen");
		sender.sendMessage("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		return;
	}
	
	private void displayGroupMainpage(CommandSender sender){
		sender.sendMessage("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		sender.sendMessage("'Ancient Relics' Hilfemenü: Gruppensystem");
		sender.sendMessage(" ");
		sender.sendMessage("/gruppe - Deine Gruppen-Information");
		sender.sendMessage("/gruppe delete - Gruppen auflösen");
		sender.sendMessage("/gruppe hilfe - Gruppen Hilfemenü");
		sender.sendMessage("/gruppe invite {Spieler} - Spieler einladen");
		sender.sendMessage("/gruppe leave - Gruppe verlassen");
		sender.sendMessage("/gruppe remove {Spieler} - Spieler entfernen");
		sender.sendMessage("/gruppe set leader {Spieler} - Anführer festlegen");
		sender.sendMessage("/gruppe set name {Name} - Gruppennamen festlegen");
		sender.sendMessage("/gruppe toggle pvp - PvP Status umschalten");
		sender.sendMessage("/gruppe toggle potion - Potion Status umschalten");
		sender.sendMessage("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		return;
	}
	
	private void displayPvPMainpage(CommandSender sender){
		sender.sendMessage("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		sender.sendMessage("'Ancient Relics' Hilfemenü: PvP-System");
		sender.sendMessage(" ");
		sender.sendMessage("/pvp - Dein PvP-Status");
		sender.sendMessage("/pvp help - PvP Hilfemenü");
		sender.sendMessage("/pvp list - PvP-Status aller Spieler");
		sender.sendMessage("/pvp toggle - Schaltet deinen Status um");
		sender.sendMessage("/pvp info {Spieler} - Status des Spielers");
		sender.sendMessage("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		return;
	}
	
}