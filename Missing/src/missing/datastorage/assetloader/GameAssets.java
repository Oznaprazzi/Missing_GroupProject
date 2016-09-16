/*	File: GameAssets.java
 * 	Author
 * 	Chris Rabe		300334207
 * 
 * 	Date			Author				Changes
 * 	14 Sep 16		Chris Rabe			created GameAssets.java
 * 	15 Sep 16		Chris Rabe			moved loading the node files here
 * 	16 Sep 16		Casey Huang			Updated class with new images added
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
	// Data Storage path

	private static final String STORAGE_PATH = "/missing/datastorage";

	// File assets
	private static final String WORLD_FILE_PATH = STORAGE_PATH + "/files/nodes/";

	// Image assets

	private static BufferedImage sandImage;

	private static BufferedImage waterImage;

	private static BufferedImage grassImage;

	private static BufferedImage roadImage;
	
	private static BufferedImage appleImage;
	
	private static BufferedImage bushImage;
	
	private static BufferedImage fireplaceImage;
	
	private static BufferedImage treeImage;

	private static BufferedImage woodImage;
	// Getters for File Assets

	public static InputStream getWorldFile(int x, int y) {
		return GameAssets.class.getResourceAsStream(WORLD_FILE_PATH + x + "," + y + ".txt");
	}

	// getters for the image assets

	public static BufferedImage getSandImage() {
		if (sandImage == null) {
			try {
				sandImage = ImageIO.read(GameAssets.class.getResource(STORAGE_PATH + "/img/sand.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sandImage;
	}

	public static BufferedImage getWaterImage() {
		if (waterImage == null) {
			try {
				waterImage = ImageIO.read(GameAssets.class.getResource(STORAGE_PATH + "/img/water.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return waterImage;
	}

	public static BufferedImage getGrassImage() {
		if (grassImage == null) {
			try {
				grassImage = ImageIO.read(GameAssets.class.getResource(STORAGE_PATH + "/img/grass.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return grassImage;
	}

	public static BufferedImage getRoadImage() {
		if (roadImage == null) {
			try {
				roadImage = ImageIO.read(GameAssets.class.getResource(STORAGE_PATH + "/img/road.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return roadImage;
	}
	
	public static BufferedImage getAppleImage() {
		if (appleImage == null) {
			try {
				appleImage = ImageIO.read(GameAssets.class.getResource(STORAGE_PATH + "/img/apple.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return appleImage;
	}
	
	public static BufferedImage getBushImage() {
		if (bushImage == null) {
			try {
				bushImage = ImageIO.read(GameAssets.class.getResource(STORAGE_PATH + "/img/bush.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return bushImage;
	}
	
	public static BufferedImage getFireplaceImage() {
		if (fireplaceImage == null) {
			try {
				fireplaceImage = ImageIO.read(GameAssets.class.getResource(STORAGE_PATH + "/img/fireplace.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return fireplaceImage;
	}

	public static BufferedImage getTreeImage() {
		if (treeImage == null) {
			try {
				treeImage = ImageIO.read(GameAssets.class.getResource(STORAGE_PATH + "/img/tree.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return treeImage;
	}
	
	public static BufferedImage getWoodImage() {
		if (woodImage == null) {
			try {
				woodImage = ImageIO.read(GameAssets.class.getResource(STORAGE_PATH + "/img/wood.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return woodImage;
	}
}
