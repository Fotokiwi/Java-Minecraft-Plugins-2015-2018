package org.community.EpicFighters.Listener;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.community.EpicFighters.EpicFighters;

public class epicFightersPlayerInteractEvent implements Listener {
	
	private EpicFighters plugin;

	public epicFightersPlayerInteractEvent(EpicFighters plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler(priority = EventPriority.LOW)
	public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if (player.getItemInHand().getType() != Material.BLAZE_ROD && player.getItemInHand().getType() != Material.BOW && player.getItemInHand().getType() != Material.WOOD_SWORD && player.getItemInHand().getType() != Material.STONE_SWORD && player.getItemInHand().getType() != Material.IRON_SWORD && player.getItemInHand().getType() != Material.GOLD_SWORD && player.getItemInHand().getType() != Material.DIAMOND_SWORD)
        	return;
        
        if (event.getAction() == Action.RIGHT_CLICK_AIR && player.isSneaking() && player.getItemInHand().getType() != Material.BOW) {
        	plugin.eFSkillScroll.changeActiveSpell(player);
        	event.setCancelled(true);
        	return;
        } else if (event.getAction() == Action.RIGHT_CLICK_BLOCK && player.isSneaking() && player.getItemInHand().getType() != Material.BOW) {
        	plugin.eFSkillScroll.changeActiveSpell(player);
        	event.setCancelled(true);
        	return;
        } else if (event.getAction() == Action.LEFT_CLICK_AIR && player.isSneaking() && player.getItemInHand().getType() == Material.BOW) {
        	plugin.eFSkillScroll.changeActiveSpell(player);
        	event.setCancelled(true);
        	return;
        } else if (event.getAction() == Action.LEFT_CLICK_BLOCK && player.isSneaking() && player.getItemInHand().getType() == Material.BOW) {
        	plugin.eFSkillScroll.changeActiveSpell(player);
        	event.setCancelled(true);
        	return;
        }
	}
	
}