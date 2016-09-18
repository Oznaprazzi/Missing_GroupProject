package missing.game.items.movable;

/*File : Craftable.java
 * 
 * Authors    		ID
 * Jian Wei Chong	300352789
 * 
 * Date 		Author		Modification
 * 18/9/16		Jian Wei	created the class
 * 
 * */
import java.awt.Point;
/**
 * This class represents a Craftable item in the Game. It extends the 
 * Usable, Movable and Item subclasses
 */

public abstract class Craftable extends Usable{
 
	/**
	 * Creates instance of Craftable class.
	 * 
	 * @param name
	 * @param description
	 * @param worldLocation
	 * @param tileLocation
	 * @param amount
	 * @param size
	 */
	public Craftable(String name, String description, Point worldLocation, Point tileLocation, int amount, int size) {
		super(name, description, worldLocation, tileLocation, amount, size);
		// TODO Auto-generated constructor stub
	}

}
