package org.community.CrazyLabs.MazeSettings;

import java.util.Arrays;
import java.util.List;

public class Theme {

	private static final List<String> THEMES = Arrays.asList("Stone", "Schmugglerversteck");
	
	public Theme() {
		
	}
	
	public static boolean themeExists(String theme) {
		if(THEMES.contains(theme))
			return true;
		
		return false;
	}
	
	public static String getThemes() {
		String themes = "";
		for(int i = 0; i < THEMES.size(); i++) {
			themes = themes + THEMES.get(i) + "  ";
		}
		return themes;
	}
	
}
