/*	File: PileCanvas.java
 * 	Author:
 *  Casey Huang		300316284 - based off the BagCanvas code.
 * 	Linus Go	    300345571
 * 
 * 	Date			Author				changes
*   8th Oct 2016	Linus Go			created ContainerCanvas class.
 */
package missing.ui.canvas;

import java.awt.BasicStroke;
import java.awt.Canvas;
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
import missing.game.items.nonmovable.Container;
import missing.game.items.nonmovable.Pocket;
import missing.game.world.nodes.WorldTile.Pile;
import missing.game.world.nodes.WorldTile.TileObject;
import missing.helper.GameException;

/**
 * Canvas used to display the Pile of Items. If an item of the grid is pressed,
 * the cell is highlighted.
 */
public class ContainerCanvas extends Canvas implements MouseListener {

	/** Pile of items to display */
	private Container container;

	/** Contains the number of unique items in the pile */
	private ArrayList<TileObject> pileSet;

	/** x position of grid. */
	protected static final int X_OFFSET = 96;

	/** Y position of grid. */
	private static final int Y_OFFSET_BG = 60;

	private static final int size = 50;

	private static final int rows = 5;

	private static final int columns = 5;
	
	/*Fields  that determine stroke of the graphics rectangle */
	private final int BOLDED_WIDTH = 3;
	private final int REG_WIDTH = 1;
	
	/**
	 * Stores all of the grids being drawn. Used to check if a click is inside a rectangle.
	 */
	private List<Rectangle> gridRectangle;
	private Map<Integer, Rectangle> gridMap;

	private Point clickPoint;
	
	/**
	 * Stores the currently clicked on item. If it is null, there is nothing in that cell.
	 */
	private  Movable selectedItem;
	private Rectangle clickRect;
	
	private int clickIndex = -1;
	
	
	public ContainerCanvas(Container container) {
		this.container = container;
		pileSet = new ArrayList<TileObject>();
		gridRectangle = new ArrayList<Rectangle>();
		gridMap = new HashMap<>();
		convertListToSet();
		addMouseListener(this);
	}

	@Override
	public void paint(Graphics g) {
		Font font = GameAssets.getFont2(30f);
		g.setFont(font);
		g.setColor(Color.black);
		g.drawString("Items inside the container:", 10, 40);
		this.drawGrid(g, Y_OFFSET_BG);
		try {
			this.drawItems(g, Y_OFFSET_BG, pileSet);
		} catch (GameException e) {
			e.printStackTrace();
		}
		g.setFont(font);
		g.setColor(Color.black);
		fillMap();
	}

	private void drawItems(Graphics g, int y_offset, List<TileObject> set) throws GameException {
		if (set.isEmpty()) {
			return;
		}
		int count = 0;
		g.setColor(Color.black);
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
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
			for (int j = 0; j < columns; j++) {
				int left = X_OFFSET + j * size;
				int top = y_offset + i * size;
				gridRectangle.add(new Rectangle(left, top, size, size));
				/* Draw the cell normally. */
				drawCell(g, left, top, size, false);
			}
		}
		
		if(clickRect != null){
			drawCell(g, clickRect.x, clickRect.y, size, true);
		}
	}

	/**
	 * Draws a Cell. Has a flag to determine if it is highlighted or not.
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
			g.setColor(Color.YELLOW);
			g.drawRect(left, top, size, size);
		} else {
			g.drawImage(GameAssets.getItemBackgroundImage(), left, top, size, size, null);
		}
	}
	
	private void fillMap(){
		for(int i = 0 ; i < gridRectangle.size(); i++){
			gridMap.put(i, gridRectangle.get(i));
		}
	}
	
	/**
	 * Returns the index of the rectangle ArrayList that is being clicked.
	 * 
	 * @param e
	 * @return
	 */
	private int indexofCell(MouseEvent e) {
		clickPoint = e.getPoint();
		for (int i = 0; i != this.gridRectangle.size(); ++i) {
			if (gridRectangle.get(i).contains(clickPoint)) {
				return i;
			}
		}
		return -1;
	}
	
	
	
	
	/**
	 * Draws a Cell. Has a flag to determine if it is highlighted or not.
	 * 
	 * @param left
	 * @param top
	 * @param isHighlighted
	 */
	private void drawCell(Graphics g, int left, int top, int width, int height, boolean isHighlighted) {
		Graphics2D gg = (Graphics2D) g;
		if (isHighlighted) {
			gg.setStroke(new BasicStroke(BOLDED_WIDTH));
			gg.setColor(Color.yellow);
			gg.drawRect(left, top, width, height);
		} else {
			gg.setStroke(new BasicStroke(REG_WIDTH));
			gg.drawRect(left, top, width, height);
		}
	}
	
	
	/**
	 * Converts the bag of items into a set - no duplicates to account for count
	 * of item and to only draw one item.
	 */
	private void convertListToSet() {
		for (TileObject m : container.getItems()) {
			if (!pileSet.contains(m)) {
				pileSet.add(m);
			}
		}
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(442, 409);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		clickIndex = indexofCell(e);
		//selectedItem = selectClickedItem();
		System.out.println(this.clickIndex);
		this.repaint();
	}

	/*
	 * EXTRA MOUSE METHODS - NOT BEING USED.
	 */
	
	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

}
