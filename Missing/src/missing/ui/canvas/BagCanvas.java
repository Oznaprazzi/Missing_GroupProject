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

import javax.swing.JPanel;

import missing.datastorage.assetloader.GameAssets;
import missing.game.items.movable.Dirt;
import missing.game.items.movable.Food;
import missing.game.items.movable.Movable;
import missing.game.items.movable.Stone;
import missing.game.items.movable.Tool;
import missing.game.items.movable.Wood;
import missing.game.items.nonmovable.Bag;

/**
 * Canvas used to display the Player's bag's items
 */
public class BagCanvas extends Canvas {
	/** Bag of items to display */
	private Bag bag;

	/** Contains the number of unique items in the bag */
	private ArrayList<Movable> bagSet;

	/** x position of item to be drawn. */
	protected static final int X_OFFSET = 96;

	/** Y position of item. */
	private static final int Y_OFFSET = 100;

	private static final int size = 50;

	private static final int rows = 2;

	private static final int colunmns = 5;

	public BagCanvas(Bag bag) {
		this.bag = bag;
		bagSet = new ArrayList<Movable>();
		convertBagToSet();
	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(GameAssets.getBagBackgroundImage(), 0, 0, null);
		Font serif = new Font("Calisto MT", Font.BOLD, 20);
		g.setFont(serif);
		g.setColor(Color.black);
		g.drawString("Items in hand:", 10, 30);
		this.drawGrid(g);
		this.drawItems(g);
	}

	private void drawItems(Graphics g) {
		if (bagSet.isEmpty()) {
			return;
		}
		int count = 0;
		Font serif = new Font("Calisto MT", Font.BOLD, 10);
		g.setFont(serif);
		g.setColor(Color.black);
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < colunmns; j++) {
				Movable item = bagSet.get(count);
				if (item instanceof Food) {
					if (((Food) item).getFoodType().equals(Food.FoodType.APPLE)) {
						g.drawImage(GameAssets.getAppleImage(), X_OFFSET + j * size, Y_OFFSET + i * size, null);
					} else if (((Food) item).getFoodType().equals(Food.FoodType.BERRY)) {
						g.drawImage(GameAssets.getBerriesImage(), X_OFFSET + j * size, Y_OFFSET + i * size, null);
					} else if (((Food) item).getFoodType().equals(Food.FoodType.FISH)) {
						g.drawImage(GameAssets.getFishImage(), X_OFFSET + j * size, Y_OFFSET + i * size, null);
					}
				} else if (item instanceof Dirt) {
					g.drawImage(GameAssets.getDirtImage(), X_OFFSET + j * size, Y_OFFSET + i * size, null);
				} else if (item instanceof Stone) {
					g.drawImage(GameAssets.getStoneImage(), X_OFFSET + j * size, Y_OFFSET + i * size, null);
				} else if (item instanceof Tool) {
					if (((Tool) item).getType().equals(Tool.ToolType.AXE)) {
						g.drawImage(GameAssets.getAxeImage(), X_OFFSET + j * size, Y_OFFSET + i * size, null);
					} else if (((Tool) item).getType().equals(Tool.ToolType.FISHINGROD)) {
						g.drawImage(GameAssets.getFishingRodImage(), X_OFFSET + j * size, Y_OFFSET + i * size, null);
					} else if (((Tool) item).getType().equals(Tool.ToolType.PICKAXE)) {
						g.drawImage(GameAssets.getPickaxeImage(), X_OFFSET + j * size, Y_OFFSET + i * size, null);
					} else if (((Tool) item).getType().equals(Tool.ToolType.SHOVEL)) {
						g.drawImage(GameAssets.getShovelImage(), X_OFFSET + j * size, Y_OFFSET + i * size, null);
					}
				} else if (item instanceof Wood) {
					g.drawImage(GameAssets.getWoodImage(), X_OFFSET + j * size, Y_OFFSET + i * size, null);
				}
				g.drawString(String.valueOf(item.getCount()), X_OFFSET + 2 + j * size, Y_OFFSET + 10 + i * size);
				if (j > 5) {
					j = 0;
				}
				count++;
				if (count >= bagSet.size()) {
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
	private void drawGrid(Graphics g) {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < colunmns; j++) {
				g.drawRect(X_OFFSET + j * size, Y_OFFSET + i * size, size, size);
			}
		}
	}

	/**
	 * Converts the bag of items into a set - no duplicates to account for count
	 * of item and to only draw one item.
	 */
	private void convertBagToSet() {
		for (Movable m : bag.getItems()) {
			if (!bagSet.contains(m)) {
				bagSet.add(m);
			}
		}
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(442, 409);
	}

}
