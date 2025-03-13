package org.community.Shield.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.community.Shield.Shield;



public class ShieldPersistCommand {

	private final Shield plugin;

	public ShieldPersistCommand(Shield plugin)
	{
		this.plugin = plugin;
	}

	public boolean getCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if(sender instanceof Player){
			Player player = (Player) sender;
			if(cmd.getName().equalsIgnoreCase("spersist")){
				if(args.length == 0){
					if(plugin.persist.containsKey(player)){
						plugin.persist.remove(player);
						player.sendMessage(ChatColor.BLUE+"Persist Modus beendet!");
						return true;
					}
					else{
						player.sendMessage(ChatColor.BLUE+"Du warst nicht im Persist Modus!");
						return true;
					}
				}
				if(args.length == 1){
					if(args[0].equalsIgnoreCase("create")){
						plugin.persist.put(player, "create");
						player.sendMessage(ChatColor.BLUE+"Create Persist Modus aktiviert!");
						return true;

					}
					if(args[0].equalsIgnoreCase("remove")){
						plugin.persist.put(player, "remove");
						player.sendMessage(ChatColor.BLUE+"Remove Persist Modus aktiviert!");
						return true;


					}
					else{
						player.sendMessage(ChatColor.BLUE+"Ungültiger Befehl. Siehe /shield info");
						return true;
					}

				}
				if(args.length == 2){
					if(args[0].equalsIgnoreCase("add")){
						plugin.persist.put(player, "a_"+args[1]);
						player.sendMessage(ChatColor.BLUE+"ADD Persist Modus aktiviert!");
						return true;
					}
					if(args[0].equalsIgnoreCase("rm")){
						plugin.persist.put(player, "r_"+args[1]);
						player.sendMessage(ChatColor.BLUE+"RM Persist Modus aktiviert!");
						return true;			
					}
					else{
						player.sendMessage(ChatColor.BLUE+"Ungültiger Befehl. Siehe /shield info");
						return true;
					}


				}
				else{
					player.sendMessage(ChatColor.BLUE+"Ungültiger Befehl. Siehe /shield info");
					return true;
				}


			}
			return false;
		}
		return false;
	}
}