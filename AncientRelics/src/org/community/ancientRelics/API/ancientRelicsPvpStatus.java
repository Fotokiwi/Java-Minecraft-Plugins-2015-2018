package org.community.ancientRelics.API;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.community.ancientRelics.ancientRelics;
import org.community.newSettlers.Town.Town;

public class ancientRelicsPvpStatus {
	
	private final ancientRelics plugin;

	public ancientRelicsPvpStatus(ancientRelics plugin)
	{
		this.plugin = plugin;
	}

	/*
	 * Liefert true zur�ck, wenn das Event abgebrochen werden soll (PvP inaktiv)
	 * Liefert false zur�ck, wenn der Schaden berechnet und ausgeteilt werden soll
	 * @return
	 */
	public boolean getPvpStatus(Entity attacker, Entity defender, int damage) {
		
		// Duell-Modus Abbruch-Abfrage bei Angreifer != Spieler
		if(plugin.duellModus.get((Player) defender) != null && !(attacker instanceof Player)) {
			plugin.aRPvP.aRDuellInvite.cancelDuell((Player) defender);
		}
		
		if(attacker instanceof Player){
			Player def = (Player) defender;
			Player att = (Player) attacker;
			
			// Duell-Modus Abbruch-Abfrage bei Angreifer != Co-Duellant
			if(plugin.duellModus.get(def) != null && plugin.duellModus.get(att) == null) {
				plugin.aRPvP.aRDuellInvite.cancelDuell((Player) def);
				return true;
			}
			
			// Duellmodus-Handling
			if(plugin.duellModus.get(attacker) == defender && plugin.duellModus.get(defender) == attacker) {
				double health = def.getHealth();
				double dmg = damage;
				if(dmg >= health) {
					plugin.getServer().broadcastMessage(att.getName() + " hat das Duell gegen " + def.getName() + " gewonnen.");
					def.setHealth(20);
					att.setHealth(20);
					plugin.duellModus.put(att, null);
					plugin.duellModus.put(def, null);
					return true;
				} else {
					setFlags(att, def);
					return false;
				}
			}
			//Stadt-Diplomatie-Abfrage
			//Wenn sich zwei St�dte im Krieg befinden, verlieren alle anderen Einstellungen ihre G�ltigkeit
			//Das Diplomatie-System hat die oberste Priorit�t
			if(plugin.newSettlersAPI.nSAPI.isPlayerInTown(att) && plugin.newSettlersAPI.nSAPI.isPlayerInTown(def) && (plugin.newSettlersAPI.nSAPI.getPlayerTown(att) != plugin.newSettlersAPI.nSAPI.getPlayerTown(def))) {
				Town attackerTown = plugin.newSettlersAPI.nSAPI.getPlayerTown(att);
				Town defTown = plugin.newSettlersAPI.nSAPI.getPlayerTown(def);
				if(attackerTown.getEnemies().contains(defTown.getName())) {
					setFlags(att, def);
					return false;
				} else if (attackerTown.getAllies().contains(defTown.getName())){
					return true;
				} else {
					//attacker.sendMessage("Eure St�dte befinden sich nicht im Krieg.");
				}
			} else {
				//att.sendMessage("Einer von euch ist in keiner Stadt o.o");
			}
			// Nur wenn beide Spieler eine Gruppe haben und beide in der gleichen sind, pr�fe die Gruppen-PvP-Flag
			// Ansonsten gehe direkt zur �berpr�fung der Spieler-PvP-Flag weiter
			if((plugin.aRGroups.isPlayerInGroup(att) && plugin.aRGroups.isPlayerInGroup(def)) && (plugin.aRGroups.getPlayerGroupInfo(att) == plugin.aRGroups.getPlayerGroupInfo(def))){
				// Wenn der PvP-Status aktiv ist, reiche den Schaden durch
				// Ansonsten beende das Dmg-Event
				if(plugin.aRGroups.getPlayerGroupInfo(att).getPvPStatus()) {
					//att.sendMessage("[DEBUG]: Ihr seid zwar in der gleichen Gruppe aber PvP ist aktiv!");
					setFlags(att, def);
					return false;
				} else if(plugin.aRGroups.getPlayerGroupInfo(att).getPotionStatus()) {
					//att.sendMessage("[DEBUG]: Ihr seid zwar in der gleichen Gruppe aber PvP ist aktiv!");
					setFlags(att, def);
					return false;
				} else {
					//att.sendMessage("[DEBUG]: Ihr seid in der gleichen Gruppe Mensch ...");
					return true;
				}
			} else {
				if(plugin.aRPvP.getPlayerPvPStatus(att.getName()) == false){
					att.sendMessage(ChatColor.DARK_RED + "Du hast kein PvP aktiviert!");
					plugin.aRPvP.aRPvPUser.user.set("Spieler." + att.getName() + ".CoolDown", System.currentTimeMillis());
					return true;
				}
				if(plugin.aRPvP.getPlayerPvPStatus(def.getName()) == false){
					att.sendMessage(ChatColor.DARK_RED + def.getName() + " hat kein PvP aktiviert!");
					def.sendMessage(ChatColor.DARK_RED + att.getName() + " hat versucht dich anzugreifen!");
					plugin.aRPvP.aRPvPUser.user.set("Spieler." + att.getName() + ".CoolDown", System.currentTimeMillis());
					return true;
				}
			}	
			setFlags(att, def);		
			return false;
		}
		return false;
		
	}
	
	private void setFlags(Player attacker, Player defender) {
		plugin.lastDamagerPlayer.put(defender, attacker);
		plugin.lastDamagerTimestamp.put(defender, System.currentTimeMillis());
	}
	
	public List<Player> getOnlinePlayerList() {
		Player[] players = plugin.getServer().getOnlinePlayers();
		List<Player> playerList = new ArrayList<Player>();
		for(int i = 0; i < players.length; i++) {
			playerList.add(players[i]);
		}
		return playerList;
	}

}