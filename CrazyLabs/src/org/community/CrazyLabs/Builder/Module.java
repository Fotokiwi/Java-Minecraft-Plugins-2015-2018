package org.community.CrazyLabs.Builder;

import java.util.Map;

public class Module {
		
	private Map<String, String> buildPattern;
	
	public Module(Map<String, String> buildPattern)
	{
		this.setBuildPattern(buildPattern);
	}


	public Map<String, String> getBuildPattern() {
		return buildPattern;
	}

	public void setBuildPattern(Map<String, String> buildPattern) {
		this.buildPattern = buildPattern;
	}
	
}
