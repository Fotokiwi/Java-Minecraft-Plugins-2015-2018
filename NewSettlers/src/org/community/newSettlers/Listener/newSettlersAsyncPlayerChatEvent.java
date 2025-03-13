package org.community.newSettlers.Listener;

import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.community.ancientRelics.Groups.Classes.Groups;
import org.community.newSettlers.newSettlers;
import org.community.newSettlers.Town.Town;

public class newSettlersAsyncPlayerChatEvent implements Listener {
	
	private final newSettlers plugin;

	public newSettlersAsyncPlayerChatEvent(newSettlers plugin)
	{
		this.plugin = plugin;
	}

	@EventHandler(priority = EventPriority.NORMAL)
	public void onPlayerChat(AsyncPlayerChatEvent event) {
		
		Player sender = event.getPlayer();
		String message = event.getMessage();
		String format = event.getFormat();
		Set<Player> recipients = event.getRecipients();
		String chatMode = plugin.user.getString("Spieler." + sender.getName() + ".Chat.Modus", "Lokal");
		if(plugin.tempChat.get(sender) != null)
			chatMode = plugin.tempChat.get(sender);
		
		if(chatMode.equalsIgnoreCase("Lokal")) {
			localChat(event, sender, format, message, recipients);
		} else if(chatMode.equalsIgnoreCase("Emote")) {
			emoteChat(event, sender, format, message, recipients);
		} else if(chatMode.equalsIgnoreCase("Flüstern")) {
			whisperChat(event, sender, format, message, recipients);
		} else if(chatMode.equalsIgnoreCase("Stadt")) {
			townChat(event, sender, format, message, recipients);
		} else if (chatMode.equalsIgnoreCase("Gruppe")) {
			groupChat(event, sender, format, message, recipients);
		} else if (chatMode.equalsIgnoreCase("Admin")) {
			adminChat(event, sender, format, message, recipients);
		} else if (chatMode.equalsIgnoreCase("Global")) {
			globalChat(event, sender, format, message, recipients);
		}else if(chatMode.equalsIgnoreCase("Schreien")){
			if(plugin.screamCooldown.get(sender) == null) {
				plugin.screamCooldown.put(sender, System.currentTimeMillis());
			} else {
				if(plugin.screamCooldown.get(sender) + 30000 > System.currentTimeMillis()) {
					sender.sendMessage(ChatColor.RED + "Du musst deine Stimme schonen, bevor du wieder schreien kannst.");
					sender.sendMessage(ChatColor.RED + "Verbleibender Cooldown: " + ChatColor.AQUA + ((plugin.screamCooldown.get(sender) + 30000) - System.currentTimeMillis()) / 1000 + "s");
					event.setCancelled(true);
				} else {
					plugin.screamCooldown.put(sender, System.currentTimeMillis());					
				}
			}
			screamChat(event, sender, format, message, recipients);
		} else {
			if(plugin.chatCooldown.get(sender) == null) {
				plugin.chatCooldown.put(sender, System.currentTimeMillis());
			} else {
				if(plugin.chatCooldown.get(sender) + 300000 > System.currentTimeMillis()) {
					sender.sendMessage(ChatColor.RED + "Für dem globalen Chat gibt es einen Cooldown von 5min. Er ist für den Notfall bestimmt.");
					sender.sendMessage(ChatColor.RED + "Verbleibender Cooldown: " + ChatColor.AQUA + ((plugin.chatCooldown.get(sender) + 300000) - System.currentTimeMillis()) / 1000 + "s");
					event.setCancelled(true);
				} else {
					plugin.chatCooldown.put(sender, System.currentTimeMillis());					
				}
			}
			helpChat(event, sender, format, message, recipients);
		}
		plugin.tempChat.remove(sender);
		
		/*if(sender.getName().equalsIgnoreCase("Elayla"))
			sender.setDisplayName("Elayla Sha'Ren");
		
		format = ChatColor.YELLOW + "Beta-Spieler " + ChatColor.WHITE + "<%s>: %s";
				
		event.setFormat(format);
		event.setMessage(ChatColor.AQUA + message);*/

	}

	// Nur f�r Spieler innerhalb einer Reichweite von 175 Bl�cken sichbar.
	private void localChat(AsyncPlayerChatEvent event, Player sender, String format, String message, Set<Player> recipients) {
		
		Player[] player = recipients.toArray(new Player[0]);
		
		//sender.sendMessage("[DEBUG]: Empf�nger-Liste: " + player);

		recipients.clear();
		
		for(Player p : player) {
			if(sender.getWorld() != p.getWorld()) {
				//break;
			} else {
				if(sender.getLocation().distance(p.getLocation()) >= 50.0) {
					//break;
				} else {
					recipients.add(p);
				}
			}
			
		}
		
		if(!recipients.contains(sender)) {
			recipients.add(sender);
			//sender.sendMessage("Sender war nicht in der Liste, manuelle Aufschaltung");
		}
		
		format = ChatColor.YELLOW + "Lokal " + ChatColor.GREEN + "<%s>: " + ChatColor.WHITE + "%s";
				
		event.setFormat(format);

		event.setMessage(message);
	}

	// Nur f�r Spieler innerhalb einer Reichweite von 175 Bl�cken sichbar.
	private void screamChat(AsyncPlayerChatEvent event, Player sender, String format, String message, Set<Player> recipients) {
		
		Player[] player = recipients.toArray(new Player[0]);
		
		//sender.sendMessage("[DEBUG]: Empf�nger-Liste: " + player);

		recipients.clear();
		
		for(Player p : player) {
			if(sender.getWorld() != p.getWorld()) {
				//break;
			} else {
				if(sender.getLocation().distance(p.getLocation()) >= 100.0) {
					//break;
				} else {
					recipients.add(p);
				}
			}
			
		}
		
		if(!recipients.contains(sender)) {
			recipients.add(sender);
			//sender.sendMessage("Sender war nicht in der Liste, manuelle Aufschaltung");
		}
		
		format = ChatColor.YELLOW + "Schrei " + ChatColor.GREEN + "<%s>: " + ChatColor.WHITE + "%s";
				
		event.setFormat(format);

		event.setMessage(message.toUpperCase());
	}

	// Nur f�r Spieler innerhalb einer Reichweite von 175 Bl�cken sichbar.
	private void emoteChat(AsyncPlayerChatEvent event, Player sender, String format, String message, Set<Player> recipients) {
		
		Player[] player = recipients.toArray(new Player[0]);
		
		//sender.sendMessage("[DEBUG]: Empf�nger-Liste: " + player);

		recipients.clear();
		
		for(Player p : player) {
			if(sender.getWorld() != p.getWorld()) {
				//break;
			} else {
				if(sender.getLocation().distance(p.getLocation()) >= 50.0) {
					//break;
				} else {
					recipients.add(p);
				}
			}
			
		}
		
		if(!recipients.contains(sender)) {
			recipients.add(sender);
			//sender.sendMessage("Sender war nicht in der Liste, manuelle Aufschaltung");
		}
		
		format = ChatColor.GRAY + "* %s%s *";
				
		event.setFormat(format);

		event.setMessage(message);
	}

	// Nur f�r Spieler innerhalb einer Reichweite von 175 Bl�cken sichbar.
	private void whisperChat(AsyncPlayerChatEvent event, Player sender, String format, String message, Set<Player> recipients) {
		
		Player[] player = recipients.toArray(new Player[0]);
		
		//sender.sendMessage("[DEBUG]: Empf�nger-Liste: " + player);

		recipients.clear();
		
		for(Player p : player) {
			if(sender.getWorld() != p.getWorld()) {
				//break;
			} else {
				if(sender.getLocation().distance(p.getLocation()) >= 5.0) {
					//break;
				} else {
					recipients.add(p);
				}
			}
			
		}
		
		if(!recipients.contains(sender)) {
			recipients.add(sender);
			//sender.sendMessage("Sender war nicht in der Liste, manuelle Aufschaltung");
		}
		
		format = ChatColor.GREEN + "<%s> " + ChatColor.GRAY + "flüstert:" + ChatColor.WHITE + "%s";
				
		event.setFormat(format);

		event.setMessage(message);
	}

	// Nur f�r Spieler innerhalb einer Reichweite von 175 Bl�cken sichbar.
	private void adminChat(AsyncPlayerChatEvent event, Player sender, String format, String message, Set<Player> recipients) {
		
		Player[] player = recipients.toArray(new Player[0]);
		
		//sender.sendMessage("[DEBUG]: Empf�nger-Liste: " + player);

		recipients.clear();
		
		for(Player p : player) {
			if(sender.getWorld() != p.getWorld()) {
				//break;
			} else {
				if(plugin.nSCore.isAdmin(p) || plugin.nSCore.isChatAdmin(p)) {
					recipients.add(p);
				} else {
					//break
				}
			}
			
		}
		
		if(!recipients.contains(sender)) {
			recipients.add(sender);
			//sender.sendMessage("Sender war nicht in der Liste, manuelle Aufschaltung");
		}
		
		format = ChatColor.RED + "AdminChat " + ChatColor.GREEN + "<%s>: " + ChatColor.WHITE + "%s";
				
		event.setFormat(format);

		event.setMessage(message);
	}

	// Nur f�r Spieler einer Stadt sichtbar (global lesbar)
	private void townChat(AsyncPlayerChatEvent event, Player sender, String format, String message, Set<Player> recipients) {

		//sender.sendMessage("[DEBUG]: Empf�nger-Liste: " + player);

		recipients.clear();
		
		Town town = null;
		
		town = plugin.nSCore.getPlayerTown(sender);
		
		if(town == null) {
			sender.sendMessage("Du bist kein Bürger einer Stadt und kannst diesen Chat nicht nutzen.");
			return;
		}
		
		for(int i = 0; i < town.getMemberList().size(); i++) {
			if(plugin.getPlayerByName(town.getMemberList().get(i)) != null) {
				recipients.add(plugin.getPlayerByName(town.getMemberList().get(i)));
			}
		}
		
		if(!recipients.contains(sender)) {
			recipients.add(sender);
			//sender.sendMessage("Sender war nicht in der Liste, manuelle Aufschaltung");
		}
		
		format = ChatColor.LIGHT_PURPLE + "" + town.getName() + " " + ChatColor.GREEN + "<%s>: " + ChatColor.WHITE + "%s";
				
		event.setFormat(format);

		event.setMessage(message);
	}

	// Nur f�r Spieler einer Gruppe sichbat (global lesbar)
	private void groupChat(AsyncPlayerChatEvent event, Player sender, String format, String message, Set<Player> recipients) {

		//sender.sendMessage("[DEBUG]: Empf�nger-Liste: " + player);

		recipients.clear();
		
		Groups group = null;
		
		group = plugin.ancientRelics.aRGroups.getPlayerGroupInfo(sender);
		
		if(group == null) {
			sender.sendMessage("Du bist kein Mitglied einer Gruppe und kannst diesen Chat nicht nutzen.");
			return;
		}
		
		for(int i = 0; i < group.getMembers().size(); i++) {
			if(plugin.getPlayerByName(group.getMembers().get(i)) != null) {
				recipients.add(plugin.getPlayerByName(group.getMembers().get(i)));
			}
		}
		if(plugin.getPlayerByName(group.getLeader()) != null) {
			recipients.add(plugin.getPlayerByName(group.getLeader()));
		}
		
		if(!recipients.contains(sender)) {
			recipients.add(sender);
			//sender.sendMessage("Sender war nicht in der Liste, manuelle Aufschaltung");
		}
		
		format = ChatColor.AQUA + "" + group.getGroupName() + " " + ChatColor.GREEN + "<%s>: " + ChatColor.WHITE + "%s";
				
		event.setFormat(format);

		event.setMessage(message);
	}
	
	private void globalChat(AsyncPlayerChatEvent event, Player sender, String format, String message, Set<Player> recipients) {
		recipients.clear(); 
		
		for(Player op : Bukkit.getServer().getOnlinePlayers()){
			if(plugin.user.getBoolean("Spieler." + sender.getName() + ".Chat.GlobalEnabled", true)){
				recipients.add(op);
			}
		}
		
		format = ChatColor.LIGHT_PURPLE + "Globaler Chat " + ChatColor.GREEN + "<%s>: " + ChatColor.WHITE + "%s";
		
		event.setFormat(format);

		event.setMessage(message);
	}

	// F�r alle Spieler auf dem Server sichbar, nur ein Notfall-Chat falls die anderen nicht funktionieren
	private void helpChat(AsyncPlayerChatEvent event, Player sender, String format, String message, Set<Player> recipients) {
		
		format = ChatColor.LIGHT_PURPLE + "Hilfe " + ChatColor.GREEN + "<%s>: " + ChatColor.WHITE + "%s";
		
		event.setFormat(format);

		event.setMessage(message);
	}
	
}