/* File: GamePanel.java
 * Authors:
 * Linus Go			300345571
 * Chris Rabe		300334207
 * Casey Huang		300316284
 * 
 * Date				Author				Changes 
 * 13 Sep 16		Linus Go			Created VControl.java
 * 13 Sep 16		Chris Rabe			implemented View abstract class
 * 14 Sep 16		Chris Rabe			fixed changing view
 * 18 Sep 16		Linus Go			added get game view method.
 * 18 Sep 16		Casey Huang			attempted scaling implementation
 * 19 Sep 16		Casey Huang			made paintIsometricNodes @deprecated
 * 19 Sep 16		Casey Huang			renamed GameView.java to GamePanel and moved it to a new package
 * 19 Sep 16 		Casey Huang			updated paint method and constructor
 */
package missing.ui.panels;

import java.awt.Color;
import java.awt.Dimension;
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
 * This class represents the Game Panel in the regular state. When the game is
 * running, this will be shown.
 */
@SuppressWarnings("serial")
public class GamePanel extends JPanel {

	private GWorld graphicWorld;
	private Point curPoint;
	private GWNode curGWNode;

	public GamePanel(VControl controller, World w) {
		graphicWorld = controller.getGGame().getGWorld();
		// TODO: need getCurPlayer
		curPoint = controller.getGGame().getGame().getAvatars()[0].getWorldLocation();
		curGWNode = graphicWorld.gwNodes()[curPoint.y][curPoint.x];
	}

	public GamePanel(Point renderLoc) {
		try {
			graphicWorld = new GWorld(new World(), new View(null) {
				@Override
				public void initialise() {
				}

				@Override
				public void setFocus() {
				}

				@Override
				public Dimension getPreferredSize() {
					return new Dimension(800, 600);
				}

			}, new Point(0, 0));
		} catch (GameException e) {
			e.printStackTrace();
		}
		curPoint = renderLoc;
		curGWNode = graphicWorld.gwNodes()[curPoint.y][curPoint.x];
	}

	public Dimension getPreferredSize() {
		return VControl.VIEW_SIZE;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
	
	/**
	 * Draws the paint 
	 */
	@Override
	public void paint(Graphics g) {
		int val = Math.min(this.getWidth(), this.getHeight());
		System.out.println(val);
		curGWNode.setNodeSize(Math.min(this.getWidth(), this.getHeight()));
		try {
			curGWNode.draw(g, 0, 0);
		} catch (GameException e) {
			e.printStackTrace();
		}
	}

}
