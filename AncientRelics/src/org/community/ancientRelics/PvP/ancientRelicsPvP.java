package org.community.ancientRelics.PvP;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Set;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.community.ancientRelics.ancientRelics;
import org.community.ancientRelics.Commands.ancientRelicsCommandPvP;
import org.community.ancientRelics.Configs.*;
import org.community.newSettlers.Town.Town;

public class ancientRelicsPvP {
		
	private final ancientRelics plugin;
	
    public ancientRelicsPvPUser aRPvPUser = null;
    public ancientRelicsPvPInventory aRPvPInventory = null;
    public ancientRelicsCommandPvP aRCommandPvP = null;
    public ancientRelicsDuellInvite aRDuellInvite = null;
    
    DecimalFormat f = new DecimalFormat("#0.00"); 
	 	 
	public ancientRelicsPvP(ancientRelics plugin)
	{
		this.plugin = plugin;
	}
	
	public boolean initiatePvP() {

		aRPvPUser = new ancientRelicsPvPUser(plugin);
		aRPvPInventory = new ancientRelicsPvPInventory(plugin);
		aRCommandPvP = new ancientRelicsCommandPvP(plugin);
		aRDuellInvite = new ancientRelicsDuellInvite(plugin);
		
		if(aRPvPUser.initiateConfig()){
			plugin.LogInfo("initialized: (PvP) user.yml");
		} else {
			plugin.LogWarning("error: (PvP) user.yml couldn't be initiated.");
			return false;
		}
		
		if(aRPvPInventory.initiateConfig()){
			plugin.LogInfo("initialized: (PvP) inventory.yml");
		} else {
			plugin.LogWarning("error: (PvP) inventory.yml couldn't be initiated.");
			return false;
		}	
		
		return true;
		
	}
	
	public boolean deactivatePvP() {
		
		aRPvPUser.saveUserConfig();
		plugin.LogInfo("initialized: (PvP) user.yml");
		
		aRPvPInventory.saveInventoryConfig();
		plugin.LogInfo("saved: (PvP) inventory.yml");
		
		return true;
		
	}
	
	public boolean getPlayerPvPStatus(String playerName) {
		
		if(aRPvPUser.user.getBoolean("Spieler." + playerName + ".PvP", false))
			return true;
		
		return false;
		
	}
	
	public void getPlayerPvPCoolDown(String playerName, Long cooldown) {
		
		aRPvPUser.user.set("Spieler." + playerName + ".CoolDown", cooldown);
		
		return;
		
	}
	
	public void togglePlayerPvPStatus(String playerName) {
		
		boolean status = false;
		
		if(aRPvPUser.user.getString("Spieler." + playerName + ".PvP") == null){
			aRPvPUser.user.set("Spieler." + playerName + ".PvP", true);
			//aRPvPUser.user.set("Spieler." + playerName + ".CoolDown", System.currentTimeMillis() - (plugin.config.getLong("Config.PvP.CooldownInMinutes") * 60 * 1000));
			plugin.getPlayerByName(playerName).sendMessage(ChatColor.GOLD + "Du hast deinen PvP-Status auf " + ChatColor.RED + "aktiv" + ChatColor.GOLD + " gesetzt.");
			plugin.getPlayerByName(playerName).sendMessage(ChatColor.GOLD + "Da du diesen Befehl das erste mal genutzt hast,");
			plugin.getPlayerByName(playerName).sendMessage(ChatColor.GOLD + "hast du keinen aktiven Cooldown und kann ihn erneut nutzen,");
			plugin.getPlayerByName(playerName).sendMessage(ChatColor.GOLD + "um dein PvP wieder zu deaktivieren.");
			plugin.getPlayerByName(playerName).sendMessage(ChatColor.GOLD + "Künftig beträgt der Cooldown " + plugin.config.getLong("Config.PvP.CooldownInMinutes") + " Minuten");
			return;
		}
		
		if(aRPvPUser.user.getDouble("Spieler." + playerName + ".Bounty", 0) >= plugin.config.getDouble("Config.PvP.KopfgeldPvPDeaktivierungsLimit", 500)) {
			plugin.getPlayerByName(playerName).sendMessage(ChatColor.GOLD + "Mit deinem Kopfgeld kannst du PvP nicht deaktivieren.");
			return;
		}
		
		if(aRPvPUser.user.getBoolean("Spieler." + playerName + ".PvP") == true){
			status = false;
			if(System.currentTimeMillis() <= (aRPvPUser.user.getLong("Spieler." + playerName + ".CoolDown") + (plugin.config.getLong("Config.PvP.CooldownInMinutes") * 60 * 1000))){
				plugin.getPlayerByName(playerName).sendMessage(ChatColor.GOLD + "Du kannst deinen PvP-Status noch nicht verändern.");
				plugin.getPlayerByName(playerName).sendMessage(ChatColor.GOLD + "Cooldown läuft in " + ChatColor.RED + (((aRPvPUser.user.getLong("Spieler." + playerName + ".CoolDown") + (plugin.config.getLong("Config.PvP.CooldownInMinutes") * 60000)) - (System.currentTimeMillis())) / 60000) + ChatColor.GOLD + " Minuten ab.");
			} else {
				aRPvPUser.user.set("Spieler." + playerName + ".PvP", status);
				aRPvPUser.user.set("Spieler." + playerName + ".CoolDown", System.currentTimeMillis());
				plugin.getPlayerByName(playerName).sendMessage(ChatColor.GOLD + "Du hast deinen PvP-Status auf " + ChatColor.RED + "inaktiv" + ChatColor.GOLD + " gesetzt.");
			}
		} else {
			status = true;
			if(System.currentTimeMillis() <= (aRPvPUser.user.getLong("Spieler." + playerName + ".CoolDown") + (plugin.config.getLong("Config.PvP.CooldownInMinutes") * 60 * 1000))){
				plugin.getPlayerByName(playerName).sendMessage(ChatColor.GOLD + "Du kannst deinen PvP-Status noch nicht verändern.");
				plugin.getPlayerByName(playerName).sendMessage(ChatColor.GOLD + "Cooldown läuft in " + ChatColor.RED + (((aRPvPUser.user.getLong("Spieler." + playerName + ".CoolDown") + (plugin.config.getLong("Config.PvP.CooldownInMinutes") * 60000)) - (System.currentTimeMillis())) / 60000) + ChatColor.GOLD + " Minuten ab.");
			} else {
				aRPvPUser.user.set("Spieler." + playerName + ".PvP", status);
				aRPvPUser.user.set("Spieler." + playerName + ".CoolDown", System.currentTimeMillis());
				plugin.getPlayerByName(playerName).sendMessage(ChatColor.GOLD + "Du hast deinen PvP-Status auf " + ChatColor.RED + "aktiv" + ChatColor.GOLD + " gesetzt.");
			}
		}
		
		
		aRPvPUser.saveUserConfig();
		return;
	}
	
	public void forcePlayerPvPStatus(String playerName, String args2) {
			
		boolean status = false;
		if(args2 == "true") {
			status = true;
		} else if(args2 == "false") {
			status = false;
		} else {
			return;
		}
		
		if(status) {
			aRPvPUser.user.set("Spieler." + playerName + ".PvP", status);
			aRPvPUser.user.set("Spieler." + playerName + ".CoolDown", System.currentTimeMillis());
			plugin.getPlayerByName(playerName).sendMessage(ChatColor.GOLD + "Du hast ein PvP-Gebiet betreten! " + ChatColor.RED + status + ChatColor.GOLD + " gesetzt.");
			plugin.getPlayerByName(playerName).sendMessage(ChatColor.GOLD + "Dein PvP-Status wurde " + ChatColor.RED + "aktiviert.");
		} else {
			aRPvPUser.user.set("Spieler." + playerName + ".PvP", status);
			aRPvPUser.user.set("Spieler." + playerName + ".CoolDown", System.currentTimeMillis());
			plugin.getPlayerByName(playerName).sendMessage(ChatColor.GOLD + "Du hast ein PvP-Gebiet verlassen! " + ChatColor.GREEN + status + ChatColor.GOLD + " gesetzt.");
			plugin.getPlayerByName(playerName).sendMessage(ChatColor.GOLD + "Dein PvP-Status wurde " + ChatColor.GREEN + "deaktiviert.");
		}
		aRPvPUser.saveUserConfig();
		return;
	}
	
	public void setKillDeath(Player attacker, Player defender) {
		
		aRPvPUser.user.set("Spieler." + attacker.getName() + ".Kills", aRPvPUser.user.getInt("Spieler." + attacker.getName() + ".Kills", 0) + 1);
		aRPvPUser.user.set("Spieler." + defender.getName() + ".Deaths", aRPvPUser.user.getInt("Spieler." + defender.getName() + ".Deaths", 0) + 1);
		
		aRPvPUser.user.set("Spieler." + attacker.getName() + ".Victim", defender.getName());
		aRPvPUser.user.set("Spieler." + defender.getName() + ".Killer", attacker.getName());
				
		return;
		
	}
	
	public void giveBountyInfo(Player attacker, Player defender) {
		/*
		defender.sendMessage(ChatColor.LIGHT_PURPLE + "+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=");
		
		defender.sendMessage(ChatColor.LIGHT_PURPLE + "Du wurdest von " + attacker.getName() + " get�tet.");
		defender.sendMessage(ChatColor.LIGHT_PURPLE + "Wenn du ein Kopfgeld aussetzen m�chtest,");
		defender.sendMessage(ChatColor.LIGHT_PURPLE + "nutze bitte den Befehl " + ChatColor.RED + "/bounty " + ChatColor.LIGHT_PURPLE + ".");
		defender.sendMessage(ChatColor.LIGHT_PURPLE + "");
		defender.sendMessage(ChatColor.LIGHT_PURPLE + "Zu jagendes Ziel: " + ChatColor.RED + attacker.getName());
		defender.sendMessage(ChatColor.LIGHT_PURPLE + "H�he des Kopfgeldes: " + ChatColor.RED + plugin.config.getInt("Config.PvP.KopfgeldInGulden", 10) + " Gulden");
		
		defender.sendMessage(ChatColor.LIGHT_PURPLE + "+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=");
				
		return;
		*/
	}
	
	public void setBounty(Player player) {
		/*
		if(aRPvPUser.user.getString("Spieler." + player.getName() + ".Killer", "[Head]").contains("[Head]")){
			player.sendMessage(ChatColor.RED + "Du hast bereits ein Kopfgeld auf diesen Spieler ausgesetzt!");
			return;
		}
		//if(!plugin.economy.has(player.getName(), plugin.config.getInt("Config.PvP.KopfgeldInGulden", 10))) {
		//	player.sendMessage(ChatColor.RED + "Du kannst dir das Kopfgeld nicht leisten!");
		//	return;
		//}
		
		String target = aRPvPUser.user.getString("Spieler." + player.getName() + ".Killer");
		aRPvPUser.user.set("Spieler." + player.getName() + ".Killer", aRPvPUser.user.getString("Spieler." + player.getName() + ".Killer") + " [Head]");
		aRPvPUser.user.set("Spieler." + target + ".Bounty", aRPvPUser.user.getInt("Spieler." + target + ".Bounty", 0) + plugin.config.getInt("Config.PvP.KopfgeldInGulden", 10));
		//plugin.economy.withdrawPlayer(player.getName(), plugin.config.getInt("Config.PvP.KopfgeldInGulden", 10));
		player.sendMessage(ChatColor.RED + "Du hast ein Kopfgeld von " + ChatColor.YELLOW + plugin.config.getInt("Config.PvP.KopfgeldInGulden", 10) + ChatColor.RED + " Gulden auf " + ChatColor.RED + target + ChatColor.RED + " ausgesetzt.");
		
				
		return;
		*/
	}
	
	public void manageBounty(Player winner, Player looser) {
		
		if(aRPvPUser.user.getInt("Spieler." + looser.getName() + ".Bounty", 0) > 0){			
			//winner.sendMessage(ChatColor.AQUA + "Dir wurde das Kopfgeld von " + ChatColor.YELLOW + looser.getName() + ChatColor.AQUA + " in H�he von " + ChatColor.YELLOW + aRPvPUser.user.getInt("Spieler." + looser.getName() + ".Bounty", 0) + " Punkten" + ChatColor.AQUA + " als Nationspunkte gutgeschrieben.");
			aRPvPUser.user.set("Spieler." + winner.getName() + ".Bounty", aRPvPUser.user.getInt("Spieler." + winner.getName() + ".Bounty", 0) + 1);
			aRPvPUser.user.set("Spieler." + winner.getName() + ".Realmpoints", aRPvPUser.user.getInt("Spieler." + winner.getName() + ".Realmpoints", 0) + aRPvPUser.user.getInt("Spieler." + looser.getName() + ".Bounty", 0));
						
			Town town = plugin.newSettlersAPI.nSCore.getPlayerTown(winner);
			if(town != null) {
				town.addRealmpoints(aRPvPUser.user.getInt("Spieler." + looser.getName() + ".Bounty", 0));
			}
			
			aRPvPUser.user.set("Spieler." + looser.getName() + ".Bounty", 0);
			
			return;
		}
				
		return;
		
	}
	
	public void showPlayerStatusList(CommandSender sender) {
		
		ConfigurationSection spielerSection = aRPvPUser.user.getConfigurationSection("Spieler"); 
		if(spielerSection == null)
		{
			sender.sendMessage("Keine Ergebnisse gefunden!");
			return;
		}
		Set<String> spielerKeys = spielerSection.getKeys(false);
		String[] spielerArray = spielerKeys.toArray(new String[0]);
		String spielerInfo = "";
		String spieler = null;
		boolean status = false;
		for(int i = 0; i < spielerKeys.size(); i++)
		{ 							
			spieler = spielerArray[i];
			if(plugin.getPlayerByName(spieler) != null) {			
				status = aRPvPUser.user.getBoolean("Spieler." + spieler + ".PvP");
				if(status) {
					spielerInfo += ChatColor.RED + spieler + ChatColor.WHITE + ", ";
				} else {
					spielerInfo += ChatColor.GREEN + spieler + ChatColor.WHITE + ", ";
				}
			}
		}
		
		sender.sendMessage(ChatColor.RED + "roter Name = PvP aktiv" + ChatColor.WHITE + ", " + ChatColor.GREEN + "gr�ner Name = PvP inaktiv" + ChatColor.WHITE + ".");
		sender.sendMessage(spielerInfo);
		
		return;
		
	}
	
	public void showPlayerStatus(CommandSender sender, String player) {
		
		if(aRPvPUser.user.getString("Spieler." + player + ".PvP") == null) {
			sender.sendMessage(ChatColor.GOLD + "+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=");
			sender.sendMessage(ChatColor.GOLD + "Zu diesem Spieler gibt es keine Statistik.");
			sender.sendMessage(ChatColor.GOLD + "+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=");
		}
		boolean status = aRPvPUser.user.getBoolean("Spieler." + player + ".PvP", false);
		Long cooldown = aRPvPUser.user.getLong("Spieler." + player + ".CoolDown", -1);
		sender.sendMessage(ChatColor.GOLD + "+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=");
		sender.sendMessage(ChatColor.GOLD + "Spieler: " + ChatColor.GREEN + player);
		if(status) {
			sender.sendMessage(ChatColor.GOLD + "Status: " + ChatColor.RED + "PvP aktiv");
		} else {
			sender.sendMessage(ChatColor.GOLD + "Status: " + ChatColor.GREEN + "PvP inaktiv");
		}
		if((((aRPvPUser.user.getLong("Spieler." + player + ".CoolDown") + (plugin.config.getLong("Config.PvP.CooldownInMinutes") * 60000)) - (System.currentTimeMillis())) / 60000) <= 0){
			cooldown = (long) 0;
		} else {
			cooldown = (((aRPvPUser.user.getLong("Spieler." + player + ".CoolDown") + (plugin.config.getLong("Config.PvP.CooldownInMinutes") * 60000)) - (System.currentTimeMillis())) / 60000);
		}
			
		sender.sendMessage(ChatColor.GOLD + "Cooldown: " + ChatColor.AQUA + cooldown + " Minuten");
		sender.sendMessage(ChatColor.GOLD + "");
		sender.sendMessage(ChatColor.GOLD + "Kopfgeld: " + ChatColor.YELLOW + aRPvPUser.user.getInt("Spieler." + player + ".Bounty", 0));
		sender.sendMessage(ChatColor.GOLD + "Nationspunkte: " + ChatColor.YELLOW + aRPvPUser.user.getInt("Spieler." + player + ".Realmpoints", 0));
		sender.sendMessage(ChatColor.GOLD + "");
		sender.sendMessage(ChatColor.GOLD + "Tötungen: " + ChatColor.GREEN + aRPvPUser.user.getInt("Spieler." + player + ".Kills", 0));
		sender.sendMessage(ChatColor.GOLD + "Tode: " + ChatColor.RED + aRPvPUser.user.getInt("Spieler." + player + ".Deaths", 0));
		sender.sendMessage(ChatColor.GOLD + "K/D-Verh�ltnis: " + ChatColor.AQUA + f.format((double) aRPvPUser.user.getDouble("Spieler." + player + ".Kills", 1) / aRPvPUser.user.getDouble("Spieler." + player + ".Deaths", 1)));
		sender.sendMessage(ChatColor.GOLD + "");
		sender.sendMessage(ChatColor.GOLD + "Letzter Mörder: " + ChatColor.YELLOW + aRPvPUser.user.getString("Spieler." + player + ".Killer", "-"));
		sender.sendMessage(ChatColor.GOLD + "Letztes Opfer: " + ChatColor.YELLOW + aRPvPUser.user.getString("Spieler." + player + ".Victim", "-"));
		sender.sendMessage(ChatColor.GOLD + "+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=");
				
		return;
		
	}
	
	public void setPlayerInventory(Player player) {
		
		//String inventory = saveKit(inv);
		this.aRPvPInventory.inventory.set("Spieler." + player.getName() + ".Inventar", player.getInventory().getContents());
		this.aRPvPInventory.inventory.set("Spieler." + player.getName() + ".Armor", player.getInventory().getArmorContents());
		this.aRPvPInventory.saveInventoryConfig();
		
	}
	
	public void getPlayerInventory(Player player) {

		if(this.aRPvPInventory.inventory.getString("Spieler." + player.getName() + ".Inventar") == null)
			return;

		if(this.aRPvPInventory.inventory.getString("Spieler." + player.getName() + ".Inventar").equalsIgnoreCase("debug"))
			return;

		player.getInventory().setContents((ItemStack[]) this.aRPvPInventory.inventory.get("Spieler." + player.getName() + ".Inventar"));
		player.getInventory().setArmorContents((ItemStack[]) this.aRPvPInventory.inventory.get("Spieler." + player.getName() + ".Armor"));
		
		this.aRPvPInventory.inventory.set("Spieler." + player.getName() + ".Inventar", "debug");
		this.aRPvPInventory.inventory.set("Spieler." + player.getName() + ".Armor", "debug");
		this.aRPvPInventory.saveInventoryConfig();

	}
	
	public void onDeathInventorySave(Player winner, Player looser, List<ItemStack> drops) {

		
		plugin.aRPvP.setKillDeath(winner, looser);
		plugin.aRPvP.giveBountyInfo(winner, looser);
		
		// Beginn der Routine zum Sichern des Inventars
		/*
		Inventory inv = plugin.getServer().createInventory(null, InventoryType.PLAYER);
		
		List<ItemStack> tempInv = drops;
		
		for(int i=0; i < tempInv.size(); i++){
			int newItemSlot = inv.firstEmpty();
			inv.setItem(newItemSlot, tempInv.get(i));
		}
		*/
		plugin.aRPvP.setPlayerInventory(looser);
		
		ItemStack[] inventory = looser.getInventory().getContents();
		for (int i = 0; i < inventory.length; i++) {
			ItemStack is = inventory[i];

			if (is != null)
				drops.remove(is);
			else
				inventory[i] = null;
		}
		inventory = looser.getEquipment().getArmorContents();
		for (int i = 0; i < inventory.length; i++) {
			ItemStack is = inventory[i];

			if (is != null)
				drops.remove(is);
			else
				inventory[i] = null;
		}
		
		// Ende der Routine zum Sichern des Inventars
		
		// Routine f�r die Kopfgeldauszahlung
		
		plugin.aRPvP.manageBounty(winner, looser);
		
		return;
	}
	
	public void onDeathInventorySave(Player looser, List<ItemStack> drops) {

		// Beginn der Routine zum Sichern des Inventars
		/*
		Inventory inv = plugin.getServer().createInventory(null, InventoryType.PLAYER);
		
		List<ItemStack> tempInv = drops;
		
		for(int i=0; i < tempInv.size(); i++){
			int newItemSlot = inv.firstEmpty();
			inv.setItem(newItemSlot, tempInv.get(i));
		}
		*/
		plugin.aRPvP.setPlayerInventory(looser);
		
		ItemStack[] inventory = looser.getInventory().getContents();
		for (int i = 0; i < inventory.length; i++) {
			ItemStack is = inventory[i];

			if (is != null)
				drops.remove(is);
			else
				inventory[i] = null;
		}
		inventory = looser.getEquipment().getArmorContents();
		for (int i = 0; i < inventory.length; i++) {
			ItemStack is = inventory[i];

			if (is != null)
				drops.remove(is);
			else
				inventory[i] = null;
		}
		
		// Ende der Routine zum Sichern des Inventars
		
		return;
	}
	/*
	public String saveKit(Inventory stack) {
		String inv = "";
		if(stack==null){
			String debug = "debug";
			return debug;
		}
		else{
			inv = toBase64(stack);
			return inv;
		}
	} 
	public static Inventory getContentInventory(PlayerInventory inventory)
	{
	ItemStack[] content = inventory.getContents();
	CraftInventoryCustom storage = new CraftInventoryCustom(null, content.length);
	 
	for (int i = 0; i < content.length; i++)
	storage.setItem(i, content[i]);
	 
	return storage;
	}
	
	public static String toBase64(Inventory inventory) {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		DataOutputStream dataOutput = new DataOutputStream(outputStream);
		NBTTagList itemList = new NBTTagList();
		for (int i = 0; i < inventory.getSize(); i++) {
			NBTTagCompound outputObject = new NBTTagCompound();
			CraftItemStack craft = getCraftVersion(inventory.getItem(i));
			if (craft != null)
				CraftItemStack.asNMSCopy(craft).save(outputObject);
			itemList.add(outputObject);
		}
		NBTBase.a(itemList, dataOutput);
		return new BigInteger(1, outputStream.toByteArray()).toString(32);
	}

	public static Inventory fromBase64(String data) {
		ByteArrayInputStream inputStream = new ByteArrayInputStream(
				new BigInteger(data, 32).toByteArray());
		NBTTagList itemList = (NBTTagList) NBTBase.a(new DataInputStream(
				inputStream));
		Inventory inventory = new CraftInventoryCustom(null, itemList.size());

		for (int i = 0; i < itemList.size(); i++) {
			NBTTagCompound inputObject = (NBTTagCompound) itemList.get(i);
			if (!inputObject.isEmpty()) {
				net.minecraft.server.v1_6_R3.ItemStack is = net.minecraft.server.v1_6_R3.ItemStack
						.createStack(inputObject);
				inventory.setItem(i, CraftItemStack.asBukkitCopy(is));
			}
		}
		return inventory;
	}

	private static CraftItemStack getCraftVersion(ItemStack stack) {
		if (stack instanceof CraftItemStack)
			return (CraftItemStack) stack;
		else if (stack != null)
			return CraftItemStack.asCraftCopy(stack);
		else
			return null;
	}*/
	
}