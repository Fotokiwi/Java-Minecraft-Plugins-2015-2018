package org.community.CrazyLabs.MazeSettings;

import java.util.Arrays;
import java.util.List;

public class ModuleSize {
	
	private static final List<String> MODULESIZES = Arrays.asList("Stone:7", "Schmugglerversteck:9");
	
	public ModuleSize() {
		
	}
	
	public static boolean isSizeAllowed(String theme, int size) {
		if(MODULESIZES.contains(theme + ":" + size))
			return true;
		
		return false;
	}
	
	public static String getModuleSizes(String theme) {
		String themes = "";
		for(int i = 0; i < MODULESIZES.size(); i++) {
			if(MODULESIZES.get(i).contains(theme)) {
				themes = themes + MODULESIZES.get(i).split(":")[1] + "  ";
			}			
		}
		return themes;
	}

}
