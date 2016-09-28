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

@SuppressWarnings("serial")
public class Dirt extends Resource{

	public Dirt(Point worldLocation, Point tileLocation) {
		this(worldLocation, tileLocation, DEFAULT_AMOUNT);
	}

	public Dirt(Point worldLocation, Point tileLocation, int amount) {
		super("Dirt", "Get it from soil", worldLocation, tileLocation, amount, 1);
	}
	
	@Override
	public String toString() {
		return getName();
	}
}
