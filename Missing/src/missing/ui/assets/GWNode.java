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

	public void draw(Graphics g) {

	}
}
