/*	File: MenuView.java
 * 	Authors:				ID:
 *  Linus Go				300345571
 * 	
 * 	Date:					Author					Changes
 *	14 Sep 16				Linus Go				Added the MenuView Class.
 */
package missing.ui.views;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JButton;

import missing.ui.controller.VControl;
import missing.ui.controller.VControl.View;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * This class represents the MenuView - that is the main menu that appears once the game is loaded. It contains JButtons
 * that perform actions once they have been pressed. 
 *
 */
public class MenuView extends View{
	
	private JButton btnSinglePlayer;
	private JButton btnMultiPlayer;
	private JButton btnAbout;
	private JButton btnExit;
	/**
	 * This constructs a new instance of a MenuView.
	 * @param controller
	 */
	public MenuView(VControl controller){
	super(controller);
	this.initialiseLayout();
	}
	
	/**
	 * This method initializes all of the buttons and the layout.
	 */
	private void initialiseLayout(){
		setLayout(null);
		
		btnSinglePlayer = new JButton("Single Player");
		btnSinglePlayer.setBackground(Color.YELLOW);
		btnSinglePlayer.setBounds(370, 100, 165, 45);
		add(btnSinglePlayer);
		
		btnMultiPlayer = new JButton("Multi Player");
		btnMultiPlayer.setBackground(Color.YELLOW);
		btnMultiPlayer.setBounds(370, 156, 165, 45);
		add(btnMultiPlayer);
		
		btnAbout = new JButton("About \"Missing\"");
		btnAbout.setBackground(Color.YELLOW);
		btnAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnAbout.setBounds(370, 212, 165, 45);
		add(btnAbout);
		
		btnExit = new JButton("Exit Game");
		btnExit.setBackground(Color.YELLOW);
		btnExit.setBounds(370, 269, 165, 45);
		add(btnExit);
		
		btnSinglePlayer = new JButton("Start Single Player");
		btnMultiPlayer = new JButton("Multi Player");
		btnAbout = new JButton("About Game");
		btnExit = new JButton("Exit Game");
		this.repaint();
		
	}
	
	
	
	
	
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
	
	/**This will paint onto the Menu View JFrame. */
	@Override
	public void paint(Graphics g){
	int wd = super.getPreferredSize().width;
	int ht = super.getPreferredSize().height;
	g.setColor(Color.BLACK);
	g.fillRect(0, 0, super.getPreferredSize().width, super.getPreferredSize().height);
	g.setColor(Color.WHITE);
	g.setFont(new Font("Comic Sans MS", Font.ITALIC, 36));
	g.drawString("MAIN MENU" , (wd/3), 50);
	}
	
	
	
	@Override
	public void initialise() {
	}

	@Override
	public void setFocus() {
	}
}
