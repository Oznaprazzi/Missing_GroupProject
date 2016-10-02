/*	File: Pocket.java
 * 	Author
 * 	Casey Huang			300316284
 * 
 * 	Date				Author					Changes
 * 	3 Oct 16			Casey Huang				created Pocket.java
 */

package missing.game.items.nonmovable;

import missing.helper.GameException;

/**
 * This represents the player's bag. Each player has their own bag. Should not
 * be displayed on the GUI Window. The Bag can contain 20 items of any size. The
 * size of the bag can be increased.
 */
@SuppressWarnings("serial")
public class Pocket extends Container {

	private final int MAX_CAPACITY = 50;

	public Pocket() {
		super(null, null, 10);
		name = "Pocket";
		description = "Pocket full of items.";
	}
}
