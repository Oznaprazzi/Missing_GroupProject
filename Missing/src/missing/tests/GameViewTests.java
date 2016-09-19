package missing.tests;

import java.awt.Point;

import missing.datastorage.assetloader.XMLHandler;
import missing.game.Game;
import missing.game.characters.Player;
import missing.helper.GameException;

public class GameViewTests {
	public static void main(String[] args) {
		// XMLHandler received file name...
		String xmlFile = "items.xml";
		XMLHandler.filename = xmlFile;
		// Create dummy avatars
		Player[] avatars = { new Player("Chris", new Point(0, 0), new Point(9, 9)) };
		try {
			Game game = new Game(avatars);
		} catch (GameException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
	}
}
