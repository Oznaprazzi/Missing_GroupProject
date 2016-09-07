/*	File: Fireplace.java
 * 	
 * 	Authors:			ID
 *	Casey Huang			300316284
 *	Chris Rabe			300334207
 *
 * 	Date				Author					Changes
 *	7 Sep 16 			Casey Huang				Created Fireplace class and added javaDoc comments
 *	7 Sep 16			Casey Huang				Updated the performAction method
 *	7 Sep 16			Casey Huang				Created a setFoodType method and Updated the performAction method.
 *	7 Sep 16			Chris Rabe				removed food type field
 *	7 Sep 16			Chris Rabe				added method for adding food item to fireplace
 *	7 Sep 16			Chris Rabe				updated performAction method
 */
package missing.game.items.nonmovable;

import java.awt.Point;

import missing.game.characters.Player;
import missing.game.items.movable.Food;
import missing.helper.GameException;

/**
 * Represents a Fireplace item that a player can use to cook food.
 */
public class Fireplace extends Cooking {

	private Food food;

	/**
	 * Creates a new instance of a Fireplace item.
	 * 
	 * @param name
	 * @param description
	 * @param worldLocation
	 * @param tileLocation
	 */
	public Fireplace(Point worldLocation, Point tileLocation) {
		super("Fireplace", "Extremely warm.", worldLocation, tileLocation);
		food = null;
	}

	// Getters and Setters

	public Food getFood() {
		return food;
	}

	public void setFood(Food food) {
		this.food = food;
	}

	// Methods

	@Override
	public void performAction(Player p) throws GameException {
		if (food == null) {
			throw new GameException("There is no food in the fireplace!");
		}
		p.removeFromPocket(food); // Takes old food out from player
		food.setCooked(true); // Cook the food
		p.addToPocket(food); // Place it back to player's inventory
		food = null; // Reset fireplace
	}

}
