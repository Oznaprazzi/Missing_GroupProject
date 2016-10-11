/*	File: Rock.java
 * 	Author
 * 	Chris Rabe			300334207
 * 
 * 	Date				Author				Changes
 * 	17 Sep 16			Chris Rabe			created Rock.java
 * 	20 Sep 16			Jian Wei			updated performAction to include pickaxe use
 * 	29 Sep 16			Jian Wei			Added timer action performed, started timer in performAction
 * 	30 Sep 16			Chris Rabe			added a null check
 */
package missing.game.items.nonmovable;

import java.awt.Point;

import missing.game.characters.Player;
import missing.game.items.movable.Stone;
import missing.game.items.movable.Tool;
import missing.game.items.movable.Tool.ToolType;
import missing.helper.GameException;
import missing.helper.SignalException;

/**
 * Represents a Rock item which players can mine to get the stone resource.
 */
@SuppressWarnings("serial")
public class Rock extends Source {

	public Rock(Point worldLocation, Point tileLocation) {
		super("Rock", "An extremely hard area.", worldLocation, tileLocation,
				new Stone(worldLocation, tileLocation, MAX_RESOURCE));
	}

	@Override
	public void performAction(Player p) throws GameException, SignalException {
		if (resource == null) {
			throw new GameException("There are no stones in this rock.");
		}
		int numStoneTaking = 0;// amount of wood they are trying to take from
		// this tree
		Tool playersTool = p.getTool(ToolType.PICKAXE); // gets the axe from the
		// player's pocket
		if (playersTool != null) { // if the player has an axe
			numStoneTaking = 5;
			if (playersTool.useTool())
				// the tool has broken, so we need to remove it
				p.getPocket().removeItem(playersTool);
		} else {
			numStoneTaking = 1; // doesnt have axe, so can only take one wood
			p.setHealth(p.getHealth() - 1);
			checkIfDead(p);
		} // should reduce health if they are breaking it with their hands
		if (numStoneTaking > resource.getAmount()) {
			numStoneTaking = resource.getAmount();
		}
		resource = new Stone(worldLocation, tileLocation, numStoneTaking);
		resource.setStored(true);
		p.addToPocket(resource);
		// Replace resource
		int newAmount = 1 + (int) (Math.random() * MAX_RESOURCE);
		resource = new Stone(worldLocation, tileLocation, newAmount);
	}
}
