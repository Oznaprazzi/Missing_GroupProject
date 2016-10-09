/* File: MenuFactory.java
 * 
 * Authors			ID
 * Edward Kelly 	300334192
 * Casey Huang    	300316284
 * 
 * Date				Author			Modification
 * 24 Sep 16		Edward Kelly	created class
 * 6 Oct 16			Casey Huang		Added another createLabel method for game menu label
 * 									- change of font size and spacing
 * 7 Oct 16			Casey Huang		Added another createHeading method for showing ClientWaitingView
 * 09 Oct 16		Casey Huang		Added another createLabel2 and createButton2
 */
package missing.ui.menustyle;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.TextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

import missing.datastorage.assetloader.GameAssets;

/**
 * Used for creating components of the menu so a set style can be followed
 *
 */
public class MenuFactory {
	public static final Color TEXT_COLOUR = Color.BLACK;
	public static final Color TEXT_COLOUR2 = Color.WHITE;
	public static final Color TEXT_COLOUR_HIGHLIGHTED = new Color(122, 169, 12);
	public static final Color TEXT_COLOUR_HIGHLIGHTED2 = new Color(199, 110, 48);
	public static final Color TEXT_COLOUR_HIGHLIGHTED3 = new Color(250, 85, 177);
	
	public static final Color VIEW_BTN_TEXT_COLOUR = new Color(121, 161, 21);
	public static final Color VIEW_BTN_BG_COLOUR = new Color(61, 86, 38);
	
	public static final int TEXT_FIELD_HEIGHT = 30;
	
	
	
	
	private MenuFactory() {
		
	}

	public static JLabel createHeading(String text) {
		JLabel label = new JLabel(text);
		label.setName(text);
		Font f = GameAssets.getFont(170f);
		label.setForeground(TEXT_COLOUR);
		label.setFont(f);
		return label;
	}
	
	public static JLabel createHeading2(String text) {
		JLabel label = new JLabel(text);
		label.setName(text);
		Font f = GameAssets.getFont(50f);
		label.setForeground(TEXT_COLOUR2);
		label.setFont(f);
		return label;
	}
	
	public static JLabel createLabel(String text) {
		JLabel label = new JLabel(text);
		label.setName(text);
		Font f = GameAssets.getFont(30f);
		label.setBorder(new EmptyBorder(0,0,25,0));
		label.setForeground(TEXT_COLOUR);
		label.setFont(f);
		return label;
	}
	
	public static JLabel createLabel2(String text) {
		JLabel label = new JLabel(text);
		label.setName(text);
		Font f = GameAssets.getFont(60f);
		label.setBorder(new EmptyBorder(50,0,55,0));
		label.setForeground(TEXT_COLOUR);
		label.setFont(f);
		return label;
	}
	
	public static JLabel createLabel3(String text) {
		JLabel label = new JLabel(text);
		label.setName(text);
		Font f = GameAssets.getFont(16f);
		//label.setBorder(new EmptyBorder(50,0,55,0));
		label.setForeground(Color.white);
		label.setFont(f);
		return label;
	}

	
	
	public static TextField createTextField(int width) {
		TextField field = new TextField();
		field.setPreferredSize(new Dimension(width, TEXT_FIELD_HEIGHT));
		Font f = GameAssets.getFont(30f);
		field.setForeground(TEXT_COLOUR);
		field.setFont(f);
		return field;
	}

	public static JButton createButton(String text) {
		JButton btn = new JButton();
		Font f = GameAssets.getFont(30f);
		btn.setFont(f);
		btn.setName(text);
		btn.setText(text);
		btn.setForeground(TEXT_COLOUR);
		btn.setOpaque(false);
		btn.setContentAreaFilled(false);
		btn.setBorderPainted(false);
		btn.setBorder(null);
		btn.setFocusPainted(false);
		btn.addMouseListener(new MouseAdapter() {
		    public void mouseEntered(MouseEvent evt) {
		    	btn.setForeground(TEXT_COLOUR_HIGHLIGHTED);
		    }

		    public void mouseExited(MouseEvent evt) {
		    	btn.setForeground(TEXT_COLOUR);
		    }
		    
		    public void mouseReleased(MouseEvent e){
		    	btn.setForeground(TEXT_COLOUR);
		    }
		    
		    public void mousePressed(MouseEvent evt){
		    	btn.setForeground(TEXT_COLOUR_HIGHLIGHTED);
		    }
		});
		return btn;
	}

	public static JButton createButton2(String text) {
		JButton btn = new JButton();
		Font f = GameAssets.getFont2(20f);
		btn.setFont(f);
		btn.setName(text);
		btn.setText(text);
		btn.setForeground(TEXT_COLOUR);
		btn.setBackground(VIEW_BTN_BG_COLOUR);
		btn.setOpaque(false);
		btn.setContentAreaFilled(false);
		btn.setBorderPainted(false);
		btn.setFocusPainted(false);
		btn.setBorder(new EmptyBorder(10, 15, 0, 15));
		btn.addMouseListener(new MouseAdapter() {
		    public void mouseEntered(MouseEvent evt) {
		    	btn.setBackground(TEXT_COLOUR_HIGHLIGHTED2);
		    	btn.setForeground(VIEW_BTN_BG_COLOUR);
		    }

		    public void mouseExited(MouseEvent evt) {
		    	btn.setBackground(VIEW_BTN_BG_COLOUR);
		    	btn.setForeground(TEXT_COLOUR);
		    }
		    
		    public void mouseReleased(MouseEvent e){
		    	btn.setBackground(VIEW_BTN_BG_COLOUR);
		    	btn.setForeground(TEXT_COLOUR);
		    }
		    
		    public void mousePressed(MouseEvent evt){
		    	btn.setBackground(TEXT_COLOUR_HIGHLIGHTED2);
		    	btn.setForeground(VIEW_BTN_BG_COLOUR);
		    }
		});
		return btn;
	}
	
	public static JButton createButton3(String text){
		JButton btn = new JButton();
		Font f = GameAssets.getFont(20f);
		btn.setFont(f);
		btn.setName(text);
		btn.setText(text);
		btn.setForeground(VIEW_BTN_TEXT_COLOUR);
		btn.setOpaque(false);
		btn.setContentAreaFilled(false);
		btn.setBorderPainted(false);
		btn.setFocusPainted(false);
		btn.setBorder(new EmptyBorder(10, 15, 0, 15));
		btn.addMouseListener(new MouseAdapter() {
		    public void mouseEntered(MouseEvent evt) {
		    	btn.setForeground(VIEW_BTN_BG_COLOUR);
		    }

		    public void mouseExited(MouseEvent evt) {
		    	btn.setForeground(VIEW_BTN_TEXT_COLOUR);
		    }
		    
		    public void mouseReleased(MouseEvent e){
		    	btn.setForeground(VIEW_BTN_TEXT_COLOUR);
		    }
		    
		    public void mousePressed(MouseEvent evt){
		    	btn.setForeground(VIEW_BTN_BG_COLOUR);
		    }
		});
		return btn;
	}
	
	public static JButton createShopButton(String text, int y, int spacing, int width) {
		JButton btn = new JButton();
		Font f = GameAssets.getFont3(40f);
		btn.setFont(f);
		btn.setName(text);
		btn.setText(text);
		btn.setForeground(TEXT_COLOUR);
		btn.setOpaque(false);
		btn.setContentAreaFilled(false);
		btn.setBorderPainted(false);
		btn.setFocusPainted(false);
		btn.setLocation((int) (width/2 - btn.getSize().getWidth()), y);
		//btn.setBounds(0, x, 80, 80);
		//btn.setAlignmentX(x);
		//btn.setAlignmentY(spacing);
		//btn.setMargin(new Insets(y, spacing, 0, spacing));
		//btn.setBorder(new EmptyBorder(y, spacing, 0, spacing));
		btn.addMouseListener(new MouseAdapter() {
		    public void mouseEntered(MouseEvent evt) {
		    	btn.setForeground(TEXT_COLOUR_HIGHLIGHTED3);
		    }

		    public void mouseExited(MouseEvent evt) {
		    	btn.setForeground(TEXT_COLOUR);
		    }
		    
		    public void mouseReleased(MouseEvent e){
		    	btn.setForeground(TEXT_COLOUR);
		    }
		    
		    public void mousePressed(MouseEvent evt){
		    	btn.setForeground(TEXT_COLOUR_HIGHLIGHTED3);
		    }
		});
		return btn;
	}
}
