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
 */

package missing.ui.assets;

import java.awt.Color;
import java.awt.Graphics;

import missing.datastorage.assetloader.GameAssets;
import missing.game.characters.Player;
import missing.game.items.movable.Dirt;
import missing.game.items.movable.Wood;
import missing.game.items.nonmovable.Bush;
import missing.game.items.nonmovable.Fireplace;
import missing.game.items.nonmovable.FishArea;
import missing.game.items.nonmovable.Rock;
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
	/** The current player, used just to obtain data about drawing position.*/
	private Player curPlayer;

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
	
	public WorldTile getWorldTile(){
		return tile;
	}


	/**
	 * Draw the tile at the specified x and y position onto this graphics
	 * context.
	 * @param g
	 * @param x
	 * @param y
	 * @param inMapView whether the MapView is currently displayed
	 * @param player the local player
	 * @throws GameException
	 */
	public void draw(Graphics g, int x, int y, boolean inMapView, Player player) throws GameException {
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
		/*Draws the player. */
		if(tile.isOccupied() && tile.getObject() instanceof Player){
				curPlayer = (Player)(tile.getObject());
				if (player == null || player.isDead() || !inMapView){
					// draw all players in node if not in mapview
					drawPlayer(g,x,y,tile.getObject());
				}
				else if (((Player)tile.getObject()).getId() == player.getId()){
					// only draw own player if in mapview
					drawPlayer(g,x,y,tile.getObject());
				}
		}
		
		/*Draws all of the nonmovable items onto the pile. */
		if(tile.getObject() instanceof Tree){
			int number = (int) (Math.random() * 2) + 1;
			g.drawImage(GameAssets.getTreeImage(number), x, y, size, size, null);
		}else if(tile.getObject() instanceof Bush){
			int bushSize = size/2;
			g.drawImage(GameAssets.getBushImage(), x+(bushSize/2) ,y+(bushSize/2) ,bushSize,bushSize, null);
		}else if(tile.getObject() instanceof Fireplace){
			int fpSize = size/2;
			g.drawImage(GameAssets.getFireplaceImage(), x+(fpSize/2) ,y+(fpSize/2) ,fpSize,fpSize, null);
		}else if(tile.getObject() instanceof Wood){
			g.drawImage(GameAssets.getWoodImage(), x ,y ,size,size, null);
		}else if(tile.getObject() instanceof Dirt){
			g.drawImage(GameAssets.getDirtImage(), x, y, size,size,null);
		}else if(tile.getObject() instanceof Rock){
			int rockSize = (size/2);
			g.drawImage(GameAssets.getRockImage(), x+(rockSize/2) ,y+(rockSize/2), rockSize,rockSize,null);
		}else if(tile.getObject() instanceof FishArea){
			g.drawImage(GameAssets.getFishingAreaImage(),x,y,size,size,null);
			
		}else if(tile.getObject() instanceof Pile){
			g.setColor(Color.green);
			g.fillRect(x, y, size, size);
			g.drawImage(GameAssets.getPileOfItemsImage(), x,y,size,size,null);
		}
	
		/*If the tile is not enterable and there is an object image for it.*/
		if(!tile.isEnterable() && tile.getObject() != null){
			g.setColor(Color.green);
			g.drawOval(x, y, size, size);
		}
	}
	
	/**
	 * Draws the player based on his current Orientation. TODO: need to update this to show proper player animation.
	 * @param g
	 */
	private void drawPlayer(Graphics g, int x, int y, TileObject tileobj){
		g.setColor(Color.red);
		int pSize = size/2;
		
		if(tileobj instanceof Player){
			curPlayer = ((Player) tileobj);
		switch(tileobj.getOrientation()){
		case NORTH:
			g.drawImage(GameAssets.getPlayerImage(curPlayer.getImageID(), "north"), x+(pSize/2),y+(pSize/2),pSize,pSize,null);
			break;
		case SOUTH:
			g.drawImage(GameAssets.getPlayerImage(curPlayer.getImageID(), "south"), x+(pSize/2),y+(pSize/2),pSize,pSize,null);
			break;
		case EAST:
			g.drawImage(GameAssets.getPlayerImage(curPlayer.getImageID(), "east"), x+(pSize/2),y+(pSize/2),pSize,pSize,null);
			break;
		case WEST:
			g.drawImage(GameAssets.getPlayerImage(curPlayer.getImageID(), "west"), x+(pSize/2),y+(pSize/2),pSize,pSize,null);
			break;
		default:
			break;
		}
		}
	}
	
	
}
