package org.community.CrazyLabs.MazeSettings;

public class Size {
	
	private static final int MINSIZE = 5;
	private static final int MAXSIZE = 100;
	private static final int MINHEIGTH = 1;
	private static final int MAXHEIGTH = 10;
	
	public Size() {		
		
	}
	
	public static boolean isInsideLengthLimits(int length) {
		if(MINSIZE <= length && length <= MAXSIZE)
			return true;
		
		return false;
	}
	
	public static boolean isInsideWidthLimits(int width) {
		if(MINSIZE <= width && width <= MAXSIZE)
			return true;
		
		return false;
	}
	
	public static boolean isInsideHeigthLimits(int heigth) {
		if(MINHEIGTH <= heigth && heigth >= MAXHEIGTH)
			return true;
		
		return false;
	}
	
	public static int getMinSize() {
		return MINSIZE;
	}
	
	public static int getMaxSize() {
		return MAXSIZE;
	}
	
	public static int getMinHeigth() {
		return MINHEIGTH;
	}
	
	public static int getMaxHeigth() {
		return MAXHEIGTH;
	}

}
