/*	File: ShopNode.java
 * 	Author
 * 	Chris Rabe			300334207
 * 
 * 	Date				Author					Changes
 * 	7 Oct 16			Chris Rabe				created the node for shop
 */

package missing.ui.assets;

import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import missing.datastorage.assetloader.GameAssets;
import missing.game.items.nonmovable.Shop;
import missing.game.world.nodes.WorldTile.TileObject.Direction;

/**
 * This contains the image which could be rendered based on the direction. It
 * also contains invisible JPanels which detects clicks.
 */
@SuppressWarnings("serial")
public class ShopNode extends JPanel {
	private BufferedImage img;

	public ShopNode(Shop shop, Direction direction) {
		if (img == null) {
			img = GameAssets.getShopImage(shop.getType(), direction);
		}
	}
}
