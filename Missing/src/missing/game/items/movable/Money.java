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
 * Class that represents a money object. It has a Location within the Game World, because
 * it can be dropped, and it can be stored for saving and loading for XML purposes.
 */
@SuppressWarnings("serial")
public class Money extends Movable {
	/**
	 * Construct a new Money object.
	 * @param worldLocation
	 * @param tileLocation
	 * @param amount
	 */
	public Money(Point worldLocation, Point tileLocation, int amount) {
		super("Money", "Used for spending", worldLocation, tileLocation, amount, 1);
	}

}
