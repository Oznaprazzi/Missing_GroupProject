/*	File: Container.java
 * 	Author
 * 	Chris Rabe			300334207
 * 
 * 	Date				Author					Changes
 * 	19 Sep 16			Chris Rabe				created container class
 * 	19 Sep 16			Chris Rabe				added getters and setters
 *  27 Sep 16			Casey Huang				fixed addItem bug
 */

package missing.game.items.nonmovable;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import missing.game.characters.Player;
import missing.game.items.movable.Movable;
import missing.helper.GameException;
import missing.helper.SignalException;

/**
 * A container which stores movable objects.
 */
public class Container extends NonMovable {

	private List<Movable> items;
	protected int size;

	public Container(Point worldLocation, Point tileLocation) {
		super("Container", "Something to store items in.", worldLocation, tileLocation);
		this.items = new ArrayList<Movable>();
		this.size = 25;
	}

	// Getters and Setters...

	public List<Movable> getItems() {
		return items;
	}

	protected void setItems(List<Movable> items) {
		this.items = items;
	}

	// Methods

	/**
	 * Stores the item inside the container. Throws an exception if the
	 * container is full.
	 * 
	 * @param item
	 * @throws GameException
	 */
	public void addItem(Movable item) throws GameException {
		if (items.size() < size) {
			this.items.add(item);
			return;
		}
		throw new GameException("No more space left.");
	}

	/**
	 * Removes the item from the container at the specified index. When calling
	 * this method, the index MUST match the item which the player wants.
	 * 
	 * @param index
	 * @return
	 * @throws GameException
	 */
	public Movable removeItem(int index) throws GameException {
		if (index < 0 || index >= items.size()) {
			throw new GameException("Index must be without bounds.");
		}
		Movable item = items.get(index);
		items.remove(index);
		return item;
	}

	@Override
	public void performAction(Player player) throws GameException, SignalException {
		throw new SignalException("CONTAINER");
	}
}
