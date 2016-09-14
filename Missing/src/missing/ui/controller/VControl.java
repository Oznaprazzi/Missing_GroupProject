/* File: VControl.java
 * Authors:
 * Linus Go			300345571
 * Chris Rabe		300334207
 * 
 * Date				Author				Changes 
 * 13 Sep 16		Linus Go			Created VControl.java
 * 13 Sep 16		Chris Rabe			implemented View abstract class
 * 14 Sep 16		Chris Rabe			fixed changing view
 */
package missing.ui.controller;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import missing.game.characters.Player;
import missing.game.world.World;
import missing.helper.GUIInitialiser;
import missing.helper.GameException;

/**
 * 
 * This class represents the Controller for the Game. It extends JFrame, and
 * will contain a View, and buttons and toggles that the user is able to
 * manipulate.
 *
 */
@SuppressWarnings("serial")
public class VControl extends JFrame {

	/**
	 * Views are essentially JPanels with different content. This abstract class
	 * should be extended by other classes.
	 */
	public static abstract class View extends JPanel {
		protected VControl controller;

		public View(VControl controller) {
			this.controller = controller;
			this.setFocusable(false);
		}

		/** Initialises the view */
		public abstract void initialise();

		/**
		 * If a key listener exists, then this should be used to set the key
		 * listener as the focus of the board.
		 */
		public abstract void setFocus();

		@Override
		public Dimension getPreferredSize() {
			return VIEW_SIZE;
		}
	}

	/** Size of each view */ // TODO Change this!
	public static final Dimension VIEW_SIZE = new Dimension(1000, 1000);

	private View[] views;
	private int cur = 0;
	private int prev = 0;

	public VControl() {
		super("Missing: The Game");
		this.views = GUIInitialiser.createViews(this);
		initialiseGUI();
		views[cur].initialise();
	}

	// View Control Methods

	public int getMenuView() {
		return 1;
	}

	public int getMapView() {
		return 2;
	}

	public int getPrevious() {
		return prev;
	}

	/**
	 * Changes view on the specified index. Should be within the boundaries of
	 * the view, otherwise it exits out.
	 * 
	 * @param index
	 */
	public void changeView(int index) {
		if (index < 0 || index >= views.length) {
			return;
		}
		if (views[index] == null) {
			return;
		}
		// Record the previous index
		this.prev = this.cur;
		this.cur = index;
		// Remove and replace the view
		getContentPane().removeAll();
		getContentPane().add(views[cur]);
		// Setting the focus allows event listeners to be activated
		views[index].repaint();
		views[index].setFocus();
		// Redraw the whole frame
		revalidate();
	}

	// TODO Change this once we have a client control
	public World getWorld() {
		try {
			return new World(new ArrayList<Player>());
		} catch (GameException e) {
			e.printStackTrace();
			return null;
		}
	}

	// Helper methods

	private void initialiseGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(new JPanel());
		getContentPane().add(views[cur]);
		pack();
		setVisible(true);
	}

	public static void main(String[] args) {
		new VControl();
	}
}
