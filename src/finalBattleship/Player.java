package finalBattleship;
import java.util.Objects;
import java.util.Scanner;
import java.util.ArrayList;

public class Player{

	public String player;
	public int shipsRemaining;
	public int shipsAlive;
	public String[][] playerGrid;
	public String[][] playerFireGrid;
	public ArrayList<Ships> fleet = new ArrayList<Ships>();
	private int xStartCoordinate;
	private int yStartCoordinate;
	private int xEndCoordinate;
	private int yEndCoordinate;
	public Scanner user_input = new Scanner(System.in);
	
	
	public Player(String player,int shipsRemaining) {
		this.player = player;
		this.shipsRemaining = shipsRemaining;
		Grid pGrid = new Grid(player + " Ship Grid", 10);
		Grid pFireGrid = new Grid(player + " Fire Grid", 10);
		this.playerGrid = pGrid.makeGrid();
		this.playerFireGrid = pFireGrid.makeGrid();
		this.shipsAlive = shipsRemaining;
	}

	
	public void setShipsAlive(int i){
		this.shipsAlive = i;
	}
	
	public int getShipsAlive(){
		return shipsAlive;
	}

	public int getShipsRemaining() {
		return shipsRemaining;
	}

	public void setShipsRemaining(int shipsRemaining) {
		this.shipsRemaining = shipsRemaining;
	}

	public String getPlayer() {
		return player;
	}

	public String[][] getPlayerGrid() {
		return playerGrid;
	}

	public String[][] getPlayerFireGrid() {
		return playerFireGrid;
	}
	
	

	public String[][] placeShips() 
	{
		
			boolean validResult = true;
			boolean shipSelected = true;
			String shipChoice;
			createShips();
			
	do{		
				do
				{		
					

						shipChoice = initiateShips();
					
					
						shipSelected = checkInputShip(shipChoice);
					
						if (shipSelected == true) {shipsRemaining --;}
				}while(!shipSelected);
				do
				{
					do
					{
							getCoordinates();
							validResult = checkCoords(fleet.get(5), xStartCoordinate, yStartCoordinate, xEndCoordinate, yEndCoordinate);
					}while(!validResult);
					
								validResult = editGrid(xStartCoordinate, yStartCoordinate, xEndCoordinate, yEndCoordinate) ;
						
				}while(!validResult);
	}while(shipsRemaining>0);
			return playerGrid;
		
		
		
		
		
	}
	
	private void setLocationArray(int x, int y) {
		
		for(int i=0;i<5;i++){
			if(Objects.equals((fleet.get(5)).getShipName(), (fleet.get(i)).getShipName())) 
			{
				(fleet.get(i)).setLocationX(x); 
				(fleet.get(i)).setLocationY(y);
				
			}
		}
	}
	
	
	
	
	


	public boolean editGrid(int startPointX, int startPointY, int endPointX, int endPointY)
	{
		if(startPointX != endPointX)
		{
			if (startPointX < endPointX)
			{
				for (int i = startPointX; i <= endPointX; i++) 
				{
					if (playerGrid[i][startPointY] == "1")
					{
						System.out.println("This ship placement coincides with another. Please select a new location.");
						return false;
					}
						setLocationArray(i, startPointY);
						playerGrid[i][startPointY] = "1";
				}
			}
			else
			{
				for (int i = startPointX; i >= endPointX; i--) 
				{
					if (playerGrid[i][startPointY] == "1") 
					{
						System.out.println("This ship placement coincides with another. Please select a new location.");
						return false;
					}
						setLocationArray(i, startPointY);
						playerGrid[i][startPointY] = "1";
				}
			}	
		}
		else
		{
			if (startPointY < endPointY)
			{
					for (int i = startPointY; i <= endPointY; i++) 
					{
						if (playerGrid[startPointX][i] == "1") 
						{
							System.out.println("This ship placement coincides with another. Please select a new location.");
							return false;
						}
							setLocationArray(startPointX, i);
							playerGrid[startPointX][i] = "1";
					}
			}
			else
			{
				for (int i = startPointY; i >= endPointY; i--) 
				{
					if (playerGrid[startPointX][i] == "1") 
					{
						System.out.println("This ship placement coincides with another. Please select a new location.");
						return false;
					}
						setLocationArray(startPointX, i);
						playerGrid[startPointX][i] = "1";
				}

			}
		}
	
		return true;
		
		
	}
	
	
	
	
	
	
	
	
	private void getCoordinates() {
		xStartCoordinate = getCoordinate("x", "start");
		yStartCoordinate = getCoordinate("y", "start");
		xEndCoordinate = getCoordinate("x", "end");
		yEndCoordinate = getCoordinate("y", "end");
	}
	
	
	
	
	public int getCoordinate(String a, String b) 
	{
		System.out.println("Enter the " + a + " coord of the " + b + " point for your vessel : ");

		int coordinate = user_input.nextInt();
		
		if (coordinate >9 || coordinate <0){
			System.out.println("This coordinate is out of bounds, please try again.");
			return getCoordinate(a, b);
		}
		else {return coordinate;}

	}
	

	
	private static boolean checkCoords(Ships vesselChoice,
			int startPointX, int startPointY, int endPointX, int endPointY) {
		if ((Math.abs(startPointX - endPointX)+1) > vesselChoice.getLength() || ((Math.abs(startPointY - endPointY)+1) > vesselChoice.getLength())) {
			System.out.println("These points are incompatible with the ship chosen, please select new points.");
			return false;
		} else if (startPointX != endPointX && startPointY != endPointY) {
			System.out.println("These points are incompatible with the ship chosen, please select new points.");
			return false;
		}
		return true;
	}
	
	
	
	public void createShips()
	{
		Ships patrolBoat = new Ships("PatrolBoat", 2, 2, 2);
		fleet.add(patrolBoat);
		Ships battleship = new Ships("Battleship", 2, 2, 2);
		fleet.add(battleship);
		Ships submarine = new Ships("Submarine", 1, 3, 3);
		fleet.add(submarine);
		Ships destroyer = new Ships("Destroyer", 1, 4, 4);
		fleet.add(destroyer);
		Ships carrier = new Ships("Carrier", 1, 5, 5);
		fleet.add(carrier);
		Ships vesselChoice = new Ships();
		fleet.add(vesselChoice);
	}
	
	
	
	public String initiateShips()
	{	
		 
		
		System.out.print("Please select which ship you would like to place (Name, Quantity Remaining) : ");
		System.out.print(((Ships) fleet.get(0)).getShipName() + ", " + ((Ships) fleet.get(0)).getQuantity() + "| ");
		System.out.print(((Ships) fleet.get(1)).getShipName() + ", " + ((Ships) fleet.get(1)).getQuantity() + "| ");
		System.out.print(((Ships) fleet.get(2)).getShipName() + ", " + ((Ships) fleet.get(2)).getQuantity() + "| ");
		System.out.print(((Ships) fleet.get(3)).getShipName() + ", " + ((Ships) fleet.get(3)).getQuantity() + "| ");
		System.out.println(((Ships) fleet.get(4)).getShipName() + ", " + ((Ships) fleet.get(4)).getQuantity() + "| ");
		
		String shipChoice = user_input.next();

		
		return shipChoice;
			
	}
	
	private boolean checkInputShip(String shipChoice) 
	{
		
		
		if(Objects.equals(shipChoice, ((Ships) fleet.get(0)).getShipName())) 
		{
			setVessel(0);
			return true;
		}
		else if(Objects.equals(shipChoice, ((Ships) fleet.get(1)).getShipName()))
		{
			setVessel(1);
			return true;
		}
		else if(Objects.equals(shipChoice, ((Ships) fleet.get(2)).getShipName()))
		{
			setVessel(2);
			return true;
		}
		else if(Objects.equals(shipChoice, ((Ships) fleet.get(3)).getShipName()))
		{
			setVessel(3);
			return true;
		}
		else if(Objects.equals(shipChoice, ((Ships) fleet.get(4)).getShipName()))
		{
			setVessel(4);
			return true;
		}
		else{
				System.out.println("There is no ship with this name, please try again.");
				return false;
			}
	}

	private void setVessel(int a) {
		(fleet.get(a)).setQuantity((fleet.get(a)).getQuantity() - 1);
		(fleet.get(5)).setShipName((fleet.get(a)).getShipName());
		(fleet.get(5)).setQuantity((fleet.get(a)).getQuantity());
		(fleet.get(5)).setHealth((fleet.get(a)).getHealth());
		(fleet.get(5)).setLength((fleet.get(a)).getLength());
	}
	
}
