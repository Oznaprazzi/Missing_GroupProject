/*	File: ShopNode.java
 * 	Author
 * 	Chris Rabe			300334207
 * 
 * 	Date				Author					Changes
 * 	7 Oct 16			Chris Rabe				created the node for shop
 */

package missing.ui.assets;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import missing.datastorage.assetloader.GameAssets;
import missing.game.items.nonmovable.Shop;
import missing.game.world.nodes.WorldTile.TileObject.Direction;

/**
 * The ShopNode represents the shop in a graphical form.
 */
public class ShopNode {

	private BufferedImage img;
	private Shop shop;
	private Direction direction;
	private JPanel parent;
	/**
	 * Construct a new Instance of the ShopNode
	 * @param parent - the parent JPanel instance
	 * @param shop - the shop
	 * @param direction
	 */
	public ShopNode(JPanel parent, Shop shop, Direction direction) {
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

	// Methods
	/**
	 * Draws the shop
	 * @param g
	 */
	public void draw(Graphics g) {
		if (img == null) {
			g.setColor(Color.BLACK);
			g.drawRect(0, 0, parent.getWidth(), parent.getHeight());
		} else {
			int size = Math.min(parent.getWidth(), parent.getHeight());
			g.drawImage(img, 0, 0, size, size, null);
		}
	}

	@Override
	public String toString() {
		return String.format("ShopNode : %s : %s", direction.name());
	}

}
