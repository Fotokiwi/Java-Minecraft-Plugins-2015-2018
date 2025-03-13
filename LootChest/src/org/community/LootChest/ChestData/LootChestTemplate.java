package org.community.LootChest.ChestData;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.community.LootChest.LootChest;

public class LootChestTemplate {

	public LootChest plugin = null;

	private FileConfiguration LootChest = null;
	private File lootChestFile = null;

	private Location loc = null;
	private List<VsWurf> vsWurf = new LinkedList<VsWurf>();
	private List<Wurf> einzelWurf = new LinkedList<Wurf>();
	private String locationString = null;
	private boolean enabled;
	private long cooldown; // Cooldown der Truhe in Sekunden
	private String chestName = "Belohnungstruhe";
	private int chestsize = 54;
	private String commands;

	public LootChestTemplate(LootChest plugin, String loc) {
		this.plugin = plugin;
		locationString = loc;
		String x, y, z, worldname;
		int indexpunkt = loc.indexOf(".", 0);
		int index1 = loc.indexOf("_", indexpunkt);
		int index2 = loc.indexOf("_", index1 + 1);
		worldname = loc.substring(0, indexpunkt);
		x = loc.substring(indexpunkt + 1, index1);
		y = loc.substring(index1 + 1, index2);
		z = loc.substring(index2 + 1);
		World world = Bukkit.getWorld(worldname);
		setLoc(new Location(world, Double.parseDouble(x), Double.parseDouble(y), Double.parseDouble(z)));
		setCooldown(86400);
		setEnabled(true);
		setCommands("");
	}

	@SuppressWarnings({ "deprecation", "unused" })
	public void loadFromFile() {
		getLootChest();
		chestsize = LootChest.getInt("Truhengroesse");
		chestName = LootChest.getString("Truhenname");
		enabled = LootChest.getBoolean("Aktiviert");
		cooldown = LootChest.getLong("CooldownDieserTruhe");
		commands = LootChest.getString("BefehleBeimOeffnen");
		// Lese Würfe hier aus
		for (int i = 0; i < LootChest.getInt("AnzahlvsWuerfe"); i++) {
			VsWurf vswurf = new VsWurf();
			vswurf.setWahrscheinlichkeit(LootChest.getInt("vsWurf" + i + ".wahrscheinlichkeit"));
			if(LootChest.contains("vsWurf" + i + ".Jobs"))
			vswurf.setJobs(LootChest.getStringList("vsWurf" + i + ".Jobs"));
			else
			{
				List<String> defaultJobs = new ArrayList<String>();
				defaultJobs.add("XX");
				vswurf.setJobs(defaultJobs);
			}
			for (int j = 0; j < LootChest.getInt("vsWurf" + i + ".AnzahlWuerfeInDiesemVsWurf"); j++) {
				List<ItemStack> isList = new LinkedList<ItemStack>();
				for (int k = 0; k < LootChest.getInt("vsWurf" + i + ".wurf" + j + ".AnzahlItemstacks"); k++) {

					ItemStack is;
					if (LootChest.contains("vsWurf" + i + ".wurf" + j + ".Itemstack" + k + ".Stack"))
						is = LootChest.getItemStack("vsWurf" + i + ".wurf" + j + ".Itemstack" + k + ".Stack");
					else {
						String path = "vsWurf" + i + ".wurf" + j + ".Itemstack" + k;
						if (LootChest.contains(path + ".subID"))
							is = new ItemStack(LootChest.getInt(path + ".Itemid"), LootChest.getInt(path + ".Anzahl"),
									(short) LootChest.getInt(path + ".subID"));
						else
							is = new ItemStack(LootChest.getInt(path + ".Itemid"), LootChest.getInt(path + ".Anzahl"));
						ItemMeta im = is.getItemMeta();
						BookMeta bm;
						if (LootChest.contains(path + ".DisplayName"))
							im.setDisplayName(LootChest.getString(path + ".DisplayName"));

						for (int m = 0; m < LootChest.getInt(path + ".AnzahlDerEnchantments"); m++) {
							im.addEnchant(Enchantment.getByName(LootChest.getString(path + ".Enchantment" + m + ".Name")),
									LootChest.getInt(path + ".Enchantment" + m + ".Level"), true);
						}
						if (LootChest.contains(path + ".Lore")) {
							String loreGesamt = LootChest.getString(path + ".Itemstack.Lore");
							List<String> Lore = new LinkedList<String>();
							for (String l : loreGesamt.split("]["))
								Lore.add(l);
							im.setLore(Lore);
							is.setItemMeta(im);
						}

					}
					isList.add(is);
				}
				Wurf wurf = new Wurf(isList, LootChest.getInt("vsWurf" + i + ".wurf" + j + ".wahrscheinlichkeit"));
				vswurf.addWurfToList(wurf);
			}
			vsWurf.add(vswurf);
		}
		for (int i = 0; i < LootChest.getInt("AnzahlEinzelneWuerfe"); i++) {
			List<ItemStack> isList = new LinkedList<ItemStack>();
			for (int j = 0; j < LootChest.getInt("einzelWurf" + i + ".AnzahlItemstacks"); j++) {
				ItemStack is;
				if (LootChest.contains("einzelWurf" + i + ".Itemstack" + j + ".Stack"))
					is = LootChest.getItemStack("einzelWurf" + i + ".Itemstack" + j + ".Stack");
				else {
					String path = "einzelWurf" + i + ".Itemstack" + j;
					if (LootChest.contains(path + ".subID"))
						is = new ItemStack(LootChest.getInt(path + ".Itemid"), LootChest.getInt(path + ".Anzahl"), (short) LootChest.getInt(path + ".subID"));
					else
						is = new ItemStack(LootChest.getInt(path + ".Itemid"), LootChest.getInt(path + ".Anzahl"));
					ItemMeta im = is.getItemMeta();
					BookMeta bm;
					if (LootChest.contains(path + ".DisplayName")) {
						im.setDisplayName(LootChest.getString(path + ".DisplayName"));
					}
					for (int k = 0; k < LootChest.getInt(path + ".AnzahlDerEnchantments"); k++) {
						im.addEnchant(Enchantment.getByName(LootChest.getString(path + ".Enchantment" + k + ".Name")),
								LootChest.getInt(path + ".Enchantment" + k + ".Level"), true);
					}
					if (LootChest.contains(path + ".Lore")) {
						String loreGesamt = LootChest.getString(path + ".Lore");
						List<String> Lore = new LinkedList<String>();
						for (String l : loreGesamt.split("][")) {
							Lore.add(l);
						}
						im.setLore(Lore);
						is.setItemMeta(im);
					}
				}
				isList.add(is);

			}
			Wurf wurf = new Wurf(isList, LootChest.getInt("einzelWurf" + i + ".wahrscheinlichkeit"));
			if(LootChest.contains("einzelWurf" + i + ".Jobs"))
			wurf.setJobs(LootChest.getStringList("einzelWurf" + i + ".Jobs"));
			else
			{
				List<String> defaultJobs = new ArrayList<String>();
				defaultJobs.add("XX");
				wurf.setJobs(defaultJobs);
			}
			einzelWurf.add(wurf);
		}

		saveLootChest();
	}


	public void saveToFile() {
		getLootChest();

		LootChest.set("Truhengroesse", chestsize);
		LootChest.set("Truhenname", chestName);
		LootChest.set("Aktiviert", enabled);
		LootChest.set("CooldownDieserTruhe", cooldown);
		LootChest.set("BefehleBeimOeffnen", commands);
		// Würfe speichern
		LootChest.set("AnzahlvsWuerfe", vsWurf.size());
		LootChest.set("AnzahlEinzelneWuerfe", einzelWurf.size());
		for (int i = 0; i < vsWurf.size(); i++) {
			LootChest.set("vsWurf" + i + ".AnzahlWuerfeInDiesemVsWurf", vsWurf.get(i).getWuerfe().size());
			LootChest.set("vsWurf" + i + ".wahrscheinlichkeit", vsWurf.get(i).getProbability());
			LootChest.set("vsWurf" + i + ".Jobs", vsWurf.get(i).getJobs());
			for (int j = 0; j < vsWurf.get(i).getWuerfe().size(); j++) {
				LootChest.set("vsWurf" + i + ".wurf" + j + ".wahrscheinlichkeit", vsWurf.get(i).getWurfByIndex(j).getProbability());

				LootChest.set("vsWurf" + i + ".wurf" + j + ".AnzahlItemstacks", vsWurf.get(i).getWurfByIndex(j).getItemstack().size());

				for (int m = 0; m < vsWurf.get(i).getWurfByIndex(j).getItemstack().size(); m++) {
					LootChest.set("vsWurf" + i + ".wurf" + j + ".Itemstack" + m + ".Stack", vsWurf.get(i).getWurfByIndex(j).getItemstack().get(m));
					/*
					 * String path = "vsWurf" + i + ".wurf" + j + ".Itemstack" +
					 * m; LootChest.set(path + ".Itemid",
					 * vsWurf.get(i).getWurfByIndex
					 * (j).getItemstack().get(m).getType().getId());
					 * LootChest.set(path + ".Anzahl",
					 * vsWurf.get(i).getWurfByIndex
					 * (j).getItemstack().get(m).getAmount()); if
					 * (vsWurf.get(i).
					 * getWurfByIndex(j).getItemstack().get(m).getDurability()
					 * != 0) LootChest.set(path + ".subID",
					 * vsWurf.get(i).getWurfByIndex
					 * (j).getItemstack().get(m).getDurability()); if
					 * (vsWurf.get
					 * (i).getWurfByIndex(j).getItemstack().get(m).hasItemMeta
					 * ()) {
					 * 
					 * if
					 * (vsWurf.get(i).getWurfByIndex(j).getItemstack().get(m).
					 * getItemMeta().hasDisplayName()) LootChest.set(path +
					 * ".DisplayName",
					 * vsWurf.get(i).getWurfByIndex(j).getItemstack
					 * ().get(m).getItemMeta().getDisplayName()); if
					 * (!(vsWurf.get
					 * (i).getWurfByIndex(j).getItemstack().get(m).getItemMeta
					 * ().getEnchants().size() == 0)) LootChest.set(path +
					 * ".AnzahlDerEnchantments",
					 * vsWurf.get(i).getWurfByIndex(j).
					 * getItemstack().get(m).getItemMeta
					 * ().getEnchants().size()); else LootChest.set(path +
					 * ".AnzahlDerEnchantments", 0); if
					 * (vsWurf.get(i).getWurfByIndex
					 * (j).getItemstack().get(m).getItemMeta().hasEnchants()) {
					 * int k = 0; for (Enchantment ent :
					 * vsWurf.get(i).getWurfByIndex
					 * (j).getItemstack().get(m).getItemMeta
					 * ().getEnchants().keySet()) { LootChest.set(path +
					 * ".Enchantment" + k + ".Name", ent.getName());
					 * LootChest.set(path + ".Enchantment" + k + ".Level",
					 * vsWurf.get(i).getWurfByIndex(j).getItemstack().get(m).
					 * getEnchantmentLevel(ent)); k++; } } if
					 * (vsWurf.get(i).getWurfByIndex
					 * (j).getItemstack().get(m).getItemMeta().hasLore()) {
					 * String loreGesamt = ""; List<String> lore =
					 * vsWurf.get(i).
					 * getWurfByIndex(j).getItemstack().get(m).getItemMeta
					 * ().getLore(); for (int k = 0; k < lore.size(); k++) { if
					 * (loreGesamt.compareTo("") == 0) loreGesamt = lore.get(k);
					 * else loreGesamt = loreGesamt + "][" + lore.get(k); }
					 * LootChest.set(path + ".Lore", loreGesamt);
					 * 
					 * }
					 * 
					 * if
					 * (vsWurf.get(i).getWurfByIndex(j).getItemstack().get(m).
					 * getType() == Material.BOOK){ LootChest.set(path +
					 * ".BookTitle",
					 * ((BookMeta)vsWurf.get(i).getWurfByIndex(j).getItemstack
					 * ().get(m).getItemMeta()).getTitle()); LootChest.set(path
					 * + ".BookAuthor",
					 * ((BookMeta)vsWurf.get(i).getWurfByIndex(j
					 * ).getItemstack().get(m).getItemMeta()).getAuthor());
					 * LootChest.set(path + ".BookPages",
					 * ((BookMeta)vsWurf.get(i
					 * ).getWurfByIndex(j).getItemstack().
					 * get(m).getItemMeta()).getPages()); } }
					 */
				}

			}

		}
		for (int i = 0; i < einzelWurf.size(); i++) {
			LootChest.set("einzelWurf" + i + ".wahrscheinlichkeit", einzelWurf.get(i).getProbability());
			LootChest.set("einzelWurf" + i + ".Jobs", einzelWurf.get(i).getJobs());
			LootChest.set("einzelWurf" + i + ".AnzahlItemstacks", einzelWurf.get(i).getItemstack().size());
			for (int j = 0; j < einzelWurf.get(i).getItemstack().size(); j++) {
				LootChest.set("einzelWurf" + i + ".Itemstack" + j + ".Stack", einzelWurf.get(i).getItemstack().get(j));
				/*
				 * String path = "einzelWurf" + i + ".Itemstack" + j;
				 * LootChest.set(path + ".Itemid",
				 * einzelWurf.get(i).getItemstack().get(j).getType().getId());
				 * LootChest.set(path + ".Anzahl",
				 * einzelWurf.get(i).getItemstack().get(j).getAmount()); if
				 * (einzelWurf.get(i).getItemstack().get(j).getDurability() !=
				 * 0) LootChest.set(path + ".subID",
				 * einzelWurf.get(i).getItemstack().get(j).getDurability()); if
				 * (einzelWurf.get(i).getItemstack().get(j).hasItemMeta()) { if
				 * (einzelWurf.get(i).getItemstack().get(j).getItemMeta().
				 * hasDisplayName()) LootChest.set(path + ".DisplayName",
				 * einzelWurf
				 * .get(i).getItemstack().get(j).getItemMeta().getDisplayName
				 * ()); if
				 * (!(einzelWurf.get(i).getItemstack().get(j).getItemMeta
				 * ().getEnchants() != null)) LootChest.set(path +
				 * ".Itemstack.AnzahlDerEnchantments",
				 * einzelWurf.get(i).getItemstack
				 * ().get(j).getItemMeta().getEnchants().size()); else
				 * LootChest.set(path + ".AnzahlDerEnchantments", 0); if
				 * (einzelWurf
				 * .get(i).getItemstack().get(j).getItemMeta().hasEnchants()) {
				 * int k = 0; for (Enchantment ent :
				 * einzelWurf.get(i).getItemstack
				 * ().get(j).getItemMeta().getEnchants().keySet()) {
				 * LootChest.set(path + ".Enchantment" + k + ".Name",
				 * ent.getName()); LootChest.set(path + ".Enchantment" + k +
				 * ".Level",
				 * einzelWurf.get(i).getItemstack().get(j).getEnchantmentLevel
				 * (ent)); k++; } } if
				 * (einzelWurf.get(i).getItemstack().get(j).getItemMeta
				 * ().hasLore()) { List<String> lore =
				 * einzelWurf.get(i).getItemstack
				 * ().get(j).getItemMeta().getLore(); String loreGesamt = "";
				 * for (int k = 0; k < lore.size(); k++) { if
				 * (loreGesamt.compareTo("") == 0) loreGesamt = lore.get(k);
				 * else loreGesamt = loreGesamt + "][" + lore.get(k); }
				 * LootChest.set(path + ".Lore", loreGesamt);
				 * 
				 * }
				 * 
				 * if (einzelWurf.get(i).getItemstack().get(j).getType() ==
				 * Material.BOOK){ LootChest.set(path + ".BookTitle",
				 * ((BookMeta)
				 * einzelWurf.get(i).getItemstack().get(j).getItemMeta
				 * ()).getTitle()); LootChest.set(path + ".BookAuthor",
				 * ((BookMeta
				 * )einzelWurf.get(i).getItemstack().get(j).getItemMeta
				 * ()).getAuthor()); LootChest.set(path + ".BookPages",
				 * ((BookMeta
				 * )einzelWurf.get(i).getItemstack().get(j).getItemMeta
				 * ()).getPages()); } }
				 */
			}
		}

		saveLootChest();
	}

	private void reloadLootChest() {
		if (lootChestFile == null) {
			lootChestFile = new File(plugin.getDataFolder(), "/lootChests/" + locationString + ".yml");
		}
		LootChest = YamlConfiguration.loadConfiguration(lootChestFile);
	}

	private FileConfiguration getLootChest() {
		if (LootChest == null) {
			reloadLootChest();
		}
		return LootChest;
	}

	private void saveLootChest() {
		if (LootChest == null || lootChestFile == null) {
			return;
		}
		try {
			LootChest.save(lootChestFile);
		} catch (IOException ex) {
			Logger.getLogger(JavaPlugin.class.getName()).log(Level.SEVERE, "Could not save config to " + lootChestFile, ex);
		}
	}

	public long getCooldown() {
		return cooldown;
	}

	public void setCooldown(long cooldown) {
		this.cooldown = cooldown;
	}

	public List<VsWurf> getVsWurf() {
		return vsWurf;
	}

	public void setVsWurf(List<VsWurf> vsWurf) {
		this.vsWurf = vsWurf;
	}

	public List<Wurf> getEinzelWurf() {
		return einzelWurf;
	}

	public void setEinzelWurf(List<Wurf> einzelWurf) {
		this.einzelWurf = einzelWurf;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Location getLoc() {
		return loc;
	}

	public void setLoc(Location loc) {
		this.loc = loc;
	}

	public String getChestName() {
		return chestName;
	}

	public void setChestName(String chestName) {
		this.chestName = chestName;
	}

	public int getChestsize() {
		return chestsize;
	}

	public void setChestsize(int chestsize) {
		this.chestsize = chestsize;
	}

	public String getCommands() {
		return commands;
	}

	public void setCommands(String commands) {
		this.commands = commands;
	}

}
