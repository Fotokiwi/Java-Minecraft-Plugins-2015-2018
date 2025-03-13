package org.community.ancientRelics.Commands;

import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.community.ancientRelics.ancientRelics;
import org.community.ancientRelics.Groups.Classes.Groups;

public class ancientRelicsCommandGroups {
		
	private final ancientRelics plugin;
	 	 
	public ancientRelicsCommandGroups(ancientRelics plugin)
	{
		this.plugin = plugin;
	}
	
	public boolean getCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		
		if(cmd.getName().equalsIgnoreCase("group")){
			if(args.length == 0) {
				//sender.sendMessage(ChatColor.GOLD + "Das Gruppensystem befindet sich in der Beta-Phase.");
				sender.sendMessage("");
				if(plugin.playerGroupMembership.get((Player) sender) == null) {
					sender.sendMessage(ChatColor.AQUA + "Du gehörst keiner Gruppe an.");
				} else {
					 plugin.playerGroupMembership.get((Player) sender).displayGroupInfo((Player) sender);
				}				
				return true;
			}
			
			if(args.length == 1) {
				if(args[0].equalsIgnoreCase("help") || args[0].equalsIgnoreCase("hilfe") || args[0].equalsIgnoreCase("?")){
					plugin.aRHelp.getCommand(sender, cmd, commandLabel, args);
					return true;
				}
				if(args[0].equalsIgnoreCase("leave")) {
					if(sender instanceof Player) {
						Player player = (Player) sender;
						Groups group = plugin.aRGroups.getPlayerGroupInfo(player);
						if(group == null){
							sender.sendMessage(ChatColor.AQUA + "Du gehörst keiner Gruppe an.");
							return true;
						}
						
						if(player.getName().equalsIgnoreCase(group.getLeader())){
							sender.sendMessage(ChatColor.AQUA + "Du musst erst einen Nachfolger bestimmen.");
							return true;
						}
						
						group.removeMember(player);
						sender.sendMessage(ChatColor.AQUA + "Du hast die Gruppe " + group.getGroupName() + " verlassen.");
						return true;
							
					}
				}
				if(args[0].equalsIgnoreCase("delete")) {
					if(sender instanceof Player) {
						Player leader = (Player) sender;
						
						if(plugin.aRGroups.getPlayerGroupInfo(leader) != null) {
							if(!plugin.aRGroups.getPlayerGroupInfo(leader).getLeader().equalsIgnoreCase(leader.getName())){
								sender.sendMessage(ChatColor.AQUA + "Du bist nicht der Anführer dieser Gruppe.");
								return true;
							}
						}
						
						sender.sendMessage(ChatColor.AQUA + "Du hast die Gruppe " + plugin.aRGroups.getPlayerGroupInfo(leader).getGroupName() + " gelöscht.");
						plugin.aRGroups.getPlayerGroupInfo(leader).eraseGroup();
					}
				}
				return true;
			}
			
			if(args.length == 2) {
				if(args[0].equalsIgnoreCase("help") || args[0].equalsIgnoreCase("hilfe") || args[0].equalsIgnoreCase("?")){
					plugin.aRHelp.getCommand(sender, cmd, commandLabel, args);
					return true;
				}
				if(args[0].equalsIgnoreCase("invite")) {
					if(sender instanceof Player) {
						Player leader = (Player) sender;
						Player invited = plugin.getPlayerByName(args[1]);
						
						if(plugin.aRGroups.getPlayerGroupInfo(leader) != null) {
							if(!plugin.aRGroups.getPlayerGroupInfo(leader).getLeader().equalsIgnoreCase(leader.getName())){
								sender.sendMessage(ChatColor.AQUA + "Du bist nicht der Anführer dieser Gruppe.");
								return true;
							}
						}
						
						if(invited == null){
							sender.sendMessage(ChatColor.AQUA + "Bitte gib einen gültigen Spielernamen an.");
							return true;
						}
						
						if(plugin.aRGroups.getPlayerGroupInfo(leader) != null) {
							if(plugin.aRGroups.getPlayerGroupInfo(leader).getMemberCount() >= 7) {
								sender.sendMessage(ChatColor.AQUA + "Deine Gruppe hat die maximale Spielerzahl von 8 erreicht.");
								return true;
							}
						}
						
						if(leader == invited){
							sender.sendMessage(ChatColor.AQUA + "Du und dein Ego?");
							return true;
						}
						
						if(plugin.aRGroups.getPlayerGroupInfo(invited) != null) {
							sender.sendMessage(ChatColor.AQUA + invited.getName() + " ist bereits Mitglied einer Gruppe.");
							return true;
						}
						
						plugin.aRGroups.aRGroupsInvite.sendRequest(leader, "Abenteurer", invited);

						return true;
					} else {
						sender.sendMessage(ChatColor.GOLD + "Der /invite Befehl ist nicht für die Konsole zugänglich.");
						return true;
					}
				}
				
				if(args[0].equalsIgnoreCase("remove")) {
					if(sender instanceof Player) {
						Player leader = (Player) sender;
						Player removed = plugin.getPlayerByName(args[1]);
						
						if(plugin.aRGroups.getPlayerGroupInfo(leader) == null) {
							sender.sendMessage(ChatColor.AQUA + "Du bist in keiner Gruppe!");
							return true;
						}
						
						if(plugin.aRGroups.getPlayerGroupInfo(leader) != null) {
							if(!plugin.aRGroups.getPlayerGroupInfo(leader).getLeader().equalsIgnoreCase(leader.getName())){
								sender.sendMessage(ChatColor.AQUA + "Du bist nicht der Anführer dieser Gruppe.");
								return true;
							}
						}
						
						if(leader == removed){
							sender.sendMessage(ChatColor.AQUA + "Du musst erst einen Nachfolger bestimmen.");
							return true;
						}
						
						if(removed == null){
							OfflinePlayer offRemoved = plugin.getOfflinePlayerByName(args[1]);						
							Groups group = plugin.aRGroups.getPlayerGroupInfo(leader);
							
							group.removeMember((Player) offRemoved);
							leader.sendMessage(ChatColor.AQUA + "Du hast " + offRemoved.getName() + " aus der Gruppe entfernt.");
							return true;	
						} else {							
							Groups group = plugin.aRGroups.getPlayerGroupInfo(leader);
							
							group.removeMember(removed);
							removed.sendMessage(ChatColor.AQUA + "Du wurdest aus der Gruppe " + group.getGroupName() + " entfernt.");
							leader.sendMessage(ChatColor.AQUA + "Du hast " + removed.getName() + " aus der Gruppe entfernt.");
						}

						return true;
					} else {
						sender.sendMessage(ChatColor.GOLD + "Der /invite Befehl ist nicht für die Konsole zugänglich.");
						return true;
					}
				}
				
				if(args[0].equalsIgnoreCase("toggle")) {
					if(sender instanceof Player) {
						Player leader = (Player) sender;
						
						if(plugin.aRGroups.getPlayerGroupInfo(leader) == null) {
							sender.sendMessage(ChatColor.AQUA + "Du bist in keiner Gruppe!");
							return true;
						}
						
						if(plugin.aRGroups.getPlayerGroupInfo(leader) != null) {
							if(!plugin.aRGroups.getPlayerGroupInfo(leader).getLeader().equalsIgnoreCase(leader.getName())){
								sender.sendMessage(ChatColor.AQUA + "Du bist nicht der Anführer dieser Gruppe.");
								return true;
							}
						}
						
						Groups group = plugin.aRGroups.getPlayerGroupInfo(leader);
						
						if(args[1].equalsIgnoreCase("pvp")) {
							group.togglePvP();
							if(group.getPvPStatus()){
								leader.sendMessage(ChatColor.AQUA + "PvP wurde innerhalb der Gruppe aktiviert.");
							} else {
								leader.sendMessage(ChatColor.AQUA + "PvP wurde innerhalb der Gruppe deaktiviert.");
							}
							return true;
						}
						
						if(args[1].equalsIgnoreCase("potion")) {
							group.togglePotion();
							if(group.getPotionStatus()){
								leader.sendMessage(ChatColor.AQUA + "Tränke wurden innerhalb der Gruppe aktiviert.");
							} else {
								leader.sendMessage(ChatColor.AQUA + "Tränke wurden innerhalb der Gruppe deaktiviert.");
							}
							return true;
						}
						
						return true;
					} else {
						sender.sendMessage(ChatColor.GOLD + "Der /toggle Befehl ist nicht für die Konsole zugänglich.");
						return true;
					}
				}		
			}
			
			if(args.length == 3) {
				if(args[0].equalsIgnoreCase("set")) {
					if(args[1].equalsIgnoreCase("leader")){
						if(sender instanceof Player) {
							Player leader = (Player) sender;
							Player invited = plugin.getPlayerByName(args[2]);		
							Groups group = null;
							
							if(plugin.aRGroups.getPlayerGroupInfo(leader) != null) {
								if(!plugin.aRGroups.getPlayerGroupInfo(leader).getLeader().equalsIgnoreCase(leader.getName())){
									sender.sendMessage(ChatColor.AQUA + "Du bist nicht der Anführer dieser Gruppe.");
									return true;
								} else {
									group = plugin.aRGroups.getPlayerGroupInfo(leader);
								}
							} else {
								sender.sendMessage(ChatColor.AQUA + "Du bist in keiner Gruppe.");
								return true;
							}
							
							if(leader == invited){
								sender.sendMessage(ChatColor.AQUA + "Du bist doch schon Anführer!");
								return true;
							}
							
							if(invited == null){
								invited = (Player) plugin.getOfflinePlayerByName(args[2]);
								if(invited == null) {
									sender.sendMessage(ChatColor.AQUA + "Bitte gib einen gültigen Spielernamen an.");
									return true;
								}								
							}
							
							if(plugin.aRGroups.getPlayerGroupInfo(invited) != plugin.aRGroups.getPlayerGroupInfo(leader)) {
								sender.sendMessage(ChatColor.AQUA + invited.getName() + " ist nicht Mitglied deiner Gruppe.");
								return true;
							}							

							leader.sendMessage(ChatColor.AQUA + "Du bist nicht länger Anführer der Gruppe.");
							
							group.setLeader(invited.getName());
							group.addMember(leader);
							group.demoteMember(invited);

							return true;
						} else {
							sender.sendMessage(ChatColor.GOLD + "Der /set leader Befehl ist nicht für die Konsole zugänglich.");
							return true;
						}
					}
					if(args[1].equalsIgnoreCase("name")){
						if(sender instanceof Player) {
							Player leader = (Player) sender;	
							Groups group = null;
							
							if(args[2].length() > 10) {
								sender.sendMessage(ChatColor.AQUA + "Der Gruppenname ist auf maximal 10 Zeichen beschränkt.");
								return true;
							}
							
							if(plugin.aRGroups.getPlayerGroupInfo(leader) != null) {
								if(!plugin.aRGroups.getPlayerGroupInfo(leader).getLeader().equalsIgnoreCase(leader.getName())){
									sender.sendMessage(ChatColor.AQUA + "Du bist nicht der Anführer dieser Gruppe.");
									return true;
								} else {
									group = plugin.aRGroups.getPlayerGroupInfo(leader);
								}
							} else {
								sender.sendMessage(ChatColor.AQUA + "Du bist in keiner Gruppe.");
								return true;
							}							

							leader.sendMessage(ChatColor.AQUA + "Du hast den Gruppennamen auf " + args[2] + " geändert.");
							
							group.setGroupName(args[2]);

							return true;
						} else {
							sender.sendMessage(ChatColor.GOLD + "Der /set leader Befehl ist nicht für die Konsole zugänglich.");
							return true;
						}
					}						
				}
			}
		}
		
		return false;
	}
	
}