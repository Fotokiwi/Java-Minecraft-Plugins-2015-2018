package org.community.monsterspawner.spawner;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.plugin.java.JavaPlugin;
import org.community.monsterspawner.MonsterSpawner;


public class Spawner {

	private final MonsterSpawner plugin;

	private FileConfiguration spawner = null;
	private File spawnerFile = null;

	// Allgemeine Spawner-Variablen
	private String spawnerName;
	private int BlockX;
	private int BlockY;
	private int BlockZ;
	private String monsterName;
	private int Intervall; //default 5
	private int Delay; //default 10
	private int Menge; //default 1
	private boolean Boss; //default false
	private boolean NightOnly; //default false
	private boolean DayOnly; //default false
	private boolean Special; //default false
	private String Helmet;  //default "none"
	private String Boots; //default "none"
	private String Leggings; //default "none"
	private String Chest; //default "none"
	private String Weapon; //default "none"
	private String Potion; //default "none"
	private boolean ItemNeed; //default "false"
	private int ItemID; //default 0
	private int ItemSubID; //default 0
	private int Max; //default 4
	private int Wait; //default 300
	private String zoneName;
	private String worldName;
	private int dynamicScale; //default 1
	private int dynamicHealth; //default 1
	private int dynamicDamage; //default 1
	private double maxHealth; //default 0
	private double followRange; //default 0
	private double knockBackResi; //default 0
	private double moveSpeed; //default 0
	private double attackDamage; //default 0
	private boolean fireDamage; //default false
	private boolean poisonDamage; //default false
	private boolean slowDamage; //default false
	private boolean explodeOnDeath; //default false
	private boolean witherDamage; //default false
	private boolean isVillager; //default false
	private int slimeSize; //default 0

	// Systemvariablen

	// Hashmaps


	/**
	 * Server-Startup Initialisierung.
	 * Der Spawner wird aus der Datei in den Cache geladen und registriert.
	 * @param plugin Die Hauptvariable des Plugins
	 * @param name Der Name des Spawners, ausgelesen aus dem Dateinamen
	 */
	public Spawner(MonsterSpawner plugin, String name, boolean dummy) {
		this.plugin = plugin;
		this.spawnerName = name;

		saveToFile();
		registerSpawner();
	}

	/**
	 * Server-Startup Initialisierung.
	 * Der Spawner wird aus der Datei in den Cache geladen und registriert.
	 * @param plugin Die Hauptvariable des Plugins
	 * @param name Der Name des Spawners, ausgelesen aus dem Dateinamen
	 */
	public Spawner(MonsterSpawner plugin, String name) {
		this.plugin = plugin;
		this.spawnerName = name;

		loadFromFile();
		registerSpawner();
	}

	/**
	 * Registriert den Spawner in der Spawnerliste, sie ist sonst nicht g�ltig.
	 * @return void
	 */
	private void registerSpawner() {
		plugin.mSCore.addSpawnerList(this);
		String SpawnerLocation = BlockX + "," + BlockY + "," + BlockZ;
		plugin.mSCore.spawnerLocs.put(SpawnerLocation, spawnerName);
		return;
	}

	// Variablen �bergabe an andere Pluginteile
	public String getSpawnerName() {
		return spawnerName;
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
	public String getMonsterName(){
		return monsterName;
	}
	public int getIntervall(){
		return Intervall;
	}
	public int getDelay(){
		return Delay;
	}
	public int getMenge(){
		return Menge;
	}
	public boolean getBoss(){
		return Boss;
	}
	public boolean getNightOnly(){
		return NightOnly;
	}
	public boolean getDayOnly(){
		return DayOnly;
	}
	public boolean getSpecial(){
		return Special;
	}
	public String getHelmet(){
		return Helmet;
	}
	public String getBoots(){
		return Boots;
	}
	public String getLeggings(){
		return Leggings;
	}
	public String getChest(){
		return Chest;
	}
	public String getWeapon(){
		return Weapon;
	}
	public String getPotion(){
		return Potion;
	}
	public boolean getItemNeed(){ 
		return ItemNeed;
	}
	public int getItemNeedID(){
		return ItemID;
	}
	public int getItemNeedSubID(){
		return ItemSubID;
	}
	public int getMax(){
		return Max;
	}
	public int getWait(){
		return Wait;
	}
	public String getZoneName(){
		return zoneName;
	}
	public String getWorldName(){
		return worldName;
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
	public double getmaxHealth(){
		return maxHealth;
	}
	public double getfollowRage(){
		return followRange;
	}
	public double getknockBackResi(){
		return knockBackResi;
	}
	public double getmoveSpeed(){
		return moveSpeed;
	}
	public double getattackDamage(){
		return attackDamage;
	}
	public boolean getfireDamage(){
		return fireDamage;
	}
	public boolean getpoisonDamage(){
		return poisonDamage;
	}
	public boolean getexplodeOnDeath(){
		return explodeOnDeath;
	}
	public boolean getslowDamage(){
		return slowDamage;
	}
	public boolean getwitherDamage(){
		return witherDamage;
	}
	public boolean getisVillager(){
		return isVillager;
	}
	public int getslimeSize(){
		return slimeSize;
	}
	//�bergabe von Werten in die Variablen von ausserhalb
	public void putSpawnerName(String Name) {
		spawnerName = Name;
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
	public void putMonsterName(String vieh){
		monsterName = vieh;
		saveToFile();
	}
	public void putIntervall(int iva){
		Intervall = iva;
		saveToFile();
	}
	public void putDelay(int dly){
		Delay = dly;
		saveToFile();
	}
	public void putMenge(int mng){
		Menge = mng;
		saveToFile();
	}
	public void putBoss(boolean bb){
		Boss = bb;
		saveToFile();
	}
	public void putNightOnly(boolean no){
		NightOnly = no;
		saveToFile();
	}
	public void putDayOnly(boolean dao){
		DayOnly = dao;
		saveToFile();
	}
	public void putSpecial(boolean spc){
		Special = spc;
		saveToFile();
	}
	public void putHelmet(String hlmt){
		Helmet = hlmt;
		saveToFile();
	}
	public void putBoots(String bts){
		Boots = bts;
		saveToFile();
	}
	public void putLeggings(String lgns){
		Leggings = lgns;
		saveToFile();
	}
	public void putChest(String chst){
		Chest = chst;
		saveToFile();
	}
	public void putWeapon(String wpn){
		Weapon = wpn;
		saveToFile();
	}
	public void putPotion(String ptn){
		Potion = ptn;
		saveToFile();
	}
	public void putItemNeed(boolean ine){ 
		ItemNeed = ine;
		saveToFile();
	}
	public void putItemNeedID(int iid){
		ItemID = iid;
		saveToFile();
	}
	public void putItemNeedSubID(int isd){
		ItemSubID = isd;
		saveToFile();
	}
	public void putMax(int mm){
		Max = mm;
		saveToFile();
	}
	public void putWait(int ww){
		Wait = ww;
		saveToFile();
	}
	public void putZoneName(String zna){
		zoneName = zna;
		saveToFile();
	}
	public void putWorldName(String wna){
		worldName = wna;
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
	public void putmaxHealth(double mhea){
		maxHealth = mhea;
		saveToFile();
	}
	public void putfollowRage(double frng){
		followRange = frng;
		saveToFile();
	}
	public void putknockBackResi(double knbk){
		knockBackResi = knbk;
		saveToFile();
	}
	public void putmoveSpeed(double mvsp){
		moveSpeed = mvsp;
		saveToFile();
	}
	public void putattackDamage(double attd){
		attackDamage = attd;
		saveToFile();
	}
	public void putfireDamage(boolean fdm){
		fireDamage = fdm;
		saveToFile();
	}
	public void putpoisonDamage(boolean pdm){
		poisonDamage = pdm;
		saveToFile();
	}
	public void putexplodeOnDeath(boolean expd){
		explodeOnDeath = expd;
		saveToFile();
	}
	public void putslowDamage(boolean slowd){
		slowDamage = slowd;
		saveToFile();
	}
	public void putwitherDamage(boolean withd){
		witherDamage = withd;
		saveToFile();
	}
	public void putisVillager(boolean vil){
		isVillager = vil;
		saveToFile();
	}
	public void putslimeSize(int ssz){
		slimeSize = ssz;
		saveToFile();
	}
	
	// Spawner Funktionen
	/** Startet den Spawner*/
	public void runSpawner(){
		if(!plugin.mSCore.spawnerLocs.containsKey(BlockX + "," + BlockY + "," + BlockZ)) {
			plugin.mSCore.spawnerLocs.put(BlockX + "," + BlockY + "," + BlockZ, spawnerName);
		}
		if(!CheckCooldown()){
			return;
		}
		if(CheckMaxLimit()){
			return;
		}
		if(NightOnly && isday()){
			return;
		}
		if(DayOnly && !isday()){
			return;
		}
		/**TODO
		 * Itemspawn Abh�ngigkeit
		 * Spawn Randomizer
		 */
		Location location = new Location(plugin.getServer().getWorld(worldName), (double) BlockX, (double) BlockY, (double) BlockZ);
		for(int i = 0; i < (Menge * dynamicScale); i++){
			Bukkit.getServer().getWorld(worldName).spawnEntity(location, getEntity(monsterName));
			if(CheckMaxLimit()){
				return;
			}
		}
	}
	/**Bestimmt ob es Tag ist*/
	public boolean isday() {
	    long time = Bukkit.getServer().getWorld(worldName).getTime();
	    return time < 12300 || time > 23850;
	}

	/**Bestimmt den Entity Type*/
	@SuppressWarnings("deprecation")
	public EntityType getEntity (String monsterName){
		monsterName = monsterName.toUpperCase();
		EntityType entity = EntityType.fromName(monsterName);
		return entity;
	}


	/**Pr�ft ob die Maximale Anzahl an Monster bereits gespawnt ist*/
	public boolean CheckMaxLimit(){
		if(plugin.mSCore.monsterLimit.containsKey(spawnerName) == false) {
			//plugin.getServer().broadcastMessage("Limit 1");
			return false;
		} else if (plugin.mSCore.monsterLimit.containsKey(spawnerName) && plugin.mSCore.monsterLimit.get(spawnerName) < (Max * dynamicScale)) {
			//plugin.getServer().broadcastMessage("Limit 2");
			return false;
		} else if(plugin.mSCore.monsterLimit.containsKey(spawnerName) && plugin.mSCore.monsterLimit.get(spawnerName) >= (Max * dynamicScale)){
			plugin.mSCore.spawnerState.put(spawnerName, "inactive");
			plugin.mSCore.spawnerResetCooldown.put(spawnerName, System.currentTimeMillis());
			//plugin.getServer().broadcastMessage("Limit 3");
			return true;
		} else{
			//plugin.getServer().broadcastMessage("Limit 4");
			return false;
		}
	}

	/** Pr�ft ob der Spawner im Cooldown ist*/
	public boolean CheckCooldown(){
		if(plugin.mSCore.spawnerState.containsKey(spawnerName) == false){
			if(System.currentTimeMillis() > (plugin.mSCore.zoneTime.get(zoneName) + (Intervall*1000))){
				plugin.mSCore.spawnerState.put(spawnerName, "active");
				plugin.mSCore.spawnerTime.put(spawnerName, System.currentTimeMillis());
				//plugin.getServer().broadcastMessage("Cooldown 1");
				return true;
			}
			//plugin.getServer().broadcastMessage("Cooldown 2");
			return false;
		}
		if(plugin.mSCore.spawnerState.get(spawnerName).equalsIgnoreCase("active") && (System.currentTimeMillis() > (plugin.mSCore.spawnerTime.get(spawnerName) + (Delay*1000) ))) {
			//plugin.getServer().broadcastMessage("Cooldown 3");
			return true;
		}
		if(plugin.mSCore.spawnerState.get(spawnerName).equalsIgnoreCase("inactive") && (System.currentTimeMillis() > (plugin.mSCore.spawnerResetCooldown.get(spawnerName) + (Wait*1000) ))) {
			plugin.mSCore.spawnerState.put(spawnerName, "active");
			plugin.mSCore.spawnerTime.put(spawnerName, System.currentTimeMillis());
			plugin.mSCore.spawnerResetCooldown.remove(spawnerName);
			plugin.mSCore.monsterLimit.remove(spawnerName);
			//plugin.getServer().broadcastMessage("Cooldown 4");
			return true;
		}
		//plugin.getServer().broadcastMessage("Cooldown 5");
		return false;
	}

	/**
	 * L�dt einen Spawner aus der Datei in den Cache
	 * @return void
	 */
	private void loadFromFile() {
		getSpawner();

		spawnerName = spawner.getString("Allgemein.Name");
		BlockX = spawner.getInt("Allgemein.BlockX");
		BlockY = spawner.getInt("Allgemein.BlockY");
		BlockZ = spawner.getInt("Allgemein.BlockZ");
		monsterName = spawner.getString("Allgemein.Monster");
		Intervall = spawner.getInt("Allgemein.Intervall");
		Delay = spawner.getInt("Allgemein.Delay");
		Menge = spawner.getInt("Allgemein.Menge");
		Boss = spawner.getBoolean("Allgemein.Boss");
		NightOnly = spawner.getBoolean("Allgemein.Night");
		DayOnly = spawner.getBoolean("Allgemein.Day");
		Special = spawner.getBoolean("Allgemein.Special");
		Helmet = spawner.getString("Allgemein.Helmet");
		Boots = spawner.getString("Allgemein.Boots");
		Leggings = spawner.getString("Allgemein.Leggings");
		Chest = spawner.getString("Allgemein.Chest");
		Weapon = spawner.getString("Allgemein.Weapon");
		Potion = spawner.getString("Allgemein.Potion");
		ItemNeed = spawner.getBoolean("Allgemein.Item");
		ItemID = spawner.getInt("Allgemein.ItemID");
		ItemSubID = spawner.getInt("Allgemein.ItemSubID");
		Wait = spawner.getInt("Allgemein.Wait");
		Max = spawner.getInt("Allgemein.Max");
		zoneName = spawner.getString("Allgemein.Zone");
		worldName = spawner.getString("Allgemein.World");
		dynamicScale = spawner.getInt("Allgemein.Scalespawn");
		dynamicHealth = spawner.getInt("Allgemein.Scalehealth");
		dynamicDamage = spawner.getInt("Allgemein.Scaledamage");
		maxHealth = spawner.getDouble("Allgemein.maxHealth");
		followRange = spawner.getDouble("Allgemein.followRange");
		knockBackResi = spawner.getDouble("Allgemein.knockBackResi");
		moveSpeed = spawner.getDouble("Allgemein.moveSpeed");
		attackDamage = spawner.getDouble("Allgemein.attackDamage");
		fireDamage = spawner.getBoolean("Allgemein.fireDamage");
		poisonDamage = spawner.getBoolean("Allgemein.poisonDamage");
		explodeOnDeath = spawner.getBoolean("Allgemein.explodeOnDeath");
		slowDamage = spawner.getBoolean("Allgemein.slowDamage");
		witherDamage = spawner.getBoolean("Allgemein.witherDamage");
		isVillager = spawner.getBoolean("Allgemein.isVillager");
		slimeSize = spawner.getInt("Allgemein.slimeSize");

		saveSpawner();
	}

	/**
	 * Speichert einen Spawner aus dem Cache in die Datei
	 * @return void
	 */
	public void saveToFile() {
		getSpawner();

		spawner.set("Allgemein.Name", spawnerName);
		spawner.set("Allgemein.BlockX", BlockX);
		spawner.set("Allgemein.BlockY", BlockY);
		spawner.set("Allgemein.BlockZ", BlockZ);
		spawner.set("Allgemein.Monster", monsterName);
		spawner.set("Allgemein.Intervall", Intervall);
		spawner.set("Allgemein.Delay", Delay);
		spawner.set("Allgemein.Menge", Menge);
		spawner.set("Allgemein.Boss", Boss);
		spawner.set("Allgemein.Night", NightOnly);
		spawner.set("Allgemein.Day", DayOnly);
		spawner.set("Allgemein.Special", Special);
		spawner.set("Allgemein.Helmet", Helmet);
		spawner.set("Allgemein.Boots", Boots);
		spawner.set("Allgemein.Leggings", Leggings);
		spawner.set("Allgemein.Chest", Chest);
		spawner.set("Allgemein.Weapon", Weapon);
		spawner.set("Allgemein.Potion", Potion);
		spawner.set("Allgemein.Item", ItemNeed);
		spawner.set("Allgemein.ItemID", ItemID);
		spawner.set("Allgemein.ItemSubID", ItemSubID);
		spawner.set("Allgemein.Wait", Wait);
		spawner.set("Allgemein.Max", Max);
		spawner.set("Allgemein.Zone", zoneName);
		spawner.set("Allgemein.World", worldName);
		spawner.set("Allgemein.Scalespawn", dynamicScale);
		spawner.set("Allgemein.Scalehealth", dynamicHealth);
		spawner.set("Allgemein.Scaledamage", dynamicDamage);
		spawner.set("Allgemein.maxHealth", maxHealth);
		spawner.set("Allgemein.followRange", followRange);
		spawner.set("Allgemein.knockBackResi", knockBackResi);
		spawner.set("Allgemein.moveSpeed", moveSpeed);
		spawner.set("Allgemein.attackDamage", attackDamage);
		spawner.set("Allgemein.fireDamage", fireDamage);
		spawner.set("Allgemein.poisonDamage", poisonDamage);
		spawner.set("Allgemein.explodeOnDeath", explodeOnDeath);
		spawner.set("Allgemein.slowDamage", slowDamage);
		spawner.set("Allgemein.witherDamage", witherDamage);
		spawner.set("Allgemein.isVillager", isVillager);
		spawner.set("Allgemein.slimeSize", slimeSize);

		saveSpawner();
	}

	/**
	 * L�dt die FileConfiguration des Spawners in eine Variable
	 * @return void
	 */
	private void reloadSpawner() {
		if (spawnerFile == null) {
			spawnerFile = new File(plugin.getDataFolder(), "/spawner/" + spawnerName + ".yml");
		}
		spawner = YamlConfiguration.loadConfiguration(spawnerFile);
	}

	/**
	 * Liefert die FileConfiguration des Spawners zur�ck
	 * @return FileConfiguration
	 */
	private FileConfiguration getSpawner() {
		if (spawner == null) {
			reloadSpawner();
		}
		return spawner;
	}

	/**
	 * Speichert die FileConfiguration in die Datei
	 * @return void
	 */
	private void saveSpawner() {
		if (spawner == null || spawnerFile == null) {
			return;
		}
		try {
			spawner.save(spawnerFile);
		} catch (IOException ex) {
			Logger.getLogger(JavaPlugin.class.getName()).log(Level.SEVERE, "Could not save config to " + spawnerFile, ex);
		}
	}

}