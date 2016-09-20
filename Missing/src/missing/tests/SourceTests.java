/* File: SourceTests.java
 * 
 * Authors: 
 * Jian Wei Chong   300352789
 * Chris Rabe		300334207
 * 
 * Date 			Author				Changes
 * 7 Sep 16			Jian Wei			Added tests 1 and 2 for trees
 * 16 Sep 16		Chris Rabe			Updated tests to match the new Tree constructor
 * 19 Sep 16		Jian Wei			Added treeTest_3 and RockTests 1&2
 * 20 Sep 16		Jian Wei			Added treeTest_4 , treeTest_5 && rockTest 3&4
 * */
package missing.tests;

import static org.junit.Assert.*;

import java.awt.Point;
import java.util.ArrayList;

import org.junit.Test;

import missing.game.characters.Player;
import missing.game.items.Item;
import missing.game.items.movable.Food;
import missing.game.items.movable.Food.FoodType;
import missing.game.items.movable.Tool.ToolType;
import missing.game.items.movable.Resource;
import missing.game.items.movable.Stone;
import missing.game.items.movable.Tool;
import missing.game.items.movable.Wood;
import missing.game.items.nonmovable.Rock;
import missing.game.items.nonmovable.Tree;
import missing.helper.GameException;

/**
 * these are tests for all the items which extend source
 */
public class SourceTests {

	/**
	 * Creates a player and a tree, then performs the tree's action. Test
	 * ensures player has 1, or 2 items(if the tree drops an apple) in pocket
	 * after action
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
		boolean isTrue = (player.getPocket().size() == 1) || player.getPocket().size() == 2;
		assertTrue(isTrue); // checks that player has 1
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
		assertTrue(isWood); // checks that player has 1 wood in pocket
	}

	/**
	 * Asserts that if the player has 2 items in his pocket, the first is Wood,
	 * and the second is an Apple
	 */
	@Test
	public void treeTest_3() {
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

		if (player.getPocket().size() == 2) {
			Item item = player.getPocket().get(1);
			assertTrue(item instanceof Food);
			assertTrue(((Food) item).getFoodType() == FoodType.APPLE);
		}
		assertEquals(isWood, true); // checks that player has 1 wood in pocket

	}
	
	
	/**
	 * Tests that when a player breaks the tree, he gets 1 wood*/
	@Test
	public void treeTest_4(){
		Player player = new Player("Chris", new Point(1, 1), new Point(0, 1));
		Point worldLocation = new Point(1, 1);
		Point tileLocation = new Point(1, 1);
		Tree tree = new Tree(worldLocation, tileLocation);
		try {
			tree.performAction(player);// player takes wood from tree
		} catch (GameException e) {
			fail(e.getMessage());
		}
		boolean has1Wood = false;
		if(player.getPocket().get(0) instanceof Wood){
			has1Wood = player.getPocket().get(0).getAmount() == 1;
		}
		assertTrue(has1Wood); // checks that player has 1 wood in pocket
	}
	
	/**
	 * Creates an axe, player and tree. Adds the axe to the players pocekt, then cuts down the tree
	 * Tests that when a player breaks the tree with an axe, he gets 3 wood*/
	@Test
	public void treeTest_5(){
		Player player = new Player("Chris", new Point(1, 1), new Point(0, 1));
		ArrayList<Resource> resources = new ArrayList<Resource>();
		Point worldLocation = new Point(1, 1);
		Point tileLocation = new Point(1, 1);
		Tree tree = new Tree(worldLocation, tileLocation);
		for(int i=0; i<2; i++){
			resources.add(new Wood(worldLocation,tileLocation)); //adds 2 wood
		}
		for(int i=0; i<3; i++){
			resources.add(new Stone(worldLocation,tileLocation)); //adds 3 stone
		}
		try {
			Tool axe = new Tool(ToolType.AXE, resources, worldLocation, tileLocation);
			player.addToPocket(axe);
			tree.performAction(player);// player takes wood from tree
		} catch (GameException e) {
			fail(e.getMessage());
		}
		boolean has3Wood = false;
		if(player.getPocket().get(1) instanceof Wood){
			has3Wood = player.getPocket().get(1).getAmount() == 3;
		}
		assertTrue(has3Wood); // checks that player has 1 wood in pocket
	}

	/**
	 * Creates a rock and performs its action, then asserts that the player has
	 * 1 item in its pocket
	 */
	@Test
	public void rockTest_1() {
		Player player = new Player("Chris", new Point(1, 1), new Point(0, 1));
		Point worldLocation = new Point(1, 1);
		Point tileLocation = new Point(1, 1);
		Rock rock = new Rock(worldLocation, tileLocation);

		try {
			rock.performAction(player);// player takes Stone from that rock
		} catch (GameException e) {
			fail(e.getMessage());
		}
		boolean isTrue = (player.getPocket().size() == 1);
		assertTrue(isTrue); // checks that player has 1
							// item in pocket
	}

	/**
	 * Creates a rock and performs its action, then asserts that the player has
	 * 1 Stone in its pocket
	 */
	@Test
	public void rockTest_2() {
		Player player = new Player("Chris", new Point(1, 1), new Point(0, 1));
		Point worldLocation = new Point(1, 1);
		Point tileLocation = new Point(1, 1);
		Rock rock = new Rock(worldLocation, tileLocation);

		try {
			rock.performAction(player);// player takes Stone from that rock
		} catch (GameException e) {
			fail(e.getMessage());
		}
		boolean isTrue = (player.getPocket().get(0) instanceof Stone);
		assertTrue(isTrue); // checks that player has 1
							// item in pocket
	}
	
	
	/**
	 * Tests that when a player breaks the Rock, he gets 1 stone*/
	@Test
	public void rockTest_3(){
		Player player = new Player("Chris", new Point(1, 1), new Point(0, 1));
		Point worldLocation = new Point(1, 1);
		Point tileLocation = new Point(1, 1);
		Rock rock = new Rock(worldLocation, tileLocation);
		try {
			rock.performAction(player);// player takes wood from tree
		} catch (GameException e) {
			fail(e.getMessage());
		}
		boolean has1Stone = false;
		if(player.getPocket().get(0) instanceof Stone){
			has1Stone = player.getPocket().get(0).getAmount() == 1;
		}
		assertTrue(has1Stone); // checks that player has 1 wood in pocket
	}
	
	/**
	 * Creates an pickaxe, player and rock. Adds the pickaxe to the players pocket, then hits the rock
	 * Tests that when a player hits the rock with a pickaxe, he gets 5 stone*/
	@Test
	public void rockTest_4(){
		Player player = new Player("Chris", new Point(1, 1), new Point(0, 1));
		ArrayList<Resource> resources = new ArrayList<Resource>();
		Point worldLocation = new Point(1, 1);
		Point tileLocation = new Point(1, 1);
		Rock rock = new Rock(worldLocation, tileLocation);
		for(int i=0; i<2; i++){
			resources.add(new Wood(worldLocation,tileLocation)); //adds 2 wood
		}
		for(int i=0; i<3; i++){
			resources.add(new Stone(worldLocation,tileLocation)); //adds 3 stone
		}
		try {
			Tool pickaxe = new Tool(ToolType.PICKAXE, resources, worldLocation, tileLocation);
			player.addToPocket(pickaxe);
			rock.performAction(player);// player takes wood from tree
		} catch (GameException e) {
			fail(e.getMessage());
		}
		boolean has5Stone = false;
		if(player.getPocket().get(1) instanceof Stone){
			has5Stone = player.getPocket().get(1).getAmount() == 5;
		}
		assertTrue(has5Stone); // checks that player has 1 wood in pocket
	}


}
