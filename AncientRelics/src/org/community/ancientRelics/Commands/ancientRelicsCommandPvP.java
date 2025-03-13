package org.community.ancientRelics.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.community.ancientRelics.ancientRelics;

public class ancientRelicsCommandPvP {
		
	private final ancientRelics plugin;
	 	 
	public ancientRelicsCommandPvP(ancientRelics plugin)
	{
		this.plugin = plugin;
	}
	
	public boolean getCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		
		if(cmd.getName().equalsIgnoreCase("pvp")){
			if(args.length == 0) {
				if(sender instanceof Player) {	
					plugin.aRPvP.showPlayerStatus(sender, sender.getName());
					return true;
				} else {
					sender.sendMessage(ChatColor.GOLD + "Der PvP-Status kann nicht in der Konsole geändert werden!");
					return true;
				}			
			}
			if(args.length == 1) {
				if(args[0].equalsIgnoreCase("help") || args[0].equalsIgnoreCase("hilfe") || args[0].equalsIgnoreCase("?")){
					plugin.aRHelp.getCommand(sender, cmd, commandLabel, args);
					return true;
				}
				if(args[0].equalsIgnoreCase("list")){
					plugin.aRPvP.showPlayerStatusList(sender);
					return true;
				}	
				if(args[0].equalsIgnoreCase("reload")){
					if(sender instanceof Player) {
						Player player = (Player) sender;
						if(!plugin.permission.has(player, "PvP.Admin")){
							player.sendMessage(ChatColor.AQUA + "Du hast keine Berechtigung die PvP Config(s) zu aktualisieren.");
							return true;
						}
							
					}
					plugin.aRPvP.aRPvPUser.reloadUserConfig();
					sender.sendMessage(ChatColor.AQUA + "PvP Config(s) erfolgreich aktualisiert.");
					return true;
				}	
				if(args[0].equalsIgnoreCase("toggle")){
					if(sender instanceof Player) {
						plugin.aRPvP.togglePlayerPvPStatus(sender.getName());
						return true;
					} else {
						sender.sendMessage(ChatColor.GOLD + "Der PvP-Status kann nicht in der Konsole geändert werden!");
						return true;
					}
				}
			}
			if(args.length == 2) {
				if(args[0].equalsIgnoreCase("help") || args[0].equalsIgnoreCase("hilfe") || args[0].equalsIgnoreCase("?")){
					plugin.aRHelp.getCommand(sender, cmd, commandLabel, args);
					return true;
				}
				if(args[0].equalsIgnoreCase("info")){
					plugin.aRPvP.showPlayerStatus(sender, args[1]);
					return true;
				}			
			}
			if(args.length == 3) {
				if(args[0].equalsIgnoreCase("force")){
					if(plugin.getPlayerByName(args[1]) == null)
						return true;
					
					if(sender instanceof Player) {
						Player player = (Player) sender;
						if(!plugin.permission.has(player, "PvP.Admin")){
							player.sendMessage(ChatColor.AQUA + "Du hast keine Berechtigung die PvP Config(s) zu aktualisieren.");
							return true;
						}							
					}
					
					plugin.aRPvP.forcePlayerPvPStatus(args[1], args[2]);
					return true;
				}			
			}
		}
		if(cmd.getName().equalsIgnoreCase("bounty")){
			if(args.length == 0) {
				if(sender instanceof Player) {
					plugin.aRPvP.setBounty((Player) sender);
					return true;
				} else {
					sender.sendMessage(ChatColor.GOLD + "Der Kopfgeldbefehl ist nicht für die Konsole zugelassen.");
					return true;
				}			
			}
		}
		if(cmd.getName().equalsIgnoreCase("duell")){
			if(args.length == 0) {
				if(sender instanceof Player) {
					Player player = (Player) sender;
					
					if(plugin.duellModus.get(player) != null) {
						player.sendMessage(ChatColor.AQUA + "Du befindest dich in einem Duell mit " + plugin.duellModus.get(player).getName() + ".");
						return true;
					} else {
						player.sendMessage(ChatColor.AQUA + "Du befindest dich derzeit in keinem Duell.");
						return true;
					}
				} else {
					sender.sendMessage(ChatColor.GOLD + "Der /duell Befehl ist nicht für die Konsole zugänglich.");
					return true;
				}		
			}
			if(args.length == 1) {
				if(sender instanceof Player) {
					Player leader = (Player) sender;
					Player invited = plugin.getPlayerByName(args[0]);
					
					if(plugin.duellModus.get(leader) != null) {
						sender.sendMessage(ChatColor.AQUA + "Du bist bereits in einem Duell mit " + plugin.duellModus.get(leader).getName() + ".");
						return true;
					}
					
					if(invited == null){
						sender.sendMessage(ChatColor.AQUA + "Bitte gib einen gültigen Spielernamen an.");
						return true;
					}
					
					if(leader == invited){
						sender.sendMessage(ChatColor.AQUA + "Du und dein Ego?");
						return true;
					}
					
					if(plugin.duellModus.get(invited) != null) {
						sender.sendMessage(ChatColor.AQUA + invited.getName() + " ist bereits in einem Duell mit " + plugin.duellModus.get(invited).getName() + ".");
						return true;
					}
					
					plugin.aRPvP.aRDuellInvite.sendRequest(leader, "#Duell#", invited);

					return true;
				} else {
					sender.sendMessage(ChatColor.GOLD + "Der /duell Befehl ist nicht für die Konsole zugänglich.");
					return true;
				}		
			}
		}
		
		return false;
	}
	
}