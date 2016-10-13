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
	private int currentSize;
	protected int size = 25;

	public Container(Point worldLocation, Point tileLocation) {
		super("Container", "Something to store items in.", worldLocation, tileLocation);
		this.items = new ArrayList<Movable>();
		this.size = 25;
		this.currentSize = 0;
	}

	public Container(Point worldLocation, Point tileLocation, int size) {
		this(worldLocation, tileLocation);
		this.size = size;
	}

	// Getters and Setters...

	public List<Movable> getItems() {
		return items;
	}

	public void setItems(List<Movable> items) {
		this.items = items;
	}

	public int getCurrentSize() {
		return currentSize;
	}

	public void setCurrentSize(int currentSize) {
		this.currentSize = currentSize;
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
		int newSize = currentSize + item.getCount();
		if (newSize <= size) {
			this.currentSize = newSize;
			Movable i = findItem(item);
			if (i == null) {
				items.add(item);
			} else {
				i.setAmount(i.getAmount() + item.getAmount()); // increase count
			}
			return;
		}
		throw new GameException("Not enough space left.");
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
		this.currentSize -= item.getAmount();
		return item;
	}

	/**
	 * Removes the item from the container at the specified index. When calling
	 * this method, the index MUST match the item which the player wants.
	 * 
	 * @param index
	 * @return
	 */
	public Movable removeItem(Movable item){
		// search for the first occurence of the item
		Movable tmp = findItem(item);
		if (tmp != null) {
			items.remove(tmp);
			if (tmp.getAmount() == 0) {
				this.currentSize--;
			} else {
				this.currentSize -= tmp.getAmount();
			}
			return tmp;
		}
		return null;
	}

	/**
	 * This method returns the first occurence of the item passed in the method.
	 * It returns null if the item is not found.
	 * 
	 * @param item
	 * @return
	 */
	private Movable findItem(Movable item) {
		for (Movable m : items) {
			if (item.getName().equals(m.getName())) {
				return m;
			}
		}
		return null;
	}

	@Override
	public void performAction(Player player) throws GameException, SignalException {
		throw new SignalException("CONTAINER");
	}
}
