/*	File: BagFrameTest.java
 * 	Author:
 * 	Casey Huang		300316284
 * 
 * 	Date			Author				changes
 * 	27 Sep 16		Casey Huang			created copy of BagFrame class for testing purposes.
 *  30 Sep 16		Casey Huang			refractored class to BagFrameTest
 */
package missing.tests;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

import missing.datastorage.assetloader.GameAssets;
import missing.game.characters.Player;
import missing.game.items.movable.Dirt;
import missing.game.items.movable.Food;
import missing.game.items.movable.Food.FoodType;
import missing.game.items.movable.Tool.ToolType;
import missing.game.items.movable.Movable;
import missing.game.items.movable.Tool;
import missing.game.items.nonmovable.Bag;
import missing.game.items.nonmovable.Pocket;
import missing.helper.GameException;
import missing.ui.canvas.HandPanel;
import missing.ui.menustyle.MenuFactory;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class BagFrameTest extends JFrame {

	private ImagePanel contentPane;
	private final JButton btnBagToPocket = MenuFactory.createButton2("Transfer To Pocket");
	private final JButton btnPocketToBag = MenuFactory.createButton2("Transfer To Bag");
	private final JButton btnUseItem = MenuFactory.createButton2("Use Item");
	private Player testPlayer = new Player(0, "chris", new Point(0,0), new Point(0,0));
	private static Point pt = new Point(0, 0); /*Used to quickly create test objects. */

	/*Holds objects that the current Player has. */
	private static Movable food = new Food(new Point(0, 0), new Point(0,0), FoodType.APPLE);
	private static Movable food4 = new Food(new Point(0, 0), new Point(0,0), FoodType.FISH);
	private static Movable dirt = new Dirt(pt, pt);
	private static Movable rod = new Tool(pt, pt, ToolType.FISHINGROD);
	private static Movable axe = new Tool(pt,pt, ToolType.AXE);
	private static Movable pickaxe = new Tool(pt, pt, ToolType.PICKAXE);
	
	private Movable clickedItem;
	private int clickedIndex;
	private HandPanel panel; //the hand canvas
	
	private Bag bag;
	private Pocket pocket;
	private BufferedImage backgroundImage = GameAssets.getWindowBackgroundImage();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Player player = new Player(0, "Chris", pt, pt);
					BagFrameTest frame = new BagFrameTest(player);
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
	public BagFrameTest(Player player) {
		this.bag = player.getBag();
		this.pocket = player.getPocket();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(backgroundImage.getWidth(), backgroundImage.getHeight());
		// elsewhere
		contentPane = new ImagePanel();
		setContentPane(contentPane);
		contentPane.add(btnPocketToBag);
		contentPane.add(btnBagToPocket);
		contentPane.add(btnUseItem);
		/*make sure that the player has some food in their pocket */
		testPlayer.setHealth(1); //ensure that the player is almost dead.
		try {
			testPlayer.addToPocket(food);
			testPlayer.addToPocket(food4);
			testPlayer.addToPocket(dirt);
			testPlayer.addToPocket(rod);
			testPlayer.addToBag(axe);
			testPlayer.addToBag(pickaxe);
		} catch (GameException e) {
			e.printStackTrace();
		}
		panel = new HandPanel(testPlayer);
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
		
		btnUseItem.addActionListener(e->{
			System.out.println("health before: " + testPlayer.getHealth());
			try{
				panel.useItem();
				System.out.println("health after: " + testPlayer.getHealth());
			}catch(Exception e1){
				e1.printStackTrace();
			}
		});
	}
	
	/**HELPER METHODS */
	
//	public static Bag addItemsToBag(){
//		Bag bag = new Bag();
//	
//		return bag;
//	}
//
//	public static Pocket addItemsToPocket() {
//		Pocket pocket = new Pocket();
//		try{
//			pocket.addItem(food);
//			pocket.addItem(food4);
//			pocket.addItem(dirt);
//			pocket.addItem(rod);
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//		return pocket;
//	}
}
