/*	File: WorldNode.java
 * 	Author
 * 	Chris Rabe		300334207
 *  Linus Go		300345571
 * 	Date			Author				Changes
 * 	8 Sep 16		Chris Rabe			create worldNode class
 * 	8 Sep 16		Linus Go			filled the class and added fields.
 */

package missing.game.world.nodes;
import java.awt.Point;
import java.util.List;
import missing.game.items.movable.Movable;
import missing.game.items.nonmovable.NonMovable;

/**
 * World node contains various types of world tiles. It should also contain a
 * list of movable and nonMovable objects because this information will be
 * needed when managing server.
 */
public class WorldNode {
	
	/**The list of non-movable items. */
	protected List<NonMovable> nonMovableItems;
	/**The list of movable items */
	protected List<Movable> movableItems;
	/**The location of this World Node on the world. */
	protected Point gameLocation;
	
	/**
	 * Create an instance of a WorldNode.
	 * @param nm - List of NonMovable items.
	 * @param m - List of Movable items.
	 * @param gl - Location of the WorldNode within the World.
	 */
	public WorldNode(List<NonMovable> nm, List<Movable> m, Point gl){
		this.nonMovableItems = nm;
		this.movableItems = m;
		this.gameLocation = gl;
	}
	
	/*Getters and Setters. */
	
	/**
	 * Returns the list of non-movable items.
	 * @return List<NonMovable>
	 */
	public List<NonMovable> getNonMovableItems(){
		return this.nonMovableItems;
	}
	
	/**
	 * Returns the list of Movable items.
	 * @return List<Movable>
	 */
	public List<Movable> getMovableItems(){
		return this.movableItems;
	}
	/**
	 * Returns the location of the board.
	 * @return
	 */
	public Point getGameLocation(){
		return this.gameLocation;
	}
	
}
