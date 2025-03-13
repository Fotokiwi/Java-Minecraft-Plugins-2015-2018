package org.community.DungeonUtility.EndlessDispenser;


import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_7_R3.block.CraftDispenser;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.community.DungeonUtility.*;


public class endlessDispenserListenerOnDispenseEvent implements Listener {	
	
	private DungeonUtility plugin;

	public endlessDispenserListenerOnDispenseEvent(DungeonUtility plugin) {
		this.plugin = plugin;
	}
	
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void onPlayerInteract(PlayerInteractEvent event){
		
		if(event.getAction() != Action.RIGHT_CLICK_BLOCK)
			return;	
		
		if(event.getClickedBlock().getType() != Material.DISPENSER)
			return;
		
		Player player = event.getPlayer();
		Block block = event.getClickedBlock();
		
		if(plugin.dispenserMode.get(block) == null)
			return;
		
		if(plugin.config.getList("Admins").contains(player.getName()))
			return;
		
		event.setCancelled(true);
		return;
    } 
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void onDispense(BlockDispenseEvent event) {	
		if (event.isCancelled())
			return;
		
		Block block = event.getBlock();
		
		if(plugin.dispenserMode.get(block) != null) {
			CraftDispenser dispenser = new CraftDispenser(event.getBlock());
  		  	ItemStack newItemStack = event.getItem().clone();
  		  	dispenser.getInventory().addItem(newItemStack);
  		}
	}
}