package org.community.CrazyLabs.Builder;

public class Cell {
	
	/*
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	
	private boolean up, down, left, right, front, back; 
	public Cell()
	{
		setUp(false);
		setDown(false);
		setLeft(false); 
		setRight(false);
		setFront(false);
		setBack(false);
	}
	public boolean isUp() {
		return up;
	}
	public void setUp(boolean up) {
		this.up = up;
	}
	public boolean isDown() {
		return down;
	}
	public void setDown(boolean down) {
		this.down = down;
	}
	public boolean isLeft() {
		return left;
	}
	public void setLeft(boolean left) {
		this.left = left;
	}
	public boolean isRight() {
		return right;
	}
	public void setRight(boolean right) {
		this.right = right;
	}
	public boolean isFront() {
		return front;
	}
	public void setFront(boolean front) {
		this.front = front;
	}
	public boolean isBack() {
		return back;
	}
	public void setBack(boolean back) {
		this.back = back;
	}

	
}
