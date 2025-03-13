package org.community.ambience.AmbientObjects.Listener;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.community.ambience.*;
import org.community.ambience.AmbientObjects.dataObjects.Blueprint;

public class playerInteractEvent implements Listener {

	private final Ambience plugin;

	public playerInteractEvent(Ambience plugin) {
		this.plugin = plugin;
	}

	@SuppressWarnings("deprecation")
	@EventHandler(priority = EventPriority.HIGHEST)
	public void PlayerInteract(PlayerInteractEvent event) {

		if (event.getAction() != Action.RIGHT_CLICK_BLOCK)
			return;
		if (event.getPlayer().getItemInHand().getType() != Material.WRITTEN_BOOK)
			return;

		ItemStack book = event.getPlayer().getItemInHand();
		BookMeta meta = (BookMeta) book.getItemMeta();

		if (meta.getAuthor().equalsIgnoreCase("Ambiente Gegenstand")) {

			event.setCancelled(true);
			Player player = event.getPlayer();
			player.closeInventory();
			String bookObject = meta.getTitle();
			
			if(!player.getWorld().getName().equalsIgnoreCase("Utopia") || plugin.newSettlersAPI.nSAPI.getChunkTown(player.getLocation().getChunk()) != null) {
				event.setCancelled(true);
				player.sendMessage("Auf dieser Welt kannst du keine Ambiente-Objekte platzieren.");
				return;
			}
			
			if (bookObject.contains("Rezept")) {

			} else {
				Location buildLocation = player.getTargetBlock(null, 3).getLocation();

				if (plugin.cache.blueprintExists(bookObject)) {
					Blueprint bp = plugin.cache.getBlueprintByName(bookObject);
					bp.initiateAmbientObjecte(player, buildLocation);
					
				} else {
					switch (bookObject) {

					case "Kleines Lagerfeuer":
						plugin.ambienceKleinesLagerfeuer.initiateFire(player, buildLocation);
						break;
					case "Kleines Zelt":
						plugin.ambienceKleinesZelt.initiateSmallTent(player, buildLocation);
						break;
					case "Mittleres Zeltlager":
						plugin.ambienceMittleresZeltlager.initiateSmallTent(player, buildLocation);
						break;

					default:
						player.sendMessage(ChatColor.DARK_RED + "Das ausgewählte Ambiente-Set ist nicht gültig.");
						player.sendMessage(ChatColor.DARK_RED + "Bitte wende dich an einen Administrator oder Moderator.");
						break;
					}
				}
			}

		}

	}

}