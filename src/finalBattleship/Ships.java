package finalBattleship;

import java.util.ArrayList;


public class Ships {

	public String shipName;
	public int quantity;
	private int health;
	public int length;
	public ArrayList<Integer> xLoc = new ArrayList<Integer>(); //stores the x coordinates covered by a ship
	public ArrayList<Integer> yLoc = new ArrayList<Integer>(); //"		"	y	"			"	  "  "  "
	
	public Ships(String shipName, int quantity, int health, int length) {
		super();
		this.shipName = shipName;
		this.quantity = quantity;
		this.health = health;
		this.length = length;
	}
	public Ships(){}
	
	public String getShipName() {
		return shipName;
	}
	public void setShipName(String shipName) {
		this.shipName = shipName;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getHealth() {
		return health;
	}
	public void setHealth(int health) {
		this.health = health;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	
	public int getLocationX(int i){
		
		return xLoc.get(i);
	}
	
	public int getLocationY(int i){
		
		return yLoc.get(i);
	}
	
	public void setLocationY(int y){
			yLoc.add(y);
		
	}
	
	public void setLocationX(int x){
		xLoc.add(x);
		
	}
	
	
	
	
	
}

