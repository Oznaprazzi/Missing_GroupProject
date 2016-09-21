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
	private static class TestWindow2 extends JFrame {

	}

}
