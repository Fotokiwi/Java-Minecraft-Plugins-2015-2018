package org.community.Angeln.Achievement;

import java.util.Set;

import org.bukkit.ChatColor;
import org.bukkit.block.Sign;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.block.SignChangeEvent;
import org.community.Angeln.Angeln;

public class Achievements {
	
	Angeln plugin = null;
	
	public Achievements(Angeln plugin) {
		this.plugin = plugin;
	}
	
	public void setCaught(Player player, String fish) {
		String uuid = player.getUniqueId().toString();
		String name = player.getName();
		
		plugin.players.set("Spieler." + uuid + ".Fische." + fish + ".Gefangen", true);
		plugin.players.set("Spieler." + uuid + ".Fische." + fish + ".Anzahl", plugin.players.getInt("Spieler." + uuid + ".Fische." + fish + ".Anzahl", 0) + 1);
		plugin.players.set("Spieler." + uuid + ".Name", name);
		
	}
	
	public void displayGeneralStats(SignChangeEvent event, Player player) {
		
		ConfigurationSection caught = plugin.players.getConfigurationSection("Spieler." + player.getUniqueId().toString() + ".Fische");
		Set<String> caughtKeys = caught.getKeys(false);
  	  	String[] caughtCount = caughtKeys.toArray(new String[0]);
  	  	
  	  	ConfigurationSection fishes = plugin.fishes.getConfigurationSection("Fische");
		Set<String> fishesKeys = fishes.getKeys(false);
	  	String[] fishesCount = fishesKeys.toArray(new String[0]);
		
		event.setLine(0, ChatColor.DARK_GREEN + player.getName() + "s");
		event.setLine(1, ChatColor.DARK_GREEN + "Angelerfolge:");
		event.setLine(2, ChatColor.DARK_PURPLE + "" + caughtCount.length + ChatColor.DARK_GREEN + " von " + ChatColor.DARK_PURPLE + fishesCount.length);		
		event.setLine(3, ChatColor.DARK_GREEN + "Fischarten");
		((Sign)event.getBlock().getState()).update(true);
	}
	
	public void displaySpecialStats(SignChangeEvent event, Player player, String fish) {
  	  	
  	  	ConfigurationSection fishes = plugin.fishes.getConfigurationSection("Fische");
		Set<String> fishesKeys = fishes.getKeys(false);
	  	String[] fishesCount = fishesKeys.toArray(new String[0]);
	  	
	  	boolean contains = false;
	  	
	  	for(int i = 0; i < fishesCount.length; i++) {
	  		if(fishesCount[i].equals(fish)) {
	  			contains = true;
	  			break;
	  		}
	  	}
	  	
	  	if(contains) {
	  		
	  		if(plugin.players.getString("Spieler." + player.getUniqueId().toString() + ".Fische." + fish + ".Gefangen") == null) {
	  			player.sendMessage(ChatColor.DARK_RED + "Du hast diesen Fisch noch nicht gefangen.");
	  		} else {
	  			event.setLine(0, ChatColor.DARK_GREEN + player.getName() + "s");
	  			event.setLine(1, ChatColor.DARK_GREEN + "Angelerfolg:");
	  			event.setLine(2, ChatColor.DARK_GREEN + fish + "e:");	
	  			event.setLine(3, ChatColor.DARK_PURPLE + "" + plugin.players.getString("Spieler." + player.getUniqueId().toString() + ".Fische." + fish + ".Anzahl"));
	  			((Sign)event.getBlock().getState()).update(true);
	  		}
	  		
	  	} else {
	  		player.sendMessage(ChatColor.DARK_RED + "Dieser Fisch ist nicht bekannt.");
	  	}		
		
	}
	
	public void displayTop5(CommandSender sender) {
  	  	
  	  	ConfigurationSection spieler = plugin.players.getConfigurationSection("Spieler");
		Set<String> spielerKeys = spieler.getKeys(false);
	  	String[] spielerList = spielerKeys.toArray(new String[0]);
	  	
	  	String platz[] = { "", "", "", "", "" };
		int anzahl[] = { 0, 0, 0, 0, 0 };
		
		for(int i = 0; i < spielerList.length; i++) {
			ConfigurationSection fische = plugin.players.getConfigurationSection("Spieler." + spielerList[i] + ".Fische");
			Set<String> fischeKeys = fische.getKeys(false);
		  	String[] fischeList = fischeKeys.toArray(new String[0]);
		  	
			int aktuelleanzahl = fischeList.length;
			String user = plugin.players.getString("Spieler." + spielerList[i] + ".Name");
			
			if (aktuelleanzahl > 0) {
				if (aktuelleanzahl > anzahl[0]) {
					for (int k = 4; k > 0; k--) {
						anzahl[k] = anzahl[k - 1];
						platz[k] = platz[k - 1];
					}
					anzahl[0] = aktuelleanzahl;
					platz[0] = user;
				} else if (aktuelleanzahl > anzahl[1]) {
					for (int k = 4; k > 1; k--) {
						anzahl[k] = anzahl[k - 1];
						platz[k] = platz[k - 1];
					}
					anzahl[1] = aktuelleanzahl;
					platz[1] = user;
				} else if (aktuelleanzahl > anzahl[2]) {
					for (int k = 4; k > 2; k--) {
						anzahl[k] = anzahl[k - 1];
						platz[k] = platz[k - 1];
					}
					anzahl[2] = aktuelleanzahl;
					platz[2] = user;
				} else if (aktuelleanzahl > anzahl[3]) {
					anzahl[4] = anzahl[3];
					platz[4] = platz[3];

					anzahl[3] = aktuelleanzahl;
					platz[3] = user;
				} else if (aktuelleanzahl > anzahl[4]) {
					anzahl[4] = aktuelleanzahl;
					platz[4] = user;
				}
			}
		}
		
		sender.sendMessage(ChatColor.GREEN + "Die " + ChatColor.UNDERLINE + ChatColor.AQUA + "Top 5" + ChatColor.RESET + ChatColor.GREEN + " der besten Angler:");
		sender.sendMessage("Platz 1: " + ChatColor.GOLD + platz[0] + ChatColor.WHITE + " (" + anzahl[0] + ") ");
		sender.sendMessage("Platz 2: " + ChatColor.GOLD + platz[1] + ChatColor.WHITE + " (" + anzahl[1] + ") ");
		sender.sendMessage("Platz 3: " + ChatColor.GOLD + platz[2] + ChatColor.WHITE + " (" + anzahl[2] + ") ");
		sender.sendMessage("Platz 4: " + ChatColor.GOLD + platz[3] + ChatColor.WHITE + " (" + anzahl[3] + ") ");
		sender.sendMessage("Platz 5: " + ChatColor.GOLD + platz[4] + ChatColor.WHITE + " (" + anzahl[4] + ") ");
		
		if(!(sender instanceof Player))
			return;

		if (sender.getName().equals(platz[0])) {sender.sendMessage("Herzlichen Glückwunsch zum " + ChatColor.GOLD + "ersten " + ChatColor.WHITE + "Platz " + sender.getName() + " !");
		} else if (sender.getName().equals(platz[1])) {
			sender.sendMessage("Herzlichen Glückwunsch du hast den " + ChatColor.GOLD + "zweiten " + ChatColor.WHITE + "Platz, " + sender.getName() + " !");
		} else if (sender.getName().equals(platz[2])) {
			sender.sendMessage("Herzlichen Glückwunsch zum " + ChatColor.GOLD + "dritten " + ChatColor.WHITE + "Platz "	+ sender.getName() + " !");
		}
		
	}
	
	public void displayAchieved(CommandSender sender) {
		
		if(!(sender instanceof Player))
			return;
		
		Player player = (Player) sender;
  	  	
  	  	ConfigurationSection spieler = plugin.players.getConfigurationSection("Spieler." + player.getUniqueId().toString() + ".Fische.");
  	  	
  	  	if(spieler == null) {
  	  		player.sendMessage(ChatColor.RED + "Du hast keine Angelerfolge vorzuweisen.");
  	  		return;
  	  	}
  	  	
		Set<String> spielerKeys = spieler.getKeys(false);
	  	String[] spielerList = spielerKeys.toArray(new String[0]);
	  	
	  	ConfigurationSection fishes = plugin.fishes.getConfigurationSection("Fische");
		Set<String> fishesKeys = fishes.getKeys(false);
	  	String[] fishesCount = fishesKeys.toArray(new String[0]);
		
	  	player.sendMessage(ChatColor.GREEN + "Gefundene Fische:");
	  	
		for(int i = 0; i < spielerList.length; i++) {
			player.sendMessage(ChatColor.GOLD + spielerList[i] + ": " + plugin.players.getInt("Spieler." + player.getUniqueId().toString() + ".Fische." + spielerList[i] + ".Anzahl") + " Stück");
		}
		
		player.sendMessage(ChatColor.GREEN + "Du hast damit " + ChatColor.AQUA + spielerList.length + ChatColor.GREEN + " von " + ChatColor.AQUA + fishesCount.length + ChatColor.GREEN + " Fischen gefunden.");
		
	}

}
