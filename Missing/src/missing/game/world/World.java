/*	File: World.java
 * 	Author:
 * 	Chris Rabe		300334207
 *  Edward Kelly	300334192
 * 
 * 	Date			Author				changes
 * 	8 Sep 16		Chris Rabe			created world class
 *  8 Sep 16		Edward Kelly		created constructor, setupWorld, setLocation
 */

package missing.game.world;
import java.awt.Point;
import java.io.InputStream;
import java.util.List;

import missing.game.characters.Player;
import missing.game.items.Item;
import missing.game.world.nodes.WorldNode;
import missing.game.world.nodes.WorldTile.TileObject;
import missing.helper.GameException;
import missing.helper.WorldInitialiser;

/**
 * This class contains all the logic for moving around the players within the
 * game world as well as performing appropriate checking if item and players
 * collide.
 */
public class World {
	public static final int WORLD_WIDTH = 4;
	public static final int WORLD_HEIGHT = 4;
	public static final String worldNodeFilePath = "src/missing/datastorage/world/node/";
	
	/** The WorldNodes that make up the World */
	private WorldNode[][] worldNodes;
	/** Reference to the players in the world */
	private List<Player> players;
	
	public World(List<Player> players) throws GameException{
		this.players = players;
		setupWorld();
	}

	/**
	 * Creates the world, places items and players
	 * @throws GameException 
	 */
	private void setupWorld() throws GameException{		
		//Create worldNodes
		worldNodes = WorldInitialiser.createWorldNodes();
		
		// add items to world (loaded from .xml file)
		List<Item> items = WorldInitialiser.loadItems();
		for (Item item : items){
			setLocation(item, item.getWorldLocation(), item.getTileLocation());
		}
		
		// add players to world
		for (Player p : players){
			setLocation(p, p.getWorldLocation(), p.getTileLocation());
		}
		
	}
	
	/**
	 * Places an object at a given location. Used for placing items and placing/moving players
	 * Can also pass a null object to set a square to blank
	 * @param object object to be placed
	 * @param destWorldNode WorldNode destination
	 * @param destTile WorldTile destination
	 */
	public void setLocation(TileObject object, Point destWorldNode, Point destTile){
		//TODO: need to implement setTileObject method in WorldNode
		//worldNodes[destWorldNode.x][destWorldNode.y].setTileObject(object, destTile);
	}	
	
}
