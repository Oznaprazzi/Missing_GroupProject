/* File: MenuFactory.java
 * 
 * Authors			ID
 * Edward Kelly 	300334192
 * 
 * Date				Author			Modification
 * 24 Sep 16		Edward Kelly	created class
 */
package missing.ui.menustyle;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.TextField;

import javax.swing.JButton;
import javax.swing.JLabel;

/**
 * Used for creating components of the menu so a set style can be followed
 *
 */
public class MenuFactory {
	public static final Color HEADING_TEXT_COLOUR = Color.WHITE;
	public static final Font HEADING_FONT = new Font("Comic Sans MS", Font.ITALIC, 40);

	public static final Color LABEL_TEXT_COLOUR = HEADING_TEXT_COLOUR;
	public static final Font LABEL_FONT = new Font("Comic Sans MS", Font.ITALIC, 20);

	public static final int TEXT_FIELD_HEIGHT = 30;

	public static final Dimension BUTTON_SIZE = new Dimension(250, 60);
	public static final Font BUTTON_FONT = new Font("Tahoma", Font.PLAIN, 20);
	public static final Color BUTTON_COLOUR = Color.YELLOW;

	private MenuFactory() {

	}

	public static JLabel createHeading(String text) {
		JLabel label = new JLabel(text);
		label.setForeground(HEADING_TEXT_COLOUR);
		label.setFont(HEADING_FONT);
		return label;
	}

	public static JLabel createLabel(String text) {
		JLabel label = new JLabel(text);
		label.setForeground(LABEL_TEXT_COLOUR);
		label.setFont(LABEL_FONT);
		return label;
	}

	public static TextField createTextField(int width) {
		TextField field = new TextField();
		field.setPreferredSize(new Dimension(width, TEXT_FIELD_HEIGHT));
		field.setFont(new Font("Arial", Font.PLAIN, TEXT_FIELD_HEIGHT - 10));
		return field;
	}

	public static JButton createButton(String text) {
		JButton btn = new JButton(text);
		btn.setFont(BUTTON_FONT);
		btn.setBackground(BUTTON_COLOUR);
		btn.setPreferredSize(BUTTON_SIZE);
		return btn;
	}
}
