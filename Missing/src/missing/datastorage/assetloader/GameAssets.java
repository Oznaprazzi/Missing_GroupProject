/*	File: GameAssets.java
 * 	Author
 * 	Chris Rabe		300334207
 * 
 * 	Date			Author				Changes
 * 	14 Sep 16		Chris Rabe			created GameAssets.java
 * 	15 Sep 16		Chris Rabe			moved loading the node files here
 */

package missing.datastorage.assetloader;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

/**
 * This class contains static fields for the images and files and uses methods
 * for retrieving those fields.
 */
public class GameAssets {

	// File assets
	private static final String WORLD_FILE_PATH = "/missing/datastorage/world/node/";

	// Image assets

	private static BufferedImage sandImage;

	private static BufferedImage waterImage;

	private static BufferedImage grassImage;

	private static BufferedImage roadImage;

	// Getters for File Assets

	public static InputStream getWorldFile(int x, int y) {
		return GameAssets.class.getResourceAsStream(WORLD_FILE_PATH + x + "," + y + ".txt");
	}

	// getters for the image assets

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
