/*	File: WorldNode.java
 * 	Author
 * 	Chris Rabe		300334207
 *  Linus Go		300345571
 * 	Date			Author				Changes
 * 	8 Sep 16		Chris Rabe			create worldNode class
 * 	8 Sep 16		Linus Go			filled the class and added fields.
 * 	8 Sep 16		Chris Rabe			removed some fields
 */

package missing.game.world.nodes;

import java.awt.Point;
import java.util.List;

import missing.game.items.Item;

/**
 * World node contains various types of world tiles. It should also contain a
 * list of movable and nonMovable objects because this information will be
 * needed when managing server.
 */
public class WorldNode {
	/** Represents the width and height of the world */
	private final int TILE_SIZE = 25;
	/** Represents the maximum number of neighbours of this node */
	private final int NEIGHBOURS = 4;

	/** The location of this World Node on the world. */
	private Point gameLocation;
	/** The World Tile 2D array. Each world node has these. */
	private WorldTile[][] worldTiles;

	/**
	 * Represents the neighbouring nodes.
	 * 
	 * <pre>
	 * 0 : north, 1 : south, 2 : east, 3 : west
	 * </pre>
	 */
	private WorldNode[] neighbours;

	private List<Item> items; // TODO Use these to initialise world

	public WorldNode(Point location) {
		this.gameLocation = location;
		worldTiles = new WorldTile[TILE_SIZE][TILE_SIZE];
		this.neighbours = new WorldNode[NEIGHBOURS];
	}

	public WorldNode(Point location, WorldNode north, WorldNode south, WorldNode east, WorldNode west) {
		this(location);
		neighbours[0] = north;
		neighbours[1] = south;
		neighbours[2] = east;
		neighbours[3] = west;
	}

	public WorldNode(Point location, List<Item> items) {
		this(location);
		this.items = items;
	}

	public WorldNode(Point location, List<Item> items, WorldNode north, WorldNode south, WorldNode east,
			WorldNode west) {
		this(location, north, south, east, west);
		this.items = items;
	}

	/* Getters and Setters. */

	public WorldNode getNorth() {
		return neighbours[0];
	}

	public WorldNode getSouth() {
		return neighbours[1];
	}

	public WorldNode getEast() {
		return neighbours[2];
	}

	public WorldNode getWest() {
		return neighbours[3];
	}

	public Point getGameLocation() {
		return this.gameLocation;
	}

	public void setGameLocation(Point gameLocation) {
		this.gameLocation = gameLocation;
	}
}
