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
 * The merchant class is a character used for trading items to the players.
 */
@SuppressWarnings("serial")
public class Merchant extends Character {

	private HashMap<String, Movable> namesToItems;
	private HashMap<Movable, Integer> costs;

	private ShopType type;

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
	 * buys item from merchant
	 */
	public void buyItem(Player player, String item) throws GameException {
		Movable i = namesToItems.get(item);
		if (i == null) {
			JOptionPane.showMessageDialog(null, "The merchant does not have this item!");
			throw new GameException("the merchant doesn't have this item");
		}
		int costOfItem = costs.get(i);
		if (player.getMoney().getAmount() < costOfItem) {
			JOptionPane.showMessageDialog(null, "You don't have enough money to buy that!");
			throw new GameException("player doesn't have enough money");
		}
		// deduct from play money
		player.setMoney(player.getMoney().getAmount() - costOfItem);
		Movable playerItem = i;
		player.addToPocket(playerItem);
	}

	/**
	 * player sells one amount of an item to the merchant
	 */
	public int sellItem(Player p, Movable item) throws GameException {
		if (!p.inventoryHas(item)) {
			JOptionPane.showMessageDialog(null, "You cannot sell an item you don't have.");
			throw new GameException("cannot sell item you dont have");
		}
		if (!costs.containsKey(item)) {
			JOptionPane.showMessageDialog(null, "This particular merchant doesn't buy this type of item!");
			throw new GameException("This merchant does not buy this type of item");
		}
		int sellAmount = (int) (costs.get(findItemInMap(item)) * 0.5);
		p.setMoney(p.getMoney().getAmount() + sellAmount);
		// reduces amount of item
		item.setAmount(item.getAmount() - 1);
		if (item.getAmount() == 0)
			p.removeFromInventory(item);
		return sellAmount;

	}

	// Helper methods

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
		Tool axe = new Tool(null, null, ToolType.AXE);
		Tool pickaxe = new Tool(null, null, ToolType.PICKAXE);
		Tool rod = new Tool(null, null, ToolType.FISHINGROD);
		Tool shovel = new Tool(null, null, ToolType.SHOVEL);
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
		Food apple = new Food(null, null, FoodType.APPLE);
		Food berry = new Food(null, null, FoodType.BERRY);
		Food fish = new Food(null, null, FoodType.FISH);
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
}
