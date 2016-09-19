/* File: FurnitureTests.java
 * 
 * Authors: 
 * Jian Wei Chong	300352789
 * Chris Rabe		300334207
 * 
 * Date: 			Author				Changes
 * 7 Sep 16			Jian Wei			Added tests 1 and 2 for fireplace
 * 13 Sep 16		Chris Rabe			FIXED DAMN COMMENTS
 */

package missing.tests;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Test;

import missing.game.characters.Player;
import missing.game.items.movable.Food;
import missing.game.items.movable.Food.FoodType;
import missing.game.items.nonmovable.Fireplace;
import missing.helper.GameException;

/**
 * these are test for all the items which extend furniture
 */
public class FurnitureTests {

	/**
	 * Creates a player and a fireplace, then performs the fireplace's action.
	 * Test ensures player has 1 item in pocket after action
	 */
	@Test
	public void fireplaceTest_1() {
		Player player = new Player("Chris", new Point(1, 1), new Point(0, 1));
		Point worldLocation = new Point(1, 1);
		Point tileLocation = new Point(1, 1);
		Food food = new Food(worldLocation, tileLocation, FoodType.APPLE);
		Fireplace fireplace = new Fireplace(worldLocation, tileLocation);
		fireplace.setFood(food);
		try {
			player.addToPocket(food);
			fireplace.performAction(player); // player takes wood from tree
		} catch (GameException e) {
			fail(e.getMessage());
		}
		assertEquals(player.getPocket().size(), 1); // checks that player has 1
													// item in pocket
	}

	/**
	 * Creates a player and a fireplace, then performs the fireplace's action.
	 * This test checks that the food in the fireplace has been cooked
	 */
	@Test
	public void fireplaceTest_2() {
		Player player = new Player("Chris", new Point(1, 1), new Point(0, 1));
		Point worldLocation = new Point(1, 1);
		Point tileLocation = new Point(1, 1);
		Food food = new Food(worldLocation, tileLocation, FoodType.APPLE);
		Fireplace fireplace = new Fireplace(worldLocation, tileLocation);
		fireplace.setFood(food);

		try {
			player.addToPocket(food);
			fireplace.performAction(player); // player places food in fireplace
		} catch (GameException e) {
			fail(e.getMessage());
		}
		boolean foodIsCooked = food.isCooked();
		assertTrue("Food is cooked", foodIsCooked); // checks that player has 1
													// item in pocket
	}

	/**
	 * Checks if there is no food inside the fireplace
	 */
	@Test
	public void fireplaceTest_3() {
		Player player = new Player("Chris", new Point(1, 1), new Point(0, 1));
		Point worldLocation = new Point(1, 1);
		Point tileLocation = new Point(1, 1);
		Food food = new Food(worldLocation, tileLocation, FoodType.APPLE);
		Fireplace fireplace = new Fireplace(worldLocation, tileLocation);
		fireplace.setFood(food);

		try {
			player.addToPocket(food);
			fireplace.performAction(player); // player places food in fireplace
		} catch (GameException e) {
			fail(e.getMessage());
		}
		assertEquals(fireplace.getFood(), null); // fireplace empty
	}
}
