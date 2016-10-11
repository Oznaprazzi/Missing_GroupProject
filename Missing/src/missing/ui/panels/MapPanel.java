/*	File: MapPanel.java
 * 	Author
 *  Edward Kelly	300334192
 *  
 * 	Date			Author				Changes
 *  3 Oct 16		Edward Kelly		created class
 */
package missing.ui.panels;

import java.awt.Graphics;

import javax.swing.JPanel;

import missing.helper.GameException;
import missing.ui.assets.GWorld;

/**
 * Draws the whole map.
 */
@SuppressWarnings("serial")
public class MapPanel extends JPanel {

	private GWorld gWorld;

	public MapPanel(GWorld gWorld) {
		this.gWorld = gWorld;
	}

	/**
	 * Updates gWorld to current game
	 * 
	 * @param gWorld
	 */
	public void updateGWorld(GWorld gWorld) {
		this.gWorld = gWorld;
	}

	@Override
	public void paint(Graphics g) {
		try {
			gWorld.draw(g);
		} catch (GameException e) {
			e.printStackTrace();
		}
	}
}
