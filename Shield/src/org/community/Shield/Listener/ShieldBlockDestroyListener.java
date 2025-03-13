package org.community.Shield.Listener;


import org.bukkit.event.entity.EntityBreakDoorEvent;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.community.Shield.Shield;


public class ShieldBlockDestroyListener implements Listener{

	private final Shield plugin;

	public ShieldBlockDestroyListener(Shield plugin)
	{
		this.plugin = plugin;
	}

	/**
	 * Automatische Protection beim setzen.
	 */
	@EventHandler
	public void onBlockDestroy(BlockBreakEvent event) {

		Block block = event.getBlock();
		if(!plugin.Blocks.contains(block.getType().name().toString())){
			return;
		}
		String xyz = block.getX()+"_"+block.getY()+"_"+block.getZ(); 
		Player player = event.getPlayer();
		String world = block.getWorld().getName();

		if(block.getType() == Material.BREWING_STAND){
			if(plugin.brewing.getString("Register."+world+"."+xyz+".Owner")==null){
				return;
			}
			if(plugin.brewing.getString("Register."+world+"."+xyz+".Owner").equals(player.getUniqueId().toString()) || plugin.isAdmin(player)){
				plugin.brewing.set("Register."+world+"."+xyz,null);
				player.sendMessage(ChatColor.BLUE+"Shield protection entfernt");
			}
			else{
				event.setCancelled(true);
				player.sendMessage("Abbau nicht möglich ! Dieser Block ist geschützt durch: " + plugin.brewing.getString("Register."+world+"."+xyz+".Owner"));
			}
		}
		if(block.getType() == Material.DISPENSER){
			if(plugin.dispenser.getString("Register."+world+"."+xyz+".Owner")==null){
				return;
			}
			if(plugin.dispenser.getString("Register."+world+"."+xyz+".Owner").equals(player.getUniqueId().toString())|| plugin.isAdmin(player)){
				plugin.dispenser.set("Register."+world+"."+xyz,null);
				player.sendMessage(ChatColor.BLUE+"Shield protection entfernt");
			}
			else{
				event.setCancelled(true);
				player.sendMessage("Abbau nicht möglich ! Dieser Block ist geschützt durch: " + plugin.dispenser.getString("Register."+world+"."+xyz+".Owner"));
			}
		}
		if(block.getType() == Material.CHEST){
			if(plugin.chest.getString("Register."+world+"."+xyz+".Owner")==null){
				return;
			}
			if(plugin.chest.getString("Register."+world+"."+xyz+".Owner").equals(player.getUniqueId().toString())|| plugin.isAdmin(player)){
				plugin.chest.set("Register."+world+"."+xyz,null);
				player.sendMessage(ChatColor.BLUE+"Shield protection entfernt");
			}
			else{
				event.setCancelled(true);
				player.sendMessage("Abbau nicht möglich ! Dieser Block ist geschützt durch: " + plugin.chest.getString("Register."+world+"."+xyz+".Owner"));
			}
		}
		if(block.getType() == Material.FURNACE){
			if(plugin.furnance.getString("Register."+world+"."+xyz+".Owner")==null){
				return;
			}
			if(plugin.furnance.getString("Register."+world+"."+xyz+".Owner").equals(player.getUniqueId().toString())|| plugin.isAdmin(player)){
				plugin.furnance.set("Register."+world+"."+xyz,null);
				player.sendMessage(ChatColor.BLUE+"Shield protection entfernt");
			}
			else{
				event.setCancelled(true);
				player.sendMessage("Abbau nicht möglich ! Dieser Block ist geschützt durch: " + plugin.furnance.getString("Register."+world+"."+xyz+".Owner"));
			}
		}
		if(block.getType() == Material.WOODEN_DOOR){
			if(plugin.wooddoor.getString("Register."+world+"."+xyz+".Owner")==null){
				return;
			}
			if(plugin.wooddoor.getString("Register."+world+"."+xyz+".Owner").equals(player.getUniqueId().toString())|| plugin.isAdmin(player)){
				plugin.wooddoor.set("Register."+world+"."+xyz,null);
				player.sendMessage(ChatColor.BLUE+"Shield protection entfernt");
			}
			else{
				event.setCancelled(true);
				player.sendMessage("Abbau nicht möglich ! Dieser Block ist geschützt durch: " + plugin.wooddoor.getString("Register."+world+"."+xyz+".Owner"));
			}
		}
		if(block.getType() == Material.IRON_DOOR_BLOCK){
			if(plugin.irondoor.getString("Register."+world+"."+xyz+".Owner")==null){
				return;
			}
			if(plugin.irondoor.getString("Register."+world+"."+xyz+".Owner").equals(player.getUniqueId().toString())|| plugin.isAdmin(player)){
				plugin.irondoor.set("Register."+world+"."+xyz,null);
				player.sendMessage(ChatColor.BLUE+"Shield protection entfernt");
			}
			else{
				event.setCancelled(true);
				player.sendMessage("Abbau nicht möglich ! Dieser Block ist geschützt durch: " + plugin.irondoor.getString("Register."+world+"."+xyz+".Owner"));
			}
		}
		if(block.getType() == Material.FENCE_GATE){
			if(plugin.fencegate.getString("Register."+world+"."+xyz+".Owner")==null){
				return;
			}
			if(plugin.fencegate.getString("Register."+world+"."+xyz+".Owner").equals(player.getUniqueId().toString())|| plugin.isAdmin(player)){
				plugin.fencegate.set("Register."+world+"."+xyz,null);
				player.sendMessage(ChatColor.BLUE+"Shield protection entfernt");
			}
			else{
				event.setCancelled(true);
				player.sendMessage("Abbau nicht möglich ! Dieser Block ist geschützt durch: " + plugin.fencegate.getString("Register."+world+"."+xyz+".Owner"));
			}
		}
		if(block.getType() == Material.TRAP_DOOR){
			if(plugin.trapdoor.getString("Register."+world+"."+xyz+".Owner")==null){
				return;
			}
			if(plugin.trapdoor.getString("Register."+world+"."+xyz+".Owner").equals(player.getUniqueId().toString())|| plugin.isAdmin(player)){
				plugin.trapdoor.set("Register."+world+"."+xyz,null);
				player.sendMessage(ChatColor.BLUE+"Shield protection entfernt");
			}
			else{
				event.setCancelled(true);
				player.sendMessage("Abbau nicht möglich ! Dieser Block ist geschützt durch: " + plugin.trapdoor.getString("Register."+world+"."+xyz+".Owner"));
			}
		}
		if(block.getType() == Material.WOOD_BUTTON || block.getType() == Material.STONE_BUTTON || block.getType() == Material.LEVER){
			if(plugin.buttons.getString("Register."+world+"."+xyz+".Owner")==null){
				return;
			}
			if(plugin.buttons.getString("Register."+world+"."+xyz+".Owner").equals(player.getUniqueId().toString())|| plugin.isAdmin(player)){
				plugin.buttons.set("Register."+world+"."+xyz,null);
				player.sendMessage(ChatColor.BLUE+"Shield protection entfernt");
			}
			else{
				event.setCancelled(true);
				player.sendMessage("Abbau nicht möglich ! Dieser Block ist geschützt durch: " + plugin.buttons.getString("Register."+world+"."+xyz+".Owner"));
			}
		}
		if(block.getType() == Material.WOOD_PLATE || block.getType() == Material.STONE_PLATE){
			if(plugin.plates.getString("Register."+world+"."+xyz+".Owner")==null){
				return;
			}
			if(plugin.plates.getString("Register."+world+"."+xyz+".Owner").equals(player.getUniqueId().toString())|| plugin.isAdmin(player)){
				plugin.plates.set("Register."+world+"."+xyz,null);
				player.sendMessage(ChatColor.BLUE+"Shield protection entfernt");
			}
			else{
				event.setCancelled(true);
				player.sendMessage("Abbau nicht möglich ! Dieser Block ist geschützt durch: " + plugin.plates.getString("Register."+world+"."+xyz+".Owner"));
			}
		}
	}
	 @EventHandler
	    public void entityBreakDoor(EntityBreakDoorEvent event) {
	        Block block = event.getBlock();
	        if(!plugin.Blocks.contains(block.getType().name().toString())){
				return;
			}
			String xyz = block.getX()+"_"+block.getY()+"_"+block.getZ(); 
			String world = block.getWorld().getName();
	        if(block.getType() == Material.BREWING_STAND){
				if(plugin.brewing.getString("Register."+world+"."+xyz+".Owner")!=null){
					event.setCancelled(true);
					return;
				}
			}
			if(block.getType() == Material.DISPENSER){
				if(plugin.dispenser.getString("Register."+world+"."+xyz+".Owner")!=null){
					event.setCancelled(true);
					return;
				}
			}
			if(block.getType() == Material.CHEST){
				if(plugin.chest.getString("Register."+world+"."+xyz+".Owner")!=null){
					event.setCancelled(true);
					return;
				}
			}
			if(block.getType() == Material.FURNACE){
				if(plugin.furnance.getString("Register."+world+"."+xyz+".Owner")!=null){
					event.setCancelled(true);
					return;
				}
			}
			if(block.getType() == Material.WOODEN_DOOR){
				if(plugin.wooddoor.getString("Register."+world+"."+xyz+".Owner")!=null){
					event.setCancelled(true);
					return;
				}
			}
			if(block.getType() == Material.IRON_DOOR_BLOCK){
				if(plugin.irondoor.getString("Register."+world+"."+xyz+".Owner")!=null){
					event.setCancelled(true);
					return;
				}
			}
			if(block.getType() == Material.FENCE_GATE){
				if(plugin.fencegate.getString("Register."+world+"."+xyz+".Owner")!=null){
					event.setCancelled(true);
					return;
				}
			}
			if(block.getType() == Material.TRAP_DOOR){
				if(plugin.trapdoor.getString("Register."+world+"."+xyz+".Owner")!=null){
					event.setCancelled(true);
					return;
				}
			}
			if(block.getType() == Material.WOOD_BUTTON || block.getType() == Material.STONE_BUTTON || block.getType() == Material.LEVER){
				if(plugin.buttons.getString("Register."+world+"."+xyz+".Owner")!=null){
					event.setCancelled(true);
					return;
				}
			}
			if(block.getType() == Material.WOOD_PLATE || block.getType() == Material.STONE_PLATE){
				if(plugin.plates.getString("Register."+world+"."+xyz+".Owner")!=null){
					event.setCancelled(true);
					return;
				}
			}
	        
	 }

}