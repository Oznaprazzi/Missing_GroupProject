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
import missing.game.items.movable.Movable;
import missing.game.items.movable.Tool;
import missing.game.items.movable.Tool.ToolType;
import missing.game.items.movable.Wood;
import missing.game.items.nonmovable.Shop;
import missing.ui.menustyle.MenuFactory;
import missing.ui.panels.SellPanel;

@SuppressWarnings("serial")
public class SellFrameTest extends JFrame {

	private ImagePanel contentPane;
	private BufferedImage backgroundImage = GameAssets.getWindowBackgroundImage();

	private static Point pt = new Point(0,
			0); /* Used to quickly create test objects. */

	private final JButton btnSell = MenuFactory.createButton2("Sell");
	private final JButton btnExit = MenuFactory.createButton2("Exit");

	private SellPanel panel; // the hand canvas

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Player player = new Player(0, "Chris", pt, pt);
					player.setMoney(100);
					Movable axe = new Wood(null, null);
					Movable paxe = new Tool(pt, pt, ToolType.PICKAXE);
					player.addToBag(axe);
					player.addToPocket(paxe);
					SellFrameTest frame = new SellFrameTest(player, new Shop(pt, pt, Shop.ShopType.RESOURCE));
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
	public SellFrameTest(Player player, Shop shop) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(backgroundImage.getWidth(), backgroundImage.getHeight());
		// elsewhere
		contentPane = new ImagePanel();
		setContentPane(contentPane);
		contentPane.add(btnSell);
		contentPane.add(btnExit);
		panel = new SellPanel(player, shop, null);
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
