/*	File: TallGrass.java
 * 	
 * 	Author
 * 	Chris Rabe				300334207
 * 
 * 	Date					Author						Changes
 * 	10 Oct 16				Chris Rabe					created TallGrass 
 */
package missing.game.items.nonmovable;

import java.awt.Point;

import missing.game.characters.Player;
import missing.helper.GameException;
import missing.helper.SignalException;

/**
 * This class represents the Tall grass in the game. It can be walked over by
 * the player and if the player is stepping on the grass, the player's feet
 * inside the grass.
 */
@SuppressWarnings("serial")
public class TallGrass extends Foliage {

	public TallGrass(Point worldLocation, Point tileLocation) {
		super("Tall Grass", "Very bushy", worldLocation, tileLocation);
	}

	@Override
	public void performAction(Player player) throws GameException, SignalException {
	}

}
