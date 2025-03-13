package org.community.CrazyLabs.MazeSettings;

public class DefaultValues {

	private static final String ALGORITHM = "RecursiveDivision";
	private static final int LENGTH = 10;
	private static final int WIDTH = 10;
	private static final int HEIGTH = 1;
	
	public DefaultValues() {
		
	}

	public static String getDefaultAlgorithm() {
		return ALGORITHM;
	}

	public static int getDefaultLength() {
		return LENGTH;
	}

	public static int getDefaultWidth() {
		return WIDTH;
	}

	public static int getDefaultHeigth() {
		return HEIGTH;
	}
	
}
