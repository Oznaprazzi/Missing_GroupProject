/*	File: MapPanel.java
 * 	Author
 * 	Casey Huang		300316284
 *  Edward Kelly	300334192
 *  
 * 	Date			Author				Changes
 * 	19 Sep 16		Casey Huang			created MapPanel class
 * 	19 Sep 16		Casey Huang			renamed MapView to MapPanel
 *  23 Sep 16		Edward Kelly		removed World param from constructor
 */
package missing.ui.panels;

import java.awt.Color;
import java.awt.Graphics;

import missing.game.world.World;
import missing.helper.GameException;
import missing.ui.assets.GWorld;
import missing.ui.controller.VControl;
import missing.ui.controller.VControl.View;

@SuppressWarnings("serial")
public class MapPanel extends View {

	private GWorld gWorld;

	public MapPanel(VControl controller) {
		super(controller);
		gWorld = controller.getGGame().getGWorld();
	}

	@Override
	public void initialise() {

	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}

	/**
	 * When called, this method will draw the splash screen message onto the
	 * screen.
	 * 
	 * @param g
	 */
	@Override
	public void paint(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, getWidth(), getHeight());
		try {
			gWorld.draw(g);
		} catch (GameException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}
}
