package org.community.fourWays.Core;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.server.ServerCommandEvent;
import org.community.fourWays.fourWays;
import org.community.fourWays.User.User;

public class fourWaysCore {
	
	private fourWays plugin;
	
	private int fourWaysHeartbeatTask = -1;
	
	public Map<String, User> userList = new HashMap<String, User>();
	
	public Map<String, Boolean> questModePlayer = new HashMap<String, Boolean>();
	public Map<String, String> questModeBlock = new HashMap<String, String>();
	public Map<String, Integer> questModeAmount = new HashMap<String, Integer>();
	public Map<String, Integer> questModeAmountTemp = new HashMap<String, Integer>();
	public Map<String, String> questModeNPC = new HashMap<String, String>();
	public Map<String, String> questModeBlockRefreshList = new HashMap<String, String>();
	public Map<String, Integer> questModeTaskID = new HashMap<String, Integer>();
	public Map<UUID, Float> bootCounter = new HashMap<UUID, Float>();
	
	public Map<UUID, Long> lastLogin = new HashMap<UUID, Long>();
	public Map<UUID, Long> lastQuit = new HashMap<UUID, Long>();
		
	public fourWaysCore(fourWays plugin) {
		this.plugin = plugin;
	}

	public boolean isFourWaysHeartbeatTaskRunning() {
		return fourWaysHeartbeatTask != -1;
	}
	
	public void toggleFourWaysHeartbeat(boolean on) {		
		if (on && !isFourWaysHeartbeatTaskRunning()) {
			
			fourWaysHeartbeatTask = plugin.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, plugin.fWHeartbeat, 1200L, plugin.config.getLong("Config.Plugin.HeartBeatInMinutes", 2) * 1200L);
				
			if (fourWaysHeartbeatTask == -1) {
				plugin.LogError("error: Heartbeat initialization failed!");
			} else {
				//plugin.fWHeartbeat.toggleFourWaysConfig(true);
				plugin.fWHeartbeat.toggleFourWaysExpGranter(true);
				plugin.LogInfo("initialized: Heartbeat");
			}
			
		} else if (!on && isFourWaysHeartbeatTaskRunning()) {
			plugin.getServer().getScheduler().cancelTask(fourWaysHeartbeatTask);
			//plugin.fWHeartbeat.toggleFourWaysConfig(false);
			plugin.fWHeartbeat.toggleFourWaysExpGranter(false);
			fourWaysHeartbeatTask = -1;
		}
	}
	
	public void addUserList(String playerName, User user) {
		userList.put(playerName, user);
	}
	
	public void removeUserList(Player player) {
		userList.remove(player);
	}
	
	public User getUserEntry(String playername) {
		return userList.get(playername);
	}
	
	public User getUserClass(Player player) {
		return userList.get(player.getName());
	}
	
	public int sizeUserList() {
		return userList.size();
	}
	
	public Map<String, User> getCompleteUserList() {
		return userList;
	}
	
	public void setPermission(Player player) {
		plugin.fWCore.handleCommand(plugin, player, "/kCme3DYr25mXcFTi " + player.getName());
	}
	
	public void removePermission(Player player) {
		plugin.fWCore.handleCommand(plugin, player, "/iBe4qQejB3GIt85M " + player.getName());
	}
	
	public boolean handleCommand(fourWays plugin, Player player, String s) {
		ServerCommandEvent sce = new ServerCommandEvent(Bukkit.getConsoleSender(), s);
		Bukkit.getPluginManager().callEvent(sce);
		plugin.getServer().dispatchCommand(Bukkit.getConsoleSender(), sce.getCommand());
		plugin.LogError("executing Console-Command '"+s+"'");
		//if (!Bukkit.dispatchCommand(Bukkit.getConsoleSender(), s)) {
		//	plugin.LogError("Could not execute Console-Command '"+s+"'");
		//	return false;
		//}
		return true;
	}
	
	/*public boolean handleCommand(fourWays plugin, Player player, String s) {
		if(s.length()<=1) return false;
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
		return true;
	}*/

}