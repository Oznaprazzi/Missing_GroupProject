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
 *  6 Oct 16 		Edward Kelly		added alpha field
 *  7 Oct 16 		Edward Kelly		added light circle
 */

package missing.ui.assets;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RadialGradientPaint;
import java.awt.Rectangle;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.List;

import missing.datastorage.initialisers.GUIInitialiser;
import missing.game.characters.Player;
import missing.game.items.nonmovable.Fireplace;
import missing.game.world.nodes.WorldNode;
import missing.game.world.nodes.WorldTile.TileObject;
import missing.helper.GameException;

/**
 * This class is a wrapper class to the WorldNode objects which contains methods
 * which are purely used for graphics and rendering.
 */
public class GWNode {
	/** Indicates the number of tiles inside the node */
	public static final int TILE_SIZE = 10;

	private WorldNode node;
	private GWTile[][] tiles;
	private int nodeSize;
	
	/** Used for darkness in day night cycles */
	private int alpha;

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
	public void setAlpha(int alpha) {
		this.alpha = alpha;
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
		List<Point> fireplaceLocs = new ArrayList<Point>();
		for (int i = 0; i < tiles.length; i++) {
			int tileY = y + (i * tileSize);
			for (int j = 0; j < tiles.length; j++) {
				int tileX = x + (j * tileSize);
				tiles[i][j].setSize(tileSize);
				tiles[i][j].draw(g, tileX, tileY, inMapView, player);
				if (tiles[i][j].getWorldTile().isOccupied()){
					if (tiles[i][j].getWorldTile().getObject() instanceof Fireplace){
						fireplaceLocs.add(new Point(j,i));
					}
				}
			}
		}
		if (!inMapView){
			drawLights(fireplaceLocs, tileSize, player, x, y, g);
		}
	}
	
	private void drawLights(List<Point> fireplaceLocs, int tileSize, Player player, int x, int y, Graphics g){

		//Fireplaces
		Graphics2D g2d = (Graphics2D)g.create();
		Color c1, c2;
		int gx, gy;
		Area fireArea;
		RadialGradientPaint fireGlow;
		int fireAlpha = 50;
		Area fireAreas = new Area();
		for (Point p : fireplaceLocs){
			fireArea = new Area(new Ellipse2D.Double((p.x-1)* tileSize, (p.y-1)* tileSize, tileSize*3, tileSize*3));
			c1 = new Color(255, 213, 45, fireAlpha);
			c2 = new Color(35, 35, 43, alpha);
			gx = x + p.x*tileSize + tileSize/2;
			gy = y + p.y*tileSize + tileSize/2;
			fireGlow = new RadialGradientPaint(gx, gy, (float) (tileSize*1.5), new float[]{0.0f,1.0f}, new Color[]{c1,c2});
			fireAreas.add(fireArea);
//			g2d.setPaint(fireGlow);
//			g2d.fill(fireArea);
		}
		// draws night colour filter for GameView
		if (alpha != 0){
			// only need to draw filter if not fully transparent
			Area filter = new Area(new Rectangle(x, y, nodeSize, nodeSize));
			int playerAlpha = 150;
			if (player.getWorldLocation().equals(this.node.getGameLocation())&&alpha>playerAlpha){
				// draws light circle
				Point playerTileLoc = player.getTileLocation();
				int haloX = x + ((playerTileLoc.x-1) * tileSize);
				int haloY = y + ((playerTileLoc.y-1) * tileSize);
				Area playerArea = new Area(new Ellipse2D.Double(haloX, haloY, tileSize*3, tileSize*3));
				
				fireAreas.add(playerArea);
				filter.subtract(fireAreas);
				g2d.setColor(new Color(35, 35, 43, playerAlpha));
				g2d.fill(fireAreas);
			}
			g2d.setColor(new Color(35, 35, 43, alpha));
			g2d.fill(filter);
			
		}
	}
	/**
	 * Returns an array of Point objects representing all the points
	 * of glowable objects. Glowable objects are players and fireplaces
	 * which emit light in the dark.
	 * @param localPlayer
	 * @return
	 */
	public Point[] getGlowableObjects(Player localPlayer){
		Point[] glowableObjects = new Point[TILE_SIZE];
		int index = 0;
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles.length; j++) {
				if (tiles[i][j].getWorldTile().isOccupied()){
					TileObject obj = tiles[i][j].getWorldTile().getObject();
					if (obj instanceof Fireplace){
						glowableObjects[index] = new Point(j,i);
						index++;
					}
					else if (obj instanceof Player){
						if (localPlayer.isDead()){
							glowableObjects[index] = new Point(j,i);
							index++;
						}
						else if (((Player)obj).getId() == localPlayer.getId()){
							glowableObjects[index] = new Point(j,i);
							index++;							
						}
					}
				}
			}
		}
		return glowableObjects;
	}

	
}
