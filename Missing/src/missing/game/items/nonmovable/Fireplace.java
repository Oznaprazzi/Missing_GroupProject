/*	File: Fireplace.java
 * 	
 * 	Authors:			ID
 *	Casey Huang			300316284
 * 	Date				Author					Changes
 *	7 Sep 16 			Casey Huang				Created Fireplace class and added javaDoc comments
 */
package missing.game.items.nonmovable;

import java.awt.Point;

import missing.game.characters.Player;

/**
 * Represents a Fireplace item that a player can use to cook food.
 */
public class Fireplace extends Cooking{

	/**
	 * Creates a new instance of a Fireplace item.
	 * @param name
	 * @param description
	 * @param worldLocation
	 * @param tileLocation
	 */
	public Fireplace(String name, String description, Point worldLocation, Point tileLocation) {
		super(name, description, worldLocation, tileLocation);
	}

	@Override
	public void performAction(Player p) {
		// TODO Auto-generated method stub
		
	}

}
