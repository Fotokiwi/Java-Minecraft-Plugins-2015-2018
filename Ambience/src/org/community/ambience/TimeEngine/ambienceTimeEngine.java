package org.community.ambience.TimeEngine;

import java.util.Calendar;

import org.bukkit.World;
import org.community.ambience.Ambience;

public class ambienceTimeEngine implements Runnable{
	
	private Ambience plugin;
	
	private int ambienceTimeEngineTask = -1;
	
	private final static int moonphases = 7;
	
	public ambienceTimeEngine(Ambience plugin) {
		this.plugin = plugin;
	}
	
	public void init() {
		ambienceTimeEngineTask = plugin.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, plugin.ambienceTimeEngine, 100L, 20L);

		if (ambienceTimeEngineTask == -1) {
			plugin.LogError("error: TimeEngine initialization failed!");
		} else {
			plugin.LogInfo("initialized: TimeEngine");
		}
	}
	
	public void stop() {
		if(ambienceTimeEngineTask >= 0){
			plugin.getServer().getScheduler().cancelTask(ambienceTimeEngineTask);
			plugin.LogInfo("stopped: TimeEngine");
		} else {
			plugin.LogError("error: TimeEngine Task not found!");
		}
	}

	@Override
	public void run() {

		//long time = System.currentTimeMillis();
		Calendar now = Calendar.getInstance();

		int minute = now.get(Calendar.MINUTE);
		int second = now.get(Calendar.SECOND);

		int tick = (minute * 60) + second;

		changeTime(plugin.getServer().getWorld("Startinsel"), tick);
		changeTime(plugin.getServer().getWorld("Utopia"), tick);

		
	}

	private void changeTime(World world, int tick) {
		
		world.setTime((long) Math.floor(tick * getTickMultiplier(tick)));	
		//world.setTime((long) Math.floor(tick * 6.66));	
		
		for(int i = 0; i < moonphases; i++)
			world.setTime(world.getTime() - 1);
		
		//Because the time was changed slightly in the above code, keeping track of the moonphase.
		//We have to change it back
		world.setTime(world.getTime() + moonphases);
		
	}
	
	private double getTickMultiplier(int tick) {
		if(tick >= 0 && tick <= 25) {
			return 5.66;
		}
		if(tick >= 26 && tick <= 50) {
			return 5.61;
		}
		if(tick >= 51 && tick <= 75) {
			return 5.56;
		}
		if(tick >= 76 && tick <= 100) {
			return 5.51;
		}
		if(tick >= 101 && tick <= 125) {
			return 5.46;
		}
		if(tick >= 126 && tick <= 150) {
			return 5.41;
		}
		if(tick >= 151 && tick <= 175) {
			return 5.36;
		}
		if(tick >= 176 && tick <= 200) {
			return 5.31;
		}
		if(tick >= 201 && tick <= 225) {
			return 5.26;
		}
		if(tick >= 226 && tick <= 250) {
			return 5.21;
		}
		if(tick >= 251 && tick <= 275) {
			return 5.16;
		}
		if(tick >= 276 && tick <= 300) {
			return 5.11;
		}
		if(tick >= 301 && tick <= 325) {
			return 5.06;
		}
		if(tick >= 326 && tick <= 350) {
			return 5.01;
		}
		if(tick >= 351 && tick <= 375) {
			return 4.96;
		}
		if(tick >= 376 && tick <= 400) {
			return 4.91;
		}
		if(tick >= 401 && tick <= 425) {
			return 4.86;
		}
		if(tick >= 426 && tick <= 450) {
			return 4.81;
		}
		if(tick >= 451 && tick <= 475) {
			return 4.76;
		}
		if(tick >= 476 && tick <= 500) {
			return 4.71;
		}
		if(tick >= 501 && tick <= 2100) {
			return 4.66;
		}
		if(tick >= 2101 && tick <= 2200) {
			return 4.91;
		}
		if(tick >= 2201 && tick <= 2300) {
			return 5.16;
		}
		if(tick >= 2301 && tick <= 2400) {
			return 5.41;
		}
		if(tick >= 2401 && tick <= 2500) {
			return 5.66;
		}
		if(tick >= 2501 && tick <= 2600) {
			return 5.91;
		}
		if(tick >= 2601 && tick <= 2700) {
			return 6.16;
		}
		if(tick >= 2701 && tick <= 2800) {
			return 6.41;
		}
		if(tick >= 2801 && tick <= 3000) {
			return 6.66;
		}
		if(tick >= 3001 && tick <= 3025) {
			return 6.58;
		}
		if(tick >= 3026 && tick <= 3050) {
			return 6.52;
		}
		if(tick >= 3051 && tick <= 3075) {
			return 6.46;
		}
		if(tick >= 3101 && tick <= 3200) {
			return 6.41;
		}
		if(tick >= 3201 && tick <= 3225) {
			return 6.36;
		}
		if(tick >= 3226 && tick <= 3250) {
			return 6.31;
		}
		if(tick >= 3251 && tick <= 3275) {
			return 6.26;
		}
		if(tick >= 3276 && tick <= 3300) {
			return 6.21;
		}
		if(tick >= 3301 && tick <= 3325) {
			return 6.16;
		}
		if(tick >= 3326 && tick <= 3350) {
			return 6.11;
		}
		if(tick >= 3351 && tick <= 3375) {
			return 6.06;
		}
		if(tick >= 3376 && tick <= 3400) {
			return 6.01;
		}
		if(tick >= 3401 && tick <= 3425) {
			return 5.96;
		}
		if(tick >= 3426 && tick <= 3500) {
			return 5.91;
		}
		if(tick >= 3501 && tick <= 3525) {
			return 5.86;
		}
		if(tick >= 3526 && tick <= 3550) {
			return 5.81;
		}
		if(tick >= 3551 && tick <= 3575) {
			return 5.76;
		}
		if(tick >= 3576 && tick <= 3600) {
			return 5.71;
		}
		if(tick >= 3601 && tick <= 3600) {
			return 5.66;
		}
		return 6.66;
	}

}
