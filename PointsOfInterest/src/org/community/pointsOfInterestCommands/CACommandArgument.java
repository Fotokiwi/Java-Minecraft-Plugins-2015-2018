package org.community.pointsOfInterestCommands;

public abstract class CACommandArgument {
	private boolean specificToValue = false;
	protected String descriptionForNonSpecific = "";
	
	public boolean isSpecificToValue() {
		return specificToValue;
	}


	public void setSpecificToValue(boolean specificToValue) {
		this.specificToValue = specificToValue;
	}
	
	public String getDescriptionForNonSpecific(){
		return descriptionForNonSpecific;
	}
	
	public abstract boolean compareToArgument(String arg);
	
	public abstract String helpValue(); 
	
	
	
}
