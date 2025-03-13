package org.community.Shield.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.community.Shield.Shield;



public class ShieldAdminCommand {

	private final Shield plugin;

	public ShieldAdminCommand(Shield plugin)
	{
		this.plugin = plugin;
	}

	public boolean getCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if(sender instanceof Player){
			Player player = (Player) sender;
			if(plugin.isAdmin(player)){
				if(cmd.getName().equalsIgnoreCase("sadmin")){
					if(args.length == 1) {
						if(args[0].equalsIgnoreCase("save")){
							plugin.shieldconfig.saveConfig();
							plugin.shieldbrewing.savebrewing();
							plugin.shieldchest.saveChest();
							plugin.shielddispenser.savedispenser();
							plugin.shieldfencegate.savefencegate();
							plugin.shieldfurnance.savefurnance();
							plugin.shieldirondoor.saveirondoor();
							plugin.shieldtrapdoor.savetrapdoor();
							plugin.shieldwooddoor.savewooddoor();
							plugin.shieldbuttons.savebuttons();
							plugin.shieldplates.saveplates();
							player.sendMessage("Daten erfolgreich gespeichert");
							return true;
						}
                    
					}
				}
			}
			else{
				player.sendMessage("Du hast nicht die nötigen Berechtigungen für diesen Befehl");
				return false;
			}
		}
		return false;
	}
}