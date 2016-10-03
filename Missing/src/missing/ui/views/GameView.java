/* File: GameView.java
 * 
 * Authors			ID
 * Edward Kelly 	300334192
 * 
 * Date				Author			Modification
 * 28 Sep 16		Edward Kelly	created class
 * 1 Oct 16			Linus Go		migrated buttons from TestWindow into this.
 * 3 Oct 16			Edward Kelly	now resizes game panel
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

import missing.ui.controller.VControl;
import missing.ui.controller.VControl.View;
import missing.ui.panels.GamePanel;

/**
 * Represents the View which displays the GamePanel and other
 * components to be displayed when playing the game
 *
 */
public class GameView extends View{
	private GamePanel gamePanel;
	
	private JButton btnTurnL;
	private JButton btnTurnR;
	private JButton btnUp;
	private JButton btnDown;
	private JButton btnLeft;
	private JButton btnRight;
	private JButton btnViewMap;
	private JButton btnPlayersBag;
	private JButton btnDoAction;
	private JPanel ctrlPanel;
	
	private JLabel lblHealthField;
	private JLabel lblPlayerWorldPos;
	private JLabel lblPlayerTilePos;
	private JLabel lblPlayerOrientation;
	
	private JTextField nameField;
	private JTextField tilePos;
	private JTextField worldPos;
	private JTextField healthField;
	private JTextField playerOrientation;
	
	private int playerID;
	
	//TODO: 
	public GameView(VControl controller) {
		super(controller);
	}

	@Override
	public void initialise() {
		setLayout(new BorderLayout());
		gamePanel = new GamePanel(controller);
		gamePanel.initialise();
		ctrlPanel = new JPanel();
		
		GridBagLayout gbl_panel = new GridBagLayout();

		gbl_panel.columnWidths = new int[] { 89, 0 };
		gbl_panel.rowHeights = new int[] { 23, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		ctrlPanel.setLayout(gbl_panel);
		ctrlPanel.setBackground(Color.gray.darker());
		JLabel lblCurrentPlayer = new JLabel("Current Player");
		GridBagConstraints gbc_lblCurrentPlayer = new GridBagConstraints();
		gbc_lblCurrentPlayer.insets = new Insets(0, 0, 5, 0);
		gbc_lblCurrentPlayer.gridx = 0;
		gbc_lblCurrentPlayer.gridy = 0;
		ctrlPanel.add(lblCurrentPlayer, gbc_lblCurrentPlayer);

		nameField = new JTextField();
		nameField.setText(""+controller.getPlayerID());//curPlayer.getName());

		GridBagConstraints gbc_txtName = new GridBagConstraints();
		gbc_txtName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtName.insets = new Insets(0, 0, 5, 0);
		gbc_txtName.gridx = 0;
		gbc_txtName.gridy = 1;
		ctrlPanel.add(nameField, gbc_txtName);
		nameField.setColumns(10);
		
		btnTurnL = new JButton("TL");
		GridBagConstraints gbc_btnTurnL = new GridBagConstraints();
		gbc_btnTurnL.insets = new Insets(0, 0, 5, 0);
		gbc_btnTurnL.gridx = 0;
		gbc_btnTurnL.gridy = 3;
		ctrlPanel.add(btnTurnL, gbc_btnTurnL);
		
		btnTurnR = new JButton("TR");
		GridBagConstraints gbc_btnTurnR = new GridBagConstraints();
		gbc_btnTurnR.insets = new Insets(0, 0, 5, 0);
		gbc_btnTurnR.gridx = 0;
		gbc_btnTurnR.gridy = 4;
		ctrlPanel.add(btnTurnR, gbc_btnTurnR);
		
		lblHealthField = new JLabel("Player Health");
		lblHealthField.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblHealthField = new GridBagConstraints();
		gbc_lblHealthField.anchor = GridBagConstraints.WEST;
		gbc_lblHealthField.insets = new Insets(0, 0, 5, 0);
		gbc_lblHealthField.gridx = 0;
		gbc_lblHealthField.gridy = 5;
		ctrlPanel.add(lblHealthField, gbc_lblHealthField);
		
		healthField = new JTextField();
		healthField.setColumns(10);
		GridBagConstraints gbc_healthField = new GridBagConstraints();
		gbc_healthField.insets = new Insets(0, 0, 5, 0);
		gbc_healthField.fill = GridBagConstraints.HORIZONTAL;
		gbc_healthField.gridx = 0;
		gbc_healthField.gridy = 6;
		ctrlPanel.add(healthField, gbc_healthField);
		
		lblPlayerWorldPos = new JLabel("Player Tile Pos");
		GridBagConstraints gbc_lblPlayerWorldPos = new GridBagConstraints();
		gbc_lblPlayerWorldPos.insets = new Insets(0, 0, 5, 0);
		gbc_lblPlayerWorldPos.gridx = 0;
		gbc_lblPlayerWorldPos.gridy = 7;
		ctrlPanel.add(lblPlayerWorldPos, gbc_lblPlayerWorldPos);
		
		tilePos = new JTextField();
		GridBagConstraints gbc_playerHealthfield = new GridBagConstraints();
		gbc_playerHealthfield.insets = new Insets(0, 0, 5, 0);
		gbc_playerHealthfield.fill = GridBagConstraints.HORIZONTAL;
		gbc_playerHealthfield.gridx = 0;
		gbc_playerHealthfield.gridy = 8;
		ctrlPanel.add(tilePos, gbc_playerHealthfield);
		tilePos.setColumns(10);
		
		lblPlayerTilePos = new JLabel("Player World Pos");
		GridBagConstraints gbc_lblPlayerTilePos = new GridBagConstraints();
		gbc_lblPlayerTilePos.insets = new Insets(0, 0, 5, 0);
		gbc_lblPlayerTilePos.gridx = 0;
		gbc_lblPlayerTilePos.gridy = 9;
		ctrlPanel.add(lblPlayerTilePos, gbc_lblPlayerTilePos);
		
		worldPos = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 5, 0);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 0;
		gbc_textField_1.gridy = 10;
		ctrlPanel.add(worldPos, gbc_textField_1);
		worldPos.setColumns(10);
		
		lblPlayerOrientation = new JLabel("Player Orientation");
		GridBagConstraints gbc_lblPlayerOrientation = new GridBagConstraints();
		gbc_lblPlayerOrientation.insets = new Insets(0, 0, 5, 0);
		gbc_lblPlayerOrientation.gridx = 0;
		gbc_lblPlayerOrientation.gridy = 11;
		ctrlPanel.add(lblPlayerOrientation, gbc_lblPlayerOrientation);
		
		playerOrientation = new JTextField();
		playerOrientation.setColumns(10);
		GridBagConstraints gbc_orientationField = new GridBagConstraints();
		gbc_orientationField.insets = new Insets(0, 0, 5, 0);
		gbc_orientationField.fill = GridBagConstraints.HORIZONTAL;
		gbc_orientationField.gridx = 0;
		gbc_orientationField.gridy = 12;
		ctrlPanel.add(playerOrientation, gbc_orientationField);
		
		btnDoAction = new JButton("Do Action");
		GridBagConstraints gbc_btnDoAction = new GridBagConstraints();
		gbc_btnDoAction.insets = new Insets(0, 0, 5, 0);
		gbc_btnDoAction.gridx = 0;
		gbc_btnDoAction.gridy = 13;
		ctrlPanel.add(btnDoAction, gbc_btnDoAction);
		
		btnViewMap = new JButton("View Map");
		GridBagConstraints gbc_btnViewMap = new GridBagConstraints();
		gbc_btnViewMap.insets = new Insets(0, 0, 5, 0);
		gbc_btnViewMap.gridx = 0;
		gbc_btnViewMap.gridy = 14;
		ctrlPanel.add(btnViewMap, gbc_btnViewMap);
		
		btnPlayersBag = new JButton("Players Bag");
		GridBagConstraints gbc_btnPlayersBag = new GridBagConstraints();
		gbc_btnPlayersBag.insets = new Insets(0, 0, 5, 0);
		gbc_btnPlayersBag.gridx = 0;
		gbc_btnPlayersBag.gridy = 15;
		ctrlPanel.add(btnPlayersBag, gbc_btnPlayersBag);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setLayout(null);
		GridBagConstraints gbc_layeredPane = new GridBagConstraints();
		gbc_layeredPane.fill = GridBagConstraints.BOTH;
		gbc_layeredPane.gridx = 0;
		gbc_layeredPane.gridy = 16;
		ctrlPanel.add(layeredPane, gbc_layeredPane);

		btnUp = new JButton("Up");
		btnUp.setBounds(65, 30, 70, 70);
		layeredPane.add(btnUp);

		btnLeft = new JButton("Left");
		btnLeft.setBounds(30, 100, 70, 70);
		layeredPane.add(btnLeft);

		btnRight = new JButton("Right");
		btnRight.setBounds(100, 100, 70, 70);
		layeredPane.add(btnRight);

		btnDown = new JButton("Down");
		btnDown.setBounds(65, 170, 70, 70);
		layeredPane.add(btnDown);
		
		
		this.add(gamePanel,BorderLayout.CENTER);
		this.add(ctrlPanel,BorderLayout.EAST);
		this.addActionListeners();
	}
	
	/**
	 * Action Listeners for the various Buttons.
	 */
	private void addActionListeners(){
		System.out.println("added action listeners");
		btnUp.addActionListener(e->{
			
		});
		
		btnDown.addActionListener(e->{
			
		});
		
		btnLeft.addActionListener(e->{
		});
		
		btnRight.addActionListener(e->{
			
		});
		
		btnTurnL.addActionListener(e->{
			
		});
		
		btnTurnR.addActionListener(e->{
			
		});
		
		btnDoAction.addActionListener(e->{
			
		});
		
		btnPlayersBag.addActionListener(e->{
			
		});
		
		btnViewMap.addActionListener(e->{
			controller.changeView(controller.getMapView());
		});
		
		
	}
	
	
	/**
	 * Updates the GamePanel with the controller with the updated game
	 * @param controller updated controller
	 */
	public void updateGamePanel(VControl controller){
		this.controller = controller;
		gamePanel.setController(controller);
		gamePanel.initialise();
	}

	@Override
	public void setFocus() {
		gamePanel.setFocusable(true);
	}

}
