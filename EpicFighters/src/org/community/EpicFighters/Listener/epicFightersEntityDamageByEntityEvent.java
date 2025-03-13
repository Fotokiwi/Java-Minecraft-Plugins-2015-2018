package org.community.EpicFighters.Listener;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.community.EpicFighters.EpicFighters;


public class epicFightersEntityDamageByEntityEvent implements Listener {

	private EpicFighters plugin;

	public epicFightersEntityDamageByEntityEvent(EpicFighters plugin) {
		this.plugin = plugin;
	}

	@EventHandler(priority = EventPriority.LOWEST)
	public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {

		Entity entity = event.getEntity();
		Entity attackingEntity = event.getDamager();
		
		if(entity instanceof ItemFrame)
			return;
		
		Player player = null;

		if(attackingEntity instanceof Player) {
			player = (Player) attackingEntity;
			if(plugin.charmedEntities.get(event.getEntity()) != null)
	        	plugin.charmedEntities.remove(entity);
		} else {
			return;
		}
		
		Material item = Material.AIR;
		item = player.getItemInHand().getType();
		int attackDelay = 0;
		//Bukkit.getServer().broadcastMessage("Entity: " +  ((LivingEntity) entity).getHealth());
		//if(item != Material.AIR && item != Material.WOOD_SWORD && item != Material.STONE_SWORD && item != Material.IRON_SWORD && item != Material.GOLD_SWORD && item != Material.DIAMOND_SWORD)
		//	return;

		if(entity.getType() == EntityType.VILLAGER && item == Material.AIR)
			event.setCancelled(true);


		if(item == Material.WOOD_SWORD)
			attackDelay = plugin.woodSword;
		else if(item == Material.STONE_SWORD)
			attackDelay = plugin.stoneSword;
		else if(item == Material.IRON_SWORD)
			attackDelay = plugin.ironSword;
		else if(item == Material.GOLD_SWORD)
			attackDelay = plugin.goldSword;
		else if(item == Material.DIAMOND_SWORD)
			attackDelay = plugin.diamondSword;
		else if(item == Material.AIR)
			attackDelay = plugin.handFight;
		else
			attackDelay = plugin.otherFight;
		
		if(!plugin.dpsDelay.containsKey(player)) {
			plugin.dpsDelay.put(player, System.currentTimeMillis());
			return;
		}


		if(System.currentTimeMillis() >= plugin.dpsDelay.get(player) + attackDelay) {
			plugin.dpsDelay.put(player, System.currentTimeMillis());
			return;
		} else {
			//player.sendMessage("Du hast zu früh erneut angegriffen!");
			event.setCancelled(true);
			return;
		}
	}
}