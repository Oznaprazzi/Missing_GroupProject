/* File: Resource.java
 * 
 * Authors:
 * Linus Go		300345571
 * Edward Kelly	300334192
 * 
 * Date			Author			Modification
 * 7 Sep 16		Linus Go		created resource class.
 * 7 Sep 16		Edward Kelly	added javadoc, removed weight, added amount
 */
package missing.game.items.movable;

import java.awt.Point;

/**
 * This class represents a Movable item that can be used to create Craftable
 * items
 *
 */
public abstract class Resource extends Movable {
	protected static final int DEFAULT_AMOUNT = 5;

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
	 * @param size
	 *            represents size of item
	 * @param amount
	 *            amount of Resource items
	 */
	public Resource(String name, String description, Point worldLocation, Point tileLocation, int amount, int size) {
		super(name, description, worldLocation, tileLocation, amount, size);
	}

}
