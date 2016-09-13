/*	File: WorldTile.java
 * 	Author:
 * 	Linus Go		300345571
 * 	Chris Rabe		300334207
 * 
 * 	Date			Author				changes
 * 	8 Sep 16		Linus Go			created WorldTile class.
 * 	8 Sep 16		Chris Rabe			removed some methods and fields
 *  8 Sep 16		Edward Kelly		added new constructor
 *  8 Sep 16		Chris Rabe			added methods inside the TileObject interface
 */
package missing.game.world.nodes;

import java.awt.Point;

import missing.game.items.nonmovable.NonMovable;

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
		public String getName();

		public String getDescription();

		public Point getTileLocation();

		public void setTileLocation(Point tileLocation);

		public Point getWorldLocation();

		public void setWorldLocation(Point worldLocation);
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

	/** Represents the entity or item within the tile */
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
		return object != null;
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
