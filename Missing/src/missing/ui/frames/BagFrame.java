/*	File: BagFrame.java
 * 	Author:
 * 	Casey Huang		300316284
 * 
 * 	Date			Author				changes
 * 	26 Sep 16		Casey Huang			created BagFrame class.
 *  27 Sep 16		Casey Huang			removed main method
 */
package missing.ui.frames;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import missing.game.items.Item;
import missing.game.items.movable.Food;
import missing.game.items.movable.Food.FoodType;
import missing.game.items.movable.Movable;
import missing.game.items.movable.Wood;
import missing.game.items.nonmovable.Bag;
import missing.game.items.nonmovable.Bush;
import missing.ui.canvas.BagCanvas;

public class BagFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public BagFrame(Bag bag) {
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setSize(442, 409);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		BagCanvas panel = new BagCanvas(bag);
		contentPane.add(panel, BorderLayout.CENTER);
	}

}
