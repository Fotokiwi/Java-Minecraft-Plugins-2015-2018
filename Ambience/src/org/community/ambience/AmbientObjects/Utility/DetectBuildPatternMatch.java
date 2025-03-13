package org.community.ambience.AmbientObjects.Utility;

import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.util.Vector;

public class DetectBuildPatternMatch {

	public static Vector[] getBoundingBox(Map<String, String> buildPattern){
		Vector[] v = new Vector[2];	
		v[0] = new Vector();
		v[1] = new Vector();
		if(buildPattern.isEmpty())
			return null;
		boolean first = true;
		for(Entry<String, String> e : buildPattern.entrySet()){
			if(first){
				String[] loc = e.getKey().split(",");
				int x = Integer.parseInt(loc[1]);
				int y = Integer.parseInt(loc[2]);
				int z = Integer.parseInt(loc[3]);
				v[0].setX(x);
				v[0].setY(y);
				v[0].setZ(z);
				v[1].setX(x);
				v[1].setY(y);
				v[1].setZ(z);
				first = false;
			}
			else {
			String[] loc = e.getKey().split(",");
			int x = Integer.parseInt(loc[1]);
			int y = Integer.parseInt(loc[2]);
			int z = Integer.parseInt(loc[3]);
			if(v[0].getBlockX() > x){
				v[0].setX(x);
			}
			if(v[0].getBlockY() > y){
				v[0].setY(y);
			}
			if(v[0].getBlockZ() > z){
				v[0].setZ(z);
			}
			if(v[1].getBlockX() < x){
				v[1].setX(x);
			}
			if(v[1].getBlockY() < y){
				v[1].setY(y);
			}
			if(v[1].getBlockZ() < z){
				v[1].setZ(z);
			}
			}
		}
		return v;
	}
}
