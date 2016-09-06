package missing.game.items.nonmovable;

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
