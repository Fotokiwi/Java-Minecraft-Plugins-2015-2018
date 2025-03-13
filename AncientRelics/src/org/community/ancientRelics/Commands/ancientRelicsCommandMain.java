package org.community.ancientRelics.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.community.ancientRelics.ancientRelics;

public class ancientRelicsCommandMain {
		
	private final ancientRelics plugin;
	 	 
	public ancientRelicsCommandMain(ancientRelics plugin)
	{
		this.plugin = plugin;
	}
	
	public boolean getCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		
		if(cmd.getName().equalsIgnoreCase("ancientrelics")){
			if(args.length == 0) {
				showPluginInfo(sender);
				return true;
			}
			if(args.length == 1) {
				if(args[0].equalsIgnoreCase("help") || args[0].equalsIgnoreCase("hilfe") || args[0].equalsIgnoreCase("?")){
					plugin.aRHelp.getCommand(sender, cmd, commandLabel, args);
					return true;
				}				
			}
			if(args.length == 2) {
				if(args[0].equalsIgnoreCase("help") || args[0].equalsIgnoreCase("hilfe") || args[0].equalsIgnoreCase("?")){
					plugin.aRHelp.getCommand(sender, cmd, commandLabel, args);
					return true;
				}				
			}
		}
		
		if(cmd.getName().equalsIgnoreCase("akzeptieren")){
			if(args.length == 0) {
				
				if(sender instanceof Player) {
					if(plugin.newApproval.get((Player) sender) == null){
						sender.sendMessage("Du hast keine laufende Anfrage.");
						return true;
					} else {
						Player requester = plugin.newApproval.get((Player) sender);
						if(requester.isOnline() == false) {
							sender.sendMessage("Der Spieler, der dich eingeladen hat, ist leider offline.");
							plugin.aRGroups.aRGroupsInvite.deleteRequest(requester, (Player) sender);
							return true;
						} else {
							sender.sendMessage("Du hast die Einladung angenommen.");
							if(plugin.newRequest.get(requester).equalsIgnoreCase("#Duell#")){
								plugin.aRPvP.aRDuellInvite.acceptRequest(requester, (Player) sender);
								return true;
							} else {
								plugin.aRGroups.aRGroupsInvite.acceptRequest(requester, (Player) sender);
								return true;
							}
						}
					}
				}
				
				return true;
			} else {
				sender.sendMessage("Bitte nur /akzeptieren oder /ablehnen eingeben.");
				return true;
			}
		}
		
		if(cmd.getName().equalsIgnoreCase("ablehnen")){
			if(args.length == 0) {
				
				if(sender instanceof Player) {
					if(plugin.newApproval.get((Player) sender) == null){
						sender.sendMessage("Du hast keine laufende Anfrage.");
						return true;
					} else {
						Player requester = plugin.newApproval.get((Player) sender);
						if(requester.isOnline() == false) {
							sender.sendMessage("Der Spieler, der dich eingeladen hat, ist leider offline.");
							plugin.aRGroups.aRGroupsInvite.deleteRequest(requester, (Player) sender);
							return true;
						} else {
							sender.sendMessage("Du hast die Einladung abgelehnt.");
							if(plugin.newRequest.get(requester).equalsIgnoreCase("#Duell#")){
								plugin.aRPvP.aRDuellInvite.denyRequest(requester, (Player) sender);
								return true;
							} else {
								plugin.aRGroups.aRGroupsInvite.denyRequest(requester, (Player) sender);
								return true;
							}
						}
					}
				}
				
				return true;
			} else {
				sender.sendMessage("Bitte nur /akzeptieren oder /ablehnen eingeben.");
				return true;
			}
		}
		
		return false;
	}
	
	private void showPluginInfo(CommandSender sender){
		sender.sendMessage(ChatColor.GREEN + plugin.logprefix);
	}
	
}