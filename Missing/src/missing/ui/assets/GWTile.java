/*	File: GWTile.java
 * 	
 * 	Author:
 * 	Jian Wei Chong	300352789
 * 	Chris Rabe		300334207
 * 	Casey Huang		300316284 	
 * 	Date			Author				Changes
 * 	13 Sep 16		Jian Wei			create GWTile.java
 * 	13 Sep 16		Chris Rabe			implemented the draw method
 *  13 Sep 16		Linus Go			added stuff into the draw method.
 *  13 Sep 16		Chris Rabe			made images compatible with executable jar files
 *  18 Sep 16		Casey Huang			Added the draw isometric tiles method
 *  18 Sep 16 		Casey Huang			Added integer fields to help draw the isometric tiles
 *  18 Sep 16		Linus Go			Set the draw isometric tiles method to public
 *  18 Sep 16		Casey Huang			attempted scaling implementation
 *  19 Sep 16 		Casey Huang			made drawIsometricTile @deprecated
 *  27 Sep 16		Casey Huang			Added tree number to obtaining tree image
 *  3 Oct 16		Edward Kelly		add inMapView and player to draw params
 *  3 Oct 16		Edward Kelly 		added logic for drawing map/spectator views
 *  9 Oct 16		Chris Rabe			separated draw method into multiple methods
 */

package missing.ui.assets;

import java.awt.Color;
import java.awt.Graphics;

import missing.datastorage.assetloader.GameAssets;
import missing.game.characters.Player;
import missing.game.items.movable.Dirt;
import missing.game.items.movable.Food;
import missing.game.items.movable.Food.FoodType;
import missing.game.items.movable.Wood;
import missing.game.items.nonmovable.Bush;
import missing.game.items.nonmovable.Fireplace;
import missing.game.items.nonmovable.FishArea;
import missing.game.items.nonmovable.Rock;
import missing.game.items.nonmovable.Shop;
import missing.game.items.nonmovable.Soil;
import missing.game.items.nonmovable.TallGrass;
import missing.game.items.nonmovable.Tree;
import missing.game.world.nodes.WorldTile;
import missing.game.world.nodes.WorldTile.Pile;
import missing.game.world.nodes.WorldTile.TileObject;
import missing.helper.GameException;

/**
 * This class is a wrapper class to the WorldTile objects which contains methods
 * which are purely used for graphics and rendering. This class displays a
 * single tile in the node.
 */
public class GWTile {
	/** A reference to a tile object. */
	private WorldTile tile;
	/** The current size of this tile. */
	private int size;
	/** The current player, used just to obtain data about drawing position. */
	private Player curPlayer;
	/** draws any debugging images */
	private final boolean DEBUG = false;

	public GWTile(WorldTile tile, int size) throws GameException {
		this.tile = tile;
		this.size = size;
	}

	// Getters and Setters

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public WorldTile getWorldTile() {
		return tile;
	}

	/**
	 * Draw the tile at the specified x and y position onto this graphics
	 * context.
	 * 
	 * @param g
	 * @param x
	 * @param y
	 * @param inMapView
	 *            whether the MapView is currently displayed
	 * @param player
	 *            the local player
	 * @throws GameException
	 */
	public void draw(Graphics g, int x, int y, boolean inMapView, Player player) throws GameException {
		// draw background tile
		drawTile(g, x, y);
		// draw the player
		if (tile.isOccupied() && tile.getObject() instanceof Player) {
			curPlayer = (Player) (tile.getObject());
			if (player == null || player.isDead() || !inMapView) {
				// draw all players in node if not in mapview
				drawPlayer(g, x, y, tile.getObject());
			} else if (((Player) tile.getObject()).getId() == player.getId()) {
				// only draw own player if in mapview
				drawPlayer(g, x, y, tile.getObject());
			}
		}
		// draw the object
		drawObject(g, x, y);
		if (DEBUG) {
			/*
			 * If the tile is not enterable and there is an object image for it.
			 */
			if (!tile.isEnterable() && tile.getObject() != null) {
				g.setColor(Color.green);
				g.drawOval(x, y, size, size);
			}
		}
	}

	private void drawObject(Graphics g, int x, int y) throws GameException {
		if (tile.getObject() instanceof Tree) {
			int number = (int) (Math.random() * 2) + 1;
			g.drawImage(GameAssets.getTreeImage(number), x, y, size, size, null);
		} else if (tile.getObject() instanceof Bush) {
			int bushSize = size / 2;
			g.drawImage(GameAssets.getBushImage(), x + (bushSize / 2), y + (bushSize / 2), bushSize, bushSize, null);
		} else if (tile.getObject() instanceof Fireplace) {
			int fpSize = size / 2;
			g.drawImage(GameAssets.getFireplaceImage(), x + (fpSize / 2), y + (fpSize / 2), fpSize, fpSize, null);
		} else if (tile.getObject() instanceof Wood) {
			g.drawImage(GameAssets.getWoodImage(), x, y, size, size, null);
		} else if (tile.getObject() instanceof Dirt) {
			g.drawImage(GameAssets.getDirtImage(), x, y, size, size, null);
		} else if (tile.getObject() instanceof Rock) {
			int rockSize = (size / 2);
			g.drawImage(GameAssets.getRockImage(), x + (rockSize / 2), y + (rockSize / 2), rockSize, rockSize, null);
		} else if (tile.getObject() instanceof FishArea) {
			g.drawImage(GameAssets.getFishingAreaImage(), x, y, size, size, null);
		} else if (tile.getObject() instanceof Pile) {
			g.drawImage(GameAssets.getPileOfItemsImage(), x, y, size, size, null);
		} else if (tile.getObject() instanceof Food) {
			Food theFood = (Food) tile.getObject();
			if (theFood.getFoodType() == FoodType.APPLE) {
				g.drawImage(GameAssets.getAppleImage(), x, y, size, size, null);
			} else if (theFood.getFoodType() == FoodType.BERRY) {
				g.drawImage(GameAssets.getBerriesImage(), x, y, size, size, null);
			} else if (theFood.getFoodType() == FoodType.FISH) {
				g.drawImage(GameAssets.getFishImage(), x, y, size, size, null);
			}
		} else if (tile.getObject() instanceof Soil) {
			g.drawImage(GameAssets.getSoilImage(), x, y, size, size, null);
		} else if (tile.getObject() instanceof Shop) {
			Shop shop = (Shop) tile.getObject();
			switch (shop.getType()) {
			case FOOD:
				g.drawImage(GameAssets.getFoodShopImage(), x, y, size, size, null);
				break;
			case RESOURCE:
				g.drawImage(GameAssets.getResourceShopImage(), x, y, size, size, null);
				break;
			case TOOLS:
				g.drawImage(GameAssets.getToolsShopImage(), x, y, size, size, null);
				break;
			default:
				throw new GameException("Invalid shop type");
			}
		} else if (tile.getObject() instanceof TallGrass) {
			g.drawImage(GameAssets.getTallGrassImage(), x, y, size, size, null);
		}
	}

	private void drawTile(Graphics g, int x, int y) throws GameException {
		switch (tile.getType()) {
		case SAND:
			g.drawImage(GameAssets.getSandImage(), x, y, size, size, null);
			break;
		case WATER:
			g.drawImage(GameAssets.getWaterImage(), x, y, size, size, null);
			break;
		case GRASS:
			g.drawImage(GameAssets.getGrassImage(), x, y, size, size, null);
			break;
		case ROAD:
			g.drawImage(GameAssets.getRoadImage(), x, y, size, size, null);
			break;
		default:
			throw new GameException("Trying to draw an invalid tile type which doesn't exist!");
		}
	}

	/**
	 * Draws the player based on his current Orientation. TODO: need to update
	 * this to show proper player animation.
	 * 
	 * @param g
	 */
	private void drawPlayer(Graphics g, int x, int y, TileObject tileobj) {
		g.setColor(Color.red);
		int pSize = size / 2;

		if (tileobj instanceof Player) {
			curPlayer = ((Player) tileobj);
			switch (tileobj.getOrientation()) {
			case NORTH:
				g.drawImage(GameAssets.getPlayerImage(curPlayer.getImageID(), "north"), x + (pSize / 2),
						y + (pSize / 2), pSize, pSize, null);
				break;
			case SOUTH:
				g.drawImage(GameAssets.getPlayerImage(curPlayer.getImageID(), "south"), x + (pSize / 2),
						y + (pSize / 2), pSize, pSize, null);
				break;
			case EAST:
				g.drawImage(GameAssets.getPlayerImage(curPlayer.getImageID(), "east"), x + (pSize / 2), y + (pSize / 2),
						pSize, pSize, null);
				break;
			case WEST:
				g.drawImage(GameAssets.getPlayerImage(curPlayer.getImageID(), "west"), x + (pSize / 2), y + (pSize / 2),
						pSize, pSize, null);
				break;
			default:
				break;
			}
			// draw grass on top of it
			if (curPlayer.isInsideGrass()) {
				g.drawImage(GameAssets.getTallGrassImage(), x, y, size, size, null);
			} else {
				drawHealthBar(x, y, g);
			}
		}
	}

	/**
	 * Helper method for drawing the health of a player. This works by dividing
	 * the health into a scale of 8 sub sections. If the health falls within
	 * that certain range, it will draw a certain
	 * 
	 * @param x
	 * @param ys
	 * @param g
	 */
	private void drawHealthBar(int x, int y, Graphics g) {

		/* Dimensions for the bar. */
		final int barHeight = size / 10;
		int curHealth = curPlayer.getHealth();
		Color fill = null;
		int sqsize = size / 8;
		/* Divide the health bar into 8 sub sections. */
		for (int i = 0; i != 8; i++) {
			if (curHealth >= 88 && curHealth <= 100) { // case 8
				fill = Color.green;
			} else if (curHealth >= 76 && curHealth <= 87) { // case 7
				if (i >= 0 && i < 7) {
					fill = Color.green;
				} else {
					fill = Color.red;
				}
			} else if (curHealth >= 63 && curHealth <= 75) { // case 6
				if (i >= 0 && i < 6) {
					fill = Color.green;
				} else
					fill = Color.red;
			} else if (curHealth >= 50 && curHealth <= 62) { // case 5
				if (i >= 0 && i < 5) {
					fill = Color.green;
				} else
					fill = Color.red;
			} else if (curHealth >= 38 && curHealth <= 49) { // case 4
				if (i >= 0 && i < 4) {
					fill = Color.green;
				} else
					fill = Color.red;
			} else if (curHealth >= 25 && curHealth <= 37) { // case 3
				if (i >= 0 && i < 3) {
					fill = Color.green;
				} else
					fill = Color.red;

			} else if (curHealth >= 13 && curHealth <= 36) { // case 2
				if (i >= 0 && i < 2) {
					fill = Color.green;
				} else
					fill = Color.red;
			} else if (curHealth >= 1 && curHealth <= 35) { // case 1
				if (i == 0) {
					fill = Color.green;
				} else
					fill = Color.red;
			} else { // 0 - player dead.
				fill = Color.red;
			}

			/* Now draw all of the squares with a specified fill color.. */
			g.setColor(fill);
			g.fillRect(x, y, sqsize, barHeight);
			g.setColor(Color.black);
			g.drawRect(x, y, sqsize, barHeight);
			x = x + sqsize;
		}

	}
}
