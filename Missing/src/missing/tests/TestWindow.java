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
import missing.ui.panels.GamePanel;

@SuppressWarnings("serial")
public class TestWindow extends JFrame implements KeyListener {
	private final int panelWd = 800;
	private final int panelHt = 600;

	private JTextField txtName;
	private JTextField txtPanning;
	private JButton btnUp;
	private JButton btnDown;
	private JButton btnLeft;
	private JButton btnRight;

	private Player curPlayer;
	// holds a current instance of the game.
	private Game gameinstance;
	// holds the gamepanel renderer.
	private GamePanel gamePanel;

	public TestWindow(Game game, World w, Player p) {
		super("Test Panel");
		this.addKeyListener(this);
		this.setVisible(true);
		this.setSize(panelWd, panelHt);
		this.gameinstance = game;
		this.curPlayer = p;

		JPanel panel = new JPanel();
		panel.setSize(500, panelHt);
		System.out.println(panel.getWidth() + " height: " + panel.getHeight());
		getContentPane().setLayout(new BorderLayout(0, 0));
		getContentPane().add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();

		gbl_panel.columnWidths = new int[] { 89, 0 };
		gbl_panel.rowHeights = new int[] { 23, 0, 0, 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		JLabel lblCurrentPlayer = new JLabel("Current Player");
		GridBagConstraints gbc_lblCurrentPlayer = new GridBagConstraints();
		gbc_lblCurrentPlayer.insets = new Insets(0, 0, 5, 0);
		gbc_lblCurrentPlayer.gridx = 0;
		gbc_lblCurrentPlayer.gridy = 0;
		panel.add(lblCurrentPlayer, gbc_lblCurrentPlayer);

		txtName = new JTextField();
		txtName.setText(curPlayer.getName());

		GridBagConstraints gbc_txtName = new GridBagConstraints();
		gbc_txtName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtName.insets = new Insets(0, 0, 5, 0);
		gbc_txtName.gridx = 0;
		gbc_txtName.gridy = 1;
		panel.add(txtName, gbc_txtName);
		txtName.setColumns(10);

		txtPanning = new JTextField();
		txtPanning.setText(curPlayer.getWorldLocation().toString());
		GridBagConstraints gbc_txtPanning = new GridBagConstraints();
		gbc_txtPanning.insets = new Insets(0, 0, 5, 0);
		gbc_txtPanning.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtPanning.gridx = 0;
		gbc_txtPanning.gridy = 3;
		panel.add(txtPanning, gbc_txtPanning);
		txtPanning.setColumns(10);

		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setLayout(null);
		GridBagConstraints gbc_layeredPane = new GridBagConstraints();
		gbc_layeredPane.fill = GridBagConstraints.BOTH;
		gbc_layeredPane.gridx = 0;
		gbc_layeredPane.gridy = 4;
		panel.add(layeredPane, gbc_layeredPane);

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

		gamePanel = new GamePanel(game, curPlayer);
		getContentPane().add(gamePanel, BorderLayout.WEST);

		setupActionListeners();
		pack();
	}

	private void setupActionListeners() {
		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("pressing up");
				try {
					gameinstance.movePlayer(0, Direction.NORTH);
					gamePanel.revalidate();
					txtPanning.setText("Tile " + gameinstance.getAvatars()[0].getTileLocation().toString() + "\n World"
							+ gameinstance.getAvatars()[0].getWorldLocation().toString());
					gamePanel.updateNodeRender(); // TODO Added this!
					gamePanel.repaint();
				} catch (GameException e1) {
					e1.printStackTrace();
				}
			}
		});

		btnDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					gameinstance.movePlayer(0, Direction.SOUTH);
					gamePanel.revalidate();
					txtPanning.setText("Tile " + gameinstance.getAvatars()[0].getTileLocation().toString() + "\n World"
							+ gameinstance.getAvatars()[0].getWorldLocation().toString());
					gamePanel.updateNodeRender(); // TODO Added this!
					gamePanel.repaint();
				} catch (GameException e1) {
					e1.printStackTrace();
				}
			}
		});

		btnLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("pressing left");
				try {
					gameinstance.movePlayer(0, Direction.WEST);
					gamePanel.revalidate();
					txtPanning.setText("Tile " + gameinstance.getAvatars()[0].getTileLocation().toString() + "\n World"
							+ gameinstance.getAvatars()[0].getWorldLocation().toString());
					gamePanel.updateNodeRender(); // TODO Added this!
					gamePanel.repaint();
				} catch (GameException e1) {
					e1.printStackTrace();
				}
			}
		});

		btnRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("pressing right");
				try {
					gameinstance.movePlayer(0, Direction.EAST);
					gamePanel.revalidate();
					txtPanning.setText("Tile " + gameinstance.getAvatars()[0].getTileLocation().toString() + "\n World"
							+ gameinstance.getAvatars()[0].getWorldLocation().toString());
					gamePanel.updateNodeRender(); // TODO Added this!
					gamePanel.repaint();				} catch (GameException e1) {
						e1.printStackTrace();
					}
			}
		});
	}


	/*These events are moving events that should be called whenever a button or key is being pressed */

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
		}
		gamePanel.revalidate();
		txtPanning.setText("Tile " + gameinstance.getAvatars()[0].getTileLocation().toString() + "\n World"
				+ gameinstance.getAvatars()[0].getWorldLocation().toString());
		gamePanel.updateNodeRender(); // TODO Added this!
		gamePanel.repaint();
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
			case KeyEvent.VK_UP:
				System.out.println("up");
				this.moveEvent(0, Direction.NORTH);
				break;
			case KeyEvent.VK_DOWN:
				System.out.println("down");
				this.moveEvent(0, Direction.SOUTH);
				break;
			case KeyEvent.VK_RIGHT:
				System.out.println("right");
				this.moveEvent(0, Direction.EAST);
				break;
			case KeyEvent.VK_LEFT:
				System.out.println("left");
				this.moveEvent(0, Direction.WEST);
				break;
			}
		}catch(GameException g){

		}

	}

	@Override
	public void keyReleased(KeyEvent arg0) {}

	@Override
	public void keyTyped(KeyEvent arg0) {}
}
