package org.community.EpicFighters.Core;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.community.EpicFighters.EpicFighters;

public class epicFightersScrollActiveSkill implements Listener {
	
	private EpicFighters plugin;

	public epicFightersScrollActiveSkill(EpicFighters plugin) {
		this.plugin = plugin;
	}
	
	public void changeActiveSpell(Player player) {
		if(plugin.user.getString("User." + player.getName() + ".ActiveSkill.Active") == null) {
			plugin.user.set("User." + player.getName() + ".ActiveSkill.Active", "Clean");
		}
		if(plugin.user.getString("User." + player.getName() + ".ActiveSkill.Active").equalsIgnoreCase("First")) {
			plugin.user.set("User." + player.getName() + ".ActiveSkill.Active", "Second");
			String skill = plugin.user.getString("User." + player.getName() + ".ActiveSkill.Second");
			player.sendMessage("(2) - " + plugin.skill.getString("Skill." + skill + ".Name"));
		} else if(plugin.user.getString("User." + player.getName() + ".ActiveSkill.Active").equalsIgnoreCase("Second")) {
			plugin.user.set("User." + player.getName() + ".ActiveSkill.Active", "Third");
			String skill = plugin.user.getString("User." + player.getName() + ".ActiveSkill.Third");
			player.sendMessage("(3) - " + plugin.skill.getString("Skill." + skill + ".Name"));
		} else if(plugin.user.getString("User." + player.getName() + ".ActiveSkill.Active").equalsIgnoreCase("Third")) {
			plugin.user.set("User." + player.getName() + ".ActiveSkill.Active", "Fourth");
			String skill = plugin.user.getString("User." + player.getName() + ".ActiveSkill.Fourth");
			player.sendMessage("(4) - " + plugin.skill.getString("Skill." + skill + ".Name"));
		} else if(plugin.user.getString("User." + player.getName() + ".ActiveSkill.Active").equalsIgnoreCase("Fourth")) {
			plugin.user.set("User." + player.getName() + ".ActiveSkill.Active", "Fifth");
			String skill = plugin.user.getString("User." + player.getName() + ".ActiveSkill.Fifth");
			player.sendMessage("(5) - " + plugin.skill.getString("Skill." + skill + ".Name"));
		} else if(plugin.user.getString("User." + player.getName() + ".ActiveSkill.Active").equalsIgnoreCase("Fifth")) {
			plugin.user.set("User." + player.getName() + ".ActiveSkill.Active", "Clean");
			player.sendMessage("(-) - Standardangriff");
		} else if(plugin.user.getString("User." + player.getName() + ".ActiveSkill.Active").equalsIgnoreCase("Clean")) {
			plugin.user.set("User." + player.getName() + ".ActiveSkill.Active", "First");
			String skill = plugin.user.getString("User." + player.getName() + ".ActiveSkill.First");
			player.sendMessage("(1) - " + plugin.skill.getString("Skill." + skill + ".Name"));
		} else {
			plugin.user.set("User." + player.getName() + ".ActiveSkill.Active", "First");
			String skill = plugin.user.getString("User." + player.getName() + ".ActiveSkill.First");
			player.sendMessage("(1) - " + plugin.skill.getString("Skill." + skill + ".Name"));
		}
	}
	
}