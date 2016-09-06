/*	File: Player.java
 * 
 * 	Authors:					ID:
 * 	Linus Go					300345571
 * 	Chris Rabe					300334207
 * 
 * 	Date						Author						Changes
 * 	6 Sep 16					Chris Rabe					create player class
 *  6 Sep 16					Linus Go					added basic methods to the player class.
 *	6 Sep 16					Linus Go					Added a health field and state field to the player.
 *	6 Sep 16					Linus Go					added a players pocket and methods to add/remove/find items in pocket.
 *	6 Sep 16					Chris Rabe					removed unused imports
 *	6 Sep 16					Chris Rabe					removed some complicated methods
 *	6 Sep 16					Chris Rabe					replaced String state to enumeration type
 */

package missing.game.entities;

import java.util.ArrayList;
import java.util.List;
import missing.game.items.Movable;
import missing.helper.GameException;

/**
 * This class represents a single player within the game. It contains
 * information such as the player's state and its health and the items the
 * player is carrying in his/her pocket.
 */
public class Player {

	// TODO: Note to Linus:

	/*
	 * You should use a combination of the defined methods inside the player
	 * class to simulate actions which the player is doing. The player class
	 * should SOLELY be used to store data. Not perform complicated actions.
	 * This is because it will have potentially have low cohesion, meaning that
	 * the methods are NOT logically linked to each other.
	 * 
	 * Here are some examples of the combination of methods which you could use:
	 * 'performAction' is defined within the 'Interactable' class.
	 * 
	 * Sleeping in Bed - restores player health to full
	 * 
	 * public void performAction(Player p){
	 * 		int n = 20;
	 * 		p.setHealth(p.getHealth + n);
	 * }
	 * 
	 * 
	 * Mining Rocks - adds stone inside player's pocket
	 * 
	 * public void performAction(Player p){
	 * 		try{
	 * 			p.addToPocket(new Stone());
	 * 		} catch (GameException e){
	 * 			System.out.println(e.getMessage());
	 * 		}
	 * }
	 * 
	 * TODO REMOVE THIS NOTE ONCE YOU FINISH READING OR WHEN YOU GET THE IDEA
	 */

	/**
	 * The State represents the condition of the player. Each state has its own
	 * effect to the player.
	 */
	public enum State {
		// If more states need to be added, place it here
		HUNGRY, NORMAL
	}

	/** Maximum number of items that the player can carry */
	private static final int POCKET_SIZE = 10;

	/** The current health of the player */
	private int health;

	/** Represents the current state of the player. */
	private State state;

	/**
	 * The players pocket. Contains a list of "Movable" items, which holds up to
	 * POCKET_SIZE items.
	 */
	private List<Movable> pocket;

	public Player() {
		this.health = 100;
		this.state = State.NORMAL;
		pocket = new ArrayList<Movable>(POCKET_SIZE);
	}

	// Getters and Setters

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public List<Movable> getPocket() {
		return pocket;
	}

	public void setPocket(List<Movable> pocket) {
		this.pocket = pocket;
	}

	// Actions performed by player

	/**
	 * Adds an item to the Players Pocket. This will fail if the player's pocket
	 * is too big.
	 * 
	 * @param i
	 *            item to be added.
	 * @throws GameException
	 */
	public void addToPocket(Movable i) throws GameException {
		if (pocket.size() >= POCKET_SIZE) {
			throw new GameException("Pocket is full.");
		}
		pocket.add(i);
	}

	/**
	 * Removes the first occurence of the given item in the player's pocket
	 * 
	 * @param i
	 *            item to be removed
	 * @throws GameException
	 */
	public void removeFromPocket(Movable i) throws GameException {
		if (i == null) {
			throw new GameException("Cannot add null to pocket");
		}
		if (pocket.isEmpty()) {
			throw new GameException("Pocket is empty.");
		}
		if (!pocket.contains(i)) {
			throw new GameException("Player does not have specified item");
		}

		for (Movable m : pocket) {
			if (m.equals(i)) {
				pocket.remove(m);
				return;
			}
		}
	}
}
