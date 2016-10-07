/*	File: ShopNode.java
 * 	Author
 * 	Chris Rabe			300334207
 * 
 * 	Date				Author					Changes
 * 	7 Oct 16			Chris Rabe				created the node for shop
 */

package missing.ui.assets;

import java.awt.Dimension;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import missing.datastorage.assetloader.GameAssets;
import missing.game.items.nonmovable.Shop;
import missing.game.world.nodes.WorldTile.TileObject.Direction;
import missing.ui.controller.VControl.View;

/**
 * This contains the image which could be rendered based on the direction. It
 * also contains invisible JPanels which detects clicks.
 */
@SuppressWarnings("serial")
public class ShopNode extends JPanel {

	private BufferedImage img;
	private Shop shop;
	private Direction direction;
	private View parent;

	public ShopNode(View parent, Shop shop, Direction direction) {
		if (img == null) {
			img = GameAssets.getShopNodeImage(shop.getType(), direction);
		}
		this.shop = shop;
		this.direction = direction;
		this.parent = parent;
	}

	// Getters and Setters

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	@Override
	public Dimension getPreferredSize() {
		return parent.getPreferredSize();
	}

	
}
