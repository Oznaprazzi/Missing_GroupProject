/*	File: GameAssets.java
 * 	Author
 * 	Chris Rabe		300334207
 * 
 * 	Date			Author				Changes
 * 	14 Sep 16		Chris Rabe			created GameAssets.java
 * 	
 */

package missing.datastorage.assetloader;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * This class contains static fields for the images and files and uses methods
 * for retrieving those fields.
 */
public class GameAssets {

	// Image assets

	private static BufferedImage sandImage;

	private static BufferedImage waterImage;

	private static BufferedImage grassImage;

	private static BufferedImage roadImage;

	// getters for the assets

	public static BufferedImage getSandImage() {
		if (sandImage == null) {
			try {
				sandImage = ImageIO.read(GameAssets.class.getResource("/img/sand.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sandImage;
	}

	public static BufferedImage getWaterImage() {
		if (waterImage == null) {
			try {
				waterImage = ImageIO.read(GameAssets.class.getResource("/img/water.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return waterImage;
	}

	public static BufferedImage getGrassImage() {
		if (grassImage == null) {
			try {
				grassImage = ImageIO.read(GameAssets.class.getResource("/img/grass.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return grassImage;
	}

	public static BufferedImage getRoadImage() {
		if (roadImage == null) {
			try {
				roadImage = ImageIO.read(GameAssets.class.getResource("/img/road.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return roadImage;
	}
}
