/*	File: SellPanel.java
 * 	Author
 *  Casey Huang		300316284
 *  
 * 	Date			Author				Changes
 *  10 Oct 16		Casey Huang			created class
 *  10 Oct 16		Linus Go			added methods to SellPanel
 */
package missing.ui.panels;

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

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import missing.datastorage.assetloader.GameAssets;
import missing.game.characters.Merchant;
import missing.game.characters.Player;
import missing.game.items.movable.Dirt;
import missing.game.items.movable.Food;
import missing.game.items.movable.Movable;
import missing.game.items.movable.Stone;
import missing.game.items.movable.Tool;
import missing.game.items.movable.Usable;
import missing.game.items.movable.Wood;
import missing.game.items.nonmovable.Bag;
import missing.game.items.nonmovable.Pocket;
import missing.game.items.nonmovable.Shop;
import missing.helper.GameException;
import missing.ui.controller.VControl;

public class SellPanel extends JPanel implements MouseListener {
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

	private Player player;

	private Merchant merchant;

	List<Movable> items;

	public SellPanel(Player player, Shop shop) {
		this.player = player;
		gridRectangle = new ArrayList<>();
		gridMap = new HashMap<>();
		addMouseListener(this);
		this.setOpaque(false);
		merchant = shop.getMerchant();
		// items = PLAYER.getItems();
		items = player.getBag().getItems();
	}

	@Override
	public void paint(Graphics g) {
		Font font = GameAssets.getFont2(30f);
		g.setFont(font);
		// g.setColor(Color.BLACK);
		g.drawString("Items in Bag", 20, 30);
		/* Firstly - draw the items inside the bag.. */
		this.drawGrid(g, Y_OFFSET_BG);
//		this.drawItems(g, Y_OFFSET_BG + 7, bagSet);
		g.setFont(font);

		/* Now - draw the items inside the pocket.. */
		Color currentColor = g.getColor();
		g.setColor(Color.black);
		g.drawString("Items in Pocket", 20, 210);
		g.setColor(currentColor);
		this.drawGrid(g, Y_OFFSET_PK);
//		this.drawItems(g, Y_OFFSET_PK + 7, pocketSet);
		fillMap();
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
	private void drawItems(Graphics g, int y_offset) {
		if (items.isEmpty()) {
			return;
		}
		int count = 0;
		Font font = GameAssets.getFont2(15f);
		g.setFont(font);
		g.setColor(Color.BLACK);
		int x = X_OFFSET + 7;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				Movable item = items.get(count);
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
				if (j > 5) {
					j = 0;
				}
				count++;
				if (count >= items.size()) {
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
				if (clickIndex <= items.size() - 1) {
					if (this.items.get(clickIndex) == null) {
						return null;
					}
					return this.items.get(clickIndex);
				}
			}
		}
		return null;
	}

	public void sellItem() {
		try {
			merchant.sellItem(this.player, this.selectedItem);
			JOptionPane.showMessageDialog(null, this.selectedItem + " has been added to your pocket successfully.");
		} catch (GameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
