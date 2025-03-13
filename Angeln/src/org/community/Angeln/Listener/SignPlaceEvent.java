package org.community.Angeln.Listener;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import org.community.Angeln.Angeln;


public class SignPlaceEvent implements Listener {	
	
	private Angeln plugin;

	public SignPlaceEvent(Angeln plugin) {
		this.plugin = plugin;
	}	
	
	@EventHandler(priority = EventPriority.NORMAL)
    public void onSignChange(SignChangeEvent event)
    {

        if (event.isCancelled()) {
            return;
        }

       
        if (!(ChatColor.stripColor(event.getLine(0)).equalsIgnoreCase("[Angeln]"))) {
            return;
        }
        
		Player player = event.getPlayer();
		
        if (plugin.players.getConfigurationSection("Spieler." + player.getUniqueId().toString()) == null) {
        	player.sendMessage(ChatColor.RED + "Du hast noch keine seltenen Fische geangelt.");
        	return;
        }
        
        if(event.getLine(2) == null || event.getLine(2).equalsIgnoreCase("")) {
        	plugin.angelnAchievements.displayGeneralStats(event, player);
        	return;
        }
        
        if(event.getLine(2) != null) {
        	plugin.angelnAchievements.displaySpecialStats(event, player, event.getLine(2));
        	return;
        }
   
    }
}