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

import java.awt.Graphics;
import java.awt.Point;

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
		// Draw each node in appropriate coordinates
		for (int i = 0; i < gwNodes.length; i++) {
			int y = (i * nodeSize) + padding.y;
			for (int j = 0; j < gwNodes[i].length; j++) {
				int x = (j * nodeSize) + padding.x;
				gwNodes[i][j].setNodeSize(nodeSize);
				gwNodes[i][j].draw(g, x, y, inMapView, player);
			}
		}
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
}
