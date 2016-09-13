/*	File: GWTile.java
 * 	
 * 	Author:
 * 	Jian Wei Chong	300352789
 * 	Chris Rabe		300334207
 * 	
 * 	Date			Author				Changes
 * 	13 Sep 16		Jian Wei			create GWTile.java
 * 	13 Sep 16		Chris Rabe			implemented the draw method
 */

package missing.ui.assets;

import java.awt.Graphics;

import missing.game.world.nodes.WorldTile;

public class GWTile {

	private WorldTile tile;
	private int size;

	public GWTile(WorldTile tile, int size) {
		this.tile = tile;
		this.size = size;
	}

	public void draw(Graphics g) {

	}
}
