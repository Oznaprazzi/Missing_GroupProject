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

import java.awt.Point;

import missing.datastorage.assetloader.XMLHandler;
import missing.game.Game;
import missing.game.characters.Player;
import missing.game.world.World;
import missing.helper.GameException;

public class GameViewTests {
	public static void main(String[] args) {
		// XMLHandler received file name...
		String xmlFile = "items.xml";
		XMLHandler.filename = xmlFile;
		// Create an array of players
		// one at worldnode 1,1 and at tile position 1,1
		Player p = new Player(0, "Chris", new Point(1,1), new Point(1,1));
		p.setHealth(13); //test the player when he is on one health.
		//p.setHealth(1); //test the player when he is on one health.
		Player[] avatars = { p};
		try {

			Game game = new Game(avatars);
			Player chris = avatars[0];
			World world = game.getWorld();
			new TestWindow(game, world, chris);
		} catch (GameException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
	}
}
