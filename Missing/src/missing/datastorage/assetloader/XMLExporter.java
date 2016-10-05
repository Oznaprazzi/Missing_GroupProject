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

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import missing.game.Game;
import missing.game.world.nodes.WorldNode;
import missing.game.world.nodes.WorldTile;
import missing.game.world.nodes.WorldTile.TileObject;

/**
 * This class encapsulates all the saving and exporting logic
 */
public class XMLExporter {

	public static Document toDocument(Game game) {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = dbFactory.newDocumentBuilder();
			Document doc = builder.newDocument();
			Element root = doc.createElement("nodes");
			doc.appendChild(root);
			WorldNode[][] nodes = game.getWorld().getWorldNodes();
			for (int y = 0; y < nodes.length; y++) {
				for (int x = 0; x < nodes[y].length; x++) {
					Element node = getNode(new Point(x, y), nodes[y][x], doc);
					root.appendChild(node);
				}
			}
			return doc;
		} catch (ParserConfigurationException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
		return null;
	}

	// Node parsers

	private static Element getNode(Point worldLocation, WorldNode wNode, Document doc) {
		// create the node to store items in
		Element node = doc.createElement("node");
		Attr attr = doc.createAttribute("worldLocation");
		attr.setValue(String.format("%d,%d", worldLocation.x, worldLocation.y));
		// get the items
		Element items = getNodeItems(wNode, doc);
		node.appendChild(items);
		return node;
	}

	// Main item parsers

	private static Element getNodeItems(WorldNode wNode, Document doc) {
		Element items = doc.createElement("items");
		WorldTile[][] tiles = wNode.getWorldTiles();
		for (int y = 0; y < tiles.length; y++) {
			for (int x = 0; x < tiles[y].length; x++) {
				Element item = parseItem(tiles[y][x].getObject(), doc);
				items.appendChild(item);
			}
		}
		return items;
	}

	private static Element parseItem(TileObject object, Document doc) {
		// TODO Auto-generated method stub
		return null;
	}

}
