/* File: Usable.java
 * 
 * Authors:
 * Linus Go		300345571
 * Chris Rabe	300334207
 * 
 * Date			Author			Modification
 * 7 Sep 16		Linus Go		created Usable class
 * 7 Sep 16		Linus Go		Added amount field.		
 * 21 Sep 16	Chris Rabe		created use method
 */
package missing.game.items.movable;

import java.awt.Point;

import missing.game.characters.Player;
import missing.helper.GameException;

/**
 * This class represents a Usable item in the Game. It extends the Movable
 * subclass and the Item subclass.
 */
public abstract class Usable extends Movable {

	/**
	 * Creates instance of Usable class.
	 * 
	 * @param name
	 * @param description
	 * @param worldLocation
	 * @param tileLocation
	 * @param amount
	 * @param size
	 */
	public Usable(String name, String description, Point worldLocation, Point tileLocation, int amount, int size) {
		super(name, description, worldLocation, tileLocation, amount, size);
	}

	/**
	 * This allows the player to use the usable item.
	 * 
	 * @param player
	 * @throws GameException
	 */
	public abstract void use(Player player) throws GameException;
}
