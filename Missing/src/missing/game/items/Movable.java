/* File: Movable.java
 * 
 * Authors:
 * Edward Kelly 300334192
 * 
 * Date			Author			Modification
 * 5 Sep 16		Edward Kelly	created Movable class
 */
package missing.game.items;

import java.awt.Point;

/**
 * Represents an item that can be moved/carried in the game. Movable items 
 * can have their location changed.
 *
 */
public abstract class Movable extends Item {
	
	public void setWorldLocation(Point destination){
		super.worldLocation = destination;
	}
	
	public void setRenderLocation(Point destination){
		super.renderLocation = destination;
	}
}
