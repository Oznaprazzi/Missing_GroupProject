/*	File: NonMovable.java
 * 	
 * 	Authors:			ID
 *	Edward Kelly		300334192
 * 	Date				Author					Changes
	7 Sep 16			Edward Kelly			Created NonMovable class
 */
package missing.game.items.nonmovable;

import java.awt.Point;

import missing.game.items.Item;

/**
 * This class provides skeleton implementation of items which cannot be moved by
 * the player throughout the game world. It represents objects which is mainly
 * used for aesthetic purposes of the game world and may be beneficial for the
 * player.
 */
public abstract class NonMovable extends Item {

	/**
	 * Creates a new instance of a NonMovable item
	 * @param name name of Movable
	 * @param description description of the Movable item
	 * @param worldLocation the section of the world the Movable item is located
	 * @param tileLocation the tile inside a worldLocation the Movable item is located
	 */
	public NonMovable(String name, String description, Point worldLocation, Point tileLocation) {
		super(name, description, worldLocation, tileLocation);
	}

}
