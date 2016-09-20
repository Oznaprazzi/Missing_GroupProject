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
import java.awt.GridBagLayout;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JPanel;

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
		private final int panelWd = 800;
		private final int panelHt = 600;

		public TestWindow(World w, Player p) {
			super("Test Panel");
			this.setVisible(true);
			gPanel = new GamePanel(p);
			this.setSize(panelWd, panelHt);
			System.out.println();
			this.getContentPane().add(gPanel);
			gPanel.setLayout(new BorderLayout(0, 0));

			JPanel panel = new JPanel();
			getContentPane().add(panel, BorderLayout.EAST);
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[] { 0 };
			gbl_panel.rowHeights = new int[] { 0 };
			gbl_panel.columnWeights = new double[] { Double.MIN_VALUE };
			gbl_panel.rowWeights = new double[] { Double.MIN_VALUE };
			panel.setLayout(gbl_panel);
			pack();
		}
	}

}
