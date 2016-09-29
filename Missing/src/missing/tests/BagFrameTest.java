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
import java.awt.EventQueue;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import missing.game.items.Item;
import missing.game.items.movable.Dirt;
import missing.game.items.movable.Food;
import missing.game.items.movable.Food.FoodType;
import missing.game.items.movable.Movable;
import missing.game.items.movable.Stone;
import missing.game.items.movable.Tool;
import missing.game.items.movable.Wood;
import missing.game.items.nonmovable.Bag;
import missing.game.items.nonmovable.Bush;
import missing.ui.canvas.BagCanvas;

public class BagFrameTest extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Bag bag = addItemsToBag();

					BagFrameTest frame = new BagFrameTest(bag);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public BagFrameTest(Bag bag) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setSize(442, 409);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		BagCanvas panel = new BagCanvas(bag);
		contentPane.add(panel, BorderLayout.CENTER);
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
			bag.addItem(food);
			bag.addItem(food2);
			bag.addItem(food3);
			bag.addItem(food4);
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
}
