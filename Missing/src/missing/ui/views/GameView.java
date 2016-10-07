/* File: GameView.java
 * 
 * Authors			ID
 * Edward Kelly 	300334192
 * 
 * Date				Author			Modification
 * 28 Sep 16		Edward Kelly	created class
 * 1 Oct 16			Linus Go		migrated buttons from TestWindow into this.
 * 3 Oct 16			Edward Kelly	now resizes game panel
 * 5 Oct 16			Linus Go		added bag and pocket drawing code.
 * 6 Oct 16			Edward Kelly	added call for performaction
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

	private GamePanel gamePanel;

	private JButton btnViewMap;
	private JButton btnPlayersBag;
	private JButton btnDoAction;

	private JTextField nameField;
	private JList<String> playersOnline;

	private JPanel ctrlPanel;
	private HandJFrame bagFrame;

	private final Color BACKGROUND_COLOR = Color.black;

	private Player currentPlayer;
	private int id;

	private final double BTN_WEIGHT = 0.05;

	public GameView(VControl controller) {
		super(controller);
	}

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

		JLabel lblCurrentPlayer = new JLabel("Current Player");
		lblCurrentPlayer.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblCurrentPlayer = new GridBagConstraints();
		gbc_lblCurrentPlayer.insets = new Insets(0, 0, 5, 0);
		gbc_lblCurrentPlayer.gridx = 0;
		gbc_lblCurrentPlayer.gridy = 0;
		ctrlPanel.add(lblCurrentPlayer, gbc_lblCurrentPlayer);

		nameField = new JTextField();
		nameField.setEditable(false);
		nameField.setText("" + currentPlayer.getName());// curPlayer.getName());

		GridBagConstraints gbc_txtName = new GridBagConstraints();
		gbc_txtName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtName.insets = new Insets(0, 0, 5, 0);
		gbc_txtName.gridx = 0;
		gbc_txtName.gridy = 1;
		ctrlPanel.add(nameField, gbc_txtName);
		nameField.setColumns(10);

		btnDoAction = new JButton("Do Action");
		btnDoAction.setBackground(Color.yellow);
		btnDoAction.setFocusable(false);
		GridBagConstraints gbc_btnDoAction = new GridBagConstraints();
		gbc_btnDoAction.insets = new Insets(0, 0, 5, 0);
		gbc_btnDoAction.fill = GridBagConstraints.BOTH;
		gbc_btnDoAction.gridx = 0;
		gbc_btnDoAction.gridy = 2;
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
		gbc_btnViewMap.gridy = 3;
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
		gbc_btnPlayersBag.gridy = 4;
		gbc_btnPlayersBag.weighty = BTN_WEIGHT;
		gbc_btnPlayersBag.anchor = GridBagConstraints.NORTH;
		ctrlPanel.add(btnPlayersBag, gbc_btnPlayersBag);

		JLabel lblPlayersOnline = new JLabel("Players Online");
		lblPlayersOnline.setForeground(Color.WHITE);
		GridBagConstraints gbc_playersOnline = new GridBagConstraints();
		gbc_playersOnline.insets = new Insets(0, 0, 5, 0);
		gbc_playersOnline.gridx = 0;
		gbc_playersOnline.gridy = 5;
		ctrlPanel.add(lblPlayersOnline, gbc_playersOnline);

		this.playersOnline = getPlayerNames(); // Set the JTextField.
		playersOnline.setFocusable(false);
		GridBagConstraints gbc_players = new GridBagConstraints();
		gbc_players.insets = new Insets(0, 0, 5, 0);
		gbc_players.gridx = 0;
		gbc_players.gridy = 6;
		gbc_players.weighty = BTN_WEIGHT * 4;
		gbc_players.fill = GridBagConstraints.BOTH;
		ctrlPanel.add(playersOnline, gbc_players);

		this.add(gamePanel, BorderLayout.CENTER);
		this.add(ctrlPanel, BorderLayout.EAST);
		this.addActionListeners();

	}

	/**
	 * Helper method that grabs all of the players online and displays it onto
	 * the JList. This should be called whenever the JList is updated or
	 * added/removed.
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
	 * Action Listeners for the various Buttons.
	 */
	private void addActionListeners() {
		currentPlayer = controller.getGGame().getGame().getAvatars()[id];

		btnDoAction.addActionListener(e -> {
			controller.requestFocus();
			controller.performAction();
		});

		btnPlayersBag.addActionListener(e -> {
			// TODO: need to fix. Currently only shows bag of first player.
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
	 * @param controller
	 *            updated controller
	 */
	public void updateGamePanel(VControl controller) {
		this.controller = controller;
		gamePanel.setController(controller);
		gamePanel.initialise();
		playersOnline = getPlayerNames();
	}

	@Override
	public void setFocus() {
		gamePanel.setFocusable(true);
	}

}
