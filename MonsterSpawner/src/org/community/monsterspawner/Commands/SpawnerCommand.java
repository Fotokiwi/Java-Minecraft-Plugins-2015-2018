package org.community.monsterspawner.Commands;


import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.community.monsterspawner.MonsterSpawner;
import org.community.monsterspawner.Zones.Zones;
import org.community.monsterspawner.spawner.Spawner;



public class SpawnerCommand {

	private MonsterSpawner plugin;

	public SpawnerCommand(MonsterSpawner monsterspawner) {
		this.plugin = monsterspawner;
	}

	public boolean getCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if(sender instanceof Player){
			Player player = (Player) sender;
			if(plugin.isAdmin(player.getUniqueId())){
				if(!plugin.mSCore.playerCommand.containsKey(player.getName())){
					player.sendMessage("Du musst erst eine Zone ausw�hlen!");
					return true;
				}
				String zoneName = plugin.mSCore.playerCommand.get(player.getName());
				for(int i=0; i<plugin.mSCore.zoneList.size(); i++){
					Zones zone = plugin.mSCore.zoneList.get(i);
					if(zone.getZoneName().equalsIgnoreCase(zoneName)){
						if(args.length==1){
							if(args[0].equalsIgnoreCase("info")){
								player.sendMessage("==============================================");
								player.sendMessage(ChatColor.GOLD+"/spawner create SPAWNERNAME MONSTER"+ChatColor.WHITE+" Erstellt einen Spawner mit Defaultwerten.");
								player.sendMessage(ChatColor.GOLD+"/spawner create SPAWNERNAME MONSTER INTERVALL DELAY WAIT MENGE MAX"+ChatColor.WHITE+" Erstellt einen Spawner (erweiterte Eingabe).");
								player.sendMessage(ChatColor.GOLD+"/spawner position"+ChatColor.WHITE+" Bewegt den Spawner an deine Position.");
								player.sendMessage(ChatColor.GOLD+"/spawner monster NAME"+ChatColor.WHITE+" �ndert den Monstertyp f�r den Spawner.");
								player.sendMessage(ChatColor.GOLD+"/spawner intervall/delay/menge/max/wait"+ChatColor.WHITE+" �ndert den angegeben Parameter f�r den Spawner (nur ein Parameter pro Befehl.");
								player.sendMessage(ChatColor.GOLD+"/spawner night/day/special/item"+ChatColor.WHITE+" �ndert die Spawnbedingungen f�r den Spawner (Nur einer pro Befehl).");
								player.sendMessage(ChatColor.GOLD+"/spawner helmet/boots/leggings/chest/weapon/armor"+ChatColor.WHITE+" �ndert die Ausr�stung (bei Armor komplett. Nur ein Argument pro Befehl.");
								player.sendMessage(ChatColor.GOLD+"/spawner potion/fire/poison/slow/explode/wither"+ChatColor.WHITE+" Gibt den Monster spezialf�higkeiten (eins pro Befehl");
								player.sendMessage(ChatColor.GOLD+"/spawner health/range/knockback/speed/damage"+ChatColor.WHITE+" �ndert die Werte der Monster.");
								return true;
							}
							else if(args[0].equalsIgnoreCase("position")){
								if(plugin.mSCore.playerSpawnerCommand.containsKey(player.getName())){
									String spawnerName = plugin.mSCore.playerSpawnerCommand.get(player.getName());
									for(int x=0; x<plugin.mSCore.spawnerList.size(); x++){
										Spawner spawner = plugin.mSCore.spawnerList.get(x);
										if(spawnerName.equalsIgnoreCase(spawner.getSpawnerName())){
											spawner.putBlockX(player.getLocation().getBlockX());
											spawner.putBlockY(player.getLocation().getBlockY());
											spawner.putBlockZ(player.getLocation().getBlockZ());
											spawner.putWorldName(player.getWorld().getName());
											player.sendMessage("Spawner: "+spawnerName+" erfolgreich an deine Position verschoben!");
											return true;
										}
									}
								}
								else{
									player.sendMessage("Du hast keinen Spawner ausgew�hlt!");
								}
								return true;
							}
							else if(args[0].equalsIgnoreCase("night")){
								if(plugin.mSCore.playerSpawnerCommand.containsKey(player.getName())){
									String spawnerName = plugin.mSCore.playerSpawnerCommand.get(player.getName());

									for(int x=0; x<plugin.mSCore.spawnerList.size(); x++){
										Spawner spawner = plugin.mSCore.spawnerList.get(x);
										if(spawnerName.equalsIgnoreCase(spawner.getSpawnerName())){
											if(spawner.getNightOnly()){
												spawner.putNightOnly(false);
												player.sendMessage("Nur Nachtspawn von Spawner "+spawnerName+" ist nun deaktiviert");
												return true;
											}
											if(!spawner.getNightOnly()){
												spawner.putNightOnly(true);
												player.sendMessage("Nur Nachtspawn von Spawner "+spawnerName+" ist nun aktiviert");
												return true;
											}
										}
									}
								}

								else{
									player.sendMessage("Du hast keinen Spawner ausgew�hlt!");
								}
								return true;

							}
							else if(args[0].equalsIgnoreCase("day")){
								if(plugin.mSCore.playerSpawnerCommand.containsKey(player.getName())){
									String spawnerName = plugin.mSCore.playerSpawnerCommand.get(player.getName());

									for(int x=0; x<plugin.mSCore.spawnerList.size(); x++){
										Spawner spawner = plugin.mSCore.spawnerList.get(x);
										if(spawnerName.equalsIgnoreCase(spawner.getSpawnerName())){
											if(spawner.getDayOnly()){
												spawner.putDayOnly(false);
												player.sendMessage("Nur Tagspawn von Spawner "+spawnerName+" ist nun deaktiviert");
												return true;
											}
											if(!spawner.getDayOnly()){
												spawner.putDayOnly(true);
												player.sendMessage("Nur Tagspawn von Spawner "+spawnerName+" ist nun aktiviert");
												return true;
											}
										}
									}
								}

								else{
									player.sendMessage("Du hast keinen Spawner ausgew�hlt!");
								}
								return true;

							}
							else if(args[0].equalsIgnoreCase("fire")){
								if(plugin.mSCore.playerSpawnerCommand.containsKey(player.getName())){
									String spawnerName = plugin.mSCore.playerSpawnerCommand.get(player.getName());

									for(int x=0; x<plugin.mSCore.spawnerList.size(); x++){
										Spawner spawner = plugin.mSCore.spawnerList.get(x);
										if(spawnerName.equalsIgnoreCase(spawner.getSpawnerName())){
											if(spawner.getfireDamage()){
												spawner.putfireDamage(false);
												player.sendMessage("Feuerschaden der Monster von "+spawnerName+" ist nun deaktiviert");
												return true;
											}
											if(!spawner.getfireDamage()){
												spawner.putfireDamage(true);
												player.sendMessage("Feuerschaden der Monster von "+spawnerName+" ist nun aktiviert");
												return true;
											}
										}
									}
								}

								else{
									player.sendMessage("Du hast keinen Spawner ausgew�hlt!");
								}
								return true;

							}
							else if(args[0].equalsIgnoreCase("poison")){
								if(plugin.mSCore.playerSpawnerCommand.containsKey(player.getName())){
									String spawnerName = plugin.mSCore.playerSpawnerCommand.get(player.getName());

									for(int x=0; x<plugin.mSCore.spawnerList.size(); x++){
										Spawner spawner = plugin.mSCore.spawnerList.get(x);
										if(spawnerName.equalsIgnoreCase(spawner.getSpawnerName())){
											if(spawner.getpoisonDamage()){
												spawner.putpoisonDamage(false);
												player.sendMessage("Giftschaden der Monster von "+spawnerName+" ist nun deaktiviert");
												return true;
											}
											if(!spawner.getpoisonDamage()){
												spawner.putpoisonDamage(true);
												player.sendMessage("Giftschaden der Monster von "+spawnerName+" ist nun aktiviert");
												return true;
											}
										}
									}
								}

								else{
									player.sendMessage("Du hast keinen Spawner ausgew�hlt!");
								}
								return true;

							}
							else if(args[0].equalsIgnoreCase("slow")){
								if(plugin.mSCore.playerSpawnerCommand.containsKey(player.getName())){
									String spawnerName = plugin.mSCore.playerSpawnerCommand.get(player.getName());

									for(int x=0; x<plugin.mSCore.spawnerList.size(); x++){
										Spawner spawner = plugin.mSCore.spawnerList.get(x);
										if(spawnerName.equalsIgnoreCase(spawner.getSpawnerName())){
											if(spawner.getslowDamage()){
												spawner.putslowDamage(false);
												player.sendMessage("Verlangsamungsschaden der Monster von "+spawnerName+" ist nun deaktiviert");
												return true;
											}
											if(!spawner.getslowDamage()){
												spawner.putslowDamage(true);
												player.sendMessage("Verlangsamungsschaden der Monster von "+spawnerName+" ist nun aktiviert");
												return true;
											}
										}
									}
								}

								else{
									player.sendMessage("Du hast keinen Spawner ausgew�hlt!");
								}
								return true;

							}
							else if(args[0].equalsIgnoreCase("wither")){
								if(plugin.mSCore.playerSpawnerCommand.containsKey(player.getName())){
									String spawnerName = plugin.mSCore.playerSpawnerCommand.get(player.getName());

									for(int x=0; x<plugin.mSCore.spawnerList.size(); x++){
										Spawner spawner = plugin.mSCore.spawnerList.get(x);
										if(spawnerName.equalsIgnoreCase(spawner.getSpawnerName())){
											if(spawner.getwitherDamage()){
												spawner.putwitherDamage(false);
												player.sendMessage("Witherschaden der Monster von "+spawnerName+" ist nun deaktiviert");
												return true;
											}
											if(!spawner.getwitherDamage()){
												spawner.putwitherDamage(true);
												player.sendMessage("Witherschaden der Monster von "+spawnerName+" ist nun aktiviert");
												return true;
											}
										}
									}
								}

								else{
									player.sendMessage("Du hast keinen Spawner ausgew�hlt!");
								}
								return true;

							}
							else if(args[0].equalsIgnoreCase("explode")){
								if(plugin.mSCore.playerSpawnerCommand.containsKey(player.getName())){
									String spawnerName = plugin.mSCore.playerSpawnerCommand.get(player.getName());

									for(int x=0; x<plugin.mSCore.spawnerList.size(); x++){
										Spawner spawner = plugin.mSCore.spawnerList.get(x);
										if(spawnerName.equalsIgnoreCase(spawner.getSpawnerName())){
											if(spawner.getexplodeOnDeath()){
												spawner.putexplodeOnDeath(false);
												player.sendMessage("Explodieren der Monster beim sterben von "+spawnerName+" ist nun deaktiviert");
												return true;
											}
											if(!spawner.getexplodeOnDeath()){
												spawner.putexplodeOnDeath(true);
												player.sendMessage("Explodieren der Monster beim sterben von "+spawnerName+" ist nun aktiviert");
												return true;
											}
										}
									}
								}

								else{
									player.sendMessage("Du hast keinen Spawner ausgew�hlt!");
								}
								return true;

							}
							else if(args[0].equalsIgnoreCase("special")){
								if(plugin.mSCore.playerSpawnerCommand.containsKey(player.getName())){
									String spawnerName = plugin.mSCore.playerSpawnerCommand.get(player.getName());

									for(int x=0; x<plugin.mSCore.spawnerList.size(); x++){
										Spawner spawner = plugin.mSCore.spawnerList.get(x);
										if(spawnerName.equalsIgnoreCase(spawner.getSpawnerName())){
											if(spawner.getSpecial()){
												spawner.putSpecial(false);
												player.sendMessage("Spezialspawn der Monster von "+spawnerName+" ist nun deaktiviert");
												return true;
											}
											if(!spawner.getSpecial()){
												spawner.putSpecial(true);
												player.sendMessage("Spezialspawn der Monster von "+spawnerName+" ist nun aktiviert");
												return true;
											}
										}
									}
								}

								else{
									player.sendMessage("Du hast keinen Spawner ausgew�hlt!");
								}
								return true;

							}
							else{
								player.sendMessage("Falscher Befehl ! F�r mehr Information siehe: "+ChatColor.GOLD+"/spawner info");
								return true;
							}
						}
						if(args.length==2){
							if(args[0].equalsIgnoreCase("select")){
								//debug
								//plugin.LogInfo(""+plugin.mSCore.spawnerList);
								//plugin.LogInfo(""+args[1].toString());
								
								String spawnername = args[1].toString();
								if(plugin.mSCore.playerSpawnerCommand.containsKey(player.getName())){
									plugin.mSCore.playerSpawnerCommand.remove(player.getName());
								}
								for(int y=0; y<plugin.mSCore.spawnerList.size(); y++){
									Spawner spawner = plugin.mSCore.spawnerList.get(i);
									if(spawner.getSpawnerName().equalsIgnoreCase(spawnername)){
										plugin.mSCore.playerSpawnerCommand.put(player.getName(), spawnername);
										player.sendMessage("Spawner "+spawnername+" erfolgreich ausgewählt!");
										return true;
									}
								}
								if(!plugin.mSCore.playerSpawnerCommand.containsKey(player.getName())){
									player.sendMessage(spawnername+" gibt es nicht!");
								}
								return true;

							}
							if(args[0].equalsIgnoreCase("monster")){
								if(plugin.mSCore.playerSpawnerCommand.containsKey(player.getName())){

									String spawnerName = plugin.mSCore.playerSpawnerCommand.get(player.getName());
									String Monster = args[1].toString();		
									if(Monster.equalsIgnoreCase("pigzombie")){
										Monster="PIGZOMBIE";
									}
									else if(Monster.equalsIgnoreCase("cavespider")){
										Monster="CAVESPIDER";
									}
									else if(Monster.equalsIgnoreCase("magmacube")){
										Monster="MAGMACUBE";
									}
									else if(Monster.equalsIgnoreCase("enderdragon")){
										Monster="ENDERDRAGON";
									}
									if(!plugin.mSCore.monsterlist.contains(Monster.toUpperCase())){
										player.sendMessage("Dies ist kein G�ltiger Monstername!");
										return true;
									}
									for(int x=0; x<plugin.mSCore.spawnerList.size(); x++){
										Spawner spawner = plugin.mSCore.spawnerList.get(x);
										if(spawnerName.equalsIgnoreCase(spawner.getSpawnerName())){
											spawner.putMonsterName(Monster);
											player.sendMessage("Monster von Spawner "+spawnerName+" erfolgreich auf "+Monster+" ge�ndert!");
											return true;
										}
									}
								}

								else{
									player.sendMessage("Du hast keinen Spawner ausgew�hlt!");
								}
								return true;
							}
							else if(args[0].equalsIgnoreCase("intervall")){
								if(plugin.mSCore.playerSpawnerCommand.containsKey(player.getName())){
									String spawnerName = plugin.mSCore.playerSpawnerCommand.get(player.getName());
									int intervall = 5;

									try {
										intervall = Integer.parseInt(args[1]);
									}
									catch(NumberFormatException Ex){
										player.sendMessage("Eingabe pr�fen ! Intervall ist keine Zahl!");
										return true;
									}

									for(int x=0; x<plugin.mSCore.spawnerList.size(); x++){
										Spawner spawner = plugin.mSCore.spawnerList.get(x);
										if(spawnerName.equalsIgnoreCase(spawner.getSpawnerName())){
											spawner.putIntervall(intervall);
											player.sendMessage("Intervall von Spawner "+spawnerName+" erfolgreich auf "+intervall+" ge�ndert!");
											return true;
										}
									}
								}

								else{
									player.sendMessage("Du hast keinen Spawner ausgew�hlt!");
								}
								return true;
							}
							else if(args[0].equalsIgnoreCase("delay")){
								if(plugin.mSCore.playerSpawnerCommand.containsKey(player.getName())){
									String spawnerName = plugin.mSCore.playerSpawnerCommand.get(player.getName());
									int delay = 10;

									try {
										delay = Integer.parseInt(args[1]);
									}
									catch(NumberFormatException Ex){
										player.sendMessage("Eingabe pr�fen ! Delay ist keine Zahl!");
										return true;
									}

									for(int x=0; x<plugin.mSCore.spawnerList.size(); x++){
										Spawner spawner = plugin.mSCore.spawnerList.get(x);
										if(spawnerName.equalsIgnoreCase(spawner.getSpawnerName())){
											spawner.putDelay(delay);
											player.sendMessage("Delay von Spawner "+spawnerName+" erfolgreich auf "+delay+" ge�ndert!");
											return true;
										}
									}
								}

								else{
									player.sendMessage("Du hast keinen Spawner ausgew�hlt!");
								}
								return true;
							}
							else if(args[0].equalsIgnoreCase("menge")){
								if(plugin.mSCore.playerSpawnerCommand.containsKey(player.getName())){
									String spawnerName = plugin.mSCore.playerSpawnerCommand.get(player.getName());
									int menge = 1;

									try {
										menge = Integer.parseInt(args[1]);
									}
									catch(NumberFormatException Ex){
										player.sendMessage("Eingabe pr�fen ! Menge ist keine Zahl!");
										return true;
									}

									for(int x=0; x<plugin.mSCore.spawnerList.size(); x++){
										Spawner spawner = plugin.mSCore.spawnerList.get(x);
										if(spawnerName.equalsIgnoreCase(spawner.getSpawnerName())){
											spawner.putMenge(menge);
											player.sendMessage("Menge von Spawner "+spawnerName+" erfolgreich auf "+menge+" ge�ndert!");
											return true;
										}
									}
								}

								else{
									player.sendMessage("Du hast keinen Spawner ausgew�hlt!");
								}
								return true;
							}
							else if(args[0].equalsIgnoreCase("max")){
								if(plugin.mSCore.playerSpawnerCommand.containsKey(player.getName())){
									String spawnerName = plugin.mSCore.playerSpawnerCommand.get(player.getName());
									int max = 4;

									try {
										max = Integer.parseInt(args[1]);
									}
									catch(NumberFormatException Ex){
										player.sendMessage("Eingabe pr�fen ! Max ist keine Zahl!");
										return true;
									}

									for(int x=0; x<plugin.mSCore.spawnerList.size(); x++){
										Spawner spawner = plugin.mSCore.spawnerList.get(x);
										if(spawnerName.equalsIgnoreCase(spawner.getSpawnerName())){
											spawner.putMax(max);
											player.sendMessage("Max von Spawner "+spawnerName+" erfolgreich auf "+max+" ge�ndert!");
											return true;
										}
									}
								}

								else{
									player.sendMessage("Du hast keinen Spawner ausgew�hlt!");
								}
								return true;
							}
							else if(args[0].equalsIgnoreCase("wait")){
								if(plugin.mSCore.playerSpawnerCommand.containsKey(player.getName())){
									String spawnerName = plugin.mSCore.playerSpawnerCommand.get(player.getName());
									int wait = 300;

									try {
										wait = Integer.parseInt(args[1]);
									}
									catch(NumberFormatException Ex){
										player.sendMessage("Eingabe pr�fen ! Wait ist keine Zahl!");
										return true;
									}

									for(int x=0; x<plugin.mSCore.spawnerList.size(); x++){
										Spawner spawner = plugin.mSCore.spawnerList.get(x);
										if(spawnerName.equalsIgnoreCase(spawner.getSpawnerName())){
											spawner.putWait(wait);
											player.sendMessage("Wait von Spawner "+spawnerName+" erfolgreich auf "+wait+" ge�ndert!");
											return true;
										}
									}
								}

								else{
									player.sendMessage("Du hast keinen Spawner ausgew�hlt!");
								}
								return true;
							}
							else if(args[0].equalsIgnoreCase("health")){
								if(plugin.mSCore.playerSpawnerCommand.containsKey(player.getName())){
									String spawnerName = plugin.mSCore.playerSpawnerCommand.get(player.getName());
									int health = 0;

									try {
										health = Integer.parseInt(args[1]);
									}
									catch(NumberFormatException Ex){
										player.sendMessage("Eingabe pr�fen ! Health ist keine Zahl!");
										return true;
									}

									for(int x=0; x<plugin.mSCore.spawnerList.size(); x++){
										Spawner spawner = plugin.mSCore.spawnerList.get(x);
										if(spawnerName.equalsIgnoreCase(spawner.getSpawnerName())){
											spawner.putmaxHealth(health);
											player.sendMessage("Health von Spawner "+spawnerName+" erfolgreich auf "+health+" ge�ndert!");
											return true;
										}
									}
								}

								else{
									player.sendMessage("Du hast keinen Spawner ausgew�hlt!");
								}
								return true;
							}
							else if(args[0].equalsIgnoreCase("Range")){
								if(plugin.mSCore.playerSpawnerCommand.containsKey(player.getName())){
									String spawnerName = plugin.mSCore.playerSpawnerCommand.get(player.getName());
									int range = 0;

									try {
										range = Integer.parseInt(args[1]);
									}
									catch(NumberFormatException Ex){
										player.sendMessage("Eingabe pr�fen ! Range ist keine Zahl!");
										return true;
									}

									for(int x=0; x<plugin.mSCore.spawnerList.size(); x++){
										Spawner spawner = plugin.mSCore.spawnerList.get(x);
										if(spawnerName.equalsIgnoreCase(spawner.getSpawnerName())){
											spawner.putfollowRage(range);
											player.sendMessage("Range von Spawner "+spawnerName+" erfolgreich auf "+range+" ge�ndert!");
											return true;
										}
									}
								}

								else{
									player.sendMessage("Du hast keinen Spawner ausgew�hlt!");
								}
								return true;
							}
							else if(args[0].equalsIgnoreCase("knockback")){
								if(plugin.mSCore.playerSpawnerCommand.containsKey(player.getName())){
									String spawnerName = plugin.mSCore.playerSpawnerCommand.get(player.getName());
									int knockback = 0;

									try {
										knockback = Integer.parseInt(args[1]);
									}
									catch(NumberFormatException Ex){
										player.sendMessage("Eingabe pr�fen ! Knockback ist keine Zahl!");
										return true;
									}

									for(int x=0; x<plugin.mSCore.spawnerList.size(); x++){
										Spawner spawner = plugin.mSCore.spawnerList.get(x);
										if(spawnerName.equalsIgnoreCase(spawner.getSpawnerName())){
											spawner.putknockBackResi(knockback);
											player.sendMessage("Knockback von Spawner "+spawnerName+" erfolgreich auf "+knockback+" ge�ndert!");
											return true;
										}
									}
								}

								else{
									player.sendMessage("Du hast keinen Spawner ausgew�hlt!");
								}
								return true;
							}
							else if(args[0].equalsIgnoreCase("speed")){
								if(plugin.mSCore.playerSpawnerCommand.containsKey(player.getName())){
									String spawnerName = plugin.mSCore.playerSpawnerCommand.get(player.getName());
									int speed = 0;

									try {
										speed = Integer.parseInt(args[1]);
									}
									catch(NumberFormatException Ex){
										player.sendMessage("Eingabe pr�fen ! Speed ist keine Zahl!");
										return true;
									}

									for(int x=0; x<plugin.mSCore.spawnerList.size(); x++){
										Spawner spawner = plugin.mSCore.spawnerList.get(x);
										if(spawnerName.equalsIgnoreCase(spawner.getSpawnerName())){
											spawner.putmoveSpeed(speed);
											player.sendMessage("Speed von Spawner "+spawnerName+" erfolgreich auf "+speed+" ge�ndert!");
											return true;
										}
									}
								}

								else{
									player.sendMessage("Du hast keinen Spawner ausgew�hlt!");
								}
								return true;
							}
							else if(args[0].equalsIgnoreCase("damage")){
								if(plugin.mSCore.playerSpawnerCommand.containsKey(player.getName())){
									String spawnerName = plugin.mSCore.playerSpawnerCommand.get(player.getName());
									int damage = 0;

									try {
										damage = Integer.parseInt(args[1]);
									}
									catch(NumberFormatException Ex){
										player.sendMessage("Eingabe pr�fen ! Damage ist keine Zahl!");
										return true;
									}

									for(int x=0; x<plugin.mSCore.spawnerList.size(); x++){
										Spawner spawner = plugin.mSCore.spawnerList.get(x);
										if(spawnerName.equalsIgnoreCase(spawner.getSpawnerName())){
											spawner.putattackDamage(damage);
											player.sendMessage("Damage von Spawner "+spawnerName+" erfolgreich auf "+damage+" ge�ndert!");
											return true;
										}
									}
								}

								else{
									player.sendMessage("Du hast keinen Spawner ausgew�hlt!");
								}
								return true;
							}
							else if(args[0].equalsIgnoreCase("helmet")){
								if(plugin.mSCore.playerSpawnerCommand.containsKey(player.getName())){
									String spawnerName = plugin.mSCore.playerSpawnerCommand.get(player.getName());
									String helmet = args[1].toString().toUpperCase();
									if(!plugin.mSCore.helmetslist.contains(helmet)){
										player.sendMessage("Dies ist kein g�ltiger Helm!");
										return true;
									}

									for(int x=0; x<plugin.mSCore.spawnerList.size(); x++){
										Spawner spawner = plugin.mSCore.spawnerList.get(x);
										if(spawnerName.equalsIgnoreCase(spawner.getSpawnerName())){
											spawner.putHelmet(helmet);;
											player.sendMessage("Monster des Spawners "+spawnerName+" erfolgreich erfolgreich mit "+helmet+" ausger�stet!");
											return true;
										}
									}
								}

								else{
									player.sendMessage("Du hast keinen Spawner ausgew�hlt!");
								}
								return true;
							}
							else if(args[0].equalsIgnoreCase("boots")){
								if(plugin.mSCore.playerSpawnerCommand.containsKey(player.getName())){
									String spawnerName = plugin.mSCore.playerSpawnerCommand.get(player.getName());
									String boots = args[1].toString().toUpperCase();
									if(!plugin.mSCore.shoeslist.contains(boots)){
										player.sendMessage("Dies sind keine g�ltigen Schuhe!");
										return true;
									}

									for(int x=0; x<plugin.mSCore.spawnerList.size(); x++){
										Spawner spawner = plugin.mSCore.spawnerList.get(x);
										if(spawnerName.equalsIgnoreCase(spawner.getSpawnerName())){
											spawner.putBoots(boots);;
											player.sendMessage("Monster des Spawners "+spawnerName+" erfolgreich erfolgreich mit "+boots+" ausger�stet!");
											return true;
										}
									}
								}

								else{
									player.sendMessage("Du hast keinen Spawner ausgew�hlt!");
								}
								return true;
							}
							else if(args[0].equalsIgnoreCase("leggings")){
								if(plugin.mSCore.playerSpawnerCommand.containsKey(player.getName())){
									String spawnerName = plugin.mSCore.playerSpawnerCommand.get(player.getName());
									String leggings = args[1].toString().toUpperCase();
									if(!plugin.mSCore.leggingslist.contains(leggings)){
										player.sendMessage("Dies ist keine g�ltige Hose!");
										return true;
									}

									for(int x=0; x<plugin.mSCore.spawnerList.size(); x++){
										Spawner spawner = plugin.mSCore.spawnerList.get(x);
										if(spawnerName.equalsIgnoreCase(spawner.getSpawnerName())){
											spawner.putLeggings(leggings);;
											player.sendMessage("Monster des Spawners "+spawnerName+" erfolgreich erfolgreich mit "+leggings+" ausger�stet!");
											return true;
										}
									}
								}

								else{
									player.sendMessage("Du hast keinen Spawner ausgew�hlt!");
								}
								return true;
							}
							else if(args[0].equalsIgnoreCase("chest")){
								if(plugin.mSCore.playerSpawnerCommand.containsKey(player.getName())){
									String spawnerName = plugin.mSCore.playerSpawnerCommand.get(player.getName());
									String chest = args[1].toString().toUpperCase();
									if(!plugin.mSCore.chestplateslist.contains(chest)){
										player.sendMessage("Dies ist kein g�ltiger Harnisch!");
										return true;
									}

									for(int x=0; x<plugin.mSCore.spawnerList.size(); x++){
										Spawner spawner = plugin.mSCore.spawnerList.get(x);
										if(spawnerName.equalsIgnoreCase(spawner.getSpawnerName())){
											spawner.putChest(chest);;
											player.sendMessage("Monster des Spawners "+spawnerName+" erfolgreich erfolgreich mit "+chest+" ausger�stet!");
											return true;
										}
									}
								}

								else{
									player.sendMessage("Du hast keinen Spawner ausgew�hlt!");
								}
								return true;
							}
							else if(args[0].equalsIgnoreCase("weapon")){
								if(plugin.mSCore.playerSpawnerCommand.containsKey(player.getName())){
									String spawnerName = plugin.mSCore.playerSpawnerCommand.get(player.getName());
									String weapon = args[1].toString().toUpperCase();
									if(!plugin.mSCore.weaponslist.contains(weapon)){
										player.sendMessage("Dies ist keine g�ltige Waffe!");
										return true;
									}

									for(int x=0; x<plugin.mSCore.spawnerList.size(); x++){
										Spawner spawner = plugin.mSCore.spawnerList.get(x);
										if(spawnerName.equalsIgnoreCase(spawner.getSpawnerName())){
											spawner.putWeapon(weapon);;
											player.sendMessage("Monster des Spawners "+spawnerName+" erfolgreich erfolgreich mit "+weapon+" ausger�stet!");
											return true;
										}
									}
								}

								else{
									player.sendMessage("Du hast keinen Spawner ausgew�hlt!");
								}
								return true;
							}
							else if(args[0].equalsIgnoreCase("armor")){
								if(plugin.mSCore.playerSpawnerCommand.containsKey(player.getName())){
									String spawnerName = plugin.mSCore.playerSpawnerCommand.get(player.getName());
									String material = args[1].toString().toUpperCase();
									if(material.equalsIgnoreCase("LEATHER")){
										for(int x=0; x<plugin.mSCore.spawnerList.size(); x++){
											Spawner spawner = plugin.mSCore.spawnerList.get(x);
											if(spawnerName.equalsIgnoreCase(spawner.getSpawnerName())){
												spawner.putHelmet("LEATHER_HELMET");
												spawner.putBoots("LEATHER_BOOTS");
												spawner.putLeggings("LEATHER_LEGGINGS");
												spawner.putChest("LEATHER_CHESTPLATE");
												player.sendMessage("Monster des Spawners "+spawnerName+" erfolgreich erfolgreich mit Lederr�stung ausger�stet!");
												return true;
											}
										}
									}
									else if(material.equalsIgnoreCase("CHAINMAIL")){
										for(int x=0; x<plugin.mSCore.spawnerList.size(); x++){
											Spawner spawner = plugin.mSCore.spawnerList.get(x);
											if(spawnerName.equalsIgnoreCase(spawner.getSpawnerName())){
												spawner.putHelmet("CHAINMAIL_HELMET");
												spawner.putBoots("CHAINMAIL_BOOTS");
												spawner.putLeggings("CHAINMAIL_LEGGINGS");
												spawner.putChest("CHAINMAIL_CHESTPLATE");
												player.sendMessage("Monster des Spawners "+spawnerName+" erfolgreich erfolgreich mit Kettenr�stung ausger�stet!");
												return true;
											}
										}
									}		
									else if(material.equalsIgnoreCase("IRON")){
										for(int x=0; x<plugin.mSCore.spawnerList.size(); x++){
											Spawner spawner = plugin.mSCore.spawnerList.get(x);
											if(spawnerName.equalsIgnoreCase(spawner.getSpawnerName())){
												spawner.putHelmet("IRON_HELMET");
												spawner.putBoots("IRON_BOOTS");
												spawner.putLeggings("IRON_LEGGINGS");
												spawner.putChest("IRON_CHESTPLATE");
												player.sendMessage("Monster des Spawners "+spawnerName+" erfolgreich erfolgreich mit Eisenr�stung ausger�stet!");
												return true;
											}
										}
									}
									else if(material.equalsIgnoreCase("GOLD")){
										for(int x=0; x<plugin.mSCore.spawnerList.size(); x++){
											Spawner spawner = plugin.mSCore.spawnerList.get(x);
											if(spawnerName.equalsIgnoreCase(spawner.getSpawnerName())){
												spawner.putHelmet("GOLD_HELMET");
												spawner.putBoots("GOLD_BOOTS");
												spawner.putLeggings("GOLD_LEGGINGS");
												spawner.putChest("GOLD_CHESTPLATE");
												player.sendMessage("Monster des Spawners "+spawnerName+" erfolgreich erfolgreich mit Goldr�stung ausger�stet!");
												return true;
											}
										}
									}
									else if(material.equalsIgnoreCase("DIAMOND")){
										for(int x=0; x<plugin.mSCore.spawnerList.size(); x++){
											Spawner spawner = plugin.mSCore.spawnerList.get(x);
											if(spawnerName.equalsIgnoreCase(spawner.getSpawnerName())){
												spawner.putHelmet("DIAMOND_HELMET");
												spawner.putBoots("DIAMOND_BOOTS");
												spawner.putLeggings("DIAMOND_LEGGINGS");
												spawner.putChest("DIAMOND_CHESTPLATE");
												player.sendMessage("Monster des Spawners "+spawnerName+" erfolgreich erfolgreich mit Diamantr�stung ausger�stet!");
												return true;
											}
										}
									}
									else{
										player.sendMessage(material+" ist keine g�ltige Eingabe. Erlaubt sind: leather, chainmail, iron, gold oder diamond");
										return true;
									}

								}

								else{
									player.sendMessage("Du hast keinen Spawner ausgew�hlt!");
								}
								return true;
							}
							else if(args[0].equalsIgnoreCase("potion")){
								if(plugin.mSCore.playerSpawnerCommand.containsKey(player.getName())){
									String spawnerName = plugin.mSCore.playerSpawnerCommand.get(player.getName());
									String potion = args[1].toString().toUpperCase();
									if(!plugin.mSCore.potionslist.contains(potion)){
										player.sendMessage("Dies ist kein g�ltiger Trankeffekt !");
										player.sendMessage("Vorgang wird abgebrochen !");
										return true;
									}
									for(int x=0; x<plugin.mSCore.spawnerList.size(); x++){
										Spawner spawner = plugin.mSCore.spawnerList.get(x);
										if(spawnerName.equalsIgnoreCase(spawner.getSpawnerName())){
											spawner.putPotion(potion);
											player.sendMessage("Monster des Spawners "+spawnerName+" erfolgreich mit Trankeffekt "+potion+" belegt!");
											return true;
										}
									}

								}
								else{
									player.sendMessage("Du hast keinen Spawner ausgew�hlt!");
								}
								return true;

							}
							else{
								player.sendMessage("Falscher Befehl ! F�r mehr Information siehe: "+ChatColor.GOLD+"/spawner info");
								return true;
							}
						}
						if(args.length==3){
							if(args[0].equalsIgnoreCase("create")){

								String spawnerName = args[1].toString();
								String Monster = args[2].toString();
								if(Monster.equalsIgnoreCase("pigzombie")){
									Monster="PIGZOMBIE";
								}
								else if(Monster.equalsIgnoreCase("cavespider")){
									Monster="CAVESPIDER";
								}
								else if(Monster.equalsIgnoreCase("magmacube")){
									Monster="MAGMACUBE";
								}
								else if(Monster.equalsIgnoreCase("enderdragon")){
									Monster="ENDERDRAGON";
								}
								if(!plugin.mSCore.monsterlist.contains(Monster.toUpperCase())){
									player.sendMessage("Dies ist kein G�ltiger Monstername!");
									return true;
								}
								for(int x=0; x<plugin.mSCore.spawnerList.size(); x++){
									Spawner spawner = plugin.mSCore.spawnerList.get(x);
									if(spawnerName.equalsIgnoreCase(spawner.getSpawnerName())){
										player.sendMessage("Spawner mit diesem Namen existiert schon!");
										player.sendMessage("Vorgang wird abgebrochen!");
										return true;
									}
								}
								plugin.mSCore.playerSpawnerCommand.put(player.getName(), spawnerName);
								Spawner spawner = new Spawner(plugin, spawnerName, true);
								spawner.putSpawnerName(spawnerName);
								spawner.putBlockX(player.getLocation().getBlockX());
								spawner.putBlockY(player.getLocation().getBlockY());
								spawner.putBlockZ(player.getLocation().getBlockZ());
								spawner.putMonsterName(Monster);
								spawner.putIntervall(5);
								spawner.putDelay(10);
								spawner.putMenge(1);
								spawner.putBoss(false);
								spawner.putNightOnly(false);
								spawner.putDayOnly(false);
								spawner.putHelmet("none");
								spawner.putBoots("none");
								spawner.putLeggings("none");
								spawner.putChest("none");
								spawner.putWeapon("none");
								spawner.putPotion("none");
								spawner.putItemNeed(false);
								spawner.putItemNeedID(0);
								spawner.putItemNeedSubID(0);
								spawner.putMax(4);
								spawner.putWait(300);
								spawner.putZoneName(zoneName);
								spawner.putWorldName(player.getWorld().getName());
								spawner.putDynamicScale(1);
								spawner.putDynamicHealth(1);
								spawner.putDynamicDamage(1);
								spawner.putmaxHealth(0);
								spawner.putfollowRage(0);
								spawner.putknockBackResi(0);
								spawner.putmoveSpeed(0);
								spawner.putattackDamage(0);
								spawner.putfireDamage(false);
								spawner.putpoisonDamage(false);
								spawner.putslowDamage(false);
								spawner.putexplodeOnDeath(false);
								spawner.putwitherDamage(false);
								spawner.putisVillager(false);
								spawner.putslimeSize(0);
								player.sendMessage("Spawner "+spawnerName+" erfolgreich mit Defaultwerten angelegt und ausw�hlt!");
								return true;	
							}
							else{
								player.sendMessage("Ung�ltiger Command. F�r Info siehe: "+ChatColor.GOLD+"/spawner help");
								return true;
							}

						}
						if(args.length==8){
							if(args[0].equalsIgnoreCase("create")){

								String spawnerName = args[1].toString();
								String Monster = args[2].toString();
								int intervall = 5;
								int delay = 10;
								int wait = 300;
								int menge = 1;
								int max = 4;

								try {
									intervall = Integer.parseInt(args[3]);
									delay = Integer.parseInt(args[4]);
									wait = Integer.parseInt(args[5]);
									menge = Integer.parseInt(args[6]);
									max = Integer.parseInt(args[7]);
								}
								catch(NumberFormatException Ex){
									player.sendMessage("Eingabe pr�fen ! Ein Wert ist kein Zahlenwert (Intervall/Delay/Wait/Menge/Max)");
									return true;
								}
								if(Monster.equalsIgnoreCase("pigzombie")){
									Monster="PIGZOMBIE";
								}
								else if(Monster.equalsIgnoreCase("cavespider")){
									Monster="CAVESPIDER";
								}
								else if(Monster.equalsIgnoreCase("magmacube")){
									Monster="MAGMACUBE";
								}
								else if(Monster.equalsIgnoreCase("enderdragon")){
									Monster="ENDERDRAGON";
								}
								if(!plugin.mSCore.monsterlist.contains(Monster.toUpperCase())){
									player.sendMessage("Dies ist kein G�ltiger Monstername!");
									return true;
								}
								for(int x=0; x<plugin.mSCore.spawnerList.size(); x++){
									Spawner spawner = plugin.mSCore.spawnerList.get(x);
									if(spawnerName.equalsIgnoreCase(spawner.getSpawnerName())){
										player.sendMessage("Spawner mit diesem Namen existiert schon!");
										player.sendMessage("Vorgang wird abgebrochen!");
										return true;
									}
								}
								plugin.mSCore.playerSpawnerCommand.put(player.getName(), spawnerName);
								Spawner spawner = new Spawner(plugin, spawnerName);
								spawner.putSpawnerName(spawnerName);
								spawner.putBlockX(player.getLocation().getBlockX());
								spawner.putBlockY(player.getLocation().getBlockY());
								spawner.putBlockZ(player.getLocation().getBlockZ());
								spawner.putMonsterName(Monster);
								spawner.putIntervall(intervall);
								spawner.putDelay(delay);
								spawner.putMenge(menge);
								spawner.putBoss(false);
								spawner.putNightOnly(false);
								spawner.putDayOnly(false);
								spawner.putHelmet("none");
								spawner.putBoots("none");
								spawner.putLeggings("none");
								spawner.putChest("none");
								spawner.putWeapon("none");
								spawner.putPotion("none");
								spawner.putItemNeed(false);
								spawner.putItemNeedID(0);
								spawner.putItemNeedSubID(0);
								spawner.putMax(max);
								spawner.putWait(wait);
								spawner.putZoneName(zoneName);
								spawner.putWorldName(player.getWorld().getName());
								spawner.putDynamicScale(1);
								spawner.putDynamicHealth(1);
								spawner.putDynamicDamage(1);
								spawner.putmaxHealth(0);
								spawner.putfollowRage(0);
								spawner.putknockBackResi(0);
								spawner.putmoveSpeed(0);
								spawner.putattackDamage(0);
								spawner.putfireDamage(false);
								spawner.putpoisonDamage(false);
								spawner.putslowDamage(false);
								spawner.putexplodeOnDeath(false);
								spawner.putwitherDamage(false);
								spawner.putisVillager(false);
								spawner.putslimeSize(0);
								player.sendMessage("Spawner "+spawnerName+" erfolgreich mit Defaultwerten angelegt und ausw�hlt!");
								return true;	
							}
							else{
								player.sendMessage("Ung�ltiger Command. F�r Info siehe: "+ChatColor.GOLD+"/spawner help");
								return true;
							}


						}

					}
				}

			}else{
				player.sendMessage("Du hast nicht die Berechtigungen f�r diesen Befehl!");
				return true;
			}
		}
		else{
			//put console commands here
		}


		return false;
	}



}