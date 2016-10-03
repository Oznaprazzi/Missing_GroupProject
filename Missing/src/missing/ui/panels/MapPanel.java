package missing.ui.panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import missing.helper.GameException;
import missing.ui.assets.GWorld;

public class MapPanel extends JPanel{
	
	
	private GWorld gWorld;
	public MapPanel(GWorld gWorld){
		this.gWorld = gWorld;
	}
	

	public void updateGWorld(GWorld gWorld){
		this.gWorld = gWorld;
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
}
