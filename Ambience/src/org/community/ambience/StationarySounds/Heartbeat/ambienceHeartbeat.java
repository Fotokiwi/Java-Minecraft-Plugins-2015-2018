package org.community.ambience.StationarySounds.Heartbeat;


import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.community.ambience.Ambience;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashMap;
import java.util.Map;


public class ambienceHeartbeat implements Runnable {
	
	private Ambience plugin;
	
	public Map<String, Long> soundCache = new HashMap<String, Long>();
	
	public ambienceHeartbeat(Ambience plugin) {
		this.plugin = plugin;
	}

	@Override
	public void run() {
		
		ConfigurationSection section = plugin.sounds.getConfigurationSection("Sound");
		Set<String> keyList = section.getKeys(false);
		String[] getList = keyList.toArray(new String[0]);
		for (int i = 0; i < keyList.size(); i++) {
			Location location = getLocation(plugin.sounds.getString("Sound." + getList[i] + ".Location"));
			World world = getWorld(plugin.sounds.getString("Sound." + getList[i] + ".Location"));
			int radius = plugin.sounds.getInt("Sound." + getList[i] + ".Radius");
			List<Player> player = getPlayersInRange(world, location, radius);
			
			//if(plugin.sounds.getString("Sound." + getList[i] + ".TriggerType").equalsIgnoreCase("Range")) {
				for(int p = 0; p < player.size(); p++) {
					if(soundCache.containsKey(player.get(p).getName() + "-" + plugin.sounds.getString("Sound." + getList[i] + ".File"))) {
						if(System.currentTimeMillis() >= soundCache.get(player.get(p).getName() + "-" + plugin.sounds.getString("Sound." + getList[i] + ".File")) + (1000*plugin.sounds.getInt("Sound." + getList[i] + ".CooldownInSeconds"))) {
							if(plugin.sounds.getInt("Sound." + getList[i] + ".Repeat") > 1) {
								
								for(int r = 1; r <= plugin.sounds.getInt("Sound." + getList[i] + ".Repeat"); r++) {
									plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new soundPlayer(plugin, player.get(p), radius, location, plugin.sounds.getString("Sound." + getList[i] + ".File")), r * plugin.sounds.getInt("Sound." + getList[i] + ".RepeatOptions.DelayInSeconds") * 20L);
								}	
								soundCache.put(player.get(p).getName() + "-" + plugin.sounds.getString("Sound." + getList[i] + ".File"), System.currentTimeMillis());
								
							} else {
								//player.get(p).playSound(location, plugin.sounds.getString("Sound." + getList[i] + ".File"), radius/16, (float) 1.0);
								plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new soundPlayer(plugin, player.get(p), radius, location, plugin.sounds.getString("Sound." + getList[i] + ".File")), 20L);
								soundCache.put(player.get(p).getName() + "-" + plugin.sounds.getString("Sound." + getList[i] + ".File"), System.currentTimeMillis());
							}
							
						}					
					} else {
						if(plugin.sounds.getInt("Sound." + getList[i] + ".Repeat") > 1) {
							
							for(int r = 1; r <= plugin.sounds.getInt("Sound." + getList[i] + ".Repeat"); r++) {
								plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new soundPlayer(plugin, player.get(p), radius, location, plugin.sounds.getString("Sound." + getList[i] + ".File")), r * plugin.sounds.getInt("Sound." + getList[i] + ".RepeatOptions.DelayInSeconds") * 20L);
							}
							soundCache.put(player.get(p).getName() + "-" + plugin.sounds.getString("Sound." + getList[i] + ".File"), System.currentTimeMillis());
							
						} else {
							//player.get(p).playSound(location, plugin.sounds.getString("Sound." + getList[i] + ".File"), radius/16, (float) 1.0);
							plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new soundPlayer(plugin, player.get(p), radius, location, plugin.sounds.getString("Sound." + getList[i] + ".File")), 20L);
							soundCache.put(player.get(p).getName() + "-" + plugin.sounds.getString("Sound." + getList[i] + ".File"), System.currentTimeMillis());
						}
					}				
				}
			//}
			
			
			/*plugin.getServer().broadcastMessage("~~~~~~~~~~");
			plugin.getServer().broadcastMessage("Name: " + getList[i]);
			plugin.getServer().broadcastMessage("Location: " + plugin.sounds.getString("Sound." + getList[i] + ".Location"));
			plugin.getServer().broadcastMessage("Radius: " + plugin.sounds.getString("Sound." + getList[i] + ".Radius"));
			plugin.getServer().broadcastMessage("File: " + plugin.sounds.getString("Sound." + getList[i] + ".File"));
			plugin.getServer().broadcastMessage("Repeat: " + plugin.sounds.getString("Sound." + getList[i] + ".Repeat"));
			plugin.getServer().broadcastMessage("CooldownInSeconds: " + plugin.sounds.getString("Sound." + getList[i] + ".CooldownInSeconds"));
			plugin.getServer().broadcastMessage("TriggerType: " + plugin.sounds.getString("Sound." + getList[i] + ".TriggerType"));
			plugin.getServer().broadcastMessage("~~~~~~~~~~");*/
        }
		
	}
	
	private World getWorld(String location) {
		String[] clearLoc = location.split(",");
		return plugin.getServer().getWorld(clearLoc[0]);
	}
	
	private Location getLocation(String location) {
		String[] clearLoc = location.split(",");
		World world = getWorld(location);
		return new Location(world, Integer.parseInt(clearLoc[1]), Integer.parseInt(clearLoc[2]), Integer.parseInt(clearLoc[3]));
	}
	
	@SuppressWarnings("deprecation")
	private Player[] getOnlinePlayer() {		
		return plugin.getServer().getOnlinePlayers();		
	}
	
	private List<Player> getPlayersInRange(World world, Location location, int radius) {
		List<Player> playerList = new ArrayList<Player>();
		for(Player player : getOnlinePlayer()) {
			if(player.getWorld() == world) {
				if(location.distance(player.getLocation()) <= radius)
					playerList.add(player);
			}
		}
		return playerList;
	}

}
