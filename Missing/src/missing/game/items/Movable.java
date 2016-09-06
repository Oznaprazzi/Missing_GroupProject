/* File: Movable.java
 * 
 * Authors			ID
 * Edward Kelly 	300334192
 * Chris Rabe		300334207
 * 
 * Date			Author			Modification
 * 5 Sep 16		Edward Kelly	created Movable class
 * 5 Sep 16		Chris Rabe		created constructor
 * 6 Sep 16		Edward Kelly	added weight, equals, hashCode, toString
 */
package missing.game.items;

/**
 * Represents an item that can be moved/carried in the game. Movable items 
 * can have their location changed.
 */
public abstract class Movable extends Item {
	/** weight represents the weight of a movable item which affects 
	 * the movement speed and capacity of the player's bag */
	protected double weight;
	public Movable(String name, String description, double weight) {
		super(name, description);
		this.weight = weight;
	}
	
	// getters/setters
	
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
	
	@Override
	public String toString(){
		return super.toString() + " weight: " + weight;
	}
	
}
