package org.community.monsterspawner.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.community.monsterspawner.MonsterSpawner;
import org.community.monsterspawner.Zones.Zones;


public class ZoneCommand {

	private MonsterSpawner plugin;

	public ZoneCommand(MonsterSpawner monsterspawner) {
		this.plugin = monsterspawner;
	}

	public boolean getCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if(sender instanceof Player){
			Player player = (Player) sender;
			if(plugin.isAdmin(player.getUniqueId())){
				if(args.length == 1){
					if(args[0].equalsIgnoreCase("info")){
						if(plugin.mSCore.playerCommand.containsKey(player.getName())){
							String zoneName = plugin.mSCore.playerCommand.get(player.getName());
							for(int i=0; i<plugin.mSCore.zoneList.size(); i++){
								Zones zone = plugin.mSCore.zoneList.get(i);
								if(zone.getZoneName().equalsIgnoreCase(zoneName)){
									player.sendMessage(zone.getCompleteInfo());
								}
							}
						}
						else{
							player.sendMessage("Du hast keine Zone ausgew�hlt!");
						}
						return true;
					}
					else if(args[0].equalsIgnoreCase("spawner")){
						if(plugin.mSCore.playerCommand.containsKey(player.getName())){
							String zoneName = plugin.mSCore.playerCommand.get(player.getName());
							for(int i=0; i<plugin.mSCore.zoneList.size(); i++){
								Zones zone = plugin.mSCore.zoneList.get(i);
								if(zone.getZoneName().equalsIgnoreCase(zoneName)){
									player.sendMessage("Spawner dieser Zone ("+zone.getZoneName()+") :");
									player.sendMessage(zone.ListSpawners());
								}
							}
						}
						else{
							player.sendMessage("Du hast keine Zone ausgew�hlt!");
						}
						return true;

					}
					else if(args[0].equalsIgnoreCase("position")){
						if(plugin.mSCore.playerCommand.containsKey(player.getName())){
							String zoneName = plugin.mSCore.playerCommand.get(player.getName());
							for(int i=0; i<plugin.mSCore.zoneList.size(); i++){
								Zones zone = plugin.mSCore.zoneList.get(i);
								if(zone.getZoneName().equalsIgnoreCase(zoneName)){
									zone.putBlockX(player.getLocation().getBlockX());
									zone.putBlockY(player.getLocation().getBlockY());
									zone.putBlockZ(player.getLocation().getBlockZ());
									zone.putChunkX(player.getLocation().getChunk().getX());
									zone.putChunkZ(player.getLocation().getChunk().getZ());
									zone.putWorldName(player.getWorld().getName());
									player.sendMessage("Zonenpunkt an deine Position verschoben!");
									return true;
								}
							}
						}
						else{
							player.sendMessage("Du hast keine Zone ausgew�hlt!");
						}
						return true;
					}
					else if(args[0].equalsIgnoreCase("help")){
						player.sendMessage("==================================================");
						player.sendMessage(ChatColor.GOLD+"/zone info"+ChatColor.WHITE+" gibt die Parameter der Zone aus.");
						player.sendMessage(ChatColor.GOLD+"/zone spawner"+ChatColor.WHITE+" gibt die Spawner der Zone aus.");
						player.sendMessage(ChatColor.GOLD+"/zone position"+ChatColor.WHITE+" Verschiebt eine Zone an deine Position.");
						player.sendMessage(ChatColor.GOLD+"/zone create NAME"+ChatColor.WHITE+" erstellt eine neue Zone mit angegebenem Namen.");
						player.sendMessage(ChatColor.GOLD+"/zone select NAME"+ChatColor.WHITE+" w�hlt eine Zone aus.");
						player.sendMessage(ChatColor.GOLD+"/zone scalespawn (oder sp) MULTIPLIKATOR"+ChatColor.WHITE+" Aktiviert skalierende Spawns mit dem Multiplikator");
						player.sendMessage(ChatColor.GOLD+"/zone scalehealth (oder sh) MULTIPLIKATOR"+ChatColor.WHITE+" Aktiviert skalierende Lebenspunkte mit dem Multiplikator");
						player.sendMessage(ChatColor.GOLD+"/zone scaledamage (oder sd) MULTIPLIKATOR"+ChatColor.WHITE+" Aktiviert skalierenden Schaden mit dem Multiplikator");
						player.sendMessage("==================================================");
						return true;
					}
					else{
						player.sendMessage("Ung�ltiges Kommando! gib "+ChatColor.GOLD+" /zone help"+ChatColor.WHITE+" f�r mehr Information ein!");
						return true;
					}
				}
				if(args.length == 2) {
					if(args[0].equalsIgnoreCase("create")){
						String zoneName = args[1].toString();
						for(int i=0; i<plugin.mSCore.zoneList.size(); i++){
							Zones zone = plugin.mSCore.zoneList.get(i);
							if(zone.getZoneName().equalsIgnoreCase(zoneName)){
								player.sendMessage("Zone mit dem Namen: "+ChatColor.RED+zoneName+ChatColor.WHITE+" existiert schon!");
								player.sendMessage("Vorgang wird abgebrochen !");
								return true;
							}
						}
						Zones zone = new Zones(plugin, zoneName, true);
						plugin.mSCore.playerCommand.put(player.getName(), zoneName);
						zone.putZoneName(zoneName);
						zone.putDynamicScaleTrue(false);
						zone.putDynamicScale(1);
						zone.putDynamicDamageTrue(false);
						zone.putDynamicDamage(1);
						zone.putDynamicHealthTrue(false);
						zone.putDynamicHealth(1);
						zone.putWorldName(player.getWorld().getName());
						zone.putBlockX(player.getLocation().getBlockX());
						zone.putBlockY(player.getLocation().getBlockY());
						zone.putBlockZ(player.getLocation().getBlockZ());
						zone.putChunkX(player.getLocation().getChunk().getX());
						zone.putChunkZ(player.getLocation().getChunk().getZ());
						player.sendMessage("Zone "+zoneName+" erfolgreich angelegt und ausgew�hlt!");
						return true;
					}
					else if(args[0].equalsIgnoreCase("select")){
						String zoneName = args[1].toString();
						if(plugin.mSCore.playerCommand.containsKey(player.getName())){
							plugin.mSCore.playerCommand.remove(player.getName());
						}
						for(int i=0; i<plugin.mSCore.zoneList.size(); i++){
							Zones zone = plugin.mSCore.zoneList.get(i);
							if(zone.getZoneName().equalsIgnoreCase(zoneName)){
								plugin.mSCore.playerCommand.put(player.getName(), zoneName);
								player.sendMessage("Zone "+zoneName+" erfolgreich ausgew�hlt!");
							}
						}
						if(!plugin.mSCore.playerCommand.containsKey(player.getName())){
							player.sendMessage(zoneName+" gibt es nicht!");
						}
						return true;

					}
					else if(args[0].equalsIgnoreCase("scalespawn") || args[0].equalsIgnoreCase("sp")){
						if(!plugin.mSCore.playerCommand.containsKey(player.getName())){
							player.sendMessage("Du hast keine Zone ausgew�hlt!");
						}
						else if(plugin.mSCore.playerCommand.containsKey(player.getName())){
							String zoneName = plugin.mSCore.playerCommand.get(player.getName());
							for(int i=0; i<plugin.mSCore.zoneList.size(); i++){
								Zones zone = plugin.mSCore.zoneList.get(i);
								if(zone.getZoneName().equalsIgnoreCase(zoneName)){
									try{
										int scalemulti = Integer.parseInt(args[1]);
										zone.putDynamicScaleTrue(true);
										zone.putDynamicDamage(scalemulti);
										player.sendMessage("Spawn Skalierung aktiviert mit Multiplier: "+scalemulti);
									}
									catch(NumberFormatException Ex){
										player.sendMessage("Angegebener Multiplikator muss eine Zahl sein!");
									}


								}
							}
						}
						return true;
					}
					else if(args[0].equalsIgnoreCase("scalehealth") || args[0].equalsIgnoreCase("sh")){
						if(!plugin.mSCore.playerCommand.containsKey(player.getName())){
							player.sendMessage("Du hast keine Zone ausgew�hlt!");
						}
						else if(plugin.mSCore.playerCommand.containsKey(player.getName())){
							String zoneName = plugin.mSCore.playerCommand.get(player.getName());
							for(int i=0; i<plugin.mSCore.zoneList.size(); i++){
								Zones zone = plugin.mSCore.zoneList.get(i);
								if(zone.getZoneName().equalsIgnoreCase(zoneName)){
									try{
										int scalemulti = Integer.parseInt(args[1]);
										zone.putDynamicHealthTrue(true);
										zone.putDynamicHealth(scalemulti);
										player.sendMessage("Lebens Skalierung aktiviert mit Multiplier: "+scalemulti);
									}
									catch(NumberFormatException Ex){
										player.sendMessage("Angegebener Multiplikator muss eine Zahl sein!");
									}


								}
							}
						}
						return true;
					}
					else if(args[0].equalsIgnoreCase("scaledamage") || args[0].equalsIgnoreCase("sd")){
						if(!plugin.mSCore.playerCommand.containsKey(player.getName())){
							player.sendMessage("Du hast keine Zone ausgew�hlt!");
						}
						else if(plugin.mSCore.playerCommand.containsKey(player.getName())){
							String zoneName = plugin.mSCore.playerCommand.get(player.getName());
							for(int i=0; i<plugin.mSCore.zoneList.size(); i++){
								Zones zone = plugin.mSCore.zoneList.get(i);
								if(zone.getZoneName().equalsIgnoreCase(zoneName)){
									try{
										int scalemulti = Integer.parseInt(args[1]);
										zone.putDynamicDamageTrue(true);
										zone.putDynamicDamage(scalemulti);
										player.sendMessage("Schadens Skalierung aktiviert mit Multiplier: "+scalemulti);
									}
									catch(NumberFormatException Ex){
										player.sendMessage("Angegebener Multiplikator muss eine Zahl sein!");
									}


								}
							}
						}
						return true;
					}
					else{
						player.sendMessage("Ung�ltiges Kommando! gib "+ChatColor.GOLD+" /zone help"+ChatColor.WHITE+" f�r mehr Information ein!");
						return true;
					}
				}
			}
			else{
				player.sendMessage("Keine Berechtigung f�r diesen Befehl!");
			}
		}
		else{
			//put console commands here
		}
		return false;
	}



}