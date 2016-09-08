/*	File: Player.java
 * 	Authors				ID
 * 	Chris Rabe			300334207
 * 
 * 	Date				Author				Changes
 * 	7 Sep 16			Chris Rabe			created player class
 *  8 Sep 16			Edward Kelly		made Player implement TileObject
 */

package missing.game.characters;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import missing.game.items.movable.Movable;
import missing.game.world.nodes.WorldTile.TileObject;
import missing.helper.GameException;

/**
 * This class represents individual players within the game world.
 */
public class Player implements TileObject{
	/** Represents the maximum number of items which the player could have. */
	private final int MAX_ITEM_SIZE = 10;

	private String name;
	private int health;
	private int currentItemSize;
	/** WorldNode of the player inside a World */
	private Point worldLocation;
	/** Tile of the player inside a WorldNode */
	private Point tileLocation;

	private List<Movable> pocket;

	public Player(String name) {
		this.name = name;
		this.health = 100;
		this.currentItemSize = 0;
		this.pocket = new ArrayList<Movable>();
	}

	// Getters and setters...

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
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
	
	public Point getWorldLocation(){
		return worldLocation;
	}
	
	public void setWorldLocation(Point worldLocation){
		this.worldLocation = worldLocation;
	}
	public Point getTileLocation(){
		return tileLocation;
	}
	
	public void setTileLocation(Point tileLocation){
		this.tileLocation = tileLocation;
	}
	// Methods for use

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
		for (int i = 0; i < pocket.size(); i++) {
			if (pocket.get(i).equals(item)) {
				Movable tmp = pocket.get(i);
				pocket.remove(i);
				this.currentItemSize -= tmp.getSize();
				return tmp;
			}
		}
		throw new GameException("Item not found.");
	}
}
