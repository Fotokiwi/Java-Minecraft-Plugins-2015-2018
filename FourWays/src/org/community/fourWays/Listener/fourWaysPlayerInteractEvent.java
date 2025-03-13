package org.community.fourWays.Listener;

import java.util.Random;
import java.util.Set;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.community.fourWays.fourWays;
import org.community.fourWays.User.User;

public class fourWaysPlayerInteractEvent implements Listener {

	private final fourWays plugin;

	public fourWaysPlayerInteractEvent(fourWays plugin) {
		this.plugin = plugin;
	}

	@SuppressWarnings({ "deprecation" })
	@EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
	public void onPlayerInteract(PlayerInteractEvent event) {

		if (plugin.adminMode.get(event.getPlayer()) == null) {

		} else if (plugin.adminMode.get(event.getPlayer()) == false) {

		} else {
			return;
		}

		Player player = event.getPlayer();
		User user = plugin.fWUsers.getPlayerInfo(player);
		ItemStack item = player.getInventory().getItemInHand();

		if (item != null) {
			if (item.getType().equals(Material.BOAT)) {

				// change here for more start health for boats
				float durability = 150.0f;
				if (item.hasItemMeta()) {
					if (item.getItemMeta().hasLore() && item.getItemMeta().getLore().get(0).equals("Haltbarkeit"))
						durability = Float.parseFloat(item.getItemMeta().getLore().get(1));
				}

				Entity entity = event.getPlayer().getWorld()
						.spawnEntity(event.getClickedBlock().getRelative(0, 1, 0).getLocation(), EntityType.BOAT);

				plugin.fWCore.bootCounter.put(entity.getUniqueId(), durability);
				player.getInventory().setItemInHand(null);

				event.setCancelled(true);
				return;
			}

			String itemID = item.getType().toString();
			String itemString = "";
			if (plugin.block.getBoolean("Block." + itemID + ".Nutzung.Ignoriere-Haltbarkeit") == true) {
				itemString = itemID.replace("'", "");
			} else {
				String itemdamage = plugin.fWItems.getBlockType(null, item);
				if (itemdamage == "") {
					itemString = itemID.replace("'", "");
				} else {
					itemString = itemID.replace("'", "") + "-" + itemdamage.replace("'", "");
				}
			}

			PlayerInventory inventory = player.getInventory();

			ConfigurationSection blockSection = null;
			blockSection = plugin.block.getConfigurationSection("Block." + itemString + ".Nutzung.Beruf");

			if (blockSection == null) {
				if (itemString.equalsIgnoreCase("INK_SACK-WHITE")) {
					if (bonemealBiomeCheck(event.getPlayer(), event.getClickedBlock()) == false) {
						event.setCancelled(true);
					}
					return;
				}
				/*if (itemString.equalsIgnoreCase("WHEAT") || itemString.equalsIgnoreCase("SEEDS")
						|| itemString.equalsIgnoreCase("CARROT") || itemString.equalsIgnoreCase("GOLDEN_APPLE")
						|| itemString.equalsIgnoreCase("GOLDEN_CARROT")) {
					if (breedCheck(event.getPlayer(), itemString) == false) {
						event.setCancelled(true);
					}
					return;
				}*/
				return;
			}

			Set<String> blockKeys = blockSection.getKeys(false);
			String[] blockArray = blockKeys.toArray(new String[0]);

			int blockLevel;

			String[] blockInfo = new String[2];
			blockInfo = user.getJobHash().split(",");
			String playerClass = blockInfo[1];
			int playerLevel = new Integer(blockInfo[0]);

			for (int i = 0; i < blockArray.length; i++) {
				blockLevel = plugin.block.getInt("Block." + itemString + ".Nutzung.Beruf." + blockArray[i], 0);
				if (playerClass.contains(blockArray[i]) && playerLevel >= blockLevel) {
					if (itemString.equalsIgnoreCase("INK_SACK-WHITE")) {
						if (bonemealBiomeCheck(event.getPlayer(), event.getClickedBlock()) == false) {
							event.setCancelled(true);
						}
						return;
					}
					return;
				}
			}
			if (inventory.firstEmpty() == -1) {
				inventory.clear(player.getInventory().getHeldItemSlot());
				player.getWorld().dropItem(player.getLocation().add(0, 1, 0), item);
				player.sendMessage(ChatColor.GOLD + "Du darfst dieses Item nicht nutzen.");
				player.sendMessage(ChatColor.GOLD + "Dein Inventar ist voll, das Item wurde auf den Boden gelegt.");
				player.updateInventory();
				event.setCancelled(true);
				return;
			} else {
				int newItemSlot = inventory.firstEmpty();
				inventory.setItem(newItemSlot, item);
				inventory.clear(player.getInventory().getHeldItemSlot());
				player.sendMessage(ChatColor.GOLD + "Du darfst dieses Item nicht nutzen.");
				player.updateInventory();
				event.setCancelled(true);
				return;
			}
		} else {
			return;
		}

	}

	private boolean bonemealBiomeCheck(Player player, Block block) {
		Random rnd = new Random();
		int roll = rnd.nextInt(100) + 1;

		String plant = plugin.harvestCache.get(block.getType().toString());

		/*
		 * if(block.getType() == Material.GRASS) { Location location =
		 * block.getLocation(); Block tempBlock = null; World world =
		 * location.getWorld(); Random dice = new Random(); int diceRoll =
		 * dice.nextInt(100 + 1);
		 * if(block.getBiome().name().equalsIgnoreCase("Birch Forest")) {
		 * //Orange Tulip //Red Tulip //Long Grass 1 }
		 * if(block.getBiome().name().equalsIgnoreCase("Desert") ||
		 * block.getBiome().name().equalsIgnoreCase("Desert Hills")) { //Dead
		 * Bush } if(block.getBiome().name().equalsIgnoreCase("Extreme Hills"))
		 * { //Dead Bush //Peony }
		 * if(block.getBiome().name().equalsIgnoreCase("Forest") ||
		 * block.getBiome().name().equalsIgnoreCase("Forest Hills")) {
		 * //Dandelion //Poppy //Long Grass 1 }
		 * if(block.getBiome().name().equalsIgnoreCase("Ice Plains")) { //White
		 * Tulip //Pink Tulip }
		 * if(block.getBiome().name().equalsIgnoreCase("Jungle") ||
		 * block.getBiome().name().equalsIgnoreCase("Jungle Hills")) { //Lilac
		 * //Long Grass 1 } if(block.getBiome().name().equalsIgnoreCase("Mesa"))
		 * { //Oxeye Daisy //Dead Bush }
		 * if(block.getBiome().name().equalsIgnoreCase("Plains")) { Location
		 * loc1 = block.getLocation(); tempBlock =
		 * world.getHighestBlockAt(loc1).getLocation().getBlock(); if(diceRoll
		 * <= 6){ tempBlock.setTypeId(175); tempBlock.setData((byte) 0); }
		 * tempBlock = world.getHighestBlockAt(loc1).getLocation().add(0, 1,
		 * 0).getBlock(); if(diceRoll <= 6){ tempBlock.setTypeId(175);
		 * tempBlock.setData((byte) 10); } //Long Grass 1 }
		 * if(block.getBiome().name().equalsIgnoreCase("Savanna")) { //Oxeye
		 * Daisy //Long Grass }
		 * if(block.getBiome().name().equalsIgnoreCase("Swampland")) { //Long
		 * Grass //Dead Bush }
		 * if(block.getBiome().name().equalsIgnoreCase("Taiga")) { //Long Grass
		 * } return false; }
		 */

		if (plant == null) {
			return true;
		}

		String biomeName = block.getBiome().name().toLowerCase().replace(" ", "_");
		// String[] shortName = biomeName.split("_");
		// if(shortName[0].equalsIgnoreCase("small"))
		// shortName[0] = "extreme";

		if (roll <= plugin.harvest.getInt("Harvest." + plant + ".Biome." + biomeName, 0)) {
			return true;
		} else {
			int itemAmount = player.getItemInHand().getAmount();
			if (itemAmount - 1 <= 0) {
				player.setItemInHand(null);
			} else {
				player.getItemInHand().setAmount(itemAmount - 1);
			}
			return false;
		}
	}

	/*private boolean breedCheck(Player player, String item) {
		User user = plugin.fWUsers.getPlayerInfo(player);

		List<Entity> entities = player.getNearbyEntities(1, 2, 1);

		int blockLevel;
		String blockIdentity = "";

		String[] blockInfo = new String[2];
		blockInfo = user.getJobHash().split(",");
		String playerClass = blockInfo[1];
		int playerLevel = new Integer(blockInfo[0]);

		if (item.equalsIgnoreCase("WHEAT")) {
			for (int i = 0; i < entities.size(); i++) {
				if (entities.get(i).getType() == EntityType.COW) {
					blockIdentity = "COW";
					break;
				}
				if (entities.get(i).getType() == EntityType.SHEEP) {
					blockIdentity = "SHEEP";
					break;
				}
			}
		}
		if (item.equalsIgnoreCase("SEEDS"))
			blockIdentity = "CHICKEN";
		if (item.equalsIgnoreCase("CARROT"))
			blockIdentity = "PIG";
		if (item.equalsIgnoreCase("GOLDEN_APPLE"))
			blockIdentity = "HORSE";
		if (item.equalsIgnoreCase("GOLDEN_CARROT"))
			blockIdentity = "HORSE";

		ConfigurationSection blockSection = plugin.entity.getConfigurationSection("Entity." + blockIdentity
				+ ".Zuechten.Beruf");

		if (blockSection == null)
			return false;

		Set<String> blockKeys = blockSection.getKeys(false);
		String[] blockArray = blockKeys.toArray(new String[0]);

		for (int i = 0; i < blockArray.length; i++) {
			blockLevel = plugin.entity.getInt("Entity." + blockIdentity + ".Zuechten.Beruf." + blockArray[i], 0);
			if (playerClass.contains(blockArray[i]) && playerLevel >= blockLevel) {
				if (plugin.entity.getString("Entity." + blockIdentity + ".Zuechten.Gebaeude") != null) {
					Town town = plugin.newSettlersAPI.nSAPI.getChunkTown(player.getLocation().getChunk());
					if (town == null) {
						// player.sendMessage("Du hast diesen Block nun initialisiert");
						user.addExp(plugin.entity.getInt("Entity." + blockIdentity + ".Zuechten.Exp", 0));
						// plugin.preCache_BlockBreak.put(player,
						// blockIdentity);
						return true;
					} else {
						if (town.getBuildingStatus(plugin.entity.getString("Entity." + blockIdentity
								+ ".Zuechten.Gebaeude"))) {
							player.sendMessage(""
									+ plugin.entity.getString("Entity." + blockIdentity + ".Zuechten.Gebaeude"));
							// player.sendMessage("Du hast diesen Block nun initialisiert");
							user.addExp(plugin.entity.getInt("Entity." + blockIdentity + ".Zuechten.Exp", 0));
							// plugin.preCache_BlockBreak.put(player,
							// blockIdentity);
							return true;
						} else {
							// player.sendMessage("Die Stadt verfügt nicht über das passende Gebäude.");
							// plugin.preCache_BlockBreak.put(player,
							// blockIdentity);
							return false;
						}
					}
				} else {
					// player.sendMessage("Du hast diesen Block nun initialisiert");
					user.addExp(plugin.entity.getInt("Entity." + blockIdentity + ".Zuechten.Exp", 0));
					// plugin.preCache_BlockBreak.put(player, blockIdentity);
					return true;
				}
			}
		}
		return false;
	}*/

}