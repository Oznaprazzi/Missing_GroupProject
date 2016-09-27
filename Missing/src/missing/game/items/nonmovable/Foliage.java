/*	File: Foliage.java
 * 
 * 	Author			ID
 * 	Chris Rabe		300334207
 * 	
 * 	Date			Author				Changes
 * 	7 Sep 16		Chris Rabe			create foliage class
 */

package missing.game.items.nonmovable;

import java.awt.Point;

/**
 * This class represents non movable objects which is purely for aesthetic
 * reasons.
 */
@SuppressWarnings("serial")
public abstract class Foliage extends NonInteractable {

	public Foliage(String name, String description, Point worldLocation, Point tileLocation) {
		super(name, description, worldLocation, tileLocation);
	}
}
