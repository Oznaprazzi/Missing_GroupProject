/*	File: PileCanvasTest.java
 * 	Author:
 *  Casey Huang		300316284 - based off the BagCanvasTest.
 * 	Linus Go		300345571
 * 
 * 	Date			Author				changes
 *  6th Oct 2016	Linus Go			created Pile Canvas Test.
 */
package missing.tests;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Point;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import missing.game.items.movable.Food;
import missing.game.items.movable.Food.FoodType;
import missing.game.items.movable.Movable;
import missing.game.items.movable.Tool;
import missing.game.items.movable.Wood;
import missing.game.items.nonmovable.Container;
import missing.helper.GameException;
import missing.ui.canvas.ContainerCanvas;

/**
 * This class tests the Container functionality and ensures that it draws it correctly.
 * @author linus
 *
 */
@SuppressWarnings("serial")
public class ContainerCanvasTest extends JFrame {

	private JPanel contentPane;
	private ContainerCanvas panel;
	
	private JButton btnTransferAll;
	private JButton btnTransferSelected;

	
	
	/**
	 * Run the test.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Container testContainer= generateContainer();
					ContainerCanvasTest frame = new ContainerCanvasTest(testContainer);
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
	public ContainerCanvasTest(Container cont) {
		super("Pile of Items");
		 btnTransferAll = new JButton("Transfer All Items");
		 btnTransferSelected = new JButton("Transfer Selected Item");
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 450, 300);
		setSize(442, 409);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		//contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(btnTransferAll);
		contentPane.add(btnTransferSelected);
		panel = new ContainerCanvas(cont);
		contentPane.add(panel, BorderLayout.CENTER);
		addActionListeners();
	}
	
	private void addActionListeners(){
		btnTransferAll.addActionListener(e->{
			
		});
		
		btnTransferSelected.addActionListener(e->{
			
		});
		
	}
	
	
	
	public static Container generateContainer() throws GameException{
		Container container = new Container(new Point(0,0), new Point(0,0));
		//test that we can put in movable items.
		Movable wood = new Wood(new Point(0, 0), new Point(0,0));
		Movable food = new Food(new Point(0, 0), new Point(0,0), FoodType.APPLE);
		Movable axe  = new Tool(new Point(0, 0), new Point(0,0), Tool.ToolType.AXE, 0);
		Movable shovel = new Tool(new Point(0, 0), new Point(0,0), Tool.ToolType.SHOVEL, 0);
		container.addItem(wood);
		container.addItem(food);
		container.addItem(axe);
		container.addItem(shovel);
		
		//test that we CANNOT add NonMovable Items - it should throw an exception when trying to render it.
		//NonMovable tree = new Tree(new Point(0,0), new Point(0,0));
		//pileItems.add(tree);
	
		return container;
	}

	
}
