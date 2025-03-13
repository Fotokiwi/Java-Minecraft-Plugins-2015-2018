package org.community.EpicFighters.Listener;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.community.EpicFighters.EpicFighters;


public class epicFightersInventoryClickEvent implements Listener{

	private EpicFighters plugin;

	public epicFightersInventoryClickEvent(EpicFighters plugin){
		this.plugin = plugin;
	}

	@EventHandler(priority = EventPriority.NORMAL)
	public void onInventoryClick(InventoryClickEvent event) {
		
		//plugin.getServer().broadcastMessage(event.getRawSlot() + "");
		
		if(!(event.getWhoClicked() instanceof Player))
			return;
		
		Player player = (Player) event.getWhoClicked();
		
		if(event.getInventory().getType() != InventoryType.CHEST || plugin.eFCore.isSkillWindow(player) == false)
			return;
		
		if(event.isShiftClick()) {
			player.sendMessage(ChatColor.RED + "Im Skillenster ist Shiftklick nicht möglich.");
			event.setCancelled(true);
			return;
		}
		
		if(event.getRawSlot() != 20 && event.getRawSlot() != 21 && event.getRawSlot() != 22 && event.getRawSlot() != 23 && event.getRawSlot() != 24 && event.getRawSlot() < 27) {
			event.setCancelled(true);
			return;
		}
		
    }
}
