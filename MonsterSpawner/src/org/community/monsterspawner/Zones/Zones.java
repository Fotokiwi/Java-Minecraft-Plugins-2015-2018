package org.community.monsterspawner.Zones;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.community.monsterspawner.MonsterSpawner;
import org.community.monsterspawner.spawner.Spawner;

public class Zones {

	private final MonsterSpawner plugin;

	private FileConfiguration zone = null;
	private File zoneFile = null;

	// Allgemeine Zonen-Variablen
	private String zoneName;
	private String worldName;
	private int ChunkX;
	private int ChunkZ;
	private int dynamicScale; //default 1
	private boolean dynamicScaleTrue; //default false
	private int dynamicHealth; //default 1
	private boolean dynamicHealthTrue; //default false
	private int dynamicDamage; //default 1
	private boolean dynamicDamageTrue; //default false
	private int BlockX;
	private int BlockZ;
	private int BlockY;
	// Systemvariablen

	/**
	 * Server-Startup Initialisierung.
	 * Der Startpunkt wird aus der Datei in den Cache geladen und registriert.
	 * @param plugin Die Hauptvariable des Plugins
	 * @param name Der Name des Startpunkts, ausgelesen aus dem Dateinamen
	 */
	public Zones(MonsterSpawner plugin, String name, boolean dummy) {
		this.plugin = plugin;
		this.zoneName = name;

		registerZone();
		saveToFile();
	}

	/**
	 * Server-Startup REInitialisierung.
	 * Der Startpunkt wird aus der Datei in den Cache geladen und registriert.
	 * @param plugin Die Hauptvariable des Plugins
	 * @param name Der Name des Startpunkts, ausgelesen aus dem Dateinamen
	 */
	public Zones(MonsterSpawner plugin, String name) {
		this.plugin = plugin;
		this.zoneName = name;

		loadFromFile();
		registerZone();
	}

	/**
	 * Registriert die Zone in der Liste
	 * @return void
	 */
	private void registerZone() {
		plugin.mSCore.addZonesList(this);
		resetZoneTempData();
		plugin.mSCore.zoneChunkLoaded.put(zoneName, false);
		return;
	}

	//Funktionen um Variablenwerte an andere Pluginteile zu �bergeben
	public String getZoneName() {
		return zoneName;
	}
	public int getDynamicScale(){
		return dynamicScale;
	}
	public int getDynamicHealth(){
		return dynamicHealth;
	}
	public int getDynamicDamage(){
		return dynamicDamage;
	}
	public int getBlockX(){
		return BlockX;
	}
	public int getBlockZ(){
		return BlockZ;
	}
	public int getBlockY(){
		return BlockY;
	}
	public String getWorldName(){
		return worldName;
	}
	public int getChunkX(){
		return ChunkX;
	}
	public int getChunkZ(){
		return ChunkZ;
	}
	public boolean getDynamicScaleTrue(){
		return dynamicScaleTrue;
	}
	public boolean getDynamicHealthTrue(){
		return dynamicHealthTrue;
	}
	public boolean getDynamicDamageTrue(){
		return dynamicDamageTrue;
	}
	public String getCompleteInfo(){
		String info = getZoneName()+": Dynamischer Spawn: "+getDynamicScaleTrue()+" Multi: "+getDynamicScale()+" , Dynamische Hitpoints: "+getDynamicHealthTrue()+" Multi: "+getDynamicHealth()+" , Dynamischer Schaden: "+getDynamicDamageTrue()+" Multi: "+getDynamicDamage()+" ,XYZ-Koordinaten: "+getBlockX()+"_"+getBlockY()+"_"+getBlockZ()+" , Welt: "+getWorldName()+" ,Chunk-Koordinaten: "+getChunkX()+"_"+getChunkZ();
		return info;
	}

	//Funktionen um Variablenwerte in die Zone zu speichern
	public void putZoneName(String zna) {
		zoneName = zna;
		saveToFile();
	}
	public void putDynamicScale(int dyns){
		dynamicScale = dyns;
		saveToFile();
	}
	public void putDynamicHealth(int dynh){
		dynamicHealth = dynh;
		saveToFile();
	}
	public void putDynamicDamage(int dynd){
		dynamicDamage = dynd;
		saveToFile();
	}
	public void putBlockX(int bX){
		BlockX = bX;
		saveToFile();
	}
	public void putBlockZ(int bZ){
		BlockZ = bZ;
		saveToFile();
	}
	public void putBlockY(int bY){
		BlockY = bY;
		saveToFile();
	}
	public void putWorldName(String wna){
		worldName = wna;
		saveToFile();
	}
	public void putChunkX(int cX){
		ChunkX = cX;
		saveToFile();
	}
	public void putChunkZ(int cZ){
		ChunkZ = cZ;
		saveToFile();
	}
	public void putDynamicScaleTrue(boolean dynst){
		dynamicScaleTrue = dynst;
		saveToFile();
	}
	public void putDynamicHealthTrue(boolean dynht){
		dynamicHealthTrue = dynht;
		saveToFile();
	}
	public void putDynamicDamageTrue(boolean dyndt){
		dynamicDamageTrue = dyndt;
		saveToFile();
	}

	/**Gibt die angelegten Spawner als String aus*/

	public String ListSpawners(){
		String zonenspawner = "";
		for(int x=0; x<plugin.mSCore.spawnerList.size(); x++){
			Spawner spawner = plugin.mSCore.spawnerList.get(x);
			if(spawner.getZoneName().equalsIgnoreCase(zoneName)){
				zonenspawner = zonenspawner + spawner.getSpawnerName() + ", ";
			}
		}
		return zonenspawner;
	}


	/** Z�hlt die Anzahl der Spieler in der N�he*/
	@SuppressWarnings("deprecation")
	public int checkForPlayers(){	 

		Location location = new Location(Bukkit.getWorld(worldName), BlockX, BlockY, BlockZ);
		int count = 0;
		Entity arrow = location.getWorld().spawnEntity(location, EntityType.ARROW);
		List<Entity> entities = arrow.getNearbyEntities(64, 32, 64);
		for (int i = 0; i < entities.size(); i++){
			if(entities.get(i).getType() == EntityType.PLAYER) {
				for(Player p : plugin.getServer().getOnlinePlayers()) {
					if(p.equals((Player) entities.get(i)))
						count++;
				}				
			}
		}
		arrow.remove();

		return count;
	}

	/** Gibt die dynamischen Spawnwert Multiplikator zur�ck
	 * @param Anzahl der Spieler �bergeben
	 * */
	public int scaleSpawn(int playerzahl){
		int Multiplikator = 1;
		if(dynamicScaleTrue){
			Multiplikator = dynamicScale * playerzahl;
		}
		return Multiplikator;
	}

	/** Gibt die dynamischen Healthwert Multiplikator zur�ck
	 * @param Anzahl der Spieler �bergeben
	 * */
	public int scaleHealth(int playerzahl){
		int Multiplikator = 1;
		if(dynamicHealthTrue){
			Multiplikator = dynamicHealth * playerzahl;
		}
		return Multiplikator;
	}

	/** Gibt die dynamischen Damagewert Multiplikator zur�ck
	 * @param Anzahl der Spieler �bergeben
	 * */
	public int scaleDamage(int playerzahl){
		int Multiplikator = 1;
		if(dynamicDamageTrue){
			Multiplikator = dynamicDamage * playerzahl;
		}
		return Multiplikator;
	}
	/** Resettet die Hashmaps f�r die Zone und die Spawner*/
	public void resetZoneTempData(){

		plugin.mSCore.zoneTime.remove(zoneName);
		plugin.mSCore.zoneTime.put(zoneName, System.currentTimeMillis() - 259200000);
		if(plugin.mSCore.spawnerList.size()>0){
			for(int x=0; x<plugin.mSCore.spawnerList.size(); x++){
				Spawner spawner = plugin.mSCore.spawnerList.get(x);
				if(zoneName.equalsIgnoreCase(spawner.getZoneName())){
					for(Entity entity : plugin.getServer().getWorld(spawner.getWorldName()).getEntities()){
						if(plugin.mSCore.monsterKey.containsKey(entity.getUniqueId())) {
							if(plugin.mSCore.monsterKey.get(entity.getUniqueId()).equalsIgnoreCase(spawner.getSpawnerName())) {
								plugin.mSCore.monsterKey.remove(entity.getUniqueId());
								entity.remove();
							}
						}
					}
					/*for(Map.Entry<UUID, String> e : plugin.mSCore.monsterKey.entrySet()){
						UUID id = e.getKey();
						String name = e.getValue();
						if(name.equalsIgnoreCase(spawner.getSpawnerName())){
							plugin.mSCore.monsterKey.remove(id);
						}
					}*/
					if(plugin.mSCore.monsterLimit.containsKey(spawner.getSpawnerName())){
						plugin.mSCore.monsterLimit.remove(spawner.getSpawnerName());
					}
					if(plugin.mSCore.spawnerState.containsKey(spawner.getSpawnerName())){
						plugin.mSCore.spawnerState.remove(spawner.getSpawnerName());
					}
					if(plugin.mSCore.spawnerTime.containsKey(spawner.getSpawnerName())){
						plugin.mSCore.spawnerTime.remove(spawner.getSpawnerName());
					}
					if(plugin.mSCore.spawnerResetCooldown.containsKey(spawner.getSpawnerName())){
						plugin.mSCore.spawnerResetCooldown.remove(spawner.getSpawnerName());
					}
				}
			}
		}

	}


	/**
	 * L�dt eine Zone aus der Datei in den Cache
	 * @return void
	 */
	private void loadFromFile() {
		getZone();

		zoneName = zone.getString("Allgemein.Name");
		worldName = zone.getString("Allgemein.World");
		ChunkX = zone.getInt("Allgemein.ChunkX");
		ChunkZ = zone.getInt("Allgemein.ChunkZ");
		dynamicScale = zone.getInt("Allgemein.Scalespawn");
		dynamicHealth = zone.getInt("Allgemein.Scalehealth");
		dynamicDamage = zone.getInt("Allgemein.Scaledamage");
		BlockX = zone.getInt("Allgemein.BlockX");
		BlockZ = zone.getInt("Allgemein.BlockZ");
		BlockY = zone.getInt("Allgemein.BlockY");
		dynamicScaleTrue = zone.getBoolean("Allgemein.ScalespawnTrue");
		dynamicHealthTrue = zone.getBoolean("Allgemein.ScalehealthTrue");
		dynamicDamageTrue = zone.getBoolean("Allgemein.ScaledamageTrue");

		saveZone();
	}

	/**
	 * Speichert eine Zone aus dem Cache in die Datei
	 * @return void
	 */
	public void saveToFile() {
		getZone();

		zone.set("Allgemein.Name", zoneName);
		zone.set("Allgemein.World", worldName);
		zone.set("Allgemein.ChunkX", ChunkX);
		zone.set("Allgemein.ChunkZ", ChunkZ);
		zone.set("Allgemein.Scalespawn", dynamicScale);
		zone.set("Allgemein.Scalehealth", dynamicHealth);
		zone.set("Allgemein.Scaledamage", dynamicDamage);
		zone.set("Allgemein.BlockX", BlockX);
		zone.set("Allgemein.BlockZ", BlockZ);
		zone.set("Allgemein.BlockY", BlockY);
		zone.set("Allgemein.ScalespawnTrue", dynamicScaleTrue);
		zone.set("Allgemein.ScalehealthTrue", dynamicHealthTrue);
		zone.set("Allgemein.ScaledamageTrue", dynamicDamageTrue);

		saveZone();
	}

	/**
	 * L�dt die FileConfiguration der Zone in eine Variable
	 * @return void
	 */
	private void reloadZone() {
		if (zoneFile == null) {
			zoneFile = new File(plugin.getDataFolder(), "/zones/" + zoneName + ".yml");
		}
		zone = YamlConfiguration.loadConfiguration(zoneFile);
	}

	/**
	 * Liefert die FileConfiguration der Zone zur�ck
	 * @return FileConfiguration
	 */
	private FileConfiguration getZone() {
		if (zone == null) {
			reloadZone();
		}
		return zone;
	}

	/**
	 * Speichert die FileConfiguration in die Datei
	 * @return void
	 */
	private void saveZone() {
		if (zone == null || zoneFile == null) {
			return;
		}
		try {
			zone.save(zoneFile);
		} catch (IOException ex) {
			Logger.getLogger(JavaPlugin.class.getName()).log(Level.SEVERE, "Could not save config to " + zoneFile, ex);
		}
	}

}