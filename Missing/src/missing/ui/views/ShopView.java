/*	File: ShopView.java
 * 	Author
 * 	Chris Rabe			300334207
 * 
 * 	Date				Author					Changes
 * 	8 Oct 16			Chris Rabe				created shopview
 *  09 Oct 16			Casey Huang				added buttons
 */
package missing.ui.views;

import missing.game.characters.Player;
import missing.game.items.nonmovable.Shop;
import missing.game.world.nodes.WorldTile.TileObject.Direction;
import missing.helper.GameException;
import missing.ui.controller.VControl;
import missing.ui.controller.VControl.View;
import missing.ui.panels.ShopPanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * This view displays a pseudo 3D view of the shop. The player can rotate its
 * camera using the q and e buttons. This view MUST be updated first before
 * displaying anything.
 */
@SuppressWarnings("serial")
public class ShopView extends View implements KeyListener {

	// Three panels representing the shop

	private static ShopPanel FOOD_PANEL;
	private static ShopPanel RESOURCE_PANEL;
	private static ShopPanel TOOLS_PANEL;

	private ShopPanel display;
	private Player player;

	public ShopView(VControl controller) {
		super(controller);
		this.addKeyListener(this);
	}

	// Getters and Setters

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	// Methods

	public void sendExitSignal() {
		controller.sendExitSignal();
		controller.requestFocus();
		this.player = null;
		controller.changeView(controller.getGameView());
	}

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
		}
		setFocus();
	}

	@Override
	public void setFocus() {
		if (display != null) {
			this.setFocusable(true);
			this.requestFocus();
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

	// Key listener methods

	@Override
	public void keyPressed(KeyEvent e) {
		if (display == null) {
			return;
		}
		int key = e.getKeyCode();
		switch (key) {
		case KeyEvent.VK_Q:
			display.rotateView(Direction.WEST);
			break;
		case KeyEvent.VK_E:
			display.rotateView(Direction.EAST);
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
}
