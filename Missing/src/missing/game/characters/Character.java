/*	File: Character.java
 * 	Author
 * 	Chris Rabe			300334207
 * 
 * 	Date				Author				Changes
 * 	19 Sep 16			Chris Rabe			create character.java
 * 	20 Sep 16			Chris Rabe			implemented orientation and approach
 */
package missing.game.characters;

import java.awt.Point;
import java.io.Serializable;

import missing.game.world.nodes.WorldTile.TileObject;

/**
 * This provides the skeleton implementation of the Objects within the world.
 * This should be extended by Player classes and NPCs.
 */
public abstract class Character implements TileObject, Serializable {

	
	protected static final long serialVersionUID = 1166932944531822141L;
	private String name;
	private String description;
	private Point worldLocation;
	private Point tileLocation;

	private Direction orientation;
	private Direction approach;
	/**
	 * Construct a new Character object.
	 * @param name of the character
	 * @param description of the character
	 * @param worldLocation of the character
	 * @param tileLocation of the character
	 * @param orientation of the character
	 * @param approach - to check if the character is facing the correct way when performing
	 * an action.
	 */
	public Character(String name, String description, Point worldLocation, Point tileLocation, Direction orientation,
			Direction approach) {
		this.name = name;
		this.description = description;
		this.worldLocation = worldLocation;
		this.tileLocation = tileLocation;
		this.orientation = orientation;
		this.approach = approach;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public Point getTileLocation() {
		return tileLocation;
	}

	@Override
	public void setTileLocation(Point tileLocation) {
		this.tileLocation = tileLocation;
	}

	@Override
	public Point getWorldLocation() {
		return worldLocation;
	}

	@Override
	public void setWorldLocation(Point worldLocation) {
		this.worldLocation = worldLocation;
	}

	@Override
	public Direction getOrientation() {
		return orientation;
	}

	public void setOrientation(Direction direction) {
		this.orientation = direction;
	}

	@Override
	public Direction getApproach() {
		return approach;
	}

}
