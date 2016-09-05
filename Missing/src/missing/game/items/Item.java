package missing.game.items;

import java.awt.Point;

import missing.game.locations.WorldLocation;

/**
 * Represents an item in the game, all items are a subclass of Item
 *
 */
public abstract class Item {
	protected String name; //name of item, used when displaying details of item
	protected String description; // description of item, used when displaying details of item
	protected WorldLocation worldLocation; // represents the WorldLocation the item is located in
	protected Point renderLocation; // represents the specific point inside a WorldLocation where the item should be rendered
	
	public String getName(){
		return name;
	}
	public String getDescription(){
		return description;
	}
	public Point getRenderLocation(){
		return renderLocation;
	}
	public WorldLocation getWorldLocation(){
		return worldLocation;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	public String toString(){
		return name + " " + description;
	}
}
