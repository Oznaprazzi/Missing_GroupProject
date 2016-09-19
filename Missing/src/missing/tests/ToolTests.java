/* File: ToolTest.java
 * 
 * Authors			ID
 * Jian Wei Chong	300352789
 * 
 * Date			Author			Modification
 * 19/9/16		Jian Wei		Created initial Axe and PickAxe tests (test1 and invalid test 1)
 * */
package missing.tests;

import static org.junit.Assert.*;

import java.awt.Point;
import java.util.ArrayList;

import org.junit.Test;

import missing.game.items.movable.Resource;
import missing.game.items.movable.Stone;
import missing.game.items.movable.Tool;
import missing.game.items.movable.Tool.ToolType;
import missing.game.items.movable.Wood;
import missing.game.items.nonmovable.Source;
import missing.helper.GameException;

public class ToolTests {

	
	/**
	 * creates an axe, by getting 2 wood and 3 stone*/
	@Test
	public void axeTest_1(){
		ArrayList<Resource> resources = new ArrayList<Resource>();
		Point worldLocation = new Point(1, 1);
		Point tileLocation = new Point(1, 1);
		for(int i=0; i<2; i++){
			resources.add(new Wood(worldLocation,tileLocation)); //adds 2 wood
		}
		for(int i=0; i<3; i++){
			resources.add(new Stone(worldLocation,tileLocation)); //adds 3 stone
		}
		
		boolean pass = true;
		try{
			Tool tool = new Tool(ToolType.AXE, resources, worldLocation, tileLocation);
		}catch(GameException e){
			pass = false;
		}
		assertTrue(pass);
	}
	
	/**
	 *tries to create an axe, but has given 1 wood instead of 2, this should throw a game exception*/
	@Test
	public void axe_Invalid_Test_1(){
		ArrayList<Resource> resources = new ArrayList<Resource>();
		Point worldLocation = new Point(1, 1);
		Point tileLocation = new Point(1, 1);
		for(int i=0; i<1; i++){
			resources.add(new Wood(worldLocation,tileLocation)); //adds 1 wood
		}
		for(int i=0; i<3; i++){
			resources.add(new Stone(worldLocation,tileLocation)); //adds 3 stone
		}
		
		boolean fail = false;
		try{
			Tool tool = new Tool(ToolType.AXE, resources, worldLocation, tileLocation);
		}catch(GameException e){
			fail = true;
		}
		assertTrue(fail);
	}
	
	
	/**
	 * creates an pickaxe, by getting 2 wood and 3 stone*/
	@Test
	public void pickaxeTest_1(){
		ArrayList<Resource> resources = new ArrayList<Resource>();
		Point worldLocation = new Point(1, 1);
		Point tileLocation = new Point(1, 1);
		for(int i=0; i<2; i++){
			resources.add(new Wood(worldLocation,tileLocation)); //adds 2 wood
		}
		for(int i=0; i<3; i++){
			resources.add(new Stone(worldLocation,tileLocation)); //adds 3 stone
		}
		
		boolean pass = true;
		try{
			Tool tool = new Tool(ToolType.PICKAXE, resources, worldLocation, tileLocation);
		}catch(GameException e){
			pass = false;
		}
		assertTrue(pass);
	}
	
	/**
	 *tries to create a pickaxe, but has given 2 stone instead of 3, this should throw a game exception*/
	@Test
	public void pickaxe_Invalid_Test_1(){
		ArrayList<Resource> resources = new ArrayList<Resource>();
		Point worldLocation = new Point(1, 1);
		Point tileLocation = new Point(1, 1);
		for(int i=0; i<2; i++){
			resources.add(new Wood(worldLocation,tileLocation)); //adds 2 wood
		}
		for(int i=0; i<2; i++){
			resources.add(new Stone(worldLocation,tileLocation)); //adds 2 stone
		}
		
		boolean fail = false;
		try{
			Tool tool = new Tool(ToolType.AXE, resources, worldLocation, tileLocation);
		}catch(GameException e){
			fail = true;
		}
		assertTrue(fail);
	}
}
