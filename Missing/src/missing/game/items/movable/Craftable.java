/*File : Craftable.java
 * 
 * Authors    		ID
 * Jian Wei Chong	300352789
 * Chris Rabe		300334207
 * 
 * Date 		Author					Modification
 * 18/09/16		Jian Wei				created the class
 * 8/10/16		Chris rabe				added ingredients field
 * 
 */

package missing.game.items.movable;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import missing.game.characters.Player;
import missing.game.items.movable.Tool.ToolType;
import missing.game.items.nonmovable.Pocket;
import missing.helper.GameException;

/**
 * This class represents a Craftable item in the Game. It extends the Usable,
 * Movable and Item subclasses. A Craftable object requires a list of resource
 * in order to make.
 */

@SuppressWarnings("serial")
public abstract class Craftable extends Usable {

	private List<Resource> ingredients;

	/**
	 * Creates instance of Craftable class.
	 * 
	 * @param worldLocation
	 * @param tileLocation
	 */
	public Craftable(Point worldLocation, Point tileLocation) {
		super(null, null, worldLocation, tileLocation, 1, 1);
	}

	// Getters and Setters

	public List<Resource> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<Resource> ingredients) {
		this.ingredients = ingredients;
	}

	/**
	 * This method checks the item inside the player's hand and compares it with
	 * the list of ingredients needed to create the tool. If it matches, then it
	 * returns the tool which the player wants to create, otherwise it throws a
	 * GameException.
	 * 
	 * @param type
	 * @param player
	 * @return
	 */
	public static Tool createTool(ToolType type, Player player) throws GameException {
		switch (type) {
		case AXE:
			return createAxe(player);
		case FISHINGROD:
			return createRod(player);
		case PICKAXE:
			return createPickaxe(player);
		case SHOVEL:
			return createShovel(player);
		}
		return null;
	}

	private static Tool createShovel(Player player) throws GameException {
		int woodLeft = 2;
		int stoneLeft = 1;
		Pocket pocket = player.getPocket();
		List<Movable> items = pocket.getItems();
		List<Movable> used = new ArrayList<Movable>();
		for (Movable item : items) {
			if (woodLeft == 0 && stoneLeft == 0) {
				break; // no point searching anymore
			}
			int amt = 0;
			int newAmt = 0;
			if (item instanceof Wood && woodLeft != 0) {
				Wood w = (Wood) item;
				amt = w.getAmount();
				if (amt > woodLeft) {
					// item has enough wood -- just decrease
					newAmt = amt - woodLeft;
					pocket.setCurrentSize(pocket.getCurrentSize() - woodLeft);
					woodLeft = 0;
					w.setAmount(newAmt);
				} else {
					newAmt = woodLeft - amt;
					woodLeft = newAmt;
					used.add(item);
				}
			} else if (item instanceof Stone && stoneLeft != 0) {
				Stone s = (Stone) item;
				amt = s.getAmount();
				if (amt > stoneLeft) {
					// item has enough stone -- just decrease
					newAmt = amt - stoneLeft;
					pocket.setCurrentSize(pocket.getCurrentSize() - stoneLeft);
					stoneLeft = 0;
					s.setAmount(newAmt);
				} else {
					newAmt = stoneLeft - amt;
					stoneLeft = newAmt;
					used.add(item);
				}
			}
		}
		if (woodLeft == 0 && stoneLeft == 0) {
			// remove all used items
			for (Movable i : used) {
				player.removeFromPocket(i);
			}
			// return the new tool
			return new Tool(null, null, ToolType.SHOVEL);
		}
		throw new GameException("Player does not have the required items");
	}

	private static Tool createPickaxe(Player player) throws GameException {
		int woodLeft = 2;
		int stoneLeft = 3;
		Pocket pocket = player.getPocket();
		List<Movable> items = pocket.getItems();
		List<Movable> used = new ArrayList<Movable>();
		for (Movable item : items) {
			if (woodLeft == 0 && stoneLeft == 0) {
				break; // no point searching anymore
			}
			int amt = 0;
			int newAmt = 0;
			if (item instanceof Wood && woodLeft != 0) {
				Wood w = (Wood) item;
				amt = w.getAmount();
				if (amt > woodLeft) {
					// item has enough wood -- just decrease
					newAmt = amt - woodLeft;
					pocket.setCurrentSize(pocket.getCurrentSize() - woodLeft);
					woodLeft = 0;
					w.setAmount(newAmt);
				} else {
					newAmt = woodLeft - amt;
					woodLeft = newAmt;
					used.add(item);
				}
			} else if (item instanceof Stone && stoneLeft != 0) {
				Stone s = (Stone) item;
				amt = s.getAmount();
				if (amt > stoneLeft) {
					// item has enough stone -- just decrease
					newAmt = amt - stoneLeft;
					pocket.setCurrentSize(pocket.getCurrentSize() - stoneLeft);
					stoneLeft = 0;
					s.setAmount(newAmt);
				} else {
					newAmt = stoneLeft - amt;
					stoneLeft = newAmt;
					used.add(item);
				}
			}
		}
		if (woodLeft == 0 && stoneLeft == 0) {
			// remove all used items
			for (Movable i : used) {
				player.removeFromPocket(i);
			}
			// return the new tool
			return new Tool(null, null, ToolType.PICKAXE);
		}
		throw new GameException("Player does not have the required items");
	}

	private static Tool createRod(Player player) throws GameException {
		int woodLeft = 2;
		int dirtLeft = 3;
		Pocket pocket = player.getPocket();
		List<Movable> items = pocket.getItems();
		List<Movable> used = new ArrayList<Movable>();
		for (Movable item : items) {
			if (woodLeft == 0 && dirtLeft == 0) {
				break; // no point searching anymore
			}
			int amt = 0;
			int newAmt = 0;
			if (item instanceof Wood && woodLeft != 0) {
				Wood w = (Wood) item;
				amt = w.getAmount();
				if (amt > woodLeft) {
					// item has enough wood -- just decrease
					newAmt = amt - woodLeft;
					pocket.setCurrentSize(pocket.getCurrentSize() - woodLeft);
					woodLeft = 0;
					w.setAmount(newAmt);
				} else {
					newAmt = woodLeft - amt;
					woodLeft = newAmt;
					used.add(item);
				}
			} else if (item instanceof Dirt && dirtLeft != 0) {
				Dirt d = (Dirt) item;
				amt = d.getAmount();
				if (amt > dirtLeft) {
					// item has enough stone -- just decrease
					newAmt = amt - dirtLeft;
					pocket.setCurrentSize(pocket.getCurrentSize() - dirtLeft);
					dirtLeft = 0;
					d.setAmount(newAmt);
				} else {
					newAmt = dirtLeft - amt;
					dirtLeft = newAmt;
					used.add(item);
				}
			}
		}
		if (woodLeft == 0 && dirtLeft == 0) {
			// remove all used items
			for (Movable i : used) {
				player.removeFromPocket(i);
			}
			// return the new tool
			return new Tool(null, null, ToolType.FISHINGROD);
		}
		throw new GameException("Player does not have the required items");
	}

	private static Tool createAxe(Player player) throws GameException {
		int woodLeft = 2;
		int stoneLeft = 3;
		Pocket pocket = player.getPocket();
		List<Movable> items = pocket.getItems();
		List<Movable> used = new ArrayList<Movable>();
		for (Movable item : items) {
			if (woodLeft == 0 && stoneLeft == 0) {
				break; // no point searching anymore
			}
			int amt = 0;
			int newAmt = 0;
			if (item instanceof Wood && woodLeft != 0) {
				Wood w = (Wood) item;
				amt = w.getAmount();
				if (amt > woodLeft) {
					// item has enough wood -- just decrease
					newAmt = amt - woodLeft;
					pocket.setCurrentSize(pocket.getCurrentSize() - woodLeft);
					woodLeft = 0;
					w.setAmount(newAmt);
				} else {
					newAmt = woodLeft - amt;
					woodLeft = newAmt;
					used.add(item);
				}
			} else if (item instanceof Stone && stoneLeft != 0) {
				Stone s = (Stone) item;
				amt = s.getAmount();
				if (amt > stoneLeft) {
					// item has enough stone -- just decrease
					newAmt = amt - stoneLeft;
					pocket.setCurrentSize(pocket.getCurrentSize() - stoneLeft);
					stoneLeft = 0;
					s.setAmount(newAmt);
				} else {
					newAmt = stoneLeft - amt;
					stoneLeft = newAmt;
					used.add(item);
				}
			}
		}
		if (woodLeft == 0 && stoneLeft == 0) {
			// remove all used items
			for (Movable i : used) {
				player.removeFromPocket(i);
			}
			// return the new tool
			return new Tool(null, null, ToolType.AXE);
		}
		throw new GameException("Player does not have the required items");
	}
}
