/*File : Unique.java
 * 
 * Authors    		ID
 * Jian Wei Chong	300352789
 * 
 * Date 		Author		Modification
 * 18/9/16		Jian Wei	created the class
 * 
 */
package missing.game.items.movable;

import java.awt.Point;

/**
 * This class represents a Unique item in the Game. It extends the Usable
 * subclass, Movable subclass and the Item subclass.
 */
public class Unique extends Usable {
	/**
	 * Creates instance of Unique class.
	 * 
	 * @param name
	 * @param description
	 * @param worldLocation
	 * @param tileLocation
	 * @param amount
	 * @param size
	 */

	public Unique(String name, String description, Point worldLocation, Point tileLocation, int amount, int size) {
		super(name, description, worldLocation, tileLocation, amount, size);
	}

}
