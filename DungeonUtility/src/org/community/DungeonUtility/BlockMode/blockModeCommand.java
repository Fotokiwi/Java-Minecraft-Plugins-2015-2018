package org.community.DungeonUtility.BlockMode;

import java.util.Set;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.community.DungeonUtility.*;

public class blockModeCommand {
		
	private final DungeonUtility plugin;
	 	 
	public blockModeCommand(DungeonUtility plugin)
	{
		this.plugin = plugin;
	}
	
	public boolean getCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		
		if(sender instanceof Player) {
			Player player = (Player) sender;
			
			if(!isAdmin(player))
				return true;

			if(cmd.getName().equalsIgnoreCase("blockmode")){
				if(args.length == 0) {
					showPluginInfo(player);
					return true;
				}
				if(args.length == 1) {
					if(args[0].equalsIgnoreCase("set")){
						if(plugin.blockmodeSet.get(player) != null) {
							player.sendMessage("Du hast aktuell das Set '" + plugin.blockmodeSet.get(player) + "' ausgewählt.");
						} else {
							player.sendMessage("Du hast kein aktives Set ausgewählt.");
						}
						return true;
					}				
				}
				if(args.length == 2) {
					if(args[0].equalsIgnoreCase("set")){

						if(args[1].equalsIgnoreCase("null")) {
							plugin.blockmodeSet.put(player, null);
							player.sendMessage("Du hast erfolgreich den BlockModus verlassen.");
							return true;
						}

						if(plugin.set.get("Sets." + args[1]) == null) {
							plugin.blockmodeSet.put(player, args[1]);
							player.sendMessage("Du hast erfolgreich das Set '" + plugin.blockmodeSet.get(player) + "' angelegt.");
							plugin.set.set("Sets." + args[1], "Blubb");
							plugin.saveSet();
							return true;
						} else {
							plugin.blockmodeSet.put(player, args[1]);
							player.sendMessage("Du hast erfolgreich das Set '" + plugin.blockmodeSet.get(player) + "' geladen.");
							return true;
						}
					}

				}
				if(args.length == 3) {
					if(args[0].equalsIgnoreCase("change")){

						if(plugin.set.get("Sets." + args[1]) == null) {
							player.sendMessage("Dieses Set ist nicht vorhanden.");
							return true;
						}

						toggleMagicBlocks(args[1], args[2]);
						return true;
					}

				}
			}
		} else {
			if(cmd.getName().equalsIgnoreCase("blockmode")){			
				if(args.length == 3) {
					if(args[0].equalsIgnoreCase("change")){

						if(plugin.set.get("Sets." + args[1]) == null) {							
							return true;
						}

						toggleMagicBlocks(args[1], args[2]);
						return true;
					}

				}
			}
		}		
		
		return false;
	}
	
	private void showPluginInfo(CommandSender sender){
		sender.sendMessage(ChatColor.GREEN + plugin.logprefix);
	}

	@SuppressWarnings("deprecation")
	private boolean toggleMagicBlocks(String set, String mode) {
		
		String blockMode = "A-Set";
		if(mode.equalsIgnoreCase("A"))
			blockMode = "A-Set";
		if(mode.equalsIgnoreCase("B"))
			blockMode = "B-Set";
		
		ConfigurationSection magicBlocksSection = plugin.set.getConfigurationSection("Sets." + set + "." + blockMode);
		if(magicBlocksSection == null)
			return false;
		Set<String> magicBlocksKeys = magicBlocksSection.getKeys(false);
		String[] magicBlocksArray = magicBlocksKeys.toArray(new String[0]);

		//getServer().broadcastMessage("Set: " + set + " Mode: " + mode);

		for(int i = 0; i < magicBlocksKeys.size(); i++) {
			String[] blockLoc = magicBlocksArray[i].split(",");
			Location targetLocation = new Location(plugin.getServer().getWorld(blockLoc[0]), (double) Integer.parseInt(blockLoc[1]), (double) Integer.parseInt(blockLoc[2]), (double) Integer.parseInt(blockLoc[3]));
			Block targetBlock = plugin.getServer().getWorld(blockLoc[0]).getBlockAt(targetLocation);
			String[] blockInfo = plugin.set.getString("Sets." + set + "." + blockMode + "." + magicBlocksArray[i]).split(",");
			if(targetBlock.getType() == Material.CHEST) {
				plugin.shieldAPI.removecommand.overrideProtectionRemove(targetBlock);
				targetBlock.setType(Material.getMaterial(blockInfo[0]));
				targetBlock.setData((byte) Integer.parseInt(blockInfo[1]));
			} else {
				targetBlock.setType(Material.getMaterial(blockInfo[0]));
				targetBlock.setData((byte) Integer.parseInt(blockInfo[1]));
			}
			
		}

		return true;
	}
	
	public boolean isAdmin(Player player) {
		if(plugin.config.getList("Config.Admins") != null) {
			if(plugin.config.getList("Config.Admins").contains(player.getUniqueId().toString()))
				return true;
		}
		
		return false;
	}
	
}