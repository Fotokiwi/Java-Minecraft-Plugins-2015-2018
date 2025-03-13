package org.community.fourWays.Cache;

import java.io.File;
import java.util.Set;
import java.util.Map.Entry;

import org.bukkit.configuration.ConfigurationSection;
import org.community.fourWays.fourWays;
import org.community.fourWays.User.User;

@SuppressWarnings("unused")
public class fourWaysCache {
	
	private fourWays plugin;
	
	public fourWaysCache(fourWays plugin) {
		this.plugin = plugin;
	}
	
	public void loadUserList() {
		/*
		File dir = new File(plugin.getDataFolder() + "/user/");
		String fileName;
		String tempName;
		File[] files = dir.listFiles();
		if (files != null) {
			for (int i = 0; i < files.length; i++) {
				if (files[i].isDirectory()) {
				}
				else {
					fileName = files[i].getName();
					tempName = fileName.substring(0, fileName.indexOf('.'));
					User user = new User(plugin, tempName);
					plugin.LogDebug("User " + user.getName() + ": Level " + user.getLevel() + ", " + user.getExp() + " exp");
				}
			}
		}
		*/
	}
	
	public void saveUserList() {
		if(plugin.fWCore.getCompleteUserList().size() <= 0)
			return;
		for (User user : plugin.fWCore.getCompleteUserList().values()) {
		    user.saveToFile();
		}
	}
	
	public void saveQuestmode() {
		if(plugin.fWCore.questModePlayer.size() <= 0)
			return;
		for(Entry<String, Boolean> entry : plugin.fWCore.questModePlayer.entrySet()){
			if(entry.getValue() != null) {
				String player = entry.getKey();
				plugin.questmode.set("Player." + player + ".Block", plugin.fWCore.questModeBlock.get(player));
				plugin.questmode.set("Player." + player + ".Amount", plugin.fWCore.questModeAmount.get(player));
				plugin.questmode.set("Player." + player + ".TempAmount", plugin.fWCore.questModeAmountTemp.get(player));
				plugin.questmode.set("Player." + player + ".NPC", plugin.fWCore.questModeNPC.get(player));
				plugin.fWQuestmode.saveConfig();
			}			
		}
		for(Entry<String, String> entry : plugin.fWCore.questModeBlockRefreshList.entrySet()){
			if(entry.getValue() != null) {
				plugin.questmode.set("Blocklist." + entry.getKey(), entry.getValue());
				plugin.fWQuestmode.saveConfig();
			}			
		}
	}
	
	public void loadQuestmode() {
		ConfigurationSection questSection = plugin.questmode.getConfigurationSection("Player");
		
		if(questSection == null)
			return;
		
		Set<String> questKeys = questSection.getKeys(false);
		String[] questKeysArray = questKeys.toArray(new String[0]);
		
		for(int i = 0; i < questKeysArray.length; i++) {
			String player = questKeysArray[i];
			plugin.fWCore.questModePlayer.put(player, true);
			plugin.fWCore.questModeBlock.put(player, plugin.questmode.getString("Player." + player + ".Block"));
			plugin.fWCore.questModeAmount.put(player, plugin.questmode.getInt("Player." + player + ".Amount"));
			plugin.fWCore.questModeAmountTemp.put(player, plugin.questmode.getInt("Player." + player + ".TempAmount"));
			plugin.fWCore.questModeNPC.put(player, plugin.questmode.getString("Player." + player + ".NPC"));
		}

		questSection = plugin.questmode.getConfigurationSection("Blocklist");
		
		if(questSection == null)
			return;
		
		questKeys = questSection.getKeys(false);
		questKeysArray = questKeys.toArray(new String[0]);
		
		for(int i = 0; i < questKeysArray.length; i++) {
			String block = questKeysArray[i];
			plugin.fWCore.questModeBlockRefreshList.put(block, plugin.questmode.getString("Blocklist." + block));
		}
	}
			
}