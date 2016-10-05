/*	File: PileCanvas.java
 * 	Author:
 *  Casey Huang		300316284 - based off the BagCanvas code.
 * 	Linus Go	    300345571
 * 
 * 	Date			Author				changes
*   6th Oct 2016	Linus Go			created PileCanvas class.
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
import missing.game.world.nodes.WorldTile.Pile;
import missing.game.world.nodes.WorldTile.TileObject;
import missing.helper.GameException;

/**
 * Canvas used to display the Pile of Items.
 */
public class PileCanvas extends Canvas {

	/** Pile of items to display */
	private Pile pile;

	/** Contains the number of unique items in the pile */
	private ArrayList<TileObject> pileSet;

	/** x position of grid. */
	protected static final int X_OFFSET = 96;

	/** Y position of grid. */
	private static final int Y_OFFSET_BG = 60;

	private static final int size = 50;

	private static final int rows = 5;

	private static final int colunmns = 5;

	public PileCanvas(Pile pile) {
		System.out.println("inside pile canvas.");
		this.pile = pile;
		pileSet = new ArrayList<TileObject>();
		convertListToSet();
	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(GameAssets.getBagBackgroundImage(), 0, 0, null);
		Font serif = new Font("Calisto MT", Font.BOLD, 20);
		g.setFont(serif);
		g.setColor(Color.black);
		g.drawString("Items in Pile:", 10, 40);
		this.drawGrid(g, Y_OFFSET_BG);
		try {
			this.drawItems(g, Y_OFFSET_BG, pileSet);
		} catch (GameException e) {
			e.printStackTrace();
		}
		g.setFont(serif);
		g.setColor(Color.black);

	}

	private void drawItems(Graphics g, int y_offset, List<TileObject> set) throws GameException {
		if (set.isEmpty()) {
			return;
		}
		int count = 0;
		Font serif = new Font("Calisto MT", Font.BOLD, 10);
		g.setFont(serif);
		g.setColor(Color.black);
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < colunmns; j++) {
				TileObject item = set.get(count);
				if (item instanceof Movable) {
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
							g.drawImage(GameAssets.getFishingRodImage(), X_OFFSET + j * size, y_offset + i * size,
									null);
						} else if (((Tool) item).getType().equals(Tool.ToolType.PICKAXE)) {
							g.drawImage(GameAssets.getPickaxeImage(), X_OFFSET + j * size, y_offset + i * size, null);
						} else if (((Tool) item).getType().equals(Tool.ToolType.SHOVEL)) {
							g.drawImage(GameAssets.getShovelImage(), X_OFFSET + j * size, y_offset + i * size, null);
						}
					} else if (item instanceof Wood) {
						g.drawImage(GameAssets.getWoodImage(), X_OFFSET + j * size, y_offset + i * size, null);
					}
				}else{
					throw new GameException("Can only add movable items to the pile canvas!");
				}

				g.drawString(String.valueOf(((Movable) item).getCount()), X_OFFSET + 2 + j * size,
						y_offset + 10 + i * size);
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
				g.setColor(Color.green.darker());
				g.drawRect(X_OFFSET + j * size, y_offset + i * size, size, size);
			}
		}
	}

	/**
	 * Converts the bag of items into a set - no duplicates to account for count
	 * of item and to only draw one item.
	 */
	private void convertListToSet() {
		for (TileObject m : pile.getItems()) {
			if (!pileSet.contains(m)) {
				pileSet.add(m);
			}
		}
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(442, 409);
	}

}
