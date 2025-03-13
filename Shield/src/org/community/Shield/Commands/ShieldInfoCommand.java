package org.community.Shield.Commands;

import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.community.Shield.Shield;


public class ShieldInfoCommand {

	private final Shield plugin;

	public ShieldInfoCommand(Shield plugin)
	{
		this.plugin = plugin;
	}

	@SuppressWarnings("deprecation")
	public boolean getCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if(sender instanceof Player){
			Player player = (Player) sender;
			if(cmd.getName().equalsIgnoreCase("sinfo")){
				if(args.length == 0) {
					Block block = player.getTargetBlock(null, 3);
					String xyz = block.getX()+"_"+block.getY()+"_"+block.getZ();
					String world = block.getWorld().getName();
					if(block.getType()==Material.DISPENSER){
						if(plugin.dispenser.getString("Register."+world+"."+xyz+".Owner")!=null){
							if(plugin.dispenser.getString("Register."+world+"."+xyz+".Owner").equals(player.getUniqueId().toString())||plugin.isAdmin(player)){
								String zugang = plugin.dispenser.getString("Register."+world+"."+xyz+".Access", "");
								player.sendMessage(ChatColor.BLUE+"Momentan haben Zugang: "+ parseAccesToNames(zugang));
								return true;
							}else{
								player.sendMessage(ChatColor.BLUE+"Nur der Besitzer "+(parseAccesToNames(plugin.dispenser.getString("Register."+world+"."+xyz+".Owner")))+ " kann Zugangsberechtigungen abfragen !");
								return true;
							}
						}else{
							player.sendMessage(ChatColor.BLUE+"Angeschauter Block ist nicht von Shield geschützt");
							return true;
						}
							
					}
					if(block.getType()==Material.CHEST){
						if(plugin.chest.getString("Register."+world+"."+xyz+".Owner")!=null){
							if(plugin.chest.getString("Register."+world+"."+xyz+".Owner").equals(player.getUniqueId().toString())||plugin.isAdmin(player)){
								String zugang = plugin.chest.getString("Register."+world+"."+xyz+".Access", "");
								player.sendMessage(ChatColor.BLUE+"Momentan haben Zugang: "+ parseAccesToNames(zugang));
								return true;
							}else{
								player.sendMessage(ChatColor.BLUE+"Nur der Besitzer "+(parseAccesToNames(plugin.chest.getString("Register."+world+"."+xyz+".Owner")))+" kann Zugangsberechtigungen abfragen !");
								return true;
							}
						}else{
							player.sendMessage(ChatColor.BLUE+"Angeschauter Block ist nicht von Shield geschützt");
							return true;
						}
							
					}
					if(block.getType()==Material.FURNACE){
						if(plugin.furnance.getString("Register."+world+"."+xyz+".Owner")!=null){
							if(plugin.furnance.getString("Register."+world+"."+xyz+".Owner").equals(player.getUniqueId().toString())||plugin.isAdmin(player)){
								String zugang = plugin.furnance.getString("Register."+world+"."+xyz+".Access", "");
								player.sendMessage(ChatColor.BLUE+"Momentan haben Zugang: "+ parseAccesToNames(zugang));
								return true;
							}else{
								player.sendMessage(ChatColor.BLUE+"Nur der Besitzer "+(parseAccesToNames(plugin.furnance.getString("Register."+world+"."+xyz+".Owner")))+" kann Zugangsberechtigungen abfragen !");
								return true;
							}
						}else{
							player.sendMessage(ChatColor.BLUE+"Angeschauter Block ist nicht von Shield geschützt");
							return true;
						}
							
					}
					if(block.getType()==Material.WOODEN_DOOR){
						if(plugin.wooddoor.getString("Register."+world+"."+xyz+".Owner")!=null){
							if(plugin.wooddoor.getString("Register."+world+"."+xyz+".Owner").equals(player.getUniqueId().toString())||plugin.isAdmin(player)){
								String zugang = plugin.wooddoor.getString("Register."+world+"."+xyz+".Access", "");
								player.sendMessage(ChatColor.BLUE+"Momentan haben Zugang: "+ parseAccesToNames(zugang));
								return true;
							}else{
								player.sendMessage(ChatColor.BLUE+"Nur der Besitzer "+(parseAccesToNames(plugin.wooddoor.getString("Register."+world+"."+xyz+".Owner")))+" kann Zugangsberechtigungen abfragen !");
								return true;
							}
						}else{
							player.sendMessage(ChatColor.BLUE+"Angeschauter Block ist nicht von Shield geschützt");
							return true;
						}
							
					}
					if(block.getType()==Material.IRON_DOOR_BLOCK){
						if(plugin.irondoor.getString("Register."+world+"."+xyz+".Owner")!=null){
							if(plugin.irondoor.getString("Register."+world+"."+xyz+".Owner").equals(player.getUniqueId().toString())||plugin.isAdmin(player)){
								String zugang = plugin.irondoor.getString("Register."+world+"."+xyz+".Access", "");
								player.sendMessage(ChatColor.BLUE+"Momentan haben Zugang: "+ parseAccesToNames(zugang));
								return true;
							}else{
								player.sendMessage(ChatColor.BLUE+"Nur der Besitzer "+(parseAccesToNames(plugin.irondoor.getString("Register."+world+"."+xyz+".Owner")))+" kann Zugangsberechtigungen abfragen !");
								return true;
							}
						}else{
							player.sendMessage(ChatColor.BLUE+"Angeschauter Block ist nicht von Shield geschützt");
							return true;
						}
							
					}
					if(block.getType()==Material.FENCE_GATE){
						if(plugin.fencegate.getString("Register."+world+"."+xyz+".Owner")!=null){
							if(plugin.fencegate.getString("Register."+world+"."+xyz+".Owner").equals(player.getUniqueId().toString())||plugin.isAdmin(player)){
								String zugang = plugin.fencegate.getString("Register."+world+"."+xyz+".Access", "");
								player.sendMessage(ChatColor.BLUE+"Momentan haben Zugang: "+ parseAccesToNames(zugang));
								return true;
							}else{
								player.sendMessage(ChatColor.BLUE+"Nur der Besitzer "+(parseAccesToNames(plugin.fencegate.getString("Register."+world+"."+xyz+".Owner")))+" kann Zugangsberechtigungen abfragen !");
								return true;
							}
						}else{
							player.sendMessage(ChatColor.BLUE+"Angeschauter Block ist nicht von Shield geschützt");
							return true;
						}
							
					}
					if(block.getType()==Material.BREWING_STAND){
						if(plugin.brewing.getString("Register."+world+"."+xyz+".Owner")!=null){
							if(plugin.brewing.getString("Register."+world+"."+xyz+".Owner").equals(player.getUniqueId().toString())||plugin.isAdmin(player)){
								String zugang = plugin.brewing.getString("Register."+world+"."+xyz+".Access", "");
								player.sendMessage(ChatColor.BLUE+"Momentan haben Zugang: "+ parseAccesToNames(zugang));
								return true;
							}else{
								player.sendMessage(ChatColor.BLUE+"Nur der Besitzer "+parseAccesToNames((plugin.brewing.getString("Register."+world+"."+xyz+".Owner")))+" kann Zugangsberechtigungen abfragen !");
								return true;
							}
						}else{
							player.sendMessage(ChatColor.BLUE+"Angeschauter Block ist nicht von Shield geschützt");
							return true;
						}
							
					}
					if(block.getType()==Material.TRAP_DOOR){
						if(plugin.trapdoor.getString("Register."+world+"."+xyz+".Owner")!=null){
							if(plugin.trapdoor.getString("Register."+world+"."+xyz+".Owner").equals(player.getUniqueId().toString())||plugin.isAdmin(player)){
								String zugang = plugin.trapdoor.getString("Register."+world+"."+xyz+".Access", "");
								player.sendMessage(ChatColor.BLUE+"Momentan haben Zugang: "+ parseAccesToNames(zugang));
								return true;
							}else{
								player.sendMessage(ChatColor.BLUE+"Nur der Besitzer "+(parseAccesToNames(plugin.trapdoor.getString("Register."+world+"."+xyz+".Owner")))+" kann Zugangsberechtigungen abfragen !");
								return true;
							}
						}else{
							player.sendMessage(ChatColor.BLUE+"Angeschauter Block ist nicht von Shield geschützt");
							return true;
						}
							
					}
					if(block.getType()==Material.WOOD_BUTTON || block.getType()==Material.STONE_BUTTON || block.getType()==Material.LEVER){
						if(plugin.buttons.getString("Register."+world+"."+xyz+".Owner")!=null){
							if(plugin.buttons.getString("Register."+world+"."+xyz+".Owner").equals(player.getUniqueId().toString())||plugin.isAdmin(player)){
								String zugang = plugin.buttons.getString("Register."+world+"."+xyz+".Access", "");
								player.sendMessage(ChatColor.BLUE+"Momentan haben Zugang: "+ parseAccesToNames(zugang));
								return true;
							}else{
								player.sendMessage(ChatColor.BLUE+"Nur der Besitzer "+(parseAccesToNames(plugin.buttons.getString("Register."+world+"."+xyz+".Owner")))+" kann Zugangsberechtigungen abfragen !");
								return true;
							}
						}else{
							player.sendMessage(ChatColor.BLUE+"Angeschauter Block ist nicht von Shield geschützt");
							return true;
						}
							
					}
					if(block.getType()==Material.WOOD_PLATE || block.getType()==Material.STONE_PLATE){
						if(plugin.plates.getString("Register."+world+"."+xyz+".Owner")!=null){
							if(plugin.plates.getString("Register."+world+"."+xyz+".Owner").equals(player.getUniqueId().toString())||plugin.isAdmin(player)){
								String zugang = plugin.plates.getString("Register."+world+"."+xyz+".Access", "");
								player.sendMessage(ChatColor.BLUE+"Momentan haben Zugang: "+ parseAccesToNames(zugang));
								return true;
							}else{
								player.sendMessage(ChatColor.BLUE+"Nur der Besitzer "+(parseAccesToNames(plugin.plates.getString("Register."+world+"."+xyz+".Owner")))+" kann Zugangsberechtigungen abfragen !");
								return true;
							}
						}else{
							player.sendMessage(ChatColor.BLUE+"Angeschauter Block ist nicht von Shield geschützt");
							return true;
						}
							
					}
					else{
						player.sendMessage(ChatColor.BLUE+"Angeschauter Block wird nicht von Shield unterstützt!");
						player.sendMessage(ChatColor.BLUE+"Nur: KNÖPFE, HEBEL, DRUCKPLATTEN, DISPENSER, KISTEN, ÖFEN, HOLZTÜREN, EISENT�REN, GATTER, BRAUSTÄNDE, FALLTÜREN möglich!");
					}

					return true;
				}
			}
		}
		return false;
	}
	
	public String parseAccesToNames(String uuidList) {
		String clearNames = "";
		String[] uuidNames = uuidList.split(",");
		
		for(int i = 0; i < uuidNames.length; i++) {
			if(plugin.newSettlersAPI.nSCore.getTown(uuidNames[i]) == null)
			clearNames += plugin.getServer().getOfflinePlayer(UUID.fromString(uuidNames[i])).getName() + ", ";
			else
				clearNames += uuidNames[i] + ", ";
		}
		
		return clearNames;
	}
}