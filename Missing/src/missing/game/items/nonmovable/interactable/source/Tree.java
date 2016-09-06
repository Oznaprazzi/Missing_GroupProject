/* File: Tree.java
 * 
 * Authors			ID
 * Casey Huang		300316284
 * Chris Rabe		300334207
 * 
 * Date				Author			Modification
 *	6 Sep 2016		Casey Huang		Created Tree class
 *	6 Sep 2016		Casey Huang		Added javadoc comments and descriptions.
 *	6 Sep 2016		Chris Rabe		made drawing the image compatible with executable jars
 */
package missing.game.items.nonmovable.interactable.source;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import missing.game.items.nonmovable.interactable.Source;

/**
 * Represents a Tree item that a player can cut down to collect wood.
 * 
 */
public class Tree extends Source {
	private final String IMAGE_NAME = "/img/interactable/tree.png";

	/**
	 * Creates an instance of a Tree item.
	 * 
	 * @param name
	 * @param description
	 */
	public Tree(String name, String description) {
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
