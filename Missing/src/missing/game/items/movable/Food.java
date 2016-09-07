/* File: Food.java
 * 
 * Authors:
 * Linus Go		300345571
 * 
 * Date			Author			Modification
 * 7 Sep 16		Linus Go		created Food.java
 * 7 Sep 16 	Casey Huang		Created the enum for the type of food and created the helper method for changing the name and description
 * 								of the type of food.
 * 7 Sep 16 	Casey Huang		Created the toString method.
 */
package missing.game.items.movable;

import java.awt.Point;

/**
 * This class represents Food Items. Classes that extend the Food class are edible items that can be eaten by players to increase their health.
 *
 */
public class Food extends Health {
	private FoodType foodType= null;
	/**
	 * Create a new instance of a Food object.
	 * @param name
	 * @param description
	 * @param worldLocation
	 * @param tileLocation
	 * @param amount
	 * @param size
	 * @param REGAMOUNT
	 */
	public Food(String name, String description, Point worldLocation, Point tileLocation, int amount, int size, int REGAMOUNT) {
		super(name, description, worldLocation, tileLocation, amount, size, REGAMOUNT);
	}

	/**
	 * Represents the type of food.
	 */
	public static enum FoodType{
		Fruit,
		Chicken,
		Beef
	}
	
	/**
	 * Sets the type of food.
	 * @param f
	 */
	public void setFoodType(FoodType f){
		foodType = f;
		this.setInfo();
	}
	
	/**
	 * Sets the name and description for each type of food.
	 */
	public void setInfo(){
		switch(foodType){
		case Fruit:
			name = "Fruit";
			description = "A tasty piece of fruit";
			break;
		case Chicken:
			name = "Chicken";
			description = "A tasty piece of chicken";
			break;
		case Beef:
			name = "Beef";
			description = "A delicious beef meal";
			break;
		}
	}
	
	public String toString(){
		return "Name: " + name + " Description: " + description;
	}
}
