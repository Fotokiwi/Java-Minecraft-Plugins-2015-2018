package org.community.monsterspawner.Core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.community.monsterspawner.MonsterSpawner;
import org.community.monsterspawner.Zones.Zones;
import org.community.monsterspawner.spawner.Spawner;

public class Core {
	
	private MonsterSpawner plugin;
	
	private int monsterSpawnerHeartbeatTask = -1;
	
	public List<Zones> zoneList = new ArrayList<Zones>();
	public List<Spawner> spawnerList = new ArrayList<Spawner>();

	/** Speichert den Ort des Spawners und dessen Namen zur Spawnzuordnung ab*/
	public Map<String, String>spawnerLocs = new HashMap<String,String>();
	/** Speichert die Zone und die Aktivierungszeit dieser*/
	public Map<String, Long> zoneTime = new HashMap<String, Long>();
	/** Speichert die aktuellen Monster zum zugeh�rigen Spawner*/
	public Map<UUID, String> monsterKey = new HashMap<UUID, String>();	
	/** Z�hlt die gespawnten Monster hoch*/
	public Map<String, Integer> monsterLimit = new HashMap<String, Integer>();
	/** Zeigt ob ein Spawner gerade active oder inactive ist*/
	public Map<String, String> spawnerState = new HashMap<String, String>();
	/** Speichert den letzten erfolgreichen Spawnzeitpunkt ab*/
	public Map<String, Long> spawnerTime = new HashMap<String, Long>();
	/** Speichert den Beginn des Cooldowns ab*/
	public Map<String, Long> spawnerResetCooldown = new HashMap<String, Long>();
	/** Speichert ob die Chunks der Zone geladen wurden oder nicht*/
	public Map<String, Boolean>zoneChunkLoaded = new HashMap<String, Boolean>();
	/** Command Hashmap, Speichert Spieler und Zonenname f�r den Edit*/
	public Map<String, String>playerCommand = new HashMap<String, String>();
	/** Command Hashmap, Speichert Spieler und Spawnername f�r den Edit*/
	public Map<String, String>playerSpawnerCommand = new HashMap<String, String>();
	
	/** Monster Checklisten - Eingabepr�fung*/
	public String[] monster = {"BLAZE","CAVE_SPIDER","CREEPER","ENDER_DRAGON","ENDERMAN","GHAST","GIANT","MAGMA_CUBE","PIGZOMBIE","SILVERFISH","SKELETON","SLIME","SPIDER","WITCH","WITHER","WOLF","ZOMBIE", "CHICKEN"};
	public List<String> monsterlist = Arrays.asList(monster);
	/** R�stung Checklisten - Eingabepr�fung*/
	public String[] helmets = {"LEATHER_HELMET","CHAINMAIL_HELMET","IRON_HELMET","GOLD_HELMET","DIAMOND_HELMET"};
	public List<String> helmetslist = Arrays.asList(helmets);
	public String[] shoes = {"LEATHER_BOOTS","IRON_BOOTS","GOLD_BOOTS","DIAMOND_BOOTS"};
	public List<String> shoeslist = Arrays.asList(shoes);
	public String[] chestplates = {"LEATHER_CHESTPLATE","CHAINMAIL_CHESTPLATE","IRON_CHESTPLATE","GOLD_CHESTPLATE","DIAMOND_CHESTPLATE"};
	public List<String> chestplateslist = Arrays.asList(chestplates);
	public String[] leggings = {"LEATHER_LEGGINGS", "CHAINMAIL_LEGGINGS", "IRON_LEGGINGS", "GOLD_LEGGINGS", "DIAMOND_LEGGINGS"};
	public List<String> leggingslist = Arrays.asList(leggings);
	/** Gegenstand/Waffe Checklisten - Eingabepr�fung*/
	public String[] weapons = {"WOOD_SWORD","STONE_SWORD","IRON_SWORD","DIAMOND_SWORD","GOLD_SWORD","WOOD_AXE","STONE_AXE","IRON_AXE","GOLD_AXE","DIAMOND_AXE","WOOD_PICKAXE","STONE_PICKAXE","IRON_PICKAXE","GOLD_PICKAXE","DIAMOND_PICKAXE","WOOD_SPADE","STONE_SPADE","IRON_SPADE","GOLD_SPADE","DIAMOND_SPADE","WOOD_AXE","STONE_AXE","IRON_AXE","GOLD_AXE","DIAMOND_AXE","WOOD_HOE","STONE_HOE","IRON_HOE","GOLD_HOE","DIAMOND_HOE","SHEARS","FISHING_ROD","FLINT_AND_STEEL","CARROT_STICK","TORCH"};
	public List<String> weaponslist = Arrays.asList(weapons);
	/** Potion Effekt Checklisten - Eingabepr�fung*/
	public String[] potions = {"SPEED","SLOW","FAST_DIGGING","SLOW_DIGGING","INCREASE_DAMAGE","HEAL","HARM","JUMP","CONFUSION","REGENERATION","DAMAGE_RESISTANCE","FIRE_RESISTANCE","WATER_BREATHING","INVISIBILITY","BLINDNESS","NIGHT_VISION","HUNGER","WEAKNESS","POISON","WITHER","HEALTH_BOOST","ABSORPTION","SATURATION"};
	public List<String> potionslist = Arrays.asList(potions);
	
	public Core(MonsterSpawner monsterSpawner) {
		this.plugin = monsterSpawner;
	}

	
	public boolean isHeartbeatTaskRunning() {
		return monsterSpawnerHeartbeatTask != -1;
	}
	
	public void toggleHeartbeat(boolean on) {		
		if (on && !isHeartbeatTaskRunning()) {
			monsterSpawnerHeartbeatTask = plugin.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, plugin.mSHeartBeat, 100, 100);
			
			if (monsterSpawnerHeartbeatTask == -1) {
				plugin.LogError("error: Monster Spawner Task initialization failed!");
			} else {
				plugin.LogInfo("initialized: Monster Spawner Task");
			}
			
		} else if (!on && isHeartbeatTaskRunning()) {
			plugin.getServer().getScheduler().cancelTask(monsterSpawnerHeartbeatTask);
			monsterSpawnerHeartbeatTask = -1;
		}
	}
	
	public void addZonesList(Zones zone) {
		zoneList.add(zone);
	}
	
	public void removeZonesList(Zones zone) {
		zoneList.remove(zone);
	}
	
	public Zones getZonesList(int index) {
		return zoneList.get(index);
	}
	
	public int sizeZonesList() {
		return zoneList.size();
	}
	
	public List<Zones> getCompleteZonesList() {
		return zoneList;
	}
	public void addSpawnerList(Spawner spawner) {
		spawnerList.add(spawner);
	}
	
	public void removeSpawnerList(Spawner spawner) {
		spawnerList.remove(spawner);
	}
	
	public Spawner getSpawnerList(int index) {
		return spawnerList.get(index);
	}
	
	public int sizeSpawnerList() {
		return spawnerList.size();
	}
	
	public List<Spawner> getCompleteSpawnerList() {
		return spawnerList;
	}

}