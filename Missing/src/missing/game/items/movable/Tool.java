/*File : Tool.java
 * 
 * Authors    		ID
 * Jian Wei Chong	300352789
 * Chris Rabe		300334207
 * 
 * Date 		Author		Modification
 * 18/9/16		Jian Wei	created the class
 * 19/9/16		Jian Wei	added the creation of axe and pickaxe
 * 19/9/16		Chris Rabe	added javadocs and rearranged methods
 * 20/9/16		Jian Wei	added creation of shovel
 * 22/9/16		Jian Wei	added creation of fishing rod
 * 29/9/16		Casey Huang	created setInfo method and updated second constructor to add in set info.
 * 2/10/16		Chris Rabe	restructured tool class
 */
package missing.game.items.movable;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import missing.game.characters.Player;
import missing.helper.GameException;

/**
 * This class represents a Tool item in the Game. It extends the Craftable,
 * Usable, Movable and Item subclasses. The tool can be used by the player to
 * receive bonus number of items.
 */
@SuppressWarnings("serial")
public class Tool extends Craftable {

	private final int MAX_DURABILITY = 10;

	public static enum ToolType {
		AXE, PICKAXE, SHOVEL, FISHINGROD
	}

	/** Number of times tool can be used before i breaks */
	private int durability;
	private ToolType type;

	/**
	 * Construct a new Tool Object.
	 * 
	 * @param worldLocation
	 * @param tileLocation
	 * @param type
	 */
	public Tool(Point worldLocation, Point tileLocation, ToolType type) {
		super(worldLocation, tileLocation);
		this.durability = MAX_DURABILITY;
		setInfo(type);
		setIngredients(type);
		this.type = type;
	}

	/**
	 * Construct a new Tool Object with a specified preset durability.
	 * 
	 * @param worldLocation
	 * @param tileLocation
	 * @param type
	 * @param durability
	 */
	public Tool(Point worldLocation, Point tileLocation, ToolType type, int durability) {
		this(worldLocation, tileLocation, type);
		this.durability = durability;
	}

	/**
	 * Construct a new Tool Object with a specified preset durability and
	 * abmount.
	 * 
	 * @param worldLocation
	 * @param tileLocation
	 * @param type
	 * @param durability
	 * @param amount
	 */
	public Tool(Point worldLocation, Point tileLocation, ToolType type, int durability, int amount) {
		this(worldLocation, tileLocation, type, durability);
		super.setAmount(amount);
	}

	// Getters

	public int getDurability() {
		return durability;
	}

	public void setDurability(int durability) {
		this.durability = durability;
	}

	public ToolType getType() {
		return type;
	}

	public void setType(ToolType type) {
		this.type = type;
	}

	// Methods

	/**
	 * This decreases the durability of the tool. Returns true if it has no more
	 * durability
	 */
	public boolean useTool() {
		durability--;
		if (durability == 0) {
			return true;
		}
		return false;
	}

	// Helper methods

	private void setIngredients(ToolType type) {
		List<Resource> tmp = new ArrayList<Resource>();
		switch (type) {
		case AXE:
			tmp.add(new Wood(null, null, 2));
			tmp.add(new Stone(null, null, 3));
			break;
		case FISHINGROD:
			tmp.add(new Wood(null, null, 2));
			tmp.add(new Dirt(null, null, 3));
			break;
		case PICKAXE:
			tmp.add(new Wood(null, null, 2));
			tmp.add(new Stone(null, null, 3));
			break;
		case SHOVEL:
			tmp.add(new Wood(null, null, 2));
			tmp.add(new Stone(null, null, 1));
			break;
		default:
			break;
		}
		super.setIngredients(tmp);
	}

	/**
	 * Sets the information for given tool type
	 * 
	 * @param type
	 */
	private void setInfo(ToolType type) {
		super.setAmount(1);
		switch (type) {
		case AXE:
			name = "Axe";
			description = "Can be used to chop trees.";
			break;
		case PICKAXE:
			name = "Pickaxe";
			description = "Can be used to mine rocks.";
			break;
		case SHOVEL:
			name = "Shovel";
			description = "Can dig stuff";
			break;
		case FISHINGROD:
			name = "Fishing Rod";
			description = "Can get fish";
			break;
		default:
			return;
		}
	}

	/**
	 * This method does not do anything because when the player is collecting
	 * resources, the tool is searched on the player's pocket and a multiplier
	 * is applied.
	 */
	@Override
	public void use(Player player) throws GameException {
	}

	@Override
	public String toString() {
		return type.toString();
	}
}
