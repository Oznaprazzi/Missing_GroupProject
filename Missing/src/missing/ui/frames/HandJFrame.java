/*	File: BagFrame.java
 * 	Author:
 * 	Casey Huang		300316284
 * 
 * 	Date			Author				changes
 * 	26 Sep 16		Casey Huang			created BagFrame class.
 *  27 Sep 16		Casey Huang			removed main method
 *  03 Oct 16		Casey Huang			added Pocket parameter.
 *  09 Oct 16		Casey Huang			Updated class and added new inner class to display background image
 */
package missing.ui.frames;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import missing.datastorage.assetloader.GameAssets;
import missing.game.items.nonmovable.Bag;
import missing.game.items.nonmovable.Pocket;
import missing.ui.canvas.HandPanel;
import missing.ui.menustyle.MenuFactory;

@SuppressWarnings("serial")
public class HandJFrame extends JFrame {

	private ImagePanel contentPane;
	private final JButton btnBagToPocket = MenuFactory.createButton2("Transfer To Pocket");
	private final JButton btnPocketToBag = MenuFactory.createButton2("Transfer To Bag");
	
	private HandPanel panel;
	
	private BufferedImage backgroundImage = GameAssets.getWindowBackgroundImage();
	
	/**
	 * Create the frame.
	 */
	public HandJFrame(Bag bag, Pocket pocket) {
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(backgroundImage.getWidth(), backgroundImage.getHeight());
		contentPane = new ImagePanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		HandPanel panel = new HandPanel(bag, pocket);
		contentPane.add(panel, BorderLayout.CENTER);
		addActionListeners();
	}

	/**
	 * Class to display background image
	 */
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
		btnPocketToBag.addActionListener(e->{
			panel.transferPocketToBag();
		});
		btnBagToPocket.addActionListener(e->{
			panel.transferBagToPocket();
		});
	}
}
