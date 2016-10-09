/*	File: WorldTile.java
 * 	Author:
 * 	Linus Go		300345571
 * 	Chris Rabe		300334207
 * 	Edward Kelly	300334192
 * 
 * 	Date			Author				changes
 * 	8 Sep 16		Linus Go			created WorldTile class.
 * 	8 Sep 16		Chris Rabe			removed some methods and fields
 *  8 Sep 16		Edward Kelly		added new constructor
 *  8 Sep 16		Chris Rabe			added methods inside the TileObject interface
 *  18 Sep 16		Chris Rabe			can now receive distributed item
 *  23 Sep 16		Casey Huang			put if statement after item is assigned in getDistributedObject method
 *  27 Sep 16		Chris Rabe			created Pile class
 *  3 Oct 16		Chris Rabe			modified Pile class constructor
 *  4 Oct 16		Chris Rabe			fixed pile bug not returning pile object if player is inside a pile
 */
package missing.game.world.nodes;

import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import missing.game.characters.Player;
import missing.game.items.nonmovable.FishArea;
import missing.game.items.nonmovable.NonMovable;
import missing.helper.GameException;
import missing.helper.SignalException;

/**
 * This class represents a WorldTile within the Game. It is the internal data
 * structure used within a WorldNode. Within a WorldTile, there are different
 * types of Tiles, such as "Grass", "Sand" or "Water", which contain different
 * characteristics.
 *
 */
@SuppressWarnings("serial")
public class WorldTile implements Serializable {

	/**
	 * This interface represents all objects which can be stored inside the
	 * WorldTile.
	 */
	public interface TileObject {

		/**
		 * This enumeration should be used to represent where the object is
		 * facing or where the object can be approached from. The 'ALL'
		 * enumeration is a special value which indicates that the object can be
		 * approached in all directions (this should not be used if this
		 * enumeration is used to represent where the item is facing)
		 */
		public static enum Direction {
			NORTH, SOUTH, EAST, WEST, ALL
		}

		public String getName();

		public String getDescription();

		public Point getTileLocation();

		public void setTileLocation(Point tileLocation);

		public Point getWorldLocation();

		public void setWorldLocation(Point worldLocation);

		/**
		 * Returns the direction which the TileObject is facing.
		 */
		public Direction getOrientation();

		/**
		 * Returns the direction which the TileObject can be approached from.
		 */
		public Direction getApproach();

		/**
		 * Performs the default action for the specified item. If the item is a
		 * movable object, then the player picks that item up. If the item is
		 * interactable, then it allows the user to perform special actions such
		 * as cook food or collect resources.
		 * 
		 * @param player
		 * @throws GameException
		 * @throws SignalException
		 */
		public void performAction(Player player) throws GameException, SignalException;
	}

	/**
	 * This class is created if the player wants to step inside the tile and
	 * there is an object inside it. This class is contained inside the
	 * WorldTile class because it is a class created for additional
	 * functionality.
	 */
	public static class Pile extends NonMovable {

		private TileObject player;
		private List<TileObject> items;

		public Pile(TileObject object) {
			super("Pile", "A pile of objects", object.getWorldLocation(), object.getTileLocation());
			items = new ArrayList<TileObject>();
			if (object instanceof Player) {
				this.player = object;
			} else {
				this.items.add(object);
			}
		}

		public Pile(Point worldLocation, Point tileLocation, List<TileObject> items) {
			super("Pile", "A pile of objects", worldLocation, tileLocation);
			this.items = items;
		}

		// Implemented Methods

		@Override
		public void performAction(Player player) throws GameException, SignalException {
			if (this.player != null) {
				this.player.performAction(player);
			} else {
				throw new SignalException("PILE");
			}
		}

		// Methods

		public boolean hasPlayer() {
			return player != null;
		}

		public TileObject getPlayer() {
			return player;
		}

		public void setPlayer(TileObject player) {
			this.player = player;
		}

		/** Adds the item to the pile of items */
		public void addItem(TileObject item) {
			items.add(item);
		}

		public void addAllItems(List<? extends TileObject> item) {
			items.addAll(item);
		}

		public List<TileObject> getItems() {
			return items;
		}
	}

	/**
	 * An enumeration type for different types of Tiles. Can add or remove from
	 * this list when necessary.
	 */
	public static enum TileType {
		GRASS, ROAD, WATER, SAND
	}

	/** Holds an instance of a TileType */
	private TileType type;

	/** Holds the location within a WorldNode. */
	private Point nodeLocation;

	/** Represents whether or not players can enter this tile */
	private boolean enterable;

	/** Represents the item within the tile */
	private TileObject object;

	public WorldTile(TileType type, Point nodeLocation) {
		this.type = type;
		this.nodeLocation = nodeLocation;
		this.enterable = determineEnterable();
	}

	// Getters and Setters...

	public TileType getType() {
		return type;
	}

	public void setType(TileType type) {
		this.type = type;
	}

	public Point getNodeLocation() {
		return nodeLocation;
	}

	public void setNodeLocation(Point nodeLocation) {
		this.nodeLocation = nodeLocation;
	}

	public TileObject getObject() {
		if (object instanceof Pile) {
			if (((Pile) object).hasPlayer()) {
				return ((Pile) object).getPlayer();
			}
		}
		return object;
	}

	public void setObject(TileObject object) {
		this.object = object;
	}

	public boolean isEnterable() {
		return enterable;
	}

	// Method

	/** Specifies whether or not the tile is occupied */
	public boolean isOccupied() {
		return object != null;
	}

	/** this check needs to be performed when moving around the game */
	public boolean hasPile() {
		return object != null && object instanceof Pile;
	}

	/**
	 * Removes the player from the pile. If the pile only has one item, it
	 * retrieves that item from the pile, reverts the pile object into an item.
	 * This method assumes that the current object field is an instance of Pile.
	 */
	public void removePlayerFromPile() {
		// Sanity check - must have pile
		if (object instanceof Pile) {
			Pile pile = (Pile) object;
			pile.setPlayer(null);
			// now check if we can revert the pile to an object only
			if (pile.getItems().isEmpty()) {
				this.object = null;
			} else if (pile.getItems().size() == 1) {
				this.object = pile.getItems().get(0);
			} else {
				this.object = pile;
			}
		}
	}

	/**
	 * Adds the player to the pile. If a pile is not created yet, the object
	 * field is converted into a pile and the player is inserted into the pile.
	 */
	public void addPlayerToPile(Player player) {
		// Sanity check - must have pile
		if (object instanceof Pile) {
			Pile pile = (Pile) object;
			pile.setPlayer(player);
			this.object = pile;
		} else {
			createPile();
			addPlayerToPile(player);
		}
	}

	/**
	 * Adds the given item to the pile. If a pile is not created yet, it creates
	 * the pile and then adds the passed item into the new pile.
	 */
	public void addObjectToPile(TileObject item) {
		// Sanity check - must have pile
		if (object instanceof Pile) {
			Pile pile = (Pile) object;
			pile.addItem(item);
			this.object = pile;
		} else {
			createPile();
			addObjectToPile(item);
		}
	}

	/**
	 * This method should be called whenever a player disconnects from the game.
	 * It turns the player into a pile of items.
	 * 
	 * @param player
	 */
	public void convertPlayerToPile(Player player) {
		// Sanity check - must have pile
		if (object instanceof Pile) {
			Pile pile = (Pile) object;
			if (player.isDead()) {
				pile.setPlayer(null);
			}
			pile.addItem(player.getMoney());
			pile.addAllItems(player.getPocket().getItems());
			pile.addAllItems(player.getBag().getItems());
			this.object = pile;
		} else {
			createPile();
			convertPlayerToPile(player);
		}
	}

	/**
	 * This method is solely used for initialisation purposes. It does some
	 * checks to see whether or not it is valid to add the object inside the
	 * tile. If it is invalid, it discards the object
	 * 
	 * @param item
	 */
	public void getDistributedObject(TileObject item) {
		if (type == TileType.WATER && item instanceof FishArea) {
			this.object = item;
			return;
		}
		if (!this.isEnterable() || this.isOccupied()) {
			return;
		}
		this.object = item;
		// redetermine enterable
		this.enterable = determineEnterable();
	}

	// Helper methods

	/**
	 * Creates a pile so that multiple items can be stored within the tile.
	 */
	private void createPile() {
		Pile tmp = new Pile(this.object);
		this.object = tmp;
	}

	private boolean determineEnterable() {
		if (object == null && type != TileType.WATER) {
			return true;
		}
		switch (type) {
		case WATER:
			return false;
		default:
			if (object instanceof NonMovable) {
				if (object instanceof Pile) {
					return true;
				}
				return false;
			} else {
				return true;
			}
		}
	}

	@Override
	public String toString() {
		return type.toString().substring(0, 1);
	}
}
