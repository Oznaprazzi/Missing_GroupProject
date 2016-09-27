/*	File: GameAssets.java
 * 	Author
 * 	Chris Rabe		300334207
 * 	Casey Huang		300316284
 * 
 * 	Date			Author				Changes
 * 	14 Sep 16		Chris Rabe			created GameAssets.java
 * 	15 Sep 16		Chris Rabe			moved loading the node files here
 * 	16 Sep 16		Casey Huang			Updated class with new images added
 * 	19 Sep 16		Casey Huang			Added all the other item images
 *  27 Sep 16       Casey Huang			Added more images
 *  27 Sep 16		Linus Go			added player images.
 *  27 Sep 16		Casey Huang			Added girl player images.
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

	private static BufferedImage playerEastImage;

	private static BufferedImage playerWestImage;

	private static BufferedImage playerSouthImage;

	private static BufferedImage playerNorthImage;




	private static BufferedImage sandImage;

	private static BufferedImage waterImage;

	private static BufferedImage grassImage;

	private static BufferedImage roadImage;

	private static BufferedImage appleImage;

	private static BufferedImage bushImage;

	private static BufferedImage fireplaceImage;

	private static BufferedImage treeImage;

	private static BufferedImage woodImage;

	private static BufferedImage rockImage;

	private static BufferedImage soilImage;

	private static BufferedImage dirtImage;

	private static BufferedImage stoneImage;

	private static BufferedImage axeImage;

	private static BufferedImage pickaxeImage;

	private static BufferedImage shovelImage;

	private static BufferedImage fishingRodImage;

	private static BufferedImage fishingAreaImage;

	private static BufferedImage darkGrassImage;

	private static BufferedImage fishImage;

	private static BufferedImage berriesImage;

	private static BufferedImage bagBackgroundImage;
	// Getters for File Assets

	public static InputStream getWorldFile(int x, int y) {
		return GameAssets.class.getResourceAsStream(WORLD_FILE_PATH + x + "," + y + ".txt");
	}

	// getters for the image assets

	public static BufferedImage getPlayerNorthImage(String gender){
		if (playerNorthImage == null) {
			try {
				if(gender.equalsIgnoreCase("girl")){
					playerNorthImage = ImageIO.read(GameAssets.class.getResource(STORAGE_PATH + "/img/"+gender+"playernorth.png"));
				}else{
					playerNorthImage = ImageIO.read(GameAssets.class.getResource(STORAGE_PATH + "/img/playernorth.png"));
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return playerNorthImage;
	}

	public static BufferedImage getPlayerSouthImage(String gender){
		if (playerSouthImage == null) {
			try {
				if(gender.equalsIgnoreCase("girl")){
					playerSouthImage= ImageIO.read(GameAssets.class.getResource(STORAGE_PATH + "/img/"+gender+"playersouth.png"));
				}else{
					playerSouthImage= ImageIO.read(GameAssets.class.getResource(STORAGE_PATH + "/img/playersouth.png"));
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return playerSouthImage;
	}

	public static BufferedImage getPlayerEastImage(String gender){
		if (playerEastImage == null) {
			try {
				if(gender.equalsIgnoreCase("girl")){
					playerEastImage= ImageIO.read(GameAssets.class.getResource(STORAGE_PATH + "/img/"+gender+"playereast.png"));
				}else{
					playerEastImage= ImageIO.read(GameAssets.class.getResource(STORAGE_PATH + "/img/playereast.png"));
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return playerEastImage;
	}

	public static BufferedImage getPlayerWestImage(String gender){
		if (playerWestImage == null) {
			try {
				if(gender.equalsIgnoreCase("girl")){
					playerWestImage= ImageIO.read(GameAssets.class.getResource(STORAGE_PATH + "/img/"+gender+"playerwest.png"));
				}else{
					playerWestImage= ImageIO.read(GameAssets.class.getResource(STORAGE_PATH + "/img/playerwest.png"));
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return playerWestImage;
	}


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

	public static BufferedImage getTreeImage(int num) {
		String imageNm = "tree" + String.valueOf(num);
		if (treeImage == null) {
			try {
				treeImage = ImageIO.read(GameAssets.class.getResource(STORAGE_PATH + "/img/"+ imageNm +".png"));
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

	public static BufferedImage getRockImage() {
		if (rockImage == null) {
			try {
				rockImage = ImageIO.read(GameAssets.class.getResource(STORAGE_PATH + "/img/rock.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return rockImage;
	}

	public static BufferedImage getSoilImage() {
		if (soilImage == null) {
			try {
				soilImage = ImageIO.read(GameAssets.class.getResource(STORAGE_PATH + "/img/soil.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return soilImage;
	}

	public static BufferedImage getDirtImage() {
		if (dirtImage == null) {
			try {
				dirtImage = ImageIO.read(GameAssets.class.getResource(STORAGE_PATH + "/img/dirt.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return dirtImage;
	}

	public static BufferedImage getStoneImage() {
		if (stoneImage == null) {
			try {
				stoneImage = ImageIO.read(GameAssets.class.getResource(STORAGE_PATH + "/img/stone.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return stoneImage;
	}

	public static BufferedImage getAxeImage() {
		if (axeImage == null) {
			try {
				axeImage = ImageIO.read(GameAssets.class.getResource(STORAGE_PATH + "/img/axe.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return axeImage;
	}

	public static BufferedImage getPickaxeImage() {
		if (pickaxeImage == null) {
			try {
				pickaxeImage = ImageIO.read(GameAssets.class.getResource(STORAGE_PATH + "/img/pickaxe.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return pickaxeImage;
	}

	public static BufferedImage getShovelImage() {
		if (shovelImage == null) {
			try {
				shovelImage = ImageIO.read(GameAssets.class.getResource(STORAGE_PATH + "/img/shovel.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return shovelImage;
	}

	public static BufferedImage getFishingRodImage() {
		if (fishingRodImage == null) {
			try {
				fishingRodImage = ImageIO.read(GameAssets.class.getResource(STORAGE_PATH + "/img/fishingrod.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return fishingRodImage;
	}

	public static BufferedImage getFishingAreaImage() {
		if (fishingAreaImage == null) {
			try {
				fishingAreaImage = ImageIO.read(GameAssets.class.getResource(STORAGE_PATH + "/img/fishingarea.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return fishingAreaImage;
	}

	public static BufferedImage getDarkGrassImage() {
		if (darkGrassImage == null) {
			try {
				darkGrassImage = ImageIO.read(GameAssets.class.getResource(STORAGE_PATH + "/img/darkgrass.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return darkGrassImage;
	}

	public static BufferedImage getBerriesImage() {
		if (berriesImage == null) {
			try {
				berriesImage = ImageIO.read(GameAssets.class.getResource(STORAGE_PATH + "/img/berries.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return berriesImage;
	}

	public static BufferedImage getFishImage() {
		if (fishImage == null) {
			try {
				fishImage = ImageIO.read(GameAssets.class.getResource(STORAGE_PATH + "/img/fish.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return fishImage;
	}

	public static BufferedImage getBagBackgroundImage() {
		if (bagBackgroundImage == null) {
			try {
				bagBackgroundImage = ImageIO.read(GameAssets.class.getResource(STORAGE_PATH + "/img/bagBackground.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return bagBackgroundImage;
	}


}
