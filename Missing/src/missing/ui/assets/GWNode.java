/*	File: GWNode.java
 * 
 * 	Author:
 * 	Jian Wei Chong	300352789
 * 	Chris Rabe		300334207
 * 	Linus Go		300345571
 * 	Casey Huang		300316284
 * 
 * 	Date			Author				Changes
 * 	13 Sep 16		Jian Wei			created GWNode.java
 * 	13 Sep 16		Chris Rabe			added comments and draw method
 * 	13 Sep 16		Linus Go			defined draw method
 *  18 Sep 16		Linus Go			added isometric draw method.
 *  18 Sep 16		Casey Huang			attempted scaling implementation
 *  19 Sep 16		Casey Huang			made drawIsometricNode @deprecated.
 */

package missing.ui.assets;

import java.awt.Graphics;

import missing.datastorage.initialisers.GUIInitialiser;
import missing.game.characters.Player;
import missing.game.world.nodes.WorldNode;
import missing.helper.GameException;

/**
 * This class is a wrapper class to the WorldNode objects which contains methods
 * which are purely used for graphics and rendering.
 */
public class GWNode {
	/** Indicates the number of tiles inside the node */
	private final int TILE_SIZE = 10;

	private WorldNode node;
	private GWTile[][] tiles;
	private int nodeSize;

	public GWNode(WorldNode node, int nodeSize) throws GameException {
		this.node = node;
		this.nodeSize = nodeSize;
		int size = nodeSize / TILE_SIZE;
		this.tiles = GUIInitialiser.initialiseGTiles(node, size);
	}

	// Getters and Setters...

	public WorldNode getNode() {
		return node;
	}

	public void setNode(WorldNode node) {
		this.node = node;
	}

	public int getNodeSize() {
		return nodeSize;
	}

	public void setNodeSize(int nodeSize) {
		this.nodeSize = nodeSize;
	}

	// Methods

	/**
	 * Draws the array of tiles that are contained in this Node object.
	 * 
	 * @param g
	 * @param x
	 * @param y
	 * @param inMapView whether the MapView is currently displayed
	 * @param player the local player
	 * @throws GameException
	 */
	public void draw(Graphics g, int x, int y, boolean inMapView, Player player) throws GameException {
		// calculate size of each tile relative to the node
		int tileSize = nodeSize / TILE_SIZE;
		// draw each tile in the appropriate coordinates
		for (int i = 0; i < tiles.length; i++) {
			int tileY = y + (i * tileSize);
			for (int j = 0; j < tiles.length; j++) {
				int tileX = x + (j * tileSize);
				tiles[i][j].setSize(tileSize);
				tiles[i][j].draw(g, tileX, tileY, inMapView, player);
			}
		}
	}
}
