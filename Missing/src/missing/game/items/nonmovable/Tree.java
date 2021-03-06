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
 *  29 Sep 16			Jian Wei				Added timer action performed, started timer in performAction
 *  30 Sep 16			Chris Rabe				made null check for resource
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
import missing.helper.SignalException;

/**
 * Represents a Tree item that a player can cut down to get the wood resource.
 * It also has a 50% chance to get an apple from the tree.
 */
@SuppressWarnings("serial")
public class Tree extends Source {

	private static final int APPLE_CHANCE = 20;

	public Tree(Point worldLocation, Point tileLocation) {
		super("Tree", "A tall majestic tree.", worldLocation, tileLocation,
				new Wood(worldLocation, tileLocation, MAX_RESOURCE));
	}

	@Override
	public void performAction(Player p) throws GameException, SignalException {
		if (resource == null) {
			throw new GameException("There is no wood in this tree.");
		}
		// amount of wood they are trying to take from this tree
		int numWoodTaking = 0;
		// gets the axe from the player's pocket
		Tool playersTool = p.getTool(ToolType.AXE);
		if (playersTool != null) { // if the player has an axe
			numWoodTaking = 3;
			if (playersTool.useTool())
				// the tool has broken, so we need to remove it
				p.getPocket().removeItem(playersTool);
		} else {
			numWoodTaking = 1; // doesnt have axe, so can only take one wood
			p.setHealth(p.getHealth() - 1);
			checkIfDead(p);
		} // should reduce health if they are breaking it with their hands
		if (numWoodTaking > resource.getAmount()) {
			numWoodTaking = resource.getAmount();
		}
		resource = new Wood(worldLocation, tileLocation, numWoodTaking);
		resource.setStored(true);
		p.addToPocket(resource);
		// Replace resource
		int newAmount = 1 + (int) (Math.random() * MAX_RESOURCE);
		resource = new Wood(worldLocation, tileLocation, newAmount);
		// 50% chance that player will get apples too
		int playerChance = (int) (random() * 100);
		if (playerChance < APPLE_CHANCE) {
			Food apple = new Food(worldLocation, tileLocation, FoodType.APPLE);
			apple.setStored(true);
			p.addToPocket(apple);
			throw new SignalException("APPLE");
		}
	}
}
