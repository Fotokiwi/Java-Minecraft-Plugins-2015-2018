package org.community.fourWays.Tasks;

import org.community.fourWays.fourWays;


public class fourWaysConfigSaver implements Runnable{
	
	private fourWays plugin;
	
	public fourWaysConfigSaver(fourWays plugin) {
		this.plugin = plugin;
	}

	public void run() {
		
		plugin.fWConfig.saveConfig();
		plugin.LogDebug("config.yml saved.");
		
		plugin.fWCraftingCooldown.saveConfig();
		plugin.LogDebug("craftingCooldown.yml saved.");
		
	}

}