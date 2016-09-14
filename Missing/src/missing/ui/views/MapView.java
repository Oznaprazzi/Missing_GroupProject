package missing.ui.views;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import missing.game.world.World;
import missing.helper.GameException;
import missing.ui.assets.GWorld;
import missing.ui.controller.VControl;
import missing.ui.controller.VControl.View;

@SuppressWarnings("serial")
public class MapView extends View {

	private GWorld gWorld;

	public MapView(VControl controller, World world) {
		super(controller);
		try {
			gWorld = new GWorld(world, this, new Point(0, 0));
		} catch (GameException e) {
			e.printStackTrace();
		}
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
