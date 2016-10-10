/*	File: HandJFrame.java
 * 	Author:
 * 	Casey Huang		300316284
 *  Linus Go		300345571
 * 
 * 	Date			Author				changes
 * 	26 Sep 16		Casey Huang			created BagFrame class.
 *  27 Sep 16		Casey Huang			removed main method
 *  03 Oct 16		Casey Huang			added Pocket parameter.
 *  09 Oct 16		Casey Huang			Updated class and added new inner class to display background image
 *  10 Oct 16		Linus Go			Enabled the HandJFrame to move between bag and pocket.
 */
package missing.ui.frames;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import missing.datastorage.assetloader.GameAssets;
import missing.game.characters.Player;
import missing.game.items.movable.Movable;
import missing.game.items.nonmovable.Bag;
import missing.game.items.nonmovable.Pocket;
import missing.ui.canvas.HandPanel;
import missing.ui.controller.VControl;
import missing.ui.menustyle.MenuFactory;

@SuppressWarnings("serial")
public class HandJFrame extends JFrame {
	/*Height and Width of this JFrame */
	private final int FRAME_WD = 434;
	private final int FRAME_HT = 481;
	/*The content Pane */
	private ImagePanel contentPane;
	
	/*The current background Image */
	private BufferedImage backgroundImage = GameAssets.getWindowBackgroundImage();
	
	/*Buttons */
	private final JButton btnBagToPocket = MenuFactory.createButton2("Transfer To Pocket");
	private final JButton btnPocketToBag = MenuFactory.createButton2("Transfer To Bag");
	private final JButton btnUseItem = MenuFactory.createButton2("Use Item");
	private final JButton btnDrop = MenuFactory.createButton2("Drop");
	
	/**The hand canvas */
	private HandPanel panel; //the hand canvas
	
	/*The bag and pocket */
	private Bag bag;
	private Pocket pocket;
	
	
	/**
	 * Create a Test canvas contained within this window.
	 */
	public HandJFrame(VControl control) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//setSize(backgroundImage.getWidth(), backgroundImage.getHeight());
		setSize(FRAME_WD, FRAME_HT);
		// elsewhere
		contentPane = new ImagePanel();
		setContentPane(contentPane);
		contentPane.add(btnPocketToBag);
		contentPane.add(btnBagToPocket);
		contentPane.add(btnUseItem);
		contentPane.add(btnDrop);
		panel = new HandPanel(control);
		contentPane.add(panel);
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
		btnPocketToBag.addActionListener(e->{
			try {
				panel.transferPocketToBag();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		btnBagToPocket.addActionListener(e->{
			try {
				panel.transferBagToPocket();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		/*Called when the item is used. */
		btnUseItem.addActionListener(e->{
			try{
				panel.useItem();
			}catch(Exception e1){
				e1.printStackTrace();
			}
		});
		btnDrop.addActionListener(e->{
			//TODO: implement Chris.
			//Item dropping logic would go inside of here.
		});
		
	}
	
}
