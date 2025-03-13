package org.community.newSettlers.Tasks;

import org.community.newSettlers.newSettlers;


public class newSettlersDaytimeToggle implements Runnable{
	
	private newSettlers plugin;
	
	private boolean isDay = false;
	
	public newSettlersDaytimeToggle(newSettlers plugin) {
		this.plugin = plugin;
	}

	public void run() {
		
		if(plugin.getServer().getWorld("Startinsel").getTime() >= 22000 || plugin.getServer().getWorld("Startinsel").getTime() <= 12000) {
			if(isDay == false) {
				plugin.nSCore.handleCommand(plugin, /*null,*/ "/gSwixsYAGlppvYPT"); // Toggle Day Script
				//plugin.getServer().broadcastMessage("Day Scripts activated.");
				isDay = true;
			}
		} else {
			if(isDay == true) {
				plugin.nSCore.handleCommand(plugin, /*null,*/ "/YgLRbxKtkiYeiRVB"); // Toggle Night Script
				//plugin.getServer().broadcastMessage("Night Scripts activated.");
				isDay = false;
			}
		}
		
	}

}