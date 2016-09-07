/* File: Usable.java
 * 
 * Authors:
 * Linus Go		300345571
 * 
 * Date			Author			Modification
 * 7 Sep 16		Linus Go		created Usable class
 */
package missing.game.items.movable;
import java.awt.Point;
/**
 * This class represents a Usable item in the Game. It extends the Movable subclass and the Item subclass.
 * @author linus
 */
public abstract class Usable extends Movable{
	
	private int amount;
	
	
	/**
	 * Create a new Usable object.
	 * @param name
	 * @param description
	 * @param worldLocation
	 * @param tileLocation
	 * @param weight
	 * @param size
	 * @param amount
	 */
	public Usable (String name, String description, Point worldLocation, Point tileLocation, int weight, int size, int amount) {
		super(name, description, worldLocation, tileLocation, weight, size);
		this.amount = amount;
	}
	
}
