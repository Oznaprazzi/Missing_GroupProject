/* File: SourceTests.java
 * 
 * Authors: 
 * Jian Wei Chong   300352789
 * Chris Rabe		300334207
 * 
 * Date 			Author				Changes
 * 7 Sep 16			Jian Wei			Added tests 1 and 2 for trees
 * 16 Sep 16		Chris Rabe			Updated tests to match the new Tree constructor
 * 
 */
package missing.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.awt.Point;

import org.junit.Test;

import missing.game.characters.Player;
import missing.game.items.movable.Wood;
import missing.game.items.nonmovable.Tree;
import missing.helper.GameException;

/**
 * these are tests for all the items which extend source
 */
public class SourceTests {

	/**
	 * Creates a player and a tree, then performs the tree's action. Test
	 * ensures player has 1 item in pocket after action
	 */
	@Test
	public void treeTest_1() {
		Player player = new Player("Chris", new Point(1, 1), new Point(0, 1));
		Point worldLocation = new Point(1, 1);
		Point tileLocation = new Point(1, 1);
		Tree tree = new Tree(worldLocation, tileLocation);
		try {
			tree.performAction(player);// player takes wood from tree
		} catch (GameException e) {
			fail(e.getMessage());
		}
		assertEquals(player.getPocket().size(), 1); // checks that player has 1
													// item in pocket
	}

	/**
	 * Creates a player and a tree, then performs the tree's action. Test
	 * ensures player has 1 wood in pocket after action
	 */
	@Test
	public void treeTest_2() {
		Player player = new Player("Chris", new Point(1, 1), new Point(0, 1));
		Point worldLocation = new Point(1, 1);
		Point tileLocation = new Point(1, 1);
		Tree tree = new Tree(worldLocation, tileLocation);
		try {
			tree.performAction(player);// player takes wood from tree
		} catch (GameException e) {
			fail(e.getMessage());
		}
		boolean isWood = player.getPocket().get(0) instanceof Wood;
		assertEquals(isWood, true); // checks that player has 1 wood in pocket
	}

}
