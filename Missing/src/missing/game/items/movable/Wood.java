/* File: Wood.java
 * 
 * Authors:
 * Edward Kelly	300334192
 * Chris Rabe	30334207
 * 
 * Date			Author			Modification
 * 7 Sep 16		Edward Kelly	created Wood class
 * 7 Sep 16		Chris Rabe		added new constructor and default amount
 */
package missing.game.items.movable;

import java.awt.Point;

/**
 * Represents a Wood item in the game. Wood is a movable resource
 *
 */
@SuppressWarnings("serial")
public class Wood extends Resource {

	public Wood(Point worldLocation, Point tileLocation) {
		this(worldLocation, tileLocation, DEFAULT_AMOUNT);
	}

	public Wood(Point worldLocation, Point tileLocation, int amount) {
		super("Wood", "Planks of Wood.", worldLocation, tileLocation, amount, 1);
	}

	@Override
	public String toString() {
		return getName();
	}
}
