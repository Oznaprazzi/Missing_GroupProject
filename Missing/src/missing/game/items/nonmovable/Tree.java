/*	File: Tree.java
 * 	
 * 	Authors:			ID
 *	Casey Huang			300316284
 *	Chris Rabe			300334207
 *	Jian Wei Chong		300352789
 *
 * 	Date				Author					Changes
 *	7 Sep 16 			Casey Huang				Created Tree class and added javaDoc comments
 *	7 Sep 16			Casey Huang				Updated the performAction method.
 *	7 Sep 16			Chris Rabe				updated constructor
 *	7 Sep 16			Chris Rabe				added a chance for apples to be given
 *  20 Sep 16			Jian Wei				extended the playerAction method to include using an axe
 */
package missing.game.items.nonmovable;

import java.awt.Point;

import static java.lang.Math.random;

import missing.game.characters.Player;
import missing.game.items.movable.Wood;
import missing.game.items.movable.Food;
import missing.game.items.movable.Food.FoodType;
import missing.game.items.movable.Tool;
import missing.game.items.movable.Tool.ToolType;
import missing.helper.GameException;

/**
 * Represents a Tree item that a player can cut down to get the wood resource.
 * It also has a 50% chance to get an apple from the tree.
 */
public class Tree extends Source {
	// TODO Add timer for refreshing resources
	// TODO Do null check before storing resource inside player's pocket

	private static final int APPLE_CHANCE = 50;

	public Tree(Point worldLocation, Point tileLocation) {
		super("Tree", "A tall majestic tree.", worldLocation, tileLocation,
				new Wood(worldLocation, tileLocation, MAX_RESOURCE));
	}

	@Override
	public void performAction(Player p) throws GameException {
		int numWoodTaking = 0;// amount of wood they are trying to take from
								// this tree
		Tool playersTool = p.getTool(ToolType.AXE); // gets the axe from the
													// player's pocket
		if (playersTool != null) { // if the player has an axe
			numWoodTaking = 3;
			if (playersTool.useTool())
				p.getPocket().remove(playersTool); // uses the tool, if true is
													// returned, the tool has
													// broken, so we need to
													// remove it
		} else {
			numWoodTaking = 1; // doesnt have axe, so can only take one wood
			p.setHealth(p.getHealth() - 1);
		} // should reduce health if they are breaking it with their hands
		int remainingWood = resource.getAmount() - numWoodTaking;
		resource = new Wood(worldLocation, tileLocation, numWoodTaking);
		resource.setStored(true);
		p.addToPocket(resource);
		// Replace resource -- should add timer here
		int numWood = remainingWood;
		resource = new Wood(worldLocation, tileLocation, numWood);
		// 50% chance that player will get apples too
		int playerChance = (int) (random() * 100);
		if (playerChance < APPLE_CHANCE) {
			Food apple = new Food(worldLocation, tileLocation, FoodType.APPLE);
			apple.setStored(true);
			p.addToPocket(apple);
		}
	}

}
