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
public class HandCanvas extends Canvas implements MouseListener {
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
	
	private final int BOLDED_WIDTH = 3;
	
	private final int REG_WIDTH = 1;
	
	private Point clickPoint;
	
	private int clickIndex;

	private List<Rectangle> gridRectangle; //array of rectangle locations.
	
	private Movable selectedItem;

	public HandCanvas(Bag bag, Pocket pocket) {
		this.bag = bag;
		this.pocket = pocket;
		bagSet = new ArrayList<Movable>();
		pocketSet = new ArrayList<Movable>();
		gridRectangle = new ArrayList<>();
		convertListToSet();
		addMouseListener(this);
	}
	
	@Override
	public void paint(Graphics g) {
		System.out.println("inside hand canvas.");
		g.drawImage(GameAssets.getBagBackgroundImage(), 0, 0, null);
		Font serif = new Font("Calisto MT", Font.BOLD, 20);
		g.setFont(serif);
		g.setColor(Color.black);
		g.drawString("Items in Bag:", 10, 40);
		/*Firstly - draw the items inside the bag.. */
		this.drawGrid(g, Y_OFFSET_BG);
		this.drawItems(g, Y_OFFSET_BG, bagSet);
		g.setFont(serif);
		g.setColor(Color.black);
		
		/*Now - draw the items inside the pocket.. */
		g.drawString("Items in Pocket:", 10, 200);
		this.drawGrid(g, Y_OFFSET_PK);
		this.drawItems(g, Y_OFFSET_PK, pocketSet);

	}
	/**
	 * Draws a list of items onto the graphics pane, with a grid.
	 * If an cell containing an item is clicked, it is selected and sets the selectedItem field.
	 * @param g
	 * @param y_offset
	 * @param set
	 */
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
				int left = X_OFFSET + j * size;
				int top = y_offset + i * size;
					gridRectangle.add(new Rectangle(left, top, size, size));
					/*Draw the cell normally. */
					drawCell(g,left,top,size,size,false);
					/*Iterate through the rectangles - highlight it if index matches click index.*/
					for(int k = 0 ; k != this.gridRectangle.size(); k++){
						if(k == clickIndex){
							Rectangle r = gridRectangle.get(k);
							int l = (int) r.getX();
							int t = (int) r.getY();
							int size = (int) r.getWidth();
							this.drawCell(this.getGraphics(), l, t, size, size, true);
						}
					}
			}
		}
	}

	
	
	/**
	 * Draws a Cell. Has a flag to determine if it is highlighted or not.
	 * @param left
	 * @param top
	 * @param isHighlighted
	 */
	private void drawCell(Graphics g, int left, int top, int width, int height, boolean isHighlighted){
		Graphics2D gg = (Graphics2D) g;
		if(isHighlighted){
			gg.setStroke(new BasicStroke(BOLDED_WIDTH));
			gg.setColor(Color.yellow);
			gg.drawRect(left,top,width,height);
		}else{
			gg.setStroke(new BasicStroke(REG_WIDTH));
			gg.drawRect(left,top,width,height);
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
	
	/**
	 * Returns the index of the rectangle ArrayList that is being clicked.
	 * @param e
	 * @return
	 */
	private int indexofCell(MouseEvent e){
		clickPoint = e.getPoint();
		for(int i = 0 ; i != this.gridRectangle.size(); ++i){
			if(gridRectangle.get(i).contains(clickPoint)){
				return i;
			}
		}
		return -1;
	}
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		this.clickIndex = indexofCell(e);
		
		if(clickIndex <= bagSet.size() - 1){
			if(this.bagSet.get(clickIndex) == null){
				return;
			}
			this.selectedItem = this.bagSet.get(clickIndex);
			System.out.println("selectedItem has been assigned.");
		}else if(clickIndex >= 10 && clickIndex <= 19){
			int pocketIndex = clickIndex - 10;
			
			if(this.pocketSet.get(pocketIndex) == null){
				return;
			}
			
		}
	

		
		
		
		
		this.repaint();
	}

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
