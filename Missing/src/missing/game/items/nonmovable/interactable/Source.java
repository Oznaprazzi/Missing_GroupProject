/* File: Source.java
 * 
 * Authors			ID
 * Casey Huang		300316284
 * 
 * Date				Author			Modification
 *	6 Sep 2016		Casey Huang		Created Source abstract class
 *	6 Sep 2016		Casey Huang		Added javadoc comments and descriptions.
 */
package missing.game.items.nonmovable.interactable;

import missing.game.items.nonmovable.Interactable;

/**
 * Represents resources that a Player can take to survive.
 * @author Casey Huang
 *
 */
public abstract class Source extends Interactable{
	/**
	 * Creates an instance of a Source item.
	 * @param name
	 * @param description
	 */
	public Source(String name, String description) {
		super(name, description);
	}

}
