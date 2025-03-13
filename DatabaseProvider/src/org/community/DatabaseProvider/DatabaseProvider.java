package org.community.DatabaseProvider;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.community.DatabaseProvider.Logging.Log;
import org.community.DatabaseProvider.MySQL.MySQL;
import org.community.DatabaseProvider.Utilities.JarUtils;

public class DatabaseProvider extends JavaPlugin {
	// MySQL Database class
	public MySQL mysqlDatabase = null;
	
	public static Log log = null;

	public static FileConfiguration config = null;
    public static File configFile = null;
	
	public void reloadConfig() {
	    if (configFile == null) {
	    configFile = new File(getDataFolder(), "config.yml");
	    }
	    config = YamlConfiguration.loadConfiguration(configFile);
	}

	public FileConfiguration getConfig() {
	    if (config == null) {
	        reloadConfig();
	    }
	    return config;
	}
	
	public void saveConfig() {
	    if (config == null || configFile == null) {
	    return;
	    }
	    try {
	        config.save(configFile);
	    } catch (IOException ex) {
	        DatabaseProvider.log.LogError("Could not save config to " + configFile, ex);
	    }
	}
	
	private Boolean loadConfig()
	{
		reloadConfig();
		getConfig();
		
		if(config.getString("Default.Url") == null)
			config.set("Default.Url", "10.0.100.111");
		if(config.getString("Default.Port") == null)
			config.set("Default.Port", 3306);
		if(config.getString("Default.Database") == null)
			config.set("Default.Database", "ca_minecraft");
		if(config.getString("Default.Username") == null)
			config.set("Default.Username", "ca_minecraft");
		if(config.getString("Default.Password") == null)
			config.set("Default.Password", "FBbS78o0H5FKM5h4hhG8");
		
		config.set("Version", "0.0.1");
		saveConfig();
		
		return true;
	}
	
	public void onEnable() {	

		try {
            final File[] libs = new File[] {
                    new File(getDataFolder(), "log4j-1.2.17.jar"),
                    new File(getDataFolder(), "mysql-connector-java-5.1.32-bin.jar") };
            for (final File lib : libs) {
                if (!lib.exists()) {
                    JarUtils.extractFromJar(lib.getName(),
                            lib.getAbsolutePath());
                }
            }
            for (final File lib : libs) {
                if (!lib.exists()) {
                    getLogger().warning(
                            "There was a critical error loading My plugin! Could not find lib: "
                                    + lib.getName());
                    this.getServer().getPluginManager().disablePlugin(this);
                    return;
                }
                addClassPath(JarUtils.getJarUrl(lib));
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }
		
		DatabaseProvider.log = new Log("DatabaseProvider");
		
		this.mysqlDatabase = new MySQL();
				
		loadConfig();
		
	}	
	
	public void onDisable() {
		
		saveConfig();
		
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		
		if(cmd.getName().equalsIgnoreCase("database")){	
			if(sender instanceof Player) {
				this.mysqlDatabase.connect();
				this.mysqlDatabase.alterTable("ALTER TABLE Statistics ADD D TEXT NOT NULL", "Statistics", "D");
				String[][] ergebnis = this.mysqlDatabase.selectString("SELECT * FROM Statistics");
				for(int i = 0; i < ergebnis.length; i++) {
					String temp = "";
					for(int j = 0; j < ergebnis[0].length; j++) {
						temp = temp + ergebnis[i][j] + ",";
					}
					temp = temp.substring(0,temp.length()-1);
					sender.sendMessage(temp);
				}
				this.mysqlDatabase.disconnect();
			} else {
				sender.sendMessage("Es sind keinen Konsolenbefehle verfügbar");
				return true;
			}
			
		}
		
		return true;
	}
	
	private void addClassPath(final URL url) throws IOException {
        final URLClassLoader sysloader = (URLClassLoader) ClassLoader
                .getSystemClassLoader();
        final Class<URLClassLoader> sysclass = URLClassLoader.class;
        try {
            final Method method = sysclass.getDeclaredMethod("addURL",
                    new Class[] { URL.class });
            method.setAccessible(true);
            method.invoke(sysloader, new Object[] { url });
        } catch (final Throwable t) {
            t.printStackTrace();
            throw new IOException("Error adding " + url
                    + " to system classloader");
        }
    }
	
}
