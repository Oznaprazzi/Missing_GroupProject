/*	File: Cooking.java
 * 	
 * 	Authors:			ID
 *	Casey Huang			300316284
 * 	Date				Author					Changes
 *	7 Sep 16 			Casey Huang				Created Cooking class and added javaDoc comments
 *	7 Sep 16  			Casey Huang				Created performAction method
 *	7 Sep 16			Casey Huang				Added Player parameter to performAction method.
 */
package missing.game.items.nonmovable;

import java.awt.Point;

import missing.game.characters.Player;
import missing.helper.GameException;

/**
 * Represents a Cooking item that a player can interact with in order to produce food.
 */
public abstract class Cooking extends Furniture{
	/**
	 * Creates an instance of a Cooking item.
	 * @param name
	 * @param description
	 * @param worldLocation
	 * @param tileLocation
	 */
	public Cooking(String name, String description, Point worldLocation, Point tileLocation) {
		super(name, description, worldLocation, tileLocation);
	}
	
	/**
	 * For player to perform an action on this Interactable item.
	 * @throws GameException 
	 */
	public abstract void performAction(Player p) throws GameException;
}
