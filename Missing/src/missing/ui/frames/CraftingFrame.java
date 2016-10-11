/*	File: CraftingCanvas.java
 * 	Author
 *  Edward Kelly	300334192
 *  
 * 	Date			Author				Changes
 *  8 Oct 16		Edward Kelly		created class
 */
package missing.ui.frames;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import missing.ui.canvas.CraftingCanvas;
import missing.ui.controller.VControl;

/** Displays Crafting window */
@SuppressWarnings("serial")
public class CraftingFrame extends JFrame {
	private JPanel contentPane;
	public CraftingCanvas canvas;

	public CraftingFrame(VControl controller) {
		super("Crafting");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setSize(442, 409);
		contentPane = new JPanel();
		contentPane.setOpaque(false);
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		canvas = new CraftingCanvas(controller, this);
		contentPane.add(canvas, BorderLayout.CENTER);

	}
}
