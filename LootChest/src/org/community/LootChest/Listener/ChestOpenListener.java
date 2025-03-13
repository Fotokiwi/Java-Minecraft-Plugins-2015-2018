package org.community.LootChest.Listener;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.block.Dispenser;
import org.bukkit.block.Furnace;
import org.bukkit.Location;
import org.bukkit.block.Chest;
import org.bukkit.block.DoubleChest;
import org.bukkit.craftbukkit.v1_7_R3.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.community.LootChest.LootChest;
import org.community.LootChest.ChestData.LootChestTemplate;
import org.community.LootChest.ChestData.VsWurf;
import org.community.LootChest.ChestData.Wurf;
import org.community.LootChest.UserData.ChestUserData;
import org.community.LootChest.UserData.Userdata;

public class ChestOpenListener implements Listener {

	private LootChest plugin;

	public ChestOpenListener(LootChest plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onInventoryOpenEvent(InventoryOpenEvent e) {
		if (e.getInventory().getHolder() instanceof Chest || e.getInventory().getHolder() instanceof DoubleChest
				|| e.getInventory().getHolder() instanceof Dispenser || e.getInventory() instanceof Furnace) {
			String locationString;
			if (e.getInventory().getHolder() instanceof Chest) {
				Chest c = (Chest) e.getInventory().getHolder();
				Location loc = c.getLocation();
				locationString = (loc.getWorld().getName() + "." + loc.getBlockX() + "_" + loc.getBlockY() + "_" + loc.getBlockZ());
			} else if (e.getInventory().getHolder() instanceof Dispenser) {
				Dispenser c = (Dispenser) e.getInventory().getHolder();
				Location loc = c.getLocation();
				locationString = (loc.getWorld().getName() + "." + loc.getBlockX() + "_" + loc.getBlockY() + "_" + loc.getBlockZ());
			} else if (e.getInventory().getHolder() instanceof Furnace) {
				Furnace c = (Furnace) e.getInventory().getHolder();
				Location loc = c.getLocation();
				locationString = (loc.getWorld().getName() + "." + loc.getBlockX() + "_" + loc.getBlockY() + "_" + loc.getBlockZ());
			} else {
				DoubleChest c = (DoubleChest) e.getInventory().getHolder();
				Location loc = c.getLocation();
				if ((loc.getX() - loc.getBlockX()) != 0)
					locationString = (loc.getWorld().getName() + "." + loc.getX() + "_" + loc.getBlockY() + "_" + loc.getBlockZ());
				else
					locationString = (loc.getWorld().getName() + "." + loc.getBlockX() + "_" + loc.getBlockY() + "_" + loc.getZ());
			}

			Player player = (Player) e.getPlayer();
			if (plugin.cache.fileNamesContainsString(locationString)) {
				if (plugin.cache.getLootChestList().get(locationString).isEnabled()) {
					Userdata ud;
					if (!plugin.cache.getUserDataChests().containsKey(player.getUniqueId())) {
						ud = new Userdata(plugin, player.getUniqueId());
						plugin.cache.getUserDataChests().put(player.getUniqueId(), ud);
					} else{
						ud = plugin.cache.getUserDataChests().get(player.getUniqueId());
					}
					if (!ud.getUserDataValues().containsKey(locationString)) {
						ChestUserData cud;
						cud = new ChestUserData(System.currentTimeMillis());
						Inventory inv = Bukkit.createInventory(null, plugin.cache.getLootChestList().get(locationString).getChestsize(), plugin.cache
								.getLootChestList().get(locationString).getChestName());
						ModifyInventory(inv, plugin.cache.getLootChestList().get(locationString), player);
						cud.setInventory(inv);
						player.openInventory(inv);
						//TriggerCommands(player, plugin.cache.getLootChestList().get(locationString).getCommands());

						ud.getUserDataValues().put(locationString, cud);
					} else {	
						ChestUserData cud = ud.getUserDataValues().get(locationString);
						if ((cud.getLastUse() + (plugin.cache.getLootChestList().get(locationString).getCooldown() * 1000)) < System.currentTimeMillis()) { // Cooldown	
							Inventory inv = Bukkit.createInventory(null, plugin.cache.getLootChestList().get(locationString).getChestsize(), plugin.cache
									.getLootChestList().get(locationString).getChestName());
							ModifyInventory(inv, plugin.cache.getLootChestList().get(locationString), player);
							cud.setInventory(inv);
							player.openInventory(inv);
							cud.setLastUse(System.currentTimeMillis());
							//TriggerCommands(player, plugin.cache.getLootChestList().get(locationString).getCommands());

						} else { // Cooldown noch aktiv

							player.openInventory(cud.getInventory());
						}
					}

					ud.SaveToFile();
					e.setCancelled(true);
				}

			} // Truhe ist keine Lootchest
		} // keine Truhen�ffnung
	}

	public void ModifyInventory(Inventory inv, LootChestTemplate lct, Player player) {
		Random randomGenerator = new Random();

		for (VsWurf vswurf : lct.getVsWurf()) {
			
			if(!vswurf.hashMatchJobs(plugin.fwc.getUserClass(player).getJobHash()))
				continue;
			
			int r = randomGenerator.nextInt(101);

			if (r <= vswurf.getProbability()) {

				int r2 = randomGenerator.nextInt(101);
				int probability = 0;

				for (Wurf wurf : vswurf.getWuerfe()) {

					probability = probability + wurf.getProbability();

					if (r2 <= probability) {
						for (ItemStack is : wurf.getItemstack())
							inv.addItem(is);
						break;
					}

				}

			}
		}
		for (Wurf wurf : lct.getEinzelWurf()) {
			if(!wurf.hashMatchJobs(plugin.fwc.getUserClass(player).getJobHash()))
				continue;
			int r = randomGenerator.nextInt(101);
			if (r <= wurf.getProbability()) {
				for (ItemStack is : wurf.getItemstack())
					inv.addItem(is);
			}
		}
	}

	public void TriggerCommands(Player player, String s) {
		if (s == null)
			return;
		String[] commandList = s.split(";");
		CraftServer cs = (CraftServer) plugin.getServer();
		String command = "";

		for (int i = 0; i < commandList.length; i++) {
			String[] com = commandList[i].split(" ");
			for (String arg : com) {
				if (command.length() > 0) {
					if ("*".equals(arg))
						arg = player.getName();
					command += " " + arg;
				} else {
					command += arg.substring(1);
				}
			}
			plugin.LogError("executing Console-Command '" + command + "'");
			if (!cs.dispatchCommand(cs.getServer().console, command)) {
				plugin.LogError("Could not execute Console-Command '" + command + "'");
				return;
			}
			command = "";
		}

	}

}