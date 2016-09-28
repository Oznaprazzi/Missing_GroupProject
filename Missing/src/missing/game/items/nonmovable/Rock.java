/*	File: Rock.java
 * 	Author
 * 	Chris Rabe			300334207
 * 
 * 	Date				Author				Changes
 * 	17 Sep 16			Chris Rabe			created Rock.java
 * 	20 Sep 16			Jian Wei			updated performAction to include pickaxe use
 */
package missing.game.items.nonmovable;

import java.awt.Point;

import missing.game.characters.Player;
import missing.game.items.movable.Stone;
import missing.game.items.movable.Tool;
import missing.game.items.movable.Tool.ToolType;
import missing.helper.GameException;

/**
 * Represents a Rock item which players can mine to get the stone resource.
 */
@SuppressWarnings("serial")
public class Rock extends Source {
	// TODO Potential for adding ores

	public Rock(Point worldLocation, Point tileLocation) {
		super("Rock", "An extremely hard area.", worldLocation, tileLocation,
				new Stone(worldLocation, tileLocation, MAX_RESOURCE));
	}

	@Override
	public void performAction(Player p) throws GameException {
		int numStoneTaking = 0;// amount of wood they are trying to take from
		// this tree
		Tool playersTool = p.getTool(ToolType.PICKAXE); // gets the axe from the
		// player's pocket
		if (playersTool != null) { // if the player has an axe
			numStoneTaking = 5;
			if (playersTool.useTool())
				p.getPocket().remove(playersTool); // uses the tool, if true is
			// returned, the tool has
			// broken, so we need to
			// remove it
		} else {
			numStoneTaking = 1; // doesnt have axe, so can only take one wood
			p.setHealth(p.getHealth() - 1);
		} // should reduce health if they are breaking it with their hands

		int remainingStone = resource.getAmount() - numStoneTaking;
		resource = new Stone(worldLocation, tileLocation, numStoneTaking);
		resource.setStored(true);
		p.addToPocket(resource);
		// Replace resource -- should add timer here
		int numStone = remainingStone;
		// if(numStone ==11)numStone--;
		resource = new Stone(worldLocation, tileLocation, numStone);
		// Add ores here...
	}

}
