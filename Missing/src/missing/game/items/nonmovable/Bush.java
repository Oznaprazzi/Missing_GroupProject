/*	File: Bush.java
 * 
 * 	Author			ID
 * 	Edward Kelly	300334192
 * 	
 * 	Date			Author				Changes
 * 	7 Sep 16		Edward Kelly		created Bush class
 */
package missing.game.items.nonmovable;

import java.awt.Point;

import missing.game.characters.Player;
import missing.helper.GameException;

/**
 * Represents a Bush. A Bush is only for aesthetics. Subclass of Foliage
 */
public class Bush extends Foliage {

	/**
	 * Creates a new Bush object at the given locations
	 * 
	 * @param worldLocation
	 *            section of world Bush is located
	 * @param tileLocation
	 *            the tile inside the worldLocation the Bush is located
	 */
	public Bush(Point worldLocation, Point tileLocation) {
		super("Bush", "It's a bush", worldLocation, tileLocation);
	}

	@Override
	public void performAction(Player player) throws GameException {
		// TODO Auto-generated method stub

	}

}
