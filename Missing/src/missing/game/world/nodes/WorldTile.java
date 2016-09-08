/*	File: WorldTile.java
 * 	Author:
 * 	Linus Go		300345571
 * 
 * 	Date			Author				changes
 * 	8 Sep 16		Linus Go			created WorldTile class.
 */
package missing.game.world.nodes;

import java.awt.Point;
import java.util.List;

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
	
	/**List that Points to the the other WorldNode, if this is an entrance node.*/
	private List<WorldNode> other;
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
	 * @param otherWorlds - the list of other WorldNode(s).
	 */
	public WorldTile(TileType type, Point nodeLocation, List<WorldNode> otherWorlds){
		this(type, nodeLocation);
		this.other = otherWorlds;
	}
	
	/**
	 * Sets the flag whether a tile is occupied.
	 * @param v - boolean value.
	 */
	public void setIsOccupied(boolean v){
		this.isOccupied = v;
	}
	
	/**
	 * Returns whether if a tile is Occupied.
	 */
	public boolean isOccupied(){
		return this.isOccupied;
	}
	
	/**
	 * Returns the other world node list.
	 * @return
	 */
	public List<WorldNode> geOtherWorldNodeList(){
		return this.other;
	}
	/**
	 * Returns the type of a Tile.
	 * @return
	 */
	public TileType getType(){
		return this.type;
	}
	
	/**
	 * Returns the location of a Tile within a WorldNode.
	 * 
	 */
	public Point getTileLocation(){
		return this.nodeLocation;
	}
	
	
	
	
/**
 * Internal interface for the WorldTile.	
 *
 */
public interface TileObject{
	
}




@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + (isOccupied ? 1231 : 1237);
	result = prime * result + ((nodeLocation == null) ? 0 : nodeLocation.hashCode());
	result = prime * result + ((other == null) ? 0 : other.hashCode());
	result = prime * result + ((type == null) ? 0 : type.hashCode());
	return result;
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	WorldTile other = (WorldTile) obj;
	if (isOccupied != other.isOccupied)
		return false;
	if (nodeLocation == null) {
		if (other.nodeLocation != null)
			return false;
	} else if (!nodeLocation.equals(other.nodeLocation))
		return false;
	if (this.other == null) {
		if (other.other != null)
			return false;
	} else if (!this.other.equals(other.other))
		return false;
	if (type != other.type)
		return false;
	return true;
}

@Override
public String toString() {
	return "WorldTile [type=" + type + ", nodeLocation=" + nodeLocation + ", isOccupied=" + isOccupied + ", other="
			+ other + "]";
}
	
	
	
}
