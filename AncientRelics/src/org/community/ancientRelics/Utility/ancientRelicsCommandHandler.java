package org.community.ancientRelics.Utility;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.community.ancientRelics.ancientRelics;


public class ancientRelicsCommandHandler {
	
	private ancientRelics plugin;
	
	public ancientRelicsCommandHandler(ancientRelics ancientRelics) {
		this.plugin = ancientRelics;
	}
	
	public boolean handleCommand(Player player, String s) {
		if(s.length()<=1) return false;
		String[] commandList = s.split(";");
		//CraftServer cs = (CraftServer)plugin.getServer();
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
			if (!Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command)) {
				plugin.LogError("Could not execute Console-Command '"+command+"'");
				return false;
			}
			command = "";
		}
		return true;
	}

}