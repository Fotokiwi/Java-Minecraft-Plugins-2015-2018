package org.community.newSettlers.ShipRoute;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.community.newSettlers.newSettlers;

public class newSettlersShipRoute {
	
	private newSettlers plugin;
	
	public Map<Player, Boolean> listA = new HashMap<Player, Boolean>();
	public Map<Player, Boolean> listB = new HashMap<Player, Boolean>();
	
	public newSettlersShipRoute(newSettlers plugin) {
		this.plugin = plugin;
	}
	
	@SuppressWarnings("deprecation")
	public void testTeleport() {
		String route = "Beispiel";
		String[] locString1 = plugin.routes.getString("Route." + route + ".LocationA-Pos1").split(",");
		String[] locString2 = plugin.routes.getString("Route." + route + ".LocationA-Pos2").split(",");
		Location location1 = new Location(plugin.getServer().getWorld(locString1[0]), Integer.parseInt(locString1[1]), Integer.parseInt(locString1[2]), Integer.parseInt(locString1[3]));
		Location location2 = new Location(plugin.getServer().getWorld(locString2[0]), Integer.parseInt(locString2[1]), Integer.parseInt(locString2[2]), Integer.parseInt(locString2[3]));
		ZoneVector vector1 = new ZoneVector(location1);
		ZoneVector vector2 = new ZoneVector(location2);
		Player[] playerList = plugin.getServer().getOnlinePlayers();
		for(int i = 0; i < playerList.length; i++) {
			isInArea(playerList[i], vector1, vector2, true);
		}
	}
	
	public void prepareTeleport(String route) {
		plugin.nSShipRoute.listA.clear();
		plugin.nSShipRoute.listB.clear();
		prepareLocationA(route);
		prepareLocationB(route);
	}
	
	@SuppressWarnings("deprecation")
	private void prepareLocationA(String route) {
		String[] locString1 = plugin.routes.getString("Route." + route + ".LocationA-Pos1").split(",");
		String[] locString2 = plugin.routes.getString("Route." + route + ".LocationA-Pos2").split(",");
		Location location1 = new Location(plugin.getServer().getWorld(locString1[0]), Integer.parseInt(locString1[1]), Integer.parseInt(locString1[2]), Integer.parseInt(locString1[3]));
		Location location2 = new Location(plugin.getServer().getWorld(locString2[0]), Integer.parseInt(locString2[1]), Integer.parseInt(locString2[2]), Integer.parseInt(locString2[3]));
		ZoneVector vector1 = new ZoneVector(location1);
		ZoneVector vector2 = new ZoneVector(location2);
		Player[] playerList = plugin.getServer().getOnlinePlayers();
		for(int i = 0; i < playerList.length; i++) {
			if(isInArea(playerList[i], vector1, vector2)) {
				playerList[i].sendMessage(plugin.routes.getString("Route." + route + ".MessageShipA"));
			}
		}
		plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new prepareTeleport(route, vector1, vector2, "A"), 60 * 20L);
	}
	
	@SuppressWarnings("deprecation")
	private void prepareLocationB(String route) {
		String[] locString1 = plugin.routes.getString("Route." + route + ".LocationB-Pos1").split(",");
		String[] locString2 = plugin.routes.getString("Route." + route + ".LocationB-Pos2").split(",");
		Location location1 = new Location(plugin.getServer().getWorld(locString1[0]), Integer.parseInt(locString1[1]), Integer.parseInt(locString1[2]), Integer.parseInt(locString1[3]));
		Location location2 = new Location(plugin.getServer().getWorld(locString2[0]), Integer.parseInt(locString2[1]), Integer.parseInt(locString2[2]), Integer.parseInt(locString2[3]));
		ZoneVector vector1 = new ZoneVector(location1);
		ZoneVector vector2 = new ZoneVector(location2);
		Player[] playerList = plugin.getServer().getOnlinePlayers();
		for(int i = 0; i < playerList.length; i++) {
			if(isInArea(playerList[i], vector1, vector2)) {
				playerList[i].sendMessage(plugin.routes.getString("Route." + route + ".MessageShipB"));
			}
		}
		plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new prepareTeleport(route, vector1, vector2, "B"), 60 * 20L);
	}
	
	public class prepareTeleport implements Runnable {

		private ZoneVector vector1;
		private ZoneVector vector2;
		private String routine;
		private String route;

		public prepareTeleport(String route, ZoneVector vector1, ZoneVector vector2, String routine) {
			this.route = route;
			this.vector1 = vector1;
			this.vector2 = vector2;
			this.routine = routine;
		}

		@SuppressWarnings("deprecation")
		public void run() {
			Player[] playerList = plugin.getServer().getOnlinePlayers();
			for(int i = 0; i < playerList.length; i++) {
				if(isInArea(playerList[i], vector1, vector2)) {
					if(routine.equalsIgnoreCase("A")) {
						plugin.nSShipRoute.listA.put(playerList[i], true);
						if(plugin.nSShipRoute.listB.get(playerList[i]) == null) {
							String[] locString = plugin.routes.getString("Route." + route + ".LocationB-Spawn").split(",");
							Location target = new Location(plugin.getServer().getWorld(locString[0]), Integer.parseInt(locString[1]), Integer.parseInt(locString[2]), Integer.parseInt(locString[3]));
							playerList[i].teleport(target);
						}
					}
					if(routine.equalsIgnoreCase("B")) {
						plugin.nSShipRoute.listB.put(playerList[i], true);
						if(plugin.nSShipRoute.listA.get(playerList[i]) == null) {
							String[] locString = plugin.routes.getString("Route." + route + ".LocationA-Spawn").split(",");
							Location target = new Location(plugin.getServer().getWorld(locString[0]), Integer.parseInt(locString[1]), Integer.parseInt(locString[2]), Integer.parseInt(locString[3]));
							playerList[i].teleport(target);
						}
					}
				}
			}
		}		

	}
	
	/*private void prepareLocationA(String route) {		

		Map<Player, Boolean> players = new HashMap<Player, Boolean>();
		
		String[] locString = plugin.routes.getString("Route." + route + ".LocationShipA").split(",");
		
		Location location = new Location(plugin.getServer().getWorld(locString[0]), Integer.parseInt(locString[1]), Integer.parseInt(locString[2]), Integer.parseInt(locString[3]));
		
		Entity entity = plugin.getServer().getWorld(locString[0]).spawnEntity(location, EntityType.ARROW);
		players = getPlayers(entity, route, plugin.routes.getInt("Route." + route + ".MessageRadius"));
		entity.remove();
		
		if(players != null) {
			for(Entry<Player, Boolean> entry : players.entrySet()){
				entry.getKey().sendMessage(plugin.routes.getString("Route." + route + ".MessageShipA"));			
			}
		}
		
		locString = plugin.routes.getString("Route." + route + ".LocationShipB").split(",");
			
		plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new prepareTeleport(route, location, locString, "A"), 60 * 20L);
		
	}
	
	private void prepareLocationB(String route) {
		
		Map<Player, Boolean> players = new HashMap<Player, Boolean>();
		
		String[] locString = plugin.routes.getString("Route." + route + ".LocationShipB").split(",");
		
		Location location = new Location(plugin.getServer().getWorld(locString[0]), Integer.parseInt(locString[1]), Integer.parseInt(locString[2]), Integer.parseInt(locString[3]));
		
		Entity entity = plugin.getServer().getWorld(locString[0]).spawnEntity(location, EntityType.ARROW);
		players = getPlayers(entity, route, plugin.routes.getInt("Route." + route + ".MessageRadius"));
		entity.remove();
		
		if(players != null) {
			for(Entry<Player, Boolean> entry : players.entrySet()){
				entry.getKey().sendMessage(plugin.routes.getString("Route." + route + ".MessageShipB"));
			}
		}
		
		locString = plugin.routes.getString("Route." + route + ".LocationShipA").split(",");
			
		plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new prepareTeleport(route, location, locString, "B"), 60 * 20L);
		
	}
	
	public class prepareTeleport implements Runnable {
		
		private String route = "";
		private Map<Player, Boolean> players = new HashMap<Player, Boolean>();
		private Location location = new Location(null, 0, 0, 0);
		private String example = "x,1,2,3";
		private String[] locString = example.split(",");
		private String routine = "";
		
		public prepareTeleport(String route, Location location, String[] locString, String routine) {
			this.route = route;
			this.location = location;
			this.locString = locString;
			this.routine = routine;
		}

		public void run() {
			Location teleportLocation = new Location(plugin.getServer().getWorld(locString[0]), Integer.parseInt(locString[1]), Integer.parseInt(locString[2]), Integer.parseInt(locString[3]));
			
			Entity entity = location.getWorld().spawnEntity(location, EntityType.ARROW);
			players = getPlayers(entity, route, plugin.routes.getInt("Route." + route + ".TeleportRadius"));
			entity.remove();
			
			if(players != null) {
				Player[] playerList = plugin.getServer().getOnlinePlayers();
				for(Entry<Player, Boolean> entry : players.entrySet()){
					for(int i = 0; i < playerList.length; i++) {
						if(playerList[i].getName().equalsIgnoreCase(entry.getKey().getName())) {
							if(routine.equalsIgnoreCase("A")) {
								plugin.nSShipRoute.listA.put(entry.getKey(), true);
								if(plugin.nSShipRoute.listB.get(entry.getKey()) == null) {
									entry.getKey().teleport(teleportLocation);
								}
							}
							if(routine.equalsIgnoreCase("B")) {
								plugin.nSShipRoute.listB.put(entry.getKey(), true);
								if(plugin.nSShipRoute.listA.get(entry.getKey()) == null) {
									entry.getKey().teleport(teleportLocation);
								}
							}
						}
					}
				}
			}
		}		
		
	}
	
	private Map<Player, Boolean> getPlayers(Entity entity, String route, int radius) {
		
		Map<Player, Boolean> players = new HashMap<Player, Boolean>();		
		
		List<Entity> entities = entity.getNearbyEntities(radius, 32, radius);
		for(int i = 0; i < entities.size(); i++) {
			if(entities.get(i) instanceof Player)
				players.put((Player) entities.get(i), true);
		}
		
		return players;
	}*/
	
	private boolean isInArea(Player player, ZoneVector cornerA, ZoneVector cornerB) {
		
		if(player.getWorld() != cornerA.getWorld())
			return false;
		
		int pLocX = player.getLocation().getBlockX();
		int pLocY = player.getLocation().getBlockY();
		int pLocZ = player.getLocation().getBlockZ();
		
		if((pLocX <= cornerA.getX() && pLocX >= cornerB.getX()) || (pLocX >= cornerA.getX() && pLocX <= cornerB.getX())) {
			if((pLocY <= cornerA.getY() && pLocY >= cornerB.getY()) || (pLocY >= cornerA.getY() && pLocY <= cornerB.getY())) {
				if((pLocZ <= cornerA.getZ() && pLocZ >= cornerB.getZ()) || (pLocZ >= cornerA.getZ() && pLocZ <= cornerB.getZ())) {
					return true;
				}
			}
		}
			
		return false;
	}
	
	private void isInArea(Player player, ZoneVector cornerA, ZoneVector cornerB, Boolean check) {
		
		if(player.getWorld() != cornerA.getWorld())
			plugin.getServer().broadcastMessage("Spieler: " + player.getName() + " ist nicht in der gleichen Welt.");
		
		int pLocX = player.getLocation().getBlockX();
		int pLocY = player.getLocation().getBlockY();
		int pLocZ = player.getLocation().getBlockZ();
		
		if((pLocX <= cornerA.getX() && pLocX >= cornerB.getX()) || (pLocX >= cornerA.getX() && pLocX <= cornerB.getX())) {
			if((pLocY <= cornerA.getY() && pLocY >= cornerB.getY()) || (pLocY >= cornerA.getY() && pLocY <= cornerB.getY())) {
				if((pLocZ <= cornerA.getZ() && pLocZ >= cornerB.getZ()) || (pLocZ >= cornerA.getZ() && pLocZ <= cornerB.getZ())) {
					plugin.getServer().broadcastMessage("Spieler: " + player.getName() + " befindet sich innerhalb der Teleport-Zone.");
				}
			}
		}
			
		plugin.getServer().broadcastMessage("Spieler: " + player.getName() + " ist nicht innerhalb der Teleport-Zone.");
	}
	
}