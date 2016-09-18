/*	File: XMLHandler.java
 * 	Author:
 * 	Chris Rabe		300334207
 * 
 * 	Date			Author				Changes
 * 	17 Sep 16		Chris Rabe			created XMLHandler.java
 * 	17 Sep 16		Chris Rabe			can now parse rocks
 * 	17 Sep 16		Chris Rabe			now uses the filename field to parse
 */

package missing.datastorage.assetloader;

import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import missing.game.items.Item;
import missing.game.items.nonmovable.*;
import missing.helper.GameException;

public class XMLHandler {

	public static String filename;

	public static List<Item> getItemsFromFile() throws GameException {
		if (filename == null) {
			throw new GameException("No file loaded!");
		}
		List<Item> tmp = new ArrayList<Item>();
		// Create document
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = dbFactory.newDocumentBuilder();
			Document doc = builder.parse(new File(filename));
			doc.getDocumentElement().normalize();

			XPath xPath = XPathFactory.newInstance().newXPath();

			// Iterate through each nodes
			String expression = "/nodes/node";
			NodeList nodes = (NodeList) xPath.compile(expression).evaluate(doc, XPathConstants.NODESET);
			for (int i = 0; i < nodes.getLength(); i++) {
				Node node = nodes.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element elem = (Element) node;
					Point worldLocation = parseWorldLocation(elem.getAttribute("worldLocation"));
					// Get all the items within a single world node
					List<Item> nodeItems = parseNodeItems(worldLocation, doc, xPath, expression);
					tmp.addAll(nodeItems);
				}
			}
		} catch (ParserConfigurationException | SAXException | IOException | XPathExpressionException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
		return tmp;
	}

	// Item Parsers

	/**
	 * Parses the items within a node and stores it inside a list of items.
	 * 
	 * @param worldLocation
	 * @param doc
	 * @param xPath
	 * @param expression
	 * @return
	 */
	private static List<Item> parseNodeItems(Point worldLocation, Document doc, XPath xPath, String expression) {
		List<Item> tmp = new ArrayList<Item>();
		expression += String.format("[@worldLocation=\"%d,%d\"]/items", worldLocation.x, worldLocation.y);
		// Parse non-movable objects
		List<Item> trees = parseTrees(worldLocation, doc, xPath, expression);
		List<Item> bushes = parseBushes(worldLocation, doc, xPath, expression);
		List<Item> rocks = parseRocks(worldLocation, doc, xPath, expression);
		// TODO Parse Shop
		// TODO Parse Fireplace
		// Parse movable objects
		// TODO Parse Wood
		// TODO Parse Stone
		// TODO Parse Food
		// TODO Parse Tools
		// Add everything together
		tmp.addAll(trees);
		tmp.addAll(bushes);
		tmp.addAll(rocks);
		return tmp;
	}

	// Movable Parsers

	// NonMovable Parsers

	private static List<Item> parseRocks(Point worldLocation, Document doc, XPath xPath, String expression) {
		List<Item> tmp = new ArrayList<Item>();
		expression += "/rock"; // TODO Change expression
		try {
			NodeList trees = (NodeList) xPath.compile(expression).evaluate(doc, XPathConstants.NODESET);
			for (int i = 0; i < trees.getLength(); i++) {
				Node tree = trees.item(i);
				if (tree.getNodeType() == Node.ELEMENT_NODE) {
					Element elem = (Element) tree;
					Node locNode = elem.getElementsByTagName("location").item(0);
					Point location = parseLocation(locNode);
					// TODO Change what is added
					tmp.add(new Rock(worldLocation, location));
				}
			}
		} catch (XPathExpressionException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
		return tmp;
	}

	private static List<Item> parseBushes(Point worldLocation, Document doc, XPath xPath, String expression) {
		List<Item> tmp = new ArrayList<Item>();
		expression += "/bush"; // TODO Change expression
		try {
			NodeList trees = (NodeList) xPath.compile(expression).evaluate(doc, XPathConstants.NODESET);
			for (int i = 0; i < trees.getLength(); i++) {
				Node tree = trees.item(i);
				if (tree.getNodeType() == Node.ELEMENT_NODE) {
					Element elem = (Element) tree;
					Node locNode = elem.getElementsByTagName("location").item(0);
					Point location = parseLocation(locNode);
					// TODO Change what is added
					tmp.add(new Bush(worldLocation, location));
				}
			}
		} catch (XPathExpressionException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
		return tmp;
	}

	private static List<Item> parseTrees(Point worldLocation, Document doc, XPath xPath, String expression) {
		List<Item> tmp = new ArrayList<Item>();
		expression += "/tree";
		try {
			NodeList trees = (NodeList) xPath.compile(expression).evaluate(doc, XPathConstants.NODESET);
			for (int i = 0; i < trees.getLength(); i++) {
				Node tree = trees.item(i);
				if (tree.getNodeType() == Node.ELEMENT_NODE) {
					Element elem = (Element) tree;
					Node locNode = elem.getElementsByTagName("location").item(0);
					Point location = parseLocation(locNode);
					tmp.add(new Tree(worldLocation, location));
				}
			}
		} catch (XPathExpressionException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
		return tmp;
	}

	// Location Parsers

	/**
	 * This returns the tileLocation field of an item
	 * 
	 * @param locNode
	 * @return
	 */
	public static Point parseLocation(Node locNode) {
		if (locNode.getNodeType() == Node.ELEMENT_NODE) {
			Element elem = (Element) locNode;
			int x = Integer.parseInt(elem.getElementsByTagName("x").item(0).getTextContent());
			int y = Integer.parseInt(elem.getElementsByTagName("y").item(0).getTextContent());
			return new Point(x, y);
		}
		return null;
	}

	/**
	 * Converts the "worldLocation" attribute into a Point location.
	 * 
	 * @param attribute
	 * @return
	 */
	private static Point parseWorldLocation(String attribute) {
		String[] coords = attribute.split(",");
		int x = Integer.parseInt(coords[0]);
		int y = Integer.parseInt(coords[1]);
		return new Point(x, y);
	}
}
