package org.community.ambience.StationarySounds.Heartbeat;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.community.ambience.Ambience;

public class soundPlayer implements Runnable {
	
	private Player player = null;
	private int radius;
	private Location location = null;
	private String sound = "";
	
	public soundPlayer(Ambience plugin, Player player, int radius, Location location, String sound) {
		this.player = player;
		this.radius = radius;
		this.sound = sound;
		this.location = location;
	}

	@SuppressWarnings("deprecation")
	public void run() {
		player.playSound(location, sound, radius/16, (float) 1.0);
	}

}
