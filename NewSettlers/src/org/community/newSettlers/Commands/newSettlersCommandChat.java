package org.community.newSettlers.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.community.newSettlers.newSettlers;

public class newSettlersCommandChat {
		
	private final newSettlers plugin;
	 	 
	public newSettlersCommandChat(newSettlers plugin)
	{
		this.plugin = plugin;
	}
	
	public boolean getCommand(Player sender, Command cmd, String commandLabel, String[] args) {
		
		if(cmd.getName().equalsIgnoreCase("me")){
			if(args.length >= 1) {
				plugin.tempChat.put(sender, "Emote");
				String msg = "";
				for(int i = 0; i < args.length; i++) {
					msg += " " + args[i];
				}
				sender.chat(msg);
				return true;
			} else {
				plugin.user.set("Spieler." + sender.getName() + ".Chat.Modus", "Emote");
				plugin.nSUserConfig.saveConfig();
				sender.sendMessage("Du befindest dich jetzt im lokalen Emotekanal mit einer Reichweite von 50 Blöcken.");
				return true;
			}			
		}if(cmd.getName().equalsIgnoreCase("flüstern")){
			if(args.length >= 1) {
				plugin.tempChat.put(sender, "Flüstern");
				String msg = "";
				for(int i = 0; i < args.length; i++) {
					msg += " " + args[i];
				}
				sender.chat(msg);
				return true;
			} else {
				plugin.user.set("Spieler." + sender.getName() + ".Chat.Modus", "Flüstern");
				plugin.nSUserConfig.saveConfig();
				sender.sendMessage("Du befindest dich jetzt im lokalen Flüsterkanal mit einer Reichweite von 5 Blöcken.");
				return true;
			}			
		}
		if(cmd.getName().equalsIgnoreCase("lc")){
			if(args.length >= 1) {
				plugin.tempChat.put(sender, "Lokal");
				String msg = "";
				for(int i = 0; i < args.length; i++) {
					msg += " " + args[i];
				}
				sender.chat(msg);
				return true;
			} else {
				plugin.user.set("Spieler." + sender.getName() + ".Chat.Modus", "Lokal");
				plugin.nSUserConfig.saveConfig();
				sender.sendMessage("Du befindest dich jetzt im lokalen Chatkanal mit einer Reichweite von 50 Blöcken.");
				return true;
			}			
		}
		if(cmd.getName().equalsIgnoreCase("sc")){
			if(args.length >= 1) {
				plugin.tempChat.put(sender, "Stadt");
				String msg = "";
				for(int i = 0; i < args.length; i++) {
					msg += " " + args[i];
				}
				sender.chat(msg);
				return true;
			} else {
				plugin.user.set("Spieler." + sender.getName() + ".Chat.Modus", "Stadt");
				plugin.nSUserConfig.saveConfig();
				sender.sendMessage("Du befindest dich jetzt im städtischen Chatkanal.");
				return true;
			}			
		}
		if(cmd.getName().equalsIgnoreCase("gc")){
			if(args.length >= 1) {
				plugin.tempChat.put(sender, "Gruppe");
				String msg = "";
				for(int i = 0; i < args.length; i++) {
					msg += " " + args[i];
				}
				sender.chat(msg);
				return true;
			} else {
				plugin.user.set("Spieler." + sender.getName() + ".Chat.Modus", "Gruppe");
				plugin.nSUserConfig.saveConfig();
				sender.sendMessage("Du befindest dich jetzt im Chatkanal deiner Gruppe.");
				return true;
			}			
		}
		if(cmd.getName().equalsIgnoreCase("ac")){
			if(!plugin.nSCore.isAdmin((Player) sender) && !plugin.nSCore.isChatAdmin((Player) sender)) {
				sender.sendMessage(ChatColor.RED + "Du darfst diesen Kanal nicht nutzen.");
				return true;
			}
			if(args.length >= 1) {
				plugin.tempChat.put(sender, "Admin");
				String msg = "";
				for(int i = 0; i < args.length; i++) {
					msg += " " + args[i];
				}
				sender.chat(msg);
				return true;
			} else {
				plugin.user.set("Spieler." + sender.getName() + ".Chat.Modus", "Admin");
				plugin.nSUserConfig.saveConfig();
				sender.sendMessage("Du befindest dich jetzt im Admin Chatkanal.");
				return true;
			}			
		}
		if(cmd.getName().equalsIgnoreCase("hilfe")){
			if(args.length >= 1) {
				plugin.tempChat.put(sender, "Global");
				String msg = "";
				for(int i = 0; i < args.length; i++) {
					msg += " " + args[i];
				}
				sender.chat(msg);
				return true;
			} else {
				plugin.user.set("Spieler." + sender.getName() + ".Chat.Modus", "Global");
				plugin.nSUserConfig.saveConfig();
				sender.sendMessage("Du befindest dich jetzt im globalen Hilfekanal.");
				return true;
			}			
		}
		if(cmd.getName().equalsIgnoreCase("schreien")){
			if(args.length >= 1) {
				plugin.tempChat.put(sender, "Schreien");
				String msg = "";
				for(int i = 0; i < args.length; i++) {
					msg += " " + args[i];
				}
				sender.chat(msg);
				return true;
			} else {
				plugin.user.set("Spieler." + sender.getName() + ".Chat.Modus", "Schreien");
				plugin.nSUserConfig.saveConfig();
				sender.sendMessage("Du wirst deine Mitteilungen jetzt schreien!");
				return true;
			}			
		}
		if(cmd.getName().equalsIgnoreCase("global")){
			if(args.length >= 1) {
				plugin.tempChat.put(sender, "Global");
				String msg = "";
				for(int i = 0; i < args.length; i++) {
					msg += " " + args[i];
				}
				sender.chat(msg);
				return true;
			} else {
				plugin.user.set("Spieler." + sender.getName() + ".Chat.Modus", "Global");
				plugin.nSUserConfig.saveConfig();
				sender.sendMessage("Du befindest dich jetzt im globalen Chatkanal!");
				return true;
			}			
		}
		if(cmd.getName().equalsIgnoreCase("toggleGlobal")){
			if(plugin.user.getBoolean("Spieler." + sender.getName() + ".Chat.GlobalEnabled", true)) {
				plugin.user.set("Spieler." + sender.getName() + ".Chat.GlobalEnabled", false);
				plugin.nSUserConfig.saveConfig();
				sender.sendMessage("Du siehst nun nicht mehr die Nachrichten des globalen Chatkanals.");
				return true;
			} else {
				plugin.user.set("Spieler." + sender.getName() + ".Chat.GlobalEnabled", true);
				plugin.nSUserConfig.saveConfig();
				sender.sendMessage("Du siehst nun die Nachrichten des globalen Chatkanals.");
				return true;
			}			
		}
		if(cmd.getName().equalsIgnoreCase("msg")){
			if(args.length == 0) {
				sender.sendMessage(ChatColor.RED + "Der Befehl funktioniert /msg Ziel Text");
				return true;
			}
			if(args.length >= 1) {
				if(plugin.getServer().getPlayer(args[0]) == null && plugin.lastMsgPartner.get(sender) == null) {
					sender.sendMessage(ChatColor.RED + "Du hast keinen Gesprächspartner, oder er ist nicht mehr online.");
					return true;
				} else if(plugin.getServer().getPlayer(args[0]) == null && plugin.lastMsgPartner.get(sender) != null) {
					String msg = "";
					for(int i = 0; i < args.length; i++) {
						msg += " " + args[i];
					}
					sender.sendMessage(ChatColor.GRAY + sender.getName() + " -> " + plugin.lastMsgPartner.get(sender).getName() + ": " + msg);
					plugin.lastMsgPartner.get(sender).sendMessage(ChatColor.GRAY + sender.getName() + " -> " + plugin.lastMsgPartner.get(sender).getName() + ": " + msg);
					plugin.lastMsgPartner.put(plugin.lastMsgPartner.get(sender), (Player) sender);
					return true;
				} else if(plugin.getServer().getPlayer(args[0]) != null && args.length >= 2) {
					String msg = "";
					for(int i = 1; i < args.length; i++) {
						msg += " " + args[i];
					}
					sender.sendMessage(ChatColor.GRAY + sender.getName() + " -> " + args[0] + ": " + msg);
					plugin.getServer().getPlayer(args[0]).sendMessage(ChatColor.GRAY + sender.getName() + " -> " + args[0] + ": " + msg);
					plugin.lastMsgPartner.put(plugin.getServer().getPlayer(args[0]), (Player) sender);
					return true;
				}
				return true;
			}		
		}
		// MAXIMAL 16 ZEICHEN !!!
		/*if(cmd.getName().equalsIgnoreCase("chatname")){
			if(args.length == 0) {
				sender.sendMessage("Zum Wechseln des Chatnamens, bitte nur '/chatname Neuername' eingeben.");
				return true;
			} if(args.length >= 3) {
				sender.sendMessage("Der Name darf maximal aus Vor- und Nachname bestehen.");
				return true;
			} else {
				if(args.length == 1) {
					plugin.user.set("Spieler." + sender.getName() + ".Chat.Displayname", args[0]);
					plugin.nSUserConfig.saveConfig();
					sender.sendMessage("Dein im Chat angezeigter Name lautet nun: " + args[0]);
					return true;
				} else {
					plugin.user.set("Spieler." + sender.getName() + ".Chat.Displayname", args[0] + " " + args[1]);
					plugin.nSUserConfig.saveConfig();
					sender.sendMessage("Dein im Chat angezeigter Name lautet nun: " + args[0] + " " + args[1]);
					return true;
				}				
				
			}			
		}*/
		
		return false;
	}
	
}