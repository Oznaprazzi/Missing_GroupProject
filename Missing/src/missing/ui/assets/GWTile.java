/*	File: GWTile.java
 * 	
 * 	Author:
 * 	Jian Wei Chong	300352789
 * 	Chris Rabe		300334207
 * 	
 * 	Date			Author				Changes
 * 	13 Sep 16		Jian Wei			create GWTile.java
 * 	13 Sep 16		Chris Rabe			implemented the draw method
 *  13 Sep 16		Linus Go			added stuff into the draw method.
 */

package missing.ui.assets;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import missing.game.world.nodes.WorldTile;
import missing.game.world.nodes.WorldTile.TileType;
import missing.helper.GameException;

public class GWTile {

	private WorldTile tile;
	private int size = 10;
	
	/*Initialize the tile images*/
	private BufferedImage sandImage;
	private BufferedImage waterImage;
	private BufferedImage grassImage;
	private BufferedImage roadImage;

	public GWTile(WorldTile tile, int size) {
		this.tile = tile;
		this.size = size;
	}
	/**
	 * Draw the tile at the specified x and y position onto this graphics context.
	 * @param g - Graphics
	 * @param x - x position
	 * @param y - y position
	 */
	public void draw(Graphics g, int x, int y){
		switch (tile.getType()) {
		case SAND:
			g.drawImage(sandImage, x, y, size,size, null);
			break;
		case WATER:
			g.drawImage(waterImage, x, y, size, size, null);
			break;
		case GRASS:
			g.drawImage(grassImage, x, y, size, size, null);
			break;
		case ROAD:
			g.drawImage(roadImage, x, y, size, size, null);
			break;
		default:
			// ???
			break;
		}
	}
}
