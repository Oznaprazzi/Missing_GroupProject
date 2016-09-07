/* File: Resource.java
 * 
 * Authors:
 * Linus Go		300345571
 * 
 * Date			Author			Modification
 * 7 Sep 16		Linus Go		created resource class.
 */
package missing.game.items.movable;

import java.awt.Point;
/**
 * This class represents a 
 * @author linus
 *
 */
public abstract class Resource extends Movable {
	/**
	 * Construct a new Instance of a Resource.
	 * @param name
	 * @param description
	 * @param worldLocation
	 * @param tileLocation
	 * @param weight
	 * @param size
	 */
	public Resource(String name, String description, Point worldLocation, Point tileLocation, int weight, int size) {
		super(name, description, worldLocation, tileLocation, weight, size);
		// TODO Auto-generated constructor stub
	}

}
