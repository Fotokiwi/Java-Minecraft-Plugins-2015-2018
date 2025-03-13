package org.community.CodeActivation;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Random;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class CodeActivation extends JavaPlugin {
	
	public final static Logger log = Logger.getLogger("Minecraft");
	public static final String logprefix = "[CodeActivation 3.0.0]";
	
	public static FileConfiguration config = null;
    public File configFile = null;
    public static FileConfiguration language = null;
	public File languageFile = null;
    public static FileConfiguration cplayer = null;
	public File playerFile = null;
	
    public Permission permission = null;
	public Economy economy = null;
	
	File activationKeys = new File(this.getDataFolder(), "activationKeys.txt");
	
	public static void LogInfo(String Message) {
		
		log.info(logprefix + " " + Message);
		
	}
	
	public static void LogError(String Message) {
		
		log.log(Level.SEVERE, logprefix + " " + Message);
		
	}
	
	public static void LogWarning(String Message) {
		
		log.log(Level.WARNING, logprefix + " " + Message);
		
	}
	
	private Boolean setupPermissions()
    {
        RegisteredServiceProvider<Permission> permissionProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.permission.Permission.class);
        if (permissionProvider != null) {
            permission = permissionProvider.getProvider();
            LogInfo("succesfully connected permissions support with Vault");
        }
        return (permission != null);
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
	
	public void reloadLanguage() {
	    if (languageFile == null) {
	    	languageFile = new File(getDataFolder(), "language.yml");
	    }
	    language = YamlConfiguration.loadConfiguration(languageFile);
	}

	public FileConfiguration getLanguage() {
	    if (language == null) {
	        reloadLanguage();
	    }
	    return language;
	}
	
	public void saveLanguage() {
	    if (language == null || languageFile == null) {
	    return;
	    }
	    try {
	    	language.save(languageFile);
	    } catch (IOException ex) {
	        Logger.getLogger(JavaPlugin.class.getName()).log(Level.SEVERE, "Could not save config to " + languageFile, ex);
	    }
	}
	
	public void reloadPlayer() {
	    if (playerFile == null) {
	    	playerFile = new File(getDataFolder(), "player.yml");
	    }
	    cplayer = YamlConfiguration.loadConfiguration(playerFile);
	}

	public FileConfiguration getPlayer() {
	    if (cplayer == null) {
	        reloadPlayer();
	    }
	    return cplayer;
	}
	
	public void savePlayer() {
	    if (cplayer == null || playerFile == null) {
	    return;
	    }
	    try {
	    	cplayer.save(playerFile);
	    } catch (IOException ex) {
	        Logger.getLogger(JavaPlugin.class.getName()).log(Level.SEVERE, "Could not save config to " + playerFile, ex);
	    }
	}
	
	private Boolean loadConfig()
	{
		reloadConfig();
		getConfig();
		reloadLanguage();
		getLanguage();
		reloadPlayer();
		getPlayer();

        if(config.getString("General.DefaultActivationCommand") == null)
        	config.set("General.DefaultActivationCommand", "/pex user * group set Default");
        if(config.getString("General.DefaultState") == null)
        	config.set("General.DefaultState", true);
        if(config.getString("General.DefaultUnique") == null)
        	config.set("General.DefaultUnique", true);
        if(language.getString("General.DefaultRandom") == null)
        	language.set("General.DefaultRandom", false);
        if(config.getString("General.ActiveOnCreative") == null)
        	config.set("General.ActiveOnCreative", false);
        
        if (config.getString("ActivationKeys.Key") == null){
	        for (int i = 0; i < 5; i++) {
	        	String code = CodeActivationCodeGen.getNext();
	        	config.set("ActivationKeys.Key." + code + ".active", config.getBoolean("General.DefaultState", true));
	        	config.set("ActivationKeys.Key." + code + ".unique", config.getBoolean("General.DefaultUnique", true));
	        	config.set("ActivationKeys.Key." + code + ".random", config.getBoolean("General.DefaultRandom", false));
	        	config.set("ActivationKeys.Key." + code + ".command", config.getString("General.DefaultActivationCommand"));
	        }
	        // Christmas special
        	config.set("ActivationKeys.Key.christmasgift.active", true);
        	config.set("ActivationKeys.Key.christmasgift.unique", false);
        	config.set("ActivationKeys.Key.christmasgift.command", "/give * 354 1;/give * 371 1;/give * 357 1");
        }        
        
        saveConfig();
        
        if(language.getString("ACCESS_DENIED") == null)
        	language.set("ACCESS_DENIED", "You don't have permission to use that command!");
        if(language.getString("General.DefaultState") == null)
        	language.set("General.DefaultState", true);
        if(language.getString("General.DefaultUnique") == null)
        	language.set("General.DefaultUnique", true);
        if(language.getString("General.DefaultRandom") == null)
        	language.set("General.DefaultRandom", false);
		      
		return true;
	}

	private Boolean reloadConfigFile(Player player){
		
		reloadConfig();
		return true;
		
	}
	
	private Boolean reloadLanguageFile(Player player){
		
		reloadLanguage();
		return true;
		
	}
	
	private Boolean reloadPlayerFile(Player player){
		
		reloadPlayer();
		return true;
		
	}
	
	private Boolean reloadConfigs(Player player){
		
		reloadConfigFile(player);
		reloadLanguageFile(player);
		reloadPlayerFile(player);
		player.sendMessage(ChatColor.GREEN + "Config files successfully reloaded.");
		return true;
		
	}
	
	@SuppressWarnings("unused")
	private void reloadKeyGenerate(Player player){
		
		String code = CodeActivationCodeGen.getNext();
    	config.set("ActivationKeys.Key." + code + ".active", config.getBoolean("General.DefaultState", true));
    	config.set("ActivationKeys.Key." + code + ".unique", config.getBoolean("General.DefaultUnique", true));
    	config.set("ActivationKeys.Key." + code + ".random", config.getBoolean("General.DefaultRandom", false));
    	config.set("ActivationKeys.Key." + code + ".command", config.getString("General.DefaultActivationCommand"));
    	saveConfig();
		
	}
	
	private void showPluginInfo(Player player){
		player.sendMessage(ChatColor.GREEN + logprefix);
	}
	
	private void showActivationKeys(Player player){
		ConfigurationSection section = config.getConfigurationSection("ActivationKeys.Key");
		Set<String> keyList = section.getKeys(false);
		String[] getList = keyList.toArray(new String[0]);
		for (int i = 0; i < keyList.size(); i++) {
			player.sendMessage(ChatColor.GOLD + "" + getList[i]);
        }
	}
	
	private void regenerateActivationKeys(Player player){
		String keys = "";
		for (int i = 0; i < 10; i++) {
        	String code = CodeActivationCodeGen.getNext();
        	config.set("ActivationKeys.Key." + code + ".active", config.getBoolean("General.DefaultState", true));
        	config.set("ActivationKeys.Key." + code + ".unique", config.getBoolean("General.DefaultUnique", true));
        	config.set("ActivationKeys.Key." + code + ".random", config.getBoolean("General.DefaultRandom", true));
        	config.set("ActivationKeys.Key." + code + ".command", config.getString("General.DefaultActivationCommand"));
		    this.logToFile(activationKeys, code);
		    keys = keys + ", " + code;
        }		
        player.sendMessage(ChatColor.BLUE+"10 keys generated!");
        player.sendMessage(ChatColor.GOLD + keys);
        saveConfig();
        reloadConfigFile(player);
	}
	
	public void onEnable() {
		
		loadConfig();
		
		LogInfo("===========================================================");
		if (!setupPermissions()) {
			System.out.println("Null perm");
	        //use these if you require econ
	        //getServer().getPluginManager().disablePlugin(this);
	        //return;
	    }
		LogInfo("was successfully initiated.");
		LogInfo("===========================================================");
		
	}
	
	public void onDisable() {
		
		LogInfo("Plugin Disabled");
		
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		
		final Player player = (Player)sender;
		
		if(sender instanceof Player) {
			
			if(cmd.getName().equalsIgnoreCase("codeactivation")){
				if(args.length==0){
                    showPluginInfo(player);
                    return true;
				}else if(args.length==1){
					if(args[0].equalsIgnoreCase("activate")){
						
						if(permission.has(player, "codeactivation.user.activate")){
							player.sendMessage(ChatColor.RED+"Use /codeactivation activate KEY");
							return true;
						} else{
							player.sendMessage(ChatColor.RED + language.getString("ACCESS_DENIED"));
							return true;							
						}
						
					} else if(args[0].equalsIgnoreCase("list")){
						
						if(permission.has(player, "codeactivation.admin.list")){
							showActivationKeys(player);
							return true;
						} else{
							player.sendMessage(ChatColor.RED + language.getString("ACCESS_DENIED"));
							return true;							
						}
							
					} else if(args[0].equalsIgnoreCase("regenerate")){
						
						if(permission.has(player, "codeactivation.admin.regenerate")){
							regenerateActivationKeys(player);
							return true;
						} else{
							player.sendMessage(ChatColor.RED + language.getString("ACCESS_DENIED"));
							return true;							
						}
						
					} else if(args[0].equalsIgnoreCase("reload")){
						
						if(permission.has(player, "codeactivation.admin.reload")){
							reloadConfigs(player);
							player.sendMessage("Configs neu geladen!");
							return true;
						} else{
							player.sendMessage(ChatColor.RED + language.getString("ACCESS_DENIED"));
							return true;							
						}
						
					} else {
						
						player.sendMessage(ChatColor.BLUE+"Use a valid parameter: activate, list, regenerate, reload");
						return true;
						
					}
				}else if(args.length==2){
					if(args[0].equalsIgnoreCase("activate")){
						if(permission.has(player, "codeactivation.user.activate")){
							ConfigurationSection section = config.getConfigurationSection("ActivationKeys.Key");
							Set<String> keyList = section.getKeys(false);
							if(keyList.contains(args[1])){
								if (config.getBoolean("ActivationKeys.Key." + args[1] + ".active", false) == true){
									if (config.getBoolean("ActivationKeys.Key." + args[1] + ".unique", true) == false){
										if (cplayer.getString("PlayerLog." + player.getName() + "." + args[1]) == null){
											String command = config.getString("ActivationKeys.Key." + args[1] + ".command");
											if(player.getGameMode() == GameMode.CREATIVE && command.contains("/give") && config.getBoolean("General.ActiveOnCreative", false) == false){
												player.sendMessage(ChatColor.RED+"You can not activate this code in Creative-Mode.");
												return true;
											}							
											handleCommand(this, player, command);
											player.sendMessage(ChatColor.YELLOW+"You were successfully activated!");
											cplayer.set("PlayerLog." + player.getName() + "." + args[1], "true");
											//reloadKeyGenerate(player);
											savePlayer();
											reloadPlayerFile(player);
											return true;
										}else{
											player.sendMessage(ChatColor.RED+"You already used this code!");
											return true;
										}
									}
									else{
										String command = config.getString("ActivationKeys.Key." + args[1] + ".command");
										if(player.getGameMode() == GameMode.CREATIVE && command.contains("/give") && config.getBoolean("General.ActiveOnCreative", false) == false){
											player.sendMessage(ChatColor.RED+"You can not activate this code in Creative-Mode.");
											return true;
										}						
										handleCommand(this, player, command);
										player.sendMessage(ChatColor.YELLOW+"You were successfully activated!");
										config.set("ActivationKeys.Key." + args[1], null);
										//reloadKeyGenerate(player);
										saveConfig();
										reloadConfigFile(player);
										return true;
									}
								} else{
									player.sendMessage(ChatColor.RED+"This code is not active anymore!");
									return true;
								}
							} else{
								player.sendMessage(ChatColor.RED+"It's no a valid activation key!");
								return true;
							}
						} else{
							player.sendMessage(ChatColor.RED + language.getString("ACCESS_DENIED"));
							return true;							
						}			
						
					} else if(args[0].equalsIgnoreCase("random")){
						if(permission.has(player, "codeactivation.user.activate")){
							ConfigurationSection section = config.getConfigurationSection("ActivationKeys.Key");
							Set<String> keyList = section.getKeys(false);
							if(keyList.contains(args[1])){
								if(config.getBoolean("ActivationKeys.Key." + args[1] + ".random", false) == true){
									if (config.getBoolean("ActivationKeys.Key." + args[1] + ".active", false) == true){
										if (config.getBoolean("ActivationKeys.Key." + args[1] + ".unique", true) == false){
											if (cplayer.getString("PlayerLog." + player.getName() + "." + args[1]) == null){
												String command = config.getString("ActivationKeys.Key." + args[1] + ".command");
												if(player.getGameMode() == GameMode.CREATIVE && command.contains("/give") && config.getBoolean("General.ActiveOnCreative", false) == false){
													player.sendMessage(ChatColor.RED+"You can not activate this code in Creative-Mode.");
													return true;
												}							
												handleRandomCommand(this, player, command);
												player.sendMessage(ChatColor.YELLOW+"Gratulations, you have won an animal!");
												cplayer.set("PlayerLog." + player.getName() + "." + args[1], "true");
												//reloadKeyGenerate(player);
												savePlayer();
												reloadPlayerFile(player);
												return true;
											}else{
												player.sendMessage(ChatColor.RED+"You already used this code!");
												return true;
											}
										}
										else{
											String command = config.getString("ActivationKeys.Key." + args[1] + ".command");
											if(player.getGameMode() == GameMode.CREATIVE && command.contains("/give") && config.getBoolean("General.ActiveOnCreative", false) == false){
												player.sendMessage(ChatColor.RED+"You can not activate this code in Creative-Mode.");
												return true;
											}						
											handleCommand(this, player, command);
											player.sendMessage(ChatColor.YELLOW+"You were successfully activated!");
											config.set("ActivationKeys.Key." + args[1], null);
											//reloadKeyGenerate(player);
											saveConfig();
											reloadConfigFile(player);
											return true;
										}
									} else{
										player.sendMessage(ChatColor.RED+"This code is not active anymore!");
										return true;
									}
								} else{
									player.sendMessage(ChatColor.RED+"This code is not a random key!");
									return true;
								}
							} else{
								player.sendMessage(ChatColor.RED+"It's no a valid activation key!");
								return true;
							}
						} else{
							player.sendMessage(ChatColor.RED + language.getString("ACCESS_DENIED"));
							return true;							
						}			
						
					} else {
						
						player.sendMessage(ChatColor.BLUE+"Use a valid parameter: activate, list, regenerate, reload");
						return true;
						
					}
				}
			}
			
		}
		
		return false;
	}
	
	protected static boolean handleCommand(CodeActivation plugin, Player player, String s) {
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
			LogError("executing Console-Command '"+command+"'");
			if (!Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command)) {
				LogError("Could not execute Console-Command '"+command+"'");
				return false;
			}
			command = "";
		}
		return true;
	}
	
	protected static boolean handleRandomCommand(CodeActivation plugin, Player player, String s) {
		if(s.length()<=1) return false;
		String[] commandList = s.split(";");
		//CraftServer cs = (CraftServer)plugin.getServer();
		String command = "";
		Random dice = new Random();
		
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
			int diceRoll = dice.nextInt(100 + 1);
			if (diceRoll <= 25){
				LogError("executing Console-Command '"+command+"'");
				if (!Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command)) {
					LogError("Could not execute Console-Command '"+command+"'");
					return false;
				}
				player.sendMessage(ChatColor.GOLD+"Congratulations, you're a winner!");
				return true;
			}
			command = "";
		}
		return true;
	}
	
	public static HashMap<File, BufferedWriter> writers = new HashMap<File, BufferedWriter>();
	public BufferedWriter getBufferedWriter(File f) {
	    try {
	        if (writers.containsKey(f)) {
	            LogInfo("found writer for file " + f.getName());
	            return writers.get(f);
	        } else {
	            LogInfo("Couldn't find writer for file " + f.getName());
	            BufferedWriter returns = new BufferedWriter(new FileWriter(f, true));
	            writers.put(f, returns);
	            return returns;
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	}
	 
	public void logToFile(File f, String message) {
	  try {
	          if (!f.exists()) {
	              f.getParentFile().mkdirs();
	              f.createNewFile();
	          }
	          BufferedWriter br = getBufferedWriter(f);
	          br.write(message);
	          br.newLine();
	          br.flush();
	  } catch (Exception e) {
	      e.printStackTrace();
	  }
	}
	
}