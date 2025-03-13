package org.community.fourWays.Configs;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.community.fourWays.fourWays;

public class fourWaysJobs {
		
	private final fourWays plugin;
	 	 
	public fourWaysJobs(fourWays plugin)
	{
		this.plugin = plugin;
	}
	
	public void reloadConfig() {
	    if (plugin.jobsFile == null) {
	    	plugin.jobsFile = new File(plugin.getDataFolder(), "configs/jobs.yml");
	    }
	    plugin.jobs = YamlConfiguration.loadConfiguration(plugin.jobsFile);

	}

	public FileConfiguration getConfig() {
	    if (plugin.jobs == null) {
	        reloadConfig();
	    }
	    return plugin.jobs;
	}
	
	public void saveConfig() {
	    if (plugin.jobs == null || plugin.jobsFile == null) {
	    return;
	    }
	    try {
	    	plugin.jobs.save(plugin.jobsFile);
	    } catch (IOException ex) {
	        Logger.getLogger(JavaPlugin.class.getName()).log(Level.SEVERE, "Could not save config to " + plugin.jobsFile, ex);
	    }
	}
	
	public boolean initiateConfig() {
		
		reloadConfig();
		getConfig();
		
		if(plugin.jobs.getString("Credit.Version") == null)
        	plugin.jobs.set("Credit.Version", "v0.0.1");
		plugin.logprefix = "Four Ways " + plugin.jobs.getString("Credit.Version");
		
		if(plugin.jobs.getString("Beruf.AA.Name") == null)
        	plugin.jobs.set("Beruf.AA.Name", "Bergarbeiter");
		if(plugin.jobs.getString("Beruf.AA.MaxStufe") == null)
        	plugin.jobs.set("Beruf.AA.MaxStufe", 20);
		if(plugin.jobs.getString("Beruf.AA.Voraussetzung") == null)
        	plugin.jobs.set("Beruf.AA.Voraussetzung", "XX");
		if(plugin.jobs.getString("Beruf.AA.Tier") == null)
        	plugin.jobs.set("Beruf.AA.Tier", 1);
		
		saveConfig();
		
		return true;
		
	}
	
}