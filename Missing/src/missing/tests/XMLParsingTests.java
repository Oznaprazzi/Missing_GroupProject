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
import missing.game.world.nodes.WorldNode;
import missing.game.world.nodes.WorldTile;
import missing.game.world.nodes.WorldTile.TileObject;
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
		if (items != null) {
			for (Item i : items) {
				Point worldLocation = i.getWorldLocation();
				Point tileLocation = i.getTileLocation();
				System.out.println(String.format("%s : (%d,%d):(%d,%d)", i.getName(), worldLocation.x, worldLocation.y,
						tileLocation.x, tileLocation.y));
			}
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

	public static void test_03() {
		Game game = createGame();
		// Retrieve world nodes
		WorldNode[][] nodes = game.getWorld().getWorldNodes();
		// Print out the items inside the node
		for (int i = 0; i < nodes.length; i++) {
			for (int j = 0; j < nodes[i].length; j++) {
				printObjects(nodes[i][j]);
			}
		}
	}

	private static void printObjects(WorldNode worldNode) {
		System.out.println(
				String.format("WORLD NODE [%d, %d]", worldNode.getGameLocation().x, worldNode.getGameLocation().y));
		WorldTile[][] tiles = worldNode.getWorldTiles();
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[i].length; j++) {
				if (tiles[i][j].isOccupied()) {
					TileObject item = tiles[i][j].getObject();
					Point wLoc = item.getWorldLocation();
					Point tLoc = item.getTileLocation();
					System.out.println(String.format("Tile [%d, %d] : ItemLoc W[%d,%d] T[%d,%d]", j, i, wLoc.x, wLoc.y,
							tLoc.x, tLoc.y));
				}
			}
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
		// test_01();
		// test_02();
		test_03();
	}
}
