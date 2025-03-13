package org.community.newSettlers.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.community.newSettlers.newSettlers;
import org.community.newSettlers.Trade.newSettlersTrade;

public class newSettlersCommandTrade {
		
	private final newSettlers plugin;
	 	 
	public newSettlersCommandTrade(newSettlers plugin)
	{
		this.plugin = plugin;
	}
	
	public boolean getCommand(Player sender, Command cmd, String commandLabel, String[] args) {
		
		if(cmd.getName().equalsIgnoreCase("handel")){
			if(args.length == 1) {				
				if(plugin.getPlayerByName(args[0]) != null) {
					if(sender.getWorld().getName().equalsIgnoreCase(plugin.getPlayerByName(args[0]).getWorld().getName())) {
						if(sender.getLocation().distance(plugin.getPlayerByName(args[0]).getLocation()) <= 10) {
							new newSettlersTrade(plugin, sender, plugin.getPlayerByName(args[0]));
							return true;
						} else {
							sender.sendMessage(ChatColor.DARK_RED + "Ihr müsst näher beisammen stehen.");
							return true;
						}
					} else {
						sender.sendMessage(ChatColor.DARK_RED + "Ihr müsst näher beisammen stehen.");
						return true;
					}
					
				} else {
					sender.sendMessage(ChatColor.DARK_RED + "Dieser Spieler steht nicht in unseren Registern.");
					return true;
				}
			}
			sender.sendMessage(ChatColor.DARK_GREEN + "Um mit anderen Spielern zu handeln gib einfach " + ChatColor.GOLD + "/handel {Spielername} " + ChatColor.DARK_GREEN + " ein.");
		}
		
		return false;
	}
	
}