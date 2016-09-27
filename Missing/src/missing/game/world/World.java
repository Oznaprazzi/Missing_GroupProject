/*	File: World.java
 * 	Author:
 * 	Chris Rabe		300334207
 *  Edward Kelly	300334192
 * 
 * 	Date			Author				changes
 * 	8 Sep 16		Chris Rabe			created world class
 *  8 Sep 16		Edward Kelly		created constructor, setupWorld, setLocation
 *  13 Sep 16		Chris Rabe			removed some non-related fields to the world node
 *  13 Sep 16		Chris Rabe			implemented the toString() method
 *  13 Sep 16		Chris Rabe			added a getter for the worldNodes field
 *  18 Sep 16		Chris Rabe			implemented item distribution method
 */

package missing.game.world;

import java.awt.Point;
import java.io.Serializable;

import missing.datastorage.initialisers.WorldInitialiser;
import missing.game.world.nodes.WorldNode;
import missing.game.world.nodes.WorldTile;
import missing.game.world.nodes.WorldTile.TileObject;
import missing.helper.GameException;

/**
 * This class contains all the logic for moving around the players within the
 * game world as well as performing appropriate checking if item and players
 * collide.
 */
public class World implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6179281677673695416L;
	public static final int WORLD_WIDTH = 4;
	public static final int WORLD_HEIGHT = 4;

	/** The WorldNodes that make up the World */
	private WorldNode[][] worldNodes;

	public World() throws GameException {
		setupWorld();
	}

	// Getters

	public WorldNode[][] getWorldNodes() {
		return worldNodes;
	}

	// Methods

	public void distributeToNode(Point worldLocation, Point tileLocation, TileObject item) {
		worldNodes[worldLocation.y][worldLocation.x].distributeToTile(tileLocation, item);
	}

	// Helper Methods...

	/**
	 * Creates the world, places items and players
	 * 
	 * @throws GameException
	 */
	private void setupWorld() throws GameException {
		// Create worldNodes
		worldNodes = WorldInitialiser.createWorldNodes();
		worldNodes = WorldInitialiser.linkNodes(worldNodes);
	}

	/**
	 * Reads the world row by row
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		int tileSize = 10;
		// Move down the world nodes
		for (int nodesRow = 0; nodesRow < worldNodes.length; nodesRow++) {
			// Move down the tile nodes
			for (int tileRow = 0; tileRow < tileSize; tileRow++) {
				// Move node column to the right
				for (int nodesCol = 0; nodesCol < worldNodes[nodesRow].length; nodesCol++) {
					WorldTile[][] tiles = worldNodes[nodesRow][nodesCol].getWorldTiles();
					// Move tile column to the right
					for (int tileCol = 0; tileCol < tileSize; tileCol++) {
						sb.append(tiles[tileRow][tileCol].toString());
					}
				}
				sb.append("\n");
			}
		}
		return sb.toString();
	}

}
