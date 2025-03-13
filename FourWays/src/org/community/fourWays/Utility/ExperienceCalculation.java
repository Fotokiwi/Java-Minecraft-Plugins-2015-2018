package org.community.fourWays.Utility;

import org.community.fourWays.fourWays;


public class ExperienceCalculation {

	@SuppressWarnings("unused")
	private fourWays plugin;
	private int[] levelExp = {0, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 20, 23, 26, 29, 32, 35, 38, 41, 44, 47, 50, 53, 56, 59, 62, 69, 76, 83, 90, 97, 104, 111, 118, 125, 132, 139, 146, 153, 160, 167, 174, 181, 188, 195};

	public ExperienceCalculation(fourWays plugin) {
		this.plugin = plugin;
	}
	
	public int getExperienceFromLevel(int level) {
		int exp = 0;
		for(int i = 1; i <= level; i++) {
			if(level > 16) {
				exp += levelExp[i] * (1.15 + ((level-16) / 100));
			} else {
				exp += levelExp[i];
			}			
		}
		return exp;
	}
}