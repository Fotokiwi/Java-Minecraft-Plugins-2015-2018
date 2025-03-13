package org.community.EpicFighters.Listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTargetEvent;
import org.community.EpicFighters.EpicFighters;

public class epicFightersEntityTargetEntityEvent implements Listener {
	
	private EpicFighters plugin;
 	 
	public epicFightersEntityTargetEntityEvent(EpicFighters plugin)
	{		
		this.plugin = plugin;
	}
	
	@EventHandler(priority = EventPriority.NORMAL)
    public void EntityTarget(EntityTargetEvent event){
        if(plugin.charmedEntities.get(event.getEntity()) != null && event.getTarget() instanceof Player)
        	event.setCancelled(true);
    }
}