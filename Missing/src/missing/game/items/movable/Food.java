/* File: Food.java
 * 
 * Authors:
 * Linus Go		300345571
 * Casey Huang	300316284
 * Chris Rabe	300334207
 * 
 * Date			Author			Modification
 * 7 Sep 16		Linus Go		created Food.java
 * 7 Sep 16 	Casey Huang		Created the enum for the type of food and created the helper method for changing the name and description
 * 								of the type of food.
 * 7 Sep 16 	Casey Huang		Created the toString method.
 * 7 Sep 16		Chris Rabe		removed some foodtype
 * 7 Sep 16		Chris Rabe		changed naming convention
 * 7 Sep 16		Chris Rabe		changed some methods to be helper methods
 * 7 Sep 16		Chris Rabe		added helper method for the toString method
 * 7 Sep 16		Chris Rabe		overrided equals and hashcode methods
 * 21 Sep 16	Chris Rabe		implemented fish and berries
 * 10 Oct 16	Edward Kelly	fixed use method
 */
package missing.game.items.movable;

import java.awt.Point;

import missing.game.characters.Player;
import missing.helper.GameException;

/**
 * This class represents Food Items. Classes that extend the Food class are
 * edible items that can be eaten by players to increase their health.
 *
 */
@SuppressWarnings("serial")
public class Food extends Health {

	/**
	 * Represents the type of food.
	 */
	public static enum FoodType {
		APPLE, BERRY, FISH
	}

	private FoodType foodType;

	private boolean cooked;
	
	/**
	 * Construct a new Food object.
	 * @param worldLocation
	 * @param tileLocation
	 * @param type
	 */
	public Food(Point worldLocation, Point tileLocation, FoodType type) {
		// Fill super constructor with dummy values
		super(null, null, worldLocation, tileLocation, -1, -1, -1);
		// Fill parent fields with actual values
		this.foodType = type;
		setInfo();
	}
	/**
	 * Construct a new Food object with a specified amount.
	 * @param worldLocation
	 * @param tileLocation
	 * @param type
	 * @param amount
	 */
	public Food(Point worldLocation, Point tileLocation, FoodType type, int amount) {
		this(worldLocation, tileLocation, type);
		super.setAmount(amount);
	}

	// Getters and Setters

	public FoodType getFoodType() {
		return foodType;
	}

	public void setFoodType(FoodType foodType) {
		this.foodType = foodType;
	}

	public boolean isCooked() {
		return cooked;
	}

	public void setCooked(boolean cooked) {
		this.cooked = cooked;
	}

	/**
	 * Returns the regenAmount that the certain food can give.
	 */
	protected int getRegenAmount() {
		//If the food item is cooked, then it increases the regenAmount by 10%.
		if (cooked) {
			return (int) (regenAmount * 0.1) + regenAmount;
		}
		return regenAmount;
	}

	// Methods

	/**
	 * The player consumes the food item. The player must have the item in
	 * his/her pocket in order to use the item. It increases the player's health
	 * by the food item's regen amount.
	 * 
	 * @throws GameException
	 */
	@Override
	public void use(Player player) throws GameException {
		// Check if player has the food in his pocket
		if (player.has(this)) {
			// increase player's health by regen amount
			int newHealth = player.getHealth() + getRegenAmount();
			if (newHealth > 100) {
				newHealth = 100;
			}
			player.setHealth(newHealth);
			// decrease the amount
			System.out.println(this.getName() + " amount "+this.getAmount());
			this.setAmount(this.getAmount() - 1);
			if (this.getAmount() <= 0) {
				player.removeFromPocket(this);
			} else {
				// decrease current size
				player.getPocket().setCurrentSize(player.getPocket().getCurrentSize() - 1);
			}
			System.out.println(this.getName() + " new amount "+this.getAmount());
		}
	}

	// Object methods

	@Override
	public String toString() {
		return name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((foodType == null) ? 0 : foodType.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Food other = (Food) obj;
		if (foodType != other.foodType)
			return false;
		return true;
	}

	// Helper Methods

	/**
	 * This method fills in the important constructor fields in the parent
	 * classes. It adds the name, description, as well as the value collected
	 * and the regeneration amount. It takes the foodType into consideration.
	 */
	private void setInfo() {
		switch (foodType) {
		case APPLE:
			name = "Apple";
			description = "A tasty piece of apple.";
			super.setAmount(2);
			regenAmount = 15;
			cooked = false;
			break;
		case BERRY:
			name = "Berry";
			description = "Yummy berries.";
			super.setAmount(3);
			regenAmount = 5;
			cooked = false;
			break;
		case FISH:
			name = "Fish";
			description = "A slimy fish.";
			super.setAmount(1);
			regenAmount = 20;
			cooked = false;
			break;
		default:
			break;
		}
	}
}
