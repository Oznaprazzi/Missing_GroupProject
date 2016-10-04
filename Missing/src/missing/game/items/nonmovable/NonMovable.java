/*	File: NonMovable.java
 * 	
 * 	Authors:			ID
 *	Edward Kelly		300334192
 *	Casey Huang			300316284
 * 	Date				Author					Changes
	7 Sep 16			Edward Kelly			Created NonMovable class
	7 Sep 16 			Casey Huang				Created width and height fields and generated getters and setters for them.
	5 Oct 16			Chris Rabe				fixed checking if the player died
 */
package missing.game.items.nonmovable;

import java.awt.Point;

import missing.game.characters.Player;
import missing.game.items.Item;
import missing.helper.SignalException;

/**
 * This class provides skeleton implementation of items which cannot be moved by
 * the player throughout the game world. It represents objects which is mainly
 * used for aesthetic purposes of the game world and may be beneficial for the
 * player.
 */
@SuppressWarnings("serial")
public abstract class NonMovable extends Item {
	private int width;
	private int height;

	/**
	 * Creates a new instance of a NonMovable item
	 * 
	 * @param name
	 *            name of Movable
	 * @param description
	 *            description of the Movable item
	 * @param worldLocation
	 *            the section of the world the Movable item is located
	 * @param tileLocation
	 *            the tile inside a worldLocation the Movable item is located
	 */
	public NonMovable(String name, String description, Point worldLocation, Point tileLocation) {
		super(name, description, worldLocation, tileLocation);
	}

	/**
	 * Returns the width of this item.
	 * 
	 * @return
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Sets the width of this item.
	 * 
	 * @param width
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * Returns the height of this item.
	 * 
	 * @return
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Sets the height of this item.
	 * 
	 * @param height
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * Checks if the player is dead (used as a helper method for performAction)
	 * 
	 * @param player
	 * @throws SignalException
	 */
	protected void checkIfDead(Player player) throws SignalException {
		if (player.getHealth() <= 0) {
			player.setDead(true);
			throw new SignalException(String.format("DEAD %d", player.getId()));
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + height;
		result = prime * result + width;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		NonMovable other = (NonMovable) obj;
		if (height != other.height)
			return false;
		if (width != other.width)
			return false;
		return true;
	}

}
