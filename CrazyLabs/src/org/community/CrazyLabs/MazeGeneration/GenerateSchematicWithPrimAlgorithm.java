package org.community.CrazyLabs.MazeGeneration;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.community.CrazyLabs.CrazyLabs;

public class GenerateSchematicWithPrimAlgorithm{
	
	@SuppressWarnings("unused")
	private CrazyLabs plugin = null;
	
	private List<String> mazeCorridor = new ArrayList<String>();
	private List<String> mazeWallA = new ArrayList<String>();
	private List<String> mazeWallB = new ArrayList<String>();
	private int mazeLength = 0;
	private int mazeWidth = 0;
	private int random;
	private int longerWays;
	
	public GenerateSchematicWithPrimAlgorithm(CrazyLabs plugin, int mazeLength, int mazeWidth, int mazeHeigth, int random, int longerWays) {
		
		this.plugin = plugin;
		
		int startLength = 0;
		int startWidth = 0;
		int startHeigth = 0;
		
		this.mazeLength = mazeLength;
		this.mazeWidth = mazeWidth;		
		
		this.random = random;
		this.longerWays = longerWays;
		
		this.mazeCorridor.add(startLength + "~" + startWidth + "~" + startHeigth);
		this.mazeWallA.add(startLength + "~" + startWidth + "~" + startHeigth + ":" + (startLength+1) + "~" + startWidth + "~" + startHeigth);
		this.mazeWallA.add(startLength + "~" + startWidth + "~" + startHeigth + ":" + startLength + "~" + (startWidth+1) + "~" + startHeigth);
		
		//iterateListA();
		iterateWallList();
	}
	
	public List<String> getCorridor() {
		return this.mazeCorridor;
	}
	
	private void iterateWallList() {
		
		if(this.mazeWallA.isEmpty())
			return;
		
		shuffleList(this.mazeWallA);
		if(checkWall(this.mazeWallA.get(0))) {
			String[] tempLoc = this.mazeWallA.remove(0).split(":");
			this.mazeCorridor.add(tempLoc[1]);
			addWalls(tempLoc[0], tempLoc[1], "A");
		} else {
			this.mazeWallA.remove(0);
		}
		
		iterateWallList();
		
	}
	
	private void iterateListA() {
		shuffleList(this.mazeWallA);
		for(int i = 0; i < this.mazeWallA.size(); i++) {
			if(checkWall(this.mazeWallA.get(i))) {
				String[] tempLoc = this.mazeWallA.get(i).split(":");
				this.mazeCorridor.add(tempLoc[1]);
				addWalls(tempLoc[0], tempLoc[1], "B");
			}
		}
		this.mazeWallA.clear();
		if(this.mazeWallB.size() >= 1)
			iterateListB();
	}

	private void iterateListB() {
		shuffleList(this.mazeWallB);
		for(int i = 0; i < this.mazeWallB.size(); i++) {
			if(checkWall(this.mazeWallB.get(i))) {
				String[] tempLoc = this.mazeWallB.get(i).split(":");
				this.mazeCorridor.add(tempLoc[1]);	
				addWalls(tempLoc[0], tempLoc[1], "A");			
			}
		}	
		this.mazeWallB.clear();
		if(this.mazeWallA.size() >= 1)
			iterateListA();	
	}
	
	private boolean checkWall(String mazeSection) {
		
		//plugin.getServer().broadcastMessage(mazeSection);
		
		String[] tempArray = mazeSection.split(":");
		String parentSegment = tempArray[0];
		String location = tempArray[1];
		String[] tempLoc = location.split("~");
		
		int error = 0;
		
		int wallX = Integer.parseInt(tempLoc[0]);
		int wallY = Integer.parseInt(tempLoc[1]);
		int wallZ = Integer.parseInt(tempLoc[2]);
		
		if(!(parentSegment.equalsIgnoreCase((wallX - 1) + "~" + wallY + "~" + wallZ))) {
			if(this.mazeCorridor.contains((wallX - 1) + "~" + wallY + "~" + wallZ)) {
				if(randomizer(100) <= this.random) {
					
				} else {
					//plugin.getServer().broadcastMessage("Errors-Position 1");
					error++;
				}				
			}
		}
		if(!(parentSegment.equalsIgnoreCase((wallX + 1) + "~" + wallY + "~" + wallZ))) {
			if(this.mazeCorridor.contains((wallX + 1) + "~" + wallY + "~" + wallZ)) {
				if(randomizer(100) <= this.random) {
					
				} else {
					//plugin.getServer().broadcastMessage("Errors-Position 1");
					error++;
				}	
			}
		}
		if(!(parentSegment.equalsIgnoreCase(wallX + "~" + (wallY - 1) + "~" + wallZ))) {
			if(this.mazeCorridor.contains(wallX + "~" + (wallY - 1) + "~" + wallZ)) {
				if(randomizer(100) <= this.random) {
					
				} else {
					//plugin.getServer().broadcastMessage("Errors-Position 1");
					error++;
				}	
			}
		}
		if(!(parentSegment.equalsIgnoreCase(wallX + "~" + (wallY + 1) + "~" + wallZ))) {
			if(this.mazeCorridor.contains(wallX + "~" + (wallY + 1) + "~" + wallZ)) {
				if(randomizer(100) <= this.random) {
					
				} else {
					//plugin.getServer().broadcastMessage("Errors-Position 1");
					error++;
				}	
			}
		}
		if(error == 0) {
			return true;
		}
		
		//plugin.getServer().broadcastMessage("Errors: " + error);
		return false;
	}

	private void addWalls(String parentMaze, String mazeSection, String list) {
		
		String[] tempLoc = mazeSection.split("~");
		
		int wallX = Integer.parseInt(tempLoc[0]);
		int wallY = Integer.parseInt(tempLoc[1]);
		int wallZ = Integer.parseInt(tempLoc[2]);
		
		
		if(!(mazeSection.equalsIgnoreCase((wallX - 1) + "~" + wallY + "~" + wallZ))) {
			if((wallX - 1) < 0) {

			} else if(this.mazeCorridor.contains((wallX - this.longerWays) + "~" + wallY + "~" + wallZ)) { 
				
			} else {
				if(list.equalsIgnoreCase("A")) {
					this.mazeWallA.add(mazeSection + ":" + (wallX - 1) + "~" + wallY + "~" + wallZ);
				} else {
					this.mazeWallB.add(mazeSection + ":" + (wallX - 1) + "~" + wallY + "~" + wallZ);
				}
			}
		}
		if(!(mazeSection.equalsIgnoreCase((wallX + 1) + "~" + wallY + "~" + wallZ))) {
			if((wallX + 1) > this.mazeLength) {

			} else if(this.mazeCorridor.contains((wallX + this.longerWays) + "~" + wallY + "~" + wallZ)) { 
				
			} else {
				if(list.equalsIgnoreCase("A")) {
					this.mazeWallA.add(mazeSection + ":" + (wallX + 1) + "~" + wallY + "~" + wallZ);
				} else {
					this.mazeWallB.add(mazeSection + ":" + (wallX + 1) + "~" + wallY + "~" + wallZ);
				}
			}
		}
		if(!(mazeSection.equalsIgnoreCase(wallX + "~" + (wallY - 1) + "~" + wallZ))) {
			if((wallY - 1) < 0) {

			} else if(this.mazeCorridor.contains(wallX + "~" + (wallY - this.longerWays) + "~" + wallZ)) { 
				
			} else {
				if(list.equalsIgnoreCase("A")) {
					this.mazeWallA.add(mazeSection + ":" + wallX + "~" + (wallY - 1) + "~" + wallZ);
				} else {
					this.mazeWallB.add(mazeSection + ":" + wallX + "~" + (wallY - 1) + "~" + wallZ);
				}
			}
		}
		if(!(mazeSection.equalsIgnoreCase(wallX + "~" + (wallY + 1) + "~" + wallZ))) {
			if((wallY + 1) > this.mazeWidth) {

			} else if(this.mazeCorridor.contains(wallX + "~" + (wallY + this.longerWays) + "~" + wallZ)) { 
				
			} else {
				if(list.equalsIgnoreCase("A")) {
					this.mazeWallA.add(mazeSection + ":" + wallX + "~" + (wallY + 1) + "~" + wallZ);
				} else {
					this.mazeWallB.add(mazeSection + ":" + wallX + "~" + (wallY + 1) + "~" + wallZ);
				}
			}
		}
	}
	
	private int randomizer(int number) {
		long seed = System.currentTimeMillis();
		Random dice = new Random(seed);
		
		int rnd = dice.nextInt(number);
		
		return rnd;
	}

	private void shuffleList(List<String> a) {
		int n = a.size();
		Random random = new Random();
		random.nextInt();
		for (int i = 0; i < n; i++) {
			int change = i + random.nextInt(n - i);
			swap(a, i, change);
		}
	}

	private void swap(List<String> a, int i, int change) {
		String helper = a.get(i);
		a.set(i, a.get(change));
		a.set(change, helper);
	}
	
	public String[][] getMaze() {
		String[][] structure = new String[this.mazeLength+1][this.mazeWidth+1];
		for(int i = 0; i < this.mazeCorridor.size(); i++) {
			String[] temp = this.mazeCorridor.get(i).split("~");
			//plugin.getServer().broadcastMessage(temp[0] + "+" + temp[1]);
			structure[Integer.parseInt(temp[0])][Integer.parseInt(temp[1])] = "Module";
		}
		return structure;
	}
	
}