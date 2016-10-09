/*	File: Player.java
 * 	Authors				ID
 * 	Chris Rabe			300334207
 * 	Jian Wei Chong		300352789
 *  Casey Huang			300316284
 * 
 * 	Date				Author				Changes
 * 	7 Sep 16			Chris Rabe			created player class
 * 	8 Sep 16			Chris Rabe			implemented TileObject
 *  8 Sep 16			Edward Kelly		made Player implement TileObject
 *  20 Sep 16			Jian Wei Chong		added findTool method
 *  26 Sep 16 			Casey Huang			added item's count when item added to bag
 *  27 Sep 16			Casey Huang			removed item count from both pocket and bag - moved this
 *  										to container class.
 *  3 Oct 16			Jian Wei			Fixed bug in setHealth method so it can be set to 0
 *  3 Oct 16			Casey Huang			Updated methods associated with pocket
 *  4 oct 16			Jian Wei			added a Money field
 *  4 Oct 16			Chris Rabe			created a flag which indicates if the player is inside a pile
 *  5 Oct 16			Chris Rabe			buffed player damage to other players - now takes away 10 health

 */

package missing.game.characters;

import java.awt.Point;
import java.util.ArrayList;

import missing.game.items.movable.Food;
import missing.game.items.movable.Money;
import missing.game.items.movable.Movable;
import missing.game.items.movable.Tool;
import missing.game.items.movable.Tool.ToolType;
import missing.game.items.nonmovable.Bag;
import missing.game.items.nonmovable.Pocket;
import missing.helper.GameException;
import missing.helper.SignalException;

/**
 * This class represents individual players within the game world.
 */
@SuppressWarnings("serial")
public class Player extends Character {

	private int health;
	private Pocket pocket;
	private Bag bag;
	private boolean isDead;
	private boolean insidePile;
	private int id; // client ID
	private int imageID; // sprite ID
	private Money money;

	public Player(int id, String name, Point worldLocation, Point tileLocation) {
		super(name, String.format("%s's avatar.", name), worldLocation, tileLocation, Direction.SOUTH, Direction.ALL);
		this.health = 100;
		this.pocket = new Pocket();
		bag = new Bag();
		this.isDead = false;
		this.id = id;
		this.money = new Money(tileLocation, tileLocation, 0);
	}

	// Getters and setters...

	public boolean isInsidePile() {
		return insidePile;
	}

	public void setInsidePile(boolean insidePile) {
		this.insidePile = insidePile;
	}

	public int getImageID() {
		return imageID;
	}

	public void setImageID(int imageID) {
		this.imageID = imageID;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isDead() {
		return isDead;
	}

	public void setDead(boolean isDead) {
		this.isDead = isDead;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		if (health >= 0 && health < 100) {
			this.health = health;
		}
	}

	public Pocket getPocket() {
		return pocket;
	}

	public void setPocket(Pocket pocket) {
		this.pocket = pocket;
	}

	public Bag getBag() {
		return bag;
	}

	public void setBag(Bag bag) {
		this.bag = bag;
	}

	public Money getMoney() {
		return money;
	}

	public void setMoney(int m) {
		money.setAmount(m);
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
		return pocket.getItems().contains(item);
	}

	/**
	 * adds the passed item into the player's pocket. If the item is over the
	 * pocket threshold, then it throws an exception.
	 * 
	 * @param item
	 * @throws GameException
	 */
	public void addToPocket(Movable item) throws GameException {
		pocket.addItem(item);
	}

	/**
	 * Removes the item from the specified index.
	 * 
	 * @param index
	 * @return
	 * @throws GameException
	 */
	public Movable removeFromPocket(int index) throws GameException {
		if (pocket.getItems().isEmpty()) {
			throw new GameException("Pocket is empty");
		}
		return pocket.removeItem(index);
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
		if (pocket.getItems().isEmpty()) {
			throw new GameException("Pocket is empty.");
		}
		return pocket.removeItem(item);
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

	/**
	 * This method retrieves the tool with the lowest durability to ensure
	 * efficiency.
	 */
	public Tool getTool(ToolType type) {
		// Get all the tools with the given type inside the player's pocket
		ArrayList<Tool> tools = new ArrayList<Tool>();
		for (Movable item : getPocket().getItems()) {
			if (item instanceof Tool) {
				if (((Tool) item).getType().equals(type)) {
					tools.add((Tool) item); // add all the tools of that type
				}
			}
		}
		// Find the tool with the lowest durability
		Tool lowest = null;
		for (Tool tool : tools) {
			if (lowest == null) {
				lowest = tool;
			} else {
				if (lowest.getDurability() > tool.getDurability()) {
					lowest = tool;
				}
			}
		}
		return lowest;
	}

	/**
	 * This method grabs the first Food object inside the player's pocket. If
	 * the player does not have any food, it returns null.
	 * 
	 * @return
	 */
	public Food getFood() {
		for (Movable i : pocket.getItems()) {
			if (i instanceof Food) {
				return (Food) i;
			}
		}
		return null;
	}

	/**
	 * This method throws a SignalException which means that a trade must be
	 * initiated between the other player and this player.
	 */
	@Override
	public void performAction(Player player) throws GameException, SignalException {
		this.health -= 10;
		if (health <= 0) {
			this.setDead(true);
			throw new SignalException(String.format("DEAD %d", id));
		}
	}

}
