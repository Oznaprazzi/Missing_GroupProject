/*	File: Main.java
 * 	Author
 * 	Chris Rabe		300334207
 * 
 * 	Date			Author				Changes
 * 	18 Sep 16		Chris Rabe			implemented functions for xmlFile
 */

package missing;

import missing.datastorage.assetloader.XMLHandler;
import missing.ui.controller.VControl;
/**
 * Main class for the game. This class is the entry point for running the game.
 *
 */
public class Main {

	public static void main(String[] args) {
		String xmlFile = null;

		// Iterate through all the arguments passed by user
		for (int i = 0; i != args.length; i++) {
			xmlFile = args[i]; // file which indicates items
		}

		// Sanity Checks
		if (xmlFile == null) {
			System.out.println("XML File must be specified.");
			System.exit(1);
		}
		if (!xmlFile.endsWith(".xml")) {
			System.out.println("Please load an XML file.");
			System.exit(1);
		}

		// Set the handler's filename as the xmlFile stated
		XMLHandler.filename = xmlFile;
		System.out.println("Now loaded " + XMLHandler.filename);
		new VControl();
	}
}
