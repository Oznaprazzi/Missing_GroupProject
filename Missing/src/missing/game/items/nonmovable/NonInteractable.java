/*	File: NonInteractable.java
 * 	
 * 	Authors:			ID
 *	Casey Huang			300316284
 * 	Date				Author					Changes
 *	7 Sep 16 			Casey Huang				Created NonInteractable class and added javaDoc comments
 */
package missing.game.items.nonmovable;

import java.awt.Point;

/**
 * Represents a NonInteractable item in the game.
 *
 */
@SuppressWarnings("serial")
public abstract class NonInteractable extends NonMovable {

	/**
	 * Creates a new an instance of a NonInteractable item.
	 * 
	 * @param name
	 * @param description
	 * @param worldLocation
	 * @param tileLocation
	 */
	public NonInteractable(String name, String description, Point worldLocation, Point tileLocation) {
		super(name, description, worldLocation, tileLocation);
	}
}
