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
 *  18 Sep 16		Casey Huang			Added the draw isometric tiles method
 *  18 Sep 16 		Casey Huang			Added integer fields to help draw the isometric tiles
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
	public final int TILE_WIDTH = 64; // width of isometric tile
	public final int TILE_HEIGHT = TILE_WIDTH/2; // height of isometric tile
	public final int CANVAS_WIDTH = 800;
	public final int CANVAS_HEIGHT = 500;
	
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
	 * Draws an individual tile. This is done by drawing an image for each tile and
	 * clipping it with a polygon which is of the isometric shape. 
	 * 
	 * @param g
	 * @param x x coordinate in grid
	 * @param y y coordinate in grid
	 */
	private void drawIsometricTile(Graphics g, int x, int y){
		/* Get isometric tile dimensions */
		// top corner of tile
		int topX = (x - y) * TILE_WIDTH/2 + CANVAS_WIDTH/2;
		int topY = (x + y) * TILE_HEIGHT/2;
		// bottom corner of tile
		int botX = topX;
		int botY = topY+TILE_HEIGHT;
		// left corner of tile
		int leftX = topX - TILE_WIDTH/2;
		int leftY = topY + TILE_HEIGHT/2;
		// right corner of tile
		int rightX = topX + TILE_WIDTH/2;
		int rightY = topY + TILE_HEIGHT/2;
		
		// Create a polygon to draw tile
		int[] xPoints = {topX,rightX,botX,leftX};
		int[] yPoints = {topY,rightY,botY,leftY};
		Polygon poly = new Polygon(xPoints, yPoints, 4);
		
		// Clips the isometric tile shape around the image about to be drawn
		g.setClip(poly);
		
		switch(tile.getType()){
		case SAND:
			g.drawImage(GameAssets.getSandImage(), leftX, topY, TILE_WIDTH, TILE_HEIGHT, null);
			break;
		case WATER:
			g.drawImage(GameAssets.getWaterImage(), leftX, topY, TILE_WIDTH, TILE_HEIGHT, null);
			break;
		case GRASS:
			g.drawImage(GameAssets.getGrassImage(), leftX, topY, TILE_WIDTH, TILE_HEIGHT, null);
			break;
		case ROAD:
			g.drawImage(GameAssets.getRoadImage(), leftX, topY, TILE_WIDTH, TILE_HEIGHT, null);
			break;
		}
		// reset clip
		g.setClip(null);
	}
}
