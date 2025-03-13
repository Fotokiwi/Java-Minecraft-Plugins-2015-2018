package org.community.Shield.Commands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.community.Shield.Shield;



public class ShieldCreateCommand {

	private final Shield plugin;

	public ShieldCreateCommand(Shield plugin)
	{
		this.plugin = plugin;
	}

	@SuppressWarnings("deprecation")
	public boolean getCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if(sender instanceof Player){
			Player player = (Player) sender;
			if(cmd.getName().equalsIgnoreCase("screate")){
				if(args.length == 0) {
					Block block = player.getTargetBlock(null, 6);
					String xyz = block.getX()+"_"+block.getY()+"_"+block.getZ();
					String world = block.getWorld().getName();
					if(!plugin.Blocks.contains(block.getType().name().toString())){
						player.sendMessage(ChatColor.BLUE+"Angeschauter Block wird nicht von Shield unterstützt!");
						player.sendMessage(ChatColor.BLUE+"Nur: KNÖPFE, DRUCKPLATTEN, SCHALTER, DISPENSER, KISTEN, ÖFEN, HOLZTÜREN, EISENTÜREN, GATTER, BRAUSTÄNDE, FALLTÜREN möglich!");
						return true;
					}
					if(plugin.newSettlersAPI.nSAPI.getChunkTown(block.getChunk()) == null && !plugin.isAdmin(player)){
						player.sendMessage(ChatColor.BLUE+"Du kannst in der Wildniss keinen Shield Schutz setzen!");
						return true;
					}
					if(!(plugin.isProtectAllowed(player, plugin.newSettlersAPI.nSAPI.getChunkTown(block.getChunk()))) && !plugin.isAdmin(player)){
						player.sendMessage(ChatColor.BLUE+"Du kannst nur Blöcke in der Stadt schützen, der du angehörst !");
						return true;
					}
					if(block.getType() == Material.BREWING_STAND){
						if(plugin.brewing.getString("Register."+world+"."+xyz+".Owner")==null){
							plugin.brewing.set("Register."+world+"."+xyz+".Owner", player.getUniqueId().toString());
							plugin.brewing.set("Register."+world+"."+xyz+".Access", player.getUniqueId().toString());
							player.sendMessage(ChatColor.BLUE+"Braustand erfolgreich mit Shield geschützt!");
							return true;
						}
						else{
							player.sendMessage(ChatColor.BLUE+"Braustand wurde schon mit Shield geschützt durch: "+ChatColor.WHITE+ plugin.getPlayerNameByUUIDString(plugin.brewing.getString("Register."+world+"."+xyz+".Owner")));
							return true;
						}

					}
					if(block.getType() == Material.DISPENSER){
						if(plugin.dispenser.getString("Register."+world+"."+xyz+".Owner")==null){
							plugin.dispenser.set("Register."+world+"."+xyz+".Owner", player.getUniqueId().toString());
							plugin.dispenser.set("Register."+world+"."+xyz+".Access", player.getUniqueId().toString());
							player.sendMessage(ChatColor.BLUE+"Dispenser erfolgreich mit Shield geschützt!");
							return true;
						}
						else{
							player.sendMessage(ChatColor.BLUE+"Dispenser wurde schon mit Shield geschützt durch: "+ChatColor.WHITE+plugin.getPlayerNameByUUIDString(plugin.dispenser.getString("Register."+world+"."+xyz+".Owner")));
							return true;
						}

					}
					if(block.getType() == Material.CHEST){
						if(plugin.chest.getString("Register."+world+"."+xyz+".Owner")==null){
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
								plugin.chest.set("Register."+world+"."+xyz2+".Owner", player.getUniqueId().toString());
								plugin.chest.set("Register."+world+"."+xyz2+".Access", player.getUniqueId().toString());
							}
							plugin.chest.set("Register."+world+"."+xyz+".Owner", player.getUniqueId().toString());
							plugin.chest.set("Register."+world+"."+xyz+".Access", player.getUniqueId().toString());
							player.sendMessage(ChatColor.BLUE+"Kiste erfolgreich mit Shield geschützt!");
							return true;
						}
						else{
							player.sendMessage(ChatColor.BLUE+"Kiste wurde schon mit Shield geschützt durch: "+ChatColor.WHITE+plugin.getPlayerNameByUUIDString(plugin.chest.getString("Register."+world+"."+xyz+".Owner")));
							return true;
						}

					}
					if(block.getType() == Material.FURNACE){
						if(plugin.furnance.getString("Register."+world+"."+xyz+".Owner")==null){
							plugin.furnance.set("Register."+world+"."+xyz+".Owner", player.getUniqueId().toString());
							plugin.furnance.set("Register."+world+"."+xyz+".Access", player.getUniqueId().toString());
							player.sendMessage(ChatColor.BLUE+"Ofen erfolgreich mit Shield geschützt!");
							return true;
						}
						else{
							player.sendMessage(ChatColor.BLUE+"Ofen wurde schon mit Shield geschützt durch: "+ChatColor.WHITE+plugin.getPlayerNameByUUIDString(plugin.furnance.getString("Register."+world+"."+xyz+".Owner")));
							return true;
						}

					}
					if(block.getType() == Material.WOODEN_DOOR){
						if(plugin.wooddoor.getString("Register."+world+"."+xyz+".Owner")==null){
							String xyz2 = "";
							Block bpos1 = block.getLocation().add(0, -1, 0).getBlock();
							Block bpos2 = block.getLocation().add(0, +1, 0).getBlock();
							if(bpos1.getType()==Material.WOODEN_DOOR)
								xyz2 = bpos1.getX()+"_"+bpos1.getY()+"_"+bpos1.getZ();
							if(bpos2.getType()==Material.WOODEN_DOOR)
								xyz2 = bpos2.getX()+"_"+bpos2.getY()+"_"+bpos2.getZ();
							if(xyz2!=""){
								plugin.wooddoor.set("Register."+world+"."+xyz2+".Owner", player.getUniqueId().toString());
								plugin.wooddoor.set("Register."+world+"."+xyz2+".Access", player.getUniqueId().toString());
							}
							plugin.wooddoor.set("Register."+world+"."+xyz+".Owner", player.getUniqueId().toString());
							plugin.wooddoor.set("Register."+world+"."+xyz+".Access", player.getUniqueId().toString());
							player.sendMessage(ChatColor.BLUE+"Holztüre erfolgreich mit Shield geschützt!");
							return true;
						}
						else{
							player.sendMessage(ChatColor.BLUE+"Holztüre wurde schon mit Shield geschützt durch: "+ChatColor.WHITE+plugin.getPlayerNameByUUIDString(plugin.wooddoor.getString("Register."+world+"."+xyz+".Owner")));
							return true;
						}

					}
					if(block.getType() == Material.IRON_DOOR_BLOCK){
						if(plugin.irondoor.getString("Register."+world+"."+xyz+".Owner")==null){
							String xyz2 = "";
							Block bpos1 = block.getLocation().add(0, -1, 0).getBlock();
							Block bpos2 = block.getLocation().add(0, +1, 0).getBlock();
							if(bpos1.getType()==Material.IRON_DOOR_BLOCK)
								xyz2 = bpos1.getX()+"_"+bpos1.getY()+"_"+bpos1.getZ();
							if(bpos2.getType()==Material.IRON_DOOR_BLOCK)
								xyz2 = bpos2.getX()+"_"+bpos2.getY()+"_"+bpos2.getZ();
							if(xyz2!=""){
								plugin.wooddoor.set("Register."+world+"."+xyz2+".Owner", player.getUniqueId().toString());
								plugin.wooddoor.set("Register."+world+"."+xyz2+".Access", player.getUniqueId().toString());
							}
							plugin.irondoor.set("Register."+world+"."+xyz+".Owner", player.getUniqueId().toString());
							plugin.irondoor.set("Register."+world+"."+xyz+".Access", player.getUniqueId().toString());
							player.sendMessage(ChatColor.BLUE+"Eisentüre erfolgreich mit Shield geschützt!");
							return true;
						}
						else{
							player.sendMessage(ChatColor.BLUE+"Eisentüre wurde schon mit Shield geschützt durch: "+ChatColor.WHITE+plugin.getPlayerNameByUUIDString(plugin.irondoor.getString("Register."+world+"."+xyz+".Owner")));
							return true;
						}

					}
					if(block.getType() == Material.FENCE_GATE){
						if(plugin.fencegate.getString("Register."+world+"."+xyz+".Owner")==null){
							plugin.fencegate.set("Register."+world+"."+xyz+".Owner", player.getUniqueId().toString());
							plugin.fencegate.set("Register."+world+"."+xyz+".Access", player.getUniqueId().toString());
							player.sendMessage(ChatColor.BLUE+"Gatter erfolgreich mit Shield geschützt!");
							return true;
						}
						else{
							player.sendMessage(ChatColor.BLUE+"Gatter wurde schon mit Shield geschützt durch: "+ChatColor.WHITE+plugin.getPlayerNameByUUIDString(plugin.fencegate.getString("Register."+world+"."+xyz+".Owner")));
							return true;
						}

					}
					if(block.getType() == Material.TRAP_DOOR){
						if(plugin.trapdoor.getString("Register."+world+"."+xyz+".Owner")==null){
							plugin.trapdoor.set("Register."+world+"."+xyz+".Owner", player.getUniqueId().toString());
							plugin.trapdoor.set("Register."+world+"."+xyz+".Access", player.getUniqueId().toString());
							player.sendMessage(ChatColor.BLUE+"Falltüre erfolgreich mit Shield geschützt!");
							return true;
						}
						else{
							player.sendMessage(ChatColor.BLUE+"Falltüre wurde schon mit Shield geschützt durch: "+ChatColor.WHITE+plugin.getPlayerNameByUUIDString(plugin.trapdoor.getString("Register."+world+"."+xyz+".Owner")));
							return true;
						}

					}
					if(block.getType() == Material.LEVER || block.getType() == Material.WOOD_BUTTON || block.getType() == Material.STONE_BUTTON){
						if(plugin.buttons.getString("Register."+world+"."+xyz+".Owner")==null){
							plugin.buttons.set("Register."+world+"."+xyz+".Owner", player.getUniqueId().toString());
							plugin.buttons.set("Register."+world+"."+xyz+".Access", player.getUniqueId().toString());
							player.sendMessage(ChatColor.BLUE+"Knopf oder Hebel erfolgreich mit Shield geschützt!");
							return true;
						}
						else{
							player.sendMessage(ChatColor.BLUE+"Knopf oder Hebel wurde schon mit Shield geschützt durch: "+ChatColor.WHITE+plugin.getPlayerNameByUUIDString(plugin.trapdoor.getString("Register."+world+"."+xyz+".Owner")));
							return true;
						}

					}
					if(block.getType() == Material.WOOD_PLATE || block.getType() == Material.STONE_PLATE){
						if(plugin.plates.getString("Register."+world+"."+xyz+".Owner")==null){
							plugin.plates.set("Register."+world+"."+xyz+".Owner", player.getUniqueId().toString());
							plugin.plates.set("Register."+world+"."+xyz+".Access", player.getUniqueId().toString());
							player.sendMessage(ChatColor.BLUE+"Druckplatte erfolgreich mit Shield geschützt!");
							return true;
						}
						else{
							player.sendMessage(ChatColor.BLUE+"Druckplatte wurde schon mit Shield geschützt durch: "+ChatColor.WHITE+plugin.getPlayerNameByUUIDString(plugin.trapdoor.getString("Register."+world+"."+xyz+".Owner")));
							return true;
						}

					}

					return true;
				}
			}
		}
		return false;
	}
}