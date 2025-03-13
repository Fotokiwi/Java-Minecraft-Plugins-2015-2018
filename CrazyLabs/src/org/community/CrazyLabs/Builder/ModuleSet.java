/**
 * 
 */
package org.community.CrazyLabs.Builder;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.community.CrazyLabs.CrazyLabs;
/**
 * Enthält alle Moduleformen und ermöglicht das Hinzufügen von neuen Moduleformen. 
 * @author steen
 *
 */
public class ModuleSet {
	public static final String[] Form = { 
		"U", 
		"D", "UD", 
		"L", "UL", "DL", "UDL", 
		"R", "UR", "DR", "UDR", "LR", "ULR", "DLR", "UDLR", 
		"F", "UF", "DF", "UDF", "LF", "ULF", "DLF", "UDLF", "RF", "URF", "DRF", "UDRF", "LRF", "ULRF", "DLRF", "UDLRF", 
		"B", "UB", "DB", "UDB", "LB", "ULB", "DLB", "UDLB", "RB", "URB", "DRB", "UDRB", "LRB", "ULRB", "DLRB", "UDLRB", "FB", "UFB", "DFB", "UDFB", "LFB", "ULFB", "DLFB", "UDLFB", "RFB", "URFB", "DRFB", "UDRFB", "LRFB", "ULRFB", "DLRFB", "UDLRFB"};
	private Map<String, ModuleForm> map; 
	private CrazyLabs plugin;
	
	public ModuleSet (CrazyLabs plugin)
	{
		this.plugin = plugin;
		map = new HashMap<String, ModuleForm>();
		for(String f : Form)
			map.put(f, new ModuleForm());
	}
	
	public void addModule(String f, Module m)
	{
		map.get(f).addModule(m);
	}
	
	public Module getModuleByForm(String f)
	{
		return map.get(f).getOneModule();
	}
	
	
	
		@SuppressWarnings("unused")	
	public void LoadAllBuildPatternAndAddModules(String theme, int size) {
		for(String form : Form)
		{
		File dir = new File(plugin.getDataFolder() + "/Modules/" + theme + "/" + size + "/" + form + "/");
		String fileName;
		File[] files = dir.listFiles();
		if (files != null) {
			for (int i = 0; i < files.length; i++) {
				if (files[i].isDirectory()) {
				} else {
					addModule(form, new Module(loadBuildPatternFromFile(files[i])));
				}
			}
		}
		else
			CrazyLabs.LogError("Konnte keine BuildPatterns für " + theme + " in der Größe " + size + " mit der Form " + form + " laden.");
		}
	}
	

	private Map<String, String> loadBuildPatternFromFile(File f) {
		FileConfiguration buildPatternFC =  YamlConfiguration.loadConfiguration(f);
		Map<String, String> buildPattern = new HashMap<String, String>();
		ConfigurationSection buildPatternCS = buildPatternFC.getConfigurationSection("buildPattern");
		Map<String, Object> dataFileMap = buildPatternCS.getValues(false);
		for (Entry<String, Object> e : dataFileMap.entrySet()) {
			try {
				buildPattern.put(e.getKey(), (String) e.getValue());
			} catch (ClassCastException cce) {
				CrazyLabs.LogError("couldn't cast value of buildpattern from object to string: " + cce);
			}
		}
		return buildPattern;
	}


}
