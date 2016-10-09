/*	File: ShopView.java
 * 	Author
 * 	Chris Rabe			300334207
 * 
 * 	Date				Author					Changes
 * 	8 Oct 16			Chris Rabe				created shopview
 *  09 Oct 16			Casey Huang				added buttons
 */
package missing.ui.views;

import missing.game.items.nonmovable.Shop;
import missing.helper.GameException;
import missing.ui.controller.VControl;
import missing.ui.controller.VControl.View;
import missing.ui.menustyle.MenuFactory;
import missing.ui.panels.ShopPanel;
import javax.swing.JButton;

/**
 * This view displays a pseudo 3D view of the shop. The player can rotate its
 * camera using the q and e buttons. This view MUST be updated first before
 * displaying anything.
 */
@SuppressWarnings("serial")
public class ShopView extends View {

	// Three panels representing the shop

	private static ShopPanel FOOD_PANEL;
	private static ShopPanel RESOURCE_PANEL;
	private static ShopPanel TOOLS_PANEL;

	private JButton btnSell;
	private JButton btnBuy;
	private JButton btnExit;

	private ShopPanel display;

	public ShopView(VControl controller) {
		super(controller);
		//this.setLayout(null);
	}

	// Methods

	public void updateDisplay(Shop shop) throws GameException {
		switch (shop.getType()) {
		case FOOD:
			setDisplay(FOOD_PANEL, shop);
			break;
		case RESOURCE:
			setDisplay(RESOURCE_PANEL, shop);
			break;
		case TOOLS:
			setDisplay(TOOLS_PANEL, shop);
			break;
		default:
			throw new GameException("Invalid display");
		}
		initialise();
	}

	// Overrided methods

	@Override
	public void initialise() {
		this.removeAll();
		if (display != null) {
			this.add(display);
			setButtons();
		}
		setFocus();

		/*
		 * JButton btnSell = new JButton("Sell"); btnSell.setBounds(103, 156,
		 * 89, 23); panel.add(btnSell);
		 * 
		 * JButton btnBuy = new JButton("Buy"); btnBuy.setBounds(256, 156, 89,
		 * 23); panel.add(btnBuy);
		 */
	}

	@Override
	public void setFocus() {
		if (display != null) {
			display.requestFocus();
		}
	}

	// Helper method

	/**
	 * replaces the display of the view.
	 * 
	 * @param panel
	 * @param shop
	 * @throws GameException
	 */
	private void setDisplay(ShopPanel panel, Shop shop) throws GameException {
		if (panel == null) {
			ShopPanel tmp = initialisePanel(shop);
			this.display = tmp;
			return;
		}
		this.display = panel;
	}

	private ShopPanel initialisePanel(Shop shop) throws GameException {
		switch (shop.getType()) {
		case FOOD:
			FOOD_PANEL = new ShopPanel(this, shop);
			return FOOD_PANEL;
		case RESOURCE:
			RESOURCE_PANEL = new ShopPanel(this, shop);
			return RESOURCE_PANEL;
		case TOOLS:
			TOOLS_PANEL = new ShopPanel(this, shop);
			return TOOLS_PANEL;
		default:
			throw new GameException("Invalid display");
		}
	}

	private void setButtons() {
		int height = display.getPreferredSize().height;
		int width = display.getPreferredSize().width;
		switch (display.getDir()) {
		case NORTH:
			if (btnExit != null) {
				this.display.remove(btnExit);
			}
			btnSell = MenuFactory.createShopButton("Sell", (int) (height / 1.6), 20, width);
			this.display.add(btnSell);

			btnBuy = MenuFactory.createShopButton("Buy", (int) (height / 1.6), 20, width);
			this.display.add(btnBuy);
			break;
		case SOUTH:
			if (btnSell != null && btnBuy != null) {
				this.display.remove(btnSell);
				this.display.remove(btnBuy);
			}
			btnExit = MenuFactory.createShopButton("Exit", (int) (height / 5), 0, width);
			this.display.add(btnExit);
			break;
		default:
			break;
		}
	}
}
