/*	File: Interactable.java
 * 	
 * 	Authors:			ID
 *	Casey Huang			300316284
 * 	Date				Author					Changes
 *	7 Sep 16 			Casey Huang				Created Interactable class and added javaDoc comments
 *	7 Sep 16  			Casey Huang				Created performAction method
 *	7 Sep 16			Casey Huang				Added Player parameter to performAction method.
 */
package missing.game.items.nonmovable;

import java.awt.Point;

import missing.game.characters.Player;
import missing.helper.GameException;
import missing.helper.SignalException;

/**
 * Represents an interactable item in the game.
 *
 */
@SuppressWarnings("serial")
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
	 * @throws GameException 
	 * @throws SignalException 
	 */
	public abstract void performAction(Player p) throws GameException, SignalException;
}
