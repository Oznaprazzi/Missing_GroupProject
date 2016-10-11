/*	File: Source.java
 * 	
 * 	Authors:			ID
 *	Casey Huang			300316284
 *	Chris Rabe			300334207
 *	Jian Wei Chong		300352789
 *
 * 	Date				Author					Changes
 *	7 Sep 16 			Casey Huang				Created Source class and added javaDoc comments
 *	7 Sep 16			Chris Rabe				added resource field
 *  29 Sep 16			Jian Wei				Added timer
 *  30 Sep 16			Chris Rabe				created refresh time field
 *  
 */
package missing.game.items.nonmovable;

import java.awt.Point;

import missing.game.characters.Player;
import missing.game.items.movable.Resource;
import missing.helper.GameException;
import missing.helper.SignalException;

/**
 * Represents a Source item that a player can collect
 *
 */
@SuppressWarnings("serial")
public abstract class Source extends Interactable {
	protected static final int MAX_RESOURCE = 10;

	/** Represents the resource which can be collected */
	protected Resource resource;

	public Resource getResource() {
		return resource;
	}

	/**
	 * Creates a new instance of a Source item.
	 * 
	 * @param name
	 * @param description
	 * @param worldLocation
	 * @param tileLocation
	 */
	public Source(String name, String description, Point worldLocation, Point tileLocation, Resource resource) {
		super(name, description, worldLocation, tileLocation);
		this.resource = resource;

	}

	/**
	 * For player to perform an action on this Interactable item.
	 * 
	 * @throws GameException
	 * @throws SignalException 
	 */
	public abstract void performAction(Player p) throws GameException, SignalException;

}
