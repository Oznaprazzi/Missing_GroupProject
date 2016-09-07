/* File: Usable.java
 * 
 * Authors:
 * Linus Go		300345571
 * 
 * Date			Author			Modification
 * 7 Sep 16		Linus Go		created Usable class
 * 7 Sep 16		Linus Go		Added amount field.		
 */
package missing.game.items.movable;
import java.awt.Point;
/**
 * This class represents a Usable item in the Game. It extends the Movable subclass and the Item subclass.
 * @author linus
 */
public abstract class Usable extends Movable{
	/**
	 * Stores the count of usable items.
	 */
	protected int amount;
	
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
	/**
	 * Returns the amount of usable items Associated with this object.
	 * @return
	 */
	public int getAmount(){
		return this.amount;
	}
	
	/**
	 * Sets the amount of items this usable object has.
	 */
	public void setAmount(int amt){
		this.amount = amt;
	}
	
}
