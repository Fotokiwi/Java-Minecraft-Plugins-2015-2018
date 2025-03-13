package org.community.monsterspawner;

import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.community.monsterspawner.Cache.Cache;
import org.community.monsterspawner.Commands.SpawnerCommand;
import org.community.monsterspawner.Commands.ZoneCommand;
import org.community.monsterspawner.Config.MonsterSpawnerConfig;
import org.community.monsterspawner.Core.Core;
import org.community.monsterspawner.Listener.CreatureDeathListener;
import org.community.monsterspawner.Listener.SpawnListener;
import org.community.monsterspawner.Listener.EntityDamageByEntityListener;
import org.community.monsterspawner.Tasks.Heartbeat;
import org.community.monsterspawner.Zones.Zones;
import org.community.monsterspawner.monster.*;
import org.community.monsterspawner.spawner.Spawner;


public class MonsterSpawner extends JavaPlugin {

	public Logger log = Logger.getLogger("Minecraft");
	public String logprefix = "[Monsterspawner]";

	//MS Subklassen
	public Zones mSZones = null;
	public Spawner mSSpawner = null;
	public Core mSCore = null;
	public Cache mSCache = null;
	public Heartbeat mSHeartBeat = null;
	public MonsterChanger mSMonsterChange = null;
	public ZoneCommand mSZoneCommand = null;
	public SpawnerCommand mSSpawnerCommand = null;
	public MonsterSpawnerConfig mSConfig = null;

	//MS Monsterklassen
	public SkeletonChanger mSChangeSkeleton = null;
	public CreeperChanger mSChangeCreeper = null;
	public GiantChanger mSChangeGiant = null;
	public MagmaCubeChanger mSChangeMagmaCube = null;
	public PigZombieChanger mSChangePigZombie = null;
	public SilverfishChanger mSChangeSilverFish = null;
	public SlimeChanger mSChangeSlime = null;
	public SpiderChanger mSChangeSpider = null;
	public WitchChanger mSChangeWitch = null;
	public WolfChanger mSChangeWolf = null;
	public ZombieChanger mSChangeZombie = null;
	public BlazeChanger mSChangeBlaze = null;
	public CaveSpiderChanger mSChangeCaveSpider = null;
	public EnderDragonChanger mSChangeEnderDragon = null;
	public EndermanChanger mSChangeEnderman = null;
	public GhastChanger mSChangeGhast = null;
	public WitherChanger mSChangeWither = null;
	public ChickenChanger mSChangeChicken = null;

	//Logger Funktionen

	public void LogInfo(String Message) {

		log.info(logprefix + " " + Message);

	}

	public void LogDebug(String Message) {

		log.info(logprefix + " [DEBUG]: " + Message);

	}

	public void LogError(String Message) {

		log.log(Level.SEVERE, logprefix + " " + Message);

	}

	public void LogWarning(String Message) {

		log.log(Level.WARNING, logprefix + " " + Message);

	}

	@Override
	public void onEnable() {

		LogInfo("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
		if (load()) {			
			LogInfo("successfully initialized.");
		} else {
			LogWarning("error: initialization failure!");
		}
		LogInfo("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");

	}

	public boolean load() {

		mSHeartBeat = new Heartbeat(this);

		mSCore = new Core(this);

		mSCache = new Cache(this);

		mSMonsterChange = new MonsterChanger(this);

		mSZoneCommand = new ZoneCommand(this);

		mSSpawnerCommand = new SpawnerCommand(this);

		//Monster SubKlassen
		mSChangeSkeleton = new SkeletonChanger(this);
		mSChangeCreeper = new CreeperChanger(this);
		mSChangeGiant = new GiantChanger(this);
		mSChangeMagmaCube = new MagmaCubeChanger(this);
		mSChangePigZombie = new PigZombieChanger(this);
		mSChangeSilverFish = new SilverfishChanger(this);
		mSChangeSlime = new SlimeChanger(this);
		mSChangeSpider = new SpiderChanger(this);
		mSChangeWitch = new WitchChanger(this);
		mSChangeWolf = new WolfChanger(this);
		mSChangeZombie = new ZombieChanger(this);
		mSChangeBlaze = new BlazeChanger(this);
		mSChangeCaveSpider = new CaveSpiderChanger(this);
		mSChangeEnderDragon = new EnderDragonChanger(this);
		mSChangeEnderman = new EndermanChanger(this);
		mSChangeGhast = new GhastChanger(this);
		mSChangeWither = new WitherChanger(this);
		mSChangeChicken = new ChickenChanger(this);


		//make sure the timers are stopped for a reset
		mSCore.toggleHeartbeat(false);

		//Start timers
		mSCore.toggleHeartbeat(true);

		mSCache.loadZonesList();
		mSCache.loadSpawnerList();

		//Temp daten in die Hashmaps einpflegen um NPE beim Hochfahren zum umgehen
		mSCore.zoneChunkLoaded.put("temp", false);
		mSCore.spawnerLocs.put("temp", "temp");
		mSCore.zoneTime.put("temp", System.currentTimeMillis());
		mSCore.monsterKey.put(UUID.fromString("666a66dc-ae66-6c6d-b66c-6b66db6f6a6c"), "temp");
		mSCore.monsterLimit.put("temp",666);
		mSCore.spawnerState.put("temp", "temp");
		mSCore.spawnerTime.put("temp", System.currentTimeMillis());
		mSCore.spawnerResetCooldown.put("temp", System.currentTimeMillis());
		mSCore.playerCommand.put("temp", "temp");
		mSCore.playerSpawnerCommand.put("temp", "temp");


		getServer().getPluginManager().registerEvents(new SpawnListener(this), this);
		getServer().getPluginManager().registerEvents(new EntityDamageByEntityListener(this), this);
		getServer().getPluginManager().registerEvents(new CreatureDeathListener(this), this);
		//LogInfo("initialized: PlayerJoinEvent");

		return true;
	}


	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if(cmd.getName().equalsIgnoreCase("zone")){
			mSZoneCommand.getCommand(sender, cmd, commandLabel, args);
			return true;
		}
		if(cmd.getName().equalsIgnoreCase("spawner")){
			mSSpawnerCommand.getCommand(sender, cmd, commandLabel, args);
			return true;
		}

		else
			return false;
	}

	public boolean isAdmin(UUID uuid) {
		for (String s : mSConfig.getAdmins()) {
			if (uuid.equals(UUID.fromString(s)))
				return true;
		}
		return false;
	}

}