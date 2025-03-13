package org.community.newSettlers.Commands;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.community.DatabaseProvider.MySQL.MySQL;
import org.community.newSettlers.newSettlers;
import org.community.newSettlers.Town.Town;

public class newSettlersCommandMain {
		
	private final newSettlers plugin;
	
	public final String PASSWORD_SALT = "g867Rzu7657F6tdf7d758F687RfHfr4fgFDhD56ffdZtZr67R7RFGFGUGzuTRDTGUt2TGT";
	 	 
	public newSettlersCommandMain(newSettlers plugin)
	{
		this.plugin = plugin;
	}
	
	@SuppressWarnings("deprecation")
	public boolean getCommand(Player sender, Command cmd, String commandLabel, String[] args) {

		if(cmd.getName().equalsIgnoreCase("siedler")){
			if(args.length == 0) {
				sender.sendMessage(ChatColor.DARK_GREEN + "Benutzt den Befehl /siedler hilfe für weitere Informationen.");
				return true;
			}
			if(args.length == 1) {
				if(args[0].equalsIgnoreCase("help") || args[0].equalsIgnoreCase("hilfe") || args[0].equalsIgnoreCase("?")){
					plugin.nSHelp.getCommand(sender, cmd, commandLabel, args);
					return true;
				}	
				if(args[0].equalsIgnoreCase("städte")){
					List<Town> towns = plugin.nSCore.getCompleteTownList();
					for(int i = 0; i < towns.size(); i++) {
						sender.sendMessage("" + towns.get(i).getName() + " (" + towns.get(i).getDescription() + ")");
					}
					return true;
				}	
				if(args[0].equalsIgnoreCase("distance")){
					sender.sendMessage("" + plugin.nSCore.getDistanceToHomePlots(sender.getLocation()));
					return true;
				}	
				if(args[0].equalsIgnoreCase("admincheck")){
					if(plugin.nSCore.isAdmin(sender)) {
						sender.sendMessage(ChatColor.DARK_GREEN + "Ja du bist ein Admin :-)");
						sender.sendMessage(sender.getName() + " (Name)");
						sender.sendMessage(sender.getDisplayName() + " (DisplayName)");
						sender.sendMessage(sender.getUniqueId() + " (UUID)");
					} else {
						sender.sendMessage(ChatColor.DARK_GREEN + "Du bist kein Admin!");
					}
					return true;
				}		
				if(args[0].equalsIgnoreCase("adminmodus")){
					if(!(sender instanceof Player))
						return true;
					if(plugin.config.getStringList("Config.Admins").contains(((Player) sender).getUniqueId().toString())) {
						if(plugin.adminMode.get((Player) sender) == null){
							plugin.adminMode.put((Player) sender, true);
							plugin.getServer().broadcastMessage(ChatColor.RED + "===== Städtesystem: Adminmodus aktiviert für: " + sender.getName() + "=====");
							return true;
						}
						if(plugin.adminMode.get((Player) sender) == false){
							plugin.adminMode.put((Player) sender, true);
							plugin.getServer().broadcastMessage(ChatColor.RED + "===== Städtesystem: Adminmodus aktiviert für: " + sender.getName() + "=====");
							return true;
						}
						if(plugin.adminMode.get((Player) sender) == true){
							plugin.adminMode.remove((Player) sender);
							plugin.getServer().broadcastMessage(ChatColor.GRAY + "===== Städtesystem: Adminmodus deaktiviert für: " + sender.getName() + "=====");
							return true;
						}
						return true;
					} else {
						sender.sendMessage(ChatColor.RED + "Du hast keine Berechtigung den Admin-Modus für NewSettlers zu nutzen!");
						return true;
					}
						
				}
				if(args[0].equalsIgnoreCase("admintarnung")){
					if(!(sender instanceof Player))
						return true;
					if(plugin.config.getStringList("Config.Admins").contains(((Player) sender).getUniqueId().toString())) {
						if(plugin.nSCore.playerHide.containsKey(sender) && plugin.nSCore.playerHide.get(sender) == true){
							plugin.nSCore.playerHide.remove(sender);
							sender.sendMessage("Du bist nun wieder sichtbar");
							for(Player onlinePlayer : Bukkit.getOnlinePlayers()){
								onlinePlayer.showPlayer(sender);
							}
							return true;
						} else {
							plugin.nSCore.playerHide.put(sender, true);
							for(Player onlinePlayer : Bukkit.getOnlinePlayers()){

								onlinePlayer.hidePlayer(sender);
							}
							sender.sendMessage("Du bist nun unsichtbar");

							for(Player onlinePlayer : Bukkit.getOnlinePlayers()){
								if(plugin.nSCore.isAdmin(onlinePlayer)){
									onlinePlayer.canSee(sender);
								}
							}
							return true;
						}
					}	
				}
				if(args[0].equalsIgnoreCase("chunkcheck")){
					if(plugin.nSCore.isAdmin(sender)) {
						for(Entry<String, Town> e : plugin.nSCore.globalChunkList.entrySet()){
							String chunk = e.getKey();
							Town chunkType = e.getValue();
							sender.sendMessage(chunk + ": " + chunkType.getName());
						}
					}
					return true;
				}
				if(args[0].equalsIgnoreCase("plotanzeige")){
					if(plugin.nSCore.playerDisplayChunks.get(sender) == null) {
						plugin.nSCore.playerDisplayChunks.put(sender, true);
						sender.sendMessage(ChatColor.DARK_GREEN + "ChunkDisplay aktiviert");
					} else if(plugin.nSCore.playerDisplayChunks.get(sender) == false) {
						plugin.nSCore.playerDisplayChunks.put(sender, true);
						sender.sendMessage(ChatColor.DARK_GREEN + "ChunkDisplay aktiviert");
					} else {
						plugin.nSCore.playerDisplayChunks.put(sender, false);
						sender.sendMessage(ChatColor.DARK_GREEN + "ChunkDisplay deaktiviert");
					}
					return true;
				}		
				if(args[0].equalsIgnoreCase("kartenanzeige")){
					if(plugin.nSCore.playerDisplayMap.get(sender) == null) {
						plugin.nSCore.playerDisplayMap.put(sender, true);
						sender.sendMessage(ChatColor.DARK_GREEN + "MapDisplay aktiviert");
					} else if(plugin.nSCore.playerDisplayMap.get(sender) == false) {
						plugin.nSCore.playerDisplayMap.put(sender, true);
						sender.sendMessage(ChatColor.DARK_GREEN + "MapDisplay aktiviert");
					} else {
						plugin.nSCore.playerDisplayMap.put(sender, false);
						sender.sendMessage(ChatColor.DARK_GREEN + "MapDisplay deaktiviert");
					}
					return true;
				}				
			}
			if(args.length == 2) {
				if(args[0].equalsIgnoreCase("help") || args[0].equalsIgnoreCase("hilfe") || args[0].equalsIgnoreCase("?")){
					plugin.nSHelp.getCommand(sender, cmd, commandLabel, args);
					return true;
				}	
				if(args[0].equalsIgnoreCase("geld")){
					if(!plugin.nSCore.isAdmin(sender)) {
						sender.sendMessage(ChatColor.RED + "Dieser Befehl steht nur Admins zur Verfügung.");
						return true;
					}
					ItemStack money = new ItemStack(Material.NETHER_STAR);
					money.setAmount(Integer.parseInt(args[1]));
					money.setDurability((short)1);
					ItemMeta meta = money.getItemMeta();
					meta.setDisplayName("Goldene Gulde");
					List<String> lore = new ArrayList<String>();
					lore.add("Das königliche Zahlungsmittel");
					meta.setLore(lore);
					meta.addEnchant(Enchantment.DURABILITY, 1, true);
					money.setItemMeta(meta);
					sender.getInventory().setItem(sender.getInventory().firstEmpty(), money);
					return true;
				}	
				if(args[0].equalsIgnoreCase("gründung")){
					if(!plugin.config.getList("System.TownWorlds").contains(sender.getWorld().getName())) {
						sender.sendMessage(ChatColor.DARK_GREEN + "Du versuchtst eine Stadt in einer nicht zugelassenen Welt zu gründen.");
						return true;
					}
					Town playerTown = plugin.nSCore.getPlayerTown(sender);
					if(playerTown == null) {
						Location newTownChunkLocation = sender.getLocation();
						double distance = plugin.nSCore.getDistanceToHomePlots(newTownChunkLocation);
						if(distance <= plugin.config.getInt("System.Distance.Founding")) {
							sender.sendMessage(ChatColor.DARK_RED + "Du bist zu nah an der nächsten Stadt. Erhöhe die Reichweite.");
							String clearDistance = "" + distance;
							String[] outputDistance = clearDistance.split("\\.");
							sender.sendMessage(ChatColor.DARK_GREEN + "Derzeit bist du " + ChatColor.BLUE + outputDistance[0] + ChatColor.DARK_GREEN + " Blöcke von der nächsten Stadt entfernt.");
							return true;
						}
						if(sender.getItemInHand().getType() != Material.WRITTEN_BOOK) {
							sender.sendMessage(ChatColor.DARK_RED + "Du benötigst eine Stadturkunde für die Gründung deiner Siedlung.");
							return true;
						}
						BookMeta meta = (BookMeta) sender.getItemInHand().getItemMeta();
						if(meta.getTitle() == null || meta.getAuthor() == null) {
							sender.sendMessage(ChatColor.DARK_RED + "Du besitzt keine gültige Stadtgründungsurkunde.");
							return true;
						}
						if(!meta.getTitle().equalsIgnoreCase("Stadturkunde")) {
							sender.sendMessage(ChatColor.DARK_RED + "Du besitzt keine gültige Stadtgründungsurkunde.");
							return true;
						}
						if(!meta.getAuthor().equalsIgnoreCase("Stadtregistrar")) {
							sender.sendMessage(ChatColor.DARK_RED + "Du besitzt keine gültige Stadtgründungsurkunde.");
							return true;
						}
						sender.setItemInHand(new ItemStack(Material.AIR));
						Town town = new Town(plugin, args[1], sender, true);
						sender.sendMessage(ChatColor.DARK_GREEN + "Herzlichen Glückwunsch zur Gründung der Siedlung " + town.getName());
						plugin.getServer().broadcastMessage(ChatColor.DARK_GREEN + "Eine neue Siedlung mit dem Namen " + town.getName() + " wurde gegründet!");
						return true;
					} else {
						sender.sendMessage(ChatColor.DARK_RED + "Du bist bereits Einwohner einer Stadt!");
						return true;
					}
				}			
			}
		}
		
		if(cmd.getName().equalsIgnoreCase("ja")){
			if(args.length == 0) {
				
				if(sender instanceof Player) {
					if(plugin.newApproval.get((Player) sender) == null){
						sender.sendMessage(ChatColor.DARK_RED + "Du hast keine laufende Anfrage.");
						return true;
					} else {
						Player requester = plugin.newApproval.get((Player) sender);
						if(requester.isOnline() == false) {
							sender.sendMessage(ChatColor.DARK_RED + "Der Spieler, der dich eingeladen hat, ist leider offline.");
							plugin.nSTownInvite.deleteRequest(requester, (Player) sender);
							return true;
						} else {
							//sender.sendMessage(ChatColor.DARK_GREEN + "Du hast die Einladung angenommen.");
							if(plugin.newRequest.get(requester).equalsIgnoreCase("#TownInvite#")){
								plugin.nSTownInvite.acceptRequest(requester, (Player) sender);
								return true;
							} else if(plugin.newRequest.get(requester).equalsIgnoreCase("#TownDestroy#")) {
								plugin.nSTownDestroy.acceptRequest(requester, (Player) sender);
								return true;
							} else if(plugin.newRequest.get(requester).equalsIgnoreCase("#AllyInvite#")) {
								plugin.nSAllyInvite.acceptRequest(requester, (Player) sender);
								return true;
							} else if(plugin.newRequest.get(requester).contains("#EnemyInvite:")) {
								plugin.nSEnemyInvite.acceptRequest(requester, (Player) sender);
								return true;
							} else if(plugin.newRequest.get(requester).contains("#AllyRemove:")) {
								plugin.nSAllyRemove.acceptRequest(requester, (Player) sender);
								return true;
							} else if(plugin.newRequest.get(requester).equalsIgnoreCase("#EnemyRemove#")) {
								plugin.nSEnemyRemove.acceptRequest(requester, (Player) sender);
								return true;
							} else if(plugin.newRequest.get(requester).contains("#BuildingInvite:")) {
								plugin.nSBuildingInvite.acceptRequest(requester, (Player) sender);
								return true;
							} else if(plugin.newRequest.get(requester).equalsIgnoreCase("#TownExpand#")) {
								plugin.nSTownExpand.acceptRequest(requester, (Player) sender);
								return true;
							}  else {
								//plugin.nSTownInvite.acceptRequest(requester, (Player) sender);
								return true;
							}
						}
					}
				}
				
				return true;
			} else {
				sender.sendMessage(ChatColor.DARK_GREEN + "Bitte nur /ja oder /nein eingeben.");
				return true;
			}
		}
		
		if(cmd.getName().equalsIgnoreCase("webui")){
			if(args.length == 0) {
				sender.sendMessage(ChatColor.AQUA + "Bitte nutze den Befehl wiefolgt:");
				sender.sendMessage(ChatColor.AQUA + "/webui {passwort}");
				sender.sendMessage(ChatColor.AQUA + "--------------------------------");
				sender.sendMessage(ChatColor.AQUA + "Mit diesem Befehl registrierst du auf deinen Nutzernamen das angegebene Passwort.");
				sender.sendMessage(ChatColor.AQUA + "Du erhälst mit diesen Daten auf die Java GUI zum Server.");
				sender.sendMessage(ChatColor.AQUA + "http://jenkins.grundbaustein-ev.de:18000/job/Calad%20Amar%20GUI/");
				return true;
			}
			if(args.length == 1) {
				String password = generatePasswordHash(args[0]);
				sender.sendMessage(ChatColor.AQUA + "Dein Passwort lautet nun:");
				sender.sendMessage(ChatColor.AQUA + args[0]);
				sender.sendMessage(ChatColor.AQUA + "--------------------------------");
				sender.sendMessage(ChatColor.AQUA + "Du kannst dich nun in der Java GUI mit deinem Benutzernamen");
				sender.sendMessage(ChatColor.AQUA + "und dem gerade ausgewählten Passwort anmelden.");
				registerJavaGuiPassword(sender.getName(), password);
				return true;
			}
			if(args.length >= 2) {
				sender.sendMessage(ChatColor.AQUA + "Bitte nutze den Befehl wiefolgt:");
				sender.sendMessage(ChatColor.AQUA + "/webui {passwort}");
				sender.sendMessage(ChatColor.AQUA + "--------------------------------");
				sender.sendMessage(ChatColor.AQUA + "Mit diesem Befehl registrierst du auf deinen Nutzernamen das angegebene Passwort.");
				sender.sendMessage(ChatColor.AQUA + "Du erhälst mit diesen Daten auf die Java GUI zum Server.");
				sender.sendMessage(ChatColor.AQUA + "http://jenkins.grundbaustein-ev.de:18000/job/Calad%20Amar%20GUI/");
				return true;
			}
		}
		
		if(cmd.getName().equalsIgnoreCase("nein")){
			if(args.length == 0) {
				
				if(sender instanceof Player) {
					if(plugin.newApproval.get((Player) sender) == null){
						sender.sendMessage(ChatColor.DARK_RED + "Du hast keine laufende Anfrage.");
						return true;
					} else {
						Player requester = plugin.newApproval.get((Player) sender);
						if(requester.isOnline() == false) {
							sender.sendMessage(ChatColor.DARK_RED + "Der Spieler, der dich eingeladen hat, ist leider offline.");
							plugin.nSTownInvite.deleteRequest(requester, (Player) sender);
							return true;
						} else {
							//sender.sendMessage(ChatColor.DARK_GREEN + "Du hast die Einladung abgelehnt.");
							if(plugin.newRequest.get(requester).equalsIgnoreCase("#TownInvite#")){
								plugin.nSTownInvite.denyRequest(requester, (Player) sender);
								return true;
							} else if(plugin.newRequest.get(requester).equalsIgnoreCase("#TownDestroy#")) {
								plugin.nSTownDestroy.denyRequest(requester, (Player) sender);
								return true;
							} else if(plugin.newRequest.get(requester).equalsIgnoreCase("#AllyInvite#")) {
								plugin.nSAllyInvite.denyRequest(requester, (Player) sender);
								return true;
							} else if(plugin.newRequest.get(requester).contains("#EnemyInvite:")) {
								plugin.nSEnemyInvite.denyRequest(requester, (Player) sender);
								return true;
							} else if(plugin.newRequest.get(requester).contains("#AllyRemove:")) {
								plugin.nSAllyRemove.denyRequest(requester, (Player) sender);
								return true;
							} else if(plugin.newRequest.get(requester).equalsIgnoreCase("#EnemyRemove#")) {
								plugin.nSEnemyRemove.denyRequest(requester, (Player) sender);
								return true;
							} else if(plugin.newRequest.get(requester).contains("#BuildingInvite:")) {
								plugin.nSBuildingInvite.denyRequest(requester, (Player) sender);
								return true;
							} else if(plugin.newRequest.get(requester).equalsIgnoreCase("#TownExpand#")) {
								plugin.nSTownExpand.denyRequest(requester, (Player) sender);
								return true;
							}   else {
								//plugin.nSTownInvite.denyRequest(requester, (Player) sender);
								return true;
							}
						}
					}
				}
				
				return true;
			} else {
				sender.sendMessage(ChatColor.DARK_GREEN + "Bitte nur /ja oder /nein eingeben.");
				return true;
			}
		}
		
		
		
		return false;
	}
	
	public void showPluginInfo(Player sender){
		sender.sendMessage(ChatColor.GREEN + plugin.logprefix);
	}
	
	public String generatePasswordHash(String password) {
		String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(PASSWORD_SALT.getBytes());
            byte[] bytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        return generatedPassword;
	}
	
	public void registerJavaGuiPassword(String username, String pass) {
		MySQL mysql = new MySQL();
		mysql.connect();
		ResultSet check = mysql.selectRS("SELECT * FROM JavaGuiUserdata WHERE Username = '" + username + "';");
		try {
			if (!check.isBeforeFirst() ) {    
				mysql.insert("INSERT INTO JavaGuiUserdata (Username, Password) VALUES ('" + username + "','" + pass + "');");
			} else {
				while(check.next()) {
					if((check.getString("Username").equalsIgnoreCase(username))) {
						mysql.update("UPDATE JavaGuiUserdata SET Password = '" + pass + "' WHERE Username = '" + username + "';");
					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mysql.disconnect();
	}
	
}