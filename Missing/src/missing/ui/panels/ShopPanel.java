/*	File: ShopPanel.java
 * 	Author
 *	Chris Rabe				300334207
 * 
 * 	Date					Author					Changes
 * 	8 Oct 16				Chris Rabe				created shop panel
 * 	10 Oct 16				Casey Huang				fixed position of buttons
 *  11 Oct 16				Casey Huang				fixed drawing bug for buttons - buttons would not show when rotated
 *  												between two nodes.
 */

package missing.ui.panels;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import missing.game.characters.Player;
import missing.game.items.nonmovable.Shop;
import missing.game.world.nodes.WorldTile.TileObject.Direction;
import missing.helper.GameException;
import missing.ui.assets.ShopNode;
import missing.ui.controller.VControl;
import missing.ui.frames.BuyFrame;
import missing.ui.frames.SellFrame;
import missing.ui.menustyle.MenuFactory;
import missing.ui.views.ShopView;

/**
 * The shop panel initialises the nodes of the game shop as well as change the
 * camera view through key listener.
 */
@SuppressWarnings("serial")
public class ShopPanel extends JPanel {

	private ShopView parent;

	private Shop shop;
	private ShopNode node;
	private Direction orientation; // where player is facing in the shop

	private JButton btnSell;
	private JButton btnBuy;
	private JButton btnExit;

	private JPanel btnPanel;

	private Player currentPlayer;
	
	private VControl controller;

	public ShopPanel(ShopView parent, Shop shop, VControl controller) {
		this.parent = parent;
		this.shop = shop;
		this.controller = controller;
		this.orientation = Direction.NORTH;
		this.node = initialiseNodes(shop, orientation);
		this.btnPanel = new JPanel();
		GridLayout layout = new GridLayout(0, 2);
		layout.setVgap(20);
		this.btnPanel.setLayout(layout);
		this.add(btnPanel);
		setButtons();
	}

	// Methods

	/**
	 * This method rotates the player's perspective of the shop. It can take in
	 * EAST and WEST input.
	 * 
	 * @param dir
	 */
	public void rotateView(Direction dir) {
		switch (dir) {
		case EAST:
			turnCamera(orientation, 1);
			break;
		case WEST:
			turnCamera(orientation, -1);
			break;
		default:
			break;
		}
		setButtons();
		this.repaint();
	}

	// Overriden Methods

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		node.draw(g);
	}

	@Override
	public Dimension getPreferredSize() {
		return parent.getPreferredSize();
	}

	// Helper methods

	/**
	 * This method changes the camera orientation based on the rotation
	 * parameter passed. If the rotation parameter is greater than 0, then it
	 * turns the player clockwise. If the rotation parameter is less than 0,
	 * then it turns the player anti-clockwise.
	 * 
	 * @param player
	 * @param rotation
	 * @throws GameException
	 */
	private void turnCamera(Direction dir, int rotation) {
		Direction newOrien = getNewOrientation(dir, rotation);
		this.node = getNode(shop, newOrien);
		if (this.node == null) {
			this.node = initialiseNodes(shop, newOrien);
		}
		this.orientation = newOrien;
	}

	/**
	 * This method returns the new orientation based on the rotation parameter
	 * passed and with relation to the orientation parameter. If the rotation
	 * parameter is greater than 0, then it returns the next direction
	 * clockwise. If the rotation parameter is less than 0, then it returns the
	 * next direction anti-clockwise.
	 * 
	 * @param orientation
	 * @param rotation
	 * @return
	 * @throws GameException
	 */
	private Direction getNewOrientation(Direction dir, int rotation) {
		Direction newOrien = null;
		switch (orientation) {
		case NORTH:
			newOrien = rotation > 0 ? Direction.EAST : Direction.WEST;
			break;
		case EAST:
			newOrien = rotation > 0 ? Direction.SOUTH : Direction.NORTH;
			break;
		case SOUTH:
			newOrien = rotation > 0 ? Direction.WEST : Direction.EAST;
			break;
		case WEST:
			newOrien = rotation > 0 ? Direction.NORTH : Direction.SOUTH;
			break;
		default:
			throw new RuntimeException("wrong parameter!");
		}
		return newOrien;
	}

	/**
	 * gets the node corresponding to the orientation.
	 * 
	 * @param shop
	 * @param dir
	 * @return
	 */
	private ShopNode getNode(Shop shop, Direction dir) {
		switch (dir) {
		case EAST:
			return shop.getEastNode();
		case NORTH:
			return shop.getNorthNode();
		case SOUTH:
			return shop.getSouthNode();
		case WEST:
			return shop.getWestNode();
		default:
			throw new RuntimeException("Invalid direction");
		}
	}

	/**
	 * Puts shop nodes into the shop the shop doesn't have any yet.
	 * 
	 * @param shop
	 * @param orientation
	 * @return
	 */
	private ShopNode initialiseNodes(Shop shop, Direction orientation) {
		ShopNode tmp = getNode(shop, orientation);
		if (this.node == null) {
			// shop nodes need to be initialised
			shop.setNode(new ShopNode(this, shop, Direction.NORTH), Direction.NORTH);
			shop.setNode(new ShopNode(this, shop, Direction.SOUTH), Direction.SOUTH);
			shop.setNode(new ShopNode(this, shop, Direction.EAST), Direction.EAST);
			shop.setNode(new ShopNode(this, shop, Direction.WEST), Direction.WEST);
			// re-initialise node to display
			tmp = getNode(shop, orientation);
		}
		return tmp;
	}

	private void setButtons() {
		switch (this.orientation) {
		case NORTH:
			if (btnExit != null) {
				this.btnPanel.remove(btnExit);
				btnExit = null;
			}
			btnPanel.setBorder(new EmptyBorder((int) (this.getPreferredSize().height / 1.6), 0, 0, 0));
			btnPanel.setOpaque(false);
			btnSell = MenuFactory.createShopButton("Sell");
			this.btnPanel.add(btnSell);
			btnBuy = MenuFactory.createShopButton("Buy");
			this.btnPanel.add(btnBuy);
			break;
		case SOUTH:
			if (btnSell != null && btnBuy != null) {
				this.btnPanel.remove(btnSell);
				this.btnPanel.remove(btnBuy);
				btnSell = null;
				btnBuy = null;
			}
			btnPanel.setBorder(new EmptyBorder((int) (this.getPreferredSize().height / 5),
					(int) (this.getPreferredSize().width / 8), 0, 0));
			btnPanel.setOpaque(false);
			btnExit = MenuFactory.createShopButton("Exit");
			// exit signal
			btnExit.addActionListener(e -> {
				parent.sendExitSignal();
			});
			this.btnPanel.add(btnExit);
			break;
		default:
			if (btnExit != null) {
				this.btnPanel.remove(btnExit);
				btnExit = null;
			}
			if (btnSell != null && btnBuy != null) {
				this.btnPanel.remove(btnSell);
				this.btnPanel.remove(btnBuy);
				btnSell = null;
				btnBuy = null;
			}
			break;
		}
		parent.revalidate();
		addActionListeners();
	}

	private void addActionListeners() {
		currentPlayer = parent.getPlayer();

		if (btnBuy != null && btnSell != null) {
			btnBuy.addActionListener(e -> {
				 new BuyFrame(currentPlayer, shop, controller);
				 parent.requestFocus();
			});

			btnSell.addActionListener(e -> {
				new SellFrame(currentPlayer, shop, controller);
				parent.requestFocus();
			});
		}

		if (btnExit != null) {
			btnExit.addActionListener(e -> {
				parent.sendExitSignal();
				parent.requestFocus();
			});
		}

	}
}
