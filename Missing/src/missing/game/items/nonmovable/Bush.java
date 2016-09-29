/*	File: Bush.java
 * 
 * 	Author			ID
 * 	Edward Kelly	300334192
 * 	
 * 	Date			Author				Changes
 * 	7 Sep 16		Edward Kelly		created Bush class
 */
package missing.game.items.nonmovable;

import static java.lang.Math.random;

import java.awt.Point;

import missing.game.characters.Player;
import missing.game.items.movable.Food;
import missing.game.items.movable.Food.FoodType;
import missing.helper.GameException;

/**
 * Represents a Bush. A Bush is only for aesthetics. Subclass of Foliage
 */
@SuppressWarnings("serial")
public class Bush extends Foliage {

	private static final int BERRY_CHANCE = 70;
	/**
	 * Creates a new Bush object at the given locations
	 * 
	 * @param worldLocation
	 *            section of world Bush is located
	 * @param tileLocation
	 *            the tile inside the worldLocation the Bush is located
	 */
	public Bush(Point worldLocation, Point tileLocation) {
		super("Bush", "It's a bush", worldLocation, tileLocation);
	}

	@Override
	public void performAction(Player player) throws GameException {
		int playerChance = (int) (random() * 100);
		if (playerChance < BERRY_CHANCE) {
			Food berry = new Food(worldLocation, tileLocation, FoodType.BERRY);
			berry.setStored(true);
			player.addToPocket(berry);
		}

	}

}
