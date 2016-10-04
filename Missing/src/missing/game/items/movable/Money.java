/*File: Money.java
 * 
 * Author			ID
 * Jian Wei Chong	300352789
 * 
 * Date			Author			Modification
 * 4 oct 16		Jian Wei		Created the class
 * */
package missing.game.items.movable;

import java.awt.Point;

/*this class simply holds an integer, which represents the money a player has. We needed to put this into
 * a separate class for xml purposes
 * **/
public class Money extends Movable{

	private int money;
	
	public int getMoney(){
		return money;
	}
	
	public void setMoney(int m){
		money = m;
	}
	public Money(String name, String description, Point worldLocation, Point tileLocation, int amount, int size) {
		super(name, description, worldLocation, tileLocation, amount, size);
		// TODO Auto-generated constructor stub
	}

}
