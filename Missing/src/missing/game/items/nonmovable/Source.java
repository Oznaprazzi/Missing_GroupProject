/*	File: Source.java
 * 	
 * 	Authors:			ID
 *	Casey Huang			300316284
 * 	Date				Author					Changes
 *	7 Sep 16 			Casey Huang				Created Source class and added javaDoc comments
 */
package missing.game.items.nonmovable;

import java.awt.Point;
/**
 * Represents a Source item that a player can collect
 *
 */
public abstract class Source extends Interactable{
	/**
	 * Creates a new instance of a Source item.
	 * @param name
	 * @param description
	 * @param worldLocation
	 * @param tileLocation
	 */
	public Source(String name, String description, Point worldLocation, Point tileLocation) {
		super(name, description, worldLocation, tileLocation);
	}

	/**
	 * For player to perform an action on this Interactable item.
	 */
	public abstract void performAction();
}
