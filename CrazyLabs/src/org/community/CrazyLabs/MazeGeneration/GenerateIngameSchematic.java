package org.community.CrazyLabs.MazeGeneration;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;

public class GenerateIngameSchematic {
	
	private List<String> mazeList;
	private Location mazeLocation;

	public GenerateIngameSchematic(Location mazeLocation, GenerateSchematicWithPrimAlgorithm mazeSchematic) {
		
		this.mazeLocation = mazeLocation;
		this.mazeList = mazeSchematic.getCorridor();
		
		generateBlockStructure();
		
	}

	public GenerateIngameSchematic(Location mazeLocation, String[][] blueprint, int length, int width) {
		
		this.mazeLocation = mazeLocation;
		
		generateBlockStructure(blueprint, length, width);
		
	}
	
	private void generateBlockStructure() {
		
		Location tempLocation;
		String[] tempCoords;
		
		for(int i = 0; i < this.mazeList.size(); i++) {
			tempCoords = this.mazeList.get(i).split("~");
			tempLocation = this.mazeLocation.clone();
			tempLocation.add(Integer.parseInt(tempCoords[0]), Integer.parseInt(tempCoords[2]), Integer.parseInt(tempCoords[1]));
			tempLocation.getBlock().setType(Material.STONE);
			tempLocation = this.mazeLocation.clone();
			tempLocation.add(Integer.parseInt(tempCoords[0]), Integer.parseInt(tempCoords[2])+1, Integer.parseInt(tempCoords[1]));
			tempLocation.getBlock().setType(Material.STONE);
			//tempLocation.add(-Integer.parseInt(tempCoords[0]), -Integer.parseInt(tempCoords[2]), -Integer.parseInt(tempCoords[1]));
		}
		
	}
	
	private void generateBlockStructure(String[][] blueprint, int length, int width) {
		
		Location tempLocation;
		
		for(int i = 0; i < length; i++) {
			for(int j = 0; j < width; j++) {
				tempLocation = this.mazeLocation.clone();
				tempLocation.add(i, 0, j);
				tempLocation.getBlock().setType(Material.WOOD);
				tempLocation = this.mazeLocation.clone();
				tempLocation.add(i, +1, j);
				tempLocation.getBlock().setType(Material.WOOD);
				if(blueprint[i][j] != null && blueprint[i][j].equalsIgnoreCase("Module")) {
					tempLocation = this.mazeLocation.clone();
					tempLocation.add(i, 0, j);
					tempLocation.getBlock().setType(Material.AIR);
					tempLocation = this.mazeLocation.clone();
					tempLocation.add(i, +1, j);
					tempLocation.getBlock().setType(Material.AIR);
					//tempLocation.add(-Integer.parseInt(tempCoords[0]), -Integer.parseInt(tempCoords[2]), -Integer.parseInt(tempCoords[1]));
				}					
			}			
		}
		
	}
	
}