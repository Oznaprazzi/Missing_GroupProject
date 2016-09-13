/*	File: GWNode.java
 * 
 * 	Author:
 * 	Jian Wei Chong	300352789
 * 
 * 	Date			Author				Changes
 * 	13 Sep 16		Jian Wei			created GWNode.java
 */

package missing.ui.assets;

import java.awt.Graphics;

import missing.game.world.nodes.WorldNode;

public class GWNode {
	private final int TILE_SIZE = 10;

	private WorldNode node;
	private GWTile[][] tiles;

	public GWNode(WorldNode node) {
		this.node = node;
		tiles = new GWTile[TILE_SIZE][TILE_SIZE];
	}
	
	public void draw(Graphics g, int scale){
		
	}
	
	
}
