package org.community.EpicFighters.Configs;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.community.EpicFighters.EpicFighters;

public class epicFightersSkillsConfig {
		
	private final EpicFighters plugin;
	 	 
	public epicFightersSkillsConfig(EpicFighters plugin)
	{
		this.plugin = plugin;
	}
	
	public void reloadConfig() {
	    if (plugin.skillFile == null) {
	    	plugin.skillFile = new File(plugin.getDataFolder(), "configs/skills.yml");
	    }
	    plugin.skill = YamlConfiguration.loadConfiguration(plugin.skillFile);

	}

	public FileConfiguration getConfig() {
	    if (plugin.skill == null) {
	        reloadConfig();
	    }
	    return plugin.skill;
	}
	
	public void saveConfig() {
	    if (plugin.skill == null || plugin.skillFile == null) {
	    return;
	    }
	    try {
	    	plugin.skill.save(plugin.skillFile);
	    } catch (IOException ex) {
	        Logger.getLogger(JavaPlugin.class.getName()).log(Level.SEVERE, "Could not save config to " + plugin.skillFile, ex);
	    }
	}
	
	public boolean initiateConfig() {
		
		reloadConfig();
		getConfig();
		
		if(plugin.config.getString("Credit.Version") == null)
        	plugin.config.set("Credit.Version", "v0.0.1");
		plugin.logprefix = "EpicFighters " + plugin.config.getString("Credit.Version");
		
		if(plugin.skill.getString("Beispiel.Kleiner_Feuerball.Name") == null)
        	plugin.skill.set("Beispiel.Kleiner_Feuerball.Name", "Kleiner Feuerball");
		if(plugin.skill.getString("Beispiel.Kleiner_Feuerball.Klasse") == null)
        	plugin.skill.set("Beispiel.Kleiner_Feuerball.Klasse", "Elementarmagier");
		if(plugin.skill.getString("Beispiel.Kleiner_Feuerball.Type") == null)
        	plugin.skill.set("Beispiel.Kleiner_Feuerball.Type", "Direktzauber");
		if(plugin.skill.getString("Beispiel.Kleiner_Feuerball.Mana") == null)
        	plugin.skill.set("Beispiel.Kleiner_Feuerball.Mana", 1.0);
		if(plugin.skill.getString("Beispiel.Kleiner_Feuerball.CooldownInSeconds") == null)
        	plugin.skill.set("Beispiel.Kleiner_Feuerball.CooldownInSeconds", 4);
		if(plugin.skill.getString("Beispiel.Kleiner_Feuerball.Option.Speed") == null)
        	plugin.skill.set("Beispiel.Kleiner_Feuerball.Option.Speed", 1);
		if(plugin.skill.getString("Beispiel.Kleiner_Feuerball.Option.Range") == null)
        	plugin.skill.set("Beispiel.Kleiner_Feuerball.Option.Range", 32);
		
		saveConfig();
		
		return true;
		
	}
	
}