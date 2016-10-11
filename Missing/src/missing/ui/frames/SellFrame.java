/*	File: SellFrame.java
 * 	Author
 *  Casey Huang		300316284
 *  
 * 	Date			Author				Changes
 *  10 Oct 16		Casey Huang			created class
 */
package missing.ui.frames;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import missing.datastorage.assetloader.GameAssets;
import missing.game.characters.Player;
import missing.game.items.nonmovable.Shop;
import missing.ui.menustyle.MenuFactory;
import missing.ui.panels.SellPanel;

/**
 * This frame stores the sell panel.
 */
@SuppressWarnings("serial")
public class SellFrame extends JFrame {

	private ImagePanel contentPane;
	private BufferedImage backgroundImage = GameAssets.getWindowBackgroundImage();

	private final JButton btnSell = MenuFactory.createButton2("Sell");
	private final JButton btnExit = MenuFactory.createButton2("Exit");

	private SellPanel panel; // the hand canvas

	/**
	 * Create a Test canvas contained within this window.
	 */
	public SellFrame(Player player, Shop shop) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(backgroundImage.getWidth(), backgroundImage.getHeight());
		// elsewhere
		contentPane = new ImagePanel();
		setContentPane(contentPane);
		contentPane.add(btnSell);
		contentPane.add(btnExit);
		panel = new SellPanel(player, shop);
		contentPane.add(panel);
		this.setVisible(true);
		addActionListeners();
	}

	private class ImagePanel extends JPanel {
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
		}
	}

	/**
	 * Sets up the Action Listeners for the buttons.
	 */
	private void addActionListeners() {
		btnSell.addActionListener(e -> {
			try {
				panel.sellItem();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		btnExit.addActionListener(e -> {
			this.dispose();
		});
	}

}
