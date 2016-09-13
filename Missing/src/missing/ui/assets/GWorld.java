/* File: GWorld.java
 * Author:
 * Jian Wei Chong	300352789
 * 
 * Date				Author				Changes
 * 13 Sep 16		Jian Wei			create GWorld.java
 * 13 Sep 16		Linus Go			Added draw method 
 */
package missing.ui.assets;
import java.awt.Graphics;
import missing.game.world.World;
import missing.helper.GUIInitialiser;
import missing.ui.controller.VControl.View;

public class GWorld {
	
	private View curView;
	private World world;
	private GWNode[][] gwNodes;

	public GWorld(World world, View view) {
		this.world = world;
		this.curView = view;
		int nodeSize = Math.min(curView.getWidth(), curView.getHeight());
		gwNodes = GUIInitialiser.initialiseGNodes(world, nodeSize);
	
	}
	
	
	
	/**
	 * 	Draws the world
	 * @param Graphics g
	 */
	public void draw(Graphics g){
		
	}

}
