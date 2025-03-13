package org.community.fourWays.Listener;

import java.util.Random;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockFromToEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Dye;
import org.community.fourWays.fourWays;
import org.community.fourWays.User.User;

public class fourWaysBlockBreakHarvestEvent implements Listener {
	private fourWays plugin;

	public fourWaysBlockBreakHarvestEvent(fourWays plugin) {
		this.plugin = plugin;
	}

	@SuppressWarnings("deprecation")
	public void setItemDurability(Player player, ItemStack item){

		if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
			ItemStack waffe = player.getItemInHand();
			if(waffe.getType() == Material.WOOD_HOE || waffe.getType() == Material.STONE_HOE || waffe.getType() == Material.IRON_HOE || waffe.getType() == Material.GOLD_HOE || waffe.getType() == Material.DIAMOND_HOE){

				int level = 0;
				if(waffe.containsEnchantment(Enchantment.DURABILITY)){
					level = waffe.getEnchantmentLevel(Enchantment.DURABILITY);	
				}
				double random1 = (Math.random()*100);
				int random = (int) random1;
				if(random > (100/(level+1)))
					return;

				player.getItemInHand().setDurability((short) (player.getItemInHand().getDurability() + this.plugin.harvest.getInt("General.Durability.damage", 1)));
				ItemStack nullStack = new ItemStack(Material.AIR, 1);
				if(player.getItemInHand().getType() == Material.WOOD_HOE && player.getItemInHand().getDurability() >= this.plugin.harvest.getInt("Options.Durability.Amount.WOOD_HOE", 66)){
					player.setItemInHand(nullStack);
					player.updateInventory();
					return;
				} else if (player.getItemInHand().getType() == Material.STONE_HOE && player.getItemInHand().getDurability() >= this.plugin.harvest.getInt("Options.Durability.Amount.STONE_HOE", 132)){
					player.setItemInHand(nullStack);
					player.updateInventory();
					return;
				} else if (player.getItemInHand().getType() == Material.IRON_HOE && player.getItemInHand().getDurability() >= this.plugin.harvest.getInt("Options.Durability.Amount.IRON_HOE", 251)){
					player.setItemInHand(nullStack);
					player.updateInventory();
					return;
				} else if (player.getItemInHand().getType() == Material.GOLD_HOE && player.getItemInHand().getDurability() >= this.plugin.harvest.getInt("Options.Durability.Amount.DIAMOND_HOE", 1562)){
					player.setItemInHand(nullStack);
					player.updateInventory();
					return;
				} else if (player.getItemInHand().getType() == Material.DIAMOND_HOE && player.getItemInHand().getDurability() >= this.plugin.harvest.getInt("Options.Durability.Amount.GOLD_HOE", 33)){
					player.setItemInHand(nullStack);
					player.updateInventory();
					return;
				} else{
					return;
				}
			}
		}
	}

	public void rollForBean(Block block, Player player){
		if(!this.plugin.harvest.getBoolean("Harvest.Wheat.CocoaBean.use", false))
			return;
		Random rnd = new Random();
		int roll = rnd.nextInt(100 + 1);
		if(roll <= this.plugin.harvest.getInt("Harvest.Wheat.CocoaBean.percent", 5)){
			ItemStack item = new ItemStack(Material.INK_SACK);
			Dye dye = (Dye) item.getData();
			dye.setColor(DyeColor.BROWN);
			item.setData(dye);
			item.setAmount(this.plugin.harvest.getInt("Harvest.Wheat.CocoaBean.amount", 2));
			block.getWorld().dropItemNaturally(block.getLocation(), item);
			player.sendMessage(ChatColor.GREEN + "Du hast eine Kakaobohne gefunden!");
		}
	}

	public void rollForSeed(Block block, Player player){
		Random rnd = new Random();
		int roll = rnd.nextInt(100 + 1);
		if(roll <= this.plugin.harvest.getInt("Harvest.Wheat.Seed.onFailPercent", 50)){
			block.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(Material.SEEDS, this.plugin.harvest.getInt("Harvest.Wheat.Seed.onFailAmount", 1)));
		}
	}

	public void dropSeed(Block block, Player player){
		int minAmount = this.plugin.harvest.getInt("Harvest.Wheat.Seed.min", 1);
		int maxAmount = this.plugin.harvest.getInt("Harvest.Wheat.Seed.max", 3);
		Random rnd = new Random();
		int roll = rnd.nextInt(maxAmount - minAmount + 1);
		int dropAmount = roll + minAmount;
		if(dropAmount > 0)
			block.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(Material.SEEDS, dropAmount));
	}

	public void checkPlantPhysics(Block blockKey, Material plant){
		if(blockKey.getLocation().add(0,3,0).getBlock().getType() == plant){
			blockKey.getLocation().add(0,3,0).getBlock().setType(Material.AIR);
		}
		if(blockKey.getLocation().add(0,2,0).getBlock().getType() == plant){
			blockKey.getLocation().add(0,2,0).getBlock().setType(Material.AIR);
		}
		if(blockKey.getLocation().add(0,1,0).getBlock().getType() == plant){
			blockKey.getLocation().add(0,1,0).getBlock().setType(Material.AIR);
		}
		blockKey.setType(Material.AIR);
	}

	public void executePlantPhysics(Block blockKey, Boolean delete, Material plant){
		if(plant == Material.DOUBLE_PLANT) {
			if(blockKey.getLocation().add(0,1,0).getBlock().getType() == plant){
				blockKey.getLocation().add(0,1,0).getBlock().setType(Material.AIR);
			}
			if(blockKey.getLocation().add(0,2,0).getBlock().getType() == plant){
				blockKey.getLocation().add(0,2,0).getBlock().setType(Material.AIR);
			}
		} else {
			if(blockKey.getLocation().add(0,2,0).getBlock().getType() == plant){
				blockKey.getLocation().add(0,2,0).getBlock().setType(Material.AIR);
			}
			if(blockKey.getLocation().add(0,1,0).getBlock().getType() == plant){
				blockKey.getLocation().add(0,1,0).getBlock().setType(Material.AIR);
			}
		}
		
		if(delete == true)
			blockKey.setType(Material.AIR);
	}

	public void dropController(Block blockKey, String blockName, Material dropItem){
		int minAmount = this.plugin.harvest.getInt("Harvest." + blockName + ".Amount.min", 1);
		int maxAmount = this.plugin.harvest.getInt("Harvest." + blockName + ".Amount.max", 1);
		Random rnd = new Random();
		int roll = rnd.nextInt(maxAmount - minAmount + 1);
		int dropAmount = roll + minAmount;
		if(dropAmount > 0)
			if(dropItem == Material.INK_SACK) {
				blockKey.getWorld().dropItemNaturally(blockKey.getLocation(), new ItemStack(dropItem, dropAmount, (short) 3));
			} else {
				blockKey.getWorld().dropItemNaturally(blockKey.getLocation(), new ItemStack(dropItem, dropAmount));
			}
	}

	public boolean grapeController(Block blockKey) {
		if(blockKey.getLocation().add(1,0,0).getBlock().getType() != Material.AIR && blockKey.getLocation().add(1,0,0).getBlock().getType() != Material.VINE) {
			return false;
		}
		if(blockKey.getLocation().add(0,0,1).getBlock().getType() != Material.AIR && blockKey.getLocation().add(0,0,1).getBlock().getType() != Material.VINE) {
			return false;
		}
		if(blockKey.getLocation().subtract(1,0,0).getBlock().getType() != Material.AIR && blockKey.getLocation().subtract(1,0,0).getBlock().getType() != Material.VINE) {
			return false;
		}
		if(blockKey.getLocation().subtract(0,0,1).getBlock().getType() != Material.AIR && blockKey.getLocation().subtract(0,0,1).getBlock().getType() != Material.VINE) {
			return false;
		}
		return true;
	}

	@EventHandler(priority = EventPriority.NORMAL)
	public void onBlockFromTo(BlockFromToEvent event) {

		Block blockKey = event.getToBlock();
		Material blockID = blockKey.getType();

		if (blockID != Material.CROPS && blockID != Material.RED_MUSHROOM && blockID != Material.BROWN_MUSHROOM && blockID != Material.VINE && blockID != Material.COCOA && blockID != Material.CARROT && blockID != Material.POTATO && blockID != Material.LONG_GRASS && blockID != Material.DOUBLE_PLANT && blockID != Material.RED_ROSE && blockID != Material.YELLOW_FLOWER && blockID != Material.DEAD_BUSH)
			return;	

		blockKey.setType(Material.AIR);

	}


	@EventHandler(priority = EventPriority.HIGH)
	public void onBlockBreak(BlockBreakEvent event) {

		if (event.isCancelled())
			return;
		if (!(event.getPlayer() instanceof Player))
			return;

		Player player = event.getPlayer();
		Block blockKey = event.getBlock();
		String blockID = blockKey.getType().toString();
		String blockData = plugin.fWItems.getBlockType(event.getBlock(), null);

		ItemStack item = player.getInventory().getItemInHand();
		String usedTool = player.getItemInHand().getType().toString();

		if (this.plugin.harvest.getBoolean("Options.Physicsharvest", true) == false){
			if (blockID.equalsIgnoreCase("GRASS") || blockID.equalsIgnoreCase("DIRT") || blockID.equalsIgnoreCase("SAND") || blockID.equalsIgnoreCase("SOIL")){
				if(blockKey.getLocation().add(0,1,0).getBlock().getType() == Material.CACTUS) {
					event.setCancelled(true);
					checkPlantPhysics(blockKey, Material.CACTUS);
				} else if(blockKey.getLocation().add(0,1,0).getBlock().getType() == Material.SUGAR_CANE_BLOCK) {
					event.setCancelled(true);
					checkPlantPhysics(blockKey, Material.SUGAR_CANE_BLOCK);
				} else if(blockKey.getLocation().add(0,1,0).getBlock().getType() == Material.CROPS) {
					event.setCancelled(true);
					executePlantPhysics(blockKey, false, Material.CROPS);
				} else if(blockKey.getLocation().add(0,1,0).getBlock().getType() == Material.RED_ROSE) {
					event.setCancelled(true);
					executePlantPhysics(blockKey, false, Material.RED_ROSE);
				} else if(blockKey.getLocation().add(0,1,0).getBlock().getType() == Material.YELLOW_FLOWER) {
					event.setCancelled(true);
					executePlantPhysics(blockKey, false, Material.YELLOW_FLOWER);
				} else if(blockKey.getLocation().add(0,1,0).getBlock().getType() == Material.DEAD_BUSH) {
					event.setCancelled(true);
					executePlantPhysics(blockKey, false, Material.DEAD_BUSH);
				} else if(blockKey.getLocation().add(0,1,0).getBlock().getType() == Material.LONG_GRASS) {
					event.setCancelled(true);
					executePlantPhysics(blockKey, false, Material.LONG_GRASS);
				} else if(blockKey.getLocation().add(0,1,0).getBlock().getType() == Material.DOUBLE_PLANT) {
					event.setCancelled(true);
					executePlantPhysics(blockKey, false, Material.DOUBLE_PLANT);
				}
			}
			if (blockID.equalsIgnoreCase("LOG")){
				if(blockKey.getLocation().add(1,0,0).getBlock().getType() == Material.COCOA) {
					blockKey.getLocation().add(1,0,0).getBlock().setType(Material.AIR);
				}
				if(blockKey.getLocation().add(0,0,1).getBlock().getType() == Material.COCOA) {
					blockKey.getLocation().add(0,0,1).getBlock().setType(Material.AIR);
				}
				if(blockKey.getLocation().subtract(1,0,0).getBlock().getType() == Material.COCOA) {
					blockKey.getLocation().subtract(1,0,0).getBlock().setType(Material.AIR);
				}
				if(blockKey.getLocation().subtract(0,0,1).getBlock().getType() == Material.COCOA) {
					blockKey.getLocation().subtract(0,0,1).getBlock().setType(Material.AIR);
				}
			}
		}

		if (!blockID.equalsIgnoreCase("LEAVES") && !blockID.equalsIgnoreCase("LEAVES_2") && !blockID.equalsIgnoreCase("RED_MUSHROOM") && !blockID.equalsIgnoreCase("BROWN_MUSHROOM") && !blockID.equalsIgnoreCase("CROPS") && !blockID.equalsIgnoreCase("SOIL") && !blockID.equalsIgnoreCase("CACTUS") && !blockID.equalsIgnoreCase("SUGAR_CANE_BLOCK") && !blockID.equalsIgnoreCase("PUMPKIN") && !blockID.equalsIgnoreCase("HUGE_MUSHROOM_1") && !blockID.equalsIgnoreCase("HUGE_MUSHROOM_2") && !blockID.equalsIgnoreCase("MELON_BLOCK") && !blockID.equalsIgnoreCase("VINE") && !blockID.equalsIgnoreCase("NETHER_WARTS") && !blockID.equalsIgnoreCase("COCOA") && !blockID.equalsIgnoreCase("CARROT") && !blockID.equalsIgnoreCase("POTATO")){
			return;
		}
		
		Random dice = new Random();
		int diceRoll = dice.nextInt(100 + 1);
		Random rnd = new Random();
		int roll = rnd.nextInt(100 + 1);

		if (blockID.equalsIgnoreCase("LEAVES") || blockID.equalsIgnoreCase("LEAVES_2")){
			if(roll <= this.plugin.harvest.getInt("General.Apple.percent", 5)){
				User user = plugin.fWUsers.getPlayerInfo(player);
				String[] blockInfo = new String[2];
				blockInfo = user.getJobHash().split(",");
				String playerClass = blockInfo[1];
				int playerLevel = new Integer(blockInfo[0]);
				if(playerClass.contains("GA") && playerLevel >= 21) {
					blockKey.getWorld().dropItemNaturally(blockKey.getLocation(), new ItemStack(Material.APPLE, this.plugin.harvest.getInt("General.Apple.amount", 1)));
					player.sendMessage(ChatColor.GREEN + "Du hast einen Apfel gefunden!");
				}
			}
			if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
				setItemDurability(player, item);
			}
			
			if (usedTool.equalsIgnoreCase("WOOD_HOE")){
				if(diceRoll <= this.plugin.harvest.getInt("Harvest.Leaves.Chance.WOOD_HOE", 3)){
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					blockKey.getWorld().dropItemNaturally(blockKey.getLocation(), new ItemStack(Material.SAPLING, 1, (short) this.plugin.fWItems.getTreeSubId(blockKey)));
					blockKey.setType(Material.AIR);
					return;
				}else{
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					blockKey.setType(Material.AIR);
					return;
				}
			} else if(usedTool.equalsIgnoreCase("STONE_HOE")){
				if(diceRoll <= this.plugin.harvest.getInt("Harvest.Leaves.Chance.STONE_HOE", 5)){
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					blockKey.getWorld().dropItemNaturally(blockKey.getLocation(), new ItemStack(Material.SAPLING, 1, (short) this.plugin.fWItems.getTreeSubId(blockKey)));
					blockKey.setType(Material.AIR);
					return;
				}else{
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					blockKey.setType(Material.AIR);
					return;
				}
			} else if(usedTool.equalsIgnoreCase("IRON_HOE")){
				if(diceRoll <= this.plugin.harvest.getInt("Harvest.Leaves.Chance.IRON_HOE", 7)){
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					blockKey.getWorld().dropItemNaturally(blockKey.getLocation(), new ItemStack(Material.SAPLING, 1, (short) this.plugin.fWItems.getTreeSubId(blockKey)));
					blockKey.setType(Material.AIR);
					return;
				}else{
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					blockKey.setType(Material.AIR);
					return;
				}
			} else if(usedTool.equalsIgnoreCase("GOLD_HOE")){
				if(diceRoll <= this.plugin.harvest.getInt("Harvest.Leaves.Chance.DIAMOND_HOE", 9)){
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					blockKey.getWorld().dropItemNaturally(blockKey.getLocation(), new ItemStack(Material.SAPLING, 1, (short) this.plugin.fWItems.getTreeSubId(blockKey)));
					blockKey.setType(Material.AIR);
					return;
				}else{
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					blockKey.setType(Material.AIR);
					return;
				}
			} else if(usedTool.equalsIgnoreCase("DIAMOND_HOE")){
				if(diceRoll <= this.plugin.harvest.getInt("Harvest.Leaves.Chance.GOLD_HOE", 10)){
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					blockKey.getWorld().dropItemNaturally(blockKey.getLocation(), new ItemStack(Material.SAPLING, 1, (short) this.plugin.fWItems.getTreeSubId(blockKey)));
					blockKey.setType(Material.AIR);
					return;
				}else{
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					blockKey.setType(Material.AIR);
					return;
				}
			} else {
				if(diceRoll <= this.plugin.harvest.getInt("Harvest.Leaves.Chance.OTHER", 10)){
					blockKey.getWorld().dropItemNaturally(blockKey.getLocation(), new ItemStack(Material.SAPLING, 1, (short) this.plugin.fWItems.getTreeSubId(blockKey)));
					blockKey.setType(Material.AIR);
					return;
				}else{
					blockKey.setType(Material.AIR);
					return;
				}
			}
		} else if (blockID.equalsIgnoreCase("RED_MUSHROOM") && this.plugin.harvest.getBoolean("Options.Control.Mushroom", false) == true){
			if (usedTool.equalsIgnoreCase("WOOD_HOE")){
				if(diceRoll <= this.plugin.harvest.getInt("Harvest.Mushroom.Chance.WOOD_HOE", 30)){
					blockKey.setType(Material.AIR);
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					dropController(blockKey, "Mushroom", Material.RED_MUSHROOM);
					return;
				}else{
					blockKey.setType(Material.AIR);
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					return;
				}
			} else if(usedTool.equalsIgnoreCase("STONE_HOE")){
				if(diceRoll <= this.plugin.harvest.getInt("Harvest.Mushroom.Chance.STONE_HOE", 50)){
					blockKey.setType(Material.AIR);
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					dropController(blockKey, "Mushroom", Material.RED_MUSHROOM);
					return;
				}else{
					blockKey.setType(Material.AIR);
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					return;
				}
			} else if(usedTool.equalsIgnoreCase("IRON_HOE")){
				if(diceRoll <= this.plugin.harvest.getInt("Harvest.Mushroom.Chance.IRON_HOE", 70)){
					blockKey.setType(Material.AIR);
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					dropController(blockKey, "Mushroom", Material.RED_MUSHROOM);
					return;
				}else{
					blockKey.setType(Material.AIR);
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					return;
				}
			} else if(usedTool.equalsIgnoreCase("GOLD_HOE")){
				if(diceRoll <= this.plugin.harvest.getInt("Harvest.Mushroom.Chance.DIAMOND_HOE", 90)){
					blockKey.setType(Material.AIR);
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					dropController(blockKey, "Mushroom", Material.RED_MUSHROOM);
					return;
				}else{
					blockKey.setType(Material.AIR);
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					return;
				}
			} else if(usedTool.equalsIgnoreCase("DIAMOND_HOE")){
				if(diceRoll <= this.plugin.harvest.getInt("Harvest.Mushroom.Chance.GOLD_HOE", 100)){
					blockKey.setType(Material.AIR);
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					dropController(blockKey, "Mushroom", Material.RED_MUSHROOM);
					return;
				}else{
					blockKey.setType(Material.AIR);
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					return;
				}
			} else {
				if(diceRoll <= this.plugin.harvest.getInt("Harvest.Mushroom.Chance.OTHER", 10)){
					blockKey.setType(Material.AIR);
					dropController(blockKey, "Mushroom", Material.RED_MUSHROOM);
					return;
				}else{
					blockKey.setType(Material.AIR);
					return;
				}
			}
		} else if (blockID.equalsIgnoreCase("BROWN_MUSHROOM") && this.plugin.harvest.getBoolean("Options.Control.Mushroom", false) == true){
			if (usedTool.equalsIgnoreCase("WOOD_HOE")){
				if(diceRoll <= this.plugin.harvest.getInt("Harvest.Mushroom.Chance.WOOD_HOE", 30)){
					blockKey.setType(Material.AIR);
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					dropController(blockKey, "Mushroom", Material.BROWN_MUSHROOM);
					return;
				}else{
					blockKey.setType(Material.AIR);
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					return;
				}
			} else if(usedTool.equalsIgnoreCase("STONE_HOE")){
				if(diceRoll <= this.plugin.harvest.getInt("Harvest.Mushroom.Chance.STONE_HOE", 50)){
					blockKey.setType(Material.AIR);
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					dropController(blockKey, "Mushroom", Material.BROWN_MUSHROOM);
					return;
				}else{
					blockKey.setType(Material.AIR);
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					return;
				}
			} else if(usedTool.equalsIgnoreCase("IRON_HOE")){
				if(diceRoll <= this.plugin.harvest.getInt("Harvest.Mushroom.Chance.IRON_HOE", 70)){
					blockKey.setType(Material.AIR);
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					dropController(blockKey, "Mushroom", Material.BROWN_MUSHROOM);
					return;
				}else{
					blockKey.setType(Material.AIR);
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					return;
				}
			} else if(usedTool.equalsIgnoreCase("GOLD_HOE")){
				if(diceRoll <= this.plugin.harvest.getInt("Harvest.Mushroom.Chance.DIAMOND_HOE", 90)){
					blockKey.setType(Material.AIR);
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					dropController(blockKey, "Mushroom", Material.BROWN_MUSHROOM);
					return;
				}else{
					blockKey.setType(Material.AIR);
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					return;
				}
			} else if(usedTool.equalsIgnoreCase("DIAMOND_HOE")){
				if(diceRoll <= this.plugin.harvest.getInt("Harvest.Mushroom.Chance.GOLD_HOE", 100)){
					blockKey.setType(Material.AIR);
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					dropController(blockKey, "Mushroom", Material.BROWN_MUSHROOM);
					return;
				}else{
					blockKey.setType(Material.AIR);
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					return;
				}
			} else {
				if(diceRoll <= this.plugin.harvest.getInt("Harvest.Mushroom.Chance.OTHER", 10)){
					blockKey.setType(Material.AIR);
					dropController(blockKey, "Mushroom", Material.BROWN_MUSHROOM);
					return;
				}else{
					blockKey.setType(Material.AIR);
					return;
				}
			}
		} else if (blockID.equalsIgnoreCase("CROPS") && blockData.equalsIgnoreCase("7") && this.plugin.harvest.getBoolean("Options.Control.Wheat", false) == true){
			if (usedTool.equalsIgnoreCase("WOOD_HOE")){
				if(diceRoll <= this.plugin.harvest.getInt("Harvest.Wheat.Chance.WOOD_HOE", 30)){
					rollForBean(blockKey, player);
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					blockKey.setType(Material.AIR);
					dropController(blockKey, "Wheat", Material.WHEAT);
					dropSeed(blockKey, player);
					player.sendMessage(ChatColor.GRAY + "Du hast erfolgreich Weizen geerntet!");
					return;
				}else{
					blockKey.setType(Material.AIR);
					rollForSeed(blockKey, player);
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					return;
				}
			} else if(usedTool.equalsIgnoreCase("STONE_HOE")){
				if(diceRoll <= this.plugin.harvest.getInt("Harvest.Wheat.Chance.STONE_HOE", 50)){
					rollForBean(blockKey, player);
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					blockKey.setType(Material.AIR);
					dropController(blockKey, "Wheat", Material.WHEAT);
					dropSeed(blockKey, player);
					return;
				}else{
					blockKey.setType(Material.AIR);
					rollForSeed(blockKey, player);
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					return;
				}
			} else if(usedTool.equalsIgnoreCase("IRON_HOE")){
				if(diceRoll <= this.plugin.harvest.getInt("Harvest.Wheat.Chance.IRON_HOE", 70)){
					rollForBean(blockKey, player);
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					blockKey.setType(Material.AIR);
					dropController(blockKey, "Wheat", Material.WHEAT);
					dropSeed(blockKey, player);
					return;
				}else{
					blockKey.setType(Material.AIR);
					rollForSeed(blockKey, player);
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					return;
				}
			} else if(usedTool.equalsIgnoreCase("GOLD_HOE")){
				if(diceRoll <= this.plugin.harvest.getInt("Harvest.Wheat.Chance.DIAMOND_HOE", 90)){
					rollForBean(blockKey, player);
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					blockKey.setType(Material.AIR);
					dropController(blockKey, "Wheat", Material.WHEAT);
					dropSeed(blockKey, player);
					return;
				}else{
					blockKey.setType(Material.AIR);
					rollForSeed(blockKey, player);
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					return;
				}
			} else if(usedTool.equalsIgnoreCase("DIAMOND_HOE")){
				if(diceRoll <= this.plugin.harvest.getInt("Harvest.Wheat.Chance.GOLD_HOE", 100)){
					rollForBean(blockKey, player);
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					blockKey.setType(Material.AIR);
					dropController(blockKey, "Wheat", Material.WHEAT);
					dropSeed(blockKey, player);
					return;
				}else{
					blockKey.setType(Material.AIR);
					rollForSeed(blockKey, player);
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					return;
				}
			} else {
				if(diceRoll <= this.plugin.harvest.getInt("Harvest.Wheat.Chance.OTHER", 10)){
					rollForBean(blockKey, player);
					blockKey.setType(Material.AIR);
					dropController(blockKey, "Wheat", Material.WHEAT);
					dropSeed(blockKey, player);
					return;
				}else{
					blockKey.setType(Material.AIR);
					rollForSeed(blockKey, player);
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					return;
				}
			}
		} else if (blockID.equalsIgnoreCase("CROPS") && !blockData.equalsIgnoreCase("7") && this.plugin.harvest.getBoolean("Options.Control.Wheat", false) == true){
			blockKey.setType(Material.AIR);
			setItemDurability(player, item);
			return;
		}/* else if (blockID.equalsIgnoreCase("SOIL") && Integer.parseInt(blockData) >= 5 && this.plugin.harvest.getBoolean("Options.Control.Beet", false) == true){
			if (usedTool.equalsIgnoreCase("WOOD_HOE")){
				if(diceRoll <= this.plugin.harvest.getInt("Harvest.Beet.Chance.WOOD_HOE", 30)){
					blockKey.setType(Material.AIR);
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					blockKey.getWorld().dropItemNaturally(blockKey.getLocation(), new ItemStack(Material.DIRT, 1));
					dropController(blockKey, "Beet", Material.NETHER_STALK);
					return;
				}else{
					blockKey.getWorld().dropItemNaturally(blockKey.getLocation(), new ItemStack(Material.DIRT, 1));
					blockKey.setType(Material.AIR);
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					return;
				}
			} else if(usedTool.equalsIgnoreCase("STONE_HOE")){
				if(diceRoll <= this.plugin.harvest.getInt("Harvest.Beet.Chance.STONE_HOE", 50)){
					blockKey.setType(Material.AIR);
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					blockKey.getWorld().dropItemNaturally(blockKey.getLocation(), new ItemStack(Material.DIRT, 1));
					dropController(blockKey, "Beet", Material.NETHER_STALK);
					return;
				}else{
					blockKey.getWorld().dropItemNaturally(blockKey.getLocation(), new ItemStack(Material.DIRT, 1));
					blockKey.setType(Material.AIR);
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					return;
				}
			} else if(usedTool.equalsIgnoreCase("IRON_HOE")){
				if(diceRoll <= this.plugin.harvest.getInt("Harvest.Beet.Chance.IRON_HOE", 70)){
					blockKey.setType(Material.AIR);
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					blockKey.getWorld().dropItemNaturally(blockKey.getLocation(), new ItemStack(Material.DIRT, 1));
					dropController(blockKey, "Beet", Material.NETHER_STALK);
					return;
				}else{
					blockKey.getWorld().dropItemNaturally(blockKey.getLocation(), new ItemStack(Material.DIRT, 1));
					blockKey.setType(Material.AIR);
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					return;
				}
			} else if(usedTool.equalsIgnoreCase("GOLD_HOE")){
				if(diceRoll <= this.plugin.harvest.getInt("Harvest.Beet.Chance.DIAMOND_HOE", 90)){
					blockKey.setType(Material.AIR);
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					blockKey.getWorld().dropItemNaturally(blockKey.getLocation(), new ItemStack(Material.DIRT, 1));
					dropController(blockKey, "Beet", Material.NETHER_STALK);
					return;
				}else{
					blockKey.getWorld().dropItemNaturally(blockKey.getLocation(), new ItemStack(Material.DIRT, 1));
					blockKey.setType(Material.AIR);
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					return;
				}
			} else if(usedTool.equalsIgnoreCase("DIAMOND_HOE")){
				if(diceRoll <= this.plugin.harvest.getInt("Harvest.Beet.Chance.GOLD_HOE", 100)){
					blockKey.setType(Material.AIR);
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					blockKey.getWorld().dropItemNaturally(blockKey.getLocation(), new ItemStack(Material.DIRT, 1));
					dropController(blockKey, "Beet", Material.NETHER_STALK);
					return;
				}else{
					blockKey.getWorld().dropItemNaturally(blockKey.getLocation(), new ItemStack(Material.DIRT, 1));
					blockKey.setType(Material.AIR);
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					return;
				}
			} else {
				if(diceRoll <= this.plugin.harvest.getInt("Harvest.Beet.Chance.OTHER", 0)){
					blockKey.setType(Material.AIR);
					blockKey.getWorld().dropItemNaturally(blockKey.getLocation(), new ItemStack(Material.DIRT, 1));
					dropController(blockKey, "Beet", Material.NETHER_STALK);
					return;
				}else{
					blockKey.getWorld().dropItemNaturally(blockKey.getLocation(), new ItemStack(Material.DIRT, 1));
					blockKey.setType(Material.AIR);
					return;
				}
			}
		} else if (blockID.equalsIgnoreCase("SOIL") && Integer.parseInt(blockData) <= 4 && this.plugin.harvest.getBoolean("Options.Control.Beet", false) == true){
			blockKey.setType(Material.AIR);
			blockKey.getWorld().dropItemNaturally(blockKey.getLocation(), new ItemStack(Material.DIRT, 1));
			setItemDurability(player, item);
			return;
		}*/ else if (blockID.equalsIgnoreCase("CACTUS") && this.plugin.harvest.getBoolean("Options.Control.Cactus", false) == true){
			if (usedTool.equalsIgnoreCase("WOOD_HOE")){
				if(diceRoll <= this.plugin.harvest.getInt("Harvest.Cactus.Chance.WOOD_HOE", 30)){
					if (this.plugin.harvest.getBoolean("Options.Physicsharvest", true) == false){
						event.setCancelled(true);
						executePlantPhysics(blockKey, true, Material.CACTUS);
					}
					blockKey.setType(Material.AIR);
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					dropController(blockKey, "Cactus", Material.CACTUS);
					return;
				}else{
					if (this.plugin.harvest.getBoolean("Options.Physicsharvest", true) == false){
						event.setCancelled(true);
						executePlantPhysics(blockKey, false, Material.CACTUS);
					}
					blockKey.setType(Material.AIR);
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					return;
				}
			} else if(usedTool.equalsIgnoreCase("STONE_HOE")){
				if(diceRoll <= this.plugin.harvest.getInt("Harvest.Cactus.Chance.STONE_HOE", 50)){
					if (this.plugin.harvest.getBoolean("Options.Physicsharvest", true) == false){
						event.setCancelled(true);
						executePlantPhysics(blockKey, true, Material.CACTUS);
					}
					blockKey.setType(Material.AIR);
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					dropController(blockKey, "Cactus", Material.CACTUS);
					return;
				}else{
					if (this.plugin.harvest.getBoolean("Options.Physicsharvest", true) == false){
						event.setCancelled(true);
						executePlantPhysics(blockKey, false, Material.CACTUS);
					}
					blockKey.setType(Material.AIR);
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					return;
				}
			} else if(usedTool.equalsIgnoreCase("IRON_HOE")){
				if(diceRoll <= this.plugin.harvest.getInt("Harvest.Cactus.Chance.IRON_HOE", 70)){
					if (this.plugin.harvest.getBoolean("Options.Physicsharvest", true) == false){
						event.setCancelled(true);
						executePlantPhysics(blockKey, true, Material.CACTUS);
					}
					blockKey.setType(Material.AIR);
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					dropController(blockKey, "Cactus", Material.CACTUS);
					return;
				}else{
					if (this.plugin.harvest.getBoolean("Options.Physicsharvest", true) == false){
						event.setCancelled(true);
						executePlantPhysics(blockKey, false, Material.CACTUS);
					}
					blockKey.setType(Material.AIR);
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					return;
				}
			} else if(usedTool.equalsIgnoreCase("GOLD_HOE")){
				if(diceRoll <= this.plugin.harvest.getInt("Harvest.Cactus.Chance.DIAMOND_HOE", 90)){
					if (this.plugin.harvest.getBoolean("Options.Physicsharvest", true) == false){
						event.setCancelled(true);
						executePlantPhysics(blockKey, true, Material.CACTUS);
					}
					blockKey.setType(Material.AIR);
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					dropController(blockKey, "Cactus", Material.CACTUS);
					return;
				}else{
					if (this.plugin.harvest.getBoolean("Options.Physicsharvest", true) == false){
						event.setCancelled(true);
						executePlantPhysics(blockKey, false, Material.CACTUS);
					}
					blockKey.setType(Material.AIR);
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					return;
				}
			} else if(usedTool.equalsIgnoreCase("DIAMOND_HOE")){
				if(diceRoll <= this.plugin.harvest.getInt("Harvest.Cactus.Chance.GOLD_HOE", 100)){
					if (this.plugin.harvest.getBoolean("Options.Physicsharvest", true) == false){
						event.setCancelled(true);
						executePlantPhysics(blockKey, true, Material.CACTUS);
					}
					blockKey.setType(Material.AIR);
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					dropController(blockKey, "Cactus", Material.CACTUS);
					return;
				}else{
					if (this.plugin.harvest.getBoolean("Options.Physicsharvest", true) == false){
						event.setCancelled(true);
						executePlantPhysics(blockKey, false, Material.CACTUS);
					}
					blockKey.setType(Material.AIR);
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					return;
				}
			} else {
				if(diceRoll <= this.plugin.harvest.getInt("Harvest.Cactus.Chance.OTHER", 10)){
					if (this.plugin.harvest.getBoolean("Options.Physicsharvest", true) == false){
						event.setCancelled(true);
						executePlantPhysics(blockKey, true, Material.CACTUS);
					}
					blockKey.setType(Material.AIR);
					dropController(blockKey, "Cactus", Material.CACTUS);
					return;
				}else{
					if (this.plugin.harvest.getBoolean("Options.Physicsharvest", true) == false){
						event.setCancelled(true);
						executePlantPhysics(blockKey, false, Material.CACTUS);
					}
					blockKey.setType(Material.AIR);
					return;
				}
			}
		} else if (blockID.equalsIgnoreCase("SUGAR_CANE_BLOCK") && this.plugin.harvest.getBoolean("Options.Control.Sugarcane", false) == true){
			if (usedTool.equalsIgnoreCase("WOOD_HOE")){
				if(diceRoll <= this.plugin.harvest.getInt("Harvest.Sugarcane.Chance.WOOD_HOE", 30)){
					if (this.plugin.harvest.getBoolean("Options.Physicsharvest", true) == false){
						event.setCancelled(true);
						executePlantPhysics(blockKey, true, Material.SUGAR_CANE_BLOCK);
					}
					blockKey.setType(Material.AIR);
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					dropController(blockKey, "Sugarcane", Material.SUGAR_CANE);
					return;
				}else{
					if (this.plugin.harvest.getBoolean("Options.Physicsharvest", true) == false){
						event.setCancelled(true);
						executePlantPhysics(blockKey, false, Material.SUGAR_CANE_BLOCK);
					}
					blockKey.setType(Material.AIR);
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					return;
				}
			} else if(usedTool.equalsIgnoreCase("STONE_HOE")){
				if(diceRoll <= this.plugin.harvest.getInt("Harvest.Sugarcane.Chance.STONE_HOE", 50)){
					if (this.plugin.harvest.getBoolean("Options.Physicsharvest", true) == false){
						event.setCancelled(true);
						executePlantPhysics(blockKey, true, Material.SUGAR_CANE_BLOCK);
					}
					blockKey.setType(Material.AIR);
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					dropController(blockKey, "Sugarcane", Material.SUGAR_CANE);
					return;
				}else{
					if (this.plugin.harvest.getBoolean("Options.Physicsharvest", true) == false){
						event.setCancelled(true);
						executePlantPhysics(blockKey, false, Material.SUGAR_CANE_BLOCK);
					}
					blockKey.setType(Material.AIR);
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					return;
				}
			} else if(usedTool.equalsIgnoreCase("IRON_HOE")){
				if(diceRoll <= this.plugin.harvest.getInt("Harvest.Sugarcane.Chance.IRON_HOE", 70)){
					if (this.plugin.harvest.getBoolean("Options.Physicsharvest", true) == false){
						event.setCancelled(true);
						executePlantPhysics(blockKey, true, Material.SUGAR_CANE_BLOCK);
					}
					blockKey.setType(Material.AIR);
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					dropController(blockKey, "Sugarcane", Material.SUGAR_CANE);
					return;
				}else{
					if (this.plugin.harvest.getBoolean("Options.Physicsharvest", true) == false){
						event.setCancelled(true);
						executePlantPhysics(blockKey, false, Material.SUGAR_CANE_BLOCK);
					}
					blockKey.setType(Material.AIR);
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					return;
				}
			} else if(usedTool.equalsIgnoreCase("GOLD_HOE")){
				if(diceRoll <= this.plugin.harvest.getInt("Harvest.Sugarcane.Chance.DIAMOND_HOE", 90)){
					if (this.plugin.harvest.getBoolean("Options.Physicsharvest", true) == false){
						event.setCancelled(true);
						executePlantPhysics(blockKey, true, Material.SUGAR_CANE_BLOCK);
					}
					blockKey.setType(Material.AIR);
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					dropController(blockKey, "Sugarcane", Material.SUGAR_CANE);
					return;
				}else{
					if (this.plugin.harvest.getBoolean("Options.Physicsharvest", true) == false){
						event.setCancelled(true);
						executePlantPhysics(blockKey, false, Material.SUGAR_CANE_BLOCK);
					}
					blockKey.setType(Material.AIR);
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					return;
				}
			} else if(usedTool.equalsIgnoreCase("DIAMOND_HOE")){
				if(diceRoll <= this.plugin.harvest.getInt("Harvest.Sugarcane.Chance.GOLD_HOE", 100)){
					if (this.plugin.harvest.getBoolean("Options.Physicsharvest", true) == false){
						event.setCancelled(true);
						executePlantPhysics(blockKey, true, Material.SUGAR_CANE_BLOCK);
					}
					blockKey.setType(Material.AIR);
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					dropController(blockKey, "Sugarcane", Material.SUGAR_CANE);
					return;
				}else{
					if (this.plugin.harvest.getBoolean("Options.Physicsharvest", true) == false){
						event.setCancelled(true);
						executePlantPhysics(blockKey, false, Material.SUGAR_CANE_BLOCK);
					}
					blockKey.setType(Material.AIR);
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					return;
				}
			} else {
				if(diceRoll <= this.plugin.harvest.getInt("Harvest.Sugarcane.Chance.OTHER", 10)){
					if (this.plugin.harvest.getBoolean("Options.Physicsharvest", true) == false){
						event.setCancelled(true);
						executePlantPhysics(blockKey, true, Material.SUGAR_CANE_BLOCK);
					}
					blockKey.setType(Material.AIR);
					dropController(blockKey, "Sugarcane", Material.SUGAR_CANE);
					return;
				}else{
					if (this.plugin.harvest.getBoolean("Options.Physicsharvest", true) == false){
						event.setCancelled(true);
						executePlantPhysics(blockKey, false, Material.SUGAR_CANE_BLOCK);
					}
					blockKey.setType(Material.AIR);
					return;
				}
			}
		} else if (blockID.equalsIgnoreCase("PUMPKIN") && this.plugin.harvest.getBoolean("Options.Control.Pumpkin", false) == true){
			if (usedTool.equalsIgnoreCase("WOOD_HOE")){
				if(diceRoll <= this.plugin.harvest.getInt("Harvest.Pumpkin.Chance.WOOD_HOE", 30)){
					blockKey.setType(Material.AIR);
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					dropController(blockKey, "Pumpkin", Material.PUMPKIN);
					return;
				}else{
					blockKey.setType(Material.AIR);
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					return;
				}
			} else if(usedTool.equalsIgnoreCase("STONE_HOE")){
				if(diceRoll <= this.plugin.harvest.getInt("Harvest.Pumpkin.Chance.STONE_HOE", 50)){
					blockKey.setType(Material.AIR);
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					dropController(blockKey, "Pumpkin", Material.PUMPKIN);
					return;
				}else{
					blockKey.setType(Material.AIR);
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					return;
				}
			} else if(usedTool.equalsIgnoreCase("IRON_HOE")){
				if(diceRoll <= this.plugin.harvest.getInt("Harvest.Pumpkin.Chance.IRON_HOE", 70)){
					blockKey.setType(Material.AIR);
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					dropController(blockKey, "Pumpkin", Material.PUMPKIN);
					return;
				}else{
					blockKey.setType(Material.AIR);
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					return;
				}
			} else if(usedTool.equalsIgnoreCase("GOLD_HOE")){
				if(diceRoll <= this.plugin.harvest.getInt("Harvest.Pumpkin.Chance.DIAMOND_HOE", 90)){
					blockKey.setType(Material.AIR);
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					dropController(blockKey, "Pumpkin", Material.PUMPKIN);
					return;
				}else{
					blockKey.setType(Material.AIR);
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					return;
				}
			} else if(usedTool.equalsIgnoreCase("DIAMOND_HOE")){
				if(diceRoll <= this.plugin.harvest.getInt("Harvest.Pumpkin.Chance.GOLD_HOE", 100)){
					blockKey.setType(Material.AIR);
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					dropController(blockKey, "Pumpkin", Material.PUMPKIN);
					return;
				}else{
					blockKey.setType(Material.AIR);
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					return;
				}
			} else {
				if(diceRoll <= this.plugin.harvest.getInt("Harvest.Pumpkin.Chance.OTHER", 10)){
					blockKey.setType(Material.AIR);
					dropController(blockKey, "Pumpkin", Material.PUMPKIN);
					return;
				}else{
					blockKey.setType(Material.AIR);
					return;
				}
			}
		} else if (blockID.equalsIgnoreCase("HUGE_MUSHROOM_1") && this.plugin.harvest.getBoolean("Options.Control.Mushroom", false) == true){
			if (usedTool.equalsIgnoreCase("WOOD_HOE")){
				if(diceRoll <= this.plugin.harvest.getInt("Harvest.GiantMushroom.Chance.WOOD_HOE", 30)){
					blockKey.setType(Material.AIR);
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					dropController(blockKey, "GiantMushroom", Material.BROWN_MUSHROOM);
					return;
				}else{
					blockKey.setType(Material.AIR);
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					return;
				}
			} else if(usedTool.equalsIgnoreCase("STONE_HOE")){
				if(diceRoll <= this.plugin.harvest.getInt("Harvest.GiantMushroom.Chance.STONE_HOE", 50)){
					blockKey.setType(Material.AIR);
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					dropController(blockKey, "GiantMushroom", Material.BROWN_MUSHROOM);
					return;
				}else{
					blockKey.setType(Material.AIR);
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					return;
				}
			} else if(usedTool.equalsIgnoreCase("IRON_HOE")){
				if(diceRoll <= this.plugin.harvest.getInt("Harvest.GiantMushroom.Chance.IRON_HOE", 70)){
					blockKey.setType(Material.AIR);
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					dropController(blockKey, "GiantMushroom", Material.BROWN_MUSHROOM);
					return;
				}else{
					blockKey.setType(Material.AIR);
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					return;
				}
			} else if(usedTool.equalsIgnoreCase("GOLD_HOE")){
				if(diceRoll <= this.plugin.harvest.getInt("Harvest.GiantMushroom.Chance.DIAMOND_HOE", 90)){
					blockKey.setType(Material.AIR);
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					dropController(blockKey, "GiantMushroom", Material.BROWN_MUSHROOM);
					return;
				}else{
					blockKey.setType(Material.AIR);
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					return;
				}
			} else if(usedTool.equalsIgnoreCase("DIAMOND_HOE")){
				if(diceRoll <= this.plugin.harvest.getInt("Harvest.GiantMushroom.Chance.GOLD_HOE", 100)){
					blockKey.setType(Material.AIR);
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					dropController(blockKey, "GiantMushroom", Material.BROWN_MUSHROOM);
					return;
				}else{
					blockKey.setType(Material.AIR);
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					return;
				}
			} else {
				if(diceRoll <= this.plugin.harvest.getInt("Harvest.GiantMushroom.Chance.OTHER", 10)){
					blockKey.setType(Material.AIR);
					dropController(blockKey, "GiantMushroom", Material.BROWN_MUSHROOM);
					return;
				}else{
					blockKey.setType(Material.AIR);
					return;
				}
			}
		} else if (blockID.equalsIgnoreCase("HUGE_MUSHROOM_2") && this.plugin.harvest.getBoolean("Options.Control.Mushroom", false) == true){
			if (usedTool.equalsIgnoreCase("WOOD_HOE")){
				if(diceRoll <= this.plugin.harvest.getInt("Harvest.GiantMushroom.Chance.WOOD_HOE", 30)){
					blockKey.setType(Material.AIR);
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					dropController(blockKey, "GiantMushroom", Material.RED_MUSHROOM);
					return;
				}else{
					blockKey.setType(Material.AIR);
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					return;
				}
			} else if(usedTool.equalsIgnoreCase("STONE_HOE")){
				if(diceRoll <= this.plugin.harvest.getInt("Harvest.GiantMushroom.Chance.STONE_HOE", 50)){
					blockKey.setType(Material.AIR);
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					dropController(blockKey, "GiantMushroom", Material.RED_MUSHROOM);
					return;
				}else{
					blockKey.setType(Material.AIR);
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					return;
				}
			} else if(usedTool.equalsIgnoreCase("IRON_HOE")){
				if(diceRoll <= this.plugin.harvest.getInt("Harvest.GiantMushroom.Chance.IRON_HOE", 70)){
					blockKey.setType(Material.AIR);
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					dropController(blockKey, "GiantMushroom", Material.RED_MUSHROOM);
					return;
				}else{
					blockKey.setType(Material.AIR);
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					return;
				}
			} else if(usedTool.equalsIgnoreCase("GOLD_HOE")){
				if(diceRoll <= this.plugin.harvest.getInt("Harvest.GiantMushroom.Chance.DIAMOND_HOE", 90)){
					blockKey.setType(Material.AIR);
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					dropController(blockKey, "GiantMushroom", Material.RED_MUSHROOM);
					return;
				}else{
					blockKey.setType(Material.AIR);
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					return;
				}
			} else if(usedTool.equalsIgnoreCase("DIAMOND_HOE")){
				if(diceRoll <= this.plugin.harvest.getInt("Harvest.GiantMushroom.Chance.GOLD_HOE", 100)){
					blockKey.setType(Material.AIR);
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					dropController(blockKey, "GiantMushroom", Material.RED_MUSHROOM);
					return;
				}else{
					blockKey.setType(Material.AIR);
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					return;
				}
			} else {
				if(diceRoll <= this.plugin.harvest.getInt("Harvest.GiantMushroom.Chance.OTHER", 10)){
					blockKey.setType(Material.AIR);
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					dropController(blockKey, "GiantMushroom", Material.RED_MUSHROOM);
					return;
				}else{
					blockKey.setType(Material.AIR);
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					return;
				}
			}
		} else if (blockID.equalsIgnoreCase("MELON_BLOCK") && this.plugin.harvest.getBoolean("Options.Control.Melon", false) == true){
			if (usedTool.equalsIgnoreCase("WOOD_HOE")){
				if(diceRoll <= this.plugin.harvest.getInt("Harvest.Melon.Chance.WOOD_HOE", 30)){
					blockKey.setType(Material.AIR);
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					dropController(blockKey, "Melon", Material.MELON);
					return;
				}else{
					blockKey.setType(Material.AIR);
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					return;
				}
			} else if(usedTool.equalsIgnoreCase("STONE_HOE")){
				if(diceRoll <= this.plugin.harvest.getInt("Harvest.Melon.Chance.STONE_HOE", 50)){
					blockKey.setType(Material.AIR);
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					dropController(blockKey, "Melon", Material.MELON);
					return;
				}else{
					blockKey.setType(Material.AIR);
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					return;
				}
			} else if(usedTool.equalsIgnoreCase("IRON_HOE")){
				if(diceRoll <= this.plugin.harvest.getInt("Harvest.Melon.Chance.IRON_HOE", 70)){
					blockKey.setType(Material.AIR);
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					dropController(blockKey, "Melon", Material.MELON);
					return;
				}else{
					blockKey.setType(Material.AIR);
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					return;
				}
			} else if(usedTool.equalsIgnoreCase("GOLD_HOE")){
				if(diceRoll <= this.plugin.harvest.getInt("Harvest.Melon.Chance.DIAMOND_HOE", 90)){
					blockKey.setType(Material.AIR);
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					dropController(blockKey, "Melon", Material.MELON);
					return;
				}else{
					blockKey.setType(Material.AIR);
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					return;
				}
			} else if(usedTool.equalsIgnoreCase("DIAMOND_HOE")){
				if(diceRoll <= this.plugin.harvest.getInt("Harvest.Melon.Chance.GOLD_HOE", 100)){
					blockKey.setType(Material.AIR);
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					dropController(blockKey, "Melon", Material.MELON);
					return;
				}else{
					blockKey.setType(Material.AIR);
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					return;
				}
			} else {
				if(diceRoll <= this.plugin.harvest.getInt("Harvest.Melon.Chance.OTHER", 10)){
					blockKey.setType(Material.AIR);
					dropController(blockKey, "Melon", Material.MELON);
					return;
				}else{
					blockKey.setType(Material.AIR);
					return;
				}
			}
		} else if (blockID.equalsIgnoreCase("VINE") && this.plugin.harvest.getBoolean("Options.Control.Grapes", false) == true){
			if (usedTool.equalsIgnoreCase("WOOD_HOE")){
				if(diceRoll <= this.plugin.harvest.getInt("Harvest.Grapes.Chance.WOOD_HOE", 30)){
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					blockKey.setType(Material.AIR);
					if(grapeController(blockKey) == false) return;
					dropController(blockKey, "Grapes", Material.GHAST_TEAR);
					return;
				}else{
					blockKey.setType(Material.AIR);
				}
			} else if(usedTool.equalsIgnoreCase("STONE_HOE")){
				if(diceRoll <= this.plugin.harvest.getInt("Harvest.Grapes.Chance.STONE_HOE", 50)){
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					blockKey.setType(Material.AIR);
					if(grapeController(blockKey) == false) return;
					dropController(blockKey, "Grapes", Material.GHAST_TEAR);
					return;
				}else{
					blockKey.setType(Material.AIR);
				}
			} else if(usedTool.equalsIgnoreCase("IRON_HOE")){
				if(diceRoll <= this.plugin.harvest.getInt("Harvest.Grapes.Chance.IRON_HOE", 70)){
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					blockKey.setType(Material.AIR);
					if(grapeController(blockKey) == false) return;
					dropController(blockKey, "Grapes", Material.GHAST_TEAR);
					return;
				}else{
					blockKey.setType(Material.AIR);
				}
			} else if(usedTool.equalsIgnoreCase("GOLD_HOE")){
				if(diceRoll <= this.plugin.harvest.getInt("Harvest.Grapes.Chance.DIAMOND_HOE", 90)){
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					blockKey.setType(Material.AIR);
					if(grapeController(blockKey) == false) return;
					dropController(blockKey, "Grapes", Material.GHAST_TEAR);
					return;
				}else{
					blockKey.setType(Material.AIR);
				}
			} else if(usedTool.equalsIgnoreCase("DIAMOND_HOE")){
				if(diceRoll <= this.plugin.harvest.getInt("Harvest.Grapes.Chance.GOLD_HOE", 100)){
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					blockKey.setType(Material.AIR);
					if(grapeController(blockKey) == false) return;
					dropController(blockKey, "Grapes", Material.GHAST_TEAR);
					return;
				}else{
					blockKey.setType(Material.AIR);
				}
			} else if(usedTool.equalsIgnoreCase("SHEARS")){
				return;
			}  else {
				if(diceRoll <= this.plugin.harvest.getInt("Harvest.Grapes.Chance.OTHER", 10)){
					blockKey.setType(Material.AIR);
					if(grapeController(blockKey) == false) return;
					dropController(blockKey, "Grapes", Material.GHAST_TEAR);
					return;
				}else{
					blockKey.setType(Material.AIR);
				}
			}
		} else if (blockID.equalsIgnoreCase("NETHER_WARTS") && this.plugin.harvest.getBoolean("Options.Control.Netherwart", false) == true){
			if (usedTool.equalsIgnoreCase("WOOD_HOE")){
				if(diceRoll <= this.plugin.harvest.getInt("Harvest.Netherwart.Chance.WOOD_HOE", 30)){
					dropController(blockKey, "Netherwart", Material.NETHER_STALK);
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					return;
				}else{
					blockKey.setType(Material.AIR);
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					return;
				}
			} else if(usedTool.equalsIgnoreCase("STONE_HOE")){
				if(diceRoll <= this.plugin.harvest.getInt("Harvest.Netherwart.Chance.STONE_HOE", 50)){
					dropController(blockKey, "Netherwart", Material.NETHER_STALK);
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					return;
				}else{
					blockKey.setType(Material.AIR);
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					return;
				}
			} else if(usedTool.equalsIgnoreCase("IRON_HOE")){
				if(diceRoll <= this.plugin.harvest.getInt("Harvest.Netherwart.Chance.IRON_HOE", 70)){
					dropController(blockKey, "Netherwart", Material.NETHER_STALK);
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					return;
				}else{
					blockKey.setType(Material.AIR);
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					return;
				}
			} else if(usedTool.equalsIgnoreCase("GOLD_HOE")){
				if(diceRoll <= this.plugin.harvest.getInt("Harvest.Netherwart.Chance.DIAMOND_HOE", 90)){
					dropController(blockKey, "Netherwart", Material.NETHER_STALK);
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					return;
				}else{
					blockKey.setType(Material.AIR);
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					return;
				}
			} else if(usedTool.equalsIgnoreCase("DIAMOND_HOE")){
				if(diceRoll <= this.plugin.harvest.getInt("Harvest.Netherwart.Chance.GOLD_HOE", 100)){
					dropController(blockKey, "Netherwart", Material.NETHER_STALK);
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					return;
				}else{
					blockKey.setType(Material.AIR);
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					return;
				}
			} else {
				if(diceRoll <= this.plugin.harvest.getInt("Harvest.Netherwart.Chance.OTHER", 10)){
					dropController(blockKey, "Netherwart", Material.NETHER_STALK);
					return;
				}else{
					blockKey.setType(Material.AIR);
					return;
				}
			}
		} else if (blockID.equalsIgnoreCase("COCOA") && Integer.parseInt(blockData) >= 8 && this.plugin.harvest.getBoolean("Options.Control.Cocoa", false) == true){
			if (usedTool.equalsIgnoreCase("WOOD_HOE")){
				if(diceRoll <= this.plugin.harvest.getInt("Harvest.Cocoa.Chance.WOOD_HOE", 30)){
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					blockKey.setType(Material.AIR);
					dropController(blockKey, "Cocoa", Material.INK_SACK);
					return;
				}else{
					blockKey.setType(Material.AIR);
				}
			} else if(usedTool.equalsIgnoreCase("STONE_HOE")){
				if(diceRoll <= this.plugin.harvest.getInt("Harvest.Cocoa.Chance.STONE_HOE", 50)){
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					blockKey.setType(Material.AIR);
					dropController(blockKey, "Cocoa", Material.INK_SACK);
					return;
				}else{
					blockKey.setType(Material.AIR);
				}
			} else if(usedTool.equalsIgnoreCase("IRON_HOE")){
				if(diceRoll <= this.plugin.harvest.getInt("Harvest.Cocoa.Chance.IRON_HOE", 70)){
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					blockKey.setType(Material.AIR);
					dropController(blockKey, "Cocoa", Material.INK_SACK);
					return;
				}else{
					blockKey.setType(Material.AIR);
				}
			} else if(usedTool.equalsIgnoreCase("GOLD_HOE")){
				if(diceRoll <= this.plugin.harvest.getInt("Harvest.Cocoa.Chance.DIAMOND_HOE", 90)){
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					blockKey.setType(Material.AIR);
					dropController(blockKey, "Cocoa", Material.INK_SACK);
					return;
				}else{
					blockKey.setType(Material.AIR);
				}
			} else if(usedTool.equalsIgnoreCase("DIAMOND_HOE")){
				if(diceRoll <= this.plugin.harvest.getInt("Harvest.Cocoa.Chance.GOLD_HOE", 100)){
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					blockKey.setType(Material.AIR);
					dropController(blockKey, "Cocoa", Material.INK_SACK);
					return;
				}else{
					blockKey.setType(Material.AIR);
				}
			} else {
				if(diceRoll <= this.plugin.harvest.getInt("Harvest.Cocoa.Chance.OTHER", 10)){
					blockKey.setType(Material.AIR);
					dropController(blockKey, "Cocoa", Material.INK_SACK);
					return;
				}else{
					blockKey.setType(Material.AIR);
				}
			}
		} else if (blockID.equalsIgnoreCase("COCOA") && Integer.parseInt(blockData) <= 7 && this.plugin.harvest.getBoolean("Options.Control.Cocoa", false) == true){
			blockKey.setType(Material.AIR);
			setItemDurability(player, item);
			return;
		} else if (blockID.equalsIgnoreCase("CARROT") && Integer.parseInt(blockData) >= 7 && this.plugin.harvest.getBoolean("Options.Control.Carrot", false) == true){
			if (usedTool.equalsIgnoreCase("WOOD_HOE")){
				if(diceRoll <= this.plugin.harvest.getInt("Harvest.Carrot.Chance.WOOD_HOE", 30)){
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					blockKey.setType(Material.AIR);
					dropController(blockKey, "Carrot", Material.CARROT_ITEM);
					return;
				}else{
					blockKey.setType(Material.AIR);
					return;
				}
			} else if(usedTool.equalsIgnoreCase("STONE_HOE")){
				if(diceRoll <= this.plugin.harvest.getInt("Harvest.Carrot.Chance.STONE_HOE", 50)){
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					blockKey.setType(Material.AIR);
					dropController(blockKey, "Carrot", Material.CARROT_ITEM);
					return;
				}else{
					blockKey.setType(Material.AIR);
					return;
				}
			} else if(usedTool.equalsIgnoreCase("IRON_HOE")){
				if(diceRoll <= this.plugin.harvest.getInt("Harvest.Carrot.Chance.IRON_HOE", 70)){
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					blockKey.setType(Material.AIR);
					dropController(blockKey, "Carrot", Material.CARROT_ITEM);
					return;
				}else{
					blockKey.setType(Material.AIR);
					return;
				}
			} else if(usedTool.equalsIgnoreCase("GOLD_HOE")){
				if(diceRoll <= this.plugin.harvest.getInt("Harvest.Carrot.Chance.DIAMOND_HOE", 90)){
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					blockKey.setType(Material.AIR);
					dropController(blockKey, "Carrot", Material.CARROT_ITEM);
					return;
				}else{
					blockKey.setType(Material.AIR);
					return;
				}
			} else if(usedTool.equalsIgnoreCase("DIAMOND_HOE")){
				if(diceRoll <= this.plugin.harvest.getInt("Harvest.Carrot.Chance.GOLD_HOE", 100)){
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					blockKey.setType(Material.AIR);
					dropController(blockKey, "Carrot", Material.CARROT_ITEM);
					return;
				}else{
					blockKey.setType(Material.AIR);
					return;
				}
			} else {
				if(diceRoll <= this.plugin.harvest.getInt("Harvest.Carrot.Chance.OTHER", 10)){
					blockKey.setType(Material.AIR);
					dropController(blockKey, "Carrot", Material.CARROT_ITEM);
					return;
				}else{
					blockKey.setType(Material.AIR);
					return;
				}
			}
		} else if (blockID.equalsIgnoreCase("CARROT") && Integer.parseInt(blockData) <= 6 && this.plugin.harvest.getBoolean("Options.Control.Carrot", false) == true){
			blockKey.setType(Material.AIR);
			setItemDurability(player, item);
			return;
		} else if (blockID.equalsIgnoreCase("POTATO") && Integer.parseInt(blockData) >= 7 && this.plugin.harvest.getBoolean("Options.Control.Potatoes", false) == true){
			if (usedTool.equalsIgnoreCase("WOOD_HOE")){
				if(diceRoll <= this.plugin.harvest.getInt("Harvest.Potatoes.Chance.WOOD_HOE", 30)){
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					blockKey.setType(Material.AIR);
					dropController(blockKey, "Potatoes", Material.POTATO_ITEM);
					return;
				}else{
					blockKey.setType(Material.AIR);
					return;
				}
			} else if(usedTool.equalsIgnoreCase("STONE_HOE")){
				if(diceRoll <= this.plugin.harvest.getInt("Harvest.Potatoes.Chance.STONE_HOE", 50)){
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					blockKey.setType(Material.AIR);
					dropController(blockKey, "Potatoes", Material.POTATO_ITEM);
					return;
				}else{
					blockKey.setType(Material.AIR);
					return;
				}
			} else if(usedTool.equalsIgnoreCase("IRON_HOE")){
				if(diceRoll <= this.plugin.harvest.getInt("Harvest.Potatoes.Chance.IRON_HOE", 70)){
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					blockKey.setType(Material.AIR);
					dropController(blockKey, "Potatoes", Material.POTATO_ITEM);
					return;
				}else{
					blockKey.setType(Material.AIR);
					return;
				}
			} else if(usedTool.equalsIgnoreCase("GOLD_HOE")){
				if(diceRoll <= this.plugin.harvest.getInt("Harvest.Potatoes.Chance.DIAMOND_HOE", 90)){
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					blockKey.setType(Material.AIR);
					dropController(blockKey, "Potatoes", Material.POTATO_ITEM);
					return;
				}else{
					blockKey.setType(Material.AIR);
					return;
				}
			} else if(usedTool.equalsIgnoreCase("DIAMOND_HOE")){
				if(diceRoll <= this.plugin.harvest.getInt("Harvest.Potatoes.Chance.GOLD_HOE", 100)){
					if(this.plugin.harvest.getBoolean("General.Durability.active", true) == true){
						setItemDurability(player, item);
					}
					blockKey.setType(Material.AIR);
					dropController(blockKey, "Potatoes", Material.POTATO_ITEM);
					return;
				}else{
					blockKey.setType(Material.AIR);
					return;
				}
			} else {
				if(diceRoll <= this.plugin.harvest.getInt("Harvest.Potatoes.Chance.OTHER", 10)){
					blockKey.setType(Material.AIR);
					dropController(blockKey, "Potatoes", Material.POTATO_ITEM);
					return;
				}else{
					blockKey.setType(Material.AIR);
					return;
				}
			}
		} else if (blockID.equalsIgnoreCase("POTATO") && Integer.parseInt(blockData) <= 6 && this.plugin.harvest.getBoolean("Options.Control.Potatoes", false) == true){
			blockKey.setType(Material.AIR);
			setItemDurability(player, item);
			return;
		} else {
			return;
		}		

	}
}