package org.community.fourWays.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.community.fourWays.fourWays;
import org.community.fourWays.User.User;

public class fourWaysCommandUser {
		
	private final fourWays plugin;
	 	 
	public fourWaysCommandUser(fourWays plugin)
	{
		this.plugin = plugin;
	}
	
	public boolean getCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		
		if(cmd.getName().equalsIgnoreCase("beruf")){

			if(args.length == 0) {
				if(sender instanceof Player) {
					User user = plugin.fWUsers.getPlayerInfo((Player) sender);
					sender.sendMessage("Hash " + user.getJobHash());
					sender.sendMessage("Stufe " + user.getLevel());
					sender.sendMessage(user.getExp() + " / " + plugin.fWUsers.fWLevels.calculateLevelExp(user.getLevel() + 1));
					sender.sendMessage("Maximales Level " + user.getMaxLevel());
					sender.sendMessage("Blockbreak-Exp " + user.getBlockbreakExp());
					sender.sendMessage("Playtime-Exp " + user.getPlaytimeExp());
					sender.sendMessage("Reset in " + user.whenIsDailyResetInMinutes() + "min");
					return true;
				} else {
					sender.sendMessage("Dieser Befehl steht der Konsole nicht zur Verfügung.");
					return true;
				}			
			}

			if(args.length == 2) {	
				if(args[0].equalsIgnoreCase("aufstieg")) {
					if(sender instanceof Player) {
						sender.sendMessage("Dieser Befehl steht Spielern nicht zur Verfügung.");
						return true;
					} else {
						Player player = plugin.getPlayerByName(args[1]);
						if(player == null) {
							plugin.LogInfo("Der Spieler ist nicht online.");
							return true;
						}
						User user = plugin.fWUsers.getPlayerInfo(player);
						plugin.LogInfo("Der Spieler " + user.getName() + " wurde aufgewertet.");
						user.levelUp();
						return true;
					}
				}	
			}

			if(args.length == 3) {	
				if(args[0].equalsIgnoreCase("lernen")) {
					if(sender instanceof Player) {
						sender.sendMessage("Dieser Befehl steht Spielern nicht zur Verfügung.");
						return true;
					} else {
						Player player = plugin.getPlayerByName(args[2]);
						if(player == null)
							return true;
						User user = plugin.fWUsers.getPlayerInfo(player);
						int feedback = user.addJob(args[1]);
						if(feedback == -1){
							player.sendMessage("Fehler in den Berufs-Voraussetzungen von " + plugin.jobs.getString("Beruf." + args[1] + ".Name"));
						}
						if(feedback == 0){
							player.sendMessage("Dies ist kein gültiger Beruf.");
						}
						if(feedback == 1) {
							player.sendMessage("Du hast bereits einen Beruf auf dieser Ebene.");
						}
						if(feedback == 2) {
							player.sendMessage("Du hast den Beruf " + plugin.jobs.getString("Beruf." + args[1] + ".Name") + " gelernt.");
						}
						if(feedback == 3) {
							player.sendMessage("Du erfüllst nicht die Voraussetzungen für den " + plugin.jobs.getString("Beruf." + args[1] + ".Name"));
						}
						return true;
					}	
				}
				if(args[0].equalsIgnoreCase("lehren")) {
					if(sender instanceof Player) {
						sender.sendMessage("Dieser Befehl steht Spielern nicht zur Verfügung.");
						return true;
					} else {						
						Player player = plugin.getPlayerByName(args[1]);
						if(player == null)
							return true;
						User user = plugin.fWUsers.getPlayerInfo(player);
						//user.grantExp(plugin.fWExpCalc.getExperienceFromLevel(Integer.parseInt(args[2])));
						user.grantExp(Integer.parseInt(args[2]));
						//player.setLevel(0);
					}
				}
				if(args[0].equalsIgnoreCase("verlernen")) {
					if(sender instanceof Player) {
						sender.sendMessage("Dieser Befehl steht Spielern nicht zur Verfügung.");
						return true;
					} else {
						Player player = plugin.getPlayerByName(args[2]);
						if(player == null)
							return true;
						User user = plugin.fWUsers.getPlayerInfo(player);
						int feedback = user.removeJob(args[1]);
						if(feedback == 2) {
							player.sendMessage("Du hast den Beruf " + plugin.jobs.getString("Beruf." + args[1] + ".Name") + " verlernt.");
						}
						return true;
					}		
				}
			}	
		}
		
		return false;
	}
	
}