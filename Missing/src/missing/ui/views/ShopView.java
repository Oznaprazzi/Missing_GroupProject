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
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
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

	private JPanel btnPanel;

	public ShopView(VControl controller) {
		super(controller);
		this.btnPanel = new JPanel();
		GridLayout layout = new GridLayout(0, 2);
		layout.setVgap(20);
		this.btnPanel.setLayout(layout);
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
		setButtons();
		if (display != null) {
			this.add(display);
			this.display.add(btnPanel);
		}
		setFocus();
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
		switch (display.getDir()) {
		case NORTH:
			btnPanel.setBorder(new EmptyBorder((int) (display.getPreferredSize().height / 1.6), 0, 0, 0));
			btnPanel.setOpaque(false);
			if (btnExit != null) {
				this.btnPanel.remove(btnExit);
			}
			btnSell = MenuFactory.createShopButton("Sell");
			this.btnPanel.add(btnSell);
			btnBuy = MenuFactory.createShopButton("Buy");
			this.btnPanel.add(btnBuy);
			break;
		case SOUTH:
			btnPanel.setBorder(new EmptyBorder((int) (display.getPreferredSize().height / 5), 0, 0, 0));
			if (btnSell != null && btnBuy != null) {
				this.btnPanel.remove(btnSell);
				this.btnPanel.remove(btnBuy);
			}
			btnExit = MenuFactory.createShopButton("Exit");
			this.btnPanel.add(btnExit);
			break;
		default:
			break;
		}
	}
}
