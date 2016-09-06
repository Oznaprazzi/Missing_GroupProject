/* File: Chair.java
 * 
 * Authors			ID
 * Casey Huang		300316284
 * Chris Rabe		300334207
 * 
 * Date				Author			Modification
 *	6 Sep 2016		Casey Huang		Created Chair class
 *	6 Sep 2016		Casey Huang		Added javadoc comments and descriptions.
 *	6 Sep 2016		Chris Rabe		made loading images compatible for executable jars
 */
package missing.game.items.nonmovable.interactable.furniture;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import missing.game.entities.Player;
import missing.game.items.nonmovable.interactable.Medical;

/**
 * Represents a Chair object that a player can sit on.
 * 
 * @author Casey Huang
 *
 */
public class Chair extends Medical {
	private final String IMAGE_NAME = "/img/interactable/chair.png";

	/**
	 * Creates a new instance of a Chair item.
	 * 
	 * @param name
	 * @param description
	 */
	public Chair(String name, String description) {
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

	@Override
	public void performAction(Player p) {
		// TODO Auto-generated method stub
		
	}

}
