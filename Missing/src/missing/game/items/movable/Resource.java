/* File: Resource.java
 * 
 * Authors			ID
 * Edward Kelly 	300334192
 * 
 * Date			Author			Modification
 * 6 Sep 16		Edward Kelly	created Resource class
 */
package missing.game.items.movable;

import missing.game.items.Movable;
/**
 * Represents a movable item that is used to make Craftable items
 *
 */
public abstract class Resource extends Movable{

	public Resource(String name, String description, double weight) {
		super(name, description, weight);
	}

}
