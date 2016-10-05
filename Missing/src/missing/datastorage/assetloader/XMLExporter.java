/*	File: XMLExporter.java
 * 	Author
 * 	Chris Rabe		300334207
 * 
 * 	Date			Author				Changes
 * 	3 Oct 16		Chris Rabe			created XMLExporter
 */

package missing.datastorage.assetloader;

import java.awt.Point;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import missing.game.Game;
import missing.game.world.nodes.WorldNode;

/**
 * This class encapsulates all the saving and exporting logic
 */
public class XMLExporter {

	public static void saveGame(Game game, String filename) {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = dbFactory.newDocumentBuilder();
			Document doc = builder.newDocument();
			Element root = doc.createElement("nodes");
			doc.appendChild(root);
			WorldNode[][] nodes = game.getWorld().getWorldNodes();
			for (int y = 0; y < nodes.length; y++) {
				for (int x = 0; x < nodes[y].length; x++) {
					writeNodes(new Point(x, y), nodes[y][x], root, doc);
				}
			}
		} catch (ParserConfigurationException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
	}

	private static void writeNodes(Point point, WorldNode worldNode, Element root, Document doc) {
		// TODO Auto-generated method stub

	}

}
