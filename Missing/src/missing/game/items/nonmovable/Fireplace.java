/*	File: Fireplace.java
 * 	
 * 	Authors:			ID
 *	Casey Huang			300316284
 * 	Date				Author					Changes
 *	7 Sep 16 			Casey Huang				Created Fireplace class and added javaDoc comments
 *	7 Sep 16			Casey Huang				Updated the performAction method
 *	7 Sep 16			Casey Huang				Created a setFoodType method and Updated the performAction method.
 */
package missing.game.items.nonmovable;

import java.awt.Point;

import missing.game.characters.Player;
import missing.game.items.movable.Food;
import missing.helper.GameException;

/**
 * Represents a Fireplace item that a player can use to cook food.
 */
public class Fireplace extends Cooking{
	private Food.FoodType foodType = null;
	/**
	 * Creates a new instance of a Fireplace item.
	 * @param name
	 * @param description
	 * @param worldLocation
	 * @param tileLocation
	 */
	public Fireplace(String name, String description, Point worldLocation, Point tileLocation) {
		super(name, description, worldLocation, tileLocation);
	}

	@Override
	public void performAction(Player p) {
		try {
			Food food = new Food("", "", worldLocation, tileLocation, 0, 1, 0);
			p.addToPocket(food);
			food.setFoodType(foodType);
		} catch (GameException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Sets the type of food made by this fireplace.
	 * @param f
	 */
	public void setFoodType(Food.FoodType f){
		foodType = f;
	}
}
