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
 */
package missing.ui.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import missing.game.characters.Player;
import missing.tests.BagFrameTest;
import missing.ui.controller.VControl;
import missing.ui.controller.VControl.View;
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
	
	private JPanel ctrlPanel;
	private JLabel lblHealthField;
	
	private JTextField nameField;
	private JTextField healthField;
	
	private BagFrameTest bagFrame;
	private final Color BACKGROUND_COLOR = Color.black;
	private Player currentPlayer;
	private int id;
	
	public GameView(VControl controller) {
		super(controller);
		id = controller.getPlayerID();
	}

	@Override
	public void initialise() {
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

		lblHealthField = new JLabel("Player Health");
		lblHealthField.setHorizontalAlignment(SwingConstants.CENTER);
		lblHealthField.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblHealthField = new GridBagConstraints();
		gbc_lblHealthField.anchor = GridBagConstraints.WEST;
		gbc_lblHealthField.insets = new Insets(0, 0, 5, 0);
		gbc_lblHealthField.gridx = 0;
		gbc_lblHealthField.gridy = 2;
		ctrlPanel.add(lblHealthField, gbc_lblHealthField);

		healthField = new JTextField();
		healthField.setColumns(10);
		healthField.setText("" +currentPlayer.getHealth());
		GridBagConstraints gbc_healthField = new GridBagConstraints();
		gbc_healthField.insets = new Insets(0, 0, 5, 0);
		gbc_healthField.fill = GridBagConstraints.HORIZONTAL;
		gbc_healthField.gridx = 0;
		gbc_healthField.gridy = 3;
		ctrlPanel.add(healthField, gbc_healthField);

		btnDoAction = new JButton("Do Action");
		btnDoAction.setBackground(Color.yellow);
		GridBagConstraints gbc_btnDoAction = new GridBagConstraints();
		gbc_btnDoAction.insets = new Insets(0, 0, 5, 0);
		gbc_btnDoAction.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnDoAction.gridx = 0;
		gbc_btnDoAction.gridy = 4;
		ctrlPanel.add(btnDoAction, gbc_btnDoAction);

		btnViewMap = new JButton("View Map");
		btnViewMap.setBackground(Color.yellow);
		GridBagConstraints gbc_btnViewMap = new GridBagConstraints();
		gbc_btnViewMap.insets = new Insets(0, 0, 5, 0);
		gbc_btnViewMap.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnViewMap.gridx = 0;
		gbc_btnViewMap.gridy = 5;
		ctrlPanel.add(btnViewMap, gbc_btnViewMap);

		btnPlayersBag = new JButton("Players Bag");
		btnPlayersBag.setBackground(Color.yellow);
		GridBagConstraints gbc_btnPlayersBag = new GridBagConstraints();
		gbc_btnPlayersBag.insets = new Insets(0, 0, 5, 0);
		gbc_btnPlayersBag.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnPlayersBag.gridx = 0;
		gbc_btnPlayersBag.gridy = 6;
		ctrlPanel.add(btnPlayersBag, gbc_btnPlayersBag);

		this.add(gamePanel, BorderLayout.CENTER);
		this.add(ctrlPanel, BorderLayout.EAST);
		this.addActionListeners();
	}

	/**
	 * Action Listeners for the various Buttons.
	 */
	private void addActionListeners() {
		currentPlayer = controller.getGGame().getGame().getAvatars()[id];

		btnDoAction.addActionListener(e -> {
			controller.requestFocus();
			//TODO EDDY: need to implement.
		});

		btnPlayersBag.addActionListener(e -> {
			controller.requestFocus();
			
			bagFrame = new BagFrameTest(currentPlayer.getBag(), currentPlayer.getPocket());
			bagFrame.setVisible(true);
		});

		btnViewMap.addActionListener(e -> {
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
		this.healthField.setText(""+ controller.getGGame().getGame().getAvatars()[id].getHealth());
		gamePanel.setController(controller);
		gamePanel.initialise();
	}

	@Override
	public void setFocus() {
		gamePanel.setFocusable(true);
	}

}
