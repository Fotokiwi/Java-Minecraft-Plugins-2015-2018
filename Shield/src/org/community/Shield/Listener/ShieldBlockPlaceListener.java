package org.community.Shield.Listener;


import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.community.Shield.Shield;


public class ShieldBlockPlaceListener implements Listener{

	private final Shield plugin;

	public ShieldBlockPlaceListener(Shield plugin)
	{
		this.plugin = plugin;
	}

	/**
	 * Automatische Protection beim setzen.
	 */
	@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = false)
	public void onBlockPlace(BlockPlaceEvent event) {

		Block block = event.getBlock();
		//nachsehen ob der Block �berhaupt ein Shield Protectbarer Block ist.
		if(!plugin.Blocks.contains(block.getType().name().toString())){
			return;
		}
		String playername = event.getPlayer().getUniqueId().toString();
		String world = block.getWorld().getName();
		String xyz = block.getX()+"_"+block.getY()+"_"+block.getZ();
		if(block.getType().name().toString().toLowerCase().contains("brewing")&&plugin.config.getBoolean("autoregister.BREWING_STAND")==true){
			plugin.brewing.set("Register."+world+"."+xyz+".Owner", playername);
			plugin.brewing.set("Register."+world+"."+xyz+".Access", playername);
			event.getPlayer().sendMessage(ChatColor.BLUE+"Shield Schutz auf Braustand gesetzt");
			return;
		}
		if(block.getType().name().toString().toLowerCase().contains("dispenser")&&plugin.config.getBoolean("autoregister.DISPENSER")==true){
			plugin.dispenser.set("Register."+world+"."+xyz+".Owner", playername);
			plugin.dispenser.set("Register."+world+"."+xyz+".Access", playername);
			event.getPlayer().sendMessage(ChatColor.BLUE+"Shield Schutz auf Dispenser gesetzt");
			return;
		}
		if(block.getType().name().toString().toLowerCase().contains("chest")&&plugin.config.getBoolean("autoregister.CHEST")==true){


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
			if(plugin.chest.getString("Register."+world+"."+xyz2+".Owner")!=null){
				String owner = plugin.chest.getString("Register."+world+"."+xyz2+".Owner");
				String access = plugin.chest.getString("Register."+world+"."+xyz2+".Access", "");
				plugin.chest.set("Register."+world+"."+xyz+".Owner", owner);
				plugin.chest.set("Register."+world+"."+xyz+".Access", access);
				return;
			}else{
				if(xyz2!=""){
					plugin.chest.set("Register."+world+"."+xyz2+".Owner", playername);
					plugin.chest.set("Register."+world+"."+xyz2+".Access", playername);}
				}

			plugin.chest.set("Register."+world+"."+xyz+".Owner", playername);
			plugin.chest.set("Register."+world+"."+xyz+".Access", playername);

			event.getPlayer().sendMessage(ChatColor.BLUE+"Shield Schutz auf Kiste gesetzt");
			return;
		}
		if(block.getType().name().toString().toLowerCase().contains("furnance")&&plugin.config.getBoolean("autoregister.FURNANCE")==true){
			plugin.furnance.set("Register."+world+"."+xyz+".Owner", playername);
			plugin.furnance.set("Register."+world+"."+xyz+".Access", playername);
			event.getPlayer().sendMessage(ChatColor.BLUE+"Shield Schutz auf Ofen gesetzt");
			return;
		}
		if(block.getType().name().toString().toLowerCase().contains("wooden_door")&&plugin.config.getBoolean("autoregister.WOODEN_DOOR")==true){
			String xyz2 = "";
			Block bpos1 = block.getLocation().add(0, -1, 0).getBlock();
			Block bpos2 = block.getLocation().add(0, +1, 0).getBlock();
			if(bpos1.getType()==Material.WOODEN_DOOR)
				xyz2 = bpos1.getX()+"_"+bpos1.getY()+"_"+bpos1.getZ();
			if(bpos2.getType()==Material.WOODEN_DOOR)
				xyz2 = bpos2.getX()+"_"+bpos2.getY()+"_"+bpos2.getZ();
			if(xyz2!=""){
				plugin.wooddoor.set("Register."+world+"."+xyz2+".Owner", playername);
				plugin.wooddoor.set("Register."+world+"."+xyz2+".Access", playername);
			}
			plugin.wooddoor.set("Register."+world+"."+xyz+".Owner", playername);
			plugin.wooddoor.set("Register."+world+"."+xyz+".Access", playername);
			event.getPlayer().sendMessage(ChatColor.BLUE+"Shield Schutz auf Holztüre gesetzt");
			return;
		}
		if(block.getType().name().toString().toLowerCase().contains("iron_door")&&plugin.config.getBoolean("autoregister.IRON_DOOR_BLOCK")==true){
			String xyz2 = "";
			Block bpos1 = block.getLocation().add(0, -1, 0).getBlock();
			Block bpos2 = block.getLocation().add(0, +1, 0).getBlock();
			if(bpos1.getType()==Material.IRON_DOOR_BLOCK)
				xyz2 = bpos1.getX()+"_"+bpos1.getY()+"_"+bpos1.getZ();
			if(bpos2.getType()==Material.IRON_DOOR_BLOCK)
				xyz2 = bpos2.getX()+"_"+bpos2.getY()+"_"+bpos2.getZ();
			if(xyz2!=""){
				plugin.wooddoor.set("Register."+world+"."+xyz2+".Owner", playername);
				plugin.wooddoor.set("Register."+world+"."+xyz2+".Access", playername);
			}
			plugin.irondoor.set("Register."+world+"."+xyz+".Owner", playername);
			plugin.irondoor.set("Register."+world+"."+xyz+".Access", playername);
			event.getPlayer().sendMessage(ChatColor.BLUE+"Shield Schutz auf Eisentüre gesetzt");
			return;
		}
		if(block.getType().name().toString().toLowerCase().contains("fence_gate")&&plugin.config.getBoolean("autoregister.FENCE_GATE")==true){
			plugin.fencegate.set("Register."+world+"."+xyz+".Owner", playername);
			plugin.fencegate.set("Register."+world+"."+xyz+".Access", playername);
			event.getPlayer().sendMessage(ChatColor.BLUE+"Shield Schutz auf Gatter gesetzt");
			return;
		}
		if(block.getType().name().toString().toLowerCase().contains("trap_door")&&plugin.config.getBoolean("autoregister.TRAP_DOOR")==true){
			plugin.trapdoor.set("Register."+world+"."+xyz+".Owner", playername);
			plugin.trapdoor.set("Register."+world+"."+xyz+".Access", playername);
			event.getPlayer().sendMessage(ChatColor.BLUE+"Shield Schutz auf Falltüre gesetzt");
			return;
		}
		if(block.getType().name().toString().toLowerCase().contains("button")&&plugin.config.getBoolean("autoregister.BUTTON")==true){
			plugin.buttons.set("Register."+world+"."+xyz+".Owner", playername);
			plugin.buttons.set("Register."+world+"."+xyz+".Access", playername);
			event.getPlayer().sendMessage(ChatColor.BLUE+"Shield Schutz auf Knopf gesetzt");
			return;
		}
		if(block.getType().name().toString().toLowerCase().contains("level")&&plugin.config.getBoolean("autoregister.BUTTON")==true){
			plugin.buttons.set("Register."+world+"."+xyz+".Owner", playername);
			plugin.buttons.set("Register."+world+"."+xyz+".Access", playername);
			event.getPlayer().sendMessage(ChatColor.BLUE+"Shield Schutz auf Hebel gesetzt");
			return;
		}
		if(block.getType().name().toString().toLowerCase().contains("plate")&&plugin.config.getBoolean("autoregister.PLATE")==true){
			plugin.plates.set("Register."+world+"."+xyz+".Owner", playername);
			plugin.plates.set("Register."+world+"."+xyz+".Access", playername);
			event.getPlayer().sendMessage(ChatColor.BLUE+"Shield Schutz auf Druckplatte gesetzt");
			return;
		}
	}

}