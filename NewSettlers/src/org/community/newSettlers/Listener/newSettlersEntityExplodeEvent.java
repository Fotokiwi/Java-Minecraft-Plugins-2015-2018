package org.community.newSettlers.Listener;

import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.community.newSettlers.newSettlers;

public class newSettlersEntityExplodeEvent implements Listener{

	@SuppressWarnings("unused")
	private newSettlers plugin;
	
	public newSettlersEntityExplodeEvent(newSettlers plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
    public void EntityExplodeEvent(EntityExplodeEvent event) {

		if(event.getEntity() instanceof TNTPrimed)
			event.blockList().clear();
		
	}
	
}
