/* File: GameView.java
 * 
 * Authors			ID
 * Edward Kelly 	300334192
 * Linus Go			300345571
 * 
 * Date				Author			Modification
 * 28 Sep 16		Edward Kelly	created class
 * 1 Oct 16			Linus Go		migrated buttons from TestWindow into this.
 * 3 Oct 16			Edward Kelly	now resizes game panel
 * 5 Oct 16			Linus Go		added bag and pocket drawing code.
 * 6 Oct 16			Edward Kelly	added call for performaction
 * 9 Oct 16			Linus Go		added money text field, and a shitload of comments.
 */
package missing.ui.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

import missing.game.characters.Player;
import missing.ui.controller.VControl;
import missing.ui.controller.VControl.View;
import missing.ui.frames.HandJFrame;
import missing.ui.panels.GamePanel;

/**
 * Represents the View which displays the GamePanel and other components to be
 * displayed when playing the game
 *
 */
@SuppressWarnings("serial")
public class GameView extends View {
	/*Holds an instance of the gamePanel */
	private GamePanel gamePanel;
	/*JButtons for the GameView. */
	private JButton btnViewMap;
	private JButton btnPlayersBag;
	private JButton btnDoAction;
	/*Text Fields to represent the name, and money amounts and the current time*/
	private JTextField nameField;
	private JTextField moneyField;
	public static JTextField timeField; //THIS IS STATIC BECAUSE THIS needs to be updated every call to run() - see DayNightCycle
	
	/*List of Players Online. */
	private JList<String> playersOnline;
	
	/*The Side Panel */
	private JPanel ctrlPanel; 
	
	/*Window that represents the Players Bag */
	private HandJFrame bagFrame;

	private final Color BACKGROUND_COLOR = Color.black;

	private Player currentPlayer;
	private int id;

	private final double BTN_WEIGHT = 0.05;
	
	/**
	 * Creates a new instance of a GameView with the current controller.
	 * @param controller
	 */
	public GameView(VControl controller) {
		super(controller);
	}
	
	/**
	 * Initializes and sets up the GameView. The GameView is laid out using GridBagConstraints, and uses <code>BTN_WEIGHT</code> fields to govern
	 * the size of the buttons. It also relies on the current state of the controller to display the correct information to the various <code>JTextField</code>s
	 */
	@Override
	public void initialise() {
		id = controller.getPlayerID();
		currentPlayer = controller.getGGame().getGame().getAvatars()[id];
		setLayout(new BorderLayout());
		gamePanel = new GamePanel(controller);
		ctrlPanel = new JPanel();

		GridBagLayout gbl_panel = new GridBagLayout();

		gbl_panel.columnWidths = new int[] { 89, 0 };
		gbl_panel.rowHeights = new int[] { 23, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 1.0, Double.MIN_VALUE };
		ctrlPanel.setLayout(gbl_panel);

		gamePanel.setBackground(BACKGROUND_COLOR);
		this.setBackground(BACKGROUND_COLOR);
		ctrlPanel.setBackground(BACKGROUND_COLOR);
		
		JLabel lblMoney = new JLabel("Players Money");
		lblMoney.setForeground(Color.WHITE);
		GridBagConstraints gbcMoney = new GridBagConstraints();
		gbcMoney.insets = new Insets(0, 0, 5, 0);
		gbcMoney.gridx = 0;
		gbcMoney.gridy = 1;
		ctrlPanel.add(lblMoney, gbcMoney);
		
		moneyField = new JTextField();
		moneyField.setFocusable(false);
		moneyField.setEditable(false);
		
		GridBagConstraints gbc_moneyField = new GridBagConstraints();
		gbc_moneyField.fill = GridBagConstraints.HORIZONTAL;
		gbc_moneyField.insets = new Insets(0, 0, 5, 0);
		gbc_moneyField.gridx = 0;
		gbc_moneyField.gridy = 2;
		ctrlPanel.add(moneyField, gbc_moneyField);
		
		
		JLabel lblTime = new JLabel("Current Time");
		lblTime.setForeground(Color.WHITE);
		GridBagConstraints gbc_time = new GridBagConstraints();
		gbc_time.insets = new Insets(0, 0, 5, 0);
		gbc_time.gridx = 0;
		gbc_time.gridy = 3;
		ctrlPanel.add(lblTime, gbc_time);

		timeField = new JTextField();
		timeField.setEditable(false);
		timeField.setFocusable(false);
		
		GridBagConstraints gbc_timeField = new GridBagConstraints();
		gbc_timeField.fill = GridBagConstraints.HORIZONTAL;
		gbc_timeField.insets = new Insets(0, 0, 5, 0);
		gbc_timeField.gridx = 0;
		gbc_timeField.gridy = 4;
		ctrlPanel.add(timeField, gbc_timeField);
		timeField.setColumns(10);
		
		
		JLabel lblCurrentPlayer = new JLabel("Current Player");
		lblCurrentPlayer.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblCurrentPlayer = new GridBagConstraints();
		gbc_lblCurrentPlayer.insets = new Insets(0, 0, 5, 0);
		gbc_lblCurrentPlayer.gridx = 0;
		gbc_lblCurrentPlayer.gridy = 5;
		ctrlPanel.add(lblCurrentPlayer, gbc_lblCurrentPlayer);

		nameField = new JTextField();
		nameField.setEditable(false);
		nameField.setText("" + currentPlayer.getName());// curPlayer.getName());

		GridBagConstraints gbc_txtName = new GridBagConstraints();
		gbc_txtName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtName.insets = new Insets(0, 0, 5, 0);
		gbc_txtName.gridx = 0;
		gbc_txtName.gridy = 6;
		ctrlPanel.add(nameField, gbc_txtName);
		nameField.setColumns(10);

		btnDoAction = new JButton("Do Action");
		btnDoAction.setBackground(Color.yellow);
		btnDoAction.setFocusable(false);
		GridBagConstraints gbc_btnDoAction = new GridBagConstraints();
		gbc_btnDoAction.insets = new Insets(0, 0, 5, 0);
		gbc_btnDoAction.fill = GridBagConstraints.BOTH;
		gbc_btnDoAction.gridx = 0;
		gbc_btnDoAction.gridy = 7;
		gbc_btnDoAction.anchor = GridBagConstraints.NORTH;
		gbc_btnDoAction.weighty = BTN_WEIGHT;

		ctrlPanel.add(btnDoAction, gbc_btnDoAction);

		btnViewMap = new JButton("View Map");
		btnViewMap.setBackground(Color.yellow);
		btnViewMap.setFocusable(false);
		GridBagConstraints gbc_btnViewMap = new GridBagConstraints();
		gbc_btnViewMap.insets = new Insets(0, 0, 5, 0);
		gbc_btnViewMap.fill = GridBagConstraints.BOTH;
		gbc_btnViewMap.gridx = 0;
		gbc_btnViewMap.gridy = 8;
		gbc_btnViewMap.anchor = GridBagConstraints.NORTH;

		gbc_btnViewMap.weighty = BTN_WEIGHT;
		ctrlPanel.add(btnViewMap, gbc_btnViewMap);

		btnPlayersBag = new JButton("Players Bag");
		btnPlayersBag.setBackground(Color.yellow);
		btnPlayersBag.setFocusable(false);
		GridBagConstraints gbc_btnPlayersBag = new GridBagConstraints();
		gbc_btnPlayersBag.insets = new Insets(0, 0, 5, 0);
		gbc_btnPlayersBag.fill = GridBagConstraints.BOTH;
		gbc_btnPlayersBag.gridx = 0;
		gbc_btnPlayersBag.gridy = 9;
		gbc_btnPlayersBag.weighty = BTN_WEIGHT;
		gbc_btnPlayersBag.anchor = GridBagConstraints.NORTH;
		ctrlPanel.add(btnPlayersBag, gbc_btnPlayersBag);

		JLabel lblPlayersOnline = new JLabel("Players Online");
		lblPlayersOnline.setForeground(Color.WHITE);
		GridBagConstraints gbc_playersOnline = new GridBagConstraints();
		gbc_playersOnline.insets = new Insets(0, 0, 5, 0);
		gbc_playersOnline.gridx = 0;
		gbc_playersOnline.gridy = 10;
		ctrlPanel.add(lblPlayersOnline, gbc_playersOnline);
		
		this.playersOnline = getPlayerNames(); //Set the JTextField.
		playersOnline.setFocusable(false);
		GridBagConstraints gbc_players = new GridBagConstraints();
		gbc_players.insets = new Insets(0, 0, 5, 0);
		gbc_players.gridx = 0;
		gbc_players.gridy = 11;
		gbc_players.weighty = BTN_WEIGHT * 4;
		gbc_players.fill = GridBagConstraints.BOTH;
		ctrlPanel.add(playersOnline, gbc_players);

		this.add(gamePanel, BorderLayout.CENTER);
		this.add(ctrlPanel, BorderLayout.EAST);
		this.addActionListeners();

	}

	/**
	 * Helper method that grabs all of the players online and displays it onto
	 * the JList. This should be called whenever the list of players changes.
	 */
	private JList<String> getPlayerNames() {
		Player[] players = controller.getGGame().getGame().getAvatars();
		String[] names = new String[players.length];

		for (int i = 0; i != players.length; ++i) {
			names[i] = players[i].getName();
		}
		return new JList<String>(names);
	}

	/**
	 * Action Listeners for the various Buttons. This is called in the constructor of the game.
	 */
	private void addActionListeners() {
		currentPlayer = controller.getGGame().getGame().getAvatars()[id];

		btnDoAction.addActionListener(e -> {
			controller.requestFocus();
			controller.performAction();
		});

		btnPlayersBag.addActionListener(e -> {
			controller.requestFocus();
			bagFrame = new HandJFrame(currentPlayer.getBag(), currentPlayer.getPocket());
			bagFrame.setVisible(true);
		});

		btnViewMap.addActionListener(e -> {
			// don't want controller to request focus because
			// shouldn't be able to move in MapView
			controller.changeView(controller.getMapView());
		});
	}

	/**
	 * Updates the GamePanel with the controller with the updated game
	 * 
	 * @param controller - updated controller
	 */
	public void updateGamePanel(VControl controller) {
		this.controller = controller;
		moneyField.setText("COINS: " + currentPlayer.getMoney());
		playersOnline = getPlayerNames();
		gamePanel.setController(controller);
		gamePanel.initialise();
	} 
	
	
	@Override
	public void setFocus() {
		gamePanel.setFocusable(true);
	}

}
