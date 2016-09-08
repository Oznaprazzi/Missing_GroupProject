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

/**
 * World node contains various types of world tiles. It should also contain a
 * list of movable and nonMovable objects because this information will be
 * needed when managing server.
 */
public class WorldNode {
	/** Represents the amount of Tiles a worldNode object should have. **/
	private static final int TILE_SIZE = 25;
	/** The location of this World Node on the world. */
	protected Point gameLocation;
	/** The World Tile 2D array. Each world node has these. */
	protected WorldTile[][] worldTiles = new WorldTile[TILE_SIZE][TILE_SIZE];

	public WorldNode(Point p) {
		this.gameLocation = p;
	}

	/* Getters and Setters. */

	public Point getGameLocation() {
		return this.gameLocation;
	}

	public void setGameLocation(Point gameLocation) {
		this.gameLocation = gameLocation;
	}

}
