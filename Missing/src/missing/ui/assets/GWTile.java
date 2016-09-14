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
 *  13 Sep 16		Chris Rabe			made images compatible with executable jar files
 */

package missing.ui.assets;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import missing.game.world.nodes.WorldTile;
import missing.helper.GameException;

/**
 * This class is a wrapper class to the WorldTile objects which contains methods
 * which are purely used for graphics and rendering. This class displays a
 * single tile in the node.
 */
public class GWTile {

	/** A reference to a tile object. */
	private WorldTile tile;
	/** The current size of this tile. */
	private int size = 10;

	// Tile images
	private BufferedImage sandImage;
	private BufferedImage waterImage;
	private BufferedImage grassImage;
	private BufferedImage roadImage;

	public GWTile(WorldTile tile, int size) throws GameException {
		/* Initialize the images. */
		/*try {
			sandImage = ImageIO.read(getClass().getResource("/img/sand.jpg"));
			waterImage = ImageIO.read(getClass().getResource("/img/water.jpg"));
			grassImage = ImageIO.read(getClass().getResource("/img/grass.jpg"));
			roadImage = ImageIO.read(getClass().getResource("/img/road.jpg"));
		} catch (IOException e) {
			// convert IOException to GameException
			throw new GameException(e.getMessage());
		}*/
		

		this.tile = tile;
		this.size = size;
	}

	/**
	 * Draw the tile at the specified x and y position onto this graphics
	 * context.
	 * 
	 * @param g
	 *            - Graphics
	 * @param x
	 *            - x position
	 * @param y
	 *            - y position
	 */
	public void draw(Graphics g, int x, int y) throws GameException {
	

		switch (tile.getType()) {
		case SAND:
			Color brown = new Color(165,42,42);
			g.setColor(brown);
			g.drawRect(x, y, size, size);
			//g.drawImage(sandImage, x, y, size, size, null);
			break;
		case WATER:
			//Color brown = new Color(165,42,42);
			g.setColor(Color.blue);
			g.drawRect(x, y, size, size);
			//g.drawImage(waterImage, x, y, size, size, null);
			break;
		case GRASS:
			//g.drawImage(grassImage, x, y, size, size, null);
			g.setColor(Color.green);
			g.drawRect(x, y, size, size);
			break;
		case ROAD:
			//g.drawImage(roadImage, x, y, size, size, null);
			g.setColor(Color.black);
			g.drawRect(x, y, size, size);
			break;
		default:
			throw new GameException("Trying to draw an invalid tile type which doesn't exist!");
		}
	}
}
