package org.community.ancientRelics.Tasks;

import org.community.ancientRelics.ancientRelics;


public class ancientRelicsConfigSaver implements Runnable{
	
	private ancientRelics plugin;
	
	public ancientRelicsConfigSaver(ancientRelics ancientRelics) {
		this.plugin = ancientRelics;
	}

	public void run() {
		
		plugin.aRConfig.saveConfig();
		plugin.LogDebug("config.yml saved.");
		
		plugin.aRPvP.aRPvPUser.saveUserConfig();
		plugin.LogDebug("(PvP) user.yml saved.");
		
		plugin.aRGroups.aRGroupsUser.saveUserConfig();
		plugin.LogDebug("(Groups) user.yml saved.");
		
	}

}