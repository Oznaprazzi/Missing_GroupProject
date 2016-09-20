/*	File: Game.java
 * 	Author
 * 	Chris Rabe			300334207
 * 
 * 	Date				Author					Changes
 * 	18 Sep 16			Chris Rabe				create Game.java
 *  19 Sep 16			Casey Huang				created getAvatars method
 *  19 Sep 16			Chris Rabe				created moving method
 */

package missing.game;

import static missing.datastorage.initialisers.WorldInitialiser.NODE_SIZE;

import java.awt.Point;
import java.util.List;

import missing.datastorage.assetloader.XMLHandler;
import missing.game.characters.Player;
import missing.game.items.Item;
import missing.game.items.movable.Movable;
import missing.game.world.World;
import missing.game.world.nodes.WorldTile;
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
		switch (direction) {
		case EAST:
			movePlayer(player, 1, 0, direction);
			break;
		case NORTH:
			movePlayer(player, 0, -1, direction);
			break;
		case SOUTH:
			movePlayer(player, 0, 1, direction);
			break;
		case WEST:
			movePlayer(player, -1, 0, direction);
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
	 * This method moves the player's tile position by dx and dy and changes the
	 * player's orientation. If the new position is out of bounds, then it
	 * changes the player's world location.
	 * 
	 * @param player
	 * @param dx
	 * @param dy
	 * @param direction
	 * @throws GameException
	 */
	private void movePlayer(Player player, int dx, int dy, Direction direction) {
		// Get new positions for the player
		Point[] locations = getNewLocations(player, dx, dy, direction);
		// check if the tile is valid
		if (validMove(locations[0], locations[1])) {
			changePlayerPos(player, locations[0], locations[1], direction);
		}
	}

	/**
	 * Changes the player's position to the new world location and new tile
	 * location as well as change the orientation of the player. It also picks
	 * up items on the ground and stores it inside the player's pocket if there
	 * is room. If there is no room in the player's pocket, then it tries to
	 * store it inside the player's bag. If that fails too, then the item is
	 * 'crushed' by the player.
	 * 
	 * @param player
	 * @param wLoc
	 * @param tLoc
	 * @param direction
	 */
	private void changePlayerPos(Player player, Point wLoc, Point tLoc, Direction direction) {
		// Make player vanish from old location
		Point oldWLoc = player.getWorldLocation();
		Point oldTLoc = player.getTileLocation();
		WorldTile tile = world.getWorldNodes()[oldWLoc.y][oldWLoc.x].getWorldTiles()[oldTLoc.y][oldTLoc.x];
		tile.setObject(null);
		// Now we move the player...
		tile = world.getWorldNodes()[wLoc.y][wLoc.x].getWorldTiles()[tLoc.y][tLoc.x];
		// If there is an item, add it to the player's pocket.
		if (tile.isOccupied()) {
			try {
				player.addToPocket((Movable) tile.getObject());
			} catch (GameException e) {
				// If adding to pocket failed, add it to bag
				try {
					player.addToBag((Movable) tile.getObject());
				} catch (GameException e1) {
					// If it fails, player crushes the item with his weight
				}
			}
		}
		// Change the player's position
		player.setWorldLocation(wLoc);
		player.setTileLocation(tLoc);
		tile.setObject(player);
		// Change the player's orientation
		player.setOrientation(direction);
	}

	/**
	 * Gets the new world location and tile locations of the player. It returns
	 * an array with the size of two. The first element is the new world
	 * location and the second element is the new tile location.
	 * 
	 * If the player goes outside the boundaries of the node, the player is
	 * moved on to the new node. It follows these transition rules:
	 * 
	 * <ul>
	 * <li>If the player is going from left to right, the player will be located
	 * on the first column of the new node.</li>
	 * 
	 * <li>If the player is going from right to left, the player will be located
	 * on the last column of the new node.</li>
	 * 
	 * <li>If the player is going from down to up, the player will be located at
	 * the last row of the new node.</li>
	 * 
	 * <li>If the player is going from up to down, the player will be located at
	 * the first row of the new node.</li>
	 * </ul>
	 * 
	 * @param player
	 * @param dx
	 * @param dy
	 * @param direction
	 * @return
	 */
	private Point[] getNewLocations(Player player, int dx, int dy, Direction direction) {
		Point pPos = player.getTileLocation(); // player's current position
		Point wLoc = player.getWorldLocation(); // new world location
		Point tLoc = new Point(pPos.x + dx, pPos.y + dy); // new tile location
		// First check the position boundaries
		if (!withinHBounds(tLoc)) {
			// Change world location
			switch (direction) {
			case EAST: // left to right
				tLoc.x = 0;
				wLoc = shiftNode(player, 1, 0);
				break;
			default: // right to left
				tLoc.x = 9;
				wLoc = shiftNode(player, -1, 0);
				break;
			}
		} else if (!withinVBounds(tLoc)) {
			// Change world location
			switch (direction) {
			case NORTH: // down to up
				tLoc.y = 9;
				wLoc = shiftNode(player, 0, -1);
				break;
			default: // up to down
				tLoc.y = 0;
				wLoc = shiftNode(player, 0, 1);
				break;
			}
		}
		return new Point[] { wLoc, tLoc };
	}

	/**
	 * This method checks if the new position is a valid location for the
	 * player. This method checks two things:
	 * <ul>
	 * <li>checks whether the tile is enterable.</li>
	 * <li>checks if the tile is occupied by an object.</li>
	 * </ul>
	 * 
	 * @param wLoc
	 * @param tLoc
	 * @return
	 */
	private boolean validMove(Point wLoc, Point tLoc) {
		// First check if the tile is enterable
		WorldTile tile = world.getWorldNodes()[wLoc.y][wLoc.x].getWorldTiles()[tLoc.y][tLoc.x];
		if (!tile.isEnterable()) {
			return false;
		}
		// Next check if the tile is occupied by an object
		if (tile.isOccupied()) {
			// Only movable objects can be walked over -- can pick them up
			if (tile.getObject() != null && tile.getObject() instanceof Movable) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Shifts the player's world location by the given dx and dy.
	 * 
	 * @param player
	 * @param dx
	 * @param dy
	 * @return
	 */
	private Point shiftNode(Player player, int dx, int dy) {
		Point pPos = player.getWorldLocation(); // player's world position
		Point tmp = new Point(pPos.x + dx, pPos.y + dy);
		return tmp;
	}

	/**
	 * Checks whether the x axis of the player is within horizontal boundaries
	 * of the WorldNode's number of tiles.
	 * 
	 * @param loc
	 * @return
	 */
	private boolean withinHBounds(Point loc) {
		return 0 <= loc.x && loc.x < NODE_SIZE;
	}

	/**
	 * Checks whether the y axis of the player is within vertical boundaries of
	 * the the WorldNode's number of tiles.
	 * 
	 * @param loc
	 * @return
	 */
	private boolean withinVBounds(Point loc) {
		return 0 <= loc.y && loc.y < NODE_SIZE;
	}

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
