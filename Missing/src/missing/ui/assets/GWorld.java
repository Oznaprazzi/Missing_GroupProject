/* File: GWorld.java
 * Author:
 * Linus Go			300345571
 * Jian Wei Chong	300352789
 * Chris Rabe		300334207
 * Casey Huang		300316284
 * 
 * Date				Author				Changes
 * 13 Sep 16		Jian Wei			create GWorld.java
 * 13 Sep 16		Chris Rabe			added draw method
 * 13 Sep 16		Linus Go			changed constructor
 * 13 Sep 16		Chris Rabe			added padding variable (indicates where world will be drawn)
 * 13 Sep 16		Chris Rabe			defined draw method
 * 18 Sep 16		Linus Go			added gwNodes getter method.
 * 18 Sep 16		Casey Huang			attempted scaling implementation
 * 20 Sep 16		Chris Rabe			optimised drawing performance
 * 3 Oct 16			Edward Kelly		added setView and setPlayer methods
 */
package missing.ui.assets;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

import missing.datastorage.initialisers.GUIInitialiser;
import missing.game.characters.Player;
import missing.game.world.World;
import missing.helper.GameException;
import missing.ui.controller.VControl.View;
import missing.ui.views.MapView;

/**
 * This class is a wrapper class to the World objects which contains methods
 * which are purely used for graphics and rendering.
 */
public class GWorld {

	private View curView;
	private World world;
	private GWNode[][] gwNodes;
	private boolean inMapView;
	private int alpha;
	/** This field indicates where the world will be drawn */
	private Point padding;
	private Player player;
	

	public GWorld(World world, View view, Point padding) throws GameException {
		this.world = world;
		this.curView = view;
		this.padding = padding;
		inMapView = false;
		int nodeSize = Math.min(curView.getWidth(), curView.getHeight()) / World.WORLD_WIDTH;
		gwNodes = GUIInitialiser.initialiseGNodes(this.world, nodeSize);
	}

	// Methods

	public void draw(Graphics g) throws GameException {
		// Calculate node size relative to the View panel
		int nodeSize = Math.min(curView.getWidth(), curView.getHeight()) / World.WORLD_WIDTH;
		Point[][][] glowableObjects = new Point[World.WORLD_WIDTH][World.WORLD_WIDTH][GWNode.TILE_SIZE];
		// Draw each node in appropriate coordinates
		for (int i = 0; i < gwNodes.length; i++) {
			int y = (i * nodeSize) + padding.y;
			for (int j = 0; j < gwNodes[i].length; j++) {
				int x = (j * nodeSize) + padding.x;
				gwNodes[i][j].setNodeSize(nodeSize);
				gwNodes[i][j].draw(g, x, y, inMapView, player);
				glowableObjects[i][j] = (gwNodes[i][j].getGlowableObjects(player));
			}
		}
		//
		if(alpha!=0){
			drawShading(g, glowableObjects);
		}
		
	}
	/**
	 * Draws night shading for MapView
	 * @param g
	 * @param glowableObjects points to draw light circles 
	 */
	private void drawShading(Graphics g, Point[][][] glowableObjects){
		int nodeSize = Math.min(curView.getWidth(), curView.getHeight()) / World.WORLD_WIDTH;
		int tileSize = nodeSize/GWNode.TILE_SIZE;
		Graphics2D g2d = (Graphics2D)g.create();
		Area filter = new Area(new Rectangle(0, 0, World.WORLD_WIDTH*nodeSize, World.WORLD_WIDTH*nodeSize));
		Area lightAreas = new Area();
		if (alpha>150){
			for (int x=0; x<glowableObjects.length; x++){
				for (int y=0; y<glowableObjects[x].length; y++){
					for (int i=0; i<glowableObjects[x][y].length; i++){
						if (glowableObjects[y][x][i]==null)break;
						int drawX = x*nodeSize+(glowableObjects[y][x][i].x-1)*tileSize;
						int drawY = y*nodeSize+(glowableObjects[y][x][i].y-1)*tileSize;
						lightAreas.add(new Area(new Ellipse2D.Double(drawX, drawY, tileSize*3, tileSize*3)));
					}
					
				}
				
			}
			int haloAlpha = 150;
			g2d.setColor(new Color(35, 35, 43, haloAlpha));
			g2d.fill(lightAreas);
			filter.subtract(lightAreas);
		}
		g2d.setColor(new Color(35, 35, 43, alpha));
		g2d.fill(filter);
		
	}
	public GWNode[][] gwNodes() {
		return this.gwNodes;
	}
	
	public void setView(View view){
		this.curView = view;
		if (view.getClass()==MapView.class){
			this.inMapView = true;
		} else{
			this.inMapView = false;
		}
	}
	
	public void setPlayer(Player player){
		this.player = player;
	}
	
	public void setAlpha(int alpha) {
		this.alpha = alpha;
		for (int i = 0; i < gwNodes.length; i++) {
			for (int j = 0; j < gwNodes[i].length; j++) {
				gwNodes[i][j].setAlpha(alpha);
			}
		}
	}
}
