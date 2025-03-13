package org.community.FoodControl;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.community.FoodControl.Listener.playerListener;

public class FoodControl extends JavaPlugin {
	
	public final static Logger log = Logger.getLogger("Minecraft");
	public static final String logprefix = "[FoodControl 3.0.0]";

	public FileConfiguration config = null;
    public File configFile = null;
	public FileConfiguration meals = null;
    public File mealsFile = null;

	playerListener playerListener = null;
	
	public Map<Player, String> foodOrder = new HashMap<Player, String>();
	
	public Set<String> allKeys;
	
	public static void LogInfo(String Message) {
		
		log.info(logprefix + " " + Message);
		
	}
	
	public static void LogError(String Message) {
		
		log.log(Level.SEVERE, logprefix + " " + Message);
		
	}
	
	public static void LogWarning(String Message) {
		
		log.log(Level.WARNING, logprefix + " " + Message);
		
	}
	
	public void reloadConfig() {
	    if (configFile == null) {
	    configFile = new File(getDataFolder(), "config.yml");
	    }
	    config = YamlConfiguration.loadConfiguration(configFile);

	}

	public FileConfiguration getConfig() {
	    if (config == null) {
	        reloadConfig();
	    }
	    return config;
	}
	
	public void saveConfig() {
	    if (config == null || configFile == null) {
	    return;
	    }
	    try {
	        config.save(configFile);
	    } catch (IOException ex) {
	        Logger.getLogger(JavaPlugin.class.getName()).log(Level.SEVERE, "Could not save config to " + configFile, ex);
	    }
	}
	
	public void reloadMeals() {
	    if (mealsFile == null) {
	    mealsFile = new File(getDataFolder(), "meals.yml");
	    }
	    meals = YamlConfiguration.loadConfiguration(mealsFile);

	}

	public FileConfiguration getMeals() {
	    if (meals == null) {
	        reloadConfig();
	    }
	    return meals;
	}
	
	public void saveMeals() {
	    if (meals == null || mealsFile == null) {
	    return;
	    }
	    try {
	        meals.save(mealsFile);
	    } catch (IOException ex) {
	        Logger.getLogger(JavaPlugin.class.getName()).log(Level.SEVERE, "Could not save meals to " + mealsFile, ex);
	    }
	}
	

	
	private Boolean loadConfig()
	{
		reloadConfig();
		getConfig();
		reloadMeals();
		getMeals();

		if(config.getString("Food.APPLE.Hunger") == null)
        	config.set("Food.APPLE.Hunger", 0);
		if(config.getString("Food.APPLE.Health") == null)
        	config.set("Food.APPLE.Health", 0);
		if(config.getString("Food.APPLE.Saturation") == null)
        	config.set("Food.APPLE.Saturation", 0);

		if(config.getString("Food.MUSHROOM_SOUP.Hunger") == null)
        	config.set("Food.MUSHROOM_SOUP.Hunger", 8);
		if(config.getString("Food.MUSHROOM_SOUP.Health") == null)
        	config.set("Food.MUSHROOM_SOUP.Health", 2);
		if(config.getString("Food.MUSHROOM_SOUP.Saturation") == null)
        	config.set("Food.MUSHROOM_SOUP.Saturation", 10);

		if(config.getString("Food.BREAD.Hunger") == null)
        	config.set("Food.BREAD.Hunger", 4);
		if(config.getString("Food.BREAD.Health") == null)
        	config.set("Food.BREAD.Health", 0);
		if(config.getString("Food.BREAD.Saturation") == null)
        	config.set("Food.BREAD.Saturation", 6);

		if(config.getString("Food.PORK.Hunger") == null)
        	config.set("Food.PORK.Hunger", -4);
		if(config.getString("Food.PORK.Health") == null)
        	config.set("Food.PORK.Health", -4);
		if(config.getString("Food.PORK.Saturation") == null)
        	config.set("Food.PORK.Saturation", -2);

		if(config.getString("Food.GRILLED_PORK.Hunger") == null)
        	config.set("Food.GRILLED_PORK.Hunger", 6);
		if(config.getString("Food.GRILLED_PORK.Health") == null)
        	config.set("Food.GRILLED_PORK.Health", 0);
		if(config.getString("Food.GRILLED_PORK.Saturation") == null)
        	config.set("Food.GRILLED_PORK.Saturation", 13);

		if(config.getString("Food.GOLDEN_APPLE.Hunger") == null)
        	config.set("Food.GOLDEN_APPLE.Hunger", 10);
		if(config.getString("Food.GOLDEN_APPLE.Health") == null)
        	config.set("Food.GOLDEN_APPLE.Health", 20);
		if(config.getString("Food.GOLDEN_APPLE.Saturation") == null)
        	config.set("Food.GOLDEN_APPLE.Saturation", 10);

		if(config.getString("Food.RAW_FISH.Hunger") == null)
        	config.set("Food.RAW_FISH.Hunger", -4);
		if(config.getString("Food.RAW_FISH.Health") == null)
        	config.set("Food.RAW_FISH.Health", -4);
		if(config.getString("Food.RAW_FISH.Saturation") == null)
        	config.set("Food.RAW_FISH.Saturation", -2);

		if(config.getString("Food.COOKED_FISH.Hunger") == null)
        	config.set("Food.COOKED_FISH.Hunger", 2);
		if(config.getString("Food.COOKED_FISH.Health") == null)
        	config.set("Food.COOKED_FISH.Health", 0);
		if(config.getString("Food.COOKED_FISH.Saturation") == null)
        	config.set("Food.COOKED_FISH.Saturation", 6);

		if(config.getString("Food.CAKE_BLOCK.Hunger") == null)
        	config.set("Food.CAKE_BLOCK.Hunger", 0);
		if(config.getString("Food.CAKE_BLOCK.Health") == null)
        	config.set("Food.CAKE_BLOCK.Health", 1);
		if(config.getString("Food.CAKE_BLOCK.Saturation") == null)
        	config.set("Food.CAKE_BLOCK.Saturation", 2);

		if(config.getString("Food.COOKIE.Hunger") == null)
        	config.set("Food.COOKIE.Hunger", 2);
		if(config.getString("Food.COOKIE.Health") == null)
        	config.set("Food.COOKIE.Health", 0);
		if(config.getString("Food.COOKIE.Saturation") == null)
        	config.set("Food.COOKIE.Saturation", 1);

		if(config.getString("Food.MELON.Hunger") == null)
        	config.set("Food.MELON.Hunger", 1);
		if(config.getString("Food.MELON.Health") == null)
        	config.set("Food.MELON.Health", 0);
		if(config.getString("Food.MELON.Saturation") == null)
        	config.set("Food.MELON.Saturation", 1);

		if(config.getString("Food.RAW_BEEF.Hunger") == null)
        	config.set("Food.RAW_BEEF.Hunger", -4);
		if(config.getString("Food.RAW_BEEF.Health") == null)
        	config.set("Food.RAW_BEEF.Health", -4);
		if(config.getString("Food.RAW_BEEF.Saturation") == null)
        	config.set("Food.RAW_BEEF.Saturation", -2);

		if(config.getString("Food.COOKED_BEEF.Hunger") == null)
        	config.set("Food.COOKED_BEEF.Hunger", 5);
		if(config.getString("Food.COOKED_BEEF.Health") == null)
        	config.set("Food.COOKED_BEEF.Health", 0);
		if(config.getString("Food.COOKED_BEEF.Saturation") == null)
        	config.set("Food.COOKED_BEEF.Saturation", 13);

		if(config.getString("Food.RAW_CHICKEN.Hunger") == null)
        	config.set("Food.RAW_CHICKEN.Hunger", -4);
		if(config.getString("Food.RAW_CHICKEN.Health") == null)
        	config.set("Food.RAW_CHICKEN.Health", -4);
		if(config.getString("Food.RAW_CHICKEN.Saturation") == null)
        	config.set("Food.RAW_CHICKEN.Saturation", -2);

		if(config.getString("Food.CHICKEN.Hunger") == null)
        	config.set("Food.CHICKEN.Hunger", 7);
		if(config.getString("Food.CHICKEN.Health") == null)
        	config.set("Food.CHICKEN.Health", 0);
		if(config.getString("Food.CHICKEN.Saturation") == null)
        	config.set("Food.CHICKEN.Saturation", 7);

		if(config.getString("Food.ROTTEN_FLESH.Hunger") == null)
        	config.set("Food.ROTTEN_FLESH.Hunger", -6);
		if(config.getString("Food.ROTTEN_FLESH.Health") == null)
        	config.set("Food.ROTTEN_FLESH.Health", -6);
		if(config.getString("Food.ROTTEN_FLESH.Saturation") == null)
        	config.set("Food.ROTTEN_FLESH.Saturation", -2);

		if(config.getString("Food.SPIDER_EYE.Hunger") == null)
        	config.set("Food.SPIDER_EYE.Hunger", -4);
		if(config.getString("Food.SPIDER_EYE.Health") == null)
        	config.set("Food.SPIDER_EYE.Health", -4);
		if(config.getString("Food.SPIDER_EYE.Saturation") == null)
        	config.set("Food.SPIDER_EYE.Saturation", -2);
        
		//if(meals.getString("Example.BREAD,BREAD,BREAD,BREAD,BREAD.Effect") == null)
        //	meals.set("Example.BREAD,BREAD,BREAD,BREAD,BREAD.Effect", "FAST_DIGGING");
		//if(meals.getString("Example.BREAD,BREAD,BREAD,BREAD,BREAD.Duration") == null)
        //	meals.set("Example.BREAD,BREAD,BREAD,BREAD,BREAD.Duration", "FAST_DIGGING");
		//if(meals.getString("Example.BREAD,BREAD,BREAD,BREAD,BREAD.Strength") == null)
        //	meals.set("Example.BREAD,BREAD,BREAD,BREAD,BREAD.Strength", "FAST_DIGGING");
		
        saveConfig();
        saveMeals();
		      
		return true;
	}
	
	public void onEnable() {
		
		loadConfig();
		
		LogInfo("=============================");
		
		ConfigurationSection foodSection = config.getConfigurationSection("Food");
		allKeys = foodSection.getKeys(false);

		getServer().getPluginManager().registerEvents(new playerListener(this), this);
		LogInfo("initialized: PlayerListener");
	
		LogInfo("was successfully initiated.");
		LogInfo("=============================");
		
	}
	
	public void onDisable() {
		
		LogInfo("Plugin Disabled");
		
	}
	
	private void showPluginInfo(CommandSender sender){
		sender.sendMessage(ChatColor.GREEN + logprefix);
	}
	
	@SuppressWarnings("unused")
	private void configReload(CommandSender sender){
		reloadConfig();
		sender.sendMessage(ChatColor.BLUE+ "Config successfully reloaded.");
	}
	
	private void allReload(CommandSender sender){
		reloadConfig();
		sender.sendMessage(ChatColor.BLUE+ "All files successfully reloaded.");
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
	
		if(cmd.getName().equalsIgnoreCase("foodcontrol")){
			Player p = (Player) sender;
			if(!isAdmin(p))
				return true;
			if(args.length==0){
                   showPluginInfo(sender);
                   return true;
			} else if(args.length==1) {
				if(args[0].equalsIgnoreCase("reload")) {
					allReload(sender);
					return true;
				} else if(args[0].equalsIgnoreCase("saturation")) {
					sender.sendMessage("Saturation: " + p.getSaturation());
					return true;
				} else if (args[0].equalsIgnoreCase("testfood")){
				    playerListener.testfood(p);
			    } else {
					sender.sendMessage("Invalid Parameter!");
					return true;
				}
			} else if(args[0].equalsIgnoreCase("info")) { 
				if(allKeys.contains("" + args[1])) {
					Material food = Material.getMaterial(args[1]);
					if(food == null) {
						sender.sendMessage(ChatColor.RED + args[1] + " ist kein gültiges Lebensmittel.");
					} else {
						sender.sendMessage(ChatColor.BLUE + "-----------------------------------");
						sender.sendMessage(ChatColor.GOLD + food.name());
						sender.sendMessage(ChatColor.BLUE + "-----------------------------------");
						sender.sendMessage(ChatColor.GREEN + "Lebenspunkte: " + ChatColor.YELLOW + config.getInt("Food." + food.name() + ".Health", 0) + ChatColor.GREEN + ChatColor.YELLOW + " - " + config.getInt("Food." + food.name() + ".Health", 0)/2 + ChatColor.GREEN + " Leben");
						sender.sendMessage(ChatColor.GREEN + "Hungerpunkte: " + ChatColor.YELLOW + config.getInt("Food." + food.name() + ".Hunger", 0) + ChatColor.GREEN + ChatColor.YELLOW + " - " + config.getInt("Food." + food.name() + ".Hunger", 0)/2 + ChatColor.GREEN + " Hunger");
						sender.sendMessage(ChatColor.GREEN + "Sättigung: " + ChatColor.YELLOW + config.getDouble("Food." + food.name() + ".Saturation", 0));
						sender.sendMessage(ChatColor.BLUE + "-----------------------------------");
					}
					
				} else {
					return true;
				}
			} else if(args[0].equalsIgnoreCase("saturation")) {
		    	Player player = (Player) sender;
		    	player.setSaturation(Integer.parseInt(args[1]));
		    	return true;
		    } else if(args[0].equalsIgnoreCase("hunger")) {
		    	Player player = (Player) sender;
		    	player.setFoodLevel(Integer.parseInt(args[1]));
		    	return true;
		    }  else {
				sender.sendMessage("Invalid Parameter!");
				return true;
			}
		}
		return false;
	}		
	
	public boolean isAdmin(Player player) {
		if(config.getList("Config.Admins") != null) {
			if(config.getList("Config.Admins").contains(player.getUniqueId().toString()))
				return true;
		}
		
		return false;
	}
}