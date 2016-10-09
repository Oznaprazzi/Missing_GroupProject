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
import missing.game.items.movable.Dirt;
import missing.game.items.movable.Food;
import missing.game.items.movable.Food.FoodType;
import missing.game.items.movable.Movable;
import missing.game.items.movable.Stone;
import missing.game.items.movable.Tool;
import missing.game.items.movable.Wood;
import missing.game.items.nonmovable.Bag;
import missing.game.items.nonmovable.Pocket;
import missing.ui.canvas.HandPanel;
import missing.ui.menustyle.MenuFactory;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class BagFrameTest extends JFrame {

	private ImagePanel contentPane;
	private final JButton btnBagToPocket = MenuFactory.createButton2("Transfer To Pocket");
	private final JButton btnPocketToBag = MenuFactory.createButton2("Transfer To Bag");

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
					Bag bag = addItemsToBag();
					Pocket pocket = addItemsToPocket();
					BagFrameTest frame = new BagFrameTest(bag, pocket);
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
	public BagFrameTest(Bag bag, Pocket pocket) {
		this.bag = bag;
		this.pocket = pocket;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(backgroundImage.getWidth(), backgroundImage.getHeight());
		// elsewhere
		contentPane = new ImagePanel();
		setContentPane(contentPane);
		contentPane.add(btnPocketToBag);
		contentPane.add(btnBagToPocket);
		panel = new HandPanel(bag, pocket);
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
	}
	
	/**HELPER METHODS */
	
	public static Bag addItemsToBag(){
		Bag bag = new Bag();
		Movable wood = new Wood(new Point(0, 0), new Point(0,0));
		Movable dirt = new Dirt(new Point(0, 0), new Point(0,0));
		Movable stone = new Stone(new Point(0, 0), new Point(0,0));
		Movable axe  = new Tool(new Point(0, 0), new Point(0,0), Tool.ToolType.AXE, 0);
		Movable pickaxe = new Tool(new Point(0, 0), new Point(0,0), Tool.ToolType.PICKAXE, 0);
		Movable shovel = new Tool(new Point(0, 0), new Point(0,0), Tool.ToolType.SHOVEL, 0);
		Movable fishingrod = new Tool(new Point(0, 0), new Point(0,0), Tool.ToolType.FISHINGROD, 0);
		try{
			bag.addItem(wood);
			bag.addItem(dirt);
			bag.addItem(stone);
			bag.addItem(axe);
			bag.addItem(pickaxe);
			bag.addItem(shovel);
			bag.addItem(fishingrod);
		}catch(Exception e){
			e.printStackTrace();
		}
		return bag;
	}

	public static Pocket addItemsToPocket() {
		Pocket pocket = new Pocket();
		Movable food = new Food(new Point(0, 0), new Point(0,0), FoodType.APPLE);
		Movable food4 = new Food(new Point(0, 0), new Point(0,0), FoodType.FISH);
		Movable dirt = new Dirt(new Point(0, 0), new Point(0,0));
		try{
			pocket.addItem(food);
			pocket.addItem(food4);
			pocket.addItem(dirt);
		}catch(Exception e){
			e.printStackTrace();
		}
		return pocket;
	}
}
