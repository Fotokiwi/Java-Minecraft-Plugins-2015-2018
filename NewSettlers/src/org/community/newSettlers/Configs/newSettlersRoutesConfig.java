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
public class newSettlersRoutesConfig {
		
	private final newSettlers plugin;
	 	 
	public newSettlersRoutesConfig(newSettlers plugin)
	{
		this.plugin = plugin;
	}
	
	public void reloadConfig() {
	    if (plugin.routesFile == null) {
	    	plugin.routesFile = new File(plugin.getDataFolder(), "configs/routes.yml");
	    }
	    plugin.routes = YamlConfiguration.loadConfiguration(plugin.routesFile);

	}

	public FileConfiguration getConfig() {
	    if (plugin.routes == null) {
	        reloadConfig();
	    }
	    return plugin.routes;
	}
	
	public void saveConfig() {
	    if (plugin.routes == null || plugin.routesFile == null) {
	    return;
	    }
	    try {
	    	plugin.routes.save(plugin.routesFile);
	    } catch (IOException ex) {
	        Logger.getLogger(JavaPlugin.class.getName()).log(Level.SEVERE, "Could not save config to " + plugin.routesFile, ex);
	    }
	}
	
	public boolean initiateConfig() {
		
		reloadConfig();
		getConfig();
		
		if(plugin.routes.getString("Credit.Version") == null)
        	plugin.routes.set("Credit.Version", "v0.0.1");
		plugin.logprefix = "NewSettlers " + plugin.routes.getString("Credit.Version");
		
		/*if(plugin.routes.getString("Route.Beispiel.LocationShipA") == null)
        	plugin.routes.set("Route.Beispiel.LocationShipA", "Lichtwelt,50,75,50");
		if(plugin.routes.getString("Route.Beispiel.LocationShipB") == null)
        	plugin.routes.set("Route.Beispiel.LocationShipB", "Schattenwelt,50,75,50");
		if(plugin.routes.getString("Route.Beispiel.LocationEvent") == null)
        	plugin.routes.set("Route.Beispiel.LocationEvent", "Eventwelt,50,75,50");
		if(plugin.routes.getString("Route.Beispiel.MessageRadius") == null)
        	plugin.routes.set("Route.Beispiel.MessageRadius", 50);
		if(plugin.routes.getString("Route.Beispiel.TeleportRadius") == null)
        	plugin.routes.set("Route.Beispiel.TeleportRadius", 30);
		if(plugin.routes.getString("Route.Beispiel.MessageShipA") == null)
        	plugin.routes.set("Route.Beispiel.MessageShipA", "Seid bereit, bald geht es in die Ferne!");
		if(plugin.routes.getString("Route.Beispiel.MessageShipB") == null)
        	plugin.routes.set("Route.Beispiel.MessageShipB", "Heimat, wir kommen!");
		if(plugin.routes.getString("Route.Beispiel.MessageEventStart") == null)
        	plugin.routes.set("Route.Beispiel.MessageEventStart", "Ein Unwetter! Götter steht uns bei!");
		if(plugin.routes.getString("Route.Beispiel.MessageEventEnd") == null)
        	plugin.routes.set("Route.Beispiel.MessageEventEnd", "Der Wind steht g�nstig, wir haben es geschafft!");*/
		
		saveConfig();
		
		return true;
		
	}
	
}