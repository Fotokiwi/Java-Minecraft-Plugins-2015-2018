package org.community.ambience;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.bukkit.Location;
import org.bukkit.util.Vector;
import org.community.ambience.AmbientObjects.dataObjects.Blueprint;

public class Cache {

	private Ambience plugin;
	private Map<String, Blueprint> blueprints;
	private Map<Location, Vector[]> placedBlueprints;

	public Cache(Ambience plugin) {
		this.plugin = plugin;
		blueprints = new HashMap<String, Blueprint>();
		placedBlueprints = new HashMap<Location, Vector[]>();
	}
	

	
	public void loadAllBlueprints() {
		File dir = new File(plugin.getDataFolder() + "/Blueprints/");
		String fileName;
		File[] files = dir.listFiles();
		if (files != null) {
			for (int i = 0; i < files.length; i++) {
				if (files[i].isDirectory()) {
				} else {
					fileName = files[i].getName();
					fileName = fileName.substring(0, fileName.indexOf(".yml", 0));
					Blueprint bp = new Blueprint(plugin, fileName);
					bp.LoadFromFile();
					blueprints.put(fileName, bp);
				}
			}
		}
	}

	public Set<String> listOfAllBlueprints() {
		Set<String> blueprintSet = new HashSet<String>();
		File dir = new File(plugin.getDataFolder() + "/Blueprints/");
		String fileName;
		File[] files = dir.listFiles();
		if (files != null) {
			for (int i = 0; i < files.length; i++) {
				if (files[i].isDirectory()) {
				} else {
					fileName = files[i].getName();
					fileName = fileName.substring(0, fileName.indexOf(".yml", 0));
					blueprintSet.add(fileName);
				}
			}
		}
		return blueprintSet;
	}

	public boolean blueprintExists(String blueprintName) {
		blueprintName = blueprintName.toLowerCase();
		return blueprints.containsKey(blueprintName);
	}

	public void saveAllBlueprints() {
		for (Entry<String, Blueprint> entry : blueprints.entrySet())
			entry.getValue().SaveToFile();
	}

	public void addBlueprint(String blueprintIdentifier, Map<String, String> m) {
		//plugin.LogInfo("bp vor toLowerCase: " + blueprintIdentifier);
		blueprintIdentifier = blueprintIdentifier.toLowerCase();
		//plugin.LogInfo("bp nach toLowerCase: " + blueprintIdentifier);
		Blueprint bp = new Blueprint(plugin, blueprintIdentifier, m);
		blueprints.put(blueprintIdentifier, bp);
	}

	public void addBlueprint(String blueprintIdentifier, String displayName, Map<String, String> m) {
		blueprintIdentifier = blueprintIdentifier.toLowerCase();
		Blueprint bp = new Blueprint(plugin, blueprintIdentifier, m, displayName);
		blueprints.put(blueprintIdentifier, bp);

	}

	public Blueprint getBlueprintByName(String bpIdentifier) {
		bpIdentifier = bpIdentifier.toLowerCase();
		return blueprints.get(bpIdentifier);
	}
	
	public Map<Location, Vector[]> getPlacedBlueprints(){
		return placedBlueprints;
	}
	
	public void setPlacedBlueprints(Map<Location, Vector[]> placedBlueprints){
		this.placedBlueprints = placedBlueprints;
	}
}
