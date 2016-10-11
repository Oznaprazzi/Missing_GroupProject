/*File : FishArea.java
 * 
 * Authors			ID
 * Jian Wei Chong	300352789
 * 
 * 
 * Date			Author			Modification
 * 22/9/16		Jian Wei		Created the class and initial constructor
 * 23/9/16		Jian Wei		finished the performAction method, added the FISH_CHANCE field
 * */
package missing.game.items.nonmovable;

import static java.lang.Math.random;

import java.awt.Point;

import missing.game.characters.Player;
import missing.game.items.movable.Food;
import missing.game.items.movable.Tool;
import missing.game.items.movable.Food.FoodType;
import missing.game.items.movable.Tool.ToolType;
import missing.helper.GameException;
import missing.helper.SignalException;

@SuppressWarnings("serial")
public class FishArea extends Source{

	private static final int FISH_CHANCE = 70;
	public FishArea(Point worldLocation, Point tileLocation) {
		super("Fish Area", "Can get fish", worldLocation, tileLocation, null);
	}

	@Override
	public void performAction(Player p) throws GameException, SignalException {
		Tool fishingRod = p.getTool(ToolType.FISHINGROD);
		if (fishingRod == null){
			throw new GameException("You must have a fishing rod to fish");
		}
		if(fishingRod.useTool()){
			p.removeFromPocket(fishingRod);
		}
		int playerChance = (int) (random() * 100);
		if (playerChance < FISH_CHANCE) {
			Food fish = new Food(worldLocation, tileLocation, FoodType.FISH);
			fish.setStored(true);
			p.addToPocket(fish);
			throw new SignalException("FISH");
		}
	}

}
