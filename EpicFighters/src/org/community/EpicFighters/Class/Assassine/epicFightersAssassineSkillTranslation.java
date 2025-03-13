package org.community.EpicFighters.Class.Assassine;

import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.community.EpicFighters.EpicFighters;

public class epicFightersAssassineSkillTranslation {
	
	private EpicFighters plugin;

	public epicFightersAssassineSkillTranslation(EpicFighters plugin) {
		this.plugin = plugin;
	}
	
	public void translateSkill(String shortcut, Entity entity, Player player) {
		
		if (shortcut.equals("Feuerpfeil")) {
			if(checkCooldown(shortcut, player)) {
				if(setMana(shortcut, player)) {
					plugin.eFAssassineDamageSkills.fireArrow(entity, plugin.skill.getInt("Skill." + shortcut + ".Option.Duration", 1));
				}				
				return;
			}
			return;
		}
		
	}

	public void translateSkill(String shortcut, Player player) {

		
		if (shortcut.equals("Feuerrad")) {
			if(checkCooldown(shortcut, player)) {
				//plugin.eFAssassineDamageSkills.sendFireballsFromPlayer(player, plugin.skill.getInt("Skill." + shortcut + ".Option.Amount", 1), plugin.skill.getInt("Skill." + shortcut + ".Option.Speed", 1));
				setMana(shortcut, player);
			}
			return;
		}
		
	}

	public boolean translateSkill(String shortcut, Block block, Player player) {

		
		if (shortcut.equals("Spitzer_Pfeil")) {
			if(checkCooldown(shortcut, player)) {
				plugin.eFAssassineDamageSkills.explosiveArrow(block);
				setMana(shortcut, player);
				return true;
			}
			return true;
		}
		return false;
		
	}
	
	private boolean checkCooldown(String shortcut, Player player) {
		if(plugin.cooldown.getString(player.getName() + "." + shortcut) == null) {
			plugin.cooldown.set(player.getName() + "." + shortcut, System.currentTimeMillis());
			return true;
		} else {
			if(System.currentTimeMillis() >= (plugin.cooldown.getLong(player.getName() + "." + shortcut) + plugin.skill.getLong("Skill." + shortcut + ".CooldownInSeconds", 0) * 1000)) {
				plugin.cooldown.set(player.getName() + "." + shortcut, System.currentTimeMillis());
				return true;
			} else {
				return false;
			}
		}
	}
	
	private boolean setMana(String shortcut, Player player) {
		float mana = plugin.skill.getInt("Skill." + shortcut + ".Mana", 2);
		
		if(player.getSaturation() >= mana) {
			player.setSaturation(player.getSaturation() - mana);
			return true;
		} else if(player.getFoodLevel() + player.getSaturation() <= mana){
			return false;
		} else {
			mana = mana - player.getSaturation();
			player.setSaturation(0);
			player.setFoodLevel(player.getFoodLevel() - (int)(mana));
			return true;
		}
	}
	
}