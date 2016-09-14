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
	private int size;

	// Tile images
	private BufferedImage sandImage;
	private BufferedImage waterImage;
	private BufferedImage grassImage;
	private BufferedImage roadImage;

	public GWTile(WorldTile tile, int size) throws GameException {
		/* Initialize the images. */
		/*
		 * try { sandImage =
		 * ImageIO.read(getClass().getResource("/img/sand.jpg")); waterImage =
		 * ImageIO.read(getClass().getResource("/img/water.jpg")); grassImage =
		 * ImageIO.read(getClass().getResource("/img/grass.jpg")); roadImage =
		 * ImageIO.read(getClass().getResource("/img/road.jpg")); } catch
		 * (IOException e) { // convert IOException to GameException throw new
		 * GameException(e.getMessage()); }
		 */
		this.tile = tile;
		this.size = size;
	}

	// Getters and Setters

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
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
			g.setColor(Color.YELLOW);
			break;
		case WATER:
			g.setColor(Color.BLUE);
			break;
		case GRASS:
			g.setColor(Color.GREEN);
			break;
		case ROAD:
			g.setColor(Color.GRAY);
			break;
		default:
			g.setColor(Color.WHITE);
			throw new GameException("Invalid tile");
		}
		g.drawRect(x, y, size, size);
		// switch (tile.getType()) {
		// case SAND:
		// g.drawImage(sandImage, x, y, size, size, null);
		// break;
		// case WATER:
		// g.drawImage(waterImage, x, y, size, size, null);
		// break;
		// case GRASS:
		// g.drawImage(grassImage, x, y, size, size, null);
		// break;
		// case ROAD:
		// g.drawImage(roadImage, x, y, size, size, null);
		// break;
		// default:
		// throw new GameException("Trying to draw an invalid tile type which
		// doesn't exist!");
		// }
	}
}
