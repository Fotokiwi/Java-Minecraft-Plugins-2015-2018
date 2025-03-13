package org.community.ancientRelics.Cache;

import java.io.File;
import org.community.ancientRelics.ancientRelics;
import org.community.ancientRelics.Groups.Classes.Groups;

public class ancientRelicsCacheManagement {
	
	private ancientRelics plugin;
	
	public ancientRelicsCacheManagement(ancientRelics ancientRelics) {
		this.plugin = ancientRelics;
	}
	
	public void loadGroupsList() {
		File dir = new File(plugin.getDataFolder() + "/groups/groupConfig/");
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
					Groups group = new Groups(plugin, tempName, true);
					plugin.LogInfo("initialized: Group (" + group.getGroupHash() + ": " + group.getMembers() + ")");
				}
			}
		}
	}
	
	public void saveGroupsList() {
		Groups tempGroup;
		for(int i = 0; i < plugin.aRCore.getCompleteGroupsList().size(); i++) {
			tempGroup = plugin.aRCore.getGroupsList(i);
			//tempGroup.saveToFile();
			plugin.LogDebug("saving group " + tempGroup.getGroupName());
		}
	}
			
}