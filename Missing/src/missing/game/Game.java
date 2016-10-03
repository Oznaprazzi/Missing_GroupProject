/*	File: Game.java
 * 	Author
 * 	Chris Rabe			300334207
 * 	Casey Huang			300316284
 * 
 * 	Date				Author					Changes
 * 	18 Sep 16			Chris Rabe				create Game.java
 *  19 Sep 16			Casey Huang				created getAvatars method
 *  19 Sep 16			Chris Rabe				created moving method
 *  22 Sep 16			Chris Rabe				implemented performAction method
 *  22 Sep 16			Chris Rabe				implemented rotation method
 *  27 Sep 16			Chris Rabe				upgraded game logic
 *  27 Sep 16			Chris Rabe				implemented spawn points
 *  30 Sep 16			Chris Rabe				added null checks
 */

package missing.game;

import static missing.datastorage.initialisers.WorldInitialiser.NODE_SIZE;

import java.awt.Point;
import java.io.Serializable;
import java.util.List;

import missing.datastorage.assetloader.XMLHandler;
import missing.datastorage.initialisers.WorldInitialiser;
import missing.game.characters.Player;
import missing.game.items.Item;
import missing.game.items.movable.Movable;
import missing.game.world.World;
import missing.game.world.nodes.WorldTile;
import missing.game.world.nodes.WorldTile.Pile;
import missing.game.world.nodes.WorldTile.TileObject;
import missing.game.world.nodes.WorldTile.TileObject.Direction;
import missing.helper.GameException;
import missing.helper.SignalException;

/**
 * This class assumes that when the game is initialised, the XMLHandler is
 * passed in a file and the players had picked their avatars.
 * 
 * 
 */
@SuppressWarnings("serial")
public class Game implements Serializable {

	/**
	 * This class stores information of all the available spawn area for the
	 * map. Since the map is always the same for each game, then it is safe to
	 * create pre-defined set of points.
	 */
	public static class Spawn implements Serializable {
		public Point worldLocation;
		public Point tileLocation;

		public Spawn(Point worldLocation, Point tileLocation) {
			this.worldLocation = worldLocation;
			this.tileLocation = tileLocation;
		}
	}

	private Player[] avatars;
	private World world;
	private List<Spawn> spawns;

	public Game(Player[] avatars) throws GameException {
		this.avatars = avatars;
		this.world = new World();
		this.spawns = WorldInitialiser.getSpawnPoints();
		distributeItems(XMLHandler.getItemsFromFile());
	}

	// Getters

	public World getWorld() {
		return this.world;
	}

	public Player[] getAvatars() {
		return avatars;
	}

	public List<Spawn> getSpawns() {
		return spawns;
	}

	// Methods for interacting with the game

	// TODO create a method which checks if the current client is dead

	/**
	 * Moves the player to a certain direction.
	 * 
	 * Preconditions:
	 * <ul>
	 * <li>id passed must be within the boundaries of the avatar length.</li>
	 * </ul>
	 * 
	 * @param id
	 * @param direction
	 * @throws GameException
	 */
	public void movePlayer(int id, Direction direction) throws GameException {
		Player player = avatars[id]; // retrieve player's piece
		if (player.isDead()) {
			throw new GameException("You are currently dead");
		}
		int[] dCoords = getCoordChange(direction);
		movePlayer(player, dCoords[0], dCoords[1], direction);
	}

	/**
	 * Changes the player's orientation based on the given direction.
	 * Preconditions for this method MUST be followed.
	 * 
	 * Preconditions:
	 * <ul>
	 * <li>Direction passed MUST be WEST or EAST.</li>
	 * <li>id passed must be within the boundaries of the avatar length.</li>
	 * </ul>
	 * 
	 * @param id
	 * @param direction
	 * @throws GameException
	 */
	public void turnPlayer(int id, Direction direction) throws GameException {
		Player player = avatars[id];
		if (player.isDead()) {
			throw new GameException("You are currently dead!");
		}
		switch (direction) {
		case EAST:
			turnPlayer(player, 1);
			break;
		case WEST:
			turnPlayer(player, -1);
			break;
		default:
			throw new GameException("Can only turn left or right!");
		}
	}

	/**
	 * When this method is called, it checks the object which is in front of the
	 * client based on the orientation. If the method throws a signal exception,
	 * this means that a special action need to be made.
	 * 
	 * @param id
	 * @see SignalException
	 * @throws GameException
	 * @throws SignalException
	 */
	public void performAction(int id) throws GameException, SignalException {
		Player player = avatars[id];
		if (player.isDead()) {
			throw new GameException("You are currently dead!");
		}
		// get the item in front of the player
		TileObject object = getObjectInFront(id);
		// Check if it is a valid approach
		if (validApproach(player, object)) {
			try {
				object.performAction(player);
			} catch (SignalException e) {
				// catch dead player
				if (e.getMessage().contains("DEAD")) {
					System.out.println("Dead player");
					removeDeadPlayer(e.getMessage());
				}
				throw new SignalException(e.getMessage());
			}
		}
	}

	/**
	 * This method should be called before the movePlayer method. The reason for
	 * this is because you want to be able to animate the player's movements
	 * before setting the player into the new square.
	 * 
	 * @param id
	 * @param direction
	 * @return
	 * @throws GameException
	 */
	public boolean validMove(int id, Direction direction) throws GameException {
		Player player = avatars[id];
		if (player.isDead()) {
			return false;
		}
		int[] dCoords = getCoordChange(direction);
		Point[] tLoc = getNewLocations(player, dCoords[0], dCoords[1], direction);
		return validMove(tLoc[0], tLoc[1]);
	}

	/**
	 * Retrieves the tilObject in front of the defined player parameter by
	 * analysing the defined player's orientation.
	 * 
	 * @param id
	 * @return
	 * @throws GameException
	 */
	public TileObject getObjectInFront(int id) throws GameException {
		Player player = avatars[id];
		// retrieve the dx and dy values which represents moving forward
		int[] dCoords = getCoordChange(player.getOrientation());
		// retrieve the world location and tile location values of item in front
		Point[] tLoc = getNewLocations(player, dCoords[0], dCoords[1], player.getOrientation());
		WorldTile tile = world.getWorldNodes()[tLoc[0].y][tLoc[0].x].getWorldTiles()[tLoc[1].y][tLoc[1].x];
		return tile.getObject();
	}

	/**
	 * This converts the player with the given id to a pile of items. This
	 * method should be called to handle client disconnection from the server.
	 * 
	 * @param id
	 */
	public void convertPlayerToPile(int id) {
		Player player = avatars[id];
		Point wLoc = player.getWorldLocation();
		Point tLoc = player.getTileLocation();
		WorldTile tile = world.getWorldNodes()[wLoc.y][wLoc.x].getWorldTiles()[tLoc.y][tLoc.x];
		tile.convertPlayerToPile(player);
	}

	// Helper methods

	/**
	 * Converts the player to a pile object and removes that player from the
	 * map.
	 * 
	 * @param id
	 */
	private void removeDeadPlayer(String signal) {
		String[] message = signal.split(" ");
		int id = Integer.parseInt(message[1]);
		System.out.println("id: " + id);
		convertPlayerToPile(id);
	}

	/**
	 * This method changes the player's orientation based on the rotation
	 * parameter passed. If the rotation parameter is greater than 0, then it
	 * turns the player clockwise. If the rotation parameter is less than 0,
	 * then it turns the player anti-clockwise.
	 * 
	 * @param player
	 * @param rotation
	 * @throws GameException
	 */
	private void turnPlayer(Player player, int rotation) throws GameException {
		Direction newOrien = getNewOrientation(player.getOrientation(), rotation);
		player.setOrientation(newOrien);
	}

	/**
	 * This method returns the new orientation based on the rotation parameter
	 * passed and with relation to the orientation parameter. If the rotation
	 * parameter is greater than 0, then it returns the next direction
	 * clockwise. If the rotation parameter is less than 0, then it returns the
	 * next direction anti-clockwise.
	 * 
	 * @param orientation
	 * @param rotation
	 * @return
	 * @throws GameException
	 */
	private Direction getNewOrientation(Direction orientation, int rotation) throws GameException {
		Direction newOrien = null;
		switch (orientation) {
		case NORTH:
			newOrien = rotation > 0 ? Direction.EAST : Direction.WEST;
			break;
		case EAST:
			newOrien = rotation > 0 ? Direction.SOUTH : Direction.NORTH;
			break;
		case SOUTH:
			newOrien = rotation > 0 ? Direction.WEST : Direction.EAST;
			break;
		case WEST:
			newOrien = rotation > 0 ? Direction.NORTH : Direction.SOUTH;
			break;
		default:
			throw new GameException("wrong parameter!");
		}
		return newOrien;
	}

	/**
	 * This method checks if the player approached the object correctly based on
	 * the player's orientation and the object's approach direction. Usually,
	 * the approach of the object and the player's orientation must be opposite
	 * to each other to simulate that the two items are 'facing each other'. A
	 * special case for a valid approach is when the object's approach direction
	 * is set to 'ALL'.
	 * 
	 * @param player
	 * @param object
	 * @return
	 */
	private boolean validApproach(Player player, TileObject object) {
		if (object == null || player == null) {
			return false;
		}
		if (object.getApproach() == Direction.ALL) {
			return true;
		}
		switch (player.getOrientation()) {
		case EAST:
			return object.getApproach() == Direction.WEST;
		case NORTH:
			return object.getApproach() == Direction.SOUTH;
		case SOUTH:
			return object.getApproach() == Direction.NORTH;
		case WEST:
			return object.getApproach() == Direction.EAST;
		default:
			return false;
		}
	}

	/**
	 * Returns the dx and dy values for the given direction. It stores it inside
	 * an integer array which contains two elements. The first element
	 * represents dx and the second element represents dy.
	 * 
	 * @param direction
	 * @return
	 */
	private int[] getCoordChange(Direction direction) throws GameException {
		int[] coords = new int[2];
		switch (direction) {
		case EAST:
			coords[0] = 1;
			coords[1] = 0;
			break;
		case NORTH:
			coords[0] = 0;
			coords[1] = -1;
			break;
		case SOUTH:
			coords[0] = 0;
			coords[1] = 1;
			break;
		case WEST:
			coords[0] = -1;
			coords[1] = 0;
			break;
		default:
			throw new GameException("Invalid direction. Please use NORTH, SOUTH, EAST, WEST.");
		}
		return coords;
	}

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
		if (tile.getObject() instanceof Pile) {
			System.out.println("Pile found");
			tile.removePlayerFromPile();
		} else {
			tile.setObject(null);
		}
		// Now we move the player...
		tile = world.getWorldNodes()[wLoc.y][wLoc.x].getWorldTiles()[tLoc.y][tLoc.x];
		// Change the player's position
		player.setWorldLocation(wLoc);
		player.setTileLocation(tLoc);
		// Change the player's orientation
		player.setOrientation(direction);
		// If there is an item, add the player to the pile
		if (tile.isOccupied()) {
			tile.addPlayerToPile(player);
		} else {
			tile.setObject(player);
		}
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
			if (tile.getObject() != null && (tile.getObject() instanceof Movable || tile.getObject() instanceof Pile)) {
				return true;
			}
			return false;
		}
		return true;
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
