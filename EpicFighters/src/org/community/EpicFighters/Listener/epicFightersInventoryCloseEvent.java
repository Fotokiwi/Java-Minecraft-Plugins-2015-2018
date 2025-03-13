package org.community.EpicFighters.Listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.community.EpicFighters.EpicFighters;
import org.community.EpicFighters.Core.epicFightersSkillScreen;


public class epicFightersInventoryCloseEvent implements Listener{

	private EpicFighters plugin;

	public epicFightersInventoryCloseEvent(EpicFighters plugin){
		this.plugin = plugin;
	}

	@EventHandler(priority = EventPriority.NORMAL)
	public void onInventoryClick(InventoryCloseEvent event) {
		
		if(event.getInventory().getType() != InventoryType.CHEST)
			return;
		
		if(plugin.eFCore.isSkillWindow((Player) event.getPlayer())) {
			//plugin.getServer().broadcastMessage(event.getInventory() + "");
			epicFightersSkillScreen skill = plugin.eFCore.getSkillWindow((Player) event.getPlayer());			

			skill.skill1 = event.getInventory().getItem(20);
			skill.skill2 = event.getInventory().getItem(21);
			skill.skill3 = event.getInventory().getItem(22);
			skill.skill4 = event.getInventory().getItem(23);
			skill.skill5 = event.getInventory().getItem(24);
			
			skill.cancelSkillWindow();
		}
		
    }
}
