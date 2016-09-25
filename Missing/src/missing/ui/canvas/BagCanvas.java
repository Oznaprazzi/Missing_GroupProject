/*	File: BagCanvas.java
 * 	Author
 * 	Casey Huang		300316284
 * 
 * 	Date			Author				Changes
 * 	25 Sep 16		Casey Huang			created BagCanvas.java
 */

package missing.ui.canvas;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import java.util.List;

import missing.datastorage.assetloader.GameAssets;
import missing.game.items.movable.Dirt;
import missing.game.items.movable.Food;
import missing.game.items.movable.Movable;
import missing.game.items.movable.Stone;
import missing.game.items.movable.Tool;
import missing.game.items.movable.Wood;
import missing.game.items.nonmovable.Bag;

/**
 * Canvas used to display the cards that a current Player has.
 * This is drawn on an attached JFrame.
 * @author Casey and Linus
 *
 */
public class BagCanvas extends Canvas{
	/**
	 * List of cards to display on the canvas.
	 */
	private Bag bag;
	public boolean hasbeenPainted = false;
	private int index_R = 0;
	private int index_W = 0;
	private int index_C = 0;

	/**
	 * x position of item to be drawn.
	 */
	protected static final int X_OFFSET = 5;

	/**
	 * Y position of item.
	 */
	private static final int Y_OFFSET = 100;

	private static final int size = 50;

	public BagCanvas(Bag bag){
		this.bag = bag;
		hasbeenPainted = false;
	}

	@Override
	public void paint(Graphics g){
		g.drawImage(GameAssets.getBagBackgroundImage(), 0, 0, null);
		g.drawString("Items in hand:", 5, 15);
		this.drawItems(g);
	}

	public void drawItems(Graphics g){
		int count = 0;
		for(int i = 0; i < bag.getItems().size()/5; i++){
			for(int j = 0; j < 5; j++){
				Movable item = bag.getItems().get(count);
				if(item instanceof Food){
					if(((Food) item).getFoodType().equals(Food.FoodType.APPLE)){
						g.drawImage(GameAssets.getAppleImage(), X_OFFSET+j*size, Y_OFFSET+i*size, null);
					}else if(((Food) item).getFoodType().equals(Food.FoodType.BERRY)){
						g.drawImage(GameAssets.getBerriesImage(), X_OFFSET+j*size, Y_OFFSET+i*size, null);
					}else if(((Food) item).getFoodType().equals(Food.FoodType.FISH)){
						g.drawImage(GameAssets.getFishImage(), X_OFFSET+j*size, Y_OFFSET+i*size, null);
					}
				}else if(item instanceof Dirt){
					g.drawImage(GameAssets.getDirtImage(), X_OFFSET+j*size, Y_OFFSET+i*size, null);
				}else if(item instanceof Stone){
					g.drawImage(GameAssets.getStoneImage(), X_OFFSET+j*size, Y_OFFSET+i*size, null);
				}else if(item instanceof Tool){
					if(((Tool) item).getType().equals(Tool.ToolType.AXE)){
						g.drawImage(GameAssets.getAxeImage(), X_OFFSET+j*size, Y_OFFSET+i*size, null);
					}else if(((Tool) item).getType().equals(Tool.ToolType.FISHINGROD)){
						g.drawImage(GameAssets.getFishingRodImage(), X_OFFSET+j*size, Y_OFFSET+i*size, null);
					}else if(((Tool) item).getType().equals(Tool.ToolType.PICKAXE)){
						g.drawImage(GameAssets.getPickaxeImage(), X_OFFSET+j*size, Y_OFFSET+i*size, null);
					}else if(((Tool) item).getType().equals(Tool.ToolType.SHOVEL)){
						g.drawImage(GameAssets.getShovelImage(), X_OFFSET+j*size, Y_OFFSET+i*size, null);
					}
				}else if(item instanceof Wood){
					g.drawImage(GameAssets.getWoodImage(), X_OFFSET+j*size, Y_OFFSET+i*size, null);
				}
				if(j > 5){
					j = 0;
				}
				count++;
				if(count >= bag.getItems().size()){
					return;
				}
			}
		}
	}

	@Override
	public Dimension getPreferredSize(){
		return new Dimension(453,366);
	}

}
