package org.community.LootChest.ChestData;

import java.util.ArrayList;
import java.util.LinkedList;

public class VsWurf extends Roll {
	private LinkedList<Wurf> wuerfe = new LinkedList<Wurf>();
	private int probability;
	
	public VsWurf(int wahrscheinlichkeit)
	{
		setWuerfe(new LinkedList<Wurf>());
		setWahrscheinlichkeit(wahrscheinlichkeit);
		super.setJobs(new ArrayList<String>());
		super.getJobs().add("XX");
		
	}
	
	public VsWurf()
	{
		probability = 0;
		super.setJobs(new ArrayList<String>());
		super.getJobs().add("XX");
	}


	public int getProbability() {
		return probability;
	}

	public void setWahrscheinlichkeit(int probability) {
		this.probability = probability;
	}

	public LinkedList<Wurf> getWuerfe() {
		return wuerfe;
	}


	public void setWuerfe(LinkedList<Wurf> wuerfe) {
		this.wuerfe = wuerfe;
	}
	
	public Wurf getWurfByIndex(int i) {
		return wuerfe.get(i);
	}
	
	public void addWurfToList(Wurf wurf) {
		wuerfe.add(wurf);
	}
	
	

}
