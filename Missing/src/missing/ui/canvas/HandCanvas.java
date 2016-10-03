/*	File: BagCanvas.java
 * 	Author:
 * 	Casey Huang		300316284
 * 
 * 	Date			Author				changes
 * 	26 Sep 16		Casey Huang			created BagCanvas class.
 *  26 Sep 16		Casey Huang			added drawGrid method and convertBagToSet method
 *  27 Sep 16		Casey Huang			updated drawing methods and added rows and columns final static fields
 */
package missing.ui.canvas;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import missing.datastorage.assetloader.GameAssets;
import missing.game.items.movable.Dirt;
import missing.game.items.movable.Food;
import missing.game.items.movable.Movable;
import missing.game.items.movable.Stone;
import missing.game.items.movable.Tool;
import missing.game.items.movable.Wood;
import missing.game.items.nonmovable.Bag;
import missing.game.items.nonmovable.Pocket;

/**
 * Canvas used to display the Player's bag's items
 */
public class HandCanvas extends Canvas {
	/** Bag of items to display */
	private Bag bag;
	
	/** Pocket of items to display */
	private Pocket pocket;

	/** Contains the number of unique items in the bag */
	private ArrayList<Movable> bagSet;
	
	/** Contains the number of unique items in the pocket */
	private ArrayList<Movable> pocketSet;

	/** x position of grid. */
	protected static final int X_OFFSET = 96;

	/** Y position of bag grid. */
	private static final int Y_OFFSET_BG = 60;
	
	/** Y position of pocket grid. */
	private static final int Y_OFFSET_PK = 220;

	private static final int size = 50;

	private static final int rows = 2;

	private static final int colunmns = 5;

	public HandCanvas(Bag bag, Pocket pocket) {
		this.bag = bag;
		this.pocket = pocket;
		bagSet = new ArrayList<Movable>();
		pocketSet = new ArrayList<Movable>();
		convertListToSet();
	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(GameAssets.getBagBackgroundImage(), 0, 0, null);
		Font serif = new Font("Calisto MT", Font.BOLD, 20);
		g.setFont(serif);
		g.setColor(Color.black);
		g.drawString("Items in Bag:", 10, 40);
		this.drawGrid(g, Y_OFFSET_BG);
		this.drawItems(g, Y_OFFSET_BG, bagSet);
		g.setFont(serif);
		g.setColor(Color.black);
		g.drawString("Items in Pocket:", 10, 200);
		this.drawGrid(g, Y_OFFSET_PK);
		this.drawItems(g, Y_OFFSET_PK, pocketSet);
	}

	private void drawItems(Graphics g, int y_offset, List<Movable> set) {
		if (set.isEmpty()) {
			return;
		}
		int count = 0;
		Font serif = new Font("Calisto MT", Font.BOLD, 10);
		g.setFont(serif);
		g.setColor(Color.black);
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < colunmns; j++) {
				Movable item = set.get(count);
				if (item instanceof Food) {
					if (((Food) item).getFoodType().equals(Food.FoodType.APPLE)) {
						g.drawImage(GameAssets.getAppleImage(), X_OFFSET + j * size, y_offset + i * size, null);
					} else if (((Food) item).getFoodType().equals(Food.FoodType.BERRY)) {
						g.drawImage(GameAssets.getBerriesImage(), X_OFFSET + j * size, y_offset + i * size, null);
					} else if (((Food) item).getFoodType().equals(Food.FoodType.FISH)) {
						g.drawImage(GameAssets.getFishImage(), X_OFFSET + j * size, y_offset + i * size, null);
					}
				} else if (item instanceof Dirt) {
					g.drawImage(GameAssets.getDirtImage(), X_OFFSET + j * size, y_offset + i * size, null);
				} else if (item instanceof Stone) {
					g.drawImage(GameAssets.getStoneImage(), X_OFFSET + j * size, y_offset + i * size, null);
				} else if (item instanceof Tool) {
					if (((Tool) item).getType().equals(Tool.ToolType.AXE)) {
						g.drawImage(GameAssets.getAxeImage(), X_OFFSET + j * size, y_offset + i * size, null);
					} else if (((Tool) item).getType().equals(Tool.ToolType.FISHINGROD)) {
						g.drawImage(GameAssets.getFishingRodImage(), X_OFFSET + j * size, y_offset + i * size, null);
					} else if (((Tool) item).getType().equals(Tool.ToolType.PICKAXE)) {
						g.drawImage(GameAssets.getPickaxeImage(), X_OFFSET + j * size, y_offset + i * size, null);
					} else if (((Tool) item).getType().equals(Tool.ToolType.SHOVEL)) {
						g.drawImage(GameAssets.getShovelImage(), X_OFFSET + j * size, y_offset + i * size, null);
					}
				} else if (item instanceof Wood) {
					g.drawImage(GameAssets.getWoodImage(), X_OFFSET + j * size, y_offset + i * size, null);
				}
				g.drawString(String.valueOf(item.getCount()), X_OFFSET + 2 + j * size, y_offset + 10 + i * size);
				if (j > 5) {
					j = 0;
				}
				count++;
				if (count >= set.size()) {
					return;
				}
			}
		}
	}

	/**
	 * Draws the outline of where the items will be placed on the screen
	 * 
	 * @param g
	 */
	private void drawGrid(Graphics g, int y_offset) {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < colunmns; j++) {
				g.drawRect(X_OFFSET + j * size, y_offset + i * size, size, size);
			}
		}
	}

	/**
	 * Converts the bag of items into a set - no duplicates to account for count
	 * of item and to only draw one item.
	 */
	private void convertListToSet() {
		for (Movable m : bag.getItems()) {
			if (!bagSet.contains(m)) {
				bagSet.add(m);
			}
		}
		
		for(Movable m : pocket.getItems()){
			if(!pocketSet.contains(m)){
				pocketSet.add(m);
			}
		}
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(442, 409);
	}

}
