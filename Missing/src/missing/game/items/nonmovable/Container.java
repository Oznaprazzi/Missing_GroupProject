/*	File: Container.java
 * 	Author
 * 	Chris Rabe			300334207
 *  Casey Huang 		300316284
 * 
 * 	Date				Author					Changes
 * 	19 Sep 16			Chris Rabe				created container class
 * 	19 Sep 16			Chris Rabe				added getters and setters
 *  27 Sep 16			Casey Huang				fixed addItem bug
 *  27 Sep 16			Casey Huang				Added item.increase() when adding item to container
 *  27 Sep 16			Casey Huang				Added findItem method to be able to increase the amount of the same 
 *  											item the player is carrying
 *  3 Oct 16			Casey Huang				Added removeItem(Movable item) method particularly for pocket
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
@SuppressWarnings("serial")
public class Container extends NonMovable {

	private List<Movable> items;
	protected int size = 25;

	public Container(Point worldLocation, Point tileLocation, int size) {
		super("Container", "Something to store items in.", worldLocation, tileLocation);
		this.items = new ArrayList<Movable>();
		this.size = size;
	}

	// Getters and Setters...

	public List<Movable> getItems() {
		return items;
	}

	public void setItems(List<Movable> items) {
		this.items = items;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
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
			Movable m = findItem(item);
			m.increaseCount();
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
		Movable m = findItem(item);
		if (m.getCount() > 0) {
			m.decreaseCount();
		}
		return item;
	}

	/**
	 * Removes the item from the container at the specified index. When calling
	 * this method, the index MUST match the item which the player wants.
	 * 
	 * @param index
	 * @return
	 * @throws GameException
	 */
	public Movable removeItem(Movable item) throws GameException {
		// search for the first occurence of the item
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i).equals(item)) {
				Movable tmp = items.get(i);
				items.remove(i);
				return tmp;
			}
		}
		// didn't find item if reaches here
		throw new GameException("Item not found.");
	}

	/**
	 * Checks if item is already in the container and returns that item, else it
	 * will just return the item
	 * 
	 * @param item
	 * @return
	 */
	private Movable findItem(Movable item) {
		for (Movable m : items) {
			if(item.getName().equals(m.getName())) {
				return m;
			}
		}
		return item;
	}

	@Override
	public void performAction(Player player) throws GameException, SignalException {
		throw new SignalException("CONTAINER");
	}
}
