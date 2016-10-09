/*	File: XMLImporter.java
 * 	Author
 * 	Chris Rabe			300334207
 * 	
 * 	Date				Author					Changes
 * 	3 Oct 2016			Chris Rabe				created XMLLoader.java
 * 	7 Oct 2016			Chris Rabe				can now parse shops
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
import missing.game.items.movable.Dirt;
import missing.game.items.movable.Food;
import missing.game.items.movable.Food.FoodType;
import missing.game.items.movable.Money;
import missing.game.items.movable.Tool;
import missing.game.items.movable.Tool.ToolType;
import missing.game.items.movable.Wood;
import missing.game.items.nonmovable.Bush;
import missing.game.items.nonmovable.Fireplace;
import missing.game.items.nonmovable.FishArea;
import missing.game.items.nonmovable.Rock;
import missing.game.items.nonmovable.Shop;
import missing.game.items.nonmovable.Shop.ShopType;
import missing.game.items.nonmovable.Soil;
import missing.game.items.nonmovable.TallGrass;
import missing.game.items.nonmovable.Tree;
import missing.game.world.nodes.WorldTile.TileObject;
import missing.game.world.nodes.WorldTile.Pile;
import missing.helper.GameException;

/**
 * This class encapsulates all the parsing and loading logic.
 */
public class XMLImporter {

	public static List<Item> getItemsFromFile(String filename) throws GameException {
		System.out.println("filename " + filename);
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
	 * ses the items within a node and stores it inside a list of items.
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
		List<Item> nonMovable = parseNonMovable(worldLocation, doc, xPath, expression);
		// Parse movable objects
		List<Item> movable = parseMovable(worldLocation, doc, xPath, expression);
		// Add everything together
		tmp.addAll(nonMovable);
		tmp.addAll(movable);
		return tmp;
	}

	// Main component parsers

	private static List<Item> parseMovable(Point worldLocation, Document doc, XPath xPath, String expression) {
		List<Item> dirt = parseDirt(worldLocation, doc, xPath, expression);
		List<Item> wood = parseWood(worldLocation, doc, xPath, expression);
		List<Item> stone = parseStone(worldLocation, doc, xPath, expression);
		List<Item> food = parseFood(worldLocation, doc, xPath, expression);
		List<Item> tools = parseTools(worldLocation, doc, xPath, expression);
		List<Item> money = parseMoney(worldLocation, doc, xPath, expression);
		// Combine everything together
		List<Item> tmp = new ArrayList<Item>();
		tmp.addAll(dirt);
		tmp.addAll(wood);
		tmp.addAll(stone);
		tmp.addAll(food);
		tmp.addAll(tools);
		tmp.addAll(money);
		return tmp;
	}

	private static List<Item> parseNonMovable(Point worldLocation, Document doc, XPath xPath, String expression) {
		// Parse all non movable items
		List<Item> trees = parseTrees(worldLocation, doc, xPath, expression);
		List<Item> bushes = parseBushes(worldLocation, doc, xPath, expression);
		List<Item> rocks = parseRocks(worldLocation, doc, xPath, expression);
		List<Item> soil = parseSoil(worldLocation, doc, xPath, expression);
		List<Item> fishareas = parseFishArea(worldLocation, doc, xPath, expression);
		List<Item> fireplace = parseFirePlace(worldLocation, doc, xPath, expression);
		List<Item> piles = parsePiles(worldLocation, doc, xPath, expression);
		List<Item> shops = parseShops(worldLocation, doc, xPath, expression);
		List<Item> tallgrass = parseTallGrass(worldLocation, doc, xPath, expression);
		// Combine everything together
		List<Item> tmp = new ArrayList<Item>();
		tmp.addAll(trees);
		tmp.addAll(bushes);
		tmp.addAll(rocks);
		tmp.addAll(soil);
		tmp.addAll(fishareas);
		tmp.addAll(fireplace);
		tmp.addAll(piles);
		tmp.addAll(shops);
		tmp.addAll(tallgrass);
		return tmp;
	}

	// Movable Parsers

	private static List<Item> parseTools(Point worldLocation, Document doc, XPath xPath, String expression) {
		List<Item> tmp = new ArrayList<Item>();
		expression += "/tool";
		try {
			NodeList tools = (NodeList) xPath.compile(expression).evaluate(doc, XPathConstants.NODESET);
			for (int i = 0; i < tools.getLength(); i++) {
				Node tool = tools.item(i);
				if (tool.getNodeType() == Node.ELEMENT_NODE) {
					Element elem = (Element) tool;
					Node locNode = elem.getElementsByTagName("location").item(0);
					Point location = parseLocation(locNode);
					ToolType type = parseToolType(elem.getElementsByTagName("type").item(0).getTextContent());
					int durability = Integer.parseInt(elem.getElementsByTagName("durability").item(0).getTextContent());
					int amount = Integer.parseInt(elem.getElementsByTagName("amount").item(0).getTextContent());
					tmp.add(new Tool(worldLocation, location, type, durability, amount));
				}
			}
		} catch (XPathExpressionException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
		return tmp;
	}

	private static List<Item> parseFood(Point worldLocation, Document doc, XPath xPath, String expression) {
		List<Item> tmp = new ArrayList<Item>();
		expression += "/food";
		try {
			NodeList food = (NodeList) xPath.compile(expression).evaluate(doc, XPathConstants.NODESET);
			for (int i = 0; i < food.getLength(); i++) {
				Node f = food.item(i);
				if (f.getNodeType() == Node.ELEMENT_NODE) {
					Element elem = (Element) f;
					Node locNode = elem.getElementsByTagName("location").item(0);
					Point location = parseLocation(locNode);
					FoodType type = parseFoodType(elem.getElementsByTagName("type").item(0).getTextContent());
					int amount = Integer.parseInt(elem.getElementsByTagName("amount").item(0).getTextContent());
					tmp.add(new Food(worldLocation, location, type, amount));
				}
			}
		} catch (XPathExpressionException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
		return tmp;
	}

	private static List<Item> parseMoney(Point worldLocation, Document doc, XPath xPath, String expression) {
		List<Item> tmp = new ArrayList<Item>();
		expression += "/money";
		try {
			NodeList money = (NodeList) xPath.compile(expression).evaluate(doc, XPathConstants.NODESET);
			for (int i = 0; i < money.getLength(); i++) {
				Node m = money.item(i);
				if (m.getNodeType() == Node.ELEMENT_NODE) {
					Element elem = (Element) m;
					Node locNode = elem.getElementsByTagName("location").item(0);
					Point location = parseLocation(locNode);
					int amount = Integer.parseInt(elem.getElementsByTagName("amount").item(0).getTextContent());
					tmp.add(new Money(worldLocation, location, amount));
				}
			}
		} catch (XPathExpressionException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
		return tmp;
	}

	private static List<Item> parseDirt(Point worldLocation, Document doc, XPath xPath, String expression) {
		List<Item> tmp = new ArrayList<Item>();
		expression += "/dirt";
		try {
			NodeList dirt = (NodeList) xPath.compile(expression).evaluate(doc, XPathConstants.NODESET);
			for (int i = 0; i < dirt.getLength(); i++) {
				Node d = dirt.item(i);
				if (d.getNodeType() == Node.ELEMENT_NODE) {
					Element elem = (Element) d;
					Node locNode = elem.getElementsByTagName("location").item(0);
					Point location = parseLocation(locNode);
					int amount = Integer.parseInt(elem.getElementsByTagName("amount").item(0).getTextContent());
					tmp.add(new Dirt(worldLocation, location, amount));
				}
			}
		} catch (XPathExpressionException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
		return tmp;
	}

	private static List<Item> parseWood(Point worldLocation, Document doc, XPath xPath, String expression) {
		List<Item> tmp = new ArrayList<Item>();
		expression += "/wood";
		try {
			NodeList wood = (NodeList) xPath.compile(expression).evaluate(doc, XPathConstants.NODESET);
			for (int i = 0; i < wood.getLength(); i++) {
				Node w = wood.item(i);
				if (w.getNodeType() == Node.ELEMENT_NODE) {
					Element elem = (Element) w;
					Node locNode = elem.getElementsByTagName("location").item(0);
					Point location = parseLocation(locNode);
					int amount = Integer.parseInt(elem.getElementsByTagName("amount").item(0).getTextContent());
					tmp.add(new Wood(worldLocation, location, amount));
				}
			}
		} catch (XPathExpressionException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
		return tmp;
	}

	private static List<Item> parseStone(Point worldLocation, Document doc, XPath xPath, String expression) {
		List<Item> tmp = new ArrayList<Item>();
		expression += "/stone";
		try {
			NodeList stones = (NodeList) xPath.compile(expression).evaluate(doc, XPathConstants.NODESET);
			for (int i = 0; i < stones.getLength(); i++) {
				Node stone = stones.item(i);
				if (stone.getNodeType() == Node.ELEMENT_NODE) {
					Element elem = (Element) stone;
					Node locNode = elem.getElementsByTagName("location").item(0);
					Point location = parseLocation(locNode);
					int amount = Integer.parseInt(elem.getElementsByTagName("amount").item(0).getTextContent());
					tmp.add(new Wood(worldLocation, location, amount));
				}
			}
		} catch (XPathExpressionException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
		return tmp;
	}

	// NonMovable Parsers

	private static List<Item> parseShops(Point worldLocation, Document doc, XPath xPath, String expression) {
		List<Item> tmp = new ArrayList<Item>();
		expression += "/shop";
		try {
			NodeList shops = (NodeList) xPath.compile(expression).evaluate(doc, XPathConstants.NODESET);
			for (int i = 0; i < shops.getLength(); i++) {
				Node shop = shops.item(i);
				if (shop.getNodeType() == Node.ELEMENT_NODE) {
					Element elem = (Element) shop;
					Node locNode = elem.getElementsByTagName("location").item(0);
					Point location = parseLocation(locNode);
					ShopType type = parseShopType(elem.getElementsByTagName("type").item(0).getTextContent());
					tmp.add(new Shop(worldLocation, location, type));
				}
			}
		} catch (XPathExpressionException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
		return tmp;
	}

	private static List<Item> parsePiles(Point worldLocation, Document doc, XPath xPath, String expression) {
		List<Item> tmp = new ArrayList<Item>();
		expression += "/pile";
		String itemsExpression = expression + "/items";
		try {
			NodeList stones = (NodeList) xPath.compile(expression).evaluate(doc, XPathConstants.NODESET);
			for (int i = 0; i < stones.getLength(); i++) {
				Node stone = stones.item(i);
				if (stone.getNodeType() == Node.ELEMENT_NODE) {
					Element elem = (Element) stone;
					Node locNode = elem.getElementsByTagName("location").item(0);
					Point location = parseLocation(locNode);
					List<Item> items = parseMovable(worldLocation, doc, xPath, itemsExpression);
					List<TileObject> objects = toTileObject(items);
					tmp.add(new Pile(worldLocation, location, objects));
				}
			}
		} catch (XPathExpressionException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
		return tmp;
	}

	private static List<Item> parseFirePlace(Point worldLocation, Document doc, XPath xPath, String expression) {
		List<Item> tmp = new ArrayList<Item>();
		expression += "/fireplace";
		try {
			NodeList trees = (NodeList) xPath.compile(expression).evaluate(doc, XPathConstants.NODESET);
			for (int i = 0; i < trees.getLength(); i++) {
				Node tree = trees.item(i);
				if (tree.getNodeType() == Node.ELEMENT_NODE) {
					Element elem = (Element) tree;
					Node locNode = elem.getElementsByTagName("location").item(0);
					Point location = parseLocation(locNode);
					tmp.add(new Fireplace(worldLocation, location));
				}
			}
		} catch (XPathExpressionException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
		return tmp;
	}

	private static List<Item> parseFishArea(Point worldLocation, Document doc, XPath xPath, String expression) {
		List<Item> tmp = new ArrayList<Item>();
		expression += "/fisharea";
		try {
			NodeList fishareas = (NodeList) xPath.compile(expression).evaluate(doc, XPathConstants.NODESET);
			for (int i = 0; i < fishareas.getLength(); i++) {
				Node fa = fishareas.item(i);
				if (fa.getNodeType() == Node.ELEMENT_NODE) {
					Element elem = (Element) fa;
					Node locNode = elem.getElementsByTagName("location").item(0);
					Point location = parseLocation(locNode);
					tmp.add(new FishArea(worldLocation, location));
				}
			}
		} catch (XPathExpressionException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
		return tmp;
	}

	private static List<Item> parseSoil(Point worldLocation, Document doc, XPath xPath, String expression) {
		List<Item> tmp = new ArrayList<Item>();
		expression += "/soil";
		try {
			NodeList trees = (NodeList) xPath.compile(expression).evaluate(doc, XPathConstants.NODESET);
			for (int i = 0; i < trees.getLength(); i++) {
				Node tree = trees.item(i);
				if (tree.getNodeType() == Node.ELEMENT_NODE) {
					Element elem = (Element) tree;
					Node locNode = elem.getElementsByTagName("location").item(0);
					Point location = parseLocation(locNode);
					tmp.add(new Soil(worldLocation, location));
				}
			}
		} catch (XPathExpressionException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
		return tmp;
	}

	private static List<Item> parseRocks(Point worldLocation, Document doc, XPath xPath, String expression) {
		List<Item> tmp = new ArrayList<Item>();
		expression += "/rock";
		try {
			NodeList trees = (NodeList) xPath.compile(expression).evaluate(doc, XPathConstants.NODESET);
			for (int i = 0; i < trees.getLength(); i++) {
				Node tree = trees.item(i);
				if (tree.getNodeType() == Node.ELEMENT_NODE) {
					Element elem = (Element) tree;
					Node locNode = elem.getElementsByTagName("location").item(0);
					Point location = parseLocation(locNode);
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
		expression += "/bush";
		try {
			NodeList trees = (NodeList) xPath.compile(expression).evaluate(doc, XPathConstants.NODESET);
			for (int i = 0; i < trees.getLength(); i++) {
				Node tree = trees.item(i);
				if (tree.getNodeType() == Node.ELEMENT_NODE) {
					Element elem = (Element) tree;
					Node locNode = elem.getElementsByTagName("location").item(0);
					Point location = parseLocation(locNode);
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

	private static List<Item> parseTallGrass(Point worldLocation, Document doc, XPath xPath, String expression) {
		List<Item> tmp = new ArrayList<Item>();
		expression += "/tallgrass";
		try {
			NodeList tallgrass = (NodeList) xPath.compile(expression).evaluate(doc, XPathConstants.NODESET);
			for (int i = 0; i < tallgrass.getLength(); i++) {
				Node t = tallgrass.item(i);
				if (t.getNodeType() == Node.ELEMENT_NODE) {
					Element elem = (Element) t;
					Node locNode = elem.getElementsByTagName("location").item(0);
					Point location = parseLocation(locNode);
					tmp.add(new TallGrass(worldLocation, location));
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
	private static Point parseLocation(Node locNode) {
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

	// Type parsers

	private static FoodType parseFoodType(String type) {
		switch (type) {
		case "APPLE":
			return FoodType.APPLE;
		case "BERRY":
			return FoodType.BERRY;
		case "FISH":
			return FoodType.FISH;
		}
		throw new RuntimeException("Invalid type");
	}

	private static ToolType parseToolType(String type) {
		switch (type) {
		case "AXE":
			return ToolType.AXE;
		case "PICKAXE":
			return ToolType.PICKAXE;
		case "SHOVEL":
			return ToolType.SHOVEL;
		case "FISHINGROD":
			return ToolType.FISHINGROD;
		}
		throw new RuntimeException("Invalid type");
	}

	private static ShopType parseShopType(String type) {
		switch (type) {
		case "RESOURCE":
			return ShopType.RESOURCE;
		case "TOOLS":
			return ShopType.TOOLS;
		case "FOOD":
			return ShopType.FOOD;
		}
		throw new RuntimeException("Invalid type");
	}

	// Helper methods

	private static List<TileObject> toTileObject(List<Item> items) {
		List<TileObject> tmp = new ArrayList<TileObject>();
		for (Item i : items) {
			tmp.add((TileObject) i);
		}
		return tmp;
	}
}
