/* File: Usable.java
 * 
 * Authors			ID
 * Edward Kelly 	300334192
 * 
 * Date			Author			Modification
 * 6 Sep 16		Edward Kelly	created Usable class
 */
package missing.game.items.movable;

import missing.game.items.Movable;

/**
 * Represents a movable item that can be used by a player to do an action
 *
 */
public abstract class Usable extends Movable{

	public Usable(String name, String description, double weight) {
		super(name, description, weight);
	}
	
}
