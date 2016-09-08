/* File: Item.java
 * 
 * Authors			ID
 * Edward Kelly 	300334192
 * Chris Rabe		300334207
 * 
 * Date				Author			Modification
 * 7 Sep 16			Edward Kelly	created item class
 * 8 Sep 16			Chris Rabe		made item implement TileObject
 * 8 Sep 16			Edward Kelly	made Item implement TileObject
 */
package missing.game.items;

import java.awt.Point;

import missing.game.world.nodes.WorldTile.TileObject;

/**
 * Represents all the interactable and non-interactable objects inside the game
 * world. This should be extended to a more specialised subclass.
 *
 */
public abstract class Item implements TileObject {
	/** name of item, used when displaying details of item */
	protected String name;
	/** description of item, used when displaying details of item */
	protected String description;
	/** represents the portion of the world which the item is located in */
	protected Point worldLocation;
	/** represents the tile this item is located on inside the worldLocation */
	protected Point tileLocation;

	/**
	 * Create an Instance of an Item, given its location.
	 * 
	 * @param name
	 *            name of the item
	 * @param description
	 *            describes what the item is
	 * @param worldLocation
	 *            the section of the world this item is located in
	 * @param tileLocation
	 *            the tile in the worldLocation in which the item is located
	 */
	public Item(String name, String description, Point worldLocation, Point tileLocation) {
		this.name = name;
		this.description = description;
		this.worldLocation = worldLocation;
		this.tileLocation = tileLocation;
	}

	// would it be better to pass a worldLocation and tileLocation as parameters
	// here?
	/**
	 * Checks if this item is on the tile at the given x, y location
	 * 
	 * @param x
	 *            x coordinate of tile
	 * @param y
	 *            y coordinate of tile
	 * @return returns true if item on tile, false if not
	 */
	public boolean on(int x, int y) {
		if (x == tileLocation.x && y == tileLocation.y)
			return true;
		return false;
	}

	// Getters and Setters

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public Point getTileLocation() {
		return tileLocation;
	}

	public void setTileLocation(Point tileLocation) {
		this.tileLocation = tileLocation;
	}

	public Point getWorldLocation() {
		return worldLocation;
	}

	public void setWorldLocation(Point worldLocation) {
		this.worldLocation = worldLocation;
	}

	// Overridden Object methods

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
		if (this == obj)
			return true;
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
		return name + ", " + description;
	}
}
