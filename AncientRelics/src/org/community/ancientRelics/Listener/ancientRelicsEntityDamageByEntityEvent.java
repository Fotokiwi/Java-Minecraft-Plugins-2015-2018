package org.community.ancientRelics.Listener;

import java.util.List;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.ThrownPotion;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityCombustByEntityEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PotionSplashEvent;
import org.bukkit.projectiles.ProjectileSource;
import org.community.ancientRelics.ancientRelics;

public class ancientRelicsEntityDamageByEntityEvent implements Listener {
	
	private final ancientRelics plugin;

	public ancientRelicsEntityDamageByEntityEvent(ancientRelics plugin)
	{
		this.plugin = plugin;
	}

	@EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
	public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {

		Entity damager = event.getDamager();
		Entity entity = event.getEntity();
		
		if(!(entity instanceof Player))
			return;
		
		if(damager instanceof Projectile) {
			Projectile projectileDamager = (Projectile) damager; 
			if(projectileDamager.getShooter() == null)
				return;
			damager = (Entity) projectileDamager.getShooter();
		}
		
		// Duell-Modus Abbruch-Abfrage bei Angreifer != Spieler
		if(plugin.duellModus.get((Player) entity) != null && !(damager instanceof Player)) {
			plugin.aRPvP.aRDuellInvite.cancelDuell((Player) entity);
		}
		
		if(damager instanceof Player){
			
			if(!plugin.aRApiPvpStatus.getOnlinePlayerList().contains(entity) || !plugin.aRApiPvpStatus.getOnlinePlayerList().contains(damager))
				return;
			
			if(plugin.aRApiPvpStatus.getPvpStatus(damager, entity, 0)) {
				event.setCancelled(true);
				return;
			} else {
				return;
			}
		}
		
		/*if(damager instanceof Player){
			Player defender = (Player) entity;
			Player attacker = (Player) damager;
			
			// Duell-Modus Abbruch-Abfrage bei Angreifer != Co-Duellant
			if(plugin.duellModus.get(defender) != null && plugin.duellModus.get(attacker) == null) {
				plugin.aRPvP.aRDuellInvite.cancelDuell((Player) entity);
			}
			
			// Duellmodus-Handling
			if(plugin.duellModus.get(attacker) == defender && plugin.duellModus.get(defender) == attacker) {
				double health = defender.getHealth();
				double dmg = event.getDamage();
				if(dmg >= health) {
					plugin.getServer().broadcastMessage(attacker.getName() + " hat das Duell gegen " + defender.getName() + " gewonnen.");
					event.setCancelled(true);
					defender.setHealth(20);
					attacker.setHealth(20);
					plugin.duellModus.put(attacker, null);
					plugin.duellModus.put(defender, null);
				} else {
					return;
				}
			}
			//Stadt-Diplomatie-Abfrage
			//Wenn sich zwei St�dte im Krieg befinden, verlieren alle anderen Einstellungen ihre G�ltigkeit
			//Das Diplomatie-System hat die oberste Priorit�t
			if(plugin.newSettlersAPI.nSAPI.isPlayerInTown(attacker) && plugin.newSettlersAPI.nSAPI.isPlayerInTown(defender) && (plugin.newSettlersAPI.nSAPI.getPlayerTown(attacker) != plugin.newSettlersAPI.nSAPI.getPlayerTown(defender))) {
				Town attackerTown = plugin.newSettlersAPI.nSAPI.getPlayerTown(attacker);
				Town defenderTown = plugin.newSettlersAPI.nSAPI.getPlayerTown(defender);
				if(attackerTown.getEnemies().contains(defenderTown.getName())) {
					return;
				} else if (attackerTown.getAllies().contains(defenderTown.getName())){
					event.setCancelled(true);
					return;
				} else {
					//attacker.sendMessage("Eure St�dte befinden sich nicht im Krieg.");
				}
			} else {
				//attacker.sendMessage("Einer von euch ist in keiner Stadt o.o");
			}
			// Nur wenn beide Spieler eine Gruppe haben und beide in der gleichen sind, pr�fe die Gruppen-PvP-Flag
			// Ansonsten gehe direkt zur �berpr�fung der Spieler-PvP-Flag weiter
			if((plugin.aRGroups.isPlayerInGroup(attacker) && plugin.aRGroups.isPlayerInGroup(defender)) && (plugin.aRGroups.getPlayerGroupInfo(attacker) == plugin.aRGroups.getPlayerGroupInfo(defender))){
				// Wenn der PvP-Status aktiv ist, reiche den Schaden durch
				// Ansonsten beende das Dmg-Event
				if(plugin.aRGroups.getPlayerGroupInfo(attacker).getPvPStatus()) {
					//attacker.sendMessage("[DEBUG]: Ihr seid zwar in der gleichen Gruppe aber PvP ist aktiv!");
					return;
				} else {
					//attacker.sendMessage("[DEBUG]: Ihr seid in der gleichen Gruppe Mensch ...");
					event.setCancelled(true);
					return;
				}
			} else {
				if(plugin.aRPvP.getPlayerPvPStatus(attacker.getName()) == false){
					attacker.sendMessage(ChatColor.DARK_RED + "Du hast kein PvP aktiviert!");
					plugin.aRPvP.aRPvPUser.user.set("Spieler." + attacker.getName() + ".CoolDown", System.currentTimeMillis());
					event.setCancelled(true);
					return;
				}
				if(plugin.aRPvP.getPlayerPvPStatus(defender.getName()) == false){
					attacker.sendMessage(ChatColor.DARK_RED + defender.getName() + " hat kein PvP aktiviert!");
					defender.sendMessage(ChatColor.DARK_RED + attacker.getName() + " hat versucht dich anzugreifen!");
					plugin.aRPvP.aRPvPUser.user.set("Spieler." + attacker.getName() + ".CoolDown", System.currentTimeMillis());
					event.setCancelled(true);
					return;
				}
			}
			plugin.aRPvP.aRPvPUser.user.set("Spieler." + attacker.getName() + ".CoolDown", System.currentTimeMillis());
			// safety variable containing last damager in case of natural death during active player versus player
			plugin.lastDamagerPlayer.put(defender, attacker);
			plugin.lastDamagerTimestamp.put(defender, System.currentTimeMillis());
			return;
		}*/
		
	}	
	
	@EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
	public void onPotionSplashEvent(PotionSplashEvent event) {

		List<LivingEntity> affectedEntities = (List<LivingEntity>) event.getAffectedEntities();
		ThrownPotion potion = event.getPotion();

		ProjectileSource source = potion.getShooter();
		Entity damager = null;
		if(source instanceof LivingEntity) {
			damager = (Entity) source;
		} else {
			return;
		}

		for (LivingEntity damaged : affectedEntities) {
			/*
			 * Don't block potion use on ourselves.
			 */
			if(damaged instanceof Player && damager instanceof Player) {
				if (damager != damaged) {
					if(plugin.aRApiPvpStatus.getPvpStatus(damager, damaged, 0)) {
						event.setCancelled(true);
						return;
					} else {
						return;
					}
				}
			}
			/*if(damaged instanceof Player && damager instanceof Player) {
				if (damager != damaged) {
					Player defender = (Player) damaged;
					Player attacker = (Player) damager;
					
					// Duell-Modus Abbruch-Abfrage bei Angreifer != Co-Duellant
					if(plugin.duellModus.get(defender) != null && plugin.duellModus.get(attacker) == null) {
						plugin.aRPvP.aRDuellInvite.cancelDuell(defender);
					}
					
					// Duellmodus-Handling
					if(plugin.duellModus.get(attacker) == defender && plugin.duellModus.get(defender) == attacker) {
						return;
					}
					if((plugin.aRGroups.isPlayerInGroup(attacker) && plugin.aRGroups.isPlayerInGroup(defender)) && (plugin.aRGroups.getPlayerGroupInfo(attacker) == plugin.aRGroups.getPlayerGroupInfo(defender))){
						// Wenn der PvP-Status aktiv ist, reiche den Schaden durch
						// Ansonsten beende das Dmg-Event
						if(plugin.aRGroups.getPlayerGroupInfo(attacker).getPotionStatus()) {
							//attacker.sendMessage("[DEBUG]: Ihr seid zwar in der gleichen Gruppe aber PvP ist aktiv!");
							return;
						} else {
							//attacker.sendMessage("[DEBUG]: Ihr seid in der gleichen Gruppe Mensch ...");
							event.setCancelled(true);
							return;
						}
					} else {
						if(plugin.aRPvP.getPlayerPvPStatus(attacker.getName()) == false){
							attacker.sendMessage(ChatColor.DARK_RED + "Du hast kein PvP aktiviert!");
							plugin.aRPvP.aRPvPUser.user.set("Spieler." + attacker.getName() + ".CoolDown", System.currentTimeMillis());
							event.setIntensity(defender, -1.0);
							return;
						}
						if(plugin.aRPvP.getPlayerPvPStatus(defender.getName()) == false){
							attacker.sendMessage(ChatColor.DARK_RED + defender.getName() + " hat kein PvP aktiviert!");
							defender.sendMessage(ChatColor.DARK_RED + attacker.getName() + " hat versucht dich anzugreifen!");
							plugin.aRPvP.aRPvPUser.user.set("Spieler." + attacker.getName() + ".CoolDown", System.currentTimeMillis());
							event.setIntensity(defender, -1.0);
							return;
						}
					}
					plugin.aRPvP.aRPvPUser.user.set("Spieler." + attacker.getName() + ".CoolDown", System.currentTimeMillis());
					// safety variable containing last damager in case of natural death during active player versus player
					plugin.lastDamagerPlayer.put(defender, attacker);
					plugin.lastDamagerTimestamp.put(defender, System.currentTimeMillis());
					return;
				}
			}*/
		}

	}

	@EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
	public void onEntityCombustByEntityEvent(EntityCombustByEntityEvent event) {

		Entity combuster = event.getCombuster();
		Entity damaged = event.getEntity();
		
		if(!(damaged instanceof Player))
			return;

		if (combuster instanceof Projectile) {

			ProjectileSource source = ((Projectile) combuster).getShooter();
			LivingEntity damager = null;
			if(source instanceof LivingEntity) {
				damager = (LivingEntity) source;
			} else {
				return;
			}

			// There is an attacker and Not war time.
			if((damager != null)) {
				if (damaged instanceof Player && damager instanceof Player) {
					if(plugin.aRApiPvpStatus.getPvpStatus(damager, damaged, 0)) {
						combuster.remove();
						event.setCancelled(true);
						return;
					} else {
						return;
					}
				}
			}
			/*if ((damager != null)) {

				if (damaged instanceof Player && damager instanceof Player) {
					Player defender = (Player) damaged;
					Player attacker = (Player) damager;
					
					// Duell-Modus Abbruch-Abfrage bei Angreifer != Co-Duellant
					if(plugin.duellModus.get(defender) != null && plugin.duellModus.get(attacker) == null) {
						plugin.aRPvP.aRDuellInvite.cancelDuell(defender);
					}
					
					// Duellmodus-Handling
					if(plugin.duellModus.get(attacker) == defender && plugin.duellModus.get(defender) == attacker) {
						return;
					}
					if((plugin.aRGroups.isPlayerInGroup(attacker) && plugin.aRGroups.isPlayerInGroup(defender)) && (plugin.aRGroups.getPlayerGroupInfo(attacker) == plugin.aRGroups.getPlayerGroupInfo(defender))){
						// Wenn der PvP-Status aktiv ist, reiche den Schaden durch
						// Ansonsten beende das Dmg-Event
						if(plugin.aRGroups.getPlayerGroupInfo(attacker).getPvPStatus()) {
							//attacker.sendMessage("[DEBUG]: Ihr seid zwar in der gleichen Gruppe aber PvP ist aktiv!");
							return;
						} else {
							//attacker.sendMessage("[DEBUG]: Ihr seid in der gleichen Gruppe Mensch ...");
							event.setCancelled(true);
							return;
						}
					} else {
						if(plugin.aRPvP.getPlayerPvPStatus(attacker.getName()) == false){
							attacker.sendMessage(ChatColor.DARK_RED + "Du hast kein PvP aktiviert!");
							plugin.aRPvP.aRPvPUser.user.set("Spieler." + attacker.getName() + ".CoolDown", System.currentTimeMillis());
							combuster.remove();
							event.setCancelled(true);
							return;
						}
						if(plugin.aRPvP.getPlayerPvPStatus(defender.getName()) == false){
							attacker.sendMessage(ChatColor.DARK_RED + defender.getName() + " hat kein PvP aktiviert!");
							defender.sendMessage(ChatColor.DARK_RED + attacker.getName() + " hat versucht dich anzugreifen!");
							plugin.aRPvP.aRPvPUser.user.set("Spieler." + attacker.getName() + ".CoolDown", System.currentTimeMillis());
							combuster.remove();
							event.setCancelled(true);
							return;
						}
					}
					plugin.aRPvP.aRPvPUser.user.set("Spieler." + attacker.getName() + ".CoolDown", System.currentTimeMillis());
					// safety variable containing last damager in case of natural death during active player versus player
					plugin.lastDamagerPlayer.put(defender, attacker);
					plugin.lastDamagerTimestamp.put(defender, System.currentTimeMillis());
					return;
				}
			}*/
		}

	}

}