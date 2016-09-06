/* File: Bed.java
 * 
 * Authors			ID
 * Casey Huang		300316284
 * 
 * Date				Author			Modification
 *	6 Sep 2016		Casey Huang		Created Bed class
 *	6 Sep 2016		Casey Huang		Added javadoc comments and descriptions.
 */
package missing.game.items.nonmovable.interactable.furniture;

import java.awt.Graphics;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Represents a Bed that a player can sleep on to gain energy.
 * @author Casey Huang
 *
 */
public class Bed extends Medical{
	private int width;
	private int height;
	
	/**
	 * Creates an instance of a Bed item.
	 * @param name
	 * @param description
	 */
	public Bed(String name, String description){
		super(name, description);
	}

	@Override
	public void display(Graphics g, int x, int y) {
		try {
			g.drawImage(ImageIO.read(new File("bed.png")) , x, y, width, height, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
