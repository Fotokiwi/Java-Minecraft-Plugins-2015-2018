package org.community.LootChest.Listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.community.LootChest.LootChest;

public class OnPlayerJoin implements Listener {
	private LootChest plugin;

	public OnPlayerJoin(LootChest plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onPlayerLogin(PlayerJoinEvent e) {		
		if(!plugin.cache.getUserDataChests().containsKey(e.getPlayer().getUniqueId())){
			plugin.cache.loadUserData(e.getPlayer().getUniqueId());
		}
	}
}
