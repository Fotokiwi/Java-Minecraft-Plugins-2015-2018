package org.community.EpicFighters.Configs;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.community.EpicFighters.EpicFighters;

public class epicFightersConfig {
		
	private final EpicFighters plugin;
	 	 
	public epicFightersConfig(EpicFighters plugin)
	{
		this.plugin = plugin;
	}
	
	public void reloadConfig() {
	    if (plugin.configFile == null) {
	    	plugin.configFile = new File(plugin.getDataFolder(), "configs/config.yml");
	    }
	    plugin.config = YamlConfiguration.loadConfiguration(plugin.configFile);

	}

	public FileConfiguration getConfig() {
	    if (plugin.config == null) {
	        reloadConfig();
	    }
	    return plugin.config;
	}
	
	public void saveConfig() {
	    if (plugin.config == null || plugin.configFile == null) {
	    return;
	    }
	    try {
	    	plugin.config.save(plugin.configFile);
	    } catch (IOException ex) {
	        Logger.getLogger(JavaPlugin.class.getName()).log(Level.SEVERE, "Could not save config to " + plugin.configFile, ex);
	    }
	}
	
	public boolean initiateConfig() {
		
		reloadConfig();
		getConfig();
		
		if(plugin.config.getString("Credit.Version") == null)
        	plugin.config.set("Credit.Version", "v0.0.1");
		plugin.logprefix = "EpicFighters " + plugin.config.getString("Credit.Version");
		
		if(plugin.config.getString("Assassine.Damage.Base") == null)
        	plugin.config.set("Assassine.Damage.Base", 1.0);
		if(plugin.config.getString("Assassine.Damage.Crouch") == null)
        	plugin.config.set("Assassine.Damage.Crouch", 1.2);
		if(plugin.config.getString("Assassine.Damage.BaseCrit") == null)
        	plugin.config.set("Assassine.Damage.BaseCrit", 2.0);
		if(plugin.config.getString("Assassine.Damage.BaseMassive") == null)
        	plugin.config.set("Assassine.Damage.BaseMassive", 4.0);
		if(plugin.config.getString("Assassine.Damage.BaseCritChance") == null)
        	plugin.config.set("Assassine.Damage.BaseCritChance", 7.0);
		if(plugin.config.getString("Assassine.Damage.BaseMassiveChance") == null)
        	plugin.config.set("Assassine.Damage.BaseMassiveChance", 4.0);
		if(plugin.config.getString("Assassine.Armor.DiamondPenalty") == null)
        	plugin.config.set("Assassine.Armor.DiamondPenalty", 2.0);
		if(plugin.config.getString("Assassine.Armor.GoldPenalty") == null)
        	plugin.config.set("Assassine.Armor.GoldPenalty", 1.25);
		if(plugin.config.getString("Assassine.Armor.IronPenalty") == null)
        	plugin.config.set("Assassine.Armor.IronPenalty", 1.5);
		if(plugin.config.getString("Assassine.Armor.ChainPenalty") == null)
        	plugin.config.set("Assassine.Armor.ChainPenalty", 1.0);
		if(plugin.config.getString("Assassine.Armor.LeatherPenalty") == null)
        	plugin.config.set("Assassine.Armor.LeatherPenalty", 0.75);
		if(plugin.config.getString("Assassine.Armor.NakedPenalty") == null)
        	plugin.config.set("Assassine.Armor.NakedPenalty", 0.5);
		
		saveConfig();
		
		return true;
		
	}
	
}