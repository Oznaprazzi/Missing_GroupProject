/* File: Cooking.java
 * 
 * Authors			ID
 * Casey Huang		300316284
 * 
 * Date				Author			Modification
 *	6 Sep 2016		Casey Huang		Created Cooking abstract class
 *	6 Sep 2016		Casey Huang		Added javadoc comments and descriptions.
 */
package missing.game.items.nonmovable.interactable.furniture;

import missing.game.items.nonmovable.interactable.Furniture;

/**
 * Represents an Object used for cooking and extends Furniture
 * @author Casey Huang
 *
 */
public abstract class Cooking extends Furniture{
	/**
	 * Creates an instance of a Cooking item.
	 * @param name
	 * @param description
	 */
	public Cooking(String name, String description) {
		super(name, description);
	}

}
