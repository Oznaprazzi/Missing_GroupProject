/* File: Interactable.java
 * 
 * Authors			ID
 * Linus Go			300345571
 * Casey Huang		300316284
 * Chris Rabe		300334207
 * 
 * Date				Author			Modification
 *	6 Sep 2016		Linus Go		Created Interactable class
 *	6 Sep 2016		Linus Go		Added javadoc comments and descriptions.
 *	6 Sep 2016 		Casey Huang		Added x and y coordinates to display image
 *	6 Sep 2016		Chris Rabe		added width and height fields since its a recurring field in subclasses
 *	6 Sep 2016		Chris Rabe		create new constructor 
 */
package missing.game.items.nonmovable;

import java.awt.Graphics;

import missing.game.items.NonMovable;

/**
 * Class that represents an Interactable item. A user can Interact with these
 * type of items, such as a door, a light switch or any type of non-movable
 * toggle.
 */
public abstract class Interactable extends NonMovable {

	protected int width;
	protected int height;

	/**
	 * Create a new Instance of an Interactable item.
	 * 
	 * @param name
	 * @param description
	 */
	public Interactable(String name, String description) {
		super(name, description);
	}

	public Interactable(String name, String description, int width, int height) {
		this(name, description);
		this.width = width;
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * Draws the current Non-movable item onto the screen.
	 */
	@Override
	public abstract void display(Graphics g, int x, int y);
}
