/* File: Rock.java
 * 
 * Authors			ID
 * Casey Huang		300316284
 * 
 * Date				Author			Modification
 *	6 Sep 2016		Casey Huang		Created Rock class
 *	6 Sep 2016		Casey Huang		Added javadoc comments and descriptions.
 */
package missing.game.items.nonmovable;

import java.awt.Graphics;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Represent a Rock object that a player can collect.
 * @author Casey Huang
 *
 */
public class Rock extends Source{
	private int width;
	private int height;
	
	/**
	 * Creates an instance of a Rock item.
	 * @param name
	 * @param description
	 */
	public Rock(String name, String description) {
		super(name, description);
	}

	@Override
	public void display(Graphics g, int x, int y) {
		try {
			g.drawImage(ImageIO.read(new File("rock.png")) , x, y, width, height, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
