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
 */
package missing.game.world.nodes;

import java.awt.Point;

import javax.swing.plaf.synth.SynthSeparatorUI;

import missing.game.characters.Player;
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
public class WorldTile {

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
		System.out.println(object);
		return this.object != null;
	}

	/**
	 * This method is solely used for initialisation purposes. It does some
	 * checks to see whether or not it is valid to add the object inside the
	 * tile. If it is invalid, it discards the object
	 * 
	 * @param item
	 */
	public void getDistributedObject(TileObject item) {
		this.object = item;
		if (!this.isEnterable() || this.isOccupied()) {
			return;
		}
		// redetermine enterable
		this.enterable = determineEnterable();
	}

	// Helper methods

	private boolean determineEnterable() {
		if (object == null && type != TileType.WATER) {
			return true;
		}
		switch (type) {
		case WATER:
			return false;
		default:
			if (object instanceof NonMovable) {
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
