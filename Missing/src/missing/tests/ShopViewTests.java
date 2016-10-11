/*	File: ShopViewTests.java
 * 
 * 	Author
 * 	Chris Rabe			300334207
 * 
 * 	Date				Author					Changes
 * 	8 Oct 16			Chris Rabe				created shop view tests
 */
package missing.tests;

import java.awt.Point;

import javax.swing.JFrame;

import missing.game.characters.Player;
import missing.game.items.movable.Dirt;
import missing.game.items.movable.Food;
import missing.game.items.movable.Movable;
import missing.game.items.movable.Tool;
import missing.game.items.movable.Food.FoodType;
import missing.game.items.movable.Tool.ToolType;
import missing.game.items.nonmovable.Shop;
import missing.game.items.nonmovable.Shop.ShopType;
import missing.helper.GameException;
import missing.ui.views.ShopView;

public class ShopViewTests {

	public static void main(String[] args) {
		ShopView view = new ShopView(null);
		Shop shop = new Shop(null, null, ShopType.FOOD);
		Shop shop2 = new Shop(null, null, ShopType.RESOURCE);
		Shop shop3 = new Shop(null,null, ShopType.TOOLS);
		Player testPlayer = new Player(0, "chris", new Point(0,0), new Point(0,0));
		
		Point pt = new Point(0, 0); /*Used to quickly create test objects. */
		
		/*Holds objects that the current Player has. */
		Movable food = new Food(new Point(0, 0), new Point(0,0), FoodType.APPLE);
		Movable food4 = new Food(new Point(0, 0), new Point(0,0), FoodType.FISH);
		Movable dirt = new Dirt(pt, pt);
		Movable rod = new Tool(pt, pt, ToolType.FISHINGROD);
		Movable axe = new Tool(pt,pt, ToolType.AXE);
		Movable pickaxe = new Tool(pt, pt, ToolType.PICKAXE);
		
		try {
			testPlayer.addToPocket(food);
			testPlayer.addToPocket(food4);
			testPlayer.addToPocket(dirt);
			testPlayer.addToPocket(rod);
			testPlayer.addToBag(axe);
			testPlayer.addToBag(pickaxe);
			view.updateDisplay(shop);
			view.setPlayer(testPlayer);
		} catch (GameException e) {
			e.printStackTrace();
		}
		JFrame frame = new JFrame("test");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setFocusable(false);
		frame.add(view);
		frame.pack();
		frame.setVisible(true);
		view.setFocus();
		try {
			view.updateDisplay(shop3);
			view.revalidate();
			
		} catch (GameException e) {
			e.printStackTrace();
		}
	}
}
