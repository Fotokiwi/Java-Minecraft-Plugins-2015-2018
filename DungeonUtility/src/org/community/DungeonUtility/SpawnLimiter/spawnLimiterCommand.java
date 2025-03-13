package org.community.DungeonUtility.SpawnLimiter;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.community.DungeonUtility.*;

public class spawnLimiterCommand {
		
	private final DungeonUtility plugin;
	 	 
	public spawnLimiterCommand(DungeonUtility plugin)
	{
		this.plugin = plugin;
	}
	
	public boolean getCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		
		int animals = 0;
		int monsters = 0;
		int aqua = 0;
		int tamable = 0;
		
		Player player = (Player) sender;
		
		if(!isAdmin(player))
			return true;
		
		if(cmd.getName().equalsIgnoreCase("spawnlimiter")){
			
			if(args.length == 0) {
				showPluginInfo(sender);
			}
			
			if(args.length == 1) {
				if(args[0].equalsIgnoreCase("cleanmobs")) {
					List<Entity> entities = player.getNearbyEntities(160, 64, 160);
					int count = 0;
					for(int i = 0; i < entities.size(); i++) {
						Entity entity = entities.get(i);
						if(entity.getType() == EntityType.COW || entity.getType() == EntityType.PIG || entity.getType() == EntityType.SHEEP || entity.getType() == EntityType.CHICKEN || entity.getType() == EntityType.HORSE || entity.getType() == EntityType.IRON_GOLEM) {
							entity.remove();
							count++;
						}						
					}
					player.sendMessage(count + " Entities entfernt.");
				}
				if(args[0].equalsIgnoreCase("server")) {
					List<World> worlds = plugin.getServer().getWorlds();
					
					player.sendMessage(ChatColor.GOLD + "----------------------------------");
					player.sendMessage(ChatColor.GOLD + "Mode: " + plugin.config.getString("Config.Mode", "player"));
					player.sendMessage(ChatColor.GOLD + "Players: " + plugin.activePlayers + " (Wert hat eine Zeitversetzung)");
					player.sendMessage(ChatColor.GOLD + "Chunks: " + plugin.activeChunks + " (nur im Chunk-Modus sichtbar)");
					player.sendMessage(ChatColor.GOLD + "Radius: " + plugin.radius + " (Wert hat eine Zeitversetzung)");
					
					int animalsAll = 0;
					int monstersAll = 0;
					int aquaAll = 0;
					int tamableAll = 0;
					
					for(int i = 0; i < worlds.size(); i++) {
						List<LivingEntity> creatureList = worlds.get(i).getLivingEntities();
						
						animals = 0;
						monsters = 0;
						aqua = 0;
						tamable = 0;
						
						for (int m=0; m < creatureList.size(); m++){
							if(creatureList.get(m).getType() == EntityType.CHICKEN || creatureList.get(m).getType() == EntityType.COW || creatureList.get(m).getType() == EntityType.MUSHROOM_COW || creatureList.get(m).getType() == EntityType.PIG || creatureList.get(m).getType() == EntityType.SHEEP) {
								animals++;
								animalsAll++;
							}
							if(creatureList.get(m).getType() == EntityType.BLAZE || creatureList.get(m).getType() == EntityType.CAVE_SPIDER || creatureList.get(m).getType() == EntityType.CREEPER || creatureList.get(m).getType() == EntityType.ENDERMAN || creatureList.get(m).getType() == EntityType.GHAST || creatureList.get(m).getType() == EntityType.MAGMA_CUBE || creatureList.get(m).getType() == EntityType.PIG_ZOMBIE || creatureList.get(m).getType() == EntityType.SILVERFISH || creatureList.get(m).getType() == EntityType.SKELETON || creatureList.get(m).getType() == EntityType.SLIME || creatureList.get(m).getType() == EntityType.SPIDER || creatureList.get(m).getType() == EntityType.ZOMBIE) {
								monsters++;
								monstersAll++;
							}
							if(creatureList.get(m).getType() == EntityType.SQUID) {
								aqua++;
								aquaAll++;
							}
							if(creatureList.get(m).getType() == EntityType.OCELOT || creatureList.get(m).getType() == EntityType.WOLF) {
								tamable++;
								tamableAll++;
							}
						}
						player.sendMessage(ChatColor.GOLD + "-----" + worlds.get(i).getName() + "-----");
						player.sendMessage(ChatColor.GOLD + "Total: " + ChatColor.WHITE + (animals+monsters+aqua+tamable));
						player.sendMessage(ChatColor.GOLD + "Animals: " + ChatColor.WHITE + (animals));
						player.sendMessage(ChatColor.GOLD + "Monsters: " + ChatColor.WHITE + (monsters));
						player.sendMessage(ChatColor.GOLD + "Aqua: " + ChatColor.WHITE + (aqua));
						player.sendMessage(ChatColor.GOLD + "Tamable: " + ChatColor.WHITE + (tamable));
						player.sendMessage(ChatColor.GOLD + "----------------------------------");
					}
					player.sendMessage(ChatColor.GOLD + "-----Server-Gesamt-----");
					player.sendMessage(ChatColor.GOLD + "Total: " + ChatColor.WHITE + (animalsAll+monstersAll+aquaAll+tamableAll));
					player.sendMessage(ChatColor.GOLD + "Animals: " + ChatColor.WHITE + (animalsAll));
					player.sendMessage(ChatColor.GOLD + "Monsters: " + ChatColor.WHITE + (monstersAll));
					player.sendMessage(ChatColor.GOLD + "Aqua: " + ChatColor.WHITE + (aquaAll));
					player.sendMessage(ChatColor.GOLD + "Tamable: " + ChatColor.WHITE + (tamableAll));
					player.sendMessage(ChatColor.GOLD + "----------------------------------");
				}
				if(args[0].equalsIgnoreCase("world")) {

					player.sendMessage(ChatColor.GOLD + "----------------------------------");
					player.sendMessage(ChatColor.GOLD + "Mode: " + plugin.config.getString("Config.Mode", "player"));
					player.sendMessage(ChatColor.GOLD + "Players: " + plugin.activePlayers + " (Wert hat eine Zeitversetzung)");
					player.sendMessage(ChatColor.GOLD + "Chunks: " + plugin.activeChunks + " (nur im Chunk-Modus sichtbar)");
					player.sendMessage(ChatColor.GOLD + "Radius: " + plugin.radius + " (Wert hat eine Zeitversetzung)");

					List<LivingEntity> creatureList = player.getWorld().getLivingEntities();

					animals = 0;
					monsters = 0;
					aqua = 0;
					tamable = 0;
					
					for (int m=0; m < creatureList.size(); m++){
						
						if(creatureList.get(m).getType() == EntityType.CHICKEN || creatureList.get(m).getType() == EntityType.COW || creatureList.get(m).getType() == EntityType.MUSHROOM_COW || creatureList.get(m).getType() == EntityType.PIG || creatureList.get(m).getType() == EntityType.SHEEP) {
							animals++;
						}
						if(creatureList.get(m).getType() == EntityType.BLAZE || creatureList.get(m).getType() == EntityType.CAVE_SPIDER || creatureList.get(m).getType() == EntityType.CREEPER || creatureList.get(m).getType() == EntityType.ENDERMAN || creatureList.get(m).getType() == EntityType.GHAST || creatureList.get(m).getType() == EntityType.MAGMA_CUBE || creatureList.get(m).getType() == EntityType.PIG_ZOMBIE || creatureList.get(m).getType() == EntityType.SILVERFISH || creatureList.get(m).getType() == EntityType.SKELETON || creatureList.get(m).getType() == EntityType.SLIME || creatureList.get(m).getType() == EntityType.SPIDER || creatureList.get(m).getType() == EntityType.ZOMBIE) {
							monsters++;
						}
						if(creatureList.get(m).getType() == EntityType.SQUID) {
							aqua++;
						}
						if(creatureList.get(m).getType() == EntityType.OCELOT || creatureList.get(m).getType() == EntityType.WOLF) {
							tamable++;
						}
					}
					player.sendMessage(ChatColor.GOLD + "-----" + player.getWorld().getName() + "-----");
					player.sendMessage(ChatColor.GOLD + "Total: " + ChatColor.WHITE + (animals+monsters+aqua+tamable));
					player.sendMessage(ChatColor.GOLD + "Animals: " + ChatColor.WHITE + (animals));
					player.sendMessage(ChatColor.GOLD + "Monsters: " + ChatColor.WHITE + (monsters));
					player.sendMessage(ChatColor.GOLD + "Aqua: " + ChatColor.WHITE + (aqua));
					player.sendMessage(ChatColor.GOLD + "Tamable: " + ChatColor.WHITE + (tamable));
					player.sendMessage(ChatColor.GOLD + "----------------------------------");
				}
			}
			
			
		}
		
		return false;
	}
	
	private void showPluginInfo(CommandSender sender){
		sender.sendMessage(ChatColor.GREEN + plugin.logprefix);
	}
	
	public boolean isAdmin(Player player) {
		if(plugin.config.getList("Config.Admins") != null) {
			if(plugin.config.getList("Config.Admins").contains(player.getUniqueId().toString()))
				return true;
		}
		
		return false;
	}
	
}