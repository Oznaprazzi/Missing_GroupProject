/* File: Usable.java
 * 
 * Authors:
 * Edward Kelly 300334192
 * 
 * Date			Author			Modification
 * 6 Sep 16		Edward Kelly	created Usable class
 */
package missing.game.items.movable;

import missing.game.items.Movable;

/**
 * Represents a Movable item that can be used by the player to perform an action
 */
public abstract class Usable extends Movable{

	public Usable(String name, String description, double weight) {
		super(name, description, weight);
	}

}
