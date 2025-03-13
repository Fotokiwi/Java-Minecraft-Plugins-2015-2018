package org.community.LootChest.UserData;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.community.LootChest.LootChest;

public class Userdata
{

    public LootChest                   plugin         = null;
    private FileConfiguration          userData       = null;
    private File                       userDataFile   = null;
   // private String                     playerName;
    private Map<String, ChestUserData> userDataValues = null;
    private UUID					   id = null;

   /* public Userdata(LootChest plugin, String playerName)
    {
        this.plugin = plugin;
       // this.playerName = playerName;
        userDataValues = new HashMap<String, ChestUserData>();
    }*/
    
    public Userdata(LootChest plugin, UUID id) 
    {
    	this.plugin = plugin;
    	this.id = id;
    	//this.playerName = plugin.getServer().getPlayer(id).getName();
    	userDataValues = new HashMap<String, ChestUserData>();
    }

    public void SaveToFile()
    {
        getUserData();
        
        for (String loc : userDataValues.keySet())
        {
            int indexp = loc.indexOf(".");
            String world = loc.substring(0, indexp);
            String withoutWorld = loc.substring(indexp + 1);
            withoutWorld = withoutWorld.replace('.', '|');
            userData.set(world + "." + withoutWorld + ".LetzteBenutzung", userDataValues.get(loc).getLastUse());
            userData.set(world + "." + withoutWorld + ".InventoryName", userDataValues.get(loc).getInventory().getName());
            userData.set(world + "." + withoutWorld + ".Inventory", userDataValues.get(loc).getInventory().getContents());

        }

        saveUserData();
    }

    @SuppressWarnings("rawtypes")
	public void LoadFromFile()
    {
        getUserData();

        ConfigurationSection allPath = userData.getConfigurationSection("");
        Set<String> allPathString = allPath.getKeys(false);
        for (String path : allPathString)
        {
            String x, y, z, worldname;
            worldname = path;
            ConfigurationSection newPath = userData.getConfigurationSection(path);
            Set<String> NewPathString = newPath.getKeys(false);
            for (String nPath : NewPathString)
            {
                int index1 = nPath.indexOf("_", 0);
                int index2 = nPath.indexOf("_", index1 + 1);
                x = nPath.substring(0, index1);
                y = nPath.substring(index1 + 1, index2);
                z = nPath.substring(index2 + 1);
                
            	String invName = "Belohnungstruhe";
            	if(userData.contains(worldname + "." + x + "_" + y + "_" + z + ".InventoryName"))
            		invName = userData.getString(worldname + x + "_" + y + "_" + z + ".InventoryName", "Belohnungstruhe");
                Inventory inv = Bukkit.createInventory(null, 54, invName);
        
                if(userData.contains(worldname + "." + x + "_" + y + "_" + z + ".Inventory"))    {  
                	List l = (List) userData.get(worldname + "." + x + "_" + y + "_" + z + ".Inventory");
                	@SuppressWarnings("unchecked")
					ItemStack[] is = (ItemStack[]) l.toArray(new ItemStack[0]);


                	
                	inv.setContents(is);
                }
                
                ChestUserData cud = new ChestUserData(userData.getLong(worldname + "." + x + "_" + y + "_" + z + ".LetzteBenutzung"), 
                        inv);
                x = x.replace('|', '.');
                z = z.replace('|', '.');
                userDataValues.put(worldname + "." + x + "_" + y + "_" + z, cud);
            }
        }

        saveUserData();
    }

    private void reloadUserData()
    {
        if (userDataFile == null)
        {
            userDataFile = new File(plugin.getDataFolder(), "/lootChestsUserData/" + id + ".yml");
        }
        userData = YamlConfiguration.loadConfiguration(userDataFile);
    }

    private FileConfiguration getUserData()
    {
        if (userData == null)
        {
            reloadUserData();
        }
        return userData;
    }

    private void saveUserData()
    {
        if (userData == null || userDataFile == null)
        {
            return;
        }
        try
        {
            userData.save(userDataFile);
        } catch (IOException ex)
        {
            Logger.getLogger(JavaPlugin.class.getName()).log(Level.SEVERE, "Could not save config to " + userDataFile, ex);
        }
    }

    public Map<String, ChestUserData> getUserDataValues()
    {
        return userDataValues;
    }

    public void setUserDataValues(Map<String, ChestUserData> userDataValues)
    {
        this.userDataValues = userDataValues;
    }
    




}
