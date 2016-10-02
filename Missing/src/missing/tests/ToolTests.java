/*File: ToolTests.java
 * 
 * Authors			ID
 * Jian Wei Chong	300352789
 * 
 * Date				Author			Modification
 * 3/10/16			Jian Wei		Created class, test_1() and invalid_test_1() for axe, pickaxe, shovel, fishing rod
 * 
 * */
package missing.tests;

import static org.junit.Assert.assertTrue;

import java.awt.Point;

import org.junit.Test;

import missing.game.characters.Player;
import missing.game.items.movable.Craftable;
import missing.game.items.movable.Dirt;
import missing.game.items.movable.Stone;
import missing.game.items.movable.Tool;
import missing.game.items.movable.Tool.ToolType;
import missing.game.items.movable.Wood;
import missing.game.items.nonmovable.Tree;
import missing.helper.GameException;

public class ToolTests {

	
	/**
	 * creates a pickaxe, and asserts that the amount of each resource has been reduced correctly*/
	@Test
	public void pickaxeTest_1(){
		Player player = new Player(0, "Chris", new Point(1, 1), new Point(0, 1));
		Point worldLocation = new Point(1, 1);
		Point tileLocation = new Point(1, 1);
		Stone stone = new Stone(worldLocation, tileLocation);
		Wood wood = new Wood(worldLocation, tileLocation);
		int wAmt = wood.getAmount();
		int sAmt = stone.getAmount();
		try{
			player.addToPocket(wood);
			player.addToPocket(stone);
			Tool pickAxe = Craftable.createTool(ToolType.PICKAXE, player);
			assertTrue(stone.getAmount() == (sAmt - 3)); //asserts stone has lost 3
			assertTrue(wood.getAmount() == (wAmt - 2)); //asserts 2 wood was used
		}catch(GameException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * tries to create a pickaxe, but doesn't have the necessary resources, a gameexception 
	 * should be throw*/
	@Test
	public void pickaxe_Invalid_Test_1(){
		Player player = new Player(0, "Chris", new Point(1, 1), new Point(0, 1));
		Point worldLocation = new Point(1, 1);
		Point tileLocation = new Point(1, 1);
		Stone stone = new Stone(worldLocation, tileLocation);
		Wood wood = new Wood(worldLocation, tileLocation);
		wood.setAmount(1);
		boolean notEnoughWood = false;
		try{
			player.addToPocket(wood);
			player.addToPocket(stone);
			Tool pickAxe = Craftable.createTool(ToolType.PICKAXE, player);
		}catch(GameException e){
			notEnoughWood = true;
		}
		assertTrue(notEnoughWood);
	}
	
	/**
	 * creates an axe, and asserts that the amount of each resource has been reduced correctly*/
	@Test
	public void axeTest_1(){
		Player player = new Player(0, "Chris", new Point(1, 1), new Point(0, 1));
		Point worldLocation = new Point(1, 1);
		Point tileLocation = new Point(1, 1);
		Stone stone = new Stone(worldLocation, tileLocation);
		Wood wood = new Wood(worldLocation, tileLocation);
		int wAmt = wood.getAmount();
		int sAmt = stone.getAmount();
		try{
			player.addToPocket(wood);
			player.addToPocket(stone);
			Tool pickAxe = Craftable.createTool(ToolType.AXE, player);
			assertTrue(stone.getAmount() == (sAmt - 3)); //asserts stone has lost 3
			assertTrue(wood.getAmount() == (wAmt - 2)); //asserts 2 wood was used
		}catch(GameException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * tries to create an axe, but doesn't have the necessary resources, a gameexception 
	 * should be throw*/
	@Test
	public void axe_Invalid_Test_1(){
		Player player = new Player(0, "Chris", new Point(1, 1), new Point(0, 1));
		Point worldLocation = new Point(1, 1);
		Point tileLocation = new Point(1, 1);
		Stone stone = new Stone(worldLocation, tileLocation);
		Wood wood = new Wood(worldLocation, tileLocation);
		wood.setAmount(1);
		boolean notEnoughWood = false;
		try{
			player.addToPocket(wood);
			player.addToPocket(stone);
			Tool Axe = Craftable.createTool(ToolType.AXE, player);
		}catch(GameException e){
			notEnoughWood = true;
		}
		assertTrue(notEnoughWood);
	}
	
	/**
	 * creates a shovel, and asserts that the amount of each resource has been reduced correctly*/
	@Test
	public void shovelTest_1(){
		Player player = new Player(0, "Chris", new Point(1, 1), new Point(0, 1));
		Point worldLocation = new Point(1, 1);
		Point tileLocation = new Point(1, 1);
		Stone stone = new Stone(worldLocation, tileLocation);
		Wood wood = new Wood(worldLocation, tileLocation);
		int wAmt = wood.getAmount();
		int sAmt = stone.getAmount();
		try{
			player.addToPocket(wood);
			player.addToPocket(stone);
			Tool shovel = Craftable.createTool(ToolType.SHOVEL, player);
			assertTrue(stone.getAmount() == (sAmt - 1)); //asserts stone has lost 1
			assertTrue(wood.getAmount() == (wAmt - 2)); //asserts 2 wood was used
		}catch(GameException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * tries to create a shovel, but doesn't have the necessary resources, a gameexception 
	 * should be throw*/
	@Test
	public void shovel_Invalid_Test_1(){
		Player player = new Player(0, "Chris", new Point(1, 1), new Point(0, 1));
		Point worldLocation = new Point(1, 1);
		Point tileLocation = new Point(1, 1);
		Stone stone = new Stone(worldLocation, tileLocation);
		Wood wood = new Wood(worldLocation, tileLocation);
		wood.setAmount(1);
		boolean notEnoughWood = false;
		try{
			player.addToPocket(wood);
			player.addToPocket(stone);
			Tool shovel = Craftable.createTool(ToolType.SHOVEL, player);
		}catch(GameException e){
			notEnoughWood = true;
		}
		assertTrue(notEnoughWood);
	}
	
	/**
	 * creates a fishing rod, and asserts that the amount of each resource has been reduced correctly*/
	@Test
	public void fishingRodTest_1(){
		Player player = new Player(0, "Chris", new Point(1, 1), new Point(0, 1));
		Point worldLocation = new Point(1, 1);
		Point tileLocation = new Point(1, 1);
		Dirt dirt = new Dirt(worldLocation, tileLocation);
		Wood wood = new Wood(worldLocation, tileLocation);
		int wAmt = wood.getAmount();
		int dAmt = dirt.getAmount();
		try{
			player.addToPocket(wood);
			player.addToPocket(dirt);
			Tool rod = Craftable.createTool(ToolType.FISHINGROD, player);
			assertTrue(dirt.getAmount() == (dAmt - 3)); //asserts 3 dirt was used
			assertTrue(wood.getAmount() == (wAmt - 2)); //asserts 2 wood was used
		}catch(GameException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * tries to create a shovel, but doesn't have the necessary resources, a gameexception 
	 * should be throw*/
	@Test
	public void fishingRod_Invalid_Test_1(){
		Player player = new Player(0, "Chris", new Point(1, 1), new Point(0, 1));
		Point worldLocation = new Point(1, 1);
		Point tileLocation = new Point(1, 1);
		Dirt dirt = new Dirt(worldLocation, tileLocation);
		Wood wood = new Wood(worldLocation, tileLocation);
		wood.setAmount(1);
		boolean notEnoughWood = false;
		try{
			player.addToPocket(wood);
			player.addToPocket(dirt);
			Tool rod = Craftable.createTool(ToolType.FISHINGROD, player);
		}catch(GameException e){
			notEnoughWood = true;
		}
		assertTrue(notEnoughWood);
	}
}
