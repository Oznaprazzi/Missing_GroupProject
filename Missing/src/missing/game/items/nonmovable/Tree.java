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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

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
@SuppressWarnings("serial")
public class Tree extends Source {
	// TODO Do null check before storing resource inside player's pocket

	private static final int APPLE_CHANCE = 50;

	public Tree(Point worldLocation, Point tileLocation) {
		super("Tree", "A tall majestic tree.", worldLocation, tileLocation,
				new Wood(worldLocation, tileLocation, MAX_RESOURCE));
		timer = new Timer(REFRESH_TIME_MS, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (resource == null) {
					int newAmount = MAX_RESOURCE;
					resource = new Wood(worldLocation, tileLocation, newAmount);
				}
			}
		});
	}

	@Override
	public void performAction(Player p) throws GameException {
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
				p.getPocket().remove(playersTool);
		} else {
			numWoodTaking = 1; // doesnt have axe, so can only take one wood
			p.setHealth(p.getHealth() - 1);
		} // should reduce health if they are breaking it with their hands
		if (numWoodTaking > resource.getAmount()) {
			numWoodTaking = resource.getAmount();
		}
		resource = new Wood(worldLocation, tileLocation, numWoodTaking);
		resource.setStored(true);
		p.addToPocket(resource);
		// Replace resource -- should add timer here
		this.resource = null;
		// Create a timer which will update resource after n milliseconds
		if (!timer.isRunning()) {
			timer.start();
		}
		// 50% chance that player will get apples too
		int playerChance = (int) (random() * 100);
		if (playerChance < APPLE_CHANCE) {
			Food apple = new Food(worldLocation, tileLocation, FoodType.APPLE);
			apple.setStored(true);
			p.addToPocket(apple);
		}

	}
}
