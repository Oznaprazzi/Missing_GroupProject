/*	File: Player.java
 * 
 * 	Authors:					ID:
 * 	Chris Rabe					300334207
 * 
 * 	Date						Author						Changes
 * 	6 Sep 16					Chris Rabe					create player class
 *  6 Sep 16					Linus Go					added basic methods to the player class.
 *	6 Sep 16					Linus Go					Added a health field and state field to the player.
 *	6 Sep 16					Linus Go					added a players pocket and methods to add/remove/find items in pocket.
 */

package missing.game.entities;

import java.util.ArrayList;
import java.util.List;
import missing.game.items.Item;

//TODO: add methods to the player.
public class Player {
	
	/**The name of the player */
	private String name;
	
	/**The current health of the player */
	private int health = 100;
	
	/**The state the player is in. Can be "Normal" or "Hungry" */
	private String playerState = "Normal";
	
	/** The players pocket. Every player has one. Can hold up to POCKET_SIZE items. */
	private List<Item> playerPocket;
	
	/** The fixed size of the player's pocket. */
	private static final int POCKET_SIZE = 10;
	
	
	/**
	 * Create a new instance of a player.
	 * @param name
	 */
	public Player(String name){
		this.name = name;
		playerPocket = new ArrayList<Item>(POCKET_SIZE);
	}
	
	/*Getter and Setter Methods for Player */
	
	/**
	 * Return the amount of health a player has.
	 * @return
	 */
	public int healthStatus(){
		return this.health;
	}
	
	/**
	 * Adds an Item to the Players Pocket.
	 * This will fail if the player's pocket is too big.
	 * @See POCKET_SIZE
	 * @param i - item to be added.
	 * @returns boolean (if successful)
	 */
	public boolean addToPocket(Item i){
		return playerPocket.size() >= POCKET_SIZE ? false : playerPocket.add(i);
	}
	
	/**
	 * Removes an Item from the Players Pocket.
	 * @param i - item to be added.
	 * @return boolean (if successful)
	 */
	public boolean removeFromPocket(Item i){
		return playerPocket.remove(i);
	}
	/**
	 * Returns if an Item is contained in the Players Pocket.
	 * @param i - item to be added.
	 * @return boolean (if contained in bag)
	 */
	public boolean containedInPocket(Item i){
		return playerPocket.contains(i);
	}
	
	/**
	 * Increase the amount of health a player has
	 * @param amt of health to increase.
	 */
	public void increaseHealth(int amt){
		health+=amt;
	}
	/**
	 * Decrease the amount of health a player has.
	 * @param amt of health to decrease.
	 */
	public void decreaseHealth(int amt){
		health-=amt;
	}
	
	
	/**
	 * Returns the state the player is in.
	 * @return
	 */
	public String getPlayerState(){
		return this.playerState;
	}
	
	/**
	 * Sets the state the player is in.
	 * @param State of player (String)
	 */
	public void setPlayerState(String st){
		this.playerState = st;
	}
		
	/**
	 * Returns the name of the player.
	 * @return
	 */
	public String getName(){
		return this.name;
	}
	
	/*ACTIONS A PLAYER CAN PERFORM */
	
	/**
	 * Allows a player to go to sleep, which replenishes their health and changes their state to "Asleep"
	 */
	public void goSleep(){
		this.setPlayerState("Asleep");
		this.increaseHealth(5);
	}
	
	public void awaken(){
		
	}
	
	
}
