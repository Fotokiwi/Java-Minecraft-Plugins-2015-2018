package org.community.newSettlers.Core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.server.ServerCommandEvent;
import org.community.DatabaseProvider.MySQL.MySQL;
import org.community.newSettlers.newSettlers;
import org.community.newSettlers.Town.Town;
import org.community.newSettlers.Trade.newSettlersTrade;

public class newSettlersCore {
	
	private newSettlers plugin;
	
	private int newSettlersHeartbeatTask = -1;

	private List<Town> townList = new ArrayList<Town>();
	private Map<String, Town> playerTownList = new HashMap<String, Town>();
	public Map<String, Town> globalChunkList = new HashMap<String, Town>();
	private Map<String, Town> globalHomePlotList = new HashMap<String, Town>();
	public Map<Player, Boolean> playerDisplayChunks = new HashMap<Player, Boolean>();
	public Map<Player, Boolean> playerDisplayMap = new HashMap<Player, Boolean>();
	private Map<Player, newSettlersTrade> tradeWindows = new HashMap<Player, newSettlersTrade>();
	public Map<Player, String> tradePartner = new HashMap<Player, String>();
	
	public Map<Player, Boolean> playerHide = new HashMap<Player, Boolean>();
	
	public Map<Player, Long> lastInventoryClick = new HashMap<Player, Long>();
		
	public newSettlersCore(newSettlers plugin) {
		this.plugin = plugin;
	}

	public boolean isNewSettlersHeartbeatTaskRunning() {
		return newSettlersHeartbeatTask != -1;
	}
	
	public void toggleNewSettlersHeartbeat(boolean on) {		
		if (on && !isNewSettlersHeartbeatTaskRunning()) {
			
			newSettlersHeartbeatTask = plugin.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, plugin.nSHeartbeat, 1200L, plugin.config.getLong("Config.Plugin.HeartBeatInMinutes", 2) * 1200L);
				
			if (newSettlersHeartbeatTask == -1) {
				plugin.LogError("error: Heartbeat initialization failed!");
			} else {
				plugin.nSHeartbeat.toggleNewSettlersConfig(true);
				plugin.LogInfo("initialized: Heartbeat");
			}
			
		} else if (!on && isNewSettlersHeartbeatTaskRunning()) {
			plugin.getServer().getScheduler().cancelTask(newSettlersHeartbeatTask);
			plugin.nSHeartbeat.toggleNewSettlersConfig(false);
			newSettlersHeartbeatTask = -1;
		}
	}
	
	public void addTownList(Town town) {
		townList.add(town);
	}
	
	public void removeTownList(Town town) {
		townList.remove(town);
	}
	
	public Town getTownList(int index) {
		return townList.get(index);
	}
	
	public int sizeTownList() {
		return townList.size();
	}
	
	public List<Town> getCompleteTownList() {
		return townList;
	}
	
	public Town getTown(String townName) {
		Town town = null;
		for(int i = 0; i < townList.size(); i++) {
			town = townList.get(i);
			if(town.getName().equalsIgnoreCase(townName)){
				return town;
			}
		}
		town = null;
		return town;
	}
	
	public Town getPlayerTown(Player player) {
		return playerTownList.get(player.getName());
	}
	
	public Town getPlayerTown(OfflinePlayer player) {
		return playerTownList.get(player.getName());
	}
	
	public Town addPlayerTown(String playerName, Town town) {
		return playerTownList.put(playerName, town);
	}
	
	public Town removePlayerTown(String playerName) {
		return playerTownList.put(playerName, null);
	}
	
	public Town getChunkInfo(String chunk) {
		return globalChunkList.get(chunk);
	}
	
	public Town addChunkInfo(String chunk, Town town) {
		return globalChunkList.put(chunk, town);
	}
	
	public Town removeChunkInfo(String chunk) {
		return globalChunkList.remove(chunk);
	}
	
	public void addHomePlot(String chunk, Town town) {
		globalHomePlotList.put(chunk, town);
	}
	
	public void removeHomePlot(String chunk) {
		globalHomePlotList.remove(chunk);
	}
	
	public double getDistanceToHomePlots(Location location) {
		double xCoord;
		double yCoord;
		double zCoord;
		World world;
		Location chunkLocation = null;
		double tempDistance = 0;
		double distance = 50000;
		for(Map.Entry<String, Town> e : globalHomePlotList.entrySet()){			
			String[] coords = e.getKey().split(",");
			world = plugin.getServer().getWorld(coords[0]);
			xCoord = Integer.parseInt(coords[1]) * 16;
			yCoord = 64;
			zCoord = Integer.parseInt(coords[2]) * 16;
			chunkLocation = new Location(world, xCoord, yCoord, zCoord);
			if(location.getWorld() != chunkLocation.getWorld()) {
				tempDistance = 50000;
			} else {
				tempDistance = location.distance(chunkLocation);
			}			
			if(tempDistance < distance)
				distance = tempDistance;
		}
		return distance;
	}
	
	public boolean isAdmin(Player player) {
		if(plugin.config.getList("Config.Admins") != null) {
			if(plugin.config.getList("Config.Admins").contains(player.getUniqueId().toString()))
				return true;
		}
		
		return false;
	}
	
	public boolean isChatAdmin(Player player) {
		if(plugin.config.getList("Config.ChatAdmins") != null) {
			if(plugin.config.getList("Config.ChatAdmins").contains(player.getUniqueId().toString()))
				return true;
		}
		
		return false;
	}
	
	public boolean isMod(Player player) {
		if(plugin.config.getList("Config.Mods") != null) {
			if(plugin.config.getList("Config.Mods").contains(player.getUniqueId().toString()))
				return true;
		}
		
		return false;
	}
	
	public boolean buildingListContains(String building) {
		
		List<?> list = plugin.config.getList("TownUpgrade.Level2");
		String listString = "";
		for(int i = 0; i < list.size(); i++) {
			listString = (String) list.get(i);
			if(listString.equalsIgnoreCase(building))
				return true;
		}
		
		list = plugin.config.getList("TownUpgrade.Level3");
		listString = "";
		for(int i = 0; i < list.size(); i++) {
			listString = (String) list.get(i);
			if(listString.equalsIgnoreCase(building))
				return true;
		}
		
		list = plugin.config.getList("TownUpgrade.Level4");
		listString = "";
		for(int i = 0; i < list.size(); i++) {
			listString = (String) list.get(i);
			if(listString.equalsIgnoreCase(building))
				return true;
		}
		
		list = plugin.config.getList("TownUpgrade.Level5");
		listString = "";
		for(int i = 0; i < list.size(); i++) {
			listString = (String) list.get(i);
			if(listString.equalsIgnoreCase(building))
				return true;
		}
		
		list = plugin.config.getList("TownUpgrade.Level6");
		listString = "";
		for(int i = 0; i < list.size(); i++) {
			listString = (String) list.get(i);
			if(listString.equalsIgnoreCase(building))
				return true;
		}
		
		list = plugin.config.getList("TownUpgrade.Level7");
		listString = "";
		for(int i = 0; i < list.size(); i++) {
			listString = (String) list.get(i);
			if(listString.equalsIgnoreCase(building))
				return true;
		}
		
		list = plugin.config.getList("TownUpgrade.Level8");
		listString = "";
		for(int i = 0; i < list.size(); i++) {
			listString = (String) list.get(i);
			if(listString.equalsIgnoreCase(building))
				return true;
		}
		
		return false;
	}
	
	public List<String> getBuildingList() {
		
		List<String> buildingList = new ArrayList<String>();
		
		List<?> list = plugin.config.getList("TownUpgrade.Level2");
		for(int i = 0; i < list.size(); i++) {
			buildingList.add("" + list.get(i));
		}
		
		list = plugin.config.getList("TownUpgrade.Level3");
		for(int i = 0; i < list.size(); i++) {
			buildingList.add("" + list.get(i));
		}
		
		list = plugin.config.getList("TownUpgrade.Level4");
		for(int i = 0; i < list.size(); i++) {
			buildingList.add("" + list.get(i));
		}
		
		list = plugin.config.getList("TownUpgrade.Level5");
		for(int i = 0; i < list.size(); i++) {
			buildingList.add("" + list.get(i));
		}
		
		list = plugin.config.getList("TownUpgrade.Level6");
		for(int i = 0; i < list.size(); i++) {
			buildingList.add("" + list.get(i));
		}
		
		list = plugin.config.getList("TownUpgrade.Level7");
		for(int i = 0; i < list.size(); i++) {
			buildingList.add("" + list.get(i));
		}
		
		list = plugin.config.getList("TownUpgrade.Level8");
		for(int i = 0; i < list.size(); i++) {
			buildingList.add("" + list.get(i));
		}
		
		return buildingList;
	}
	
	public List<String> getRessourceList() {
		ConfigurationSection plotSection = plugin.config.getConfigurationSection("TownStorage.Plot");
		ConfigurationSection productionSection = plugin.config.getConfigurationSection("TownStorage.Production");
		ConfigurationSection citizenSection = plugin.config.getConfigurationSection("TownStorage.Citizen");
		ConfigurationSection allySection = plugin.config.getConfigurationSection("TownStorage.Ally");
		
		List<String> storage = new ArrayList<String>();
		
		if(plotSection != null){
			Set<String> plotKeys = plotSection.getKeys(false);
	  	  	String[] plotArray = plotKeys.toArray(new String[0]); 
	  	  	
	  	  	for(int i = 0; i < plotArray.length; i++){
		  	  	if(storage.contains(plotArray[i])) {
		  	  		
		  	  	} else {
		  	  		storage.add(plotArray[i]);
		  	  	}	  	  		
	  	  	}
		}
		
		if(productionSection != null){
			Set<String> productionKeys = productionSection.getKeys(false);
	  	  	String[] productionArray = productionKeys.toArray(new String[0]); 
	  	  	
	  	  	for(int i = 0; i < productionArray.length; i++){
	  	  		if(storage.contains(productionArray[i])) {

	  	  		} else {
	  	  			storage.add(productionArray[i]);
	  	  		}	
		  	}
		}
		
		if(citizenSection != null){
			Set<String> citizenKeys = citizenSection.getKeys(false);
	  	  	String[] citizenArray = citizenKeys.toArray(new String[0]); 
	  	  	
	  	  	for(int i = 0; i < citizenArray.length; i++){
	  	  		if(storage.contains(citizenArray[i])) {

	  	  		} else {
	  	  			storage.add(citizenArray[i]);
	  	  		}	
		  	}
		}
		
		if(allySection != null){
			Set<String> allyKeys = allySection.getKeys(false);
	  	  	String[] allyArray = allyKeys.toArray(new String[0]); 
	  	  	
	  	  	for(int i = 0; i < allyArray.length; i++){
	  	  		if(storage.contains(allyArray[i])) {

	  	  		} else {
	  	  			storage.add(allyArray[i]);
	  	  		}
	  	  	}
		}
		
		return storage;
	}
	
	public Map<String, String> getRessourceName() {
		ConfigurationSection plotSection = plugin.config.getConfigurationSection("TownStorage.Plot");
		ConfigurationSection productionSection = plugin.config.getConfigurationSection("TownStorage.Production");
		ConfigurationSection citizenSection = plugin.config.getConfigurationSection("TownStorage.Citizen");
		ConfigurationSection allySection = plugin.config.getConfigurationSection("TownStorage.Ally");
		
		Map<String, String> storageName = new HashMap<String, String>();
		
		if(plotSection != null){
			Set<String> plotKeys = plotSection.getKeys(false);
	  	  	String[] plotArray = plotKeys.toArray(new String[0]); 
	  	  	
	  	  	for(int i = 0; i < plotArray.length; i++){
	  	  		storageName.put(plotArray[i], plugin.config.getString("TownStorage.Plot." + plotArray[i] + ".Display"));
	  	  	}
		}
		
		if(productionSection != null){
			Set<String> productionKeys = productionSection.getKeys(false);
	  	  	String[] productionArray = productionKeys.toArray(new String[0]); 
	  	  	
	  	  	for(int i = 0; i < productionArray.length; i++){
	  	  		storageName.put(productionArray[i], plugin.config.getString("TownStorage.Production." + productionArray[i] + ".Display"));
	  	  	}
		}
		
		if(citizenSection != null){
			Set<String> citizenKeys = citizenSection.getKeys(false);
	  	  	String[] citizenArray = citizenKeys.toArray(new String[0]); 
	  	  	
	  	  	for(int i = 0; i < citizenArray.length; i++){
	  	  		storageName.put(citizenArray[i], plugin.config.getString("TownStorage.Citizen." + citizenArray[i] + ".Display"));
	  	  	}
		}
		
		if(allySection != null){
			Set<String> allyKeys = allySection.getKeys(false);
	  	  	String[] allyArray = allyKeys.toArray(new String[0]); 
	  	  	
	  	  	for(int i = 0; i < allyArray.length; i++){
	  	  		storageName.put(allyArray[i], plugin.config.getString("TownStorage.Ally." + allyArray[i] + ".Display"));
	  	  	}
		}
		
		return storageName;
	}
	
	public Map<String, Integer> getRessourceLevel() {
		ConfigurationSection plotSection = plugin.config.getConfigurationSection("TownStorage.Plot");
		ConfigurationSection productionSection = plugin.config.getConfigurationSection("TownStorage.Production");
		ConfigurationSection citizenSection = plugin.config.getConfigurationSection("TownStorage.Citizen");
		ConfigurationSection allySection = plugin.config.getConfigurationSection("TownStorage.Ally");
		
		Map<String, Integer> storageLevel = new HashMap<String, Integer>();
		
		if(plotSection != null){
			Set<String> plotKeys = plotSection.getKeys(false);
	  	  	String[] plotArray = plotKeys.toArray(new String[0]); 
	  	  	
	  	  	for(int i = 0; i < plotArray.length; i++){
		  	  	storageLevel.put(plugin.config.getString("TownStorage.Plot." + plotArray[i] + ".Display"), plugin.config.getInt("TownStorage.Plot." + plotArray[i] + ".Level"));
	  	  	}
		}
		
		if(productionSection != null){
			Set<String> productionKeys = productionSection.getKeys(false);
	  	  	String[] productionArray = productionKeys.toArray(new String[0]); 
	  	  	
	  	  	for(int i = 0; i < productionArray.length; i++){
		  	  	storageLevel.put(plugin.config.getString("TownStorage.Production." + productionArray[i] + ".Display"), plugin.config.getInt("TownStorage.Production." + productionArray[i] + ".Level"));
	  	  	}
		}
		
		if(citizenSection != null){
			Set<String> citizenKeys = citizenSection.getKeys(false);
	  	  	String[] citizenArray = citizenKeys.toArray(new String[0]); 
	  	  	
	  	  	for(int i = 0; i < citizenArray.length; i++){
		  	  	storageLevel.put(plugin.config.getString("TownStorage.Citizen." + citizenArray[i] + ".Display"), plugin.config.getInt("TownStorage.Citizen." + citizenArray[i] + ".Level"));
	  	  	}
		}
		
		if(allySection != null){
			Set<String> allyKeys = allySection.getKeys(false);
	  	  	String[] allyArray = allyKeys.toArray(new String[0]); 
	  	  	
	  	  	for(int i = 0; i < allyArray.length; i++){
		  	  	storageLevel.put(plugin.config.getString("TownStorage.Ally." + allyArray[i] + ".Display"), plugin.config.getInt("TownStorage.Ally." + allyArray[i] + ".Level"));
	  	  	}
		}
		
		return storageLevel;
	}
	
	public Map<String, Double> getRessourceAmount() {
		ConfigurationSection plotSection = plugin.config.getConfigurationSection("TownStorage.Plot");
		ConfigurationSection productionSection = plugin.config.getConfigurationSection("TownStorage.Production");
		ConfigurationSection citizenSection = plugin.config.getConfigurationSection("TownStorage.Citizen");
		ConfigurationSection allySection = plugin.config.getConfigurationSection("TownStorage.Ally");
		
		Map<String, Double> storageAmount = new HashMap<String, Double>();
		
		if(plotSection != null){
			Set<String> plotKeys = plotSection.getKeys(false);
	  	  	String[] plotArray = plotKeys.toArray(new String[0]); 
	  	  	
	  	  	for(int i = 0; i < plotArray.length; i++){
	  	  		storageAmount.put(plugin.config.getString("TownStorage.Plot." + plotArray[i] + ".Display"), plugin.config.getDouble("TownStorage.Plot." + plotArray[i] + ".Amount"));
	  	  	}
		}
		
		if(productionSection != null){
			Set<String> productionKeys = productionSection.getKeys(false);
	  	  	String[] productionArray = productionKeys.toArray(new String[0]); 
	  	  	
	  	  	for(int i = 0; i < productionArray.length; i++){
		  	  	storageAmount.put(plugin.config.getString("TownStorage.Production." + productionArray[i] + ".Display"), plugin.config.getDouble("TownStorage.Production." + productionArray[i] + ".Amount"));
	  	  	}
		}
		
		if(citizenSection != null){
			Set<String> citizenKeys = citizenSection.getKeys(false);
	  	  	String[] citizenArray = citizenKeys.toArray(new String[0]); 
	  	  	
	  	  	for(int i = 0; i < citizenArray.length; i++){
		  	  	storageAmount.put(plugin.config.getString("TownStorage.Citizen." + citizenArray[i] + ".Display"), plugin.config.getDouble("TownStorage.Citizen." + citizenArray[i] + ".Amount"));
	  	  	}
		}
		
		if(allySection != null){
			Set<String> allyKeys = allySection.getKeys(false);
	  	  	String[] allyArray = allyKeys.toArray(new String[0]); 
	  	  	
	  	  	for(int i = 0; i < allyArray.length; i++){
		  	  	storageAmount.put(plugin.config.getString("TownStorage.Ally." + allyArray[i] + ".Display"), plugin.config.getDouble("TownStorage.Ally." + allyArray[i] + ".Amount"));
	  	  	}
		}
		
		return storageAmount;
	}
	
	public Map<String, String> getRessourceType() {
		ConfigurationSection plotSection = plugin.config.getConfigurationSection("TownStorage.Plot");
		ConfigurationSection productionSection = plugin.config.getConfigurationSection("TownStorage.Production");
		ConfigurationSection citizenSection = plugin.config.getConfigurationSection("TownStorage.Citizen");
		ConfigurationSection allySection = plugin.config.getConfigurationSection("TownStorage.Ally");
		
		Map<String, String> storageType = new HashMap<String, String>();
		
		if(plotSection != null){
			Set<String> plotKeys = plotSection.getKeys(false);
	  	  	String[] plotArray = plotKeys.toArray(new String[0]); 
	  	  	
	  	  	for(int i = 0; i < plotArray.length; i++){
	  	  		storageType.put(plugin.config.getString("TownStorage.Plot." + plotArray[i] + ".Display"), "Plot");
	  	  	}
		}
		
		if(productionSection != null){
			Set<String> productionKeys = productionSection.getKeys(false);
	  	  	String[] productionArray = productionKeys.toArray(new String[0]); 
	  	  	
	  	  	for(int i = 0; i < productionArray.length; i++){
		  	  	storageType.put(plugin.config.getString("TownStorage.Production." + productionArray[i] + ".Display"), "Production");
	  	  	}
		}
		
		if(citizenSection != null){
			Set<String> citizenKeys = citizenSection.getKeys(false);
	  	  	String[] citizenArray = citizenKeys.toArray(new String[0]); 
	  	  	
	  	  	for(int i = 0; i < citizenArray.length; i++){
		  	  	storageType.put(plugin.config.getString("TownStorage.Citizen." + citizenArray[i] + ".Display"), "Citizen");
	  	  	}
		}
		
		if(allySection != null){
			Set<String> allyKeys = allySection.getKeys(false);
	  	  	String[] allyArray = allyKeys.toArray(new String[0]); 
	  	  	
	  	  	for(int i = 0; i < allyArray.length; i++){
		  	  	storageType.put(plugin.config.getString("TownStorage.Ally." + allyArray[i] + ".Display"), "Ally");
	  	  	}
		}
		
		return storageType;
	}
	
	public void addTradeWindow(Player player, newSettlersTrade trade) {
		tradeWindows.put(player, trade);
	}
	
	public newSettlersTrade getTradeWindow(Player player) {
		return tradeWindows.get(player);
	}
	
	public boolean isTradeWindow(Player player) {
		if(tradeWindows.get(player) != null){
			//plugin.getServer().broadcastMessage("Das Fenster ist registriert!");
			return true;
		}
		
		return false;
	}
	
	public void removeTradeWindow(Player player, newSettlersTrade trade) {
		//plugin.getServer().broadcastMessage("Das Fenster wurde entfernt!");
		tradeWindows.remove(player);
	}
	/**
	* Notfall Port Routine
	* @param player Spieler
	* Startet einen Scheduler für den Cooldown
	*
	*/
	public void notfall(Player player){
		Location loc = player.getLocation();
		boolean allowteleport = false;
		
		if(player.getWorld().getName().equalsIgnoreCase("Dungeons")) {
			player.sendMessage("In Dungeons darfst du keinen Notfall-Teleport einsetzen.");
			return;
		}
		
		if(plugin.user.getString("Spieler." + player.getUniqueId().toString() + ".Notfall-Cooldown") == null){
			allowteleport = true;
		}else {
			int hdelay = plugin.config.getInt("System.Homereset");
			if(System.currentTimeMillis() <= (plugin.user.getLong("Spieler." + player.getUniqueId().toString() + ".Notfall-Cooldown") + (hdelay*1000))){
				allowteleport = false;
			} else{
				allowteleport = true;
			}
		}
		if(allowteleport==true){
			player.sendMessage("Notfallteleport wird in 30 Sekunden aktiviert!");
			//notfallexecute(player, loc);
			plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new newSettlersNotfall(plugin, player, loc), 620L);
		}else{
			player.sendMessage("Notfallteleport noch nicht bereit! (" + ((plugin.user.getLong("Spieler." + player.getUniqueId().toString() + ".Notfall-Cooldown") + (plugin.config.getInt("System.Homereset")*1000) - System.currentTimeMillis()) / 1000 / 60) + "min)");
		}
	}
	/**
	 * Führt den notfall teleport aus wenn der Spieler sich nicht bewegt hat
	 * @param player Spieler wird von notfall übergeben
	 * @param location Location wird von notfall übergeben
	 * @return 
	 */
	
	/*public void notfallexecute (final Player player, final Location location){
		new BukkitRunnable(){

			@Override
			public void run() {
				Location currentLocation = player.getLocation();
				if(location.distance(currentLocation)>0){
					player.sendMessage("Du musst still stehen um dich wegporten zu können");
					return;
				}else{
					plugin.user.set("Spieler." + player.getUniqueId().toString() + ".Notfall-Cooldown", System.currentTimeMillis());

					Map<Location, Block> blockList = plugin.nSBFU.getBlocksInRange(currentLocation, 15, 0, 15);
					//plugin.getServer().broadcastMessage(blockList.toString());
					World world = player.getWorld();

					for(Map.Entry<Location, Block> e : blockList.entrySet()){			
						Block tempBlock = world.getHighestBlockAt(e.getKey());
						if(tempBlock.getType() != Material.LAVA && tempBlock.getType() != Material.STATIONARY_LAVA) {
							Location tele = tempBlock.getLocation().add(0, 1, 0);
							if(currentLocation.distance(tele) > 7) {
								player.teleport(tele);
								Bukkit.broadcastMessage("Der Spieler "+ChatColor.RED+player.getName()+ChatColor.WHITE+" hat den Notfall Teleport eingesetzt");
								break;
							}

						}
					}

				}

			}

		}.runTaskLater(plugin, 620L);

	}*/
	
	public void gethome(Player player){
         		
		Location newLocation = new Location(plugin.getServer().getWorld(plugin.config.getString("Locations.Default.World")), plugin.config.getDouble("Locations.Default.X"), plugin.config.getDouble("Locations.Default.Y"), plugin.config.getDouble("Locations.Default.Z")); 
		if(plugin.user.getString("User." + player.getName()) == null){
			plugin.user.set("User." + player.getName(), System.currentTimeMillis());
			  //saveUser();
			  player.sendMessage("Notfall teleport aktiviert");
			  player.teleport(newLocation);
	    } else {
			int hdelay = plugin.config.getInt("Config.Homereset");
		    if(System.currentTimeMillis() <= (plugin.user.getLong("User." + player.getName()) + (hdelay*1000))){
		      player.sendMessage("Notfall Teleport noch nicht bereit!");
		    } else{
		      player.sendMessage("Notfall teleport aktiviert!");
		      player.teleport(newLocation);	
		      plugin.user.set("User." + player.getName(), System.currentTimeMillis());
		      //saveUser();
		    }
		}	      
    }
	
	public void setTempSpawn(Player player, Long duration){
         		
		Location tempLocation = player.getLocation(); 
		
		plugin.user.set("Spieler." + player.getUniqueId().toString() + ".Tempspawn.Location", parseLocationToString(tempLocation));
		plugin.user.set("Spieler." + player.getUniqueId().toString() + ".Tempspawn.Timer", System.currentTimeMillis());
		plugin.user.set("Spieler." + player.getUniqueId().toString() + ".Tempspawn.Duration", duration);
    }		
	
	public String parseLocationToString(Location location) {
		String stringLocation = null;
		stringLocation = location.getWorld().getName();
		stringLocation += "," + location.getBlockX();
		stringLocation += "," + location.getBlockY();
		stringLocation += "," + location.getBlockZ();
		return stringLocation;
	}	
	
	public Location parseStringToLocation(String string) {
		Location location = null;
		String[] data = string.split(",");
		location = new Location(plugin.getServer().getWorld(data[0]), Double.parseDouble(data[1]), Double.parseDouble(data[2]), Double.parseDouble(data[3]));
		return location;
	}
	
	public void setSpawn(Location location, Player player){
		Town town = getPlayerTown(player);
	    if(town.isMayor(player)){   
	      if (getChunkInfo(location.getWorld().getName() + "," + location.getChunk().getX() + "," + location.getChunk().getZ()) == town){
	    	  town.setTownSpawn(location);
	    	  player.sendMessage(ChatColor.DARK_GREEN + "Spawnpunkt der Stadt: "+ChatColor.GOLD + town.getName() + ChatColor.DARK_GREEN + " erfolgreich gesetzt");
	      }
	      else{
	    	  player.sendMessage(ChatColor.DARK_RED + "Der Spawnpunkt kann nur innerhalb der Stadt gesetzt werden. ");
	      }
	    }
	    else{
	    	player.sendMessage(ChatColor.DARK_RED + "Für diese Einstellung benötigst du Anführerrechte.");
	    }
		
	}
	
	public boolean handleCommand(newSettlers plugin, /*Player player,*/ String s) {
		ServerCommandEvent sce = new ServerCommandEvent(Bukkit.getConsoleSender(), s);
		Bukkit.getPluginManager().callEvent(sce);
		plugin.getServer().dispatchCommand(Bukkit.getConsoleSender(), sce.getCommand());
		plugin.LogInfo("executing Console-Command '"+s+"'");
		return true;
		/*if(s.length()<=1) return false;
		String[] commandList = s.split(";");
		CraftServer cs = (CraftServer)plugin.getServer();
		String command = "";
		
		for (int i=0; i < commandList.length; i++){
			String[] com = commandList[i].split(" ");
			for(String arg : com) {
				if(command.length()>0) {
					if("*".equals(arg)) arg = player.getName();
					command += " " + arg;
				}
				else {
					command += arg.substring(1);	
				}
			}
			plugin.LogError("executing Console-Command '"+command+"'");
			if (!cs.dispatchCommand(cs.getServer().console, command)) {
				plugin.LogError("Could not execute Console-Command '"+command+"'");
				return false;
			}
			command = "";
		}
		return true;*/
	}
	
	public void initializeMySQL() {
		
		MySQL mysql = new MySQL();
		mysql.connect();
		mysql.create("CREATE TABLE IF NOT EXISTS NewSettlers_Town (ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY , Name TEXT NOT NULL , Stufe TINYINT NOT NULL , Bezeichnung TEXT NOT NULL , Oeffentlich BOOLEAN NOT NULL , Stadtwache BOOLEAN NOT NULL , PlotLimit TINYINT NOT NULL , PlotAktuell TINYINT NOT NULL , ProduktionLimit TINYINT NOT NULL , ProduktionAktuell TINYINT NOT NULL , Reichspunkte TINYINT NOT NULL) ENGINE=MYISAM;");
		mysql.create("CREATE TABLE IF NOT EXISTS JavaGuiUserdata (ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY , Username TEXT NOT NULL , Password TEXT NOT NULL) ENGINE=MYISAM;");
		mysql.disconnect();
		
	}
	
	public void patchMySQL() {
		MySQL mysql = new MySQL();
		mysql.connect();
		//mysql.alterTable("CREATE TABLE IF NOT EXISTS 'NewSettlers_Town' ('ID' INT NOT NULL AUTO_INCREMENT PRIMARY KEY , 'Name' TEXT NOT NULL , 'Stufe' TINYINT NOT NULL) ENGINE=MYISAM DEFAULT CHARSET=utf8;", "NewSettlers_Town", "Column");
		mysql.disconnect();
	}

}