package org.community.fourWays.Listener;

import java.io.File;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.community.fourWays.fourWays;
import org.community.fourWays.User.User;

public class fourWaysPlayerJoinEvent implements Listener {
	
	private final fourWays plugin;

	public fourWaysPlayerJoinEvent(fourWays plugin)
	{
		this.plugin = plugin;
	}
	@EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
	public void onPlayerJoin(PlayerJoinEvent event) {

		Player player = event.getPlayer();
		UUID id = player.getUniqueId();
		
		if(plugin.fWCore.lastLogin.get(id) == null) {
			plugin.fWCore.lastLogin.put(id, System.currentTimeMillis());
			event.setJoinMessage(ChatColor.LIGHT_PURPLE + "[Server] " + player.getName() + " ist dem Spiel beigetreten.");
		} else if(System.currentTimeMillis() >= (plugin.fWCore.lastLogin.get(id) + 300000)) {
			plugin.fWCore.lastLogin.put(id, System.currentTimeMillis());
			event.setJoinMessage(ChatColor.LIGHT_PURPLE + "[Server] " + player.getName() + " ist dem Spiel beigetreten.");
		} else {
			event.setJoinMessage("");
		}
		
		if(plugin.fWUsers.getPlayerInfo(player) == null) {
			File tempUser = new File(plugin.getDataFolder(), "/user/" + player.getUniqueId() + ".yml");
			if(!tempUser.exists()) {
				User user = new User(plugin, player, true);
				plugin.fWCore.addUserList(player.getName(), user);
				plugin.LogInfo("[DEBUG]: User-Datei angelegt. (" + player.getUniqueId() + ")");
				//player.sendMessage("[DEBUG]: User-Datei angelegt.");
			} else {
				User user = new User(plugin, id);
				plugin.fWCore.addUserList(player.getName(), user);
				plugin.LogInfo("User " + user.getName() + ": Level " + user.getLevel() + ", " + user.getExp() + " exp");
			}			
		}
		
		return;
		
	}
	
}