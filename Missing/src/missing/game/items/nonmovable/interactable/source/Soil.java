/* File: Soil.java
 * 
 * Authors			ID
 * Casey Huang		300316284
 * Chris Rabe		300334207
 * 
 * Date				Author			Modification
 *	6 Sep 2016		Casey Huang		Created Soil class
 *	6 Sep 2016		Casey Huang		Added javadoc comments and descriptions.
 *	6 Sep 2016		Chris Rabe		made loading image compatible with executable jars
 */
package missing.game.items.nonmovable.interactable.source;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import missing.game.items.nonmovable.interactable.Source;

/**
 * Represents a Soil item that a player can collect.
 * 
 * @author Casey Huang
 *
 */
public class Soil extends Source {
	private final String IMAGE_NAME = "/img/interactable/soil.png";

	/**
	 * Creates an instance of a Soil item.
	 * 
	 * @param name
	 * @param description
	 */
	public Soil(String name, String description) {
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
