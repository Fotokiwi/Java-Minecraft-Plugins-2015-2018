package org.community.Shield.Commands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.community.Shield.Shield;


public class ShieldModifyCommand {

	private final Shield plugin;

	public ShieldModifyCommand(Shield plugin)
	{
		this.plugin = plugin;
	}

	@SuppressWarnings("deprecation")
	public boolean getCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if(sender instanceof Player){
			Player player = (Player) sender;
			String accessor = "";
			if(cmd.getName().equalsIgnoreCase("smodify")){
				if(args.length == 1) {
					if(args[0].contains("t:")) {
						String townName = args[0].replace("t:", "");
						if(plugin.newSettlersAPI.nSCore.getTown(townName) == null) {
							sender.sendMessage(ChatColor.RED + "Diese Stadt existiert in den Registern nicht.");
							return true;
						}
						accessor = plugin.newSettlersAPI.nSCore.getTown(townName).getName();

					} else {
						if(plugin.getPlayerUUID(args[0]) == null) {
							sender.sendMessage(ChatColor.RED + "Bitte gib einen gültigen Spielernamen an. (Groß-/Kleinschreibung)");
							return true;
						}
						accessor = plugin.getPlayerUUID(args[0]).toString();
					}
					
					Block block = player.getTargetBlock(null, 6);
					String xyz = block.getX()+"_"+block.getY()+"_"+block.getZ();
					String world = block.getWorld().getName();
					if(block.getType() == Material.BREWING_STAND){
						if(plugin.brewing.getString("Register."+world+"."+xyz+".Owner")==null){
							player.sendMessage(ChatColor.BLUE+"Dieser Braustand ist noch nicht geschützt!");
							return true;
						}	
						else if(plugin.brewing.getString("Register."+world+"."+xyz+".Owner").equalsIgnoreCase(player.getUniqueId().toString())||plugin.isAdmin(player)){    
							String access = plugin.brewing.getString("Register."+world+"."+xyz+".Access", "");
							if(access.contains(accessor)){
								player.sendMessage(ChatColor.BLUE+"Diese Berechtigung existiert bereits!");
								return true;
							}
							access = access+"," + accessor;
							plugin.brewing.set("Register."+world+"."+xyz+".Access", access);
							player.sendMessage(ChatColor.BLUE+"Shield Berechtigung für diesen Braustand erfolgreich modifiziert!");
							return true;
						}
						else{
							player.sendMessage(ChatColor.BLUE+"Du bist nicht der Besitzer dieses Braustands!");
							return true;
						}

					}
					if(block.getType() == Material.IRON_DOOR_BLOCK){
						if(plugin.irondoor.getString("Register."+world+"."+xyz+".Owner")==null){
							player.sendMessage(ChatColor.BLUE+"Diese Eisentüre ist noch nicht geschützt!");
							return true;
						}	
						else if(plugin.irondoor.getString("Register."+world+"."+xyz+".Owner").equalsIgnoreCase(player.getUniqueId().toString())||plugin.isAdmin(player)){    
							String access = plugin.irondoor.getString("Register."+world+"."+xyz+".Access", "");							
							if(access.contains(accessor)){
								player.sendMessage(ChatColor.BLUE+"Diese Berechtigung existiert bereits!");
								return true;
							}
							access = access+"," + accessor;
							String xyz2 = "";
							Block bpos1 = block.getLocation().add(0, -1, 0).getBlock();
							Block bpos2 = block.getLocation().add(0, +1, 0).getBlock();
							if(bpos1.getType()==Material.IRON_DOOR_BLOCK)
								xyz2 = bpos1.getX()+"_"+bpos1.getY()+"_"+bpos1.getZ();
							if(bpos2.getType()==Material.IRON_DOOR_BLOCK)
								xyz2 = bpos2.getX()+"_"+bpos2.getY()+"_"+bpos2.getZ();
							if(xyz2!=""){
								String access2 = plugin.irondoor.getString("Register."+world+"."+xyz+".Access", "");
								access2 = access2+"," + accessor;
								plugin.irondoor.set("Register."+world+"."+xyz2+".Access", access2);
							}
							plugin.irondoor.set("Register."+world+"."+xyz+".Access", access);
							player.sendMessage(ChatColor.BLUE+"Shield Berechtigung für diese Eisentüre erfolgreich modifiziert!");
							return true;
						}
						else{
							player.sendMessage(ChatColor.BLUE+"Du bist nicht der Besitzer dieser Eisentüre!");
							return true;
						}

					}
					if(block.getType() == Material.CHEST){
						if(plugin.chest.getString("Register."+world+"."+xyz+".Owner")==null){
							player.sendMessage(ChatColor.BLUE+"Diese Kiste ist noch nicht geschützt!");
							return true;
						}	
						else if(plugin.chest.getString("Register."+world+"."+xyz+".Owner").equalsIgnoreCase(player.getUniqueId().toString())||plugin.isAdmin(player)){    
							String access = plugin.chest.getString("Register."+world+"."+xyz+".Access", "");							
							if(access.contains(accessor)){
								player.sendMessage(ChatColor.BLUE+"Diese Berechtigung existiert bereits!");
								return true;
							}
							access = access + "," + accessor;
							String xyz2 = "";
							Block bpos1 = block.getLocation().add(-1, 0, 0).getBlock();
							Block bpos2 = block.getLocation().add(+1, 0, 0).getBlock();
							Block bpos3 = block.getLocation().add(0, 0, -1).getBlock();
							Block bpos4 = block.getLocation().add(0, 0, +1).getBlock();
							if(bpos1.getType()==Material.CHEST)
								xyz2 = bpos1.getX()+"_"+bpos1.getY()+"_"+bpos1.getZ();
							if(bpos2.getType()==Material.CHEST)
								xyz2 = bpos2.getX()+"_"+bpos2.getY()+"_"+bpos2.getZ();
							if(bpos3.getType()==Material.CHEST)
								xyz2 = bpos3.getX()+"_"+bpos3.getY()+"_"+bpos3.getZ();
							if(bpos4.getType()==Material.CHEST)
								xyz2 = bpos4.getX()+"_"+bpos4.getY()+"_"+bpos4.getZ();
							if(xyz2!=""){
								String access2 = plugin.chest.getString("Register."+world+"."+xyz2+".Access", "");
								access2 = access2+"," + accessor;
								plugin.chest.set("Register."+world+"."+xyz2+".Access", access2);
							}
							plugin.chest.set("Register."+world+"."+xyz+".Access", access);
							player.sendMessage(ChatColor.BLUE+"Shield Berechtigung für diese Kiste erfolgreich modifiziert!");
							return true;
						}
						else{
							player.sendMessage(ChatColor.BLUE+"Du bist nicht der Besitzer dieser Kiste!");
							return true;
						}
					}
					if(block.getType() == Material.FURNACE){
						if(plugin.furnance.getString("Register."+world+"."+xyz+".Owner")==null){
							player.sendMessage(ChatColor.BLUE+"Dieser Ofen ist noch nicht geschützt!");
							return true;
						}	
						else if(plugin.furnance.getString("Register."+world+"."+xyz+".Owner").equalsIgnoreCase(player.getUniqueId().toString())||plugin.isAdmin(player)){    
							String access = plugin.furnance.getString("Register."+world+"."+xyz+".Access", "");
							if(access.contains(accessor)){
								player.sendMessage(ChatColor.BLUE+"Diese Berechtigung existiert bereits!");
								return true;
							}
							access = access+"," + accessor;
							plugin.furnance.set("Register."+world+"."+xyz+".Access", access);
							player.sendMessage(ChatColor.BLUE+"Shield Berechtigung für diese Ofen erfolgreich modifiziert!");
							return true;
						}
						else{
							player.sendMessage(ChatColor.BLUE+"Du bist nicht der Besitzer dieses Ofens!");
							return true;
						}

					}
					if(block.getType() == Material.WOODEN_DOOR){
						if(plugin.wooddoor.getString("Register."+world+"."+xyz+".Owner")==null){
							player.sendMessage(ChatColor.BLUE+"Diese Holztüre ist noch nicht geschützt!");
							return true;
						}	
						else if(plugin.wooddoor.getString("Register."+world+"."+xyz+".Owner").equalsIgnoreCase(player.getUniqueId().toString())||plugin.isAdmin(player)){    
							String access = plugin.wooddoor.getString("Register."+world+"."+xyz+".Access", "");						
							if(access.contains(accessor)){
								player.sendMessage(ChatColor.BLUE+"Diese Berechtigung existiert bereits!");
								return true;
							}
							access = access+"," + accessor;
							String xyz2 = "";
							Block bpos1 = block.getLocation().add(0, -1, 0).getBlock();
							Block bpos2 = block.getLocation().add(0, +1, 0).getBlock();
							if(bpos1.getType()==Material.WOODEN_DOOR)
								xyz2 = bpos1.getX()+"_"+bpos1.getY()+"_"+bpos1.getZ();
							if(bpos2.getType()==Material.WOODEN_DOOR)
								xyz2 = bpos2.getX()+"_"+bpos2.getY()+"_"+bpos2.getZ();
							if(xyz2!=""){
								String access2 = plugin.wooddoor.getString("Register."+world+"."+xyz+".Access", "");
								access2 = access2+"," + accessor;
								plugin.wooddoor.set("Register."+world+"."+xyz2+".Access", access2);
							}
							plugin.wooddoor.set("Register."+world+"."+xyz+".Access", access);
							player.sendMessage(ChatColor.BLUE+"Shield Berechtigung für diese Holztüre erfolgreich modifiziert!");
							return true;
						}
						else{
							player.sendMessage(ChatColor.BLUE+"Du bist nicht der Besitzer dieser Holztüre!");
							return true;
						}
					}

					if(block.getType() == Material.FENCE_GATE){
						if(plugin.fencegate.getString("Register."+world+"."+xyz+".Owner")==null){
							player.sendMessage(ChatColor.BLUE+"Dieses Gatter ist noch nicht geschützt!");
							return true;
						}	
						else if(plugin.fencegate.getString("Register."+world+"."+xyz+".Owner").equalsIgnoreCase(player.getUniqueId().toString())||plugin.isAdmin(player)){    
							String access = plugin.fencegate.getString("Register."+world+"."+xyz+".Access", "");
							if(access.contains(accessor)){
								player.sendMessage(ChatColor.BLUE+"Diese Berechtigung existiert bereits!");
								return true;
							}
							access = access+"," + accessor;
							plugin.fencegate.set("Register."+world+"."+xyz+".Access", access);
							player.sendMessage(ChatColor.BLUE+"Shield Berechtigung für dieses Gatter erfolgreich modifiziert!");
							return true;
						}
						else{
							player.sendMessage(ChatColor.BLUE+"Du bist nicht der Besitzer dieses Gatters!");
							return true;
						}
					}
					if(block.getType() == Material.TRAP_DOOR){
						if(plugin.trapdoor.getString("Register."+world+"."+xyz+".Owner")==null){
							player.sendMessage(ChatColor.BLUE+"Diese Falltüre ist noch nicht geschützt!");
							return true;
						}	
						else if(plugin.trapdoor.getString("Register."+world+"."+xyz+".Owner").equalsIgnoreCase(player.getUniqueId().toString())||plugin.isAdmin(player)){    
							String access = plugin.trapdoor.getString("Register."+world+"."+xyz+".Access", "");
							if(access.contains(accessor)){
								player.sendMessage(ChatColor.BLUE+"Diese Berechtigung existiert bereits!");
								return true;
							}
							access = access+"," + accessor;
							plugin.trapdoor.set("Register."+world+"."+xyz+".Access", access);
							player.sendMessage(ChatColor.BLUE+"Shield Berechtigung für diese Falltüre erfolgreich modifiziert!");
							return true;
						}
						else{
							player.sendMessage(ChatColor.BLUE+"Du bist nicht der Besitzer dieser Falltüre!");
							return true;
						}
					}
					if(block.getType() == Material.DISPENSER){
						if(plugin.dispenser.getString("Register."+world+"."+xyz+".Owner")==null){
							player.sendMessage(ChatColor.BLUE+"Dieser Dispenser ist noch nicht geschützt!");
							return true;
						}	
						else if(plugin.dispenser.getString("Register."+world+"."+xyz+".Owner").equalsIgnoreCase(player.getUniqueId().toString())||plugin.isAdmin(player)){    
							String access = plugin.dispenser.getString("Register."+world+"."+xyz+".Access", "");
							if(access.contains(accessor)){
								player.sendMessage(ChatColor.BLUE+"Diese Berechtigung existiert bereits!");
								return true;
							}
							access = access+"," + accessor;
							plugin.dispenser.set("Register."+world+"."+xyz+".Access", access);
							player.sendMessage(ChatColor.BLUE+"Shield Berechtigung für diesen Dispenser erfolgreich modifiziert!");
							return true;
						}
						else{
							player.sendMessage(ChatColor.BLUE+"Du bist nicht der Besitzer dieses Dispensers!");
							return true;
						}
					}
					if(block.getType() == Material.WOOD_BUTTON || block.getType() == Material.STONE_BUTTON || block.getType() == Material.LEVER){
						if(plugin.buttons.getString("Register."+world+"."+xyz+".Owner")==null){
							player.sendMessage(ChatColor.BLUE+"Dieser Knopf ist noch nicht geschützt!");
							return true;
						}	
						else if(plugin.buttons.getString("Register."+world+"."+xyz+".Owner").equalsIgnoreCase(player.getUniqueId().toString())||plugin.isAdmin(player)){    
							String access = plugin.buttons.getString("Register."+world+"."+xyz+".Access", "");
							if(access.contains(accessor)){
								player.sendMessage(ChatColor.BLUE+"Diese Berechtigung existiert bereits!");
								return true;
							}
							access = access+"," + accessor;
							plugin.buttons.set("Register."+world+"."+xyz+".Access", access);
							player.sendMessage(ChatColor.BLUE+"Shield Berechtigung für diesen Knopf oder Hebel erfolgreich modifiziert!");
							return true;
						}
						else{
							player.sendMessage(ChatColor.BLUE+"Du bist nicht der Besitzer dieses Knopfes oder Hebels!");
							return true;
						}
					}
					if(block.getType() == Material.WOOD_PLATE || block.getType() == Material.STONE_PLATE){
						if(plugin.plates.getString("Register."+world+"."+xyz+".Owner")==null){
							player.sendMessage(ChatColor.BLUE+"Dieser plates ist noch nicht geschützt!");
							return true;
						}	
						else if(plugin.plates.getString("Register."+world+"."+xyz+".Owner").equalsIgnoreCase(player.getUniqueId().toString())||plugin.isAdmin(player)){    
							String access = plugin.plates.getString("Register."+world+"."+xyz+".Access", "");
							if(access.contains(accessor)){
								player.sendMessage(ChatColor.BLUE+"Diese Berechtigung existiert bereits!");
								return true;
							}
							access = access+"," + accessor;
							plugin.plates.set("Register."+world+"."+xyz+".Access", access);
							player.sendMessage(ChatColor.BLUE+"Shield Berechtigung für diese Druckplatte erfolgreich modifiziert!");
							return true;
						}
						else{
							player.sendMessage(ChatColor.BLUE+"Du bist nicht der Besitzer dieser Druckplatte!");
							return true;
						}
					}
					else{
						player.sendMessage(ChatColor.BLUE+"Angeschauter Block wird nicht von Shield unterstützt!");
						player.sendMessage(ChatColor.BLUE+"Nur: DRUCKPLATTE,HEBEL,KNÖPFE,DISPENSER, KISTEN, ÖFEN, HOLZTÜREN, EISENTÜREN, GATTER, BRAUSTÄNDE, FALLTÜREN möglich!");
						return true;
					}

				}else{
					player.sendMessage("Ungültige Eingabe. Für Informationen: /shield info");
					return true;
				}
			}
		}
		return false;
	}
}