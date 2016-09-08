/*	File: WorldInitialiser.java
 * 	Author:
 * 	Edward Kelly		300334192
 * 
 * 	Date			Author				changes
 * 	8 Sep 16		Edward Kelly		created class
 *  8 Sep 16		Edward Kelly		created unimplemented Loading World and Loading Items methods
 *  8 Sep 16		Edward Kelly		implemented Loading World methods
 */
package missing.helper;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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
		String filePath = "src/missing.gdatastorage.world.node/";
		WorldNode[][] worldNodes = new WorldNode[World.WORLD_WIDTH][World.WORLD_HEIGHT];
		for (int x=0; x < World.WORLD_WIDTH; x++){
			for (int y=0; y < World.WORLD_HEIGHT; y++){
				WorldNode worldNode = parseWorldNode(new File(filePath + x+","+y+".txt"), x, y);
				worldNodes[x][y] = worldNode;
			}
		}
		return worldNodes;
	}
	
	/**
	 * Reads data for one worldNode from a file and returns a new WorldNode
	 * @param file file containing data for WorldNode
	 * @param x x position of WorldNode in World
	 * @param y y position of WorldNode in World
	 * @return
	 */
	private static WorldNode parseWorldNode(File file, int x, int y){
		WorldTile[][] worldTiles = new WorldTile[WorldNode.TILE_SIZE][WorldNode.TILE_SIZE];
		BufferedReader reader = null;
		try{
			reader = new BufferedReader(new FileReader(file));
			reader.readLine(); // read header
			String line;
			WorldTile currentTile;
			//read lines
			while((line = reader.readLine()) != null){
				currentTile = parseWorldTile(line); // get new WorldTile from lone data
				// add currentTile to correct position in WorldTiles for this WorldNode
				worldTiles[currentTile.getNodeLocation().x][currentTile.getNodeLocation().y] = currentTile;
			}
			reader.close();
		} catch(IOException e){
			e.printStackTrace();
		}
		return new WorldNode(new Point(x,y), worldTiles);
	}
	
	/**
	 * Reads data for one WorldTile from a line in a file and returns a new WorldTile
	 * @param line line to be read, line holds data for one tile
	 * @return
	 */
	private static WorldTile parseWorldTile(String line){
		String[] data = line.split("\\t"); // split at tab
		WorldTile.TileType tileType = null;	
		// get tile type
		for (WorldTile.TileType type : WorldTile.TileType.values()){
			if (type.toString().equals(data[0])){
				tileType = type;
			}
		}
		if (tileType == null){
			System.out.println("Invalid file, invalid tile type in file "); // for testing
		}
		// get tile x and y
		int tileX = Integer.parseInt(data[1]);
		int tileY = Integer.parseInt(data[2]);
		return new WorldTile(tileType, new Point(tileX, tileY));
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
