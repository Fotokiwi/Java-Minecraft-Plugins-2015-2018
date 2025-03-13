package org.community.fourWays.Listener;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import org.community.fourWays.fourWays;


public class fourWaysSignChangeEvent implements Listener{

	private fourWays plugin;

	public fourWaysSignChangeEvent(fourWays plugin){
		this.plugin = plugin;
	}

	@EventHandler(priority=EventPriority.NORMAL)
	public void onSignChange(SignChangeEvent event) {
		
		Sign sign = (Sign) event.getBlock().getState();
		Player player = event.getPlayer();
		
		player.sendMessage(event.getLine(0));
		player.sendMessage(event.getLine(1));
		player.sendMessage(event.getLine(2));
		player.sendMessage(event.getLine(3));
		
		if(!event.getLine(0).equalsIgnoreCase("[Lieferung]")) {
			
			return;
			
		} else {
			
			if(event.getLine(2) == null || event.getLine(2).equalsIgnoreCase("")) {
				player.sendMessage("Der Empfänger ist kein gültiger Spieler.");
				return;
			}
			
			if((plugin.getPlayerByName(event.getLine(2)) == null) && (plugin.getOfflinePlayerByName(event.getLine(2)) == null)) {
				player.sendMessage("Der Empfänger ist kein gültiger Spieler.");
				return;
			} else {
				Location location = event.getBlock().getLocation().add(0, -1, 0);
				if(location.getBlock().getType() != Material.CHEST) {
					player.sendMessage("Unter dem Schild steht keine Truhe.");
					return;
				} else {
					event.setLine(1, player.getName());
					event.setLine(3, "{offen}");
					sign.update(true);
					return;
				}
			}
			
		}
		
	}
}
