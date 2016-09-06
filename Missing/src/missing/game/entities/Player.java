/*	File: Player.java
 * 
 * 	Authors:					ID:
 * 	Chris Rabe					300334207
 * 
 * 	Date						Author						Changes
 * 	6 Sep 16					Chris Rabe					create player class
 *  6 Sep 16					Linus Go					added basic methods to the player class.
 	6 Sep 16					Linus Go					Added a health field and state field to the player.
 */

package missing.game.entities;

//TODO: add methods to the player.
public class Player {
	
	/*The name of the player */
	private String name;
	
	/*The current health of the player */
	private int health = 100;
	
	/*The state the player is in. Can be "Normal" or "Hungry" */
	private String playerState = "Normal";
	
	/**
	 * Create a new instance of a player.
	 * @param name
	 */
	public Player(String name){
		this.name = name;
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
	
	
}
