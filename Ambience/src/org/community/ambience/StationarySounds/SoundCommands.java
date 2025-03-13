package org.community.ambience.StationarySounds;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.community.ambience.Ambience;
import org.community.ambience.StationarySounds.Heartbeat.soundPlayer;


public class SoundCommands {
	
	Ambience plugin;

	public SoundCommands(Ambience plugin) {
		this.plugin = plugin;

	}


	public boolean getCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (sender instanceof Player) {
			final Player player = (Player) sender;
			if(!isAdmin(player)){
				player.sendMessage("Du hast keine Berechtigungen, um dieses Plugin zu benutzen.");
				return true;
			}
				
			if (cmd.getName().equalsIgnoreCase("sound")) {
				if(args.length == 0){
					noArgs(player);
					return true;
				}
				if(args[0].equalsIgnoreCase("info")){
					info(player);
					return true;
				}
				if(args[0].equalsIgnoreCase("playsoundglobal")){
					soundglobal(player, args);
					return true;
				}
				player.sendMessage("Ungültige Befehlseingabe für Sound-Plugin. Probiere /sound info");
			}
		} else {
			sender.sendMessage("Ungültige Befehlseingabe für Sound-Plugin. Probiere /sound info");
			return true;
		}
		return false;
	
	}
	
	public boolean isAdmin(Player player) {
		if(plugin.config.getList("Config.Admins") != null) {
			if(plugin.config.getList("Config.Admins").contains(player.getUniqueId().toString()))
				return true;
		}
		
		return false;
	}

	private void noArgs(Player player) {
		player.sendMessage("Dieses Plugin bietet Möglichkeiten zur Erstellung und Modifizierung von stationären Sounds.");
	}
	
	private void info(Player player) {
		player.sendMessage("Es gibt folgende Befehle: ");
		player.sendMessage("/sound playsoundglobal soundName [radius] | Spielt einen Sound bei allen Spielern ab.");
	}
	
	@SuppressWarnings("deprecation")
	private void soundglobal(Player player, String[] args) {
		if(args.length <= 1 || args.length > 3)
			player.sendMessage("/sound playsoundglobal soundName [radius] ist der korrekte Syntax für diesen Befehl.");
		int radius = 16;
		String sound = "";
		sound = args[1];
		if(args.length == 3)
			radius = Integer.parseInt(args[2]);
		for(Player p : Bukkit.getOnlinePlayers())
			plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new soundPlayer(plugin, p, radius, player.getLocation(), sound), 0);
	}
}
