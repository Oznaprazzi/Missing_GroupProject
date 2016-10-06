/*	File: BagFrame.java
 * 	Author:
 * 	Casey Huang		300316284
 * 
 * 	Date			Author				changes
 * 	26 Sep 16		Casey Huang			created BagFrame class.
 *  27 Sep 16		Casey Huang			removed main method
 *  03 Oct 16		Casey Huang			added Pocket parameter.
 */
package missing.ui.frames;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import missing.game.items.nonmovable.Bag;
import missing.game.items.nonmovable.Pocket;
import missing.ui.canvas.HandCanvas;

@SuppressWarnings("serial")
public class HandJFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public HandJFrame(Bag bag, Pocket pocket) {
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setSize(442, 409);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		HandCanvas panel = new HandCanvas(bag, pocket);
		contentPane.add(panel, BorderLayout.CENTER);
	}

}
