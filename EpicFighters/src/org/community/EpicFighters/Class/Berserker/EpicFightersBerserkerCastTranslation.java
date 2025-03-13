package org.community.EpicFighters.Class.Berserker;

import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.community.EpicFighters.EpicFighters;

public class EpicFightersBerserkerCastTranslation {

	private EpicFighters plugin;

	public EpicFightersBerserkerCastTranslation(EpicFighters plugin)
	{
		this.plugin = plugin;
	}
	
	public void translateSpell(String shortcut, Player player, Entity entity) {
		if (shortcut.equals("Test_Schlag")) {
			if(checkCooldown(shortcut, player)) {
				if(setMana(shortcut, player)) {
					plugin.eFBerserkerDamageSpells.testSwordSkill(player, entity);
				}				
				return;
			}
			return;
		}
		
	}
	
	public void translateSpell(String shortcut, Player player) {
		
	}
	

	public void translateSpell(String shortcut, Player player, Block block) {
		
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
