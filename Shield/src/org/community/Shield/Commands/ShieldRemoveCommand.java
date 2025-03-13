package org.community.Shield.Commands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.community.Shield.Shield;



public class ShieldRemoveCommand {

	private final Shield plugin;

	public ShieldRemoveCommand(Shield plugin)
	{
		this.plugin = plugin;
	}

	@SuppressWarnings("deprecation")
	public boolean getCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if(sender instanceof Player){
			Player player = (Player) sender;
			if(cmd.getName().equalsIgnoreCase("sremove")){
				Block block = player.getTargetBlock(null, 3);
				String xyz = block.getX()+"_"+block.getY()+"_"+block.getZ();
				String world = block.getWorld().getName();
				if(args.length == 0) {
					if(block.getType() == Material.BREWING_STAND){
						if(plugin.brewing.getString("Register."+world+"."+xyz+".Owner")==null){
							player.sendMessage(ChatColor.BLUE+"Dieser Braustand ist noch nicht geschützt!");
							return true;
						}	
						else if(plugin.brewing.getString("Register."+world+"."+xyz+".Owner").equalsIgnoreCase(player.getUniqueId().toString())||plugin.isAdmin(player)){    
							plugin.brewing.set("Register."+world+"."+xyz, null);
							player.sendMessage(ChatColor.BLUE+"Shield Berechtigung für diesen Braustand erfolgreich entfernt!");
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
							String xyz2 = "";
							Block bpos1 = block.getLocation().add(0, -1, 0).getBlock();
							Block bpos2 = block.getLocation().add(0, +1, 0).getBlock();
							if(bpos1.getType()==Material.IRON_DOOR_BLOCK)
								xyz2 = bpos1.getX()+"_"+bpos1.getY()+"_"+bpos1.getZ();
							if(bpos2.getType()==Material.IRON_DOOR_BLOCK)
								xyz2 = bpos2.getX()+"_"+bpos2.getY()+"_"+bpos2.getZ();
							if(xyz2!=""){
								plugin.irondoor.set("Register."+world+"."+xyz2, null);
								plugin.irondoor.set("Register."+world+"."+xyz2, null);
							}
							plugin.irondoor.set("Register."+world+"."+xyz, null);
							player.sendMessage(ChatColor.BLUE+"Shield Berechtigung für diese Eisentüre erfolgreich entfernt!");
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
								try{
								plugin.chest.set("Register."+world+"."+xyz2, null);}
								catch(NullPointerException NPE){								
								}
							}

							plugin.chest.set("Register."+world+"."+xyz, null);
							player.sendMessage(ChatColor.BLUE+"Shield Berechtigung für diese Kiste erfolgreich entfernt!");
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
							plugin.furnance.set("Register."+world+"."+xyz,null);
							player.sendMessage(ChatColor.BLUE+"Shield Berechtigung für diese Ofen erfolgreich entfernt!");
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
							String xyz2 = "";
							Block bpos1 = block.getLocation().add(0, -1, 0).getBlock();
							Block bpos2 = block.getLocation().add(0, +1, 0).getBlock();
							if(bpos1.getType()==Material.WOODEN_DOOR)
								xyz2 = bpos1.getX()+"_"+bpos1.getY()+"_"+bpos1.getZ();
							if(bpos2.getType()==Material.WOODEN_DOOR)
								xyz2 = bpos2.getX()+"_"+bpos2.getY()+"_"+bpos2.getZ();
							if(xyz2!=""){
								plugin.wooddoor.set("Register."+world+"."+xyz2, null);
								plugin.wooddoor.set("Register."+world+"."+xyz2, null);
							}
							plugin.wooddoor.set("Register."+world+"."+xyz, null);
							player.sendMessage(ChatColor.BLUE+"Shield Berechtigung für diese Holztüre erfolgreich entfernt!");
							return true;
						}
						else{
							player.sendMessage(ChatColor.BLUE+"Du bist nicht der Besitzer dieser Holzt�re!");
							return true;
						}
					}

					if(block.getType() == Material.FENCE_GATE){
						if(plugin.fencegate.getString("Register."+world+"."+xyz+".Owner")==null){
							player.sendMessage(ChatColor.BLUE+"Dieses Gatter ist noch nicht geschützt!");
							return true;
						}	
						else if(plugin.fencegate.getString("Register."+world+"."+xyz+".Owner").equalsIgnoreCase(player.getUniqueId().toString())||plugin.isAdmin(player)){    
							plugin.fencegate.set("Register."+world+"."+xyz, null);
							player.sendMessage(ChatColor.BLUE+"Shield Berechtigung für dieses Gatter erfolgreich entfernt!");
							return true;
						}
						else{
							player.sendMessage(ChatColor.BLUE+"Du bist nicht der Besitzer dieses Gatters!");
							return true;
						}
					}
					if(block.getType() == Material.TRAP_DOOR){
						if(plugin.trapdoor.getString("Register."+world+"."+xyz+".Owner")==null){
							player.sendMessage(ChatColor.BLUE+"Diese Fallt�re ist noch nicht geschützt!");
							return true;
						}	
						else if(plugin.trapdoor.getString("Register."+world+"."+xyz+".Owner").equalsIgnoreCase(player.getUniqueId().toString())||plugin.isAdmin(player)){    
							plugin.trapdoor.set("Register."+world+"."+xyz,null);
							player.sendMessage(ChatColor.BLUE+"Shield Berechtigung für diese Falltüre erfolgreich entfernt!");
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
							plugin.dispenser.set("Register."+world+"."+xyz,null);
							player.sendMessage(ChatColor.BLUE+"Shield Berechtigung für diesen Dispenser erfolgreich entfernt!");
							return true;
						}
						else{
							player.sendMessage(ChatColor.BLUE+"Du bist nicht der Besitzer dieses Dispensers!");
							return true;
						}
					}
					if(block.getType() == Material.WOOD_BUTTON || block.getType() == Material.STONE_BUTTON || block.getType() == Material.LEVER){
						if(plugin.buttons.getString("Register."+world+"."+xyz+".Owner")==null){
							player.sendMessage(ChatColor.BLUE+"Dieser Knopf oder Hebel ist noch nicht geschützt!");
							return true;
						}	
						else if(plugin.buttons.getString("Register."+world+"."+xyz+".Owner").equalsIgnoreCase(player.getUniqueId().toString())||plugin.isAdmin(player)){    	
							plugin.buttons.set("Register."+world+"."+xyz,null);
							player.sendMessage(ChatColor.BLUE+"Shield Berechtigung für diesen Knopf oder Hebel erfolgreich modifizert!");
							return true;
						}
						else{
							player.sendMessage(ChatColor.BLUE+"Du bist nicht der Besitzer dieses Knopf oder Hebels!");
							return true;
						}
					}
					if(block.getType() == Material.WOOD_PLATE || block.getType() == Material.STONE_PLATE){
						if(plugin.plates.getString("Register."+world+"."+xyz+".Owner")==null){
							player.sendMessage(ChatColor.BLUE+"Diese Druckplatte ist noch nicht geschützt!");
							return true;
						}	
						else if(plugin.plates.getString("Register."+world+"."+xyz+".Owner").equalsIgnoreCase(player.getUniqueId().toString())||plugin.isAdmin(player)){    
							plugin.plates.set("Register."+world+"."+xyz,null);
							player.sendMessage(ChatColor.BLUE+"Shield Berechtigung für diese Druckplatte erfolgreich modifizert!");
							return true;
						}
						else{
							player.sendMessage(ChatColor.BLUE+"Du bist nicht der Besitzer dieser Druckplatte!");
							return true;
						}
					}
				}
				if(args.length == 1) {
					if(block.getType() == Material.BREWING_STAND){
						if(plugin.brewing.getString("Register."+world+"."+xyz+".Owner")==null){
							player.sendMessage(ChatColor.BLUE+"Dieser Braustand ist noch nicht geschützt!");
							return true;
						}	
						else if(plugin.brewing.getString("Register."+world+"."+xyz+".Owner").equalsIgnoreCase(player.getUniqueId().toString())||plugin.isAdmin(player)){
							if(plugin.brewing.getString("Register."+world+"."+xyz+".Owner").equals(args[0].toLowerCase())){
								player.sendMessage("Du kannst nicht die Zugangsberechtigung des Besitzers entfernen!");
							}	
							String access = plugin.brewing.getString("Register."+world+"."+xyz+".Access", "");
							access = access.replace(","+args[0].toLowerCase(), "");
							plugin.brewing.set("Register."+world+"."+xyz+".Access", access);
							player.sendMessage(ChatColor.BLUE+"Shield Berechtigung für diesen Braustand erfolgreich modifizert!");
							return true;
						}
						else{
							player.sendMessage(ChatColor.BLUE+"Du bist nicht der Besitzer dieses Braustands!");
							return true;
						}

					}
					if(block.getType() == Material.IRON_DOOR_BLOCK){
						if(plugin.irondoor.getString("Register."+world+"."+xyz+".Owner")==null){
							player.sendMessage(ChatColor.BLUE+"Diese Eisent�re ist noch nicht geschützt!");
							return true;
						}	
						else if(plugin.irondoor.getString("Register."+world+"."+xyz+".Owner").equalsIgnoreCase(player.getUniqueId().toString())||plugin.isAdmin(player)){    
							if(plugin.irondoor.getString("Register."+world+"."+xyz+".Owner").equals(args[0].toLowerCase())){
								player.sendMessage("Du kannst nicht die Zugangsberechtigung des Besitzers entfernen!");
							}	
							String xyz2 = "";
							Block bpos1 = block.getLocation().add(0, -1, 0).getBlock();
							Block bpos2 = block.getLocation().add(0, +1, 0).getBlock();
							if(bpos1.getType()==Material.IRON_DOOR_BLOCK)
								xyz2 = bpos1.getX()+"_"+bpos1.getY()+"_"+bpos1.getZ();
							if(bpos2.getType()==Material.IRON_DOOR_BLOCK)
								xyz2 = bpos2.getX()+"_"+bpos2.getY()+"_"+bpos2.getZ();
							String access = plugin.irondoor.getString("Register."+world+"."+xyz+".Access");
							access = access.replace(","+args[0].toLowerCase(), "");
							plugin.irondoor.set("Register."+world+"."+xyz+".Access", access);
							if(xyz2!=""){
								plugin.irondoor.set("Register."+world+"."+xyz2+".Access", access);
								plugin.irondoor.set("Register."+world+"."+xyz2+".Access", access);
							}
							player.sendMessage(ChatColor.BLUE+"Shield Berechtigung für diese Eisent�re erfolgreich modifizert!");
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
							if(plugin.chest.getString("Register."+world+"."+xyz+".Owner").equals(args[0].toLowerCase())){
								player.sendMessage("Du kannst nicht die Zugangsberechtigung des Besitzers entfernen!");
							}	
							String access = plugin.chest.getString("Register."+world+"."+xyz+".Access", "");
							access = access.replace(","+args[0].toLowerCase(), "");

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
								plugin.chest.set("Register."+world+"."+xyz2+".Access", access);
							}

							plugin.chest.set("Register."+world+"."+xyz+".Access", access);
							player.sendMessage(ChatColor.BLUE+"Shield Berechtigung für diese Kiste erfolgreich modifizert!");
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
							if(plugin.furnance.getString("Register."+world+"."+xyz+".Owner").equals(args[0].toLowerCase())){
								player.sendMessage("Du kannst nicht die Zugangsberechtigung des Besitzers entfernen!");
							}	
							String access = plugin.furnance.getString("Register."+world+"."+xyz+".Access");
							access = access.replace(","+args[0].toLowerCase(), "");
							plugin.furnance.set("Register."+world+"."+xyz+".Access", access);
							player.sendMessage(ChatColor.BLUE+"Shield Berechtigung für diesen Ofen erfolgreich modifizert!");
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
							if(plugin.wooddoor.getString("Register."+world+"."+xyz+".Owner").equals(args[0].toLowerCase())){
								player.sendMessage("Du kannst nicht die Zugangsberechtigung des Besitzers entfernen!");
							}
							String xyz2 = "";
							Block bpos1 = block.getLocation().add(0, -1, 0).getBlock();
							Block bpos2 = block.getLocation().add(0, +1, 0).getBlock();
							if(bpos1.getType()==Material.WOODEN_DOOR)
								xyz2 = bpos1.getX()+"_"+bpos1.getY()+"_"+bpos1.getZ();
							if(bpos2.getType()==Material.WOODEN_DOOR)
								xyz2 = bpos2.getX()+"_"+bpos2.getY()+"_"+bpos2.getZ();

							String access = plugin.wooddoor.getString("Register."+world+"."+xyz+".Access", "");
							access = access.replace(","+args[0].toLowerCase(), "");
							plugin.wooddoor.set("Register."+world+"."+xyz+".Access", access);
							if(xyz2!=""){
								plugin.wooddoor.set("Register."+world+"."+xyz2+".Access", access);
								plugin.wooddoor.set("Register."+world+"."+xyz2+".Access", access);
							}
							player.sendMessage(ChatColor.BLUE+"Shield Berechtigung für diese Holztüre erfolgreich modifizert!");
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
							if(plugin.fencegate.getString("Register."+world+"."+xyz+".Owner").equals(args[0].toLowerCase())){
								player.sendMessage("Du kannst nicht die Zugangsberechtigung des Besitzers entfernen!");
							}	
							String access = plugin.fencegate.getString("Register."+world+"."+xyz+".Access", "");
							access = access.replace(","+args[0].toLowerCase(), "");
							plugin.fencegate.set("Register."+world+"."+xyz+".Access", access);
							player.sendMessage(ChatColor.BLUE+"Shield Berechtigung für dieses Gatter erfolgreich modifizert!");
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
							if(plugin.trapdoor.getString("Register."+world+"."+xyz+".Owner").equals(args[0].toLowerCase())){
								player.sendMessage("Du kannst nicht die Zugangsberechtigung des Besitzers entfernen!");
							}	
							String access = plugin.trapdoor.getString("Register."+world+"."+xyz+".Access", "");
							access = access.replace(","+args[0].toLowerCase(), "");
							plugin.trapdoor.set("Register."+world+"."+xyz+".Access", access);
							player.sendMessage(ChatColor.BLUE+"Shield Berechtigung für diese Falltüre erfolgreich modifizert!");
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
							if(plugin.dispenser.getString("Register."+world+"."+xyz+".Owner").equals(args[0].toLowerCase())){
								player.sendMessage("Du kannst nicht die Zugangsberechtigung des Besitzers entfernen!");
							}	
							String access = plugin.dispenser.getString("Register."+world+"."+xyz+".Access", "");
							access = access.replace(","+args[0].toLowerCase(), "");
							plugin.dispenser.set("Register."+world+"."+xyz+".Access", access);
							player.sendMessage(ChatColor.BLUE+"Shield Berechtigung für diesen Dispenser erfolgreich modifizert!");
							return true;
						}
						else{
							player.sendMessage(ChatColor.BLUE+"Du bist nicht der Besitzer dieses Dispensers!");
							return true;
						}
					}
					if(block.getType() == Material.WOOD_BUTTON || block.getType() == Material.STONE_BUTTON || block.getType() == Material.LEVER){
						if(plugin.buttons.getString("Register."+world+"."+xyz+".Owner")==null){
							player.sendMessage(ChatColor.BLUE+"Dieser Knopf oder Hebel ist noch nicht geschützt!");
							return true;
						}	
						else if(plugin.buttons.getString("Register."+world+"."+xyz+".Owner").equalsIgnoreCase(player.getUniqueId().toString())||plugin.isAdmin(player)){    
							if(plugin.buttons.getString("Register."+world+"."+xyz+".Owner").equals(args[0].toLowerCase())){
								player.sendMessage("Du kannst nicht die Zugangsberechtigung des Besitzers entfernen!");
							}	
							String access = plugin.buttons.getString("Register."+world+"."+xyz+".Access", "");
							access = access.replace(","+args[0].toLowerCase(), "");
							plugin.buttons.set("Register."+world+"."+xyz+".Access", access);
							player.sendMessage(ChatColor.BLUE+"Shield Berechtigung für diesen Knopf oder Hebel erfolgreich modifizert!");
							return true;
						}
						else{
							player.sendMessage(ChatColor.BLUE+"Du bist nicht der Besitzer dieses Knopf oder Hebels!");
							return true;
						}
					}
					if(block.getType() == Material.WOOD_PLATE || block.getType() == Material.STONE_PLATE){
						if(plugin.plates.getString("Register."+world+"."+xyz+".Owner")==null){
							player.sendMessage(ChatColor.BLUE+"Diese Druckplatte ist noch nicht geschützt!");
							return true;
						}	
						else if(plugin.plates.getString("Register."+world+"."+xyz+".Owner").equalsIgnoreCase(player.getUniqueId().toString())||plugin.isAdmin(player)){    
							if(plugin.plates.getString("Register."+world+"."+xyz+".Owner").equals(args[0].toLowerCase())){
								player.sendMessage("Du kannst nicht die Zugangsberechtigung des Besitzers entfernen!");
							}	
							String access = plugin.plates.getString("Register."+world+"."+xyz+".Access", "");
							access = access.replace(","+args[0].toLowerCase(), "");
							plugin.plates.set("Register."+world+"."+xyz+".Access", access);
							player.sendMessage(ChatColor.BLUE+"Shield Berechtigung für diese Druckplatte erfolgreich modifizert!");
							return true;
						}
						else{
							player.sendMessage(ChatColor.BLUE+"Du bist nicht der Besitzer dieser Druckplatte!");
							return true;
						}
					}




				}else{
					player.sendMessage(ChatColor.BLUE+"Block nicht von Shield unterstützt");
					return true;
				}

				return true;
			}
		}

		return false;
	}
	
	public void overrideProtectionRemove(Block block) {
		if(plugin.chest.getString("Register."+block.getWorld().getName()+"."+block.getX() + "_" + block.getY() + "_" + block.getZ()) == null)
			return;
		
		plugin.chest.set("Register."+block.getWorld().getName()+"."+block.getX() + "_" + block.getY() + "_" + block.getZ(), null);
		return;
	}
	
}