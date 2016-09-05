/* File: Item.java
 * 
 * Authors			ID
 * Edward Kelly 	300334192
 * Chris Rabe		300334207
 * 
 * Date				Author			Modification
 * 5 Sep 16			Edward Kelly	create item class
 * 5 Sep 16			Chris Rabe		improved javadocs and changed worldLocation to Point object
 * 5 Sep 16			Chris Rabe		added constructor for the item class
 */
package missing.game.items;

import java.awt.Point;

/**
 * Represents all the interactable and non-interactable objects inside the game
 * world. This should be extended to a more specialised subclass.
 *
 */
public abstract class Item {
	/** name of item, used when displaying details of item */
	protected String name;
	/** description of item, used when displaying details of item */
	protected String description;
	/** represents the portion of the world which the item is located in */
	protected Point worldLocation;
	/** represents the rendering origin of this item */
	protected Point renderLocation;

	public Item(String name, String description) {
		this.name = name;
		this.description = description;
	}

	public Item(String name, String description, Point worldLocation, Point renderLocation) {
		this(name, description);
		this.worldLocation = worldLocation;
		this.renderLocation = renderLocation;
	}

	// Getters and Setters...

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public Point getRenderLocation() {
		return renderLocation;
	}

	public void setRenderLocation(Point renderLocation) {
		this.renderLocation = renderLocation;
	}

	public Point getWorldLocation() {
		return worldLocation;
	}

	public void setWorldLocation(Point worldLocation) {
		this.worldLocation = worldLocation;
	}

	// Overrided Object methods

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return name + " " + description;
	}
}
