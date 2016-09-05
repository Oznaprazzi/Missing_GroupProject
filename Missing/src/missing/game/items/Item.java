/* File: Item.java
 * 
 * Authors:
 * Edward Kelly 300334192
 * Chris Rabe	300334207
 * 
 * Date			Author			Modification
 * 5 Sep 16		Edward Kelly	create item class
 * 5 Sep 16		Chris Rabe		improved javadocs and changed worldLocation to Point object
 */
package missing.game.items;

import java.awt.Point;

/**
 * Represents an item in the game, all items are a subclass of Item
 *
 */
public abstract class Item {
	/** name of item, used when displaying details of item */
	protected String name;
	/** description of item, used when displaying details of item */
	protected String description;
	/** represents the portion of the world which the item is located in */
	protected Point worldLocation;
	/**
	 * represents the specific point inside the render window where the item
	 * should be rendered
	 */
	protected Point renderLocation;

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public Point getRenderLocation() {
		return renderLocation;
	}

	public Point getWorldLocation() {
		return worldLocation;
	}

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

	public String toString() {
		return name + " " + description;
	}
}
