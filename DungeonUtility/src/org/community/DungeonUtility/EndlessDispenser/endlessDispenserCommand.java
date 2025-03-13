package org.community.DungeonUtility.EndlessDispenser;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.community.DungeonUtility.*;

public class endlessDispenserCommand {
		
	private final DungeonUtility plugin;
	 	 
	public endlessDispenserCommand(DungeonUtility plugin)
	{
		this.plugin = plugin;
	}
	
	@SuppressWarnings("deprecation")
	public boolean getCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		
		Player player = (Player) sender;
		
		if(!isAdmin(player))
			return true;
		
		if(cmd.getName().equalsIgnoreCase("edispenser")){
			if(args.length == 0) {
				showPluginInfo(player);
				return true;
			}
			if(args.length == 1) {
				if(args[0].equalsIgnoreCase("add")) {
					Block block = player.getTargetBlock(null, 3);
					if(block.getType()!= Material.DISPENSER){
						player.sendMessage("Angeschauter Block ist kein Dispenser!");
						return true;
					}
					String blockWorld = block.getWorld().getName().toString();
					int blockx = block.getX();
					int blocky = block.getY();
					int blockz = block.getZ();
					String blockxyz = (""+blockx+"_"+blocky+"_"+blockz);
					plugin.dispenser.set("Dispenser." + blockWorld + "." + blockxyz + ".X", blockx);
					plugin.dispenser.set("Dispenser." + blockWorld + "." + blockxyz + ".Y", blocky);
					plugin.dispenser.set("Dispenser." + blockWorld + "." + blockxyz + ".Z", blockz);
					plugin.saveDispenser();
					plugin.dispenserMode.put(block, true);
					player.sendMessage("Dispenser erfolgreich registiert");
					return true;
				}
				if(args[0].equalsIgnoreCase("remove")) {
					Block block = player.getTargetBlock(null, 3);
					if(block.getType()!= Material.DISPENSER){
						player.sendMessage("Angeschauter Block ist kein Dispenser!");
						return true;
					}
					if(plugin.dispenserMode.get(block) == null) {
						player.sendMessage("Dieser Dispenser ist bisher nicht registriert.");
						return true;
					}
					String blockWorld = block.getWorld().getName().toString();
					int blockx = block.getX();
					int blocky = block.getY();
					int blockz = block.getZ();
					String blockxyz = (""+blockx+"_"+blocky+"_"+blockz);
					plugin.dispenser.set("Dispenser." + blockWorld + "." + blockxyz + ".X", null);
					plugin.dispenser.set("Dispenser." + blockWorld + "." + blockxyz + ".Y", null);
					plugin.dispenser.set("Dispenser." + blockWorld + "." + blockxyz + ".Z", null);
					plugin.dispenser.set("Dispenser." + blockWorld + "." + blockxyz, null);
					plugin.saveDispenser();
					plugin.dispenserMode.remove(block);
					player.sendMessage("Dispenser erfolgreich freigegeben");
					return true;
				}
				if(args[0].equalsIgnoreCase("info")) {
					Block block = player.getTargetBlock(null, 3);
					if(block.getType()!= Material.DISPENSER){
						player.sendMessage("Angeschauter Block ist kein Dispenser!");
						return true;
					}
					if(plugin.dispenserMode.get(block) == null) {
						player.sendMessage("Dieser Dispenser ist bisher nicht registriert.");
						return true;
					}
					player.sendMessage("Dieser Dispenser ist als eDispenser registriert.");
					return true;
				}
			}
		}
		
		return false;
	}
	
	private void showPluginInfo(CommandSender sender){
		sender.sendMessage(ChatColor.GREEN + plugin.logprefix);
	}
	
	public boolean isAdmin(Player player) {
		if(plugin.config.getList("Config.Admins") != null) {
			if(plugin.config.getList("Config.Admins").contains(player.getUniqueId().toString()))
				return true;
		}
		
		return false;
	}
	
}