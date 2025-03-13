package org.community.Statistics.Player;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;
import org.community.DatabaseProvider.MySQL.MySQL;
import org.community.Statistics.Statistics;

public class statisticsPlayer {
	
	private Statistics plugin = null;
	private Player player = null;
	
	private Map<String, Integer> blockBreakCount = new HashMap<String, Integer>();
	private Map<String, Integer> blockPlaceCount = new HashMap<String, Integer>();
	private Map<String, Integer> playerDeathCount = new HashMap<String, Integer>();
	private Map<String, Integer> playerDeathByPlayerCount = new HashMap<String, Integer>();
	private Map<String, Integer> playerKillCount = new HashMap<String, Integer>();
	private Map<String, Integer> playerKillPlayerCount = new HashMap<String, Integer>();
	private int playerJoinCount = 0;
	//private Map<String, Integer> playerShearCount = new HashMap<String, Integer>();
	
	public statisticsPlayer(Statistics plugin, Player player) {
		
		this.plugin = plugin;
		this.player = player;
		
		MySQL mysql = new MySQL();
		mysql.connect();
		String[][] blockBreak = mysql.selectString("SELECT * FROM Statistics_BlockBreak WHERE Player = '" + player.getName() + "';");
		for(int i = 0; i < blockBreak.length; i++) {
			this.blockBreakCount.put(blockBreak[i][1], Integer.parseInt(blockBreak[i][2]));
		}
		String[][] blockPlace = mysql.selectString("SELECT * FROM Statistics_BlockPlace WHERE Player = '" + player.getName() + "';");
		for(int i = 0; i < blockPlace.length; i++) {
			this.blockPlaceCount.put(blockPlace[i][1], Integer.parseInt(blockPlace[i][2]));
		}
		mysql.disconnect();
		
	}
	
	public Player getPlayer() {
		return this.player;
	}
	
	public void setBlockBreakCount(String blockType) {
		int count = this.blockBreakCount.containsKey(blockType) ? this.blockBreakCount.get(blockType) : 0;
		this.blockBreakCount.put(blockType, count + 1);
	}
	
	public void setBlockPlaceCount(String blockType) {
		int count = this.blockPlaceCount.containsKey(blockType) ? this.blockPlaceCount.get(blockType) : 0;
		this.blockPlaceCount.put(blockType, count + 1);
	}
	
	public void setPlayerDeathCount(String entityType) {
		int count = this.playerDeathCount.containsKey(entityType) ? this.playerDeathCount.get(entityType) : 0;
		this.playerDeathCount.put(entityType, count + 1);
	}
	
	public void setPlayerDeathByPlayerCount(String player) {
		int count = this.playerDeathByPlayerCount.containsKey(player) ? this.playerDeathByPlayerCount.get(player) : 0;
		this.playerDeathByPlayerCount.put(player, count + 1);
	}
	
	public void setPlayerKillCount(String entityType) {
		int count = this.playerKillCount.containsKey(entityType) ? this.playerKillCount.get(entityType) : 0;
		this.playerKillCount.put(entityType, count + 1);
	}
	
	public void setPlayerKillPlayerCount(String player) {
		int count = this.playerKillPlayerCount.containsKey(player) ? this.playerKillPlayerCount.get(player) : 0;
		this.playerKillPlayerCount.put(player, count + 1);
	}
	
	public void setPlayerJoinCount() {
		this.playerJoinCount = this.playerJoinCount + 1;
	}
	
	public void saveStats() {
		
		MySQL mysql = new MySQL();
		mysql.connect();
		
		for(Map.Entry<String, Integer> entry : this.blockBreakCount.entrySet()){
			this.plugin.config.set(this.player.getName() + ".BlockBreak." + entry.getKey(), this.plugin.config.getInt(this.player.getName() + ".BlockBreak." + entry.getKey(), 0) + entry.getValue());
			mysql.update("UPDATE Statistics_BlockBreak SET Name = '" + this.player.getName() + "', Block = " + entry.getKey() + ", Amount = '" + entry.getValue() + "' WHERE Name = '" + this.player.getName() + "';");
		}
		
		for(Map.Entry<String, Integer> entry : this.blockPlaceCount.entrySet()){
			this.plugin.config.set(this.player.getName() + ".BlockPlace." + entry.getKey(), this.plugin.config.getInt(this.player.getName() + ".BlockPlace." + entry.getKey(), 0) + entry.getValue());			
		}
		
		this.plugin.config.set(this.player.getName() + ".PlayerJoin", this.plugin.config.getInt(this.player.getName() + ".PlayerJoin", 0) + this.playerJoinCount);
		
		for(Map.Entry<String, Integer> entry : this.playerDeathCount.entrySet()){
			this.plugin.config.set(this.player.getName() + ".PlayerDeath." + entry.getKey(), this.plugin.config.getInt(this.player.getName() + ".stopPlayerDeath." + entry.getKey(), 0) + entry.getValue());			
		}
	}

}
