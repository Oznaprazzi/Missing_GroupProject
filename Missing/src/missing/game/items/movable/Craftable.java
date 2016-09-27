/*File : Craftable.java
 * 
 * Authors    		ID
 * Jian Wei Chong	300352789
 * 
 * Date 		Author		Modification
 * 18/9/16		Jian Wei	created the class
 * 
 * */

package missing.game.items.movable;

import java.awt.Point;
import java.util.List;

/**
 * This class represents a Craftable item in the Game. It extends the Usable,
 * Movable and Item subclasses. A Craftable object requires a list of resource
 * in order to make.
 */

@SuppressWarnings("serial")
public abstract class Craftable extends Usable {

	protected List<Resource> ingredients;

	/**
	 * Creates instance of Craftable class.
	 * 
	 * @param worldLocation
	 * @param tileLocation
	 */
	public Craftable(Point worldLocation, Point tileLocation, List<Resource> ingredients) {
		super(null, null, worldLocation, tileLocation, 1, 1);
		this.ingredients = ingredients;
	}
}
