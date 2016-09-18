package missing.game.items.movable;

/*File : Machine.java
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
 * This class represents a Machine item in the Game. It extends the Craftable, 
 * Usable, Movable and Item subclasses
 */
public class Machine extends Craftable{

	/**
	 * Creates instance of Machine class.
	 * 
	 * @param name
	 * @param description
	 * @param worldLocation
	 * @param tileLocation
	 * @param amount
	 * @param size
	 */
	public Machine(String name, String description, Point worldLocation, Point tileLocation, int amount, int size) {
		super(name, description, worldLocation, tileLocation, amount, size);
		// TODO Auto-generated constructor stub
	}

}
