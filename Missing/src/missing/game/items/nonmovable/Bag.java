/*	File: Bag.java
 * 	Author
 * 	Chris Rabe			300334207
 * 
 * 	Date				Author					Changes
 * 	19 Sep 16			Chris Rabe				created Bag.java
 * 	19 Sep 16			Chris Rabe				implemented increase size method
 */

package missing.game.items.nonmovable;

import missing.helper.GameException;

/**
 * This represents the player's bag. Each player has their own bag. Should not
 * be displayed on the GUI Window. The Bag can contain 20 items of any size. The
 * size of the bag can be increased.
 */
@SuppressWarnings("serial")
public class Bag extends Container {

	private final int MAX_CAPACITY = 50;

	public Bag() {
		super(null, null, 20);
		name = "Bag";
		description = "Bag full of items.";
	}

	/**
	 * Increases the capacity of the bag. This bag has a threshold of 50 items.
	 * 
	 * @param amount
	 * @throws GameException
	 */
	public void increaseSize(int amount) throws GameException {
		if ((size + amount) > MAX_CAPACITY) {
			throw new GameException("Can only increase up to 50 capacity.");
		}
		this.size = amount;
	}
}
