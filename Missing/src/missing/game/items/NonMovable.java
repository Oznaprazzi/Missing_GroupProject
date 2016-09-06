/*	File: NonMovable.java
 * 	
 * 	Authors:			ID
 * 	Chris Rabe			300334207
 *  Linus Go			300345571
 * 	Date				Author					Changes
 * 	5 Sep 16			Chris Rabe				added java documentation		
 *  6 Sep 16			Linus Go				updated documentation and added methods.
 *  6 Sep 16			Linus Go				Added an abstract display method.
 */
package missing.game.items;

import java.awt.Graphics;

/**
 * This class provides skeleton implementation of items which cannot be moved by
 * the player throughout the game world. It represents objects which is mainly
 * used for aesthetic purposes of the game world and may be beneficial for the
 * player.
 */
public abstract class NonMovable extends Item {
	/**
	 * Constructs an instance of a NonMovable object.
	 * @param name
	 * @param description
	 */
	public NonMovable(String name, String description) {
		super(name, description);
	}
	
	/**
	 * Draws the current Non-movable item onto the screen.
	 */
	public abstract void display(Graphics g);

}
