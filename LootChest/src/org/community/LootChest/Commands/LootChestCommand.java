package org.community.LootChest.Commands;

import java.util.Map.Entry;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.community.LootChest.LootChest;
import org.community.LootChest.ChestData.LootChestTemplate;
import org.community.LootChest.ChestData.VsWurf;
import org.community.LootChest.ChestData.Wurf;

public class LootChestCommand {
	private LootChest plugin;

	public LootChestCommand(LootChest lootChest) {
		this.plugin = lootChest;
	}

	@SuppressWarnings("deprecation")
	public boolean getCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (args.length == 1) {
				if (args[0].equalsIgnoreCase("cooldown") || args[0].equalsIgnoreCase("abklingzeit")) {
					Block block = player.getTargetBlock(null, 6);
					if (blockIsAChest(block)) {
						String output = getPlayerSendString(block);
						String locationString = getLocationstring(block);
						if (plugin.cache.fileNamesContainsString(locationString)) {
							long cooldown = 0;
							if (plugin.cache.getUserDataChests().get(player.getUniqueId()).getUserDataValues().containsKey(locationString))
								cooldown = plugin.cache.getUserDataChests().get(player.getUniqueId()).getUserDataValues().get(locationString).getLastUse();
							if (cooldown == 0)
								player.sendMessage("Du hast die Kiste noch nie geöffnet.");
							else
								player.sendMessage("Die Kiste wird in " + cooldown / ((float) 1000 * 60 * 60) + " Minuten zurückgesetzt.");
							return true;
						} else
							player.sendMessage(output + "ist keine eine LootChest.");

						return true;
					} else
						player.sendMessage("Der angeschaute Block an Position " + block.getX() + ", " + block.getY() + ", " + block.getZ()
								+ " ist keine Truhe.");
					return true;
				}
			}

			if (isAdmin(player.getUniqueId())) {
				if (args.length == 1) {

					/*
					 * Alle Befehle auflisten einschließlich Erklärungen
					 */
					if (args[0].equalsIgnoreCase("help") || args[0].equalsIgnoreCase("hilfe")) {
						player.sendMessage("==================================================");
						player.sendMessage("[Irgendwas] bedeutet, dass diese Angabe nicht zwangsläufig getätigt werden muss.");
						player.sendMessage(ChatColor.GOLD + "/lootchest erstellen [NAME]" + ChatColor.WHITE + "Die angeschaute Kiste wird zu einer Lootchest.");
						player.sendMessage(ChatColor.GOLD + "/lootchest aktivieren " + ChatColor.WHITE
								+ "Die angeschaute Truhe wird aktiviert (falls deaktiviert).");
						player.sendMessage(ChatColor.GOLD + "/lootchest deaktivieren " + ChatColor.WHITE
								+ "Die angeschaute Truhe wird deaktiviert (falls aktiviert).");
						player.sendMessage(ChatColor.GOLD + "/lootchest zeige " + ChatColor.WHITE + "Die Daten der angeschauten Truhe werden angezeigt.");
						player.sendMessage(ChatColor.GOLD + "/lootchest setzeTruhengroeße " + ChatColor.WHITE + "Die Größe der Truhe wird neugesetzt.");
						player.sendMessage(ChatColor.GOLD + "/lootchest setzeAbklingzeit " + ChatColor.WHITE + "Die Abklingzeit der Truhe wird neugesetzt.");
						player.sendMessage(ChatColor.GOLD + "/lootchest setzeTruhenname " + ChatColor.WHITE + "Definiere einen neuen Truhennamen.");
						player.sendMessage(ChatColor.GOLD + "/lootchest cooldown " + ChatColor.WHITE
								+ "Zeige den verbleibenden Cooldown für den Spieler für diese Truhe an.");
						player.sendMessage(ChatColor.GOLD + "/lootchest hilfe2 " + ChatColor.WHITE
								+ "Zeige die Befehle zum Hinzufügen und Übernehmen von Würfen an.");
						player.sendMessage("==================================================");
					} else if (args[0].equalsIgnoreCase("loothelp") || args[0].equalsIgnoreCase("hilfe2")) {
						player.sendMessage("==================================================");
						player.sendMessage("SubIDs werden mit einem Doppelpunkt von der ID getrennt. Zum Beispiel: 5:1 ");
						player.sendMessage(ChatColor.GOLD + "/lootchest wurfhinzufuegen Itemid Anzahl Wahrscheinlichkeit [Displayname] " + ChatColor.WHITE
								+ "Fügt der angeschauten Truhe einen Wurf hinzu.");
						player.sendMessage(ChatColor.GOLD + "/lootchest reload " + ChatColor.WHITE
								+ "Lädt die Datei der angeguckten Truhe neu.");
						player.sendMessage(ChatColor.GOLD + "/lootchest save " + ChatColor.WHITE
								+ "Speichert alle Truhendateien.");
						player.sendMessage(ChatColor.GOLD
								+ "/lootchest vswurfhinzufuegen WahrscheinlichkeitVsWurf Itemid Anzahl Wahrscheinlichkeit Itemid2 Anzahl2 Wahrscheinlichkeit2 "
								+ ChatColor.WHITE + "Fügt der angeschauten Truhe einen VsWurf hinzu.");
						player.sendMessage(ChatColor.GOLD + "/lootchest ÜbernehmeWürfe KoordinateX KoordinateY KoordinateZ" + ChatColor.WHITE
								+ " Fügt der angeschauten Truhe alle Würfe der Lootchest an den eingegeben Koordinaten hinzu.");
						player.sendMessage("Bei Doppelkisten bitte eine .5 in der Koordinate verwenden, in die sich die Doppelkiste ersteckt. Beispiel: /lootchest übernehmeWürfe 172.5 80 213");
						player.sendMessage("==================================================");
					}

					/*
					 * erstellt aus angeschauter Kiste eine Lootchest; falls
					 * bereits vorhanden, wird eine Fehlermeldung ausgegeben
					 */
					else if (args[0].equalsIgnoreCase("create") || args[0].equalsIgnoreCase("erstellen")) {
						Block block = player.getTargetBlock(null, 6);
						if (blockIsAChest(block)) {
							String output = getPlayerSendString(block);
							String locationString = getLocationstring(block);
							if (!plugin.cache.fileNamesContainsString(locationString)) {
								LootChestTemplate lct = new LootChestTemplate(plugin, locationString);
								lct.saveToFile();
								plugin.cache.getFileNames().add(locationString);
								plugin.cache.getLootChestList().put(locationString, lct);
								player.sendMessage(output + "ist nun eine LootChest.");

							} else
								player.sendMessage(output + "ist bereits eine LootChest.");

							return true;
						} else
							player.sendMessage("Der angeschaute Block an Position " + block.getX() + ", " + block.getY() + ", " + block.getZ()
									+ " ist keine Truhe.");
						return true;
					}// end create
					
					/*
					 * reloadet die angeguckt Truhe
					 */
					else if (args[0].equalsIgnoreCase("reload") || args[0].equalsIgnoreCase("neuladen")) {
						Block block = player.getTargetBlock(null, 6);
						if (blockIsAChest(block)) {
							String output = getPlayerSendString(block);
							String locationString = getLocationstring(block);
							if (plugin.cache.fileNamesContainsString(locationString)) {
								plugin.cache.getLootChestList().get(locationString).loadFromFile();

								player.sendMessage(output + " wurde gerade neu geladen.");
								return true;
							} else
								player.sendMessage(output + "ist keine eine LootChest.");

							return true;
						} else
							player.sendMessage("Der angeschaute Block an Position " + block.getX() + ", " + block.getY() + ", " + block.getZ()
									+ " ist keine Truhe.");
						return true;
					}// end reload

					/*
					 * speichert alle Truhen
					 */
					else if (args[0].equalsIgnoreCase("save") || args[0].equalsIgnoreCase("speichern")) {
						for(Entry<String, LootChestTemplate> e : plugin.cache.getLootChestList().entrySet())
							e.getValue().saveToFile();
						player.sendMessage("Alle Truhendateien wurden gespeichert.");	
						return true;
					}// end reload

					/*
					 * aktiviert die angeschaute Truhe wieder
					 */
					if (args[0].equalsIgnoreCase("enable") || args[0].equalsIgnoreCase("aktivieren")) {
						Block block = player.getTargetBlock(null, 6);
						if (blockIsAChest(block)) {
							String output = getPlayerSendString(block);
							String locationString = getLocationstring(block);
							if (plugin.cache.fileNamesContainsString(locationString)) {
								LootChestTemplate lct = plugin.cache.getLootChestList().get(locationString);
								if (lct.isEnabled())
									player.sendMessage(output + "ist bereits aktiviert.");
								else {
									lct.setEnabled(true);
									lct.saveToFile();
									player.sendMessage(output + "ist nun aktiviert.");

								}
							} else
								player.sendMessage(output + "ist keine LootChest.");

							return true;
						} else
							player.sendMessage("Der angeschaute Block an Position " + block.getX() + ", " + block.getY() + ", " + block.getZ()
									+ " ist keine Truhe.");
						return true;
					}// end enable

					if (args[0].equalsIgnoreCase("readin") || args[0].equalsIgnoreCase("einlesen")) {
						Block block = player.getTargetBlock(null, 6);
						if (blockIsAChest(block)) {
							String output = getPlayerSendString(block);
							String locationString = getLocationstring(block);
							if (plugin.cache.fileNamesContainsString(locationString)) {
								LootChestTemplate lct = plugin.cache.getLootChestList().get(locationString);
								ItemStack[] inv = plugin.cache.getUserDataChests().get(player.getUniqueId()).getUserDataValues().get(locationString)
										.getInventory().getContents();
								for (ItemStack is : inv) {
									if(is != null)
									lct.getEinzelWurf().add(new Wurf(is, 100));
								}
								player.sendMessage("Alle Itemstacks wurden eingelesen.");
								return true;
							} else
								player.sendMessage(output + "ist keine LootChest.");

							return true;
						} else
							player.sendMessage("Der angeschaute Block an Position " + block.getX() + ", " + block.getY() + ", " + block.getZ()
									+ " ist keine Truhe.");
						return true;
					}// end enable

					/*
					 * deaktiviert die angeschaute Truhe
					 */
					if (args[0].equalsIgnoreCase("disable") || args[0].equalsIgnoreCase("deaktivieren")) {
						Block block = player.getTargetBlock(null, 6);
						if (blockIsAChest(block)) {
							String output = getPlayerSendString(block);
							String locationString = getLocationstring(block);
							if (plugin.cache.fileNamesContainsString(locationString)) {
								LootChestTemplate lct = plugin.cache.getLootChestList().get(locationString);
								if (!lct.isEnabled())
									player.sendMessage(output + "ist bereits deaktiviert.");
								else {
									lct.setEnabled(false);
									lct.saveToFile();

									player.sendMessage(output + "ist nun deaktiviert.");

								}
							} else
								player.sendMessage(output + "ist keine LootChest.");

							return true;
						} else
							player.sendMessage("Der angeschaute Block an Position " + block.getX() + ", " + block.getY() + ", " + block.getZ()
									+ " ist keine Truhe.");
						return true;
					}// end disable

					/*
					 * Zeige alle W�rfe der angeschauten Truhe
					 */
					if (args[0].equalsIgnoreCase("show") || args[0].equalsIgnoreCase("zeige")) {
						Block block = player.getTargetBlock(null, 6);
						if (blockIsAChest(block)) {
							String output = getPlayerSendString(block);
							String locationString = getLocationstring(block);
							if (plugin.cache.fileNamesContainsString(locationString)) {
								LootChestTemplate lct = plugin.cache.getLootChestList().get(locationString);
								player.sendMessage(output + "besitzt folgende Würfe:");
								player.sendMessage("VsWürfe:");
								if (lct.getVsWurf().isEmpty())
									player.sendMessage("Keine VsWürfe vorhanden");
								else {
									for (VsWurf vswurf : lct.getVsWurf()) {
										int i = 1;
										int j = 1;
										player.sendMessage("Dieser VsWurf besitzt eine Wahrscheinlichkeit von " + vswurf.getProbability() + " %.");
										for (Wurf wurf : vswurf.getWuerfe()) {
											player.sendMessage("Wurf" + i + ": Wahrscheinlichkeit von " + wurf.getProbability() + "% für folgende Itemstacks: ");
											for (ItemStack is : wurf.getItemstack()) {
												player.sendMessage("Itemstack " + j + ": ID: " + is.getTypeId() + " Anzahl: " + is.getAmount());
												j++;
											}
											i++;
										}
									}
								}
								player.sendMessage("Einzelwürfe:");
								if (lct.getEinzelWurf().isEmpty())
									player.sendMessage("Keine Einzelwürfe vorhanden");
								else {
									int i = 1;
									int j = 1;
									for (Wurf wurf : lct.getEinzelWurf()) {
										player.sendMessage("Einzelwurf" + i + ": Wahrscheinlichkeit von " + wurf.getProbability()
												+ "% für folgende Itemstacks: ");
										for (ItemStack is : wurf.getItemstack()) {
											player.sendMessage("Itemstack" + j + ": ID: " + is.getTypeId() + " Anzahl: " + is.getAmount());
											j++;
										}
										i++;
									}
								}
							} else
								player.sendMessage(output + "ist keine LootChest.");

							return true;
						} else
							player.sendMessage("Der angeschaute Block an Position " + block.getX() + ", " + block.getY() + ", " + block.getZ()
									+ " ist keine Truhe.");
						return true;
					}// end show

				} else if (args.length == 2) {
					/*
					 * Definiere einen neuen Cooldown f�r die angeschaute Truhe
					 */
					if (args[0].equalsIgnoreCase("setCooldown") || args[0].equalsIgnoreCase("setzeAbklingzeit")) {
						Block block = player.getTargetBlock(null, 6);
						if (blockIsAChest(block)) {
							String output = getPlayerSendString(block);
							String locationString = getLocationstring(block);
							if (plugin.cache.fileNamesContainsString(locationString)) {
								int newCooldown = 0;
								try {
									newCooldown = Integer.parseInt(args[1]);
								} catch (NumberFormatException Ex) {
									player.sendMessage("Eingabe überprüfen! Der eingegebene Wert ist kein Zahlenwert.");
								}
								if (newCooldown > 0) {
									plugin.cache.getLootChestList().get(locationString).setCooldown(newCooldown);
									plugin.cache.getLootChestList().get(locationString).saveToFile();
									player.sendMessage(output + "besitzt nun einen neuen Cooldown von " + newCooldown + " Sekunden.");
								} else
									player.sendMessage("Der eingebene neue Cooldown ist nicht positiv.");
							} else
								player.sendMessage(output + "ist keine Lootchest.");
						} else
							player.sendMessage("Der angeschaute Block an Position " + block.getX() + ", " + block.getY() + ", " + block.getZ()
									+ " ist keine Truhe.");
						return true;
					}// end setCooldown

					/*
					 * Definiere eine neue Truhengr��e f�r die angeschaute Truhe
					 */
					if (args[0].equalsIgnoreCase("setChestsize") || args[0].equalsIgnoreCase("setzeTruhengroeße")) {
						Block block = player.getTargetBlock(null, 6);
						if (blockIsAChest(block)) {
							String output = getPlayerSendString(block);
							String locationString = getLocationstring(block);
							if (plugin.cache.fileNamesContainsString(locationString)) {
								int newchestsize = 0;
								try {
									newchestsize = Integer.parseInt(args[1]);
								} catch (NumberFormatException Ex) {
									player.sendMessage("Eingabe überprüfen! Der eingegebene Wert ist kein Zahlenwert.");
								}
								if (newchestsize == 9 || newchestsize == 18 || newchestsize == 27 || newchestsize == 36 || newchestsize == 45
										|| newchestsize == 54) {
									plugin.cache.getLootChestList().get(locationString).setChestsize(newchestsize);

									plugin.cache.getLootChestList().get(locationString).saveToFile();
									player.sendMessage(output + "besitzt nun eine neue Truhengröße von " + newchestsize + " Plätzen.");
								} else
									player.sendMessage("Truhen können nur 9, 18, 27, 36, 45 oder 54 Plätze beinhalten.");
							} else
								player.sendMessage(output + "ist keine Lootchest.");
						} else
							player.sendMessage("Der angeschaute Block an Position " + block.getX() + ", " + block.getY() + ", " + block.getZ()
									+ " ist keine Truhe.");
						return true;
					}// end setChestsize

					/*
					 * Definiere einen neuen Truhennamen
					 */
					if (args[0].equalsIgnoreCase("setChestname") || args[0].equalsIgnoreCase("setzeTruhenname")) {
						Block block = player.getTargetBlock(null, 6);
						if (blockIsAChest(block)) {
							String output = getPlayerSendString(block);
							String locationString = getLocationstring(block);
							if (plugin.cache.fileNamesContainsString(locationString)) {
								plugin.cache.getLootChestList().get(locationString).setChestName(args[1]);
								;
								plugin.cache.getLootChestList().get(locationString).saveToFile();
								player.sendMessage(output + "besitzt nun den neuen Namen " + args[1] + ".");
							} else
								player.sendMessage(output + "ist keine Lootchest.");

						} else
							player.sendMessage("Der angeschaute Block an Position " + block.getX() + ", " + block.getY() + ", " + block.getZ()
									+ " ist keine Truhe.");
						return true;
					}// end setChestname

					/*
					 * erstellt aus angeschauter Kiste eine Lootchest mit dem
					 * angegebenen Namen; falls bereits vorhanden, wird eine
					 * Fehlermeldung ausgegeben
					 */
					if (args[0].equalsIgnoreCase("create") || args[0].equalsIgnoreCase("erstellen")) {
						Block block = player.getTargetBlock(null, 6);
						if (blockIsAChest(block)) {
							String output = getPlayerSendString(block);
							String locationString = getLocationstring(block);
							if (!plugin.cache.fileNamesContainsString(locationString)) {
								LootChestTemplate lct = new LootChestTemplate(plugin, locationString);
								lct.setChestName(args[1]);
								lct.saveToFile();
								plugin.cache.getFileNames().add(locationString);
								plugin.cache.getLootChestList().put(locationString, lct);

								player.sendMessage(output + "ist nun eine LootChest.");

							} else
								player.sendMessage(output + "ist bereits eine LootChest.");

							return true;
						} else
							player.sendMessage("Der angeschaute Block an Position " + block.getX() + ", " + block.getY() + ", " + block.getZ()
									+ " ist keine Truhe.");
						return true;
					}// end create
				} else if (args.length == 4) {

					if (args[0].equalsIgnoreCase("übernehmeWürfe") || args[0].equalsIgnoreCase("adoptLoot")) {
						Block block = player.getTargetBlock(null, 6);
						if (blockIsAChest(block)) {
							String output = getPlayerSendString(block);
							String locationString = getLocationstring(block);
							if (plugin.cache.fileNamesContainsString(locationString)) {
								String inputLocationstring = player.getWorld().getName() + "." + args[1] + "_" + args[2] + "_" + args[3];
								if (plugin.cache.fileNamesContainsString(inputLocationstring)) {
									player.sendMessage(output + "wurde alle Würfe hinzugefügt, die die Truhe an Position " + args[1] + ", " + args[2] + ", "
											+ args[3] + " besitzt.");

									if (!plugin.cache.getLootChestList().get(inputLocationstring).getEinzelWurf().isEmpty()) {
										for (Wurf w : plugin.cache.getLootChestList().get(inputLocationstring).getEinzelWurf())
											plugin.cache.getLootChestList().get(locationString).getEinzelWurf().add(w);
									}
									if (!plugin.cache.getLootChestList().get(inputLocationstring).getVsWurf().isEmpty()) {
										for (VsWurf w : plugin.cache.getLootChestList().get(inputLocationstring).getVsWurf())
											plugin.cache.getLootChestList().get(locationString).getVsWurf().add(w);
									}
									plugin.cache.getLootChestList().get(locationString).saveToFile();

								} else
									player.sendMessage("Die eingegebenen Koordinaten gehören zu keiner Lootchesttruhe.");
							} else
								player.sendMessage(output + "ist keine LootChest.");
						} else
							player.sendMessage("Der angeschaute Block an Position " + block.getX() + ", " + block.getY() + ", " + block.getZ()
									+ " ist keine Truhe.");
					}
					/*
					 * F�ge der angeschauten Truhe einen Einzelwurf hinzu
					 */
					else if (args[0].equalsIgnoreCase("addloot") || args[0].equalsIgnoreCase("wurfhinzufuegen") || args[0].equalsIgnoreCase("wurf")) {
						Block block = player.getTargetBlock(null, 6);
						if (blockIsAChest(block)) {
							String locationString = getLocationstring(block);
							String output = getPlayerSendString(block);
							int id, anzahl, wahrscheinlichkeit;
							short subid = -1;
							try {
								if (args[1].contains(":")) {
									String idargs = args[1].substring(0, args[1].indexOf(":"));
									String subIDargs = args[1].substring(args[1].indexOf(":") + 1);
									id = Integer.parseInt(idargs);
									subid = Short.parseShort(subIDargs);
								} else
									id = Integer.parseInt(args[1]);
								anzahl = Integer.parseInt(args[2]);
								wahrscheinlichkeit = Integer.parseInt(args[3]);
							} catch (NumberFormatException Ex) {
								player.sendMessage("Eingabe prüfen ! Ein Wert ist kein Zahlenwert (ID/Anzahl/Wahrscheinlichkeit)");
								return true;
							}
							if (plugin.cache.fileNamesContainsString(locationString) && Material.getMaterial(id) != null) {
								LootChestTemplate lct = plugin.cache.getLootChestList().get(locationString);
								if (subid == -1)
									lct.getEinzelWurf().add(new Wurf(new ItemStack(id, anzahl), wahrscheinlichkeit));
								else
									lct.getEinzelWurf().add(new Wurf(new ItemStack(id, anzahl, subid), wahrscheinlichkeit));
								lct.saveToFile();
								player.sendMessage(output + "wurde ein neuer Einzelwurf hinzugefügt.");
								if (subid == -1)
									player.sendMessage("Itemstack: ID: " + id + " | Anzahl: " + anzahl + " | Wahrscheinlichkeit: " + wahrscheinlichkeit);
								else
									player.sendMessage("Itemstack: ID: " + id + ":" + subid + " | Anzahl: " + anzahl + " | Wahrscheinlichkeit: "
											+ wahrscheinlichkeit);
							}
						} else
							player.sendMessage("Der angeschaute Block an Position " + block.getX() + ", " + block.getY() + ", " + block.getZ()
									+ " ist keine Truhe.");
						return true;

					}// end addwurf
				} else if (args.length == 5) {
					/*
					 * F�ge der angeschauten Truhe einen Einzelwurf mit
					 * entsprechendem Displaynamen als 5.String hinzu
					 */
					if (args[0].equalsIgnoreCase("addloot") || args[0].equalsIgnoreCase("wurfhinzufuegen") || args[0].equalsIgnoreCase("wurf")) {
						Block block = player.getTargetBlock(null, 6);
						if (blockIsAChest(block)) {
							String output = getPlayerSendString(block);
							String locationString = getLocationstring(block);

							int id, anzahl, wahrscheinlichkeit;
							short subid = -1;
							try {
								if (args[1].contains(":")) {
									String idargs = args[1].substring(0, args[1].indexOf(":"));
									String subIDargs = args[1].substring(args[1].indexOf(":") + 1);
									id = Integer.parseInt(idargs);
									subid = Short.parseShort(subIDargs);
								} else
									id = Integer.parseInt(args[1]);
								anzahl = Integer.parseInt(args[2]);
								wahrscheinlichkeit = Integer.parseInt(args[3]);
							} catch (NumberFormatException Ex) {
								player.sendMessage("Eingabe prüfen ! Ein Wert ist kein Zahlenwert (ID/Anzahl/Wahrscheinlichkeit)");
								return true;
							}
							if (plugin.cache.fileNamesContainsString(locationString) && Material.getMaterial(id) != null) {
								LootChestTemplate lct = plugin.cache.getLootChestList().get(locationString);
								ItemStack is;
								if (subid == -1)
									is = new ItemStack(id, anzahl);
								else
									is = new ItemStack(id, anzahl, subid);
								ItemMeta im = is.getItemMeta();
								im.setDisplayName(args[4]);
								is.setItemMeta(im);
								lct.getEinzelWurf().add(new Wurf(is, wahrscheinlichkeit));
								lct.saveToFile();
								player.sendMessage(output + "wurde ein neuer Einzelwurf hinzugefügt.");
								if (subid == -1)
									player.sendMessage("Itemstack: ID: " + id + " | Anzahl: " + anzahl + " | Wahrscheinlichkeit: " + wahrscheinlichkeit);
								else
									player.sendMessage("Itemstack: ID: " + id + ":" + subid + " | Anzahl: " + anzahl + " | Wahrscheinlichkeit: "
											+ wahrscheinlichkeit);
							}
						} else
							player.sendMessage("Der angeschaute Block an Position " + block.getX() + ", " + block.getY() + ", " + block.getZ()
									+ " ist keine Truhe.");
						return true;

					}// end addwurf

				} else if (args.length == 8) {
					/*
					 * F�ge der angeschauten Truhe einen VsWurf hinzu
					 */
					if (args[0].equalsIgnoreCase("addvsloot") || args[0].equalsIgnoreCase("vswurfhinzufuegen") || args[0].equalsIgnoreCase("vswurf")) {
						Block block = player.getTargetBlock(null, 6);
						if (blockIsAChest(block)) {
							String output = getPlayerSendString(block);
							String locationString = getLocationstring(block);
							Wurf wurf1, wurf2;
							int wahrscheinlichkeit, id, id2;
							id = id2 = wahrscheinlichkeit = 0;
							short subid, subid2;
							subid = subid2 = 0;

							if (!args[2].contains(":")) {
								id = safeParseToInt(args[2]);
								if (id == Integer.MAX_VALUE) {
									player.sendMessage("ID des ersten Inputs konnte nicht geparst werden. ");
									return true;
								}
							} else {
								String idargs = args[2].substring(0, args[2].indexOf(":"));
								String subidargs = args[2].substring(args[2].indexOf(":") + 1);
								id = safeParseToInt(idargs);
								subid = safeParseToShort(subidargs);
								if (id == Integer.MAX_VALUE) {
									player.sendMessage("ID des ersten Inputs konnte nicht geparst werden");
									return true;
								}
								subid = safeParseToShort(subidargs);
								if (subid == Short.MAX_VALUE) {
									player.sendMessage("SubID des ersten Inputs konnte nicht geparst werden");
									return true;
								}
							}
							if (!args[5].contains(":")) {
								id2 = safeParseToInt(args[5]);
								if (id2 == Integer.MAX_VALUE) {
									player.sendMessage("ID des zweiten Inputs konnte nicht geparst werden. ");
									return true;
								}
							} else {
								String idargs = args[5].substring(0, args[5].indexOf(":"));
								String subidargs = args[5].substring(args[5].indexOf(":") + 1);
								id2 = safeParseToInt(idargs);
								subid2 = safeParseToShort(subidargs);
								if (id2 == Integer.MAX_VALUE) {
									player.sendMessage("ID des zweiten Inputs konnte nicht geparst werden");
									return true;
								}
								if (subid2 == Short.MAX_VALUE) {
									player.sendMessage("SubID des zweiten Inputs konnte nicht geparst werden");
									return true;
								}
							}

							wahrscheinlichkeit = safeParseToInt(args[1]);
							if (wahrscheinlichkeit == Integer.MAX_VALUE) {
								player.sendMessage("Die Wahrscheinlichkeit konnte nicht geparst werden.");
								return true;
							}
							int anzahl1, anzahl2;
							anzahl1 = anzahl2 = 0;
							int wahrscheinlichkeit1, wahrscheinlichkeit2;
							wahrscheinlichkeit1 = wahrscheinlichkeit2 = 0;
							anzahl1 = safeParseToInt(args[3]);
							if (anzahl1 == Integer.MAX_VALUE) {
								player.sendMessage("Die Anzahl des ersten Inputs konnte nicht geparst werden.");
								return true;
							}
							wahrscheinlichkeit1 = safeParseToInt(args[4]);
							if (wahrscheinlichkeit1 == Integer.MAX_VALUE) {
								player.sendMessage("Die Wahrscheinlichkeit des ersten Inputs konnte nicht geparst werden.");
								return true;
							}
							anzahl2 = safeParseToInt(args[6]);
							if (anzahl2 == Integer.MAX_VALUE) {
								player.sendMessage("Die Anzahl des zweiten Inputs konnte nicht geparst werden.");
								return true;
							}
							wahrscheinlichkeit2 = safeParseToInt(args[7]);
							if (wahrscheinlichkeit2 == Integer.MAX_VALUE) {
								player.sendMessage("Die Wahrscheinlichkeit des zweiten Inputs konnte nicht geparst werden.");
								return true;
							}

							if (subid == Integer.MAX_VALUE)
								wurf1 = new Wurf(new ItemStack(id, anzahl1), wahrscheinlichkeit1);
							else
								wurf1 = new Wurf(new ItemStack(id, anzahl1, subid), wahrscheinlichkeit1);
							if (subid == Integer.MAX_VALUE)
								wurf2 = new Wurf(new ItemStack(id2, anzahl2), wahrscheinlichkeit2);
							else
								wurf2 = new Wurf(new ItemStack(id2, anzahl2, subid2), wahrscheinlichkeit2);

							if (plugin.cache.fileNamesContainsString(locationString) && Material.getMaterial(id) != null && Material.getMaterial(id2) != null) {
								LootChestTemplate lct = plugin.cache.getLootChestList().get(locationString);
								VsWurf vs = new VsWurf(wahrscheinlichkeit);
								vs.addWurfToList(wurf1);
								vs.addWurfToList(wurf2);
								lct.getVsWurf().add(vs);
								lct.saveToFile();
								player.sendMessage(output + "wurde ein neuer VsWurf mit einer Wahrscheinlichkeit " + wahrscheinlichkeit + " % hinzugef�gt.");
								if (subid == 0)
									player.sendMessage("Itemstack: ID: " + id + " | Anzahl: " + anzahl1 + " | Wahrscheinlichkeit: " + wahrscheinlichkeit1);
								else
									player.sendMessage("Itemstack: ID: " + id + ":" + subid + " | Anzahl: " + anzahl1 + " | Wahrscheinlichkeit: "
											+ wahrscheinlichkeit1);
								if (subid2 == 0)
									player.sendMessage("Itemstack: ID: " + id2 + " | Anzahl: " + anzahl2 + " | Wahrscheinlichkeit: " + wahrscheinlichkeit2);
								else
									player.sendMessage("Itemstack: ID: " + id2 + ":" + subid2 + " | Anzahl: " + anzahl2 + " | Wahrscheinlichkeit: "
											+ wahrscheinlichkeit2);

							}
						} else
							player.sendMessage("Der angeschaute Block an Position " + block.getX() + ", " + block.getY() + ", " + block.getZ()
									+ " ist keine Truhe.");
						return true;

					}// end addvswurf
				} else
					player.sendMessage("Eingegebener Befehl besitzt keine bekannte Befehlsstruktur.");
				return true;
			}
			player.sendMessage("Du besitzt keine Berechtigungen für diesen Befehl!");
			return true;
		} else {
			// Konsolenbefehle, falls ben�tigt
		}
		return false;

	}

	public boolean blockIsAChest(Block block) {
		if (block.getType() == Material.CHEST || block.getType() == Material.DISPENSER || block.getType() == Material.FURNACE
				|| block.getType() == Material.BURNING_FURNACE)
			return true;
		return false;
	}

	public String getLocationstring(Block block) {
		String xyz = block.getX() + "_" + block.getY() + "_" + block.getZ();
		if (block.getRelative(1, 0, 0).getType() == Material.CHEST) {
			xyz = (((double) block.getX()) + 0.5) + "_" + block.getY() + "_" + block.getZ();
		}
		if (block.getRelative(-1, 0, 0).getType() == Material.CHEST) {
			xyz = (((double) block.getX()) - 0.5) + "_" + block.getY() + "_" + block.getZ();
		}
		if (block.getRelative(0, 0, 1).getType() == Material.CHEST) {
			xyz = block.getX() + "_" + block.getY() + "_" + (((double) block.getZ()) + 0.5);
		}
		if (block.getRelative(0, 0, -1).getType() == Material.CHEST) {
			xyz = block.getX() + "_" + block.getY() + "_" + (((double) block.getZ()) - 0.5);
		}

		String world = block.getWorld().getName();
		return world + "." + xyz;
	}

	public boolean blockIsDoublechest(Block block) {
		boolean doubleChest = false;
		if (block.getRelative(1, 0, 0).getType() == Material.CHEST) {
			doubleChest = true;
		}
		if (block.getRelative(-1, 0, 0).getType() == Material.CHEST) {
			doubleChest = true;
		}
		if (block.getRelative(0, 0, 1).getType() == Material.CHEST) {
			doubleChest = true;
		}
		if (block.getRelative(0, 0, -1).getType() == Material.CHEST) {
			doubleChest = true;
		}
		return doubleChest;

	}

	public String getPlayerSendString(Block block) {
		if (blockIsDoublechest(block)) {
			if (block.getRelative(1, 0, 0).getType() == Material.CHEST) {
				return "Die angeschaute Doppeltruhe an Position " + (block.getX() + 0.5) + ", " + block.getY() + ", " + block.getZ() + " ";
			}
			if (block.getRelative(-1, 0, 0).getType() == Material.CHEST) {
				return "Die angeschaute Doppeltruhe an Position " + (block.getX() - 0.5) + ", " + block.getY() + ", " + block.getZ() + " ";
			}
			if (block.getRelative(0, 0, 1).getType() == Material.CHEST) {
				return "Die angeschaute Doppeltruhe an Position " + block.getX() + ", " + block.getY() + ", " + (block.getZ() + 0.5) + " ";
			} else {
				return "Die angeschaute Doppeltruhe an Position " + block.getX() + ", " + block.getY() + ", " + (block.getZ() - 0.5) + " ";
			}
		} else
			return "Die angeschaute Truhe an Position " + block.getX() + ", " + block.getY() + ", " + block.getZ() + " ";
	}

	public int safeParseToInt(String s) {
		int i = Integer.MAX_VALUE;
		try {
			i = Integer.parseInt(s);
			return i;
		} catch (NumberFormatException e) {
			return i;
		}
	}

	public short safeParseToShort(String s) {
		short i = Short.MAX_VALUE;
		try {
			i = Short.parseShort(s);
			return i;
		} catch (NumberFormatException e) {
			return i;
		}
	}

	private boolean isAdmin(UUID uuid) {
		for (String s : plugin.lcConfig.getAdmins()) {
			if (uuid.equals(UUID.fromString(s)))
				return true;
		}
		return false;
	}
}
