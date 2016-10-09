/*	File: XMLExporter.java
 * 	Author
 * 	Chris Rabe		300334207
 * 
 * 	Date			Author				Changes
 * 	3 Oct 16		Chris Rabe			created XMLExporter
 * 	5 Oct 16		Chris Rabe			created methods for converting the game to XML Document
 * 	7 Oct 16		Chris Rabe			can now turn shop objects into XML file
 * 	9 Oct 16		Chris Rabe			can now turn money objects into XML file
 */

package missing.datastorage.assetloader;

import java.awt.Point;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import missing.game.Game;
import missing.game.characters.Player;
import missing.game.world.nodes.WorldNode;
import missing.game.world.nodes.WorldTile;
import missing.game.world.nodes.WorldTile.Pile;
import missing.game.world.nodes.WorldTile.TileObject;
import missing.game.items.movable.*;
import missing.game.items.movable.Food.FoodType;
import missing.game.items.movable.Tool.ToolType;
import missing.game.items.nonmovable.*;
import missing.game.items.nonmovable.Shop.ShopType;

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
		node.setAttributeNode(attr);
		node.appendChild(items);
		return node;
	}

	// Main item parsers

	private static Element getNodeItems(WorldNode wNode, Document doc) {
		Element items = doc.createElement("items");
		WorldTile[][] tiles = wNode.getWorldTiles();
		for (int y = 0; y < tiles.length; y++) {
			for (int x = 0; x < tiles[y].length; x++) {
				TileObject obj = tiles[y][x].getObject();
				if (obj != null) {
					Element item = parseItem(obj, doc);
					if (item != null) {
						items.appendChild(item);
					}
				}
			}
		}
		return items;
	}

	private static Element parseItem(TileObject object, Document doc) {
		if (object instanceof NonMovable) {
			return getNonMovable(object, doc);
		} else if (object instanceof Movable) {
			return getMovable(object, doc);
		} else if (object instanceof Player) {
			return null;
		}
		throw new RuntimeException("Unknown object type: " + object.getName());
	}

	private static Element getNonMovable(TileObject object, Document doc) {
		if (object instanceof Tree) {
			return getTree((Tree) object, doc);
		} else if (object instanceof Rock) {
			return getRock((Rock) object, doc);
		} else if (object instanceof Bush) {
			return getBush((Bush) object, doc);
		} else if (object instanceof FishArea) {
			return getFisharea((FishArea) object, doc);
		} else if (object instanceof Soil) {
			return getSoil((Soil) object, doc);
		} else if (object instanceof Pile) {
			return getPile((Pile) object, doc);
		} else if (object instanceof Fireplace) {
			return getFireplace((Fireplace) object, doc);
		} else if (object instanceof Shop) {
			return getShop((Shop) object, doc);
		}
		throw new RuntimeException("Unknown object type");
	}

	private static Element getMovable(TileObject object, Document doc) {
		if (object instanceof Wood) {
			return getWood((Wood) object, doc);
		} else if (object instanceof Stone) {
			return getStone((Stone) object, doc);
		} else if (object instanceof Dirt) {
			return getDirt((Dirt) object, doc);
		} else if (object instanceof Food) {
			return getFood((Food) object, doc);
		} else if (object instanceof Tool) {
			return getTool((Tool) object, doc);
		} else if (object instanceof Money) {
			return getMoney((Money) object, doc);
		}
		throw new RuntimeException("Unknown object type");
	}

	// Movable parsers

	private static Element getTool(Tool object, Document doc) {
		Element tool = doc.createElement("tool");
		Element loc = getLocation(object.getTileLocation(), doc);
		Element type = getToolType(object.getType(), doc);
		Element durability = getDurability(object.getDurability(), doc);
		Element amt = getAmount(object.getAmount(), doc);
		tool.appendChild(loc);
		tool.appendChild(type);
		tool.appendChild(durability);
		tool.appendChild(amt);
		return tool;
	}

	private static Element getFood(Food object, Document doc) {
		Element food = doc.createElement("food");
		Element loc = getLocation(object.getTileLocation(), doc);
		Element type = getFoodType(object.getFoodType(), doc);
		Element amt = getAmount(object.getAmount(), doc);
		food.appendChild(loc);
		food.appendChild(type);
		food.appendChild(amt);
		return food;
	}

	private static Element getDirt(Dirt object, Document doc) {
		Element dirt = doc.createElement("dirt");
		Element loc = getLocation(object.getTileLocation(), doc);
		Element amt = getAmount(object.getAmount(), doc);
		dirt.appendChild(loc);
		dirt.appendChild(amt);
		return dirt;
	}

	private static Element getStone(Stone object, Document doc) {
		Element stone = doc.createElement("stone");
		Element loc = getLocation(object.getTileLocation(), doc);
		Element amt = getAmount(object.getAmount(), doc);
		stone.appendChild(loc);
		stone.appendChild(amt);
		return stone;
	}

	private static Element getWood(Wood object, Document doc) {
		Element wood = doc.createElement("wood");
		Element loc = getLocation(object.getTileLocation(), doc);
		Element amt = getAmount(object.getAmount(), doc);
		wood.appendChild(loc);
		wood.appendChild(amt);
		return wood;
	}

	private static Element getMoney(Money object, Document doc) {
		Element money = doc.createElement("money");
		Element loc = getLocation(object.getTileLocation(), doc);
		Element amt = getAmount(object.getAmount(), doc);
		money.appendChild(loc);
		money.appendChild(amt);
		return money;
	}

	// Non-Movable parsers

	private static Element getTree(Tree object, Document doc) {
		Element tree = doc.createElement("tree");
		Element loc = getLocation(object.getTileLocation(), doc);
		tree.appendChild(loc);
		return tree;
	}

	private static Element getShop(Shop object, Document doc) {
		Element shop = doc.createElement("shop");
		Element loc = getLocation(object.getTileLocation(), doc);
		Element type = getShopType(object.getType(), doc);
		shop.appendChild(loc);
		shop.appendChild(type);
		return shop;
	}

	private static Element getSoil(Soil object, Document doc) {
		Element soil = doc.createElement("soil");
		Element loc = getLocation(object.getTileLocation(), doc);
		soil.appendChild(loc);
		return soil;
	}

	private static Element getRock(Rock object, Document doc) {
		Element rock = doc.createElement("rock");
		Element loc = getLocation(object.getTileLocation(), doc);
		rock.appendChild(loc);
		return rock;
	}

	private static Element getBush(Bush object, Document doc) {
		Element bush = doc.createElement("bush");
		Element loc = getLocation(object.getTileLocation(), doc);
		bush.appendChild(loc);
		return bush;
	}

	private static Element getFireplace(Fireplace object, Document doc) {
		Element fireplace = doc.createElement("fireplace");
		Element loc = getLocation(object.getTileLocation(), doc);
		fireplace.appendChild(loc);
		return fireplace;
	}

	private static Element getFisharea(FishArea object, Document doc) {
		Element fisharea = doc.createElement("fisharea");
		Element loc = getLocation(object.getTileLocation(), doc);
		fisharea.appendChild(loc);
		return fisharea;
	}

	private static Element getPile(Pile object, Document doc) {
		Element pile = doc.createElement("pile");
		Element loc = getLocation(object.getTileLocation(), doc);
		Element items = parseItems(object.getItems(), doc);
		pile.appendChild(loc);
		pile.appendChild(items);
		return pile;
	}

	// Helper methods

	private static Element parseItems(List<TileObject> items, Document doc) {
		Element itms = doc.createElement("items");
		for (TileObject o : items) {
			Element i = getMovable(o, doc);
			itms.appendChild(i);
		}
		return itms;
	}

	private static Element getDurability(int durability, Document doc) {
		Element amount = doc.createElement("durability");
		amount.appendChild(doc.createTextNode(String.valueOf(durability)));
		return amount;
	}

	private static Element getToolType(ToolType type, Document doc) {
		Element t = doc.createElement("type");
		t.appendChild(doc.createTextNode(type.name()));
		return t;
	}

	private static Element getFoodType(FoodType foodType, Document doc) {
		Element t = doc.createElement("type");
		t.appendChild(doc.createTextNode(foodType.name()));
		return t;
	}

	private static Element getShopType(ShopType type, Document doc) {
		Element t = doc.createElement("type");
		t.appendChild(doc.createTextNode(type.name()));
		return t;
	}

	private static Element getAmount(int amt, Document doc) {
		Element amount = doc.createElement("amount");
		amount.appendChild(doc.createTextNode(String.valueOf(amt)));
		return amount;
	}

	/**
	 * This method converts the give point to an XML element.
	 * 
	 * @param tileLocation
	 * @param doc
	 * @return
	 */
	private static Element getLocation(Point tileLocation, Document doc) {
		// null check here

		// create all the nodes needed
		Element loc = doc.createElement("location");
		Element x = doc.createElement("x");
		Element y = doc.createElement("y");
		// set the values of x and y
		if (tileLocation != null) {
			x.appendChild(doc.createTextNode(String.valueOf(tileLocation.x)));
			y.appendChild(doc.createTextNode(String.valueOf(tileLocation.y)));
		}
		// put x and y as children of loc
		loc.appendChild(x);
		loc.appendChild(y);
		return loc;
	}
}
