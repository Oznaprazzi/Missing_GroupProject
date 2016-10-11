/*	File: Stone.java
 * 	Author
 * 	Chris Rabe			300334207
 * 	
 * 	Date				Author				Changes
 * 	17 Sep 16			Chris Rabe			created stone.java
 *  19 Sep 16			Jian Wei			Set size parameter to 1 in super constructor
 */

package missing.game.items.movable;

import java.awt.Point;

/**
 * Represents a Stone item in the game. Stone is a movable resource.
 */
@SuppressWarnings("serial")
public class Stone extends Resource {
	/**
	 * Construct a new Stone object.
	 * @param worldLocation
	 * @param tileLocation
	 */
	public Stone(Point worldLocation, Point tileLocation) {
		this(worldLocation, tileLocation, DEFAULT_AMOUNT);
	}
	/**
	 * Construct a new Stone object with a specified preset amount.
	 * @param worldLocation
	 * @param tileLocation
	 * @param amount
	 */
	public Stone(Point worldLocation, Point tileLocation, int amount) {
		super("Stone", "Extremely heavy", worldLocation, tileLocation, amount, 1);
	}

	@Override
	public String toString() {
		return getName();
	}
}
