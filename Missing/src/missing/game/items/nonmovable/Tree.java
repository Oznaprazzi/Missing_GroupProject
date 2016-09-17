/*	File: Tree.java
 * 	
 * 	Authors:			ID
 *	Casey Huang			300316284
 *	Chris Rabe			300334207
 *
 * 	Date				Author					Changes
 *	7 Sep 16 			Casey Huang				Created Tree class and added javaDoc comments
 *	7 Sep 16			Casey Huang				Updated the performAction method.
 *	7 Sep 16			Chris Rabe				updated constructor
 *	7 Sep 16			Chris Rabe				added a chance for apples to be given
 */
package missing.game.items.nonmovable;

import java.awt.Point;

import static java.lang.Math.random;

import missing.game.characters.Player;
import missing.game.items.movable.Wood;
import missing.game.items.movable.Food;
import missing.game.items.movable.Food.FoodType;
import missing.helper.GameException;

/**
 * Represents a Tree item that a player can cut down to get the wood resource.
 * It also has a 50% chance to get an apple from the tree.
 */
public class Tree extends Source {
	// TODO Add timer for refreshing resources
	// TODO Do null check before storing resource inside player's pocket

	private static final int APPLE_CHANCE = 50;

	public Tree(Point worldLocation, Point tileLocation) {
		super("Tree", "A tall majestic tree.", worldLocation, tileLocation,
				new Wood(worldLocation, tileLocation, MAX_RESOURCE));
	}

	@Override
	public void performAction(Player p) throws GameException {
		resource.setStored(true);
		p.addToPocket(resource);
		// Replace resource -- should add timer here
		int numWood = 1 + (int) (random() * MAX_RESOURCE);
		resource = new Wood(worldLocation, tileLocation, numWood);
		// 50% chance that player will get apples too
		int playerChance = (int) (random() * 100);
		if (playerChance < APPLE_CHANCE) {
			Food apple = new Food(worldLocation, tileLocation, FoodType.APPLE);
			apple.setStored(true);
			p.addToPocket(apple);
		}
	}

}
