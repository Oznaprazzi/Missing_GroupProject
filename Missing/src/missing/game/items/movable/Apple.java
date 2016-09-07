/* File: Apple.java
 * 
 * Authors:
 * Linus Go		300345571
 * 
 * Date			Author			Modification
 * 7 Sep 16		Linus Go		created Apple.java
 * 7 Sep 16		Linus Go		updated the constructor for the Apple.
 */
package missing.game.items.movable;

import java.awt.Point;
/**
 * This class represents an Apple object.
 *
 */
public class Apple extends Food {
	/**
	 * The amount of health gained by an Apple object.
	 */
	private static final int REGEN_AMOUNT = 15;
	/**
	 * Construct a new Apple object.
	 * @param name
	 * @param description
	 * @param worldLocation
	 * @param tileLocation
	 * @param amount
	 * @param size
	 */
	public Apple(Point worldLocation, Point tileLocation, int amount, int size) {
		super("Apple", "A tasty piece of fruit.", worldLocation, tileLocation, amount, size, REGEN_AMOUNT);
	}
	
}
