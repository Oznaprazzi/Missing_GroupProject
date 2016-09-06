/* File: Interactable.java
 * 
 * Authors			ID
 * Linus Go			300345571
 * 
 * Date				Author			Modification
 *	6 Sep 2016		Linus Go		Created Interactable class
 *	6 Sep 2016		Linus Go		Added javadoc comments and descriptions.
 *	6 Sep 2016 		Casey Huang		Added x and y coordinates to display image
 */
package missing.game.items.nonmovable;
import java.awt.Graphics;

import missing.game.items.NonMovable;

/**
 * Class that represents an Interactable item. A user can Interact with these type of items,
 * such as a door, a light switch or any type of non-movable toggle.
 */
public abstract class Interactable extends NonMovable{
	
	/**
	 * Create a new Instance of an Interactable item.
	 * @param name
	 * @param description
	 */
	public Interactable(String name, String description) {
		super(name, description);
	}

	/**
	 * Draws the current Non-movable item onto the screen.
	 */
	@Override
	public abstract void display(Graphics g, int x, int y);
}
