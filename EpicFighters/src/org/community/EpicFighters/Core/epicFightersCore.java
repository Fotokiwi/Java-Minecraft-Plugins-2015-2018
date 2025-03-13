package org.community.EpicFighters.Core;

import java.util.HashMap;
import java.util.Map;
import org.bukkit.entity.Player;
import org.community.EpicFighters.EpicFighters;

public class epicFightersCore {
	
	@SuppressWarnings("unused")
	private EpicFighters plugin;
	
	private Map<Player, epicFightersSkillScreen> skillWindows = new HashMap<Player, epicFightersSkillScreen>();
		
	public epicFightersCore(EpicFighters plugin) {
		this.plugin = plugin;
	}
	
	public void addSkillWindow(Player player, epicFightersSkillScreen trade) {
		skillWindows.put(player, trade);
	}
	
	public epicFightersSkillScreen getSkillWindow(Player player) {
		return skillWindows.get(player);
	}
	
	public boolean isSkillWindow(Player player) {
		if(skillWindows.get(player) != null){
			//plugin.getServer().broadcastMessage("Das Fenster ist registriert!");
			return true;
		}
		
		return false;
	}
	
	public void removeSkillWindow(Player player) {
		//plugin.getServer().broadcastMessage("Das Fenster wurde entfernt!");
		skillWindows.remove(player);
	}

}