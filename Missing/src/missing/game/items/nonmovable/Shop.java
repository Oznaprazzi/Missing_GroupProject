/*	File: Shop.java
 * 	Author	
 * 	Chris Rabe		300334207
 * 
 * 	Date			Author				Changes
 * 	7 Oct 16		Chris Rabe			created shop class
 * 
 */
package missing.game.items.nonmovable;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import missing.game.Game;
import missing.game.characters.Merchant;
import missing.game.characters.Player;
import missing.helper.GameException;
import missing.helper.SignalException;
import missing.ui.assets.ShopNode;

/**
 * This class represents a place where players can purchase items from the
 * merchant. The items being sold depends on the type of shop the player has
 * entered
 */
@SuppressWarnings("serial")
public class Shop extends NonMovable {

	public static enum ShopType {
		RESOURCE, FOOD, TOOLS
	}

	private ShopType type;
	private Merchant merchant;
	private List<Player> players;
	private ShopNode[] nodes;

	public Shop(Point worldLocation, Point tileLocation, ShopType type) {
		super("Shop", "A place where you can buy items", worldLocation, tileLocation);
		this.type = type;
		this.merchant = new Merchant(worldLocation, tileLocation, type);
		this.players = new ArrayList<Player>();
		this.nodes = new ShopNode[4]; // north south east west
	}

	// Getters and Setters...

	public ShopType getType() {
		return type;
	}

	public void setType(ShopType type) {
		this.type = type;
	}

	public Merchant getMerchant() {
		return merchant;
	}

	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}

	public ShopNode getNorthNode() {
		return nodes[0];
	}

	public ShopNode getEastNode() {
		return nodes[1];
	}

	public ShopNode getSouthNode() {
		return nodes[2];
	}

	public ShopNode getWestNode() {
		return nodes[3];
	}

	public void setNode(ShopNode node, Direction direction) {
		switch (direction) {
		case EAST:
			nodes[1] = node;
			break;
		case NORTH:
			nodes[0] = node;
			break;
		case SOUTH:
			nodes[2] = node;
			break;
		case WEST:
			nodes[3] = node;
			break;
		default:
			break;
		}
	}

	// Methods

	/**
	 * Removes the player from the shop
	 * 
	 * @param game
	 * @param id
	 * @throws GameException
	 */
	public void exitShop(Game game, int id) throws GameException {
		Player player = findPlayer(id);
		if (player != null) {
			game.movePlayer(player.getId(), getOrientation());
		}
	}

	/**
	 * Adds the player to the shop
	 */
	public void enterShop(Player player) {
		if (player != null) {
			players.add(player);
		}
		throw new RuntimeException("Player cannot be null");
	}

	@Override
	public void performAction(Player player) throws GameException, SignalException {
		throw new SignalException("SHOP");
	}

	// Helper methods

	/**
	 * Searches through the avatars in the shop and returns the instance of
	 * player with the specified id and removes the player from inside the shop.
	 * 
	 * @param id
	 * @return
	 * @throws GameException
	 */
	private Player findPlayer(int id) throws GameException {
		if (players.isEmpty()) {
			throw new GameException("No players in the shop");
		}
		for (int i = 0; i < players.size(); i++) {
			Player p = players.get(i);
			if (p.getId() == id) {
				players.remove(i);
				return p;
			}
		}
		throw new GameException("Player not found");
	}
}
