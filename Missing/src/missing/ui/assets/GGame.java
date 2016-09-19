/* File: GWorld.java
 * Author:
 * Linus Go			300345571
 * Jian Wei Chong	300352789
 * Chris Rabe		300334207
 * 
 * Date				Author				Changes
 * 19 Sep 16		Casey Huang			created GGame class
 */
package missing.ui.assets;

import java.awt.Graphics;
import java.awt.Point;

import missing.datastorage.initialisers.GUIInitialiser;
import missing.game.world.World;
import missing.helper.GameException;
import missing.ui.controller.VControl.View;

/**
 * This class is a wrapper class to the World objects which contains methods
 * which are purely used for graphics and rendering.
 */
public class GGame {

	private View curView;
	private GWorld gworld;

	/** This field indicates where the world will be drawn */
	private Point padding;

	public GGame(World world, View view, Point padding) throws GameException {
		gworld = new GWorld(world, view, padding);
		int nodeSize = Math.min(curView.getWidth(), curView.getHeight()) / World.WORLD_WIDTH;
	}

	// Methods

	public void draw(Graphics g) throws GameException {
		this.setNodeSize();
		// Calculate node size relative to the View panel
		int nodeSize = Math.min(curView.getWidth(), curView.getHeight()) / World.WORLD_WIDTH;
		// Draw each node in appropriate coordinates
		for (int i = 0; i < gwNodes.length; i++) {
			int y = (i * nodeSize) + padding.y;
			for (int j = 0; j < gwNodes[i].length; j++) {
				int x = (j * nodeSize) + padding.x;
				gwNodes[i][j].draw(g, x, y);
			}
		}
	}

	public void setNodeSize() throws GameException{
		// Calculate node size relative to the View panel
		int nodeSize = Math.min(curView.getWidth(), curView.getHeight()) / World.WORLD_WIDTH;
		// Draw each node in appropriate coordinates
		for (int i = 0; i < gwNodes.length; i++) {
			for (int j = 0; j < gwNodes[i].length; j++) {
				gwNodes[i][j].setNodeSize(nodeSize);
			}
		}
	}
	
	public GWorld getGWorld(){
		return gworld;
	}
}
