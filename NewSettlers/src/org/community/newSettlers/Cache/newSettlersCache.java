package org.community.newSettlers.Cache;

import java.io.File;

import org.community.newSettlers.newSettlers;
import org.community.newSettlers.Town.Town;

public class newSettlersCache {
	
	private newSettlers plugin;
	
	public newSettlersCache(newSettlers plugin) {
		this.plugin = plugin;
	}
	
	public void loadTownList() {
		File dir = new File(plugin.getDataFolder() + "/towns/");
		String fileName;
		String tempName;
		File[] files = dir.listFiles();
		if (files != null) {
			for (int i = 0; i < files.length; i++) {
				if (files[i].isDirectory()) {
				}
				else {
					fileName = files[i].getName();
					tempName = fileName.substring(0, fileName.indexOf('.'));
					Town town = new Town(plugin, tempName);
					plugin.LogInfo("initialized: Town (" + town.getName() + ")");
				}
			}
		}
	}
	
	public void saveTownList() {
		Town tempTown;
		for(int i = 0; i < plugin.nSCore.getCompleteTownList().size(); i++) {
			tempTown = plugin.nSCore.getTownList(i);
			//tempGroup.saveToFile();
			plugin.LogDebug("saving town " + tempTown.getName());
		}
	}
			
}