package org.community.fourWays.User;

import org.community.fourWays.fourWays;

public class fourWaysLevels {
		
	private final fourWays plugin;
	 	 
	public fourWaysLevels(fourWays plugin)
	{
		this.plugin = plugin;
	}

	/**
	 * Liefert die Erfahrungspunkte für Level X zurück
	 * @param int - Welches Level soll berechnet werden
	 * @return int - Die errechneten Exp
	 */
	public int calculateLevelExp(int level) {
		
		if(level < 1)
			level = 1;
		
		int experience = 0;
		
		if(level < 20){
			experience = ((level * plugin.config.getInt("Config.Levels.LevelBaseLow")) + ((level - 1) * plugin.config.getInt("Config.Levels.LevelBaseLow")));
		} else {
			experience = ((level * plugin.config.getInt("Config.Levels.LevelBaseHigh")) + ((level - 1) * plugin.config.getInt("Config.Levels.LevelBaseHigh")));
		}
		
		return experience;
		
	}
	
}