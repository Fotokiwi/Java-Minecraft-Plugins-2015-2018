package org.community.ambience;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

public class Commands {

	private Ambience plugin;

	private Map<Player, LocationsForBuildPattern> locationsForBP;

	public Commands(Ambience plugin) {
		this.plugin = plugin;
		locationsForBP = new HashMap<Player, LocationsForBuildPattern>();
	}

	@SuppressWarnings("deprecation")
	public boolean getCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (sender instanceof Player) {
			final Player player = (Player) sender;
			
			if(!isAdmin(player)) {
				return true;
			}

			if (args.length == 0) {
				player.sendMessage(ChatColor.RED + "Keinen Befehl eingegeben!");
				return true;
			}
			if (args.length == 1) {
				if (args[0].equalsIgnoreCase("info")) {
					// hilfetexte
					plugin.ambienceDiseases.diseasesTemperature.calculateTemperature(player);
					return true;
				}

				if (args[0].equalsIgnoreCase("lagerfeuer")) {
					ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
					BookMeta meta = (BookMeta) book.getItemMeta();
					meta.setAuthor("Ambiente Gegenstand");
					meta.setTitle("Kleines Lagerfeuer");
					meta.addPage("A");
					meta.addPage("B");
					meta.addPage("STEP,3");
					book.setItemMeta(meta);
					book = player.getWorld().dropItemNaturally(player.getLocation(), book).getItemStack();
					return true;
				}

				if (args[0].equalsIgnoreCase("kleineszelt")) {

					ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
					BookMeta meta = (BookMeta) book.getItemMeta();
					meta.setAuthor("Ambiente Gegenstand");
					meta.setTitle("Kleines Zelt");
					meta.addPage("A");
					meta.addPage("B");
					meta.addPage("WOOL,1-WOOL,1-WOOL,1-WOOL,1-WOOL,1-FENCE,0-FENCE_GATE,0");
					book.setItemMeta(meta);
					book = player.getWorld().dropItemNaturally(player.getLocation(), book).getItemStack();
					return true;
				}

				if (args[0].equalsIgnoreCase("mittlereszeltlager")) {

					ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
					BookMeta meta = (BookMeta) book.getItemMeta();
					meta.setAuthor("Ambiente Gegenstand");
					meta.setTitle("Mittleres Zeltlager");
					book.setItemMeta(meta);
					book = player.getWorld().dropItemNaturally(player.getLocation(), book).getItemStack();
					return true;
				}

				if (args[0].equalsIgnoreCase("setEU")) {
					LocationsForBuildPattern lfbp = null;
					if (!locationsForBP.containsKey(player)) {
						locationsForBP.put(player, new LocationsForBuildPattern());
					}

					lfbp = locationsForBP.get(player);
					lfbp.EU = player.getTargetBlock(null, 3).getLocation();
					player.sendMessage("Ecke unten an Position " + lfbp.EU.getBlockX() + ", " + lfbp.EU.getBlockY() + ", " + lfbp.EU.getBlockZ()
							+ " gespeichert.");
					return true;
				}

				if (args[0].equalsIgnoreCase("setEO")) {
					LocationsForBuildPattern lfbp = null;
					if (!locationsForBP.containsKey(player)) {
						locationsForBP.put(player, new LocationsForBuildPattern());
					}

					lfbp = locationsForBP.get(player);
					lfbp.EO = player.getTargetBlock(null, 3).getLocation();
					player.sendMessage("Ecke oben an Position " + lfbp.EO.getBlockX() + ", " + lfbp.EO.getBlockY() + ", " + lfbp.EO.getBlockZ()
							+ " gespeichert.");
					return true;
				}
				if (args[0].equalsIgnoreCase("setC")) {
					LocationsForBuildPattern lfbp = null;
					if (!locationsForBP.containsKey(player)) {
						locationsForBP.put(player, new LocationsForBuildPattern());
					}

					lfbp = locationsForBP.get(player);
					lfbp.C = player.getTargetBlock(null, 3).getLocation();
					player.sendMessage("Mitte an Position " + lfbp.C.getBlockX() + ", " + lfbp.C.getBlockY() + ", " + lfbp.C.getBlockZ() + " gespeichert.");
					return true;
				}

				if (args[0].equalsIgnoreCase("ListeBP")) {
					Set<String> setbp = plugin.cache.listOfAllBlueprints();
					if (setbp.isEmpty()) {
						player.sendMessage("Es gibt noch keine Blueprints.");
						return true;
					} else {
						String output = "Es gibt folgende Blueprints: ";
						for (String s : setbp)
							output += s + ", ";
						player.sendMessage(output);
					}

				}

			}
			if (args.length == 2) {
				if (args[0].equalsIgnoreCase("blueprint")) {
					if (!(locationsForBP.containsKey(player) && locationsForBP.get(player).oneIsNull())) {
						player.sendMessage("Eine Ecke oder die Mitte wurden noch nicht gesetzt");
						return true;
					}
					if (plugin.cache.blueprintExists(args[1])) {
						player.sendMessage("Der gewählte Name wird bereits von einem anderen Blueprint verwendet");
						return true;
					}
					Map<String, String> buildPattern = calculateBuildPattern(locationsForBP.get(player));
					plugin.cache.addBlueprint(args[1], buildPattern);
					plugin.cache.getBlueprintByName(args[1]).SaveToFile();
					player.sendMessage("Ein neuer Blueprint mit Name " + args[1] + " wurde erstellt.");
					return true;
				}

				if (args[0].equalsIgnoreCase("blueprintErstellen") && plugin.cache.blueprintExists(args[1])) {
					ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
					BookMeta meta = (BookMeta) book.getItemMeta();
					meta.setAuthor("Ambiente Gegenstand");
					meta.setTitle(plugin.cache.getBlueprintByName(args[1]).getIdentifierName());
					book.setItemMeta(meta);
					book = player.getWorld().dropItemNaturally(player.getLocation(), book).getItemStack();
					player.sendMessage("Die Blueprint für " + plugin.cache.getBlueprintByName(args[1]).getIdentifierName() + " liegt vor dir.");
					return true;
				}

				if (args[0].equalsIgnoreCase("rezeptErstellen") && plugin.cache.blueprintExists(args[1])) {
					ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
					BookMeta meta = (BookMeta) book.getItemMeta();
					meta.setAuthor("Ambiente Gegenstand");
					meta.setTitle("Rezept für" + plugin.cache.getBlueprintByName(args[1]).getIdentifierName());
					List<ItemStack> requiredMaterials = plugin.cache.getBlueprintByName(args[1]).getRequiredMaterials();
					plugin.LogInfo(requiredMaterials.size() + " requiredMaterials list");
					for(ItemStack s : requiredMaterials)
					{
						meta.addPage("Das Objekt benötigt insgesamt " + s.getAmount() + " " +s.getType() );
					}
					book.setItemMeta(meta);
					book = player.getWorld().dropItemNaturally(player.getLocation(), book).getItemStack();
					player.sendMessage("Das Rezept für " + plugin.cache.getBlueprintByName(args[1]).getIdentifierName() + " liegt vor dir.");
					return true;
				}
			}

			if (args.length == 3) {

				if (args[0].equalsIgnoreCase("blueprint")) {
					if (!(locationsForBP.containsKey(player) && locationsForBP.get(player).oneIsNull())) {
						player.sendMessage("Eine Ecke oder die Mitte wurden noch nicht gesetzt");
						return true;
					}
					if (plugin.cache.blueprintExists(args[1])) {
						player.sendMessage("Der gewählte Name wird bereits von einem anderen Blueprint verwendet");
						return true;
					}
					Map<String, String> buildPattern = calculateBuildPattern(locationsForBP.get(player));
					plugin.cache.addBlueprint(args[1], args[2], buildPattern);
					plugin.cache.getBlueprintByName(args[1]).SaveToFile();
					player.sendMessage("Ein neuer Blueprint mit Anzeigenamem " + args[2] + " wurde erstellt.");
					return true;
				}

			}
		}
		return false;

	}

	@SuppressWarnings({ "unused", "deprecation" })
	public Map<String, String> calculateBuildPattern(LocationsForBuildPattern lfbp) {
		Location locEU = lfbp.EU;
		Location locEO = lfbp.EO;
		Location locC = lfbp.C;
		Map<String, String> buildPattern = new HashMap<String, String>();
		int heigth = locEO.getBlockY() - locEU.getBlockY();
		int width = 0;
		int length = 0;
		if (locEO.getBlockZ() >= locEU.getBlockZ()) {
			width = locEO.getBlockZ() - locEU.getBlockZ();
		} else {
			width = locEU.getBlockZ() - locEO.getBlockZ();
		}

		if (locEO.getBlockX() >= locEU.getBlockX()) {
			length = locEO.getBlockX() - locEU.getBlockX();
		} else {
			length = locEU.getBlockX() - locEO.getBlockX();
		}

		int startX = 0;
		int endX = 0;
		int startZ = 0;
		int endZ = 0;
		int startY = 0;
		int endY = 0;

		if (locEO.getBlockY() >= locEU.getBlockY()) {
			startY = locEU.getBlockY();
			endY = locEO.getBlockY();
		} else {
			startY = locEO.getBlockY();
			endY = locEU.getBlockY();
		}

		if (locEO.getBlockX() >= locEU.getBlockX()) {
			startX = locEU.getBlockX();
			endX = locEO.getBlockX();
		} else {
			startX = locEO.getBlockX();
			endX = locEU.getBlockX();
		}

		if (locEO.getBlockZ() >= locEU.getBlockZ()) {
			startZ = locEU.getBlockZ();
			endZ = locEO.getBlockZ();
		} else {
			startZ = locEO.getBlockZ();
			endZ = locEU.getBlockZ();
		}

		int cX = locC.getBlockX();
		int cY = locC.getBlockY();
		int cZ = locC.getBlockZ();

		// h, w, l = absolute Position in world
		for (int h = startY; h <= endY; h++) {

			for (int w = startZ; w <= endZ; w++) {

				for (int l = startX; l <= endX; l++) {

					// x, y, z = relative position in world
					int x = 0;
					int y = 0;
					int z = 0;

					if (cX >= l) {
						x = (cX - l) * -1;
					} else {
						x = l - cX;
					}
					if (cY >= y) {
						y = (cY - h) * -1;
					} else {
						y = h - cY;
					}
					if (cZ >= w) {
						z = (cZ - w) * -1;
					} else {
						z = w - cZ;
					}

					if (locC.getWorld().getBlockAt(l, h, w).getType() == Material.DIAMOND_BLOCK) {

					} else {
						Location blockLocation = new Location(locC.getWorld(), x, y, z);
						buildPattern.put(blockLocation.getWorld().getName() + ',' + blockLocation.getBlockX() + ',' + blockLocation.getBlockY() + ','
								+ blockLocation.getBlockZ(),
								locC.getWorld().getBlockAt(l, h, w).getType().toString() + "," + locC.getWorld().getBlockAt(l, h, w).getData());

					}

				}

			}
		}
		return buildPattern;
	}

	public class LocationsForBuildPattern {
		public Location EU, EO, C;

		public LocationsForBuildPattern() {
		}

		public boolean oneIsNull() {
			return EU != null && EO != null && C != null;
		}
	}
	
	public boolean isAdmin(Player player) {
		if(plugin.config.getList("Config.Admins") != null) {
			if(plugin.config.getList("Config.Admins").contains(player.getUniqueId().toString()))
				return true;
		}
		
		return false;
	}
}
