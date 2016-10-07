/*	File: BagFrameTest.java
 * 	Author:
 * 	Casey Huang		300316284
 * 
 * 	Date			Author				changes
 * 	27 Sep 16		Casey Huang			created copy of BagFrame class for testing purposes.
 *  30 Sep 16		Casey Huang			refractored class to BagFrameTest
 */
package missing.tests;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridBagLayout;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import missing.game.items.movable.Dirt;
import missing.game.items.movable.Food;
import missing.game.items.movable.Food.FoodType;
import missing.game.items.movable.Movable;
import missing.game.items.movable.Stone;
import missing.game.items.movable.Tool;
import missing.game.items.movable.Wood;
import missing.game.items.nonmovable.Bag;
import missing.game.items.nonmovable.Pocket;
import missing.ui.canvas.HandCanvas;
import javax.swing.JButton;
import java.awt.GridLayout;

@SuppressWarnings("serial")
public class BagFrameTest extends JFrame {

	private JPanel contentPane;
	private final JButton btnBagToPocket = new JButton("Transfer To Pocket");
	private final JButton btnPocketToBag = new JButton("Transfer To Bag");

	private Movable clickedItem;
	private int clickedIndex;
	private Canvas panel; //the hand canvas
	
	private Bag bag;
	private Pocket pocket;
	
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
		setBounds(100, 100, 450, 300);
		setSize(442, 439);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(157,213,243));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.add(btnPocketToBag);
		contentPane.add(btnBagToPocket);
		panel = new HandCanvas(bag, pocket);
		contentPane.add(panel);
		addActionListeners();
	}
	
	/**
	 * Sets up the Action Listeners for the buttons.
	 */
	private void addActionListeners() {
		

		btnPocketToBag.addActionListener(e->{
			transferPocketToBag();
		});
		btnBagToPocket.addActionListener(e->{
			transferBagToPocket();
		});
		
		
		
	}
	
	/**HELPER METHODS */
	
	/**
	 * When the button is clicked, if there is a clicked item selected, it transfers it from the current Players Pocket to the Bag.
	 */
	private void transferPocketToBag(){
		clickedItem = HandCanvas.getselectedItem();
		if(clickedItem == null) return;
		System.out.println("Selected " + clickedItem.toString());
		if(clickedIndex >=0 && clickedIndex <= 19){
			//can't transfer to yourself - leave.
			return;
		}else if(clickedIndex >= 10 && clickedIndex <= 19){
			//we are in the pocket.
			//TODO: do some logic here.
		}
		
		
		panel.repaint();
	}
	
	/**
	 * When the button is clicked, if there is a clicked item selected, it transfers it from the current Players Bag to the Pocket.
	 */
	private void transferBagToPocket(){
		clickedItem = HandCanvas.getselectedItem();
		clickedIndex = HandCanvas.getClickedIndex();
		if(clickedItem == null) return;
		System.out.println("Selected " + clickedItem.toString());
		if(clickedIndex >=0 && clickedIndex <= 9){ 
			//we are in the bag.
			//TODO: do some logic here.
	
		}else if(clickedIndex >= 10 && clickedIndex <= 19){
			return; //cant transfer to yourself - leave.
		}
		
		
		
		
		panel.repaint();
	}
	
	
	
	
	public static Bag addItemsToBag(){
		Bag bag = new Bag();
		Movable wood = new Wood(new Point(0, 0), new Point(0,0));
		Movable wood2 = new Wood(new Point(0, 0), new Point(0,0));
		Movable food = new Food(new Point(0, 0), new Point(0,0), FoodType.APPLE);
		Movable food2 = new Food(new Point(0, 0), new Point(0,0), FoodType.APPLE);
		Movable food3 = new Food(new Point(0, 0), new Point(0,0), FoodType.BERRY);
		Movable food4 = new Food(new Point(0, 0), new Point(0,0), FoodType.FISH);
		Movable dirt = new Dirt(new Point(0, 0), new Point(0,0));
		Movable stone = new Stone(new Point(0, 0), new Point(0,0));
		Movable axe  = new Tool(new Point(0, 0), new Point(0,0), Tool.ToolType.AXE, 0);
		Movable pickaxe = new Tool(new Point(0, 0), new Point(0,0), Tool.ToolType.PICKAXE, 0);
		Movable shovel = new Tool(new Point(0, 0), new Point(0,0), Tool.ToolType.SHOVEL, 0);
		Movable fishingrod = new Tool(new Point(0, 0), new Point(0,0), Tool.ToolType.FISHINGROD, 0);
		try{
			bag.addItem(wood);
			bag.addItem(wood2);
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
		Movable wood = new Wood(new Point(0, 0), new Point(0,0));
		Movable wood2 = new Wood(new Point(0, 0), new Point(0,0));
		Movable food = new Food(new Point(0, 0), new Point(0,0), FoodType.APPLE);
		Movable food2 = new Food(new Point(0, 0), new Point(0,0), FoodType.APPLE);
		Movable food3 = new Food(new Point(0, 0), new Point(0,0), FoodType.BERRY);
		Movable food4 = new Food(new Point(0, 0), new Point(0,0), FoodType.FISH);
		Movable dirt = new Dirt(new Point(0, 0), new Point(0,0));
		Movable stone = new Stone(new Point(0, 0), new Point(0,0));
		try{
			pocket.addItem(wood);
		}catch(Exception e){
			e.printStackTrace();
		}
		return pocket;
	}
}
