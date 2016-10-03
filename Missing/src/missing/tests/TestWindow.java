/*	File: TestWindow.java
 * 	Authors:
 * 	Linus Go			300345571
 *	Chris Rabe			300334207
 * 	Casey Huang			300316284
 * 	
 * 	Date				Author					Changes
 * 	20 Sep 16			Casey + Linus			created and worked on this
 * 	21 Sep 16			Chris Rabe				fixed moving bug...
 * 	23 Sep 16			Chris Rabe				fixed item rendering bug...
 *  26 Sep 16			Linus Go				Allowed WASD movement and fixed up side panel.
 */
package missing.tests;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import missing.game.Game;
import missing.game.characters.Player;
import missing.game.world.World;
import missing.game.world.nodes.WorldTile.TileObject.Direction;
import missing.helper.GameException;
import missing.helper.SignalException;
import missing.ui.panels.GamePanel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class TestWindow extends JFrame implements KeyListener {
	/*Dimensions for the Game Panels width and height */
	private final int panelWd = 800;
	private final int panelHt = 600;
	/*Swing components for this window */
	private JTextField nameField;
	private JTextField tilePos;
	private JTextField worldPos;
	private JTextField playerOrientation;
	private JLabel lblPlayerOrientation;
	private JLabel lblPlayerTilePos;
	private JLabel lblPlayerWorldPos;
	private JButton btnUp;
	private JButton btnDown;
	private JButton btnLeft;
	private JButton btnRight;
	private JButton btnViewMap;
	private JButton btnPlayersBag;
	
	/* The current player */
	private Player curPlayer;
	/*Holds a local instance of the game */
	private Game gameinstance;
	/* Holds the gamePanel renderer. */
	private GamePanel gamePanel;
	/* Holds the BagFrame renderer*/
	private BagFrameTest bagFrame;
	private JLabel lblHealthField;
	private JTextField healthField;
	
	public TestWindow(Game game, World w, Player p) {
		super("Test Panel");
		addKeyListener(this);
		setVisible(true);
		setSize(panelWd, panelHt);
		gameinstance = game;
		curPlayer = p;
		initializeGUI();
		setupActionListeners();
		pack();
	}
	/**
	 * Helper method to initialize the Windows GUI components.
	 */
	private void initializeGUI(){
		JPanel panel = new JPanel();
		panel.setSize(500, panelHt);
		getContentPane().setLayout(new BorderLayout(0, 0));
		getContentPane().add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();

		gbl_panel.columnWidths = new int[] { 89, 0 };
		gbl_panel.rowHeights = new int[] { 23, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		JLabel lblCurrentPlayer = new JLabel("Current Player");
		GridBagConstraints gbc_lblCurrentPlayer = new GridBagConstraints();
		gbc_lblCurrentPlayer.insets = new Insets(0, 0, 5, 0);
		gbc_lblCurrentPlayer.gridx = 0;
		gbc_lblCurrentPlayer.gridy = 0;
		panel.add(lblCurrentPlayer, gbc_lblCurrentPlayer);

		nameField = new JTextField();
		nameField.setText(curPlayer.getName());

		GridBagConstraints gbc_txtName = new GridBagConstraints();
		gbc_txtName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtName.insets = new Insets(0, 0, 5, 0);
		gbc_txtName.gridx = 0;
		gbc_txtName.gridy = 1;
		panel.add(nameField, gbc_txtName);
		nameField.setColumns(10);
		
		lblHealthField = new JLabel("Player Health");
		lblHealthField.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblHealthField = new GridBagConstraints();
		gbc_lblHealthField.anchor = GridBagConstraints.WEST;
		gbc_lblHealthField.insets = new Insets(0, 0, 5, 0);
		gbc_lblHealthField.gridx = 0;
		gbc_lblHealthField.gridy = 5;
		panel.add(lblHealthField, gbc_lblHealthField);
		
		healthField = new JTextField();
		healthField.setColumns(10);
		GridBagConstraints gbc_healthField = new GridBagConstraints();
		gbc_healthField.insets = new Insets(0, 0, 5, 0);
		gbc_healthField.fill = GridBagConstraints.HORIZONTAL;
		gbc_healthField.gridx = 0;
		gbc_healthField.gridy = 6;
		panel.add(healthField, gbc_healthField);
		
		lblPlayerWorldPos = new JLabel("Player Tile Pos");
		GridBagConstraints gbc_lblPlayerWorldPos = new GridBagConstraints();
		gbc_lblPlayerWorldPos.insets = new Insets(0, 0, 5, 0);
		gbc_lblPlayerWorldPos.gridx = 0;
		gbc_lblPlayerWorldPos.gridy = 7;
		panel.add(lblPlayerWorldPos, gbc_lblPlayerWorldPos);
		
		tilePos = new JTextField();
		GridBagConstraints gbc_playerHealthfield = new GridBagConstraints();
		gbc_playerHealthfield.insets = new Insets(0, 0, 5, 0);
		gbc_playerHealthfield.fill = GridBagConstraints.HORIZONTAL;
		gbc_playerHealthfield.gridx = 0;
		gbc_playerHealthfield.gridy = 8;
		panel.add(tilePos, gbc_playerHealthfield);
		tilePos.setColumns(10);
		
		lblPlayerTilePos = new JLabel("Player World Pos");
		GridBagConstraints gbc_lblPlayerTilePos = new GridBagConstraints();
		gbc_lblPlayerTilePos.insets = new Insets(0, 0, 5, 0);
		gbc_lblPlayerTilePos.gridx = 0;
		gbc_lblPlayerTilePos.gridy = 9;
		panel.add(lblPlayerTilePos, gbc_lblPlayerTilePos);
		
		worldPos = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 5, 0);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 0;
		gbc_textField_1.gridy = 10;
		panel.add(worldPos, gbc_textField_1);
		worldPos.setColumns(10);
		
		lblPlayerOrientation = new JLabel("Player Orientation");
		GridBagConstraints gbc_lblPlayerOrientation = new GridBagConstraints();
		gbc_lblPlayerOrientation.insets = new Insets(0, 0, 5, 0);
		gbc_lblPlayerOrientation.gridx = 0;
		gbc_lblPlayerOrientation.gridy = 11;
		panel.add(lblPlayerOrientation, gbc_lblPlayerOrientation);
		
		playerOrientation = new JTextField();
		playerOrientation.setColumns(10);
		GridBagConstraints gbc_orientationField = new GridBagConstraints();
		gbc_orientationField.insets = new Insets(0, 0, 5, 0);
		gbc_orientationField.fill = GridBagConstraints.HORIZONTAL;
		gbc_orientationField.gridx = 0;
		gbc_orientationField.gridy = 12;
		panel.add(playerOrientation, gbc_orientationField);
				
		btnViewMap = new JButton("View Map");
		btnViewMap.setFocusable(false);
		GridBagConstraints gbc_btnViewMap = new GridBagConstraints();
		gbc_btnViewMap.insets = new Insets(0, 0, 5, 0);
		gbc_btnViewMap.gridx = 0;
		gbc_btnViewMap.gridy = 14;
		panel.add(btnViewMap, gbc_btnViewMap);
		
		btnPlayersBag = new JButton("Players Bag");
		btnPlayersBag.setFocusable(false);
		GridBagConstraints gbc_btnPlayersBag = new GridBagConstraints();
		gbc_btnPlayersBag.insets = new Insets(0, 0, 5, 0);
		gbc_btnPlayersBag.gridx = 0;
		gbc_btnPlayersBag.gridy = 15;
		panel.add(btnPlayersBag, gbc_btnPlayersBag);
		
		JButton btnPlayersHand = new JButton("Players Hand");
		btnPlayersHand.setFocusable(false);
		GridBagConstraints gbc_btnPlayersHand = new GridBagConstraints();
		gbc_btnPlayersHand.insets = new Insets(0, 0, 5, 0);
		gbc_btnPlayersHand.gridx = 0;
		gbc_btnPlayersHand.gridy = 16;
		panel.add(btnPlayersHand, gbc_btnPlayersHand);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setLayout(null);
		GridBagConstraints gbc_layeredPane = new GridBagConstraints();
		gbc_layeredPane.fill = GridBagConstraints.BOTH;
		gbc_layeredPane.gridx = 0;
		gbc_layeredPane.gridy = 17;
		panel.add(layeredPane, gbc_layeredPane);

		btnUp = new JButton("Up");
		btnUp.setFocusable(false);
		btnUp.setBounds(65, 30, 70, 70);
		layeredPane.add(btnUp);

		btnLeft = new JButton("Left");
		btnLeft.setFocusable(false);
		btnLeft.setBounds(30, 100, 70, 70);
		layeredPane.add(btnLeft);

		btnRight = new JButton("Right");
		btnRight.setFocusable(false);
		btnRight.setBounds(100, 100, 70, 70);
		layeredPane.add(btnRight);

		btnDown = new JButton("Down");
		btnDown.setFocusable(false);
		btnDown.setBounds(65, 170, 70, 70);
		layeredPane.add(btnDown);

		gamePanel = new GamePanel(gameinstance, curPlayer);
		getContentPane().add(gamePanel, BorderLayout.WEST);
		gamePanel.updateNodeRender();
		gamePanel.repaint();
	}
	
	/**
	 * Used to set up the action listeners.
	 */
	private void setupActionListeners() {
		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					moveEvent(0, Direction.NORTH);
					repaintGamePanel();
				} catch (GameException e1) {
					e1.printStackTrace();
				}
			}
		});

		btnDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					moveEvent(0, Direction.SOUTH);
					repaintGamePanel();
				} catch (GameException e1) {
					e1.printStackTrace();
				}
			}
		});

		btnLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					moveEvent(0, Direction.WEST);
					repaintGamePanel();
				} catch (GameException e1) {
					e1.printStackTrace();
				}
			}
		});

		btnRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					moveEvent(0, Direction.EAST);
					gamePanel.revalidate();
					gamePanel.updateNodeRender(); 
					gamePanel.repaint();				
				} catch (GameException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnViewMap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("I'm supposed to do something cool!");
			}
		});
		
		btnPlayersBag.addActionListener(e->{
			bagFrame = new BagFrameTest(curPlayer.getBag(), curPlayer.getPocket());
			bagFrame.setVisible(true);
		});	
	}
	/*These events are moving events that should be called whenever a button or key is being pressed */
	
	/**
	 * Move the selected player one spot in a specific direction.
	 * @param int - playerID
	 * @param Direction - dir
	 * @throws GameException
	 */
	private void moveEvent(int playerID, Direction dir) throws GameException{
		switch(dir){
		case NORTH:
			gameinstance.movePlayer(playerID, Direction.NORTH);
			break;
		case SOUTH:
			gameinstance.movePlayer(playerID, Direction.SOUTH);
			break;
		case EAST:
			gameinstance.movePlayer(playerID, Direction.EAST);
			break;
		case WEST:
			gameinstance.movePlayer(playerID, Direction.WEST);
			break;
		default:
			break;
		}
		updateTextBarPositions();
		repaintGamePanel();

	}
	
	/**
	 * Call this method if you want the current player to rotate in the opposite direction.
	 * @param playerID
	 * @param dir
	 * @throws GameException
	 */
	private void rotateEvent(int playerID, Direction dir) throws GameException{
		switch(dir){
		case NORTH: case SOUTH:
			throw new GameException("Can't rotate a player if they're moving left or right!");
		case EAST:
		gameinstance.turnPlayer(playerID, Direction.EAST);
		break;
		case WEST:
		gameinstance.turnPlayer(playerID, Direction.WEST);
		break;
		default:
			break;
		}
		updateTextBarPositions();
		repaintGamePanel();
	}
	
	private void performActionEvent(int playerID) throws GameException{
		try {
			gameinstance.performAction(playerID);
			updateTextBarPositions();
			repaintGamePanel();
		} catch (SignalException e) {
			e.printStackTrace();
		}
	}
	
	private void repaintGamePanel(){
		gamePanel.revalidate();
		gamePanel.updateNodeRender();
		gamePanel.repaint();

	}
	
	private void updateTextBarPositions(){
		tilePos.setText("x: " + curPlayer.getTileLocation().getX() + " y: " + curPlayer.getTileLocation().getY());
		worldPos.setText("x: " + curPlayer.getWorldLocation().getX() + " y: " + curPlayer.getWorldLocation().getY());
		playerOrientation.setText(curPlayer.getOrientation().toString());
		healthField.setText("" + curPlayer.getHealth());
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(panelWd, panelHt);
	}



	@Override
	public void keyPressed(KeyEvent e){
		int keyID = e.getKeyCode();
		try{
			switch(keyID){
			case KeyEvent.VK_UP: case KeyEvent.VK_W:
				moveEvent(0, Direction.NORTH);
				break;
			case KeyEvent.VK_DOWN: case KeyEvent.VK_S:
				moveEvent(0, Direction.SOUTH);
				break;
			case KeyEvent.VK_RIGHT: case KeyEvent.VK_D:
				moveEvent(0, Direction.EAST);
				break;
			case KeyEvent.VK_LEFT: case KeyEvent.VK_A:
				moveEvent(0, Direction.WEST);
				break;
			case KeyEvent.VK_E:
				rotateEvent(0, Direction.EAST);
				break;
			case KeyEvent.VK_Q:
				rotateEvent(0, Direction.WEST);
				break;
			case KeyEvent.VK_F:
				this.performActionEvent(0);
				break;
			}
		}catch(GameException g){
			g.printStackTrace();
		}

	}

	@Override
	public void keyReleased(KeyEvent arg0) {}

	@Override
	public void keyTyped(KeyEvent arg0) {}
}
