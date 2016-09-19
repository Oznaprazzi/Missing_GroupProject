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
import missing.game.items.Item;
import missing.helper.GameException;;

/**
 * This class tests whether the XMLHandler is working properly.
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

	public static void main(String[] args) {
		test_01();
	}
}
