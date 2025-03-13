package org.community.CrazyLabs.MazeGeneration;

import org.community.CrazyLabs.CrazyLabs;
import org.community.CrazyLabs.Builder.*;


public class GenerateLayer {
	
	CrazyLabs plugin;
	
	Layer layer = null;
	
	String[][] blueprint;
	
	int layerLength = 0;
	int layerWidth = 0;
	
	public GenerateLayer(CrazyLabs plugin, int layerLength, int layerWidth, String[][] blueprint) {
		
		this.plugin = plugin;
		this.layerLength = layerLength;
		this.layerWidth = layerWidth;
		this.blueprint = blueprint;
		
		this.layer = new Layer(this.layerLength, this.layerWidth);
		
		analyzeCells();
		
	}
	
	public Layer getLayer() {
		return this.layer;
	}
	
	private void analyzeCells() {
		
		for(int i = 0; i < this.layerLength; i++) {
			
			for(int j = 0; j < this.layerWidth; j++) {

				Cell tempCell = new Cell();
				
				if(this.blueprint[i][j] != null) {
					
					if(this.blueprint[i][j].equalsIgnoreCase("Module")) {
						
						if((i-1 >= 0) && this.blueprint[i-1][j] != null && this.blueprint[i-1][j].equalsIgnoreCase("Module")) {
							tempCell.setBack(true);
						}
						if((i+1 < this.layerLength) && this.blueprint[i+1][j] != null && this.blueprint[i+1][j].equalsIgnoreCase("Module")) {
							tempCell.setFront(true);
						}
						if((j-1 >= 0) && this.blueprint[i][j-1] != null && this.blueprint[i][j-1].equalsIgnoreCase("Module")) {
							tempCell.setLeft(true);
						}
						if((j+1 < this.layerWidth) && this.blueprint[i][j+1] != null && this.blueprint[i][j+1].equalsIgnoreCase("Module")) {
							tempCell.setRight(true);
						}
						
						this.layer.setCell(i, j, tempCell);
							
					}
					
				}					
				
			}
			
		}
		
	}
	
}
