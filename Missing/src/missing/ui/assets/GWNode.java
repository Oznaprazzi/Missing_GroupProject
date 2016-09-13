/*	File: GWNode.java
 * 
 * 	Author:
 * 	Jian Wei Chong	300352789
 * 	Chris Rabe		300334207
 * 
 * 	Date			Author				Changes
 * 	13 Sep 16		Jian Wei			created GWNode.java
 * 	13 Sep 16		Chris Rabe			added comments and draw method
 */

package missing.ui.assets;

import java.awt.Graphics;

import missing.game.world.nodes.WorldNode;
import missing.helper.GUIInitialiser;
import missing.helper.GameException;

public class GWNode {
	/** Indicates the number of tiles inside the node */
	private final int TILE_SIZE = 10;

	private WorldNode node;
	private GWTile[][] tiles;
	private int nodeSize;

	public GWNode(WorldNode node, int nodeSize) {
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
	 * @param g
	 * @throws GameException 
	 */
	public void draw(Graphics g) throws GameException {
	int x = 0, y = 0;
	for(int i = 0; i < tiles.length; i++){
		for(int j = 0; j < tiles.length; j++){
			//TODO: i need to come back and ensure that it wraps around.
			tiles[i][j].draw(g, x, y);
			x+=TILE_SIZE;
		}
	}
		
		
	}
}
