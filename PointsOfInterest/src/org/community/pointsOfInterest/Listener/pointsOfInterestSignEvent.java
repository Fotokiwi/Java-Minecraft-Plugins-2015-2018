package org.community.pointsOfInterest.Listener;

import java.io.File;
import java.util.HashMap;
import java.util.Random;
import java.util.Set;

import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.community.pointsOfInterest.pointsOfInterest;

@SuppressWarnings("unused")
public class pointsOfInterestSignEvent implements Listener {	
	
	private pointsOfInterest plugin;

	public pointsOfInterestSignEvent(pointsOfInterest plugin) {
		this.plugin = plugin;
	}
	
	
	@EventHandler(priority = EventPriority.NORMAL)
    public void onSignChange(final SignChangeEvent event)
    {

        if (event.isCancelled())
        {
            return;
        }

       
        if (!(ChatColor.stripColor(event.getLine(0))
                .equalsIgnoreCase("[poi]")))
        {
            // kein POI Sign
            return;
        }
        
		Block block = event.getBlock();
		Player player = event.getPlayer();
        Sign sign = (Sign) block.getState();
		
        //nur Spieler der Stufe 1+ k�nnen Poi-Schilder setzen!
        if (plugin.poiUser.getOnePlayer(event.getPlayer().getUniqueId()).getStufe()<1)
        {
        	player.sendMessage(ChatColor.RED + "Deine Stufe reicht dafür noch nicht aus!");
        	return;
        }
   
        event.setLine(0, ChatColor.GREEN +  plugin.poiUser.getOnePlayer(event.getPlayer().getUniqueId()).getSpielerName());
        event.setLine(1, ChatColor.RED+ "***"+ChatColor.GOLD + plugin.poiUser.getOnePlayer(player.getUniqueId()) +ChatColor.RED+ "***");
        event.setLine(2, "Orte gefunden");
        event.setLine(3, "von: " + ChatColor.DARK_PURPLE+ plugin.poiPOIs.getNumberOfPOIs());
        sign.update(true);
        plugin.poiSign.addOneSign(player.getUniqueId(), event.getBlock().getLocation());
    }
}