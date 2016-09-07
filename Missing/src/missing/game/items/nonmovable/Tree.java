/*	File: Tree.java
 * 	
 * 	Authors:			ID
 *	Casey Huang			300316284
 * 	Date				Author					Changes
 *	7 Sep 16 			Casey Huang				Created Tree class and added javaDoc comments
 *	7 Sep 16			Casey Huang				Updated the performAction method.
 */
package missing.game.items.nonmovable;

import java.awt.Point;

import missing.game.characters.Player;
import missing.game.items.movable.Resource;
import missing.game.items.movable.Wood;
import missing.helper.GameException;

/**
 * Represents a Tree item that a player can cut down to get the wood resource
 */
public class Tree extends Source{

	/**
	 * Creates an instance of a Tree item.
	 * @param name
	 * @param description
	 * @param worldLocation
	 * @param tileLocation
	 * @param resource
	 */
	public Tree(String name, String description, Point worldLocation, Point tileLocation, Resource resource) {
		super(name, description, worldLocation, tileLocation, resource);
	}

	@Override
	public void performAction(Player p) {
		try {
			p.addToPocket(new Wood(worldLocation, tileLocation, 5, 1));
		} catch (GameException e) {
			e.printStackTrace();
		}
	}

}
