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

	public World getWorld() {
		// TODO Auto-generated method stub
		return null;
	}

	public Player[] getAvatars() {
		// TODO Auto-generated method stub
		return null;
	}
}
