/* File: Food.java
 * 
 * Authors:
 * Linus Go		300345571
 * 
 * Date			Author			Modification
 * 7 Sep 16		Linus Go		created Food.java
 */
package missing.game.items.movable;

import java.awt.Point;

/**
 * This class represents Food Items. Classes that extend the Food class are edible items that can be eaten by players to increase their health.
 *
 */
public abstract class Food extends Health {
	
	/**
	 * Create a new instance of a Food object.
	 * @param name
	 * @param description
	 * @param worldLocation
	 * @param tileLocation
	 * @param amount
	 * @param size
	 * @param REGAMOUNT
	 */
	public Food(String name, String description, Point worldLocation, Point tileLocation, int amount, int size, int REGAMOUNT) {
		super(name, description, worldLocation, tileLocation, amount, size, REGAMOUNT);
	}

}
