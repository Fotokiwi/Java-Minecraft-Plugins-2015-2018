package org.community.LootChest.Cache;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.community.LootChest.LootChest;
import org.community.LootChest.ChestData.LootChestTemplate;
import org.community.LootChest.UserData.Userdata;

public class Cache
{

    LootChest                              plugin         = null;
    private List<String>                   fileNames      = null;
    private Map<String, LootChestTemplate> LootChestList  = null;
    private Map<UUID, Userdata>          userDataChests = null;

    public Cache(LootChest plugin)
    {
        this.plugin = plugin;
        LootChestList = new HashMap<String, LootChestTemplate>();
        userDataChests = new HashMap<UUID, Userdata>();
        fileNames = new LinkedList<String>();
    }

    /*public void loadUserDataChestsMap()
    {
        File dir = new File(plugin.getDataFolder() + "/lootChestsUserData/");
        String fileName;
        File[] files = dir.listFiles();
        if (files != null)
        {
            for (int i = 0; i < files.length; i++)
            {
                if (files[i].isDirectory())
                {
                } else
                {
                    fileName = files[i].getName();
                    fileName = fileName.substring(0, fileName.indexOf(".yml", 0));
                    Userdata ud = new Userdata(plugin, fileName);
                    ud.LoadFromFile();
                    userDataChests.put(fileName, new Userdata(plugin, fileName));
                    //plugin.LogInfo("initialized: LootChestUserData (" + fileName + ")");
                }
            }
        }

    }*/
    
    @SuppressWarnings("unused")
	public void loadUserData(UUID id)
    {
        File file = new File(plugin.getDataFolder() + "/lootChestsUserData/" + id + ".yml");
        if(file != null)
        {
        	Userdata ud = new Userdata(plugin, id);
        	ud.LoadFromFile();
        	userDataChests.put(id, ud);
        }
        else {
        	plugin.LogInfo("couldn't load an user with that UUID");    	
        }
    }

    public void saveUserDataChestsMap()
    {
        if (!userDataChests.keySet().isEmpty())
        {
            for (UUID fileName : userDataChests.keySet())
            {
                Userdata ud = userDataChests.get(fileName);
                ud.SaveToFile();
            }
        }
    }

    public void loadLootChestList()
    {
        File dir = new File(plugin.getDataFolder() + "/lootChests/");
        String fileName;
        File[] files = dir.listFiles();
        if (files != null)
        {
            for (int i = 0; i < files.length; i++)
            {
                if (files[i].isDirectory())
                {
                } else
                {
                    fileName = files[i].getName();
                    fileName = fileName.substring(0, fileName.indexOf(".yml", fileName.indexOf("_", 0)));
                    LootChestTemplate lootChest = new LootChestTemplate(plugin, fileName);
                    lootChest.loadFromFile();
                    LootChestList.put(fileName, lootChest);
                    fileNames.add(fileName);
                    //plugin.LogInfo("initialized: LootChest (" + lootChest.getLoc().getBlockX() + ", " + lootChest.getLoc().getBlockY() + ", " + lootChest.getLoc().getBlockZ() + ")"); 
                }
            }
        }

    }

    public void saveLootChestList()
    {
        if (!fileNames.isEmpty())
        {
            LootChestTemplate lc;
            for (String fileName : fileNames)
            {
                lc = LootChestList.get(fileName);
                lc.saveToFile();
            }
        }
    }

    public List<String> getFileNames()
    {
        return fileNames;
    }

    public boolean fileNamesContainsString(String name)
    {
        boolean StringVorhanden = false;
        for (String a : fileNames)
        {
            if (a.compareTo(name) == 0)
                StringVorhanden = true;
        }
        return StringVorhanden;
    }

    public void setFileNames(List<String> fileNames)
    {
        this.fileNames = fileNames;
    }

    public Map<String, LootChestTemplate> getLootChestList()
    {
        return LootChestList;
    }

    public void setLootChestList(Map<String, LootChestTemplate> lootChestList)
    {
        LootChestList = lootChestList;
    }

    public Map<UUID, Userdata> getUserDataChests()
    {
        return userDataChests;
    }

    public void setUserDataChests(Map<UUID, Userdata> userDataChests)
    {
        this.userDataChests = userDataChests;
    }

}
