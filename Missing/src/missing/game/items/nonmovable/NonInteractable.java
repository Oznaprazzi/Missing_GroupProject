/* File: NonInteractable.java
 * 
 * Authors			ID
 * Linus Go			300345571
 * 
 * Date				Author			Modification
 *	6 Sep 2016		Linus Go		Created NonInteractable class
 *	6 Sep 2016		Linus Go		Added javadoc comments and descriptions.
 *	6 Sep 2016		Linus Go		Added display filler code.
 */

package missing.game.items.nonmovable;
import java.awt.Graphics;

import missing.game.items.NonMovable;

/**
 * Class that represents an Non-Interactable item. The item may be purely decorative and serve no actual purpose in terms of gameplay.
 * It contains a position and a size.
 * @author linus
 *
 */
public class NonInteractable extends NonMovable{
	
	/**
	 * Create a new Instance of an Non-Interactable item.
	 * @param name
	 * @param description
	 */
	public NonInteractable(String name, String description) {
		super(name, description);
	}
	
	/**
	 * Draws the current NonInteractable object.
	 */
	@Override
	public void display(Graphics g, int x, int y) {
		// TODO Auto-generated method stub
	}
	
	
}
