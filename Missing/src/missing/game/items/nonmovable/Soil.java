/*File: Soil.java
 * 
 * Authors			ID
 * Jian Wei Chong	300352789
 * Chris Rabe		300334207
 * 
 * Date 		Author			Modification
 * 21 Sep 16	Jian Wei		Created the class, finished the performAction method
 * 29 Sep 16	Jian Wei		Added timer action performed, started timer in performAction
 * 30 Sep 16	Chris Rabe		added a null check
 * */
package missing.game.items.nonmovable;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import missing.game.characters.Player;
import missing.game.items.movable.Dirt;
import missing.game.items.movable.Tool;
import missing.game.items.movable.Tool.ToolType;
import missing.helper.GameException;
import missing.helper.SignalException;

/**
 * Represents a soil object in the game. This object gives the player some dirt.
 */
@SuppressWarnings("serial")
public class Soil extends Source {

	public Soil(Point worldLocation, Point tileLocation) {
		super("Soil", "It is soil", worldLocation, tileLocation, new Dirt(worldLocation, tileLocation, MAX_RESOURCE));
		timer = new Timer(REFRESH_TIME_MS, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (resource == null) {
					int newAmount = MAX_RESOURCE;
					resource = new Dirt(worldLocation, tileLocation, newAmount);
				}
			}
		});
	}

	@Override
	public void performAction(Player p) throws GameException, SignalException {
		if (resource == null) {
			throw new GameException("There is no dirt in this soil");
		}
		int numDirtTaking = 0;// amount of Dirt they are trying to take from
								// soil
		Tool playersTool = p.getTool(ToolType.SHOVEL); // gets the axe from the
		// player's pocket
		if (playersTool != null) { // if the player has a shovel
			numDirtTaking = 5;
			if (playersTool.useTool())
				// the tool has broken, so we need to remove it
				p.getPocket().remove(playersTool);
		} else {
			numDirtTaking = 1; // doesnt have shovel, so can only take one dirt
			p.setHealth(p.getHealth() - 1);
			checkIfDead(p);
		} // should reduce health if they are breaking it with their hands
		if (numDirtTaking > resource.getAmount())
			numDirtTaking = resource.getAmount();
		resource = new Dirt(worldLocation, tileLocation, numDirtTaking);
		resource.setStored(true);
		p.addToPocket(resource);
		// Replace resource -- should add timer here
		this.resource = null;
		// Create a timer which will update resource after n milliseconds
		if (!timer.isRunning()) {
			timer.start();
		}
	}
}
