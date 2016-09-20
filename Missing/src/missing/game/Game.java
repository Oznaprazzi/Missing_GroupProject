/*	File: Game.java
 * 	Author
 * 	Chris Rabe			300334207
 * 
 * 	Date				Author					Changes
 * 	18 Sep 16			Chris Rabe				create Game.java
 *  19 Sep 16			Casey Huang				created getAvatars method
 */

package missing.game;

import java.awt.Point;
import java.util.List;

import missing.datastorage.assetloader.XMLHandler;
import missing.game.characters.Player;
import missing.game.items.Item;
import missing.game.world.World;
import missing.game.world.nodes.WorldTile.TileObject.Direction;
import missing.helper.GameException;

/**
 * This class assumes that when the game is initialised, the XMLHandler is
 * passed in a file and the players had picked their avatars.
 */
public class Game {
	private Player[] avatars;
	private World world;

	public Game(Player[] avatars) throws GameException {
		this.avatars = avatars;
		this.world = new World();
		distributeItems(XMLHandler.getItemsFromFile());
	}

	// Getters

	public World getWorld() {
		return this.world;
	}

	public Player[] getAvatars() {
		return avatars;
	}

	// Methods for moving

	/**
	 * Moves the player to a certain direction.
	 * 
	 * @param id
	 * @param direction
	 * @throws GameException
	 */
	public void movePlayer(int id, Direction direction) throws GameException {
		Player player = avatars[id]; // retrieve player's piece
		Point newTileLocation = null;
		switch (direction) {
		case EAST:
			break;
		case NORTH:
			break;
		case SOUTH:
			break;
		case WEST:
			break;
		default:
			throw new GameException("Invalid direction. Please use NORTH, SOUTH, EAST, WEST.");
		}
	}

	/**
	 * When this method is called, it checks the object which is in front of the
	 * client based on the orientation.
	 * 
	 * @param id
	 */
	public void performAction(int id) {

	}

	// Helper methods

	/**
	 * Distributes the items inside the game world.
	 * 
	 * @param itemsFromFile
	 */
	private void distributeItems(List<Item> items) {
		for (Item i : items) {
			Point worldLocation = i.getWorldLocation();
			Point tileLocation = i.getTileLocation();
			world.distributeToNode(worldLocation, tileLocation, i);
		}
	}

}
