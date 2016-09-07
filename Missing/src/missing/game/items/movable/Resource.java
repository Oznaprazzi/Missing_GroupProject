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
 * 
 * @author linus
 *
 */
public abstract class Resource extends Movable {

	public Resource(String name, String description, Point worldLocation, Point tileLocation, int amount, int size) {
		super(name, description, worldLocation, tileLocation, size, size);
		// TODO Auto-generated constructor stub
	}

}
