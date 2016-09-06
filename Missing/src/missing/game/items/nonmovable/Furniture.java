/* File: Furniture.java
 * 
 * Authors			ID
 * Casey Huang			300316284
 * 
 * Date				Author			Modification
 *	6 Sep 2016		Casey Huang		Created Furniture abstract class
 *	6 Sep 2016		Casey Huang		Added javadoc comments and descriptions.
 */
package missing.game.items.nonmovable;

/**
 * Represents all furniture items
 * @author Casey Huang
 *
 */
public abstract class Furniture extends Interactable{
	
	/**
	 * Constructs a new instance of a Furniture item.
	 * @param name
	 * @param description
	 */
	public Furniture(String name, String description){
		super(name, description);
	}
	
	//TODO display method
}
