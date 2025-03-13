package org.community.ancientRelics.Graves;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.community.ancientRelics.ancientRelics;



public class Graves {
	
	private Map<UUID, List<PlayerDeath>> deaths;
	private ancientRelics plugin;
	
	public Graves(ancientRelics plugin)
	{
		this.plugin = plugin;
		deaths = new HashMap<UUID, List<PlayerDeath>>();
	}
	
	public void playerRemoveDeath(Player p, Location l){
		List<PlayerDeath> pd = deaths.get(p.getUniqueId());
		if(pd == null)
			return;
		for(PlayerDeath death : pd){
			if(death.getL().equals(l)){
				death.dropInventoryAtPosition(l);
				Bukkit.getScheduler().runTask(plugin,new PlayerDeathRemover(plugin, pd, l));
				break;
			}
		}
				
	}
	
	public void addDeath(UUID id, PlayerDeath pd){
		
		if(deaths.containsKey(id)){
			deaths.get(id).add(pd);
		}
		else 
		{
			List<PlayerDeath> list = new ArrayList<PlayerDeath>();
			list.add(pd);
			deaths.put(id, list);
		}
		Bukkit.getScheduler().runTaskLater(plugin,new PlayerDeathRemover(plugin, deaths.get(id), pd.getL()), 60 * 60 * 20L);
		pd.getL().getBlock().setType(Material.SIGN);
		Sign sign = (Sign) pd.getL().getBlock().getState();
		sign.setLine(0, "Hier verstarb ");
		sign.setLine(1, pd.getPlayerName());
	}
	
	public void removeAllDeaths(){
		for(Entry<UUID, List<PlayerDeath>> e : deaths.entrySet()){
			for(PlayerDeath pd : e.getValue())
				Bukkit.getScheduler().runTask(plugin,new PlayerDeathRemover(plugin, e.getValue(), pd.getL()));
		}
	}
}
