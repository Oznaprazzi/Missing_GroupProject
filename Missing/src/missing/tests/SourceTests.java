/* File: SourceTests.java
 * 
 * Authors: 
 * Jian Wei Chong    300352789
 * 
 * Date 			Author				Changes
 * 7 Sep 16			Jian Wei			Added tests 1 and 2 for trees
 * 
 */
package missing.tests;

import static org.junit.Assert.assertEquals;

import java.awt.Point;

import org.junit.Test;

import missing.game.characters.Player;
import missing.game.items.movable.Resource;
import missing.game.items.movable.Wood;
import missing.game.items.nonmovable.Tree;

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
		Player player = new Player("Chris");
		Point worldLocation = new Point(1, 1);
		Point tileLocation = new Point(1, 1);
		Resource wood = new Wood(worldLocation, tileLocation, 5, 1);
		Tree tree = new Tree(worldLocation, tileLocation, wood);
		tree.performAction(player); // player takes wood from tree
		assertEquals(player.getPocket().size(), 1); // checks that player has 1
													// item in pocket
	}

	/**
	 * Creates a player and a tree, then performs the tree's action. Test
	 * ensures player has 1 wood in pocket after action
	 */
	@Test
	public void treeTest_2() {
		Player player = new Player("Chris");
		Point worldLocation = new Point(1, 1);
		Point tileLocation = new Point(1, 1);
		Resource wood = new Wood(worldLocation, tileLocation, 5, 1);
		Tree tree = new Tree(worldLocation, tileLocation, wood);
		tree.performAction(player); // player takes wood from tree
		boolean isWood = player.getPocket().get(0) instanceof Wood;
		assertEquals(isWood, true); // checks that player has 1 wood in pocket
	}

}
