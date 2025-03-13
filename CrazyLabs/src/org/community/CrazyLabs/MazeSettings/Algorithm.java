package org.community.CrazyLabs.MazeSettings;

import java.util.Arrays;
import java.util.List;

public class Algorithm {
	
	private static final List<String> ALGORITHMS = Arrays.asList("RecursiveDivision", "Prims", "DepthFirst");
	
	public Algorithm() {
		
	}
	
	public static boolean algorithmExists(String algorithm) {
		if(ALGORITHMS.contains(algorithm))
			return true;
		
		return false;
	}
	
	public static String getAlgorithms() {
		String themes = "";
		for(int i = 0; i < ALGORITHMS.size(); i++) {
			themes = themes + ALGORITHMS.get(i) + "  ";
		}
		return themes;
	}

}
