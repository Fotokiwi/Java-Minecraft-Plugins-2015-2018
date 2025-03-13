package org.community.newSettlers.Town;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.Map.Entry;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.community.DatabaseProvider.MySQL.MySQL;
import org.community.newSettlers.newSettlers;

public class Town {
		
	private final newSettlers plugin;
	
	private FileConfiguration town = null;
	private File townFile = null;
	
	// Allgemeine Town-Variablen
	private String townHomePlot;
	private World townHomeWorld;
	private Map<String, String> townChunkList = new HashMap<String, String>();
	private boolean townHasUpkeep;
	private long townUpkeepTime;
	
	private String townName;
	private String townDescription;
	
	private boolean townOpen;
	
	private int townLevel;
	private int townPlotLimit;
	private int townPlot;
	private int townProductionLimit;
	private int townProduction;
	
	private boolean townPermissionOutsiderBreak;
	private boolean townPermissionOutsiderBuild;
	private boolean townPermissionOutsiderCraft;
	private boolean townPermissionOutsiderInteract;
	private boolean townPermissionOutsiderProtect;
	private boolean townPermissionMemberBreak;
	private boolean townPermissionMemberBuild;
	private boolean townPermissionMemberCraft;
	private boolean townPermissionMemberInteract;
	private boolean townPermissionMemberProtect;
	private boolean townPermissionAllyBreak;
	private boolean townPermissionAllyBuild;
	private boolean townPermissionAllyCraft;
	private boolean townPermissionAllyInteract;
	private boolean townPermissionAllyProtect;
	
	private Long townCooldownAllyBreak;
	private Long townCooldownAllyBuild;
	private Long townCooldownAllyCraft;
	private Long townCooldownAllyInteract;
	private Long townCooldownOutsiderBreak;
	private Long townCooldownOutsiderBuild;
	private Long townCooldownOutsiderCraft;
	private Long townCooldownOutsiderInteract;
	private Long townCooldownTownGuard;
	private Long townCooldownProductionSlotReset;
	
	private List<String> townAllies = new ArrayList<String>();
	private List<String> townEnemies = new ArrayList<String>();
	private List<String> townBuildings = new ArrayList<String>();
	
	private String townMayor;
	private String townMayorUUID = "";
	private String townFormerMayor;
	private String townFormerMayorUUID = "";
	private String townDiplomat;
	private String townDiplomatUUID = "";
	private List<String> townAssistants = new ArrayList<String>();
	private List<String> townMembers = new ArrayList<String>();
	private String townMessage;	
	
	private Location townSpawn = null;
	
	private Boolean townGuard;
	
	private int townRealmPoints;
	
	private int productionSlotResets;
	
	private Map<String, Boolean> productionBuildings = new HashMap<String, Boolean>();
	private Map<String, Location> productionLocations = new HashMap<String, Location>();
	private Map<String, Integer> ressourceStorage = new HashMap<String, Integer>();
	
	/**
	 * Server-Startup Initialisierung.
	 * Der Spieler wird aus der Datei in den Cache geladen und registriert.
	 * @param plugin Die Hauptvariable des Plugins
	 * @param name Der Name des Spielers, ausgelesen aus dem Dateinamen
	 */
	public Town(newSettlers plugin, String name, Player founder, boolean dummy) {
		this.plugin = plugin;
		
		initiateProductionHash();
		initiateRessourceHash();
		
		this.townHomePlot = founder.getLocation().getChunk().getX() + "," + founder.getLocation().getChunk().getZ() +  ",A";
		this.townHomeWorld = founder.getWorld();
		this.townChunkList.put(founder.getLocation().getWorld().getName() + "," + founder.getLocation().getChunk().getX() + "," + founder.getLocation().getChunk().getZ(), "A");
		this.townHasUpkeep = true;
		this.townUpkeepTime = System.currentTimeMillis() + 3600000;
		
		this.townName = name;
		this.townLevel = plugin.config.getInt("Default.Level");
		this.townDescription = plugin.config.getString("Default.Description");
		this.townPlotLimit = plugin.config.getInt("Default.PlotLimit");
		this.townPlot = 1;
		this.townProductionLimit = plugin.config.getInt("Default.ProductionLimit");
		this.townProduction = plugin.config.getInt("Default.Production");
		this.townOpen = plugin.config.getBoolean("Default.Open");
		this.townGuard = plugin.config.getBoolean("Default.TownGuard");
		this.productionSlotResets = 1;
		
		this.townMayor = founder.getName();
		this.townMayorUUID = founder.getUniqueId().toString();
		this.townMessage = plugin.config.getString("Default.TownBoard");
		
		this.townPermissionOutsiderBreak = plugin.config.getBoolean("Default.Permission.Outsider.Break");
		this.townPermissionOutsiderBuild = plugin.config.getBoolean("Default.Permission.Outsider.Build");
		this.townPermissionOutsiderCraft = plugin.config.getBoolean("Default.Permission.Outsider.Craft");
		this.townPermissionOutsiderInteract = plugin.config.getBoolean("Default.Permission.Outsider.Interact");
		this.townPermissionOutsiderProtect = plugin.config.getBoolean("Default.Permission.Outsider.Protect");
		this.townPermissionMemberBreak = plugin.config.getBoolean("Default.Permission.Member.Break");
		this.townPermissionMemberBuild = plugin.config.getBoolean("Default.Permission.Member.Build");
		this.townPermissionMemberCraft = plugin.config.getBoolean("Default.Permission.Member.Craft");
		this.townPermissionMemberInteract = plugin.config.getBoolean("Default.Permission.Member.Interact");
		this.townPermissionMemberProtect = plugin.config.getBoolean("Default.Permission.Member.Protect");
		this.townPermissionAllyBreak = plugin.config.getBoolean("Default.Permission.Ally.Break");
		this.townPermissionAllyBuild = plugin.config.getBoolean("Default.Permission.Ally.Build");
		this.townPermissionAllyCraft = plugin.config.getBoolean("Default.Permission.Ally.Craft");
		this.townPermissionAllyInteract = plugin.config.getBoolean("Default.Permission.Ally.Interact");
		this.townPermissionAllyProtect = plugin.config.getBoolean("Default.Permission.Ally.Protect");
		
		this.townCooldownAllyBreak = 0L;
		this.townCooldownAllyBuild = 0L;
		this.townCooldownAllyCraft = 0L;
		this.townCooldownAllyInteract = 0L;
		this.townCooldownOutsiderBreak = 0L;
		this.townCooldownOutsiderBuild = 0L;
		this.townCooldownOutsiderCraft = 0L;
		this.townCooldownOutsiderInteract = 0L;
		this.townCooldownTownGuard = 0L;
		this.townCooldownProductionSlotReset = 0L;
		
		addMember(founder);
		registerTown();
		saveToFile();
		
		this.initializeMySQL();
	}
	
	/**
	 * Server-Startup Initialisierung.
	 * Der Spieler wird aus der Datei in den Cache geladen und registriert.
	 * @param plugin Die Hauptvariable des Plugins
	 * @param name Der Name des Spielers, ausgelesen aus dem Dateinamen
	 */
	public Town(newSettlers plugin, String name) {
		this.plugin = plugin;
		
		initiateProductionHash();
		initiateRessourceHash();
		
		this.townName = name;
		//this.townUpkeepTime = System.currentTimeMillis();
		
		loadFromFile();
		registerTown();
		saveToFile();
	}
	
	private void initiateProductionHash() {
		productionBuildings.put("Steinbruch", false);
		productionBuildings.put("Lehmgrube", false);
		productionBuildings.put("Kohlemine", false);
		productionBuildings.put("Eisenmine", false);
		productionBuildings.put("Goldmine", false);
		productionBuildings.put("Smaragdmine", false);
		productionBuildings.put("Lapismine", false);
		productionBuildings.put("Redstonemine", false);
		productionBuildings.put("Diamantmine", false);
		productionBuildings.put("Marmorsteinbruch", false);
		productionBuildings.put("Holzfaellerlager", false);
		productionBuildings.put("Bauernhof", false);
		
		productionBuildings.put("Saegemuehle", false);
		productionBuildings.put("Schreinerei", false);
		productionBuildings.put("Bogenmacher", false);
		productionBuildings.put("Steinmetz", false);
		productionBuildings.put("Ziegelei", false);
		productionBuildings.put("Eisenschmelze", false);
		productionBuildings.put("Goldschmelze", false);
		productionBuildings.put("Werkzeugschmied", false);
		productionBuildings.put("Waffenschmied", false);
		productionBuildings.put("Ruestungsschmied", false);
		productionBuildings.put("Werkstatt", false);
		productionBuildings.put("Metzgerei", false);
		productionBuildings.put("Gerberei", false);
		productionBuildings.put("Sattlerei", false);
		productionBuildings.put("Stallung", false);
		productionBuildings.put("Baeckerei", false);
		productionBuildings.put("Fleischer", false);
		productionBuildings.put("Fischraeucherei", false);
		productionBuildings.put("Taverne", false);
		productionBuildings.put("Faerberei", false);
		productionBuildings.put("Schneiderei", false);
		productionBuildings.put("Karawanserei", false);
		productionBuildings.put("Glasblaeserei", false);
		productionBuildings.put("Brauerei", false);
		productionBuildings.put("Apotheke", false);
		productionBuildings.put("Buchbinder", false);
		
		productionLocations.put("Saegemuehle", null);
		productionLocations.put("Schreinerei", null);
		productionLocations.put("Bogenmacher", null);
		productionLocations.put("Steinmetz", null);
		productionLocations.put("Ziegelei", null);
		productionLocations.put("Eisenschmelze", null);
		productionLocations.put("Goldschmelze", null);
		productionLocations.put("Werkzeugschmied", null);
		productionLocations.put("Waffenschmied", null);
		productionLocations.put("Ruestungsschmied", null);
		productionLocations.put("Werkstatt", null);
		productionLocations.put("Metzgerei", null);
		productionLocations.put("Gerberei", null);
		productionLocations.put("Sattlerei", null);
		productionLocations.put("Stallung", null);
		productionLocations.put("Baeckerei", null);
		productionLocations.put("Fleischer", null);
		productionLocations.put("Fischraeucherei", null);
		productionLocations.put("Taverne", null);
		productionLocations.put("Faerberei", null);
		productionLocations.put("Schneiderei", null);
		productionLocations.put("Karawanserei", null);
		productionLocations.put("Glasblaeserei", null);
		productionLocations.put("Brauerei", null);
		productionLocations.put("Apotheke", null);
		productionLocations.put("Buchbinder", null);
	}
	
	private void initiateRessourceHash() {
		List<String> storage = plugin.nSCore.getRessourceList();
		Map<String, String> storageName = plugin.nSCore.getRessourceName();
		
		for(int i = 0; i < storage.size(); i++) {
			ressourceStorage.put(storageName.get(storage.get(i)), 0);
		}
	}
	
	/**
	 * Registriert die Stadt in der Stadtliste, sie ist sonst nicht g�ltig.
	 * @return void
	 */
	private void registerTown() {
		plugin.nSCore.addTownList(this);
		String[] cleanHomePlot = townHomePlot.split(",");
		plugin.nSCore.addChunkInfo(townHomeWorld.getName() + "," + cleanHomePlot[0] + "," + cleanHomePlot[1], this);
		plugin.nSCore.addHomePlot(townHomeWorld.getName() + "," + townHomePlot, this);
		return;
	}
	
	/**
	 * Registriert den Plot in der Stadt-Plotliste, er ist sonst nicht g�ltig.
	 * @return void
	 */
	private void registerPlot(Chunk chunk) {
		plugin.nSCore.addChunkInfo(chunk.getWorld().getName() + "," + chunk.getX() + "," + chunk.getZ(), this);
		return;
	}
	
	/**
	 * Registriert den Plot in der Stadt-Plotliste, er ist sonst nicht g�ltig.
	 * @return void
	 */
	private void unregisterPlot(Chunk chunk) {
		plugin.nSCore.removeChunkInfo(chunk.getWorld().getName() + "," + chunk.getX() + "," + chunk.getZ());
		return;
	}
	
	/**
	 * Liefert den Stadtnamen zur�ck
	 * @return String
	 */
	public String getName() {
		return townName;
	}
	
	/**
	 * Liefert den letzten Upkeep-Zeitpunkt zur�ck
	 * @return Long
	 */
	public long getUpkeepTime() {
		return townUpkeepTime;
	}
	
	/**
	 * Setzt den aktuellen Upkeep-Zeitpunkt
	 * @return Long
	 */
	public void setUpkeepTime() {
		townUpkeepTime = System.currentTimeMillis();
		saveToFile();
	}
	
	/**
	 * Liefert das Stadtlevel zur�ck
	 * @return String
	 */
	public int getLevel() {
		return townLevel;
	}
	
	/**
	 * Liefert die Stadtbezeichnung zur�ck
	 * @return String
	 */
	public String getDescription() {
		return townDescription;
	}
	
	/**
	 * Liefert das Stadtmotto zur�ck
	 * @return String
	 */
	public String getTownMessage() {
		return townMessage;
	}
	
	/**
	 * Liefert den B�rgermeister zur�ck
	 * @return String
	 */
	public String getMayor() {
		return townMayor;
	}
	
	/**
	 * Liefert den B�rgermeister zur�ck
	 * @return String
	 */
	public String getMayorUUID() {
		return townMayorUUID;
	}
	
	/**
	 * Liefert den B�rgermeister zur�ck
	 * @return String
	 */
	public String getFormerMayor() {
		return townFormerMayor;
	}
	
	/**
	 * Liefert den B�rgermeister zur�ck
	 * @return String
	 */
	public String getFormerMayorUUID() {
		return townFormerMayorUUID;
	}
	
	/**
	 * Liefert den Diplomaten zur�ck
	 * @return String
	 */
	public String getDiplomat() {
		return townDiplomat;
	}
	
	/**
	 * Liefert die Assistenten zur�ck
	 * @return String
	 */
	public List<String> getAssistants() {
		return townAssistants;
	}
	
	/**
	 * Setzt das Stadtmotto
	 * @return void
	 */
	public void setTownMessage(String string) {
		townMessage = string;
		saveToFile();
		return;
	}
	
	/**
	 * Liefert zur�ck ob die Stadt offen ist
	 * @return String
	 */
	public boolean isOpen() {
		return townOpen;
	}
	
	/**
	 * Liefert zur�ck ob die Stadt eine Stadtwache gegen Monsterspawn besitzt
	 * @return String
	 */
	public boolean hasTownGuard() {
		return townGuard;
	}
	
	/**
	 * Liefert das Plotlimit zur�ck
	 * @return String
	 */
	public int getPlotLimit() {
		return townPlotLimit;
	}
	
	/**
	 * Liefert die Zahl aktueller Plots zur�ck
	 * @return String
	 */
	public int getPlots() {
		return townPlot;
	}
	
	/**
	 * Liefert das Produktionslimit zur�ck
	 * @return String
	 */
	public int getProductionLimit() {
		return townProductionLimit;
	}
	
	/**
	 * Liefert die Produktionsanzahl zur�ck
	 * @return String
	 */
	public int getProduction() {
		return townProduction;
	}
	
	public int getProductionSlotResets() {
		return productionSlotResets;
	}
	
	public void addProductionSlotResets() {
		productionSlotResets++;
		return;
	}
	
	public void subProductionSlotResets() {
		productionSlotResets--;
		return;
	}
	
	/**
	 * Liefert das Stadtlager zur�ck
	 * @return String
	 */
	public Map<String, Integer> getTownStorage() {
		return ressourceStorage;
	}
	
	/**
	 * Liefert den Geb�udestatus zur�ck
	 * @return Boolean
	 */
	public boolean getBuildingStatus(String building) {
		return productionBuildings.get(building);
	}
	
	/**
	 * Liefert den Geb�udestatus zur�ck
	 * @return Boolean
	 */
	public Map<String, Boolean> getProductionBuildings() {
		return productionBuildings;
	}
	
	/**
	 * Liefert die Reichspunktzahl zur�ck
	 * @return Integer
	 */
	public int getRealmpoints() {
		return townRealmPoints;
	}
	
	public boolean isCooldownAllowed(String right) {
		Long timestamp = 0L;
		
		if(right.equalsIgnoreCase("AllyBreak"))
			timestamp = this.townCooldownAllyBreak;
		if(right.equalsIgnoreCase("AllyBuild"))
			timestamp = this.townCooldownAllyBuild;
		if(right.equalsIgnoreCase("AllyCraft"))
			timestamp = this.townCooldownAllyCraft;
		if(right.equalsIgnoreCase("AllyInteract"))
			timestamp = this.townCooldownAllyInteract;
		if(right.equalsIgnoreCase("OutsiderBreak"))
			timestamp = this.townCooldownOutsiderBreak;
		if(right.equalsIgnoreCase("OutsiderBuild"))
			timestamp = this.townCooldownOutsiderBuild;
		if(right.equalsIgnoreCase("OutsiderCraft"))
			timestamp = this.townCooldownOutsiderCraft;
		if(right.equalsIgnoreCase("OutsiderInteract"))
			timestamp = this.townCooldownOutsiderInteract;
		if(right.equalsIgnoreCase("TownGuard"))
			timestamp = this.townCooldownTownGuard;
		
		if(System.currentTimeMillis() >= (timestamp + 604800000)) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * F�gt der Stadt Reichspunkte hinzu
	 * @return Integer
	 */
	public int addRealmpoints(int rp) {
		return townRealmPoints = townRealmPoints + rp;
	}
	
	/**
	 * Zieht der Stadt Reichspunkte ab
	 * @return Integer
	 */
	public int removeRealmpoints(int rp) {
		return townRealmPoints = townRealmPoints - rp;
	}
	
	/**
	 * Setzt den Gebäudestatus
	 * @return void
	 */
	public void setBuildingStatus(String building, Location location, boolean status) {
		productionBuildings.put(building, status);
		productionLocations.put(building, location);
		townProduction++;
		saveToFile();
		return;
	}
	
	/**
	 * Setzt den Gebäudestatus
	 * @return void
	 */
	public void setBuildingLocation(String building, Location location) {
		productionLocations.put(building, location);
		saveToFile();
		return;
	}
	
	public void removeBuilding(String building) {
		productionBuildings.put(building, false);
		productionLocations.remove(building);
		townProduction--;
		productionSlotResets--;
		saveToFile();
		return;
	}
	
	/**
	 * Liefert die Alliiertenliste zur�ck
	 * @return String
	 */
	public List<String> getAllies() {
		return townAllies;
	}

	/**
	 * F�gt einen Alliierten der Stadt hinzu
	 * @return void
	 */
	public void addAlly(String ally) {
		townAllies.add(ally);
		townEnemies.remove(ally);
		saveToFile();
		return;
	}

	/**
	 * F�gt einen Alliierten der Stadt hinzu
	 * @return void
	 */
	public void removeAlly(String ally) {
		townAllies.remove(ally);
		saveToFile();
		return;
	}
	
	/**
	 * Liefert die Feindesliste zur�ck
	 * @return String
	 */
	public List<String> getEnemies() {
		return townEnemies;
	}

	/**
	 * F�gt einen Alliierten der Stadt hinzu
	 * @return void
	 */
	public void addEnemy(String enemy) {
		townEnemies.add(enemy);
		townAllies.remove(enemy);
		saveToFile();
		return;
	}

	/**
	 * F�gt einen Alliierten der Stadt hinzu
	 * @return void
	 */
	public void removeEnemy(String enemy) {
		townEnemies.remove(enemy);
		saveToFile();
		return;
	}

	/**
	 * F�gt ein Stadtgeb�ude der Stadt hinzu
	 * @return void
	 */
	public void addBuilding(String name) {
		townBuildings.add(name);
		saveToFile();
		return;
	}

	/**
	 * F�gt ein Stadtgeb�ude der Stadt hinzu
	 * @return void
	 */
	public List<String> getBuildingList() {
		return townBuildings;
	}
	
	/**
	 * Pr�ft den B�rgermeisterstatus
	 * @return boolean
	 */
	public boolean isMayor(Player player) {
		if(player.getName().equalsIgnoreCase(getMayor()) && player.getUniqueId().toString().equalsIgnoreCase(getMayorUUID()))
			return true;
		
		return false;
	}
	
	/**
	 * Pr�ft den Ex-B�rgermeisterstatus
	 * @return boolean
	 */
	public boolean isFormerMayor(Player player) {
		if(player.getName().equalsIgnoreCase(getFormerMayor()) && player.getUniqueId().toString().equalsIgnoreCase(getFormerMayorUUID()))
			return true;
		
		return false;
	}
	
	/**
	 * Pr�ft den Diplomatenstatus
	 * @return boolean
	 */
	public boolean isDiplomat(Player player) {
		if(player.getName().equalsIgnoreCase(townDiplomat) && player.getUniqueId().toString().equalsIgnoreCase(townDiplomatUUID))
			return true;
		
		return false;
	}
	
	/**
	 * Pr�ft den Assistentenstatus
	 * @return boolean
	 */
	public boolean isAssistant(String name) {
		if(townAssistants == null)
			return false;
		if(townAssistants.contains(name))
			return true;
		
		return false;
	}
	
	/**
	 * Pr�ft den Einwohnerstatus
	 * @return boolean
	 */
	public boolean isMember(String name) {
		if(townMembers.contains(name))
			return true;
		
		return false;
	}
	
	/**
	 * Pr�ft den Alliiertenstatus
	 * @return boolean
	 */
	public boolean isAlly(Player player) {
		Town town = plugin.nSCore.getPlayerTown(player);
		if(town == null)
			return false;
		
		if(townAllies.contains(town.getName()))
			return true;
		
		return false;
	}
	
	/**
	 * Pr�ft den Abbaustatus
	 * @return boolean
	 */
	public boolean isBreakAllowed() {
		if(townPermissionMemberBreak)
			return true;
		
		return false;
	}
	
	/**
	 * Pr�ft den Aufbaustatus
	 * @return boolean
	 */
	public boolean isBuildAllowed() {
		if(townPermissionMemberBuild)
			return true;
		
		return false;
	}
	
	/**
	 * Pr�ft den Handwerksstatus
	 * @return boolean
	 */
	public boolean isCraftAllowed() {
		if(townPermissionMemberCraft)
			return true;
		
		return false;
	}
	
	/**
	 * Prüft den Interaktionsstatus
	 * @return boolean
	 */
	public boolean isInteractAllowed() {
		if(townPermissionMemberInteract)
			return true;
		
		return false;
	}
	
	/**
	 * Prüft den Schutzstatus
	 * @return boolean
	 */
	public boolean isProtectAllowed() {
		if(townPermissionMemberInteract)
			return true;
		
		return false;
	}
	
	/**
	 * Pr�ft den Abbaustatus f�r Alliierte
	 * @return boolean
	 */
	public boolean isBreakAllowedAlly() {
		if(townPermissionAllyBreak)
			return true;
		
		return false;
	}
	
	/**
	 * Pr�ft den Aufbaustatus f�r Alliierte
	 * @return boolean
	 */
	public boolean isBuildAllowedAlly() {
		if(townPermissionAllyBuild)
			return true;
		
		return false;
	}
	
	/**
	 * Pr�ft den Handwerksstatus f�r Alliierte
	 * @return boolean
	 */
	public boolean isCraftAllowedAlly() {
		if(townPermissionAllyCraft)
			return true;
		
		return false;
	}
	
	/**
	 * Prüft den Interaktionsstatus für Alliierte
	 * @return boolean
	 */
	public boolean isInteractAllowedAlly() {
		if(townPermissionAllyInteract)
			return true;
		
		return false;
	}
	
	/**
	 * Prüft den Schutzstatus für Alliierte
	 * @return boolean
	 */
	public boolean isProtectAllowedAlly() {
		if(townPermissionAllyProtect)
			return true;
		
		return false;
	}
	
	/**
	 * Pr�ft den Abbaustatus f�r Fremde
	 * @return boolean
	 */
	public boolean isBreakAllowedOutsider() {
		if(townPermissionOutsiderBreak)
			return true;
		
		return false;
	}
	
	/**
	 * Pr�ft den Aufbaustatus f�r Fremde
	 * @return boolean
	 */
	public boolean isBuildAllowedOutsider() {
		if(townPermissionOutsiderBuild)
			return true;
		
		return false;
	}
	
	/**
	 * Pr�ft den Handwerksstatus f�r Fremde
	 * @return boolean
	 */
	public boolean isCraftAllowedOutsider() {
		if(townPermissionOutsiderCraft)
			return true;
		
		return false;
	}
	
	/**
	 * Prüft den Interaktionsstatus für Fremde
	 * @return boolean
	 */
	public boolean isInteractAllowedOutsider() {
		if(townPermissionOutsiderInteract)
			return true;
		
		return false;
	}
	
	/**
	 * Prüft den Schutzstatus für Fremde
	 * @return boolean
	 */
	public boolean isProtectAllowedOutsider() {
		if(townPermissionOutsiderProtect)
			return true;
		
		return false;
	}
	
	/**
	 * �ndert den Public-Status
	 * @return boolean
	 */
	public boolean togglePublicStatus() {
		if(townOpen) {
			townOpen = false;
			saveToFile();
			return false;
		} else {
			townOpen = true;
			saveToFile();
			return true;
		}		
	}
	
	/**
	 * �ndert den Public-Status
	 * @return boolean
	 */
	public boolean toggleTownGuardStatus() {
		if(townGuard) {
			townGuard = false;
			this.townCooldownTownGuard = System.currentTimeMillis();
			saveToFile();
			return false;
		} else {
			townGuard = true;
			this.townCooldownTownGuard = System.currentTimeMillis();
			saveToFile();
			return true;
		}		
	}
	
	/**
	 * �ndert den Abbaustatus
	 * @return boolean
	 */
	public boolean toggleBreakAllowed() {
		if(townPermissionMemberBreak) {
			townPermissionMemberBreak = false;
			saveToFile();
			return false;
		} else {
			townPermissionMemberBreak = true;
			saveToFile();
			return true;
		}
	}
	
	/**
	 * �ndert den Aufbaustatus
	 * @return boolean
	 */
	public boolean toggleBuildAllowed() {
		if(townPermissionMemberBuild) {
			townPermissionMemberBuild = false;
			saveToFile();
			return false;
		} else {
			townPermissionMemberBuild = true;
			saveToFile();
			return true;
		}
	}
	
	/**
	 * �ndert den Handwerksstatus
	 * @return boolean
	 */
	public boolean toggleCraftAllowed() {
		if(townPermissionMemberCraft) {
			townPermissionMemberCraft = false;
			saveToFile();
			return false;
		} else {
			townPermissionMemberCraft = true;
			saveToFile();
			return true;
		}
	}
	
	/**
	 * Ändert den Interaktionsstatus
	 * @return boolean
	 */
	public boolean toggleInteractAllowed() {
		if(townPermissionMemberInteract) {
			townPermissionMemberInteract = false;
			saveToFile();
			return false;
		} else {
			townPermissionMemberInteract = true;
			saveToFile();
			return true;
		}
	}
	
	/**
	 * Ändert den Sicherungsstatus
	 * @return boolean
	 */
	public boolean toggleProtectAllowed() {
		if(townPermissionMemberProtect) {
			townPermissionMemberProtect = false;
			saveToFile();
			return false;
		} else {
			townPermissionMemberProtect = true;
			saveToFile();
			return true;
		}
	}
	
	/**
	 * �ndert den Abbaustatus
	 * @return boolean
	 */
	public boolean toggleBreakAllowedOutsider() {
		if(townPermissionOutsiderBreak) {
			townPermissionOutsiderBreak = false;
			this.townCooldownOutsiderBreak = System.currentTimeMillis();
			saveToFile();
			return false;
		} else {
			townPermissionOutsiderBreak = true;
			this.townCooldownOutsiderBreak = System.currentTimeMillis();
			saveToFile();
			return true;
		}
	}
	
	/**
	 * �ndert den Aufbaustatus
	 * @return boolean
	 */
	public boolean toggleBuildAllowedOutsider() {
		if(townPermissionOutsiderBuild) {
			townPermissionOutsiderBuild = false;
			this.townCooldownOutsiderBuild = System.currentTimeMillis();
			saveToFile();
			return false;
		} else {
			townPermissionOutsiderBuild = true;
			this.townCooldownOutsiderBuild = System.currentTimeMillis();
			saveToFile();
			return true;
		}
	}
	
	/**
	 * �ndert den Handwerksstatus
	 * @return boolean
	 */
	public boolean toggleCraftAllowedOutsider() {
		if(townPermissionOutsiderCraft) {
			townPermissionOutsiderCraft = false;
			this.townCooldownOutsiderCraft = System.currentTimeMillis();
			saveToFile();
			return false;
		} else {
			townPermissionOutsiderCraft = true;
			this.townCooldownOutsiderCraft = System.currentTimeMillis();
			saveToFile();
			return true;
		}
	}
	
	/**
	 * Ändert den Interaktionsstatus
	 * @return boolean
	 */
	public boolean toggleInteractAllowedOutsider() {
		if(townPermissionOutsiderInteract) {
			townPermissionOutsiderInteract = false;
			this.townCooldownOutsiderInteract = System.currentTimeMillis();
			saveToFile();
			return false;
		} else {
			townPermissionOutsiderInteract = true;
			this.townCooldownOutsiderInteract = System.currentTimeMillis();
			saveToFile();
			return true;
		}
	}
	
	/**
	 * Ändert den Sicherungsstatus
	 * @return boolean
	 */
	public boolean toggleProtectAllowedOutsider() {
		if(townPermissionOutsiderProtect) {
			townPermissionOutsiderProtect = false;
			saveToFile();
			return false;
		} else {
			townPermissionOutsiderProtect = true;
			saveToFile();
			return true;
		}
	}
	
	/**
	 * �ndert den Abbaustatus
	 * @return boolean
	 */
	public boolean toggleBreakAllowedAlly() {
		if(townPermissionAllyBreak) {
			townPermissionAllyBreak = false;
			this.townCooldownAllyBreak = System.currentTimeMillis();
			saveToFile();
			return false;
		} else {
			townPermissionAllyBreak = true;
			this.townCooldownAllyBreak = System.currentTimeMillis();
			saveToFile();
			return true;
		}
	}
	
	/**
	 * �ndert den Aufbaustatus
	 * @return boolean
	 */
	public boolean toggleBuildAllowedAlly() {
		if(townPermissionAllyBuild) {
			townPermissionAllyBuild = false;
			this.townCooldownAllyBuild = System.currentTimeMillis();
			saveToFile();
			return false;
		} else {
			townPermissionAllyBuild = true;
			this.townCooldownAllyBuild = System.currentTimeMillis();
			saveToFile();
			return true;
		}
	}
	
	/**
	 * �ndert den Handwerksstatus
	 * @return boolean
	 */
	public boolean toggleCraftAllowedAlly() {
		if(townPermissionAllyCraft) {
			townPermissionAllyCraft = false;
			this.townCooldownAllyCraft = System.currentTimeMillis();
			saveToFile();
			return false;
		} else {
			townPermissionAllyCraft = true;
			this.townCooldownAllyCraft = System.currentTimeMillis();
			saveToFile();
			return true;
		}
	}
	
	/**
	 * Ändert den Interaktionsstatus
	 * @return boolean
	 */
	public boolean toggleInteractAllowedAlly() {
		if(townPermissionAllyInteract) {
			townPermissionAllyInteract = false;
			this.townCooldownAllyInteract = System.currentTimeMillis();
			saveToFile();
			return false;
		} else {
			townPermissionAllyInteract = true;
			this.townCooldownAllyInteract = System.currentTimeMillis();
			saveToFile();
			return true;
		}
	}
	
	/**
	 * Ändert den Sicherungsstatus
	 * @return boolean
	 */
	public boolean toggleProtectAllowedAlly() {
		if(townPermissionAllyProtect) {
			townPermissionAllyProtect = false;
			saveToFile();
			return false;
		} else {
			townPermissionAllyProtect = true;
			saveToFile();
			return true;
		}
	}
	
	/**
	 * Setzt die Berechtigungen auf Standard
	 * @return boolean
	 */
	public void setDefaultPermissions() {
		townPermissionOutsiderBreak = plugin.config.getBoolean("Default.Permission.Outsider.Break");
		townPermissionOutsiderBuild = plugin.config.getBoolean("Default.Permission.Outsider.Build");
		townPermissionOutsiderCraft = plugin.config.getBoolean("Default.Permission.Outsider.Craft");
		townPermissionOutsiderInteract = plugin.config.getBoolean("Default.Permission.Outsider.Interact");
		townPermissionOutsiderProtect = plugin.config.getBoolean("Default.Permission.Outsider.Protect");
		townPermissionMemberBreak = plugin.config.getBoolean("Default.Permission.Member.Break");
		townPermissionMemberBuild = plugin.config.getBoolean("Default.Permission.Member.Build");
		townPermissionMemberCraft = plugin.config.getBoolean("Default.Permission.Member.Craft");
		townPermissionMemberInteract = plugin.config.getBoolean("Default.Permission.Member.Interact");
		townPermissionMemberProtect = plugin.config.getBoolean("Default.Permission.Member.Protect");
		townPermissionAllyBreak = plugin.config.getBoolean("Default.Permission.Ally.Break");
		townPermissionAllyBuild = plugin.config.getBoolean("Default.Permission.Ally.Build");
		townPermissionAllyCraft = plugin.config.getBoolean("Default.Permission.Ally.Craft");
		townPermissionAllyInteract = plugin.config.getBoolean("Default.Permission.Ally.Interact");
		townPermissionAllyProtect = plugin.config.getBoolean("Default.Permission.Ally.Protect");
		saveToFile();
		return;
	}
	
	/**
	 * Pr�ft und claimt einen neuen Stadtplot
	 * @return void
	 */
	public void claimTownPlot(Player player) {
		Chunk chunk = player.getLocation().getChunk();
		String world = chunk.getWorld().getName();
		int chunkX = chunk.getX();
		int chunkZ = chunk.getZ();
		if(townPlot >= townPlotLimit){
			player.sendMessage(ChatColor.DARK_GREEN + "Deine Stadt hat ihre maximale Anzahl an Grundstücken erreicht.");
			return;
		}
		if(plugin.nSCore.getChunkInfo(world + "," + chunkX + "," + chunkZ) == this){
			player.sendMessage(ChatColor.DARK_GREEN + "Dieses Grundstück ist bereits im Besitz deiner Stadt.");
			return;
		}
		Chunk nextChunk = player.getLocation().getWorld().getChunkAt(chunkX - 1, chunkZ);
		if(townChunkList.containsKey(nextChunk.getWorld().getName() + "," + nextChunk.getX() + "," + nextChunk.getZ())) {
			townChunkList.put(chunk.getWorld().getName() + "," + chunk.getX() + "," + chunk.getZ(), "A");
			townPlot++;
			player.sendMessage(ChatColor.DARK_GREEN + "Du hast das Grundstück erfolgreich der Stadt hinzugefügt.");
			registerPlot(chunk);
			saveToFile();
			return;
		}
		nextChunk = player.getLocation().getWorld().getChunkAt(chunkX + 1, chunkZ);
		if(townChunkList.containsKey(nextChunk.getWorld().getName() + "," + nextChunk.getX() + "," + nextChunk.getZ())) {
			townChunkList.put(chunk.getWorld().getName() + "," + chunk.getX() + "," + chunk.getZ(), "A");
			townPlot++;
			player.sendMessage(ChatColor.DARK_GREEN + "Du hast das Grundstück erfolgreich der Stadt hinzugefügt.");
			registerPlot(chunk);
			saveToFile();
			return;
		}
		nextChunk = player.getLocation().getWorld().getChunkAt(chunkX, chunkZ - 1);
		if(townChunkList.containsKey(nextChunk.getWorld().getName() + "," + nextChunk.getX() + "," + nextChunk.getZ())) {
			townChunkList.put(chunk.getWorld().getName() + "," + chunk.getX() + "," + chunk.getZ(), "A");
			townPlot++;
			player.sendMessage(ChatColor.DARK_GREEN + "Du hast das Grundstück erfolgreich der Stadt hinzugefügt.");
			registerPlot(chunk);
			saveToFile();
			return;
		}
		nextChunk = player.getLocation().getWorld().getChunkAt(chunkX, chunkZ + 1);
		if(townChunkList.containsKey(nextChunk.getWorld().getName() + "," + nextChunk.getX() + "," + nextChunk.getZ())) {
			townChunkList.put(chunk.getWorld().getName() + "," + chunk.getX() + "," + chunk.getZ(), "A");
			townPlot++;
			player.sendMessage(ChatColor.DARK_GREEN + "Du hast das Grundstück erfolgreich der Stadt hinzugefügt.");
			registerPlot(chunk);
			saveToFile();
			return;
		}
		player.sendMessage(ChatColor.DARK_GREEN + "Das Grundstück grenzt nicht an deiner Stadt an.");
		return;
	}
	
	/**
	 * Pr�ft und verwildert einen Stadtplot
	 * @return void
	 */
	public void removeTownPlot(Player player) {
		Chunk chunk = player.getLocation().getChunk();
		int chunkX = chunk.getX();
		int chunkZ = chunk.getZ();
		
		if(townHomePlot.equalsIgnoreCase(chunk.getWorld() + "," + chunk.getX() + "," + chunk.getX())){
			player.sendMessage(ChatColor.DARK_GREEN + "Du kannst das Gründungsgrundstück nicht freigeben.");
			return;
		}
		if(plugin.nSCore.getChunkInfo(chunk.getWorld() + "," + chunk.getX() + "," + chunk.getX()) != this){
			player.sendMessage(ChatColor.DARK_GREEN + "Dieses Grundstück ist nicht im Besitz deiner Stadt.");
			return;
		}
		
		Chunk testChunkA = null;
		Chunk testChunkB = null;
		Chunk testChunkC = null;
		Chunk testChunkD = null;
		Chunk testChunkE = null;
		Chunk testChunkF = null;
		Chunk testChunkG = null;
		Chunk testChunkH = null;
		
		boolean checkA = false;
		boolean checkB = false;
		boolean checkC = false;
		boolean checkD = false;
 
		
		testChunkA = townHomeWorld.getChunkAt(chunkX - 1, chunkZ - 1);
		testChunkB = townHomeWorld.getChunkAt(chunkX - 1, chunkZ);
		testChunkC = townHomeWorld.getChunkAt(chunkX - 1, chunkZ + 1);
		testChunkD = townHomeWorld.getChunkAt(chunkX, chunkZ - 1);
		testChunkE = townHomeWorld.getChunkAt(chunkX, chunkZ + 1);
		testChunkF = townHomeWorld.getChunkAt(chunkX + 1, chunkZ - 1);
		testChunkG = townHomeWorld.getChunkAt(chunkX + 1, chunkZ);
		testChunkH = townHomeWorld.getChunkAt(chunkX + 1, chunkZ + 1);
		
		if(townChunkList.containsKey(testChunkA) && townChunkList.containsKey(testChunkB) 
				&& townChunkList.containsKey(testChunkC) && townChunkList.containsKey(testChunkD) 
				&& townChunkList.containsKey(testChunkE) && townChunkList.containsKey(testChunkF) 
				&& townChunkList.containsKey(testChunkG) && townChunkList.containsKey(testChunkH)) {
			
			player.sendMessage(ChatColor.DARK_GREEN + "Du kannst nur Grenzgrundstücke freigeben.");
			return;
			
		}
		
		testChunkA = townHomeWorld.getChunkAt(chunkX - 1, chunkZ);
		testChunkB = townHomeWorld.getChunkAt(chunkX, chunkZ - 1);
		
		if(townChunkList.containsKey(testChunkA) && townChunkList.containsKey(testChunkB)) {
			testChunkC = townHomeWorld.getChunkAt(chunkX - 1, chunkZ - 1);
			if(townChunkList.containsKey(testChunkC)) {
				checkA = true;
			}
		} 
		
		testChunkA = townHomeWorld.getChunkAt(chunkX - 1, chunkZ);
		testChunkB = townHomeWorld.getChunkAt(chunkX, chunkZ + 1);
		
		if(townChunkList.containsKey(testChunkA) && townChunkList.containsKey(testChunkB)) {
			testChunkC = townHomeWorld.getChunkAt(chunkX - 1, chunkZ + 1);
			if(townChunkList.containsKey(testChunkC)) {
				checkB = true;
			}
		} 
		
		testChunkA = townHomeWorld.getChunkAt(chunkX + 1, chunkZ);
		testChunkB = townHomeWorld.getChunkAt(chunkX, chunkZ - 1);
		
		if(townChunkList.containsKey(testChunkA) && townChunkList.containsKey(testChunkB)) {
			testChunkC = townHomeWorld.getChunkAt(chunkX + 1, chunkZ - 1);
			if(townChunkList.containsKey(testChunkC)) {
				checkC = true;
			}
		} 
		
		testChunkA = townHomeWorld.getChunkAt(chunkX + 1, chunkZ);
		testChunkB = townHomeWorld.getChunkAt(chunkX, chunkZ + 1);
		
		if(townChunkList.containsKey(testChunkA) && townChunkList.containsKey(testChunkB)) {
			testChunkC = townHomeWorld.getChunkAt(chunkX + 1, chunkZ + 1);
			if(townChunkList.containsKey(testChunkC)) {
				checkD = true;
			}
		}	
		
		if(checkA && checkB && checkC && checkD) {
			townChunkList.remove(chunk);
			townPlot--;
			unregisterPlot(chunk);
			saveToFile();
			player.sendMessage(ChatColor.DARK_GREEN + "Du hast das Grundstück freigegeben, es ist nicht länger im Besitz deiner Stadt.");
			return;
		}
		
		testChunkA = townHomeWorld.getChunkAt(chunkX - 1, chunkZ);
		testChunkB = townHomeWorld.getChunkAt(chunkX + 1, chunkZ);
		
		if(townChunkList.containsKey(testChunkA) && townChunkList.containsKey(testChunkB)) {
			testChunkC = townHomeWorld.getChunkAt(chunkX - 1, chunkZ + 1);
			testChunkD = townHomeWorld.getChunkAt(chunkX, chunkZ + 1);
			testChunkE = townHomeWorld.getChunkAt(chunkX + 1, chunkZ + 1);			
			if(townChunkList.containsKey(testChunkC) && townChunkList.containsKey(testChunkD) && townChunkList.containsKey(testChunkE)) {
				townChunkList.remove(chunk);
				townPlot--;
				unregisterPlot(chunk);
				saveToFile();
				player.sendMessage(ChatColor.DARK_GREEN + "Du hast das Grundstück freigegeben, es ist nicht länger im Besitz deiner Stadt.");
				return;
			}
			testChunkC = townHomeWorld.getChunkAt(chunkX - 1, chunkZ - 1);
			testChunkD = townHomeWorld.getChunkAt(chunkX, chunkZ - 1);
			testChunkE = townHomeWorld.getChunkAt(chunkX + 1, chunkZ - 1);			
			if(townChunkList.containsKey(testChunkC) && townChunkList.containsKey(testChunkD) && townChunkList.containsKey(testChunkE)) {
				townChunkList.remove(chunk);
				townPlot--;
				unregisterPlot(chunk);
				saveToFile();
				player.sendMessage(ChatColor.DARK_GREEN + "Du hast das Grundstück freigegeben, es ist nicht länger im Besitz deiner Stadt.");
				return;
			}			
		} 
		
		testChunkA = townHomeWorld.getChunkAt(chunkX, chunkZ - 1);
		testChunkB = townHomeWorld.getChunkAt(chunkX, chunkZ + 1);
		
		if(townChunkList.containsKey(testChunkA) && townChunkList.containsKey(testChunkB)) {
			testChunkC = townHomeWorld.getChunkAt(chunkX - 1, chunkZ - 1);
			testChunkD = townHomeWorld.getChunkAt(chunkX - 1, chunkZ);
			testChunkE = townHomeWorld.getChunkAt(chunkX - 1, chunkZ + 1);			
			if(townChunkList.containsKey(testChunkC) && townChunkList.containsKey(testChunkD) && townChunkList.containsKey(testChunkE)) {
				townChunkList.remove(chunk);
				townPlot--;
				unregisterPlot(chunk);
				saveToFile();
				player.sendMessage(ChatColor.DARK_GREEN + "Du hast das Grundstück freigegeben, es ist nicht länger im Besitz deiner Stadt.");
				return;
			}
			testChunkC = townHomeWorld.getChunkAt(chunkX + 1, chunkZ - 1);
			testChunkD = townHomeWorld.getChunkAt(chunkX + 1, chunkZ);
			testChunkE = townHomeWorld.getChunkAt(chunkX + 1, chunkZ + 1);			
			if(townChunkList.containsKey(testChunkC) && townChunkList.containsKey(testChunkD) && townChunkList.containsKey(testChunkE)) {
				townChunkList.remove(chunk);
				townPlot--;
				unregisterPlot(chunk);
				saveToFile();
				player.sendMessage(ChatColor.DARK_GREEN + "Du hast das Grundstück freigegeben, es ist nicht länger im Besitz deiner Stadt.");
				return;
			}	
		} 
		
		player.sendMessage(ChatColor.DARK_GREEN + "Du kannst dieses Grundstück aufgrund seiner Lage nicht freigeben.");
		return;
	}
	
	/**
	 * F�gt einen Spieler der Stadt hinzu
	 * @param player Die Spielerklasse des neuen Mitglieds
	 * @return void
	 */
	public void addMember(Player player) {
		townMembers.add(player.getName());
		registerPlayerTown(player.getName());
		saveTown();
		plugin.nSCore.handleCommand(plugin, /*player,*/ "/FTgcm70o79WIEn3p " + player.getName());
		return;
	}
	
	/**
	 * F�gt einen Spieler der Ministerliste hinzu
	 * @param player Die Spielerklasse des neuen Mitglieds
	 * @return void
	 */
	public void addAssistant(String name) {
		townAssistants.add(name);
		saveToFile();
		return;
	}
	
	/**
	 * F�gt einen Spieler der Ministerliste hinzu
	 * @param player Die Spielerklasse des neuen Mitglieds
	 * @return void
	 */
	public void removeAssistant(String name) {
		townAssistants.remove(name);
		saveToFile();
		return;
	}
	
	/**
	 * Entfernt einen Spieler aus der Stadt
	 * @param player Die Spielerklasse des neuen Mitglieds
	 * @return void
	 */
	public void removeMember(Player player) {
		townMembers.remove(player.getName());
		removePlayerTown(player.getName());
		saveToFile();
		plugin.user.set("Spieler." + player.getUniqueId().toString() + "." + townName + ".TownJoinCooldown", System.currentTimeMillis());
		plugin.user.set("Spieler." + player.getUniqueId().toString() + ".Username", player.getName());
		plugin.nSUserConfig.saveConfig();
		plugin.nSCore.handleCommand(plugin, /*player,*/ "/RG8Uz2AU7rSxNTzk " + player.getName());
		return;
	}
	
	/**
	 * Entfernt einen Spieler aus der Stadt
	 * @param player Die Spielerklasse des neuen Mitglieds
	 * @return void
	 */
	public void removeMember(OfflinePlayer player) {
		townMembers.remove(player.getName());
		removePlayerTown(player.getName());
		saveToFile();
		plugin.user.set("Spieler." + player.getUniqueId().toString() + "." + townName + ".TownJoinCooldown", System.currentTimeMillis());
		plugin.user.set("Spieler." + player.getUniqueId().toString() + ".Username", player.getName());
		plugin.nSUserConfig.saveConfig();
		plugin.nSCore.handleCommand(plugin, /*player,*/ "/RG8Uz2AU7rSxNTzk " + player.getName());
		return;
	}
	
	/**
	 * F�gt einen Spieler der Ministerliste hinzu
	 * @param player Die Spielerklasse des neuen Mitglieds
	 * @return void
	 */
	@SuppressWarnings("deprecation")
	public void setMayor(String name) {
		townMayor = name;
		if(name.equalsIgnoreCase("Gouverneur Monroe")){
			townMayorUUID = null;
		} else {
			if(plugin.getPlayerByName(name) != null) {
				townMayorUUID = plugin.getPlayerByName(name).getUniqueId().toString();
			} else {
				townMayorUUID = plugin.getServer().getOfflinePlayer(name).getUniqueId().toString();			
			}	
		}
			
		saveToFile();
		return;
	}
	
	/**
	 * F�gt einen Spieler der Ministerliste hinzu
	 * @param player Die Spielerklasse des neuen Mitglieds
	 * @return void
	 */
	@SuppressWarnings("deprecation")
	public void setFormerMayor(String name) {
		townFormerMayor = name;
		if(name.equalsIgnoreCase("Gouverneur Monroe") || name.equalsIgnoreCase("")){
			townFormerMayorUUID = null;
		} else {
			if(plugin.getPlayerByName(name) != null) {
				townFormerMayorUUID = plugin.getPlayerByName(name).getUniqueId().toString();
			} else {
				townFormerMayorUUID = plugin.getServer().getOfflinePlayer(name).getUniqueId().toString();			
			}
		}
		saveToFile();
		return;
	}
	
	/**
	 * F�gt einen Spieler der Ministerliste hinzu
	 * @param player Die Spielerklasse des neuen Mitglieds
	 * @return void
	 */
	@SuppressWarnings("deprecation")
	public void setDiplomat(String name) {
		townDiplomat = name;
		if(!townDiplomat.equalsIgnoreCase("")){
			if(plugin.getPlayerByName(name) != null) {
				townDiplomatUUID = plugin.getPlayerByName(name).getUniqueId().toString();
			} else {
				townDiplomatUUID = plugin.getServer().getOfflinePlayer(name).getUniqueId().toString();		
			}
		}			
		saveToFile();
		return;
	}
	
	/**
	 * F�gt einen Spieler der Ministerliste hinzu
	 * @param player Die Spielerklasse des neuen Mitglieds
	 * @return void
	 */
	public void removeDiplomat(String name) {
		townDiplomat = "";
		townDiplomatUUID = null;
		saveToFile();
		return;
	}
	
	/**
	 * F�gt einen Spieler der Spieler-Stadt HashMap hinzu
	 * @param player Die Spielerklasse des neuen Mitglieds
	 * @return void
	 */
	private void registerPlayerTown(String playerName) {
		plugin.nSCore.addPlayerTown(playerName, this);
		return;
	}
	
	/**
	 * Entfernt einen Spieler ausder Spieler-Stadt HashMap
	 * @param player Die Spielerklasse des neuen Mitglieds
	 * @return void
	 */
	private void removePlayerTown(String playerName) {
		plugin.nSCore.removePlayerTown(playerName);
		return;
	}
	
	/**
	 * Liefert die Lister der Einwohner zur�ck
	 * @return List<String>
	 */
	public List<String> getMemberList() {
		return townMembers;
	}
	
	/**
	 * Liefert die Lister der Einwohner formatiert f�r den Chat zur�ck
	 * @return String
	 */
	public String getMembers() {
		String memberlist = "";
		
		for(int i = 0; i < townMembers.size(); i++) {
			if(plugin.getPlayerByName(this.townMembers.get(i)) != null) {
				memberlist += "" + ChatColor.GREEN + this.townMembers.get(i) + ChatColor.GRAY + ", ";
			} else {
				memberlist += "" + ChatColor.GRAY + this.townMembers.get(i) + ChatColor.GRAY + ", ";
			}
		}
		
		return memberlist;
	}
	
	/**
	 * Misst die Distanz zum n�chsten Handwerksbetrieb
	 * @param string Bezeichnung des Betriebs
	 * @return boolean
	 */
	public boolean isBuildingInDistance(String building, Location location) {
		if(this.productionLocations.containsKey(building) == false)
			return true;
		if(location.distance(productionLocations.get(building)) <= plugin.config.getDouble("System.Distance.Crafting"))
			return true;
		
		return false;
	}
	
	/**
	 * Misst die Distanz zum n�chsten Handwerksbetrieb
	 * @param string Bezeichnung des Betriebs
	 * @return boolean
	 */
	public boolean isProductionInDistance(Player sender, String building, Location location) {
		int check = 0;
		for(Entry<String, Location> entry : productionLocations.entrySet()){
			if(entry.getValue() != null) {
				double distance = location.distance(entry.getValue());
				String[] distanceClear = ("" + distance).split("/.");
				if(distance < (plugin.config.getDouble("System.Distance.Crafting") * 2)) {
					sender.sendMessage(ChatColor.DARK_RED + "Der Abstand zu " + entry.getKey() + " ist zu gering (" + distanceClear[0] + ") !");
					check++;
				} else {
					sender.sendMessage(entry.getKey() + ": " + distanceClear[0]);
				}
			}			
		}
		if(check == 0)
			return true;
		
		return false;
	}
	
	/**
	 * Setzt den Stadtspawn
	 * @param location Der Standort
	 * @return void
	 */
	public void setTownSpawn(Location location) {
		townSpawn = location;
		saveToFile();
		return;
	}
	
	/**
	 * Holt den Stadtspawn
	 * @return 
	 * @return location
	 */
	public Location getTownSpawn() {
		return townSpawn;
	}

	/**
	 * Lädt einen Spieler aus der Datei in den Cache
	 * @return void
	 */
	private void loadFromFile() {
		getTown();
		
		townHomeWorld = plugin.getServer().getWorld(town.getString("Stadt.System.Welt"));
		generateHomePlot();
		generatePlotList();
		townHasUpkeep = town.getBoolean("Stadt.System.Upkeep");
		townUpkeepTime = town.getLong("Stadt.System.UpkeepTime");
		
		townLevel = town.getInt("Stadt.Daten.Stufe");
		townDescription = town.getString("Stadt.Daten.Bezeichnung");
		townPlotLimit = town.getInt("Stadt.Daten.Plot-Limit");
		townPlot = town.getInt("Stadt.Daten.Plot-Aktuell");
		townProductionLimit = town.getInt("Stadt.Daten.Produktions-Limit");
		townProduction = town.getInt("Stadt.Daten.Produktion-Aktuell");
		townOpen = town.getBoolean("Stadt.Daten.Oeffentlich");
		townGuard = town.getBoolean("Stadt.Daten.Stadtwache");
		townRealmPoints = town.getInt("Stadt.Daten.Reichspuntke");
		productionSlotResets = town.getInt("Stadt.Daten.Produktions-Reset", 1);
		
		townMayor = town.getString("Stadt.Einwohner.Buergermeister");
		if(townMayor != null && !townMayor.isEmpty() && !townMayor.equalsIgnoreCase("[]")) {
			townMayorUUID = town.getString("Stadt.Einwohner.BuergermeisterUUID");
			/*if(townMayorUUID != null && !townMayorUUID.isEmpty() && !townMayorUUID.equalsIgnoreCase("[]")) {
				if(!townMayor.equalsIgnoreCase(plugin.getServer().getOfflinePlayer(UUID.fromString(townMayorUUID)).getName())){
					plugin.LogInfo("[WARNUNG] Es wurde versucht durch Namenswechsel Rechte zu erschleichen! (" + townMayor + ")");
					townMayor = plugin.getServer().getOfflinePlayer(UUID.fromString(townMayorUUID)).getName();
				}
			}*/
			
		}
		/*if(!townMayorUUID.equalsIgnoreCase("")) {
			if(!townMayor.equalsIgnoreCase(plugin.getServer().getOfflinePlayer(UUID.fromString(townMayorUUID)).getName())){
				plugin.LogInfo("[WARNUNG] Es wurde versucht durch Namenswechsel Rechte zu erschleichen! (" + townMayor + ")");
				townMayor = plugin.getServer().getOfflinePlayer(UUID.fromString(townMayorUUID)).getName();
			}
		}*/
		townFormerMayor = town.getString("Stadt.Einwohner.Ex-Buergermeister");
		if(townFormerMayor != null && !townFormerMayor.isEmpty() && !townFormerMayor.equalsIgnoreCase("[]")) {
			townFormerMayorUUID = town.getString("Stadt.Einwohner.Ex-BuergermeisterUUID");
			/*if(townFormerMayorUUID != null && !townFormerMayorUUID.isEmpty() && !townFormerMayorUUID.equalsIgnoreCase("[]")) {
				if(!townFormerMayor.equalsIgnoreCase(plugin.getServer().getOfflinePlayer(UUID.fromString(townFormerMayorUUID)).getName())){
					plugin.LogInfo("[WARNUNG] Es wurde versucht durch Namenswechsel Rechte zu erschleichen! (" + townFormerMayor + ")");
					townFormerMayor = plugin.getServer().getOfflinePlayer(UUID.fromString(townFormerMayorUUID)).getName();
				}
			}*/
		}
		/*if(!townFormerMayorUUID.equalsIgnoreCase("")) {
			if(!townFormerMayor.equalsIgnoreCase(plugin.getServer().getOfflinePlayer(UUID.fromString(townFormerMayorUUID)).getName())){
				plugin.LogInfo("[WARNUNG] Es wurde versucht durch Namenswechsel Rechte zu erschleichen! (" + townFormerMayor + ")");
				townFormerMayor = plugin.getServer().getOfflinePlayer(UUID.fromString(townFormerMayorUUID)).getName();
			}
		}*/
		townDiplomat = town.getString("Stadt.Einwohner.Diplomat");
		if(townDiplomat != null && !townDiplomat.isEmpty() && !townDiplomat.equalsIgnoreCase("[]")) {
			townDiplomatUUID = town.getString("Stadt.Einwohner.DiplomatUUID");
			if(townDiplomatUUID != null && !townDiplomatUUID.isEmpty() && !townDiplomatUUID.equalsIgnoreCase("[]")) {
				if(!townDiplomat.equalsIgnoreCase(plugin.getServer().getOfflinePlayer(UUID.fromString(townDiplomatUUID)).getName())){
					plugin.LogInfo("[WARNUNG] Es wurde versucht durch Namenswechsel Rechte zu erschleichen! (" + townDiplomat + ")");
					townDiplomat = plugin.getServer().getOfflinePlayer(UUID.fromString(townDiplomatUUID)).getName();
				}
			}
		}
		/*if(!townDiplomatUUID.equalsIgnoreCase("")) {
			if(!townDiplomat.equalsIgnoreCase(plugin.getServer().getOfflinePlayer(UUID.fromString(townDiplomatUUID)).getName())){
				plugin.LogInfo("[WARNUNG] Es wurde versucht durch Namenswechsel Rechte zu erschleichen! (" + townDiplomat + ")");
				townDiplomat = plugin.getServer().getOfflinePlayer(UUID.fromString(townDiplomatUUID)).getName();
			}
		}*/
		
		
		if(town.getList("Stadt.Einwohner.Assistent") == null) {
			town.set("Stadt.Einwohner.Assistent", "[]");
		} else {
			townAssistants = town.getStringList("Stadt.Einwohner.Assistent");
		}
		
		if(town.getList("Stadt.Einwohner.Buerger") == null) {
			town.set("Stadt.Einwohner.Buerger", "[]");
		} else {
			townMembers = town.getStringList("Stadt.Einwohner.Buerger");
		}
		
		townMessage = town.getString("Stadt.Einwohner.Stadtmitteilung");
		
		if(town.getString("Stadt.System.Spawn.Welt") == null) {
			townSpawn = null;
		} else {
			townSpawn = plugin.nSCore.parseStringToLocation("" + town.getString("Stadt.System.Spawn.Welt") + "," + town.getString("Stadt.System.Spawn.X") + "," + town.getString("Stadt.System.Spawn.Y") + "," + town.getString("Stadt.System.Spawn.Z"));
		}
		
		plugin.LogInfo("Townspawn: " + townSpawn);
		
		townPermissionOutsiderBreak = town.getBoolean("Stadt.Permission.Outsider.Break");
		townPermissionOutsiderBuild = town.getBoolean("Stadt.Permission.Outsider.Build");
		townPermissionOutsiderCraft = town.getBoolean("Stadt.Permission.Outsider.Craft");
		townPermissionOutsiderInteract = town.getBoolean("Stadt.Permission.Outsider.Interact");
		townPermissionOutsiderProtect = town.getBoolean("Stadt.Permission.Outsider.Protect", false);
		townPermissionMemberBreak = town.getBoolean("Stadt.Permission.Member.Break");
		townPermissionMemberBuild = town.getBoolean("Stadt.Permission.Member.Build");
		townPermissionMemberCraft = town.getBoolean("Stadt.Permission.Member.Craft");
		townPermissionMemberInteract = town.getBoolean("Stadt.Permission.Member.Interact");
		townPermissionMemberProtect = town.getBoolean("Stadt.Permission.Member.Protect", true);
		townPermissionAllyBreak = town.getBoolean("Stadt.Permission.Ally.Break");
		townPermissionAllyBuild = town.getBoolean("Stadt.Permission.Ally.Build");
		townPermissionAllyCraft = town.getBoolean("Stadt.Permission.Ally.Craft");
		townPermissionAllyInteract = town.getBoolean("Stadt.Permission.Ally.Interact");
		townPermissionAllyProtect = town.getBoolean("Stadt.Permission.Ally.Protect", false);
		
		this.townCooldownAllyBreak = town.getLong("Stadt.Cooldown.Ally.Break", 0);
		this.townCooldownAllyBuild = town.getLong("Stadt.Cooldown.Ally.Build", 0);
		this.townCooldownAllyCraft = town.getLong("Stadt.Cooldown.Ally.Craft", 0);
		this.townCooldownAllyInteract = town.getLong("Stadt.Cooldown.Ally.Interact", 0);
		this.townCooldownOutsiderBreak = town.getLong("Stadt.Cooldown.Outsider.Break", 0);
		this.townCooldownOutsiderBuild = town.getLong("Stadt.Cooldown.Outsider.Build", 0);
		this.townCooldownOutsiderCraft = town.getLong("Stadt.Cooldown.Outsider.Craft", 0);
		this.townCooldownOutsiderInteract = town.getLong("Stadt.Cooldown.Outsider.Interact", 0);
		this.townCooldownTownGuard = town.getLong("Stadt.Cooldown.TownGuard", 0);
		this.townCooldownProductionSlotReset = town.getLong("Stadt.Cooldown.ProductionSlotReset", 0);
		
		if(town.getList("Stadt.Diplomatie.Alliierte") == null) {
			town.set("Stadt.Diplomatie.Alliierte", "[]");
		} else {
			townAllies = town.getStringList("Stadt.Diplomatie.Alliierte");
		}
		
		if(town.getList("Stadt.Diplomatie.Feind") == null) {
			town.set("Stadt.Diplomatie.Feind", "[]");
		} else {
			townEnemies = town.getStringList("Stadt.Diplomatie.Feind");
		}	
		
		if(town.getList("Stadt.Upgrade.Gebaeude") == null) {
			town.set("Stadt.Upgrade.Gebaeude", "[]");
		} else {
			townBuildings = town.getStringList("Stadt.Upgrade.Gebaeude");
		}
		
		registerTownMembers();
		
		productionBuildings.put("Steinbruch", town.getBoolean("Stadt.Produktion.Rohstoffe.Steinbruch"));
		productionBuildings.put("Lehmgrube", town.getBoolean("Stadt.Produktion.Rohstoffe.Lehmgrube"));
		productionBuildings.put("Kohlemine", town.getBoolean("Stadt.Produktion.Rohstoffe.Kohlemine"));
		productionBuildings.put("Eisenmine", town.getBoolean("Stadt.Produktion.Rohstoffe.Eisenmine"));
		productionBuildings.put("Goldmine", town.getBoolean("Stadt.Produktion.Rohstoffe.Goldmine"));
		productionBuildings.put("Smaragdmine", town.getBoolean("Stadt.Produktion.Rohstoffe.Smaragdmine"));
		productionBuildings.put("Lapismine", town.getBoolean("Stadt.Produktion.Rohstoffe.Lapismine"));
		productionBuildings.put("Redstonemine", town.getBoolean("Stadt.Produktion.Rohstoffe.Redstonemine"));
		productionBuildings.put("Diamantmine", town.getBoolean("Stadt.Produktion.Rohstoffe.Diamantmine"));
		productionBuildings.put("Marmorsteinbruch", town.getBoolean("Stadt.Produktion.Rohstoffe.Marmorsteinbruch"));
		productionBuildings.put("Holzfaellerlager", town.getBoolean("Stadt.Produktion.Rohstoffe.Holzfaellerlager"));
		productionBuildings.put("Bauernhof", town.getBoolean("Stadt.Produktion.Rohstoffe.Bauernhof"));
		
		productionBuildings.put("Saegemuehle", town.getBoolean("Stadt.Produktion.Produkte.Saegemuehle"));
		productionBuildings.put("Schreinerei", town.getBoolean("Stadt.Produktion.Produkte.Schreinerei"));
		productionBuildings.put("Bogenmacher", town.getBoolean("Stadt.Produktion.Produkte.Bogenmacher"));
		productionBuildings.put("Steinmetz", town.getBoolean("Stadt.Produktion.Produkte.Steinmetz"));
		productionBuildings.put("Ziegelei", town.getBoolean("Stadt.Produktion.Produkte.Ziegelei"));
		productionBuildings.put("Eisenschmelze", town.getBoolean("Stadt.Produktion.Produkte.Eisenschmelze"));
		productionBuildings.put("Goldschmelze", town.getBoolean("Stadt.Produktion.Produkte.Goldschmelze"));
		productionBuildings.put("Werkzeugschmied", town.getBoolean("Stadt.Produktion.Produkte.Werkzeugschmied"));
		productionBuildings.put("Waffenschmied", town.getBoolean("Stadt.Produktion.Produkte.Waffenschmied"));
		productionBuildings.put("Ruestungsschmied", town.getBoolean("Stadt.Produktion.Produkte.Ruestungsschmied"));
		productionBuildings.put("Werkstatt", town.getBoolean("Stadt.Produktion.Produkte.Werkstatt"));
		productionBuildings.put("Metzgerei", town.getBoolean("Stadt.Produktion.Produkte.Metzgerei"));
		productionBuildings.put("Gerberei", town.getBoolean("Stadt.Produktion.Produkte.Gerberei"));
		productionBuildings.put("Sattlerei", town.getBoolean("Stadt.Produktion.Produkte.Sattlerei"));
		productionBuildings.put("Stallung", town.getBoolean("Stadt.Produktion.Produkte.Stallung"));
		productionBuildings.put("Baeckerei", town.getBoolean("Stadt.Produktion.Produkte.Baeckerei"));
		productionBuildings.put("Fleischer", town.getBoolean("Stadt.Produktion.Produkte.Fleischer"));
		productionBuildings.put("Fischraeucherei", town.getBoolean("Stadt.Produktion.Produkte.Fischraeucherei"));
		productionBuildings.put("Taverne", town.getBoolean("Stadt.Produktion.Produkte.Taverne"));
		productionBuildings.put("Faerberei", town.getBoolean("Stadt.Produktion.Produkte.Faerberei"));
		productionBuildings.put("Schneiderei", town.getBoolean("Stadt.Produktion.Produkte.Schneiderei"));
		productionBuildings.put("Karawanserei", town.getBoolean("Stadt.Produktion.Produkte.Karawanserei"));
		productionBuildings.put("Glasblaeserei", town.getBoolean("Stadt.Produktion.Produkte.Glasblaeserei"));
		productionBuildings.put("Brauerei", town.getBoolean("Stadt.Produktion.Produkte.Brauerei"));
		productionBuildings.put("Apotheke", town.getBoolean("Stadt.Produktion.Produkte.Apotheke"));
		productionBuildings.put("Buchbinder", town.getBoolean("Stadt.Produktion.Produkte.Buchbinder"));
		
		
		productionLocations.put("Saegemuehle", generateBuildingLocation("Saegemuehle"));
		productionLocations.put("Schreinerei", generateBuildingLocation("Schreinerei"));
		productionLocations.put("Bogenmacher", generateBuildingLocation("Bogenmacher"));
		productionLocations.put("Steinmetz", generateBuildingLocation("Steinmetz"));
		productionLocations.put("Ziegelei", generateBuildingLocation("Ziegelei"));
		productionLocations.put("Eisenschmelze", generateBuildingLocation("Eisenschmelze"));
		productionLocations.put("Goldschmelze", generateBuildingLocation("Goldschmelze"));
		productionLocations.put("Werkzeugschmied", generateBuildingLocation("Werkzeugschmied"));
		productionLocations.put("Waffenschmied", generateBuildingLocation("Waffenschmied"));
		productionLocations.put("Ruestungsschmied", generateBuildingLocation("Ruestungsschmied"));
		productionLocations.put("Werkstatt", generateBuildingLocation("Werkstatt"));
		productionLocations.put("Metzgerei", generateBuildingLocation("Metzgerei"));
		productionLocations.put("Gerberei", generateBuildingLocation("Gerberei"));
		productionLocations.put("Sattlerei", generateBuildingLocation("Sattlerei"));
		productionLocations.put("Stallung", generateBuildingLocation("Stallung"));
		productionLocations.put("Baeckerei", generateBuildingLocation("Baeckerei"));
		productionLocations.put("Fleischer", generateBuildingLocation("Fleischer"));
		productionLocations.put("Fischraeucherei", generateBuildingLocation("Fischraeucherei"));
		productionLocations.put("Taverne", generateBuildingLocation("Taverne"));
		productionLocations.put("Faerberei", generateBuildingLocation("Faerberei"));
		productionLocations.put("Schneiderei", generateBuildingLocation("Schneiderei"));
		productionLocations.put("Karawanserei", generateBuildingLocation("Karawanserei"));
		productionLocations.put("Glasblaeserei", generateBuildingLocation("Glasblaeserei"));
		productionLocations.put("Brauerei", generateBuildingLocation("Brauerei"));
		productionLocations.put("Apotheke", generateBuildingLocation("Apotheke"));
		productionLocations.put("Buchbinder", generateBuildingLocation("Buchbinder"));

		ConfigurationSection ressourceSection = town.getConfigurationSection("Stadt.Stadtlager.Release.1-0-0");
		
		if(ressourceSection != null) {
			Set<String> ressourceKeys = ressourceSection.getKeys(false);
	  	  	String[] ressourceArray = ressourceKeys.toArray(new String[0]); 
	  	  	
	  	  	for(int i = 0; i < ressourceArray.length; i++){
	  	  	ressourceStorage.put(ressourceArray[i], town.getInt("Stadt.Stadtlager.Release.1-0-0." + ressourceArray[i]));
	  	  	}
		}
			
		
		saveTown();
	}
	
	/**
	 * Generiert die Plot-Liste aus dem Chunk-Cache der Stadt
	 * @return String
	 */
	private Location generateBuildingLocation(String building) {
		if(town.getString("Stadt.Produktion.Standort." + building) == null)
			return null;
		
		int x = town.getInt("Stadt.Produktion.Standort." + building + ".X");
		int y = town.getInt("Stadt.Produktion.Standort." + building + ".Y");
		int z = town.getInt("Stadt.Produktion.Standort." + building + ".Z");
		Location location = new Location(townHomeWorld, x, y, z);
		return location;
	}
	
	/**
	 * Generiert die Plot-Liste aus dem Chunk-Cache der Stadt
	 * @return String
	 */
	private void saveBuildingLocation(String building) {
		if(productionLocations.get(building) == null)
			return;
		
		town.set("Stadt.Produktion.Standort." + building + ".X", productionLocations.get(building).getBlockX());
		town.set("Stadt.Produktion.Standort." + building + ".Y", productionLocations.get(building).getBlockY());
		town.set("Stadt.Produktion.Standort." + building + ".Z", productionLocations.get(building).getBlockZ());
		return;
	}
	
	/**
	 * Generiert die Plot-Liste aus dem Chunk-Cache der Stadt
	 * @return String
	 */
	private String parsePlotList() {
		String plotList = "";
		String[] chunk = null;
		String chunkType = "";
		for(Map.Entry<String, String> e : townChunkList.entrySet()){
			chunk = e.getKey().split(",");
			chunkType = e.getValue();
			plotList += chunk[1] + "," + chunk[2] + "," + chunkType + ";";
		}
		return plotList;
	}
	
	/**
	 * Generiert den Home-Plot aus dem Chunk-Cache der Stadt
	 * @return String
	 */
	private String parseHomePlot() {
		String plotList = "";
		plotList += townHomePlot + ";";
		return plotList;
	}
	
	/**
	 * Generiert die Plot-Liste aus dem Chunk-Cache der Stadt
	 * @return String
	 */
	private void generatePlotList() {
		String[] plotList = town.getString("Stadt.System.Plots").split(";");
		int chunkX;
		int chunkZ;
		String chunkType;
		//TownChunk chunk = null;
		String[] plotData = new String[2];
		int count = 0;
		for(int i = 0; i < plotList.length; i++) {
			plotData = plotList[i].split(",");
			chunkX = Integer.parseInt(plotData[0]);
			chunkZ = Integer.parseInt(plotData[1]);
			chunkType = plotData[2];
			//chunk = new TownChunk(townHomeWorld, chunkX, chunkZ);
			townChunkList.put(townHomeWorld.getName() + "," + chunkX + "," + chunkZ, chunkType);
			plugin.nSCore.addChunkInfo(townHomeWorld.getName() + "," + chunkX + "," + chunkZ, this);
			plugin.LogInfo(townHomeWorld.getName() + "," + chunkX + "," + chunkZ + "," + this.getName());
			count++;
		}
		plugin.LogInfo(townName + " registered " + count + " Chunks");
		return;
	}
	
	/**
	 * Generiert den Home-Plot aus dem Chunk-Cache der Stadt
	 * @return String
	 */
	private void generateHomePlot() {
		String[] plotList = town.getString("Stadt.System.HomePlot").replace(";", "").split(",");
		int chunkX;
		int chunkZ;
		//TownChunk chunk = null;
		chunkX = Integer.parseInt(plotList[0]);
		chunkZ = Integer.parseInt(plotList[1]);
		//chunk = new TownChunk(townHomeWorld, chunkX, chunkZ);
		townHomePlot = chunkX + "," + chunkZ + "," + plotList[2];
		return;
	}
	
	/**
	 * Meldet alle B�rger in der Stadtliste an
	 * @return String
	 */
	private void registerTownMembers() {
		for(int i = 0; i < townMembers.size(); i++) {
			registerPlayerTown(townMembers.get(i));
		}
		return;
	}
	
	/**
	 * Pr�ft eine Truhe und f�gt alle passenden Rohstoffe dem Lager hinzu
	 * @return void
	 */
	public void saveInventoryToTown(Player player) {
	    Inventory inventory = player.getInventory();
	    ItemStack stack = null;
	    String itemName = "";
	    int itemAmount = 0;
	    
	    ConfigurationSection plotSection = plugin.config.getConfigurationSection("TownStorage.Plot");
		ConfigurationSection productionSection = plugin.config.getConfigurationSection("TownStorage.Production");
		ConfigurationSection citizenSection = plugin.config.getConfigurationSection("TownStorage.Citizen");
		ConfigurationSection allySection = plugin.config.getConfigurationSection("TownStorage.Ally");
		
		List<String> storage = new ArrayList<String>();
		Map<String, String> storageName = new HashMap<String, String>();
		Map<String, Integer> storageLevel = new HashMap<String, Integer>();
		Map<String, Double> storageAmount = new HashMap<String, Double>();
		
		if(plotSection != null){
			Set<String> plotKeys = plotSection.getKeys(false);
	  	  	String[] plotArray = plotKeys.toArray(new String[0]); 
	  	  	
	  	  	for(int i = 0; i < plotArray.length; i++){
		  	  	storage.add(plotArray[i]);
	  	  		storageName.put(plotArray[i], plugin.config.getString("TownStorage.Plot." + plotArray[i] + ".Display"));
		  	  	storageLevel.put(plugin.config.getString("TownStorage.Plot." + plotArray[i] + ".Display"), plugin.config.getInt("TownStorage.Plot." + plotArray[i] + ".Level"));
		  	  	storageAmount.put(plugin.config.getString("TownStorage.Plot." + plotArray[i] + ".Display"), plugin.config.getDouble("TownStorage.Plot." + plotArray[i] + ".Amount"));
	  	  	}
		}
		
		if(productionSection != null){
			Set<String> productionKeys = productionSection.getKeys(false);
	  	  	String[] productionArray = productionKeys.toArray(new String[0]); 
	  	  	
	  	  	for(int i = 0; i < productionArray.length; i++){
		  	  	storage.add(productionArray[i]);
	  	  		storageName.put(productionArray[i], plugin.config.getString("TownStorage.Production." + productionArray[i] + ".Display"));
		  	  	storageLevel.put(plugin.config.getString("TownStorage.Production." + productionArray[i] + ".Display"), plugin.config.getInt("TownStorage.Production." + productionArray[i] + ".Level"));
		  	  	storageAmount.put(plugin.config.getString("TownStorage.Production." + productionArray[i] + ".Display"), plugin.config.getDouble("TownStorage.Production." + productionArray[i] + ".Amount"));
	  	  	}
		}

		if(citizenSection != null){
			Set<String> citizenKeys = citizenSection.getKeys(false);
	  	  	String[] citizenArray = citizenKeys.toArray(new String[0]); 
	  	  	
	  	  	for(int i = 0; i < citizenArray.length; i++){
		  	  	storage.add(citizenArray[i]);
	  	  		storageName.put(citizenArray[i], plugin.config.getString("TownStorage.Citizen." + citizenArray[i] + ".Display"));
		  	  	storageLevel.put(plugin.config.getString("TownStorage.Citizen." + citizenArray[i] + ".Display"), plugin.config.getInt("TownStorage.Citizen." + citizenArray[i] + ".Level"));
		  	  	storageAmount.put(plugin.config.getString("TownStorage.Citizen." + citizenArray[i] + ".Display"), plugin.config.getDouble("TownStorage.Citizen." + citizenArray[i] + ".Amount"));
	  	  	}
		}
		
		if(allySection != null){
			Set<String> allyKeys = allySection.getKeys(false);
	  	  	String[] allyArray = allyKeys.toArray(new String[0]); 
	  	  	
	  	  	for(int i = 0; i < allyArray.length; i++){
		  	  	storage.add(allyArray[i]);
	  	  		storageName.put(allyArray[i], plugin.config.getString("TownStorage.Ally." + allyArray[i] + ".Display"));
		  	  	storageLevel.put(plugin.config.getString("TownStorage.Ally." + allyArray[i] + ".Display"), plugin.config.getInt("TownStorage.Ally." + allyArray[i] + ".Level"));
		  	  	storageAmount.put(plugin.config.getString("TownStorage.Ally." + allyArray[i] + ".Display"), plugin.config.getDouble("TownStorage.Ally." + allyArray[i] + ".Amount"));
	  	  	}
		}
	    
	    for(int i = 0; i <= inventory.getSize(); i++) {
	    	stack = inventory.getItem(i);
	    	if(stack != null) {
	    		itemName = stack.getType().name();
		    	itemAmount = stack.getAmount();
		    	if(storage.contains(stack.getType().name())){
		    		if(this.ressourceStorage.get(storageName.get(itemName)) == null) {
		    			this.ressourceStorage.put(storageName.get(itemName), 0);
		    		}
		    		this.ressourceStorage.put(storageName.get(itemName), this.ressourceStorage.get(storageName.get(itemName)) + itemAmount);
		    		player.sendMessage("Du hast " + itemAmount + " " + storageName.get(itemName) + " eingelagert.");
		    		inventory.setItem(i, null);
		    	}
	    	}		    	
	    }
	    saveToFile();
		return;
	}
	
	/**
	 * Sendet eine Nachricht an alle aktiven Stadtmitglieder
	 * @return void
	 */
	public void sendTownMessage(String message) {

		for(int i = 0; i < this.townMembers.size(); i++) {
			if(plugin.getPlayerByName(this.townMembers.get(i)) != null) {
				plugin.getPlayerByName(this.townMembers.get(i)).sendMessage(ChatColor.DARK_GREEN + message);
			}
		}
		
		return;
	}
	
	/**
	 * Sendet eine Nachricht an alle aktiven Stadtmitglieder
	 * @return void
	 */
	public void expandTown(Player sender) {

		List<String> neededBuildings = null;
		
		if(townLevel == 1) {
			neededBuildings = plugin.config.getStringList("TownUpgrade.Level2");
		}
		if(townLevel == 2) {
			neededBuildings = plugin.config.getStringList("TownUpgrade.Level3");
		}
		if(townLevel == 3) {
			neededBuildings = plugin.config.getStringList("TownUpgrade.Level4");
		}
		if(townLevel == 4) {
			neededBuildings = plugin.config.getStringList("TownUpgrade.Level5");
		}
		if(townLevel == 5) {
			neededBuildings = plugin.config.getStringList("TownUpgrade.Level6");
		}
		if(townLevel == 6) {
			neededBuildings = plugin.config.getStringList("TownUpgrade.Level7");
		}
		if(townLevel == 7) {
			neededBuildings = plugin.config.getStringList("TownUpgrade.Level8");
		}
		
		int check = 0;
		for(int i = 0; i < neededBuildings.size(); i++) {
			if(townBuildings.contains(neededBuildings.get(i)))
				check++;
		}
		
		if(check == neededBuildings.size()) {
			townLevel++;
			townDescription = plugin.config.getString("TownLevel.Level" + townLevel + ".Description");
			townPlotLimit = plugin.config.getInt("TownLevel.Level" + townLevel + ".PlotLimit");
			townProductionLimit = plugin.config.getInt("TownLevel.Level" + townLevel + ".ProductionLimit");
			saveToFile();
			sendTownMessage(ChatColor.DARK_GREEN + "Deine Stadt ist nun auf Stufe " + ChatColor.BLUE + townLevel + ChatColor.DARK_GREEN + " und trägt die Bezeichnung " + ChatColor.GREEN + townDescription + ".");
		} else {
			sender.sendMessage(ChatColor.DARK_RED + "Deiner Stadt fehlt eines der benötigten Gebäude:");
			String output = "";
			for(int i = 0; i < neededBuildings.size(); i++) {
				if(townBuildings.contains(neededBuildings.get(i))) {
					output += ChatColor.GREEN + neededBuildings.get(i) + ",";
				} else {
					output += ChatColor.RED + neededBuildings.get(i) + ",";
				}
			}
			sender.sendMessage(output);
		}
		
		return;
	}
	
	/**
	 * Sendet eine Nachricht an alle aktiven Stadtmitglieder
	 * @return 
	 * @return void
	 */
	public boolean hasBuilding(String building) {

		if(townBuildings.contains(building))
			return true;
		
		return false;
	}
	
	/**
	 * Startet die Berechnung des Upkeeps und gibt bei Spielerinitialisierung die Werte aus
	 * @return void
	 */
	public void runUpkeepCalculation(Player sender, boolean freeTown) {
	    
		Map<String, Double> upkeep = new HashMap<String, Double>();
		
		List<String> storage = plugin.nSCore.getRessourceList();
		Map<String, String> storageName = plugin.nSCore.getRessourceName();
		Map<String, Integer> storageLevel = plugin.nSCore.getRessourceLevel();
		Map<String, Double> storageAmount = plugin.nSCore.getRessourceAmount();
		Map<String, String> storageType = plugin.nSCore.getRessourceType();
		
		double tempCost = 0;
		double warCost = 0;
		double allyCost = 0;
		double allyRightsCost = 0;
		double enemyRightsCost = 0;
		double townLevelBonus = 0;
		//double townGuardCost = 0;
		
		warCost = 1 + (townEnemies.size() * plugin.config.getDouble("System.UpkeepCalculation.EnemyFactor", 0.5));
		allyCost = 1 + (townAllies.size() * plugin.config.getDouble("System.UpkeepCalculation.AllyFactor", 0.15));
		townLevelBonus = plugin.config.getDouble("System.UpkeepCalculation.TownLevel." + townLevel, 1);
		/*
		if(townGuard == true) {
			townGuardCost = plugin.config.getDouble("System.UpkeepCalculation.TownGuard", 1.25);
		} else {
			townGuardCost = 1;
		}
		*/
		if(townPermissionAllyBreak == true || townPermissionAllyBuild == true || townPermissionAllyCraft == true) {
			allyRightsCost = plugin.config.getDouble("System.UpkeepCalculation.Ally-Rights", 1.5);
		} else {
			allyRightsCost = 1;
		}
		if(townPermissionOutsiderBreak == true || townPermissionOutsiderBuild == true || townPermissionOutsiderCraft == true) {
			enemyRightsCost = plugin.config.getDouble("System.UpkeepCalculation.Enemy-Rights", 2.0);
		} else {
			enemyRightsCost = 1;
		}
		for(int i = 0; i < storage.size(); i++) {
			if(storageType.get(storageName.get(storage.get(i))).equalsIgnoreCase("Plot")) {
				if(storageLevel.get(storageName.get(storage.get(i))) <= townLevel) {
					tempCost = ((townLevel * storageAmount.get(storageName.get(storage.get(i)))) + (townPlot * plugin.config.getDouble("System.UpkeepCalculation.Plot", 0.1) * storageAmount.get(storageName.get(storage.get(i))))) * allyCost * warCost * allyRightsCost * enemyRightsCost * townLevelBonus * plugin.config.getInt("System.UpkeepIntervalInDays", 1);
					upkeep.put(storageName.get(storage.get(i)), tempCost);
					tempCost = 0;
				}
			}
			if(storageType.get(storageName.get(storage.get(i))).equalsIgnoreCase("Production")) {
				if(storageLevel.get(storageName.get(storage.get(i))) <= townLevel) {
					tempCost = ((townLevel * storageAmount.get(storageName.get(storage.get(i)))) + (townProduction * plugin.config.getDouble("System.UpkeepCalculation.Production", 1.0) * storageAmount.get(storageName.get(storage.get(i))))) * allyCost * warCost * allyRightsCost * enemyRightsCost * townLevelBonus * plugin.config.getInt("System.UpkeepIntervalInDays", 1);
					upkeep.put(storageName.get(storage.get(i)), tempCost);
					tempCost = 0;
				}
			}
			if(storageType.get(storageName.get(storage.get(i))).equalsIgnoreCase("Citizen")) {
				if(storageLevel.get(storageName.get(storage.get(i))) <= townLevel) {
					tempCost = ((townLevel * storageAmount.get(storageName.get(storage.get(i)))) + (townMembers.size() * plugin.config.getDouble("System.UpkeepCalculation.Citizen", 2.0) * storageAmount.get(storageName.get(storage.get(i))))) * allyCost * warCost * allyRightsCost * enemyRightsCost * townLevelBonus * plugin.config.getInt("System.UpkeepIntervalInDays", 1);
					upkeep.put(storageName.get(storage.get(i)), tempCost);
					tempCost = 0;
				}
			}
			if(storageType.get(storageName.get(storage.get(i))).equalsIgnoreCase("Ally")) {
				if(storageLevel.get(storageName.get(storage.get(i))) <= townLevel) {
					tempCost = ((townLevel * storageAmount.get(storageName.get(storage.get(i)))) + (townAllies.size() * plugin.config.getDouble("System.UpkeepCalculation.Ally", 2.0) * storageAmount.get(storageName.get(storage.get(i))))) * allyCost * warCost * allyRightsCost * enemyRightsCost * townLevelBonus * plugin.config.getInt("System.UpkeepIntervalInDays", 1);
					upkeep.put(storageName.get(storage.get(i)), tempCost);
					tempCost = 0;
				}
			}			
		}
		
		if(sender == null) {
			if(freeTown == true) {
				runUpkeepPayment(upkeep, true);
			} else {
				runUpkeepPayment(upkeep, false);
			}			
		} else {
			int tage = 1000;
			for(Map.Entry<String, Double> entry : upkeep.entrySet()){
				if(new Double(ressourceStorage.get(entry.getKey()) / entry.getValue()).intValue() <= tage) {
					tage = new Double(ressourceStorage.get(entry.getKey()) / entry.getValue()).intValue();
				}
				
			}
			sender.sendMessage(ChatColor.DARK_GREEN + "+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~");
			sender.sendMessage(ChatColor.DARK_GREEN + "Stadterhaltungskosten von " + getName());
			sender.sendMessage(ChatColor.DARK_GREEN + "Intervall: " + ChatColor.BLUE + "" + plugin.config.getInt("System.UpkeepIntervalInDays", 1) + ChatColor.DARK_GREEN + " Tag(e)");
			sender.sendMessage(ChatColor.DARK_GREEN + "Bezahlbare Tage: " + ChatColor.BLUE + "" + (tage * plugin.config.getInt("System.UpkeepIntervalInDays", 1)));
			sender.sendMessage(ChatColor.DARK_GREEN + "+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~");
			
			for(Map.Entry<String, Double> entry : upkeep.entrySet()){
				if(ressourceStorage.get(entry.getKey()) >= entry.getValue()) {
					sender.sendMessage(ChatColor.DARK_GREEN + entry.getKey() + ": " + ChatColor.BLUE + Math.round(entry.getValue()) + ChatColor.DARK_GRAY + " (" + ressourceStorage.get(entry.getKey()) + ")");
				} else {
					sender.sendMessage(ChatColor.DARK_GREEN + entry.getKey() + ": " + ChatColor.RED + Math.round(entry.getValue()) + ChatColor.DARK_GRAY + " (" + ressourceStorage.get(entry.getKey()) + ")");
				}
				
			}
			sender.sendMessage(ChatColor.DARK_GREEN + "+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~+~");
		}
		return;
	}
	
	/**
	 * Startet die Prozedur zum Eintreiben des Upkeeps
	 * @param upkeep 
	 * @return void
	 */
	public void runUpkeepPayment(Map<String, Double> upkeep, boolean freeTown) {
	    
		boolean negativeCheck = false;
		
		if(freeTown == false) {
			
			for(Map.Entry<String, Double> entry : upkeep.entrySet()){
				if(ressourceStorage.get((entry.getKey())) >= (entry.getValue() * townLevel) * -2) {
					ressourceStorage.put(entry.getKey(), (int) (ressourceStorage.get((entry.getKey())) - Math.round(entry.getValue())));
				}

				if(ressourceStorage.get(entry.getKey()) < 0) {
					if(negativeCheck == false) {
						negativeCheck = true;
					}
				}
			}

			if(negativeCheck == true && !townMayor.contains("Gouverneur")) {
				sendTownMessage(ChatColor.DARK_RED + "Es war eurer Stadt nicht möglich die Unterhaltskosten zu bezahlen.");
				sendTownMessage(ChatColor.DARK_RED + "Ab sofort übernimmt ein Gouverneur die Amtsgeschäfte.");
				setFormerMayor(townMayor);
				setMayor("Gouverneur Monroe");
				setDiplomat("");
				townAssistants.clear();
				this.townPermissionOutsiderBreak = false;
				this.townPermissionOutsiderBuild = false;
				this.townPermissionOutsiderCraft = false;
				this.townPermissionOutsiderInteract = false;
				this.townPermissionMemberBreak = plugin.config.getBoolean("Default.Permission.Member.Break");
				this.townPermissionMemberBuild = true;
				this.townPermissionMemberCraft = true;
				this.townPermissionMemberInteract = true;
				this.townPermissionAllyBreak = false;
				this.townPermissionAllyBuild = false;
				this.townPermissionAllyCraft = false;
				this.townPermissionAllyInteract = false;
			}
			
		} else {
			
			for(Map.Entry<String, Double> entry : upkeep.entrySet()){
				if(ressourceStorage.get((entry.getKey())) < entry.getValue()) {
					if(negativeCheck == false) {
						negativeCheck = true;
					}
				}
			}

			if(negativeCheck == true && townMayor.contains("Gouverneur ")) {
				sendTownMessage(ChatColor.DARK_RED + "Der Lagerbestand der Stadt reicht nicht aus um die Stadt freizukaufen.");
				sendTownMessage(ChatColor.DARK_RED + "Bitte überprüft eure Bestände.");
			}

			if(negativeCheck == false && townMayor.contains("Gouverneur ")) {
				sendTownMessage(ChatColor.DARK_GREEN + "Ihr habt erfolgreich eure Stadt aus der Verwaltung freigekauft.");
				setMayor(townFormerMayor);
				setFormerMayor("");
				runUpkeepPayment(upkeep, false);
				setUpkeepTime();
			}
			
		}	
		
	    saveToFile();
		return;
	}
	
	private void initializeMySQL() {
		
		MySQL mysql = new MySQL();
		mysql.connect();
		mysql.insert("INSERT INTO NewSettlers_Town (ID, Name, Stufe, Bezeichnung, Oeffentlich, Stadtwache, PlotLimit, PlotAktuell, ProduktionLimit, ProduktionAktuell, Reichspunkte) VALUES (NULL, '" + townName + "', " + townLevel + ", '" + townDescription + "', " + townOpen + ", " + townGuard + ", " + townPlotLimit + ", " + townPlot + ", " + townProductionLimit + ", " + townProduction + ", " + townRealmPoints + ")");
		mysql.disconnect();
		
	}
	
	/**
	 * Speichert einen Spieler aus dem Cache in die Datei
	 * @return void
	 */
	private void saveToMySQL() {
		
		MySQL mysql = new MySQL();
		mysql.connect();
		mysql.update("UPDATE NewSettlers_Town SET Name = '" + townName + "', Stufe = " + townLevel + ", Bezeichnung = '" + townDescription + "', Oeffentlich = " + townOpen + ", Stadtwache = " + townGuard + ", PlotLimit = " + townPlotLimit + ", PlotAktuell = " + townPlot + ", ProduktionLimit = " + townProductionLimit + ", ProduktionAktuell = " + townProduction + ", Reichspunkte = " + townRealmPoints + " WHERE Name = '" + townName + "';");
		mysql.disconnect();

	}
	
	/**
	 * Speichert eine Stadt aus dem Cache in die Datei
	 * @return void
	 */
	public void saveToFile() {
		getTown();
		
		town.set("Stadt.System.Welt", townHomeWorld.getName());
		town.set("Stadt.System.HomePlot", parseHomePlot());
		town.set("Stadt.System.Plots", parsePlotList());
		town.set("Stadt.System.Upkeep", townHasUpkeep);
		town.set("Stadt.System.UpkeepTime", townUpkeepTime);
		
		town.set("Stadt.Daten.Name", townName);
		town.set("Stadt.Daten.Stufe", townLevel);
		town.set("Stadt.Daten.Bezeichnung", townDescription);
		town.set("Stadt.Daten.Oeffentlich", townOpen);
		town.set("Stadt.Daten.Stadtwache", townGuard);
		town.set("Stadt.Daten.Plot-Limit", townPlotLimit);
		town.set("Stadt.Daten.Plot-Aktuell", townPlot);
		town.set("Stadt.Daten.Produktions-Limit", townProductionLimit);
		town.set("Stadt.Daten.Produktion-Aktuell", townProduction);
		town.set("Stadt.Daten.Reichspunkte", townRealmPoints);
		town.set("Stadt.Daten.Produktions-Reset", productionSlotResets);
		
		if(townSpawn != null) {
			String spawn = plugin.nSCore.parseLocationToString(townSpawn);
			String[] spawnData = spawn.split(",");

			town.set("Stadt.System.Spawn.Welt", spawnData[0]);
			town.set("Stadt.System.Spawn.X", Integer.parseInt(spawnData[1]));
			town.set("Stadt.System.Spawn.Y", Integer.parseInt(spawnData[2]));
			town.set("Stadt.System.Spawn.Z", Integer.parseInt(spawnData[3]));
		}
		
		
		town.set("Stadt.Einwohner.Buergermeister", townMayor);
		if(townMayorUUID != null && !townMayorUUID.isEmpty()) {
			town.set("Stadt.Einwohner.BuergermeisterUUID", townMayorUUID);
		} else {
			town.set("Stadt.Einwohner.BuergermeisterUUID", "[]");
		}
		town.set("Stadt.Einwohner.Ex-Buergermeister", townFormerMayor);
		if(townFormerMayorUUID != null && !townFormerMayorUUID.isEmpty())
			town.set("Stadt.Einwohner.Ex-BuergermeisterUUID", townFormerMayorUUID);
		town.set("Stadt.Einwohner.Diplomat", townDiplomat);
		if(townDiplomatUUID != null && !townDiplomatUUID.isEmpty())
			town.set("Stadt.Einwohner.DiplomatUUID", townDiplomatUUID);
		town.set("Stadt.Einwohner.Assistent", townAssistants);
		town.set("Stadt.Einwohner.Buerger", townMembers);
		town.set("Stadt.Einwohner.Stadtmitteilung", townMessage);
		
		town.set("Stadt.Permission.Outsider.Break", townPermissionOutsiderBreak);
		town.set("Stadt.Permission.Outsider.Build", townPermissionOutsiderBuild);
		town.set("Stadt.Permission.Outsider.Craft", townPermissionOutsiderCraft);
		town.set("Stadt.Permission.Outsider.Interact", townPermissionOutsiderInteract);
		town.set("Stadt.Permission.Outsider.Protect", townPermissionOutsiderProtect);
		town.set("Stadt.Permission.Member.Break", townPermissionMemberBreak);
		town.set("Stadt.Permission.Member.Build", townPermissionMemberBuild);
		town.set("Stadt.Permission.Member.Craft", townPermissionMemberCraft);
		town.set("Stadt.Permission.Member.Interact", townPermissionMemberInteract);
		town.set("Stadt.Permission.Member.Protect", townPermissionMemberProtect);
		town.set("Stadt.Permission.Ally.Break", townPermissionAllyBreak);
		town.set("Stadt.Permission.Ally.Build", townPermissionAllyBuild);
		town.set("Stadt.Permission.Ally.Craft", townPermissionAllyCraft);
		town.set("Stadt.Permission.Ally.Interact", townPermissionAllyInteract);
		town.set("Stadt.Permission.Ally.Protect", townPermissionAllyProtect);
		
		town.set("Stadt.Cooldown.Ally.Break", this.townCooldownAllyBreak);
		town.set("Stadt.Cooldown.Ally.Build", this.townCooldownAllyBuild);
		town.set("Stadt.Cooldown.Ally.Craft", this.townCooldownAllyCraft);
		town.set("Stadt.Cooldown.Ally.Interact", this.townCooldownAllyInteract);
		town.set("Stadt.Cooldown.Outsider.Break", this.townCooldownOutsiderBreak);
		town.set("Stadt.Cooldown.Outsider.Build", this.townCooldownOutsiderBuild);
		town.set("Stadt.Cooldown.Outsider.Craft", this.townCooldownOutsiderCraft);
		town.set("Stadt.Cooldown.Outsider.Interact", this.townCooldownOutsiderInteract);
		town.set("Stadt.Cooldown.TownGuard", this.townCooldownTownGuard);
		town.set("Stadt.Cooldown.ProductionSlotReset", this.townCooldownProductionSlotReset);
		
		town.set("Stadt.Diplomatie.Alliierte", townAllies);
		town.set("Stadt.Diplomatie.Feinde", townEnemies);
		
		town.set("Stadt.Produktion.Rohstoffe.Steinbruch", productionBuildings.get("Steinbruch"));
		town.set("Stadt.Produktion.Rohstoffe.Lehmgrube", productionBuildings.get("Lehmgrube"));
		town.set("Stadt.Produktion.Rohstoffe.Kohlemine", productionBuildings.get("Kohlemine"));
		town.set("Stadt.Produktion.Rohstoffe.Eisenmine", productionBuildings.get("Eisenmine"));
		town.set("Stadt.Produktion.Rohstoffe.Goldmine", productionBuildings.get("Goldmine"));
		town.set("Stadt.Produktion.Rohstoffe.Smaragdmine", productionBuildings.get("Smaragdmine"));
		town.set("Stadt.Produktion.Rohstoffe.Lapismine", productionBuildings.get("Lapismine"));
		town.set("Stadt.Produktion.Rohstoffe.Redstonemine", productionBuildings.get("Redstonemine"));
		town.set("Stadt.Produktion.Rohstoffe.Diamantmine", productionBuildings.get("Diamantmine"));
		town.set("Stadt.Produktion.Rohstoffe.Marmorsteinbruch", productionBuildings.get("Marmorsteinbruch"));
		town.set("Stadt.Produktion.Rohstoffe.Holzfaellerlager", productionBuildings.get("Holzfaellerlager"));
		town.set("Stadt.Produktion.Rohstoffe.Bauernhof", productionBuildings.get("Bauernhof"));
		
		town.set("Stadt.Produktion.Produkte.Saegemuehle", productionBuildings.get("Saegemuehle"));
		town.set("Stadt.Produktion.Produkte.Schreinerei", productionBuildings.get("Schreinerei"));
		town.set("Stadt.Produktion.Produkte.Bogenmacher", productionBuildings.get("Bogenmacher"));
		town.set("Stadt.Produktion.Produkte.Steinmetz", productionBuildings.get("Steinmetz"));
		town.set("Stadt.Produktion.Produkte.Ziegelei", productionBuildings.get("Ziegelei"));
		town.set("Stadt.Produktion.Produkte.Eisenschmelze", productionBuildings.get("Eisenschmelze"));
		town.set("Stadt.Produktion.Produkte.Goldschmelze", productionBuildings.get("Goldschmelze"));
		town.set("Stadt.Produktion.Produkte.Werkzeugschmied", productionBuildings.get("Werkzeugschmied"));
		town.set("Stadt.Produktion.Produkte.Waffenschmied", productionBuildings.get("Waffenschmied"));
		town.set("Stadt.Produktion.Produkte.Ruestungsschmied", productionBuildings.get("Ruestungsschmied"));
		town.set("Stadt.Produktion.Produkte.Werkstatt", productionBuildings.get("Werkstatt"));
		town.set("Stadt.Produktion.Produkte.Metzgerei", productionBuildings.get("Metzgerei"));
		town.set("Stadt.Produktion.Produkte.Gerberei", productionBuildings.get("Gerberei"));
		town.set("Stadt.Produktion.Produkte.Sattlerei", productionBuildings.get("Sattlerei"));
		town.set("Stadt.Produktion.Produkte.Stallung", productionBuildings.get("Stallung"));
		town.set("Stadt.Produktion.Produkte.Baeckerei", productionBuildings.get("Baeckerei"));
		town.set("Stadt.Produktion.Produkte.Fleischer", productionBuildings.get("Fleischer"));
		town.set("Stadt.Produktion.Produkte.Fischraeucherei", productionBuildings.get("Fischraeucherei"));
		town.set("Stadt.Produktion.Produkte.Taverne", productionBuildings.get("Taverne"));
		town.set("Stadt.Produktion.Produkte.Faerberei", productionBuildings.get("Faerberei"));
		town.set("Stadt.Produktion.Produkte.Schneiderei", productionBuildings.get("Schneiderei"));
		town.set("Stadt.Produktion.Produkte.Karawanserei", productionBuildings.get("Karawanserei"));
		town.set("Stadt.Produktion.Produkte.Glasblaeserei", productionBuildings.get("Glasblaeserei"));
		town.set("Stadt.Produktion.Produkte.Brauerei", productionBuildings.get("Brauerei"));
		town.set("Stadt.Produktion.Produkte.Apotheke", productionBuildings.get("Apotheke"));
		town.set("Stadt.Produktion.Produkte.Buchbinder", productionBuildings.get("Buchbinder"));
		
		town.set("Stadt.Upgrade.Gebaeude", townBuildings);
		
		saveBuildingLocation("Saegemuehle");
		saveBuildingLocation("Schreinerei");
		saveBuildingLocation("Bogenmacher");
		saveBuildingLocation("Steinmetz");
		saveBuildingLocation("Ziegelei");
		saveBuildingLocation("Eisenschmelze");
		saveBuildingLocation("Goldschmelze");
		saveBuildingLocation("Werkzeugschmied");
		saveBuildingLocation("Waffenschmied");
		saveBuildingLocation("Ruestungsschmied");
		saveBuildingLocation("Werkstatt");
		saveBuildingLocation("Metzgerei");
		saveBuildingLocation("Gerberei");
		saveBuildingLocation("Sattlerei");
		saveBuildingLocation("Stallung");
		saveBuildingLocation("Baeckerei");
		saveBuildingLocation("Fleischer");
		saveBuildingLocation("Fischraeucherei");
		saveBuildingLocation("Taverne");
		saveBuildingLocation("Faerberei");
		saveBuildingLocation("Schneiderei");
		saveBuildingLocation("Karawanserei");
		saveBuildingLocation("Glasblaeserei");
		saveBuildingLocation("Brauerei");
		saveBuildingLocation("Apotheke");
		saveBuildingLocation("Buchbinder");		

		List<String> storage = plugin.nSCore.getRessourceList();
		Map<String, String> storageName = plugin.nSCore.getRessourceName();
		
		for(int i = 0; i < storage.size(); i++) {
			town.set("Stadt.Stadtlager.Release.1-0-0." + storageName.get(storage.get(i)), ressourceStorage.get(storageName.get(storage.get(i))));
		}
		
		saveTown();
		saveToMySQL();
	}
	
	/**
	 * L�dt die FileConfiguration des Spielers in eine Variable
	 * @return void
	 */
	private void reloadTown() {
		if (townFile == null) {
			townFile = new File(plugin.getDataFolder(), "/towns/" + townName + ".yml");
		}
		town = YamlConfiguration.loadConfiguration(townFile);
	}

	/**
	 * Liefert die FileConfiguration des Spielers zur�ck
	 * @return FileConfiguration
	 */
	private FileConfiguration getTown() {
		if (town == null) {
			reloadTown();
		}
		return town;
	}
	
	/**
	 * Speichert die FileConfiguration in die Datei
	 * @return void
	 */
	private void saveTown() {
		if (town == null || townFile == null) {
			return;
		}
		try {
			town.save(townFile);
		} catch (IOException ex) {
			Logger.getLogger(JavaPlugin.class.getName()).log(Level.SEVERE, "Could not save config to " + townFile, ex);
		}
	}
	
	/**
	 * Entfernt die Gruppe aus dem System
	 * @return void
	 */
	public void eraseTown() {
		if (townFile == null) {
			return;
		} else {
			for(int i = 0; i < townMembers.size(); i++) {
				this.removePlayerTown(townMembers.get(i));
			}
			townMembers = null;
			plugin.nSCore.removeTownList(this);
			plugin.nSCore.removeHomePlot(townHomePlot);
			for(Map.Entry<String, String> entry : townChunkList.entrySet()) {
				plugin.nSCore.removeChunkInfo(entry.getKey());
			}
			townChunkList = null;
			town = null;
			townFile.delete();
			return;
		}
	}
	
}
