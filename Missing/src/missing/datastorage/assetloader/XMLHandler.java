/*	File: XMLHandler.java
 * 	Author:
 * 	Chris Rabe		300334207
 * 
 * 	Date			Author				Changes
 * 	17 Sep 16		Chris Rabe			created XMLHandler.java
 * 	17 Sep 16		Chris Rabe			can now parse rocks
 * 	17 Sep 16		Chris Rabe			now uses the filename field to parse
 * 	29 Sep 16		Chris Rabe			added parsing for nonmovable and movable
 * 	3 Oct 16		Chris Rabe			moved all loading logic to XMLLoader
 */

package missing.datastorage.assetloader;

import java.util.List;

import missing.game.items.Item;
import missing.helper.GameException;

/**
 * This class is responsible for reading and writing XML files.
 */
public class XMLHandler {

	public static String filename;

	public static List<Item> getItemsFromFile() throws GameException {
		if (filename == null) {
			throw new GameException("No file loaded!");
		}
		return XMLImporter.getItemsFromFile(filename);
	}
}
