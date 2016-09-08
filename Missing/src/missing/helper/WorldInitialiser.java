/*	File: WorldInitialiser.java
 * 	Author:
 * 	Edward Kelly		300334192
 * 
 * 	Date			Author				changes
 * 	8 Sep 16		Edward Kelly		created class
 *  8 Sep 16		Edward Kelly		added Loading World and Loading Items methods
 */
package missing.helper;

import java.util.ArrayList;
import java.util.List;

import missing.game.items.Item;
import missing.game.world.World;
import missing.game.world.nodes.WorldNode;
import missing.game.world.nodes.WorldTile;

/**
 * Helper class containing static methods used to help with creation of the world
 *
 */
public class WorldInitialiser {
	
	/* Loading World */
	/**
	 * Creates all worldNode objects and returns as 2D array
	 * Reads data from .txt file
	 * @return
	 */
	public static WorldNode[][] loadWorldNodes(){
		WorldNode[][] worldNodes = new WorldNode[World.WORLD_WIDTH][World.WORLD_HEIGHT];
		//TODO: implement
		
		return worldNodes;
	}
	
	/**
	 * Reads data for one worldNode from a file and returns a new WorldNode
	 * @return
	 */
	private static WorldNode parseWorldNode(){
		//TODO: implement
		return null;
	}
	
	/**
	 * Reads data for one WorldTile from a file and returns a new WorldTile
	 * @param line line to be read, line holds data for one tile
	 * @return
	 */
	private static WorldTile parseWorldTile(String line){
		//TODO: implement
		return null;
	}
	
	/* Loading Items */
	/**
	 * Initialises list of Item objects be added to the world.
	 * @return List of Item objects to put in the world
	 */
	public static List<Item> loadItems(){
		List<Item> items = new ArrayList<Item>();
		//TODO: parse one line of file at a time using parseItem, add each item to list, return list
		return items;
	}
	
	/**
	 * Reads info for one item and returns a new Item
	 * @return New Item based on file data
	 */
	private static Item parseItem(){
		//TODO: implement
		return null;
	}
	
}
