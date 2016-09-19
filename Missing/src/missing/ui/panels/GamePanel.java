/* File: VControl.java
 * Authors:
 * Linus Go			300345571
 * Chris Rabe		300334207
 * 
 * Date				Author				Changes 
 * 13 Sep 16		Linus Go			Created VControl.java
 * 13 Sep 16		Chris Rabe			implemented View abstract class
 * 14 Sep 16		Chris Rabe			fixed changing view
 * 18 Sep 16		Linus Go			added get game view method.
 * 18 Sep 16		Casey Huang			attempted scaling implementation
 * 19 Sep 16		Casey Huang			made paintIsometricNodes @deprecated
 * 19 Sep 16		Casey Huang			renamed GameView.java to GamePanel and moved it to a new package
 */
package missing.ui.panels;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JPanel;

import missing.game.world.World;
import missing.helper.GameException;
import missing.ui.assets.GWNode;
import missing.ui.assets.GWorld;
import missing.ui.controller.VControl;
import missing.ui.controller.VControl.View;
/**
 * This class represents the Game View in the regular sate.
 * @author linus
 *
 */
@SuppressWarnings("serial")
public class GamePanel extends JPanel {

	private GWorld graphicWorld;

	public GamePanel(VControl controller, World w) {
		//graphicWorld = controller.getG
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//paintIsometricNodes(g);	
	}

	/**
	 * @deprecated
	 * @param g
	 */
	private void paintIsometricNodes(Graphics g){
		GWNode gwNodes[][] = graphicWorld.gwNodes();
		try {
			graphicWorld.setNodeSize();
			//just draw 10 of them for now.
			for (int y=0; y<1;y++){
				for (int x=0; x<1; x++){
					System.out.println(this.getWidth());
					gwNodes[x][y].drawIsometricNode(g, this.getWidth());
				} 
			}
		}
		catch (GameException e) {
			e.printStackTrace();
		}
	}

}
