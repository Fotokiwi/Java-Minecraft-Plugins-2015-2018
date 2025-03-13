package org.community.fourWays.Listener;

import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Horse;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerUnleashEntityEvent;
import org.community.fourWays.fourWays;

public class fourWaysPlayerUnleashEvent implements Listener {

	@SuppressWarnings("unused")
	private fourWays plugin = null;
	
	public fourWaysPlayerUnleashEvent(fourWays plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
    public void PlayerUnleash(PlayerUnleashEntityEvent event){

		if(event.getEntityType() == EntityType.HORSE) {
			Horse horse = (Horse) event.getEntity();
			if(!event.getPlayer().getName().equalsIgnoreCase(horse.getOwner().getName())) {
				event.setCancelled(true);
				event.getPlayer().sendMessage(ChatColor.RED + "Du darfst dieses Pferd nicht losleinen, es gehört dir nicht!");
			}
		}
		
    }
	
}
