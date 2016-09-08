/*	File: WorldInitialiser.java
 * 	Author:
 * 	Edward Kelly		300334192
 * 
 * 	Date			Author				changes
 * 	8 Sep 16		Edward Kelly		created class
 */
package missing.helper;

import java.util.ArrayList;
import java.util.List;

import missing.game.items.Item;

/**
 * Helper class containing static methods used to help with creation of the world
 *
 */
public class WorldInitialiser {
	
	/**
	 * Initialises list of Item objects be added to the world.
	 * @return List of Item objects to put in the world
	 */
	public static List<Item> initItems(){
		List<Item> items = new ArrayList<Item>();
		//TODO: parse one line of file at a time using parseItem, add each item to list, return list
		return items;
	}
	
	/**
	 * Reads info for one item and returns a new Item
	 * @return New Item based on file data
	 */
	public static Item parseItem(){
		//TODO: implement
		return null;
	}
}
