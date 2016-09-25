/*	File: BagJFrame.java
 * 	Author
 * 	Casey Huang		300316284
 * 
 * 	Date			Author				Changes
 * 	25 Sep 16		Casey Huang			created BagJFrame.java
 */

package missing.ui.frames;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cluedo.cards.Card;
import missing.ui.canvas.CardsCanvas;
import net.miginfocom.swing.MigLayout;

/**
 * JFrame to show Cards Canvas
 * 
 * @author Casey and Linus
 *
 */
public class BagJFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public BagJFrame(List<Card> cards) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow]", "[grow]"));
		
		CardsCanvas panel = new CardsCanvas(cards);
		contentPane.add(panel, "cell 0 0,grow");
	}
}
