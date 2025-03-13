package org.community.pointsOfInterestCommands;

public class CACommandArgumentInt extends CACommandArgument {
	
	private int iValue;
	
	public CACommandArgumentInt(int value, boolean specificToValue, String descriptionForNonSpecific){
		this.iValue = value;
		super.setSpecificToValue(specificToValue);
		super.descriptionForNonSpecific = descriptionForNonSpecific;
	}

	@Override
	public boolean compareToArgument(String arg) {
		int valueArg;
		try{
			valueArg = Integer.parseInt(arg);
			if(!super.isSpecificToValue())
			return true;
		}
		catch(Exception e){
			return false;
		}
		return valueArg == iValue;		
	}

	@Override
	public String helpValue() {
		// TODO Auto-generated method stub
		return ""+iValue;
	}
	
	
	


}
