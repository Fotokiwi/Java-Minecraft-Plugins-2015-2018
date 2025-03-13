package org.community.Statistics.Core;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;
import org.community.DatabaseProvider.MySQL.MySQL;
import org.community.Statistics.Player.statisticsPlayer;

public class statisticsCore {
	
	public Map<Player, statisticsPlayer> playerHash = new HashMap<Player, statisticsPlayer>();
	
	public statisticsCore() {
		
		initializeMySQL();
		
	}
	
	public void initializeMySQL() {
		
		MySQL mysql = new MySQL();
		mysql.connect();
		mysql.create("CREATE TABLE IF NOT EXISTS Statistics_BlockBreak (ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY , Player TEXT NOT NULL , Block TEXT NOT NULL , Amount Int NOT NULL) ENGINE=MYISAM;");
		mysql.create("CREATE TABLE IF NOT EXISTS Statistics_BlockPlace (ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY , Player TEXT NOT NULL , Block TEXT NOT NULL , Amount Int NOT NULL) ENGINE=MYISAM;");
		mysql.create("CREATE TABLE IF NOT EXISTS Statistics_PlayerJoin (ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY , Player TEXT NOT NULL , Amount Int NOT NULL) ENGINE=MYISAM;");
		mysql.disconnect();
		
	}

}
