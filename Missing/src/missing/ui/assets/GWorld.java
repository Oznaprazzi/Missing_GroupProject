/* File: GWorld.java
 * Author:
 * Jian Wei Chong	300352789
 * Chris Rabe		300334207
 * 
 * Date				Author				Changes
 * 13 Sep 16		Jian Wei			create GWorld.java
 * 13 Sep 16		Chris Rabe			added draw method
 * 13 Sep 16		Linus Go			changed constructor
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

	public void draw(Graphics g) {

	}
}
