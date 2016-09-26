/*File: Soil.java
 * 
 * Authors			ID
 * Jian Wei Chong	300352789
 * 
 * Date 		Author			Modification
 * 21 Sep 16	Jian Wei		Created the class, finished the performAction method
 * 
 * */
package missing.game.items.nonmovable;

import java.awt.Point;

import missing.game.characters.Player;
import missing.game.items.movable.Dirt;
import missing.game.items.movable.Tool;
import missing.game.items.movable.Tool.ToolType;
import missing.helper.GameException;

/**
 * Represents a soil object in the game. This object gives the player some dirt.
 */
public class Soil extends Source {

	public Soil(Point worldLocation, Point tileLocation) {
		super("Soil", "It is soil", worldLocation, tileLocation, new Dirt(worldLocation, tileLocation, MAX_RESOURCE));
	}

	@Override
	public void performAction(Player p) throws GameException {
		// TODO Auto-generated method stub
		int numDirtTaking = 0;// amount of Dirt they are trying to take from
		// this tree
		Tool playersTool = p.getTool(ToolType.SHOVEL); // gets the axe from the
		// player's pocket
		if (playersTool != null) { // if the player has a shovel
			numDirtTaking = 5;
			if (playersTool.useTool())
				p.getPocket().remove(playersTool); // uses the tool, if true is
			// returned, the tool has
			// broken, so we need to
			// remove it
		} else {
			numDirtTaking = 1; // doesnt have axe, so can only take one wood
			p.setHealth(p.getHealth() - 1);
		} // should reduce health if they are breaking it with their hands

		int remainingDirt = resource.getAmount() - numDirtTaking;
		resource = new Dirt(worldLocation, tileLocation, numDirtTaking);
		resource.setStored(true);
		p.addToPocket(resource);
		// Replace resource -- should add timer here
		int numDirt = remainingDirt;
		// if(numStone ==11)numStone--;
		resource = new Dirt(worldLocation, tileLocation, numDirt);
	}

}
