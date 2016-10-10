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
 * 2 Oct 16			Edward Kelly		added close confirmation and client disconnect
 * 3 Oct 16			Edward Kelly		added displayDead method
 * 3 Oct 16			Edward Kelly		integrated MapView
 * 3 Oct 16			Linus Go			added JMenuChooser and some items.
 * 6 Oct 16			Edward Kelly		implemented calls to XMLHandler.saveGame()
 * 6 Oct 16 		Edward Kelly		implemented day night cycle
 */
package missing.ui.controller;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import missing.ui.daynight.DayNightCycle;
import missing.ui.frames.ContainerFrame;
import missing.ui.frames.CraftingFrame;
import missing.ui.frames.PileFrame;
import missing.datastorage.assetloader.XMLHandler;
import missing.datastorage.initialisers.GUIInitialiser;
import missing.game.Game;
import missing.game.items.nonmovable.Container;
import missing.game.world.nodes.WorldTile.Pile;
import missing.game.world.nodes.WorldTile.TileObject;
import missing.helper.GameException;
import missing.networking.Client;
import missing.ui.assets.GGame;
import missing.ui.views.GameView;
import missing.ui.views.MapView;
import javax.swing.JMenu;

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

	/** Size of each view */
	public static final Dimension VIEW_SIZE = new Dimension(1000, 1000);
	/** How often clock ticks in minutes per second */
	private final int CLOCK_TICK = 10; // 1 sec = 10 game minutes
	private View[] views;
	private int cur = 0;
	private int prev = 0;
	/** Graphical representation of game for this VControl */
	private GGame gGame;
	/** ID for player with this VControl */
	private int playerID;
	/** Whether the player with this VControl is the host */
	private boolean isHost;
	/** Client this VControl belongs to */
	private Client client;
	/** Darkness level of world */
	private int alpha;

	/* JMenuBar stuff */
	private JMenuBar menuBar;
	private JMenuItem saveGame;
	private JMenuItem loadGame;
	private JMenuItem exitItem;
	private JMenuItem helpItem;
	private JMenuItem credits;
	private JFileChooser fc;

	private DayNightCycle dnc;

	public VControl() {
		super("Missing: The Game");
		this.views = GUIInitialiser.createViews(this);
		initializeMenu();
		initialiseGUI();
		setupMenuListeners();
		views[cur].initialise();
	}

	/**
	 * Helper method. Initializes the JMenu and all of the Items inside.
	 */
	private void initializeMenu() {
		menuBar = new JMenuBar();

		JMenu fileMenu = new JMenu("File");
		saveGame = new JMenuItem("Save Game", KeyEvent.VK_S);
		loadGame = new JMenuItem("Load Game", KeyEvent.VK_L);
		exitItem = new JMenuItem("Exit", KeyEvent.VK_E);
		/* Ensure that the VControl doesn't lose focus. */
		saveGame.setFocusable(false);
		loadGame.setFocusable(false);
		exitItem.setFocusable(false);
		fileMenu.add(saveGame);
		fileMenu.add(loadGame);
		fileMenu.add(exitItem);
		menuBar.add(fileMenu);

		JMenu helpMenu = new JMenu("Help");
		helpItem = new JMenuItem("Game Instructions", KeyEvent.VK_G);
		credits = new JMenuItem("Credits", KeyEvent.VK_C);
		/* Ensure that VControl doesn't lose focus. */
		helpItem.setFocusable(false);
		credits.setFocusable(false);

		helpMenu.add(helpItem);
		helpMenu.add(credits);
		menuBar.add(helpMenu);

		this.setJMenuBar(menuBar);
	}

	private void setupMenuListeners() {

		saveGame.addActionListener(e -> {
			if (gGame == null) {
				JOptionPane.showMessageDialog(this, "You must load or start a game to save", "No Game to Save",
						JOptionPane.WARNING_MESSAGE);
			} else
				saveGame();
		});

		loadGame.addActionListener(e -> {
			loadGame();
		});

		exitItem.addActionListener(e -> {
			int choice = JOptionPane.showConfirmDialog(null, "Do you want to exit?", "Exit Confirmation",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

			if (choice == 0) {
				if (isHost && gGame != null) {
					// ask if want to save game
					choice = JOptionPane.showConfirmDialog(null, "Would you like to save the game?", "Save game",
							JOptionPane.YES_NO_OPTION);
					if (choice == JOptionPane.YES_OPTION) {
						saveGame();
					}
				}
				closeVControl();
			}
		});

		helpItem.addActionListener(e -> {

		});

		credits.addActionListener(e -> {

		});
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

	public int getClientWaitingView() {
		return 8;
	}

	public int getCreatePlayerView() {
		return 9;
	}

	public int getShopView() {
		return 10;
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
		if (gGame != null) {
			gGame.setView(views[index]);
			gGame.setPlayer(gGame.getGame().getAvatars()[playerID]);
		}
		// Setting the focus allows event listeners to be activated
		views[index].repaint();
		views[index].setFocus();
		// Redraw the whole frame
		revalidate();
	}

	/**
	 * Disconnects client from game and closes window
	 */
	private void closeVControl() {
		if (client != null) {
			client.disconnectClient();
		}
		System.exit(0);
	}

	public void openCrafting() {
		new CraftingFrame(this).setVisible(true);
	}

	/**
	 * Called if the player performed an action on a Container Displays the
	 * items in that Container
	 */
	public void displayContainerItems() {
		try {
			TileObject container = gGame.getGame().getObjectInFront(playerID);
			new ContainerFrame((Container) container).setVisible(true);
		} catch (GameException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Called if the player performed an action on a Pile Displays the items in
	 * that Pile
	 */
	public void displayPileItems() {
		try {
			TileObject pile = gGame.getGame().getObjectInFront(playerID);
			new PileFrame((Pile) pile).setVisible(true);
		} catch (GameException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Displays a message momentarily on the screen. Used when a player
	 * disconnects
	 * 
	 * @param msg
	 *            message to be displayed
	 */
	public void displayTimedMessage(String msg) {
		JDialog dialog = new JDialog(this);
		Timer timer = new Timer(3000, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dialog.setVisible(false);
				dialog.dispose();
			}
		});
		timer.setRepeats(false);
		timer.start();
		JLabel label = new JLabel(msg);
		dialog.getContentPane().add(label);
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
	 * 
	 * @param msg
	 *            message to be displayed
	 */
	public void displayException(String msg) {
		JOptionPane.showMessageDialog(views[cur], msg);
	}

	/**
	 * Displays to player that they died and sets to spectator mode
	 */
	public void displayDead() {
		JOptionPane.showMessageDialog(views[cur], "YOU DIED");
		((MapView) views[this.getMapView()]).setSpectator(true);
		this.changeView(this.getMapView());
	}
	// Helper methods

	private void initialiseGUI() {
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		// Asks for confirmation when closing the game
		addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit?", "Quit game",
						JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.YES_OPTION) {
					if (isHost && gGame != null) {
						// ask if want to save game
						result = JOptionPane.showConfirmDialog(null, "Would you like to save the game?", "Save game",
								JOptionPane.YES_NO_OPTION);
						if (result == JOptionPane.YES_OPTION) {
							saveGame();
						}
					}
					closeVControl();
				}
			}
		});
		JPanel panel = new JPanel();
		getContentPane().add(panel);
		getContentPane().add(views[cur]);
		pack();
		setVisible(true);
	}

	/**
	 * Displays GUI to save game and calls XMLHandler.saveGame()
	 */
	private void saveGame() {
		// JFileChooser choose save location
		fc = new JFileChooser("Choose save location");
		fc.setCurrentDirectory(new File("."));
		while (true) {
			int rVal = fc.showSaveDialog(this);
			if (rVal == JFileChooser.APPROVE_OPTION) {
				String fileName = fc.getSelectedFile().getAbsolutePath();
				if (fileName == null) {
					JOptionPane.showMessageDialog(this, "Please give a filename", "No Filename",
							JOptionPane.WARNING_MESSAGE);
					continue;
				}
				System.out.println(fileName);
				// create tmpgame in case save fails
				Game tmpGame = gGame.getGame();
				tmpGame.killAll();
				try {
					XMLHandler.saveGame(tmpGame, fileName);
					JOptionPane.showMessageDialog(this, "Game saved", "Game Saved", JOptionPane.INFORMATION_MESSAGE);
					break;
				} catch (GameException e) {
					JOptionPane.showMessageDialog(this, e.getMessage(), "File Saving Error", JOptionPane.ERROR_MESSAGE);
					break;
				}
			} else
				break;
		}
	}

	/**
	 * Displays GUI to load game and calls XMLHandler.filename to set the
	 * current XML file.
	 */
	private void loadGame() {
		fc = new JFileChooser("Load");
		// set up the file chooser
		fc.setCurrentDirectory(new File("."));
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);

		File theFile = null;
		String xmlFile = "";
		while (true) {
			int rVal = fc.showOpenDialog(this);

			if (rVal == JFileChooser.APPROVE_OPTION) {
				theFile = fc.getSelectedFile();
				xmlFile = theFile.getAbsolutePath();

				// Sanity Checks
				if (xmlFile == null) {
					JOptionPane.showMessageDialog(null, "XML File must be specified.", "File Loading Error",
							JOptionPane.ERROR_MESSAGE);
					// System.exit(1);
				}
				if (!xmlFile.endsWith(".xml")) {
					JOptionPane.showMessageDialog(null, "File must end with XML.", "File Loading Error",
							JOptionPane.ERROR_MESSAGE);
					// System.exit(1);
				}
			} else if (rVal == JFileChooser.CANCEL_OPTION) {
				JOptionPane.showMessageDialog(null, "User did not specify an XML file.", "File Loading Error",
						JOptionPane.ERROR_MESSAGE);
				System.exit(1);
			}

			if (xmlFile != null && xmlFile.endsWith(".xml"))
				break;

		}
		XMLHandler.filename = xmlFile;
		JOptionPane.showMessageDialog(null, "The XML file " + xmlFile + " has been loaded.");
	}

	public GGame getGGame() {
		return gGame;
	}

	/** Updates the current GGame. Called by client */
	public void updateGGame(Game game) throws GameException {
		this.gGame = new GGame(game, views[cur]);
		updateAlpha(this.alpha);
		gGame.setView(views[cur]);
		gGame.setPlayer(gGame.getGame().getAvatars()[playerID]);
		((GameView) views[this.getGameView()]).updateGamePanel(this);
		((MapView) views[this.getMapView()]).updateMapPanel(this);

	}

	public void setGGame(Game game) throws GameException {
		this.gGame = new GGame(game, views[cur]);
		views[this.getGameView()].initialise();
		views[this.getMapView()].initialise();
		startDayNightCycle();
	}

	/**
	 * Starts day and night cycle for game
	 */
	private void startDayNightCycle() {
		java.util.Timer timer = new java.util.Timer();
		dnc = new DayNightCycle(this);
		timer.scheduleAtFixedRate(dnc, 0, CLOCK_TICK * 10);
	}

	/**
	 * Updates the alpha for MapView and GameView
	 * 
	 * @param alpha
	 *            The transparency of the rect to be drawn over game
	 */
	public void updateAlpha(int alpha) {
		this.alpha = alpha;
		this.gGame.setAlpha(alpha);
	}

	/**
	 * Calls Client.handleAction(). This method is called by performAction
	 * button in GameView
	 */
	public void performAction() {
		client.handleAction();
	}

	public void sendCraftedItem(String item) {
		client.sendCraftedItem(item);
	}

	public void sendExitSignal() {
		client.sendExitSignal(playerID);
	}

	public int getPlayerID() {
		return playerID;
	}

	public void setPlayerID(int playerID) {
		this.playerID = playerID;
	}

	public View getView(int viewIndex) {
		return views[viewIndex];
	}

	public void setIsHost(boolean isHost) {
		this.isHost = isHost;
	}

	public boolean isHost() {
		return isHost;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	/** Disconnects client from game */
	public void disconnectClient() {
		client.disconnectClient();
	}

	@Override
	public void repaint() {
		super.repaint();
	}

}
