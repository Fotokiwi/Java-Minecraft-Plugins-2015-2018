package org.community.Shield.Listener;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.community.Shield.Shield;


public class ShieldInteractListener implements Listener{

	private final Shield plugin;

	public ShieldInteractListener(Shield plugin)
	{
		this.plugin = plugin;
	}


	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerInteract(PlayerInteractEvent event) {

		if(!event.hasBlock())
			return;

		Block block = event.getClickedBlock();
		//nachsehen ob der Block überhaupt ein Shield Protectbarer Block ist.
		if(!plugin.Blocks.contains(block.getType().name().toString())){
			return;
		}
		Player player = event.getPlayer();
		if (event.getAction() == Action.LEFT_CLICK_BLOCK){
			if(plugin.persist.containsKey(player)){
			  String modus = plugin.persist.get(player);
			  if(modus.equals("create")){
				  player.performCommand("screate");
			  }
			  else if(modus.equals("remove")){
				  player.performCommand("sremove");
			  }
			  else if(modus.contains("a_")){
				  String[] splittet = modus.split("_");
				  player.performCommand("smodify "+splittet[1]);
			  }
			  else if(modus.contains("r_")){
				  String[] splittet = modus.split("_");
				  player.performCommand("sremove "+splittet[1]);				  
			  }
				  
			}
		}
	
        	
		if(plugin.hasAccess(player, block)==false){
			if(plugin.isAdmin(player)){
				return;
			}
			event.setCancelled(true);
			if(block.getType()!=Material.WOOD_PLATE && block.getType()!=Material.STONE_PLATE)
				player.sendMessage(ChatColor.BLUE+"Du hast keinen Zugriff");
		}

	}

}