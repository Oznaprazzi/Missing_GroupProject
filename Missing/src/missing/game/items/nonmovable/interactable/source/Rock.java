/* File: Rock.java
 * 
 * Authors			ID
 * Casey Huang		300316284
 * Chris Rabe		300334207
 * 
 * Date				Author			Modification
 *	6 Sep 2016		Casey Huang		Created Rock class
 *	6 Sep 2016		Casey Huang		Added javadoc comments and descriptions.
 *	6 Sep 2016		Chris Rabe		made displaying images compatible for executable jars
 */
package missing.game.items.nonmovable.interactable.source;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import missing.game.items.nonmovable.interactable.Source;

/**
 * Represent a Rock object that a player can collect.
 * 
 * @author Casey Huang
 *
 */
public class Rock extends Source {
	private final String IMAGE_NAME = "/img/interactable/rock.png";

	/**
	 * Creates an instance of a Rock item.
	 * 
	 * @param name
	 * @param description
	 */
	public Rock(String name, String description) {
		super(name, description);
	}

	@Override
	public void display(Graphics g, int x, int y) {
		// Create image to display
		BufferedImage img = null;
		try {
			img = ImageIO.read(getClass().getResource(IMAGE_NAME));
		} catch (IOException e) {
			e.printStackTrace();
		}
		// TODO add image observer once we get User Interface added
		g.drawImage(img, x, y, width, height, null);
	}

}
