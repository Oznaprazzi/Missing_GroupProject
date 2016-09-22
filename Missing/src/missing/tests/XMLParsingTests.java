/*	File: XMLParsingTests.java
 * 	Authors:
 * 	Chris Rabe			300334207
 * 	
 * 	Date				Author				Changes
 * 	17 Sep 16			Chris Rabe			created XMLParsingTests.java
 */

package missing.tests;

import static missing.datastorage.assetloader.XMLHandler.getItemsFromFile;

import java.awt.Point;
import java.util.List;

import missing.datastorage.assetloader.XMLHandler;
import missing.game.Game;
import missing.game.characters.Player;
import missing.game.items.Item;
import missing.game.world.nodes.WorldTile;
import missing.helper.GameException;;

/**
 * This class tests whether the XMLHandler is working properly. Note: Cannot do
 * this test via JUnit testing. We have to physically see the items.
 */
public class XMLParsingTests {

	public static void test_01() {
		XMLHandler.filename = "items.xml";
		List<Item> items = null;
		try {
			items = getItemsFromFile();
		} catch (GameException e) {
			e.printStackTrace();
		}
		for (Item i : items) {
			Point worldLocation = i.getWorldLocation();
			Point tileLocation = i.getTileLocation();
			System.out.println(String.format("%s : (%d,%d):(%d,%d)", i.getName(), worldLocation.x, worldLocation.y,
					tileLocation.x, tileLocation.y));
		}
	}

	/**
	 * Tests whether the items parsed from the XMLHandler are stored within the
	 * game instance
	 */
	public static void test_02() {
		Game game = createGame();
		// There should be an item at (0,0):(7,7)
		WorldTile tile = game.getWorld().getWorldNodes()[0][0].getWorldTiles()[7][7];
		System.out.println(tile.getObject());
		if (tile.isOccupied()) {
			System.out.println("has item.");
		}
	}

	private static Game createGame() {
		// XMLHandler received file name...
		String xmlFile = "items.xml";
		XMLHandler.filename = xmlFile;
		// Create an array of players
		// one at worldnode 1,1 and at tile position 1,1
		Player[] avatars = { new Player("Chris", new Point(1, 1), new Point(1, 1)) };
		try {
			return new Game(avatars);
		} catch (GameException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		test_01();
		test_02();
	}
}
