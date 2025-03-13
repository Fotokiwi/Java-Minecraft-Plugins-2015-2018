package org.community.ambience;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.community.ambience.AmbientObjects.Lagerfeuer.*;
import org.community.ambience.AmbientObjects.Listener.blockBreakEvent;
import org.community.ambience.AmbientObjects.Listener.playerInteractEvent;
import org.community.ambience.AmbientObjects.Zelte.kleinesZelt;
import org.community.ambience.AmbientObjects.Zelte.mittleresZeltlager;
import org.community.ambience.Diseases.ambienceDiseases;
import org.community.ambience.Diseases.Heartbeat.diseasesCore;
import org.community.ambience.Diseases.Heartbeat.diseasesHeartbeat;
import org.community.ambience.StationarySounds.SoundCommands;
import org.community.ambience.StationarySounds.ambienceSounds;
import org.community.ambience.StationarySounds.Heartbeat.*;
import org.community.ambience.TimeEngine.*;
import org.community.newSettlers.newSettlers;

public class Ambience extends JavaPlugin {

	public final static Logger log = Logger.getLogger("Minecraft");
	public static final String logprefix = "[Ambience 3.0.0]";

	public FileConfiguration config = null;
	public File configFile = null;
	public FileConfiguration diseases = null;
	public File diseasesFile = null;
	public FileConfiguration sounds = null;
	public File soundsFile = null;

	public ambienceDiseases ambienceDiseases = null;
	public diseasesCore ambienceDiseasesCore = null;
	public diseasesHeartbeat ambienceDiseasesHeartbeat = null;
	public ambienceSounds ambienceSounds = null;
	public ambienceHeartbeat ambienceHeartbeat = null;
	public ambienceCore ambienceCore = null;
	public soundPlayer ambienceSoundPlayer = null;
	public SoundCommands SoundCommands = null;
	public ambienceTimeEngine ambienceTimeEngine = null;
	public TimeCommands TimeCommands = null;

	public static boolean chairActivated = false;

	public kleinesLagerfeuer ambienceKleinesLagerfeuer = null;
	public kleinesZelt ambienceKleinesZelt = null;
	public mittleresZeltlager ambienceMittleresZeltlager = null;;
	public ArrayList<String> forbidBreak = new ArrayList<String>();
	public Map<String, String> resetOnShutdown = new HashMap<String, String>();

	public Commands commands = new Commands(this);
	public Cache cache = new Cache(this);

	public newSettlers newSettlersAPI = null;

	public void LogInfo(String Message) {
		log.info(logprefix + " " + Message);
	}

	public void LogError(String Message) {
		log.log(Level.SEVERE, logprefix + " " + Message);

	}

	public void LogWarning(String Message) {

		log.log(Level.WARNING, logprefix + " " + Message);

	}

	public void reloadConfig() {
		if (configFile == null) {
			configFile = new File(getDataFolder(), "/configs/config.yml");
		}
		config = YamlConfiguration.loadConfiguration(configFile);
	}

	public FileConfiguration getConfig() {
		if (config == null) {
			reloadConfig();
		}
		return config;
	}

	public void saveConfig() {
		if (config == null || configFile == null) {
			return;
		}
		try {
			config.save(configFile);
		} catch (IOException ex) {
			Logger.getLogger(JavaPlugin.class.getName())
					.log(Level.SEVERE, "Could not save config to " + configFile, ex);
		}
	}

	private Boolean loadConfig() {
		reloadConfig();
		getConfig();

		if (config.getString("Active.Diseases") == null)
			config.set("Active.Diseases", false);
		if (config.getString("Active.Sounds") == null)
			config.set("Active.Sounds", false);

		saveConfig();

		return true;
	}

	public void onEnable() {

		loadConfig();

		getServer().getPluginManager().registerEvents(new blockBreakEvent(this), this);
		LogInfo("initialized: BlockBreakEvent");
		getServer().getPluginManager().registerEvents(new playerInteractEvent(this), this);
		LogInfo("initialized: playerInteractEvent");

		cache.loadAllBlueprints();

		newSettlersAPI = (newSettlers) Bukkit.getServer().getPluginManager().getPlugin("NewSettlers");

		ambienceKleinesLagerfeuer = new kleinesLagerfeuer(this);
		ambienceKleinesZelt = new kleinesZelt(this);
		ambienceMittleresZeltlager = new mittleresZeltlager(this);
		// ambienceSounds = new sounds(this);

		if (config.getBoolean("Active.Diseases")) {
			ambienceDiseases = new ambienceDiseases(this);
			ambienceDiseasesHeartbeat = new diseasesHeartbeat(this);
			ambienceDiseasesCore = new diseasesCore(this);
			ambienceDiseasesCore.init();
		}
		if (config.getBoolean("Active.Sounds")) {
			ambienceSounds = new ambienceSounds(this);
			ambienceHeartbeat = new ambienceHeartbeat(this);
			ambienceCore = new ambienceCore(this);
			SoundCommands = new SoundCommands(this);
			// ambienceSoundPlayer = new soundPlayer();
			ambienceCore.init();
		}
		if (config.getBoolean("Active.TimeEngine")) {
			ambienceTimeEngine = new ambienceTimeEngine(this);
			ambienceTimeEngine.init();
			TimeCommands = new TimeCommands(this);
		}

		LogInfo("Ambience enabled");
	}

	@SuppressWarnings("deprecation")
	public void onDisable() {
		cache.saveAllBlueprints();
		for (Entry<String, String> entry : resetOnShutdown.entrySet()) {
			if (entry.getValue() != null) {
				String[] clearedLoc = entry.getKey().split(",");
				Location loc = new Location(getServer().getWorld(clearedLoc[0]), Integer.parseInt(clearedLoc[1]),
						Integer.parseInt(clearedLoc[2]), Integer.parseInt(clearedLoc[3]));

				String[] clearedMat = entry.getValue().split(",");
				loc.getBlock().setType(Material.getMaterial(clearedMat[0]));
				loc.getBlock().setData(Byte.parseByte(clearedMat[1]));

			}
		}
		LogInfo("Ambience Disabled");
		if (config.getBoolean("Active.TimeEngine")) {
			ambienceTimeEngine.init();
		}
	}

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {

		if (cmd.getName().equalsIgnoreCase("ambience") || cmd.getName().equalsIgnoreCase("ambiente")) {
			commands.getCommand(sender, cmd, commandLabel, args);
			return true;
		} else if (cmd.getName().equalsIgnoreCase("sound")) {
			SoundCommands.getCommand(sender, cmd, commandLabel, args);
			return true;
		} else if (cmd.getName().equalsIgnoreCase("zeit")) {
			TimeCommands.getCommand(sender, cmd, commandLabel, args);
			return true;
		}  else
			return false;

	}
}