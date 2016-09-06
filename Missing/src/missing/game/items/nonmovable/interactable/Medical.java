/* File: Medical.java
 * 
 * Authors			ID
 * Casey Huang		300316284
 * 
 * Date				Author			Modification
 *	6 Sep 2016		Casey Huang		Created Medical abstract class
 *	6 Sep 2016		Casey Huang		Added javadoc comments and descriptions.
 */
package missing.game.items.nonmovable.interactable;

/**
 * Represents an Object that helps health and extends Furniture.
 *
 */
public abstract class Medical extends Furniture {
	// TODO add fields which represent amount it can heal player for

	/**
	 * Creates an instance of a Medical item.
	 * 
	 * @param name
	 * @param description
	 */
	public Medical(String name, String description) {
		super(name, description);
	}

}
