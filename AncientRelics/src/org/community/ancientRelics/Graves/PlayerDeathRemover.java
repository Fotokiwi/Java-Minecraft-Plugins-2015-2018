package org.community.ancientRelics.Graves;

import java.util.List;
import org.bukkit.Location;
import org.bukkit.Material;
import org.community.ancientRelics.ancientRelics;

public class PlayerDeathRemover implements Runnable {

	private List<PlayerDeath> deaths;
	private Location l;

	public PlayerDeathRemover(ancientRelics plugin, List<PlayerDeath> deaths, Location l) {
		this.deaths = deaths;
		this.l = l;
	}

	@Override
	public void run() {
		for (PlayerDeath pd : deaths) {
			if (pd.getL().equals(l)) {
				l.getBlock().setType(Material.AIR);
				deaths.remove(pd);
				break;
			}
		}
	}

}
