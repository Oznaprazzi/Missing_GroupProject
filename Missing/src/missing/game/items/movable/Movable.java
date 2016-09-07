/* File: Movable.java
 * 
 * Authors:
 * Edward Kelly 300334192
 * Linus Go		300345571
 * 
 * Date			Author			Modification
 * 7 Sep 16		Edward Kelly	created Movable class
 * 7 Sep 16		Linus Go		Added size field and getters and setters.
 */
package missing.game.items.movable;

import java.awt.Point;

import missing.game.items.Item;

/**
 * Represents an item that can be moved/carried in the game. Movable items can
 * have their location changed.
 *
 */
public abstract class Movable extends Item {

	/** Represents the size of this movable object. **/
	protected int size;
	/** Number of movable items */
	private int amount;

	/**
	 * Creates a new instance of a Movable item
	 * 
	 * @param name
	 *            name of Movable
	 * @param description
	 *            description of the Movable item
	 * @param worldLocation
	 *            the section of the world the Movable item is located
	 * @param tileLocation
	 *            the tile inside a worldLocation the Movable item is located
	 * @param size
	 *            weight of the Movable item
	 */
	public Movable(String name, String description, Point worldLocation, Point tileLocation, int amount, int size) {
		super(name, description, worldLocation, tileLocation);
		this.size = size;
		this.amount = amount;
	}

	public int getSize() {
		return this.amount * this.size;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	// Overridden Object methods

	@Override
	public String toString() {
		return super.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + size;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Movable other = (Movable) obj;
		if (size != other.size)
			return false;
		return true;
	}

}
