/*	File: BagCanvas.java
 * 	Author:
 * 	Casey Huang		300316284
 * 
 * 	Date			Author				changes
 * 	26 Sep 16		Casey Huang			created BagCanvas class.
 *  26 Sep 16		Casey Huang			added drawGrid method and convertBagToSet method
 *  27 Sep 16		Casey Huang			updated drawing methods and added rows and columns final static fields
 *  08 Oct 16		Casey Huang			fixed bug
 *  09 Oct 16		Casey Huang			added map to draw selected item
 */
package missing.ui.canvas;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import missing.helper.GameException;

/**
 * Canvas used to display the Player's bag's items
 */
@SuppressWarnings("serial")
public class HandPanel extends JPanel implements MouseListener {
	/** Bag of items to display */
	private Bag bag;

	/** Pocket of items to display */
	private Pocket pocket;

	/** Contains the number of unique items in the bag */
	private List<Movable> bagSet;

	/** Contains the number of unique items in the pocket */
	private List<Movable> pocketSet;

	/** x position of grid. */
	protected static final int X_OFFSET = 58;

	/** Y position of bag grid. */
	private static final int Y_OFFSET_BG = 45;

	/** Y position of pocket grid. */
	private static final int Y_OFFSET_PK = 225;

	private static final int size = 65;

	private static final int rows = 2;

	private static final int columns = 5;

	private final int BOLDED_WIDTH = 3;

	private Point clickPoint;

	private List<Rectangle> gridRectangle; // array of rectangle locations.
	private Map<Integer, Rectangle> gridMap;

	private Movable selectedItem;
	private Rectangle clickRect;
	private int clickIndex;

	public HandPanel(Bag bag, Pocket pocket) {
		this.bag = bag;
		this.pocket = pocket;
		bagSet = new ArrayList<Movable>();
		pocketSet = new ArrayList<Movable>();
		gridRectangle = new ArrayList<>();
		gridMap = new HashMap<>();
		convertListToSet();
		addMouseListener(this);
		this.setOpaque(false);
	}

	@Override
	public void paint(Graphics g) {
		Font font = GameAssets.getFont2(30f);
		g.setFont(font);
		// g.setColor(Color.BLACK);
		g.drawString("Items in Bag", 20, 30);
		/* Firstly - draw the items inside the bag.. */
		this.drawGrid(g, Y_OFFSET_BG);
		this.drawItems(g, Y_OFFSET_BG + 7, bagSet);
		g.setFont(font);

		/* Now - draw the items inside the pocket.. */
		Color currentColor = g.getColor();
		g.setColor(Color.black);
		g.drawString("Items in Pocket", 20, 210);
		g.setColor(currentColor);
		this.drawGrid(g, Y_OFFSET_PK);
		this.drawItems(g, Y_OFFSET_PK + 7, pocketSet);
		fillMap();
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

		for (Movable m : pocket.getItems()) {
			if (!pocketSet.contains(m)) {
				pocketSet.add(m);
			}
		}
	}

	private int findItemInSet(List<Movable> pocketSet2) {
		for (int i = 0; i < pocketSet2.size(); i++) {
			if (pocketSet2.get(i).equals(selectedItem)) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(442, 439);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		this.clickIndex = indexOfCell(e);
		this.clickRect = gridMap.get(clickIndex);
		this.selectedItem = selectClickedItem();
		System.out.println(this.selectedItem);
		this.repaint();
	}

	/**
	 * Returns the selected Item for use with other classes.
	 * 
	 * @return
	 */
	public Movable getselectedItem() {
		Movable itm = null;
		if (selectedItem != null)
			itm = selectedItem;

		return itm;
	}

	/**
	 * Draws a list of items onto the graphics pane, with a grid. If an cell
	 * containing an item is clicked, it is selected and sets the selectedItem
	 * field.
	 * 
	 * @param g
	 * @param y_offset
	 * @param set
	 */
	private void drawItems(Graphics g, int y_offset, List<Movable> set) {
		if (set.isEmpty()) {
			return;
		}
		int count = 0;
		Font font = GameAssets.getFont2(15f);
		g.setFont(font);
		g.setColor(Color.BLACK);
		int x = X_OFFSET + 7;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				Movable item = set.get(count);
				if (item instanceof Food) {
					if (((Food) item).getFoodType().equals(Food.FoodType.APPLE)) {
						g.drawImage(GameAssets.getAppleImage(), x + j * size, y_offset + i * size, null);
					} else if (((Food) item).getFoodType().equals(Food.FoodType.BERRY)) {
						g.drawImage(GameAssets.getBerriesImage(), x + j * size, y_offset + i * size, null);
					} else if (((Food) item).getFoodType().equals(Food.FoodType.FISH)) {
						g.drawImage(GameAssets.getFishImage(), x + j * size, y_offset + i * size, null);
					}
				} else if (item instanceof Dirt) {
					g.drawImage(GameAssets.getDirtImage(), x + j * size, y_offset + i * size, null);
				} else if (item instanceof Stone) {
					g.drawImage(GameAssets.getStoneImage(), x + j * size, y_offset + i * size, null);
				} else if (item instanceof Tool) {
					if (((Tool) item).getType().equals(Tool.ToolType.AXE)) {
						g.drawImage(GameAssets.getAxeImage(), x + j * size, y_offset + i * size, null);
					} else if (((Tool) item).getType().equals(Tool.ToolType.FISHINGROD)) {
						g.drawImage(GameAssets.getFishingRodImage(), x + j * size, y_offset + i * size, null);
					} else if (((Tool) item).getType().equals(Tool.ToolType.PICKAXE)) {
						g.drawImage(GameAssets.getPickaxeImage(), x + j * size, y_offset + i * size, null);
					} else if (((Tool) item).getType().equals(Tool.ToolType.SHOVEL)) {
						g.drawImage(GameAssets.getShovelImage(), x + j * size, y_offset + i * size, null);
					}
				} else if (item instanceof Wood) {
					g.drawImage(GameAssets.getWoodImage(), x + j * size, y_offset + i * size, null);
				}
				g.drawString(String.valueOf(item.getCount()), x + 2 + j * size, y_offset + 10 + i * size);
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

	/*
	 * START OF HELPER METHODS:
	 */

	/**
	 * Draws the outline of where the items will be placed on the screen
	 * 
	 * @param g
	 */
	private void drawGrid(Graphics g, int y_offset) {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				int left = X_OFFSET + j * size;
				int top = y_offset + i * size;
				gridRectangle.add(new Rectangle(left, top, size, size));
				/* Draw the cell normally. */
				drawCell(g, left, top, size, false);
			}
		}

		if (clickRect != null) {
			drawCell(g, clickRect.x, clickRect.y, size, true);
		}
	}

	/**
	 * Draws a Cell. Has a flag to determine if it is highlighted or not.
	 * 
	 * @param g
	 * @param left
	 * @param top
	 * @param size
	 * @param isHighlighted
	 */
	private void drawCell(Graphics g, int left, int top, int size, boolean isHighlighted) {
		Graphics2D gg = (Graphics2D) g;
		if (isHighlighted) {
			gg.setStroke(new BasicStroke(BOLDED_WIDTH));
			gg.setColor(Color.YELLOW);
			gg.drawRect(left, top, size, size);
		} else {
			gg.drawImage(GameAssets.getItemBackgroundImage(), left, top, size, size, null);
		}
	}

	private void fillMap() {
		for (int i = 0; i < gridRectangle.size(); i++) {
			gridMap.put(i, gridRectangle.get(i));
		}
	}

	/**
	 * Returns the rectangle of the rectangle ArrayList that is being clicked.
	 * 
	 * @param e
	 * @return
	 */
	private int indexOfCell(MouseEvent e) {
		clickPoint = e.getPoint();
		for (int i = 0; i < gridRectangle.size(); i++) {
			if (gridRectangle.get(i).contains(clickPoint)) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * This is a helper method that selects a clicked Item, and returns the
	 * object associated in that mouse click.
	 * 
	 * @return
	 */
	private Movable selectClickedItem() {
		if (clickIndex != -1) {
			if (clickIndex >= 0 && clickIndex <= 9) {
				if (clickIndex <= bagSet.size() - 1) {
					if (this.bagSet.get(clickIndex) == null) {
						return null;
					}
					return this.bagSet.get(clickIndex);
				}
			} else if (clickIndex >= 10 && clickIndex <= 19) {
				int pocketIndex = clickIndex - 10;
				if (pocketIndex <= pocketSet.size() - 1) {
					if (this.pocketSet.get(pocketIndex) == null) {
						return null; // At this memory address, there was an
										// undefined object at that position.
					}
					return this.pocketSet.get(pocketIndex);
				}
			}
		}
		return null;
	}

	/**
	 * When the button is clicked, if there is a clicked item selected, it
	 * transfers it from the current Players Pocket to the Bag.
	 * 
	 * @throws GameException
	 */
	public void transferPocketToBag() throws GameException {
		if (selectedItem == null)
			return;
		// System.out.println("Selected " + clickedItem.toString());
		if (clickIndex >= 0 && clickIndex <= 9) {
			return; // cant transfer to yourself - leave.
		} else if (clickIndex >= 10 && clickIndex <= 19) {

			if (!bagSet.contains(selectedItem)) {
				bagSet.add(selectedItem);
			}
			bag.addItem(selectedItem);
			pocket.removeItem(selectedItem);

			pocketSet.remove(selectedItem);
			selectedItem = null;
			clickIndex = -1;
		}
		this.repaint();
	}

	/**
	 * When the button is clicked, if there is a clicked item selected, it
	 * transfers it from the current Players Bag to the Pocket.
	 * 
	 * @throws GameException
	 */
	public void transferBagToPocket() throws GameException {
		if (selectedItem == null)
			return;
		if (clickIndex >= 0 && clickIndex <= 9) {
			int index = findItemInSet(pocketSet);
			if (index != -1) {
				pocketSet.get(index).addAmount(selectedItem.getAmount());
			} else {
				pocketSet.add(selectedItem);
			}
			pocket.addItem(selectedItem);
			bag.removeItem(selectedItem);

			bagSet.remove(selectedItem);
			selectedItem = null;
			clickIndex = -1;
		} else if (clickIndex >= 10 && clickIndex <= 19) {
			// can't transfer to yourself - leave.
			return;
		}
		this.repaint();
	}

	/*
	 * END OF HELPER METHODS..
	 */

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

}
