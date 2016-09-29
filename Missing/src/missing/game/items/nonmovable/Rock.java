/*	File: Rock.java
 * 	Author
 * 	Chris Rabe			300334207
 * 
 * 	Date				Author				Changes
 * 	17 Sep 16			Chris Rabe			created Rock.java
 * 	20 Sep 16			Jian Wei			updated performAction to include pickaxe use
 * 29 Sep 16	Jian Wei		Added timer action performed, started timer in performAction
 */
package missing.game.items.nonmovable;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import missing.game.characters.Player;
import missing.game.items.movable.Stone;
import missing.game.items.movable.Tool;
import missing.game.items.movable.Wood;
import missing.game.items.movable.Tool.ToolType;
import missing.helper.GameException;

/**
 * Represents a Rock item which players can mine to get the stone resource.
 */
public class Rock extends Source {
	// TODO Potential for adding ores

	public Rock(Point worldLocation, Point tileLocation) {
		super("Rock", "An extremely hard area.", worldLocation, tileLocation,
				new Stone(worldLocation, tileLocation, MAX_RESOURCE));
		timer = new Timer(30000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (resource == null) {
					int newAmount =MAX_RESOURCE;
					resource = new Stone(worldLocation, tileLocation, newAmount);
				}
			}
		});
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
		if(numStoneTaking > resource.getAmount()){
			numStoneTaking = resource.getAmount(); 
		}
		resource = new Stone(worldLocation, tileLocation, numStoneTaking);
		resource.setStored(true);
		p.addToPocket(resource);
		this.resource = null;
		// Create a timer which will update resource after n milliseconds
		if (!timer.isRunning()) {
			timer.start();
		}
		// Add ores here...
	}
	
	/*public void run(){
		try {
			for(int i = resource.getAmount(); i<MAX_RESOURCE; i++){
				int increase = i+1;
				resource.setAmount(increase); //increases the ammount of the resource
				System.out.println("amount = "+increase);
				Thread.sleep(1000);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void start(){
		thread = new Thread(this, "Rock thread");
		thread.start();
	}
*/
}
