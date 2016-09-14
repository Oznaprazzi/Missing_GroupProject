/* File: VControl.java
 * Authors:
 * Linus Go			300345571
 * Chris Rabe		300334207
 * 
 * Date				Author				Changes 
 * 13 Sep 16		Linus Go			Created VControl.java
 * 13 Sep 16		Chris Rabe			implemented View abstract class
 */
package missing.ui.controller;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import missing.ui.views.MapView;
import missing.ui.views.SplashView;

/**
 * 
 * This Class represents the Controller for the Game. It extends JFrame, and
 * will contain a View, and buttons and toggles that the user is able to
 * manipulate.
 *
 */
@SuppressWarnings("serial")
public class VControl extends JFrame {
	
	/**Holds an instance of splashView */
	private View splashView = new SplashView(this);
	/**Holds an instance of mapView */
	//TODO: placeholder null value. Need to pass in an instance of the map soon. Plz fix me.
	private View mapView;
	/**The current View that is being displayed. */
	private View currentView = splashView;
	
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
		
		/**Draws on the current View. */
		public abstract void draw(Graphics g);
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

	/** Size of each view */
	public static final Dimension VIEW_SIZE = new Dimension(600, 600);

	public VControl() {
		super("Missing: The Game");
		setLayout(new BorderLayout());
		add(currentView, BorderLayout.CENTER);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setResizable(false);
		setVisible(true);
	}
	
	/**
	 * This draws the current view.
	 */
	public void drawView(Graphics g){
		currentView.draw(g);
	}
	/**
	 * Sets the current Map View
	 * @param m
	 */
	public void setMapView(MapView m){
		this.mapView = m;
	}
	
	/**
	 * Sets the current View to whatever is being passed in.
	 */
	public void setCurrentView(View v){
		this.currentView = v;
		System.out.println("Current View = " + currentView.toString());
		this.revalidate();
	}
	
	
	@Override
	public void paint(Graphics g){
		drawView(g);
	}
	
	public static void main(String[] args){
		VControl v = new VControl();
	}
	
	
	
}
