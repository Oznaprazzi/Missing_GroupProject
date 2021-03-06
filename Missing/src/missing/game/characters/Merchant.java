/*File: Merchant.java
 * 
 * Authors			ID
 * Jian Wei Chong	300352789
 * Chris Rabe		300334207
 * 
 * Date				Author			Modification
 * 4 oct 16			Jian Wei		created the class, implemented initialiseCosts and buyitem, sellitem
 * 5 Oct 16			Chris Rabe		changed structure of the code so that only buy and sell methods are available
 * 7 Oct 16			Chris Rabe		changed type of merchant to be based on shop type
 * 11 Oct 16		Casey Huang		fixed sell item bug - looks through bag and pocket as well as just pocket
 */
package missing.game.characters;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JOptionPane;

import missing.game.items.movable.Dirt;
import missing.game.items.movable.Food;
import missing.game.items.movable.Food.FoodType;
import missing.game.items.movable.Movable;
import missing.game.items.movable.Resource;
import missing.game.items.movable.Stone;
import missing.game.items.movable.Tool;
import missing.game.items.movable.Tool.ToolType;
import missing.game.items.movable.Wood;
import missing.game.items.nonmovable.Shop.ShopType;
import missing.helper.GameException;
import missing.helper.SignalException;

/**
 * The merchant class is a special type of character that is able to trade items
 * to players.
 */
@SuppressWarnings("serial")
public class Merchant extends Character {
	/**
	 * Maps the names to items.
	 */
	private HashMap<String, Movable> namesToItems;
	/**
	 * Maps the item to their cost
	 */
	private HashMap<Movable, Integer> costs;
	/**
	 * The Type of Shop.
	 */
	private ShopType type;

	/**
	 * Construct a new Merchant.
	 * 
	 * @param worldLocation
	 *            - where in the world they are
	 * @param tileLocation
	 *            - where in the tile they are
	 * @param type
	 *            of shop that they operate.
	 */
	public Merchant(Point worldLocation, Point tileLocation, ShopType type) {
		super("Merchant", "You can buy stuff from him.", worldLocation, tileLocation, Direction.SOUTH, Direction.ALL);
		this.type = type;
		namesToItems = new HashMap<String, Movable>();
		costs = new HashMap<Movable, Integer>();
		getCosts();
	}

	// Getters

	public ArrayList<Movable> getItems() {
		return new ArrayList<Movable>(namesToItems.values());
	}

	// Methods

	/**
	 * Since the merchant will always be inside the shop, this method does not
	 * do anything. Please use buyItem and sellItem methods instead.
	 */
	@Override
	public void performAction(Player player) throws GameException, SignalException {
	}

	/**
	 * Enables the Player to buy items from the merchant
	 * 
	 * @param player
	 *            - that is buying the item
	 * @param item
	 *            - that is being bought.
	 */
	public void buyItem(Player player, String item) throws GameException {
		Movable i = namesToItems.get(item);
		if (i == null) {
			throw new GameException("The merchant does not have this item!");
		}
		int costOfItem = costs.get(i);
		if (player.getMoney().getAmount() < costOfItem) {
			throw new GameException("You don't have enough money to buy that!");
		}
		// deduct from play money
		player.setMoney(player.getMoney().getAmount() - costOfItem);
		Movable playerItem = createItem(i);
		try {
			player.addToInventory(playerItem);
		} catch (GameException e) {
			// could not fit
			player.setMoney(player.getMoney().getAmount() + costOfItem);
			throw new GameException(e.getMessage());
		}
	}

	/**
	 * Enables the player to sell an item to the Merchant.
	 * 
	 * @param p
	 *            the Player that is selling the item
	 * @param item
	 *            being sold
	 */
	public int sellItem(Player p, Movable item) throws GameException {
		if (!p.inventoryHas(item)) {
			throw new GameException("You cannot sell an item you don't have.");
		}
		if (!costs.containsKey(item)) {
			throw new GameException("This particular merchant doesn't buy this type of item!");
		}
		int sellAmount = (int) (costs.get(findItemInMap(item)) * 0.5);
		p.setMoney(p.getMoney().getAmount() + sellAmount);
		int reduction = 1;
		// reduces amount of item
		int newAmount = item.getAmount() - reduction;
		if (newAmount <= 0) {
			p.removeFromInventory(item);
		} else {
			p.reduceItemAmount(item, reduction);
		}
		return sellAmount;

	}

	// Helper methods
	
	private Movable createItem(Movable m) {
		int newAmt = m.getAmount();
		Point wLoc = m.getWorldLocation();
		Point tLoc = m.getTileLocation();
		switch (m.getName()) {
		case "Wood":
			return new Wood(wLoc, tLoc, newAmt);
		case "Stone":
			return new Stone(wLoc, tLoc, newAmt);
		case "Dirt":
			return new Dirt(wLoc, tLoc, newAmt);
		case "Axe":
			return new Tool(wLoc, tLoc, ToolType.AXE, newAmt);
		case "Fishing Rod":
			return new Tool(wLoc, tLoc, ToolType.FISHINGROD, newAmt);
		case "Shovel":
			return new Tool(wLoc, tLoc, ToolType.SHOVEL, newAmt);
		case "Pickaxe":
			return new Tool(wLoc, tLoc, ToolType.PICKAXE, newAmt);
		case "Apple":
			return new Food(wLoc, tLoc, FoodType.APPLE, newAmt);
		case "Berry":
			return new Food(wLoc, tLoc, FoodType.BERRY, newAmt);
		case "Fish":
			return new Food(wLoc, tLoc, FoodType.FISH, newAmt);
		}
		return null;
	}
	
	private Movable findItemInMap(Movable item) {
		for (Movable key : costs.keySet()) {
			if (item.getClass() == key.getClass()) {
				return key;
			}
		}
		return null;
	}

	/** Fills up the map of trades. Initialises the cost of each item */
	private void getCosts() {
		switch (type) {
		case RESOURCE:
			getResourcePrices();
			break;
		case FOOD:
			getFoodPrices();
			break;
		case TOOLS:
			getToolPrices();
			break;
		}
	}

	/**
	 * Costs of tools:
	 * 
	 * <pre>
	 * axe : 30 coins
	 * pickaxe: 20 coins
	 * fishingrod = 25 coins
	 * shovel: 25 coins
	 * </pre>
	 */
	private void getToolPrices() {
		Tool axe = new Tool(null, null, ToolType.AXE, 1);
		Tool pickaxe = new Tool(null, null, ToolType.PICKAXE, 1);
		Tool rod = new Tool(null, null, ToolType.FISHINGROD, 1);
		Tool shovel = new Tool(null, null, ToolType.SHOVEL, 1);
		// put those items into the map
		namesToItems.put(axe.getName(), axe);
		namesToItems.put(pickaxe.getName(), pickaxe);
		namesToItems.put(rod.getName(), rod);
		namesToItems.put(shovel.getName(), shovel);
		// map the items to cost
		costs.put(axe, 30);
		costs.put(pickaxe, 20);
		costs.put(rod, 25);
		costs.put(shovel, 25);
	}

	/**
	 * Costs of food:
	 * 
	 * <pre>
	 * apple: 20 coins
	 * berry: 10 coins
	 * fish: 30 coins
	 * </pre>
	 * 
	 */
	private void getFoodPrices() {
		Food apple = new Food(null, null, FoodType.APPLE, 1);
		Food berry = new Food(null, null, FoodType.BERRY, 1);
		Food fish = new Food(null, null, FoodType.FISH, 1);
		// put those items into the map
		namesToItems.put(apple.getName(), apple);
		namesToItems.put(berry.getName(), berry);
		namesToItems.put(fish.getName(), fish);
		// map the items to cost
		costs.put(apple, 20);
		costs.put(berry, 10);
		costs.put(fish, 30);
	}

	/**
	 * Cost of resources:
	 * 
	 * <pre>
	 * wood: 20 coins
	 * stone: 30 coins
	 * dirt: 40 coins
	 * </pre>
	 */
	private void getResourcePrices() {
		Resource wood = new Wood(null, null, 1);
		Resource stone = new Stone(null, null, 1);
		Resource dirt = new Dirt(null, null, 1);
		// put those items into map
		namesToItems.put(wood.getName(), wood);
		namesToItems.put(stone.getName(), stone);
		namesToItems.put(dirt.getName(), dirt);
		// map the items to cost
		costs.put(wood, 20);
		costs.put(stone, 30);
		costs.put(dirt, 40);
	}
	
	public ShopType getType(){
		return this.type;
	}
}
