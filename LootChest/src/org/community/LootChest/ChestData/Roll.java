package org.community.LootChest.ChestData;

import java.util.List;

public abstract class Roll {
	private List<String> jobs;

	public List<String> getJobs() {
		return jobs;
	}

	public void setJobs(List<String> jobs) {
		this.jobs = jobs;
	}
	
	public boolean hashMatchJobs(String hash)
	{
		String[] playerJobs = (hash.split(",")[1]).split("-");
		boolean contains = false;
		for(int i = 0; i < playerJobs.length; i++)
		{
			for(String j : jobs)
			{
				if(playerJobs[i].equals(j))
				{
					contains = true;
					break;
				}
			}
		}
		
		return contains;
		
	}
	
}
