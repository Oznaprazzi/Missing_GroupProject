/*	File: WorldTile.java
 * 	Author:
 * 	Linus Go		300345571
 * 
 * 	Date			Author				changes
 * 	8 Sep 16		Linus Go			created WorldTile class.
 */
package missing.game.world.nodes;

import java.awt.Point;

/**
 * This class represents a WorldTile within the Game. It is the internal data structure used within a WorldNode.
 * Within a WorldTile, there are different types of Tiles, such as "Grass", "Sand" or "Water", which contain different characteristics.
 *
 */
public class WorldTile {
	
	
	/**
	 * An enumeration type for different types of Tiles. Can add or remove from this list when necessary.
	 */
	public static enum TileType{
		GRASS, ROAD, WATER, SAND
	}
	/** Holds an instance of a TileType */
	private TileType type;
	
	/** Holds the location within a WorldNode. */
	private Point nodeLocation;
	
	/** Flag for determining if a Tile is being occupied. */
	private boolean isOccupied = false;
	
	/**Points to the the other WorldNode, if this is an entrance node.*/
	//TODO: Eddy/Chris? May have to review this. How about other worldnodes.
	private WorldNode other = null;
	/**
	 * Create a new Instance of WorldTile.
	 * @param type - of Tile.
	 * @param nodeLocation - location of the tile within a point.
	 */
	public WorldTile(TileType type, Point nodeLocation){
		this.type = type;
		this.nodeLocation = nodeLocation;
	}
	
	/**
	 * Create a new instance of a WorldTile that points to another WorldNode.
	 * @param type - of Tile.
	 * @param nodeLocation - location of the tile within a point.
	 * @param other - the Other WorldNode.
	 */
	public WorldTile(TileType type, Point nodeLocation, WorldNode other){
		this(type, nodeLocation);
		this.other = other;
	}
	
	
/**
 * Internal interface for the WorldTile.	
 *
 */
public interface TileObject{
	
}
	
	
	
}
