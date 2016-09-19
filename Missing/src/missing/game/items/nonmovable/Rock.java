/*	File: Rock.java
 * 	Author
 * 	Chris Rabe			300334207
 * 
 * 	Date				Author				Changes
 * 	17 Sep 16			Chris Rabe			created Rock.java
 * 
 */
package missing.game.items.nonmovable;

import java.awt.Point;

import static java.lang.Math.random;

import missing.game.characters.Player;
import missing.game.items.movable.Stone;
import missing.helper.GameException;

/**
 * Represents a Rock item which players can mine to get the stone resource.
 */
public class Rock extends Source {
	// TODO Potential for adding ores

	public Rock(Point worldLocation, Point tileLocation) {
		super("Rock", "An extremely hard area.", worldLocation, tileLocation,
				new Stone(worldLocation, tileLocation, MAX_RESOURCE));
	}

	@Override
	public void performAction(Player p) throws GameException {
		resource.setStored(true);
		p.addToPocket(resource);
		// Replace resource -- should add timer here
		int numStone = 1 + (int) (random() * MAX_RESOURCE);
		//if(numStone ==11)numStone--;
		resource = new Stone(worldLocation, tileLocation, numStone);
		// Add ores here...
	}

}
