package missing.game.items.nonmovable;
/**
 * Represents an Object that helps health and extends Furniture.
 * @author Casey Huang
 *
 */
public abstract class Medical extends Furniture{
	/**
	 * Creates an instance of a Medical item.
	 * @param name
	 * @param description
	 */
	public Medical(String name, String description) {
		super(name, description);
	}

}
