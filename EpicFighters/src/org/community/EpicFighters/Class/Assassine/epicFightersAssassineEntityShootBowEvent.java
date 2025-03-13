package org.community.EpicFighters.Class.Assassine;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.community.EpicFighters.EpicFighters;

public class epicFightersAssassineEntityShootBowEvent implements Listener {
	
	private EpicFighters plugin;

	public epicFightersAssassineEntityShootBowEvent(EpicFighters plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void onBowShoot(EntityShootBowEvent event) {
		if(!(event.getEntity() instanceof Player))
			return;
		if(!(event.getProjectile() instanceof Arrow))
			return;
		
		Player player = (Player) event.getEntity();
		Arrow arrow = (Arrow) event.getProjectile();
		String skill = plugin.user.getString("User." + player.getName() + ".ActiveSkill." + (plugin.user.getString("User." + player.getName() + ".ActiveSkill.Active")));
		
		if(skill == null) {
        	skill = "Pfeil";
		} else {
			if(!plugin.skill.getString("Skill." + skill + ".Type").equalsIgnoreCase("Pfeil"))
				skill = "Pfeil";
		}
		
		//plugin.getServer().broadcastMessage("Skill: " + skill + " Arrow: " + arrow);
		
		plugin.shotArrows.put(arrow, skill);
		plugin.shotArrowsOwner.put(arrow, player);
	}
	
}