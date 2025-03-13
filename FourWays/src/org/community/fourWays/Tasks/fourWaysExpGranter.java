package org.community.fourWays.Tasks;

import org.community.fourWays.fourWays;


public class fourWaysExpGranter implements Runnable{
	
	private fourWays plugin;
	
	public fourWaysExpGranter(fourWays plugin) {
		this.plugin = plugin;
	}

	public void run() {
		
		plugin.fWUsers.grantPlaytimeExp();
		//plugin.LogDebug("Playtime exp granted.");
		
	}

}