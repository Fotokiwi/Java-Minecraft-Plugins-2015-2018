package org.community.fourWays.Trader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Set;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.block.Sign;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.community.fourWays.fourWays;
import org.community.fourWays.User.User;

public class Trader {

	private fourWays plugin;
	
	private FileConfiguration trader = null;
	private File traderFile = null;

	/**
	 * Server-Startup Initialisierung.
	 * Diese Funktion registriert die Händler-Klasse
	 * @param plugin Die Hauptvariable des Plugins
	 */
	public Trader(fourWays plugin) {
		this.plugin = plugin;
		
		loadFromFile();
		
		saveToFile();
	}
	
	public boolean isPlayerTrader(Player player) {
		
		User user = plugin.fWUsers.getPlayerInfo(player);
		
		String jobs = user.getJobHash();
		int level = user.getLevel();
		
		// TODO Unbedingt die Job-Bezeichnung der T3-Klasse anpassen!
		if(jobs.contains("Trader") && level >= 30) {
			return true;
		} else {
			return false;
		}
		
	}
	
	public int getTradechestCount(Player player) {
		
		ConfigurationSection chestSection = trader.getConfigurationSection("TraderChest." + player.getName());
		
		if(chestSection == null)
			return 0;
		
		Set<String> chestKeys = chestSection.getKeys(false);
  	  	String[] chestArray = chestKeys.toArray(new String[0]);  	  	
  	  	
		int chestCount = 0;
		
		for(int i = 0; i < chestArray.length; i++) {
			chestCount++;				
		}
		
		return chestCount;
		
	}
	
	public String listPlayerTradeChests(Player player) {
		
		String list = "Lieferungs-IDs: ";
		
		ConfigurationSection chestSection = trader.getConfigurationSection("TraderChest." + player.getName());
		
		if(chestSection == null)
			return "Lieferungs-IDs: [keine Lieferungen vorhanden]";
		
		Set<String> chestKeys = chestSection.getKeys(false);
  	  	String[] chestArray = chestKeys.toArray(new String[0]);
		
		for(int i = 0; i < chestArray.length; i++) {
			list = list + chestArray[i] + ", ";			
		}
		
		return list;
		
	}
	
	public void showTradeChestInfo(Player player, String chest) {
		
		String sender = trader.getString("TraderChest." + player.getName() + "." + chest + ".Absender");
		String receiver = trader.getString("TraderChest." + player.getName() + "." + chest + ".Empfaenger");
		long getDate = trader.getLong("TraderChest." + player.getName() + "." + chest + ".Verpackungsdatum");
		long lastDate = trader.getLong("TraderChest." + player.getName() + "." + chest + ".Verfallsdatum");
		Calendar cGetDate = Calendar.getInstance();
		cGetDate.setTimeInMillis(getDate);
		Calendar cLastDate = Calendar.getInstance();
		cLastDate.setTimeInMillis(lastDate);
		
		if(trader.getString("TraderChest." + player.getName() + "." + chest + ".Absender") == null) {
			player.sendMessage("Zu dieser Liefernummer sind keine Informationen vorhanden.");
			return;
		}
		
		player.sendMessage("Absender: " + sender);
		player.sendMessage("Empf�nger: " + receiver);
		//player.sendMessage("Verpackungsdatum: " + cGetDate.get(Calendar.DAY_OF_MONTH) + "." + cGetDate.get(Calendar.MONTH) + "." + cGetDate.get(Calendar.YEAR) + " " + cGetDate.get(Calendar.HOUR_OF_DAY) + ":" + String.format("%02d", cGetDate.get(Calendar.MINUTE)) + " Uhr");
		//player.sendMessage("Verfallsdatum: " + cLastDate.get(Calendar.DAY_OF_MONTH) + "." + cLastDate.get(Calendar.MONTH) + "." + cLastDate.get(Calendar.YEAR) + " " + cLastDate.get(Calendar.HOUR_OF_DAY) + ":" + String.format("%02d", cLastDate.get(Calendar.MINUTE)) + " Uhr");
		
	}
	
	public void packTradeChest(Player player) {
		
		if(isTargetSign(player) == false) {
			player.sendMessage("Du musst ein Lieferschild anschauen. ([Lieferung] in der ersten Zeile)");
			return;
		} else {
			Sign sign = getTargetSign(player);
			Location location = sign.getLocation();
			location.subtract(0, 1, 0);
			
			if(!sign.getLine(0).equalsIgnoreCase("[Lieferung]") || location.getBlock().getType() != Material.CHEST) {
				player.sendMessage("Das ist kein gültiges Lieferschild.");
				return;
			} else {
				
				Chest chest = (Chest) location.getBlock().getState();
				ItemStack[] inventory = chest.getBlockInventory().getContents();
				User user = plugin.fWUsers.getPlayerInfo(player);
			
				if(isDoubleChest(chest) && user.getLevel() <= 36) {
					player.sendMessage("Du kannst erst ab Stufe 36 Doppeltruhen verpacken.");
					return;
				}
				
				String randomID = UUID.randomUUID().toString();
				
				//plugin.config.set("TraderChest." + randomID + ".Inventory", inventory);
				//saveConfig();
				
				ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
				BookMeta meta = (BookMeta) book.getItemMeta();
				meta.setAuthor("Handelsware");
				ArrayList<String> lore = new ArrayList<String>();
				lore.add(sign.getLine(1));
				lore.add(sign.getLine(2));
				lore.add(randomID);
				meta.setLore(lore);
				meta.setTitle("Lieferung");
				book.setItemMeta(meta);
				
				trader.set("TraderChest." + player.getName() + "." + randomID + ".Absender", sign.getLine(1));
				trader.set("TraderChest." + player.getName() + "." + randomID + ".Empfänger", sign.getLine(2));
				//trader.set("TraderChest." + player.getName() + "." + randomID + ".Verpackungsdatum", System.currentTimeMillis());
				// Verfallsdatum ist eine Ganzzahl die in Tagen angegeben wird. (Standard 7 Tage)
				//trader.set("TraderChest." + player.getName() + "." + randomID + ".Verfallsdatum", System.currentTimeMillis() + (1000 * 60 * 60 * 24 * 7));
				trader.set("TraderChest." + player.getName() + "." + randomID + ".Inventar", inventory);
				
				book = player.getWorld().dropItemNaturally(player.getLocation(), book).getItemStack();
				
				chest.getBlockInventory().clear();
				saveTrader();
				
				//sign.setLine(1, "Lieferung bis:");
				//sign.setLine(2, arg1);
				sign.setLine(3, "{" + player.getName() + "}");
				sign.update(true);
				
				return;
			}
		}
		
	}
	
	@SuppressWarnings("deprecation")
	public boolean isTargetSign(Player player) {
		
		if(player.getTargetBlock(null, 3) == null)
			return false;
		
		if(player.getTargetBlock(null, 3).getType() != Material.SIGN && player.getTargetBlock(null, 3).getType() != Material.SIGN_POST && player.getTargetBlock(null, 3).getType() != Material.WALL_SIGN)
			return false;
		
		return true;
		
	}
	
	@SuppressWarnings("deprecation")
	public Sign getTargetSign(Player player) {
		
		return (Sign) player.getTargetBlock(null, 3).getState();
		
	}
	
	public boolean isDoubleChest(Chest chest) {
		if(chest.getInventory().getSize() == 54)
			return true;
		
		return false;
	}

	/**
	 * L�dt einen Spieler aus der Datei in den Cache
	 * @return void
	 */
	private void loadFromFile() {
		getTrader();
		
		
		
		saveTrader();
	}
	
	/**
	 * Speichert einen Spieler aus dem Cache in die Datei
	 * @return void
	 */
	public void saveToFile() {
		getTrader();
		
		
		
		saveTrader();
	}
	
	/**
	 * L�dt die FileConfiguration des Spielers in eine Variable
	 * @return void
	 */
	private void reloadTrader() {
		if (traderFile == null) {
			traderFile = new File(plugin.getDataFolder(), "/trader/chests.yml");
		}
		trader = YamlConfiguration.loadConfiguration(traderFile);
	}

	/**
	 * Liefert die FileConfiguration des Spielers zur�ck
	 * @return FileConfiguration
	 */
	private FileConfiguration getTrader() {
		if (trader == null) {
			reloadTrader();
		}
		return trader;
	}
	
	/**
	 * Speichert die FileConfiguration in die Datei
	 * @return void
	 */
	private void saveTrader() {
		if (trader == null || traderFile == null) {
			return;
		}
		try {
			trader.save(traderFile);
		} catch (IOException ex) {
			Logger.getLogger(JavaPlugin.class.getName()).log(Level.SEVERE, "Could not save config to " + traderFile, ex);
		}
	}
	
}