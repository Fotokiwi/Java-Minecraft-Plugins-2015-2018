package org.community.fourWays.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.community.fourWays.fourWays;

public class fourWaysCommandMain {
		
	private final fourWays plugin;
	 	 
	public fourWaysCommandMain(fourWays plugin)
	{
		this.plugin = plugin;
	}
	
	public boolean getCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		
		if(cmd.getName().equalsIgnoreCase("fourways")){
			if(args.length == 0) {
				showPluginInfo(sender);
				return true;
			}
			if(args.length == 1) {
				if(args[0].equalsIgnoreCase("help") || args[0].equalsIgnoreCase("hilfe") || args[0].equalsIgnoreCase("?")){
					plugin.fWHelp.getCommand(sender, cmd, commandLabel, args);
					return true;
				}	
				if(args[0].equalsIgnoreCase("adminmodus")){
					if(!(sender instanceof Player))
						return true;
					if(plugin.config.getStringList("Config.Admins").contains(((Player) sender).getUniqueId().toString())) {
						if(plugin.adminMode.get((Player) sender) == null){
							plugin.adminMode.put((Player) sender, true);
							plugin.getServer().broadcastMessage(ChatColor.RED + "===== Klassensystem: Adminmodus aktiviert für: " + sender.getName() + "=====");
							return true;
						}
						if(plugin.adminMode.get((Player) sender) == false){
							plugin.adminMode.put((Player) sender, true);
							plugin.getServer().broadcastMessage(ChatColor.RED + "===== Klassensystem: Adminmodus aktiviert für: " + sender.getName() + "=====");
							return true;
						}
						if(plugin.adminMode.get((Player) sender) == true){
							plugin.adminMode.remove((Player) sender);
							plugin.getServer().broadcastMessage(ChatColor.GRAY + "===== Klassensystem: Adminmodus deaktiviert für: " + sender.getName() + "=====");
							return true;
						}
						return true;
					} else {
						sender.sendMessage(ChatColor.RED + "Du hast keine Berechtigung den Admin-Modus für FourWays zu nutzen!");
						return true;
					}
						
				}	
				if(args[0].equalsIgnoreCase("biom")){
					Player player = (Player) sender;
					String biomeName = player.getLocation().getBlock().getBiome().name().toLowerCase().replace(" ", "_");
					
					player.sendMessage("Biom: " + player.getLocation().getBlock().getBiome().name() + " fourwaysName: " + biomeName);
				}
			}
			if(args.length == 2) {
				if(args[0].equalsIgnoreCase("help") || args[0].equalsIgnoreCase("hilfe") || args[0].equalsIgnoreCase("?")){
					plugin.fWHelp.getCommand(sender, cmd, commandLabel, args);
					return true;
				}				
			}
		}
		
		if(cmd.getName().equalsIgnoreCase("akzeptieren")){
			if(args.length == 0) {
				
				return true;
			} else {
				sender.sendMessage("Bitte nur /akzeptieren (/ja) oder /ablehnen (/nein) eingeben.");
				return true;
			}
		}
		
		if(cmd.getName().equalsIgnoreCase("ablehnen")){
			if(args.length == 0) {				
				
				return true;
			} else {
				sender.sendMessage("Bitte nur /akzeptieren (/ja) oder /ablehnen (/nein) eingeben.");
				return true;
			}
		}
		

		
		if(!(sender instanceof Player))
			return true;
		
		if(cmd.getName().equalsIgnoreCase("questmodus")){
			if(args.length == 4) {	
				Player player = null;
				String block = args[1];
				String npc = args[3];
				int amount = Integer.parseInt(args[2]);
				
				if(plugin.getPlayerByName(args[0]) != null) {
					player = plugin.getPlayerByName(args[0]);
				} else {
					return true;
				}
				
				if(plugin.fWCore.questModePlayer.get(player.getName()) != null) {
					if(plugin.fWCore.questModePlayer.get(player.getName())) {
						player.sendMessage("Du erledigst bereits eine Sammel-Aufgabe für " + plugin.fWCore.questModeNPC.get(player.getName()));
						return true;
					}
				}					
				
				plugin.fWCore.questModePlayer.put(player.getName(), true);
				plugin.fWCore.questModeBlock.put(player.getName(), block);
				plugin.fWCore.questModeAmount.put(player.getName(), amount);
				plugin.fWCore.questModeAmountTemp.put(player.getName(), 0);
				plugin.fWCore.questModeNPC.put(player.getName(), npc);
				
				plugin.fWCore.setPermission(player);
				
				player.sendMessage("Questmodus gesetzt.");
				
			} else {
				return true;
			}
		}
		
		return false;
	}
	
	private void showPluginInfo(CommandSender sender){
		sender.sendMessage(ChatColor.GREEN + plugin.logprefix);
	}
	
}