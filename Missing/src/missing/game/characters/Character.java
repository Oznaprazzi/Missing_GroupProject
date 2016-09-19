/*	File: Character.java
 * 	Author
 * 	Chris Rabe			300334207
 * 
 * 	Date				Author				Changes
 * 	19 Sep 16			Chris Rabe			create character.java
 */
package missing.game.characters;

import java.awt.Point;

import missing.game.world.nodes.WorldTile.TileObject;

/**
 * This provides the skeleton implementation of the Objects within the world.
 * This should be extended by Player classes and NPCs.
 */
public abstract class Character implements TileObject {
	
	private String name;
	private String description;
	private Point worldLocation;
	private Point tileLocation;

	private Direction orientation;
	private Direction approach;

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

	@Override
	public Direction getApproach() {
		return approach;
	}

}
