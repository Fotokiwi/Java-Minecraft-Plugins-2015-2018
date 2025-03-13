package org.community.EpicFighters;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Snowball;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.community.EpicFighters.Class.Assassine.*;
import org.community.EpicFighters.Class.Berserker.*;
import org.community.EpicFighters.Class.Mage.*;
import org.community.EpicFighters.Configs.*;
import org.community.EpicFighters.Core.*;
import org.community.EpicFighters.Listener.*;
import org.community.ancientRelics.ancientRelics;
import org.community.fourWays.fourWays;
import org.fusesource.jansi.Ansi;


public class EpicFighters extends JavaPlugin {
	
	public Logger log = Logger.getLogger("Minecraft");
	public String logprefix = "[EpicFighters 3.0.0]";
	
	final static public String CMD_COLOR_GOLD = Ansi.ansi().fg(Ansi.Color.YELLOW).bold().toString();
	final static public String CMD_COLOR_AQUA = Ansi.ansi().fg(Ansi.Color.CYAN).bold().toString();
	final static public String CMD_COLOR_RED = Ansi.ansi().fg(Ansi.Color.RED).bold().toString();
	final static public String CMD_COLOR_DEFAULT = Ansi.ansi().reset().toString();

	public FileConfiguration config = null;
    public File configFile = null;    
    public FileConfiguration user = null;
	public File userFile = null;
    public FileConfiguration skill = null;
	public File skillFile = null;
    public FileConfiguration cooldown = null;
	public File cooldownFile = null;

	public Map<Player, Long> dpsDelay = new HashMap<Player, Long>();
	public Map<Entity, Boolean> charmedEntities = new HashMap<Entity, Boolean>();
	public Map<Arrow, String> shotArrows = new HashMap<Arrow, String>();
	public Map<Arrow, Player> shotArrowsOwner = new HashMap<Arrow, Player>();
	
	public Map<Fireball, String> shotFireballs = new HashMap<Fireball, String>();
	public Map<Fireball, Player> shotFireballsOwner = new HashMap<Fireball, Player>();
	public Map<Snowball, String> shotSnowballs = new HashMap<Snowball, String>();
	public Map<Snowball, Player> shotSnowballsOwner = new HashMap<Snowball, Player>();
	public Map<Arrow, String> shotMagicArrows = new HashMap<Arrow, String>();
	public Map<Arrow, Player> shotMagicArrowsOwner = new HashMap<Arrow, Player>();
	
	public Map<Player, String> playerAttack = new HashMap<Player, String>();
	
	public Random randomGenerator = new Random();

	public int otherFight = 2000;
	public int handFight = 1000;
	public int woodSword = 1600;
	public int stoneSword = 1250;
	public int ironSword = 857;
	public int goldSword = 470;
	public int diamondSword = 777;
    
    //Config Klassen
	public epicFightersConfig eFConfig = null;    
    public epicFightersUserConfig eFUser = null;   
    public epicFightersSkillsConfig eFSkills = null;  
    public epicFightersCooldownConfig eFCooldown = null;
    //Hilfe Klassen

    //Command Klassen

    //Mage Klassen
    public epicFightersMageDamageSpells eFMageDamageSpells = null;
    public epicFightersMageCastTranslation eFMageCastTranslation = null;
    //Melee Klassen
    public EpicFightersBerserkerDamageSpells eFBerserkerDamageSpells = null;
    public EpicFightersBerserkerCastTranslation eFBerserkerCastTranslation = null;
    //Assassine Klassen
    public epicFightersAssassineDamageSkills eFAssassineDamageSkills = null;
    public epicFightersAssassineSkillTranslation eFAssassineSkillTranslation = null;
    //Core Klassen
    public epicFightersScrollActiveSkill eFSkillScroll = null;
    public epicFightersCore eFCore = null;
    public epicFightersDamageCalculation eFDmg = null;
    //FourWays User Klasse
    public fourWays fourWaysAPI = null;
    //AncientRelics Klasse
    public ancientRelics ancientRelicsAPI = null;
    
	public void LogInfo(String Message) {
		
		log.info(logprefix + " " + Message);
		
	}
	
	public void LogDebug(String Message) {
		
		if(config.getBoolean("Config.Debug"))
			log.info(logprefix + " [DEBUG]: " + Message);
		
	}
	
	public void LogError(String Message) {
		
		log.log(Level.SEVERE, CMD_COLOR_RED + logprefix + " " + Message);
		
	}
	
	public void LogWarning(String Message) {
		
		log.log(Level.WARNING, CMD_COLOR_RED + logprefix + " " + Message);
		
	}
	
	public void onEnable() {
		
		LogInfo("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
		if (load()) {			
			LogInfo("successfully initialized.");
		} else {
			LogWarning("error: initialization failure!");
		}
		LogInfo("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
		
	}
	
	public void onDisable() {
	
		LogInfo("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
		if(save()) {			
			LogInfo("successfully disabled.");
		}
		LogInfo("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
				
	}
	
	public boolean load() {
		
		eFConfig = new epicFightersConfig(this);
		eFUser = new epicFightersUserConfig(this);
		eFSkills = new epicFightersSkillsConfig(this);
		eFCooldown = new epicFightersCooldownConfig(this);
		eFSkillScroll = new epicFightersScrollActiveSkill(this);		
		eFCore = new epicFightersCore(this);
		eFDmg = new epicFightersDamageCalculation(this);
		fourWaysAPI = (fourWays) Bukkit.getServer().getPluginManager().getPlugin("FourWays");
		ancientRelicsAPI = (ancientRelics) Bukkit.getServer().getPluginManager().getPlugin("AncientRelics");

	    eFMageDamageSpells = new epicFightersMageDamageSpells(this);
	    eFMageCastTranslation = new epicFightersMageCastTranslation(this);
	    
	    eFBerserkerDamageSpells = new EpicFightersBerserkerDamageSpells(this);
	    eFBerserkerCastTranslation = new EpicFightersBerserkerCastTranslation(this);
	    
	    eFAssassineDamageSkills = new epicFightersAssassineDamageSkills(this);
	    eFAssassineSkillTranslation = new epicFightersAssassineSkillTranslation(this);
		
		if(eFConfig.initiateConfig()){
			LogInfo("initialized: config.yml");
		} else {
			LogWarning("error: config.yml couldn't be initiated.");
			return false;
		}
		
		if(eFUser.initiateConfig()){
			LogInfo("initialized: user.yml");
		} else {
			LogWarning("error: user.yml couldn't be initiated.");
			return false;
		}
		
		if(eFSkills.initiateConfig()){
			LogInfo("initialized: skills.yml");
		} else {
			LogWarning("error: skills.yml couldn't be initiated.");
			return false;
		}
		
		if(eFCooldown.initiateConfig()){
			LogInfo("initialized: cooldown.yml");
		} else {
			LogWarning("error: cooldown.yml couldn't be initiated.");
			return false;
		}

		getServer().getPluginManager().registerEvents(new epicFightersEntityDamageByEntityEvent(this), this);
		LogInfo("initialized: EntityDamageByEntityEvent");
		getServer().getPluginManager().registerEvents(new epicFightersEntityExplodeEvent(this), this);
		LogInfo("initialized: EntityExplodeEvent");
		getServer().getPluginManager().registerEvents(new epicFightersEntityTargetEntityEvent(this), this);
		LogInfo("initialized: EntityTargetEvent");
		getServer().getPluginManager().registerEvents(new epicFightersInventoryClickEvent(this), this);
		LogInfo("initialized: InventoryClickEvent");
		getServer().getPluginManager().registerEvents(new epicFightersInventoryCloseEvent(this), this);
		LogInfo("initialized: InventoryCloseEvent");
		getServer().getPluginManager().registerEvents(new epicFightersPlayerInteractEvent(this), this);
		LogInfo("initialized: PlayerInteractEvent");
		getServer().getPluginManager().registerEvents(new epicFightersMageEntityDamageByEntityEvent(this), this);
		LogInfo("initialized: Mage - EntityDamageByEntityEvent");
		getServer().getPluginManager().registerEvents(new epicFightersMagePlayerInteractEvent(this), this);
		LogInfo("initialized: Mage - PlayerInteractEvent");
		getServer().getPluginManager().registerEvents(new epicFightersBerserkerEntityDamageByEntityEvent(this), this);
		LogInfo("initialized: Berserker - EntityDamageByEntityEvent");
		getServer().getPluginManager().registerEvents(new EpicFightersBerserkerPlayerInteractEvent(this), this);
		LogInfo("initialized: Berserker - PlayerInteractEvent");
		getServer().getPluginManager().registerEvents(new epicFightersAssassineEntityDamageByEntityEvent(this), this);
		LogInfo("initialized: Assassine - EntityDamageByEntityEvent");
		getServer().getPluginManager().registerEvents(new epicFightersAssassineEntityShootBowEvent(this), this);
		LogInfo("initialized: Assassine - EntityShootBowEvent");
		getServer().getPluginManager().registerEvents(new epicFightersAssassineProjectileHitEvent(this), this);
		LogInfo("initialized: Assassine - ProjectileHitEvent");
		
		return true;
	}
	
	public boolean save() {
		eFConfig.saveConfig();
		
		eFUser.saveConfig();
		
		eFSkills.saveConfig();
		
		eFCooldown.saveConfig();
		return true;
		
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		
		if(sender instanceof Player) {
			
			/*if(cmd.getName().equalsIgnoreCase("skill"))
				new epicFightersSkillScreen(this, (Player) sender);
			if(cmd.getName().equalsIgnoreCase("zauberstab")) {
				
				ItemStack wand = new ItemStack(Material.BLAZE_ROD);
				ItemMeta meta = wand.getItemMeta();
				meta.setDisplayName("Zauberstab");
				ArrayList<String> lore = new ArrayList<String>();
				lore.add("Zauberstab des Elementarmagiers");
				meta.setLore(lore);
				wand.setItemMeta(meta);

				((Player) sender).getWorld().dropItemNaturally(((Entity) sender).getLocation(), wand).getItemStack();			
					
			}*/
			
			return true;
			
		} else {
			
			sender.sendMessage("Die aktuelle Version unterstützt keine Konsolen-Befehle.");
			return true;
			
		}
	}
}