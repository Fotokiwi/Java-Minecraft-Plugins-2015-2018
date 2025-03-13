package org.community.fourWays.Listener;

import java.util.Random;
import java.util.Set;

import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.projectiles.ProjectileSource;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.community.fourWays.fourWays;


public class fourWaysEntityDamageByEntityEvent implements Listener {

	private fourWays plugin;

	public fourWaysEntityDamageByEntityEvent(fourWays plugin) {
		this.plugin = plugin;
	}

	@EventHandler(priority = EventPriority.LOW)
	public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {

		Entity entity = event.getEntity();
		Entity attackingEntity = event.getDamager();
		
		Player player = null;
		
		if(attackingEntity instanceof Fireball) {
			Fireball fireball = (Fireball) attackingEntity;
			ProjectileSource source =  fireball.getShooter();
			if(source instanceof Player) {
				player = (Player) fireball.getShooter();
			} else {
				return;
			}			
		} else if(attackingEntity instanceof Arrow) {
			Arrow arrow = (Arrow) attackingEntity;
			if(arrow.getShooter() instanceof Skeleton)
				return;
			player = (Player) arrow.getShooter();
		} else if(attackingEntity instanceof Player) {
			player = (Player) attackingEntity;
		} else {
			return;
		}		

		if(entity.getType() == EntityType.VILLAGER){
			if(plugin.config.getBoolean("Villager.Config.active") == true) {

				String[] messageKeysArray = null;
				String[] messages;
				int messageLines = 1;

				ConfigurationSection messageSection = plugin.config.getConfigurationSection("Villager.Message");
				Set<String> messageKeys = messageSection.getKeys(false);
				messageKeysArray = messageKeys.toArray(new String[0]); 

				Random rnd = new Random();
				int roll = rnd.nextInt(messageKeysArray.length + 1);
				if(roll == 0)
					roll = 1;

				String originalMessage = plugin.config.getString("Villager.Message." + roll);
				originalMessage = originalMessage.replace("<player>", player.getName());
				messageLines = originalMessage.replaceAll("[^|]", "").length() + 1;
				if(messageLines == 1) {
					messages = new String[1];
					messages[0] = originalMessage;
				} else {
					messages = originalMessage.split("\\+");
				}		

				for(int i = 0; i < messageLines; i++) {
					player.sendMessage(ChatColor.GRAY + "<Dorfbewohner>: " + messages[i]);
				}
			}
		}

		plugin.damagerTemp.put(entity.getEntityId(), player);
	}
}