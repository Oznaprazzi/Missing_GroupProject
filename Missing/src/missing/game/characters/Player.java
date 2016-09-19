/*	File: Player.java
 * 	Authors				ID
 * 	Chris Rabe			300334207
 * 
 * 	Date				Author				Changes
 * 	7 Sep 16			Chris Rabe			created player class
 * 	8 Sep 16			Chris Rabe			implemented TileObject
 *  8 Sep 16			Edward Kelly		made Player implement TileObject
 */

package missing.game.characters;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import missing.game.items.movable.Movable;
import missing.game.items.nonmovable.Bag;
import missing.helper.GameException;

/**
 * This class represents individual players within the game world.
 */
public class Player extends Character {

	/** Represents the maximum number of items which the player could have. */
	private final int MAX_ITEM_SIZE = 10;

	private int health;
	private int currentItemSize;
	private List<Movable> pocket;
	private Bag bag;

	public Player(String name, Point worldLocation, Point tileLocation) {
		super(name, String.format("%s's avatar.", name), worldLocation, tileLocation, Direction.SOUTH, Direction.ALL);
		this.health = 100;
		this.currentItemSize = 0;
		this.pocket = new ArrayList<Movable>();
		bag = new Bag();
	}

	// Getters and setters...

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getCurrentItemSize() {
		return currentItemSize;
	}

	public void setCurrentItemSize(int currentItemSize) {
		this.currentItemSize = currentItemSize;
	}

	public List<Movable> getPocket() {
		return pocket;
	}

	public void setPocket(List<Movable> pocket) {
		this.pocket = pocket;
	}

	public Bag getBag() {
		return bag;
	}

	// Methods for use

	/**
	 * Checks the player's pocket for any item. This method should be called
	 * when the player is using tools to gather resources.
	 * 
	 * @param item
	 * @return
	 */
	public boolean has(Movable item) {
		return pocket.contains(item);
	}

	/**
	 * adds the passed item into the player's pocket. If the item is over the
	 * pocket threshold, then it throws an exception.
	 * 
	 * @param item
	 * @throws GameException
	 */
	public void addToPocket(Movable item) throws GameException {
		int newSize = this.currentItemSize + item.getSize();
		if (newSize > MAX_ITEM_SIZE) {
			throw new GameException("Item cannot fit inside pocket.");
		}
		this.currentItemSize = newSize;
		pocket.add(item);
	}

	/**
	 * Removes the item from the specified index.
	 * 
	 * @param index
	 * @return
	 * @throws GameException
	 */
	public Movable removeFromPocket(int index) throws GameException {
		if (pocket.isEmpty()) {
			throw new GameException("Pocket is empty");
		}
		if (index >= pocket.size() || index < 0) {
			throw new GameException("Index must be within bounds of the pocket.");
		}
		Movable tmp = pocket.get(index);
		pocket.remove(index);
		return tmp;
	}

	/**
	 * Removes the first occurence of the passed item within the player's
	 * pocket.
	 * 
	 * @param item
	 * @return
	 * @throws GameException
	 */
	public Movable removeFromPocket(Movable item) throws GameException {
		if (pocket.isEmpty()) {
			throw new GameException("Pocket is empty.");
		}
		// search for the first occurence of the item
		for (int i = 0; i < pocket.size(); i++) {
			if (pocket.get(i).equals(item)) {
				Movable tmp = pocket.get(i);
				pocket.remove(i);
				this.currentItemSize -= tmp.getSize();
				return tmp;
			}
		}
		// didn't find item if reaches here
		throw new GameException("Item not found.");
	}

	/** Adds the specified item into the bag */
	public void addToBag(Movable item) throws GameException {
		bag.addItem(item);
	}

	/**
	 * Removes the specified item from the bag and shifts it to the pocket. If
	 * the pocket will be full after the transfer, the item will be placed back
	 * into the bag and it will notify the player that he/she needs to transfer
	 * some items into the bag.
	 * 
	 * @param index
	 * @throws GameException
	 */
	public void removeFromBag(int index) throws GameException {
		Movable tmp = bag.removeItem(index);
		try {
			addToPocket(tmp);
		} catch (GameException e) {
			// No room inside pocket
			bag.addItem(tmp);
			throw new GameException(e.getMessage());
		}
	}
}
