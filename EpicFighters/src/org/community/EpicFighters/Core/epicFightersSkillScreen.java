package org.community.EpicFighters.Core;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.community.EpicFighters.EpicFighters;
import org.community.fourWays.User.User;

public class epicFightersSkillScreen {
		
	private final EpicFighters plugin;
	
	Player player = null;
	
	public ItemStack slotFiller = new ItemStack(Material.OBSIDIAN, 1);
	public ItemStack skill1 = null;
	public ItemStack skill2 = null;
	public ItemStack skill3 = null;
	public ItemStack skill4 = null;
	public ItemStack skill5 = null;
	
	Inventory inventory = null;
	 	 
	public epicFightersSkillScreen(EpicFighters plugin, Player player)
	{
		this.plugin = plugin;
		
		this.player = player;
		
		initiateSkillWindow();
	}
	
	public void initiateSkillWindow() {
		inventory = Bukkit.getServer().createInventory(null, InventoryType.CHEST);
		for(int i = 0; i < 27; i++) {
			inventory.setItem(i, slotFiller);
		}
		
		inventory.setItem(11, new ItemStack(Material.ITEM_FRAME, 1));
		inventory.setItem(12, new ItemStack(Material.ITEM_FRAME, 2));
		inventory.setItem(13, new ItemStack(Material.ITEM_FRAME, 3));
		inventory.setItem(14, new ItemStack(Material.ITEM_FRAME, 4));
		inventory.setItem(15, new ItemStack(Material.ITEM_FRAME, 5));
		
		inventory.setItem(20, new ItemStack(Material.AIR, 1));
		inventory.setItem(21, new ItemStack(Material.AIR, 1));
		inventory.setItem(22, new ItemStack(Material.AIR, 1));
		inventory.setItem(23, new ItemStack(Material.AIR, 1));
		inventory.setItem(24, new ItemStack(Material.AIR, 1));
		
		player.openInventory(inventory);
		
		plugin.eFCore.addSkillWindow(player, this);
	}
	
	public void cancelSkillWindow() {
		plugin.eFCore.removeSkillWindow(player);
		
		Inventory inventory = player.getInventory();
		
		manageSkills(inventory);
		
		player.closeInventory();
	}
	
	@SuppressWarnings("deprecation")
	private void manageSkills(Inventory inventory) {
		List<ItemStack> list = new ArrayList<ItemStack>();
		
		if(skill1 != null) {
			list.add(skill1);
			if(skill1.getType() == Material.WRITTEN_BOOK) {
				getSkillType(skill1, "First");
			}
		}
			
		if(skill2 != null) {
			list.add(skill2);
			if(skill2.getType() == Material.WRITTEN_BOOK) {
				getSkillType(skill2, "Second");
			}
		}
			
		if(skill3 != null) {
			list.add(skill3);
			if(skill3.getType() == Material.WRITTEN_BOOK) {
				getSkillType(skill3, "Third");
			}
		}
			
		if(skill4 != null) {
			list.add(skill4);
			if(skill4.getType() == Material.WRITTEN_BOOK) {
				getSkillType(skill4, "Fourth");
			}
		}
			
		if(skill5 != null) {
			list.add(skill5);
			if(skill5.getType() == Material.WRITTEN_BOOK) {
				getSkillType(skill5, "Fifth");
			}
		}			
		
		for(int i = 0; i < list.size(); i++) {
			int slot = player.getInventory().firstEmpty();
			if (slot == -1){
				player.getWorld().dropItem(player.getLocation().add(0, 1, 0), list.get(i));
				player.sendMessage(ChatColor.RED + "Dein Inventar war voll, ein Stapel wurde auf den Boden gelegt.");
				player.updateInventory();
			} else{
				player.getInventory().setItem(slot, list.get(i));
				player.updateInventory();
			}
		}
	}
	
	private void getSkillType(ItemStack skill, String slot) {
		BookMeta meta = (BookMeta) skill.getItemMeta();
		if(meta.getAuthor().equalsIgnoreCase("Kampfschule") && meta.getTitle().equalsIgnoreCase("Handbuch")) {
			User user = plugin.fourWaysAPI.fWCore.getUserClass(player);
			
			String[] userInfo = user.getJobHash().split(",");
			String playerClass = userInfo[1];
			int playerLevel = new Integer(userInfo[0]);
			
			List<String> bookInfo = meta.getLore();
			
			ConfigurationSection skillSection = plugin.skill.getConfigurationSection("Skill");
			
			if(skillSection == null)
				return;
			
			Set<String> skillKeys = skillSection.getKeys(false);
	  	  	String[] skillArray = skillKeys.toArray(new String[0]);
			
			for(int i = 0; i < skillArray.length; i++){
				if(plugin.skill.getString("Skill." + skillArray[i] + ".Name").equalsIgnoreCase(bookInfo.get(1))) {
					String skillClass = plugin.skill.getString("Skill." + skillArray[i] + ".Klasse");
					int skillLevel = plugin.skill.getInt("Skill." + skillArray[i] + ".Stufe", 30);
					
					if(playerClass.contains(skillClass) && playerLevel >= skillLevel) {
						plugin.user.set("User." + player.getName() + ".ActiveSkill." + slot, skillArray[i]);
						plugin.eFUser.saveConfig();
						return;
					} else {
						player.sendMessage("Du erfüllst die Anforderungen dieser Fertigkeit nicht.");
						return;
					}
				}
			}		
			player.sendMessage("Diese Trainingsanweisung ist ungültig.");
		}
	}
	
}