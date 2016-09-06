/* File: Movable.java
 * 
 * Authors:
 * Edward Kelly 300334192
 * 
 * Date			Author			Modification
 * 5 Sep 16		Edward Kelly	created Movable class
 * 6 Sep 16		Edward Kelly	added weight
 */
package missing.game.items;

/**
 * Represents an item that can be moved/carried in the game. Movable items 
 * can have their location changed.
 *
 */
public abstract class Movable extends Item {
	/** Represents the weight of the item which affects bag 
	 * capacity and player movement speed */
	protected double weight;
	public Movable(String name, String description, double weight) {
		super(name, description);
		this.weight = weight;
	}
	
	public double getWeight(){
		return weight;
	}

	// Overridden Object methods
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		long temp;
		temp = Double.doubleToLongBits(weight);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Movable other = (Movable) obj;
		if (Double.doubleToLongBits(weight) != Double.doubleToLongBits(other.weight))
			return false;
		return true;
	}
	
	public String toString(){
		return super.toString() + " weight: " +weight;
	}
}
