package org.community.ambience.AmbientObjects.Utility;

import org.bukkit.CropState;
import org.bukkit.DyeColor;
import org.bukkit.GrassSpecies;
import org.bukkit.Material;
import org.bukkit.SandstoneType;
import org.bukkit.TreeSpecies;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Crops;
import org.bukkit.material.Dye;
import org.bukkit.material.LongGrass;
import org.bukkit.material.Sandstone;
import org.bukkit.material.SmoothBrick;
import org.bukkit.material.Step;
import org.bukkit.material.Tree;
import org.bukkit.material.WoodenStep;
import org.bukkit.material.Wool;

public class ItemTypeParser {
	
	
	public static String blockLibrary = "WOOD,LOG,LEAVES,SAPLING,SANDSTONE,LONG_GRASS,WOOL,CROPS,DOUBLE_STEP,STEP,SMOOTH_BRICK,WOOD_DOUBLE_STEP,WOOD_STEP,INK_SACK";
	
	@SuppressWarnings("deprecation")
	public static String getBlockType(Block block, ItemStack item) {
		
		Material blockMaterial = null;
		Material itemMaterial = null;
		
		if(block != null) {
			blockMaterial = block.getType();
		}
		if(item != null) {
			itemMaterial = item.getType();
		}
		
		if(blockMaterial == null) {
			blockMaterial = itemMaterial;
		}
		if(itemMaterial == null) {
			itemMaterial = blockMaterial;
		}
		
		String itemType = "";

		if(blockMaterial == Material.WOOD || itemMaterial == Material.WOOD) {
			Tree tree = null;
			if(block != null) {
				tree = (Tree) block.getState().getData();
			}
			if(item != null) {
				tree = (Tree) item.getData();
			}
			
			if(tree.getSpecies() == TreeSpecies.GENERIC) {
				itemType = "";
			}
			if(tree.getSpecies() == TreeSpecies.BIRCH) {
				itemType = "BIRCH";
			}
			if(tree.getSpecies() == TreeSpecies.JUNGLE) {
				itemType = "JUNGLE";
			}
			if(tree.getSpecies() == TreeSpecies.REDWOOD) {
				itemType = "REDWOOD";
			}
			if(tree.getSpecies() == TreeSpecies.ACACIA) {
				itemType = "ACACIA";
			}
			if(tree.getSpecies() == TreeSpecies.DARK_OAK) {
				itemType = "DARK_OAK";
			}
		}
		if(blockMaterial == Material.SAPLING || itemMaterial == Material.SAPLING) {
			Tree tree = null;
			if(block != null) {
				tree = (Tree) block.getState().getData();
			}
			if(item != null) {
				tree = (Tree) item.getData();
			}
			
			if(tree.getSpecies() == TreeSpecies.GENERIC) {
				itemType = "";
			}
			if(tree.getSpecies() == TreeSpecies.BIRCH) {
				itemType = "BIRCH";
			}
			if(tree.getSpecies() == TreeSpecies.JUNGLE) {
				itemType = "JUNGLE";
			}
			if(tree.getSpecies() == TreeSpecies.REDWOOD) {
				itemType = "REDWOOD";
			}
			if(tree.getSpecies() == TreeSpecies.ACACIA) {
				itemType = "ACACIA";
			}
			if(tree.getSpecies() == TreeSpecies.DARK_OAK) {
				itemType = "DARK_OAK";
			}
		}
		if(blockMaterial == Material.LOG || itemMaterial == Material.LOG) {
			Tree tree = null;
			if(block != null) {
				tree = (Tree) block.getState().getData();
			}
			if(item != null) {
				tree = (Tree) item.getData();
			}
			
			if(tree.getSpecies() == TreeSpecies.GENERIC) {
				itemType = "";
			}
			if(tree.getSpecies() == TreeSpecies.BIRCH) {
				itemType = "BIRCH";
			}
			if(tree.getSpecies() == TreeSpecies.JUNGLE) {
				itemType = "JUNGLE";
			}
			if(tree.getSpecies() == TreeSpecies.REDWOOD) {
				itemType = "REDWOOD";
			}
			if(tree.getSpecies() == TreeSpecies.ACACIA) {
				itemType = "ACACIA";
			}
			if(tree.getSpecies() == TreeSpecies.DARK_OAK) {
				itemType = "DARK_OAK";
			}
		}
		if(blockMaterial == Material.LEAVES || itemMaterial == Material.LEAVES) {
			Tree tree = null;
			if(block != null) {
				tree = (Tree) block.getState().getData();
			}
			if(item != null) {
				tree = (Tree) item.getData();
			}
			
			if(tree.getSpecies() == TreeSpecies.GENERIC) {
				itemType = "";
			}
			if(tree.getSpecies() == TreeSpecies.BIRCH) {
				itemType = "BIRCH";
			}
			if(tree.getSpecies() == TreeSpecies.JUNGLE) {
				itemType = "JUNGLE";
			}
			if(tree.getSpecies() == TreeSpecies.REDWOOD) {
				itemType = "REDWOOD";
			}
			if(tree.getSpecies() == TreeSpecies.ACACIA) {
				itemType = "ACACIA";
			}
			if(tree.getSpecies() == TreeSpecies.DARK_OAK) {
				itemType = "DARK_OAK";
			}
		}
		if(blockMaterial == Material.SANDSTONE || itemMaterial == Material.SANDSTONE) {
			Sandstone sandstone = null;
			if(block != null) {
				sandstone = (Sandstone) block.getState().getData();
			}
			if(item != null) {
				sandstone = (Sandstone) item.getData();
			}
			
			if(sandstone.getType() == SandstoneType.CRACKED) {
				itemType = "";
			}
			if(sandstone.getType() == SandstoneType.GLYPHED) {
				itemType = "GLYPHED";
			}
			if(sandstone.getType() == SandstoneType.SMOOTH) {
				itemType = "SMOOTH";
			}
		}
		if(blockMaterial == Material.LONG_GRASS || itemMaterial == Material.LONG_GRASS) {
			LongGrass longgrass = null;
			if(block != null) {
				longgrass = (LongGrass) block.getState().getData();
			}
			if(item != null) {
				longgrass = (LongGrass) item.getData();
			}
			
			if(longgrass.getSpecies() == GrassSpecies.NORMAL) {
				itemType = "";
			}
			if(longgrass.getSpecies() == GrassSpecies.DEAD) {
				itemType = "DEAD";
			}
			if(longgrass.getSpecies() == GrassSpecies.FERN_LIKE) {
				itemType = "FERN";
			}
		}
		if(blockMaterial == Material.WOOL || itemMaterial == Material.WOOL) {
			Wool wool = null;
			if(block != null) {
				wool = (Wool) block.getState().getData();
			}
			if(item != null) {
				wool = (Wool) item.getData();
			}
			
			if(wool.getColor() == DyeColor.WHITE) {
				itemType = "";
			}
			if(wool.getColor() == DyeColor.BLACK) {
				itemType = "BLACK";
			}
			if(wool.getColor() == DyeColor.BLUE) {
				itemType = "BLUE";
			}
			if(wool.getColor() == DyeColor.BROWN) {
				itemType = "BROWN";
			}
			if(wool.getColor() == DyeColor.CYAN) {
				itemType = "CYAN";
			}
			if(wool.getColor() == DyeColor.GRAY) {
				itemType = "GRAY";
			}
			if(wool.getColor() == DyeColor.GREEN) {
				itemType = "GREEN";
			}
			if(wool.getColor() == DyeColor.LIGHT_BLUE) {
				itemType = "LIGHT_BLUE";
			}
			if(wool.getColor() == DyeColor.LIME) {
				itemType = "LIME";
			}
			if(wool.getColor() == DyeColor.MAGENTA) {
				itemType = "MAGENTA";
			}
			if(wool.getColor() == DyeColor.ORANGE) {
				itemType = "ORANGE";
			}
			if(wool.getColor() == DyeColor.PINK) {
				itemType = "PINK";
			}
			if(wool.getColor() == DyeColor.PURPLE) {
				itemType = "PURPLE";
			}
			if(wool.getColor() == DyeColor.RED) {
				itemType = "RED";
			}
			if(wool.getColor() == DyeColor.SILVER) {
				itemType = "LIGHT_GRAY";
			}
			if(wool.getColor() == DyeColor.YELLOW) {
				itemType = "YELLOW";
			}
		}
		if(blockMaterial == Material.CROPS || itemMaterial == Material.CROPS) {
			Crops crops = null;
			if(block != null) {
				crops = (Crops) block.getState().getData();
			}
			if(item != null) {
				crops = (Crops) item.getData();
			}
			
			if(crops.getState() == CropState.SEEDED) {
				itemType = "0";
			}
			if(crops.getState() == CropState.GERMINATED) {
				itemType = "1";
			}
			if(crops.getState() == CropState.VERY_SMALL) {
				itemType = "2";
			}
			if(crops.getState() == CropState.SMALL) {
				itemType = "3";
			}
			if(crops.getState() == CropState.MEDIUM) {
				itemType = "4";
			}
			if(crops.getState() == CropState.TALL) {
				itemType = "5";
			}
			if(crops.getState() == CropState.VERY_TALL) {
				itemType = "6";
			}
			if(crops.getState() == CropState.RIPE) {
				itemType = "7";
			}
		}
		if(blockMaterial == Material.DOUBLE_STEP || itemMaterial == Material.DOUBLE_STEP) {
			Step step = null;
			if(block != null) {
				step = (Step) block.getState().getData();
			}
			if(item != null) {
				step = (Step) item.getData();
			}
			
			if(step.getMaterial() == Material.STONE) {
				itemType = "STONE_SLAB";
			}
			if(step.getMaterial() == Material.SANDSTONE) {
				itemType = "SANDSTONE_SLAB";
			}
			if(step.getMaterial() == Material.COBBLESTONE) {
				itemType = "COBBLESTONE_SLAB";
			}
			if(step.getMaterial() == Material.BRICK) {
				itemType = "BRICK_SLAB";
			}
			if(step.getMaterial() == Material.SMOOTH_BRICK) {
				itemType = "SMOOTH_BRICK_SLAB";
			}
			if(step.getMaterial() == Material.NETHER_BRICK) {
				itemType = "NETHER_BRICK_SLAB";
			}
			if(step.getMaterial() == Material.QUARTZ_BLOCK) {
				itemType = "QUARTZ_SLAB";
			}
		}
		if(blockMaterial == Material.STEP || itemMaterial == Material.STEP) {
			Step step = null;
			if(block != null) {
				step = (Step) block.getState().getData();
			}
			if(item != null) {
				step = (Step) item.getData();
			}
			
			if(step.getMaterial() == Material.STONE) {
				itemType = "STONE_SLAB";
			}
			if(step.getMaterial() == Material.SANDSTONE) {
				itemType = "SANDSTONE_SLAB";
			}
			if(step.getMaterial() == Material.COBBLESTONE) {
				itemType = "COBBLESTONE_SLAB";
			}
			if(step.getMaterial() == Material.BRICK) {
				itemType = "BRICK_SLAB";
			}
			if(step.getMaterial() == Material.SMOOTH_BRICK) {
				itemType = "SMOOTH_BRICK_SLAB";
			}
			if(step.getMaterial() == Material.NETHER_BRICK) {
				itemType = "NETHER_BRICK_SLAB";
			}
			if(step.getMaterial() == Material.QUARTZ_BLOCK) {
				itemType = "QUARTZ_SLAB";
			}
		}
		if(blockMaterial == Material.SMOOTH_BRICK || itemMaterial == Material.SMOOTH_BRICK) {
			SmoothBrick brick = null;
			if(block != null) {
				brick = (SmoothBrick) block.getState().getData();
			}
			if(item != null) {
				brick = (SmoothBrick) item.getData();
			}
			
			if(brick.getMaterial() == Material.STONE) {
				itemType = "STONEBRICKS";
			}
			if(brick.getMaterial() == Material.MOSSY_COBBLESTONE) {
				itemType = "MOSSY_STONEBRICKS";
			}
			if(brick.getMaterial() == Material.COBBLESTONE) {
				itemType = "CRACKED_STONEBRICKS";
			}
			if(brick.getMaterial() == Material.SMOOTH_BRICK) {
				itemType = "CHISELED_STONEBRICKS";
			}
		}
		if(blockMaterial == Material.WOOD_DOUBLE_STEP || itemMaterial == Material.WOOD_DOUBLE_STEP) {
			WoodenStep tree = null;
			if(block != null) {
				tree = (WoodenStep) block.getState().getData();
			}
			if(item != null) {
				tree = (WoodenStep) item.getData();
			}
			
			if(tree.getSpecies() == TreeSpecies.GENERIC) {
				itemType = "";
			}
			if(tree.getSpecies() == TreeSpecies.BIRCH) {
				itemType = "BIRCH";
			}
			if(tree.getSpecies() == TreeSpecies.JUNGLE) {
				itemType = "JUNGLE";
			}
			if(tree.getSpecies() == TreeSpecies.REDWOOD) {
				itemType = "REDWOOD";
			}
			if(tree.getSpecies() == TreeSpecies.ACACIA) {
				itemType = "ACACIA";
			}
			if(tree.getSpecies() == TreeSpecies.DARK_OAK) {
				itemType = "DARK_OAK";
			}
		}
		if(blockMaterial == Material.WOOD_STEP || itemMaterial == Material.WOOD_STEP) {
			WoodenStep tree = null;
			if(block != null) {
				tree = (WoodenStep) block.getState().getData();
			}
			if(item != null) {
				tree = (WoodenStep) item.getData();
			}
			
			if(tree.getSpecies() == TreeSpecies.GENERIC) {
				itemType = "";
			}
			if(tree.getSpecies() == TreeSpecies.BIRCH) {
				itemType = "BIRCH";
			}
			if(tree.getSpecies() == TreeSpecies.JUNGLE) {
				itemType = "JUNGLE";
			}
			if(tree.getSpecies() == TreeSpecies.REDWOOD) {
				itemType = "REDWOOD";
			}
			if(tree.getSpecies() == TreeSpecies.ACACIA) {
				itemType = "ACACIA";
			}
			if(tree.getSpecies() == TreeSpecies.DARK_OAK) {
				itemType = "DARK_OAK";
			}
		}
		if(blockMaterial == Material.INK_SACK || itemMaterial == Material.INK_SACK) {
			Dye dye = null;
			if(block != null) {
				dye = (Dye) block.getState().getData();
			}
			if(item != null) {
				dye = (Dye) item.getData();
			}
			
			if(dye.getColor() == DyeColor.WHITE) {
				itemType = "WHITE";
			}
			if(dye.getColor() == DyeColor.BLACK) {
				itemType = "";
			}
			if(dye.getColor() == DyeColor.BLUE) {
				itemType = "BLUE";
			}
			if(dye.getColor() == DyeColor.BROWN) {
				itemType = "BROWN";
			}
			if(dye.getColor() == DyeColor.CYAN) {
				itemType = "CYAN";
			}
			if(dye.getColor() == DyeColor.GRAY) {
				itemType = "GRAY";
			}
			if(dye.getColor() == DyeColor.GREEN) {
				itemType = "GREEN";
			}
			if(dye.getColor() == DyeColor.LIGHT_BLUE) {
				itemType = "LIGHT_BLUE";
			}
			if(dye.getColor() == DyeColor.LIME) {
				itemType = "LIME";
			}
			if(dye.getColor() == DyeColor.MAGENTA) {
				itemType = "MAGENTA";
			}
			if(dye.getColor() == DyeColor.ORANGE) {
				itemType = "ORANGE";
			}
			if(dye.getColor() == DyeColor.PINK) {
				itemType = "PINK";
			}
			if(dye.getColor() == DyeColor.PURPLE) {
				itemType = "PURPLE";
			}
			if(dye.getColor() == DyeColor.RED) {
				itemType = "RED";
			}
			if(dye.getColor() == DyeColor.SILVER) {
				itemType = "LIGHT_GRAY";
			}
			if(dye.getColor() == DyeColor.YELLOW) {
				itemType = "YELLOW";
			}
		}
		
		if(item != null && itemType.equalsIgnoreCase("")) {
			itemType = "" + item.getDurability();
		}
		if(block != null && itemType.equalsIgnoreCase("")) {
			if(block.getType() == Material.SOIL) {
				itemType = "" + block.getData();
			} else {
				itemType = "" + block.getData();
			}
		}
		
		return itemType;
		
	}
	


}