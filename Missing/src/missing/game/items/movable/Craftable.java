/* File: Usable.java
 * 
 * Authors			ID
 * Edward Kelly 	300334192
 * 
 * Date			Author			Modification
 * 6 Sep 16		Edward Kelly	created Craftable class
 */
package missing.game.items.movable;

import java.util.List;

/**
 * Represents a usable item that can only be created from multiple 
 * Resource items
 */
public abstract class Craftable extends Usable{
	/** The Resource items that are required to create this item */
	protected List<Resource> requiredResources;
	
	public Craftable(String name, String description, double weight, List<Resource> requiredResources) {
		super(name, description, weight);
		this.requiredResources = requiredResources;
	}
	
	public List<Resource> getRequiredResources(){
		return requiredResources;
	}

	// Overridden Object methods
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((requiredResources == null) ? 0 : requiredResources.hashCode());
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
		Craftable other = (Craftable) obj;
		if (requiredResources == null) {
			if (other.requiredResources != null)
				return false;
		} else if (!requiredResources.equals(other.requiredResources))
			return false;
		return true;
	}
	
	public String toString(){
		return super.toString() + "\n Required items: " + this.requiredResources.toString();
	}
}
