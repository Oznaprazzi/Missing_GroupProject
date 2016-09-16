/* File: Wood.java
 * 
 * Authors:
 * Linus Go		300345571
 * Edward Kelly	300334192
 * 
 * Date			Author			Modification
 * 7 Sep 16		Edward Kelly	created Wood class
 */
package missing.game.items.movable;

import java.awt.Point;

/**
 * Represents a Wood item in the game. Wood is a movable resource
 *
 */
public class Wood extends Resource {
	/**
	 * Construct a new Instance of a Resource.
	 * 
	 * @param name
	 *            name of the item
	 * @param description
	 *            describes what the item is
	 * @param worldLocation
	 *            the section of the world this item is located in
	 * @param tileLocation
	 *            the tile in the worldLocation in which the item is located
	 * @param amount
	 *            amount of Wood items
	 */
	public Wood(Point worldLocation, Point tileLocation, int amount) {
		super("Wood", "Planks of Wood.", worldLocation, tileLocation, amount, 1);
	}

	@Override
	public String toString() {
		return getName();
	}
}
