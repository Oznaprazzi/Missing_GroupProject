/*	File: Stone.java
 * 	Author
 * 	Chris Rabe			300334207
 * 	
 * 	Date				Author				Changes
 * 	17 Sep 16			Chris Rabe			created stone.java
 */

package missing.game.items.movable;

import java.awt.Point;

/**
 * Represents a Stone item in the game. Stone is a movable resource.
 */
public class Stone extends Resource {

	public Stone(Point worldLocation, Point tileLocation) {
		this(worldLocation, tileLocation, DEFAULT_AMOUNT);
	}

	public Stone(Point worldLocation, Point tileLocation, int amount) {
		super("Stone", "Extremely heavy", worldLocation, tileLocation, amount, 2);
	}

	@Override
	public String toString() {
		return getName();
	}
}
