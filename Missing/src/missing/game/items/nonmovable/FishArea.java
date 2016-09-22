/*File : FishArea.java
 * 
 * Authors			ID
 * Jian Wei Chong	300352789
 * 
 * 
 * Date			Author			Modification
 * 22/9/16		Jian Wei		Created the class and initial constructor
 * */
package missing.game.items.nonmovable;

import java.awt.Point;

import missing.game.characters.Player;
import missing.game.items.movable.Resource;
import missing.helper.GameException;

public class FishArea extends Source{

	public FishArea(Point worldLocation, Point tileLocation) {
		super("Fish Area", "Can get fish", worldLocation, tileLocation, new Resource());
		// TODO Auto-generated constructor stub
	}

	@Override
	public void performAction(Player p) throws GameException {
		// TODO Auto-generated method stub
		
	}

}
