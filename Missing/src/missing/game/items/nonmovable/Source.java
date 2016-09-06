package missing.game.items.nonmovable;
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
