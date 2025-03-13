package org.community.fourWays.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.community.fourWays.fourWays;
import org.community.fourWays.User.User;

public class fourWaysCommandTrader {
		
	private final fourWays plugin;
	 	 
	public fourWaysCommandTrader(fourWays plugin)
	{
		this.plugin = plugin;
	}
	
	public boolean getCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		
		if(cmd.getName().equalsIgnoreCase("lieferung")){
			if(args.length == 0) {
				showHelp(sender);
				return true;
			}
			if(args.length == 1) {
				if(args[0].equalsIgnoreCase("liste")){
					lieferungListe((Player) sender);
					return true;
				}	
				if(args[0].equalsIgnoreCase("verpacken")){
					lieferungVerpacken((Player) sender);
					return true;
				}	
				if(args[0].equalsIgnoreCase("entpacken")){
					sender.sendMessage(ChatColor.DARK_GREEN + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
					sender.sendMessage(ChatColor.DARK_GREEN + " ");
					sender.sendMessage(ChatColor.DARK_GREEN + "Du entpackst eine Lieferung, indem du mit dem Lieferbuch auf eine Posttruhe klickst.");
					sender.sendMessage(ChatColor.DARK_GREEN + " ");
					sender.sendMessage(ChatColor.DARK_GREEN + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
					return true;
				}			
			}
			if(args.length == 2) {
				if(args[0].equalsIgnoreCase("anzeigen")){
					lieferungAnzeigen((Player) sender, args[1]);
					return true;
				}				
			}
		}
		
		return false;
	}

	private void showHelp(CommandSender sender){
		sender.sendMessage(ChatColor.DARK_GREEN + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		sender.sendMessage(ChatColor.DARK_GREEN + "Willkommen im Hilfe-Menü für Warenlieferungen");
		sender.sendMessage(ChatColor.DARK_GREEN + " ");
		sender.sendMessage(ChatColor.DARK_GREEN + "- Lieferungen auflisten: " + ChatColor.GOLD + "/lieferung liste");
		sender.sendMessage(ChatColor.DARK_GREEN + " ");
		sender.sendMessage(ChatColor.DARK_GREEN + "- Bestimmte Lieferungen anzeigen: " + ChatColor.GOLD + "/lieferung anzeigen {ID}");
		sender.sendMessage(ChatColor.DARK_GREEN + " ");
		sender.sendMessage(ChatColor.DARK_GREEN + "- Lieferungen erstellen: " + ChatColor.GOLD + "/lieferung verpacken");
		sender.sendMessage(ChatColor.DARK_GREEN + " ");
		sender.sendMessage(ChatColor.DARK_GREEN + "- Lieferungen beenden: " + ChatColor.GOLD + "/lieferung entpacken");
		sender.sendMessage(ChatColor.DARK_GREEN + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	}
	
	private void lieferungListe(Player player) {
		
		if(plugin.fWTrader.isPlayerTrader(player)) {
			player.sendMessage(plugin.fWTrader.listPlayerTradeChests(player));
			return;
		} else {
			player.sendMessage("Nur Händler können ihre Lieferlisten betrachten.");
		}
		
	}

	private void lieferungVerpacken(Player player) {
		
		User user = plugin.fWUsers.getPlayerInfo(player);
		int level = user.getLevel();
		
		if(plugin.fWTrader.isPlayerTrader(player)) {

			int chestCount = plugin.fWTrader.getTradechestCount(player);
			if(chestCount >= 4) {
				player.sendMessage("Du hast bereits die Höchstmenge (4) an Lieferungen erreicht.");
				return;
			} else if(chestCount >= 3 && level <= 47) {
				player.sendMessage("Du hast bereits die Höchstmenge (3) an Lieferungen erreicht.");
				return;
			} else if(chestCount >= 2 && level <= 41) {
				player.sendMessage("Du hast bereits die Höchstmenge (2) an Lieferungen erreicht.");
				return;
			} else if(chestCount >= 1 && level <= 32) {
				player.sendMessage("Du hast bereits die Höchstmenge (1) an Lieferungen erreicht.");
				return;
			}
			plugin.fWTrader.packTradeChest(player);
			return;
		} else {
			player.sendMessage("Nur Händler können Lieferungen verpacken.");
		}
		
	}

	private void lieferungAnzeigen(Player player, String chest) {

		if(plugin.fWTrader.isPlayerTrader(player)) {
			plugin.fWTrader.showTradeChestInfo(player, chest);
			return;
		} else {
			player.sendMessage("Nur Händler können ihre Lieferlisten betrachten.");
		}
		
	}
	
}