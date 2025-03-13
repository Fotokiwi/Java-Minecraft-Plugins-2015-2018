package org.community.CrazyLabs.Builder;

public class Layer {
	
	private int dimensionx, dimensionz;
	private Cell[][] cells;
	
	public Layer(int dimensionx, int dimensionz)
	{
		this.setDimensionx(dimensionx);
		this.setDimensionz(dimensionz);
		cells = new Cell[dimensionx][dimensionz];
	}
	
	public void setCell(int x, int z, Cell c)
	{
		cells[x][z] = c; 
	}
	
	public Cell getCell(int x, int z)
	{
		return cells[x][z];
	}

	public int getDimensionx() {
		return dimensionx;
	}

	public void setDimensionx(int dimensionx) {
		this.dimensionx = dimensionx;
	}

	public int getDimensionz() {
		return dimensionz;
	}

	public void setDimensionz(int dimensionz) {
		this.dimensionz = dimensionz;
	}
	
	public String toString()
	{
		String s = new String("Dimensionx " + dimensionx + "   |  Dimensionz : " + dimensionz + "\n");
		for(int z = 0; z < dimensionz; z++)
		{
			for(int x = 0; x < dimensionx; x++)
			{
				s += (getCell(x, z) != null? " a " : " n ");
			}
			s += "\n";
		}
		return s;
	}
}
