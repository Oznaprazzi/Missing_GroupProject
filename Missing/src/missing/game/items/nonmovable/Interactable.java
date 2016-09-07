/*	File: NonMovable.java
 * 	
 * 	Authors:			ID
 *	Casey Huang			300316284
 * 	Date				Author					Changes
 *	7 Sep 16 			Casey Huang				Created Interactable class and added javaDoc comments
 *	7 Sep 16  			Casey Huang				Created performAction method
 */
package missing.game.items.nonmovable;

import java.awt.Point;

/**
 * Represents an interactable item in the game.
 * @author Casey Huang
 *
 */
public abstract class Interactable extends NonMovable{

	/**
	 * Creates an instance of an Interactable item.
	 * @param name
	 * @param description
	 * @param worldLocation
	 * @param tileLocation
	 */
	public Interactable(String name, String description, Point worldLocation, Point tileLocation) {
		super(name, description, worldLocation, tileLocation);
	}
	
	/**
	 * For player to perform an action on this Interactable item.
	 */
	public abstract void performAction();
}
