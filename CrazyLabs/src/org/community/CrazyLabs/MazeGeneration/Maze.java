package org.community.CrazyLabs.MazeGeneration;

import org.bukkit.Location;
import org.bukkit.util.Vector;
import org.community.CrazyLabs.CrazyLabs;
import org.community.CrazyLabs.Builder.*;
import org.community.CrazyLabs.MazeSettings.*;

public class Maze {
	
	private CrazyLabs plugin;
	
	private String mazeAlgorithm;
	
	private String moduleTheme;
	private int moduleSize;
	
	private int mazeLength;
	private int mazeWidth;
	@SuppressWarnings("unused")
	private int mazeHeigth;
	
	private Vector entrance;
	private Vector exit; 
	
	private Location mazeLocation;
	
	//private int randomNoWall;
	//private int longerWays;
	
	private Layer layer;
	private String[][] blueprint;
	
	public Maze(CrazyLabs plugin, String theme, int moduleSize) {
		
		this.plugin = plugin;
		
		this.moduleTheme = theme;
		this.moduleSize = moduleSize;
		
		this.mazeAlgorithm = DefaultValues.getDefaultAlgorithm();
		this.mazeLength = DefaultValues.getDefaultLength();
		this.mazeWidth = DefaultValues.getDefaultWidth();
		this.mazeHeigth = DefaultValues.getDefaultHeigth();
		this.setEntrance(new Vector(0,0,0));
		this.setExit(new Vector(0,0,0));
		this.mazeLocation = null;

	}
	
	public String getMazeAlgorithm() {
		return this.mazeAlgorithm;
	}
	
	public void setMazeAlgorithm(String algorithm) {
		this.mazeAlgorithm = algorithm;
	}
	
	public String getModuleTheme() {
		return this.moduleTheme;
	}
	
	public void setModuleTheme(String theme) {
		this.moduleTheme = theme;
	}
	
	public int getModuleSize() {
		return this.moduleSize;
	}
	
	public void setModuleSize(int size) {
		this.moduleSize = size;
	}
	
	public int getMazeLength() {
		return this.mazeLength;
	}
	
	public void setMazeLength(int length) {
		this.mazeLength = length;
	}
	
	public int getMazeWidth() {
		return this.mazeWidth;
	}
	
	public void setMazeWidth(int width) {
		this.mazeWidth = width;
	}
	
	public int getMazeHeigth() {
		return this.mazeLength;
	}
	
	public void setMazeHeigth(int heigth) {
		this.mazeHeigth = heigth;
	}
	
	public Location getMazeLocation() {
		return this.mazeLocation;
	}
	
	public void setMazeLocation(Location location) {
		this.mazeLocation = location;
	}
	
	
	
	public Vector getEntrance() {
		return entrance;
	}

	public void setEntrance(Vector entrance) {
		this.entrance = entrance;
	}

	public Vector getExit() {
		return exit;
	}

	public void setExit(Vector exit) {
		this.exit = exit;
	}

	public void generateBlueprint() {
	
		if(this.mazeAlgorithm.equalsIgnoreCase("Prims")) {
			GenerateSchematicWithPrimAlgorithm primSchematic = new GenerateSchematicWithPrimAlgorithm(plugin, (this.mazeLength*2+1), (this.mazeWidth*2+1), 1, 1, 1);
			this.blueprint = primSchematic.getMaze();
		} else if(this.mazeAlgorithm.equalsIgnoreCase("DepthFirst")) {
			GenerateSchematicWithDepthFirstAlgorithm dfSchematic = new GenerateSchematicWithDepthFirstAlgorithm(this.mazeLength*2+1, this.mazeWidth*2+1);
			this.blueprint = dfSchematic.getMaze();
		} else {
			GenerateSchematicWithRecursiveDivision rdSchematic = new GenerateSchematicWithRecursiveDivision(plugin, this.mazeLength, this.mazeWidth);
			rdSchematic.makeMaze();
			this.blueprint = rdSchematic.getMaze();
		}
		
	}
	
	public void generateIngameMaze() {
		//new GenerateIngameSchematic(this.mazeLocation, this.blueprint, this.mazeLength*2+1, this.mazeWidth*2+1);
		Builder b = new Builder(plugin, this.mazeLocation, moduleSize, moduleTheme);
		generateLayer();
		Layer [] l = {layer};
		b.setLayers(l);
		b.startBuildingMaze();
	}
	
	public void generateLayer() {
		GenerateLayer gLayer = new GenerateLayer(this.plugin, this.mazeLength*2+1, this.mazeWidth*2+1, this.blueprint);
		this.layer = gLayer.getLayer();
	}
	
	public void debugLayer() {
		for(int i = 0; i < this.mazeLength*2+1; i++) {
			for(int j = 0; j < this.mazeWidth*2+1; j++) {
				Cell cell = layer.getCell(i, j);
				if(cell != null)
					plugin.getServer().broadcastMessage(i + "-" + j + " : Left? " + cell.isLeft() + " : Right? " + cell.isRight() + " : Front? " + cell.isFront() + " : Back? " + cell.isBack());
			}
		}
	}
	
}