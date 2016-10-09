/*	File: ShopViewTests.java
 * 
 * 	Author
 * 	Chris Rabe			300334207
 * 
 * 	Date				Author					Changes
 * 	8 Oct 16			Chris Rabe				created shop view tests
 */
package missing.tests;

import javax.swing.JFrame;

import missing.game.items.nonmovable.Shop;
import missing.game.items.nonmovable.Shop.ShopType;
import missing.helper.GameException;
import missing.ui.views.ShopView;

public class ShopViewTests {

	public static void main(String[] args) {
		ShopView view = new ShopView(null);
		Shop shop = new Shop(null, null, ShopType.RESOURCE);
		
		try {
			view.updateDisplay(shop);
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
	}
}
