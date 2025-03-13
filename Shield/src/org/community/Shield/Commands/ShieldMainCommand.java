package org.community.Shield.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.community.Shield.Shield;


public class ShieldMainCommand {

	private final Shield plugin;

	public ShieldMainCommand(Shield plugin)
	{
		this.plugin = plugin;
	}

	public boolean getCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
      if(sender instanceof Player){
    	  Player player = (Player) sender;
      
		if(cmd.getName().equalsIgnoreCase("shield")){
			if(args.length == 0) {
				showPluginInfo(sender);
				return true;
			}
			if(args.length==1){
				if(args[0].equalsIgnoreCase("info")){
					player.sendMessage(ChatColor.BLUE+"#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*");
					player.sendMessage(ChatColor.BLUE+"screate:  "+ChatColor.WHITE+"Erstellt einen Schutz für den angeschauten Block");
					player.sendMessage(ChatColor.BLUE+"#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*");
					player.sendMessage(ChatColor.BLUE+"sinfo:   "+ChatColor.WHITE+"Zeigt die Zugangsberechtigungen für den angeschauten Block an");
					player.sendMessage(ChatColor.BLUE+"#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*");
					player.sendMessage(ChatColor.BLUE+"smodify NAME:   "+ChatColor.WHITE+"Fügt den angegebenen Spieler der Berechtigungsliste hinzu. Für Städte t:NAME für Nationen n:NAME");
					player.sendMessage(ChatColor.BLUE+"#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*");
					player.sendMessage(ChatColor.BLUE+"sremove:   "+ChatColor.WHITE+"Entfernt einen Schutz");
					player.sendMessage(ChatColor.BLUE+"#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*");
					player.sendMessage(ChatColor.BLUE+"sremove NAME:   "+ChatColor.WHITE+"Entfernt die angegebene Zugangsberechtigung");
					player.sendMessage(ChatColor.BLUE+"#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*");
					player.sendMessage(ChatColor.BLUE+"~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
					player.sendMessage(ChatColor.BLUE+"PERSIST MODUS: WIRD MIT LINKSKLICK AUF EINE KISTE AUSGEFÜHRT, SOLANGE BIS MITTELS /spersist ABGEBROCHEN WIRD!");
					player.sendMessage(ChatColor.BLUE+"~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
					player.sendMessage(ChatColor.BLUE+"#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*");
					player.sendMessage(ChatColor.BLUE+"spersis:   "+ChatColor.WHITE+"Beendet den Persist Modus");
					player.sendMessage(ChatColor.BLUE+"spersist create:   "+ChatColor.WHITE+"Erstellt einen Schutz (Persist Modus)");
					player.sendMessage(ChatColor.BLUE+"spersis remove:   "+ChatColor.WHITE+"Entfernt einen Schutz zu (Persist Modus)");
					player.sendMessage(ChatColor.BLUE+"spersis add NAME:   "+ChatColor.WHITE+"Fügt den angegebenen Namen zur Zugangsliste hinzu (Persist Modus)");
					player.sendMessage(ChatColor.BLUE+"spersis rm NAME:   "+ChatColor.WHITE+"Entfernt den angegebenen Namen von der Zugangsliste (Persist Modus)");
				}
			}
		}
      }
		return false;
	}

	private void showPluginInfo(CommandSender sender){
		sender.sendMessage(ChatColor.BLUE + plugin.logprefix);
	}


}