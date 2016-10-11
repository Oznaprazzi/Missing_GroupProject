package missing.tests;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import missing.datastorage.assetloader.GameAssets;
import missing.game.characters.Player;
import missing.game.items.nonmovable.Shop;
import missing.ui.menustyle.MenuFactory;
import missing.ui.panels.BuyPanel;

@SuppressWarnings("serial")
public class BuyFrameTest extends JFrame {

	private ImagePanel contentPane;
	private BufferedImage backgroundImage = GameAssets.getWindowBackgroundImage();
	
	private static Point pt = new Point(0, 0); /*Used to quickly create test objects. */

	private final JButton btnBuy = MenuFactory.createButton2("Buy");
	private final JButton btnCancel = MenuFactory.createButton2("Cancel");

	private BuyPanel panel; //the hand canvas
	
	private Player player;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Player player = new Player(0, "Chris", pt, pt);
					player.setMoney(100);
					
					BuyFrameTest frame = new BuyFrameTest(player, new Shop(pt, pt, Shop.ShopType.RESOURCE));
					frame.setResizable(false);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create a Test canvas contained within this window.
	 */
	public BuyFrameTest(Player player, Shop shop) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(backgroundImage.getWidth(), backgroundImage.getHeight());
		// elsewhere
		contentPane = new ImagePanel();
		setContentPane(contentPane);
		contentPane.add(btnBuy);
		contentPane.add(btnCancel);
		panel = new BuyPanel(player, shop);
		contentPane.add(panel);
		this.player = player;
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
				System.out.println(player.getPocket().getItems());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		btnCancel.addActionListener(e->{
			this.dispose();
		});
	}
	
}

