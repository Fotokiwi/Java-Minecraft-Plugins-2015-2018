package org.community.pointsOfInterestCommands;

public class CACommandArgumentString extends CACommandArgument{

	private String sValue;
	
	public CACommandArgumentString(String sValue, boolean specificToValue, String descriptionForNonSpecific){
		this.sValue = sValue;
		super.setSpecificToValue(specificToValue);
		super.descriptionForNonSpecific = descriptionForNonSpecific;
	}
	
	@Override
	public boolean compareToArgument(String arg) {
		if(super.isSpecificToValue())
			return arg.equalsIgnoreCase(sValue);
		else
			return true;
	}

	@Override
	public String helpValue() {
		// TODO Auto-generated method stub
		return sValue;
	}

}
