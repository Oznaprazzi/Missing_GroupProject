/* File: Consumable.java
 * 
 * Authors:
 * Linus Go		300345571
 * 
 * Date			Author			Modification
 * 7 Sep 16		Linus Go		created Consumable.java
 */
package missing.game.items.movable;

import java.awt.Point;

/**
 * Consumable subclass. subclasses of this class represents items that can be consumed, such as various types of food.
 * 
 */
public abstract class Consumable extends Usable {
	/**
	 * Construct a new Instance of a consumable Item.
	 * @param name
	 * @param description
	 * @param worldLocation
	 * @param tileLocation
	 * @param amount
	 * @param size
	 */
	public Consumable(String name, String description, Point worldLocation, Point tileLocation, int amount, int size) {
		super(name, description, worldLocation, tileLocation, amount, size);
	}

}
