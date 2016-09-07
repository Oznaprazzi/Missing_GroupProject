/*	File: Fireplace.java
 * 	
 * 	Authors:			ID
 *	Casey Huang			300316284
 * 	Date				Author					Changes
 *	7 Sep 16 			Casey Huang				Created Fireplace class and added javaDoc comments
 *	7 Sep 16			Casey Huang				Updated the performAction method
 */
package missing.game.items.nonmovable;

import java.awt.Point;

import missing.game.characters.Player;
import missing.game.items.movable.Apple;
import missing.helper.GameException;

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
		try {
			p.addToPocket(new Apple(worldLocation, tileLocation, 5, 1));
		} catch (GameException e) {
			e.printStackTrace();
		}
	}

}
