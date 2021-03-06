/*	File: BuyFrame.java
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import missing.datastorage.assetloader.GameAssets;
import missing.game.characters.Player;
import missing.game.items.nonmovable.Shop;
import missing.ui.controller.VControl;
import missing.ui.menustyle.MenuFactory;
import missing.ui.panels.BuyPanel;
/**
 * Class that represents the frame for buying items. It has facilities to allow for 
 * a current player to buy items from the Merchant.
 * @author linus
 *
 */
public class BuyFrame extends JFrame {

	private ImagePanel contentPane;
	private BufferedImage backgroundImage = GameAssets.getWindowBackgroundImage();

	private final JButton btnBuy = MenuFactory.createButton2("Buy");
	private final JButton btnExit = MenuFactory.createButton2("Exit");

	private BuyPanel panel; //the hand canvas
	private VControl controller;
	/**
	 * Create a Test canvas contained within this window.
	 */
	public BuyFrame(Player player, Shop shop, VControl controller) {
		this.controller = controller;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(backgroundImage.getWidth(), backgroundImage.getHeight());
		// elsewhere
		contentPane = new ImagePanel();
		setContentPane(contentPane);
		contentPane.add(btnBuy);
		contentPane.add(btnExit);
		panel = new BuyPanel(player, shop, controller);
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
		btnBuy.addActionListener(e->{
			try {
				panel.buyItem();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		btnExit.addActionListener(e->{
			this.dispose();
		});
	}
	
}

