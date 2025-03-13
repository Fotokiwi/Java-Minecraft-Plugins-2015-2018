package org.community.ambience.Diseases;

import org.bukkit.Location;
import org.community.ambience.Ambience;
import org.community.ambience.Diseases.Config.ambienceConfig;
import org.community.ambience.Diseases.Temperature.Temperature;

public class ambienceDiseases {
	
	public Location location = null;
	public Temperature diseasesTemperature = null;

	public ambienceDiseases(Ambience plugin)
	{
		new ambienceConfig(plugin);
		diseasesTemperature = new Temperature(plugin);
		
		//plugin.getServer().getPluginManager().registerEvents(new ambienceDiseasesPlayerMoveEvent(plugin), plugin);
		//plugin.LogInfo("initialized: PlayerMoveEvent (Diseases)");
	}
	
}
