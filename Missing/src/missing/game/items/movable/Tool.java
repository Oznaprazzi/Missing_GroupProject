/*File : Tool.java
 * 
 * Authors    		ID
 * Jian Wei Chong	300352789
 * 
 * Date 		Author		Modification
 * 18/9/16		Jian Wei	created the class
 * 19/9/16		Jian Wei	added the creation of axe and pickaxe
 * 19/9/16		Chris Rabe	added javadocs and rearranged methods
 * 20/9/16		Jian Wei	added creation of shovel
 * */
package missing.game.items.movable;

import java.awt.Point;
import java.util.List;

import missing.game.items.Item;
import missing.helper.GameException;

/**
 * This class represents a Tool item in the Game. It extends the Craftable,
 * Usable, Movable and Item subclasses. The tool can be used by the player to
 * receive bonus number of items.
 */
public class Tool extends Craftable {

	public static enum ToolType {
		AXE, PICKAXE, SHOVEL
	}

	// number of times tool can be used before it breaks
	private int durability = 10;

	private ToolType toolType;

	public Tool(ToolType toolType, List<Resource> items, Point worldLocation, Point tileLocation) throws GameException {
		super(worldLocation, tileLocation, items);
		if (!createTool(toolType))
			// if creating the tool was unsuccessful
			throw new GameException("You had the wrong items");
	}

	// Getters

	public ToolType getType() {
		return toolType;
	}

	public int getDurability() {
		return durability;
	}

	// Creating the tool

	/**
	 * Determines what type of tool is being created, then tries to create that
	 * tool. if valid, return true
	 */
	public boolean createTool(ToolType type) {
		switch (type) {
		case AXE:
			toolType = type;
			if (createAxe()) {
				name = "Axe";
				description = "Can be used to chop trees.";
				return true;
			}
			return false;
		case PICKAXE:
			toolType = type;
			if (createPickaxe()) {
				name = "Pickaxe";
				description = "Can be used to mine rocks.";
				return true;
			}
			return false;
			
		case SHOVEL: 
			toolType = type;
			if(createShovel()){
				name = "Shovel";
				description = "Can dig stuff";
				return true;
			}
			return false;
		default:
			return false;
		}
	}

	// Methods

	/**
	 * This decreases the durability of the tool. Returns true if it has no more durability
	 */
	public boolean useTool() {
		durability--;
		if(durability==0){
			return true;
		}
		return false;
		// TODO: verify that what they are trying to do requires that tool
	}

	// Helper methods

	/**
	 * checks that there are 2 wood and 3 stone inside the resources list, which
	 * are the required resources to create an Axe
	 */
	private boolean createAxe() {
		int woodCount = 0;
		int stoneCount = 0;

		for (Item item : ingredients) {
			if (item instanceof Wood)
				woodCount++;
			else if (item instanceof Stone)
				stoneCount++;
		}
		if (woodCount != 2)
			return false;
		if (stoneCount != 3)
			return false;
		return true;
	}

	/**
	 * checks that there are 2 wood and 3 stone inside the resources list which
	 * are the required resources to create an Pickaxe
	 */
	private boolean createPickaxe() {
		int woodCount = 0;
		int stoneCount = 0;

		for (Item item : ingredients) {
			if (item instanceof Wood)
				woodCount++;
			else if (item instanceof Stone)
				stoneCount++;
		}
		if (woodCount != 2)
			return false;
		if (stoneCount != 3)
			return false;
		return true;
	}
	
	/**
	 * checks that there are 2 wood and 1 stone inside the resources list which
	 * are the required resources to create a Shovel
	 */
	private boolean createShovel() {
		int woodCount = 0;
		int stoneCount = 0;

		for (Item item : ingredients) {
			if (item instanceof Wood)
				woodCount++;
			else if (item instanceof Stone)
				stoneCount++;
		}
		if (woodCount != 2)
			return false;
		if (stoneCount != 1)
			return false;
		return true;
	}

}
