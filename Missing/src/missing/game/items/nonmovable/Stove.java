/* File: Stove.java
 * 
 * Authors			ID
 * Casey Huang		300316284
 * 
 * Date				Author			Modification
 *	6 Sep 2016		Casey Huang		Created Stove abstract class
 *	6 Sep 2016		Casey Huang		Added javadoc comments and descriptions.
 */
package missing.game.items.nonmovable;

import java.awt.Graphics;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Represents a Stove item used for cooking
 * @author oznaprazzi
 *
 */
public class Stove extends Cooking{
	private int width;
	private int height;
	
	/**
	 * Creates an instance of a Stove item.
	 * @param name
	 * @param description
	 */
	public Stove(String name, String description) {
		super(name, description);
	}

	@Override
	public void display(Graphics g, int x, int y) {
		try {
			g.drawImage(ImageIO.read(new File("stove.png")) , x, y, width, height, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
