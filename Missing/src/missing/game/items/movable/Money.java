/*File: Money.java
 * 
 * Author			ID
 * Jian Wei Chong	300352789
 * Chris Rabe		300334207
 * 
 * Date			Author			Modification
 * 4 oct 16		Jian Wei		Created the class
 * 5 Oct 16		Chris Rabe		updated Money constructor
 * */
package missing.game.items.movable;

import java.awt.Point;

/**
 * this class simply holds an integer, which represents the money a player has.
 * We needed to put this into a separate class for xml purposes
 **/
@SuppressWarnings("serial")
public class Money extends Movable {

	private int money;

	public int getMoney() {
		return money;
	}

	public void setMoney(int m) {
		money = m;
	}

	public Money(Point worldLocation, Point tileLocation, int amount) {
		super("Money", "Used for spending", worldLocation, tileLocation, amount, 1);
	}

}
