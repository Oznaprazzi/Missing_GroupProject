/*	File: GWTile.java
 * 	
 * 	Author:
 * 	Jian Wei Chong	300352789
 * 	Chris Rabe		300334207
 * 	Casey Huang		300316284 	
 * 	Date			Author				Changes
 * 	13 Sep 16		Jian Wei			create GWTile.java
 * 	13 Sep 16		Chris Rabe			implemented the draw method
 *  13 Sep 16		Linus Go			added stuff into the draw method.
 *  13 Sep 16		Chris Rabe			made images compatible with executable jar files
 *  18 Sep 16		Casey Huang			Added the draw isometric tiles method
 *  18 Sep 16 		Casey Huang			Added integer fields to help draw the isometric tiles
 *  18 Sep 16		Linus Go			Set the draw isometric tiles method to public
 *  18 Sep 16		Casey Huang			attempted scaling implementation
 *  19 Sep 16 		Casey Huang			made drawIsometricTile @deprecated
 */

package missing.ui.assets;

import java.awt.Graphics;
import java.awt.Polygon;

import missing.datastorage.assetloader.GameAssets;
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

	public GWTile(WorldTile tile, int size) throws GameException {
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
			g.drawImage(GameAssets.getSandImage(), x, y, size, size, null);
			break;
		case WATER:
			g.drawImage(GameAssets.getWaterImage(), x, y, size, size, null);
			break;
		case GRASS:
			g.drawImage(GameAssets.getGrassImage(), x, y, size, size, null);
			break;
		case ROAD:
			g.drawImage(GameAssets.getRoadImage(), x, y, size, size, null);
			break;
		default:
			throw new GameException("Trying to draw an invalid tile type which doesn't exist!");
		}
	}

	/**
	 * Draws an individual tile. This is done by drawing an image for each tile
	 * and clipping it with a polygon which is of the isometric shape.
	 * 
	 * @deprecated
	 * @param g
	 * @param x
	 *            x coordinate in grid
	 * @param y
	 *            y coordinate in grid
	 */
	public void drawIsometricTile(Graphics g, int x, int y, int canvas_width) {
		/* Get isometric tile dimensions */
		int tile_width = this.size;
		int tile_height = tile_width / 2;
		// top corner of tile
		int topX = (x - y) * tile_width / 2 + canvas_width / 2;
		int topY = (x + y) * tile_height / 2;
		// bottom corner of tile
		int botX = topX;
		int botY = topY + tile_height;
		// left corner of tile
		int leftX = topX - tile_width / 2;
		int leftY = topY + tile_height / 2;
		// right corner of tile
		int rightX = topX + tile_width / 2;
		int rightY = topY + tile_height / 2;

		// Create a polygon to draw tile
		int[] xPoints = { topX, rightX, botX, leftX };
		int[] yPoints = { topY, rightY, botY, leftY };
		Polygon poly = new Polygon(xPoints, yPoints, 4);

		// Clips the isometric tile shape around the image about to be drawn
		g.setClip(poly);

		switch (tile.getType()) {
		case SAND:
			g.drawImage(GameAssets.getSandImage(), leftX, topY, tile_width, tile_height, null);
			break;
		case WATER:
			g.drawImage(GameAssets.getWaterImage(), leftX, topY, tile_width, tile_height, null);
			break;
		case GRASS:
			g.drawImage(GameAssets.getGrassImage(), leftX, topY, tile_width, tile_height, null);
			break;
		case ROAD:
			g.drawImage(GameAssets.getRoadImage(), leftX, topY, tile_width, tile_height, null);
			break;
		}
		// reset clip
		g.setClip(null);
	}
}
