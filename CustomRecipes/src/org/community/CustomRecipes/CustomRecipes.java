package org.community.CustomRecipes;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;
import org.community.CustomRecipes.Listener.customRecipesPrepareCraftEvent;

public class CustomRecipes extends JavaPlugin {

	public final static Logger log = Logger.getLogger("Minecraft");
	public static final String logprefix = "[CustomRecipes]";

	public FileConfiguration config = null;
	public File configFile = null;
	public FileConfiguration alternatives = null;
	public File alternativesFile = null;
	
	public customRecipesPrepareCraftEvent cRPCE = null;

	final static Enchantment PROTECTION = Enchantment.PROTECTION_ENVIRONMENTAL;
	final static Enchantment FIRE_PROTECTION = Enchantment.PROTECTION_FIRE;
	final static Enchantment FEATHER_FALLING = Enchantment.PROTECTION_FALL;
	final static Enchantment BLAST_PROTECTION = Enchantment.PROTECTION_EXPLOSIONS;
	final static Enchantment PROJECTILE_PROTECTION = Enchantment.PROTECTION_PROJECTILE;
	final static Enchantment RESPIRATION = Enchantment.OXYGEN;
	final static Enchantment AQUA_AFFINITY = Enchantment.WATER_WORKER;
	final static Enchantment SHARPNESS = Enchantment.DAMAGE_ALL;
	final static Enchantment SMITE = Enchantment.DAMAGE_UNDEAD;
	final static Enchantment BANE = Enchantment.DAMAGE_ARTHROPODS;
	final static Enchantment KNOCKBACK = Enchantment.KNOCKBACK;
	final static Enchantment FIRE_ASPECT = Enchantment.FIRE_ASPECT;
	final static Enchantment LOOTING = Enchantment.LOOT_BONUS_MOBS;
	final static Enchantment EFFICIENCY = Enchantment.DIG_SPEED;
	final static Enchantment SILK_TOUCH = Enchantment.SILK_TOUCH;
	final static Enchantment UNBREAKING = Enchantment.DURABILITY;
	final static Enchantment FORTUNE = Enchantment.LOOT_BONUS_BLOCKS;
	final static Enchantment POWER = Enchantment.ARROW_DAMAGE;
	final static Enchantment PUNCH = Enchantment.ARROW_KNOCKBACK;
	final static Enchantment FLAME = Enchantment.ARROW_FIRE;
	final static Enchantment INFINITE = Enchantment.ARROW_INFINITE;
	final static Enchantment THORNS = Enchantment.THORNS;


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

	public void reloadAlternatives() {
		if (alternativesFile == null) {
			alternativesFile = new File(getDataFolder(), "alternatives.yml");
		}
		alternatives = YamlConfiguration.loadConfiguration(alternativesFile);

	}

	public FileConfiguration getAlternatives() {
		if (alternatives == null) {
			reloadAlternatives();
		}
		return alternatives;
	}

	public void saveAlternatives() {
		if (alternatives == null || alternativesFile == null) {
			return;
		}
		try {
			alternatives.save(alternativesFile);
		} catch (IOException ex) {
			Logger.getLogger(JavaPlugin.class.getName()).log(Level.SEVERE, "Could not save alternatives to " + alternativesFile, ex);
		}
	}


	@SuppressWarnings("deprecation")
	public void buildRecipes(){

		for(String key : getConfig().getConfigurationSection("Potion").getKeys(false)){
			int matsub = getConfig().getInt("Potion."+key+".SubID");
			int amount = getConfig().getInt("Potion."+key+".OutputMenge");

			ItemStack stack = new ItemStack(Material.POTION, amount, (short)matsub);
			Boolean bol = getConfig().getBoolean("Potion."+key+".Splash");
			if( bol == true){
				stack = new Potion (PotionType.WATER, amount, bol).toItemStack(amount);
			}
			ItemMeta meta = stack.getItemMeta();
			meta.setDisplayName(getConfig().getString("Potion."+key+".Displayname"));
			if(getConfig().getString("Potion."+key+".Lore")!=null){
				ArrayList<String>desc=new ArrayList<String>();
				desc.add(getConfig().getString("Potion."+key+".Lore"));
				meta.setLore(desc);
			}
			stack.setItemMeta(meta);
			stack.setDurability((short)matsub);
			PotionMeta pmeta = (PotionMeta) stack.getItemMeta();
			pmeta.clearCustomEffects();
			int count = getConfig().getInt("Potion."+key+".Effektmenge");
			for(int i=1; count >= i; i++ ) {

				pmeta.addCustomEffect(new PotionEffect(PotionEffectType.getByName(getConfig().getString("Potion."+key+".Effekt"+i)),getConfig().getInt("Potion."+key+".Dauer"+count),getConfig().getInt("Potion."+key+".Level"+count)), true);	
			}
			stack.setItemMeta(pmeta);
			if(getConfig().getString("Potion."+key+".Recipetyp").equalsIgnoreCase("shaped")){
				ShapedRecipe stackrecipe = new ShapedRecipe(stack);
				String oben;
				String mitte;
				String unten;

				if(getConfig().getString("Potion."+key+".Oben") != null && getConfig().getString("Potion."+key+".Mitte") == null && getConfig().getString("Potion."+key+".Unten") == null){					
					oben = getConfig().getString("Potion."+key+".Oben");
					String[] shape = {oben};
					stackrecipe.shape(shape);
				}
				if(getConfig().getString("Potion."+key+".Oben") == null && getConfig().getString("Potion."+key+".Mitte") != null && getConfig().getString("Potion."+key+".Unten") == null){
					mitte = getConfig().getString("Potion."+key+".Mitte");
					String[] shape = {mitte};
					stackrecipe.shape(shape);
				}
				if(getConfig().getString("Potion."+key+".Oben") == null && getConfig().getString("Potion."+key+".Mitte") == null && getConfig().getString("Potion."+key+".Unten") != null){
					unten = getConfig().getString("Potion."+key+".Unten");
					String[] shape = {unten};
					stackrecipe.shape(shape);
				}
				if(getConfig().getString("Potion."+key+".Oben") != null && getConfig().getString("Potion."+key+".Mitte") != null && getConfig().getString("Potion."+key+".Unten") == null){
					oben = getConfig().getString("Potion."+key+".Oben");
					mitte = getConfig().getString("Potion."+key+".Mitte");
					String[] shape = {oben,mitte};
					stackrecipe.shape(shape);
				}
				if(getConfig().getString("Potion."+key+".Oben") != null && getConfig().getString("Potion."+key+".Mitte") == null && getConfig().getString("Potion."+key+".Unten") != null){
					oben = getConfig().getString("Potion."+key+".Oben");
					unten = getConfig().getString("Potion."+key+".Unten");
					String[] shape = {oben,unten};
					stackrecipe.shape(shape);
				}
				if(getConfig().getString("Potion."+key+".Oben") == null && getConfig().getString("Potion."+key+".Mitte") != null && getConfig().getString("Potion."+key+".Unten") != null){
					mitte = getConfig().getString("Potion."+key+".Mitte");
					unten = getConfig().getString("Potion."+key+".Unten");
					String[] shape = {mitte,unten};
					stackrecipe.shape(shape);
				}
				if(getConfig().getString("Potion."+key+".Oben") != null && getConfig().getString("Potion."+key+".Mitte") != null && getConfig().getString("Potion."+key+".Unten") != null){
					oben = getConfig().getString("Potion."+key+".Oben");
					mitte = getConfig().getString("Potion."+key+".Mitte");
					unten = getConfig().getString("Potion."+key+".Unten");
					String[] shape = {oben,mitte,unten};
					stackrecipe.shape(shape);
				}

				if(getConfig().getString("Potion."+key+".Zutat1ID") != null){
					int zu1 = getConfig().getInt("Potion."+key+".Zutat1ID");
					stackrecipe.setIngredient('A', Material.getMaterial(getConfig().getString("Potion."+key+".Zutat1")), (short)zu1);
				}
				else
					stackrecipe.setIngredient('A', Material.getMaterial(getConfig().getString("Potion."+key+".Zutat1")));

				if(getConfig().getString("Potion."+key+".Zutat2") != null){
					if(getConfig().getString("Potion."+key+".Zutat2ID") != null){
						int zu2 = getConfig().getInt("Potion."+key+".Zutat2ID");
						stackrecipe.setIngredient('B', Material.getMaterial(getConfig().getString("Potion."+key+".Zutat2")), (short)zu2);
					}
					else
						stackrecipe.setIngredient('B', Material.getMaterial(getConfig().getString("Potion."+key+".Zutat2")));
				}
				if(getConfig().getString("Potion."+key+".Zutat3") != null){
					if(getConfig().getString("Potion."+key+".Zutat3ID") != null){

						int zu3 = getConfig().getInt("Potion."+key+".Zutat3ID");
						stackrecipe.setIngredient('C', Material.getMaterial(getConfig().getString("Potion."+key+".Zutat3")), (short)zu3);
					}
					else
						stackrecipe.setIngredient('C', Material.getMaterial(getConfig().getString("Potion."+key+".Zutat3")));
				}
				if(getConfig().getString("Potion."+key+".Zutat4") != null){
					if(getConfig().getString("Potion."+key+".Zutat4ID") != null){
						int zu4 = getConfig().getInt("Potion."+key+".Zutat4ID");
						stackrecipe.setIngredient('D', Material.getMaterial(getConfig().getString("Potion."+key+".Zutat4")), (short)zu4);
					}
					else
						stackrecipe.setIngredient('D', Material.getMaterial(getConfig().getString("Potion."+key+".Zutat4")));
				}
				if(getConfig().getString("Potion."+key+".Zutat5") != null){
					if(getConfig().getString("Potion."+key+".Zutat5ID") != null){
						int zu5 = getConfig().getInt("Potion."+key+".Zutat5ID");
						stackrecipe.setIngredient('E', Material.getMaterial(getConfig().getString("Potion."+key+".Zutat5")), (short)zu5);
					}
					else
						stackrecipe.setIngredient('E', Material.getMaterial(getConfig().getString("Potion."+key+".Zutat5")));
				}
				if(getConfig().getString("Potion."+key+".Zutat6") != null){
					if(getConfig().getString("Potion."+key+".Zutat6ID") != null){
						int zu6 = getConfig().getInt("Potion."+key+".Zutat6ID");
						stackrecipe.setIngredient('F', Material.getMaterial(getConfig().getString("Potion."+key+".Zutat6")), (short)zu6);
					}
					else
						stackrecipe.setIngredient('F', Material.getMaterial(getConfig().getString("Potion."+key+".Zutat6")));
				}
				if(getConfig().getString("Potion."+key+".Zutat7") != null){
					if(getConfig().getString("Potion."+key+".Zutat7ID") != null){
						int zu7 = getConfig().getInt("Potion."+key+".Zutat7ID");
						stackrecipe.setIngredient('G', Material.getMaterial(getConfig().getString("Potion."+key+".Zutat7")), (short)zu7);
					}
					else
						stackrecipe.setIngredient('G', Material.getMaterial(getConfig().getString("Potion."+key+".Zutat7")));
				}
				if(getConfig().getString("Potion."+key+".Zutat8") != null){
					if(getConfig().getString("Potion."+key+".Zutat8ID") != null){
						int zu8 = getConfig().getInt("Potion."+key+".Zutat8ID");
						stackrecipe.setIngredient('H', Material.getMaterial(getConfig().getString("Potion."+key+".Zutat8")), (short)zu8);
					}
					else
						stackrecipe.setIngredient('H', Material.getMaterial(getConfig().getString("Potion."+key+".Zutat8")));
				}
				if(getConfig().getString("Potion."+key+".Zutat9") != null){
					if(getConfig().getString("Potion."+key+".Zutat9ID") != null){
						int zu9 = getConfig().getInt("Potion."+key+".Zutat9ID");
						stackrecipe.setIngredient('I', Material.getMaterial(getConfig().getString("Potion."+key+".Zutat9")), (short)zu9);
					}
					else
						stackrecipe.setIngredient('I', Material.getMaterial(getConfig().getString("Potion."+key+".Zutat9")));
				}

				getServer().addRecipe(stackrecipe);

			}
			if(getConfig().getString("Potion."+key+".Recipetyp").equalsIgnoreCase("shapeless")){
				ShapelessRecipe stackrecipe = new ShapelessRecipe(stack);

				if(getConfig().getString("Potion."+key+".Zutat1ID") != null){
					int zu1 = getConfig().getInt("Potion."+key+".Zutat1ID");
					stackrecipe.addIngredient(Material.getMaterial(getConfig().getString("Potion."+key+".Zutat1")), (short)zu1);
				}
				else
					stackrecipe.addIngredient(Material.getMaterial(getConfig().getString("Potion."+key+".Zutat1")));

				if(getConfig().getString("Potion."+key+".Zutat2") != null){
					if(getConfig().getString("Potion."+key+".Zutat2ID") != null){
						int zu2 = getConfig().getInt("Potion."+key+".Zutat2ID");
						stackrecipe.addIngredient(Material.getMaterial(getConfig().getString("Potion."+key+".Zutat2")), (short)zu2);
					}
					else
						stackrecipe.addIngredient(Material.getMaterial(getConfig().getString("Potion."+key+".Zutat2")));
				}
				if(getConfig().getString("Potion."+key+".Zutat3") != null){
					if(getConfig().getString("Potion."+key+".Zutat3ID") != null){
						int zu3 = getConfig().getInt("Potion."+key+".Zutat3ID");
						stackrecipe.addIngredient(Material.getMaterial(getConfig().getString("Potion."+key+".Zutat3")), (short)zu3);
					}
					else
						stackrecipe.addIngredient(Material.getMaterial(getConfig().getString("Potion."+key+".Zutat3")));
				}
				if(getConfig().getString("Potion."+key+".Zutat4") != null){
					if(getConfig().getString("Potion."+key+".Zutat4ID") != null){
						int zu4 = getConfig().getInt("Potion."+key+".Zutat4ID");
						stackrecipe.addIngredient(Material.getMaterial(getConfig().getString("Potion."+key+".Zutat4")), (short)zu4);
					}
					else
						stackrecipe.addIngredient(Material.getMaterial(getConfig().getString("Potion."+key+".Zutat4")));
				}
				if(getConfig().getString("Potion."+key+".Zutat5") != null){
					if(getConfig().getString("Potion."+key+".Zutat5ID") != null){
						int zu5 = getConfig().getInt("Potion."+key+".Zutat5ID");
						stackrecipe.addIngredient(Material.getMaterial(getConfig().getString("Potion."+key+".Zutat5")), (short)zu5);
					}
					else
						stackrecipe.addIngredient(Material.getMaterial(getConfig().getString("Potion."+key+".Zutat5")));
				}
				if(getConfig().getString("Potion."+key+".Zutat6") != null){
					if(getConfig().getString("Potion."+key+".Zutat6ID") != null){
						int zu6 = getConfig().getInt("Potion."+key+".Zutat6ID");
						stackrecipe.addIngredient(Material.getMaterial(getConfig().getString("Potion."+key+".Zutat6")), (short)zu6);
					}
					else
						stackrecipe.addIngredient(Material.getMaterial(getConfig().getString("Potion."+key+".Zutat6")));
				}
				if(getConfig().getString("Potion."+key+".Zutat7") != null){
					if(getConfig().getString("Potion."+key+".Zutat7ID") != null){
						int zu7 = getConfig().getInt("Potion."+key+".Zutat7ID");
						stackrecipe.addIngredient(Material.getMaterial(getConfig().getString("Potion."+key+".Zutat7")), (short)zu7);
					}
					else
						stackrecipe.addIngredient(Material.getMaterial(getConfig().getString("Potion."+key+".Zutat7")));
				}
				if(getConfig().getString("Potion."+key+".Zutat8") != null){
					if(getConfig().getString("Potion."+key+".Zutat8ID") != null){
						int zu8 = getConfig().getInt("Potion."+key+".Zutat8ID");
						stackrecipe.addIngredient(Material.getMaterial(getConfig().getString("Potion."+key+".Zutat8")), (short)zu8);
					}
					else
						stackrecipe.addIngredient(Material.getMaterial(getConfig().getString("Potion."+key+".Zutat8")));
				}
				if(getConfig().getString("Potion."+key+".Zutat9") != null){
					if(getConfig().getString("Potion."+key+".Zutat9ID") != null){
						int zu9 = getConfig().getInt("Potion."+key+".Zutat9ID");
						stackrecipe.addIngredient(Material.getMaterial(getConfig().getString("Potion."+key+".Zutat9")), (short)zu9);
					}
					else
						stackrecipe.addIngredient(Material.getMaterial(getConfig().getString("Potion."+key+".Zutat9")));
				}

				getServer().addRecipe(stackrecipe);

			}
		}

		//ITEMS

		for(String skey : getConfig().getConfigurationSection("Item").getKeys(false)){
			int amount = 1;
			if(getConfig().getString("Item."+skey+".OutputMenge")!=null){
				amount = getConfig().getInt("Item."+skey+".OutputMenge");
			}
			int matsub = 0;
			ItemStack stack = stackbuilder(Material.getMaterial(getConfig().getString("Item."+skey+".Material")), amount);
			if(getConfig().getString("Item."+skey+".SubID") != null){
				matsub = getConfig().getInt("Item."+skey+".SubID");

				stack = stackbuilderID(Material.getMaterial(getConfig().getString("Item."+skey+".Material")), amount, matsub);
			}

			if(getConfig().getString("Item."+skey+".Displayname")!=null){
				ItemMeta meta = stack.getItemMeta();
				if(getConfig().getString("Item."+skey+".Displayname")!=null){
					meta.setDisplayName(getConfig().getString("Item."+skey+".Displayname"));
					ArrayList<String>desc=new ArrayList<String>();
					desc.add(getConfig().getString("Item."+skey+".Lore"));
					meta.setLore(desc);
				}

				stack.setItemMeta(meta);
			}
			if(getConfig().getString("Item."+skey+".Enchantment1")!=null){

				String name = getConfig().getString("Item."+skey+".Enchantment1");
				Enchantment enchantment = getEnchantment(name);
				int level = 1;
				if(getConfig().getString("Item."+skey+".EnchantmentLevel1") != null){
					level = getConfig().getInt("Item."+skey+".EnchantmentLevel1");
				}
				stack.addUnsafeEnchantment(enchantment, level);
			}
			if(getConfig().getString("Item."+skey+".Enchantment2")!=null){

				String name = getConfig().getString("Item."+skey+".Enchantment2");
				Enchantment enchantment = getEnchantment(name);
				int level = 1;
				if(getConfig().getString("Item."+skey+".EnchantmentLevel2") != null){
					level = getConfig().getInt("Item."+skey+".EnchantmentLevel2");
				}
				stack.addUnsafeEnchantment(enchantment, level);
			}

			if(getConfig().getString("Item."+skey+".Enchantment3")!=null){

				String name = getConfig().getString("Item."+skey+".Enchantment3");
				Enchantment enchantment = getEnchantment(name);
				int level = 1;
				if(getConfig().getString("Item."+skey+".EnchantmentLevel3") != null){
					level = getConfig().getInt("Item."+skey+".EnchantmentLevel3");
				}
				stack.addUnsafeEnchantment(enchantment, level);
			}
			if(getConfig().getString("Item."+skey+".Enchantment4")!=null){

				String name = getConfig().getString("Item."+skey+".Enchantment4");
				Enchantment enchantment = getEnchantment(name);
				int level = 1;
				if(getConfig().getString("Item."+skey+".EnchantmentLevel4") != null){
					level = getConfig().getInt("Item."+skey+".EnchantmentLevel4");
				}
				stack.addUnsafeEnchantment(enchantment, level);
			}
			if(getConfig().getString("Item."+skey+".Enchantment5")!=null){

				String name = getConfig().getString("Item."+skey+".Enchantment5");
				Enchantment enchantment = getEnchantment(name);
				int level = 1;
				if(getConfig().getString("Item."+skey+".EnchantmentLevel5") != null){
					level = getConfig().getInt("Item."+skey+".EnchantmentLevel5");
				}
				stack.addUnsafeEnchantment(enchantment, level);
			}


			if(getConfig().getString("Item."+skey+".Recipetyp").equalsIgnoreCase("shaped")){
				ShapedRecipe stackrecipe = new ShapedRecipe(stack);
				String oben;
				String mitte;
				String unten;

				if(getConfig().getString("Item."+skey+".Oben") != null && getConfig().getString("Item."+skey+".Mitte") == null && getConfig().getString("Item."+skey+".Unten") == null){					
					oben = getConfig().getString("Item."+skey+".Oben");
					String[] shape = {oben};
					stackrecipe.shape(shape);
				}
				if(getConfig().getString("Item."+skey+".Oben") == null && getConfig().getString("Item."+skey+".Mitte") != null && getConfig().getString("Item."+skey+".Unten") == null){
					mitte = getConfig().getString("Item."+skey+".Mitte");
					String[] shape = {mitte};
					stackrecipe.shape(shape);
				}
				if(getConfig().getString("Item."+skey+".Oben") == null && getConfig().getString("Item."+skey+".Mitte") == null && getConfig().getString("Item."+skey+".Unten") != null){
					unten = getConfig().getString("Item."+skey+".Unten");
					String[] shape = {unten};
					stackrecipe.shape(shape);
				}
				if(getConfig().getString("Item."+skey+".Oben") != null && getConfig().getString("Item."+skey+".Mitte") != null && getConfig().getString("Item."+skey+".Unten") == null){
					oben = getConfig().getString("Item."+skey+".Oben");
					mitte = getConfig().getString("Item."+skey+".Mitte");
					String[] shape = {oben,mitte};
					stackrecipe.shape(shape);
				}
				if(getConfig().getString("Item."+skey+".Oben") != null && getConfig().getString("Item."+skey+".Mitte") == null && getConfig().getString("Item."+skey+".Unten") != null){
					oben = getConfig().getString("Item."+skey+".Oben");
					unten = getConfig().getString("Item."+skey+".Unten");
					String[] shape = {oben,unten};
					stackrecipe.shape(shape);
				}
				if(getConfig().getString("Item."+skey+".Oben") == null && getConfig().getString("Item."+skey+".Mitte") != null && getConfig().getString("Item."+skey+".Unten") != null){
					mitte = getConfig().getString("Item."+skey+".Mitte");
					unten = getConfig().getString("Item."+skey+".Unten");
					String[] shape = {mitte,unten};
					stackrecipe.shape(shape);
				}
				if(getConfig().getString("Item."+skey+".Oben") != null && getConfig().getString("Item."+skey+".Mitte") != null && getConfig().getString("Item."+skey+".Unten") != null){
					oben = getConfig().getString("Item."+skey+".Oben");
					mitte = getConfig().getString("Item."+skey+".Mitte");
					unten = getConfig().getString("Item."+skey+".Unten");
					String[] shape = {oben,mitte,unten};
					stackrecipe.shape(shape);
				}

				if(getConfig().getString("Item."+skey+".Zutat1ID") != null){
					int zu1 = getConfig().getInt("Item."+skey+".Zutat1ID");
					stackrecipe.setIngredient('A', Material.getMaterial(getConfig().getString("Item."+skey+".Zutat1")), (short)zu1);
				}
				else
					stackrecipe.setIngredient('A', Material.getMaterial(getConfig().getString("Item."+skey+".Zutat1")));

				if(getConfig().getString("Item."+skey+".Zutat2") != null){
					if(getConfig().getString("Item."+skey+".Zutat2ID") != null){
						int zu2 = getConfig().getInt("Item."+skey+".Zutat2ID");
						stackrecipe.setIngredient('B', Material.getMaterial(getConfig().getString("Item."+skey+".Zutat2")), (short)zu2);
					}
					else
						stackrecipe.setIngredient('B', Material.getMaterial(getConfig().getString("Item."+skey+".Zutat2")));
				}
				if(getConfig().getString("Item."+skey+".Zutat3") != null){
					if(getConfig().getString("Item."+skey+".Zutat3ID") != null){

						int zu3 = getConfig().getInt("Item."+skey+".Zutat3ID");
						stackrecipe.setIngredient('C', Material.getMaterial(getConfig().getString("Item."+skey+".Zutat3")), (short)zu3);
					}
					else
						stackrecipe.setIngredient('C', Material.getMaterial(getConfig().getString("Item."+skey+".Zutat3")));
				}
				if(getConfig().getString("Item."+skey+".Zutat4") != null){
					if(getConfig().getString("Item."+skey+".Zutat4ID") != null){
						int zu4 = getConfig().getInt("Item."+skey+".Zutat4ID");
						stackrecipe.setIngredient('D', Material.getMaterial(getConfig().getString("Item."+skey+".Zutat4")), (short)zu4);
					}
					else
						stackrecipe.setIngredient('D', Material.getMaterial(getConfig().getString("Item."+skey+".Zutat4")));
				}
				if(getConfig().getString("Item."+skey+".Zutat5") != null){
					if(getConfig().getString("Item."+skey+".Zutat5ID") != null){
						int zu5 = getConfig().getInt("Item."+skey+".Zutat5ID");
						stackrecipe.setIngredient('E', Material.getMaterial(getConfig().getString("Item."+skey+".Zutat5")), (short)zu5);
					}
					else
						stackrecipe.setIngredient('E', Material.getMaterial(getConfig().getString("Item."+skey+".Zutat5")));
				}
				if(getConfig().getString("Item."+skey+".Zutat6") != null){
					if(getConfig().getString("Item."+skey+".Zutat6ID") != null){
						int zu6 = getConfig().getInt("Item."+skey+".Zutat6ID");
						stackrecipe.setIngredient('F', Material.getMaterial(getConfig().getString("Item."+skey+".Zutat6")), (short)zu6);
					}
					else
						stackrecipe.setIngredient('F', Material.getMaterial(getConfig().getString("Item."+skey+".Zutat6")));
				}
				if(getConfig().getString("Item."+skey+".Zutat7") != null){
					if(getConfig().getString("Item."+skey+".Zutat7ID") != null){
						int zu7 = getConfig().getInt("Item."+skey+".Zutat7ID");
						stackrecipe.setIngredient('G', Material.getMaterial(getConfig().getString("Item."+skey+".Zutat7")), (short)zu7);
					}
					else
						stackrecipe.setIngredient('G', Material.getMaterial(getConfig().getString("Item."+skey+".Zutat7")));
				}
				if(getConfig().getString("Item."+skey+".Zutat8") != null){
					if(getConfig().getString("Item."+skey+".Zutat8ID") != null){
						int zu8 = getConfig().getInt("Item."+skey+".Zutat8ID");
						stackrecipe.setIngredient('H', Material.getMaterial(getConfig().getString("Item."+skey+".Zutat8")), (short)zu8);
					}
					else
						stackrecipe.setIngredient('H', Material.getMaterial(getConfig().getString("Item."+skey+".Zutat8")));
				}
				if(getConfig().getString("Item."+skey+".Zutat9") != null){
					if(getConfig().getString("Item."+skey+".Zutat9ID") != null){
						int zu9 = getConfig().getInt("Item."+skey+".Zutat9ID");
						stackrecipe.setIngredient('I', Material.getMaterial(getConfig().getString("Item."+skey+".Zutat9")), (short)zu9);
					}
					else
						stackrecipe.setIngredient('I', Material.getMaterial(getConfig().getString("Item."+skey+".Zutat9")));
				}

				getServer().addRecipe(stackrecipe);

			}
			if(getConfig().getString("Item."+skey+".Recipetyp").equalsIgnoreCase("shapeless")){
				ShapelessRecipe stackrecipe = new ShapelessRecipe(stack);

				if(getConfig().getString("Item."+skey+".Zutat1ID") != null){
					int zu1 = getConfig().getInt("Item."+skey+".Zutat1ID");
					stackrecipe.addIngredient(Material.getMaterial(getConfig().getString("Item."+skey+".Zutat1")), (short)zu1);
				}
				else
					stackrecipe.addIngredient(Material.getMaterial(getConfig().getString("Item."+skey+".Zutat1")));

				if(getConfig().getString("Item."+skey+".Zutat2") != null){
					if(getConfig().getString("Item."+skey+".Zutat2ID") != null){
						int zu2 = getConfig().getInt("Item."+skey+".Zutat2ID");
						stackrecipe.addIngredient(Material.getMaterial(getConfig().getString("Item."+skey+".Zutat2")), (short)zu2);
					}
					else
						stackrecipe.addIngredient(Material.getMaterial(getConfig().getString("Item."+skey+".Zutat2")));
				}
				if(getConfig().getString("Item."+skey+".Zutat3") != null){
					if(getConfig().getString("Item."+skey+".Zutat3ID") != null){
						int zu3 = getConfig().getInt("Item."+skey+".Zutat3ID");
						stackrecipe.addIngredient(Material.getMaterial(getConfig().getString("Item."+skey+".Zutat3")), (short)zu3);
					}
					else
						stackrecipe.addIngredient(Material.getMaterial(getConfig().getString("Item."+skey+".Zutat3")));
				}
				if(getConfig().getString("Item."+skey+".Zutat4") != null){
					if(getConfig().getString("Item."+skey+".Zutat4ID") != null){
						int zu4 = getConfig().getInt("Item."+skey+".Zutat4ID");
						stackrecipe.addIngredient(Material.getMaterial(getConfig().getString("Item."+skey+".Zutat4")), (short)zu4);
					}
					else
						stackrecipe.addIngredient(Material.getMaterial(getConfig().getString("Item."+skey+".Zutat4")));
				}
				if(getConfig().getString("Item."+skey+".Zutat5") != null){
					if(getConfig().getString("Item."+skey+".Zutat5ID") != null){
						int zu5 = getConfig().getInt("Item."+skey+".Zutat5ID");
						stackrecipe.addIngredient(Material.getMaterial(getConfig().getString("Item."+skey+".Zutat5")), (short)zu5);
					}
					else
						stackrecipe.addIngredient(Material.getMaterial(getConfig().getString("Item."+skey+".Zutat5")));
				}
				if(getConfig().getString("Item."+skey+".Zutat6") != null){
					if(getConfig().getString("Item."+skey+".Zutat6ID") != null){
						int zu6 = getConfig().getInt("Item."+skey+".Zutat6ID");
						stackrecipe.addIngredient(Material.getMaterial(getConfig().getString("Item."+skey+".Zutat6")), (short)zu6);
					}
					else
						stackrecipe.addIngredient(Material.getMaterial(getConfig().getString("Item."+skey+".Zutat6")));
				}
				if(getConfig().getString("Item."+skey+".Zutat7") != null){
					if(getConfig().getString("Item."+skey+".Zutat7ID") != null){
						int zu7 = getConfig().getInt("Item."+skey+".Zutat7ID");
						stackrecipe.addIngredient(Material.getMaterial(getConfig().getString("Item."+skey+".Zutat7")), (short)zu7);
					}
					else
						stackrecipe.addIngredient(Material.getMaterial(getConfig().getString("Item."+skey+".Zutat7")));
				}
				if(getConfig().getString("Item."+skey+".Zutat8") != null){
					if(getConfig().getString("Item."+skey+".Zutat8ID") != null){
						int zu8 = getConfig().getInt("Item."+skey+".Zutat8ID");
						stackrecipe.addIngredient(Material.getMaterial(getConfig().getString("Item."+skey+".Zutat8")), (short)zu8);
					}
					else
						stackrecipe.addIngredient(Material.getMaterial(getConfig().getString("Item."+skey+".Zutat8")));
				}
				if(getConfig().getString("Item."+skey+".Zutat9") != null){
					if(getConfig().getString("Item."+skey+".Zutat9ID") != null){
						int zu9 = getConfig().getInt("Item."+skey+".Zutat9ID");
						stackrecipe.addIngredient(Material.getMaterial(getConfig().getString("Item."+skey+".Zutat9")), (short)zu9);
					}
					else
						stackrecipe.addIngredient(Material.getMaterial(getConfig().getString("Item."+skey+".Zutat9")));
				}

				getServer().addRecipe(stackrecipe);

			}
		}

		//Ebooks:
		if(getConfig().getConfigurationSection("Ebook") == null){
			return;}
		for(String ekey : getConfig().getConfigurationSection("Ebook").getKeys(false)){
			ItemStack stack = stackbuilder(Material.ENCHANTED_BOOK, 1);
			if(getConfig().getString("Ebook."+ekey+".SubID") != null){
				int ematsub = getConfig().getInt("Ebook."+ekey+".SubID");

				stack = stackbuilderID(Material.ENCHANTED_BOOK, 1, ematsub);
			}

			if(getConfig().getString("Ebook."+ekey+".Displayname")!=null){
				ItemMeta meta = stack.getItemMeta();
				if(getConfig().getString("Ebook."+ekey+".Displayname")!=null){
					meta.setDisplayName(getConfig().getString("Ebook."+ekey+".Displayname"));
					ArrayList<String>desc=new ArrayList<String>();
					desc.add(getConfig().getString("Ebook."+ekey+".Lore"));
					meta.setLore(desc);
				}

				stack.setItemMeta(meta);
			}

			EnchantmentStorageMeta emeta = (EnchantmentStorageMeta) stack.getItemMeta();
			String name = getConfig().getString("Ebook."+ekey+".Enchantment");

			Enchantment enchantment = getEnchantment(name);

			int level = getConfig().getInt("Ebook."+ekey+".EnchantmentLevel", 1);
			if(level > 5){
				level = 5;
			}
			emeta.addStoredEnchant(enchantment, level, true);
			stack.setItemMeta(emeta);

			if(getConfig().getString("Ebook."+ekey+".Recipetyp").equalsIgnoreCase("shaped")){
				ShapedRecipe stackrecipe = new ShapedRecipe(stack);
				String oben;
				String mitte;
				String unten;

				if(getConfig().getString("Ebook."+ekey+".Oben") != null && getConfig().getString("Ebook."+ekey+".Mitte") == null && getConfig().getString("Ebook."+ekey+".Unten") == null){					
					oben = getConfig().getString("Ebook."+ekey+".Oben");
					String[] shape = {oben};
					stackrecipe.shape(shape);
				}
				if(getConfig().getString("Ebook."+ekey+".Oben") == null && getConfig().getString("Ebook."+ekey+".Mitte") != null && getConfig().getString("Ebook."+ekey+".Unten") == null){
					mitte = getConfig().getString("Ebook."+ekey+".Mitte");
					String[] shape = {mitte};
					stackrecipe.shape(shape);
				}
				if(getConfig().getString("Ebook."+ekey+".Oben") == null && getConfig().getString("Ebook."+ekey+".Mitte") == null && getConfig().getString("Ebook."+ekey+".Unten") != null){
					unten = getConfig().getString("Ebook."+ekey+".Unten");
					String[] shape = {unten};
					stackrecipe.shape(shape);
				}
				if(getConfig().getString("Ebook."+ekey+".Oben") != null && getConfig().getString("Ebook."+ekey+".Mitte") != null && getConfig().getString("Ebook."+ekey+".Unten") == null){
					oben = getConfig().getString("Ebook."+ekey+".Oben");
					mitte = getConfig().getString("Ebook."+ekey+".Mitte");
					String[] shape = {oben,mitte};
					stackrecipe.shape(shape);
				}
				if(getConfig().getString("Ebook."+ekey+".Oben") != null && getConfig().getString("Ebook."+ekey+".Mitte") == null && getConfig().getString("Ebook."+ekey+".Unten") != null){
					oben = getConfig().getString("Ebook."+ekey+".Oben");
					unten = getConfig().getString("Ebook."+ekey+".Unten");
					String[] shape = {oben,unten};
					stackrecipe.shape(shape);
				}
				if(getConfig().getString("Ebook."+ekey+".Oben") == null && getConfig().getString("Ebook."+ekey+".Mitte") != null && getConfig().getString("Ebook."+ekey+".Unten") != null){
					mitte = getConfig().getString("Ebook."+ekey+".Mitte");
					unten = getConfig().getString("Ebook."+ekey+".Unten");
					String[] shape = {mitte,unten};
					stackrecipe.shape(shape);
				}
				if(getConfig().getString("Ebook."+ekey+".Oben") != null && getConfig().getString("Ebook."+ekey+".Mitte") != null && getConfig().getString("Ebook."+ekey+".Unten") != null){
					oben = getConfig().getString("Ebook."+ekey+".Oben");
					mitte = getConfig().getString("Ebook."+ekey+".Mitte");
					unten = getConfig().getString("Ebook."+ekey+".Unten");
					String[] shape = {oben,mitte,unten};
					stackrecipe.shape(shape);
				}

				if(getConfig().getString("Ebook."+ekey+".Zutat1ID") != null){
					int zu1 = getConfig().getInt("Ebook."+ekey+".Zutat1ID");
					stackrecipe.setIngredient('A', Material.getMaterial(getConfig().getString("Ebook."+ekey+".Zutat1")), (short)zu1);
				}
				else
					stackrecipe.setIngredient('A', Material.getMaterial(getConfig().getString("Ebook."+ekey+".Zutat1")));

				if(getConfig().getString("Ebook."+ekey+".Zutat2") != null){
					if(getConfig().getString("Ebook."+ekey+".Zutat2ID") != null){
						int zu2 = getConfig().getInt("Ebook."+ekey+".Zutat2ID");
						stackrecipe.setIngredient('B', Material.getMaterial(getConfig().getString("Ebook."+ekey+".Zutat2")), (short)zu2);
					}
					else
						stackrecipe.setIngredient('B', Material.getMaterial(getConfig().getString("Ebook."+ekey+".Zutat2")));
				}
				if(getConfig().getString("Ebook."+ekey+".Zutat3") != null){
					if(getConfig().getString("Ebook."+ekey+".Zutat3ID") != null){

						int zu3 = getConfig().getInt("Ebook."+ekey+".Zutat3ID");
						stackrecipe.setIngredient('C', Material.getMaterial(getConfig().getString("Ebook."+ekey+".Zutat3")), (short)zu3);
					}
					else
						stackrecipe.setIngredient('C', Material.getMaterial(getConfig().getString("Ebook."+ekey+".Zutat3")));
				}
				if(getConfig().getString("Ebook."+ekey+".Zutat4") != null){
					if(getConfig().getString("Ebook."+ekey+".Zutat4ID") != null){
						int zu4 = getConfig().getInt("Ebook."+ekey+".Zutat4ID");
						stackrecipe.setIngredient('D', Material.getMaterial(getConfig().getString("Ebook."+ekey+".Zutat4")), (short)zu4);
					}
					else
						stackrecipe.setIngredient('D', Material.getMaterial(getConfig().getString("Ebook."+ekey+".Zutat4")));
				}
				if(getConfig().getString("Ebook."+ekey+".Zutat5") != null){
					if(getConfig().getString("Ebook."+ekey+".Zutat5ID") != null){
						int zu5 = getConfig().getInt("Ebook."+ekey+".Zutat5ID");
						stackrecipe.setIngredient('E', Material.getMaterial(getConfig().getString("Ebook."+ekey+".Zutat5")), (short)zu5);
					}
					else
						stackrecipe.setIngredient('E', Material.getMaterial(getConfig().getString("Ebook."+ekey+".Zutat5")));
				}
				if(getConfig().getString("Ebook."+ekey+".Zutat6") != null){
					if(getConfig().getString("Ebook."+ekey+".Zutat6ID") != null){
						int zu6 = getConfig().getInt("Ebook."+ekey+".Zutat6ID");
						stackrecipe.setIngredient('F', Material.getMaterial(getConfig().getString("Ebook."+ekey+".Zutat6")), (short)zu6);
					}
					else
						stackrecipe.setIngredient('F', Material.getMaterial(getConfig().getString("Ebook."+ekey+".Zutat6")));
				}
				if(getConfig().getString("Ebook."+ekey+".Zutat7") != null){
					if(getConfig().getString("Ebook."+ekey+".Zutat7ID") != null){
						int zu7 = getConfig().getInt("Ebook."+ekey+".Zutat7ID");
						stackrecipe.setIngredient('G', Material.getMaterial(getConfig().getString("Ebook."+ekey+".Zutat7")), (short)zu7);
					}
					else
						stackrecipe.setIngredient('G', Material.getMaterial(getConfig().getString("Ebook."+ekey+".Zutat7")));
				}
				if(getConfig().getString("Ebook."+ekey+".Zutat8") != null){
					if(getConfig().getString("Ebook."+ekey+".Zutat8ID") != null){
						int zu8 = getConfig().getInt("Ebook."+ekey+".Zutat8ID");
						stackrecipe.setIngredient('H', Material.getMaterial(getConfig().getString("Ebook."+ekey+".Zutat8")), (short)zu8);
					}
					else
						stackrecipe.setIngredient('H', Material.getMaterial(getConfig().getString("Ebook."+ekey+".Zutat8")));
				}
				if(getConfig().getString("Ebook."+ekey+".Zutat9") != null){
					if(getConfig().getString("Ebook."+ekey+".Zutat9ID") != null){
						int zu9 = getConfig().getInt("Ebook."+ekey+".Zutat9ID");
						stackrecipe.setIngredient('I', Material.getMaterial(getConfig().getString("Ebook."+ekey+".Zutat9")), (short)zu9);
					}
					else
						stackrecipe.setIngredient('I', Material.getMaterial(getConfig().getString("Ebook."+ekey+".Zutat9")));
				}

				getServer().addRecipe(stackrecipe);

			}
			if(getConfig().getString("Ebook."+ekey+".Recipetyp").equalsIgnoreCase("shapeless")){
				ShapelessRecipe stackrecipe = new ShapelessRecipe(stack);

				if(getConfig().getString("Ebook."+ekey+".Zutat1ID") != null){
					int zu1 = getConfig().getInt("Ebook."+ekey+".Zutat1ID");
					stackrecipe.addIngredient(Material.getMaterial(getConfig().getString("Ebook."+ekey+".Zutat1")), (short)zu1);
				}
				else
					stackrecipe.addIngredient(Material.getMaterial(getConfig().getString("Ebook."+ekey+".Zutat1")));

				if(getConfig().getString("Ebook."+ekey+".Zutat2") != null){
					if(getConfig().getString("Ebook."+ekey+".Zutat2ID") != null){
						int zu2 = getConfig().getInt("Ebook."+ekey+".Zutat2ID");
						stackrecipe.addIngredient(Material.getMaterial(getConfig().getString("Ebook."+ekey+".Zutat2")), (short)zu2);
					}
					else
						stackrecipe.addIngredient(Material.getMaterial(getConfig().getString("Ebook."+ekey+".Zutat2")));
				}
				if(getConfig().getString("Ebook."+ekey+".Zutat3") != null){
					if(getConfig().getString("Ebook."+ekey+".Zutat3ID") != null){
						int zu3 = getConfig().getInt("Ebook."+ekey+".Zutat3ID");
						stackrecipe.addIngredient(Material.getMaterial(getConfig().getString("Ebook."+ekey+".Zutat3")), (short)zu3);
					}
					else
						stackrecipe.addIngredient(Material.getMaterial(getConfig().getString("Ebook."+ekey+".Zutat3")));
				}
				if(getConfig().getString("Ebook."+ekey+".Zutat4") != null){
					if(getConfig().getString("Ebook."+ekey+".Zutat4ID") != null){
						int zu4 = getConfig().getInt("Ebook."+ekey+".Zutat4ID");
						stackrecipe.addIngredient(Material.getMaterial(getConfig().getString("Ebook."+ekey+".Zutat4")), (short)zu4);
					}
					else
						stackrecipe.addIngredient(Material.getMaterial(getConfig().getString("Ebook."+ekey+".Zutat4")));
				}
				if(getConfig().getString("Ebook."+ekey+".Zutat5") != null){
					if(getConfig().getString("Ebook."+ekey+".Zutat5ID") != null){
						int zu5 = getConfig().getInt("Ebook."+ekey+".Zutat5ID");
						stackrecipe.addIngredient(Material.getMaterial(getConfig().getString("Ebook."+ekey+".Zutat5")), (short)zu5);
					}
					else
						stackrecipe.addIngredient(Material.getMaterial(getConfig().getString("Ebook."+ekey+".Zutat5")));
				}
				if(getConfig().getString("Ebook."+ekey+".Zutat6") != null){
					if(getConfig().getString("Ebook."+ekey+".Zutat6ID") != null){
						int zu6 = getConfig().getInt("Ebook."+ekey+".Zutat6ID");
						stackrecipe.addIngredient(Material.getMaterial(getConfig().getString("Ebook."+ekey+".Zutat6")), (short)zu6);
					}
					else
						stackrecipe.addIngredient(Material.getMaterial(getConfig().getString("Ebook."+ekey+".Zutat6")));
				}
				if(getConfig().getString("Ebook."+ekey+".Zutat7") != null){
					if(getConfig().getString("Ebook."+ekey+".Zutat7ID") != null){
						int zu7 = getConfig().getInt("Ebook."+ekey+".Zutat7ID");
						stackrecipe.addIngredient(Material.getMaterial(getConfig().getString("Ebook."+ekey+".Zutat7")), (short)zu7);
					}
					else
						stackrecipe.addIngredient(Material.getMaterial(getConfig().getString("Ebook."+ekey+".Zutat7")));
				}
				if(getConfig().getString("Ebook."+ekey+".Zutat8") != null){
					if(getConfig().getString("Ebook."+ekey+".Zutat8ID") != null){
						int zu8 = getConfig().getInt("Ebook."+ekey+".Zutat8ID");
						stackrecipe.addIngredient(Material.getMaterial(getConfig().getString("Ebook."+ekey+".Zutat8")), (short)zu8);
					}
					else
						stackrecipe.addIngredient(Material.getMaterial(getConfig().getString("Ebook."+ekey+".Zutat8")));
				}
				if(getConfig().getString("Ebook."+ekey+".Zutat9") != null){
					if(getConfig().getString("Ebook."+ekey+".Zutat9ID") != null){
						int zu9 = getConfig().getInt("Ebook."+ekey+".Zutat9ID");
						stackrecipe.addIngredient(Material.getMaterial(getConfig().getString("Ebook."+ekey+".Zutat9")), (short)zu9);
					}
					else
						stackrecipe.addIngredient(Material.getMaterial(getConfig().getString("Ebook."+ekey+".Zutat9")));
				}

				getServer().addRecipe(stackrecipe);

			}
		}








	}

	private Boolean loadConfig()
	{
		reloadConfig();
		getConfig();
		/*if(config.get("HowTo.Step1") == null){
			config.set("HowTo.Step1", "Bei Shaped Rezepten shaped angeben, bei Shapeless shapeless");
			config.set("HowTo.Step2", "Bei Shaped Rezepten, die Buchstaben fuer OBEN, UNTEN, MITTE entsprechen der Anordnung der Items im Grid");
			config.set("HowTo.Step3", "Bitte Zutaten immer von A-I bennen und bei A Anfangen der Reihe nach, sonst gehts nicht");
			config.set("HowTo.Step4", "Leere Felder bei Shaped Recipes einfach weglassen");
			config.set("HowTo.Step5", "Bei Shapeless Rezepten kann das OBEN,MITTE,UNTEN weggelassen werden");
			config.set("HowTo.Step6", "Die SubID ist die, die der Trank bekommen soll. Gehen auch Phantasiezahlen so lange sie nicht belegt sind");
			config.set("HowTo.Step7", "Zutat1 = A, Zutat2 = B etc.. Wenn SubIDs in den Zutaten sind, diese mit Zutat1ID etc.. angeben");
			config.set("HowTo.Step8", "WICHTIG arbeitet nicht mit air, sondern mit Freiräumen und Apostrophen, siehe Kakao");
			config.set("HowTo.Step9", "Bei Splash Potions ein Splash: true reinschreiben");
			config.set("HowTo.Step10", "Bei Items gehts genauso. nur das ihr mit Material noch das Material angeben muesst, das hinterher rauskommt");
		}
		if(config.getString("Potion.Alkohol.Displayname") == null){
			config.set("Potion.Alkohol.Displayname", "reiner Alkohol");
			config.set("Potion.Alkohol.OutputMenge", 2);
			config.set("Potion.Alkohol.SubID", 7);
			config.set("Potion.Alkohol.Lore", "feinster, reiner Alkohol...");
			config.set("Potion.Alkohol.Effektmenge", 2);
			config.set("Potion.Alkohol.Effekt1", "CONFUSION");
			config.set("Potion.Alkohol.Dauer1", 6000);
			config.set("Potion.Alkohol.Level1", 5);
			config.set("Potion.Alkohol.Effekt2", "SLOW");
			config.set("Potion.Alkohol.Dauer2", 6000);
			config.set("Potion.Alkohol.Level2", 10);
			config.set("Potion.Alkohol.Recipetyp", "shaped");
			config.set("Potion.Alkohol.Oben", "AAA");
			config.set("Potion.Alkohol.Mitte", "ABA");
			config.set("Potion.Alkohol.Unten", "ABA");
			config.set("Potion.Alkohol.Zutat1", "SUGAR_CANE");
			config.set("Potion.Alkohol.Zutat2", "GLASS_BOTTLE");
		}
		if(config.getString("Potion.Bier.Displayname") == null){
			config.set("Potion.Bier.Displayname", "Darkbraeu Bier");
			config.set("Potion.Bier.OutputMenge", 1);
			config.set("Potion.Bier.SubID", 11);
			config.set("Potion.Bier.Lore", "Das beste Bier-Gebraeu in ganz Calad Amar");
			config.set("Potion.Bier.Effektmenge", 1);
			config.set("Potion.Bier.Effekt1", "CONFUSION");
			config.set("Potion.Bier.Dauer1", 600);
			config.set("Potion.Bier.Level1", 1);
			config.set("Potion.Bier.Recipetyp", "shaped");
			config.set("Potion.Bier.Oben", "AAA");
			config.set("Potion.Bier.Mitte", "BCB");
			config.set("Potion.Bier.Zutat1", "SUGAR");
			config.set("Potion.Bier.Zutat2", "WHEAT");
			config.set("Potion.Bier.Zutat3", "POTION");
			config.set("Potion.Bier.Zutat3ID", 7);


		}
		if(config.getString("Item.SalvageDiamondSword.Material") == null){
			config.set("Item.SalvageDiamondSword.Material", "DIAMOND");
			config.set("Item.SalvageDiamondSword.OutputMenge", 1);
			config.set("Item.SalvageDiamondSword.Recipetyp", "shapeless");
			config.set("Item.SalvageDiamondSword.Zutat1", "DIAMOND_SWORD");

		}

		saveConfig();*/
		
		reloadAlternatives();
		getAlternatives();
		
		alternatives.set("Version", 3.00);
		
		saveAlternatives();

		return true;
	}

	public ItemStack stackbuilder (Material material, int amount){
		ItemStack stack = new ItemStack(material, amount);
		return stack;
	}
	public Enchantment getEnchantment(String name){
		Enchantment enchantment = null;
		if(name.equalsIgnoreCase("protection"))
			enchantment=PROTECTION;
		if(name.equalsIgnoreCase("fire_protection"))
			enchantment=FIRE_PROTECTION;
		if(name.equalsIgnoreCase("feather_falling"))
			enchantment=FEATHER_FALLING;
		if(name.equalsIgnoreCase("blast_protection"))
			enchantment=BLAST_PROTECTION;
		if(name.equalsIgnoreCase("projectile_protection"))
			enchantment=PROJECTILE_PROTECTION;
		if(name.equalsIgnoreCase("respiration"))
			enchantment=RESPIRATION;
		if(name.equalsIgnoreCase("aqua_affinity"))
			enchantment=AQUA_AFFINITY;
		if(name.equalsIgnoreCase("SHARPNESS"))
			enchantment=SHARPNESS;
		if(name.equalsIgnoreCase("smite"))
			enchantment=SMITE;
		if(name.equalsIgnoreCase("bane"))
			enchantment=BANE;
		if(name.equalsIgnoreCase("knockback"))
			enchantment=KNOCKBACK;
		if(name.equalsIgnoreCase("fire_aspect"))
			enchantment=FIRE_ASPECT;
		if(name.equalsIgnoreCase("looting"))
			enchantment=LOOTING;
		if(name.equalsIgnoreCase("efficiency"))
			enchantment=EFFICIENCY;
		if(name.equalsIgnoreCase("SILK_TOUCH"))
			enchantment=SILK_TOUCH;
		if(name.equalsIgnoreCase("unbreaking"))
			enchantment=UNBREAKING;
		if(name.equalsIgnoreCase("fortune"))
			enchantment=FORTUNE;
		if(name.equalsIgnoreCase("power"))
			enchantment=POWER;
		if(name.equalsIgnoreCase("punch"))
			enchantment=PUNCH;
		if(name.equalsIgnoreCase("flame"))
			enchantment=FLAME;
		if(name.equalsIgnoreCase("infinite"))
			enchantment=INFINITE;
		if(name.equalsIgnoreCase("thorns"))
			enchantment=THORNS;

		return enchantment;
	}

	public ItemStack stackbuilderID (Material material, int amount, int ID){
		ItemStack stack = new ItemStack(material, amount, (short) ID);
		return stack;
	}

	public void onEnable() {	

		loadConfig();
		buildRecipes();
		
		getServer().getPluginManager().registerEvents(new customRecipesPrepareCraftEvent(this), this);
		LogInfo("initialized: PrepareItemCraftEvent");

	}

	public void onDisable() {

	}


	@SuppressWarnings("deprecation")
	public void createPotion (String key, Player player){

		int matsub = getConfig().getInt("Potion."+key+".SubID");
		int amount = getConfig().getInt("Potion."+key+".OutputMenge");

		ItemStack stack = new ItemStack(Material.POTION, amount, (short)matsub);
		Boolean bol = getConfig().getBoolean("Potion."+key+".Splash");
		if( bol == true){
			stack = new Potion (PotionType.WATER, amount, bol).toItemStack(amount);
		}
		ItemMeta meta = stack.getItemMeta();
		meta.setDisplayName(getConfig().getString("Potion."+key+".Displayname"));
		if(getConfig().getString("Potion."+key+".Lore")!=null){
			ArrayList<String>desc=new ArrayList<String>();
			desc.add(getConfig().getString("Potion."+key+".Lore"));
			meta.setLore(desc);
		}
		stack.setItemMeta(meta);
		stack.setDurability((short)matsub);
		PotionMeta pmeta = (PotionMeta) stack.getItemMeta();
		pmeta.clearCustomEffects();
		int count = getConfig().getInt("Potion."+key+".Effektmenge");
		for(int i=1; count >= i; i++ ) {

			pmeta.addCustomEffect(new PotionEffect(PotionEffectType.getByName(getConfig().getString("Potion."+key+".Effekt"+i)),getConfig().getInt("Potion."+key+".Dauer"+count),getConfig().getInt("Potion."+key+".Level"+count)), true);	
		}
		stack.setItemMeta(pmeta);
		player.getInventory().addItem(stack);
	}
	private void showPluginInfo(CommandSender sender){
		sender.sendMessage(ChatColor.GREEN + logprefix);
	}

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if(sender instanceof Player) {
			
			//Player player = (Player)sender;
			//if(cmd.getName().equalsIgnoreCase("recipe")){

				if(args.length==0){
					ItemStack stack = new ItemStack(Material.WHEAT, 3);
					ItemMeta itemMeta = stack.getItemMeta();
					itemMeta.setDisplayName("Weizenmehl");
					ArrayList<String>desc=new ArrayList<String>();
					desc.add("Ein einfaches aber gutes Mehl");
					itemMeta.setLore(desc);
					stack.setItemMeta(itemMeta);
					((Player) sender).getInventory().addItem(stack);
					stack = new ItemStack(Material.WHEAT, 3);
					itemMeta = stack.getItemMeta();
					itemMeta.setDisplayName("Roggenmehl");
					desc = new ArrayList<String>();
					desc.add("Ein vollwertiges Mehl");
					itemMeta.setLore(desc);
					stack.setItemMeta(itemMeta);
					((Player) sender).getInventory().addItem(stack);
					showPluginInfo(sender);
					return true;
				} 

				/*
				if(args.length==1){					
					if(args[0].equalsIgnoreCase("reload")){		
						if(permission.has(player, "CustomRecipes.Admin")){
							reloadConfig();
							buildRecipes();
							player.sendMessage("Config neu geladen und Rezepte erstellt");
							return true;
						} else {
							player.sendMessage("Dir fehlen die nötigen Berechtigungen für diesen Befehl.");
							return true;
						}
					}else {
						return true;
					}
				}
				if(args.length==2){
					if(args[0].equalsIgnoreCase("give")){
						if(permission.has(player, "CustomRecipes.Give")){
							createPotion(args[1], player);
							return true;
						} else {
							player.sendMessage("Dir fehlen die nötigen Berechtigungen für diesen Befehl.");
							return true;
						}
					}
				}*/
			}

		return true;
	}
}