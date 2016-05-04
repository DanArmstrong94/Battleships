package finalBattleship;

public class Grid {
	
	public String gridType;
	public int gridSize;
	public String[][] coordinates;
	
	public Grid(String gridType, int gridSize){
		this.gridType = gridType;
		this.gridSize = gridSize;
		}
	public Grid(){}
	
	
	public String getGridType() {
		return gridType;
	}

	public int getGridSize() {
		return gridSize;
	}

	public String getCoordinateValue(int x, int y) {
		return coordinates[x][y];
	}

	public void setCoordinateValue(int x, int y, String value) {
		this.coordinates[x][y] = value;
	}
	
	public String[][] getGrid(String[][] values) {			// returns the values of a grid passed to it
		this.coordinates = new String[gridSize][gridSize];
		
		for(int i=0;i<gridSize;i++){
			for(int j=0;j<gridSize;j++){
				setCoordinateValue(j, i, values[j][i]);
			}
		}
		return this.coordinates;
	}
	
	
	public String[][] makeGrid(){							// constructs the original grid at the start of the game
		this.coordinates = new String[gridSize][gridSize];
		
		for(int i=0;i<gridSize;i++){
			for(int j=0;j<gridSize;j++){
				setCoordinateValue(j, i, "0");
			}
		}
		return this.coordinates;
	}
	
}
