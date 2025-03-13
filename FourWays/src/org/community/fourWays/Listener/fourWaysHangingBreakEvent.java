package org.community.fourWays.Listener;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.hanging.HangingBreakByEntityEvent;
import org.community.fourWays.fourWays;

public class fourWaysHangingBreakEvent implements Listener {

	@SuppressWarnings("unused")
	private fourWays plugin = null;
	
	public fourWaysHangingBreakEvent(fourWays plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void onHangingBreak(HangingBreakByEntityEvent event) {
		
		if(event.getEntity().getType() == EntityType.LEASH_HITCH)
			event.setCancelled(true);
		
    }
	
}
