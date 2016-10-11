/*File: Dirt.java
 * 
 * Authors			ID
 * Jian Wei Chong	300352789
 * 
 * Date 		Author			Modification
 * 21 Sep 16	Jian Wei		Created the class
 * */
package missing.game.items.movable;

import java.awt.Point;
/**
 * Class that represents an instance of a Dirt object. It extends Resource, as it can be
 * used to craft an item.
 *
 */
@SuppressWarnings("serial")
public class Dirt extends Resource{
	/**
	 * Construct a new instance of dirt, without a specified amount.
	 * @param worldLocation
	 * @param tileLocation
	 */
	public Dirt(Point worldLocation, Point tileLocation) {
		this(worldLocation, tileLocation, DEFAULT_AMOUNT);
	}
	/**
	 * Construct a new instance of dirt, with a specified amount.
	 * @param worldLocation
	 * @param tileLocation
	 * @param amount
	 */
	public Dirt(Point worldLocation, Point tileLocation, int amount) {
		super("Dirt", "Get it from soil", worldLocation, tileLocation, amount, 1);
	}
	
	@Override
	public String toString() {
		return getName();
	}
}
