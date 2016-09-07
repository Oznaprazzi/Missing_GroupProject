/* File: Health.java
 * 
 * Authors:
 * Linus Go		300345571
 * 
 * Date			Author			Modification
 * 7 Sep 16		Linus Go		created Health.java
 */
package missing.game.items.movable;
import java.awt.Point;
/**
 * This class represents Consumable Items that can give health to a player.
 *
 */
public class Health extends Consumable {
	/**
	 * Construct a new Instance of a Health class.
	 * @param name
	 * @param description
	 * @param worldLocation
	 * @param tileLocation
	 * @param amount
	 * @param size
	 */
	public Health(String name, String description, Point worldLocation, Point tileLocation, int amount, int size) {
		super(name, description, worldLocation, tileLocation, amount, size);
	}

}
