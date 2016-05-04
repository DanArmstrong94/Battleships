package finalBattleship;

import java.util.ArrayList;
import java.util.Scanner;

import finalBattleship.Player;
import finalBattleship.Ships;

public class GameController {
	
	static void playGame(){ //runs the full game
		
		Scanner user_input = new Scanner(System.in);
		boolean playAgain;
		
		do{
			Player player1 = new Player("Player 1", 7);
			Player player2 = new Player("Player 2", 7);
			System.out.println("Welcome to Battleships!");
			beginGame(player1);
			beginGame(player2);
		
			firingPart(player1, player2, user_input);
			playAgain = playAgainResponse(user_input);
		}while(playAgain);
		user_input.close();
	}
	
	private static void beginGame(Player player) {			//initialises the game and sets up the grids of each player
		System.out.println(player.player);
		printGrid(10, player.playerGrid);
		System.out.println(player.player + " Turn Begin!");
		System.out.println("Place your ships!");
		printGrid(10, player.placeShips());
		pressAnyKeyToContinue();
	}

	private static void firingPart(Player player1, Player player2, Scanner user_input) { //starts the turn based combat
		do{
				fireGame(player1, player2, user_input);
				pressAnyKeyToContinue();
				if (player2.getShipsAlive() != 0) 
				{
						fireGame(player2, player1, user_input);
						pressAnyKeyToContinue();
				}
			
		}while((player1.getShipsAlive() > 0) && (player2.getShipsAlive() > 0)); // defines the end of the game as when one player runs out of ships
		
		if (player1.getShipsAlive() == 0){    // win statements
				System.out.println(player2.player + " wins!");
		}
		else{
				System.out.println(player1.player + " wins!");
		}
	}


	private static void fireGame(Player player, Player playerTwo, Scanner user_input) // The Method which describes how the second part of the game is played
	{

			fireCoords(player, user_input);						// requests the target coordinates
			fire(player, playerTwo);				// fires the shot
			pressAnyKeyToContinue();
		
	}


	public static void fire(Player turnPlayer, Player enemyPlayer) // works out if a fired shot has hit
	{
		
		
		if (enemyPlayer.playerGrid[xFire][yFire] == "1"){
			System.out.println("CRITICAL HIT!!!!");					// hit
			enemyPlayer.playerGrid[xFire][yFire] = "x";
			turnPlayer.playerFireGrid[xFire][yFire] = "x";
			shipHPReduction(enemyPlayer, xFire, yFire); // uses the enemies grid without showing it to reduce the HP of their ship that was hit
			
		}
		else
		{	
			System.out.println("MISSED!!!!");						// miss
			turnPlayer.playerFireGrid[xFire][yFire] = "m";
			enemyPlayer.playerGrid[xFire][yFire] = "m";
		}	
	}
	
	
	public static void shipHPReduction(Player player, int xFire, int yFire) // reduces the health points of the enemy players ships if they are hit, also informs both players if a ship is sunk
	{
		for (int i=0; i<5; i++)
		{
				for(int j=0; j<((Ships) player.fleet.get(i)).getLength(); j++){
					if (xFire == ((Ships) player.fleet.get(i)).getLocationX(j) && yFire == ((Ships) player.fleet.get(i)).getLocationY(j)){
						
							(player.fleet.get(i)).setHealth((player.fleet.get(i)).getHealth() - 1);
								
								if((player.fleet.get(i)).getHealth() == 0){
										System.out.println(player.player + "'s " + (player.fleet.get(i)).getShipName() + " has been sunk!");
										player.setShipsAlive(player.getShipsAlive()- 1); //reduces the number of ships a player has alive when a ship of theirs is sunk
								}
					}
				}		
		}
	}
	
	public static void fireCoords(Player player, Scanner user_input) // gets the coordinates of the desired shot location, showing both a players ships and their "fire grid" 
	{


		
		System.out.println(player.player);
		printGrid(10, player.playerGrid);
		System.out.println("Fire Grid");
		printGrid(10, player.playerFireGrid);
		System.out.println(player.player + " Turn Begin!");
		System.out.println("Choose your target x coordinate : ");
		try{
		xFire = user_input.nextInt();
		} catch (ArrayIndexOutOfBoundsException arrExc){										//checks for array out of bounds exception and recurs the function should one be thrown
			System.out.println("These coordinates are not on the grid, please try again.");
			fireCoords(player, user_input);
		}
		System.out.println("Choose your target y coordinate : ");
		try{
		yFire = user_input.nextInt();
		} catch(ArrayIndexOutOfBoundsException arrExc){
			System.out.println("These coordinates are not on the grid, please try again.");
			fireCoords(player, user_input);
		}
		if (player.playerFireGrid[xFire][yFire] == "m" || player.playerFireGrid[xFire][yFire] == "x"){
			System.out.println("You have already fired here, please select a new firing position.");
			fireCoords(player, user_input);
		}
		

	}
	
	
	private static void printGrid(int arraySize, String[][] grid) { //sets up the grid in a readable format (y axis is inverted)
		System.out.println("    0 1 2 3 4 5 6 7 8 9");
		System.out.println("    ___________________");
		for(int i=0;i<arraySize;i++){
			System.out.print(i + "  |");
			for(int j=0;j<arraySize;j++){
				System.out.print(grid[j][i] + " ");
			}
			System.out.println("");
		}
		System.out.println("");
	}	
	


	private static void pressAnyKeyToContinue() // allows for a player to view their grid until the press a key to start the next players turn
	{ 
		System.out.println("Press any key end turn.");
		try
		{
			System.in.read();
     	 }  
		catch(Exception e)
     	 {}  
		for (int i=0;i<20;i++){
			System.out.println();
		}
	}
	
	private static boolean playAgainResponse(Scanner user_input){
		
				System.out.println("Play Again? (y/n)");
				String answer = user_input.next();
				if (answer == "y"){
					return true;
				}
				else{
					return false;
				}
		
	}

}
