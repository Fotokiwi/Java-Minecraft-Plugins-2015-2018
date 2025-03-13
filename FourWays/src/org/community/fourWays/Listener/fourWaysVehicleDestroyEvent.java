package org.community.fourWays.Listener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Vehicle;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.vehicle.VehicleDestroyEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.community.fourWays.fourWays;

public class fourWaysVehicleDestroyEvent implements Listener {

	private fourWays plugin;

	private static float dmgForPlayerPassenger = 10.0f;
	private static float dmgForNonPlayerPassenger = 30.0f;

	public fourWaysVehicleDestroyEvent(fourWays plugin) {
		this.plugin = plugin;
	}

	@EventHandler(priority = EventPriority.NORMAL)
	public void onVehicleDestroy(VehicleDestroyEvent e) {

		if (e.getVehicle().getType() == EntityType.BOAT) {
			Vehicle boat = e.getVehicle();
			Location loc1 = boat.getLocation();
			Player p = null;
			if (boat.getPassenger() instanceof Player) {
				p = (Player) boat.getPassenger();
			}
			if(!plugin.fWCore.bootCounter.containsKey(boat.getUniqueId()))
				plugin.fWCore.bootCounter.put(boat.getUniqueId(), 150.0f);
			float newHealth = plugin.fWCore.bootCounter.get(boat.getUniqueId())
					- (p != null ? dmgForPlayerPassenger : dmgForNonPlayerPassenger);
			if (newHealth <= 0.0f) {
				plugin.fWCore.bootCounter.remove(boat.getUniqueId());
				if (p != null)
					p.sendMessage(bootDown());
				else {
					for (Entity ent : boat.getNearbyEntities(5, 5, 5)) {
						if (ent instanceof Player)
							((Player) ent)
									.sendMessage(bootDown());
					}
				}
				boat.eject();
				boat.remove();

			} else {
				if (p == null) {
					plugin.fWCore.bootCounter.remove(boat.getUniqueId());
					for (Entity ent : boat.getNearbyEntities(5, 5, 5)) {
						if (ent instanceof Player)
							((Player) ent).sendMessage(statusBoat(newHealth));
					}
					ItemStack is = new ItemStack(Material.BOAT);
					List<String> l = new ArrayList<String>();
					l.add("Haltbarkeit");
					l.add(newHealth + "");
					ItemMeta im = is.getItemMeta();
					im.setDisplayName("Calad-Amar Boot");
					im.setLore(l);
					is.setItemMeta(im);
					loc1.getWorld().dropItem(loc1, is);
					boat.remove();

				} else {
					plugin.fWCore.bootCounter.put(boat.getUniqueId(), newHealth);
					p.sendMessage(statusBoat(newHealth));
				}
			}
			e.setCancelled(true);
		}
	}

	public String statusBoat(float f) {
		Random r = new Random();
		String[] hp14 = { "Puh, das ist ja gerade noch mal gut gegangen, dein Boot ist unbeschädigt.",
				"Nur ein leichter Aufprall, das Boot hat ihn gut weggesteckt.",
				"Dein Boot schrammte etwas hart an einer harten Kante. Nix passiert!",
				"Diesen leichten Stoß hat dein Boot gut verkraftet." };
		String[] hp13 = { "Das gibt eine Schmarre im Boot. Der Lack ist angestoßen.",
				"Ein lautes Kratzen verrät dir, dass dein Boot jetzt eine Schramme ziert.",
				"Das ist der erste Kratzer in deinem niegelnagelneuem Boot.", "Ein Kratzer im Boot. Na wunderbar..." };
		String[] hp12 = { "Du hast deinem Boot einen weiteren Schmiss hinzugefügt.",
				"Das war der zweite Kratzer in deinem Boot. Unschön.",
				"Diesen Aufprall fand der Lack deines Bootes nicht so gut.",
				"Nichts mehr mit Neu-Boot. Zwei Kratzer schon." };
		String[] hp11 = { "Da ist ein Stück von deinem Boot abgesplittert.",
				"Das ist der erste nennenswerte Schaden an deinem Boot. Den sieht man sogar von weiter weg.",
				"Beim Umkippen mit dem Boot hast du eines der Ruder beschädigt.",
				"Das ist der erste nennenswerte Schaden an deinem Boot." };
		String[] hp10 = { "Dein Boot hat wieder eine schadhafte Stelle mehr.",
				"Der Marktpreis deines Bootes ist erneut gesunken. Kategorie: Unfallschiff",
				"Das war ein lauterer Aufprall. Das Schiff wurde etwas weiter beschädigt.",
				"Deine unsanfte Fahrweise hat dein Boot wieder etwas mehr zerkratzt." };
		String[] hp9 = { "Dieser Aufprall war heftig. Die Sitzfläche deines Bootes wackelt nun.",
				"Ein unsanfter Stoß. Quittiert durch eine angebrochene Planke in deinem Boot.",
				"Dieser Aufprall ließ deinen kleinen Anker über Board gehen. Der ist weg... ",
				"Dein Gewicht hat beim Aufprall die Sitzbank deines Bootes beschädigt." };
		String[] hp8 = { "Wie es aussieht hast du ein winziges Leck im Boot.",
				"Der letzte Aufprall hat dein Boot ein wenig undicht gemacht.",
				"Es sammeln sich mehrere Fingerhut Wasser zwischen deinen Füßen. Das Boot ist leicht leck.",
				"Durch eine Plankenritze dringt ein Rinnsal Wasser in dein Boot." };
		String[] hp7 = { "Dieses Knirschen deines Bootes gefiel dir gar nicht.", "Dein Boot knarzt ungewöhnlich laut.",
				"Während dieses Stoßes machte dein Boot unangenehme Geräusche.",
				"Neben dem Knall des Aufpralls hörst du ein besorgnisergendes Knarzen deines Bootes." };
		String[] hp6 = { "Wieder etwas von der Außenwand deines Bootes abgesplittert...",
				"Wo eben noch eine glatte Planke war, ist nun ein unschöner Bruch zu sehen.",
				"Du kannst Stückchen deine Bootes im Wasser treiben sehen.",
				"Es ist ein Stück deiner Boardwand abgebrochen bei dem heftigen Aufschlag." };
		String[] hp5 = { "Dein Boot hat diesen Aufprall nur knapp überstanden.",
				"Du bist dir nicht sicher, welchen Schaden dein Boot genommen haben könnte.",
				"Das war heftig. Ein wunder, dass das Boot noch in einem Stück ist.",
				"Du misstraust deinem Boot zunehmend." };
		String[] hp4 = { "Eine Ruderhalterung hat sich gelöst. Dein Boot lässt sich schlechter steuern als zuvor.",
				"Irgendwie sieht dein Boot nach diesem Aufprall 'schief' aus.",
				"Stellenweise dringt Hanf aus den Planken deines Bootes. Die Mängel nehmen zu.",
				"Dieses Boot kannst du wohl nur noch verschenken. Es sieht mittlerweile arg lediert aus." };
		String[] hp3 = { "Ein weiteres Leck. Es läuft immer wieder Wasser in das Schiff.",
				"Es sammelt sich scheinbar schneller Wasser in deinem Boot. Nicht gut...",
				"Durch einige Ritzen dringt Tröpfchen für Tröpfchen mehr Wasser in dein Boot.",
				"Der Dichtigkeits-Index deiner archimedischen Schwimmvorrichtung hat sich erneut erniedrigt." };
		String[] hp2 = { "Es fehlt ein Stück in der Railing. Lange macht es dein Boot nicht mehr.",
				"So langsam übersteht dein Boot diese Fahrweise nicht mehr lange.",
				"Boots Qualität - 10 ... Bitte suchen sie den Service auf!",
				"Die gesamte Ruderhalterung ist aus deinem Boot gebrochen. Nun heißt's Stechpaddeln." };
		String[] hp1 = { "Nur mit Glück hat dein Boot diesen Aufprall überlebt. Es ist in sehr schlechtem Zustand.",
				"Fortan verbringst du mehr Zeit damit Wasser aus deinem Boot zu schippen, als zu rudern.",
				"Dieses Boot taugt fast nur noch als Feuerholz.",
				"Du traust dich nur noch an guten tagen in diese Bootsreste." };

		if (f >= 140.0f)
			return hp14[r.nextInt(hp14.length)];
		else if (f >= 130.0f)
			return hp13[r.nextInt(hp13.length)];
		else if (f >= 120.0f)
			return hp12[r.nextInt(hp12.length)];
		else if (f >= 110.0f)
			return hp11[r.nextInt(hp11.length)];
		else if (f >= 100.0f)
			return hp10[r.nextInt(hp10.length)];
		else if (f >= 90.0f)
			return hp9[r.nextInt(hp9.length)];
		else if (f >= 80.0f)
			return hp8[r.nextInt(hp8.length)];
		else if (f >= 70.0f)
			return hp7[r.nextInt(hp7.length)];
		else if (f >= 60.0f)
			return hp6[r.nextInt(hp6.length)];
		else if (f >= 50.0f)
			return hp5[r.nextInt(hp5.length)];
		else if (f >= 40.0f)
			return hp4[r.nextInt(hp4.length)];
		else if (f >= 30.0f)
			return hp3[r.nextInt(hp3.length)];
		else if (f >= 20.0f)
			return hp2[r.nextInt(hp2.length)];
		else
			return hp1[r.nextInt(hp1.length)];
		

	}

	public String bootDown() {
		Random r = new Random();
		String[] bootDown = { "Dein Boot ist endgültig unter gegangen. Schade...",
				"Dein Boot ist ein Wrack, das fährt nirgendwo mehr hin.",
				"Mit einem krachen springen die Planken deines Bootes entzwei.", "'Kazosch' und kaputt ist das Boot.",
				"Dein Boot ist nun endgültig Schrottreif.", "Die Reste deines Bootes taugen nicht mal mehr als Floß.",
				"Nicht einmal Streitwagen-Tape könnte dein Boot noch retten.", "Dieses Boot ist hin. Mist!" };
		return bootDown[r.nextInt(bootDown.length)];
	}

}
