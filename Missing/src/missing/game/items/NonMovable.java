/*	File: NonMovable.java
 * 	
 * 	Authors:			ID
 * 	Chris Rabe			300334207
 * 
 * 	Date				Author					Changes
 * 	5 Sep 16			Chris Rabe				added java documentation		
 */
package missing.game.items;

/**
 * This class provides skeleton implementation of items which cannot be moved by
 * the player throughout the game world. It represents objects which is mainly
 * used for aesthetic purposes of the game world and may be beneficial for the
 * player.
 */
public abstract class NonMovable extends Item {

	public NonMovable(String name, String description) {
		super(name, description);
	}

}
