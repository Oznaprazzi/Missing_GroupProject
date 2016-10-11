/*	File: Bush.java
 * 
 * 	Author			ID
 * 	Edward Kelly	300334192
 * 	
 * 	Date			Author				Changes
 * 	7 Sep 16		Edward Kelly		created Bush class
 */
package missing.game.items.nonmovable;

import static java.lang.Math.random;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import missing.game.characters.Player;
import missing.game.items.movable.Food;
import missing.game.items.movable.Food.FoodType;
import missing.helper.GameException;
import missing.helper.SignalException;

/**
 * Represents a Bush. A Bush is only for aesthetics. Subclass of Foliage
 */
@SuppressWarnings("serial")
public class Bush extends Foliage {

	private static final int BERRY_CHANCE = 70;
	private Food berry = new Food(worldLocation, tileLocation, FoodType.BERRY);
	private Timer timer;
	private static final int REFRESH_TIME_MS = 10000;
	/**
	 * Creates a new Bush object at the given locations
	 * 
	 * @param worldLocation
	 *            section of world Bush is located
	 * @param tileLocation
	 *            the tile inside the worldLocation the Bush is located
	 */
	public Bush(Point worldLocation, Point tileLocation) {
		super("Bush", "It's a bush", worldLocation, tileLocation);
		timer = new Timer(REFRESH_TIME_MS, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (berry == null) {
					berry = new Food(worldLocation, tileLocation, FoodType.BERRY);
				}
			}
		});
	}

	@Override
	public void performAction(Player player) throws GameException, SignalException {
		if(berry == null){
			throw new GameException("There is no berry in this bush");
		}
		int playerChance = (int) (random() * 100);
		if (playerChance < BERRY_CHANCE) {
			berry.setStored(true);
			player.addToPocket(berry);
			this.berry = null;
			// Create a timer which will update resource after n milliseconds
			if (!timer.isRunning()) {
				timer.start();
			}

			throw new SignalException("BERRY");
		}

	}

}
