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
 * 20 Sep 16		Linus Go			Made GamePanel render a a current Players node.
 * 21 Sep 16		Chris Rabe			fixed bugs. Rendering can now update
 * 22 Sep 16		Chris Rabe			removed old testing constructor. Now bases world off the game class.
 */
package missing.ui.panels;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JPanel;

import missing.game.Game;
import missing.game.characters.Player;
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
	private Player currentPlayer;
	private VControl controller;

	/**
	 * Construct a GamePanel instance with a controller and a world object.
	 * 
	 * @param VControl
	 *            controller
	 * @param World
	 *            w
	 */
	public GamePanel(VControl controller) {
		this.controller = controller;
		this.setFocusable(true);
		initialise();
	}

	public void setController(VControl controller) {
		this.controller = controller;
	}

	/******
	 * Testing constructors! TODO: remove this/alter in the final release.
	 *********/

	public GamePanel(Game game, Player currentPlayer) {
		super(null);
		try {
			graphicWorld = new GWorld(game.getWorld(), new View(null) {

				@Override
				public void initialise() {
					// TODO Auto-generated method stub

				}

				@Override
				public void setFocus() {
					// TODO Auto-generated method stub

				}

			}, new Point(0, 0));
		} catch (GameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.currentPlayer = currentPlayer;
		curPoint = currentPlayer.getWorldLocation();
		curGWNode = graphicWorld.gwNodes()[curPoint.y][curPoint.x];
	}

	public void initialise() {
		graphicWorld = controller.getGGame().getGWorld();
		currentPlayer = controller.getGGame().getGame().getAvatars()[controller.getPlayerID()];
		curPoint = currentPlayer.getWorldLocation();
		curGWNode = graphicWorld.gwNodes()[curPoint.y][curPoint.x];

	}

	public Dimension getPreferredSize() {
		return new Dimension(600, 600);
	}

	public void updateNodeRender() {
		curPoint = currentPlayer.getWorldLocation();
		curGWNode = graphicWorld.gwNodes()[curPoint.y][curPoint.x];
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
		if (curGWNode == null)
			return;
		curGWNode.setNodeSize(Math.min(this.getWidth(), this.getHeight()));
		try {
			curGWNode.draw(g, 0, 0, false, null);// player doesnt matter for game view
		} catch (GameException e) {
			e.printStackTrace();
		}
	}

}
