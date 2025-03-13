package org.community.ambience.Diseases.Listener;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.community.ambience.Ambience;

public class ambienceDiseasesPlayerMoveEvent implements Listener {
	
	private final Ambience plugin;
	
	public Location location = null;

	public ambienceDiseasesPlayerMoveEvent(Ambience plugin)
	{
		this.plugin = plugin;
	}
	
	@EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
	public void onPlayerMove(PlayerMoveEvent event) {

		Player player = event.getPlayer();
		Location pLocation = player.getLocation();
		String biome = pLocation.getBlock().getBiome().name();
		double temperature = 0.0;
		
		String biomeName = biome.toLowerCase().replace(" ", "_");
		String[] shortName = biomeName.split("_");
		if(shortName[0].equalsIgnoreCase("small"))
			shortName[0] = "extreme";
		
		if(location != null && location.getWorld() != pLocation.getWorld())
			location = pLocation;
		
		if(location == null) {
			location = pLocation;
			temperature = plugin.diseases.getDouble("Config.Temperature.Biome." + shortName[0]);
			event.getPlayer().sendMessage(ChatColor.GREEN + "" + temperature + "°C");
		} else if (location.distance(pLocation) <= 5) {
			
		} else {
			location = pLocation;
			temperature = plugin.diseases.getDouble("Config.Temperature.Biome." + shortName[0]);
			event.getPlayer().sendMessage(ChatColor.GREEN + "" + temperature + "°C");
		}
		
		return;
		
	}
	
}