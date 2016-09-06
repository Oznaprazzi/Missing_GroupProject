/* File: Chair.java
 * 
 * Authors			ID
 * Casey Huang		300316284
 * 
 * Date				Author			Modification
 *	6 Sep 2016		Casey Huang		Created Chair class
 *	6 Sep 2016		Casey Huang		Added javadoc comments and descriptions.
 */
package missing.game.items.nonmovable.interactable.furniture;

import java.awt.Graphics;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Represents a Chair object that a player can sit on.
 * @author Casey Huang
 *
 */
public class Chair extends Medical{
	private int width;
	private int height;
	
	/**
	 * Creates a new instance of a Chair item.
	 * @param name
	 * @param description
	 */
	public Chair(String name, String description) {
		super(name, description);
	}

	@Override
	public void display(Graphics g, int x, int y) {
		try {
			g.drawImage(ImageIO.read(new File("chair.png")) , x, y, width, height, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
