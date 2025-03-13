package org.community.ambience.Diseases.Temperature;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.community.ambience.Ambience;

public class Temperature {
	
	Ambience plugin = null;
	boolean debug = false;
	
	public Temperature(Ambience plugin) {
		this.plugin = plugin;
	}
	
	Map<String, Double> globalHabit = new HashMap<String, Double>();
	Map<String, Double> globalBody = new HashMap<String, Double>();
	
	public double calculateTemperature(Player player) {
		
		if(globalHabit.get(player.getName()) == null) {
			globalHabit.put(player.getName(), 25.0);
		}
		if(globalBody.get(player.getName()) == null) {
			globalBody.put(player.getName(), 37.0);
		}
		
		long start = System.currentTimeMillis();
		
		Location location = player.getLocation();
		String biome = location.getBlock().getBiome().name();
		boolean isLandMode;
		double temperature = 0.0;
		
		if(location.getBlock().getType() == Material.WATER || location.getBlock().getType() == Material.STATIONARY_WATER) {
			debugOutput("LandMode: false");
			isLandMode = false;
		} else {
			debugOutput("LandMode: true");
			isLandMode = true;
		}
		
		if(isLandMode == true) {
			temperature = (-1 * Math.cos(getDayTime() / 3.75) * getBiomeFluctuation(biome)) + getBiomeBaseTemp(biome) + getSunlightFactor(location, biome) - getBiomeRainTemp(location, biome) + getHeatSourceTemp(location);
		} else {
			temperature = getBiomeWaterTemp(biome) - Math.min((15 - location.getBlock().getLightLevel()), 4);
		}
		
		double habit = getHabitTemperature(temperature, player.getName());
		double body = getBodyTemperature(temperature, habit, player.getName());
		
		long end = System.currentTimeMillis();
		long sum = end - start;
		
		//player.sendMessage("Temperatur: " + temperature + " (" + sum + "ms)");
		//player.sendMessage("Habit: " + habit);
		//player.sendMessage("Body: " + body);
		return temperature;
	}
	
	
	
	private void debugOutput(String output) {
		if(debug)
			plugin.LogInfo("[Temperature-Debug] + " + output);
	}
	
	private int getDayTime() {
		long time = plugin.getServer().getWorld("Startinsel").getTime();		
		int hours = (int) ((Math.floor(time / 1000.0) + 8) % 24); // '8' is the offset
		debugOutput("Uhrzeit: " + hours);
		return hours;
	}
	
	private double getBiomeFluctuation(String biome) {
		double value = plugin.diseases.getDouble("Config.Biome.plains.Schwankung", 10);
		debugOutput("Fluctuation: " + value);
		return value;
	}
	
	private double getBiomeBaseTemp(String biome) {
		double value = plugin.diseases.getDouble("Config.Biome.plains.Basistemperatur", 15);
		debugOutput("BaseTemp: " + value);
		return value;
	}
	
	private double getBiomeWaterTemp(String biome) {
		double value = plugin.diseases.getDouble("Config.Biome.plains.Wassertemperatur", 10);
		debugOutput("WaterTemp: " + value);
		return value;
	}
	
	private double getBiomeRainTemp(Location location, String biome) {
		double value = plugin.diseases.getDouble("Config.Biome.plains.Niederschlag", -5);
		if(location.getWorld().isThundering() == false)
			value = 0;
		debugOutput("RainTemp: " + value);
		return value;
	}
	
	private double getBiomeSunLevel(String biome) {
		double value = plugin.diseases.getDouble("Config.Biome.plains.Sonnenlicht", 1.0);
		debugOutput("SunLevel: " + value);
		return value;
	}
	
	private double getRealSunlight(int time, int lightFromSky) {
		//double value = Math.min((((-1 * Math.cos(time / 3.75)) + 0.75)/1.75) * lightFromSky, 0);
		double value = (((-1 * Math.cos(time / 3.75)) + 0.75) / 1.75) * lightFromSky;
		value = Math.max(value, 0);
		debugOutput("RealSunlight: " + value);
		return value;
	}
	
	private double getSunlightFactor(Location location, String biome) {
		double value = (getRealSunlight(getDayTime(), location.getBlock().getLightFromSky()) * getBiomeSunLevel(biome));
		debugOutput("SunlightFactor: " + value);
		return value;
	}
	
	private double getHeatSourceTemp(Location location) {
		double temperature = 0.0;
		Map<Location, Block> blocks = plugin.newSettlersAPI.nSBFU.getBlocksInRange(location, 3, 3, 3);
		for(Map.Entry<Location, Block> e : blocks.entrySet()){			
			double tempValue = 0.0;
			if(e.getValue().getType() == Material.FIRE || e.getValue().getType() == Material.LAVA) {
				tempValue = ( 100 / (location.distance(e.getKey()) + 1) );
				if (tempValue >= temperature)
					temperature = tempValue;
			}
		}
		debugOutput("HeatSourceTemp: " + temperature);
		return temperature;
	}
	
	private double getBodyTemperature(double temperature, double habit, String player) {
		double playerTemperature = globalBody.get(player);
		double habTemp = habit - temperature;
		if(habTemp < 0) {
			habTemp = habTemp * -1;
		} 
		if(temperature - habit - getBodyTolerance(player) >= 0) {
			playerTemperature = playerTemperature + 0.005*((habTemp));
		} else {
			playerTemperature = playerTemperature - 0.005*((habTemp));
		}
		globalBody.put(player, playerTemperature);
		return playerTemperature;
	}
	
	private double getHabitTemperature(double temperature, String player) {
		double playerHabit = globalHabit.get(player);
		double habTemp = playerHabit - temperature;
		if(habTemp < 0) {
			habTemp = habTemp * -1;
		} 
		if(temperature - playerHabit - getBodyTolerance(player) >= 0) {
			playerHabit = playerHabit + 0.001*((habTemp));
		} else {
			playerHabit = playerHabit - 0.001*((habTemp));
		}
		globalHabit.put(player, playerHabit);
		return playerHabit;
	}
	
	private double getBodyTolerance(String player) {
		return 2;
	}

}
