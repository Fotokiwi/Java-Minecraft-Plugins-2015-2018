package org.community.newSettlers.Configs;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.community.newSettlers.newSettlers;

@SuppressWarnings("unused")
public class newSettlersConfig {
		
	private final newSettlers plugin;
	
	private List<String> townStorageLevel1 = new ArrayList<String>();
	private List<String> townStorageLevel2 = new ArrayList<String>();
	private List<String> townStorageLevel3 = new ArrayList<String>();
	private List<String> townStorageLevel4 = new ArrayList<String>();
	private List<String> townStorageLevel5 = new ArrayList<String>();
	private List<String> townStorageLevel6 = new ArrayList<String>();
	private List<String> townStorageLevel7 = new ArrayList<String>();
	private List<String> townStorageLevel8 = new ArrayList<String>();

	private List<String> townUpgradeLevel2 = new ArrayList<String>();
	private List<String> townUpgradeLevel3 = new ArrayList<String>();
	private List<String> townUpgradeLevel4 = new ArrayList<String>();
	private List<String> townUpgradeLevel5 = new ArrayList<String>();
	private List<String> townUpgradeLevel6 = new ArrayList<String>();
	private List<String> townUpgradeLevel7 = new ArrayList<String>();
	private List<String> townUpgradeLevel8 = new ArrayList<String>();
	 	 
	public newSettlersConfig(newSettlers plugin)
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
		plugin.logprefix = "NewSettlers " + plugin.config.getString("Credit.Version");
		
		if(plugin.config.getString("Default.Level") == null)
        	plugin.config.set("Default.Level", 1);
		if(plugin.config.getString("Default.Description") == null)
        	plugin.config.set("Default.Description", "Siedlung");
		if(plugin.config.getString("Default.PlotLimit") == null)
        	plugin.config.set("Default.PlotLimit", 25);
		if(plugin.config.getString("Default.ProductionLimit") == null)
        	plugin.config.set("Default.ProductionLimit", 4);
		if(plugin.config.getString("Default.Production") == null)
        	plugin.config.set("Default.Production", 0);
		if(plugin.config.getString("Default.TownBoard") == null)
        	plugin.config.set("Default.TownBoard", "Heute ist ein schöner Tag um Minecraft zu spielen!");
		if(plugin.config.getString("Default.Open") == null)			
        	plugin.config.set("Default.Open", false);
		if(plugin.config.getString("Default.TownGuard") == null)			
        	plugin.config.set("Default.TownGuard", false);

		if(plugin.config.getString("TownLevel.Level2.Description") == null)			
        	plugin.config.set("TownLevel.Level2.Description", "Kleines Dorf");
		if(plugin.config.getString("TownLevel.Level2.PlotLimit") == null)			
        	plugin.config.set("TownLevel.Level2.PlotLimit", 49);
		if(plugin.config.getString("TownLevel.Level2.ProductionLimit") == null)			
        	plugin.config.set("TownLevel.Level2.ProductionLimit", 6);

		if(plugin.config.getString("TownLevel.Level3.Description") == null)			
        	plugin.config.set("TownLevel.Level3.Description", "Großes Dorf");
		if(plugin.config.getString("TownLevel.Level3.PlotLimit") == null)			
        	plugin.config.set("TownLevel.Level3.PlotLimit", 81);
		if(plugin.config.getString("TownLevel.Level3.ProductionLimit") == null)			
        	plugin.config.set("TownLevel.Level3.ProductionLimit", 8);

		if(plugin.config.getString("TownLevel.Level4.Description") == null)			
        	plugin.config.set("TownLevel.Level4.Description", "Kleinstadt");
		if(plugin.config.getString("TownLevel.Level4.PlotLimit") == null)			
        	plugin.config.set("TownLevel.Level4.PlotLimit", 144);
		if(plugin.config.getString("TownLevel.Level4.ProductionLimit") == null)			
        	plugin.config.set("TownLevel.Level4.ProductionLimit", 10);

		if(plugin.config.getString("TownLevel.Level5.Description") == null)			
        	plugin.config.set("TownLevel.Level5.Description", "Stadt");
		if(plugin.config.getString("TownLevel.Level5.PlotLimit") == null)			
        	plugin.config.set("TownLevel.Level5.PlotLimit", 225);
		if(plugin.config.getString("TownLevel.Level5.ProductionLimit") == null)			
        	plugin.config.set("TownLevel.Level5.ProductionLimit", 12);

		if(plugin.config.getString("TownLevel.Level6.Description") == null)			
        	plugin.config.set("TownLevel.Level6.Description", "Großstadt");
		if(plugin.config.getString("TownLevel.Level6.PlotLimit") == null)			
        	plugin.config.set("TownLevel.Level6.PlotLimit", 324);
		if(plugin.config.getString("TownLevel.Level6.ProductionLimit") == null)			
        	plugin.config.set("TownLevel.Level6.ProductionLimit", 14);

		if(plugin.config.getString("TownLevel.Level7.Description") == null)			
        	plugin.config.set("TownLevel.Level7.Description", "Handelsstadt");
		if(plugin.config.getString("TownLevel.Level7.PlotLimit") == null)			
        	plugin.config.set("TownLevel.Level7.PlotLimit", 484);
		if(plugin.config.getString("TownLevel.Level7.ProductionLimit") == null)			
        	plugin.config.set("TownLevel.Level7.ProductionLimit", 16);

		if(plugin.config.getString("TownLevel.Level8.Description") == null)			
        	plugin.config.set("TownLevel.Level8.Description", "Metropole");
		if(plugin.config.getString("TownLevel.Level8.PlotLimit") == null)			
        	plugin.config.set("TownLevel.Level8.PlotLimit", 676);
		if(plugin.config.getString("TownLevel.Level8.ProductionLimit") == null)			
        	plugin.config.set("TownLevel.Level8.ProductionLimit", 18);
		
		if(plugin.config.getString("Default.Permission.Outsider.Break") == null)
        	plugin.config.set("Default.Permission.Outsider.Break", false);
		if(plugin.config.getString("Default.Permission.Outsider.Build") == null)
        	plugin.config.set("Default.Permission.Outsider.Build", false);
		if(plugin.config.getString("Default.Permission.Outsider.Craft") == null)
        	plugin.config.set("Default.Permission.Outsider.Craft", false);
		if(plugin.config.getString("Default.Permission.Outsider.Interact") == null)
        	plugin.config.set("Default.Permission.Outsider.Interact", false);
		if(plugin.config.getString("Default.Permission.Member.Break") == null)
        	plugin.config.set("Default.Permission.Member.Break", true);
		if(plugin.config.getString("Default.Permission.Member.Build") == null)
        	plugin.config.set("Default.Permission.Member.Build", true);
		if(plugin.config.getString("Default.Permission.Member.Craft") == null)
        	plugin.config.set("Default.Permission.Member.Craft", true);
		if(plugin.config.getString("Default.Permission.Member.Interact") == null)
        	plugin.config.set("Default.Permission.Member.Interact", true);
		if(plugin.config.getString("Default.Permission.Ally.Break") == null)
        	plugin.config.set("Default.Permission.Ally.Break", false);
		if(plugin.config.getString("Default.Permission.Ally.Build") == null)
        	plugin.config.set("Default.Permission.Ally.Build", false);
		if(plugin.config.getString("Default.Permission.Ally.Craft") == null)
        	plugin.config.set("Default.Permission.Ally.Craft", false);
		if(plugin.config.getString("Default.Permission.Ally.Interact") == null)
        	plugin.config.set("Default.Permission.Ally.Interact", false);

		if(plugin.config.getString("Plots.Type.A") == null)
        	plugin.config.set("Plots.Type.A", "Town");
		if(plugin.config.getString("Plots.Type.B") == null)
        	plugin.config.set("Plots.Type.B", "Private");
		if(plugin.config.getString("Plots.Type.C") == null)
        	plugin.config.set("Plots.Type.C", "Embassy");
		
		if(plugin.config.getString("Wilderness.Whitelist.Break") == null)
        	plugin.config.set("Wilderness.Whitelist.Break", "[]");
		if(plugin.config.getString("Wilderness.Whitelist.Build") == null)
        	plugin.config.set("Wilderness.Whitelist.Build", "[]");

		if(plugin.config.getString("System.InteractBlocks") == null)
        	plugin.config.set("System.InteractBlocks", "[]");
		if(plugin.config.getString("System.SpawnMode") == null)
        	plugin.config.set("System.SpawnMode", "Town");
		if(plugin.config.getString("System.Homereset") == null)
        	plugin.config.set("System.Homereset", 7200);

		if(plugin.config.getString("System.TownWorlds") == null)
        	plugin.config.set("System.Townworlds", "[]");
		
		if(plugin.config.getString("System.UpkeepCalculation.Plot") == null)
        	plugin.config.set("System.UpkeepCalculation.Plot", 0.1);
		if(plugin.config.getString("System.UpkeepCalculation.Production") == null)
        	plugin.config.set("System.UpkeepCalculation.Production", 1.0);
		if(plugin.config.getString("System.UpkeepCalculation.Citizen") == null)
        	plugin.config.set("System.UpkeepCalculation.Citizen", 2.0);
		if(plugin.config.getString("System.UpkeepCalculation.Ally") == null)
        	plugin.config.set("System.UpkeepCalculation.Ally", 2.0);
		if(plugin.config.getString("System.UpkeepCalculation.Enemy") == null)
        	plugin.config.set("System.UpkeepCalculation.Enemy", 0.5);
		if(plugin.config.getString("System.UpkeepCalculation.Ally-Rights") == null)
        	plugin.config.set("System.UpkeepCalculation.Ally-Rights", 2.5);
		if(plugin.config.getString("System.UpkeepCalculation.Enemy-Rights") == null)
        	plugin.config.set("System.UpkeepCalculation.Enemy-Rights", 2.0);
		if(plugin.config.getString("System.UpkeepCalculation.TownGuard") == null)
        	plugin.config.set("System.UpkeepCalculation.TownGuard", 0.5);
		
		if(plugin.config.getString("System.UpkeepIntervalInDays") == null)
        	plugin.config.set("System.UpkeepIntervalInDays", 2);
		

		if(plugin.config.getString("Locations.Default.World") == null)
        	plugin.config.set("Locations.Default.World", "CaladAmar");
		if(plugin.config.getString("Locations.Default.X") == null)
        	plugin.config.set("Locations.Default.X", -216);
		if(plugin.config.getString("Locations.Default.Y") == null)
        	plugin.config.set("Locations.Default.Y", 64);
		if(plugin.config.getString("Locations.Default.Z") == null)
        	plugin.config.set("Locations.Default.Z", 52);

		if(plugin.config.getString("System.Distance.Crafting") == null)
        	plugin.config.set("System.Distance.Crafting", 10);
		if(plugin.config.getString("System.Distance.Founding") == null)
        	plugin.config.set("System.Distance.Founding", 500);	
		
		townUpgradeLevel2.add("Kapelle");
		townUpgradeLevel2.add("Taverne");
		
		townUpgradeLevel3.add("Kirche");
		townUpgradeLevel3.add("Gasthaus");
		
		if(plugin.config.getString("TownUpgrade.Level2") == null)
        	plugin.config.set("TownUpgrade.Level2", townUpgradeLevel2);	
		
		if(plugin.config.getString("TownUpgrade.Level3") == null)
        	plugin.config.set("TownUpgrade.Level3", townUpgradeLevel3);	

		if(plugin.config.getString("Admins") == null)
        	plugin.config.set("Admins", "[]");
		
		saveConfig();
		
		return true;
		
	}
	
}