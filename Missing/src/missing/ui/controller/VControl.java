/* File: VControl.java
 * Authors:
 * Linus Go			300345571
 * Chris Rabe		300334207
 * Casey Huang		300316284
 * Edward Kelly		300334192
 * 
 * Date				Author				Changes 
 * 13 Sep 16		Linus Go			Created VControl.java
 * 13 Sep 16		Chris Rabe			implemented View abstract class
 * 14 Sep 16		Chris Rabe			fixed changing view
 * 18 Sep 16		Linus Go			added get game view method.
 * 19 Sep 16 		Casey Huang			created getGGame method
 * 23 Sep 16		Edward Kelly		added GGame and playerID
 * 24 Sep 16		Edward Kelly		added play game menu views
 * 30 Sep 16		Edward Kelly		added CreatePlayerView
 * 1 Oct 16			Edward Kelly		added displayException & displayTimedMessage
 */
package missing.ui.controller;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import missing.datastorage.initialisers.GUIInitialiser;
import missing.game.Game;
import missing.game.world.World;
import missing.helper.GameException;
import missing.ui.assets.GGame;
import missing.ui.views.GameView;

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
	private GGame gGame;
	private int playerID;
	private boolean isHost;

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

	public int getGameView() {
		return 3;
	}
	
	public int getPlayGameView() {
		return 4;
	}
	public int getHostGameView() {
		return 5;
	}
	public int getJoinGameView() {
		return 6;
	}
	public int getLobbyView() {
		return 7;
	}
	public int getClientWaitingView(){
		return 8;
	}
	public int getCreatePlayerView(){
		return 9;
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
		//TODO: change GGame view
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
			return new World();
		} catch (GameException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Called if the player performed an action on a Container
	 * Displays the items in that Container
	 */
	public void displayContainerItems(){
		//TODO: implement. get container items in square in front of player
		// will probably call another method, maybe in GameView
	}
	
	/**
	 * Called if the player performed an action on a Pile
	 * Displays the items in that Pile
	 */
	public void displayPileItems(){
		//TODO: similar to displayContainerItems
	}
	
	/**
	 * Displays a message momentarily on the screen.
	 * Used when a player disconnects
	 * @param msg message to be displayed
	 */
	public void displayTimedMessage(String msg){
		JDialog dialog = new JDialog(this);
		Timer timer = new Timer(3000, new ActionListener(){
			public void actionPerformed(ActionEvent e){
				dialog.setVisible(false);
				dialog.dispose();
			}
		});
		timer.setRepeats(false);
		timer.start();
		JLabel label = new JLabel(msg);
		dialog.add(label);
		dialog.setUndecorated(true);
		dialog.pack();
		dialog.setLocationRelativeTo(views[3]);
		dialog.setFocusable(false);
		dialog.setFocusableWindowState(false);
		dialog.getContentPane().setBackground(Color.YELLOW);
		dialog.setVisible(true);
	}
	
	/**
	 * Displays a message dialog
	 * @param msg message to be displayed
	 */
	public void displayException(String msg){
		JOptionPane.showMessageDialog(views[3], msg);
	}
	// Helper methods

	private void initialiseGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(new JPanel());
		getContentPane().add(views[cur]);
		pack();
		setVisible(true);
	}

	public GGame getGGame() {
		return gGame;
	}
	public void updateGGame(Game game) throws GameException{
		this.gGame = new GGame(game, views[3]);
		((GameView)views[3]).updateGamePanel(this);
	}
	
	public void setGGame(GGame gGame) {
		this.gGame = gGame;
		views[3].initialise();
	}
	
	public int getPlayerID() {
		return playerID;
	}

	public void setPlayerID(int playerID) {
		this.playerID = playerID;
	}
	public View getView(int viewIndex){
		return views[viewIndex];
	}
	public void setIsHost(boolean isHost){
		this.isHost = isHost;
	}
	public boolean isHost(){
		return isHost;
	}
	@Override
	public void repaint(){
		super.repaint();
	}
	

	
}
