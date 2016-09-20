/*	File: GameViewTests.java
 * 	Author:
 * 	Chris Rabe		300334207
 * 	Linus Go		300345571
 * 
 * 	Date			Author			Changes
 * 	20 Sep 16		Chris Rabe		created GameViewTests.java class
 * 	20 Sep 16		Linus Go 		made testing create a test window
 * 	20 Sep 16		Chris Rabe		moved TestWindow inside this class...
 */

package missing.tests;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import missing.datastorage.assetloader.XMLHandler;
import missing.game.Game;
import missing.game.characters.Player;
import missing.game.world.World;
import missing.helper.GameException;
import missing.ui.panels.GamePanel;

public class GameViewTests {
	public static void main(String[] args) {
		// XMLHandler received file name...
		String xmlFile = "items.xml";
		XMLHandler.filename = xmlFile;
		// Create an array of players
		Player[] avatars = { new Player("Chris", new Point(3, 3), new Point(9, 9)) };
		try {

			Game game = new Game(avatars);
			Player chris = avatars[0];
			World world = game.getWorld();
			new TestWindow(world, chris);
		} catch (GameException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
	}

	@SuppressWarnings("serial")
	private static class TestWindow extends JFrame {

		private GamePanel gPanel;
		private final int panelWd = 900;
		private final int panelHt = 600;
		private JTextField txtName;
		private JTextField txtPanning;
		
		public TestWindow(World w, Player p){
			super("Test Panel");
			this.setVisible(true);
			gPanel = new GamePanel(p);
			this.setSize(panelWd, panelHt);
			System.out.println();
			gPanel.setSize(panelHt, panelHt);
			this.getContentPane().add(gPanel);
			gPanel.setLayout(new BorderLayout(0, 0));
			
			JPanel panel = new JPanel();
			panel.setSize(300, panelHt);
			panel.setLocation(600, 0);
			System.out.println(panel.getWidth() + " height: " + panel.getHeight());
			getContentPane().add(panel, BorderLayout.EAST);
			GridBagLayout gbl_panel = new GridBagLayout();
			
			gbl_panel.columnWidths = new int[]{89, 0};
			gbl_panel.rowHeights = new int[]{23, 0, 0, 0, 0, 0};
			gbl_panel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
			gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
			panel.setLayout(gbl_panel);
			
			JLabel lblCurrentPlayer = new JLabel("Current Player");
			GridBagConstraints gbc_lblCurrentPlayer = new GridBagConstraints();
			gbc_lblCurrentPlayer.insets = new Insets(0, 0, 5, 0);
			gbc_lblCurrentPlayer.gridx = 0;
			gbc_lblCurrentPlayer.gridy = 0;
			panel.add(lblCurrentPlayer, gbc_lblCurrentPlayer);
			
			txtName = new JTextField();
			txtName.setText("Name");
			GridBagConstraints gbc_txtName = new GridBagConstraints();
			gbc_txtName.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtName.insets = new Insets(0, 0, 5, 0);
			gbc_txtName.gridx = 0;
			gbc_txtName.gridy = 1;
			panel.add(txtName, gbc_txtName);
			txtName.setColumns(10);
			
			JButton button = new JButton("New button");
			GridBagConstraints gbc_button = new GridBagConstraints();
			gbc_button.insets = new Insets(0, 0, 5, 0);
			gbc_button.anchor = GridBagConstraints.NORTHWEST;
			gbc_button.gridx = 0;
			gbc_button.gridy = 2;
			panel.add(button, gbc_button);
			
			txtPanning = new JTextField();
			txtPanning.setText("Panning");
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
			
			JButton btnUp = new JButton("Up");
			btnUp.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			btnUp.setBounds(45, 30, 70, 70);
			layeredPane.add(btnUp);
			
			JButton btnLeft = new JButton("Left");
			btnLeft.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			btnLeft.setBounds(0, 100, 70, 70);
			layeredPane.add(btnLeft);
			
			JButton btnRight = new JButton("Right");
			btnRight.setBounds(70, 100, 70, 70);
			layeredPane.add(btnRight);
			
			JButton btnDown = new JButton("Down");
			btnDown.setBounds(45, 130, 70, 70);
			layeredPane.add(btnDown);
			
			
			pack();
		}
		
		@Override
		public Dimension getPreferredSize(){
			return new Dimension(panelWd, panelHt);
		}
	}

}
