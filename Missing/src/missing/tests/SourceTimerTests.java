/*File: SourceTimerTests.java
 * 
 * Authors			ID
 * Jian Wei Chong	300352789
 * 
 * Date				Author				Modification
 * 28/9/16			Jian Wei			Created rockTest1
 * 29/9/16			Jian Wei
 * */

package missing.tests;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.awt.Point;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import missing.game.characters.Player;
import missing.game.items.movable.Dirt;
import missing.game.items.movable.Resource;
import missing.game.items.movable.Stone;
import missing.game.items.movable.Tool;
import missing.game.items.movable.Wood;
import missing.game.items.movable.Tool.ToolType;
import missing.game.items.nonmovable.Rock;
import missing.game.items.nonmovable.Soil;
import missing.game.items.nonmovable.Tree;
import missing.helper.GameException;
import missing.helper.SignalException;

public class SourceTimerTests {

	/**
	 * Creates an pickaxe, player and rock. Adds the pickaxe to the players
	 * pocket, then hits the rock Tests that when a player hits the rock with a
	 * pickaxe, he gets 5 stone
	 * 
	 * THEN, it delays itself for a little while, so that the rock thread can
	 * catch up, then it asserts that the rock resource has been replenished
	 */
	@Test
	public void rockTest_1() {
		Player player = new Player(0, "Chris", new Point(1, 1), new Point(0, 1));
		ArrayList<Resource> resources = new ArrayList<Resource>();
		Point worldLocation = new Point(1, 1);
		Point tileLocation = new Point(1, 1);
		Rock rock = new Rock(worldLocation, tileLocation);
		for (int i = 0; i < 2; i++) {
			resources.add(new Wood(worldLocation, tileLocation)); // adds 2 wood
		}
		for (int i = 0; i < 3; i++) {
			resources.add(new Stone(worldLocation, tileLocation)); // adds 3
																	// stone
		}
		try {
			Tool pickaxe = new Tool(worldLocation, tileLocation, ToolType.PICKAXE);
			player.addToPocket(pickaxe);
			rock.performAction(player);// player takes wood from tree
		} catch (GameException e) {
			fail(e.getMessage());
		} catch (SignalException e) {
		}
		boolean has5Stone = false;
		if (player.getPocket().getItems().get(1) instanceof Stone) {
			has5Stone = player.getPocket().getItems().get(1).getAmount() == 5;
		}
		assertTrue(has5Stone); // checks that player has 1 wood in pocket

		try {
			TimeUnit.SECONDS.sleep(31);
			assertTrue(rock.getResource().getAmount() == 10); // asserts that
																// the rock has
																// replenished
																// its resource
			System.out.println("It works!!");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Creates a shovel, player and soil. Adds the shovel to the players pocket,
	 * then hits the rock Tests that when a player digs the soil with a shovel,
	 * he gets 5 dirt
	 * 
	 * THEN, it delays itself for a little while, so that the soil thread can
	 * catch up, then it asserts that the soil resource has been replenished
	 */
	@Test
	public void soilTest_1() {
		Player player = new Player(0, "Chris", new Point(1, 1), new Point(0, 1));
		ArrayList<Resource> resources = new ArrayList<Resource>();
		Point worldLocation = new Point(1, 1);
		Point tileLocation = new Point(1, 1);
		Soil soil = new Soil(worldLocation, tileLocation);
		for (int i = 0; i < 2; i++) {
			resources.add(new Wood(worldLocation, tileLocation)); // adds 2 wood
		}
		for (int i = 0; i < 1; i++) {
			resources.add(new Stone(worldLocation, tileLocation)); // adds 1
																	// stone
		}
		try {
			Tool shovel = new Tool(worldLocation, tileLocation, ToolType.SHOVEL);
			player.addToPocket(shovel);
			soil.performAction(player);// player takes wood from tree
		} catch (GameException e) {
			fail(e.getMessage());
		} catch (SignalException e) {
		}
		boolean has5dirt = false;
		if (player.getPocket().getItems().get(1) instanceof Dirt) {
			has5dirt = player.getPocket().getItems().get(1).getAmount() == 5;
		}
		assertTrue(has5dirt); // checks that player has 1 wood in pocket

		try {
			TimeUnit.SECONDS.sleep(31);
			assertTrue(soil.getResource().getAmount() == 10); // asserts that
																// the soil has
																// replenished
																// its resource
			System.out.println("It works!!");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Creates an pickaxe, player and rock. Adds the pickaxe to the players
	 * pocket, then hits the rock Tests that when a player hits the rock with a
	 * pickaxe, he gets 5 stone
	 * 
	 * THEN, it delays itself for a little while, so that the rock thread can
	 * catch up, then it asserts that the rock resource has been replenished
	 */
	@Test
	public void treeTest_1() {
		Player player = new Player(0, "Chris", new Point(1, 1), new Point(0, 1));
		ArrayList<Resource> resources = new ArrayList<Resource>();
		Point worldLocation = new Point(1, 1);
		Point tileLocation = new Point(1, 1);
		Tree tree = new Tree(worldLocation, tileLocation);
		for (int i = 0; i < 2; i++) {
			resources.add(new Wood(worldLocation, tileLocation)); // adds 2 wood
		}
		for (int i = 0; i < 3; i++) {
			resources.add(new Stone(worldLocation, tileLocation)); // adds 3
																	// stone
		}
		try {
			Tool axe = new Tool(worldLocation, tileLocation, ToolType.AXE);
			player.addToPocket(axe);
			tree.performAction(player);// player takes wood from tree
		} catch (GameException e) {
			fail(e.getMessage());
		} catch (SignalException e) {
		}
		boolean has5wood = false;
		if (player.getPocket().getItems().get(1) instanceof Wood) {
			has5wood = player.getPocket().getItems().get(1).getAmount() == 3;
		}
		assertTrue(has5wood); // checks that player has 1 wood in pocket

		try {
			TimeUnit.SECONDS.sleep(31);
			assertTrue(tree.getResource().getAmount() == 10); // asserts that
																// the rock has
																// replenished
																// its resource
			System.out.println("It works!!");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
